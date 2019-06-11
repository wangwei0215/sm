/*
SQLyog Enterprise v12.09 (64 bit)
MySQL - 5.7.13-log : Database - data_db
*********************************************************************
*/
DROP TABLE IF EXISTS `tb_cgsm`;
CREATE TABLE `tb_cgsm` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `year` int(4) NOT NULL COMMENT '年份',
  `day` int(2) NOT NULL COMMENT '月份',
  `month` int(2) NOT NULL COMMENT '日',
  `hour` varchar(20) NOT NULL COMMENT '小时',
	`content` mediumtext COMMENT '内容', 
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTimes` datetime DEFAULT NULL COMMENT '更新时间',
  `state` varchar(32) DEFAULT 'AVAILABLE' COMMENT '记录状态（可用AVAILABLE删除DELETE禁用FORBID）',
  `versions` bigint(20) DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`),
  KEY `index_YEAR` (`year`),
  KEY `index_DAY` (`day`),
  KEY `index_MONTH` (`month`),
  KEY `index_hour` (`hour`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '称骨算命表';


/*Table structure for table `td_birth` */

DROP TABLE IF EXISTS `td_birth`;

CREATE TABLE `td_birth` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `month` INT(11) NOT NULL,
  `day` INT(11) NOT NULL,
  `content` TEXT,
  `createTime` DATETIME DEFAULT NULL,
  `updateTimes` DATETIME DEFAULT NULL,
  `state` VARCHAR(32) DEFAULT 'AVAILABLE',
  `versions` BIGINT(20) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `month_day_index` (`month`,`day`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Table structure for table `td_pair` */

DROP TABLE IF EXISTS `td_pair`;

CREATE TABLE `td_pair` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `zodiac1` CHAR(2) NOT NULL,
  `zodiac2` CHAR(2) NOT NULL,
  `content` TEXT,
  `createTime` DATETIME DEFAULT NULL,
  `updateTimes` DATETIME DEFAULT NULL,
  `state` VARCHAR(32) DEFAULT 'AVAILABLE',
  `versions` BIGINT(20) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `zodiac_index` (`zodiac1`,`zodiac2`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

