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

INSERT INTO `rm_log` VALUES ('04ab8a02c6d64d4f9c0de47d85be878e', null, '保存或修改资源', '113', 'com.waken.dorm.controller.resource.ResourceController.saveResource()', '  editResourceForm: EditResourceForm(pkResourceId=null, resourceName=学校删除, resourceIcon=null, resourceType=2, resourceUrl=null, parentId=3808f9ac86884504af5827963986248f, perms=schoolsdelete)', '127.0.0.1', '2019-08-21 17:55:47', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('05d8a68d40cf4902aa423cde9c1e99c4', null, '用户登录', '1064', 'com.waken.dorm.controller.auth.LoginController.login()', '  queryUserForm: QueryUserForm(userName=aishu, password=aishu)  request: org.apache.shiro.web.servlet.ShiroHttpServletRequest@6f18f59f', '127.0.0.1', '2019-08-21 14:03:57', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('065b349bf69c4eb6978135faf9d18c5b', null, '保存或修改资源', '93', 'com.waken.dorm.controller.resource.ResourceController.saveResource()', '  editResourceForm: EditResourceForm(pkResourceId=null, resourceName=资源删除, resourceIcon=null, resourceType=2, resourceUrl=null, parentId=3939e23ff7a7481c9d36d3ebf1baf08c, perms=resources::delete)', '127.0.0.1', '2019-08-21 11:01:41', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('06a1a108718d4107b24dc53caed60bcc', null, '用户登录', '1719', 'com.waken.dorm.controller.auth.LoginController.login()', '  queryUserForm: QueryUserForm(userName=aishu, password=123456)  request: org.apache.shiro.web.servlet.ShiroHttpServletRequest@65c41ea9', '127.0.0.1', '2019-08-15 13:57:33', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('079ccb90afc848939e1b0fa20f94ac6d', null, '用户登录', '8944', 'com.waken.dorm.controller.auth.LoginController.login()', '  queryUserForm: QueryUserForm(userName=aishu, password=aishu)  request: org.apache.shiro.web.servlet.ShiroHttpServletRequest@640cba09', '127.0.0.1', '2019-08-21 22:03:55', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('0b6541d9e2cf4d7bac53dfe21cb49ec7', null, '用户登录', '224', 'com.waken.dorm.controller.user.UserController.login()', '  queryUserForm: QueryUserForm(userName=许峻, password=123456)', '127.0.0.1', '2019-08-13 12:21:34', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('0fe05773157f4c6384bd42bfeb605ae1', null, '用户登录', '290', 'com.waken.dorm.controller.user.UserController.login()', '  queryUserForm: QueryUserForm(userName=许峻, password=123456)', '127.0.0.1', '2019-08-05 14:09:37', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('1042933bbd894b57a75895ed3ba8a55b', null, '用户登录', '66520', 'com.waken.dorm.controller.auth.LoginController.login()', '  queryUserForm: QueryUserForm(userName=aishu, password=123456)  request: org.apache.shiro.web.servlet.ShiroHttpServletRequest@5eb1c622', '127.0.0.1', '2019-08-16 19:52:51', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('111228010e7548eeb9d89ba831120c8b', null, '保存或修改资源', '96', 'com.waken.dorm.controller.resource.ResourceController.saveResource()', '  editResourceForm: EditResourceForm(pkResourceId=null, resourceName=用户导出, resourceIcon=null, resourceType=2, resourceUrl=null, parentId=93a2394ccf044c709ea660569b91d5ea, perms=users::export)', '127.0.0.1', '2019-08-21 10:58:15', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('11c9cabc1394458585a50f853cdac121', null, '用户登录', '1203', 'com.waken.dorm.controller.auth.LoginController.login()', '  queryUserForm: QueryUserForm(userName=aishu, password=123456)  request: org.apache.shiro.web.servlet.ShiroHttpServletRequest@708c3674', '127.0.0.1', '2019-08-16 20:54:58', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('1226e1ca941c4f7998bfad508c5c5f3c', 'af7cae9f7b87426ab1572d431c56253d', '删除资源', '104549', 'com.waken.dorm.controller.resource.ResourceController.deleteResource()', 'deleteFrom: DeleteForm{delIds=[e8a45231e88345d7850e2ba439a7e4dc, 0e2fa4ad02324ec68ce294ae454cb338, 28600d66f2ed49749c9d299fe4f2defb, e62dd535125e41e9bf4ef257c8c9a376], delStatus=0}', '127.0.0.1', '2019-05-05 07:34:21', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('12974d44fc754b5d965f6459cc81b846', null, '导出用户信息', '599', 'com.waken.dorm.controller.user.UserController.export()', '  response: com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@7e695e18', '127.0.0.1', '2019-08-18 10:48:03', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('12ab0e5e7d0947f6aefefdd3f7f2556e', null, '用户登录', '244', 'com.waken.dorm.controller.auth.LoginController.login()', '  queryUserForm: QueryUserForm(userName=许俊, password=123456)  request: org.apache.shiro.web.servlet.ShiroHttpServletRequest@3704232a', '127.0.0.1', '2019-08-20 10:48:07', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('12e1bc9eb0304485ab4a2ce69943f138', null, '用户登录', '579', 'com.waken.dorm.controller.auth.LoginController.login()', '  queryUserForm: QueryUserForm(userName=许峻, password=123456)  request: org.apache.shiro.web.servlet.ShiroHttpServletRequest@8cc66c1', '127.0.0.1', '2019-08-19 21:17:16', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('13c512f8f7484108b62dae8e657a3796', 'af7cae9f7b87426ab1572d431c56253d', '(保存/修改)字典信息', '138', 'com.waken.dorm.controller.dict.DictController.saveDict()', 'editDictForm: EditDictForm{pkDictId=\'81478ecdc4714c6c8803534d7fc3f4f9\', schoolId=\'4ddecf810f15405e8b4665a85f273172\', dictKey=\'1\', dictValue=\'生效\', columnName=\'status\', columnDesc=\'状态\', tableName=\'rm_dorm\', tableDesc=\'宿舍信息表\'}', '127.0.0.1', '2019-04-19 10:06:24', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('14d5d54b173b4768a1199548d64e41f4', null, '用户登录', '226', 'com.waken.dorm.controller.user.UserController.login()', '  queryUserForm: QueryUserForm(userName=许峻, password=123456)', '127.0.0.1', '2019-08-12 12:46:48', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('155fdad76ed0431d84a0a4de402c536c', null, '用户登录', '826', 'com.waken.dorm.controller.auth.LoginController.login()', '  queryUserForm: QueryUserForm(userName=aishu, password=aishu)  request: org.apache.shiro.web.servlet.ShiroHttpServletRequest@4e06a223', '127.0.0.1', '2019-08-21 15:36:32', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('15bfd52cb3a342c19855b6b10099d7bc', 'af7cae9f7b87426ab1572d431c56253d', '(保存/修改)字典信息', '287', 'com.waken.dorm.controller.dict.DictController.saveDict()', 'editDictForm: EditDictForm{pkDictId=\'null\', schoolId=\'4ddecf810f15405e8b4665a85f273172\', dictKey=\'null\', dictValue=\'null\', columnName=\'status\', columnDesc=\'状态\', tableName=\'rm_dorm\', tableDesc=\'宿舍表\'}', '127.0.0.1', '2019-04-19 10:04:30', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('1764c9506494420f80d0f1a7dcba0573', null, '用户登录', '495', 'com.waken.dorm.controller.auth.LoginController.login()', '  queryUserForm: QueryUserForm(userName=aishu, password=aishu)  request: org.apache.shiro.web.servlet.ShiroHttpServletRequest@71138774', '127.0.0.1', '2019-08-21 11:22:42', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('17d32eddd61a4b248920d9d7c1cc5fa8', 'af7cae9f7b87426ab1572d431c56253d', '(保存/修改)字典信息', '94949', 'com.waken.dorm.controller.dict.DictController.saveDict()', 'editDictForm: EditDictForm{pkDictId=\'null\', schoolId=\'4ddecf810f15405e8b4665a85f273172\', key=\'1\', value=\'生效\', columnName=\'status\', columnDesc=\'状态\', tableName=\'rm_dorm\', tableDesc=\'宿舍表\'}', '127.0.0.1', '2019-04-19 09:36:22', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('18ef850b2932419c93b0a822fc594a9e', null, '用户登录', '7014', 'com.waken.dorm.controller.auth.LoginController.login()', '  queryUserForm: QueryUserForm(userName=aishu, password=123456)  request: org.apache.shiro.web.servlet.ShiroHttpServletRequest@2611aff8', '127.0.0.1', '2019-08-15 13:54:41', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('1bd6e7e453b744648233985b5ef73870', null, '用户登录', '920', 'com.waken.dorm.controller.auth.LoginController.login()', '  queryUserForm: QueryUserForm(userName=zhou, password=123456)  request: org.apache.shiro.web.servlet.ShiroHttpServletRequest@76eb9b0f', '127.0.0.1', '2019-08-15 13:18:37', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('1cbe36a4663a456dbf0319b2502c32f5', null, '用户登录', '527', 'com.waken.dorm.controller.auth.LoginController.login()', '  queryUserForm: QueryUserForm(userName=aishu, password=aishu)  request: org.apache.shiro.web.servlet.ShiroHttpServletRequest@53a26d79', '127.0.0.1', '2019-08-21 22:20:06', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('1e3838597b714d278d679f9f9cf20673', null, '导出用户信息', '1183', 'com.waken.dorm.controller.user.UserController.export()', '  response: com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@63ccfc0e', '127.0.0.1', '2019-08-18 10:51:01', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('1fca3dd683284d47aa7e758c907a957e', 'af7cae9f7b87426ab1572d431c56253d', '删除资源', '75', 'com.waken.dorm.controller.resource.ResourceController.deleteResource()', 'deleteFrom: DeleteForm{delIds=[e8a45231e88345d7850e2ba439a7e4dc], delStatus=0}', '127.0.0.1', '2019-05-05 07:23:51', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('20340e6d1fce48849262b19ae64ed72d', null, '用户登录', '3156', 'com.waken.dorm.controller.auth.LoginController.login()', '  queryUserForm: QueryUserForm(userName=aishu, password=123456)  request: org.apache.shiro.web.servlet.ShiroHttpServletRequest@da043a5', '127.0.0.1', '2019-08-15 22:02:38', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('27691aa7ab584b8db4ead3b39bd2de65', null, '用户登录', '547', 'com.waken.dorm.controller.user.UserController.login()', '  queryUserForm: QueryUserForm(userName=许峻, password=123456)', '127.0.0.1', '2019-08-13 11:23:59', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('2e68d215b79b4f1185ad0fdc781bfa36', null, '保存或修改角色', '241', 'com.waken.dorm.controller.role.RoleController.saveRole()', '  editRoleForm: EditRoleForm(pkRoleId=null, roleName=superAdmin, roleDesc=超级管理员)', '127.0.0.1', '2019-08-21 09:32:22', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('2e6cc41f06a048fcbb0b24a7b315cef7', 'af7cae9f7b87426ab1572d431c56253d', '删除资源', '18444', 'com.waken.dorm.controller.resource.ResourceController.deleteResource()', 'deleteFrom: DeleteForm{delIds=[e8a45231e88345d7850e2ba439a7e4dc, 0e2fa4ad02324ec68ce294ae454cb338, 28600d66f2ed49749c9d299fe4f2defb, e62dd535125e41e9bf4ef257c8c9a376], delStatus=0}', '127.0.0.1', '2019-05-05 07:10:58', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('2e8e2e971c0440c7be87ca8cc082575e', null, '用户登录', '36', 'com.waken.dorm.controller.user.UserController.login()', '  queryUserForm: QueryUserForm(userName=许峻, password=123456)', '127.0.0.1', '2019-08-12 12:41:57', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('2f5e0e7fcbef4bedb5c9d61a413d0a48', null, '保存或修改资源', '121', 'com.waken.dorm.controller.resource.ResourceController.saveResource()', '  editResourceForm: EditResourceForm(pkResourceId=null, resourceName=保存角色, resourceIcon=null, resourceType=2, resourceUrl=/dorm/role/save.html, parentId=ea71d0b7204c4bc7ab1a3455c77eb85e, perms=roles::save)', '127.0.0.1', '2019-08-21 10:43:43', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('30bb4344e1b4424991e592409a4566a1', null, '用户登录', '545', 'com.waken.dorm.controller.user.UserController.login()', '  queryUserForm: QueryUserForm(userName=许峻, password=123456)', '127.0.0.1', '2019-08-05 16:00:02', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('310e41e3218a48e58792bd99227867fc', null, '用户登录', '882', 'com.waken.dorm.controller.auth.LoginController.login()', '  queryUserForm: QueryUserForm(userName=aishu, password=123456)  request: org.apache.shiro.web.servlet.ShiroHttpServletRequest@e4a91f9', '127.0.0.1', '2019-08-16 22:49:29', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('31b4a5d75e5d42fbbcd48b5e917c9ba8', null, '保存或修改资源', '122', 'com.waken.dorm.controller.resource.ResourceController.saveResource()', '  editResourceForm: EditResourceForm(pkResourceId=null, resourceName=学校宿舍孩纸1, resourceIcon=null, resourceType=1, resourceUrl=null, parentId=ff48acc71ba5457d81e20bb668e2a1f9, perms=null)', '127.0.0.1', '2019-08-21 17:56:56', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('3469a17b50b74984b868ec5fabad47bc', 'af7cae9f7b87426ab1572d431c56253d', '用户登录', '16878', 'com.waken.dorm.controller.user.UserController.login()', 'queryUserForm: QueryUserForm{userName=\'许峻\', password=\'123456\'}', '127.0.0.1', '2019-04-16 12:53:44', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('34964d45f64945bd987bb1dc0d2bde0a', null, '用户登录', '3668', 'com.waken.dorm.controller.auth.LoginController.login()', '  queryUserForm: QueryUserForm(userName=许峻, password=123456)  request: org.apache.shiro.web.servlet.ShiroHttpServletRequest@14b3fd4', '127.0.0.1', '2019-08-15 13:09:40', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('34b780e2154a4bdfb10f2d0f4ec1c88a', null, '用户登录', '868', 'com.waken.dorm.controller.auth.LoginController.login()', '  queryUserForm: QueryUserForm(userName=许峻, password=123456)  request: org.apache.shiro.web.servlet.ShiroHttpServletRequest@467797c2', '127.0.0.1', '2019-08-15 12:49:03', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('358cebd419c946cc973680773f3dd629', 'af7cae9f7b87426ab1572d431c56253d', '(保存/修改)字典信息', '19', 'com.waken.dorm.controller.dict.DictController.saveDict()', 'editDictForm: EditDictForm{pkDictId=\'null\', schoolId=\'4ddecf810f15405e8b4665a85f273172\', key=\'1\', value=\'生效\', columnName=\'status\', columnDesc=\'状态\', tableName=\'rm_dorm\', tableDesc=\'宿舍表\'}', '127.0.0.1', '2019-04-19 09:23:58', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('3794dec7374745758da0d55ac826a023', 'af7cae9f7b87426ab1572d431c56253d', '删除资源', '228186', 'com.waken.dorm.controller.resource.ResourceController.deleteResource()', 'deleteFrom: DeleteForm{delIds=[e8a45231e88345d7850e2ba439a7e4dc, 0e2fa4ad02324ec68ce294ae454cb338, 28600d66f2ed49749c9d299fe4f2defb, e62dd535125e41e9bf4ef257c8c9a376], delStatus=0}', '127.0.0.1', '2019-05-05 07:31:00', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('38818c329a214b35a1ce4bac4e0caa54', null, '用户登录', '176', 'com.waken.dorm.controller.auth.LoginController.login()', '  queryUserForm: QueryUserForm(userName=许峻, password=123456)  request: org.apache.shiro.web.servlet.ShiroHttpServletRequest@74759884', '127.0.0.1', '2019-08-17 00:03:52', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('3ac154fdcd4746d39ee27351358c6679', null, '导出用户信息', '1984', 'com.waken.dorm.controller.user.UserController.export()', '  response: com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@15c7bf38', '127.0.0.1', '2019-08-17 22:50:49', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('3d696d38cdd74158af4b81efd8d8ca0a', 'af7cae9f7b87426ab1572d431c56253d', '(保存/修改)字典信息', '35644', 'com.waken.dorm.controller.dict.DictController.saveDict()', 'editDictForm: EditDictForm{pkDictId=\'null\', schoolId=\'4ddecf810f15405e8b4665a85f273172\', key=\'1\', value=\'生效\', columnName=\'status\', columnDesc=\'状态\', tableName=\'rm_dorm\', tableDesc=\'宿舍表\'}', '127.0.0.1', '2019-04-19 09:18:54', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('3d84c52d7d69433789fe9d29ae61a3df', null, '用户登录', '6257', 'com.waken.dorm.controller.auth.LoginController.login()', '  queryUserForm: QueryUserForm(userName=aishu, password=123456)  request: org.apache.shiro.web.servlet.ShiroHttpServletRequest@5a221122', '127.0.0.1', '2019-08-16 20:22:20', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('3ebcc77643c24ed7aa0e02303730958a', 'af7cae9f7b87426ab1572d431c56253d', '删除资源', '15', 'com.waken.dorm.controller.resource.ResourceController.deleteResource()', 'deleteFrom: DeleteForm{delIds=[e8a45231e88345d7850e2ba439a7e4dc, 0e2fa4ad02324ec68ce294ae454cb338, 28600d66f2ed49749c9d299fe4f2defb, e62dd535125e41e9bf4ef257c8c9a376], delStatus=0}', '127.0.0.1', '2019-05-05 07:09:01', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('3ed8551a391643eb873e2e40736eee8f', null, '用户登录', '265', 'com.waken.dorm.controller.user.UserController.login()', '  queryUserForm: QueryUserForm(userName=许峻, password=123456)  host: \"[localhost:8080]\"  user-agent: \"[Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:68.0) Gecko/20100101 Firefox/68.0]\"  accept: \"[*/*]\"  accept-language: \"[zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2]\"  accept-encoding: \"[gzip, deflate]\"  referer: \"[http://localhost:8080/swagger-ui.html]\"  content-type: \"[application/json]\"  content-length: \"[50]\"  connection: \"[keep-alive]\"  cookie: \"[pgv_pvi=6184981504; Hm_lvt_8d87b104337ffe716311a5b6e78efd28=1557037562,1557127465; style=null; UM_distinctid=16a86ac83bf449-01e20f1a2a441e8-4c312c7c-e1000-16a86ac83c00; CNZZDATA1277354711=890341012-1557037402-null%7C1557037402; CNZZDATA30088308=cnzz_eid%3D726125434-1562675321-%26ntime%3D1562675321; JSESSIONID=4207ba20-f790-4f80-b225-16b557348cae]\"  userToken: \"[4207ba20-f790-4f80-b225-16b557348cae]\"', '127.0.0.1', '2019-08-05 16:03:16', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('41babb9362564896a01ddeb987964bf8', 'af7cae9f7b87426ab1572d431c56253d', '删除学校类别信息', '1302432', 'com.waken.dorm.controller.school.SchoolClassController.deleteSchoolClass()', 'deleteFrom: DeleteForm{delIds=[851558e5ef554079b540bc8b4ef8c03d, 1861e832380c42a18b8a1adfd0ee10f2, a98af597af5347089b841515140ad207, fca430170aa2477fae1b191fc926fe68, 04ea45d9b6a4483887e58d2bc2414aa6, 5807da8235314b0490952676b9abc144, 88b17f48fd89481dbdfae0c4ad1effe7, 9959cac621704942983fa0b859d4d70d, 12e461a8c5ee45f788be1f9e95e3bd8f, b73963a47f97429ea55e03da1ba043c2, c63749a5e52648f1addcf15fe71398b2, f09fad67b0f941128e9cabbcc9bd108c, a98af597af5347089b841515140ad207, f09fad67b0f941128e9cabbcc9bd108c, 851558e5ef554079b540bc8b4ef8c03d, fca430170aa2477fae1b191fc926fe68, b73963a47f97429ea55e03da1ba043c2, c63749a5e52648f1addcf15fe71398b2, 12e461a8c5ee45f788be1f9e95e3bd8f, 88b17f48fd89481dbdfae0c4ad1effe7, 9959cac621704942983fa0b859d4d70d, 1861e832380c42a18b8a1adfd0ee10f2, 04ea45d9b6a4483887e58d2bc2414aa6, 5807da8235314b0490952676b9abc144], delStatus=0}', '127.0.0.1', '2019-05-05 08:01:25', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('4247f9aaf9064ae1a6b6215624335196', null, '用户登录', '801', 'com.waken.dorm.controller.user.UserController.login()', '  queryUserForm: QueryUserForm(userName=许峻, password=123456)', '127.0.0.1', '2019-08-12 12:40:54', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('44ac3a3acc4145879c7b8680ea6ab78b', 'af7cae9f7b87426ab1572d431c56253d', '用户登录', '142', 'com.waken.dorm.controller.user.UserController.login()', 'queryUserForm: QueryUserForm{userName=\'许峻\', password=\'123456\'}', '127.0.0.1', '2019-04-18 04:46:30', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('451c44d7dfde44d8aff8c3de12005e6a', 'af7cae9f7b87426ab1572d431c56253d', '删除用户信息', '138', 'com.waken.dorm.controller.user.UserController.deleteUser()', '  deleteFrom: DeleteForm(delIds=[string], delStatus=0)', '127.0.0.1', '2019-06-10 08:42:09', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('45e4b9d7f97d4730929233fb84708377', null, '删除资源', '108839', 'com.waken.dorm.controller.resource.ResourceController.deleteResource()', '  deleteFrom: DeleteForm(delIds=[3808f9ac86884504af5827963986248f], delStatus=1)', '127.0.0.1', '2019-08-21 18:46:40', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('46b2f17702c643e6a9e9d2db8b19a604', null, '批量添加用户角色关联', '279', 'com.waken.dorm.controller.user.UserController.batchAddUserRoleRel()', '  addUserRoleRelForm: AddUserRoleRelForm(userId=1, roleIds=[c8cb2e89be9a4dc9b12d2375e879e98c, ec155fd23f21484d9d33289ae705b1a1])', '127.0.0.1', '2019-08-21 11:16:00', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('4702b173ca8b4ca789576756579685ae', 'af7cae9f7b87426ab1572d431c56253d', '用户登录', '32', 'com.waken.dorm.controller.user.UserController.login()', '  queryUserForm: QueryUserForm(userName=许峻, password=123456)', '127.0.0.1', '2019-06-10 09:48:46', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('4817194119434a38ba0f57dd9a10a1c9', null, '用户登录', '867', 'com.waken.dorm.controller.auth.LoginController.login()', '  queryUserForm: QueryUserForm(userName=许峻, password=123456)  request: org.apache.shiro.web.servlet.ShiroHttpServletRequest@2086e429', '127.0.0.1', '2019-08-17 00:01:03', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('48e5168629d64019878a21956439229a', null, '保存或修改资源', '119', 'com.waken.dorm.controller.resource.ResourceController.saveResource()', '  editResourceForm: EditResourceForm(pkResourceId=null, resourceName=学校宿舍, resourceIcon=null, resourceType=1, resourceUrl=null, parentId=3808f9ac86884504af5827963986248f, perms=null)', '127.0.0.1', '2019-08-21 17:56:29', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('4bc719c8dce2414c9d40eff70b487c12', 'af7cae9f7b87426ab1572d431c56253d', '用户登录', '48', 'com.waken.dorm.controller.user.UserController.login()', '  queryUserForm: QueryUserForm(userName=许峻, password=123456)', '127.0.0.1', '2019-06-10 08:41:54', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('4c24e99ac9a749e89b05daa10c93a813', null, '保存或修改资源', '111', 'com.waken.dorm.controller.resource.ResourceController.saveResource()', '  editResourceForm: EditResourceForm(pkResourceId=null, resourceName=测出猜测, resourceIcon=null, resourceType=1, resourceUrl=null, parentId=null, perms=菜单)', '127.0.0.1', '2019-08-21 22:38:32', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('4d3c8fae339a4fdb8a14489c9479de7b', null, '用户登录', '945', 'com.waken.dorm.controller.auth.LoginController.login()', '  queryUserForm: QueryUserForm(userName=xiaozhu, password=xiaozhu)  request: org.apache.shiro.web.servlet.ShiroHttpServletRequest@34d0ae01', '127.0.0.1', '2019-08-21 19:07:25', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('4d57810157f04921b2158afc3545f6bb', 'af7cae9f7b87426ab1572d431c56253d', '删除资源', '13', 'com.waken.dorm.controller.resource.ResourceController.deleteResource()', 'deleteFrom: DeleteForm{delIds=[e8a45231e88345d7850e2ba439a7e4dc, 0e2fa4ad02324ec68ce294ae454cb338, 28600d66f2ed49749c9d299fe4f2defb, e62dd535125e41e9bf4ef257c8c9a376], delStatus=0}', '127.0.0.1', '2019-05-05 07:10:12', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('4d939c06019d47a993944626f047d5b0', 'af7cae9f7b87426ab1572d431c56253d', '用户登录', '163', 'com.waken.dorm.controller.user.UserController.login()', 'queryUserForm: QueryUserForm{userName=\'许峻\', password=\'123456\'}', '127.0.0.1', '2019-04-17 06:27:53', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('4f19365682db418fb04c72d57215b594', 'af7cae9f7b87426ab1572d431c56253d', '(保存/修改)字典信息', '1009488', 'com.waken.dorm.controller.dict.DictController.saveDict()', 'editDictForm: EditDictForm{pkDictId=\'null\', schoolId=\'4ddecf810f15405e8b4665a85f273172\', key=\'1\', value=\'生效\', columnName=\'status\', columnDesc=\'状态\', tableName=\'rm_dorm\', tableDesc=\'宿舍表\'}', '127.0.0.1', '2019-04-19 10:00:04', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('52a9fa88cc334e4f8c4d3a11e80e3bab', null, '用户登录', '9440', 'com.waken.dorm.controller.auth.LoginController.login()', '  queryUserForm: QueryUserForm(userName=aishu, password=aishu)  request: org.apache.shiro.web.servlet.ShiroHttpServletRequest@c6770a9', '127.0.0.1', '2019-08-21 16:47:56', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('542ae18b552646b6bf06f6a95e30782e', null, '导出用户信息', '98', 'com.waken.dorm.controller.user.UserController.export()', '  response: com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@17124f03', '127.0.0.1', '2019-08-17 22:57:17', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('559b627f847249f487550f4ae2060a2c', 'af7cae9f7b87426ab1572d431c56253d', '导入批量学生通过学校类别id（excel）', '823', 'com.waken.dorm.controller.school.SchoolClassController.batchImportStudentByClassId()', 'request: org.springframework.web.multipart.support.StandardMultipartHttpServletRequest@253569ad  file: \"org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile@6f998e9d\"  schoolClassId: \"b73963a47f97429ea55e03da1ba043c2\"', '127.0.0.1', '2019-04-18 05:30:19', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('564e71366c704f4895d1461822bdc086', null, '用户登录', '1882', 'com.waken.dorm.controller.auth.LoginController.login()', '  queryUserForm: QueryUserForm(userName=许峻, password=123456)  request: org.apache.shiro.web.servlet.ShiroHttpServletRequest@61673587', '127.0.0.1', '2019-08-15 23:41:07', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('5715648dde10436e8c750fc46a918a9b', null, '用户登录', '675', 'com.waken.dorm.controller.auth.LoginController.login()', '  queryUserForm: QueryUserForm(userName=aishu, password=123456)  request: org.apache.shiro.web.servlet.ShiroHttpServletRequest@732ec71f', '127.0.0.1', '2019-08-18 10:45:48', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('575cb98b6bf646daab51c395f096478b', null, '保存或修改资源', '68', 'com.waken.dorm.controller.resource.ResourceController.saveResource()', '  editResourceForm: EditResourceForm(pkResourceId=null, resourceName=用户查看, resourceIcon=null, resourceType=2, resourceUrl=null, parentId=93a2394ccf044c709ea660569b91d5ea, perms=users:view)', '127.0.0.1', '2019-08-21 22:36:50', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('582a79e970bf462697fea8de97a8c6cb', null, '保存或修改资源', '527', 'com.waken.dorm.controller.resource.ResourceController.saveResource()', '  editResourceForm: EditResourceForm(pkResourceId=null, resourceName=测出猜测, resourceIcon=null, resourceType=1, resourceUrl=null, parentId=null, perms=menu)', '127.0.0.1', '2019-08-21 22:41:45', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('593e2662a93642a39d9aba0b7c073521', null, '用户登录', '8353', 'com.waken.dorm.controller.auth.LoginController.login()', '  queryUserForm: QueryUserForm(userName=aishu, password=aishu)  request: org.apache.shiro.web.servlet.ShiroHttpServletRequest@38f704ae', '127.0.0.1', '2019-08-21 17:25:40', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('59477c767f294cd6935722acbba4da70', null, '保存或修改角色', '69', 'com.waken.dorm.controller.role.RoleController.saveRole()', '  editRoleForm: EditRoleForm(pkRoleId=null, roleName=admin, roleDesc=管理员)', '127.0.0.1', '2019-08-21 09:32:37', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('5b9be7f556a04d4bae197a43fe0cc945', null, '批量添加用户资源关联', '254484', 'com.waken.dorm.controller.user.UserController.batchAddUserRoleRel()', '  addForm: AddUserResourcesForm(userId=string, resourceIds=[ea71d0b7204c4bc7ab1a3455c77eb85e, 93a2394ccf044c709ea660569b91d5ea])', '127.0.0.1', '2019-08-21 16:05:49', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('5c430a5191ab457f9d116729d86a0fb8', null, '用户登录', '1159', 'com.waken.dorm.controller.auth.LoginController.login()', '  queryUserForm: QueryUserForm(userName=aishu, password=123456)  request: org.apache.shiro.web.servlet.ShiroHttpServletRequest@48aa11bc', '127.0.0.1', '2019-08-17 12:24:41', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('5c5e1adf23744b1d96e53119ff19cde6', null, '用户登录', '234', 'com.waken.dorm.controller.user.UserController.login()', '  queryUserForm: QueryUserForm(userName=许峻, password=123456)', '127.0.0.1', '2019-08-12 12:51:13', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('5c8e61a4105e4f59824968eb54fcd245', 'af7cae9f7b87426ab1572d431c56253d', '(保存/修改)字典信息', '276', 'com.waken.dorm.controller.dict.DictController.saveDict()', 'editDictForm: EditDictForm{pkDictId=\'null\', schoolId=\'4ddecf810f15405e8b4665a85f273172\', dictKey=\'1\', dictValue=\'生效\', columnName=\'status\', columnDesc=\'状态\', tableName=\'rm_dorm\', tableDesc=\'宿舍表\'}', '127.0.0.1', '2019-04-19 10:05:17', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('5fa5bfa08e1c4b5cbdd11b7341686426', 'af7cae9f7b87426ab1572d431c56253d', '用户登录', '124', 'com.waken.dorm.controller.user.UserController.login()', 'queryUserForm: QueryUserForm{userName=\'许峻\', password=\'123456\'}', '127.0.0.1', '2019-04-19 09:17:08', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('632c479057244761bc2b76248abe02fb', 'af7cae9f7b87426ab1572d431c56253d', '删除资源', '53664', 'com.waken.dorm.controller.resource.ResourceController.deleteResource()', 'deleteFrom: DeleteForm{delIds=[e8a45231e88345d7850e2ba439a7e4dc, 0e2fa4ad02324ec68ce294ae454cb338, 28600d66f2ed49749c9d299fe4f2defb, e62dd535125e41e9bf4ef257c8c9a376], delStatus=0}', '127.0.0.1', '2019-05-05 07:20:08', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('63413c755e8846c386362ccd35abed57', null, '用户登录', '37', 'com.waken.dorm.controller.user.UserController.login()', '  queryUserForm: QueryUserForm(userName=许峻, password=123456)', '127.0.0.1', '2019-08-05 16:01:09', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('63fc349e80fc4d748d438f98f59c579d', null, '导出用户信息', '25214', 'com.waken.dorm.controller.user.UserController.export()', '  response: com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@5b0ecffc', '127.0.0.1', '2019-08-17 22:39:31', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('642ad38e4234447aa9913431e9b52b88', null, '用户登录', '3156', 'com.waken.dorm.controller.auth.LoginController.login()', '  queryUserForm: QueryUserForm(userName=aishu, password=123456)  request: org.apache.shiro.web.servlet.ShiroHttpServletRequest@49c5e108', '127.0.0.1', '2019-08-15 22:14:13', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('6719a68eadd645cb95aa12f0adf9ce94', null, '保存或修改资源', '130', 'com.waken.dorm.controller.resource.ResourceController.saveResource()', '  editResourceForm: EditResourceForm(pkResourceId=null, resourceName=学校保存, resourceIcon=null, resourceType=2, resourceUrl=null, parentId=3808f9ac86884504af5827963986248f, perms=schools:save)', '127.0.0.1', '2019-08-21 17:55:31', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('672d91cb869f491fbfd5f1beb41bbb06', null, '用户登录', '12434', 'com.waken.dorm.controller.auth.LoginController.login()', '  queryUserForm: QueryUserForm(userName=aishu, password=123456)  request: org.apache.shiro.web.servlet.ShiroHttpServletRequest@3e3f110', '127.0.0.1', '2019-08-16 19:54:32', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('6a5eb7dbb4a64b0b893a4a15548a14bc', null, '用户登录', '2134', 'com.waken.dorm.controller.auth.LoginController.login()', '  queryUserForm: QueryUserForm(userName=aishu, password=123456)  request: org.apache.shiro.web.servlet.ShiroHttpServletRequest@3609b602', '127.0.0.1', '2019-08-15 23:39:23', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('6aeda3eb57514da09949591fed82575f', 'af7cae9f7b87426ab1572d431c56253d', '(保存/修改)字典信息', '12', 'com.waken.dorm.controller.dict.DictController.saveDict()', 'editDictForm: EditDictForm{pkDictId=\'null\', schoolId=\'4ddecf810f15405e8b4665a85f273172\', key=\'1\', value=\'生效\', columnName=\'status\', columnDesc=\'状态\', tableName=\'rm_dorm\', tableDesc=\'宿舍表\'}', '127.0.0.1', '2019-04-19 09:18:00', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('6dc747ade1534b7aac72cfdee620889c', 'af7cae9f7b87426ab1572d431c56253d', '批量导入宿舍评分记录（excel）', '305721', 'com.waken.dorm.controller.dorm.DormScoreController.batchAddDormScore()', 'file: \"org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile@cefac03\"', '127.0.0.1', '2019-04-18 05:00:19', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('6f42bb37456b4491934584260e0e6521', null, '保存或修改用户', '189', 'com.waken.dorm.controller.user.UserController.saveUser()', '  userForm: EditUserForm(userId=null, userName=aishu, password=123456, name=zhaorong, mobile=15328417106, email=2181250231@qq.com, gender=0, userType=1)', '127.0.0.1', '2019-08-15 13:28:12', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('6f4f4f43052440aebac655a48e0351c9', null, '用户登录', '631', 'com.waken.dorm.controller.auth.LoginController.login()', '  queryUserForm: QueryUserForm(userName=aishu, password=123456)  request: org.apache.shiro.web.servlet.ShiroHttpServletRequest@121a0b82', '127.0.0.1', '2019-08-16 19:51:18', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('72977cba5d8c40fe95c42bc353fe7f23', null, '导出用户信息', '794', 'com.waken.dorm.controller.user.UserController.export()', '  response: com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@7d771f2f', '127.0.0.1', '2019-08-21 23:00:39', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('73e803fd88e64be199ccd7c7df15dc06', null, '用户登录', '661', 'com.waken.dorm.controller.user.UserController.login()', '  queryUserForm: QueryUserForm(userName=许峻, password=123456)', '127.0.0.1', '2019-08-06 13:44:24', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('74cec82baafd4638a29d63558caff511', 'af7cae9f7b87426ab1572d431c56253d', '批量导入宿舍评分记录（excel）', '796', 'com.waken.dorm.controller.dorm.DormScoreController.batchAddDormScore()', 'file: \"org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile@1a68ce18\"', '127.0.0.1', '2019-04-18 04:49:44', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('7572ec57126c4039a860596ea73c13a2', 'af7cae9f7b87426ab1572d431c56253d', '用户登录', '11', 'com.waken.dorm.controller.user.UserController.login()', '  queryUserForm: QueryUserForm(userName=许峻11, password=123456)', '127.0.0.1', '2019-06-10 08:44:44', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('77df155682ed4245938a833780cbc453', null, '用户登录', '1231', 'com.waken.dorm.controller.auth.LoginController.login()', '  queryUserForm: QueryUserForm(userName=许峻, password=123456)  request: org.apache.shiro.web.servlet.ShiroHttpServletRequest@15cc8a3f', '127.0.0.1', '2019-08-18 22:43:50', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('7850ad7f0e70435face34d78fde1ebbf', null, '用户登录', '309', 'com.waken.dorm.controller.auth.LoginController.login()', '  queryUserForm: QueryUserForm(userName=aishu, password=aishu)  request: org.apache.shiro.web.servlet.ShiroHttpServletRequest@2e07228e', '127.0.0.1', '2019-08-21 23:00:27', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('7a086938106749feaf92725c24f71330', null, '保存或修改资源', '4645', 'com.waken.dorm.controller.resource.ResourceController.saveResource()', '  editResourceForm: EditResourceForm(pkResourceId=null, resourceName=string, resourceIcon=string, resourceType=2, resourceUrl=string, parentId=null, perms=string)', '127.0.0.1', '2019-08-21 09:54:38', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('7be5c264ba7744cb878161bef01d75af', null, '用户登录', '1006', 'com.waken.dorm.controller.auth.LoginController.login()', '  queryUserForm: QueryUserForm(userName=aishu, password=aishu)  request: org.apache.shiro.web.servlet.ShiroHttpServletRequest@2c3d4072', '127.0.0.1', '2019-08-21 18:38:43', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('7d92bd715fd8411698478e0972886c67', null, '用户登录', '686', 'com.waken.dorm.controller.auth.LoginController.login()', '  queryUserForm: QueryUserForm(userName=aishu, password=123456)  request: org.apache.shiro.web.servlet.ShiroHttpServletRequest@782b8bb0', '127.0.0.1', '2019-08-17 09:43:36', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('7e51fb5e62a14081b83b977e9c98a28c', null, '用户登录', '538', 'com.waken.dorm.controller.auth.LoginController.login()', '  queryUserForm: QueryUserForm(userName=许峻, password=123456)  request: org.apache.shiro.web.servlet.ShiroHttpServletRequest@69130447', '127.0.0.1', '2019-08-16 22:50:08', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('81aacaa57ee54b98871473dded4a173a', null, '保存或修改资源', '91', 'com.waken.dorm.controller.resource.ResourceController.saveResource()', '  editResourceForm: EditResourceForm(pkResourceId=null, resourceName=用户绑定资源, resourceIcon=null, resourceType=2, resourceUrl=null, parentId=93a2394ccf044c709ea660569b91d5ea, perms=users::resources)', '127.0.0.1', '2019-08-21 10:59:31', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('83a7033536894137a83786abc12ecd38', null, '用户登录', '636', 'com.waken.dorm.controller.auth.LoginController.login()', '  queryUserForm: QueryUserForm(userName=许峻, password=123456)  request: org.apache.shiro.web.servlet.ShiroHttpServletRequest@436a650e', '127.0.0.1', '2019-08-18 00:07:02', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('85f037f5311b4ac496c1baeac3e8ef81', null, '用户登录', '290', 'com.waken.dorm.controller.user.UserController.login()', '  queryUserForm: QueryUserForm(userName=zhou, password=123456)', '127.0.0.1', '2019-08-11 15:08:39', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('860fee48537847a3a00119b7dbb3e45b', null, '批量添加宿舍学生关联', '24714', 'com.waken.dorm.controller.dorm.DormController.batchAddDormStudentRel()', '  addForm: AddDormStudentRelForm(dormId=null, studentIds=null)', '127.0.0.1', '2019-08-20 11:14:22', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('871881216d2f4500a0906c33e3114f60', null, '保存或修改资源', '127', 'com.waken.dorm.controller.resource.ResourceController.saveResource()', '  editResourceForm: EditResourceForm(pkResourceId=null, resourceName=学校宿舍孩纸2, resourceIcon=null, resourceType=1, resourceUrl=null, parentId=ff48acc71ba5457d81e20bb668e2a1f9, perms=null)', '127.0.0.1', '2019-08-21 17:57:12', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('8734b2267cf449f887789c82f6c14cd7', 'af7cae9f7b87426ab1572d431c56253d', '(保存/修改)字典信息', '114182', 'com.waken.dorm.controller.dict.DictController.saveDict()', 'editDictForm: EditDictForm{pkDictId=\'null\', schoolId=\'4ddecf810f15405e8b4665a85f273172\', key=\'1\', value=\'生效\', columnName=\'status\', columnDesc=\'状态\', tableName=\'rm_dorm\', tableDesc=\'宿舍表\'}', '127.0.0.1', '2019-04-19 09:20:55', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('896ebe6acd9e4561bd84b2458492aee0', null, '用户登录', '492', 'com.waken.dorm.controller.auth.LoginController.login()', '  queryUserForm: QueryUserForm(userName=许峻, password=123456)  request: org.apache.shiro.web.servlet.ShiroHttpServletRequest@31d78142', '127.0.0.1', '2019-08-17 00:02:29', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('8df4bc3dd4644a88a801a9db06975dca', null, '用户登录', '1147', 'com.waken.dorm.controller.auth.LoginController.login()', '  queryUserForm: QueryUserForm(userName=aishu, password=123456)  request: org.apache.shiro.web.servlet.ShiroHttpServletRequest@1db917d0', '127.0.0.1', '2019-08-17 22:33:58', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('8ea6e98e46d444858818d910b913df74', null, '用户登录', '11062', 'com.waken.dorm.controller.auth.LoginController.login()', '  queryUserForm: QueryUserForm(userName=许峻, password=123456)  request: org.apache.shiro.web.servlet.ShiroHttpServletRequest@69ec2530', '127.0.0.1', '2019-08-15 13:04:34', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('8ecfa6f3bfac4eaab1b6a182da60e95d', null, '用户登录', '334042', 'com.waken.dorm.controller.auth.LoginController.login()', '  queryUserForm: QueryUserForm(userName=aishu, password=aishu)  request: org.apache.shiro.web.servlet.ShiroHttpServletRequest@705516a8', '127.0.0.1', '2019-08-21 09:27:15', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('8f5673a3b7c74e018d84a020b7c98c2c', null, '保存或修改资源', '350', 'com.waken.dorm.controller.resource.ResourceController.saveResource()', '  editResourceForm: EditResourceForm(pkResourceId=null, resourceName=系统管理, resourceIcon=icon, resourceType=1, resourceUrl=, parentId=null, perms=Anon)', '127.0.0.1', '2019-08-21 10:11:24', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('8f7c04c6bdf04cf4987e91e3c8a59d34', 'af7cae9f7b87426ab1572d431c56253d', '删除资源', '185788', 'com.waken.dorm.controller.resource.ResourceController.deleteResource()', 'deleteFrom: DeleteForm{delIds=[e8a45231e88345d7850e2ba439a7e4dc, 0e2fa4ad02324ec68ce294ae454cb338, 28600d66f2ed49749c9d299fe4f2defb, e62dd535125e41e9bf4ef257c8c9a376], delStatus=0}', '127.0.0.1', '2019-05-05 07:23:29', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('91f591672b2c4063b34e921aaa353e6d', null, '保存或修改资源', '73', 'com.waken.dorm.controller.resource.ResourceController.saveResource()', '  editResourceForm: EditResourceForm(pkResourceId=null, resourceName=用户绑定单个角色, resourceIcon=null, resourceType=2, resourceUrl=null, parentId=93a2394ccf044c709ea660569b91d5ea, perms=users::role)', '127.0.0.1', '2019-08-21 11:00:18', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('925920227e1a4e35a4ad57c64bd8ff75', null, '批量添加用户资源关联', '301', 'com.waken.dorm.controller.user.UserController.batchAddUserRoleRel()', '  addForm: AddUserResourcesForm(userId=a0f5e121974d4adaba055a1602ce42b4, resourceIds=[93a2394ccf044c709ea660569b91d5ea, 347de92f327c40e290b1f33f8206266d, 49240f0695b44453b3cdccf7e2167188, 6377b4d5ae084b6086a7b224488ee273, 68cd6eb3e1264628a25052f9f8f24dba, ea71d0b7204c4bc7ab1a3455c77eb85e])', '127.0.0.1', '2019-08-21 11:44:17', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('943c6b553d514ab2906ca74311ea040e', 'af7cae9f7b87426ab1572d431c56253d', '(保存/修改)学校类别信息', '159', 'com.waken.dorm.controller.school.SchoolClassController.saveSchoolClass()', 'editSchoolClassForm: EditSchoolClassForm{pkSchoolClassId=\'c2866c8e92074d38a3d4821d509a42a1\', schoolId=\'4ddecf810f15405e8b4665a85f273172\', parentId=\'null\', className=\'2016\', classDesc=\'2016\', memo=\'null\'}', '127.0.0.1', '2019-04-17 06:29:19', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('95a54f4a37e9477cbe315a05a9549ff6', 'af7cae9f7b87426ab1572d431c56253d', '用户登录', '157', 'com.waken.dorm.controller.user.UserController.login()', '  queryUserForm: QueryUserForm(userName=许峻, password=123456)', '127.0.0.1', '2019-05-09 10:26:24', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('95c1fdf040fb4393aa794ede0c5598e5', 'af7cae9f7b87426ab1572d431c56253d', '(保存/修改)字典信息', '38670', 'com.waken.dorm.controller.dict.DictController.saveDict()', 'editDictForm: EditDictForm{pkDictId=\'null\', schoolId=\'4ddecf810f15405e8b4665a85f273172\', key=\'1\', value=\'生效\', columnName=\'status\', columnDesc=\'状态\', tableName=\'rm_dorm\', tableDesc=\'宿舍表\'}', '127.0.0.1', '2019-04-19 09:25:01', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('962a2fa0ca7c42bb91f7f8f6e73b9faa', null, '导出用户信息', '1771', 'com.waken.dorm.controller.user.UserController.export()', '  response: com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@7464fab9', '127.0.0.1', '2019-08-17 22:42:21', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('98fcdf196bdc414dbd8899f777ef9f81', 'af7cae9f7b87426ab1572d431c56253d', '删除资源', '222389', 'com.waken.dorm.controller.resource.ResourceController.deleteResource()', 'deleteFrom: DeleteForm{delIds=[e8a45231e88345d7850e2ba439a7e4dc, 0e2fa4ad02324ec68ce294ae454cb338, 28600d66f2ed49749c9d299fe4f2defb, e62dd535125e41e9bf4ef257c8c9a376], delStatus=0}', '127.0.0.1', '2019-05-05 07:18:20', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('999073d75e38422ba589764f4319bc6d', null, '保存或修改资源', '542', 'com.waken.dorm.controller.resource.ResourceController.saveResource()', '  editResourceForm: EditResourceForm(pkResourceId=null, resourceName=角色查看, resourceIcon=null, resourceType=2, resourceUrl=null, parentId=ea71d0b7204c4bc7ab1a3455c77eb85e, perms=roles:view)', '127.0.0.1', '2019-08-21 22:36:03', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('9a71b7373f1c423d86a441abf77e19ab', 'af7cae9f7b87426ab1572d431c56253d', '(保存/修改)字典信息', '4724', 'com.waken.dorm.controller.dict.DictController.saveDict()', 'editDictForm: EditDictForm{pkDictId=\'null\', schoolId=\'4ddecf810f15405e8b4665a85f273172\', key=\'1\', value=\'生效\', columnName=\'status\', columnDesc=\'状态\', tableName=\'rm_dorm\', tableDesc=\'宿舍表\'}', '127.0.0.1', '2019-04-19 09:42:50', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('9b6070183e14459f9b28b06d30379b04', 'af7cae9f7b87426ab1572d431c56253d', '(保存/修改)字典信息', '99328', 'com.waken.dorm.controller.dict.DictController.saveDict()', 'editDictForm: EditDictForm{pkDictId=\'null\', schoolId=\'4ddecf810f15405e8b4665a85f273172\', key=\'1\', value=\'生效\', columnName=\'status\', columnDesc=\'状态\', tableName=\'rm_dorm\', tableDesc=\'宿舍表\'}', '127.0.0.1', '2019-04-19 09:38:04', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('9b9154f809d34b01b94d05ded6ac6377', null, '用户登录', '1564', 'com.waken.dorm.controller.auth.LoginController.login()', '  queryUserForm: QueryUserForm(userName=aishu, password=123456)  request: org.apache.shiro.web.servlet.ShiroHttpServletRequest@57dec659', '127.0.0.1', '2019-08-17 23:47:47', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('9def3bc304964a61ab48fb80ed51e3d1', null, '用户登录', '467', 'com.waken.dorm.controller.auth.LoginController.login()', '  queryUserForm: QueryUserForm(userName=aishu, password=123456)  request: org.apache.shiro.web.servlet.ShiroHttpServletRequest@23f9252b', '127.0.0.1', '2019-08-16 20:35:05', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('a093ee203c604dff9fba3d0a062a5c1b', 'af7cae9f7b87426ab1572d431c56253d', '批量导入宿舍评分记录（excel）', '1157', 'com.waken.dorm.controller.dorm.DormScoreController.batchAddDormScore()', 'file: \"org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile@197a1e1c\"', '127.0.0.1', '2019-04-18 05:01:01', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('a46d8dac843b4ee4920b449c61a6f363', null, '用户登录', '28', 'com.waken.dorm.controller.user.UserController.login()', '  queryUserForm: QueryUserForm(userName=许峻, password=123456)  host: \"[localhost:8080]\"  user-agent: \"[Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:68.0) Gecko/20100101 Firefox/68.0]\"  accept: \"[*/*]\"  accept-language: \"[zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2]\"  accept-encoding: \"[gzip, deflate]\"  referer: \"[http://localhost:8080/swagger-ui.html]\"  content-type: \"[application/json]\"  content-length: \"[50]\"  connection: \"[keep-alive]\"  cookie: \"[pgv_pvi=6184981504; Hm_lvt_8d87b104337ffe716311a5b6e78efd28=1557037562,1557127465; style=null; UM_distinctid=16a86ac83bf449-01e20f1a2a441e8-4c312c7c-e1000-16a86ac83c00; CNZZDATA1277354711=890341012-1557037402-null%7C1557037402; CNZZDATA30088308=cnzz_eid%3D726125434-1562675321-%26ntime%3D1562675321; JSESSIONID=4207ba20-f790-4f80-b225-16b557348cae]\"  userToken: \"[4207ba20-f790-4f80-b225-16b557348cae]\"', '127.0.0.1', '2019-08-05 16:03:30', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('a580d6a6500241d28eeba146d88b28c5', 'af7cae9f7b87426ab1572d431c56253d', '批量添加用户角色关联', '15', 'com.waken.dorm.controller.user.UserController.batchAddUserRoleRel()', '  addUserRoleRelForm: AddUserRoleRelForm(userId=null, roleIds=null)', '127.0.0.1', '2019-06-21 02:16:49', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('a5a760c8453e4268a0d0bb060f4a813a', 'af7cae9f7b87426ab1572d431c56253d', '删除学校类别信息', '19198', 'com.waken.dorm.controller.school.SchoolClassController.deleteSchoolClass()', 'deleteFrom: DeleteForm{delIds=[851558e5ef554079b540bc8b4ef8c03d, 1861e832380c42a18b8a1adfd0ee10f2, a98af597af5347089b841515140ad207, fca430170aa2477fae1b191fc926fe68, 04ea45d9b6a4483887e58d2bc2414aa6, 5807da8235314b0490952676b9abc144, 88b17f48fd89481dbdfae0c4ad1effe7, 9959cac621704942983fa0b859d4d70d, 12e461a8c5ee45f788be1f9e95e3bd8f, b73963a47f97429ea55e03da1ba043c2, c63749a5e52648f1addcf15fe71398b2, f09fad67b0f941128e9cabbcc9bd108c], delStatus=0}', '127.0.0.1', '2019-05-05 11:30:46', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('a7c60f3efbfb4549b5160ddbaa263509', 'af7cae9f7b87426ab1572d431c56253d', '删除资源', '18', 'com.waken.dorm.controller.resource.ResourceController.deleteResource()', 'deleteFrom: DeleteForm{delIds=[e8a45231e88345d7850e2ba439a7e4dc, 0e2fa4ad02324ec68ce294ae454cb338, 28600d66f2ed49749c9d299fe4f2defb, e62dd535125e41e9bf4ef257c8c9a376], delStatus=0}', '127.0.0.1', '2019-05-05 07:09:30', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('a8a6d48a4d624e9188d8019a88a80219', 'af7cae9f7b87426ab1572d431c56253d', '删除资源', '42228', 'com.waken.dorm.controller.resource.ResourceController.deleteResource()', 'deleteFrom: DeleteForm{delIds=[e8a45231e88345d7850e2ba439a7e4dc, 0e2fa4ad02324ec68ce294ae454cb338, 28600d66f2ed49749c9d299fe4f2defb, e62dd535125e41e9bf4ef257c8c9a376], delStatus=0}', '127.0.0.1', '2019-05-05 07:14:35', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('a93b050e984546eba240a9361215cebe', null, '用户登录', '27618', 'com.waken.dorm.controller.auth.LoginController.login()', '  queryUserForm: QueryUserForm(userName=aishu, password=123456)  request: org.apache.shiro.web.servlet.ShiroHttpServletRequest@423e52b3', '127.0.0.1', '2019-08-15 23:22:09', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('aae105f7ebef40a6a3153caabf9ab1fa', null, '批量导入宿舍评分记录（excel）', '28043', 'com.waken.dorm.controller.dorm.DormScoreController.batchImportScore()', '  file: \"org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile@1b2786ed\"', '127.0.0.1', '2019-08-18 10:47:11', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('ac22298907fc4b5e94cfd1d5fcc8acfe', null, '保存或修改资源', '138', 'com.waken.dorm.controller.resource.ResourceController.saveResource()', '  editResourceForm: EditResourceForm(pkResourceId=null, resourceName=角色管理, resourceIcon=null, resourceType=1, resourceUrl=/dorm/role.html, parentId=e719a109a7014484901f657f7f3a8c3b, perms=roles::view)', '127.0.0.1', '2019-08-21 10:38:47', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('acb399aa0e9f4d59b6e2554460c5927b', null, '保存或修改资源', '84', 'com.waken.dorm.controller.resource.ResourceController.saveResource()', '  editResourceForm: EditResourceForm(pkResourceId=null, resourceName=资源保存, resourceIcon=null, resourceType=2, resourceUrl=null, parentId=3939e23ff7a7481c9d36d3ebf1baf08c, perms=resources::save)', '127.0.0.1', '2019-08-21 11:01:26', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('acd256142bd84c43ba17afbdbca13172', null, '用户登录', '23093', 'com.waken.dorm.controller.user.UserController.login()', '  queryUserForm: QueryUserForm(userName=许峻, password=123456)', '127.0.0.1', '2019-08-13 13:58:43', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('ad7cc8bd65f043ecb3d7dc13a9a1eeb1', null, '保存或修改资源', '118', 'com.waken.dorm.controller.resource.ResourceController.saveResource()', '  editResourceForm: EditResourceForm(pkResourceId=null, resourceName=资源管理, resourceIcon=null, resourceType=1, resourceUrl=/dorm/resource.html, parentId=e719a109a7014484901f657f7f3a8c3b, perms=resources::view)', '127.0.0.1', '2019-08-21 10:38:06', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('aec003af74ec4337bd27f10eaefb0505', null, '用户登录', '9190', 'com.waken.dorm.controller.user.UserController.login()', '  queryUserForm: QueryUserForm(userName=许峻, password=123456)', '127.0.0.1', '2019-08-11 15:05:45', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('b1e407e181bc40ff8c5c40fa8aaea818', null, '用户登录', '836', 'com.waken.dorm.controller.auth.LoginController.login()', '  queryUserForm: QueryUserForm(userName=许峻, password=123456)  request: org.apache.shiro.web.servlet.ShiroHttpServletRequest@63ca62f6', '127.0.0.1', '2019-08-19 20:09:26', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('b1f7e021c20842108a4c3181f58b6011', null, '保存或修改资源', '824', 'com.waken.dorm.controller.resource.ResourceController.saveResource()', '  editResourceForm: EditResourceForm(pkResourceId=null, resourceName=角色绑定资源, resourceIcon=null, resourceType=2, resourceUrl=null, parentId=ea71d0b7204c4bc7ab1a3455c77eb85e, perms=roles::resources)', '127.0.0.1', '2019-08-21 10:52:10', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('b2b526ce2dba4ccb9f08ceb7ec8f48be', null, '用户登录', '8969', 'com.waken.dorm.controller.auth.LoginController.login()', '  queryUserForm: QueryUserForm(userName=aishu, password=aishu)  request: org.apache.shiro.web.servlet.ShiroHttpServletRequest@7bb5518a', '127.0.0.1', '2019-08-21 10:36:34', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('b342282a9388417eb545367452a091af', null, '删除用户信息', '12954', 'com.waken.dorm.controller.user.UserController.deleteUser()', '  deleteFrom: DeleteForm(delIds=[7463756b77b34cfa97d8edf9893616a6], delStatus=0)', '127.0.0.1', '2019-08-17 23:30:41', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('b3b1bbc9826a474ca8ec6f44d7710ee7', null, '用户登录', '550', 'com.waken.dorm.controller.auth.LoginController.login()', '  queryUserForm: QueryUserForm(userName=许峻, password=123456)  request: org.apache.shiro.web.servlet.ShiroHttpServletRequest@7d491208', '127.0.0.1', '2019-08-19 22:23:53', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('b44c97b61fd84236831e87d9c30710e1', null, '用户登录', '17', 'com.waken.dorm.controller.user.UserController.login()', '  queryUserForm: QueryUserForm(userName=许峻, password=123456)', '127.0.0.1', '2019-08-11 15:08:50', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('b52f649b6517434dae043391d27e19ab', 'af7cae9f7b87426ab1572d431c56253d', '批量添加用户角色关联', '2', 'com.waken.dorm.controller.user.UserController.batchAddUserRoleRel()', '  addUserRoleRelForm: AddUserRoleRelForm(userId=string, roleIds=[string])', '127.0.0.1', '2019-06-10 09:49:03', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('b60c80c1af7e48188c369436d9f3acbc', null, '导出用户信息', '136', 'com.waken.dorm.controller.user.UserController.export()', '  response: com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@7c0319ed', '127.0.0.1', '2019-08-17 22:58:01', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('b66ae20752874a9aac3618d6a06af168', null, '导出用户信息', '136', 'com.waken.dorm.controller.user.UserController.export()', '  response: com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@678b95', '127.0.0.1', '2019-08-17 23:08:49', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('b7b881f3ecc9472f97c8a40528bc4c0a', null, '用户登录', '574', 'com.waken.dorm.controller.user.UserController.login()', '  queryUserForm: QueryUserForm(userName=许峻, password=123456)', '127.0.0.1', '2019-08-11 15:10:42', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('b7d9f84b21934f2483ddbe1691ff7e8b', null, '保存或修改用户', '516', 'com.waken.dorm.controller.user.UserController.saveUser()', '  userForm: EditUserForm(userId=null, userName=aishu, password=123456, name=zhaorong, mobile=15328417106, email=2181250231@qq.com, gender=0, userType=1)', '127.0.0.1', '2019-08-15 13:33:48', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('b8279e02615f4a19af0ee3a9a44362f4', null, '保存或修改资源', '123', 'com.waken.dorm.controller.resource.ResourceController.saveResource()', '  editResourceForm: EditResourceForm(pkResourceId=null, resourceName=用户管理, resourceIcon=null, resourceType=1, resourceUrl=/dorm/user.html, parentId=e719a109a7014484901f657f7f3a8c3b, perms=users::view)', '127.0.0.1', '2019-08-21 10:37:14', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('b8721e801b384813aec1c42764ec74ce', null, '用户登录', '1227', 'com.waken.dorm.controller.auth.LoginController.login()', '  queryUserForm: QueryUserForm(userName=aishu, password=aishu)  request: org.apache.shiro.web.servlet.ShiroHttpServletRequest@3fe236c3', '127.0.0.1', '2019-08-21 15:56:20', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('b8b6670e372643e88c662034ffb079b3', null, '保存或修改资源', '119', 'com.waken.dorm.controller.resource.ResourceController.saveResource()', '  editResourceForm: EditResourceForm(pkResourceId=null, resourceName=学校宿舍孩纸3, resourceIcon=null, resourceType=1, resourceUrl=null, parentId=ff48acc71ba5457d81e20bb668e2a1f9, perms=null)', '127.0.0.1', '2019-08-21 17:57:23', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('bbbc405679b7435289dc5c61af39f7cb', null, '保存或修改资源', '600', 'com.waken.dorm.controller.resource.ResourceController.saveResource()', '  editResourceForm: EditResourceForm(pkResourceId=null, resourceName=系统管理, resourceIcon=icon, resourceType=1, resourceUrl=null, parentId=null, perms=Anon)', '127.0.0.1', '2019-08-21 10:20:19', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('bd44cfec32d4459ca49f39a14d8b9eae', null, '导出用户信息', '1897', 'com.waken.dorm.controller.user.UserController.export()', '  response: com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@358c4460', '127.0.0.1', '2019-08-17 23:14:00', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('c00a03b11cea45449ea1a5eb56ce3c6f', null, '保存或修改用户', '213', 'com.waken.dorm.controller.user.UserController.saveUser()', '  userForm: EditUserForm(userId=null, userName=xiaozhu, password=xiaozhu, name=xiaozhu, mobile=15328417109, email=2181250234@qq.com, gender=0, userType=1)', '127.0.0.1', '2019-08-21 11:24:21', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('c0b92f699ebd484a8d6e185fad7799a7', 'af7cae9f7b87426ab1572d431c56253d', '用户登录', '89877', 'com.waken.dorm.controller.user.UserController.login()', '  queryUserForm: QueryUserForm(userName=许峻, password=123456)', '127.0.0.1', '2019-07-07 11:40:12', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('c252fb028e4e4b54992fe85e7627977c', null, '用户登录', '10368', 'com.waken.dorm.controller.auth.LoginController.login()', '  queryUserForm: QueryUserForm(userName=aishu, password=123456)  request: org.apache.shiro.web.servlet.ShiroHttpServletRequest@78eee15b', '127.0.0.1', '2019-08-16 20:09:09', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('c26848beca11424ebefb0b911035f64c', null, '保存或修改用户', '112', 'com.waken.dorm.controller.user.UserController.saveUser()', '  userForm: EditUserForm(userId=null, userName=aishu, password=123456, name=zhaorong, mobile=15328417106, email=2181250231, gender=0, userType=1)', '127.0.0.1', '2019-08-15 13:27:55', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('c3d6448fb653413c9c86c90989775bf3', 'af7cae9f7b87426ab1572d431c56253d', '(保存/修改)字典信息', '7', 'com.waken.dorm.controller.dict.DictController.saveDict()', 'editDictForm: EditDictForm{pkDictId=\'null\', schoolId=\'4ddecf810f15405e8b4665a85f273172\', key=\'1\', value=\'生效\', columnName=\'status\', columnDesc=\'状态\', tableName=\'rm_dorm\', tableDesc=\'宿舍表\'}', '127.0.0.1', '2019-04-19 09:24:01', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('c709617064134f91b1e052f5dbda3fe9', null, '保存或修改资源', '101', 'com.waken.dorm.controller.resource.ResourceController.saveResource()', '  editResourceForm: EditResourceForm(pkResourceId=null, resourceName=删除角色, resourceIcon=null, resourceType=2, resourceUrl=null, parentId=ea71d0b7204c4bc7ab1a3455c77eb85e, perms=roles::delete)', '127.0.0.1', '2019-08-21 10:44:42', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('c71695c358264f0ca5fca40a53ea2b40', null, '保存或修改资源', '76', 'com.waken.dorm.controller.resource.ResourceController.saveResource()', '  editResourceForm: EditResourceForm(pkResourceId=null, resourceName=资源查看, resourceIcon=null, resourceType=2, resourceUrl=null, parentId=3939e23ff7a7481c9d36d3ebf1baf08c, perms=resources:view)', '127.0.0.1', '2019-08-21 22:37:25', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('ca6504f911c647018c2cd46945d33e87', null, '导出用户信息', '1658', 'com.waken.dorm.controller.user.UserController.export()', '  response: com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@6b0a13a', '127.0.0.1', '2019-08-17 22:34:15', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('cab93687141b407db50aa161590f1e31', 'af7cae9f7b87426ab1572d431c56253d', '批量添加宿舍学生关联', '15', 'com.waken.dorm.controller.dorm.DormController.batchAddDormStudentRel()', '  addDormStudentRelForm: AddDormStudentRelForm(pkDormId=null, pkStudentIds=null)', '127.0.0.1', '2019-06-21 02:17:24', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('cafb514de8f1436cbcb3535bffeca399', 'af7cae9f7b87426ab1572d431c56253d', '批量导入宿舍评分记录（excel）', '565', 'com.waken.dorm.controller.dorm.DormScoreController.batchAddDormScore()', 'file: \"org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile@2e7654fb\"', '127.0.0.1', '2019-04-18 04:47:57', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('cc55407e0c5044a9aeac43e49386c02e', null, '用户登录', '202828', 'com.waken.dorm.controller.auth.LoginController.login()', '  queryUserForm: QueryUserForm(userName=许峻, password=123456)  request: org.apache.shiro.web.servlet.ShiroHttpServletRequest@1324808b', '127.0.0.1', '2019-08-16 22:57:03', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('ceb4a18a121f4ac6b4209b93674fcd0a', 'af7cae9f7b87426ab1572d431c56253d', '保存或修改用户', '30', 'com.waken.dorm.controller.user.UserController.saveUser()', '  userForm: EditUserForm(userId=string, userName=string, password=string, name=string, mobile=string, email=string, gender=0, userType=1)', '127.0.0.1', '2019-07-07 11:41:30', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('d2e83b20ffc744078f867de85cf6c73c', null, '保存或修改资源', '862', 'com.waken.dorm.controller.resource.ResourceController.saveResource()', '  editResourceForm: EditResourceForm(pkResourceId=null, resourceName=学校管理, resourceIcon=null, resourceType=1, resourceUrl=null, parentId=null, perms=null)', '127.0.0.1', '2019-08-21 17:54:16', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('d4dd43b66061426d9d6d30a377abfb79', 'af7cae9f7b87426ab1572d431c56253d', '(保存/修改)字典信息', '91', 'com.waken.dorm.controller.dict.DictController.saveDict()', 'editDictForm: EditDictForm{pkDictId=\'null\', schoolId=\'4ddecf810f15405e8b4665a85f273172\', key=\'1\', value=\'生效\', columnName=\'status\', columnDesc=\'状态\', tableName=\'rm_dorm\', tableDesc=\'宿舍表\'}', '127.0.0.1', '2019-04-19 09:17:16', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('d6991f9a2cd44079a9769636e1cd9cc1', null, '用户登录', '691', 'com.waken.dorm.controller.auth.LoginController.login()', '  queryUserForm: QueryUserForm(userName=许峻, password=123456)  request: org.apache.shiro.web.servlet.ShiroHttpServletRequest@23f82b75', '127.0.0.1', '2019-08-17 00:10:03', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('d9f452d8f2234753960bfc7e2f7558a7', null, '批量添加宿舍学生关联', '12526', 'com.waken.dorm.controller.dorm.DormController.batchAddDormStudentRel()', '  addForm: AddDormStudentRelForm(dormId=null, studentIds=null)', '127.0.0.1', '2019-08-20 11:24:31', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('da7dcd3147a04861b81f41dfbd2cc890', 'af7cae9f7b87426ab1572d431c56253d', '用户登录', '179', 'com.waken.dorm.controller.user.UserController.login()', 'queryUserForm: QueryUserForm{userName=\'许峻\', password=\'123456\'}', '127.0.0.1', '2019-05-05 07:08:57', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('db072377b7874b1db9264861be877b8b', null, '用户登录', '17', 'com.waken.dorm.controller.user.UserController.login()', '  queryUserForm: QueryUserForm(userName=zhou, password=123456)', '127.0.0.1', '2019-08-11 15:06:05', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('db421cca26534f0ba3463c8aad7f5010', 'af7cae9f7b87426ab1572d431c56253d', '用户登录', '191', 'com.waken.dorm.controller.user.UserController.login()', '  queryUserForm: QueryUserForm(userName=许峻, password=123456)', '127.0.0.1', '2019-05-10 02:19:10', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('dc13d86c894244b1834012caf9edf7a1', null, '用户登录', '1505', 'com.waken.dorm.controller.auth.LoginController.login()', '  queryUserForm: QueryUserForm(userName=aishu, password=123456)  request: org.apache.shiro.web.servlet.ShiroHttpServletRequest@501b5301', '127.0.0.1', '2019-08-16 19:50:32', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('dcae55c9cd22427baa51de20cadb2e32', 'af7cae9f7b87426ab1572d431c56253d', '(保存/修改)学校类别信息', '337', 'com.waken.dorm.controller.school.SchoolClassController.saveSchoolClass()', 'editSchoolClassForm: EditSchoolClassForm{pkSchoolClassId=\'null\', schoolId=\'4ddecf810f15405e8b4665a85f273172\', parentId=\'null\', className=\'一班\', classDesc=\'一班\', memo=\'null\'}', '127.0.0.1', '2019-04-17 06:27:58', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('ddbb85a58f884a2d8e66da70456fde6b', 'af7cae9f7b87426ab1572d431c56253d', '删除资源', '149582', 'com.waken.dorm.controller.resource.ResourceController.deleteResource()', 'deleteFrom: DeleteForm{delIds=[e8a45231e88345d7850e2ba439a7e4dc, 0e2fa4ad02324ec68ce294ae454cb338, 28600d66f2ed49749c9d299fe4f2defb, e62dd535125e41e9bf4ef257c8c9a376], delStatus=0}', '127.0.0.1', '2019-05-05 07:13:33', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('ddee8a846ca648a99e78259ea6bcc77d', null, '批量添加宿舍学生关联', '5368', 'com.waken.dorm.controller.dorm.DormController.batchAddDormStudentRel()', '  addForm: AddDormStudentRelForm(dormId=null, studentIds=null)', '127.0.0.1', '2019-08-20 11:24:01', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('e1c0a7d920424a67bd24addeec1bbf7d', 'af7cae9f7b87426ab1572d431c56253d', '删除资源', '164159', 'com.waken.dorm.controller.resource.ResourceController.deleteResource()', 'deleteFrom: DeleteForm{delIds=[e8a45231e88345d7850e2ba439a7e4dc], delStatus=0}', '127.0.0.1', '2019-05-05 07:26:49', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('e21f4e725e80467398ef50c351f68a18', null, '用户登录', '757', 'com.waken.dorm.controller.auth.LoginController.login()', '  queryUserForm: QueryUserForm(userName=aishu, password=123456)  request: org.apache.shiro.web.servlet.ShiroHttpServletRequest@7d79ee67', '127.0.0.1', '2019-08-18 14:41:15', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('e26c3cd1be964d61945721e08447ec80', 'af7cae9f7b87426ab1572d431c56253d', '用户登录', '281', 'com.waken.dorm.controller.user.UserController.login()', '  queryUserForm: QueryUserForm(userName=许峻, password=123456)', '127.0.0.1', '2019-06-21 02:03:32', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('e298ba922d6943aca059f7ad7bc1f626', null, '用户登录', '48781', 'com.waken.dorm.controller.auth.LoginController.login()', '  queryUserForm: QueryUserForm(userName=许峻, password=123456)  request: org.apache.shiro.web.servlet.ShiroHttpServletRequest@6c8a06cb', '127.0.0.1', '2019-08-15 12:51:51', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('e2b7567a435547dda0f7faaa090f42f1', null, '用户登录', '17944', 'com.waken.dorm.controller.auth.LoginController.login()', '  queryUserForm: QueryUserForm(userName=aishu, password=123456)  request: org.apache.shiro.web.servlet.ShiroHttpServletRequest@102314c1', '127.0.0.1', '2019-08-15 13:35:38', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('e45fc6ebd40e4850b329a11bb836a955', null, '保存或修改资源', '80', 'com.waken.dorm.controller.resource.ResourceController.saveResource()', '  editResourceForm: EditResourceForm(pkResourceId=null, resourceName=用户绑定角色, resourceIcon=null, resourceType=2, resourceUrl=null, parentId=93a2394ccf044c709ea660569b91d5ea, perms=users::roles)', '127.0.0.1', '2019-08-21 10:59:09', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('e586f329d79f4f3a9e1f201b84d3ae96', null, '用户登录', '728', 'com.waken.dorm.controller.auth.LoginController.login()', '  queryUserForm: QueryUserForm(userName=aishu, password=123456)  request: org.apache.shiro.web.servlet.ShiroHttpServletRequest@ce21084', '127.0.0.1', '2019-08-20 19:46:33', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('eb2691c597044946915b428f699eeef1', null, '批量添加用户资源关联', '25649', 'com.waken.dorm.controller.user.UserController.batchAddUserRoleRel()', '  addForm: AddUserResourcesForm(userId=string, resourceIds=[93a2394ccf044c709ea660569b91d5ea])', '127.0.0.1', '2019-08-21 15:59:44', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('eb5a067e1ddc4b42aeb316fdd78cea52', null, '批量添加用户资源关联', '36167', 'com.waken.dorm.controller.user.UserController.batchAddUserRoleRel()', '  addForm: AddUserResourcesForm(userId=string, resourceIds=[ea71d0b7204c4bc7ab1a3455c77eb85e, 93a2394ccf044c709ea660569b91d5ea])', '127.0.0.1', '2019-08-21 16:50:11', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('ec826e4b119840508cc68fa2e2228199', null, '保存或修改资源', '70', 'com.waken.dorm.controller.resource.ResourceController.saveResource()', '  editResourceForm: EditResourceForm(pkResourceId=null, resourceName=用户删除, resourceIcon=null, resourceType=2, resourceUrl=null, parentId=93a2394ccf044c709ea660569b91d5ea, perms=users::delete)', '127.0.0.1', '2019-08-21 10:57:34', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('edc6a7c2cee248bfa421c3fbdd6ae837', null, '用户登录', '36740', 'com.waken.dorm.controller.auth.LoginController.login()', '  queryUserForm: QueryUserForm(userName=aishu, password=123456)  request: org.apache.shiro.web.servlet.ShiroHttpServletRequest@18b9eadf', '127.0.0.1', '2019-08-15 23:19:45', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('edd31dcaebd141b082fbaa8cbf67b67e', null, '保存或修改资源', '94', 'com.waken.dorm.controller.resource.ResourceController.saveResource()', '  editResourceForm: EditResourceForm(pkResourceId=null, resourceName=用户保存, resourceIcon=null, resourceType=2, resourceUrl=null, parentId=93a2394ccf044c709ea660569b91d5ea, perms=users::save)', '127.0.0.1', '2019-08-21 10:57:11', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('ee36ec5dfb3c486baf0bbb49a2bd4374', null, '保存或修改资源', '687', 'com.waken.dorm.controller.resource.ResourceController.saveResource()', '  editResourceForm: EditResourceForm(pkResourceId=null, resourceName=strin1g, resourceIcon=stri12ng, resourceType=1, resourceUrl=stri11ng, parentId=null, perms=Anon)', '127.0.0.1', '2019-08-21 10:04:41', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('eef0b86c03814388bd96216d1bb2bc60', null, '删除宿舍信息', '32177', 'com.waken.dorm.controller.dorm.DormController.deleteDorm()', '  deleteFrom: DeleteForm(delIds=null, delStatus=1)', '127.0.0.1', '2019-08-20 11:23:44', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('efa4fcd601a047b0a7f3f0b110ee8ab0', null, '批量添加用户资源关联', '48585', 'com.waken.dorm.controller.user.UserController.batchAddUserRoleRel()', '  addForm: AddUserResourcesForm(userId=string, resourceIds=[ea71d0b7204c4bc7ab1a3455c77eb85e, 93a2394ccf044c709ea660569b91d5ea])', '127.0.0.1', '2019-08-21 16:52:36', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('f205e3704de647ea8754c07c7da2b232', 'af7cae9f7b87426ab1572d431c56253d', '批量导入宿舍评分记录（excel）', '725', 'com.waken.dorm.controller.dorm.DormScoreController.batchAddDormScore()', 'file: \"org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile@4f889484\"', '127.0.0.1', '2019-04-18 04:52:46', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('f2dec75761604bd48ca325e93cb129d1', null, '用户登录', '69623', 'com.waken.dorm.controller.auth.LoginController.login()', '  queryUserForm: QueryUserForm(userName=zhou, password=123456)  request: org.apache.shiro.web.servlet.ShiroHttpServletRequest@622f9a7c', '127.0.0.1', '2019-08-15 13:20:56', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('f342efa3004340e8a7d10491a81ef3a6', null, '导出用户信息', '1452', 'com.waken.dorm.controller.user.UserController.export()', '  response: com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@4f718033', '127.0.0.1', '2019-08-17 22:58:52', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('f36b70bf439c4eec8a847b73ae0d7cce', null, '用户登录', '546', 'com.waken.dorm.controller.user.UserController.login()', '  queryUserForm: QueryUserForm(userName=许峻, password=123456)', '127.0.0.1', '2019-08-14 12:41:44', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('f548985303bf439baf0e8220e31eadfb', null, '用户登录', '586', 'com.waken.dorm.controller.auth.LoginController.login()', '  queryUserForm: QueryUserForm(userName=许峻, password=123456)  request: org.apache.shiro.web.servlet.ShiroHttpServletRequest@3f005e52', '127.0.0.1', '2019-08-20 10:48:25', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('f5f954a0e0df45618b9276f831bcff34', 'af7cae9f7b87426ab1572d431c56253d', '用户登录', '360', 'com.waken.dorm.controller.user.UserController.login()', 'queryUserForm: QueryUserForm{userName=\'许峻\', password=\'123456\'}', '127.0.0.1', '2019-04-16 05:37:43', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('fa0d20eba72c4fb399dcc2dd3278c877', 'af7cae9f7b87426ab1572d431c56253d', '(保存/修改)字典信息', '530', 'com.waken.dorm.controller.dict.DictController.saveDict()', 'editDictForm: EditDictForm{pkDictId=\'null\', schoolId=\'4ddecf810f15405e8b4665a85f273172\', key=\'1\', value=\'生效\', columnName=\'status\', columnDesc=\'状态\', tableName=\'rm_dorm\', tableDesc=\'宿舍表\'}', '127.0.0.1', '2019-04-19 09:33:37', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('fa21110f82974ed8b5cc3b4d7312b9a2', 'af7cae9f7b87426ab1572d431c56253d', '(保存/修改)学校类别信息', '175', 'com.waken.dorm.controller.school.SchoolClassController.saveSchoolClass()', 'editSchoolClassForm: EditSchoolClassForm{pkSchoolClassId=\'c2866c8e92074d38a3d4821d509a42a1\', schoolId=\'4ddecf810f15405e8b4665a85f273172\', parentId=\'null\', className=\'一班\', classDesc=\'一班\', memo=\'null\'}', '127.0.0.1', '2019-04-17 06:28:52', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('fcd17027f0254ff4bd095f9eec42cfb4', 'af7cae9f7b87426ab1572d431c56253d', '用户登录', '247', 'com.waken.dorm.controller.user.UserController.login()', 'queryUserForm: QueryUserForm{userName=\'许峻\', password=\'123456\'}', '127.0.0.1', '2019-05-05 11:30:01', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('fdc311f435aa4d10bc59fed84c309041', null, '导出用户信息', '1868', 'com.waken.dorm.controller.user.UserController.export()', '  response: com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@41a0a3a7', '127.0.0.1', '2019-08-17 23:08:36', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `rm_log` VALUES ('fe1507683756482896ea1f3315f8026c', 'af7cae9f7b87426ab1572d431c56253d', '(保存/修改)字典信息', '86', 'com.waken.dorm.controller.dict.DictController.saveDict()', 'editDictForm: EditDictForm{pkDictId=\'81478ecdc4714c6c8803534d7fc3f4f9\', schoolId=\'4ddecf810f15405e8b4665a85f273172\', dictKey=\'1\', dictValue=\'生效\', columnName=\'status\', columnDesc=\'状态\', tableName=\'rm_dorm\', tableDesc=\'宿舍表\'}', '127.0.0.1', '2019-04-19 10:06:13', '内网IP|0|0|内网IP|内网IP');

CREATE TABLE `rm_resource` (
  `pk_resource_id` varchar(32) NOT NULL COMMENT '资源ID',
  `parent_id` varchar(32) NOT NULL COMMENT '父级ID',
  `resource_name` varchar(50) NOT NULL COMMENT '资源名称',
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

INSERT INTO `rm_resource` VALUES ('1e0af706d4854f7b9d0b8b6013fc405b', 'ea71d0b7204c4bc7ab1a3455c77eb85e', '角色保存', null, '2', '/dorm/role/save.html', 'roles::save', '0', '1', '', '2019-08-21 10:43:43', '1', '2019-08-21 10:43:43', '1');
INSERT INTO `rm_resource` VALUES ('347de92f327c40e290b1f33f8206266d', '93a2394ccf044c709ea660569b91d5ea', '用户保存', null, '2', null, 'users::save', '0', '1', '', '2019-08-21 10:57:11', '1', '2019-08-21 10:57:11', '1');
INSERT INTO `rm_resource` VALUES ('3939e23ff7a7481c9d36d3ebf1baf08c', 'e719a109a7014484901f657f7f3a8c3b', '资源管理', null, '1', '/dorm/resource.html', 'menu', '2', '1', '', '2019-08-21 10:38:06', '1', '2019-08-21 10:38:06', '1');
INSERT INTO `rm_resource` VALUES ('49240f0695b44453b3cdccf7e2167188', '93a2394ccf044c709ea660569b91d5ea', '用户绑定单个角色', null, '2', null, 'users::role', '5', '1', '', '2019-08-21 11:00:18', '1', '2019-08-21 11:00:18', '1');
INSERT INTO `rm_resource` VALUES ('5455948952f441588d88ef3b194d6a91', '93a2394ccf044c709ea660569b91d5ea', '用户查看', null, '2', null, 'users:view', '6', '1', '', '2019-08-21 22:36:50', '1', '2019-08-21 22:36:50', '1');
INSERT INTO `rm_resource` VALUES ('6377b4d5ae084b6086a7b224488ee273', '93a2394ccf044c709ea660569b91d5ea', '用户绑定资源', null, '2', null, 'users::resources', '4', '1', '', '2019-08-21 10:59:31', '1', '2019-08-21 10:59:31', '1');
INSERT INTO `rm_resource` VALUES ('68cd6eb3e1264628a25052f9f8f24dba', '93a2394ccf044c709ea660569b91d5ea', '用户绑定角色', null, '2', null, 'users::roles', '3', '1', '', '2019-08-21 10:59:09', '1', '2019-08-21 10:59:09', '1');
INSERT INTO `rm_resource` VALUES ('6e4f27095b6640078aa217433a663d7b', 'ea71d0b7204c4bc7ab1a3455c77eb85e', '角色绑定资源', null, '2', null, 'roles::resources', '2', '1', '', '2019-08-21 10:52:09', '1', '2019-08-21 10:52:09', '1');
INSERT INTO `rm_resource` VALUES ('7b77b230570642e4b68182989e18460d', '93a2394ccf044c709ea660569b91d5ea', '用户删除', null, '2', null, 'users::delete', '1', '1', '', '2019-08-21 10:57:34', '1', '2019-08-21 10:57:34', '1');
INSERT INTO `rm_resource` VALUES ('83c6ad1f73d746de924a03d00069e790', 'ea71d0b7204c4bc7ab1a3455c77eb85e', '角色查看', null, '2', null, 'roles:view', '3', '1', '', '2019-08-21 22:36:03', '1', '2019-08-21 22:36:03', '1');
INSERT INTO `rm_resource` VALUES ('93a2394ccf044c709ea660569b91d5ea', 'e719a109a7014484901f657f7f3a8c3b', '用户管理', null, '1', '/dorm/user.html', 'menu', '0', '1', '', '2019-08-21 10:37:14', '1', '2019-08-21 10:37:14', '1');
INSERT INTO `rm_resource` VALUES ('9ee1344e62104f39a857ffc96d445423', '3939e23ff7a7481c9d36d3ebf1baf08c', '资源删除', null, '2', null, 'resources::delete', '1', '1', '', '2019-08-21 11:01:41', '1', '2019-08-21 11:01:41', '1');
INSERT INTO `rm_resource` VALUES ('ca4c6f3e713b4ca0b60e2752e3fc70db', '3939e23ff7a7481c9d36d3ebf1baf08c', '资源查看', null, '2', null, 'resources:view', '2', '1', '', '2019-08-21 22:37:25', '1', '2019-08-21 22:37:25', '1');
INSERT INTO `rm_resource` VALUES ('cc2a7dae77cc4625a1397b9c60fa5c51', '3939e23ff7a7481c9d36d3ebf1baf08c', '资源保存', null, '2', null, 'resources::save', '0', '1', '', '2019-08-21 11:01:26', '1', '2019-08-21 11:01:26', '1');
INSERT INTO `rm_resource` VALUES ('d0e73b3b14504550a554f552c58a91df', 'ea71d0b7204c4bc7ab1a3455c77eb85e', '角色删除', null, '2', null, 'roles::delete', '1', '1', '', '2019-08-21 10:44:41', '1', '2019-08-21 10:44:41', '1');
INSERT INTO `rm_resource` VALUES ('e719a109a7014484901f657f7f3a8c3b', 'root', '系统管理', 'icon', '1', null, 'menu', '0', '1', '', '2019-08-21 10:20:19', '1', '2019-08-21 10:20:19', '1');
INSERT INTO `rm_resource` VALUES ('ea71d0b7204c4bc7ab1a3455c77eb85e', 'e719a109a7014484901f657f7f3a8c3b', '角色管理', null, '1', '/dorm/role.html', 'menu', '1', '1', '', '2019-08-21 10:38:47', '1', '2019-08-21 10:38:47', '1');
INSERT INTO `rm_resource` VALUES ('ee00f923bcae44a48d73a343975788f3', '93a2394ccf044c709ea660569b91d5ea', '用户导出', null, '2', null, 'users::export', '2', '1', '', '2019-08-21 10:58:15', '1', '2019-08-21 10:58:15', '1');

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
INSERT INTO `rm_role_resource_rel` VALUES ('1b6fc310a10b4eef97dae29d735e2cc3', 'ec155fd23f21484d9d33289ae705b1a1', 'ea71d0b7204c4bc7ab1a3455c77eb85e', '1', '2019-08-21 10:38:47', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('1cc21f338b5640208874e78dd7e18f7e', 'ec155fd23f21484d9d33289ae705b1a1', '6e4f27095b6640078aa217433a663d7b', '2', '2019-08-21 10:52:09', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('29cab999af57473daa444e627a4fe136', 'ec155fd23f21484d9d33289ae705b1a1', '2c8c47c7d3eb413c8fa0f5823a6bc862', '1', '2019-08-21 10:04:41', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('338f35e9d03241acaef6d389de9ae716', 'ec155fd23f21484d9d33289ae705b1a1', '1e0af706d4854f7b9d0b8b6013fc405b', '2', '2019-08-21 10:43:43', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('359f236e193b42dd991aa9448e77ed8e', 'ec155fd23f21484d9d33289ae705b1a1', '7351a07ece4f4fc9bab359dcabaa23cf', '1', '2019-08-21 10:11:24', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('3a9e009408c84b2580feb58c522d3cfa', 'ec155fd23f21484d9d33289ae705b1a1', 'ca4c6f3e713b4ca0b60e2752e3fc70db', '2', '2019-08-21 22:37:25', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('3aa91054b2574336bc17cb685e4cd69e', 'ec155fd23f21484d9d33289ae705b1a1', 'cc2a7dae77cc4625a1397b9c60fa5c51', '2', '2019-08-21 11:01:26', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('488c822aeebb455ebdfdf8e08dede4fd', 'ec155fd23f21484d9d33289ae705b1a1', 'd68735a434b74d01b502b330790d54ec', '1', '2019-08-21 22:41:45', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('6ae90b8d3c37432385c45e5e347d9cbf', 'ec155fd23f21484d9d33289ae705b1a1', '68cd6eb3e1264628a25052f9f8f24dba', '2', '2019-08-21 10:59:09', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('728a2612aba04b1aacf7dd6118554838', 'ec155fd23f21484d9d33289ae705b1a1', 'ee00f923bcae44a48d73a343975788f3', '2', '2019-08-21 10:58:15', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('874c449bd0944007a458706969b83646', 'ec155fd23f21484d9d33289ae705b1a1', '5455948952f441588d88ef3b194d6a91', '2', '2019-08-21 22:36:50', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('933ed7952a874f68972eada2e1016b89', 'ec155fd23f21484d9d33289ae705b1a1', '93a2394ccf044c709ea660569b91d5ea', '1', '2019-08-21 10:37:14', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('9502e3d13e054d1bbb387895870b08dc', 'ec155fd23f21484d9d33289ae705b1a1', 'd0e73b3b14504550a554f552c58a91df', '2', '2019-08-21 10:44:41', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('9ac025708323437baefdcd4cb9637199', 'ec155fd23f21484d9d33289ae705b1a1', 'e719a109a7014484901f657f7f3a8c3b', '1', '2019-08-21 10:20:19', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('a484215035d6416ca9d441978e969d17', 'ec155fd23f21484d9d33289ae705b1a1', '83c6ad1f73d746de924a03d00069e790', '2', '2019-08-21 22:36:03', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('a6f079cccd314be4b240bbb172a4ecb5', 'ec155fd23f21484d9d33289ae705b1a1', '6377b4d5ae084b6086a7b224488ee273', '2', '2019-08-21 10:59:31', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('bff0c0055fd24d3e950ef39e40d0b7fe', 'ec155fd23f21484d9d33289ae705b1a1', '3939e23ff7a7481c9d36d3ebf1baf08c', '1', '2019-08-21 10:38:06', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('c0e270a6a96d4f4396ed9c369045c93f', 'ec155fd23f21484d9d33289ae705b1a1', 'd9484a8b6afc4de78be1323a96c4b1c7', '1', '2019-08-21 22:38:32', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('c89084fc50ae460c96a071edef3c2ff5', 'ec155fd23f21484d9d33289ae705b1a1', '49240f0695b44453b3cdccf7e2167188', '2', '2019-08-21 11:00:18', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('cec0469828d24b9dace656946eb381b1', 'ec155fd23f21484d9d33289ae705b1a1', '347de92f327c40e290b1f33f8206266d', '2', '2019-08-21 10:57:11', '1');
INSERT INTO `rm_role_resource_rel` VALUES ('d3d1688611d542d8b4eec336b6e7df97', 'ec155fd23f21484d9d33289ae705b1a1', '9ee1344e62104f39a857ffc96d445423', '2', '2019-08-21 11:01:41', '1');

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

INSERT INTO `rm_user` VALUES ('1', 'aishu', 'aishu', '1e0e3a8eec85a1c21509f022a7d7e949', '15328417106', '1', null, '1', '511324199702101111', '格子衬衫', '1', '1', null, null, null, '2181250231@qq.com', '2019-08-21 23:00:27', '2019-08-21 09:23:39', '1', '2019-08-21 09:24:01', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, '管理员用户');
INSERT INTO `rm_user` VALUES ('a0f5e121974d4adaba055a1602ce42b4', 'xiaozhu', 'xiaozhu', 'd6c53ecc0e306110050b7c46b848eb49', '15328417109', '0', null, null, null, null, '1', '1', null, null, null, '2181250234@qq.com', '2019-08-21 19:07:25', '2019-08-21 11:24:21', 'guest', '2019-08-21 11:24:21', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);

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

INSERT INTO `rm_user_privilege` VALUES ('bbec66146a0642f7840c2bc307d86341', '1', 'c8cb2e89be9a4dc9b12d2375e879e98c', '3', '2019-08-21 11:16:00', '1');
INSERT INTO `rm_user_privilege` VALUES ('d2366fb620ae4dd4a6abe4f5801ab137', '1', 'ec155fd23f21484d9d33289ae705b1a1', '3', '2019-08-21 11:16:00', '1');