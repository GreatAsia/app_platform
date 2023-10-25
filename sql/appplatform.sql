/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.6.46 : Database - testerplatform
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`testerplatform` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `testerplatform`;

/*Table structure for table `job` */

DROP TABLE IF EXISTS `job`;

CREATE TABLE `job` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `jobName` varchar(200) NOT NULL COMMENT '任务名称',
  `jobGroup` varchar(50) NOT NULL COMMENT '任务组',
  `cron` varchar(300) NOT NULL COMMENT '时间表达式',
  `status` varchar(50) NOT NULL COMMENT '使用状态(0:禁用 1:启用)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `job` */

/*Table structure for table `pad_account` */

DROP TABLE IF EXISTS `pad_account`;

CREATE TABLE `pad_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `envName` varchar(20) NOT NULL COMMENT '环境名',
  `account` varchar(50) NOT NULL COMMENT '账号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1830 DEFAULT CHARSET=utf8;

/*Data for the table `pad_account` */


/*Table structure for table `pad_autocase` */

DROP TABLE IF EXISTS `pad_autocase`;

CREATE TABLE `pad_autocase` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `caseName` varchar(50) NOT NULL COMMENT '用例名',
  `caseDesc` text NOT NULL COMMENT '用例说明',
  `caseContent` varchar(50) NOT NULL COMMENT '用例中文名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=160 DEFAULT CHARSET=utf8;

/*Data for the table `pad_autocase` */


/*Table structure for table `performance_history` */

DROP TABLE IF EXISTS `performance_history`;

CREATE TABLE `performance_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `runId` int(11) NOT NULL COMMENT '运行id',
  `name` varchar(50) NOT NULL COMMENT 'app/rom',
  `type` varchar(50) NOT NULL COMMENT '内存/耗电量/流量/启动时间/cpu/帧率',
  `serialno` varchar(50) NOT NULL COMMENT '序列号',
  `preSize` float NOT NULL COMMENT '性能测试数值',
  `unit` varchar(50) NOT NULL COMMENT '单位',
  `result` varchar(50) NOT NULL COMMENT '参考结果',
  `runTime` varchar(50) NOT NULL COMMENT '运行的时间',
  `totalTime` varchar(50) DEFAULT NULL COMMENT '运行总时间',
  `version` varchar(200) DEFAULT NULL COMMENT 'Rom版本',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=212895 DEFAULT CHARSET=utf8;

/*Data for the table `performance_history` */


/*Table structure for table `rom_autocase` */

DROP TABLE IF EXISTS `rom_autocase`;

CREATE TABLE `rom_autocase` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `caseName` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '用例名',
  `caseDesc` text COLLATE utf8_bin NOT NULL COMMENT '用例说明',
  `caseContent` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '用例中文名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `rom_autocase` */


/*Table structure for table `ui_pad_caselist` */

DROP TABLE IF EXISTS `ui_pad_caselist`;

CREATE TABLE `ui_pad_caselist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `runId` int(11) NOT NULL COMMENT '运行ID',
  `serialno` varchar(50) NOT NULL COMMENT '序列号',
  `caseName` varchar(50) NOT NULL COMMENT '用例名',
  `caseDesc` varchar(50) NOT NULL COMMENT '用例说明',
  `result` varchar(50) NOT NULL COMMENT '结果',
  `elapsedTime` varchar(50) NOT NULL COMMENT '耗时',
  `errorLog` longtext,
  `startTime` varchar(50) NOT NULL,
  `caseModule` varchar(50) NOT NULL COMMENT '所属模块',
  `testLog` text COMMENT '测试日志',
  `picturePath` varchar(5000) DEFAULT NULL COMMENT '图片路径',
  `testLogPath` varchar(2000) DEFAULT NULL COMMENT '测试日志路径',
  PRIMARY KEY (`id`),
  KEY `runId` (`runId`),
  CONSTRAINT `ui_pad_caselist_ibfk_1` FOREIGN KEY (`runId`) REFERENCES `ui_pad_runidlist` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=47847 DEFAULT CHARSET=utf8;

/*Data for the table `ui_pad_caselist` */


/*Table structure for table `ui_pad_device` */

DROP TABLE IF EXISTS `ui_pad_device`;

CREATE TABLE `ui_pad_device` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `serialno` varchar(50) NOT NULL COMMENT '序列号',
  `ip` varchar(50) DEFAULT NULL COMMENT 'Ip地址',
  `status` varchar(50) DEFAULT NULL COMMENT '状态',
  `run_from` varchar(50) DEFAULT NULL COMMENT 'usb/远程',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=121336 DEFAULT CHARSET=utf8;

/*Data for the table `ui_pad_device` */


/*Table structure for table `ui_pad_runidlist` */

DROP TABLE IF EXISTS `ui_pad_runidlist`;

CREATE TABLE `ui_pad_runidlist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `totalDevice` int(11) NOT NULL COMMENT '设备数',
  `passDevice` int(11) NOT NULL COMMENT '通过设备数',
  `failDevice` int(11) NOT NULL COMMENT '失败设备数',
  `errorDevice` int(11) NOT NULL COMMENT '错误设备数',
  `elapsedTime` varchar(50) NOT NULL COMMENT '耗时',
  `startTime` varchar(50) NOT NULL COMMENT '开始时间',
  `passRate` varchar(50) NOT NULL COMMENT '通过率',
  `runType` varchar(50) NOT NULL COMMENT 'app/rom',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1773 DEFAULT CHARSET=utf8;

/*Data for the table `ui_pad_runidlist` */


/*Table structure for table `ui_pad_serialnolist` */

DROP TABLE IF EXISTS `ui_pad_serialnolist`;

CREATE TABLE `ui_pad_serialnolist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `runId` int(11) NOT NULL COMMENT '运行ID',
  `serialno` varchar(50) NOT NULL COMMENT '序列号',
  `version` varchar(50) DEFAULT NULL COMMENT '版本号',
  `totalCase` int(11) NOT NULL COMMENT '用例总数',
  `passCase` int(11) NOT NULL COMMENT '通过用例数',
  `failCase` int(11) NOT NULL COMMENT '失败用例数',
  `errorCase` int(11) NOT NULL COMMENT '错误用例数',
  `elapsedTime` varchar(50) NOT NULL COMMENT '耗时',
  `startTime` varchar(50) NOT NULL COMMENT '开始时间',
  `passRate` varchar(50) NOT NULL COMMENT '通过率',
  `romVersion` varchar(50) NOT NULL COMMENT 'ROM版本',
  `apkVersion` varchar(2000) NOT NULL COMMENT 'APK版本',
  `netWork` varchar(50) NOT NULL COMMENT '网络',
  `env` varchar(50) NOT NULL COMMENT '环境',
  PRIMARY KEY (`id`),
  KEY `runId` (`runId`),
  CONSTRAINT `ui_pad_serialnolist_ibfk_1` FOREIGN KEY (`runId`) REFERENCES `ui_pad_runidlist` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=27005 DEFAULT CHARSET=utf8;

/*Data for the table `ui_pad_serialnolist` */



/*Table structure for table `web_assert_type` */

DROP TABLE IF EXISTS `web_assert_type`;

CREATE TABLE `web_assert_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL COMMENT '断言类型的名字',
  `is_delete` char(1) NOT NULL DEFAULT '0' COMMENT '0未删除，1删除',
  PRIMARY KEY (`id`,`name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `web_assert_type` */

insert  into `web_assert_type`(`id`,`name`,`is_delete`) values (1,'页面断言','0');

/*Table structure for table `web_assert_type_name` */

DROP TABLE IF EXISTS `web_assert_type_name`;

CREATE TABLE `web_assert_type_name` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL COMMENT '断言类型对应的name',
  `assertTypeId` bigint(20) NOT NULL COMMENT 'assert表id',
  `is_delete` char(1) NOT NULL DEFAULT '0' COMMENT '0未删除，1删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `web_assert_type_name` */


/*Table structure for table `web_case` */

DROP TABLE IF EXISTS `web_case`;

CREATE TABLE `web_case` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `platform_id` int(20) NOT NULL COMMENT '平台',
  `url` varchar(500) NOT NULL COMMENT '请求url',
  `case_desc` varchar(240) NOT NULL COMMENT '用例描述',
  `screenshot` varchar(120) DEFAULT NULL COMMENT '截图',
  `case_step` text NOT NULL COMMENT '案例步骤',
  `is_delete` char(1) NOT NULL DEFAULT '0' COMMENT '0未删除，1删除',
  `test_status` char(1) DEFAULT '0' COMMENT '0未测试，1测试中，2测试通过，3测试未通过',
  `need_login` char(1) NOT NULL DEFAULT '0' COMMENT '0不需要，1需要',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

/*Data for the table `web_case` */


/*Table structure for table `web_platform` */

DROP TABLE IF EXISTS `web_platform`;

CREATE TABLE `web_platform` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `platform_name` varchar(100) NOT NULL COMMENT '平台名称',
  `login_url` varchar(250) NOT NULL COMMENT '登录url',
  `login_params` text NOT NULL COMMENT '登录信息',
  `request_type` char(1) NOT NULL COMMENT '登录方式0、get 1、post',
  `body_type` char(1) DEFAULT NULL COMMENT '暂且只用于post，0、form 1、raw',
  `is_delete` char(1) NOT NULL DEFAULT '0' COMMENT '0未删除，1删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `web_platform` */


/*Table structure for table `web_report` */

DROP TABLE IF EXISTS `web_report`;

CREATE TABLE `web_report` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `project_names` varchar(100) NOT NULL COMMENT '项目名称',
  `project_ids` varchar(20) NOT NULL COMMENT '项目id',
  `start_time` datetime NOT NULL COMMENT '执行开始时间',
  `end_time` datetime NOT NULL COMMENT '执行结束时间',
  `fail_case_count` int(9) NOT NULL COMMENT '失败的案例个数',
  `all_case_count` int(9) NOT NULL COMMENT '所有案例个数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=utf8;

/*Data for the table `web_report` */


/*Table structure for table `web_report_desc` */

DROP TABLE IF EXISTS `web_report_desc`;

CREATE TABLE `web_report_desc` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `case_name` varchar(20) NOT NULL COMMENT '案例名称',
  `url` varchar(200) NOT NULL COMMENT 'url',
  `start_time` datetime NOT NULL COMMENT '开始时间',
  `end_time` datetime NOT NULL COMMENT '结束时间',
  `shot_pic` varchar(200) DEFAULT NULL COMMENT '截图',
  `status` char(1) NOT NULL COMMENT '2测试通过，3测试未通过',
  `web_report_id` int(20) NOT NULL COMMENT '报告id',
  `error_msg` varchar(200) DEFAULT NULL COMMENT '错误信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=348 DEFAULT CHARSET=utf8;

/*Data for the table `web_report_desc` */


/*Table structure for table `xy_group` */

DROP TABLE IF EXISTS `xy_group`;

CREATE TABLE `xy_group` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `role_id` int(20) NOT NULL COMMENT '角色id',
  `menu_id` bigint(20) NOT NULL,
  `is_delete` char(1) NOT NULL DEFAULT '0' COMMENT '0未删除，1删除',
  `create_by` varchar(20) DEFAULT NULL,
  `create_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_by` varchar(20) DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8;

/*Data for the table `xy_group` */


/*Table structure for table `xy_menu` */

DROP TABLE IF EXISTS `xy_menu`;

CREATE TABLE `xy_menu` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(15) NOT NULL,
  `menu_url` varchar(100) DEFAULT NULL,
  `is_menu` char(1) DEFAULT NULL COMMENT '0父节点，1菜单url，数越大代表等级越低，null表示不是等级菜单',
  `parent_id` int(20) DEFAULT '0' COMMENT '父id,0代表无父节点',
  `is_delete` char(1) NOT NULL DEFAULT '0',
  `create_by` varchar(20) DEFAULT NULL,
  `create_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_by` varchar(20) DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

/*Data for the table `xy_menu` */


/*Table structure for table `xy_role` */

DROP TABLE IF EXISTS `xy_role`;

CREATE TABLE `xy_role` (
  `id` int(20) unsigned NOT NULL AUTO_INCREMENT,
  `role_name` varchar(10) NOT NULL COMMENT '角色表',
  `role_code` varchar(80) NOT NULL COMMENT '角色代称',
  `is_delete` char(1) NOT NULL DEFAULT '0' COMMENT '0未删除，1删除',
  `create_by` varchar(20) DEFAULT NULL COMMENT '创建者的名字',
  `create_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(20) DEFAULT NULL COMMENT '创建者名字',
  `update_at` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_code` (`role_code`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `xy_role` */


/*Table structure for table `xy_user` */

DROP TABLE IF EXISTS `xy_user`;

CREATE TABLE `xy_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_name` varchar(30) NOT NULL COMMENT '用户名',
  `user_email` varchar(50) NOT NULL COMMENT '邮箱',
  `user_password` varchar(50) NOT NULL DEFAULT 'f4bd9049fce4157a55551da9a966015c' COMMENT '密码',
  `position` varchar(15) NOT NULL COMMENT '职位',
  `role_id` int(20) NOT NULL COMMENT '角色id',
  `sex` char(1) NOT NULL DEFAULT '0' COMMENT '0男，1女',
  `is_delete` char(1) NOT NULL DEFAULT '0' COMMENT '0未删除，1删除',
  `create_by` varchar(20) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(20) DEFAULT NULL COMMENT '更新人',
  `create_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_at` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `tester_name` (`user_email`,`is_delete`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;

/*Data for the table `xy_user` */


/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
