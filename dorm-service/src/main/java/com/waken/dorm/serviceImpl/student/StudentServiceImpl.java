package com.waken.dorm.serviceImpl.student;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.waken.dorm.common.base.ResultView;
import com.waken.dorm.common.constant.Constant;
import com.waken.dorm.common.entity.student.Student;
import com.waken.dorm.common.entity.student.StudentInfo;
import com.waken.dorm.common.enums.CodeEnum;
import com.waken.dorm.common.enums.ResultEnum;
import com.waken.dorm.common.exception.ServerException;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.student.EditStudentForm;
import com.waken.dorm.common.form.student.StudentForm;
import com.waken.dorm.common.utils.*;
import com.waken.dorm.common.utils.redis.RedisCacheManager;
import com.waken.dorm.common.view.base.ImgView;
import com.waken.dorm.common.view.student.StudentView;
import com.waken.dorm.dao.student.StudentMapper;
import com.waken.dorm.manager.UserManager;
import com.waken.dorm.service.student.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName StudentServiceImpl
 * @Description StudentServiceImpl
 * @Author zhaoRong
 * @Date 2019/3/29 21:22
 **/
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
@Service
public class StudentServiceImpl implements StudentService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    RedisCacheManager redisCacheManager;
    @Autowired
    AliyunOSSUtil aliyunOSSUtil;

    /**
     * 批量新增学生
     */
    @Transactional
    @Override
    public List<String> batchAddStudent(List<Student> studentList) {
        Date curDate = DateUtils.getCurrentDate();
        String userId = UserManager.getCurrentUserId();
        List<String> studentIds = new ArrayList<>();
        for (Student student : studentList) {
            String studentId = UUIDUtils.getPkUUID();
            studentIds.add(studentId);
            student.setPkStudentId(studentId);
            student.setStatus(CodeEnum.ENABLE.getCode());
            student.setCreateTime(curDate);
            student.setCreateUserId(userId);
            student.setLastModifyTime(curDate);
            student.setLastModifyUserId(userId);
        }
        int count = Constant.ZERO;
        count = studentMapper.batchAddStudent(studentList);
        if (count == Constant.ZERO) {
            throw new ServerException("mysql 批量新增失败");
        }
        return studentIds;
    }

    /**
     * （保存或修改）单个学生信息
     *
     * @param editStudentForm
     * @return
     */
    @Override
    @Transactional
    public Student saveStudent(EditStudentForm editStudentForm) {
        this.editStudentValidate(editStudentForm);
        Date curDate = DateUtils.getCurrentDate();
        String userId = UserManager.getCurrentUserId();
        Student student = new Student();
        BeanMapper.copy(editStudentForm, student);
        student.setLastModifyTime(curDate);
        student.setLastModifyUserId(userId);
        if (StringUtils.isEmpty(editStudentForm.getPkStudentId())) {//新增
            logger.info("service: 开始进入单个添加学生");
            String studentId = UUIDUtils.getPkUUID();
            student.setPkStudentId(studentId);
            if (editStudentForm.getGender() == null) {
                student.setGender(CodeEnum.MALE.getCode());//默认性别男
            } else {
                student.setGender(editStudentForm.getGender());
            }
            student.setStatus(CodeEnum.ENABLE.getCode());
            student.setCreateTime(curDate);
            student.setCreateUserId(userId);
            int count = Constant.ZERO;
            count = studentMapper.insert(student);
            if (count == Constant.ZERO) {
                throw new ServerException("添加单个学生失败");
            }
            return student;
        } else {//修改
            logger.info("service: 开始进入修改学生信息");
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
    @Transactional
    public void deleteStudent(DeleteForm deleteForm) {
        logger.info("service: 删除学生开始");
        List<String> studentIds = deleteForm.getDelIds();
        Integer delStatus = deleteForm.getDelStatus();
        int count = Constant.ZERO;
        if (CodeEnum.YES.getCode() == delStatus) { // 物理删除
            List<Student> studentList = studentMapper.selectByIds(studentIds);
            StringBuffer sb = new StringBuffer();
            for (Student student : studentList) {
                if (CodeEnum.ENABLE.getCode() == student.getStatus()) {
                    sb.append(student.getStudentName());
                }
            }
            if (StringUtils.isEmpty(sb.toString())) {//删除学生
                count = studentMapper.deleteBatchIds(studentIds);
                if (count == Constant.ZERO) {
                    throw new ServerException("删除学生个数为 0 条");
                }
            } else {
                throw new ServerException("以下学生处于生效中：" + sb.toString());
            }

        } else if (CodeEnum.NO.getCode() == delStatus) {
            Map updateStatusMap = DormUtil.getToUpdateStatusMap(studentIds, UserManager.getCurrentUserId());
            count = studentMapper.batchUpdateStatus(updateStatusMap);
            if (count == Constant.ZERO) {
                throw new ServerException("状态删除失败");
            }
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
    public PageInfo<StudentView> listStudents(StudentForm studentForm) {
        logger.info("service: 分页查询宿舍评分信息开始");
        if (studentForm.getStartTime() != null) {
            studentForm.setStartTime(DateUtils.formatDate2DateTimeStart(studentForm.getStartTime()));
        }
        if (studentForm.getEndTime() != null) {
            studentForm.setEndTime(DateUtils.formatDate2DateTimeEnd(studentForm.getEndTime()));
        }
        PageHelper.startPage(studentForm.getPageNum(), studentForm.getPageSize());
        List<StudentView> studentViews = studentMapper.listStudents(studentForm);
        return new PageInfo<StudentView>(studentViews);
    }

    /**
     * 学生登陆
     *
     * @param studentNum
     * @param password
     * @return
     */
    @Override
    public ResultView studentLogin(Integer studentNum, String password) {
        logger.info("service: 开始进入学生登陆");
        ResultView resultView = new ResultView();
        List<Student> studentList = studentMapper.selectList(new EntityWrapper<Student>()
                .eq("student_num", studentNum)
        );
        if (studentList.isEmpty()) {
            logger.info("登陆失败，学号错误！");
            resultView.setCode(ResultEnum.FAIL.getCode());
            resultView.setMsg("登录失败，学号错误！");
            return resultView;
        } else {
            if (StringUtils.isEmpty(studentList.get(Constant.ZERO).getPassword())) {
                String studentNumStr = String.valueOf(studentNum);
                if (StringUtils.equals(studentNumStr, password)) {
                    logger.info("第一次登陆成功");
                    resultView.setCode(ResultEnum.FIRST_LOGIN.getCode());
                    resultView.setData(studentList.get(Constant.ZERO));
                    resultView.setMsg("第一次登录成功！");
                    return resultView;
                } else {
                    logger.info("登陆失败，初始密码错误！");
                    resultView.setCode(ResultEnum.FAIL.getCode());
                    resultView.setMsg("登录失败，初始密码错误！");
                    return resultView;
                }
            } else {
                String passwordMd5 = Md5Utils.encodeByMD5(password);
                Student student = new Student();
                student.setStudentNum(studentNum);
                student.setPassword(passwordMd5);
                StudentInfo studentInfo = studentMapper.studentLogin(student);
                if (studentInfo == null) {
                    logger.info("登陆失败，密码错误！");
                    resultView.setCode(ResultEnum.FAIL.getCode());
                    resultView.setMsg("登录失败，密码错误！");
                    return resultView;
                } else {
                    String studentToken = this.getStudentToken(studentInfo);
                    studentInfo.setStudentToken(studentToken);
                    resultView.setData(studentInfo);
                    logger.info("登陆成功");
                    resultView.setCode(ResultEnum.SUCCESS.getCode());
                    resultView.setMsg("登录成功！");
                    return resultView;
                }
            }
        }
    }

    /**
     * 第一次登陆后必须先设置新密码
     *
     * @param studentId
     * @param newPassword
     */
    @Override
    @Transactional
    public void updatePasswordByNew(String studentId, String newPassword) {
        logger.info("service : 开始进入修改密码");
        Student student = studentMapper.selectById(studentId);
        if (student == null) {
            logger.info("service：设置新密码失败");
            throw new ServerException("service：设置新密码失败");
        } else {
            String password = Md5Utils.encodeByMD5(newPassword);
            student.setPassword(password);
            int count = Constant.ZERO;
            count = studentMapper.updateById(student);
            if (count == Constant.ZERO) {
                throw new ServerException("更新的个数为 0 条！");
            }
        }
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
        String folderName = Constant.STUDENT;
        if (StringUtils.isEmpty(fileName)) {
            throw new ServerException("您上传的头像图片文件为空！");
        }
        try {
            Student updateStuff = studentMapper.selectById(studentId);
            if (!StringUtils.isEmpty(updateStuff.getImgUrl())) {
                aliyunOSSUtil.deleteFile(updateStuff.getImgUrl());// 删除已经存在的头像
                logger.info("删除已经存在的学生头像成功！");
            }
            File toFile = FileUtils.multipartFileToFile(file);
            // 上传到OSS
            String headImgUrl = aliyunOSSUtil.upLoad(toFile, folderName);
            if (StringUtils.isEmpty(headImgUrl)) {
                throw new ServerException("上传学生头像失败！");
            }
            updateStuff.setImgUrl(headImgUrl);
            studentMapper.updateById(updateStuff);
            logger.info("学生头像访问路径：" + headImgUrl);
            ImgView imgView = new ImgView();
            imgView.setImgUrl(headImgUrl);
            return imgView;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("学生头像上传失败，原因是：" + e.getMessage());
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
        String studentToken = UUIDUtils.getPkUUID();
        studentInfo.setStudentToken(studentToken);
        long redisCacheTime = 10000 * 60 * 30;// 缓存时间
        redisCacheManager.setEx(Constant.STUDENT_CACHE_PREFIX + studentToken, studentInfo, redisCacheTime);//redis缓存
        logger.info("生成学生studentToken为：" + studentToken);
        return studentToken;
    }

    /**
     * 编辑资源时验证
     *
     * @param editStudentForm
     */
    private void editStudentValidate(EditStudentForm editStudentForm) {
        if (StringUtils.isEmpty(editStudentForm.getPkStudentId())) {//新增验证
            StringBuffer sb = new StringBuffer();
            if (StringUtils.isEmpty(editStudentForm.getStudentName())) {
                sb.append("学生姓名为空！");
            }
            if (editStudentForm.getStudentNum() != null) {
                sb.append("学号为空！");
            }
            if (StringUtils.isEmpty(editStudentForm.getMobile())) {
                sb.append("手机号码不能为空！");
            }
            if (StringUtils.isNotEmpty(sb.toString())) {
                logger.info("新增学生失败,原因是：" + sb.toString());
                throw new ServerException(sb.toString());
            }
            List<Student> StudentList = studentMapper.selectList(new EntityWrapper<Student>()
                    .eq("student_name", editStudentForm.getStudentName())
                    .or()
                    .eq("student_num", editStudentForm.getStudentNum())
            );
            if (!StudentList.isEmpty()) {
                throw new ServerException("已经存在相同姓名或学号的学生！");
            }
        } else {
            Student student = studentMapper.selectById(editStudentForm.getPkStudentId());
            if (student == null) {
                throw new ServerException("学生id无效");
            }
        }
    }
}
