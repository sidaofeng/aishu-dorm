//package com.waken.dorm.common.handle;
//
///**
// * @ClassName: MetaHandler
// * @Description:
// * @Author: zhaoRong
// * @Create: 2019/11/16 23:18
// **/
//
//import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
//import com.waken.dorm.common.manager.UserManager;
//import com.waken.dorm.common.utils.DateUtils;
//import com.waken.dorm.common.utils.StringUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.ibatis.reflection.MetaObject;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//
///**
// * 处理新增和更新的基础数据填充，配合BaseEntity和MyBatisPlusConfig使用
// */
//@Component
//@Slf4j
//public class MybatisPlusMetaHandler implements MetaObjectHandler {
//    private static final String createTime = "createTime";
//    private static final String createUserId = "createUserId";
//    private static final String lastModifyTime = "lastModifyTime";
//    private static final String lastModifyUserId = "lastModifyUserId";
//    /**
//     * 新增数据执行
//     * @param metaObject
//     */
//    @Override
//    public void insertFill(MetaObject metaObject) {
//        String userId = UserManager.getCurrentUserId();
//        Date date = DateUtils.getCurrentDate();
//        if (!StringUtils.isEmpty(userId)){
//            this.setFieldValByName(createUserId, userId, metaObject);
//            this.setFieldValByName(lastModifyUserId, userId, metaObject);
//        }
//        this.setFieldValByName(createTime, date, metaObject);
//        this.setFieldValByName(lastModifyTime, date, metaObject);
//
//    }
//
//    /**
//     * 更新数据执行
//     * @param metaObject
//     */
//    @Override
//    public void updateFill(MetaObject metaObject) {
//        String curUserId = UserManager.getCurrentUserId();
//        if (!StringUtils.isEmpty(curUserId)){
//            this.setFieldValByName(lastModifyUserId, curUserId, metaObject);
//        }
//        this.setFieldValByName(lastModifyTime, DateUtils.getCurrentDate(), metaObject);
//
//    }
//
//}
