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
INSERT INTO `rm_building` VALUES ('e5be8fe57f7e437ba26726aa88f0263b', '1', '校外A栋', 'A', '1', '10', '0', '0', '23124141', '', '2019-12-06 22:03:14', '1', '2019-12-06 22:03:14', '1', '校外A栋');
INSERT INTO `rm_building` VALUES ('f516b958a40c4f4a9a3b9901ea6be6a1', '1', '校外B栋', 'B', '2', '12', '0', '0', '23124141', '', '2019-12-06 22:03:54', '1', '2019-12-06 22:03:54', '1', '校外B栋');

-- ----------------------------
-- Table structure for `rm_building_floor`
-- ----------------------------
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
INSERT INTO `rm_building_floor` VALUES ('09fd28ccfe994d4f8f4b0329017600fb', 'e5be8fe57f7e437ba26726aa88f0263b', '5层', '5', '', '2019-12-06 22:03:14', '1', '2019-12-06 22:03:14', '1');
INSERT INTO `rm_building_floor` VALUES ('10f94e7a3a024cf6aa0bb896303d1321', 'f516b958a40c4f4a9a3b9901ea6be6a1', '8层', '8', '', '2019-12-06 22:03:54', '1', '2019-12-06 22:03:54', '1');
INSERT INTO `rm_building_floor` VALUES ('124c8661d08c41538cf56d3e0a03cf5e', 'f516b958a40c4f4a9a3b9901ea6be6a1', '5层', '5', '', '2019-12-06 22:03:54', '1', '2019-12-06 22:03:54', '1');
INSERT INTO `rm_building_floor` VALUES ('17f9c169b8254c7791b352b341b77497', 'e5be8fe57f7e437ba26726aa88f0263b', '1层', '1', '', '2019-12-06 22:03:14', '1', '2019-12-06 22:03:14', '1');
INSERT INTO `rm_building_floor` VALUES ('1a61faef022941808916bd134528031f', 'e5be8fe57f7e437ba26726aa88f0263b', '8层', '8', '', '2019-12-06 22:03:14', '1', '2019-12-06 22:03:14', '1');
INSERT INTO `rm_building_floor` VALUES ('1ff34d2924954f00ad9259f5e435761b', 'e5be8fe57f7e437ba26726aa88f0263b', '3层', '3', '', '2019-12-06 22:03:14', '1', '2019-12-06 22:03:14', '1');
INSERT INTO `rm_building_floor` VALUES ('35594a52c7c841f39f93fe020ae54d35', 'f516b958a40c4f4a9a3b9901ea6be6a1', '9层', '9', '', '2019-12-06 22:03:54', '1', '2019-12-06 22:03:54', '1');
INSERT INTO `rm_building_floor` VALUES ('4cfe10a4807c4062ad06fabc7dc87c4d', 'f516b958a40c4f4a9a3b9901ea6be6a1', '2层', '2', '', '2019-12-06 22:03:54', '1', '2019-12-06 22:03:54', '1');
INSERT INTO `rm_building_floor` VALUES ('509ee62c00c24440aaba8a73624b1bb0', 'e5be8fe57f7e437ba26726aa88f0263b', '2层', '2', '', '2019-12-06 22:03:14', '1', '2019-12-06 22:03:14', '1');
INSERT INTO `rm_building_floor` VALUES ('5bfe689e1e4642a3b0d151de7b58942a', 'f516b958a40c4f4a9a3b9901ea6be6a1', '10层', '10', '', '2019-12-06 22:03:54', '1', '2019-12-06 22:03:54', '1');
INSERT INTO `rm_building_floor` VALUES ('7ec8a0875dbe4c7b9e55b919b867d678', 'e5be8fe57f7e437ba26726aa88f0263b', '7层', '7', '', '2019-12-06 22:03:14', '1', '2019-12-06 22:03:14', '1');
INSERT INTO `rm_building_floor` VALUES ('8a98b2fdb9284029b4b000c58528a12a', 'f516b958a40c4f4a9a3b9901ea6be6a1', '13层', '13', '', '2019-12-06 22:03:54', '1', '2019-12-06 22:03:54', '1');
INSERT INTO `rm_building_floor` VALUES ('98e5013b47084d659bbba5c3dcaeac26', 'f516b958a40c4f4a9a3b9901ea6be6a1', '4层', '4', '', '2019-12-06 22:03:54', '1', '2019-12-06 22:03:54', '1');
INSERT INTO `rm_building_floor` VALUES ('a4b181b13f0c41ab998d5927261f7f0a', 'f516b958a40c4f4a9a3b9901ea6be6a1', '3层', '3', '', '2019-12-06 22:03:54', '1', '2019-12-06 22:03:54', '1');
INSERT INTO `rm_building_floor` VALUES ('a9294c5434b74039929f2bba4fdb39d7', 'f516b958a40c4f4a9a3b9901ea6be6a1', '12层', '12', '', '2019-12-06 22:03:54', '1', '2019-12-06 22:03:54', '1');
INSERT INTO `rm_building_floor` VALUES ('c304ef09cd4843229f755ef0f10d427b', 'e5be8fe57f7e437ba26726aa88f0263b', '9层', '9', '', '2019-12-06 22:03:14', '1', '2019-12-06 22:03:14', '1');
INSERT INTO `rm_building_floor` VALUES ('c7d239302732453f964938ff87a8e471', 'f516b958a40c4f4a9a3b9901ea6be6a1', '7层', '7', '', '2019-12-06 22:03:54', '1', '2019-12-06 22:03:54', '1');
INSERT INTO `rm_building_floor` VALUES ('de94d61bc9cf44648968f715aacc6ea4', 'f516b958a40c4f4a9a3b9901ea6be6a1', '11层', '11', '', '2019-12-06 22:03:54', '1', '2019-12-06 22:03:54', '1');
INSERT INTO `rm_building_floor` VALUES ('e0ad43aa0c06427d990fd4647dbca779', 'e5be8fe57f7e437ba26726aa88f0263b', '6层', '6', '', '2019-12-06 22:03:14', '1', '2019-12-06 22:03:14', '1');
INSERT INTO `rm_building_floor` VALUES ('f3e242938fab4571b58abce0aa35b19c', 'f516b958a40c4f4a9a3b9901ea6be6a1', '6层', '6', '', '2019-12-06 22:03:54', '1', '2019-12-06 22:03:54', '1');
INSERT INTO `rm_building_floor` VALUES ('f75b424259024044acf375b01b70d44e', 'e5be8fe57f7e437ba26726aa88f0263b', '10层', '10', '', '2019-12-06 22:03:14', '1', '2019-12-06 22:03:14', '1');
INSERT INTO `rm_building_floor` VALUES ('fb51cab9f94746f6a6fb3a2a04612dc1', 'e5be8fe57f7e437ba26726aa88f0263b', '4层', '4', '', '2019-12-06 22:03:14', '1', '2019-12-06 22:03:14', '1');

-- ----------------------------
-- Table structure for `rm_dorm`
-- ----------------------------
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
INSERT INTO `rm_dorm` VALUES ('0061460a66e643c59d942f0f389089bd', '10f94e7a3a024cf6aa0bb896303d1321', 'B-812', 'B-812', '1', '1', '1', '10', '', '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', null);
INSERT INTO `rm_dorm` VALUES ('07b916dd1b774bd6a70d93bbf9629e49', '10f94e7a3a024cf6aa0bb896303d1321', 'B-807', 'B-807', '1', '1', '1', '10', '', '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', null);
INSERT INTO `rm_dorm` VALUES ('217db757167e49699c3bb6bb48d6b838', '10f94e7a3a024cf6aa0bb896303d1321', 'B-806', 'B-806', '1', '1', '1', '10', '', '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', null);
INSERT INTO `rm_dorm` VALUES ('3e24aa60c54b421b98440928b03e2775', '10f94e7a3a024cf6aa0bb896303d1321', 'B-813', 'B-813', '1', '1', '1', '10', '', '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', null);
INSERT INTO `rm_dorm` VALUES ('6c7e91499aff4d8896616f9db1142b12', '10f94e7a3a024cf6aa0bb896303d1321', 'B-805', 'B-805', '1', '1', '1', '10', '', '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', null);
INSERT INTO `rm_dorm` VALUES ('6d502e1abed84766879d046da839a695', '10f94e7a3a024cf6aa0bb896303d1321', 'B-814', 'B-814', '1', '1', '1', '10', '', '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', null);
INSERT INTO `rm_dorm` VALUES ('751cb63a306f4c468dd7260b7b39cafc', '10f94e7a3a024cf6aa0bb896303d1321', 'B-804', 'B-804', '1', '1', '1', '10', '', '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', null);
INSERT INTO `rm_dorm` VALUES ('8bb5c9ba134545f8b0c3ce835e43520e', '10f94e7a3a024cf6aa0bb896303d1321', 'B-808', 'B-808', '1', '1', '1', '10', '', '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', null);
INSERT INTO `rm_dorm` VALUES ('8c5650675a4244a683c974aef4bd4fcb', '10f94e7a3a024cf6aa0bb896303d1321', 'B-803', 'B-803', '1', '1', '1', '10', '', '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', null);
INSERT INTO `rm_dorm` VALUES ('8e8ee376855040ff93fa778170a99fd9', '10f94e7a3a024cf6aa0bb896303d1321', 'B-815', 'B-815', '1', '1', '1', '10', '', '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', null);
INSERT INTO `rm_dorm` VALUES ('8f9ac391c4254cc0a75e938d87fd840e', '10f94e7a3a024cf6aa0bb896303d1321', 'B-809', 'B-809', '1', '1', '1', '10', '', '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', null);
INSERT INTO `rm_dorm` VALUES ('a6e60691eb7244b19ed78c49e3cb0f76', '10f94e7a3a024cf6aa0bb896303d1321', 'B-801', 'B-801', '1', '1', '1', '10', '', '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', null);
INSERT INTO `rm_dorm` VALUES ('bf88b015b2244f5c9f0526b36e92c053', '10f94e7a3a024cf6aa0bb896303d1321', 'B-802', 'B-802', '1', '1', '1', '10', '', '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', null);
INSERT INTO `rm_dorm` VALUES ('cf6e6fe68a594638a99d765dedaf2338', '10f94e7a3a024cf6aa0bb896303d1321', 'B-811', 'B-811', '1', '1', '1', '10', '', '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', null);
INSERT INTO `rm_dorm` VALUES ('eb5a98ab812e4e2a87422afd4fbc0726', '10f94e7a3a024cf6aa0bb896303d1321', 'B-810', 'B-810', '1', '1', '1', '10', '', '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', null);

-- ----------------------------
-- Table structure for `rm_dorm_bed`
-- ----------------------------
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
INSERT INTO `rm_dorm_bed` VALUES ('012f756e25a847c7bcaa357652224f36', '8e8ee376855040ff93fa778170a99fd9', '9号床', '9', null, '2019-12-06 23:33:41', '1', '2019-12-06 23:33:41', '1', 'B-815-9号床');
INSERT INTO `rm_dorm_bed` VALUES ('02eb5f823ccd4f718ba6d92402fb40d6', '751cb63a306f4c468dd7260b7b39cafc', '9号床', '9', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-804-9号床');
INSERT INTO `rm_dorm_bed` VALUES ('038bec16c9564531bb535bdb2713956a', 'eb5a98ab812e4e2a87422afd4fbc0726', '9号床', '9', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-810-9号床');
INSERT INTO `rm_dorm_bed` VALUES ('046080a0bf90472b84aa8a698fba04a0', '8e8ee376855040ff93fa778170a99fd9', '1号床', '1', null, '2019-12-06 23:33:41', '1', '2019-12-06 23:33:41', '1', 'B-815-1号床');
INSERT INTO `rm_dorm_bed` VALUES ('0514a9aef45b4059b05dd86a7fdc1c07', 'eb5a98ab812e4e2a87422afd4fbc0726', '10号床', '10', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-810-10号床');
INSERT INTO `rm_dorm_bed` VALUES ('0516b8171c8b4ec38cf7257303af49d2', 'bf88b015b2244f5c9f0526b36e92c053', '7号床', '7', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-802-7号床');
INSERT INTO `rm_dorm_bed` VALUES ('07cc684c0c93494cae710a11d544bd2c', '6c7e91499aff4d8896616f9db1142b12', '7号床', '7', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-805-7号床');
INSERT INTO `rm_dorm_bed` VALUES ('0bd9bcb2539649bba823f71348298e7a', '751cb63a306f4c468dd7260b7b39cafc', '1号床', '1', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-804-1号床');
INSERT INTO `rm_dorm_bed` VALUES ('0de34ee9f63340bda3fdd3c275045cb4', '0061460a66e643c59d942f0f389089bd', '4号床', '4', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-812-4号床');
INSERT INTO `rm_dorm_bed` VALUES ('0e57e9b89a85441791e585fa6eda0140', 'bf88b015b2244f5c9f0526b36e92c053', '2号床', '2', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-802-2号床');
INSERT INTO `rm_dorm_bed` VALUES ('0e6600e4adc34923bc1b3405b8ac7266', '751cb63a306f4c468dd7260b7b39cafc', '10号床', '10', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-804-10号床');
INSERT INTO `rm_dorm_bed` VALUES ('0eba0357cd29461a99afc015c470b8c9', '6c7e91499aff4d8896616f9db1142b12', '6号床', '6', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-805-6号床');
INSERT INTO `rm_dorm_bed` VALUES ('0f5dc2032145409da3cad385dc5dc37b', '8bb5c9ba134545f8b0c3ce835e43520e', '3号床', '3', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-808-3号床');
INSERT INTO `rm_dorm_bed` VALUES ('1558a816345945fe82bde2ead3276dfa', '8c5650675a4244a683c974aef4bd4fcb', '5号床', '5', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-803-5号床');
INSERT INTO `rm_dorm_bed` VALUES ('181f5cf43dc34835bf010a5fe3a8bf40', 'cf6e6fe68a594638a99d765dedaf2338', '4号床', '4', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-811-4号床');
INSERT INTO `rm_dorm_bed` VALUES ('1850cea47e194b71b78f2252378be27e', '6d502e1abed84766879d046da839a695', '6号床', '6', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-814-6号床');
INSERT INTO `rm_dorm_bed` VALUES ('18e3c44eb57f4dcbaf1c1f251ba496f5', '6c7e91499aff4d8896616f9db1142b12', '3号床', '3', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-805-3号床');
INSERT INTO `rm_dorm_bed` VALUES ('1a483c23261b4af4b7885de3d33f9ccd', 'a6e60691eb7244b19ed78c49e3cb0f76', '1号床', '1', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-801-1号床');
INSERT INTO `rm_dorm_bed` VALUES ('1ab7c16982204c079fc8449e08afaff8', '8e8ee376855040ff93fa778170a99fd9', '4号床', '4', null, '2019-12-06 23:33:41', '1', '2019-12-06 23:33:41', '1', 'B-815-4号床');
INSERT INTO `rm_dorm_bed` VALUES ('1cdce444b2e24be09bddb6592619cbe4', '3e24aa60c54b421b98440928b03e2775', '7号床', '7', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-813-7号床');
INSERT INTO `rm_dorm_bed` VALUES ('1d5a24fa0ac04851a1ddc0f3080c1f14', 'eb5a98ab812e4e2a87422afd4fbc0726', '6号床', '6', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-810-6号床');
INSERT INTO `rm_dorm_bed` VALUES ('1db02ca16bdf4b1898fd89e1122cf1f7', '217db757167e49699c3bb6bb48d6b838', '4号床', '4', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-806-4号床');
INSERT INTO `rm_dorm_bed` VALUES ('1ebff63f7c3048ff8492efffa0ad6a86', '217db757167e49699c3bb6bb48d6b838', '5号床', '5', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-806-5号床');
INSERT INTO `rm_dorm_bed` VALUES ('222c362db2df42bc8042da52f2bed1e9', '6d502e1abed84766879d046da839a695', '2号床', '2', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-814-2号床');
INSERT INTO `rm_dorm_bed` VALUES ('253885e9680a443bbf28f9e276297635', 'eb5a98ab812e4e2a87422afd4fbc0726', '1号床', '1', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-810-1号床');
INSERT INTO `rm_dorm_bed` VALUES ('26d1b63b50584b8586a9713cad65e680', '0061460a66e643c59d942f0f389089bd', '1号床', '1', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-812-1号床');
INSERT INTO `rm_dorm_bed` VALUES ('28e145f4ae8c409ba10f488ad926c99c', 'cf6e6fe68a594638a99d765dedaf2338', '1号床', '1', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-811-1号床');
INSERT INTO `rm_dorm_bed` VALUES ('2b3053e722284ecab4e7106bf2875abf', '751cb63a306f4c468dd7260b7b39cafc', '3号床', '3', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-804-3号床');
INSERT INTO `rm_dorm_bed` VALUES ('2b95d587e4494fde9981b08a3605c069', '8f9ac391c4254cc0a75e938d87fd840e', '4号床', '4', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-809-4号床');
INSERT INTO `rm_dorm_bed` VALUES ('2edfc68046b745b481bb3ee49451edc5', '8f9ac391c4254cc0a75e938d87fd840e', '3号床', '3', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-809-3号床');
INSERT INTO `rm_dorm_bed` VALUES ('33801feb6c0443ba9022cd3582b09f3e', '217db757167e49699c3bb6bb48d6b838', '7号床', '7', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-806-7号床');
INSERT INTO `rm_dorm_bed` VALUES ('34847bbfd65b427d8862af59c18d5711', 'eb5a98ab812e4e2a87422afd4fbc0726', '2号床', '2', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-810-2号床');
INSERT INTO `rm_dorm_bed` VALUES ('356c391c9ec94eb78b0fce419926affa', 'eb5a98ab812e4e2a87422afd4fbc0726', '3号床', '3', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-810-3号床');
INSERT INTO `rm_dorm_bed` VALUES ('38761ea83a1c48b1ae1cba714f02e187', '0061460a66e643c59d942f0f389089bd', '5号床', '5', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-812-5号床');
INSERT INTO `rm_dorm_bed` VALUES ('39027c2556514dbb868dfd53958caa0f', 'bf88b015b2244f5c9f0526b36e92c053', '9号床', '9', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-802-9号床');
INSERT INTO `rm_dorm_bed` VALUES ('392ee17c47814e638246c831aa0212cd', 'cf6e6fe68a594638a99d765dedaf2338', '9号床', '9', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-811-9号床');
INSERT INTO `rm_dorm_bed` VALUES ('3d0654681ea744049e3d98256d045243', '6d502e1abed84766879d046da839a695', '4号床', '4', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-814-4号床');
INSERT INTO `rm_dorm_bed` VALUES ('3dbc75763ba74f208e4af81abaec0550', '217db757167e49699c3bb6bb48d6b838', '3号床', '3', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-806-3号床');
INSERT INTO `rm_dorm_bed` VALUES ('4262465b096140278502b140d97f50af', 'cf6e6fe68a594638a99d765dedaf2338', '5号床', '5', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-811-5号床');
INSERT INTO `rm_dorm_bed` VALUES ('43efb2dac93b4994933b4137c3886f79', '217db757167e49699c3bb6bb48d6b838', '2号床', '2', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-806-2号床');
INSERT INTO `rm_dorm_bed` VALUES ('46a7c8db974b4a3daca1ed2843230051', '8bb5c9ba134545f8b0c3ce835e43520e', '5号床', '5', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-808-5号床');
INSERT INTO `rm_dorm_bed` VALUES ('47bb82177d30442ea10ea137591c62fc', 'a6e60691eb7244b19ed78c49e3cb0f76', '2号床', '2', '1', '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-801-2号床');
INSERT INTO `rm_dorm_bed` VALUES ('486f1b1e26634d538f8f66beffa38d9f', '751cb63a306f4c468dd7260b7b39cafc', '8号床', '8', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-804-8号床');
INSERT INTO `rm_dorm_bed` VALUES ('4a35646f9e484a7c85bbf86ffa109011', '3e24aa60c54b421b98440928b03e2775', '2号床', '2', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-813-2号床');
INSERT INTO `rm_dorm_bed` VALUES ('4c2ae1a6ecaf42ddb651dfcb04c1c3c8', '8bb5c9ba134545f8b0c3ce835e43520e', '1号床', '1', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-808-1号床');
INSERT INTO `rm_dorm_bed` VALUES ('4cf31fe15eab4c68a49f034be4cb0074', 'bf88b015b2244f5c9f0526b36e92c053', '3号床', '3', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-802-3号床');
INSERT INTO `rm_dorm_bed` VALUES ('4d44e7bdced046e99bf36784e05802a0', '8bb5c9ba134545f8b0c3ce835e43520e', '9号床', '9', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-808-9号床');
INSERT INTO `rm_dorm_bed` VALUES ('4e40252bd1ca4b57bba1500c2499be96', '07b916dd1b774bd6a70d93bbf9629e49', '5号床', '5', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-807-5号床');
INSERT INTO `rm_dorm_bed` VALUES ('4e81291a077e429dba7147bd1861b5ad', 'cf6e6fe68a594638a99d765dedaf2338', '3号床', '3', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-811-3号床');
INSERT INTO `rm_dorm_bed` VALUES ('503134f3af704ff3923dd73d9722c3df', '3e24aa60c54b421b98440928b03e2775', '6号床', '6', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-813-6号床');
INSERT INTO `rm_dorm_bed` VALUES ('5189a78ce6334ef39ac2566bc8cfb180', '07b916dd1b774bd6a70d93bbf9629e49', '2号床', '2', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-807-2号床');
INSERT INTO `rm_dorm_bed` VALUES ('54889ac72fc3404e955b951e415030c3', '3e24aa60c54b421b98440928b03e2775', '4号床', '4', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-813-4号床');
INSERT INTO `rm_dorm_bed` VALUES ('5853f5c4e407477ba66b3a5a31185d43', 'bf88b015b2244f5c9f0526b36e92c053', '1号床', '1', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-802-1号床');
INSERT INTO `rm_dorm_bed` VALUES ('5877f2cb23ca4b0eb48aad7542badf04', '8f9ac391c4254cc0a75e938d87fd840e', '5号床', '5', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-809-5号床');
INSERT INTO `rm_dorm_bed` VALUES ('5a1b310ed9684d87b75daca98957cdef', '07b916dd1b774bd6a70d93bbf9629e49', '7号床', '7', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-807-7号床');
INSERT INTO `rm_dorm_bed` VALUES ('5b14221bfc1f4e98b25bc38ca2191af7', '3e24aa60c54b421b98440928b03e2775', '3号床', '3', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-813-3号床');
INSERT INTO `rm_dorm_bed` VALUES ('5c889940a8d646db9df938e9985ca1cf', '8c5650675a4244a683c974aef4bd4fcb', '4号床', '4', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-803-4号床');
INSERT INTO `rm_dorm_bed` VALUES ('603a3728bb26474eb38cc8ab7c6e8fd2', '8e8ee376855040ff93fa778170a99fd9', '7号床', '7', null, '2019-12-06 23:33:41', '1', '2019-12-06 23:33:41', '1', 'B-815-7号床');
INSERT INTO `rm_dorm_bed` VALUES ('613d2be8e70f4cf8a4d75ed11aa44697', 'a6e60691eb7244b19ed78c49e3cb0f76', '5号床', '5', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-801-5号床');
INSERT INTO `rm_dorm_bed` VALUES ('64d8596ae3334fdc8e3fdc96db0eafda', '6d502e1abed84766879d046da839a695', '5号床', '5', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-814-5号床');
INSERT INTO `rm_dorm_bed` VALUES ('656a59ece05a4f58973db6358871ac8b', '8bb5c9ba134545f8b0c3ce835e43520e', '6号床', '6', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-808-6号床');
INSERT INTO `rm_dorm_bed` VALUES ('671cb1c23c75488299eff1262664b213', '0061460a66e643c59d942f0f389089bd', '2号床', '2', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-812-2号床');
INSERT INTO `rm_dorm_bed` VALUES ('6a6196be207348ef93da7eefb50163a8', '6c7e91499aff4d8896616f9db1142b12', '8号床', '8', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-805-8号床');
INSERT INTO `rm_dorm_bed` VALUES ('73b5750479d943b497f20751ef8fe62c', 'eb5a98ab812e4e2a87422afd4fbc0726', '7号床', '7', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-810-7号床');
INSERT INTO `rm_dorm_bed` VALUES ('74d063b0ff4d439297cf205239356bb1', '07b916dd1b774bd6a70d93bbf9629e49', '1号床', '1', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-807-1号床');
INSERT INTO `rm_dorm_bed` VALUES ('7549f0d9105f423ebb1ca85858d8096b', '6c7e91499aff4d8896616f9db1142b12', '2号床', '2', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-805-2号床');
INSERT INTO `rm_dorm_bed` VALUES ('7614353f8152432d8378832979555642', '8bb5c9ba134545f8b0c3ce835e43520e', '4号床', '4', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-808-4号床');
INSERT INTO `rm_dorm_bed` VALUES ('79c75998129f4453831f4d5e6d244752', '8f9ac391c4254cc0a75e938d87fd840e', '7号床', '7', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-809-7号床');
INSERT INTO `rm_dorm_bed` VALUES ('7a298cda3afc4a6f81c258f74a92bc73', '8c5650675a4244a683c974aef4bd4fcb', '6号床', '6', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-803-6号床');
INSERT INTO `rm_dorm_bed` VALUES ('7cb2f1e5caef49eca8a411587c294c8d', '6d502e1abed84766879d046da839a695', '10号床', '10', null, '2019-12-06 23:33:41', '1', '2019-12-06 23:33:41', '1', 'B-814-10号床');
INSERT INTO `rm_dorm_bed` VALUES ('8028131e567c429b96f4aa20d40a68cd', 'a6e60691eb7244b19ed78c49e3cb0f76', '10号床', '10', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-801-10号床');
INSERT INTO `rm_dorm_bed` VALUES ('831379a59b404ecba47a0b4e60c94f82', '6c7e91499aff4d8896616f9db1142b12', '10号床', '10', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-805-10号床');
INSERT INTO `rm_dorm_bed` VALUES ('836e0e2687514bb6a27a5d80029b846e', '8c5650675a4244a683c974aef4bd4fcb', '7号床', '7', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-803-7号床');
INSERT INTO `rm_dorm_bed` VALUES ('846e47cd8b7141c7b924a8613a84d354', '6d502e1abed84766879d046da839a695', '7号床', '7', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-814-7号床');
INSERT INTO `rm_dorm_bed` VALUES ('8807e78a7b2f4276abaf6493c98b8856', 'a6e60691eb7244b19ed78c49e3cb0f76', '3号床', '3', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-801-3号床');
INSERT INTO `rm_dorm_bed` VALUES ('8b7c9a1e90e04d3d90bde7c29486d020', '751cb63a306f4c468dd7260b7b39cafc', '4号床', '4', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-804-4号床');
INSERT INTO `rm_dorm_bed` VALUES ('8bde43459d234d0890859d770574e613', '8bb5c9ba134545f8b0c3ce835e43520e', '7号床', '7', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-808-7号床');
INSERT INTO `rm_dorm_bed` VALUES ('8c05ecc2e26441f4accd5adb94bf309a', '8e8ee376855040ff93fa778170a99fd9', '10号床', '10', null, '2019-12-06 23:33:41', '1', '2019-12-06 23:33:41', '1', 'B-815-10号床');
INSERT INTO `rm_dorm_bed` VALUES ('8fa85d52f33b4a0aa3fbc1bf467e6f7a', '8f9ac391c4254cc0a75e938d87fd840e', '1号床', '1', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-809-1号床');
INSERT INTO `rm_dorm_bed` VALUES ('9433d6810ce74feb83f7c238215de4be', 'cf6e6fe68a594638a99d765dedaf2338', '8号床', '8', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-811-8号床');
INSERT INTO `rm_dorm_bed` VALUES ('94c4d6b5793c453591e88f6379db17b5', 'bf88b015b2244f5c9f0526b36e92c053', '10号床', '10', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-802-10号床');
INSERT INTO `rm_dorm_bed` VALUES ('9978b3f3fdcd4a25ad2ea415bc3a190d', '8e8ee376855040ff93fa778170a99fd9', '6号床', '6', null, '2019-12-06 23:33:41', '1', '2019-12-06 23:33:41', '1', 'B-815-6号床');
INSERT INTO `rm_dorm_bed` VALUES ('99f0c78c7d6045d7a3e5be4ac713fcc6', '6d502e1abed84766879d046da839a695', '3号床', '3', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-814-3号床');
INSERT INTO `rm_dorm_bed` VALUES ('9ebffa4ef3b14abc94063075e679ad14', '07b916dd1b774bd6a70d93bbf9629e49', '6号床', '6', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-807-6号床');
INSERT INTO `rm_dorm_bed` VALUES ('a2888eebeef94286bf2602ff00f0d906', '6c7e91499aff4d8896616f9db1142b12', '9号床', '9', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-805-9号床');
INSERT INTO `rm_dorm_bed` VALUES ('a3e849a7fce34dff9700a2e4dea8668a', 'cf6e6fe68a594638a99d765dedaf2338', '7号床', '7', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-811-7号床');
INSERT INTO `rm_dorm_bed` VALUES ('a59d606b435645d2a75c6e9c7c87b2d6', '6d502e1abed84766879d046da839a695', '9号床', '9', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-814-9号床');
INSERT INTO `rm_dorm_bed` VALUES ('a5b5ffccb80f4b78b839924e0bf392f3', '217db757167e49699c3bb6bb48d6b838', '8号床', '8', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-806-8号床');
INSERT INTO `rm_dorm_bed` VALUES ('a723dec97034445d8457c109955940bc', '07b916dd1b774bd6a70d93bbf9629e49', '3号床', '3', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-807-3号床');
INSERT INTO `rm_dorm_bed` VALUES ('a7fd6c85af404a8ba68c901531a18825', '8f9ac391c4254cc0a75e938d87fd840e', '2号床', '2', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-809-2号床');
INSERT INTO `rm_dorm_bed` VALUES ('a96e101690e94872b3a9317304e075ab', '6c7e91499aff4d8896616f9db1142b12', '5号床', '5', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-805-5号床');
INSERT INTO `rm_dorm_bed` VALUES ('ae8bb6961c374f5d842ea18a598167bf', 'cf6e6fe68a594638a99d765dedaf2338', '10号床', '10', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-811-10号床');
INSERT INTO `rm_dorm_bed` VALUES ('ae986fbcfad4446bb7b0316c0a4a3ec0', '6d502e1abed84766879d046da839a695', '1号床', '1', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-814-1号床');
INSERT INTO `rm_dorm_bed` VALUES ('ae9b39a71e234a2481dec4ae5faf8471', '751cb63a306f4c468dd7260b7b39cafc', '5号床', '5', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-804-5号床');
INSERT INTO `rm_dorm_bed` VALUES ('b21cdaa080034502a7b18a4b9c901b81', '217db757167e49699c3bb6bb48d6b838', '10号床', '10', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-806-10号床');
INSERT INTO `rm_dorm_bed` VALUES ('b27d036d19004d0fb3428d280dfd2027', '8bb5c9ba134545f8b0c3ce835e43520e', '2号床', '2', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-808-2号床');
INSERT INTO `rm_dorm_bed` VALUES ('b38469d14d364c4880da12e37d51c227', '8bb5c9ba134545f8b0c3ce835e43520e', '10号床', '10', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-808-10号床');
INSERT INTO `rm_dorm_bed` VALUES ('b3e5878f34b347cf82e4bce6674967ed', 'a6e60691eb7244b19ed78c49e3cb0f76', '8号床', '8', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-801-8号床');
INSERT INTO `rm_dorm_bed` VALUES ('b4102fac9f5340d2a725f29c8a7cbd4f', '8f9ac391c4254cc0a75e938d87fd840e', '8号床', '8', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-809-8号床');
INSERT INTO `rm_dorm_bed` VALUES ('b419e36a70984682b225053e19963726', '3e24aa60c54b421b98440928b03e2775', '9号床', '9', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-813-9号床');
INSERT INTO `rm_dorm_bed` VALUES ('b53ea963f1df43188aee1a13c10f8072', '8c5650675a4244a683c974aef4bd4fcb', '8号床', '8', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-803-8号床');
INSERT INTO `rm_dorm_bed` VALUES ('b7f0141e3984422cbc1d19a94bb0608e', '8c5650675a4244a683c974aef4bd4fcb', '1号床', '1', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-803-1号床');
INSERT INTO `rm_dorm_bed` VALUES ('bd9da58410a345079571f5dda6edd618', '3e24aa60c54b421b98440928b03e2775', '8号床', '8', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-813-8号床');
INSERT INTO `rm_dorm_bed` VALUES ('bfa3a211ac5448abaee28b508e0aefe0', '217db757167e49699c3bb6bb48d6b838', '6号床', '6', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-806-6号床');
INSERT INTO `rm_dorm_bed` VALUES ('bff33b8cfac744cf8a7e959af5fd040b', '07b916dd1b774bd6a70d93bbf9629e49', '9号床', '9', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-807-9号床');
INSERT INTO `rm_dorm_bed` VALUES ('c27407fc27b0459bba13846ddcbc70e0', '0061460a66e643c59d942f0f389089bd', '3号床', '3', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-812-3号床');
INSERT INTO `rm_dorm_bed` VALUES ('c54f02ee6e5f45b3b410f8ff8dadf262', 'a6e60691eb7244b19ed78c49e3cb0f76', '7号床', '7', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-801-7号床');
INSERT INTO `rm_dorm_bed` VALUES ('c581b07cd27d424799a7f0fdc3e490dd', '8c5650675a4244a683c974aef4bd4fcb', '9号床', '9', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-803-9号床');
INSERT INTO `rm_dorm_bed` VALUES ('c5e3ed64a16648bea5e28cfd91193fa9', '8e8ee376855040ff93fa778170a99fd9', '3号床', '3', null, '2019-12-06 23:33:41', '1', '2019-12-06 23:33:41', '1', 'B-815-3号床');
INSERT INTO `rm_dorm_bed` VALUES ('cbad41e573e44466aa8dc6fcf48305e3', '8f9ac391c4254cc0a75e938d87fd840e', '10号床', '10', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-809-10号床');
INSERT INTO `rm_dorm_bed` VALUES ('cbb26534e54449d5bdc04b20dec32f16', '07b916dd1b774bd6a70d93bbf9629e49', '10号床', '10', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-807-10号床');
INSERT INTO `rm_dorm_bed` VALUES ('cfece5e0c55d48d49a5c3110fddee95d', 'a6e60691eb7244b19ed78c49e3cb0f76', '9号床', '9', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-801-9号床');
INSERT INTO `rm_dorm_bed` VALUES ('d146e37da22f4220b08a851221830349', '8c5650675a4244a683c974aef4bd4fcb', '10号床', '10', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-803-10号床');
INSERT INTO `rm_dorm_bed` VALUES ('d2b9650a32aa4d679f09ce8758e9c347', 'bf88b015b2244f5c9f0526b36e92c053', '5号床', '5', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-802-5号床');
INSERT INTO `rm_dorm_bed` VALUES ('d3892bf12a2a48369cc1a3e81d8b0e5d', '0061460a66e643c59d942f0f389089bd', '7号床', '7', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-812-7号床');
INSERT INTO `rm_dorm_bed` VALUES ('d4de5e7f2f31433db71e07189f4d24f0', '0061460a66e643c59d942f0f389089bd', '8号床', '8', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-812-8号床');
INSERT INTO `rm_dorm_bed` VALUES ('d5154eee0eb447f4bc09c0f4183a3e5e', 'eb5a98ab812e4e2a87422afd4fbc0726', '8号床', '8', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-810-8号床');
INSERT INTO `rm_dorm_bed` VALUES ('d6dda3c6be8248fdbb125613dc4ceddb', 'bf88b015b2244f5c9f0526b36e92c053', '6号床', '6', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-802-6号床');
INSERT INTO `rm_dorm_bed` VALUES ('d6ec5ab602174aa9ac9bee8ba0f8eacb', '8c5650675a4244a683c974aef4bd4fcb', '2号床', '2', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-803-2号床');
INSERT INTO `rm_dorm_bed` VALUES ('d9a2ce3ee979436c8259b55a73041037', 'a6e60691eb7244b19ed78c49e3cb0f76', '6号床', '6', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-801-6号床');
INSERT INTO `rm_dorm_bed` VALUES ('dc8a3d9a2a2a41e380a92f0901504bcc', '751cb63a306f4c468dd7260b7b39cafc', '6号床', '6', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-804-6号床');
INSERT INTO `rm_dorm_bed` VALUES ('ddc38dc5ef25470c8a94ea4ab991732e', '8e8ee376855040ff93fa778170a99fd9', '8号床', '8', null, '2019-12-06 23:33:41', '1', '2019-12-06 23:33:41', '1', 'B-815-8号床');
INSERT INTO `rm_dorm_bed` VALUES ('e058196823a346cc8178fc637a875bd6', '0061460a66e643c59d942f0f389089bd', '10号床', '10', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-812-10号床');
INSERT INTO `rm_dorm_bed` VALUES ('e163c765e96343e9bf093e54bf6f6a70', '8c5650675a4244a683c974aef4bd4fcb', '3号床', '3', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-803-3号床');
INSERT INTO `rm_dorm_bed` VALUES ('e17232e0788740dcb0268e3234737913', '07b916dd1b774bd6a70d93bbf9629e49', '8号床', '8', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-807-8号床');
INSERT INTO `rm_dorm_bed` VALUES ('e7602c3b95c149089f1bd70b7a70c53f', '0061460a66e643c59d942f0f389089bd', '9号床', '9', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-812-9号床');
INSERT INTO `rm_dorm_bed` VALUES ('e7738b8dbe314ff7824e98a609cd1172', '6d502e1abed84766879d046da839a695', '8号床', '8', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-814-8号床');
INSERT INTO `rm_dorm_bed` VALUES ('e7a497e875b84a69ab11ff60810fb80c', 'cf6e6fe68a594638a99d765dedaf2338', '2号床', '2', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-811-2号床');
INSERT INTO `rm_dorm_bed` VALUES ('e7eb1be0913f4560831d77153008f955', '07b916dd1b774bd6a70d93bbf9629e49', '4号床', '4', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-807-4号床');
INSERT INTO `rm_dorm_bed` VALUES ('e8d573d3aa854e18a9207690ccb74118', 'cf6e6fe68a594638a99d765dedaf2338', '6号床', '6', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-811-6号床');
INSERT INTO `rm_dorm_bed` VALUES ('ea1138dd835940dea768a4339c38cdbb', 'eb5a98ab812e4e2a87422afd4fbc0726', '5号床', '5', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-810-5号床');
INSERT INTO `rm_dorm_bed` VALUES ('ea3bc1e9ba884211ac1fe9475e9b2bb0', 'eb5a98ab812e4e2a87422afd4fbc0726', '4号床', '4', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-810-4号床');
INSERT INTO `rm_dorm_bed` VALUES ('ee325fbdcb0f493d8b129e053bde8ff4', '8e8ee376855040ff93fa778170a99fd9', '5号床', '5', null, '2019-12-06 23:33:41', '1', '2019-12-06 23:33:41', '1', 'B-815-5号床');
INSERT INTO `rm_dorm_bed` VALUES ('f01b56439de248bd9baa95fd988110c2', '3e24aa60c54b421b98440928b03e2775', '1号床', '1', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-813-1号床');
INSERT INTO `rm_dorm_bed` VALUES ('f03b3c042f1842fa9e57a0e34dad9d7a', '8bb5c9ba134545f8b0c3ce835e43520e', '8号床', '8', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-808-8号床');
INSERT INTO `rm_dorm_bed` VALUES ('f112689388ce4065a156d72b41e4b027', '3e24aa60c54b421b98440928b03e2775', '5号床', '5', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-813-5号床');
INSERT INTO `rm_dorm_bed` VALUES ('f2c7dbde72a34ca3b28917ba1a297b69', '8f9ac391c4254cc0a75e938d87fd840e', '6号床', '6', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-809-6号床');
INSERT INTO `rm_dorm_bed` VALUES ('f32e423f75ec4afe9b95b48df9ae3631', '6c7e91499aff4d8896616f9db1142b12', '4号床', '4', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-805-4号床');
INSERT INTO `rm_dorm_bed` VALUES ('f3323b3a2b4c48aa899ad0184a71c0ae', '8f9ac391c4254cc0a75e938d87fd840e', '9号床', '9', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-809-9号床');
INSERT INTO `rm_dorm_bed` VALUES ('f4882524697e4326acc8a510d6667577', '8e8ee376855040ff93fa778170a99fd9', '2号床', '2', null, '2019-12-06 23:33:41', '1', '2019-12-06 23:33:41', '1', 'B-815-2号床');
INSERT INTO `rm_dorm_bed` VALUES ('f4e930583fce4c5abc479244aa0d3785', '0061460a66e643c59d942f0f389089bd', '6号床', '6', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-812-6号床');
INSERT INTO `rm_dorm_bed` VALUES ('f985c6b3942449cb8bf54b019797849a', '217db757167e49699c3bb6bb48d6b838', '9号床', '9', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-806-9号床');
INSERT INTO `rm_dorm_bed` VALUES ('f9e7902970b64a47b1d2d03d83b98551', 'bf88b015b2244f5c9f0526b36e92c053', '4号床', '4', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-802-4号床');
INSERT INTO `rm_dorm_bed` VALUES ('fa4dbec97e6b45d69feca63b29ef5c84', '217db757167e49699c3bb6bb48d6b838', '1号床', '1', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-806-1号床');
INSERT INTO `rm_dorm_bed` VALUES ('fb0dc95c31c0401f9932d591bab685a6', '751cb63a306f4c468dd7260b7b39cafc', '7号床', '7', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-804-7号床');
INSERT INTO `rm_dorm_bed` VALUES ('fb17ee8eb043478ebf681a198274c319', '751cb63a306f4c468dd7260b7b39cafc', '2号床', '2', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-804-2号床');
INSERT INTO `rm_dorm_bed` VALUES ('fdf0b57079914cffb53d57ea697b6e57', 'a6e60691eb7244b19ed78c49e3cb0f76', '4号床', '4', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-801-4号床');
INSERT INTO `rm_dorm_bed` VALUES ('fecdb9b4f85146d7b08adfefe67f3467', 'bf88b015b2244f5c9f0526b36e92c053', '8号床', '8', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-802-8号床');
INSERT INTO `rm_dorm_bed` VALUES ('fed642b1797247319de40cccd90d463b', '3e24aa60c54b421b98440928b03e2775', '10号床', '10', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-813-10号床');
INSERT INTO `rm_dorm_bed` VALUES ('ff51d1ebcd224496bdd16ac63b15e865', '6c7e91499aff4d8896616f9db1142b12', '1号床', '1', null, '2019-12-06 23:33:40', '1', '2019-12-06 23:33:40', '1', 'B-805-1号床');

-- ----------------------------
-- Table structure for `rm_dorm_repair`
-- ----------------------------
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

-- ----------------------------
-- Table structure for `rm_dorm_score`
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

-- ----------------------------
-- Records of rm_dorm_score
-- ----------------------------

-- ----------------------------
-- Table structure for `rm_dorm_violation`
-- ----------------------------
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

-- ----------------------------
-- Records of rm_dorm_violation
-- ----------------------------

-- ----------------------------
-- Table structure for `rm_school_campus`
-- ----------------------------
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

-- ----------------------------
-- Records of rm_school_campus
-- ----------------------------
INSERT INTO `rm_school_campus` VALUES ('1', '12312313', '西南交通大学成都校区', '成都金堂县', '12313', '123123', '23131', '李林洋', '', '2019-12-01 17:39:54', '1', '2019-12-01 17:39:54', '1');

-- ----------------------------
-- Table structure for `rm_student_basic`
-- ----------------------------
CREATE TABLE `rm_student_basic` (
  `id` varchar(32) NOT NULL COMMENT '学生基本数据表id',
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
