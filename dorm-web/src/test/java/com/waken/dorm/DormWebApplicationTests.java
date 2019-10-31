package com.waken.dorm;

import com.waken.dorm.dao.user.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DormWebApplicationTests {
    @Resource
    UserMapper userMapper;

    @Test
    public void contextLoads() {
        System.out.println(this.userMapper.selectById(1));
    }




}
