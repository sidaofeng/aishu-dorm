package com.waken.dorm.serviceImpl.student;

import com.aliyun.oss.ServiceException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.waken.dorm.common.base.AjaxResponse;
import com.waken.dorm.common.constant.CacheConstant;
import com.waken.dorm.common.constant.Constant;
import com.waken.dorm.common.entity.student.StudentBasic;
import com.waken.dorm.common.entity.student.StudentInfo;
import com.waken.dorm.common.enums.CodeEnum;
import com.waken.dorm.common.enums.ResultEnum;
import com.waken.dorm.common.exception.ServerException;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.student.StudentBasicForm;
import com.waken.dorm.common.manager.StudentManager;
import com.waken.dorm.common.sequence.UUIDSequence;
import com.waken.dorm.common.utils.*;
import com.waken.dorm.common.utils.redis.RedisCacheManager;
import com.waken.dorm.common.view.base.ImgView;
import com.waken.dorm.common.view.student.StudentBasicView;
import com.waken.dorm.dao.student.StudentBasicMapper;
import com.waken.dorm.handle.DataHandle;
import com.waken.dorm.service.student.StudentBasicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 学生基本数据表 服务实现类
 * </p>
 *
 * @author zhaoRong
 * @since 2019-11-21
 */
@Slf4j
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StudentBasicServiceImpl extends ServiceImpl<StudentBasicMapper, StudentBasic> implements StudentBasicService {
    private final StudentManager studentManager;
    private final RedisCacheManager redisCacheManager;
    private final AliyunOSSUtil aliyunOSSUtil;

    @Override
    public int insert(StudentBasic basic) {
        int result = 0;
        if (basic == null) {
            throw new ServiceException("入参数据为空");
        }
        if (this.verification(basic) == 0) {
            result = this.baseMapper.insert(basic);
        }
        return result;
    }

    @Override
    public void delete(DeleteForm deleteForm) {
        if (deleteForm == null || deleteForm.getDelIds() == null) {
            throw new ServiceException("入参数据为空");
        }
        if (!deleteForm.getDelIds().isEmpty()) {
            //物理删除
            if (deleteForm.getDelStatus() == CodeEnum.YES.getCode()) {
                this.baseMapper.deleteBatchIds(deleteForm.getDelIds());
            } else if (deleteForm.getDelStatus() == CodeEnum.NO.getCode()) {
                List<StudentBasic> basicList = new ArrayList<>();
                for (String id : deleteForm.getDelIds()) {
                    StudentBasic basic = new StudentBasic();
                    basic.setId(id);
                    basic.setIsDeleted(true);
                    basicList.add(basic);
                }
                this.updateBatchById(basicList);
            } else {
                throw new ServerException("删除状态码错误");
            }

        }
    }

    @Override
    public int update(StudentBasic basic) {
        int result = 0;
        if (basic == null) {
            throw new ServiceException("入参数据为空");
        }
        if (this.verification(basic) == 0) {
            result = this.baseMapper.updateById(basic);
        }
        return result;
    }

    @Override
    public StudentBasic get(String id) {
        if (StringUtils.isEmpty(id)) {
            throw new ServiceException("入参数据为空");
        }
        return this.baseMapper.selectById(id);
    }

    @Override
    public IPage<StudentBasicView> findPage(StudentBasicForm basicForm) {

        //TODO
        return this.baseMapper.findPage(DataHandle.getWrapperPage(basicForm), basicForm);
    }

    @Override
    public void batchInsert(List<StudentBasic> basicList) {
        Assert.notNull(basicList, basicList.isEmpty(), "参数为空！");
        if (!this.saveBatch(basicList)) {
            throw new ServerException("批量新增失败");
        }
    }

    /**
     * 学生登陆
     *
     * @param studentCode
     * @param password
     * @return
     */
    @Override
    public AjaxResponse studentLogin(String studentCode, String password) {
        StudentBasic student = this.baseMapper.selectOne(new LambdaQueryWrapper<StudentBasic>()
                .eq(StudentBasic::getCode, studentCode)
        );
        if (null == student) {
            return AjaxResponse.error("登录失败，学号错误！");
        }
        if (StringUtils.isEmpty(student.getPassword())) {
            if (StringUtils.equals(studentCode, password)) {
                return AjaxResponse.success(ResultEnum.FIRST_LOGIN.getCode(), student);
            } else {
                return AjaxResponse.error("登录失败，初始密码错误！");
            }
        } else {
            String passwordMd5 = Md5Utils.encodeByMD5(password);
            StudentBasic basic = new StudentBasic();
            basic.setCode(studentCode);
            basic.setPassword(passwordMd5);
            StudentInfo studentInfo = this.baseMapper.studentLogin(studentCode, passwordMd5);
            Assert.notNull(studentInfo, "密码错误!");
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

        StudentBasic student = this.baseMapper.selectById(studentManager.getCurrentStudentId());
        Assert.notNull(student, "设置新密码失败");
        String password = Md5Utils.encodeByMD5(newPassword);
        student.setPassword(password);
        int count = this.baseMapper.updateById(student);
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
            StudentBasic updateStuff = this.baseMapper.selectById(studentId);
            if (!StringUtils.isEmpty(updateStuff.getImgUrl())) {
                // 删除已经存在的头像
                aliyunOSSUtil.deleteFile(updateStuff.getImgUrl());
            }
            File toFile = FileUtils.multipartFileToFile(file);
            // 上传到OSS
            String headImgUrl = aliyunOSSUtil.upLoad(toFile, folderName);
            Assert.notNull(headImgUrl, "上传失败");
            updateStuff.setImgUrl(headImgUrl);
            this.baseMapper.updateById(updateStuff);
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


    private int verification(StudentBasic basic) {
        if (basic.getName() != null) {
            //验证楼层
            List<StudentBasic> basicList = this.baseMapper.selectList(new LambdaQueryWrapper<StudentBasic>()
                    .eq(StudentBasic::getName, basic.getName())
                    .eq(StudentBasic::getIsDeleted, 0));
            if (basicList.size() != 0) {
                if (basic.getId() == null) {
                    throw new ServiceException("该名称已经存在");
                } else {
                    for (StudentBasic campus : basicList) {
                        if (!campus.getId().equals(basic.getId())) {
                            //Id不一致说明数据不止一条 ，数据重复
                            throw new ServiceException("该名称已经存在");
                        }
                    }
                }
            }
        }
        if (basic.getCode() != null) {
            //验证校区名称
            List<StudentBasic> basicList = this.baseMapper.selectList(new LambdaQueryWrapper<StudentBasic>()
                    .eq(StudentBasic::getCode, basic.getCode())
                    .eq(StudentBasic::getIsDeleted, 0));
            if (basicList.size() != 0) {
                if (basic.getId() == null) {
                    throw new ServiceException("学号不能重复");
                } else {
                    for (StudentBasic campus : basicList) {
                        if (!campus.getId().equals(basic.getId())) {
                            //Id不一致说明数据不止一条 ，数据重复
                            throw new ServiceException("学号不能重复");
                        }
                    }
                }
            }
        }
        return 0;
    }
}
