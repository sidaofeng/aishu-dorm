package com.waken.dorm.serviceImpl.student;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.waken.dorm.common.base.AjaxResponse;
import com.waken.dorm.common.constant.CacheConstant;
import com.waken.dorm.common.constant.Constant;
import com.waken.dorm.common.entity.student.Student;
import com.waken.dorm.common.entity.student.StudentInfo;
import com.waken.dorm.common.enums.CodeEnum;
import com.waken.dorm.common.enums.ResultEnum;
import com.waken.dorm.common.exception.ServerException;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.student.EditStudentForm;
import com.waken.dorm.common.form.student.StudentForm;
import com.waken.dorm.common.manager.StudentManager;
import com.waken.dorm.common.manager.UserManager;
import com.waken.dorm.common.sequence.UUIDSequence;
import com.waken.dorm.common.utils.*;
import com.waken.dorm.common.utils.redis.RedisCacheManager;
import com.waken.dorm.common.view.base.ImgView;
import com.waken.dorm.common.view.student.StudentView;
import com.waken.dorm.dao.student.StudentMapper;
import com.waken.dorm.handle.DataHandle;
import com.waken.dorm.service.student.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @ClassName StudentServiceImpl
 * @Description StudentServiceImpl
 * @Author zhaoRong
 * @Date 2019/3/29 21:22
 **/
@Slf4j
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StudentServiceImpl implements StudentService {
    private final StudentMapper studentMapper;
    private final StudentManager studentManager;
    private final RedisCacheManager redisCacheManager;
    private final AliyunOSSUtil aliyunOSSUtil;

    /**
     * 批量新增学生
     */
    @Transactional
    @Override
    public int batchAddStudent(List<Student> studentList) {

        studentList.stream().forEach(student -> student.setStatus(CodeEnum.ENABLE.getCode()));

        return studentMapper.batchAddStudent(studentList);
    }

    /**
     * （保存或修改）单个学生信息
     *
     * @param editStudentForm
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Student saveStudent(EditStudentForm editStudentForm) {
        this.editStudentValidate(editStudentForm);
        Student student = new Student();
        BeanMapper.copy(editStudentForm, student);
        //新增
        if (StringUtils.isEmpty(editStudentForm.getPkStudentId())) {
            log.info("service: 开始进入单个添加学生");
            String studentId = UUIDSequence.next();
            student.setPkStudentId(studentId);
            if (editStudentForm.getGender() == null) {
                //默认性别男
                student.setGender(CodeEnum.MALE.getCode());
            } else {
                student.setGender(editStudentForm.getGender());
            }
            student.setStatus(CodeEnum.ENABLE.getCode());
            studentMapper.insert(student);
            return student;
        } else {//修改
            log.info("service: 开始进入修改学生信息");
            studentMapper.updateById(student);
            return studentMapper.selectById(editStudentForm.getPkStudentId());
        }
    }

    /**
     * 删除学生信息
     *
     * @param deleteForm
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteStudent(DeleteForm deleteForm) {
        log.info("service: 删除学生开始");
        List<String> studentIds = deleteForm.getDelIds();
        Integer delStatus = deleteForm.getDelStatus();
        // 物理删除
        if (CodeEnum.YES.getCode().equals(delStatus)) {

            List<Student> studentList = studentMapper.selectBatchIds(studentIds);
            StringBuffer sb = new StringBuffer();
            for (Student student : studentList) {
                if (CodeEnum.ENABLE.getCode() == student.getStatus()) {
                    sb.append(student.getStudentName());
                }
            }
            //删除学生
            if (StringUtils.isEmpty(sb.toString())) {
                studentMapper.deleteBatchIds(studentIds);
            } else {
                throw new ServerException("以下学生处于生效中：" + sb.toString());
            }

        } else if (CodeEnum.NO.getCode().equals(delStatus)) {
            Map updateStatusMap = DormUtil.getToUpdateStatusMap(studentIds, UserManager.getCurrentUserId());
            studentMapper.batchUpdateStatus(updateStatusMap);
        } else {
            throw new ServerException("删除状态码错误！");
        }
    }

    /**
     * 分页查询学生信息
     *
     * @param studentForm
     * @return
     */
    @Override
    public IPage<StudentView> listStudents(StudentForm studentForm) {

        return studentMapper.listStudents(DataHandle.getWrapperPage(studentForm),studentForm);
    }

    /**
     * 学生登陆
     *
     * @param studentNum
     * @param password
     * @return
     */
    @Override
    public AjaxResponse studentLogin(Integer studentNum, String password) {
        log.info("service: 开始进入学生登陆");
        Student student= studentMapper.selectOne(new LambdaQueryWrapper<Student>()
                .eq(Student::getStudentNum, studentNum)
        );
        if (null == student){
            return AjaxResponse.error("登录失败，学号错误！");
        }
        if (StringUtils.isEmpty(student.getPassword())) {
            String studentNumStr = String.valueOf(studentNum);
            if (StringUtils.equals(studentNumStr, password)) {
                return AjaxResponse.success(ResultEnum.FIRST_LOGIN.getCode(),student);
            } else {
                return AjaxResponse.error("登录失败，初始密码错误！");
            }
        } else {
            String passwordMd5 = Md5Utils.encodeByMD5(password);
            Student studentL = new Student();
            studentL.setStudentNum(studentNum);
            studentL.setPassword(passwordMd5);
            StudentInfo studentInfo = studentMapper.studentLogin(studentL);
            Assert.notNull(studentInfo,"密码错误!");
            String studentToken = this.getStudentToken(studentInfo);
            studentInfo.setStudentToken(studentToken);
            return AjaxResponse.success(studentInfo);
        }
    }

    /**
     * 第一次登陆后必须先设置新密码
     *
     * @param newPassword
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePasswordByNew(String newPassword) {
        log.info("service : 开始进入修改密码");
        Student student = studentMapper.selectById(studentManager.getCurrentStudentId());
        Assert.notNull(student,"设置新密码失败");
        String password = Md5Utils.encodeByMD5(newPassword);
        student.setPassword(password);
        int count = studentMapper.updateById(student);
        Assert.isFalse(count == Constant.ZERO);
        studentManager.delCacheStudentInfo();
    }

    /**
     * 上传头像
     *
     * @param file
     * @param studentId
     * @return
     */
    @Override
    @Transactional
    public ImgView uploadHeadImg(MultipartFile file, String studentId) {
        String fileName = file.getOriginalFilename();
        Assert.notNull(fileName);
        String folderName = Constant.STUDENT;
        try {
            Student updateStuff = studentMapper.selectById(studentId);
            if (!StringUtils.isEmpty(updateStuff.getImgUrl())) {
                // 删除已经存在的头像
                aliyunOSSUtil.deleteFile(updateStuff.getImgUrl());
            }
            File toFile = FileUtils.multipartFileToFile(file);
            // 上传到OSS
            String headImgUrl = aliyunOSSUtil.upLoad(toFile, folderName);
            Assert.notNull(headImgUrl,"上传失败");
            updateStuff.setImgUrl(headImgUrl);
            studentMapper.updateById(updateStuff);
            log.info("学生头像访问路径：" + headImgUrl);
            ImgView imgView = new ImgView();
            imgView.setImgUrl(headImgUrl);
            return imgView;
        } catch (Exception e) {
            e.printStackTrace();
            log.info("学生头像上传失败，原因是：" + e.getMessage());
        }
        return null;
    }

    /**
     * 获取学生登陆token
     *
     * @param studentInfo
     * @return
     */
    private String getStudentToken(StudentInfo studentInfo) {
        //TODO 暂且用uuid作为学生token，后面按具体需求修改
        String studentToken = UUIDSequence.next();
        studentInfo.setStudentToken(studentToken);
        // 缓存时间
        long redisCacheTime = 10000 * 60 * 30;
        //redis缓存
        redisCacheManager.setEx(CacheConstant.STUDENT_CACHE_PREFIX + studentToken, studentInfo, redisCacheTime);
        return studentToken;
    }

    /**
     * 编辑资源时验证
     *
     * @param editForm
     */
    private void editStudentValidate(EditStudentForm editForm) {
        //新增验证
        if (StringUtils.isEmpty(editForm.getPkStudentId())) {
            Assert.notNull(editForm.getStudentName());
            Assert.notNull(editForm.getStudentNum());
            Assert.notNull(editForm.getMobile());
            Assert.isTrue(CheckUtils.isPhoneLegality(editForm.getMobile()),"请输入正确的手机号！");
            Student student = studentMapper.selectOne(new LambdaQueryWrapper<Student>()
                    .eq(Student::getStudentName, editForm.getStudentName())
                    .or()
                    .eq(Student::getStudentNum, editForm.getStudentNum())
            );
            Assert.isNull(student,"已经存在相同姓名或学号的学生！");
        } else {
            Student student = studentMapper.selectById(editForm.getPkStudentId());
            Assert.notNull(student,"参数错误");
        }
    }
}
