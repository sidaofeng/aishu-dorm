package com.waken.dorm.serviceImpl.user;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.waken.dorm.common.constant.Constant;
import com.waken.dorm.common.entity.school.School;
import com.waken.dorm.common.entity.school.SchoolUserRel;
import com.waken.dorm.common.entity.school.SchoolUserRelExample;
import com.waken.dorm.common.entity.user.User;
import com.waken.dorm.common.entity.user.UserExample;
import com.waken.dorm.common.entity.user.UserRoleRel;
import com.waken.dorm.common.entity.user.UserRoleRelExample;
import com.waken.dorm.common.enums.CodeEnum;
import com.waken.dorm.common.exception.DormException;
import com.waken.dorm.common.form.base.DeleteForm;
import com.waken.dorm.common.form.role.ListAddedRoleForm;
import com.waken.dorm.common.form.user.AddUserRoleRelForm;
import com.waken.dorm.common.form.user.EditUserForm;
import com.waken.dorm.common.form.user.QueryUserForm;
import com.waken.dorm.common.form.user.UserForm;
import com.waken.dorm.common.utils.*;
import com.waken.dorm.common.view.base.ImgView;
import com.waken.dorm.common.view.user.UserRoleRelView;
import com.waken.dorm.common.view.user.UserRolesView;
import com.waken.dorm.common.view.user.UserView;
import com.waken.dorm.dao.resource.ResourceMapper;
import com.waken.dorm.dao.role.RoleMapper;
import com.waken.dorm.dao.school.SchoolUserRelMapper;
import com.waken.dorm.dao.user.UserMapper;
import com.waken.dorm.dao.user.UserRoleRelMapper;
import com.waken.dorm.service.school.SchoolService;
import com.waken.dorm.service.user.UserService;
import com.waken.dorm.serviceImpl.base.BaseServerImpl;
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

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserServiceImpl extends BaseServerImpl implements UserService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserRoleRelMapper userRoleRelMapper;
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    ResourceMapper resourceMapper;
    @Autowired
    AliyunOSSUtil aliyunOSSUtil;
    @Autowired
    SchoolUserRelMapper schoolUserRelMapper;

    @Override
    @Transactional
    public User saveUser(EditUserForm editUserForm){
        this.editUserValidate(editUserForm);
        String curUserId = ShiroUtils.getUserId();
        Date curDate = DateUtils.getCurrentDate();
        int count = Constant.ZERO;
        User user = new User();
        String encodePassword = PasswordEncode.shiroEncode(editUserForm.getUserName(),editUserForm.getPassword());//shiro加密 密码
        BeanMapper.copy(editUserForm,user);
        user.setPassword(encodePassword);
        user.setLastModifyTime(curDate);
        user.setLastModifyUserId(curUserId);
        if (StringUtils.isEmpty(editUserForm.getUserId())){//新增
            logger.info("service: 新增用户开始");
            String userId = UUIDUtils.getPkUUID();
            user.setUserId(userId);
            user.setStatus(CodeEnum.ENABLE.getCode());
            user.setCreateTime(curDate);
            user.setCreateUserId(curUserId);
            count = userMapper.insertSelective(user);
            if (count == Constant.ZERO){
                throw new DormException("新增用户失败");
            }
            this.addUserSchoolRel(curUserId,userId);
            user.setPassword(Constant.NULL_STRING);
            return user;
        }else {//修改
            logger.info("service: 更新用户信息开始");
            userMapper.updateByPrimaryKeySelective(user);
            return userMapper.selectByPrimaryKey(editUserForm.getUserId());
        }

    }
    @Override
    public User queryUserInfo(QueryUserForm queryUserForm){
        logger.info("service: 查询用户开始");
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUserNameEqualTo(queryUserForm.getUserName());
        List<User> userList = userMapper.selectByExample(example);
        if (userList.isEmpty()){
            return new User();
        }
        return userList.get(Constant.ZERO);
    }

    @Override
    @Transactional// 事务控制
    public void deleteUser(DeleteForm deleteForm) {
        logger.info("service: 删除用户开始");
        List<String> userIds = deleteForm.getDelIds();
        Integer delStatus = deleteForm.getDelStatus();
        if (CodeEnum.YES.getCode() == delStatus){ // 物理删除
            // 删除用户与角色的关联
            UserRoleRelExample userRoleRelExample = new UserRoleRelExample();
            UserRoleRelExample.Criteria userRoleRelCriteria = userRoleRelExample.createCriteria();
            userRoleRelCriteria.andUserIdIn(userIds);
            List<UserRoleRel> userRoleRels = userRoleRelMapper.selectByExample(userRoleRelExample);
            if (!userRoleRels.isEmpty()){
                userRoleRelCriteria.andStatusEqualTo(CodeEnum.ENABLE.getCode());
                List<UserRoleRel> userRoleRelList = userRoleRelMapper.selectByExample(userRoleRelExample);
                if (userRoleRelList.isEmpty()){
                    userRoleRelMapper.deleteByExample(userRoleRelExample);
                }else {
                    throw new DormException("删除失败，原因是你删除的用户与角色关联生效中！");
                }
            }
            // 删除用户本身
            UserExample example = new UserExample();
            UserExample.Criteria criteria = example.createCriteria();
            criteria.andUserIdIn(userIds);
            userMapper.deleteByExample(example);
        }else if(CodeEnum.NO.getCode() == delStatus){
            int count = Constant.ZERO;
            count = userMapper.batchUpdateStatus(getToUpdateStatusMap(userIds,CodeEnum.DELETE.getCode()));
            if (count == Constant.ZERO){
                throw new DormException("状态删除失败");
            }
            UserRoleRelExample userRoleRelExample = new UserRoleRelExample();
            UserRoleRelExample.Criteria userRoleRelCriteria = userRoleRelExample.createCriteria();
            userRoleRelCriteria.andUserIdIn(userIds);
            userRoleRelMapper.deleteByExample(userRoleRelExample);
        }else {
            throw new DormException("删除状态码错误！");
        }
    }

    @Override
    public PageInfo<UserView> listUsers(UserForm userForm) {
        logger.info("service: 用户信息分页查询开始");
        if (userForm.getStartTime() != null) {
            userForm.setStartTime(DateUtils.formatDate2DateTimeStart(userForm.getStartTime()));
        }
        if (userForm.getEndTime() != null) {
            userForm.setEndTime(DateUtils.formatDate2DateTimeEnd(userForm.getEndTime()));
        }
        PageHelper.startPage(userForm.getPageNum(),userForm.getPageSize());
        List<UserView> userViews = userMapper.listUsers(userForm);
        return new PageInfo<UserView>(userViews);
    }

    @Transactional // 事务控制
    @Override
    public void batchAddUserRoleRel(AddUserRoleRelForm addUserRoleRelForm) {
        logger.info("service: 批量新增用户角色关联开始");
        StringBuffer sb = new StringBuffer();
        if (StringUtils.isEmpty(addUserRoleRelForm.getUserId())){
            sb.append("用户 id为空！");
        }
        if (addUserRoleRelForm.getRoleIds().isEmpty()){
            sb.append("角色 id集合为空！");
        }
        if (StringUtils.isEmpty(sb.toString())){
            throw new DormException("批量新增用户角色关联失败，原因："+sb.toString());
        }
        List<UserRoleRel> toBeAddUserRoleRel = this.getToBeAddUserRoleRel(addUserRoleRelForm);
        if (!toBeAddUserRoleRel.isEmpty()){
            int count = Constant.ZERO;
            count = userRoleRelMapper.batchAddUserRoleRel(toBeAddUserRoleRel);
            if (count == Constant.ZERO){
                throw new DormException("批量新增用户角色关联失败");
            }

        }
    }
    @Transactional
    private List<UserRoleRel> getToBeAddUserRoleRel(AddUserRoleRelForm addUserRoleRelForm){
        String userId = addUserRoleRelForm.getUserId();
        List<String> roleIds = addUserRoleRelForm.getRoleIds();
        UserRoleRelExample example = new UserRoleRelExample();
        UserRoleRelExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        List<UserRoleRel> userRoleRelList = userRoleRelMapper.selectByExample(example);
        if (!userRoleRelList.isEmpty()){
            List<String> existIds = new ArrayList<>();// 接收已经存在关联的角色id
            List<String> toDelIds = new ArrayList<>();// 接收需要删除的角色id
            List<String> oldIds = new ArrayList<>();// 接收所有已经绑定的角色id
            for (UserRoleRel userRoleRel : userRoleRelList) {
                oldIds.add(userRoleRel.getRoleId());
            }
            for (String oldId : oldIds) {
                if (roleIds.contains(oldId)) {
                    existIds.add(oldId);
                } else {
                    toDelIds.add(oldId);
                }
            }
            roleIds.removeAll(existIds);
            if (!toDelIds.isEmpty()) {
                UserRoleRelExample delExample = new UserRoleRelExample();
                UserRoleRelExample.Criteria delCriteria = delExample.createCriteria();
                delCriteria.andUserIdEqualTo(userId);
                delCriteria.andRoleIdIn(roleIds);
                int count = Constant.ZERO;
                count = userRoleRelMapper.deleteByExample(delExample);
                if (count == Constant.ZERO){
                    throw new DormException("删除用户角色关联失败");
                }
            }
        }
        if (roleIds.isEmpty()){
            List<UserRoleRel> toBeAddUserRoleRelList = new ArrayList<UserRoleRel>();
            for (String roleId : roleIds) {
                String pkUserRoleId = UUIDUtils.getPkUUID();
                String curUserId = ShiroUtils.getUserId();
                Date curDate = DateUtils.getCurrentDate();
                UserRoleRel userRoleRel = new UserRoleRel();
                userRoleRel.setPkUserRoleId(pkUserRoleId);
                userRoleRel.setUserId(userId);
                userRoleRel.setRoleId(roleId);
                userRoleRel.setStatus(CodeEnum.ENABLE.getCode());
                userRoleRel.setCreateTime(curDate);
                userRoleRel.setCreateUserId(curUserId);
                userRoleRel.setLastModifyTime(curDate);
                userRoleRel.setLastModifyUserId(curUserId);
                toBeAddUserRoleRelList.add(userRoleRel);
            }
            return toBeAddUserRoleRelList;
        }else {
            return new ArrayList<UserRoleRel>();
        }
   }
    @Override
    public UserRolesView listUserRoles(ListAddedRoleForm listAddedRoleForm) {
        String userId = listAddedRoleForm.getUserId();
        String curUserId = ShiroUtils.getUserId();
        if (StringUtils.isEmpty(userId)){
            throw new DormException("用户id为空");
        }
        List<UserRoleRelView> userRoleRelViewList = new ArrayList<>();
        if (isSuperAdmin(curUserId)){//超级管理员可以管理所有角色
            userRoleRelViewList = roleMapper.listSuperAdminRoles(curUserId);
        }else {//其他管理员只能管理自己拥有的角色
            listAddedRoleForm.setCurUserId(curUserId);
            userRoleRelViewList = roleMapper.listAddedRoles(listAddedRoleForm);
        }
        if (!userRoleRelViewList.isEmpty()){
            List<UserRoleRelView> addedList = new ArrayList<>();//用于接收已经存在关联的角色信息
            List<UserRoleRelView> unAddedList = new ArrayList<>();//用于接收没有关联的角色信息
            for(UserRoleRelView userRoleRelView : userRoleRelViewList){
                if (StringUtils.isEmpty(userRoleRelView.getPkUserRoleId())){//关联id为空，表示是未关联的角色
                    unAddedList.add(userRoleRelView);
                }else {
                    addedList.add(userRoleRelView);
                }
            }
            UserRolesView userRolesView = new UserRolesView();
            userRolesView.setAddedList(addedList);
            userRolesView.setToBeAddList(unAddedList);
            return userRolesView;
        }
        return new UserRolesView();
    }

    /**
     * 上传头像
     *
     * @param file
     * @return
     */
    @Transactional
    @Override
    public ImgView uploadUserImg(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String userId = ShiroUtils.getUserId();
        String folderName = Constant.STUDENT;
        if (StringUtils.isEmpty(fileName)) {
            throw new DormException("您上传的头像图片文件为空！");
        }
        try {
            User user = userMapper.selectByPrimaryKey(userId);
            if (!StringUtils.isEmpty(user.getImgUrl())) {
                aliyunOSSUtil.deleteFile(user.getImgUrl());// 删除已经存在的头像
                logger.info("删除已经存在的用户头像成功！");
            }
            File toFile = FileUtils.multipartFileToFile(file);
            // 上传到OSS
            String headImgUrl = aliyunOSSUtil.upLoad(toFile, folderName);
            if (StringUtils.isEmpty(headImgUrl)) {
                throw new DormException("上传学生头像失败！");
            }
            user.setImgUrl(headImgUrl);
            userMapper.updateByPrimaryKeySelective(user);
            logger.info("用户头像访问路径：" + headImgUrl);
            ImgView imgView = new ImgView();
            imgView.setImgUrl(headImgUrl);
            return imgView;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("用户头像上传失败，原因是：" + e.getMessage());
        }
        return null;
    }

    /**
     * 判断当前操作用户是否有超级管理员角色
     * @return
     */
    private boolean isSuperAdmin(String curUserId){
        List<UserRoleRelView> roleList = roleMapper.listUserRoleInfo(curUserId);
        if (!roleList.isEmpty()){
            for (UserRoleRelView role : roleList) {
                if (Constant.SuperAdmin.equals(role.getRoleName())){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 默认绑定与当前登录用户所关联的学校
     * @param curUserId
     * @param userId
     */
    @Transactional
    private void addUserSchoolRel(String curUserId,String userId){
        SchoolUserRelExample example = new SchoolUserRelExample();
        SchoolUserRelExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(curUserId);
        List<SchoolUserRel> schoolUserRels = schoolUserRelMapper.selectByExample(example);
        if (!schoolUserRels.isEmpty()){
            SchoolUserRel schoolUserRel = new SchoolUserRel();
            String pkRelId = UUIDUtils.getPkUUID();
            Date curDate = DateUtils.getCurrentDate();
            schoolUserRel.setPkSchoolUserId(pkRelId);
            schoolUserRel.setUserId(userId);
            schoolUserRel.setSchoolId(schoolUserRels.get(Constant.ZERO).getSchoolId());
            schoolUserRel.setStatus(CodeEnum.ENABLE.getCode());
            schoolUserRel.setCreateTime(curDate);
            schoolUserRel.setCreateUserId(curUserId);
            schoolUserRel.setLastModifyTime(curDate);
            schoolUserRel.setLastModifyUserId(curUserId);
            int count = Constant.ZERO;
            count = schoolUserRelMapper.insertSelective(schoolUserRel);
            if (count == Constant.ZERO){
                throw new DormException("新增用户学校关联失败");
            }

        }
    }
    /**
     * 验证
     * @param userForm
     */
    private void editUserValidate(EditUserForm userForm){
        if (StringUtils.isEmpty(userForm.getUserId())){//新增验证
            if (StringUtils.isEmpty(userForm.getUserName()) || StringUtils.isEmpty(userForm.getPassword())){
                throw new DormException("用户名或密码为空！");
            }
            if (StringUtils.isEmpty(userForm.getMobile())){
                throw new DormException("电话不能为空！");
            }else {
                if (!CheckUtils.isPhoneLegality(userForm.getMobile())){
                    throw new DormException("请输入正确的手机号码！");
                }
            }
            if (StringUtils.isEmpty(userForm.getEmail())){
                throw new DormException("电子邮箱不能为空！");
            }else {
                if (!CheckUtils.isEmailLegality(userForm.getEmail())){
                    throw new DormException("请输入正确的电子邮箱！");
                }
            }
            UserExample example = new UserExample();
            UserExample.Criteria criteria = example.createCriteria();
            criteria.andUserNameEqualTo(userForm.getUserName());
            List<User> userList = userMapper.selectByExample(example);
            if (!userList.isEmpty()){
                throw new DormException("用户名已存在！");
            }
        }else {//修改验证
            if (StringUtils.isNotEmpty(userForm.getUserName())){
                UserExample example = new UserExample();
                UserExample.Criteria criteria = example.createCriteria();
                criteria.andUserNameEqualTo(userForm.getUserName());
                List<User> userList = userMapper.selectByExample(example);
                if (!userList.isEmpty()){
                    if (!StringUtils.equals(userList.get(Constant.ZERO).getUserId(),userForm.getUserId())){
                        throw new DormException("用户名已存在！");
                    }
                }
            }
        }

    }
}
