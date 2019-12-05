CREATE TABLE `rm_building` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL,
  `campus_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '校区',
  `name` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '名称',
  `code` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '编码',
  `floor_start` int(3) DEFAULT NULL COMMENT '起始楼层',
  `floor_total` int(3) DEFAULT NULL COMMENT '楼层数量',
  `status` tinyint(2) DEFAULT NULL COMMENT '使用状态(1使用，2闲置)',
  `type` tinyint(2) DEFAULT NULL COMMENT '建筑类型 1校内，2校外',
  `certificate_code` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '产权证号',
  `is_deleted` bit(1) DEFAULT b'0' COMMENT '是否删除（0否，1是）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL,
  `last_modify_time` datetime DEFAULT NULL COMMENT '最终修改时间',
  `last_modify_user_id` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL,
  `memo` varchar(500) CHARACTER SET utf8 DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of rm_building
-- ----------------------------
INSERT INTO `rm_building` VALUES ('37291642945847508cf422e3db2dce62', '127b55f3025d412a827664670152f257', '一号宿舍楼', 'string', '-2', '5', '1', '1', '21231', '', '2019-12-01 18:58:12', '1', '2019-12-01 18:58:12', '1', '宿舍楼');
INSERT INTO `rm_building` VALUES ('b4c13fdf7a20407a918d4e5aa5b7da11', '127b55f3025d412a827664670152f257', '二号宿舍楼', 'stringss', '3', '5', '1', '1', '21231', '', '2019-12-01 18:59:07', '1', '2019-12-01 18:59:07', '1', '宿舍楼');

CREATE TABLE `rm_building_floor` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL,
  `building_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '建筑物ID',
  `name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '名称',
  `code` int(3) DEFAULT NULL COMMENT '楼层',
  `is_deleted` bit(1) DEFAULT b'0' COMMENT '是否删除（0否，1是）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL,
  `last_modify_time` datetime DEFAULT NULL COMMENT '最终修改时间',
  `last_modify_user_id` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='建筑物楼层';

-- ----------------------------
-- Records of rm_building_floor
-- ----------------------------
INSERT INTO `rm_building_floor` VALUES ('2292e29fe7804af9ad27699c35edb19c', 'b4c13fdf7a20407a918d4e5aa5b7da11', '5楼', '5', '', '2019-12-01 18:59:07', '1', '2019-12-01 18:59:07', '1');
INSERT INTO `rm_building_floor` VALUES ('31f4de0ad6664f0380ff4ddaa31e6981', '37291642945847508cf422e3db2dce62', '-2楼', '-2', '', '2019-12-01 18:58:12', '1', '2019-12-01 18:58:12', '1');
INSERT INTO `rm_building_floor` VALUES ('5893d038d472431bb7dc9c83ce83a7e2', 'b4c13fdf7a20407a918d4e5aa5b7da11', '6楼', '6', '', '2019-12-01 18:59:07', '1', '2019-12-01 18:59:07', '1');
INSERT INTO `rm_building_floor` VALUES ('5d5b010a65e343bc98e3520cd548a6ec', '37291642945847508cf422e3db2dce62', '3楼', '3', '', '2019-12-01 18:58:12', '1', '2019-12-01 18:58:12', '1');
INSERT INTO `rm_building_floor` VALUES ('6b671bd9d137476ea543d85759826bb8', 'b4c13fdf7a20407a918d4e5aa5b7da11', '3楼', '3', '', '2019-12-01 18:59:07', '1', '2019-12-01 18:59:07', '1');
INSERT INTO `rm_building_floor` VALUES ('73e64a44bae54b8e86dd293700e1fcf5', '37291642945847508cf422e3db2dce62', '1楼', '1', '', '2019-12-01 18:58:12', '1', '2019-12-01 18:58:12', '1');
INSERT INTO `rm_building_floor` VALUES ('884b3e8711394a5181ba15d280c7a180', 'b4c13fdf7a20407a918d4e5aa5b7da11', '7楼', '7', '', '2019-12-01 18:59:07', '1', '2019-12-01 18:59:07', '1');
INSERT INTO `rm_building_floor` VALUES ('90f3070f571544e89f8f86c6ec70e661', '37291642945847508cf422e3db2dce62', '2楼', '2', '', '2019-12-01 18:58:12', '1', '2019-12-01 18:58:12', '1');
INSERT INTO `rm_building_floor` VALUES ('9d3cca9c029d4b8c9907380d0f2735fe', 'b4c13fdf7a20407a918d4e5aa5b7da11', '4楼', '4', '', '2019-12-01 18:59:07', '1', '2019-12-01 18:59:07', '1');
INSERT INTO `rm_building_floor` VALUES ('f8ef245e631d4f479c0eb6cccb8150e4', '37291642945847508cf422e3db2dce62', '-1楼', '-1', '', '2019-12-01 18:58:12', '1', '2019-12-01 18:58:12', '1');

CREATE TABLE `rm_dorm` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL,
  `floor_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '楼层ID（b_building_floor）',
  `name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '名称',
  `code` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '编码',
  `dorm_sex` tinyint(1) DEFAULT NULL COMMENT '宿舍性别（1：男生寝室 2：女生寝室）',
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

-- ----------------------------
-- Records of rm_dorm
-- ----------------------------
INSERT INTO `rm_dorm` VALUES ('c657c6539ea849c29e1f5e0d78ba7209', '5893d038d472431bb7dc9c83ce83a7e2', 'B-605', 'B-605', '1', '1', '1', '8', '', '2019-12-01 20:50:09', '1', '2019-12-01 20:50:09', '1', null);

CREATE TABLE `rm_dorm_bed` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '主键id',
  `dorm_id` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '宿舍id',
  `name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '床位名称',
  `code` int(3) DEFAULT NULL COMMENT '床位号',
  `subject_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '入住人id（学生/教师/宿管/其他）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL,
  `last_modify_time` datetime DEFAULT NULL COMMENT '最终修改时间',
  `last_modify_user_id` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL,
  `memo` varchar(500) CHARACTER SET utf8 DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `base_id_index` (`subject_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='宿舍床位';

-- ----------------------------
-- Records of rm_dorm_bed
-- ----------------------------
INSERT INTO `rm_dorm_bed` VALUES ('2773c172f56d48bbb870ab125d08beb4', 'c657c6539ea849c29e1f5e0d78ba7209', '7号床', '7', '1', '2019-12-01 20:50:09', '1', '2019-12-01 20:50:09', '1', 'B-605-7号床');
INSERT INTO `rm_dorm_bed` VALUES ('290b7ff346de42db886c1b3829489534', 'c657c6539ea849c29e1f5e0d78ba7209', '1号床', '1', null, '2019-12-01 20:50:09', '1', '2019-12-01 20:50:09', '1', 'B-605-1号床');
INSERT INTO `rm_dorm_bed` VALUES ('703fdb1855664067a4732c2694c8455d', 'c657c6539ea849c29e1f5e0d78ba7209', '6号床', '6', null, '2019-12-01 20:50:09', '1', '2019-12-01 20:50:09', '1', 'B-605-6号床');
INSERT INTO `rm_dorm_bed` VALUES ('a0a8b500e35141c69e1afdc77720c3dd', 'c657c6539ea849c29e1f5e0d78ba7209', '4号床', '4', null, '2019-12-01 20:50:09', '1', '2019-12-01 20:50:09', '1', 'B-605-4号床');
INSERT INTO `rm_dorm_bed` VALUES ('a6f9556a141c49fdac84d155aeedb0bc', 'c657c6539ea849c29e1f5e0d78ba7209', '3号床', '3', null, '2019-12-01 20:50:09', '1', '2019-12-01 20:50:09', '1', 'B-605-3号床');
INSERT INTO `rm_dorm_bed` VALUES ('a74f5f132d6440b89eb56d385387136f', 'c657c6539ea849c29e1f5e0d78ba7209', '5号床', '5', null, '2019-12-01 20:50:09', '1', '2019-12-01 20:50:09', '1', 'B-605-5号床');
INSERT INTO `rm_dorm_bed` VALUES ('cf09b6a2563c40bebc3dec175adeb3b4', 'c657c6539ea849c29e1f5e0d78ba7209', '2号床', '2', null, '2019-12-01 20:50:09', '1', '2019-12-01 20:50:09', '1', 'B-605-2号床');
INSERT INTO `rm_dorm_bed` VALUES ('db911544f53646cc9f58352e8574dcc9', 'c657c6539ea849c29e1f5e0d78ba7209', '8号床', '8', null, '2019-12-01 20:50:09', '1', '2019-12-01 20:50:09', '1', 'B-605-8号床');

CREATE TABLE `rm_dorm_repair` (
  `id` varchar(32) NOT NULL,
  `dorm_code` varchar(32) DEFAULT NULL COMMENT '宿舍号',
  `type` int(2) DEFAULT NULL COMMENT '维修类型(门窗、床、水电)',
  `description` varchar(255) DEFAULT NULL COMMENT '维修描述',
  `img_url` varchar(255) DEFAULT NULL COMMENT '维修图片',
  `student_code` varchar(32) DEFAULT NULL COMMENT '报修人',
  `student_mobile` varchar(20) DEFAULT NULL COMMENT '报修人联系方式',
  `status` int(2) DEFAULT NULL COMMENT '状态（0：未维修1：已维修2：已报废）',
  `cost` decimal(16,4) DEFAULT NULL COMMENT '维修费用',
  `bill_url` varchar(255) DEFAULT NULL COMMENT '维修费用发票',
  `is_deleted` bit(1) DEFAULT b'0' COMMENT '是否删除（0否，1是）',
  `create_time` datetime DEFAULT NULL,
  `create_user_id` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL,
  `last_modify_time` datetime DEFAULT NULL,
  `last_modify_user_id` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL,
  `memo` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='维修信息表';

-- ----------------------------
-- Records of rm_dorm_repair
-- ----------------------------

CREATE TABLE `rm_dorm_score` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `dorm_code` varchar(32) DEFAULT NULL COMMENT '宿舍号',
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
  `status` tinyint(1) DEFAULT NULL COMMENT '状态（1生效 0 失效）',
  `is_deleted` bit(1) DEFAULT b'0' COMMENT '是否删除（0否，1是）',
  `create_time` datetime DEFAULT NULL,
  `create_user_id` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL,
  `last_modify_time` datetime DEFAULT NULL,
  `last_modify_user_id` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL,
  `memo` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='宿舍评分表';

CREATE TABLE `rm_dorm_violation` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `dorm_code` varchar(32) DEFAULT NULL COMMENT '宿舍号',
  `student_code` varchar(32) DEFAULT NULL COMMENT '学生id',
  `img_url` varchar(500) DEFAULT NULL COMMENT '违规图片url',
  `reason` varchar(255) DEFAULT NULL COMMENT '违规原因',
  `result` varchar(255) DEFAULT NULL COMMENT '解决结果',
  `status` int(2) DEFAULT NULL COMMENT '状态（1：已处理 0：未处理）',
  `is_deleted` bit(1) DEFAULT b'0' COMMENT '是否删除（0否，1是）',
  `create_time` datetime DEFAULT NULL COMMENT '处理时间',
  `create_user_id` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '处理人',
  `last_modify_time` datetime DEFAULT NULL,
  `last_modify_user_id` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL,
  `memo` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='违规信息表';

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

INSERT INTO `rm_school_campus` VALUES ('127b55f3025d412a827664670152f257', '12312313', '西南交通大学南充校区', '西南交通大学南充校区', '12313', '123123', '23131', '李林洋', '', '2019-12-01 17:39:54', '1', '2019-12-01 17:39:54', '1');

CREATE TABLE `rm_student_basic` (
  `id` bigint(20) NOT NULL COMMENT '学生基本数据表id',
  `name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `code` varchar(20) DEFAULT NULL COMMENT '学号',
  `card_id` varchar(50) DEFAULT NULL COMMENT '身份证件号',
  `grade_id` varchar(50) DEFAULT NULL COMMENT '年级',
  `major_id` varchar(50) DEFAULT NULL COMMENT '专业id',
  `class_id` varchar(50) DEFAULT NULL COMMENT '班级名称',
  `sex` int(2) DEFAULT NULL COMMENT '性别',
  `tel` varchar(50) DEFAULT NULL COMMENT '学生联系电话',
  `email` varchar(50) DEFAULT NULL COMMENT '电子信箱',
  `level` varchar(10) DEFAULT NULL COMMENT '层次（本科、专科）',
  `race` varchar(10) DEFAULT '' COMMENT '名族',
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

-- ----------------------------
-- Records of rm_student_basic
-- ----------------------------
INSERT INTO `rm_student_basic` VALUES ('1', '李林洋', '20161545', '522124124141', '2016级', '计算机专业', '计算机二班', '1', '1341414124', '21312@qq', '大学', '汉族', '阿三大苏打', '213231', '213123123', '231231', '张素珍', null, null, '2019-12-05 21:21:26', '1', '2019-12-05 21:21:31', '1', '');
