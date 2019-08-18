package com.waken.dorm.dao.user;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.waken.dorm.common.entity.user.User;
import com.waken.dorm.common.form.user.UserForm;
import com.waken.dorm.common.view.user.UserView;

import java.util.List;
import java.util.Map;

public interface UserMapper extends BaseMapper<User> {
    int batchUpdateStatus(Map<String, Object> param);

    List<UserView> listUsers(UserForm userForm);
}