package com.waken.dorm.serviceImpl.student;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.waken.dorm.common.base.ResultView;
import com.waken.dorm.common.constant.Constant;
import com.waken.dorm.common.entity.student.Student;
import com.waken.dorm.common.entity.student.StudentExample;
import com.waken.dorm.common.entity.student.StudentInfo;
import com.waken.dorm.common.enums.CodeEnum;
import com.waken.dorm.common.enums.ResultEnum;
import com.waken.dorm.common.exception.DormException;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.student.EditStudentForm;
import com.waken.dorm.common.form.student.StudentForm;
import com.waken.dorm.common.utils.*;
import com.waken.dorm.common.utils.excel.ExcelUtil;
import com.waken.dorm.common.utils.redis.RedisCacheManager;
import com.waken.dorm.common.view.base.ImgView;
import com.waken.dorm.common.view.student.StudentView;
import com.waken.dorm.dao.student.StudentMapper;
import com.waken.dorm.service.school.SchoolService;
import com.waken.dorm.service.student.StudentService;
import com.waken.dorm.serviceImpl.base.BaseServerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName StudentServiceImpl
 * @Description StudentServiceImpl
 * @Author zhaoRong
 * @Date 2019/3/29 21:22
 **/
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
@Service
public class StudentServiceImpl extends BaseServerImpl implements StudentService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    RedisCacheManager redisCacheManager;
    @Autowired
    SchoolService schoolService;
    @Autowired
    AliyunOSSUtil aliyunOSSUtil;
    /**
     * 批量新增学生
     */
    @Transactional
    @Override
    public List<String> batchAddStudent(MultipartFile multipartFile) {
        ExcelUtil<Student> excelUtil = new ExcelUtil<>(Student.class);
        List<Student> studentList = excelUtil.importExcel(multipartFile,Constant.ONE);
        Date curDate = DateUtils.getCurrentDate();
        String userId = ShiroUtils.getUserId();
        String schoolId = schoolService.getSchoolIdByUserId(userId);
        List<String> studentIds = new ArrayList<>();
        for (Student student : studentList) {
            String studentId = UUIDUtils.getPkUUID();
            studentIds.add(studentId);
            student.setPkStudentId(studentId);
            student.setSchoolId(schoolId);
            student.setStatus(CodeEnum.ENABLE.getCode());
            student.setCreateTime(curDate);
            student.setCreateUserId(userId);
            student.setLastModifyTime(curDate);
            student.setLastModifyUserId(userId);
        }
        int count = Constant.ZERO;
        count = studentMapper.batchAddStudent(studentList);
        if (count == Constant.ZERO){
            throw new DormException("mysql 批量新增失败");
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
        if (StringUtils.isEmpty(editStudentForm.getPkStudentId())){//新增
            this.editStudentValidate(editStudentForm);
            logger.info("service: 开始进入单个添加学生");
            String studentId = UUIDUtils.getPkUUID();
            Date curDate = DateUtils.getCurrentDate();
            String userId = ShiroUtils.getUserId();
            Student student = new Student();
            student.setPkStudentId(studentId);
            student.setStudentName(editStudentForm.getStudentName());
            student.setStudentNum(editStudentForm.getStudentNum());
            if (StringUtils.isNotEmpty(editStudentForm.getEmail())){
                student.setEmail(editStudentForm.getEmail());
            }
            if (editStudentForm.getGender() == null){
                student.setGender(CodeEnum.MALE.getCode());//默认性别男
            }else {
                student.setGender(editStudentForm.getGender());
            }
            student.setMobile(editStudentForm.getMobile());
            student.setStatus(CodeEnum.ENABLE.getCode());
            student.setCreateTime(curDate);
            student.setCreateUserId(userId);
            student.setLastModifyTime(curDate);
            student.setLastModifyUserId(userId);
            int count = Constant.ZERO;
            count = studentMapper.insertSelective(student);
            if (count == Constant.ZERO){
                throw new DormException("添加单个学生失败");
            }
        }else {//修改
            logger.info("service: 开始进入修改学生信息");
            Student student = studentMapper.selectByPrimaryKey(editStudentForm.getPkStudentId());
            if (student == null){
                throw new DormException("学生id无效");
            }
            if (StringUtils.isNotEmpty(editStudentForm.getEmail())){
                student.setEmail(editStudentForm.getEmail());
            }
            if (StringUtils.isNotEmpty(editStudentForm.getMobile())){
                student.setMobile(editStudentForm.getMobile());
            }
            if (editStudentForm.getGender() != null){
                student.setGender(editStudentForm.getGender());
            }
            if (editStudentForm.getStudentNum() != null){
                student.setStudentNum(editStudentForm.getStudentNum());
            }
            String userId = ShiroUtils.getUserId();
            Date curDate = DateUtils.getCurrentDate();
            student.setLastModifyTime(curDate);
            student.setLastModifyUserId(userId);
            studentMapper.updateByPrimaryKeySelective(student);
        }
        return null;
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
        if (CodeEnum.YES.getCode() == delStatus){ // 物理删除
            List<Student> studentList = studentMapper.selectByIds(studentIds);
            StringBuffer sb = new StringBuffer();
            for (Student student : studentList){
                if (CodeEnum.ENABLE.getCode() == student.getStatus()){
                    sb.append(student.getStudentName());
                }
            }
            if (StringUtils.isEmpty(sb.toString())){//删除学生
                StudentExample example = new StudentExample();
                StudentExample.Criteria criteria = example.createCriteria();
                criteria.andPkStudentIdIn(studentIds);
                count = studentMapper.deleteByExample(example);
                if (count == Constant.ZERO){
                    throw new DormException("删除学生个数为 0 条");
                }
            }else {
                throw new DormException("以下学生处于生效中："+sb.toString());
            }

        }else if(CodeEnum.NO.getCode() == delStatus){
            count = studentMapper.batchUpdateStatus(getToUpdateStatusMap(studentIds,CodeEnum.DELETE.getCode()));
            if (count == Constant.ZERO){
                throw new DormException("状态删除失败");
            }
        }else {
            throw new DormException("删除状态码错误！");
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
        if (StringUtils.isEmpty(studentForm.getSchoolId())) {
            String userId = ShiroUtils.getUserId();
            String schoolId = schoolService.getSchoolIdByUserId(userId);
            studentForm.setSchoolId(schoolId);
        }
        if (studentForm.getStartTime() != null) {
            studentForm.setStartTime(DateUtils.formatDate2DateTimeStart(studentForm.getStartTime()));
        }
        if (studentForm.getEndTime() != null) {
            studentForm.setEndTime(DateUtils.formatDate2DateTimeEnd(studentForm.getEndTime()));
        }
        PageHelper.startPage(studentForm.getPageNum(),studentForm.getPageSize());
        List<StudentView> studentViews = studentMapper.listStudents(studentForm);
        return new PageInfo<StudentView>(studentViews);
    }

    /**
     * 学生登陆
     * @param studentNum
     * @param password
     * @return
     */
    @Override
    public ResultView studentLogin(Integer studentNum, String password) {
        logger.info("service: 开始进入学生登陆");
        ResultView resultView = new ResultView();
        StudentExample example = new StudentExample();
        StudentExample.Criteria criteria = example.createCriteria();
        criteria.andStudentNumEqualTo(studentNum);
        List<Student> studentList = studentMapper.selectByExample(example);
        if (studentList.isEmpty()){//TODO 后续加上学校的验证
            logger.info("登陆失败，学号错误！");
            resultView.setCode(ResultEnum.FAIL.getCode());
            resultView.setMsg("登录失败，学号错误！");
            return resultView;
        }else{
            if (StringUtils.isEmpty(studentList.get(Constant.ZERO).getPassword())){
                String studentNumStr = String.valueOf(studentNum);
                if (StringUtils.equals(studentNumStr,password)){
                    logger.info("第一次登陆成功");
                    resultView.setCode(ResultEnum.FIRST_LOGIN.getCode());
                    resultView.setData(studentList.get(Constant.ZERO));
                    resultView.setMsg("第一次登录成功！");
                    return resultView;
                }else {
                    logger.info("登陆失败，初始密码错误！");
                    resultView.setCode(ResultEnum.FAIL.getCode());
                    resultView.setMsg("登录失败，初始密码错误！");
                    return resultView;
                }
            }else {
                String passwordMd5 = Md5Utils.encodeByMD5(password);
                Student student = new Student();
                student.setStudentNum(studentNum);
                student.setPassword(passwordMd5);
                StudentInfo studentInfo = studentMapper.studentLogin(student);
                if (studentInfo == null){
                    logger.info("登陆失败，密码错误！");
                    resultView.setCode(ResultEnum.FAIL.getCode());
                    resultView.setMsg("登录失败，密码错误！");
                    return resultView;
                }else {
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
     *@param studentId
     * @param newPassword
     */
    @Override
    @Transactional
    public void updatePasswordByNew(String studentId,String newPassword) {
        logger.info("service : 开始进入修改密码");
        Student student = studentMapper.selectByPrimaryKey(studentId);
        if (student == null){
            logger.info("service：设置新密码失败");
            throw new DormException("service：设置新密码失败");
        }else {
            String password = Md5Utils.encodeByMD5(newPassword);
            student.setPassword(password);
            int count = Constant.ZERO;
            count = studentMapper.updateByPrimaryKeySelective(student);
            if (count == Constant.ZERO){
                throw new DormException("更新的个数为 0 条！");
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
            throw new DormException("您上传的头像图片文件为空！");
        }
        try {
            Student updateStuff = studentMapper.selectByPrimaryKey(studentId);
            if (!StringUtils.isEmpty(updateStuff.getImgUrl())) {
                aliyunOSSUtil.deleteFile(updateStuff.getImgUrl());// 删除已经存在的头像
                logger.info("删除已经存在的学生头像成功！");
            }
            File toFile = FileUtils.multipartFileToFile(file);
            // 上传到OSS
            String headImgUrl = aliyunOSSUtil.upLoad(toFile, folderName);
            if (StringUtils.isEmpty(headImgUrl)) {
                throw new DormException("上传学生头像失败！");
            }
            updateStuff.setImgUrl(headImgUrl);
            studentMapper.updateByPrimaryKeySelective(updateStuff);
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
     * @param studentInfo
     * @return
     */
    private String getStudentToken(StudentInfo studentInfo){
        logger.info("开始获取学生studentToken");
        String studentToken = UUIDUtils.getPkUUID();
        studentInfo.setStudentToken(studentToken);
        long redisCacheTime = 10000 * 60 * 30;// 缓存时间
        redisCacheManager.setEx(studentToken, studentInfo, redisCacheTime);//redis缓存
        logger.info("生成学生studentToken为：" + studentToken);
        return studentToken;
    }
    /**
     * 编辑资源时验证
     * @param editStudentForm
     */
    private void editStudentValidate(EditStudentForm editStudentForm){
        if (StringUtils.isEmpty(editStudentForm.getPkStudentId())){//新增验证
            StringBuffer sb = new StringBuffer();
            if(StringUtils.isEmpty(editStudentForm.getStudentName())){
                sb.append("学生姓名为空！");
            }
            if(editStudentForm.getStudentNum() != null){
                sb.append("学号为空！");
            }
            if (StringUtils.isEmpty(editStudentForm.getMobile())){
                sb.append("手机号码不能为空！");
            }
            if(StringUtils.isNotEmpty(sb.toString())){
                logger.info("新增学生失败,原因是：" + sb.toString());
                throw new DormException(sb.toString());
            }
            StudentExample example = new StudentExample();
            StudentExample.Criteria criteria = example.createCriteria();
            criteria.andStudentNameEqualTo(editStudentForm.getStudentName());
            StudentExample.Criteria orCriteria = example.createCriteria();
            orCriteria.andStudentNumEqualTo(editStudentForm.getStudentNum());
            example.or(orCriteria);
            List<Student> StudentList = studentMapper.selectByExample(example);
            if (!StudentList.isEmpty()){
                throw new DormException("已经存在相同姓名或学号的学生！");
            }
        }
    }
}
