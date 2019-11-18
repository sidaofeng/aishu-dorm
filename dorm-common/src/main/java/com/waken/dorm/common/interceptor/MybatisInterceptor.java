package com.waken.dorm.common.interceptor;

import com.waken.dorm.common.annotation.Id;
import com.waken.dorm.common.exception.ServerException;
import com.waken.dorm.common.manager.UserManager;
import com.waken.dorm.common.sequence.UUIDSequence;
import com.waken.dorm.common.utils.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @ClassName: MybatisInterceptor
 * @Description: mybatis拦截器，主要对新增和修改的做自定义处理
 * @Author: zhaoRong
 * @Create: 2019/11/16 22:15
 **/

@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
public class MybatisInterceptor implements Interceptor {
    private static final String id = "id";
    private static final String createTime = "createTime";
    private static final String createUserId = "createUserId";
    private static final String lastModifyTime = "lastModifyTime";
    private static final String lastModifyUserId = "lastModifyUserId";
    private static final String serialVersionUID = "serialVersionUID";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        MappedStatement mappedStatement = (MappedStatement) args[0];

        // 获取 SQL
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        // 获取参数
        Object parameter = args[1];

        //有参数别名或者参数是集合等情况时是Map
        if (parameter instanceof Map) {
            Map<String, Object> updateParam = (Map<String, Object>) parameter;
            Iterator<Map.Entry<String, Object>> iterator = updateParam.entrySet().iterator();
            if (iterator.hasNext()) {
                Map.Entry<String, Object> next = iterator.next();
                this.fillBaseEntity(next.getValue(), sqlCommandType);
            }
        } else {
            this.fillBaseEntity(parameter, sqlCommandType);
        }
        return invocation.proceed();
    }
    /**
     * 解析参数对象
     * @param entity
     * @param sqlCommandType
     */
    private void fillBaseEntity(Object entity, SqlCommandType sqlCommandType) {
        if (entity instanceof List) {
            ((List) entity).stream().forEach(obj -> this.fillBaseEntity(obj, sqlCommandType));
        } else {
            try{
                this.setFieldValue(entity,sqlCommandType);
            }catch (Throwable e){
                throw new ServerException("填充公共字段发生异常！");
            }

        }
    }

    /**
     * 给公共字段设置
     * @param entity
     * @param sqlCommandType
     * @throws Throwable
     */
    private void setFieldValue(Object entity,SqlCommandType sqlCommandType) throws Throwable{
        // 获取私有成员变量
        Field[] declaredFields = entity.getClass().getDeclaredFields();
        //获取父级的私有成员变量
        if (entity.getClass().getSuperclass() != null) {
            Field[] superField = entity.getClass().getSuperclass().getDeclaredFields();
            declaredFields = ArrayUtils.addAll(declaredFields, superField);
        }
        String curUserId = UserManager.getCurrentUserId();
        //填充公共字段
        Object filedValue;
        String fieldName;
        for (Field field : declaredFields) {
            fieldName = field.getName();
            //处理序列化ID
            if (serialVersionUID.equals(fieldName)) {
                continue;
            }
            // insert语句插入创建时间与创建人
            if (SqlCommandType.INSERT.equals(sqlCommandType)) {
                field.setAccessible(true);
                filedValue = field.get(entity);
                if (Objects.isNull(filedValue)) {
                    //设置主键ID
                    if (this.createKey(field, entity)) continue;
                    //设置创建时间
                    if (createTime.equals(fieldName)) {
                        field.set(entity, new Date());
                        continue;
                    }
                    //设置创建人
                    if (createUserId.equals(fieldName)) {
                        if (!StringUtils.isEmpty(curUserId)) {
                            filedValue = curUserId;
                        }
                        field.set(entity, filedValue);
                        continue;
                    }
                }
            }
            // insert或update语句插入更新时间、更新人
            if (SqlCommandType.INSERT.equals(sqlCommandType)
                    || SqlCommandType.UPDATE.equals(sqlCommandType)) {
                //设置更新时间
                if (lastModifyTime.equals(fieldName)) {
                    field.setAccessible(true);
                    //兼容mybatis plus的update
                    field.set(entity, new Date());
                    continue;
                }
                //设置更新人
                if (lastModifyUserId.equals(fieldName)) {
                    field.setAccessible(true);
                    filedValue = field.get(entity);
                    if (!StringUtils.isEmpty(curUserId)) {
                        filedValue = curUserId;
                    }
                    field.set(entity, filedValue);
                    continue;
                }
            }
        }
    }

    /**
     * 创建主键ID
     *
     * @param field
     * @param parameter
     * @return
     * @throws Throwable
     */
    private Boolean createKey(Field field, Object parameter) throws Throwable {
        if (field.getAnnotation(Id.class) != null) {
            field.setAccessible(true);
            field.set(parameter, UUIDSequence.next());
            return true;
        } else if (id.equals(field.getName())) {
            field.setAccessible(true);
            field.set(parameter, UUIDSequence.next());
            return true;
        }
        return false;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }
}
