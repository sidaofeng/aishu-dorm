package com.waken.dorm.dao.user;

import com.waken.dorm.common.entity.user.User;
import com.waken.dorm.common.entity.user.UserExample;
import java.util.List;
import java.util.Map;

import com.waken.dorm.common.form.user.UserForm;
import com.waken.dorm.common.view.user.UserView;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int batchUpdateStatus(Map<String,Object> param);

    List<UserView> listUsers(UserForm userForm);

    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(String userId);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(String userId);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}