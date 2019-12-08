use wop;

CREATE TABLE `t_op_tenant_info` (
  `id` bigint(20) NOT NULL COMMENT '租户编号',
  `tenant_name` varchar(1000) NOT NULL COMMENT '租户名称',
  `display_name` varchar(1000) NOT NULL COMMENT '显示名称',
  `tenant_province` varchar(100) DEFAULT NULL COMMENT '省',
  `tenant_city` varchar(100) DEFAULT NULL COMMENT '市',
  `tenant_area` varchar(100) DEFAULT NULL COMMENT '区县',
  `tenant_address` varchar(1000) DEFAULT NULL COMMENT '联系地址',
  `tenant_contact` varchar(100) NOT NULL COMMENT '联系人',
  `tenant_mobile` varchar(100) NOT NULL COMMENT '手机号码',
  `tenant_tel` varchar(100) DEFAULT NULL COMMENT '单位电话',
  `tenant_email` varchar(100) NOT NULL COMMENT '邮箱',
  `tenant_qq` varchar(100) DEFAULT NULL COMMENT 'QQ号码',
  `tenant_type` int(11) NOT NULL COMMENT '租户类型（1：使用单位；2：供应单位；3：内部运营）',
  `tenant_status` int(11) NOT NULL COMMENT '租户状态（1：正式；2：试用）',
  `reg_time` datetime NOT NULL COMMENT '注册时间',
  `end_date` date NOT NULL COMMENT '到期日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='租户';