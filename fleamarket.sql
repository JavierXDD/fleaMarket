/*
SQLyog Ultimate v8.32 
MySQL - 5.5.36 : Database - fleamarket
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`fleamarket` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `fleamarket`;

/*Table structure for table `blog` */

DROP TABLE IF EXISTS `blog`;

CREATE TABLE `blog` (
  `id` varchar(255) NOT NULL,
  `create_time` varchar(200) DEFAULT NULL,
  `menu_id` varchar(200) DEFAULT NULL,
  `menu_name` varchar(200) DEFAULT NULL,
  `modify_time` varchar(200) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `quality` varchar(200) DEFAULT NULL,
  `surface_pic_url` varchar(200) DEFAULT NULL,
  `tags` varchar(200) DEFAULT NULL,
  `text` longtext,
  `title` varchar(200) DEFAULT NULL,
  `type_id` varchar(200) DEFAULT NULL,
  `type_name` varchar(200) DEFAULT NULL,
  `user_email` varchar(200) DEFAULT NULL,
  `user_id` varchar(200) DEFAULT NULL,
  `user_name` varchar(200) DEFAULT NULL,
  `user_telephone` varchar(200) DEFAULT NULL,
  `is_use` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `blog` */

insert  into `blog`(`id`,`create_time`,`menu_id`,`menu_name`,`modify_time`,`name`,`price`,`quality`,`surface_pic_url`,`tags`,`text`,`title`,`type_id`,`type_name`,`user_email`,`user_id`,`user_name`,`user_telephone`,`is_use`) values ('297e22e45bf0b5d4015bf0bc474a0001','2017/05/10 12:59:36','4028db815b1e1f0a015b1e2040510000','手机/数码','2017/05/10 13:03:13','答辩测试',467,'八成新','media\\surfacePic\\3\\0\\5.jpg','手机，标签测试1，标签测试2','<p><img src=\"/WenLiFleaMarket/media/ueditor/jsp/upload/image/20170510/1494392492863074701.jpg\" title=\"1494392492863074701.jpg\" alt=\"photo1.jpg\"/></p><p>手机哈哈哈</p>','答辩测试','4028db815b1e1f0a015b1e2040510003','华为','369761578@qq.com','297e22e45bf0b5d4015bf0b994bd0000','','13312341234',0),('4028db815b37b3bb015b37c4b1be0000','2017/04/04 15:00:14','4028db815b1e1f0a015b1e2040510000','手机/数码','2017/05/09 23:00:59','平板电脑',1432,'九成新','media\\surfacePic\\d\\f\\photo3.jpg','324','<p>342<img src=\"/WenLiFleaMarket/media/ueditor/jsp/upload/image/20170428/1493358856148078828.png\" title=\"1493358856148078828.png\" alt=\"img1.png\"/></p>','432test','4028db815b1e1f0a015b1e2040510002','三星','123321123@qq.com','4028db815af4c7ca015af4c97a4c0001','x1为','13312341234',0),('4028db815b5b832c015b5b8b793c0000','2017/04/11 13:37:57','4028db815b1e1f0a015b1e2040510000','手机/数码','2017/04/26 20:30:58','交易信息',700,'九成新','media\\surfacePic\\d\\f\\photo2.jpg','手机,数码','<p>顶顶顶顶顶顶顶顶顶顶多多多多多多多多多多多多顶顶顶顶顶顶顶顶顶顶多多多多多多多多多多多多顶顶顶顶顶顶顶顶顶顶多多多多多多多多多多多多顶顶顶顶顶顶顶顶顶顶多多多多多多多多多多多多</p>','x5第一交易信息测试','4028db815b1e1f0a015b1e2040510002','三星','123321123@qq.com','4028db815af4c7ca015af4c97a4c0005','x55','123321321321',0),('4028db815b5b9113015b5b934ee90000','2017/04/11 13:52:21','4028db815b1e1f0a015b1e2040510001','图书','2017/04/26 20:54:06','x6发布交易信息',320,'八成新','media\\surfacePic\\3\\2\\8.jpg','','<p>发发如热风枪供热歌曲个全国前二个热发发如热风枪供热歌曲个全国前二个热发发如热风枪供热歌曲个全国前二个热发发如热风枪供热歌曲个全国前二个热发发如热风枪供热歌曲个全国前二个热发发如热风枪供热歌曲个全国前二个热发发如热风枪供热歌曲个全国前二个热发发如热风枪供热歌曲个全国前二个热发发如热风枪供热歌曲个全国前二个热发发如热风枪供热歌曲个全国前二个热发发如热风枪供热歌曲个全国前二个热</p>','x6发布交易信息','4028db815b5bc774015b5bd417f90000','教材','369761578@qq.com','4028db815af4c7ca015af4c97a4c0006','x66','123421321',1),('4028db815b5bc774015b5bd707180006','2017/04/11 15:06:09','4028db815b4d2ef0015b4d3984390000','体育','2017/04/26 20:56:39','x6发布交易信息测试',2222,'五成新','media\\surfacePic\\2\\c\\1.jpg','','<p>我符文dfa地方萨芬发多少</p>','x6发布交易信息测试','4028db815b5bc774015b5bd512130003','篮球','123321123@qq.com','4028db815af4c7ca015af4c97a4c0006','x66','123513421',0),('4028db815b5bc774015b5bd987c40007','2017/04/11 15:09:45','4028db815b1e1f0a015b1e2040510001','图书','2017/04/26 20:55:04','而无法',3234,'32','media\\surfacePic\\5\\6\\11.jpg','','<p>额物权法</p><p>范德萨</p><p>的说法</p>','x6发布','4028db815b5bc774015b5bd417f90000','教材','123321123@qq.com','4028db815af4c7ca015af4c97a4c0006','x66','13312341234',0),('4028db815b6118f1015b475c3f0b0001','2017/04/07 15:40:43','4028db815b1e1f0a015b1e2040510000','手机/数码','2017/04/26 20:31:50','5555',5555,'55','media\\surfacePic\\d\\f\\photo4.jpg','','<p>rqwfqw</p>','测试55555','4028db815b1e1f0a015b1e2040510002','三星','123321123@qq.com','4028db815af4c7ca015af4c97a4c0005','x55','18888888888',0),('4028db815b6118f1015b611a39f80000','2017/04/12 15:38:25','4028db815b1e1f0a015b1e2040510000','手机/数码','2017/04/26 20:30:01','x5测试2',123,'九成新','media\\surfacePic\\d\\f\\photo1.jpg','','<p>jiushi&nbsp;<br/></p>','x5测试2','4028db815b1e1f0a015b1e2040510002','三星','369761578@qq.com','4028db815af4c7ca015af4c97a4c0005','x55','13312341234',1);

/*Table structure for table `index_pic_model` */

DROP TABLE IF EXISTS `index_pic_model`;

CREATE TABLE `index_pic_model` (
  `id` varchar(255) NOT NULL,
  `buttonlink` varchar(200) DEFAULT NULL,
  `buttonname` varchar(200) DEFAULT NULL,
  `isuse` int(11) DEFAULT NULL,
  `menuorder` int(11) DEFAULT NULL,
  `piclink` varchar(200) DEFAULT NULL,
  `picurl` varchar(200) DEFAULT NULL,
  `secondtitle` varchar(200) DEFAULT NULL,
  `text` longtext,
  `title` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `index_pic_model` */

insert  into `index_pic_model`(`id`,`buttonlink`,`buttonname`,`isuse`,`menuorder`,`piclink`,`picurl`,`secondtitle`,`text`,`title`) values ('4028db815b246373015b2464ed840000','#showMenuDiv','了解更多',0,0,'#','media/image/img1.png','文理小跳蚤致力于服务全校师生，创建勤俭节约的良好氛围。','<p>在这里，您可以<br/>买卖数码产品，校友交易有保障。帮验机，卖得快。<br/>买卖图书音像，同校交流更省心。帮运输，超方便。<br/>······<br/>在文理小跳蚤，你发布的每一件商品都会被更多的人看到，断舍离从未变得如此美好。<br/></p>','何波何波文理小跳蚤欢迎您...'),('4028db815b246373015b2464ed840001','#showMenuDiv','按钮名',0,1,'#','media/image/img1.png','2标','内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容','标题'),('4028db815b246373015b2464ed840002','#showMenuDiv','立即购买',0,2,'#','media/image/img1_2.png','在文理小跳蚤，大家努力让买卖闲置物品这件事变得更轻松！','这里的商品应有尽有，商品质量有保障，我们交易的是诚信。<br>这里的价格低廉合适，闲置物品合理处理，我们服务的是大家。<br>这里的交易者是家人，学长学姐学弟学妹，我们买的是情感。<br><br><br>','低价好货等你来拿...');

/*Table structure for table `menu` */

DROP TABLE IF EXISTS `menu`;

CREATE TABLE `menu` (
  `id` varchar(255) NOT NULL,
  `isshow` int(11) DEFAULT NULL,
  `isuse` int(11) DEFAULT NULL,
  `menuorder` int(11) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  `parentsid` varchar(50) DEFAULT NULL,
  `picurl` varchar(200) DEFAULT NULL,
  `text` longtext,
  `thumbnailurl` varchar(200) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `menu` */

insert  into `menu`(`id`,`isshow`,`isuse`,`menuorder`,`name`,`parentsid`,`picurl`,`text`,`thumbnailurl`,`url`) values ('4028db815b1e1f0a015b1e2040510000',0,0,0,'手机/数码','0','media/image/img3.png','废旧手机闲置在家中，日积月累越来越多?想扔掉这些旧手机?请三思而行，或许你正在扔掉一个小金矿。<br><br>想换个新手机，手里又拮据？<br>想换换新风格，没必要买新机？<br>想试试没用过的数码产品？<br>想过天天有新鲜玩意儿陪的日子？<br>来文理小跳蚤手机数码专区。','icon-windows',NULL),('4028db815b1e1f0a015b1e2040510001',0,0,1,'图书','0','media/image/img4.png','废旧手机闲置在家中，日积月累越来越多?想扔掉这些旧手机?请三思而行，或许你正在扔掉一个小金矿。<br><br>想换个新手机，手里又拮据？<br>想换换新风格，没必要买新机？<br>想试试没用过的数码产品？<br>想过天天有新鲜玩意儿陪的日子？<br>来文理小跳蚤手机数码专区。','icon-book',NULL),('4028db815b1e1f0a015b1e2040510002',0,0,3,'三星','4028db815b1e1f0a015b1e2040510000','',NULL,NULL,NULL),('4028db815b1e1f0a015b1e2040510003',NULL,0,NULL,'华为','4028db815b1e1f0a015b1e2040510000',NULL,NULL,NULL,''),('4028db815b4d2ef0015b4d3984390000',1,0,5,'体育','0','media\\menuPic\\d\\3\\img2.png','<p>发斯蒂芬第三方三是afs 的说法 是是范德萨发的发</p>','icon-dribble','#'),('4028db815b5bc774015b5bd417f90000',NULL,0,NULL,'教材','4028db815b1e1f0a015b1e2040510001',NULL,NULL,NULL,''),('4028db815b5bc774015b5bd44b2c0001',NULL,0,NULL,'计算机','4028db815b1e1f0a015b1e2040510001',NULL,NULL,NULL,''),('4028db815b5bc774015b5bd4aa780002',NULL,0,NULL,'生物与科学','4028db815b1e1f0a015b1e2040510001',NULL,NULL,NULL,''),('4028db815b5bc774015b5bd512130003',NULL,0,NULL,'篮球','4028db815b4d2ef0015b4d3984390000',NULL,NULL,NULL,''),('4028db815b5bc774015b5bd529790004',NULL,0,NULL,'足球','4028db815b4d2ef0015b4d3984390000',NULL,NULL,NULL,''),('4028db815b5bc774015b5bd558120005',NULL,0,NULL,'排球','4028db815b4d2ef0015b4d3984390000',NULL,NULL,NULL,''),('8a1688fb5c0b9764015c0b9c9fc00000',NULL,0,NULL,'OPPO','4028db815b1e1f0a015b1e2040510000',NULL,NULL,NULL,'');

/*Table structure for table `message` */

DROP TABLE IF EXISTS `message`;

CREATE TABLE `message` (
  `id` varchar(255) NOT NULL,
  `blog_id` varchar(255) DEFAULT NULL,
  `blog_name` varchar(255) DEFAULT NULL,
  `is_use` int(11) DEFAULT NULL,
  `message_type` int(11) DEFAULT NULL,
  `parent_id` varchar(255) DEFAULT NULL,
  `receiver_id` varchar(255) DEFAULT NULL,
  `receiver_name` varchar(255) DEFAULT NULL,
  `send_time` varchar(255) DEFAULT NULL,
  `sender_id` varchar(255) DEFAULT NULL,
  `sender_name` varchar(255) DEFAULT NULL,
  `text` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `message` */

insert  into `message`(`id`,`blog_id`,`blog_name`,`is_use`,`message_type`,`parent_id`,`receiver_id`,`receiver_name`,`send_time`,`sender_id`,`sender_name`,`text`) values ('297eb0e35bedb6e8015bedbd0fe30000','4028db815b37b3bb015b37c4b1be0000','432test',0,0,'','','','2017/05/09 23:04:02','4028db815af4c7ca015af4c97a4c0003','x3','哇 全新的平板电脑'),('297eb0e35bedb6e8015bedbdb7600001','4028db815b37b3bb015b37c4b1be0000','432test',0,0,'4028db815b56c394015b56c539330003','4028db815af4c7ca015af4c97a4c0001','x1为','2017/05/09 23:04:45','4028db815af4c7ca015af4c97a4c0003','x3','回复@x1为: 哇 真的是好<hr><div class=\'alert green\'>  原文——@x1为:用户评论测试3</div>'),('4028db815b56c394015b56c4f9c60001','4028db815b37b3bb015b37c4b1be0000','432',0,0,'',NULL,NULL,'2017/04/21 15:30:01','4028db815af4c7ca015af4c97a4c0001','x1为','用户评论测试1'),('4028db815b56c394015b56c539330003','4028db815b37b3bb015b37c4b1be0000','432',0,0,'',NULL,NULL,'2017/04/29 15:30:17','4028db815af4c7ca015af4c97a4c0001','x1为','用户评论测试3'),('4028db815b56d5ac015b56d643ba0000','4028db815b37b3bb015b37c4b1be0000','432',0,0,'','','','2017/04/28 15:48:54','4028db815af4c7ca015af4c97a4c0001','x1为','new test'),('4028db815b570b73015b570ce8fd0000','4028db815b37b3bb015b37c4b1be0000','432',0,0,'4028db815b56c394015b56c539330003','4028db815af4c7ca015af4c97a4c0001','x1为','2017/04/28 16:48:35','4028db815af4c7ca015af4c97a4c0001','x1为','回复@x1为: 的撒范德萨发佛挡杀佛是飒飒<hr><div class=\'alert green\'>原文——@x1为:用户评论测试3</div>'),('4028db815b57118d015b57195c7d0000','4028db815b37b3bb015b37c4b1be0000','432',0,0,'4028db815b570b73015b570ce8fd0000','4028db815af4c7ca015af4c97a4c0001','x1为','2017/04/27 17:02:11','4028db815af4c7ca015af4c97a4c0001','x11','回复@x1为: 打撒<hr><div class=\'alert green\'>  原文——@x1为:回复@x1为: 的撒范德萨发佛挡杀佛是飒飒<hr><div class=\'alert green\'>原文——@x1为:用户评论测试3</div></div>'),('4028db815b57118d015b5719cbfd0001','4028db815b37b3bb015b37c4b1be0000','432',0,0,'4028db815b57118d015b57195c7d0000','4028db815af4c7ca015af4c97a4c0001','x11','2017/04/20 17:02:40','4028db815af4c7ca015af4c97a4c0001','x11','回复@x11: 我的<hr><div class=\'alert green\'>  原文——@x11:回复@x1为: 打撒<hr><div class=\'alert green\'>  原文——@x1为:回复@x1为: 的撒范德萨发佛挡杀佛是飒飒<hr><div class=\'alert green\'>原文——@x1为:用户评论测试3</div></div></div>'),('4028db815b6118f1015b475e40a00002','4028db815b5b832c015b5b8b793c0000','x5第一交易信息测试',0,0,'','','','2017/04/27 15:43:07','4028db815af4c7ca015af4c97a4c0005','x5','第三方三'),('8a168d835baa3723015baa3aea030003','4028db815b6118f1015b611a39f80000','x5测试2',0,0,'','','','2017/04/26 20:27:19','4028db815af4c7ca015af4c97a4c0000','xjwTZ','抢沙发'),('8a168d835baa3723015baa3faf2e0004','4028db815b6118f1015b611a39f80000','x5测试2',0,0,'8a168d835baa3723015baa3aea030003','4028db815af4c7ca015af4c97a4c0000','xjwTZ','2017/04/26 20:32:32','4028db815af4c7ca015af4c97a4c0005','x5','回复@xjwTZ: wo de <hr><div class=\'alert green\'>  原文——@xjwTZ:抢沙发</div>');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` varchar(255) NOT NULL,
  `createtime` varchar(100) DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  `is_manager` int(11) DEFAULT NULL,
  `is_use` int(11) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  `nickname` varchar(200) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  `sex` varchar(200) DEFAULT NULL,
  `telephone` varchar(200) DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`createtime`,`email`,`is_manager`,`is_use`,`name`,`nickname`,`password`,`sex`,`telephone`,`updatetime`) values ('297e22e45bf0b5d4015bf0b994bd0000','2017/05/10 12:59:05','1062441009@qq.com',0,0,NULL,'xiejw','123456',NULL,NULL,'2017-05-10 12:59:05'),('4028db815af4c7ca015af4c97a4c0000','2017/03/21 14:52:01','imxjwdd@163.com',2,0,'xjwTZ','xjwTZ','1',NULL,'18812341234','2017-04-28 14:03:39'),('4028db815af4c7ca015af4c97a4c0001','2017/03/21 14:52:02','imxjwdd@163.com',1,0,'谢佳伟','x11','1','男','18812341231','2017-05-09 23:02:09'),('4028db815af4c7ca015af4c97a4c0002','2017/03/22 14:52:03','imxjwdd@163.com',1,0,'x2','x2','1','女','18812341232',NULL),('4028db815af4c7ca015af4c97a4c0003','2017/03/25 14:52:07','imxjwdd@163.com',0,0,'x32','x3','1',NULL,'18812341233',NULL),('4028db815af4c7ca015af4c97a4c0004','2017/03/25 14:52:09','imxjwdd@163.com',0,0,'x42','x4','1',NULL,'18812341234',NULL),('4028db815af4c7ca015af4c97a4c0005','2017/03/22 14:52:04','imxjwdd@163.com',0,0,'x55','x5','1',NULL,'18812341235',NULL),('4028db815af4c7ca015af4c97a4c0006','2017/03/22 14:52:05','imxjwdd@163.com',0,0,'x66','x6','1',NULL,'18812341236',NULL),('4028db815af4c7ca015af4c97a4c0007','2017/04/23 14:52:06','imxjwdd@163.com',1,0,'x7','x7','1',NULL,'18812341237',NULL),('4028db815afa9589015afa96385a0000','2017/04/12 17:53:36','imxjwdd@163.com',1,0,'谢佳伟','x8','123456','男','','2017-04-26 17:23:33'),('4028db815afa9589015afa9a04680001','2017/03/26 17:57:26','imxjwdd@163.com',0,0,'x9','x9','123456','女','','2017-03-23 17:58:41'),('8a168ac35ba8b15f015ba8b9cb350000','2017/04/12 13:26:09','imxjwdd@163.com',1,0,'谢佳伟','xiejiawei','123456','男','15619950406','2017-03-25 13:26:40'),('8a168d835baa3723015baa37dc4e0000','2017/04/26 20:23:47','imxjwdd@163.com',0,0,'1','guanyi','123456','男','','2017-04-26 20:23:59'),('8a168d835baa3723015baa3823e70001','2017/04/26 20:24:05','1062441009@qq.com',1,1,'','guan2','123456','男','','2017-04-26 20:24:17');

/*Table structure for table `user_record` */

DROP TABLE IF EXISTS `user_record`;

CREATE TABLE `user_record` (
  `id` varchar(255) NOT NULL,
  `adminid` varchar(200) DEFAULT NULL,
  `operation` varchar(200) DEFAULT NULL,
  `reason` varchar(200) DEFAULT NULL,
  `recordtime` datetime DEFAULT NULL,
  `userid` varchar(200) DEFAULT NULL,
  `admin_nickname` varchar(200) DEFAULT NULL,
  `user_nickname` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_record` */

insert  into `user_record`(`id`,`adminid`,`operation`,`reason`,`recordtime`,`userid`,`admin_nickname`,`user_nickname`) values ('4028db815affad3f015affc5b070000f','4028db815af4c7ca015af4c97a4c0000','禁用','test','2017-03-24 18:03:50','4028db815afa9589015afa9a04680001','xjwTZ','x9'),('4028db815affda5d015affdd392f0000','4028db815af4c7ca015af4c97a4c0000','启用','','2017-03-24 18:29:32','4028db815afa9589015afa9a04680001','xjwTZ','x9'),('4028db815affda5d015affdd68550001','4028db815af4c7ca015af4c97a4c0000','禁用','test','2017-03-24 18:29:44','4028db815afa9589015afa9a04680001','xjwTZ','x9'),('4028db815affda5d015affdd73220002','4028db815af4c7ca015af4c97a4c0000','启用','','2017-03-24 18:29:47','4028db815afa9589015afa9a04680001','xjwTZ','x9'),('4028db815afff7d9015afff966a60000','4028db815af4c7ca015af4c97a4c0001','禁用','test','2017-03-24 19:00:19','4028db815afa9589015afa96385a0000','x11','x8'),('4028db815afff7d9015afff9826e0001','4028db815af4c7ca015af4c97a4c0001','启用','','2017-03-24 19:00:26','4028db815afa9589015afa96385a0000','x11','x8'),('4028db815b283020015b28455c330000','4028db815af4c7ca015af4c97a4c0001','禁用','','2017-04-01 14:48:05','4028db815afa9589015afa96385a0000','x11','x8'),('4028db815b283020015b284571160001','4028db815af4c7ca015af4c97a4c0001','启用','','2017-04-01 14:48:11','4028db815afa9589015afa96385a0000','x11','x8'),('8a168d835baa3723015baa38a5ec0002','4028db815af4c7ca015af4c97a4c0000','禁用','ss','2017-04-26 20:24:50','8a168d835baa3723015baa3823e70001','xjwTZ','guan2'),('8a168ee35bb325a3015bb3278c700000','4028db815af4c7ca015af4c97a4c0000','禁用','禁用测试','2017-04-28 14:02:45','4028db815af4c7ca015af4c97a4c0001','xjwTZ','x11'),('8a168ee35bb325a3015bb32797b60001','4028db815af4c7ca015af4c97a4c0000','启用','','2017-04-28 14:02:48','4028db815af4c7ca015af4c97a4c0001','xjwTZ','x11');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
