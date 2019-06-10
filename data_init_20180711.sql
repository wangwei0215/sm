/*
SQLyog Enterprise v12.09 (64 bit)
MySQL - 5.7.13-log : Database - data_db
*********************************************************************
*/
DROP TABLE IF EXISTS `yhb_loan_product_process_fee`;
CREATE TABLE `yhb_loan_product_process_fee` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) NOT NULL COMMENT '产品ID',
  `confirm_fee_flag` tinyint(2) NOT NULL COMMENT '是否有确认用款流程（1：是 0：否）',
  `confirm_fee_url` varchar(255) DEFAULT NULL COMMENT '确认用款流程跳转地址',
  `pay_fee_flag` tinyint(2) NOT NULL COMMENT '是否有支付信用报告费流程（1：是 0：否）',
  `pay_fee_url` varchar(255) DEFAULT NULL COMMENT '支付信用报告费流程跳转地址',
  `fee_name` varchar(32) NOT NULL COMMENT '流程费用名称',
  `fee_rate` decimal(20,4) DEFAULT NULL COMMENT '费率，基数百分比',
  `base_amount` varchar(32) DEFAULT NULL COMMENT '基数，capital、loan_amount',
  `url` varchar(255) DEFAULT NULL COMMENT '购买地址',
  `add_time` bigint(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  `add_user` bigint(20) DEFAULT NULL,
  `update_user` bigint(20) DEFAULT NULL,
   createTime           datetime            null comment '创建时间',
   updateTimes          datetime            null comment '更新时间',
   state                varchar(32)         null default 'AVAILABLE' comment '记录状态（可用AVAILABLE删除DELETE禁用FORBID）',
   versions             bigint              null default 0 comment '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='报告费费率表';

DROP TABLE IF EXISTS `yhb_custservice_user_feedback_deal`;
create table yhb_custservice_user_feedback_deal 
(
   id                   varchar(32)    		not null comment '主键ID',
   createTime           datetime            null comment '创建时间',
   updateTimes          datetime            null comment '更新时间',
   state                varchar(32)         null default 'AVAILABLE' comment '记录状态（可用AVAILABLE删除DELETE禁用FORBID）',
   versions             bigint              null default 0 comment '版本号',
   custserviceUserFeedbackId bigint(20)     null comment '客户反馈外键',
   remark               varchar(1024)       null comment '备注内容',
   dealName             varchar(32)         null comment '处理人'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户反馈处理表';

DROP TABLE IF EXISTS `yhb_user_human_amount`;
CREATE TABLE `yhb_user_human_amount` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
	 createTime           datetime           null comment '创建时间',
   updateTimes          datetime           null comment '更新时间',
   state                varchar(32)        null default 'AVAILABLE' comment '记录状态（可用AVAILABLE删除DELETE禁用FORBID）',
   versions             bigint             null default 0 comment '版本号',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `value` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '用户人工额度',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否开启：\n            0：未开启\n            1：开启',
  `add_time` bigint(20) DEFAULT NULL,
  `add_user` bigint(20) DEFAULT '0' COMMENT '添加用户ID',
  `update_time` bigint(20) DEFAULT NULL,
  `update_user` bigint(20) DEFAULT '0' COMMENT '更新用户ID',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户人工额度表';

DROP TABLE IF EXISTS `yhb_saas_merchants_users`;
CREATE TABLE `yhb_saas_merchants_users` (
  `merchantId` bigint(20) NOT NULL,
  `userId` varchar(32) NOT NULL,
  KEY `FK2E94BE06370DC8E6` (`merchantId`),
  KEY `FK2E94BE063C631E50` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `yhb_loan_product_model`;
create table yhb_loan_product_model 
(
   id                   varchar(32)        not null comment '主键ID',
   createTime           datetime           null comment '创建时间',
   updateTimes          datetime           null comment '更新时间',
   state                varchar(32)        null default 'AVAILABLE' comment '记录状态（可用AVAILABLE删除DELETE禁用FORBID）',
   versions             bigint             null default 0 comment '版本号',
   name                 varchar(32)        null comment '产品名称',
   maxAmount            decimal(10,2)      null comment '最大金额',
   minAmount            decimal(10,2)      null comment '最小金额',
   amountStep           decimal(10,0)      null comment '金额步长',
   periodValue          int(11)            null comment '期限',
   interestRate         decimal(20,6)      null comment '服务费百分比',
   interestSubRate      decimal(20,6)      null comment '利息百分比',
   feeChargeType        varchar(32)        null comment '服务费计费方式',
   lateFee              varchar(32)        null comment '逾期滞纳金',
   dayOverDueRate       varchar(32)        null comment '逾期罚息百分比',
   maxOverdueFeeRate    decimal(20,6)      null comment '罚息封顶百分比',
   reportFee   			decimal(20,4)      null comment '报告费(百分比)',
   beforeFeeOne   		decimal(20,7)      null comment '前置费用1(年化360天百分比)',
   beforeFeeTwo   		decimal(20,7)      null comment '前置费用2(年化360天百分比)',
   afterFeeOne   		decimal(20,7)      null comment '后置费用1(年化360天百分比)',
   afterFeeTwo   		decimal(20,7)      null comment '后置费用2(年化360天百分比)',
   secondPayFeeOne   	decimal(20,7)      null comment '秒扣费用1(年化360天百分比)',
   secondPayFeeTwo   	decimal(20,7)      null comment '秒扣费用2(年化360天百分比)'
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='产品模型表。用户让用户选择某个产品';

DROP TABLE IF EXISTS `yhb_company`;
create table yhb_company 
(
   id                   varchar(32)     not null comment '主键ID',
   createTime           datetime        null comment '创建时间',
   updateTimes          datetime        null comment '更新时间',
   state                varchar(32)     null default 'AVAILABLE' comment '记录状态（可用AVAILABLE删除DELETE禁用FORBID）',
   versions             bigint          null default 0 comment '版本号',
   companyCode          varchar(32)     null comment '商户code',
   companyName          varchar(64)     null comment '公司名称',
   contactName          varchar(32)     null comment '联系人姓名',
   contactMobile        varchar(32)     null comment '联系人电话',
   PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='商户表';


DROP TABLE IF EXISTS `yhb_third_service_cfg`;
CREATE TABLE `yhb_third_service_cfg` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `key` varchar(64) NOT NULL DEFAULT '' COMMENT '键',
  `value` varchar(1024) NOT NULL DEFAULT '' COMMENT '值',
  `name` varchar(64) NOT NULL DEFAULT '' COMMENT '名称',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '描述',
  `add_time` bigint(20) NOT NULL DEFAULT '0',
  `update_time` bigint(20) NOT NULL DEFAULT '0',
  `add_user` bigint(20) NOT NULL DEFAULT '0',
  `update_user` bigint(20) NOT NULL DEFAULT '0',
   createTime           datetime         null comment '创建时间',
   updateTimes           datetime        null comment '更新时间',
   state               varchar(32)       null default 'AVAILABLE' comment '记录状态（可用AVAILABLE删除DELETE禁用FORBID）',
   versions              bigint          null default 0 comment '版本号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `key` (`key`)
) ENGINE=InnoDB AUTO_INCREMENT=518 DEFAULT CHARSET=utf8 COMMENT='第三方服务配置表';

DROP TABLE IF EXISTS `yhb_push_send_log`;
CREATE TABLE `yhb_push_send_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `merchant_id` bigint(20) NOT NULL DEFAULT '1' COMMENT '商户id',
  `user_id` bigint(20) NOT NULL,
  `unique_id` varchar(100) DEFAULT NULL COMMENT '推送的唯一目标',
  `request_text` varchar(1000) DEFAULT NULL,
  `response_text` varchar(1000) DEFAULT NULL,
  `begin_time` bigint(20) DEFAULT NULL COMMENT '发送提交时间',
  `end_time` bigint(20) DEFAULT NULL COMMENT '发送提交完成时间',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '发送状态，0：处理中，1：成功，2：失败',
  `content` varchar(1000) DEFAULT NULL COMMENT '发送的内容',
  `msg_id` bigint(20) DEFAULT NULL COMMENT 'push推送返回的msgId',
  `template_id` bigint(20) NOT NULL COMMENT '消息模板id',
  `channel` varchar(10) DEFAULT NULL,
  `add_time` bigint(20) DEFAULT NULL,
  `add_user` varchar(50) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  `update_user` varchar(50) DEFAULT NULL,
   createTime           datetime         null comment '创建时间',
   updateTimes           datetime        null comment '更新时间',
   state               varchar(32)       null default 'AVAILABLE' comment '记录状态（可用AVAILABLE删除DELETE禁用FORBID）',
   versions              bigint          null default 0 comment '版本号',
  PRIMARY KEY (`id`),
  KEY `template_id` (`template_id`),
  KEY `channel` (`channel`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=142 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `yhb_jpush_info`;
CREATE TABLE `yhb_jpush_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL,
  `jpush_id` varchar(32) NOT NULL COMMENT '注册到jpush的用户唯一标识',
  `add_product` varchar(16) NOT NULL DEFAULT '',
  `device_platform` varchar(16) DEFAULT '' COMMENT '手机设备系统ios，安卓,all(都支持)',
  `product_version` varchar(16) DEFAULT '' COMMENT '产品版本号',
  `add_channel` varchar(16) NOT NULL DEFAULT '',
  `add_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '添加时间',
  `add_user` bigint(20) NOT NULL DEFAULT '0' COMMENT '添加用户ID',
  `update_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新时间, unix时间戳（毫秒）,修改任何字段(除本字段外)都应更新此字段.',
  `update_user` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新用户ID',
   createTime           datetime         null comment '创建时间',
   updateTimes           datetime        null comment '更新时间',
   state               varchar(32)       null default 'AVAILABLE' comment '记录状态（可用AVAILABLE删除DELETE禁用FORBID）',
   versions              bigint          null default 0 comment '版本号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`),
  KEY `jpush_id` (`jpush_id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 COMMENT='用户jpush唯一标识(当前活跃的,登录操作更新此表)';

DROP TABLE IF EXISTS `yhb_loan_contract`;
CREATE TABLE `yhb_loan_contract` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `merchant_id` bigint(20) NOT NULL DEFAULT '1' COMMENT '商户id',
  `nid` varchar(32) NOT NULL COMMENT '借款单号',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `product_id` bigint(20) NOT NULL COMMENT '产品id',
  `contract_no` varchar(30) DEFAULT NULL COMMENT '合同编号',
  `period_seq` int(11) NOT NULL DEFAULT '1' COMMENT '当前还款第几期(不分期产品，只能为1)',
  `loan_amount` decimal(10,2) DEFAULT NULL COMMENT '放款金额',
  `capital` decimal(10,2) DEFAULT NULL COMMENT '合同金额',
  `id_type` varchar(20) DEFAULT NULL COMMENT '证件类型',
  `id_number` varchar(18) DEFAULT NULL COMMENT '证件号',
  `name` varchar(20) DEFAULT NULL COMMENT '客户姓名',
  `account_status` varchar(32) DEFAULT NULL COMMENT '账户状态：1正常，2逾期，3结清，4提前结清，5合同作废',
  `account_busi_type` varchar(20) DEFAULT NULL COMMENT '账户业务状态：NORMAL 正常，EARLYREPAY预约提前把还款',
  `max_delinquent_days` int(11) NOT NULL DEFAULT '0' COMMENT '最大逾期天数',
  `delinquent_days` int(11) NOT NULL DEFAULT '0' COMMENT '逾期天数',
  `delinquent_amount` decimal(10,2) DEFAULT NULL COMMENT '逾期金额',
  `next_repay_date` date DEFAULT NULL COMMENT '下一个还款日',
  `period_num` int(11) DEFAULT NULL COMMENT '贷款期数',
  `cur_remain_capital` decimal(10,2) DEFAULT NULL COMMENT '当前剩余未还本金',
  `cur_remain_interest` decimal(10,2) DEFAULT NULL COMMENT '当前剩余未还利息',
  `lend_date` date DEFAULT NULL COMMENT '放款日期',
  `clear_date` date DEFAULT NULL COMMENT '结清日期',
  `contract_end_date` date DEFAULT NULL COMMENT '合同结束时间',
  `contract_begin_date` date DEFAULT NULL COMMENT '合同起始时间',
  `plan_code` varchar(40) DEFAULT NULL COMMENT '支付计划代码',
  `total_repay_amount` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '已还款总金额',
  `rate` decimal(20,6) DEFAULT '0.000000' COMMENT '费率',
  `overflow_amount` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '溢缴金额',
  `refund_flag` tinyint(4) DEFAULT '0' COMMENT '是否已退款',
  `is_overflow_amount_handled` tinyint(4) DEFAULT '0' COMMENT '是否人工处理过溢缴金额',
  `batch_date` date DEFAULT NULL COMMENT '最新跑批日期',
  `add_time` bigint(20) DEFAULT NULL,
  `add_user` bigint(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  `update_user` bigint(20) DEFAULT NULL,
   createTime           datetime         null comment '创建时间',
   updateTimes           datetime        null comment '更新时间',
   state               varchar(32)       null default 'AVAILABLE' comment '记录状态（可用AVAILABLE删除DELETE禁用FORBID）',
   versions              bigint          null default 0 comment '版本号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `nid_period_seq` (`nid`,`period_seq`),
  KEY `idx_user_id` (`user_id`),
  KEY `account_status` (`account_status`),
  KEY `clear_date` (`clear_date`),
  KEY `max_delinquent_days` (`max_delinquent_days`),
  KEY `name` (`name`),
  KEY `idx_update_time` (`update_time`)
) ENGINE=InnoDB AUTO_INCREMENT=2492385 DEFAULT CHARSET=utf8 COMMENT='合同账务主表';

DROP TABLE IF EXISTS `yhb_finance_borrow`;
CREATE TABLE `yhb_finance_borrow` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `merchant_id` bigint(20) NOT NULL DEFAULT '1' COMMENT '商户id',
  `nid` varchar(32) NOT NULL DEFAULT '' COMMENT '借款单号',
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `phone` varchar(20) NOT NULL DEFAULT '' COMMENT '用户注册手机号',
  `product_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '产品ID',
  `contract_no` varchar(30) NOT NULL DEFAULT '' COMMENT '合同编号',
  `card_no` varchar(32) NOT NULL DEFAULT '' COMMENT '放款的卡号',
  `card_bank_name` varchar(20) DEFAULT NULL COMMENT '卡所在银行',
  `amount` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '合同金额',
  `loan_amount` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '放款金额',
  `fee` decimal(20,4) DEFAULT '0.0000' COMMENT '费用',
  `fee_charge_type` varchar(8) DEFAULT '' COMMENT 'postpaid:后付费, prepaid: 预付费',
  `secured_type` varchar(32) DEFAULT '' COMMENT 'postpaid:后付费, prepaid: 预付费',
  `id_card` varchar(18) NOT NULL DEFAULT '' COMMENT '证件号',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '客户姓名',
  `status` varchar(64) NOT NULL DEFAULT '' COMMENT '借款单状态：  待放款 3  放款处理中 5  放款成功 6  代付失败 7  代付超时 8  放款失败 9  还款中 10  结清 11',
  `repayment_method` tinyint(4) DEFAULT NULL COMMENT '    1:等额本息还款',
  `period_num` int(11) NOT NULL DEFAULT '0' COMMENT '贷款期数',
  `verify_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '审核通过时间',
  `apply_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '借款申请时间',
  `refuse_time` bigint(20) DEFAULT NULL COMMENT '拒绝时间',
  `refuse_remark` varchar(512) DEFAULT NULL COMMENT '拒绝描述',
  `loan_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '放款时间',
  `clear_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '结清时间',
  `period_type` enum('daily','weekly','monthly') DEFAULT 'daily' COMMENT '期数类型（daily:不分期 ,weekly：每期1周,monthly：每期1月）',
  `period_value` int(11) DEFAULT NULL COMMENT '期数（7、14、21）',
  `borrow_days` int(11) DEFAULT NULL COMMENT '借款天数',
  `repay_channel` varchar(30) NOT NULL DEFAULT '' COMMENT '结清的渠道',
  `loan_channel` varchar(20) NOT NULL DEFAULT '' COMMENT '放款渠道',
  `router_code` varchar(200) NOT NULL DEFAULT '' COMMENT '路由选到的渠道',
  `add_product` varchar(20) DEFAULT NULL COMMENT '产品名称',
  `add_channel` varchar(20) DEFAULT NULL COMMENT '渠道',
  `product_version` varchar(8) NOT NULL DEFAULT '' COMMENT '版本号',
  `add_time` bigint(20) NOT NULL DEFAULT '0',
  `add_user` bigint(20) NOT NULL DEFAULT '0',
  `update_time` bigint(20) NOT NULL DEFAULT '0',
  `update_user` bigint(20) NOT NULL DEFAULT '0',
  `debit_card_no` varchar(32) DEFAULT NULL COMMENT '还款银行卡号',
  `debit_bank_name` varchar(32) DEFAULT NULL COMMENT '还款银行卡名称',
  `loan_type` enum('cash','credit','cash_period','consumption') DEFAULT 'cash',
   createTime           datetime         null comment '创建时间',
   updateTimes           datetime        null comment '更新时间',
   state               varchar(32)       null default 'AVAILABLE' comment '记录状态（可用AVAILABLE删除DELETE禁用FORBID）',
   versions              bigint          null default 0 comment '版本号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `nid_2` (`nid`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_phone` (`phone`),
  KEY `apply_time` (`apply_time`),
  KEY `status` (`status`),
  KEY `loan_channel` (`loan_channel`),
  KEY `loan_time` (`loan_time`),
  KEY `idx_update_time` (`update_time`)
) ENGINE=InnoDB AUTO_INCREMENT=185 DEFAULT CHARSET=utf8 COMMENT='财务借款表';

DROP TABLE IF EXISTS `yhb_borrow`;
CREATE TABLE `yhb_borrow` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `merchant_id` bigint(20) NOT NULL DEFAULT '1' COMMENT '商户id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `nid` varchar(32) NOT NULL DEFAULT '' COMMENT '借款合同编号',
  `name` varchar(32) NOT NULL COMMENT '客户姓名',
  `phone` varchar(20) NOT NULL COMMENT '客户借款手机号',
  `product_id` bigint(20) NOT NULL COMMENT '借款产品id,miaobt_fi_loan_product主键',
  `amount` decimal(20,2) NOT NULL COMMENT '申请额度',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态，0：待审核；1：审核成功；2：审核失败；3：协议确认； 4：财务建账',
  `status_update_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '状态变更时间',
  `loan_type` enum('cash','credit','cash_period','consumption') NOT NULL DEFAULT 'cash' COMMENT '贷款类型：cash：现金贷，consumptoin：消费贷，credit：信用卡代还 cash_period: 现金分期',
  `repayment_method` tinyint(4) DEFAULT NULL COMMENT '分期贷款的还款方式，不分期的固定为一次性还本付息\n            1:等额本息还款\n            2:等额本金还款\n            3:每期还息到期还本\n            4:一次性利随本清\n            5:等本等息\n            6:zk等本等息',
  `urgent_flag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '加急标识',
  `urgent_time` bigint(20) DEFAULT NULL COMMENT '加急申请时间',
  `urgent_op_user` bigint(20) DEFAULT NULL COMMENT '加急操作人',
  `ci` int(11) DEFAULT NULL COMMENT '专案代码',
  `auto_verified` tinyint(4) DEFAULT NULL COMMENT '是否自动审核（完全由系统审核）',
  `verify_stage` tinyint(4) DEFAULT NULL COMMENT '当前借款单处于的审核阶段：1：准入；2：初审；3：授信电核；4：终审；\n 5：协议电核（客服系统触发修改）；6：放款（财务系统触发修改）',
  `card_no` varchar(32) DEFAULT NULL COMMENT '用户用来借款的银行卡号',
  `id_card` varchar(18) DEFAULT NULL COMMENT '身份证号',
  `card_bank_name` varchar(32) DEFAULT NULL COMMENT '银行卡所属银行名称',
  `period_type` enum('daily','weekly','monthly') DEFAULT 'daily' COMMENT '分期类型',
  `period_value` int(11) DEFAULT '0' COMMENT '借款期数',
  `period_num` int(11) DEFAULT NULL COMMENT '分期数，根据repayment_method与period_value算出',
  `borrow_use` bigint(20) NOT NULL DEFAULT '0' COMMENT '借款用途，对应miaobt_borrow_use.id',
  `reject_reason` int(11) DEFAULT NULL COMMENT '原因',
  `add_product` varchar(16) NOT NULL DEFAULT '' COMMENT '新增的产品',
  `add_channel` varchar(16) NOT NULL DEFAULT '' COMMENT '新增的渠道',
  `product_version` varchar(8) NOT NULL DEFAULT '' COMMENT '产品版本号',
  `score` int(11) NOT NULL DEFAULT '0' COMMENT '得分值',
  `borrow_days` int(11) DEFAULT NULL COMMENT '借款天数',
  `auto_decision_flag` tinyint(4) DEFAULT '0' COMMENT '决策引擎处理状态：0-待处理， 1-处理完成，2-处理中，3-挂起，4-挂起后就绪',
  `cid` varchar(32) DEFAULT NULL COMMENT '基站ID, 四部分构成，用冒号分隔，格式如: MCC:MNC:LAC:CID',
  `network_type` varchar(32) DEFAULT NULL COMMENT 'unavailable,wifi,2g,3g,4g,5g,other',
  `market_channel` varchar(64) DEFAULT NULL COMMENT 'app推广渠道',
  `device_platform` varchar(32) DEFAULT NULL COMMENT '设备平台, 取值:ios/android/WM/PC/other',
  `sim` varchar(64) DEFAULT NULL COMMENT '手机sim卡号',
  `device_os` varchar(64) DEFAULT NULL COMMENT '操作系统',
  `device_model` varchar(64) DEFAULT NULL COMMENT '设备型号',
  `idfa` varchar(64) DEFAULT NULL COMMENT '仅ios系统提供',
  `mac` varchar(64) DEFAULT NULL COMMENT '网卡MAC地址，12位字符，不含冒号',
  `imei` varchar(128) DEFAULT NULL COMMENT '设备IMEI',
  `ready_for_pre_auth` tinyint(4) DEFAULT '0' COMMENT '是否可以进行预授权，1-是，0-否',
  `ready_for_pre_auth_time` bigint(20) DEFAULT NULL COMMENT '就绪时间',
  `add_time` bigint(20) DEFAULT NULL COMMENT '添加用户ID',
  `add_user` bigint(20) DEFAULT '0' COMMENT '添加人user_id',
  `update_time` bigint(20) DEFAULT NULL COMMENT '更新时间',
  `update_user` bigint(20) DEFAULT '0' COMMENT '更新用户ID',
  `pre_auth_percent` int(11) NOT NULL DEFAULT '80' COMMENT '预授权百分比',
  `secured_type` varchar(32) DEFAULT NULL,
  `fee` decimal(8,2) DEFAULT '0.00' COMMENT '借款费用',
  `fee_charge_type` varchar(8) DEFAULT '' COMMENT 'postpaid:后付费, prepaid: 预付费',
  `debit_bank_name` varchar(32) DEFAULT NULL COMMENT '还款银行卡名称',
  `debit_card_no` varchar(32) DEFAULT NULL COMMENT '还款银行卡号',
   createTime           datetime         null comment '创建时间',
   updateTimes           datetime        null comment '更新时间',
   state               varchar(32)       null default 'AVAILABLE' comment '记录状态（可用AVAILABLE删除DELETE禁用FORBID）',
   versions              bigint          null default 0 comment '版本号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `nid` (`nid`),
  KEY `AK_add_time` (`add_time`),
  KEY `AK_update_time` (`update_time`),
  KEY `phone` (`phone`),
  KEY `name` (`name`),
  KEY `user_id` (`user_id`),
  KEY `verify_stage` (`verify_stage`),
  KEY `add_product` (`add_product`),
  KEY `add_channel` (`add_channel`),
  KEY `score` (`score`),
  KEY `product_id` (`product_id`),
  KEY `status` (`status`),
  KEY `secured_type` (`secured_type`),
  KEY `market_channel` (`market_channel`),
  KEY `id_card` (`id_card`)
) ENGINE=InnoDB AUTO_INCREMENT=50430 DEFAULT CHARSET=utf8 COMMENT='借款申请信息表';


DROP TABLE IF EXISTS `yhb_suspension_top`;
create table yhb_suspension_top 
(
  `id` varchar(32) NOT NULL comment '主键ID',
   createTime           datetime         null comment '创建时间',
   updateTimes           datetime        null comment '更新时间',
   state               varchar(32)       null default 'AVAILABLE' comment '记录状态（可用AVAILABLE删除DELETE禁用FORBID）',
   versions              bigint          null default 0 comment '版本号',
   merchantId           bigint(20)       null comment 'app分类表id',
   content              varchar(256)     null comment '发送内容',
   supportPage          varchar(64)      null comment '支持页面(home:首页  borrowApply:借款申请页 repayment:还款页) 多选 用,隔开',
   sendPersonType       int(1)           null comment '发送人类型（0:全部 1:固定名单 2:系统标签 3:自定义）',
   fixedName            varchar(1024)    null comment '固定名单集合（存userTag表的id集合，用逗号隔开）',
   sysTag               varchar(1024)    null comment '系统标签集合 1新户 2次新户 3老户(多选，用逗号隔开)',
   customName           varchar(2048)    null comment '自定义手机号集合(用逗号隔开)',
   isUsed               int(1)           null comment '是否启用（0不启用 1启用）',
   PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='顶部悬浮通知表';


DROP TABLE IF EXISTS `yhb_msg_push_task`;
create table yhb_msg_push_task 
(
  `id` varchar(32) NOT NULL comment '主键ID',
   createTime           datetime         null comment '创建时间',
   updateTimes           datetime        null comment '更新时间',
   state               varchar(32)       null default 'AVAILABLE' comment '记录状态（可用AVAILABLE删除DELETE禁用FORBID）',
   versions              bigint          null default 0 comment '版本号',
   taskType             int              null comment '发送类型（0:消息  1:推送）',
   merchantId           bigint(20)       null comment 'app分类表id',
   sendStatus           int(1)           null comment '发送状态（0:未发送  1:发送中 2:发送成功 3:发送失败 4:无活跃 5:无用户）',
   content              varchar(256)     null comment '内容',
   sendType             int(1)           null comment '发送方式（0立即  1定时）',
   mobile               varchar(32)      null comment '发送手机号',
   userId               bigint           null comment '发送用户id',
   openType             int(1)           null comment '打开方式(0:app内部 1:外部浏览器)',
   url                  varchar(256)     null comment '链接',
   isJump               int(1)           null comment '是否跳转（0:不跳转  1:跳转）',
   needLogin            int(1)           null comment '是否自动登录(0:否  1:是)',
   sendTime             datetime        null comment '发送时间',
   msgPushId			varchar(32)     not null comment '推送消息表id',
   PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='发送消息中间表';


DROP TABLE IF EXISTS `yhb_user`;
CREATE TABLE `yhb_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `merchant_id` bigint(20) NOT NULL DEFAULT '1' COMMENT '商户id',
  `username` varchar(32) NOT NULL DEFAULT '' COMMENT '用户名，目前取值为用户手机号。同一个手机号只能有一个活跃用户(write_off为1)',
  `invite_code` varchar(32) NOT NULL DEFAULT '' COMMENT '邀请码，用于邀请别人',
  `invited_by` bigint(20) NOT NULL DEFAULT '0' COMMENT '邀请人user_id',
  `password` varchar(32) NOT NULL DEFAULT '',
  `salt` char(8) NOT NULL DEFAULT '' COMMENT '密码salt',
  `write_off` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否注销，1-是',
  `edu_degree` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0：未知；1:高中及以下；2：大专；3：本科；4：硕士；5：博士以及以上（用户填写的）',
  `edu_degree_verified` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0：未知；1:高中及以下；2：大专；3：本科；4：硕士；5：博士以及以上（验证的）',
  `edu_degree_verify_time` bigint(20) NOT NULL DEFAULT '0',
  `marry_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0：未知；1：未婚；2：已婚未育；3：已婚已育；4：离异；5：其他',
  `house_status` tinyint(4) DEFAULT '0' COMMENT '1:租房, 2:小产权房, 3:经济适用房, 4:房改房, 5:单位自建房, 6:集资房, 7:商品房, 8:其他',
  `prev_product_version` varchar(32) NOT NULL DEFAULT '' COMMENT '产品版本，如:1.0, 2.1',
  `prev_channel` varchar(12) NOT NULL DEFAULT '' COMMENT '产品渠道',
  `prev_login_ip` varchar(16) NOT NULL DEFAULT '' COMMENT '上次登录客户端IP',
  `prev_login_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '上次登录时间, unix时间戳（毫秒）',
  `last_product_version` varchar(32) NOT NULL DEFAULT '' COMMENT '产品版本，如:1.0, 2.1',
  `last_channel` varchar(12) NOT NULL DEFAULT '' COMMENT '产品渠道',
  `last_login_ip` varchar(16) NOT NULL DEFAULT '' COMMENT '最后登录客户端IP',
  `last_login_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '最后登录时间, unix时间戳（毫秒）',
  `add_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '添加时间',
  `add_user` bigint(20) NOT NULL DEFAULT '0' COMMENT '添加用户ID',
  `update_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新时间, unix时间戳（毫秒）,修改任何字段(除本字段外)都应更新此字段.',
  `update_user` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新用户ID',
  `front_file_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '身份证正面照片',
  `back_file_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '身份证反面照片',
  `photo_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '真实照片',
   createTime           datetime         null comment '创建时间',
   updateTimes           datetime        null comment '更新时间',
   state               varchar(32)       null default 'AVAILABLE' comment '记录状态（可用AVAILABLE删除DELETE禁用FORBID）',
   versions              bigint          null default 0 comment '版本号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_2` (`username`,`write_off`,`merchant_id`),
  KEY `AK_invite_code` (`invite_code`),
  KEY `add_time` (`add_time`),
  KEY `user_name` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=5109397 DEFAULT CHARSET=utf8 COMMENT='用户主表';


DROP TABLE IF EXISTS `yhb_user_tag`;
create table yhb_user_tag 
(
  `id` varchar(32) NOT NULL comment '主键ID',
   createTime           datetime         null comment '创建时间',
   updateTimes           datetime        null comment '更新时间',
   state               varchar(32)       null default 'AVAILABLE' comment '记录状态（可用AVAILABLE删除DELETE禁用FORBID）',
   versions              bigint          null default 0 comment '版本号',
   name                 varchar(32)      null comment '标签名称',
   tagType              int(1)           null comment '标签类型（0:系统标签  1:固定名单）',
   num                  int              null comment '数量',
   phones               varchar(4000)    null comment '号码集合',
   userIds              varchar(4000)    null comment '用户id集合',
   PRIMARY KEY (`id`),
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='用户系统标签表';


DROP TABLE IF EXISTS `yhb_message_receive`;
CREATE TABLE `yhb_message_receive` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
   createTime           datetime         null comment '创建时间',
   updateTimes           datetime        null comment '更新时间',
   state               varchar(32)       null default 'AVAILABLE' comment '记录状态（可用AVAILABLE删除DELETE禁用FORBID）',
   versions              bigint          null default 0 comment '版本号',
  `user_id` bigint(20) DEFAULT NULL COMMENT '推送的唯一目标',
  `type` varchar(30) DEFAULT NULL COMMENT '发送信息的类型（自定义）',
  `content` varchar(500) DEFAULT NULL COMMENT '短信内容',
  `extension` varchar(200) DEFAULT NULL,
  `status` int(11) DEFAULT NULL COMMENT '是否发送成功（0：未成功；1：成功）',
  `read_flag` int(11) DEFAULT NULL,
  `add_time` bigint(20) DEFAULT NULL,
  `add_user` bigint(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  `update_user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `type` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=5248782 DEFAULT CHARSET=utf8 COMMENT='消息中心';



DROP TABLE IF EXISTS `yhb_msg_push`;
create table yhb_msg_push (
  `id` varchar(32) NOT NULL comment '主键ID',
   createTime           datetime         null comment '创建时间',
   updateTimes           datetime        null comment '更新时间',
   state               varchar(32)       null default 'AVAILABLE' comment '记录状态（可用AVAILABLE删除DELETE禁用FORBID）',
   versions              bigint          null default 0 comment '版本号',
   content              varchar(256)     null comment '内容',
   sendType             int(1)           null comment '发送方式（0立即  1定时）',
   sendTime             datetime         null comment '发送时间',
   merchantId           bigint(20)       null comment 'app分类表id',
   mobiles              varchar(2048)    null comment '手机号集合',
   sendStatus           int(1)           null comment '发送状态（0:未发送  1:发送中 2:发送成功 3:发送失败）',
   isJump               int(1)           null comment '是否跳转（0:不跳转  1:跳转）',
   sendPersonType       int(1)           null comment '发送人类型（0:全部 1:固定名单 2:系统标签 3:自定义）',
   fixedName            varchar(1024)    null comment '固定名单集合',
   sysTag               varchar(1024)    null comment '系统标签集合',
   customName           varchar(1024)    null comment '自定义名单集合',
   openType             int(1)           null comment '打开方式(0:app内部 1:外部浏览器)',
   needLogin            int(1)           null comment '是否自动登录(0:否  1:是)',
   url                  varchar(256)     null comment '链接',
   msgOrPush            varchar(32)     null comment '发送类型 msg:消息 push:推送',
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='消息推送表';

DROP TABLE IF EXISTS `yhb_banner`;
CREATE TABLE `yhb_banner` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` varchar(16) NOT NULL COMMENT '适用的app版本号，支持通配符%(代替0或多个字符)，如：1.0.0/1.0.%',
  `add_product` varchar(30) NOT NULL COMMENT '支持通配符%(代替0或多个字符)',
  `add_channel` varchar(30) NOT NULL COMMENT '支持通配符%(代替0或多个字符)',
  `src` varchar(5000) NOT NULL COMMENT '图片url',
  `seq` int(11) NOT NULL COMMENT '顺序，从小大排列',
  `target` varchar(16) NOT NULL COMMENT 'self:  app内部打开, blank: 浏览器打开',
  `href` varchar(256) NOT NULL COMMENT '跳转地址，如果需要登录，跳转时请加sid参数',
  `type` varchar(16) NOT NULL COMMENT '取值： img: 仅图片展示；href: 带超链接的图片, 超链接由字段href定义，repeatedMsg:反复显示的文本消息，oneTimeMsg:一次性消息',
  `need_login` tinyint(4) NOT NULL COMMENT '跳转链接页是否需要登录，如果需要，建议app先引导登录',
  `location` varchar(32) NOT NULL COMMENT '页面位置：支持通配符%(代替0或多个字符)：homepage:首页, userinfo:我的资料,homepage_top:首页轮播,start:启动页,activity:活动弹窗,',
  `enabled` tinyint(4) NOT NULL COMMENT '是否可用,1:是, 0:否',
  `merchant_id` bigint(20) NOT NULL DEFAULT '1' COMMENT '商户ID',
  `add_user` bigint(20) NOT NULL COMMENT '添加用户ID',
  `add_time` bigint(20) NOT NULL COMMENT '添加时间',
  `update_time` bigint(20) NOT NULL COMMENT '更新时间, unix时间戳（毫秒）,修改任何字段(除本字段外)都应更新此字段.',
  `update_user` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新用户ID',
  `description` varchar(1024) NOT NULL DEFAULT '' COMMENT '描述',
  `title` varchar(64) DEFAULT '',
  `weight` int(10) DEFAULT '10',
  `name` varchar(64)  DEFAULT '' COMMENT '名称',
  `startType` varchar(64)  DEFAULT '' COMMENT '启动方式  rightNow（立即启用） timing（定时启动） ',
   timingStartTime           datetime            null comment '定时启动时间',
   createTime           datetime            null comment '创建时间',
   updateTimes           datetime            null comment '更新时间',
   state               varchar(32)         null default 'AVAILABLE' comment '记录状态（可用AVAILABLE删除DELETE禁用FORBID）',
   versions              bigint              null default 0 comment '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=170 DEFAULT CHARSET=utf8 COMMENT='banner图模型';


DROP TABLE IF EXISTS `yhb_system_config`;
CREATE TABLE `yhb_system_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `category` varchar(32) NOT NULL COMMENT '配置项类别',
  `name` varchar(128) NOT NULL COMMENT '配置项',
  `value` varchar(5000) DEFAULT NULL COMMENT '取值',
  `remark` varchar(2000) DEFAULT NULL,
  `add_time` bigint(20) DEFAULT '0' COMMENT '添加时间, unix时间戳（毫秒）',
  `add_user` bigint(20) DEFAULT '0' COMMENT '添加用户ID',
  `update_time` bigint(20) DEFAULT '0' COMMENT '更新时间, unix时间戳（毫秒）,修改任何字段(除本字段外)都应更新此字段.',
  `update_user` bigint(20) DEFAULT '0' COMMENT '更新用户ID',
   createTime           datetime            null comment '创建时间',
   updateTimes           datetime            null comment '更新时间',
   state               varchar(32)         null default 'AVAILABLE' comment '记录状态（可用AVAILABLE删除DELETE禁用FORBID）',
   versions              bigint              null default 0 comment '版本号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `AK_name` (`category`,`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=120 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `yhb_system_cfg`;
CREATE TABLE `yhb_system_cfg` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `key` varchar(64) NOT NULL DEFAULT '' COMMENT '配置的键',
  `value` varchar(240) NOT NULL DEFAULT '' COMMENT '配置的值',
  `remark` varchar(200) NOT NULL DEFAULT '' COMMENT '配置的备注',
  `add_time` bigint(20) NOT NULL DEFAULT '0',
  `update_time` bigint(20) NOT NULL DEFAULT '0',
  `add_user` bigint(20) NOT NULL DEFAULT '0',
  `update_user` bigint(20) NOT NULL DEFAULT '0',
   createTime           datetime            null comment '创建时间',
   updateTimes           datetime            null comment '更新时间',
   state               varchar(32)         null default 'AVAILABLE' comment '记录状态（可用AVAILABLE删除DELETE禁用FORBID）',
   versions              bigint              null default 0 comment '版本号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `key` (`key`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `yhb_loan_product_repay_priority`;
CREATE TABLE `yhb_loan_product_repay_priority` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) NOT NULL COMMENT '项目id',
  `fee_id` bigint(20) NOT NULL COMMENT '费用id，参照miaobt_loan_product_fee',
  `priority` int(11) NOT NULL DEFAULT '1' COMMENT '值越小，优先级越高',
  `add_time` bigint(20) NOT NULL DEFAULT '0',
  `add_user` bigint(20) NOT NULL DEFAULT '0',
  `update_time` bigint(20) NOT NULL DEFAULT '0',
  `update_user` bigint(20) NOT NULL DEFAULT '0',
   createTime           datetime            null comment '创建时间',
   updateTimes           datetime            null comment '更新时间',
   state               varchar(32)         null default 'AVAILABLE' comment '记录状态（可用AVAILABLE删除DELETE禁用FORBID）',
   versions              bigint              null default 0 comment '版本号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `AK_product_fee` (`product_id`,`fee_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8 COMMENT='还款的冲账顺序';

DROP TABLE IF EXISTS `yhb_loan_product_pay_plan`;
CREATE TABLE `yhb_loan_product_pay_plan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) NOT NULL COMMENT '借款产品ID',
  `code` varchar(32) NOT NULL DEFAULT '' COMMENT '代码',
  `name` varchar(32) DEFAULT '' COMMENT '名称',
  `mch_code` varchar(32) NOT NULL DEFAULT '' COMMENT '资方',
  `add_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '添加时间',
  `add_user` bigint(20) NOT NULL DEFAULT '0' COMMENT '添加用户ID',
  `update_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新时间, unix时间戳（毫秒）,修改任何字段(除本字段外)都应更新此字段.',
  `update_user` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新用户ID',
   createTime           datetime            null comment '创建时间',
   updateTimes           datetime            null comment '更新时间',
   state               varchar(32)         null default 'AVAILABLE' comment '记录状态（可用AVAILABLE删除DELETE禁用FORBID）',
   versions              bigint              null default 0 comment '版本号',
  PRIMARY KEY (`id`),
  KEY `AK_product` (`product_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='产品支付计划';



DROP TABLE IF EXISTS `yhb_pay_deduct_category_detail`;
CREATE TABLE `yhb_pay_deduct_category_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) NOT NULL COMMENT '借款产品ID',
  `category_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '产品支付计划id',
  `fee_id` bigint(20) NOT NULL COMMENT '产品费用id，参照miaobt_loan_product_fee',
  `fee_name` varchar(64) NOT NULL COMMENT '产品费用名称，参照miaobt_loan_product_fee',
  `fee_fixed_flag` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否固定金额，1-是，0-否',
  `fee_fixed_amount` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT '费用固定金额',
  `fee_rate` decimal(20,6) NOT NULL DEFAULT '0.000000' COMMENT '费率（千分比，年化，每年计360天）',
  `fee_base_amount` varchar(32) NOT NULL DEFAULT '' COMMENT '费用计算基数：\n                        capital:   本金\n                        contract: 合同金额',
  `add_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '添加时间',
  `add_user` bigint(20) NOT NULL DEFAULT '0' COMMENT '添加用户ID',
  `update_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新时间, unix时间戳（毫秒）,修改任何字段(除本字段外)都应更新此字段.',
  `update_user` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新用户ID',
   createTime           datetime            null comment '创建时间',
   updateTimes           datetime            null comment '更新时间',
   state               varchar(32)         null default 'AVAILABLE' comment '记录状态（可用AVAILABLE删除DELETE禁用FORBID）',
   versions              bigint              null default 0 comment '版本号',
  PRIMARY KEY (`id`),
  KEY `AK_product` (`product_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8 COMMENT='支付计划扣款明细';

DROP TABLE IF EXISTS `yhb_pay_deduct_category`;
CREATE TABLE `yhb_pay_deduct_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) NOT NULL COMMENT '借款产品ID',
  `pay_plan_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '产品支付计划id',
  `merchant_code` varchar(32) NOT NULL DEFAULT '' COMMENT '资金方商户代码：\n            nanyue:南粤银行',
  `add_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '添加时间',
  `add_user` bigint(20) NOT NULL DEFAULT '0' COMMENT '添加用户ID',
  `update_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新时间, unix时间戳（毫秒）,修改任何字段(除本字段外)都应更新此字段.',
  `update_user` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新用户ID',
   createTime           datetime            null comment '创建时间',
   updateTimes           datetime            null comment '更新时间',
   state               varchar(32)         null default 'AVAILABLE' comment '记录状态（可用AVAILABLE删除DELETE禁用FORBID）',
   versions              bigint              null default 0 comment '版本号',
  PRIMARY KEY (`id`),
  KEY `AK_product` (`product_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='支付计划扣款类别';


DROP TABLE IF EXISTS `yhb_product_usertype_map`;
CREATE TABLE `yhb_product_usertype_map` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `merchant_id` bigint(20) NOT NULL DEFAULT '1' COMMENT '商户id',
  `user_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '用户类型 1：新户，2：次新户 3：老户 4:秒白条老户',
  `product_id` bigint(20) NOT NULL COMMENT '借款产品ID',
  `enabled` tinyint(4) NOT NULL COMMENT '是否可用 1:是 0:否',
  `add_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '添加时间',
  `add_user` bigint(20) NOT NULL DEFAULT '0' COMMENT '添加用户ID',
  `update_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新时间, unix时间戳（毫秒）,修改任何字段(除本字段外)都应更新此字段.',
   createTime           datetime            null comment '创建时间',
   updateTimes           datetime            null comment '更新时间',
   state               varchar(32)         null default 'AVAILABLE' comment '记录状态（可用AVAILABLE删除DELETE禁用FORBID）',
   versions              bigint              null default 0 comment '版本号',
  `update_user` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新用户ID',
  PRIMARY KEY (`id`),
  KEY `user_type` (`user_type`) USING BTREE,
  KEY `AK_product` (`product_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='产品和用户类型的映射表';


DROP TABLE IF EXISTS `yhb_loan_product_fee`;
CREATE TABLE `yhb_loan_product_fee` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) NOT NULL COMMENT '借款产品ID',
  `name` varchar(512) NOT NULL COMMENT '费用名称',
  `payment_type` enum('prepaid','normal','refund') NOT NULL DEFAULT 'normal' COMMENT '付费方式\n                        prepaid：预付\n                        normal：正常还款\n                        refund：退还',
  `charge_method` varchar(32) NOT NULL DEFAULT '' COMMENT '收取方式：\n                        fixed:                      一次性收取，\n                        by_period:              按期收取，\n                        overdue_pay_day: 逾期后扣款日收取\n                        overdue_days:       逾期后天数',
  `fee_type` varchar(32) NOT NULL DEFAULT '' COMMENT '费用类型：\n                        approve: 审核费\n                        trade: 交易费\n                        guarantee: 担保费',
  `calculate_type` varchar(32) NOT NULL DEFAULT 'repay_day' COMMENT '计算周期（冲账用）:\n                        repay_day:  扣款日收\n                        overdue:  逾期收',
  `enabled` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否可用，1-是 0-否',
  `charge_begin_day` int(11) NOT NULL DEFAULT '0' COMMENT '计费开始天数，如：逾期第5天开始',
  `charge_end_day` int(11) NOT NULL DEFAULT '0' COMMENT '计费结束天数，如：逾期第10天结束',
  `add_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '添加时间',
  `add_user` bigint(20) NOT NULL DEFAULT '0' COMMENT '添加用户ID',
  `update_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新时间, unix时间戳（毫秒）,修改任何字段(除本字段外)都应更新此字段.',
  `update_user` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新用户ID',
   createTime           datetime            null comment '创建时间',
   updateTimes           datetime            null comment '更新时间',
   state               varchar(32)         null default 'AVAILABLE' comment '记录状态（可用AVAILABLE删除DELETE禁用FORBID）',
   versions              bigint              null default 0 comment '版本号',
  PRIMARY KEY (`id`),
  KEY `AK_product` (`product_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8 COMMENT='产品费用';


DROP TABLE IF EXISTS `yhb_loan_product`;
CREATE TABLE `yhb_loan_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `merchant_id` bigint(20) NOT NULL DEFAULT '1' COMMENT '商户id',
  `name` varchar(512) NOT NULL COMMENT '名称',
  `loan_type` enum('cash','credit','cash_period','consumption') NOT NULL COMMENT 'credit,信用借款',
  `max_amount` decimal(10,2) NOT NULL COMMENT '最大额度',
  `min_amount` decimal(10,2) NOT NULL COMMENT '最小额度',
  `amount_step` decimal(10,0) NOT NULL COMMENT '额度增加步长，固定额度取值为0',
  `installment_period` int(11) NOT NULL DEFAULT '-1',
  `repayment_method` tinyint(4) NOT NULL COMMENT '分期贷款的还款方式，不分期的固定为:4,\n            1:等额本息还款\n            2:等额本金还款\n            3:每期还息到期还本\n            4:一次性利随本清\n            5:等本等息\n            6:zk等本等息',
  `pay_day_value` varchar(64) NOT NULL COMMENT '还款日值，根据pay_day_type决定，\n            固定日期：日期值\n            放款日：    按放款日期推算',
  `pay_day_type` varchar(32) NOT NULL COMMENT '还款日类型：\n            contract_date: 放款日\n            fixed_date: 每月固定日期',
  `grace_day` int(11) NOT NULL COMMENT '宽限天数',
  `enabled` tinyint(4) NOT NULL COMMENT '是否可用',
  `add_time` bigint(20) NOT NULL COMMENT '添加时间',
  `add_user` bigint(20) NOT NULL COMMENT '添加用户ID',
  `update_time` bigint(20) NOT NULL COMMENT '更新时间, unix时间戳（毫秒）,修改任何字段(除本字段外)都应更新此字段.',
  `update_user` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新用户ID',
  `start_day_type` enum('lend_day','lend_next_day') NOT NULL DEFAULT 'lend_next_day' COMMENT '起息日类型，lend_day：放款日，lend_next_day：放款日下一天',
  `fee_charge_type` enum('postpaid','prepaid') NOT NULL DEFAULT 'prepaid' COMMENT '费用收取方式, postpaid:后付费, prepaid:预付费',
  `secured_type` varchar(32) NOT NULL DEFAULT 'credit_card_secured' COMMENT '担保类型：credit_card_secured：信用卡预授权担保，credit_card_unsecured：有信用卡无担保，unsecured：无信用卡无担保',
  `pre_auth_percent` int(11) NOT NULL DEFAULT '0' COMMENT '信用卡预授权比例',
  `period_type` enum('daily','weekly','monthly') NOT NULL COMMENT '期数类型:: daily：不分期，period_value值为天数; weekly：每期1周，period_value值为期数;  monthly：每期1月，period_value值为期数',
  `period_value` int(11) NOT NULL COMMENT '根据period_type:: daily：1表示1天; weekly：1表示1周; monthly：1表示1个月',
  `interest_rate` decimal(20,6) NOT NULL COMMENT '利率（千分比，年化，每年计360天）',
  `service_fee_create_type` tinyint(4) DEFAULT '2' COMMENT '1:平摊每期， 2：服务费放在第一期',
  `period_max` int(10) DEFAULT '0' COMMENT '最大期数',
  `period_min` int(11) DEFAULT '0' COMMENT '最小期数',
  `period_step` int(11) DEFAULT '0' COMMENT '期数步数',
  `max_overdue_fee_rate` decimal(20,6)  COMMENT '逾期费达本金封顶费率',

   createTime           datetime            null comment '创建时间',
   updateTimes           datetime            null comment '更新时间',
   state               varchar(32)         null default 'AVAILABLE' comment '记录状态（可用AVAILABLE删除DELETE禁用FORBID）',
   versions              bigint              null default 0 comment '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='产品表 此表记录只增不减';


/*Table structure for table `yhb_selected_information` */
DROP TABLE IF EXISTS `yhb_selected_information`;
create table yhb_selected_information 
(
  `id` varchar(32) NOT NULL comment '主键ID',
   createTime           datetime            null comment '创建时间',
   updateTimes           datetime            null comment '更新时间',
   state               varchar(32)         null default 'AVAILABLE' comment '记录状态（可用AVAILABLE删除DELETE禁用FORBID）',
   versions              bigint              null default 0 comment '版本号',
   isMoreContact        int(1)              null comment '更多联系人是否启用（0不启用 1启用）',
   moreContactAmount    decimal(10,2)       null comment '更多联系人额度',
   isSesameCredit       int(1)              null comment '芝麻信用是否启用（0不启用 1启用）',
   sesameCreditAmount   decimal(10,2)       null comment '芝麻信用额度',
   isCompanyInformation int(1)              null comment '公司信息是否启用（0不启用 1启用）',
   companyInformationAmount decimal(10,2)   null comment '公司信息额度',
   isFamilyInformation  int(1)              null comment '家庭信息是否启用（0不启用 1启用）',
   familyInformationAmount decimal(10,2)    null comment '家庭信息额度',
   merchantId           bigint(20)       null comment 'app商户表id',
   constraint PK_YHB_SELECTED_INFORMATION primary key clustered (id)
);
alter table yhb_selected_information comment '选填资料表';


/*Table structure for table `yhb_required_information` */
DROP TABLE IF EXISTS `yhb_required_information`;
create table yhb_required_information 
(
  `id` varchar(32) NOT NULL comment '主键ID',
   createTime           datetime          null comment '创建时间',
   updateTimes           datetime          null comment '更新时间',
   state               varchar(32)       null default 'AVAILABLE' comment '记录状态（可用AVAILABLE删除DELETE禁用FORBID）',
   versions              bigint            null default 0 comment '版本号',
   isIdentity           int(1)            null comment '身份认证是否启用（0不启用 1启用）',
   identityAmount       decimal(10,2)     null comment '身份认证额度',
   isContact            int(1)            null comment '联系人认证是否启用（0不启用 1启用）',
   contactAmount        decimal(10,2)     null comment '联系人认证额度',
   isOperator           int(1)            null comment '运营商认证是否启用（0不启用 1启用）',
   operatorAmount       decimal(10,2)     null comment '运营商认证额度',
   isBankcard           int(1)            null comment '银行卡认证是否启用（0不启用 1启用）',
   bankcardAmount       decimal(10,2)     null comment '银行卡认证额度',
   merchantId           bigint(20)       null comment 'app商户表id',
   constraint PK_YHB_REQUIRED_INFORMATION primary key clustered (id)
);
alter table yhb_required_information comment '必填资料表';

/*Table structure for table `yhb_quota` */
DROP TABLE IF EXISTS `yhb_quota`;
create table yhb_quota 
(
  `id` varchar(32) NOT NULL comment '主键ID',
   createTime           datetime         null comment '创建时间',
   updateTimes           datetime         null comment '更新时间',
   state               varchar(32)      null default 'AVAILABLE' comment '记录状态（可用AVAILABLE删除DELETE禁用FORBID）',
   versions              bigint           null default 0 comment '版本号',
   newMerberAmount      decimal(10,2)    null comment '新用户额度',
   subNewMerberAmount   decimal(10,2)    null comment '次新户额度',
   oldMemberAmount      decimal(10,2)    null comment '老用户额度',
   merchantId           bigint(20)       null comment 'app商户表id',
	constraint PK_YHB_QUOTA primary key clustered (id)
);
alter table yhb_quota comment 'app额度表';

/*Table structure for table `yhb_merchant` */

DROP TABLE IF EXISTS `yhb_merchant`;
CREATE TABLE `yhb_merchant` (
   id                   bigint(20) NOT NULL AUTO_INCREMENT comment '主键ID',
   createTime           datetime          null comment '创建时间',
   updateTimes           datetime          null comment '更新时间',
   state               varchar(32)       null default 'AVAILABLE' comment '记录状态（可用AVAILABLE删除DELETE禁用FORBID）',
   versions              bigint            null default 0 comment '版本号',
  `merchant_name` varchar(50) DEFAULT '' COMMENT '合作商名称',
  `merchant_code` varchar(20) NOT NULL DEFAULT '' COMMENT '合作商代码(在SAAS平台的身份唯一标识)',
  `linkman` varchar(50) DEFAULT '' COMMENT '联系人',
  `tel` varchar(20) NOT NULL DEFAULT '' COMMENT '联系电话',
  `status` bit(1) DEFAULT b'0' COMMENT '0 表示下线（禁用），1表示上线（启用）',
  `website` varchar(225) NOT NULL DEFAULT '' COMMENT '官网地址',
  `is_verify_card` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否付费验卡（0：是 1：否）',
  `add_time` bigint(20) DEFAULT NULL COMMENT '添加 时间',
  `update_time` bigint(20) NOT NULL COMMENT '最后一次修改时间',
  `add_user` varchar(255) DEFAULT '',
  `update_user` varchar(255) NOT NULL,
  `companyId`   varchar(32)  null  comment '公司id',
  `remark`  varchar(1024) null  comment '简介',
  `logo`  varchar(1024) null  comment 'logo',
  PRIMARY KEY (`id`),
  UNIQUE KEY `merchant` (`merchant_code`) USING BTREE,
  UNIQUE KEY `merchantname` (`merchant_name`) USING BTREE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;
alter table yhb_merchant comment 'app商户表';

/*Table structure for table `yhb_saas_access` */

DROP TABLE IF EXISTS `yhb_saas_access`;

CREATE TABLE `yhb_saas_access` (
  `id` varchar(32) NOT NULL,
  `createTime` datetime DEFAULT NULL,
  `state` varchar(32) DEFAULT NULL,
  `updateTimes` datetime DEFAULT NULL,
  `menuId` varchar(32) DEFAULT NULL,
  `roleId` varchar(32) DEFAULT NULL,
  `userId` varchar(32) DEFAULT NULL,
  `menuMethodId` varchar(32) DEFAULT NULL,
  `versions` bigint(11) DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`) USING BTREE,
  KEY `FKAB27B5642DF75C78` (`menuId`) USING BTREE,
  KEY `FKAB27B564370DC8E6` (`roleId`) USING BTREE,
  KEY `FKAB27B5643C631E50` (`userId`) USING BTREE,
  KEY `FKAB27B5641C6B59F3` (`menuMethodId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `yhb_saas_access` */

insert  into `yhb_saas_access`(`id`,`createTime`,`state`,`updateTimes`,`menuId`,`roleId`,`userId`,`menuMethodId`,`versions`) values ('402881ca549e5fc101549e66e4ba00dd','2016-05-11 14:00:23','AVAILABLE','2016-05-11 14:00:23','40288092394735dd01394735f3x30002','402880923bca990e013bca99afa30001',NULL,NULL,0),('402881ca549e5fc101549e66e4fb00ec','2016-05-11 14:00:23','AVAILABLE','2016-05-11 14:00:23','402880f13d675476013d679ad2410066','402880923bca990e013bca99afa30001',NULL,NULL,0),('402881ca549e5fc101549e66ec7f00fb','2016-05-11 14:00:25','AVAILABLE','2016-05-11 14:00:25','40288092394735dd01394735f3x30008','402880923bca990e013bca99afa30001',NULL,NULL,0),('402881ca549e5fc101549e66ec8200fc','2016-05-11 14:00:25','AVAILABLE','2016-05-11 14:00:25','40288092384735dd01394735f3x30070','402880923bca990e013bca99afa30001',NULL,NULL,0),('402881ca549e5fc101549e66eda00131','2016-05-11 14:00:25','AVAILABLE','2016-05-11 14:00:25','40288092394735dd01394735f3x30011','402880923bca990e013bca99afa30001',NULL,NULL,0),('402881d35aabe8bd015aabf0d7dc0002','2017-03-08 11:22:52','AVAILABLE','2017-03-08 11:22:52','402881d35aabe8bd015aabf072460001','402880923bca990e013bca99afa30001',NULL,NULL,0),('402881d35ac63134015ac635d9000001','2017-03-13 13:48:22','AVAILABLE','2017-03-13 13:48:22','402881d35ac63134015ac635abbd0000','402880923bca990e013bca99afa30001',NULL,NULL,0),('8a28b50f5c14519f015c1eaba7d203c9','2017-05-19 11:06:25','AVAILABLE','2017-05-19 11:06:25','40288092394735dd01394735f3x30011','402880923bca990e013bca99afa30001',NULL,NULL,0),('8a28b50f5c14519f015c1eae55ac03ce','2017-05-19 11:09:20','AVAILABLE','2017-05-19 11:09:20','40288092394735dd01394735f3x30011','402880923bca990e013bca99afa30001',NULL,NULL,0),('8a28b50f5c14519f015c1f69f6b00588','2017-05-19 14:34:17','AVAILABLE','2017-05-19 14:34:17','40288092394735dd01394735f3x30011','402880923bca990e013bca99afa30001',NULL,NULL,0),('8a28b50f5c14519f015c2eb0e09f0f9d','2017-05-22 13:46:02','AVAILABLE','2017-05-22 13:46:02','40288092394735dd01394735f3x30011','402880923bca990e013bca99afa30001',NULL,NULL,0);

/*Table structure for table `yhb_saas_menu` */

DROP TABLE IF EXISTS `yhb_saas_menu`;

CREATE TABLE `yhb_saas_menu` (
  `id` varchar(32) NOT NULL,
  `createTime` datetime DEFAULT NULL,
  `state` varchar(32) DEFAULT NULL,
  `updateTimes` datetime DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `menuName` varchar(32) DEFAULT NULL,
  `menuPath` varchar(256) DEFAULT NULL,
  `tagName` varchar(128) DEFAULT NULL,
  `sort` varchar(64) DEFAULT NULL,
  `upId` varchar(32) DEFAULT NULL,
  `menuIconCss` varchar(128) DEFAULT NULL,
  `remark` varchar(128) DEFAULT NULL,
  `menuType` varchar(32) DEFAULT 'INTERNAL',
  `versions` bigint(11) DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`) USING BTREE,
  KEY `FK33155F6E6AF6B4` (`upId`) USING BTREE,
  KEY `FK33155F7D25BA7B` (`upId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `yhb_saas_menu` */

insert  into `yhb_saas_menu`(`id`,`createTime`,`state`,`updateTimes`,`level`,`menuName`,`menuPath`,`tagName`,`sort`,`upId`,`menuIconCss`,`remark`,`menuType`,`versions`) values ('40288092384735dd01394735f3x30070','2012-10-29 11:46:23','AVAILABLE','2017-03-26 17:01:17',1,'角色信息管理','/admin/role/index.do?state=AVAILABLE','role','Z01-02-03','40288092394735dd01394735f3x30008','glyphicon glyphicon-plane','','INTERNAL',0),('40288092394735dd01394735f3x30002','2012-10-29 11:46:23','AVAILABLE','2017-03-26 16:56:28',1,'菜单信息管理','/admin/menu/index.do?state=AVAILABLE','menu','Z01-02-01','40288092394735dd01394735f3x30008','glyphicon glyphicon-folder-open','','INTERNAL',0),('40288092394735dd01394735f3x30008','2013-09-23 10:45:18','AVAILABLE','2017-03-26 17:04:36',0,'系统后台配置','','access_mg','Z01-01-01',NULL,'glyphicon glyphicon-off','','INTERNAL',0),('40288092394735dd01394735f3x30011','2012-10-29 11:46:23','AVAILABLE','2017-03-26 17:02:15',1,'用户信息管理','/admin/user/index.do?state=AVAILABLE','user','Z01-02-05','40288092394735dd01394735f3x30008','glyphicon glyphicon-education','','INTERNAL',0),('402880f13d675476013d679ad2410066','2013-05-23 12:01:34','AVAILABLE','2017-03-26 16:59:35',1,'菜单方法管理','/admin/menuMethod/index.do?state=AVAILABLE','menuMethod','Z01-02-02','40288092394735dd01394735f3x30008','glyphicon glyphicon-alert','','INTERNAL',0),('402881d35aabe8bd015aabf072460001','2017-03-08 11:22:26','AVAILABLE','2017-03-26 17:04:18',1,'角色权限管理','/admin/role/roleOperator.do','role','Z01-02-04','40288092394735dd01394735f3x30008','glyphicon glyphicon-ok','','INTERNAL',0),('402881d35ac63134015ac635abbd0000','2017-03-13 13:48:10','AVAILABLE','2017-03-26 17:02:41',1,'用户密码修改','/admin/user/modifyPassword.do','user','Z01-02-06','40288092394735dd01394735f3x30008','glyphicon glyphicon-lock','后台登入用户密码修改','INTERNAL',0);

/*Table structure for table `yhb_saas_menu_method` */

DROP TABLE IF EXISTS `yhb_saas_menu_method`;

CREATE TABLE `yhb_saas_menu_method` (
  `id` varchar(32) NOT NULL,
  `createTime` datetime DEFAULT NULL,
  `state` varchar(32) DEFAULT NULL,
  `updateTimes` datetime DEFAULT NULL,
  `methodName` varchar(32) DEFAULT NULL,
  `methodTagName` varchar(32) DEFAULT NULL,
  `remark` varchar(128) DEFAULT NULL,
  `menuId` varchar(32) DEFAULT NULL,
  `isSelected` int(11) DEFAULT NULL,
  `versions` bigint(11) DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`) USING BTREE,
  KEY `FK56E0BFC16337A0D1` (`menuId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统菜单支持方法信息表';

/*Data for the table `yhb_saas_menu_method` */

/*Table structure for table `yhb_saas_role` */

DROP TABLE IF EXISTS `yhb_saas_role`;

CREATE TABLE `yhb_saas_role` (
  `id` varchar(32) NOT NULL,
  `createTime` datetime DEFAULT NULL,
  `state` varchar(32) DEFAULT NULL,
  `updateTimes` datetime DEFAULT NULL,
  `level` int(4) DEFAULT '0',
  `remark` varchar(512) DEFAULT NULL,
  `roleName` varchar(32) DEFAULT NULL,
  `upId` varchar(32) DEFAULT NULL,
  `versions` bigint(11) DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK3580766E6D61CB` (`upId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `role` */

insert  into `yhb_saas_role`(`id`,`createTime`,`state`,`updateTimes`,`level`,`remark`,`roleName`,`upId`,`versions`) values ('402880923bca990e013bca99afa30001','2012-12-24 09:49:27','AVAILABLE','2017-05-18 14:20:32',1,'系统管理员','系统管理员','402880923bca990e013bca99afa30001',0);

/*Table structure for table `yhb_saas_roles_users` */

DROP TABLE IF EXISTS `yhb_saas_roles_users`;

CREATE TABLE `yhb_saas_roles_users` (
  `roleId` varchar(32) NOT NULL,
  `userId` varchar(32) NOT NULL,
  KEY `FK2E94BE06370DC8E6` (`roleId`),
  KEY `FK2E94BE063C631E50` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `yhb_saas_roles_users` */

insert  into `yhb_saas_roles_users`(`roleId`,`userId`) values ('402880923bca990e013bca99afa30001','402880d83ec49a7d013ec4d4151c0001'),('402880923bca990e013bca99afa30001','ff8080815fd754c7015fe7fe830d0004');

/*Table structure for table `yhb_saas_user` */

DROP TABLE IF EXISTS `yhb_saas_user`;

CREATE TABLE `yhb_saas_user` (
  `id` varchar(32) NOT NULL,
  `createTime` datetime DEFAULT NULL,
  `state` varchar(32) DEFAULT NULL,
  `updateTimes` datetime DEFAULT NULL,
  `loginName` varchar(32) NOT NULL,
  `loginPwd` varchar(32) NOT NULL,
  `userName` varchar(32) DEFAULT NULL,
  `mobile` varchar(32) DEFAULT NULL,
  `email` varchar(32) DEFAULT NULL,
  `remark` text,
  `jobNumber` varchar(32) DEFAULT NULL COMMENT '工号',
  `accountOpenId` varchar(50) DEFAULT NULL COMMENT '通行证账号',
  `versions` bigint(11) DEFAULT '0' COMMENT '版本号',
  `lastLoginTime` datetime DEFAULT NULL COMMENT '最后登录时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `yhb_saas_user` */

insert  into `yhb_saas_user`(`id`,`createTime`,`state`,`updateTimes`,`loginName`,`loginPwd`,`userName`,`mobile`,`email`,`remark`,`jobNumber`,`accountOpenId`,`versions`,`lastLoginTime`) values ('402880d83ec49a7d013ec4d4151c0001','2013-05-21 10:03:53','AVAILABLE','2017-05-11 13:32:53','admin','E10ADC3949BA59ABBE56E057F20F883E','超级管理员','18652167520','sunhao@qq.com',NULL,NULL,NULL,0,NULL),('ff8080815fd754c7015fe7fe830d0004','2017-11-23 16:28:57','AVAILABLE','2017-11-23 16:28:57','18716727740','73A9745DD615457195C092120FEAC554','孙浩','18716727740','sunhao@myqifa.com',NULL,NULL,'3f5ca44160f73e66a52935cc31a618fc',0,NULL);
