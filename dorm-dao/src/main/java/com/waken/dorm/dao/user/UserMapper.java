package com.waken.dorm.dao.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.waken.dorm.common.entity.user.User;
import com.waken.dorm.common.form.user.UserForm;
import com.waken.dorm.common.view.user.UserView;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface UserMapper extends BaseMapper<User> {
    int batchUpdateStatus(Map<String, Object> param);

    IPage<UserView> listUsers(Page<UserView> page,@Param("form") UserForm userForm);
}