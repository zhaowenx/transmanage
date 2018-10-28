--用户表
CREATE TABLE `user` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `USERNAME` varchar(100) NOT NULL COMMENT '用户名',
  `PASSWORD` varchar(100) NOT NULL COMMENT '登录密码',
  `REALNAME` varchar(500) DEFAULT NULL COMMENT '真实姓名',
  `PHONE` varchar(20) DEFAULT NULL COMMENT '电话',
  `MOBILE` varchar(20) DEFAULT NULL COMMENT '手机',
  `EMAIL` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `WEIXIN` varchar(100) DEFAULT NULL COMMENT '微信',
  `QQNUMBER` varchar(100) DEFAULT NULL COMMENT 'QQ',
  `STAFFNO` varchar(300) DEFAULT NULL COMMENT '工号',
  `ISSUPERUSER` int(1) NOT NULL COMMENT '是否超级用户，0：非超级用户，1：超级用户',
  `LOGINTIMES` bigint(20) DEFAULT NULL COMMENT '登录次数',
  `LASTLOGINDATE` datetime DEFAULT NULL COMMENT '最后一次登录时间',
  `LASTIP` varchar(50) DEFAULT NULL COMMENT '最后登录的IP',
  `SEX` int(1) DEFAULT NULL COMMENT '性别，1：男，0：女',
  `CREATETIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATETIME` datetime DEFAULT NULL COMMENT '更新时间',
  `CREATEBY` bigint(20) DEFAULT NULL COMMENT '创建者',
  `UPDATEBY` bigint(20) DEFAULT NULL COMMENT '更新者',
  `ISDELETE` char(1) DEFAULT NULL COMMENT '是否删除，Y：是，N：否',
  `PROVINCENAME` varchar(200) DEFAULT NULL COMMENT '归属省份名称',
  `CITYNAME` varchar(200) DEFAULT NULL COMMENT '归属城市名称',
  `COUNTYNAME` varchar(200) DEFAULT NULL COMMENT '归属地区名称',
  `DEPARTMENT` varchar(500) DEFAULT NULL COMMENT '所属部门',
  `COMPANY` varchar(500) DEFAULT NULL COMMENT '所属公司',
  `HIREDATE` datetime DEFAULT NULL COMMENT '入职时间',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `USERNAME` (`USERNAME`),
  UNIQUE KEY `MOBILE` (`MOBILE`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户表';

ALTER TABLE `USER` ADD COLUMN `IDCARD` VARCHAR(30) DEFAULT NULL COMMENT '身份证号码';
ALTER TABLE `USER` ADD COLUMN `PROFESSION` VARCHAR(10) DEFAULT NULL COMMENT '职业';
ALTER TABLE `USER` ADD COLUMN `HOBBY` VARCHAR(300) DEFAULT NULL COMMENT '兴趣爱好';
ALTER TABLE `USER` ADD COLUMN `WISDOM` VARCHAR(3000) DEFAULT NULL COMMENT '至理名言';

INSERT INTO `USER` (`USERNAME`,`PASSWORD`,`REALNAME`,`PHONE`,`MOBILE`,`EMAIL`,`WEIXIN`,
	`QQNUMBER`,`STAFFNO`,`ISSUPERUSER`,`LOGINTIMES`,`LASTLOGINDATE`,`LASTIP`,`SEX`,`CREATETIME`,
	`UPDATETIME`,`ISDELETE`,`PROVINCENAME`,`CITYNAME`,`COUNTYNAME`,`DEPARTMENT`,`COMPANY`,`HIREDATE`,
  `IDCARD`,`PROFESSION`,`HOBBY`,`WISDOM`)
VALUES
	('admin','123456','超级管理员','','18171414730','','',
		'','',1,0,'','','',now(),
		now(),'N','','','','','','',
		'','','','');


--公告表
CREATE TABLE `PUBLISHNOTIFICATION` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `notificationTitle` varchar(50) DEFAULT NULL COMMENT '通知标题',
	`notificationContent` varchar(3000) DEFAULT NULL COMMENT '内容',
	`publishBy` bigint(20) DEFAULT NULL COMMENT '发布人',
	`publishDate` datetime DEFAULT NULL COMMENT '发布时间',
	`createBy` bigint(20) NOT NULL COMMENT '创建人',
	`createDate` datetime NOT NULL COMMENT '创建时间',
	`updateBy` bigint(20) NOT NULL COMMENT '更新人',
	`updateDate` datetime NOT NULL COMMENT '更新时间',
	`status` INTEGER(1) NOT NULL COMMENT '发布状态，0：发布，1：未发布',
	`stick` INTEGER(1) NOT NULL COMMENT '置顶，1：置顶，2：未置顶',
	`messageType` INTEGER DEFAULT NULL COMMENT '消息类型',
	`messageAttachment` varchar(300) DEFAULT NULL COMMENT '附件信息',
  `isDelete` INTEGER(1) NOT NULL COMMENT '是否删除，1：是，2：否',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公告表';


--记事本表
CREATE TABLE `wordpad` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `USERID` bigint(20) NOT NULL COMMENT '用户ID',
	`TITLE` varchar(30) DEFAULT NULL COMMENT '标题',
  `CONTENT` varchar(3000) DEFAULT NULL COMMENT '内容',
  `CREATETIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATETIME` datetime DEFAULT NULL COMMENT '更新时间',
  `ISDELETE` char(1) DEFAULT NULL COMMENT '是否删除，Y：是，N：否',
	`ISSHIELD` char(1) DEFAULT NULL COMMENT '是否屏蔽，Y：是，N：否',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='记事本表';


--日报表
CREATE TABLE `daily` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `USERID` bigint(20) NOT NULL COMMENT '用户ID',
  `CONTENT` varchar(3000) DEFAULT NULL COMMENT '内容',
	`ISEVECTION` char(1) DEFAULT NULL COMMENT '是否出差，Y：是，N：否',
	`DAILYDATE` varchar(10) DEFAULT NULL COMMENT '日报日期',
  `CREATETIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATETIME` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='日报表';


--数据字典定义表、字典数据表
CREATE TABLE `SYS_DICT`
(
  DICT      VARCHAR(64) not null COMMENT '字典标识',
  DICTNAME  VARCHAR(200) not null COMMENT '字典名称',
  GROUPDICT VARCHAR(64) not null COMMENT '分组字典标识，此字段值为ROOT的记录为分组信息',
	UNIQUE KEY `DICT` (`DICT`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典定义表';

CREATE TABLE `SYS_DICT_ITEM`
(
  DICT      VARCHAR(64) not null COMMENT '字典标识',
  ITEMKEY   VARCHAR(200) not null COMMENT '数据键',
  ITEMVAL   VARCHAR(200) not null COMMENT '数据值'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典数据表';

INSERT INTO `sys_dict` (`DICT`, `DICTNAME`, `GROUPDICT`) VALUES ('id_type', '证件类型', 'system');
INSERT INTO `sys_dict` (`DICT`, `DICTNAME`, `GROUPDICT`) VALUES ('material_type', '资料类型', 'system');
INSERT INTO `sys_dict` (`DICT`, `DICTNAME`, `GROUPDICT`) VALUES ('profession', '职业', 'system');
INSERT INTO `sys_dict` (`DICT`, `DICTNAME`, `GROUPDICT`) VALUES ('roletype', '角色类型', 'user');
INSERT INTO `sys_dict` (`DICT`, `DICTNAME`, `GROUPDICT`) VALUES ('sex', '性别', 'system');
INSERT INTO `sys_dict` (`DICT`, `DICTNAME`, `GROUPDICT`) VALUES ('system', '系统字典', 'root');
INSERT INTO `sys_dict` (`DICT`, `DICTNAME`, `GROUPDICT`) VALUES ('type', '身份分类', 'user');
INSERT INTO `sys_dict` (`DICT`, `DICTNAME`, `GROUPDICT`) VALUES ('user', '用户管理', 'root');
INSERT INTO `sys_dict_item` (`DICT`, `ITEMKEY`, `ITEMVAL`) VALUES ('sex', '1', '男');
INSERT INTO `sys_dict_item` (`DICT`, `ITEMKEY`, `ITEMVAL`) VALUES ('sex', '0', '女');
INSERT INTO `sys_dict_item` (`DICT`, `ITEMKEY`, `ITEMVAL`) VALUES ('sex', '9', '不明确');
INSERT INTO `sys_dict_item` (`DICT`, `ITEMKEY`, `ITEMVAL`) VALUES ('id_type', '1021', ' 士兵证 ');
INSERT INTO `sys_dict_item` (`DICT`, `ITEMKEY`, `ITEMVAL`) VALUES ('id_type', '1022', '军官证');
INSERT INTO `sys_dict_item` (`DICT`, `ITEMKEY`, `ITEMVAL`) VALUES ('roletype', '0', '普通用户');
INSERT INTO `sys_dict_item` (`DICT`, `ITEMKEY`, `ITEMVAL`) VALUES ('roletype', '1', '超级管理员');
INSERT INTO `sys_dict_item` (`DICT`, `ITEMKEY`, `ITEMVAL`) VALUES ('material_type', '2', '资料');
INSERT INTO `sys_dict_item` (`DICT`, `ITEMKEY`, `ITEMVAL`) VALUES ('material_type', '3', 'kayak');
INSERT INTO `sys_dict_item` (`DICT`, `ITEMKEY`, `ITEMVAL`) VALUES ('material_type', '4', '其他');
INSERT INTO `sys_dict_item` (`DICT`, `ITEMKEY`, `ITEMVAL`) VALUES ('id_type', '1011', '居民身份证');
INSERT INTO `sys_dict_item` (`DICT`, `ITEMKEY`, `ITEMVAL`) VALUES ('id_type', '1071', '港澳居民往来内地通行证');
INSERT INTO `sys_dict_item` (`DICT`, `ITEMKEY`, `ITEMVAL`) VALUES ('id_type', '1041', ' 户口簿 ');
INSERT INTO `sys_dict_item` (`DICT`, `ITEMKEY`, `ITEMVAL`) VALUES ('profession', '1', '互联网/IT计算机');
INSERT INTO `sys_dict_item` (`DICT`, `ITEMKEY`, `ITEMVAL`) VALUES ('profession', '2', '制造业');
INSERT INTO `sys_dict_item` (`DICT`, `ITEMKEY`, `ITEMVAL`) VALUES ('profession', '3', '电子/微电子');
INSERT INTO `sys_dict_item` (`DICT`, `ITEMKEY`, `ITEMVAL`) VALUES ('profession', '4', '广告/公关');
INSERT INTO `sys_dict_item` (`DICT`, `ITEMKEY`, `ITEMVAL`) VALUES ('profession', '5', '房地产/建筑');
INSERT INTO `sys_dict_item` (`DICT`, `ITEMKEY`, `ITEMVAL`) VALUES ('profession', '6', '贸易/进出口/零售批发');
INSERT INTO `sys_dict_item` (`DICT`, `ITEMKEY`, `ITEMVAL`) VALUES ('profession', '7', '消费品');
INSERT INTO `sys_dict_item` (`DICT`, `ITEMKEY`, `ITEMVAL`) VALUES ('profession', '8', '交通/运输/物流');
INSERT INTO `sys_dict_item` (`DICT`, `ITEMKEY`, `ITEMVAL`) VALUES ('profession', '9', '教育/培训/科研');
INSERT INTO `sys_dict_item` (`DICT`, `ITEMKEY`, `ITEMVAL`) VALUES ('profession', '10', '艺术/休闲/运动');
INSERT INTO `sys_dict_item` (`DICT`, `ITEMKEY`, `ITEMVAL`) VALUES ('profession', '11', '农/林/牧/渔/水利');
INSERT INTO `sys_dict_item` (`DICT`, `ITEMKEY`, `ITEMVAL`) VALUES ('profession', '12', '石油/化工/能源');
INSERT INTO `sys_dict_item` (`DICT`, `ITEMKEY`, `ITEMVAL`) VALUES ('profession', '13', '金融/银行');
INSERT INTO `sys_dict_item` (`DICT`, `ITEMKEY`, `ITEMVAL`) VALUES ('profession', '14', '文化/媒体/出版/印刷');
INSERT INTO `sys_dict_item` (`DICT`, `ITEMKEY`, `ITEMVAL`) VALUES ('profession', '15', '服务/中介/物业');
INSERT INTO `sys_dict_item` (`DICT`, `ITEMKEY`, `ITEMVAL`) VALUES ('profession', '16', '政府/非营利机构');
INSERT INTO `sys_dict_item` (`DICT`, `ITEMKEY`, `ITEMVAL`) VALUES ('profession', '17', '其他');
INSERT INTO `sys_dict_item` (`DICT`, `ITEMKEY`, `ITEMVAL`) VALUES ('material_type', '1', '视频');
INSERT INTO `sys_dict_item` (`DICT`, `ITEMKEY`, `ITEMVAL`) VALUES ('type', '1', '本人');
INSERT INTO `sys_dict_item` (`DICT`, `ITEMKEY`, `ITEMVAL`) VALUES ('type', '2', '亲人');
INSERT INTO `sys_dict_item` (`DICT`, `ITEMKEY`, `ITEMVAL`) VALUES ('type', '3', '朋友');
INSERT INTO `sys_dict_item` (`DICT`, `ITEMKEY`, `ITEMVAL`) VALUES ('type', '4', '同事');
INSERT INTO `sys_dict_item` (`DICT`, `ITEMKEY`, `ITEMVAL`) VALUES ('type', '5', '同学');
INSERT INTO `sys_dict_item` (`DICT`, `ITEMKEY`, `ITEMVAL`) VALUES ('type', '6', '其他');


--通讯录表
CREATE TABLE `ADDRESSBOOK` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `USERID` bigint(20) NOT NULL COMMENT '用户ID',
	`CHINESENAME` varchar(30) DEFAULT NULL COMMENT '中文名',
  `ENGLISHNAME` varchar(30) DEFAULT NULL COMMENT '英文名',
	`ANOTHERNAME` varchar(30) DEFAULT NULL COMMENT '别称',
  `QQNUMBER` varchar(20) DEFAULT NULL COMMENT 'QQ号码',
  `WEIXIN` varchar(50) DEFAULT NULL COMMENT '微信',
	`DOMICILE` varchar(500) DEFAULT NULL COMMENT '户籍所在地',
	`ADDRESS` varchar(500) DEFAULT NULL COMMENT '现住址',
	`EMAIL` varchar(100) DEFAULT NULL COMMENT '邮箱',
	`PHONE` varchar(20) DEFAULT NULL COMMENT '手机',
	`WEIBO` varchar(200) DEFAULT NULL COMMENT '微博',
	`PROFESSION` varchar(20) DEFAULT NULL COMMENT '职业',
	`TYPE` varchar(2) DEFAULT NULL COMMENT '分类',
  `CREATETIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATETIME` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通讯录表';

ALTER TABLE `ADDRESSBOOK` ADD COLUMN `BIRTHDAY` VARCHAR(8) DEFAULT NULL COMMENT '生日';
ALTER TABLE `ADDRESSBOOK` ADD COLUMN `SEX` VARCHAR(1) DEFAULT NULL COMMENT '性别，0：女，1：男，9：不明确';



INSERT INTO `transmanage`.`addressbook` (
	`USERID`,
	`CHINESENAME`,
	`ENGLISHNAME`,
	`ANOTHERNAME`,
	`QQNUMBER`,
	`WEIXIN`,
	`DOMICILE`,
	`ADDRESS`,
	`EMAIL`,
	`PHONE`,
	`WEIBO`,
	`PROFESSION`,
	`TYPE`,
	`CREATETIME`,
	`UPDATETIME`,
	`BIRTHDAY`,
	`SEX`
)
VALUES
	(
		'2',
		'赵文宣',
		'zhaowenx',
		'文宣',
		'1533147879',
		'wx1533147879',
		'南昌市',
		'太原市',
		'zhaowenxuan@kayak.com.cn',
		'18171414730',
		'18171414730',
		'1',
		'1',
		now(),
		now(),
		'19950820',
		'1'
	);



--资料表
CREATE TABLE `material` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `USERID` bigint(20) NOT NULL COMMENT '用户ID',
  `DESCRIPTION` varchar(300) DEFAULT NULL COMMENT '描述',
	`URL` varchar(300) DEFAULT NULL COMMENT '路径',
	`TYPE` CHAR(1)  DEFAULT NULL COMMENT '分类',
  `CREATETIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATETIME` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资料表';

CREATE TABLE `menu`(
	`ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
	`TEXT` varchar(64) DEFAULT NULL COMMENT '菜单名称',
	`ICON` varchar(32) DEFAULT NULL COMMENT '菜单图标',
	`HREF` varchar(32) DEFAULT NULL COMMENT '访问地址',
	`PARENTID` int(11) DEFAULT NULL COMMENT '父菜单id,所有一级菜单的父菜单id都为0',
	`AVAILABLE` TINYINT(1) DEFAULT 1 COMMENT '是否可用，1：可用，2：不可用',
	PRIMARY KEY (`ID`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';

INSERT INTO `menu` (`id`,`TEXT`,`ICON`,`HREF`,`PARENTID`,`AVAILABLE`)VALUES (1,'个人中心', '&#xe653;', NULL, 0, 1);
INSERT INTO `menu` (`id`,`TEXT`,`ICON`,`HREF`,`PARENTID`,`AVAILABLE`)VALUES (2,'系统管理', '&#xe716;', NULL, 0, 1);
INSERT INTO `menu` (`id`,`TEXT`,`ICON`,`HREF`,`PARENTID`,`AVAILABLE`)VALUES (9,'客户管理', '&#xe717;', NULL, 0, 1);


INSERT INTO `menu` (`id`,`TEXT`,`ICON`,`HREF`,`PARENTID`,`AVAILABLE`)VALUES (3,'日报', '&#xe60a;', '/html/daily.html', 1, 1);
INSERT INTO `menu` (`id`,`TEXT`,`ICON`,`HREF`,`PARENTID`,`AVAILABLE`)VALUES (4,'记事本', '&#xe656;', '/html/wordpad.html', 1, 1);
INSERT INTO `menu` (`id`,`TEXT`,`ICON`,`HREF`,`PARENTID`,`AVAILABLE`)VALUES (5,'资料', '&#xe655;', '/html/material.html', 1, 1);
INSERT INTO `menu` (`id`,`TEXT`,`ICON`,`HREF`,`PARENTID`,`AVAILABLE`)VALUES (6,'通讯录', '&#xe613;', '/address/show', 1, 1);
INSERT INTO `menu` (`id`,`TEXT`,`ICON`,`HREF`,`PARENTID`,`AVAILABLE`)VALUES (7,'数据字典', '&#xe857;', '/html/sys-dict.html', 2, 1);
INSERT INTO `menu` (`id`,`TEXT`,`ICON`,`HREF`,`PARENTID`,`AVAILABLE`)VALUES (8,'公告消息', '&#xe857;', '/html/publish-notification.html', 2, 1);