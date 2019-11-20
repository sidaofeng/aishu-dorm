CREATE TABLE `rm_dict` (
  `pk_dict_id` varchar(32) NOT NULL COMMENT '主键',
  `dict_key` varchar(20) DEFAULT NULL COMMENT '键',
  `dict_value` varchar(50) DEFAULT NULL COMMENT '值',
  `column_name` varchar(20) DEFAULT NULL COMMENT '列名',
  `column_desc` varchar(50) DEFAULT NULL COMMENT '列描述',
  `table_name` varchar(20) DEFAULT NULL COMMENT '表名称',
  `table_desc` varchar(50) NOT NULL COMMENT '表描述',
  `status` int(2) DEFAULT NULL COMMENT '状态',
  `school_id` varchar(32) DEFAULT NULL COMMENT '学校id',
  `create_time` datetime DEFAULT NULL,
  `create_user_id` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL,
  `last_modify_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  `last_modify_user_id` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL,
  PRIMARY KEY (`pk_dict_id`,`table_desc`),
  UNIQUE KEY `suoyim` (`dict_key`,`dict_value`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `rm_dict` VALUES ('81478ecdc4714c6c8803534d7fc3f4f9', '1', '生效', 'status', '状态', 'rm_dorm', '宿舍信息表', '1', '4ddecf810f15405e8b4665a85f273172', '2019-04-19 10:05:17', 'af7cae9f7b87426ab1572d431c56253d', '2019-04-19 10:06:24', 'af7cae9f7b87426ab1572d431c56253d');

CREATE TABLE `rm_dorm` (
  `pk_dorm_id` varchar(32) NOT NULL COMMENT '主键',
  `dorm_building_id` varchar(32) NOT NULL COMMENT '宿舍楼id',
  `building_levelth` int(2) DEFAULT NULL COMMENT '楼栋层数(第几层)',
  `dorm_type` int(1) DEFAULT NULL COMMENT '宿舍类型（1：男生寝室 2：女生寝室 3：教师宿舍 4：宿管宿舍 5：存放物品宿舍）',
  `dorm_num` varchar(20) DEFAULT NULL COMMENT '宿舍编号',
  `dorm_desc` varchar(500) DEFAULT NULL COMMENT '宿舍描述',
  `status` int(1) DEFAULT NULL COMMENT '状态 (1-生效，0-无效)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL,
  `last_modify_time` datetime DEFAULT NULL COMMENT '最终修改时间',
  `last_modify_user_id` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL,
  `memo` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`pk_dorm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `rm_dorm` VALUES ('bb37bdb0a95640bc988224d13a934285', '02add4779e62441c9ba2e5299acd5df9', '8', '1', 'B-805', '校外B栋第8层', '1', '2019-04-10 02:58:06', 'af7cae9f7b87426ab1572d431c56253d', '2019-04-10 02:58:06', 'af7cae9f7b87426ab1572d431c56253d', '校外B栋第8层');

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

INSERT INTO `rm_dorm_building` VALUES ('02add4779e62441c9ba2e5299acd5df9', '1', 'B栋', '20', '校外B栋', '-1', '2019-04-10 02:56:22', 'af7cae9f7b87426ab1572d431c56253d', '2019-04-15 05:19:59', 'af7cae9f7b87426ab1572d431c56253d', 'string');

CREATE TABLE `rm_dorm_item` (
  `pk_dorm_item_id` varchar(32) COLLATE utf8_bin NOT NULL,
  `dorm_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '宿舍id',
  PRIMARY KEY (`pk_dorm_item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='宿舍详情';

CREATE TABLE `rm_dorm_repair` (
  `pk_dorm_repair_id` varchar(32) NOT NULL,
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
  PRIMARY KEY (`pk_dorm_repair_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `rm_dorm_rule` (
  `pk_dorm_rule_id` varchar(32) NOT NULL,
  `rule_name` varchar(50) DEFAULT NULL,
  `rule_desc` varchar(500) DEFAULT NULL,
  `status` int(2) DEFAULT NULL COMMENT '状态（1：生效 0：失效）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '创建人',
  `last_modify_time` datetime DEFAULT NULL,
  `last_modify_user_id` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL,
  `memo` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`pk_dorm_rule_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `rm_dorm_score` (
  `pk_dorm_score_id` varchar(32) NOT NULL COMMENT '主键',
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
  PRIMARY KEY (`pk_dorm_score_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `rm_dorm_score` VALUES ('0a89bdc2292243de812252fbb83e2158', 'B-805', '4', '8', '18', '18', '8', '8', '8', '8', '5', '85', '1', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '优');
INSERT INTO `rm_dorm_score` VALUES ('0cc82c2bf2c7402cbe9d2f24576a319a', 'B-805', '4', '10', '15', '15', '5', '7', '6', '7', '5', '74', '1', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '垃圾未倒');
INSERT INTO `rm_dorm_score` VALUES ('0e85c00a143e4488b4b0fab1060768bc', 'B-805', '4', '7', '16', '15', '7', '7', '8', '7', '4', '75', '1', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '良');
INSERT INTO `rm_dorm_score` VALUES ('0eb1035809b5452099d05a60bba9ec7b', 'B-805', '4', '10', '15', '15', '4', '8', '6', '7', '5', '74', '1', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '垃圾未倒');
INSERT INTO `rm_dorm_score` VALUES ('1981664e20954b0cbaba1226bc835517', 'B-805', '4', '10', '15', '15', '7', '7', '4', '7', '5', '74', '1', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '地面脏');
INSERT INTO `rm_dorm_score` VALUES ('23f2640628e94f9f84a6eae1e843b52f', 'B-805', '4', '10', '15', '15', '6', '8', '7', '8', '5', '78', '1', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '一般');
INSERT INTO `rm_dorm_score` VALUES ('26b336baace74d6cbe9e40fb6f809af4', 'B-805', '4', '8', '16', '17', '8', '8', '8', '7', '4', '80', '1', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '良');
INSERT INTO `rm_dorm_score` VALUES ('2e7315ac3b9949fdb7a52a6dd55643cf', 'B-805', '4', '7', '15', '15', '6', '6', '6', '7', '4', '70', '1', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '卫生差');
INSERT INTO `rm_dorm_score` VALUES ('31591e55574f4f0f9ce7512dae7a1d53', 'B-805', '4', '10', '15', '15', '4', '7', '6', '7', '5', '73', '1', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '脏乱差');
INSERT INTO `rm_dorm_score` VALUES ('340e4dcc526b4b4f968e269cb6a7b1a7', 'B-805', '4', '7', '18', '18', '6', '6', '7', '7', '4', '77', '1', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '卫生一般');
INSERT INTO `rm_dorm_score` VALUES ('37a6e61fd3504c539a35c293e5160eb4', 'B-805', '4', '10', '15', '16', '7', '9', '8', '8', '5', '82', '1', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '好');
INSERT INTO `rm_dorm_score` VALUES ('437f23751d4843a989bc677e151debc6', 'B-805', '4', '8', '16', '16', '8', '8', '7', '8', '4', '79', '1', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '良');
INSERT INTO `rm_dorm_score` VALUES ('49ea5d1246394c6b8b2c195458849191', 'B-805', '4', '8', '17', '17', '8', '8', '8', '7', '4', '81', '1', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '良好');
INSERT INTO `rm_dorm_score` VALUES ('4aa7f65a32cf42db884a98928bec48ba', 'B-805', '5', '10', '15', '17', '7', '7', '7', '10', '5', '83', '1', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '优秀');
INSERT INTO `rm_dorm_score` VALUES ('4e721291b4434b6cb863fa0d48c952ca', 'B-805', '4', '10', '15', '16', '8', '9', '7', '8', '5', '82', '1', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '干净');
INSERT INTO `rm_dorm_score` VALUES ('7d54cb2aca7b493d930fbfef3afa19c9', 'B-805', '4', '7', '15', '16', '8', '7', '7', '7', '4', '76', '1', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '良');
INSERT INTO `rm_dorm_score` VALUES ('7f335b7602a949f88e37468ff4f92e1c', 'B-805', '4', '10', '16', '16', '8', '9', '7', '8', '5', '83', '1', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '地面干净');
INSERT INTO `rm_dorm_score` VALUES ('8196ad97da624c4dabe71b99a29905fe', 'B-805', '4', '9', '18', '18', '8', '8', '8', '8', '4', '85', '1', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '优');
INSERT INTO `rm_dorm_score` VALUES ('976f9a5cbf734177930129e007a1bae6', 'B-805', '4', '10', '17', '17', '8', '8', '8', '9', '5', '86', '1', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '优秀');
INSERT INTO `rm_dorm_score` VALUES ('99737f5a48604aa2bbc7ff3f7c8cebba', 'B-805', '4', '10', '15', '16', '7', '8', '8', '8', '5', '81', '1', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '一般');
INSERT INTO `rm_dorm_score` VALUES ('9aa81877c216449b993c9f3b4012b5f0', 'B-805', '5', '10', '16', '17', '6', '7', '5', '10', '5', '81', '1', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '优秀');
INSERT INTO `rm_dorm_score` VALUES ('9d705f24d85041df8600262e2aca417f', 'B-805', '4', '8', '16', '16', '8', '8', '7', '7', '4', '78', '1', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '良');
INSERT INTO `rm_dorm_score` VALUES ('a7269e5989e1448c8a07138e5ba40929', 'B-805', '5', '10', '18', '17', '6', '5', '5', '10', '5', '81', '1', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '优秀');
INSERT INTO `rm_dorm_score` VALUES ('ad2c1151e2cf4bafaacf2d2163888c25', 'B-805', '4', '10', '15', '15', '7', '7', '7', '8', '5', '78', '1', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '整洁');
INSERT INTO `rm_dorm_score` VALUES ('ae2a4b5c11a44862be46447dfb529b37', 'B-805', '4', '7', '16', '16', '7', '7', '6', '7', '4', '78', '1', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '良');
INSERT INTO `rm_dorm_score` VALUES ('b4d495275e834ef59db210add52c13b3', 'B-805', '4', '10', '15', '15', '8', '8', '7', '8', '5', '80', '1', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '一般');
INSERT INTO `rm_dorm_score` VALUES ('b98cb4eace2748f9bd341102973d8d15', 'B-805', '4', '10', '12', '14', '8', '7', '6', '7', '5', '73', '1', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '地面脏');
INSERT INTO `rm_dorm_score` VALUES ('be8722dc860b4acc9c649eda875ae210', 'B-805', '4', '10', '16', '16', '8', '9', '7', '8', '5', '83', '1', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '好');
INSERT INTO `rm_dorm_score` VALUES ('c8e3a17e457643b185d2c45e4bee4a29', 'B-805', '4', '7', '16', '15', '7', '8', '7', '7', '4', '75', '1', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '良');
INSERT INTO `rm_dorm_score` VALUES ('c9e39dfbb7b5472b9899a153cb664f4b', 'B-805', '4', '10', '18', '16', '10', '10', '8', '7', '5', '88', '1', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '优秀');
INSERT INTO `rm_dorm_score` VALUES ('caf0ebc063ba4a4f8cea33205014cd96', 'B-805', '4', '7', '16', '15', '7', '7', '7', '8', '4', '75', '1', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '良');
INSERT INTO `rm_dorm_score` VALUES ('cbc3fef94f7d410092c4fe9e774f6584', 'B-805', '4', '8', '16', '17', '8', '8', '8', '7', '4', '80', '1', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '良');
INSERT INTO `rm_dorm_score` VALUES ('d8cc87012b674a6cbffac220630290ae', 'B-805', '4', '8', '16', '16', '8', '8', '8', '7', '4', '79', '1', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '良');
INSERT INTO `rm_dorm_score` VALUES ('e9d8eef5c5d34264a6618e5407ba39ba', 'B-805', '5', '10', '16', '16', '6', '5', '8', '10', '5', '81', '1', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '优秀');
INSERT INTO `rm_dorm_score` VALUES ('ebcb1f384ecd476b883df59fecfdf0a3', 'B-805', '5', '10', '17', '15', '7', '7', '6', '10', '5', '82', '1', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '优秀');
INSERT INTO `rm_dorm_score` VALUES ('edab6ecd7ab04456a8c0a5486bfa7680', 'B-805', '4', '10', '15', '15', '8', '9', '7', '8', '5', '81', '1', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '较干净');
INSERT INTO `rm_dorm_score` VALUES ('f0b1dcef489d406ba5639b24926b30f5', 'B-805', '4', '10', '17', '17', '10', '10', '8', '7', '5', '88', '1', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '优秀');
INSERT INTO `rm_dorm_score` VALUES ('f34e015d80a7477aa327610e6e971499', 'B-805', '4', '10', '17', '16', '8', '8', '7', '8', '5', '83', '1', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '整洁');
INSERT INTO `rm_dorm_score` VALUES ('f5eb0069510843d89a39cf81deac2acb', 'B-805', '4', '7', '15', '15', '7', '9', '8', '7', '4', '76', '1', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '良');
INSERT INTO `rm_dorm_score` VALUES ('fcaa18216a14419ca5a0f2bca2f83cfd', 'B-805', '4', '10', '17', '16', '8', '8', '7', '8', '5', '83', '1', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '好');
INSERT INTO `rm_dorm_score` VALUES ('fffa8906c88f4fab85f4e9dea1bb3c4b', 'B-805', '4', '10', '15', '15', '5', '7', '6', '7', '5', '74', '1', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '2019-08-18 10:47:11', '6861df82ae5c40d39b37b51964bf40b0', '较差');

CREATE TABLE `rm_dorm_student_rel` (
  `pk_dorm_student_id` varchar(32) CHARACTER SET utf8mb4 NOT NULL COMMENT '主键',
  `dorm_id` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '宿舍id',
  `student_id` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '学生id',
  `status` int(1) DEFAULT NULL COMMENT '状态(1生效0失效)',
  `create_time` datetime DEFAULT NULL,
  `create_user_id` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL,
  `last_modify_time` datetime DEFAULT NULL,
  `last_modify_user_id` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL,
  `memo` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`pk_dorm_student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `rm_dorm_student_rel` VALUES ('199fc42e173740538c10ff5cffb3e3da', 'bb37bdb0a95640bc988224d13a934285', '31701f91052949a38c00f0ea96a1d148', '1', '2019-04-18 05:59:05', 'af7cae9f7b87426ab1572d431c56253d', '2019-04-18 05:59:05', 'af7cae9f7b87426ab1572d431c56253d', null);
INSERT INTO `rm_dorm_student_rel` VALUES ('1bd31c486ec44ffa90b6586f7c87de07', 'bb37bdb0a95640bc988224d13a934285', 'c939d5623e7146728c165b910ea78062', '1', '2019-04-18 05:59:05', 'af7cae9f7b87426ab1572d431c56253d', '2019-04-18 05:59:05', 'af7cae9f7b87426ab1572d431c56253d', null);
INSERT INTO `rm_dorm_student_rel` VALUES ('4488cfa1834d4da0a447c77f0279ddca', 'bb37bdb0a95640bc988224d13a934285', '7a72e1ebe5be45fbadbe0e9e61b49aac', '1', '2019-04-18 05:59:05', 'af7cae9f7b87426ab1572d431c56253d', '2019-04-18 05:59:05', 'af7cae9f7b87426ab1572d431c56253d', null);
INSERT INTO `rm_dorm_student_rel` VALUES ('640827e4c4ff402090484b2a9d5774b6', 'bb37bdb0a95640bc988224d13a934285', 'cece48defcc841d5a3bc740ede306cae', '1', '2019-04-18 05:59:05', 'af7cae9f7b87426ab1572d431c56253d', '2019-04-18 05:59:05', 'af7cae9f7b87426ab1572d431c56253d', null);
INSERT INTO `rm_dorm_student_rel` VALUES ('6a505ee7f60440049ec2fe468b10a452', 'bb37bdb0a95640bc988224d13a934285', '949a8e66e545448f81e64520975cd9e4', '1', '2019-04-18 05:59:05', 'af7cae9f7b87426ab1572d431c56253d', '2019-04-18 05:59:05', 'af7cae9f7b87426ab1572d431c56253d', null);
INSERT INTO `rm_dorm_student_rel` VALUES ('6d44ee0389194a58a1f21afe1518b542', 'bb37bdb0a95640bc988224d13a934285', '1525768912444d11a2ce180909d97616', '1', '2019-04-18 05:59:05', 'af7cae9f7b87426ab1572d431c56253d', '2019-04-18 05:59:05', 'af7cae9f7b87426ab1572d431c56253d', null);
INSERT INTO `rm_dorm_student_rel` VALUES ('8c678118bb3445d098bf2d6274dedf95', 'bb37bdb0a95640bc988224d13a934285', 'd4cf7e719dfc427084adf539e44288c3', '1', '2019-04-18 05:59:05', 'af7cae9f7b87426ab1572d431c56253d', '2019-04-18 05:59:05', 'af7cae9f7b87426ab1572d431c56253d', null);
INSERT INTO `rm_dorm_student_rel` VALUES ('ac8d5642604d4ac1b553ef6c4a910f2b', 'bb37bdb0a95640bc988224d13a934285', '8d473405f42244e3a1d47a647695108c', '1', '2019-04-18 05:59:05', 'af7cae9f7b87426ab1572d431c56253d', '2019-04-18 05:59:05', 'af7cae9f7b87426ab1572d431c56253d', null);

CREATE TABLE `rm_dorm_violation` (
  `pk_dorm_violation_id` varchar(32) NOT NULL COMMENT '主键',
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
  PRIMARY KEY (`pk_dorm_violation_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `rm_log` (
  `pk_log_id` varchar(32) NOT NULL COMMENT '日志ID',
  `user_id` varchar(32) DEFAULT NULL COMMENT '操作用户id',
  `operation_content` varchar(100) DEFAULT NULL COMMENT '操作内容',
  `duration` int(11) DEFAULT NULL COMMENT '耗时',
  `method` varchar(255) DEFAULT NULL COMMENT '操作方法',
  `params` varchar(5000) DEFAULT NULL COMMENT '方法参数',
  `ip` varchar(64) DEFAULT NULL COMMENT '操作者IP',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `location` varchar(100) DEFAULT NULL COMMENT '操作地点',
  PRIMARY KEY (`pk_log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `rm_resource` (
  `pk_resource_id` varchar(32) NOT NULL COMMENT '资源ID',
  `parent_id` varchar(32) NOT NULL COMMENT '父级ID',
  `resource_name` varchar(50) NOT NULL COMMENT '资源名称',
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
  PRIMARY KEY (`pk_resource_id`),
  UNIQUE KEY `resource_name_index` (`resource_name`) USING BTREE,
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
  `pk_role_id` varchar(32) NOT NULL,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `role_desc` varchar(255) DEFAULT NULL COMMENT '角色描述',
  `status` int(1) DEFAULT NULL COMMENT '用户状态(-1已删除，0失效，1-生效)',
  `create_time` datetime DEFAULT NULL,
  `create_user_id` varchar(32) DEFAULT NULL,
  `last_modify_time` datetime DEFAULT NULL,
  `last_modify_user_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`pk_role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `rm_role` VALUES ('12b4c9a27eab48cf8362203c110680a8', 'string', 'string', '1', '2019-11-16 22:20:33', '1', '2019-11-16 22:20:33', '1');
INSERT INTO `rm_role` VALUES ('18a6209039c44b1c94663c9114281f8e', 'string1', 'string1', '1', '2019-11-16 22:22:56', '1', '2019-11-16 22:22:56', '1');
INSERT INTO `rm_role` VALUES ('1c5a4c4c9380421a9c4dd91b4069fdf1', 'string14', 'string13', '1', '2019-11-17 23:18:14', '10', '2019-11-17 23:18:14', '10');
INSERT INTO `rm_role` VALUES ('290d483b1bea441d877025e0e37ecffd', 'string2', 'string2', '1', '2019-11-17 00:13:22', '1', '2019-11-17 00:14:57', '1');
INSERT INTO `rm_role` VALUES ('2e657e45f21a48f6a3f0111b590fbe1e', 'anonym', '匿名角色，拥有除用户、角色、资源的删除权限外的所有权限', '1', '2019-08-25 21:03:12', '1', '2019-08-25 21:03:12', '1');
INSERT INTO `rm_role` VALUES ('3b23f71f604a4eea93bb5bf542aff068', 'text', 'text', null, '2019-11-17 13:01:22', '1', '2019-11-17 13:01:22', '1');
INSERT INTO `rm_role` VALUES ('708290cc3d9542b6abae7823be9d3f56', 'text', 'text', null, '2019-11-17 13:29:45', '1', '2019-11-17 13:29:45', '1');
INSERT INTO `rm_role` VALUES ('9c9b37620a4146b89d26a4b6d52ccdc7', 'string4', 'string3', '1', '2019-11-17 13:35:09', '10', '2019-11-17 13:35:09', '10');
INSERT INTO `rm_role` VALUES ('c8cb2e89be9a4dc9b12d2375e879e98c', 'admin', '管理员', '1', '2019-08-21 09:32:37', '1', '2019-08-21 09:32:37', '1');
INSERT INTO `rm_role` VALUES ('ec155fd23f21484d9d33289ae705b1a1', 'superAdmin', '超级管理员', '1', '2019-08-21 09:32:22', '1', '2019-08-21 09:32:22', '1');

CREATE TABLE `rm_role_resource_rel` (
  `pk_role_resource_id` varchar(32) NOT NULL COMMENT '主键',
  `role_id` varchar(32) DEFAULT NULL COMMENT '角色ID',
  `resource_id` varchar(32) DEFAULT NULL COMMENT '资源ID',
  `resource_type` tinyint(1) DEFAULT NULL COMMENT '1菜单，2按钮',
  `create_time` datetime DEFAULT NULL,
  `create_user_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`pk_role_resource_id`),
  KEY `index_role_id` (`role_id`) USING BTREE,
  KEY `index_resource_id` (`resource_id`) USING BTREE,
  KEY `index_resource_type` (`resource_type`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `rm_role_resource_rel` VALUES ('0003e6443fde4ac281d37060801ecb42', 'ec155fd23f21484d9d33289ae705b1a1', '1b3445eac1e6482090186aa105d7049a', '2', '2019-08-21 09:54:38', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('0f649f2e72c0450bb799ebefaa7136b6', 'ec155fd23f21484d9d33289ae705b1a1', '7b77b230570642e4b68182989e18460d', '2', '2019-08-21 10:57:34', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('0f8ffc6512be47289cde85731f4a0b4f', '2e657e45f21a48f6a3f0111b590fbe1e', '83c6ad1f73d746de924a03d00069e790', null, '2019-08-25 21:16:56', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('187cece907044866832b0d0e3ca522b3', '2e657e45f21a48f6a3f0111b590fbe1e', '3939e23ff7a7481c9d36d3ebf1baf08c', null, '2019-08-25 21:16:56', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('18d02734dc2045098a21a2a7b4a62dcf', '2e657e45f21a48f6a3f0111b590fbe1e', '5455948952f441588d88ef3b194d6a91', null, '2019-08-25 21:16:56', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('19efe28f0e214a59b17dc0b80d33ef2d', '2e657e45f21a48f6a3f0111b590fbe1e', 'cc2a7dae77cc4625a1397b9c60fa5c51', null, '2019-08-25 21:16:56', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('1b6fc310a10b4eef97dae29d735e2cc3', 'ec155fd23f21484d9d33289ae705b1a1', 'ea71d0b7204c4bc7ab1a3455c77eb85e', '1', '2019-08-21 10:38:47', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('1cc21f338b5640208874e78dd7e18f7e', 'ec155fd23f21484d9d33289ae705b1a1', '6e4f27095b6640078aa217433a663d7b', '2', '2019-08-21 10:52:09', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('29cab999af57473daa444e627a4fe136', 'ec155fd23f21484d9d33289ae705b1a1', '2c8c47c7d3eb413c8fa0f5823a6bc862', '1', '2019-08-21 10:04:41', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('303651ac171849efb095de2b33e1b84c', '2e657e45f21a48f6a3f0111b590fbe1e', '93a2394ccf044c709ea660569b91d5ea', null, '2019-08-25 21:16:56', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('338f35e9d03241acaef6d389de9ae716', 'ec155fd23f21484d9d33289ae705b1a1', '1e0af706d4854f7b9d0b8b6013fc405b', '2', '2019-08-21 10:43:43', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('359f236e193b42dd991aa9448e77ed8e', 'ec155fd23f21484d9d33289ae705b1a1', '7351a07ece4f4fc9bab359dcabaa23cf', '1', '2019-08-21 10:11:24', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('3a9e009408c84b2580feb58c522d3cfa', 'ec155fd23f21484d9d33289ae705b1a1', 'ca4c6f3e713b4ca0b60e2752e3fc70db', '2', '2019-08-21 22:37:25', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('3aa91054b2574336bc17cb685e4cd69e', 'ec155fd23f21484d9d33289ae705b1a1', 'cc2a7dae77cc4625a1397b9c60fa5c51', '2', '2019-08-21 11:01:26', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('488c822aeebb455ebdfdf8e08dede4fd', 'ec155fd23f21484d9d33289ae705b1a1', 'd68735a434b74d01b502b330790d54ec', '1', '2019-08-21 22:41:45', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('4cfaad5ff05c4e0fa3af9763ddf5f5e6', '2e657e45f21a48f6a3f0111b590fbe1e', '1e0af706d4854f7b9d0b8b6013fc405b', null, '2019-08-25 21:16:56', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('6ae90b8d3c37432385c45e5e347d9cbf', 'ec155fd23f21484d9d33289ae705b1a1', '68cd6eb3e1264628a25052f9f8f24dba', '2', '2019-08-21 10:59:09', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('728a2612aba04b1aacf7dd6118554838', 'ec155fd23f21484d9d33289ae705b1a1', 'ee00f923bcae44a48d73a343975788f3', '2', '2019-08-21 10:58:15', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('874c449bd0944007a458706969b83646', 'ec155fd23f21484d9d33289ae705b1a1', '5455948952f441588d88ef3b194d6a91', '2', '2019-08-21 22:36:50', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('933ed7952a874f68972eada2e1016b89', 'ec155fd23f21484d9d33289ae705b1a1', '93a2394ccf044c709ea660569b91d5ea', '1', '2019-08-21 10:37:14', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('93407de553564e1bab97395b71ed01fb', 'ec155fd23f21484d9d33289ae705b1a1', 'a85d7f0b1feb472580a676f5f0b4ad88', '1', '2019-11-16 21:14:13', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('9502e3d13e054d1bbb387895870b08dc', 'ec155fd23f21484d9d33289ae705b1a1', 'd0e73b3b14504550a554f552c58a91df', '2', '2019-08-21 10:44:41', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('9ac025708323437baefdcd4cb9637199', 'ec155fd23f21484d9d33289ae705b1a1', 'e719a109a7014484901f657f7f3a8c3b', '1', '2019-08-21 10:20:19', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('a0f4935469b94661a3d5093056252ce4', '2e657e45f21a48f6a3f0111b590fbe1e', 'ca4c6f3e713b4ca0b60e2752e3fc70db', null, '2019-08-25 21:16:56', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('a484215035d6416ca9d441978e969d17', 'ec155fd23f21484d9d33289ae705b1a1', '83c6ad1f73d746de924a03d00069e790', '2', '2019-08-21 22:36:03', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('a6f079cccd314be4b240bbb172a4ecb5', 'ec155fd23f21484d9d33289ae705b1a1', '6377b4d5ae084b6086a7b224488ee273', '2', '2019-08-21 10:59:31', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('a8978c9727e541cebc4b5d58a4723b11', '2e657e45f21a48f6a3f0111b590fbe1e', '347de92f327c40e290b1f33f8206266d', null, '2019-08-25 21:16:56', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('b5722b6122b54410a4a0480cae80a305', '2e657e45f21a48f6a3f0111b590fbe1e', 'e719a109a7014484901f657f7f3a8c3b', null, '2019-08-25 21:16:56', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('bff0c0055fd24d3e950ef39e40d0b7fe', 'ec155fd23f21484d9d33289ae705b1a1', '3939e23ff7a7481c9d36d3ebf1baf08c', '1', '2019-08-21 10:38:06', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('c0e270a6a96d4f4396ed9c369045c93f', 'ec155fd23f21484d9d33289ae705b1a1', 'd9484a8b6afc4de78be1323a96c4b1c7', '1', '2019-08-21 22:38:32', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('c89084fc50ae460c96a071edef3c2ff5', 'ec155fd23f21484d9d33289ae705b1a1', '49240f0695b44453b3cdccf7e2167188', '2', '2019-08-21 11:00:18', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('c99dccd49ac943269fa841fa68519fd0', '2e657e45f21a48f6a3f0111b590fbe1e', 'ee00f923bcae44a48d73a343975788f3', null, '2019-08-25 21:16:56', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('cec0469828d24b9dace656946eb381b1', 'ec155fd23f21484d9d33289ae705b1a1', '347de92f327c40e290b1f33f8206266d', '2', '2019-08-21 10:57:11', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('d3d1688611d542d8b4eec336b6e7df97', 'ec155fd23f21484d9d33289ae705b1a1', '9ee1344e62104f39a857ffc96d445423', '2', '2019-08-21 11:01:41', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('eddda0f784184b09aff9950b3705f9f9', '2e657e45f21a48f6a3f0111b590fbe1e', 'ea71d0b7204c4bc7ab1a3455c77eb85e', null, '2019-08-25 21:16:56', '1');

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

INSERT INTO `rm_student` VALUES ('097116cf179d4085a6efb4a4c338f949', '刘海天', '20161572', null, '18382680902', null, null, null, '1', '2019-04-18 05:30:19', 'af7cae9f7b87426ab1572d431c56253d', '2019-04-18 05:30:19', 'af7cae9f7b87426ab1572d431c56253d');
INSERT INTO `rm_student` VALUES ('1525768912444d11a2ce180909d97616', '胡锐', '20161224', null, '17796416935', null, null, null, '1', '2019-04-18 05:30:19', 'af7cae9f7b87426ab1572d431c56253d', '2019-04-18 05:30:19', 'af7cae9f7b87426ab1572d431c56253d');
INSERT INTO `rm_student` VALUES ('230c29a1b05a40a2b42c5624fb847f85', '邓鑫', '20162403', null, '13028149773', null, null, null, '1', '2019-04-18 05:30:19', 'af7cae9f7b87426ab1572d431c56253d', '2019-04-18 05:30:19', 'af7cae9f7b87426ab1572d431c56253d');
INSERT INTO `rm_student` VALUES ('2752de1a4608416c919e66a9e94f610c', '胡兰月', '20161165', null, '13281153951', null, null, null, '1', '2019-04-18 05:30:19', 'af7cae9f7b87426ab1572d431c56253d', '2019-04-18 05:30:19', 'af7cae9f7b87426ab1572d431c56253d');
INSERT INTO `rm_student` VALUES ('2a167c39ad09440bba0c56db4fc9532b', '高偲源', '20162633', null, '15281238258', null, null, null, '1', '2019-04-18 05:30:19', 'af7cae9f7b87426ab1572d431c56253d', '2019-04-18 05:30:19', 'af7cae9f7b87426ab1572d431c56253d');
INSERT INTO `rm_student` VALUES ('2cd32fa1705240589c526890f65851e8', '陶银', '20161249', null, '18284012368', null, null, null, '1', '2019-04-18 05:30:19', 'af7cae9f7b87426ab1572d431c56253d', '2019-04-18 05:30:19', 'af7cae9f7b87426ab1572d431c56253d');
INSERT INTO `rm_student` VALUES ('2e5d4b737d964c1194debe3e50c06b11', '曾植', '20161412', null, '18982441821', null, null, null, '1', '2019-04-18 05:30:19', 'af7cae9f7b87426ab1572d431c56253d', '2019-04-18 05:30:19', 'af7cae9f7b87426ab1572d431c56253d');
INSERT INTO `rm_student` VALUES ('31701f91052949a38c00f0ea96a1d148', '杨宏达', '20161917', null, '13072895380', null, null, null, '1', '2019-04-18 05:30:19', 'af7cae9f7b87426ab1572d431c56253d', '2019-04-18 05:30:19', 'af7cae9f7b87426ab1572d431c56253d');
INSERT INTO `rm_student` VALUES ('531c411f6b424a82a8c04cd68a475d74', '李绍杰', '20161418', null, '17780617249', null, null, null, '1', '2019-04-18 05:30:19', 'af7cae9f7b87426ab1572d431c56253d', '2019-04-18 05:30:19', 'af7cae9f7b87426ab1572d431c56253d');
INSERT INTO `rm_student` VALUES ('55649a2c09944804907c09a22d6d81c7', '梁焱', '20161280', null, '13550266782', null, null, null, '1', '2019-04-18 05:30:19', 'af7cae9f7b87426ab1572d431c56253d', '2019-04-18 05:30:19', 'af7cae9f7b87426ab1572d431c56253d');
INSERT INTO `rm_student` VALUES ('623dec65e5614b6cbe87332c1b2bca78', '胡云龙', '20161265', null, '13568278458', null, null, null, '1', '2019-04-18 05:30:19', 'af7cae9f7b87426ab1572d431c56253d', '2019-04-18 05:30:19', 'af7cae9f7b87426ab1572d431c56253d');
INSERT INTO `rm_student` VALUES ('6dc297461d334247b35cc4b3659ddecc', '杨耀', '20161482', null, '13982244895', null, null, null, '1', '2019-04-18 05:30:19', 'af7cae9f7b87426ab1572d431c56253d', '2019-04-18 05:30:19', 'af7cae9f7b87426ab1572d431c56253d');
INSERT INTO `rm_student` VALUES ('75810cff8d8a458bbb408c8e06578d3b', '杨东', '20161441', null, '18398760146', null, null, null, '1', '2019-04-18 05:30:19', 'af7cae9f7b87426ab1572d431c56253d', '2019-04-18 05:30:19', 'af7cae9f7b87426ab1572d431c56253d');
INSERT INTO `rm_student` VALUES ('7a72e1ebe5be45fbadbe0e9e61b49aac', '彭佳舟', '20161526', null, '17796400645', null, null, null, '1', '2019-04-18 05:30:19', 'af7cae9f7b87426ab1572d431c56253d', '2019-04-18 05:30:19', 'af7cae9f7b87426ab1572d431c56253d');
INSERT INTO `rm_student` VALUES ('849aea486f9649de9d38a852ae325c1d', '蓝俊幢', '20161025', null, '13258107297', null, null, null, '1', '2019-04-18 05:30:19', 'af7cae9f7b87426ab1572d431c56253d', '2019-04-18 05:30:19', 'af7cae9f7b87426ab1572d431c56253d');
INSERT INTO `rm_student` VALUES ('88a68394b3354a6e99021c5e89268e2d', '丁旭', '20161059', null, '15348126805', null, null, null, '1', '2019-04-18 05:30:19', 'af7cae9f7b87426ab1572d431c56253d', '2019-04-18 05:30:19', 'af7cae9f7b87426ab1572d431c56253d');
INSERT INTO `rm_student` VALUES ('8b7e635f99224720afd967a3310ced7b', '黄红霞', '20161615', null, '17381547580', null, null, null, '1', '2019-04-18 05:30:19', 'af7cae9f7b87426ab1572d431c56253d', '2019-04-18 05:30:19', 'af7cae9f7b87426ab1572d431c56253d');
INSERT INTO `rm_student` VALUES ('8d473405f42244e3a1d47a647695108c', '许峻', '20161545', 'e10adc3949ba59abbe56e057f20f883e', '18728688910', null, null, null, '1', '2019-04-18 05:30:19', 'af7cae9f7b87426ab1572d431c56253d', '2019-04-18 05:30:19', 'af7cae9f7b87426ab1572d431c56253d');
INSERT INTO `rm_student` VALUES ('9057b18fa8264758b7208e169d38e7b6', '于毅', '20162024', null, '13350894596', null, null, null, '1', '2019-04-18 05:30:19', 'af7cae9f7b87426ab1572d431c56253d', '2019-04-18 05:30:19', 'af7cae9f7b87426ab1572d431c56253d');
INSERT INTO `rm_student` VALUES ('949a8e66e545448f81e64520975cd9e4', '赵荣', '20162010', 'e10adc3949ba59abbe56e057f20f883e', '17796410773', null, null, null, '1', '2019-04-18 05:30:19', 'af7cae9f7b87426ab1572d431c56253d', '2019-04-18 05:30:19', 'af7cae9f7b87426ab1572d431c56253d');
INSERT INTO `rm_student` VALUES ('977033b9ce0241bab80a939d961856ff', '叶开琅', '20162009', null, '13348881015', null, null, null, '1', '2019-04-18 05:30:19', 'af7cae9f7b87426ab1572d431c56253d', '2019-04-18 05:30:19', 'af7cae9f7b87426ab1572d431c56253d');
INSERT INTO `rm_student` VALUES ('ac79f6fdfd0f47048a445e4d6e099a53', '汪文馨', '20162946', null, '13980954627', null, null, null, '1', '2019-04-18 05:30:19', 'af7cae9f7b87426ab1572d431c56253d', '2019-04-18 05:30:19', 'af7cae9f7b87426ab1572d431c56253d');
INSERT INTO `rm_student` VALUES ('b6caeb5eeadc42729fe5f937eb9bcb80', '陈梅', '20161432', null, '15196712736', null, null, null, '1', '2019-04-18 05:30:19', 'af7cae9f7b87426ab1572d431c56253d', '2019-04-18 05:30:19', 'af7cae9f7b87426ab1572d431c56253d');
INSERT INTO `rm_student` VALUES ('ba7f0708aa32405a8301c68ae9e82d8d', '伍毓灵', '20161440', null, '18784827103', null, null, null, '1', '2019-04-18 05:30:19', 'af7cae9f7b87426ab1572d431c56253d', '2019-04-18 05:30:19', 'af7cae9f7b87426ab1572d431c56253d');
INSERT INTO `rm_student` VALUES ('c383af8b27884fd491ae6638a1e1c33b', '肖翔', '20161935', null, '15183294906', null, null, null, '1', '2019-04-18 05:30:19', 'af7cae9f7b87426ab1572d431c56253d', '2019-04-18 05:30:19', 'af7cae9f7b87426ab1572d431c56253d');
INSERT INTO `rm_student` VALUES ('c3dd47f5c96d4defa45f2252f3777624', '余相霖', '20161402', null, '18200576478', null, null, null, '1', '2019-04-18 05:30:19', 'af7cae9f7b87426ab1572d431c56253d', '2019-04-18 05:30:19', 'af7cae9f7b87426ab1572d431c56253d');
INSERT INTO `rm_student` VALUES ('c7821deab220424b84c3d3b606722d42', '邓显辉', '20161732', null, '13350684860', null, null, null, '1', '2019-04-18 05:30:19', 'af7cae9f7b87426ab1572d431c56253d', '2019-04-18 05:30:19', 'af7cae9f7b87426ab1572d431c56253d');
INSERT INTO `rm_student` VALUES ('c939d5623e7146728c165b910ea78062', '李岚', '20162651', null, '17738721014', null, null, null, '1', '2019-04-18 05:30:19', 'af7cae9f7b87426ab1572d431c56253d', '2019-04-18 05:30:19', 'af7cae9f7b87426ab1572d431c56253d');
INSERT INTO `rm_student` VALUES ('cece48defcc841d5a3bc740ede306cae', '李林洋', '20162508', null, '13072801589', null, null, null, '1', '2019-04-18 05:30:19', 'af7cae9f7b87426ab1572d431c56253d', '2019-04-18 05:30:19', 'af7cae9f7b87426ab1572d431c56253d');
INSERT INTO `rm_student` VALUES ('d4cf7e719dfc427084adf539e44288c3', '张清平', '20161228', null, '15982729942', null, null, null, '1', '2019-04-18 05:30:19', 'af7cae9f7b87426ab1572d431c56253d', '2019-04-18 05:30:19', 'af7cae9f7b87426ab1572d431c56253d');
INSERT INTO `rm_student` VALUES ('d910ec419e3e4bdbb06ca39b1dbd34fe', '文永鹏', '20161822', null, '13018220489', null, null, null, '1', '2019-04-18 05:30:19', 'af7cae9f7b87426ab1572d431c56253d', '2019-04-18 05:30:19', 'af7cae9f7b87426ab1572d431c56253d');
INSERT INTO `rm_student` VALUES ('e02b4f04042c4157af08428b87be5d62', '曾鹏', '20161585', null, '18190770731', null, null, null, '1', '2019-04-18 05:30:19', 'af7cae9f7b87426ab1572d431c56253d', '2019-04-18 05:30:19', 'af7cae9f7b87426ab1572d431c56253d');
INSERT INTO `rm_student` VALUES ('f1fe624befb54c1da17e5a52dea8b449', '刘浩', '20162465', null, '18161274081', null, null, null, '1', '2019-04-18 05:30:19', 'af7cae9f7b87426ab1572d431c56253d', '2019-04-18 05:30:19', 'af7cae9f7b87426ab1572d431c56253d');
INSERT INTO `rm_student` VALUES ('f2f5176a226c4a2486398d0a8d090d55', '贾腾焱', '20161744', null, '13183763545', null, null, null, '1', '2019-04-18 05:30:19', 'af7cae9f7b87426ab1572d431c56253d', '2019-04-18 05:30:19', 'af7cae9f7b87426ab1572d431c56253d');

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
  `status` int(11) DEFAULT NULL COMMENT '用户状态(-1已删除，0禁用，1启用)',
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

INSERT INTO `rm_user` VALUES ('1', 'aishu', 'aishu', '744837cc03a9df3a5df0c8e196dfec7b', '15328417106', '0', null, '1', '511324199702101111', '格子衬衫', '1', '1', null, null, null, '2181250231@163.com', '2019-11-18 23:31:23', '2019-08-21 09:23:39', '1', '2019-11-18 23:31:24', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, '最帅的辣个男人');
INSERT INTO `rm_user` VALUES ('10', 'xiaozhu', 'xiaozhu', 'f9fe0689a2ad1773d0eaabddab6218ce', '15328417109', '0', null, '1', '511324199702101112', 'xiaozhu', '1', '1', null, null, null, '2181250234@163.com', '2019-11-17 23:17:43', '2019-08-21 11:24:21', '1', '2019-11-17 23:17:43', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, '匿名用户');
INSERT INTO `rm_user` VALUES ('973d35c93505423cac7004751c75565a', 'yang', 'yang', 'f138137a73e163022ee429851e421e18', '13551354962', '1', null, null, null, null, '1', '1', null, null, null, '1264576767@qq.com', null, '2019-10-19 21:57:06', '10', '2019-10-31 21:30:57', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `rm_user` VALUES ('a7a7bf6d194b4c0a8f057e21ab8553d2', 'tom', 'tom', '263278683ca54115753e703da503a598', '15328417102', '0', null, '1', '511324199702101114', 'tom', '1', '1', null, null, null, 'tom@163.com', null, '2019-08-26 01:13:53', '10', '2019-10-31 21:30:57', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, '测试用户');
INSERT INTO `rm_user` VALUES ('e56a8a111fbc47029fc91825536ca4cd', 'jughead', 'jughead', 'b1fade221f96c86c6022711ab3b5303a', '15328417105', '0', null, '1', '511324199702101115', 'jughead', '1', '1', null, null, null, 'jughead@163.com', null, '2019-08-26 01:13:18', '10', '2019-10-31 21:30:57', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, '测试用户');

CREATE TABLE `rm_user_privilege` (
  `pk_privilege_id` varchar(32) NOT NULL,
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户ID',
  `subject_id` varchar(32) DEFAULT NULL COMMENT '资源id或者角色id',
  `subject_type` tinyint(1) DEFAULT NULL COMMENT '类型（1菜单，2按钮，3角色）',
  `create_time` datetime DEFAULT NULL,
  `create_user_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`pk_privilege_id`),
  KEY `index_user_id` (`user_id`) USING BTREE,
  KEY `index_subject_id` (`subject_id`) USING BTREE,
  KEY `index_subject_type` (`subject_type`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户与角色、资源关联信息';

INSERT INTO `rm_user_privilege` VALUES ('1033a2150d7b47deb4fc09e14cd11aaa', '10', '2e657e45f21a48f6a3f0111b590fbe1e', '3', '2019-08-25 21:05:06', '1');
INSERT INTO `rm_user_privilege` VALUES ('bbec66146a0642f7840c2bc307d86341', '1', 'c8cb2e89be9a4dc9b12d2375e879e98c', '3', '2019-08-21 11:16:00', '1');
INSERT INTO `rm_user_privilege` VALUES ('d2366fb620ae4dd4a6abe4f5801ab137', '1', 'ec155fd23f21484d9d33289ae705b1a1', '3', '2019-08-21 11:16:00', '1');
