package com.waken.dorm.dao.auth;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.waken.dorm.common.entity.auth.RoleResourceRel;

import java.util.List;

public interface RoleResourceRelMapper extends BaseMapper<RoleResourceRel> {
    /**
     * 批量新增角色资源关联
     * @param roleResourceRelList
     * @return
     */
    int batchAddRoleResourceRel(List<RoleResourceRel> roleResourceRelList);

}