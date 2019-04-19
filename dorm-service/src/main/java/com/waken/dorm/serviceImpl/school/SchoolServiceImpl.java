package com.waken.dorm.serviceImpl.school;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.waken.dorm.common.constant.Constant;
import com.waken.dorm.common.entity.school.*;
import com.waken.dorm.common.entity.user.User;
import com.waken.dorm.common.entity.user.UserExample;
import com.waken.dorm.common.enums.CodeEnum;
import com.waken.dorm.common.exception.DormException;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.school.EditSchoolForm;
import com.waken.dorm.common.form.school.SchoolForm;
import com.waken.dorm.common.utils.*;
import com.waken.dorm.common.view.school.SchoolView;
import com.waken.dorm.dao.school.SchoolMapper;
import com.waken.dorm.dao.school.SchoolUserRelMapper;
import com.waken.dorm.dao.user.UserMapper;
import com.waken.dorm.service.school.SchoolService;
import com.waken.dorm.serviceImpl.base.BaseServerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @ClassName SchoolServiceImpl
 * @Description SchoolServiceImpl
 * @Author zhaoRong
 * @Date 2019/4/2 10:52
 **/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class SchoolServiceImpl extends BaseServerImpl implements SchoolService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    SchoolMapper schoolMapper;
    @Autowired
    SchoolUserRelMapper schoolUserRelMapper;
    @Autowired
    UserMapper userMapper;

    /**
     * 保存或修改学习信息
     *
     * @param editSchoolForm
     * @return
     */
    @Transactional
    @Override
    public School saveSchool(EditSchoolForm editSchoolForm) {
        this.editSchoolValidate(editSchoolForm);
        String userId = ShiroUtils.getUserId();
        Date curDate = DateUtils.getCurrentDate();
        School school = new School();
        BeanMapper.copy(editSchoolForm,school);
        school.setLastModifyTime(curDate);
        school.setLastModifyUserId(userId);
        if (StringUtils.isEmpty(editSchoolForm.getPkSchoolId())){//新增
            logger.info("service: 新增学校开始");
            String pkSchoolId = UUIDUtils.getPkUUID();
            school.setPkSchoolId(pkSchoolId);
            school.setStatus(CodeEnum.ENABLE.getCode());
            school.setCreateTime(curDate);
            school.setCreateUserId(userId);
            int count = Constant.ZERO;
            count = schoolMapper.insertSelective(school);
            if (count == Constant.ZERO){
                throw new DormException("新增学校个数为 0 个");
            }
            editSchoolForm.setPkSchoolId(pkSchoolId);
            this.defaultAddUserRel(editSchoolForm);
            return school;
        }else {//修改
            logger.info("service: 更新学校信息开始");
            schoolMapper.updateByPrimaryKeySelective(school);
            return schoolMapper.selectByPrimaryKey(editSchoolForm.getPkSchoolId());
        }
    }

    /**
     * 删除学校
     *
     * @param deleteForm
     */
    @Override
    public void deleteSchool(DeleteForm deleteForm) {
        logger.info("service: 删除学校开始");
        List<String> schoolIds = deleteForm.getDelIds();
        Integer delStatus = deleteForm.getDelStatus();
        int count = Constant.ZERO;
        if (CodeEnum.YES.getCode() == delStatus){ // 物理删除
            List<School> schoolList = schoolMapper.selectByIds(schoolIds);
            StringBuffer sb = new StringBuffer();
            for (School school : schoolList){
                if (CodeEnum.ENABLE.getCode() == school.getStatus()){
                    sb.append(school.getSchoolName());
                }
            }
            if (StringUtils.isEmpty(sb.toString())){//删除学校
                SchoolExample example = new SchoolExample();
                SchoolExample.Criteria criteria = example.createCriteria();
                criteria.andPkSchoolIdIn(schoolIds);
                count = schoolMapper.deleteByExample(example);
                if (count == Constant.ZERO){
                    throw new DormException("删除学校个数为 0 条");
                }
                SchoolUserRelExample schoolUserRelExample = new SchoolUserRelExample();
                SchoolUserRelExample.Criteria sCriteria = schoolUserRelExample.createCriteria();
                sCriteria.andSchoolIdIn(schoolIds);
                schoolUserRelMapper.deleteByExample(schoolUserRelExample);
            }else {
                throw new DormException("以下学校处于生效中："+sb.toString());
            }

        }else if(CodeEnum.NO.getCode() == delStatus){
            count = schoolMapper.batchUpdateStatus(getToUpdateStatusMap(schoolIds,CodeEnum.DELETE.getCode()));
            if (count == Constant.ZERO){
                throw new DormException("状态删除失败");
            }
            SchoolUserRelExample example = new SchoolUserRelExample();
            SchoolUserRelExample.Criteria criteria = example.createCriteria();
            criteria.andSchoolIdIn(schoolIds);
            schoolUserRelMapper.deleteByExample(example);
        }else {
            throw new DormException("删除状态码错误！");
        }
    }

    /**
     * 分页查询学校信息
     *
     * @param schoolForm
     * @return
     */
    @Override
    public PageInfo<SchoolView> listSchoolView(SchoolForm schoolForm) {
        logger.info("service: 分页查询学校信息开始");
        if (schoolForm.getStartTime() != null) {
            schoolForm.setStartTime(DateUtils.formatDate2DateTimeStart(schoolForm.getStartTime()));
        }
        if (schoolForm.getEndTime() != null) {
            schoolForm.setEndTime(DateUtils.formatDate2DateTimeEnd(schoolForm.getEndTime()));
        }
        PageHelper.startPage(schoolForm.getPageNum(),schoolForm.getPageSize());
        List<SchoolView> schoolList = schoolMapper.listSchoolView(schoolForm);
        return new PageInfo<SchoolView>(schoolList);
    }

    /**
     * 获取当前登陆用户对应的学校的信息
     *
     * @return
     */
    @Override
    public String getSchoolIdByUserId(String userId) {
        logger.info("service: 开始进入通过当前登陆用户id 查询对应的学校信息");
        SchoolUserRelExample example = new SchoolUserRelExample();
        SchoolUserRelExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        List<SchoolUserRel> schoolUserRels = schoolUserRelMapper.selectByExample(example);
        if (schoolUserRels.isEmpty()){
            return null;
        }else {
            String schoolId = schoolUserRels.get(Constant.ZERO).getSchoolId();
            logger.info("当前登陆用户所对应的学校id为："+schoolId);
            return schoolId;
        }

    }
    private void editSchoolValidate(EditSchoolForm editSchoolForm){
        if (StringUtils.isEmpty(editSchoolForm.getPkSchoolId())){
            if (StringUtils.isEmpty(editSchoolForm.getSchoolName())){
                throw new DormException("学校名称不能为空！");
            }
            if (StringUtils.isEmpty(editSchoolForm.getUserName())){
                throw new DormException("用户名不能为空！");
            }
            if (StringUtils.isEmpty(editSchoolForm.getPassword())){
                throw new DormException("密码不能为空！");
            }
            if (StringUtils.isEmpty(editSchoolForm.getMobile())){
                throw new DormException("电话不能为空！");
            }else {
                if (!CheckUtils.isPhoneLegality(editSchoolForm.getMobile())){
                    throw new DormException("请输入正确的手机号码！");
                }
            }
            if (StringUtils.isEmpty(editSchoolForm.getEmail())){
                throw new DormException("电子邮箱不能为空！");
            }else {
                if (!CheckUtils.isEmailLegality(editSchoolForm.getEmail())){
                    throw new DormException("请输入正确的电子邮箱！");
                }
            }
            UserExample userExample = new UserExample();
            UserExample.Criteria userCriteria = userExample.createCriteria();
            userCriteria.andUserNameEqualTo(editSchoolForm.getUserName());
            List<User> userList = userMapper.selectByExample(userExample);
            if (!userList.isEmpty()){
                throw new DormException("用户名已存在！");
            }
            SchoolExample example = new SchoolExample();
            SchoolExample.Criteria criteria = example.createCriteria();
            criteria.andSchoolNameEqualTo(editSchoolForm.getSchoolName());
            List<School> schools = schoolMapper.selectByExample(example);
            if (!schools.isEmpty()){
                throw new DormException("学校名称已存在！");
            }
        }else {
            School school = schoolMapper.selectByPrimaryKey(editSchoolForm.getPkSchoolId());
            if (school == null){
                throw new DormException("学校id无效!");
            }
        }
    }

    /**
     * 用户注册完学校后默认新增用户管理
     */
    @Transactional
    private void defaultAddUserRel(EditSchoolForm editSchoolForm){
        logger.info("service：开始新增用户：");
        //新增用户
        String pkUserId = UUIDUtils.getPkUUID();
        String userId = ShiroUtils.getUserId();
        Date curDate = DateUtils.getCurrentDate();
        String encodePassword = PasswordEncode.shiroEncode(editSchoolForm.getUserName(),editSchoolForm.getPassword());//shiro加密 密码
        User user = new User();
        user.setUserId(pkUserId);
        user.setUserName(editSchoolForm.getUserName());
        user.setPassword(encodePassword);
        user.setMobile(editSchoolForm.getMobile());
        user.setEmail(editSchoolForm.getEmail());
        user.setUserType(CodeEnum.SCHOOL_USER.getCode());
        user.setStatus(CodeEnum.ENABLE.getCode());
        user.setCreateTime(curDate);
        user.setCreateUserId(userId);
        user.setLastModifyTime(curDate);
        user.setLastModifyUserId(userId);
        user.setMemo(CodeEnum.SCHOOL_USER.getMsg());
        int count = Constant.ZERO;
        count = userMapper.insertSelective(user);
        if (count == Constant.ZERO){
            throw new DormException("新增学校用户失败！");
        }
        logger.info("service：开始新增用户与学校关联：");
        //新增学校与用户关联
        String pkSchoolUserId = UUIDUtils.getPkUUID();
        SchoolUserRel schoolUserRel = new SchoolUserRel();
        schoolUserRel.setPkSchoolUserId(pkSchoolUserId);
        schoolUserRel.setSchoolId(editSchoolForm.getPkSchoolId());
        schoolUserRel.setUserId(pkUserId);
        schoolUserRel.setStatus(CodeEnum.ENABLE.getCode());
        schoolUserRel.setCreateTime(curDate);
        schoolUserRel.setCreateUserId(userId);
        schoolUserRel.setLastModifyTime(curDate);
        schoolUserRel.setLastModifyUserId(userId);
        schoolUserRel.setMemo(CodeEnum.SCHOOL_USER.getMsg());
        int counts = Constant.ZERO;
        counts = schoolUserRelMapper.insertSelective(schoolUserRel);
        if (counts == Constant.ZERO){
            throw new DormException("新增学校用户关联失败！");
        }
    }
}
