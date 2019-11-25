CREATE TABLE `rm_building` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL,
  `campus_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '校区',
  `name` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '名称',
  `code` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '编码',
  `floor_start` int(3) DEFAULT NULL COMMENT '起始楼层',
  `floor_total` int(3) DEFAULT NULL COMMENT '楼层数量',
  `status` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '使用状态(1使用，2闲置)',
  `type` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '建筑类型 1校内，2校外',
  `certificate_code` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '产权证号',
  `is_deleted` bit(1) DEFAULT b'0' COMMENT '是否删除（0否，1是）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL,
  `last_modify_time` datetime DEFAULT NULL COMMENT '最终修改时间',
  `last_modify_user_id` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL,
  `memo` varchar(500) CHARACTER SET utf8 DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `rm_building_floor` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL,
  `building_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '建筑物ID',
  `name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '名称',
  `code` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '楼层',
  `is_deleted` bit(1) DEFAULT b'0' COMMENT '是否删除（0否，1是）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL,
  `last_modify_time` datetime DEFAULT NULL COMMENT '最终修改时间',
  `last_modify_user_id` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='建筑物楼层';

CREATE TABLE `rm_dict` (
  `id` varchar(32) NOT NULL COMMENT '主键id',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '父节点id',
  `name` varchar(45) DEFAULT NULL COMMENT '字典数据名称',
  `code` varchar(200) DEFAULT NULL COMMENT '字典数据编码',
  `is_default` bit(1) DEFAULT b'0' COMMENT '是否默认 0：否 1：是',
  `sort` int(3) DEFAULT NULL COMMENT '排序sort',
  `description` varchar(200) DEFAULT NULL COMMENT '字典描述',
  `is_deleted` bit(1) DEFAULT b'0' COMMENT '是否删除（0否，1是）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL,
  `last_modify_time` datetime DEFAULT NULL COMMENT '最终修改时间',
  `last_modify_user_id` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `rm_dict` VALUES ('08e84b799a9641daa0a2b892f1a83b16', 'a47da99cd9c94988bf39bb10670ca136', '电话后6位', 'TEL', '', '2', '电话后6位', '', '2019-11-24 00:22:20', '1', '2019-11-24 00:22:20', '1');
INSERT INTO `rm_dict` VALUES ('4567a7494d9743bdac6124b98289bb5b', 'ed5d2d420f8d4416a4857ca5ecd2cea3', '女', '2', '', '1', '女', '', '2019-11-24 00:12:45', '1', '2019-11-24 00:12:45', '1');
INSERT INTO `rm_dict` VALUES ('4793d8bace4441ee869d465dcb2e97fd', 'a47da99cd9c94988bf39bb10670ca136', '证件号后6位', 'IDCARD', '', '1', '证件号后6位', '', '2019-11-24 00:22:09', '1', '2019-11-24 00:22:09', '1');
INSERT INTO `rm_dict` VALUES ('5119e28da2904e04bea67c0c4ee48cf6', 'a47da99cd9c94988bf39bb10670ca136', '系统默认', '000000', '', '0', '系统默认密码', '', '2019-11-24 00:17:09', '1', '2019-11-24 00:17:09', '1');
INSERT INTO `rm_dict` VALUES ('591c2d432bfd4fa8a020337f8ddd99c5', '92a4a7e39b524f0dac2dc57290590492', '菜单', '1', '', '0', '菜单', '', '2019-11-24 00:26:31', '1', '2019-11-24 00:26:31', '1');
INSERT INTO `rm_dict` VALUES ('6a89c01da9b245b5b2c324ab4fb78139', '92a4a7e39b524f0dac2dc57290590492', '按钮', '2', '', '1', '按钮', '', '2019-11-24 00:26:50', '1', '2019-11-24 00:26:50', '1');
INSERT INTO `rm_dict` VALUES ('76c48701fe4f4dac8102b1f11e739069', 'ed5d2d420f8d4416a4857ca5ecd2cea3', '男', '1', '', '0', '男', '', '2019-11-24 00:12:15', '1', '2019-11-24 00:12:15', '1');
INSERT INTO `rm_dict` VALUES ('92a4a7e39b524f0dac2dc57290590492', 'root', '资源类型', 'RESOURCETYPE', '', '2', '资源类型', '', '2019-11-24 00:26:02', '1', '2019-11-24 00:26:02', '1');
INSERT INTO `rm_dict` VALUES ('a47da99cd9c94988bf39bb10670ca136', 'root', '默认密码', 'DEFPWD', '', '0', '系统默认密码', '', '2019-11-24 00:16:11', '1', '2019-11-24 00:16:11', '1');
INSERT INTO `rm_dict` VALUES ('ed5d2d420f8d4416a4857ca5ecd2cea3', 'root', '性别', 'SEX', '', '1', '性别', '', '2019-11-24 00:06:41', '1', '2019-11-24 00:06:41', '1');

CREATE TABLE `rm_dorm` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL,
  `building_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '建筑物ID',
  `floor_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '楼层ID（b_building_floor）',
  `name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '名称',
  `code` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '编码',
  `dorm_sex` varchar(48) COLLATE utf8_bin DEFAULT NULL COMMENT '宿舍性别（1：男生寝室 2：女生寝室）',
  `status` int(2) DEFAULT NULL COMMENT '宿舍状态（1使用，2闲置，3不可用）',
  `type` int(2) DEFAULT NULL COMMENT '宿舍类型（1学生宿舍、2教师宿舍、3宿管宿舍、4其他宿舍',
  `bed_num` int(2) DEFAULT NULL COMMENT '床位数量',
  `is_deleted` bit(1) DEFAULT b'0' COMMENT '是否删除（0否，1是）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL,
  `last_modify_time` datetime DEFAULT NULL COMMENT '最终修改时间',
  `last_modify_user_id` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL,
  `memo` varchar(500) CHARACTER SET utf8 DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='宿舍基本信息';

CREATE TABLE `rm_dorm_bed` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '主键id',
  `dorm_id` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '宿舍id',
  `name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '床位名称',
  `code` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '床位号',
  `subject_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '入住人id（学生/教师/宿管/其他）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL,
  `last_modify_time` datetime DEFAULT NULL COMMENT '最终修改时间',
  `last_modify_user_id` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL,
  `memo` varchar(500) CHARACTER SET utf8 DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `base_id_index` (`subject_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='宿舍床位';

CREATE TABLE `rm_dorm_building` (
  `pk_dorm_building_id` varchar(32) NOT NULL COMMENT '主键',
  `dorm_building_type` int(1) DEFAULT NULL COMMENT '楼栋类型（0：校内 1：校外）',
  `dorm_building_num` varchar(20) DEFAULT NULL COMMENT '楼栋编号（1，2，A，B）',
  `dorm_building_levels` int(2) DEFAULT NULL COMMENT '楼栋总层数',
  `dorm_building_desc` varchar(500) DEFAULT NULL COMMENT '楼栋描述',
  `status` int(1) DEFAULT NULL COMMENT '状态 (1-生效，0-无效)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL,
  `last_modify_time` datetime DEFAULT NULL COMMENT '最终修改时间',
  `last_modify_user_id` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL,
  `memo` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`pk_dorm_building_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `rm_dorm_repair` (
  `id` varchar(32) NOT NULL,
  `dorm_id` varchar(32) DEFAULT NULL COMMENT '宿舍id',
  `repair_type` int(2) DEFAULT NULL COMMENT '维修类型(门窗、床、水电)',
  `repair_desc` varchar(255) DEFAULT NULL COMMENT '维修描述',
  `repair_img_url` varchar(255) DEFAULT NULL COMMENT '维修图片',
  `student_id` varchar(32) DEFAULT NULL COMMENT '报修人',
  `student_mobile` varchar(20) DEFAULT NULL COMMENT '报修人联系方式',
  `status` int(2) DEFAULT NULL COMMENT '状态（0：未维修1：已维修2：已报废）',
  `repair_cost` decimal(16,4) DEFAULT NULL COMMENT '维修费用',
  `repair_bill_url` varchar(255) DEFAULT NULL COMMENT '维修费用发票',
  `create_time` datetime DEFAULT NULL,
  `create_user_id` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL,
  `last_modify_time` datetime DEFAULT NULL,
  `last_modify_user_id` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL,
  `memo` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `rm_dorm_rule` (
  `id` varchar(32) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `status` int(2) DEFAULT NULL COMMENT '状态（1：生效 0：失效）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '创建人',
  `last_modify_time` datetime DEFAULT NULL,
  `last_modify_user_id` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL,
  `memo` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `rm_dorm_score` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `dorm_num` varchar(32) DEFAULT NULL COMMENT '宿舍号',
  `culture_score` int(2) DEFAULT NULL COMMENT '宿舍文化得分',
  `discipline_score` int(2) DEFAULT NULL COMMENT '纪律得分',
  `bed_score` int(2) DEFAULT NULL COMMENT '宿舍床铺得分',
  `desk_score` int(2) DEFAULT NULL COMMENT '书桌得分',
  `balcony_score` int(2) DEFAULT NULL COMMENT '阳台得分',
  `toilet_score` int(2) DEFAULT NULL COMMENT '厕所得分',
  `ground_score` int(2) DEFAULT NULL COMMENT '地面得分',
  `door_window_score` int(2) DEFAULT NULL COMMENT '门窗得分',
  `metope_score` int(2) DEFAULT NULL COMMENT '墙面得分',
  `total_score` int(2) DEFAULT NULL COMMENT '总计得分',
  `status` int(1) DEFAULT NULL COMMENT '状态（1生效 0 失效）',
  `create_time` datetime DEFAULT NULL,
  `create_user_id` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL,
  `last_modify_time` datetime DEFAULT NULL,
  `last_modify_user_id` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL,
  `memo` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `rm_dorm_violation` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `dorm_rule_id` varchar(32) DEFAULT NULL COMMENT '规则id',
  `dorm_id` varchar(32) DEFAULT NULL COMMENT '宿舍id',
  `student_id` varchar(32) DEFAULT NULL COMMENT '学生id',
  `violation_img_url` varchar(500) DEFAULT NULL COMMENT '违规图片url',
  `violation_reason` varchar(255) DEFAULT NULL COMMENT '违规原因',
  `solve_result` varchar(255) DEFAULT NULL COMMENT '解决结果',
  `status` int(2) DEFAULT NULL COMMENT '状态（1：已处理 0：未处理）',
  `create_time` datetime DEFAULT NULL COMMENT '处理时间',
  `create_user_id` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '处理人',
  `last_modify_time` datetime DEFAULT NULL,
  `last_modify_user_id` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL,
  `memo` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `rm_log` (
  `id` varchar(32) NOT NULL COMMENT '日志ID',
  `user_id` varchar(32) DEFAULT NULL COMMENT '操作用户id',
  `operation_content` varchar(100) DEFAULT NULL COMMENT '操作内容',
  `duration` int(11) DEFAULT NULL COMMENT '耗时',
  `method` varchar(255) DEFAULT NULL COMMENT '操作方法',
  `params` varchar(5000) DEFAULT NULL COMMENT '方法参数',
  `ip` varchar(64) DEFAULT NULL COMMENT '操作者IP',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `location` varchar(100) DEFAULT NULL COMMENT '操作地点',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `rm_resource` (
  `id` varchar(32) NOT NULL COMMENT '资源ID',
  `parent_id` varchar(32) NOT NULL COMMENT '父级ID',
  `name` varchar(50) NOT NULL COMMENT '资源名称',
  `route_name` varchar(50) DEFAULT NULL COMMENT '路由名称',
  `component` varchar(100) DEFAULT NULL COMMENT '路由组件',
  `resource_icon` varchar(100) DEFAULT NULL COMMENT '图标',
  `resource_type` tinyint(1) DEFAULT NULL COMMENT '资源类型（1-菜单，2-按钮）',
  `resource_url` varchar(255) DEFAULT NULL COMMENT '资源URL',
  `perms` varchar(20) DEFAULT NULL COMMENT '权限，默认为无权限anon',
  `sort` int(11) DEFAULT NULL COMMENT '资源序号',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态(-1，已删除，1-启用，2-停用,3-作废)',
  `is_parent` bit(1) DEFAULT NULL COMMENT '是否父节点-(0-是，1-否)',
  `create_time` datetime DEFAULT NULL,
  `create_user_id` varchar(32) DEFAULT NULL,
  `last_modify_time` datetime DEFAULT NULL,
  `last_modify_user_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `resource_name_index` (`name`) USING BTREE,
  UNIQUE KEY `resource_url_index` (`resource_url`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='资源信息表';

INSERT INTO `rm_resource` VALUES ('1e0af706d4854f7b9d0b8b6013fc405b', 'ea71d0b7204c4bc7ab1a3455c77eb85e', '角色保存', null, null, null, '2', '/dorm/role/save.html', 'roles::save', '0', '1', '', '2019-08-21 10:43:43', '1', '2019-08-21 10:43:43', '1');
INSERT INTO `rm_resource` VALUES ('347de92f327c40e290b1f33f8206266d', '93a2394ccf044c709ea660569b91d5ea', '用户保存', null, null, null, '2', null, 'users::save', '0', '1', '', '2019-08-21 10:57:11', '1', '2019-08-21 10:57:11', '1');
INSERT INTO `rm_resource` VALUES ('3939e23ff7a7481c9d36d3ebf1baf08c', 'e719a109a7014484901f657f7f3a8c3b', '资源管理', 'resource', 'Resource', 'el-icon-help', '1', '/home/resource', 'menu', '2', '1', '', '2019-08-21 10:38:06', '1', '2019-11-16 20:34:34', '973d35c93505423cac7004751c75565a');
INSERT INTO `rm_resource` VALUES ('49240f0695b44453b3cdccf7e2167188', '93a2394ccf044c709ea660569b91d5ea', '用户绑定单个角色', null, null, null, '2', null, 'users::role', '5', '1', '', '2019-08-21 11:00:18', '1', '2019-08-21 11:00:18', '1');
INSERT INTO `rm_resource` VALUES ('5455948952f441588d88ef3b194d6a91', '93a2394ccf044c709ea660569b91d5ea', '用户查看', null, null, null, '2', null, 'users::view', '6', '1', '', '2019-08-21 22:36:50', '1', '2019-08-21 22:36:50', '1');
INSERT INTO `rm_resource` VALUES ('6377b4d5ae084b6086a7b224488ee273', '93a2394ccf044c709ea660569b91d5ea', '用户绑定资源', null, null, null, '2', null, 'users::resources', '4', '1', '', '2019-08-21 10:59:31', '1', '2019-08-21 10:59:31', '1');
INSERT INTO `rm_resource` VALUES ('68cd6eb3e1264628a25052f9f8f24dba', '93a2394ccf044c709ea660569b91d5ea', '用户绑定角色', null, null, null, '2', null, 'users::roles', '3', '1', '', '2019-08-21 10:59:09', '1', '2019-08-21 10:59:09', '1');
INSERT INTO `rm_resource` VALUES ('6e4f27095b6640078aa217433a663d7b', 'ea71d0b7204c4bc7ab1a3455c77eb85e', '角色绑定资源', null, null, null, '2', null, 'roles::resources', '2', '1', '', '2019-08-21 10:52:09', '1', '2019-08-21 10:52:09', '1');
INSERT INTO `rm_resource` VALUES ('7b77b230570642e4b68182989e18460d', '93a2394ccf044c709ea660569b91d5ea', '用户删除', null, null, null, '2', null, 'users::delete', '1', '1', '', '2019-08-21 10:57:34', '1', '2019-08-21 10:57:34', '1');
INSERT INTO `rm_resource` VALUES ('83c6ad1f73d746de924a03d00069e790', 'ea71d0b7204c4bc7ab1a3455c77eb85e', '角色查看', null, null, null, '2', null, 'roles::view', '3', '1', '', '2019-08-21 22:36:03', '1', '2019-08-21 22:36:03', '1');
INSERT INTO `rm_resource` VALUES ('93a2394ccf044c709ea660569b91d5ea', 'e719a109a7014484901f657f7f3a8c3b', '用户管理', 'user', 'User', 'el-icon-user', '1', '/home/user', 'menu', '0', '1', '', '2019-08-21 10:37:14', '1', '2019-11-16 20:34:11', '973d35c93505423cac7004751c75565a');
INSERT INTO `rm_resource` VALUES ('9ee1344e62104f39a857ffc96d445423', '3939e23ff7a7481c9d36d3ebf1baf08c', '资源删除', null, null, null, '2', null, 'resources::delete', '1', '1', '', '2019-08-21 11:01:41', '1', '2019-08-21 11:01:41', '1');
INSERT INTO `rm_resource` VALUES ('ca4c6f3e713b4ca0b60e2752e3fc70db', '3939e23ff7a7481c9d36d3ebf1baf08c', '资源查看', null, null, null, '2', null, 'resources::view', '2', '1', '', '2019-08-21 22:37:25', '1', '2019-08-21 22:37:25', '1');
INSERT INTO `rm_resource` VALUES ('cc2a7dae77cc4625a1397b9c60fa5c51', '3939e23ff7a7481c9d36d3ebf1baf08c', '资源保存', null, null, null, '2', null, 'resources::save', '0', '1', '', '2019-08-21 11:01:26', '1', '2019-08-21 11:01:26', '1');
INSERT INTO `rm_resource` VALUES ('d0e73b3b14504550a554f552c58a91df', 'ea71d0b7204c4bc7ab1a3455c77eb85e', '角色删除', null, null, null, '2', null, 'roles::delete', '1', '1', '', '2019-08-21 10:44:41', '1', '2019-08-21 10:44:41', '1');
INSERT INTO `rm_resource` VALUES ('e719a109a7014484901f657f7f3a8c3b', 'root', '系统管理', 'routeName', 'component', 'el-icon-user', '1', 'test1', 'menu', '0', '1', '', '2019-08-21 10:20:19', '1', '2019-11-15 15:36:11', '973d35c93505423cac7004751c75565a');
INSERT INTO `rm_resource` VALUES ('ea71d0b7204c4bc7ab1a3455c77eb85e', 'e719a109a7014484901f657f7f3a8c3b', '角色管理', 'role', 'Role', 'el-icon-user', '1', '/home/role', 'menu', '1', '1', '', '2019-08-21 10:38:47', '1', '2019-08-21 10:38:47', '1');
INSERT INTO `rm_resource` VALUES ('ee00f923bcae44a48d73a343975788f3', '93a2394ccf044c709ea660569b91d5ea', '用户导出', null, null, null, '2', null, 'users::export', '2', '1', '', '2019-08-21 10:58:15', '1', '2019-08-21 10:58:15', '1');

CREATE TABLE `rm_role` (
  `id` varchar(32) NOT NULL,
  `name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `code` varchar(50) DEFAULT NULL COMMENT '角色编码',
  `description` varchar(255) DEFAULT NULL COMMENT '角色描述',
  `status` int(1) DEFAULT NULL COMMENT '用户状态(-1已删除，0失效，1-生效)',
  `create_time` datetime DEFAULT NULL,
  `create_user_id` varchar(32) DEFAULT NULL,
  `last_modify_time` datetime DEFAULT NULL,
  `last_modify_user_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色信息表';

INSERT INTO `rm_role` VALUES ('2e657e45f21a48f6a3f0111b590fbe1e', '匿名角色', 'anonym', '匿名角色，拥有除用户、角色、资源的删除权限外的所有权限', '1', '2019-08-25 21:03:12', '1', '2019-08-25 21:03:12', '1');
INSERT INTO `rm_role` VALUES ('c8cb2e89be9a4dc9b12d2375e879e98c', '管理员', 'admin', '管理员', '1', '2019-08-21 09:32:37', '1', '2019-08-21 09:32:37', '1');
INSERT INTO `rm_role` VALUES ('ec155fd23f21484d9d33289ae705b1a1', '超级管理员', 'superAdmin', '超级管理员', '1', '2019-08-21 09:32:22', '1', '2019-08-21 09:32:22', '1');

CREATE TABLE `rm_role_resource_rel` (
  `id` varchar(32) NOT NULL COMMENT '角色与资源关联主键',
  `role_id` varchar(32) DEFAULT NULL COMMENT '角色ID',
  `resource_id` varchar(32) DEFAULT NULL COMMENT '资源ID',
  `create_time` datetime DEFAULT NULL,
  `create_user_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_role_id` (`role_id`) USING BTREE,
  KEY `index_resource_id` (`resource_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色与资源关联表';

INSERT INTO `rm_role_resource_rel` VALUES ('0003e6443fde4ac281d37060801ecb42', 'ec155fd23f21484d9d33289ae705b1a1', '1b3445eac1e6482090186aa105d7049a', '2019-08-21 09:54:38', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('0c96f9eedb824d6780793b8ef5e8546b', 'ec155fd23f21484d9d33289ae705b1a1', '13a501fecb684be092a3a34e0ab5c30d', '2019-11-24 23:13:41', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('0f649f2e72c0450bb799ebefaa7136b6', 'ec155fd23f21484d9d33289ae705b1a1', '7b77b230570642e4b68182989e18460d', '2019-08-21 10:57:34', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('0f8ffc6512be47289cde85731f4a0b4f', '2e657e45f21a48f6a3f0111b590fbe1e', '83c6ad1f73d746de924a03d00069e790', '2019-08-25 21:16:56', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('187cece907044866832b0d0e3ca522b3', '2e657e45f21a48f6a3f0111b590fbe1e', '3939e23ff7a7481c9d36d3ebf1baf08c', '2019-08-25 21:16:56', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('18d02734dc2045098a21a2a7b4a62dcf', '2e657e45f21a48f6a3f0111b590fbe1e', '5455948952f441588d88ef3b194d6a91', '2019-08-25 21:16:56', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('19efe28f0e214a59b17dc0b80d33ef2d', '2e657e45f21a48f6a3f0111b590fbe1e', 'cc2a7dae77cc4625a1397b9c60fa5c51', '2019-08-25 21:16:56', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('1b6fc310a10b4eef97dae29d735e2cc3', 'ec155fd23f21484d9d33289ae705b1a1', 'ea71d0b7204c4bc7ab1a3455c77eb85e', '2019-08-21 10:38:47', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('1cc21f338b5640208874e78dd7e18f7e', 'ec155fd23f21484d9d33289ae705b1a1', '6e4f27095b6640078aa217433a663d7b', '2019-08-21 10:52:09', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('21adbb0b362a474e8b8c5eea53e867ad', 'ec155fd23f21484d9d33289ae705b1a1', '1859f0071df24afc9e6a3772e3a22ff2', '2019-11-24 23:25:09', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('29cab999af57473daa444e627a4fe136', 'ec155fd23f21484d9d33289ae705b1a1', '2c8c47c7d3eb413c8fa0f5823a6bc862', '2019-08-21 10:04:41', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('303651ac171849efb095de2b33e1b84c', '2e657e45f21a48f6a3f0111b590fbe1e', '93a2394ccf044c709ea660569b91d5ea', '2019-08-25 21:16:56', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('338f35e9d03241acaef6d389de9ae716', 'ec155fd23f21484d9d33289ae705b1a1', '1e0af706d4854f7b9d0b8b6013fc405b', '2019-08-21 10:43:43', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('359f236e193b42dd991aa9448e77ed8e', 'ec155fd23f21484d9d33289ae705b1a1', '7351a07ece4f4fc9bab359dcabaa23cf', '2019-08-21 10:11:24', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('3a9e009408c84b2580feb58c522d3cfa', 'ec155fd23f21484d9d33289ae705b1a1', 'ca4c6f3e713b4ca0b60e2752e3fc70db', '2019-08-21 22:37:25', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('3aa91054b2574336bc17cb685e4cd69e', 'ec155fd23f21484d9d33289ae705b1a1', 'cc2a7dae77cc4625a1397b9c60fa5c51', '2019-08-21 11:01:26', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('488c822aeebb455ebdfdf8e08dede4fd', 'ec155fd23f21484d9d33289ae705b1a1', 'd68735a434b74d01b502b330790d54ec', '2019-08-21 22:41:45', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('4cfaad5ff05c4e0fa3af9763ddf5f5e6', '2e657e45f21a48f6a3f0111b590fbe1e', '1e0af706d4854f7b9d0b8b6013fc405b', '2019-08-25 21:16:56', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('5389d8f797b24ed9a7b97c8ec175da47', 'ec155fd23f21484d9d33289ae705b1a1', 'cef7b163d7e548d380e40a67fbc22522', '2019-11-24 23:42:19', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('6ae90b8d3c37432385c45e5e347d9cbf', 'ec155fd23f21484d9d33289ae705b1a1', '68cd6eb3e1264628a25052f9f8f24dba', '2019-08-21 10:59:09', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('728a2612aba04b1aacf7dd6118554838', 'ec155fd23f21484d9d33289ae705b1a1', 'ee00f923bcae44a48d73a343975788f3', '2019-08-21 10:58:15', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('78e3544bad8c47529cc71afcda58b1f4', 'ec155fd23f21484d9d33289ae705b1a1', 'dfc87fa0906c4e6198896bc793a0bd9c', '2019-11-25 00:23:00', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('810aee99c26d40abbcd4658c58d4745c', 'ec155fd23f21484d9d33289ae705b1a1', '90f055a438eb44f39682c47cd64134b1', '2019-11-25 00:19:39', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('874c449bd0944007a458706969b83646', 'ec155fd23f21484d9d33289ae705b1a1', '5455948952f441588d88ef3b194d6a91', '2019-08-21 22:36:50', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('933ed7952a874f68972eada2e1016b89', 'ec155fd23f21484d9d33289ae705b1a1', '93a2394ccf044c709ea660569b91d5ea', '2019-08-21 10:37:14', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('93407de553564e1bab97395b71ed01fb', 'ec155fd23f21484d9d33289ae705b1a1', 'a85d7f0b1feb472580a676f5f0b4ad88', '2019-11-16 21:14:13', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('9502e3d13e054d1bbb387895870b08dc', 'ec155fd23f21484d9d33289ae705b1a1', 'd0e73b3b14504550a554f552c58a91df', '2019-08-21 10:44:41', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('9ac025708323437baefdcd4cb9637199', 'ec155fd23f21484d9d33289ae705b1a1', 'e719a109a7014484901f657f7f3a8c3b', '2019-08-21 10:20:19', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('a0f4935469b94661a3d5093056252ce4', '2e657e45f21a48f6a3f0111b590fbe1e', 'ca4c6f3e713b4ca0b60e2752e3fc70db', '2019-08-25 21:16:56', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('a484215035d6416ca9d441978e969d17', 'ec155fd23f21484d9d33289ae705b1a1', '83c6ad1f73d746de924a03d00069e790', '2019-08-21 22:36:03', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('a6f079cccd314be4b240bbb172a4ecb5', 'ec155fd23f21484d9d33289ae705b1a1', '6377b4d5ae084b6086a7b224488ee273', '2019-08-21 10:59:31', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('a8978c9727e541cebc4b5d58a4723b11', '2e657e45f21a48f6a3f0111b590fbe1e', '347de92f327c40e290b1f33f8206266d', '2019-08-25 21:16:56', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('a9514316f21e485d9e23f338158041ad', 'ec155fd23f21484d9d33289ae705b1a1', 'dd900e96a390426dba30ebb70bed26ea', '2019-11-25 00:28:01', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('b5722b6122b54410a4a0480cae80a305', '2e657e45f21a48f6a3f0111b590fbe1e', 'e719a109a7014484901f657f7f3a8c3b', '2019-08-25 21:16:56', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('bff0c0055fd24d3e950ef39e40d0b7fe', 'ec155fd23f21484d9d33289ae705b1a1', '3939e23ff7a7481c9d36d3ebf1baf08c', '2019-08-21 10:38:06', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('c0e270a6a96d4f4396ed9c369045c93f', 'ec155fd23f21484d9d33289ae705b1a1', 'd9484a8b6afc4de78be1323a96c4b1c7', '2019-08-21 22:38:32', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('c89084fc50ae460c96a071edef3c2ff5', 'ec155fd23f21484d9d33289ae705b1a1', '49240f0695b44453b3cdccf7e2167188', '2019-08-21 11:00:18', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('c99dccd49ac943269fa841fa68519fd0', '2e657e45f21a48f6a3f0111b590fbe1e', 'ee00f923bcae44a48d73a343975788f3', '2019-08-25 21:16:56', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('cec0469828d24b9dace656946eb381b1', 'ec155fd23f21484d9d33289ae705b1a1', '347de92f327c40e290b1f33f8206266d', '2019-08-21 10:57:11', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('d3d1688611d542d8b4eec336b6e7df97', 'ec155fd23f21484d9d33289ae705b1a1', '9ee1344e62104f39a857ffc96d445423', '2019-08-21 11:01:41', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('eddda0f784184b09aff9950b3705f9f9', '2e657e45f21a48f6a3f0111b590fbe1e', 'ea71d0b7204c4bc7ab1a3455c77eb85e', '2019-08-25 21:16:56', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('fb9cf7e1f73c47e18eb324093f3c46ef', 'ec155fd23f21484d9d33289ae705b1a1', 'bfa105ae82644d4a91b58ad79d518e83', '2019-11-25 00:24:17', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('ff2ccbc762a64a448a23fe802b7d3b0e', 'ec155fd23f21484d9d33289ae705b1a1', 'f654d58edecf4852a8417f75f6d49e8f', '2019-11-25 00:27:19', '1');

CREATE TABLE `rm_school_campus` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '校区基本数据id',
  `code` varchar(45) COLLATE utf8_bin NOT NULL COMMENT '校区号',
  `name` varchar(45) COLLATE utf8_bin NOT NULL COMMENT '校区名称',
  `address` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '校区地址',
  `postal` varchar(45) COLLATE utf8_bin NOT NULL COMMENT '校区邮政编码',
  `tel` varchar(45) COLLATE utf8_bin NOT NULL COMMENT '校区联系电话',
  `fax` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '校区传真电话',
  `leader` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '负责人id',
  `is_deleted` bit(1) DEFAULT b'0' COMMENT '是否删除（0否，1是）',
  `create_time` datetime DEFAULT NULL,
  `create_user_id` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL,
  `last_modify_time` datetime DEFAULT NULL,
  `last_modify_user_id` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_id` (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='校区基本数据表';

CREATE TABLE `rm_student` (
  `pk_student_id` varchar(32) NOT NULL COMMENT '主键',
  `student_name` varchar(50) NOT NULL COMMENT '学校姓名',
  `student_num` int(10) NOT NULL COMMENT '学号',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号',
  `gender` int(1) DEFAULT NULL COMMENT '性别',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `img_url` varchar(500) DEFAULT NULL COMMENT '头像',
  `status` int(1) DEFAULT NULL COMMENT '状态 (1-生效，0-无效)',
  `create_time` datetime DEFAULT NULL,
  `create_user_id` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL,
  `last_modify_time` datetime DEFAULT NULL,
  `last_modify_user_id` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL,
  PRIMARY KEY (`pk_student_id`),
  UNIQUE KEY `student_name_num` (`student_name`,`student_num`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `rm_student_basic` (
  `id` bigint(20) NOT NULL COMMENT '学生基本数据表id',
  `name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `code` varchar(20) DEFAULT NULL COMMENT '学号',
  `id_card` varchar(50) DEFAULT NULL COMMENT '身份证件号',
  `sex` int(2) DEFAULT NULL COMMENT '性别',
  `tel` varchar(50) DEFAULT NULL COMMENT '学生联系电话',
  `email` varchar(50) DEFAULT NULL COMMENT '电子信箱',
  `level` varchar(10) DEFAULT NULL COMMENT '层次（本科、专科）',
  `class_name` varchar(100) DEFAULT NULL COMMENT '班级名称',
  `race` varchar(10) DEFAULT '名族',
  `native_place` varchar(100) DEFAULT '' COMMENT '籍贯',
  `qq` int(20) DEFAULT NULL COMMENT 'qq号',
  `family_address` varchar(100) DEFAULT NULL COMMENT '家庭地址',
  `family_tel` varchar(50) DEFAULT NULL COMMENT '家人电话',
  `counselor` varchar(50) DEFAULT '' COMMENT '辅导员',
  `password` varchar(50) DEFAULT NULL,
  `img_url` varchar(255) DEFAULT NULL COMMENT '头像',
  `create_time` datetime DEFAULT NULL,
  `create_user_id` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL,
  `last_modify_time` datetime DEFAULT NULL,
  `last_modify_user_id` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT b'0' COMMENT '是否删除（0否，1是）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学生基本数据表';

CREATE TABLE `rm_user` (
  `user_id` varchar(32) NOT NULL COMMENT '用户ID',
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户名',
  `name` varchar(20) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `gender` tinyint(1) DEFAULT NULL COMMENT '性别 1-男，2-女',
  `tel_phone` varchar(20) DEFAULT NULL COMMENT '座机号码',
  `identify_type` varchar(40) DEFAULT NULL COMMENT '证件类型 1-身份证,2-学生证',
  `identify_no` varchar(40) DEFAULT NULL COMMENT '证件号码',
  `nick_name` varchar(255) DEFAULT NULL COMMENT '昵称',
  `user_type` int(11) DEFAULT NULL COMMENT '用户类型(1-系统管理员，2-宿管管理员，3-辅导员管理员,4-学生处管理员，5-学生)',
  `status` int(11) DEFAULT NULL COMMENT '用户状态(0已删除，1启用，2禁用)',
  `img_url` varchar(255) DEFAULT NULL COMMENT '头像',
  `identify_img_url` varchar(255) DEFAULT NULL COMMENT '证件照片',
  `work_img_url` varchar(255) DEFAULT NULL COMMENT '工作照',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `last_login_time` datetime DEFAULT NULL COMMENT '上次登录时间',
  `create_time` datetime DEFAULT NULL,
  `create_user_id` varchar(32) DEFAULT NULL,
  `last_modify_time` datetime DEFAULT NULL,
  `last_modify_user_id` varchar(32) DEFAULT NULL,
  `audit_time` datetime DEFAULT NULL COMMENT '审核时间',
  `audit_user_id` varchar(32) DEFAULT NULL COMMENT '审核人',
  `third_part_qq` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT 'qq登录',
  `third_part_wechat` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '微信登录',
  `client_id` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '登录手机地址',
  `platform_type` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '登录手机类型： 1 安卓 ；2 IOS',
  `channel_id` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '推送消息Id',
  `easemob_uid` varchar(4000) CHARACTER SET utf8 DEFAULT NULL COMMENT '环信uuid',
  `qr_code` varchar(255) DEFAULT NULL COMMENT '二维码',
  `agent_level` int(1) DEFAULT NULL COMMENT '代理级别（1-省代，2-市代，3-区代）',
  `province_id` int(11) DEFAULT NULL COMMENT '省级ID',
  `city_id` int(11) DEFAULT NULL COMMENT '市级id',
  `area_id` int(11) DEFAULT NULL COMMENT '区域id',
  `memo` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `index_mobile` (`mobile`) USING BTREE,
  UNIQUE KEY `index_user_name` (`user_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息表';

INSERT INTO `rm_user` VALUES ('1', 'aishu', 'aishu', '744837cc03a9df3a5df0c8e196dfec7b', '15328417106', '0', null, '1', '511324199702101111', '格子衬衫', '1', '1', null, null, null, '2181250231@163.com', '2019-11-25 21:36:11', '2019-08-21 09:23:39', '1', '2019-11-25 21:36:11', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, '最帅的辣个男人');
INSERT INTO `rm_user` VALUES ('10', 'xiaozhu', 'xiaozhu', 'f9fe0689a2ad1773d0eaabddab6218ce', '15328417109', '0', null, '1', '511324199702101112', 'xiaozhu', '1', '1', null, null, null, '2181250234@163.com', '2019-11-17 23:17:43', '2019-08-21 11:24:21', '1', '2019-11-17 23:17:43', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, '匿名用户');
INSERT INTO `rm_user` VALUES ('973d35c93505423cac7004751c75565a', 'yang', 'yang', 'f138137a73e163022ee429851e421e18', '13551354962', '1', null, null, null, null, '1', '1', null, null, null, '1264576767@qq.com', null, '2019-10-19 21:57:06', '10', '2019-10-31 21:30:57', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);

CREATE TABLE `rm_user_role_rel` (
  `id` varchar(32) NOT NULL,
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户ID',
  `role_id` varchar(32) DEFAULT NULL COMMENT '资源id或者角色id',
  `create_time` datetime DEFAULT NULL,
  `create_user_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_user_id` (`user_id`) USING BTREE,
  KEY `index_subject_id` (`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户与角色关联表';

INSERT INTO `rm_user_role_rel` VALUES ('1033a2150d7b47deb4fc09e14cd11aaa', '10', '2e657e45f21a48f6a3f0111b590fbe1e', '2019-08-25 21:05:06', '1');
INSERT INTO `rm_user_role_rel` VALUES ('bbec66146a0642f7840c2bc307d86341', '1', 'c8cb2e89be9a4dc9b12d2375e879e98c', '2019-08-21 11:16:00', '1');
INSERT INTO `rm_user_role_rel` VALUES ('d2366fb620ae4dd4a6abe4f5801ab137', '1', 'ec155fd23f21484d9d33289ae705b1a1', '2019-08-21 11:16:00', '1');
INSERT INTO `rm_user_role_rel` VALUES ('a5f9b3687fba478c8b1a05b920cad16f', '973d35c93505423cac7004751c75565a', 'ec155fd23f21484d9d33289ae705b1a1', '2019-10-23 14:29:36', '1');
