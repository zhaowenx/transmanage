/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50619
Source Host           : 127.0.0.1:3306
Source Database       : transmanage

Target Server Type    : MYSQL
Target Server Version : 50619
File Encoding         : 65001

Date: 2019-03-03 20:57:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for addressbook
-- ----------------------------
DROP TABLE IF EXISTS `addressbook`;
CREATE TABLE `addressbook` (
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
  `BIRTHDAY` varchar(8) DEFAULT NULL COMMENT '生日',
  `SEX` varchar(1) DEFAULT NULL COMMENT '性别，0：女，1：男，9：不明确',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='通讯录表';

-- ----------------------------
-- Records of addressbook
-- ----------------------------
INSERT INTO `addressbook` VALUES ('1', '2', '赵文宣', 'zhaowenx', '文宣', '1533147879', 'wx1533147879', '南昌市', '太原市', 'zhaowenxuan@kayak.com.cn', '18171414730', '', '1', '1', '2018-10-12 14:10:41', '2018-10-26 11:10:17', '19950820', '1');
INSERT INTO `addressbook` VALUES ('2', '2', '杨崇顺', 'shun', '顺子', '844851460', 'chongshun1314', '凯里市', '贵阳市', 'a844851460@qq.com', '17585380490', '', '1', '5', '2018-10-26 10:58:48', '2018-10-26 10:58:48', '19930101', '1');
INSERT INTO `addressbook` VALUES ('3', '2', '梅豪', 'hao', '豪', '412018423', 'meihao15007165924', '黄冈市', '深圳市', '412018423@qq.com', '15007165924', '', '1', '5', '2018-10-26 16:22:23', '2018-10-26 17:05:00', '19950824', '1');

-- ----------------------------
-- Table structure for daily
-- ----------------------------
DROP TABLE IF EXISTS `daily`;
CREATE TABLE `daily` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `USERID` bigint(20) NOT NULL COMMENT '用户ID',
  `CONTENT` varchar(3000) DEFAULT NULL COMMENT '内容',
  `ISEVECTION` char(1) DEFAULT NULL COMMENT '是否出差，Y：是，N：否',
  `DAILYDATE` varchar(10) DEFAULT NULL COMMENT '日报日期',
  `CREATETIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATETIME` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='日报表';

-- ----------------------------
-- Records of daily
-- ----------------------------
INSERT INTO `daily` VALUES ('1', '2', '继续编写事务管理系统，编辑日报栏目的前后台代码', 'Y', '2018-09-03', '2018-09-03 18:25:59', '2018-09-05 09:04:09');
INSERT INTO `daily` VALUES ('2', '2', '继续编写事务管理系统，编辑通讯录的前后台代码', 'Y', '2018-09-04', '2018-09-04 09:00:40', '2018-09-04 09:00:40');
INSERT INTO `daily` VALUES ('3', '2', '继续编写事务管理系统，编写通讯录功能', 'Y', '2018-09-05', '2018-09-05 09:04:02', '2018-09-05 09:04:02');
INSERT INTO `daily` VALUES ('4', '2', '修复事务管理系统服务器宕机主页面展示有误的问题。\n编写事务管理系统的文档。', 'Y', '2018-09-06', '2018-09-06 16:37:50', '2018-09-06 16:40:10');
INSERT INTO `daily` VALUES ('5', '2', '学习maven', 'Y', '2018-09-07', '2018-09-07 17:15:27', '2018-09-07 17:15:27');
INSERT INTO `daily` VALUES ('6', '2', '学习maven', 'Y', '2018-09-10', '2018-09-10 14:16:53', '2018-09-10 14:16:53');
INSERT INTO `daily` VALUES ('7', '2', '完成系统顶部滚动点击后的优化\n', 'Y', '2018-10-12', '2018-10-12 11:18:05', '2018-10-12 11:18:05');
INSERT INTO `daily` VALUES ('8', '2', '完成数据字典功能的编写', 'Y', '2018-10-11', '2018-10-12 11:18:35', '2018-10-12 11:18:35');
INSERT INTO `daily` VALUES ('9', '2', '完成公告消息功能的编写', 'Y', '2018-10-10', '2018-10-12 11:19:00', '2018-10-12 11:19:00');
INSERT INTO `daily` VALUES ('10', '2', '完成通讯录功能的编写', 'Y', '2018-10-13', '2018-10-12 14:58:57', '2018-10-12 14:58:57');
INSERT INTO `daily` VALUES ('11', '2', '睡觉，玩游戏，做饭，看视频', 'Y', '2018-10-14', '2018-10-14 15:00:02', '2018-10-14 15:00:02');
INSERT INTO `daily` VALUES ('12', '2', '完善系统', 'Y', '2018-10-09', '2018-10-14 17:09:17', '2018-10-15 14:05:27');

-- ----------------------------
-- Table structure for leave
-- ----------------------------
DROP TABLE IF EXISTS `leave`;
CREATE TABLE `leave` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `LEAVEFROM` bigint(20) DEFAULT NULL COMMENT '发起人',
  `LEAVETO` bigint(20) DEFAULT NULL COMMENT '接收人',
  `CONTENT` varchar(300) DEFAULT NULL COMMENT '内容',
  `CREATETIME` datetime DEFAULT NULL COMMENT '创建时间',
  `ISREAD` int(1) DEFAULT NULL COMMENT '是否已读1：是，2：否',
  `PARENTID` bigint(20) DEFAULT NULL COMMENT '父id',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='留言表';

-- ----------------------------
-- Records of leave
-- ----------------------------
INSERT INTO `leave` VALUES ('1', '2', '1', '你对金庸有什么评价？', '2018-10-31 17:47:38', '1', '0');
INSERT INTO `leave` VALUES ('2', '1', '2', '你对李咏有什么评价', '2018-10-31 17:48:22', '1', '0');
INSERT INTO `leave` VALUES ('3', '2', '1', '李咏是个主持人！', '2018-10-31 17:48:44', '2', '2');
INSERT INTO `leave` VALUES ('4', '1', '2', '金庸武侠小说是20世纪中国文学史上无可比肩的畅销书，其在征服了无数读者的同时也掀起学术界对其进行研究的热潮。在21世纪，对金庸武侠小说进行深入的探究和挖掘，追问其广泛流行的根源，阐述和归纳其特有的艺术特征，正确评价金庸武侠小说的艺术特征，把握当下文坛的创作及评价趋向，都有一定的理论意义和现实意义。金庸武侠小说之所以流行，主要原因是它对传统武侠小说的升华和超越。', '2018-10-31 17:52:25', '2', '1');
INSERT INTO `leave` VALUES ('5', '1', '2', '你对曾志伟有什么评价?', '2018-10-31 17:53:29', '1', '0');
INSERT INTO `leave` VALUES ('6', '2', '1', '住在香港！', '2018-10-31 18:06:46', '2', '5');
INSERT INTO `leave` VALUES ('7', '2', '1', '今晚几点下班', '2018-10-31 18:08:38', '1', '0');
INSERT INTO `leave` VALUES ('8', '1', '2', '7.30', '2018-10-31 18:10:24', '2', '7');
INSERT INTO `leave` VALUES ('9', '2', '1', '吃了早饭没有', '2018-11-01 09:28:25', '1', '0');
INSERT INTO `leave` VALUES ('10', '1', '2', '吃了两包子', '2018-11-01 11:32:24', '2', '9');

-- ----------------------------
-- Table structure for material
-- ----------------------------
DROP TABLE IF EXISTS `material`;
CREATE TABLE `material` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `USERID` bigint(20) NOT NULL COMMENT '用户ID',
  `DESCRIPTION` varchar(300) DEFAULT NULL COMMENT '描述',
  `URL` varchar(300) DEFAULT NULL COMMENT '路径',
  `TYPE` char(1) DEFAULT NULL COMMENT '分类',
  `CREATETIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATETIME` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 COMMENT='资料表';

-- ----------------------------
-- Records of material
-- ----------------------------
INSERT INTO `material` VALUES ('1', '2', 'iconfont-阿里巴巴矢量图标库', 'http://www.iconfont.cn/search/index?q=%E9%80%9A%E8%AE%AF%E5%BD%95', '2', '2018-09-04 16:47:36', '2018-09-04 16:47:36');
INSERT INTO `material` VALUES ('2', '2', '设计模式之六大原则~~开闭原则（OCP）', 'https://www.cnblogs.com/muzongyan/archive/2010/08/05/1793454.html', '2', '2018-09-04 17:19:00', '2018-09-04 17:19:00');
INSERT INTO `material` VALUES ('3', '2', 'spring boot', 'http://www.cnblogs.com/ityouknow/p/5662753.html', '2', '2018-09-04 17:19:38', '2018-09-04 17:19:38');
INSERT INTO `material` VALUES ('4', '2', 'spring cloud', 'http://blog.csdn.net/forezp/article/details/70148833', '2', '2018-09-04 17:19:53', '2018-09-04 17:20:25');
INSERT INTO `material` VALUES ('5', '2', 'spring cloud', 'http://www.ityouknow.com/spring-cloud.html', '2', '2018-09-04 17:20:37', '2018-09-04 17:20:37');
INSERT INTO `material` VALUES ('6', '2', '纯洁的微笑', 'http://www.ityouknow.com/springboot/2016/01/06/springboot(%E4%B8%80)-%E5%85%A5%E9%97%A8%E7%AF%87.html', '2', '2018-09-04 17:20:53', '2018-09-04 17:20:53');
INSERT INTO `material` VALUES ('7', '2', 'spring cloud', 'http://www.cnblogs.com/chry/p/7286601.html', '2', '2018-09-04 17:21:49', '2018-09-04 17:21:49');
INSERT INTO `material` VALUES ('8', '2', 'spring cloud', 'http://www.cnblogs.com/chry/p/7248947.html', '2', '2018-09-04 17:22:00', '2018-09-04 17:22:00');
INSERT INTO `material` VALUES ('9', '2', '怎么创建一个spring boot项目', 'https://blog.csdn.net/lom9357bye/article/details/69677120', '2', '2018-09-04 17:22:16', '2018-09-04 17:22:16');
INSERT INTO `material` VALUES ('10', '2', 'LUA字符串操作', 'http://www.runoob.com/lua/lua-strings.html', '2', '2018-09-04 17:22:52', '2018-09-04 17:22:52');
INSERT INTO `material` VALUES ('11', '2', '安装lua', 'http://www.runoob.com/lua/lua-environment.html', '2', '2018-09-04 17:23:22', '2018-09-04 17:23:22');
INSERT INTO `material` VALUES ('12', '2', 'LUA菜鸟教程', 'http://www.runoob.com/lua/lua-variables.html', '2', '2018-09-04 17:23:39', '2018-09-04 17:23:39');
INSERT INTO `material` VALUES ('13', '2', 'mysql环境变量配置', 'http://www.jb51.net/article/83636.htm', '2', '2018-09-04 17:31:52', '2018-09-04 17:31:52');
INSERT INTO `material` VALUES ('14', '2', 'activity工作流', 'https://blog.csdn.net/burpee/article/details/52792998', '2', '2018-09-04 17:32:21', '2018-09-04 17:32:21');
INSERT INTO `material` VALUES ('15', '2', 'Zookeeper, Dubbo', 'http://dubbo.apache.org/#!/docs/user/references/registry/multicast.md?lang=zh-cn', '2', '2018-09-04 17:32:37', '2018-09-04 17:32:37');
INSERT INTO `material` VALUES ('16', '2', 'RabbitMQ', 'https://blog.csdn.net/whoamiyang/article/details/54954780#t1', '2', '2018-09-04 17:32:53', '2018-09-04 17:32:53');
INSERT INTO `material` VALUES ('17', '2', 'oracle教程', 'http://www.oraok.com/', '2', '2018-09-04 17:37:07', '2018-09-04 17:37:07');
INSERT INTO `material` VALUES ('18', '2', 'processOn免费作图', 'https://www.processon.com/;jsessionid=7C77A9889A97B8BEA62E4B2EE88233A2.jvm1', '2', '2018-09-04 17:37:38', '2018-09-04 17:37:38');
INSERT INTO `material` VALUES ('19', '2', 'Font Awesome\n一套绝佳的图标字体库和CSS框架', 'http://fontawesome.dashgame.com/', '2', '2018-09-04 17:38:24', '2018-09-04 17:38:24');
INSERT INTO `material` VALUES ('20', '2', '将项目部署到Github', 'https://www.cnblogs.com/fengxiongZz/p/6477456.html', '2', '2018-09-04 17:38:48', '2018-09-04 17:38:48');
INSERT INTO `material` VALUES ('21', '2', 'LINUX命令大全', 'http://man.linuxde.net/', '2', '2018-09-04 17:39:16', '2018-09-04 17:39:16');
INSERT INTO `material` VALUES ('22', '2', '关于ORACLE数据库的备份和整库的导出', 'https://blog.csdn.net/xtongzhen/article/details/51707494', '2', '2018-09-04 17:39:36', '2018-09-04 17:39:36');
INSERT INTO `material` VALUES ('23', '2', 'lua编辑器', 'https://www.lua.org/cgi-bin/demo', '2', '2018-09-04 17:40:27', '2018-09-04 17:40:27');
INSERT INTO `material` VALUES ('24', '2', '连接Mysql数据库集成Mybatis、ehcache采用MapperXml访问数据库', 'https://segmentfault.com/a/1190000011632583', '2', '2018-09-04 17:41:18', '2018-09-04 17:41:18');
INSERT INTO `material` VALUES ('25', '2', '易百教程    lua、spring、maven', 'https://www.yiibai.com', '2', '2018-09-04 17:41:52', '2018-09-04 17:44:30');
INSERT INTO `material` VALUES ('26', '2', 'spring cloud官方文档', 'https://springcloud.cc/spring-cloud-dalston.html#_features', '2', '2018-09-04 17:43:37', '2018-09-04 17:43:37');
INSERT INTO `material` VALUES ('27', '2', 'linux环境，创建用户、表空间、授权', 'https://www.cnblogs.com/byao-8816/p/8872950.html', '2', '2018-09-04 17:45:18', '2018-09-04 17:45:18');
INSERT INTO `material` VALUES ('28', '2', '编码转换、crontab表达式、等', 'http://www.matools.com/', '2', '2018-09-04 17:47:00', '2018-09-04 17:47:06');
INSERT INTO `material` VALUES ('29', '2', '码云', 'https://gitee.com/', '2', '2018-09-04 17:47:25', '2018-09-04 17:47:25');
INSERT INTO `material` VALUES ('30', '2', 'layui官方文档', 'https://www.layui.com/doc/', '2', '2018-09-04 17:47:48', '2018-09-04 17:47:48');
INSERT INTO `material` VALUES ('31', '2', 'layui模板', 'http://demo.vip-admin.com/', '2', '2018-09-04 17:48:11', '2018-09-04 17:48:11');
INSERT INTO `material` VALUES ('32', '2', 'thymeleaf模板', 'https://blog.csdn.net/f0rd_/article/details/80580225', '2', '2018-09-04 17:48:48', '2018-09-04 17:48:48');
INSERT INTO `material` VALUES ('33', '2', 'thymeleaf学习笔记', 'https://blog.csdn.net/xiao______xin/article/details/73302447', '2', '2018-09-04 17:49:07', '2018-09-04 17:49:07');
INSERT INTO `material` VALUES ('34', '2', '电影天堂', 'https://www.dy2018.com/', '1', '2018-09-04 17:49:44', '2018-09-04 17:49:44');
INSERT INTO `material` VALUES ('35', '2', 'maven搜索jar', 'https://repository.sonatype.org/#welcome', '2', '2018-09-10 14:15:53', '2018-09-10 14:15:53');
INSERT INTO `material` VALUES ('36', '2', '如何将项目部署到github', 'https://blog.csdn.net/qq_37932082/article/details/79464676', '2', '2018-10-15 14:16:20', '2018-10-15 18:46:31');
INSERT INTO `material` VALUES ('37', '2', 'Git 中 SSH key 生成步骤', 'https://www.cnblogs.com/horanly/p/6604104.html', '2', '2018-10-15 18:45:48', '2018-10-15 18:45:48');
INSERT INTO `material` VALUES ('38', '2', '将项目部署到github', 'https://blog.csdn.net/highboys/article/details/78701095', '2', '2018-10-15 18:46:57', '2018-10-28 09:26:12');
INSERT INTO `material` VALUES ('39', '2', '异常状态码', 'https://www.cnblogs.com/DoubleEggs/p/6114833.html', '2', '2018-10-30 17:15:59', '2018-10-30 17:15:59');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `TEXT` varchar(64) DEFAULT NULL COMMENT '菜单名称',
  `ICON` varchar(32) DEFAULT NULL COMMENT '菜单图标',
  `HREF` varchar(200) DEFAULT NULL COMMENT '访问地址',
  `PARENTID` int(11) DEFAULT NULL COMMENT '父菜单id,所有一级菜单的父菜单id都为0',
  `AVAILABLE` tinyint(1) DEFAULT '1' COMMENT '是否可用，1：可用，2：不可用',
  `MENULEVEL` int(2) DEFAULT NULL COMMENT '菜单级别',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '个人中心', '&#xe653;', '', '0', '1', '1');
INSERT INTO `menu` VALUES ('2', '系统管理', '&#xe716;', '', '0', '1', '1');
INSERT INTO `menu` VALUES ('3', '日报', '&#xe60a;', '/html/daily.html', '1', '1', '2');
INSERT INTO `menu` VALUES ('4', '记事本', '&#xe656;', '/html/wordpad.html', '1', '1', '2');
INSERT INTO `menu` VALUES ('5', '资料', '&#xe655;', '/html/material.html', '1', '1', '2');
INSERT INTO `menu` VALUES ('6', '通讯录', '&#xe613;', '/address/show', '1', '1', '2');
INSERT INTO `menu` VALUES ('7', '数据字典', '&#xe857;', '/html/sys-dict.html', '2', '1', '2');
INSERT INTO `menu` VALUES ('8', '公告管理', '&#xe857;', '/html/publish-notification.html', '2', '1', '2');
INSERT INTO `menu` VALUES ('9', '开科OA', '&#xe857;', 'http://oa.kayakwise.com:39090/login.jsp', '2', '1', '2');
INSERT INTO `menu` VALUES ('10', '笔趣阁', '&#xe857;', 'http://www.biquyun.com', '2', '1', '2');
INSERT INTO `menu` VALUES ('11', '菜单管理', '&#xe857;', '/menu/init', '2', '1', '2');
INSERT INTO `menu` VALUES ('12', '用户', '&#xe857;', '', '0', '1', '1');
INSERT INTO `menu` VALUES ('13', '角色管理', '&#xe857;', '/role/init', '12', '1', '2');
INSERT INTO `menu` VALUES ('14', '用户管理', '&#xe857;', '/html/user-management.html', '12', '1', '2');

-- ----------------------------
-- Table structure for publishnotification
-- ----------------------------
DROP TABLE IF EXISTS `publishnotification`;
CREATE TABLE `publishnotification` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `notificationTitle` varchar(50) DEFAULT NULL COMMENT '通知标题',
  `notificationContent` varchar(3000) DEFAULT NULL COMMENT '内容',
  `publishBy` bigint(20) DEFAULT NULL COMMENT '发布人',
  `publishDate` datetime DEFAULT NULL COMMENT '发布时间',
  `createBy` bigint(20) NOT NULL COMMENT '创建人',
  `createDate` datetime NOT NULL COMMENT '创建时间',
  `updateBy` bigint(20) NOT NULL COMMENT '更新人',
  `updateDate` datetime NOT NULL COMMENT '更新时间',
  `status` int(1) NOT NULL COMMENT '发布状态，0：发布，1：未发布',
  `stick` int(1) NOT NULL COMMENT '置顶，1：置顶，2：未置顶',
  `messageType` int(11) DEFAULT NULL COMMENT '消息类型',
  `messageAttachment` varchar(300) DEFAULT NULL COMMENT '附件信息',
  `isDelete` int(1) NOT NULL COMMENT '是否删除，1：是，2：否',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='公告表';

-- ----------------------------
-- Records of publishnotification
-- ----------------------------
INSERT INTO `publishnotification` VALUES ('1', '修改系统全部下拉款的通知', '下拉框的内容全部设置为数据字典中的内容', '2', '2018-10-28 09:20:08', '2', '2018-10-28 09:20:08', '2', '2018-10-28 09:20:08', '0', '1', '1', '修改系统全部下拉款的通知', '2');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `ROLEID` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `ROLENAME` varchar(32) NOT NULL COMMENT '角色名称',
  `ROLETYPE` int(2) DEFAULT NULL COMMENT '角色类型',
  `DESCRIPT` varchar(200) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`ROLEID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '超级管理员', '1', null);
INSERT INTO `role` VALUES ('2', '游客', '0', null);

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
  `ROLEID` int(11) NOT NULL COMMENT '角色ID',
  `MENUID` int(11) NOT NULL COMMENT '菜单ID',
  KEY `role_menu_index` (`MENUID`,`ROLEID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限表';

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES ('1', '1');
INSERT INTO `role_menu` VALUES ('2', '1');
INSERT INTO `role_menu` VALUES ('1', '2');
INSERT INTO `role_menu` VALUES ('2', '2');
INSERT INTO `role_menu` VALUES ('1', '3');
INSERT INTO `role_menu` VALUES ('2', '3');
INSERT INTO `role_menu` VALUES ('1', '4');
INSERT INTO `role_menu` VALUES ('2', '4');
INSERT INTO `role_menu` VALUES ('1', '5');
INSERT INTO `role_menu` VALUES ('2', '5');
INSERT INTO `role_menu` VALUES ('1', '6');
INSERT INTO `role_menu` VALUES ('2', '6');
INSERT INTO `role_menu` VALUES ('1', '7');
INSERT INTO `role_menu` VALUES ('1', '8');
INSERT INTO `role_menu` VALUES ('1', '9');
INSERT INTO `role_menu` VALUES ('2', '9');
INSERT INTO `role_menu` VALUES ('1', '10');
INSERT INTO `role_menu` VALUES ('2', '10');
INSERT INTO `role_menu` VALUES ('1', '11');
INSERT INTO `role_menu` VALUES ('1', '12');
INSERT INTO `role_menu` VALUES ('1', '13');
INSERT INTO `role_menu` VALUES ('1', '14');

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `DICT` varchar(64) NOT NULL COMMENT '字典标识',
  `DICTNAME` varchar(200) NOT NULL COMMENT '字典名称',
  `GROUPDICT` varchar(64) NOT NULL COMMENT '分组字典标识，此字段值为ROOT的记录为分组信息',
  UNIQUE KEY `DICT` (`DICT`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典定义表';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('id_type', '证件类型', 'system');
INSERT INTO `sys_dict` VALUES ('isEvection', '是否出差', 'user');
INSERT INTO `sys_dict` VALUES ('is_read', '是否已读', 'user');
INSERT INTO `sys_dict` VALUES ('material_type', '资料类型', 'system');
INSERT INTO `sys_dict` VALUES ('messageType', '消息类型', 'system');
INSERT INTO `sys_dict` VALUES ('profession', '职业', 'system');
INSERT INTO `sys_dict` VALUES ('roletype', '角色类型', 'user');
INSERT INTO `sys_dict` VALUES ('sex', '性别', 'system');
INSERT INTO `sys_dict` VALUES ('status', '是否发布', 'user');
INSERT INTO `sys_dict` VALUES ('stick', '是否置顶', 'user');
INSERT INTO `sys_dict` VALUES ('system', '系统字典', 'root');
INSERT INTO `sys_dict` VALUES ('type', '身份分类', 'user');
INSERT INTO `sys_dict` VALUES ('user', '用户管理', 'root');

-- ----------------------------
-- Table structure for sys_dict_item
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_item`;
CREATE TABLE `sys_dict_item` (
  `DICT` varchar(64) NOT NULL COMMENT '字典标识',
  `ITEMKEY` varchar(200) NOT NULL COMMENT '数据键',
  `ITEMVAL` varchar(200) NOT NULL COMMENT '数据值'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典数据表';

-- ----------------------------
-- Records of sys_dict_item
-- ----------------------------
INSERT INTO `sys_dict_item` VALUES ('sex', '1', '男');
INSERT INTO `sys_dict_item` VALUES ('sex', '0', '女');
INSERT INTO `sys_dict_item` VALUES ('id_type', '1021', ' 士兵证 ');
INSERT INTO `sys_dict_item` VALUES ('id_type', '1022', '军官证');
INSERT INTO `sys_dict_item` VALUES ('roletype', '0', '普通用户');
INSERT INTO `sys_dict_item` VALUES ('roletype', '1', '超级管理员');
INSERT INTO `sys_dict_item` VALUES ('material_type', '2', '资料');
INSERT INTO `sys_dict_item` VALUES ('material_type', '3', 'kayak');
INSERT INTO `sys_dict_item` VALUES ('material_type', '4', '其他');
INSERT INTO `sys_dict_item` VALUES ('id_type', '1011', '居民身份证');
INSERT INTO `sys_dict_item` VALUES ('id_type', '1071', '港澳居民往来内地通行证');
INSERT INTO `sys_dict_item` VALUES ('id_type', '1041', ' 户口簿 ');
INSERT INTO `sys_dict_item` VALUES ('profession', '1', '互联网/IT计算机');
INSERT INTO `sys_dict_item` VALUES ('profession', '2', '制造业');
INSERT INTO `sys_dict_item` VALUES ('profession', '3', '电子/微电子');
INSERT INTO `sys_dict_item` VALUES ('profession', '4', '广告/公关');
INSERT INTO `sys_dict_item` VALUES ('profession', '5', '房地产/建筑');
INSERT INTO `sys_dict_item` VALUES ('profession', '6', '贸易/进出口/零售批发');
INSERT INTO `sys_dict_item` VALUES ('profession', '7', '消费品');
INSERT INTO `sys_dict_item` VALUES ('profession', '8', '交通/运输/物流');
INSERT INTO `sys_dict_item` VALUES ('profession', '9', '教育/培训/科研');
INSERT INTO `sys_dict_item` VALUES ('profession', '10', '艺术/休闲/运动');
INSERT INTO `sys_dict_item` VALUES ('profession', '11', '农/林/牧/渔/水利');
INSERT INTO `sys_dict_item` VALUES ('profession', '12', '石油/化工/能源');
INSERT INTO `sys_dict_item` VALUES ('profession', '13', '金融/银行');
INSERT INTO `sys_dict_item` VALUES ('profession', '14', '文化/媒体/出版/印刷');
INSERT INTO `sys_dict_item` VALUES ('profession', '15', '服务/中介/物业');
INSERT INTO `sys_dict_item` VALUES ('profession', '16', '政府/非营利机构');
INSERT INTO `sys_dict_item` VALUES ('profession', '17', '其他');
INSERT INTO `sys_dict_item` VALUES ('material_type', '1', '视频');
INSERT INTO `sys_dict_item` VALUES ('type', '2', '亲人');
INSERT INTO `sys_dict_item` VALUES ('type', '3', '朋友');
INSERT INTO `sys_dict_item` VALUES ('type', '4', '同事');
INSERT INTO `sys_dict_item` VALUES ('type', '5', '同学');
INSERT INTO `sys_dict_item` VALUES ('type', '6', '其他');
INSERT INTO `sys_dict_item` VALUES ('type', '1', '本人');
INSERT INTO `sys_dict_item` VALUES ('isEvection', 'Y', '是');
INSERT INTO `sys_dict_item` VALUES ('isEvection', 'N', '否');
INSERT INTO `sys_dict_item` VALUES ('stick', '2', '否');
INSERT INTO `sys_dict_item` VALUES ('stick', '1', '是');
INSERT INTO `sys_dict_item` VALUES ('status', '0', '是');
INSERT INTO `sys_dict_item` VALUES ('status', '1', '否');
INSERT INTO `sys_dict_item` VALUES ('messageType', '1', '系统消息');
INSERT INTO `sys_dict_item` VALUES ('messageType', '2', '生日消息');
INSERT INTO `sys_dict_item` VALUES ('messageType', '3', '其他消息');
INSERT INTO `sys_dict_item` VALUES ('is_read', '1', '是');
INSERT INTO `sys_dict_item` VALUES ('is_read', '2', '否');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
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
  `LOGINTIMES` bigint(20) DEFAULT NULL COMMENT '登录次数',
  `LASTLOGINDATE` varchar(100) DEFAULT NULL COMMENT '最后一次登录时间',
  `LASTIP` varchar(50) DEFAULT NULL COMMENT '最后登录的IP',
  `SEX` int(1) DEFAULT NULL COMMENT '性别，1：男，0：女',
  `CREATETIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATETIME` datetime DEFAULT NULL COMMENT '更新时间',
  `ISDELETE` char(1) DEFAULT NULL COMMENT '是否删除，Y：是，N：否',
  `PROVINCENAME` varchar(200) DEFAULT NULL COMMENT '归属省份名称',
  `CITYNAME` varchar(200) DEFAULT NULL COMMENT '归属城市名称',
  `COUNTYNAME` varchar(200) DEFAULT NULL COMMENT '归属地区名称',
  `DEPARTMENT` varchar(500) DEFAULT NULL COMMENT '所属部门',
  `COMPANY` varchar(500) DEFAULT NULL COMMENT '所属公司',
  `HIREDATE` varchar(100) DEFAULT NULL COMMENT '入职时间',
  `IDCARD` varchar(30) DEFAULT NULL COMMENT '身份证号码',
  `PROFESSION` varchar(10) DEFAULT NULL COMMENT '职业',
  `HOBBY` varchar(300) DEFAULT NULL COMMENT '兴趣爱好',
  `WISDOM` varchar(3000) DEFAULT NULL COMMENT '至理名言',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `USERNAME` (`USERNAME`),
  UNIQUE KEY `MOBILE` (`MOBILE`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '123456', '管理员', '', '', '', '', '', '', '71', '2019-03-03 20:56:34', '192.168.1.10', null, '2018-08-23 17:29:59', '2019-03-03 20:56:34', 'N', '', '', '', '', '', '', null, '15', null, null);
INSERT INTO `user` VALUES ('2', 'zhaowenxuan', '204813', '赵文宣', '18171414730', '18171414730', 'zhaowenxuan@kayak.com.cn', 'zwx1533147879', '1533147879', '01800', '563', '2019-03-03 20:55:54', '192.168.1.10', '1', '2018-08-23 17:29:59', '2019-03-03 20:55:54', 'N', '江西省', '南昌市', '进贤县', '财富管理软件中心-技术研发部', '北京开科唯识技术有限公司', '2018-04-19', '360124199508204813', '1', 'a,b,c,d,e,f', '得之我幸，失之我命');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `USERID` int(11) NOT NULL COMMENT '用户ID',
  `ROLEID` int(11) NOT NULL COMMENT '角色ID',
  KEY `user_role_index` (`USERID`,`ROLEID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1');
INSERT INTO `user_role` VALUES ('2', '2');

-- ----------------------------
-- Table structure for wordpad
-- ----------------------------
DROP TABLE IF EXISTS `wordpad`;
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='记事本表';

-- ----------------------------
-- Records of wordpad
-- ----------------------------
INSERT INTO `wordpad` VALUES ('1', '2', '偶尔的灵感，时间的记忆', '历史的记忆，如茶般醇厚', '2018-08-31 22:47:09', '2018-08-31 22:47:09', 'N', 'N');
INSERT INTO `wordpad` VALUES ('2', '2', '叹昭君塞', '暮至晨光夕阳醉，万里春沙漫思泪。\n思君不见办敛眉，归去胡边落叶悲。', '2018-09-01 12:28:27', '2018-09-03 11:22:44', 'N', 'N');
INSERT INTO `wordpad` VALUES ('3', '2', '叹昭君塞', '暮至晨光夕阳醉，万里春纱漫思泪。\n思君不见半敛眉，归去胡边落叶悲。', '2018-09-04 15:49:24', '2018-09-04 15:49:24', 'N', 'N');
INSERT INTO `wordpad` VALUES ('4', '2', '北京攻略', '【天安们广场】【人民英雄纪念碑】【人民大会堂】【毛主席纪念堂】【中国国家博物馆】【故宫】【老北京胡同】【王府井】【天坛公园】【八达岭长城】【鸟巢】【水立方】【国家体育馆】【颐和园】【军事博物馆】【清华大学】【定陵】', '2018-10-19 15:02:10', '2018-10-19 15:02:10', 'N', 'N');
INSERT INTO `wordpad` VALUES ('5', '2', '评语', '交通基本靠走，通讯基本靠吼，治安基本靠狗，取暖基本靠抖，娱乐基本靠手，致富基本靠抢，吃饭基本靠党，娶妻基本靠想', '2018-10-26 14:52:28', '2018-10-26 14:52:28', 'N', 'N');
