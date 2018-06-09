/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.5.23 : Database - trip
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`trip` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `trip`;

/*Table structure for table `citytb` */

DROP TABLE IF EXISTS `citytb`;

CREATE TABLE `citytb` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `citytb` */

insert  into `citytb`(`id`,`name`) values (1,'重庆'),(2,'泸州'),(3,'北京'),(4,'上海'),(6,'南京'),(8,'全国');

/*Table structure for table `dingdan` */

DROP TABLE IF EXISTS `dingdan`;

CREATE TABLE `dingdan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  `content` varchar(80) DEFAULT NULL,
  `price` float NOT NULL,
  `telephone` varchar(11) NOT NULL DEFAULT '',
  `image` varchar(40) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_dingdan_user` (`userid`),
  CONSTRAINT `FK_dingdan_user` FOREIGN KEY (`userid`) REFERENCES `userinfo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

/*Data for the table `dingdan` */

insert  into `dingdan`(`id`,`name`,`content`,`price`,`telephone`,`image`,`userid`) values (20,'九寨沟','原生态，风景秀丽',120,'48430963','xianggang.jpg',1),(21,'九寨沟','原生态，风景秀丽',120,'48430963','xianggang.jpg',1),(22,'洪崖洞','千与千寻的取景地，观赏重庆夜景的好去处',30,'13140208470','jiuzhaigou.jpg',1),(24,'上海航空','主飞海外旅行',999,'63636363636','plane3.jpg',2),(25,'重庆航空','可飞往海内外',888,'12112112133','plane1.jpg',1),(26,'ni','qe',20,'12313','eqw',2);

/*Table structure for table `hotel` */

DROP TABLE IF EXISTS `hotel`;

CREATE TABLE `hotel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  `content` varchar(80) DEFAULT NULL,
  `price` float NOT NULL,
  `telephone` varchar(11) NOT NULL,
  `image` varchar(40) NOT NULL,
  `cityid` int(11) DEFAULT NULL,
  `typeid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_hotel_city` (`cityid`),
  KEY `FK_hotel_type` (`typeid`),
  CONSTRAINT `FK_hotel_city` FOREIGN KEY (`cityid`) REFERENCES `citytb` (`id`),
  CONSTRAINT `FK_hotel_typehotel` FOREIGN KEY (`typeid`) REFERENCES `typehoteltb` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

/*Data for the table `hotel` */

insert  into `hotel`(`id`,`name`,`content`,`price`,`telephone`,`image`,`cityid`,`typeid`) values (1,'七天连锁酒店','全国各地均有连锁店，安全，便捷',168,'12345678911','qitian.jpg',8,1),(2,'尚客优连锁酒店','时尚舒适的旅行之家，给您舒适的服务',248,'78945612311','shangkeyou.jpg',8,1),(3,'如家酒店','让您尽享如家一般的服务，到酒店就到家了',400,'69325874111','rujiajiudian.jpg',8,1),(4,'ZMAX潮漫酒店','酒店为铂涛集团旗下新锐的社交酒店品牌ZMAX潮漫力作,无论是独处、玩聚，或是商务会谈，都能在这里享受到非同一般的惊喜与体验',168,'023-6769668','zmax.jpg',1,3),(5,'全季酒店','全季酒店（重庆解放碑步行街店）位于长江、嘉陵江环抱的“渝中半岛”中心解放碑，地处重庆解放碑最繁华最核心的商业步行街',263,'023-6393799','quanji.jpg',1,4),(6,'重庆洪崖洞大酒店','重庆洪崖洞大酒店位于国家级4A风景区洪崖洞旅游景区内，地处市中心，风景优美，交通便捷，酒店采用独特气派的现代中式建筑风格，以别具重庆特色的吊角楼造型的楼体组成',404,'023-6399288','cqhy.jpg',1,1),(7,'重庆凯宾斯基酒店','凯宾斯基酒店管理集团已将享誉全球的欧式奢华品质与细致入微的服务带到了重庆',548,'023-8688888','cqkbs.jpg',1,2),(8,'重庆希尔顿酒店','重庆希尔顿酒店由希尔顿集团统一管理，地处商务、休闲的绝佳位置，坐落于重庆市交通枢纽，两路口地铁站旁，临近解放碑商圈，免费WIFI覆盖酒店公共区域，客房公共区域免',510,'023-8903999','cqxed.jpg',1,2),(9,'上海迪士尼乐园酒店','“上海迪士尼乐园酒店”是一座造型别致的新艺术风格酒店，充满了迪士尼的神奇和想象力',1748,'4001800000','shdsn.jpg',4,2),(10,'上海华亭宾馆','上海华亭宾馆位于漕溪北路，出门即是地铁1、4号线，漫步即可抵达徐家汇商业中心，与八万人体育馆咫尺之遥',852,'021-6439600','shht.jpg',4,3),(11,'上海外滩浦华大酒店','上海外滩浦华大酒店位置优越，距离著名的黄浦江北外滩仅一步之遥。酒店豪华的装饰，典雅的格调，超常的服务，是商务差旅和度假休闲的理想之选',868,'021-5558999','shwtph.jpg',4,2),(12,'锦江之星','锦江之星（上海浦东机场二店）位于浦东机场工作区，全新的产品是商务人士和旅游客人理想的下榻之处，酒店有免费的班车来往于候机楼，交通便捷',326,'021-6885388','pjzx.jpg',4,3),(13,'上海浦东香格里拉大酒店','上海浦东香格里拉大酒店毗邻黄浦江，地处陆家嘴金融贸易区中心区域，紧邻延安东路隧道、复兴路隧道、地铁二号线，浦江两岸景色一览无余，东方明珠、海洋水族馆、正大广场等',2029,'021-6882888','shpdxgll.jpg',4,2),(14,'泸州千升酒店','泸州千升酒店是一家商务型酒店，位于江阳北路，近水井沟',147,'0830-666333','lzqs.jpg',2,1),(15,'泸州酒城宾馆','泸州酒城宾馆位于泸州市区中心，系“杨森公馆”旧址，树阴如盖，历史悠久。宾馆独特的园林式设计与周围幽雅的环境融为一体，实为各界人士下榻的最佳之处',524,'0830-315999','lzjcbg.jpg',2,4),(16,'泸州天河酒店','泸州天河酒店是一家商务型酒店，位于龙马大道二段，临近龙马潭区区政府',120,'0830-251366','lzth.jpg',2,2),(17,'泸州半岛酒店','泸州半岛酒店位于慈善路，周边生活设施完备，位置优越，交通便利',163,'0830-602666','lzbd.jpg',2,1),(18,'泸州唯逸酒店','这是一间住了会幸福的酒店，从清晨的太极、上午的微办公区、午后的下午茶，民谣音乐演绎，晚间的健身、瑜伽及睡前的中药泡脚，让您的旅程缓解身心，享以自在，如此的放松',239,'0830-311668','lzwy.jpg',2,2),(19,'北京新世界酒店','北京新世界酒店秉承现代东方待客之道，用心致力于为商务及休闲旅客提供个性化的服务以及独一无二的时尚空间，是您追求舒适便捷，物超所值的理想选择',895,'010-5960888','bjxsj.jpg',3,1),(20,'北京内蒙古大厦','北京内蒙古大厦是内蒙古自治区人民政府在京兴建的一家具有浓郁内蒙特色的商务酒店，客房舒适整洁、环境温馨雅致，配套设施先进，为无烟酒店',693,'010-6518666','bjnmg.jpg',3,2),(21,'北京首都大酒店','酒店位于北京市中心著名的商业、风景区内，距天安门广场和举世闻名的紫禁城仅咫尺之遥，临近王府井商业区；酒店隶属于国家机关事务管理局，建设于上个世纪80年代，曾成功',702,'010-5815998','bjsd.jpg',3,2),(22,'北京新侨诺富特饭店','北京新侨诺富特饭店矗立在北京市繁华的购物、商业和政府办公区，距离故宫，天安门广场和天坛等名胜古迹咫尺之遥。步行1分钟至地铁，驱车5分钟至北京火车站。商务楼层可以',552,'010-6513336','bjxqnft.jpg',3,4),(23,'北京希尔顿逸林酒店','酒店位于西二环广安门外大街，近地铁7号线达官营站，毗邻金融街，与马连道茶叶特色休闲街和北京西客站、金融街仅咫尺之遥；酒店拥有200多件来自中、外三十多名艺术家的',724,'010-6338188','bjxed.jpg',3,2),(24,'南京威斯汀大酒店','南京威斯汀大酒店地处湖南路中央商务区，距离主要餐厅及购物场所仅数步之遥，地理位置优越，可俯瞰风景如画的玄武湖，是一处具有中国特色的酒店',896,'025-8556888','njwst.jpg',6,1),(25,'南京金陵饭店','南京金陵饭店位于南京市繁华的商业金融中心—新街口，周边地区拥有完善的商业、金融机构及通达全城的交通网络。无论是市内观光还是商务旅行，都十分便捷',694,'025-8471188','njjl.jpg',6,2),(26,'南京鼎业开元大酒店','南京鼎业开元大酒店位于江北最繁华的地段，交通出行均便捷，客房配有液晶壁挂电视，冲淋和浴缸分开等人性化设计，入住更感舒适豪华，各项完备的配套设置让您的出行更显品味',513,'025-5828888','njdyky.jpg',6,2),(27,'南京玄武饭店','南京玄武饭店座落于城市中央，地理位置优越，毗邻风景怡人的玄武湖，坐拥南京著名的大型购物美食街区，比肩众多百货公司，文化中心，办公楼宇；交通便利，城市地铁直达酒店',693,'025-8335888','njxw.jpg',6,4),(28,'南京索菲特银河大酒店','南京索菲特银河大酒店是法国雅高酒店管理集团管理的高端奢华品牌，酒店座落于湖南路繁华商业中心，酒店无可比拟的现代化设计，豪华舒适的客房设施，完善先进的会议服务，曾',563,'025-8371888','njsft.jpg',6,2),(29,'汉庭酒店','全国连锁快捷酒店，舒适豪华，各项完备的配套设置让您的出行更显品味',156,'025-5222567','htkjjd.jpg',8,1);

/*Table structure for table `jingdian` */

DROP TABLE IF EXISTS `jingdian`;

CREATE TABLE `jingdian` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  `content` varchar(80) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `telephone` varchar(11) NOT NULL,
  `image` varchar(40) DEFAULT NULL,
  `cityid` int(11) NOT NULL,
  `typeid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_jingdian_city` (`cityid`),
  KEY `FK_jingdian_type` (`typeid`),
  CONSTRAINT `FK_jingdian_city` FOREIGN KEY (`cityid`) REFERENCES `citytb` (`id`),
  CONSTRAINT `FK_jingdian_type` FOREIGN KEY (`typeid`) REFERENCES `typetb` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

/*Data for the table `jingdian` */

insert  into `jingdian`(`id`,`name`,`content`,`price`,`telephone`,`image`,`cityid`,`typeid`) values (1,'洪崖洞','洪崖洞，位于重庆直辖市核心商圈解放碑沧白路，长江、嘉陵江两江交汇的滨江地带，坐拥城市旅游景观、商务休闲景观和城市人文景观于一体',30,'13140208470','hongyadong.jpg',1,1),(2,'解放碑','解放碑全称“人民解放纪念碑”，位于重庆市渝中区商业区中心部位，民族路、民权路、邹容路交汇处，是重庆标志性建筑之一',35,'15178710812','jiefangbei.jpg',1,2),(3,'峨眉山','峨眉山处于多种自然要素的交汇地区，区系成分复杂，生物种类丰富，特有物种繁多，保存有完整的亚热带植被体系',60,'18323898129','emeishan.jpg',2,1),(4,'九寨沟','九寨沟位于四川省阿坝藏族羌族自治州九寨沟县境内，地处青藏高原向四川盆地过渡地带',120,'48430963','jiuzhaigou.jpg',2,2),(5,'狮子山','狮子山早在1.4亿年前已形成，现在狮子山的位置，从前是一片大熔岩，“狮子”头面向九龙西边，狮身连尾巴完整地伏在山上',20,'11011211912','shizisahn.jpg',1,3),(6,'新加坡 ','新加坡6日4晚自由行(3钻)·西部航空直飞 4晚连住不挪窝/省心省力 ',100,'12424724222','xinjiapo.jpg',8,1),(7,'张家界','张家界4日自由行·【低价保证】1晚市区天门山+2晚景区武陵源',200,'13453543533','zhangjiajie.jpg',8,4),(8,'长江三峡','长江三峡4日3晚半自助游(4钻)·美维系列 重庆-宜昌航线 船票 特卖',210,'15417821121','changjaingsanxia.jpg',1,3),(9,'香港','香港+迪士尼（Disney）4日3晚半自助游·特惠纯玩 海洋公园+全天迪士尼+1天自由行！',400,'42542542522','xinjiapo.jpg',8,4),(10,'武隆天生三桥','<武隆天生三桥-龙水峡地缝1日游>早餐 自助中餐 下午茶 耳麦讲解 WIFI',268,'15174318906','wulong.jpg',1,2),(11,'磁器口','国家AAAA级景区，中国历史文化名街，重庆市重点保护传统街，重庆“新巴渝十二景”',128,'0537-662456','ciqikou.jpg',1,2),(12,'长江索道','长江索道，位于中国重庆，是重庆第二条跨江索道（第一条为嘉陵江索道），已经运行近30年，被誉为“万里长江第一条空中走廊”和“山城空中公共汽车”',35,'13578719875','changjaingsuodao.jpg',1,2),(13,'双人长江索道','城市漫游之重庆-坐长江索道-揽胜重庆',68,'14589746464','changjaingsuodao1.jpg',1,1),(14,'武隆天生三桥','<武隆天生三桥-龙水峡地缝1日游>早餐 自助中餐 下午茶 耳麦讲解 WIFI',300,'15174318906','wulong.jpg',1,1),(15,'解放碑','解放碑全称“人民解放纪念碑”，位于重庆市渝中区商业区中心部位，民族路、民权路、邹容路交汇处，是重庆标志性建筑之一',70,'15178710812','jiefangbei.jpg',1,1),(16,'洪崖洞','洪崖洞，位于重庆直辖市核心商圈解放碑沧白路，长江、嘉陵江两江交汇的滨江地带，坐拥城市旅游景观、商务休闲景观和城市人文景观于一体',60,'13140208470','hongyadong.jpg',1,3),(17,'八达岭长城','是中国古代伟大的防御工程万里长城的重要组成部分，是明长城的一个隘口。八达岭长城为居庸关的重要前哨，古称“居庸之险不在关而在八达岭',0,'12141321312','cahngchen.jpg',3,1),(18,'国家体育场（鸟巢）','为2008年北京奥运会的主体育场。工程总占地面积21公顷，场内观众坐席约为91000个',30,'0430-123141','liaochao.jpg',3,2),(19,'定陵博物馆','定陵博物馆为遗址性专题博物馆，位于北京以北40公里处大峪山脚下，定陵建于明万历十二年-万历十八年（公元1584年-1590年），占地面积8万平方米',40,'12144123123','dinglings.jpg',3,3),(20,'古北水镇','古北水镇是司马台长城脚下独具北方风情的度假式小镇。北京古北水镇旅游有限公司成立于2010年7月，由IDG战略资本、中青旅控股股份有限公司',300,'13957563532','gubeishuizhen.jpg',3,4);

/*Table structure for table `plane` */

DROP TABLE IF EXISTS `plane`;

CREATE TABLE `plane` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  `content` varchar(80) DEFAULT NULL,
  `price` float NOT NULL,
  `telephone` varchar(11) NOT NULL,
  `image` varchar(40) NOT NULL,
  `cityid` int(11) DEFAULT NULL,
  `typeid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_plane_city` (`cityid`),
  KEY `FK_plane_type` (`typeid`),
  CONSTRAINT `FK_plane_city` FOREIGN KEY (`cityid`) REFERENCES `citytb` (`id`),
  CONSTRAINT `FK_plane_typeplane` FOREIGN KEY (`typeid`) REFERENCES `typeplanetb` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

/*Data for the table `plane` */

insert  into `plane`(`id`,`name`,`content`,`price`,`telephone`,`image`,`cityid`,`typeid`) values (1,'中国南方航空','长沙-上海',224,'12112112133','zgnanfang.jpg',2,1),(2,'中国南方航空','海口-上海',234,'55664499789','zgnanfang.jpg',2,1),(3,'中国南方航空','西安-上海',254,'63636363636','zgnanfang.jpg',2,2),(4,'中国南方航空','北京-成都',294,'25896314712','zgnanfang.jpg',2,1),(5,'中国东方航空','海口-上海',100,'09309188829','zgdongfanghk.jpg',3,1),(6,'中国东方航空','南昌-西安',340,'31381998303','zgdongfanghk.jpg',3,2),(7,'中国东方航空','烟台-上海',389,'19873991001','zgdongfanghk.jpg',3,1),(8,'中国东方航空','长沙-昆明',712,'27838739383','zgdongfanghk.jpg',3,2),(9,'深圳航空','南昌-西安',348,'13455444333','shenzhen.jpg',6,1),(10,'深圳航空','福州-长沙',518,'53553256774','shenzhen.jpg',6,2),(11,'深圳航空','大连-杭州',600,'67744332222','shenzhen.jpg',6,3),(12,'深圳航空','三亚-北京',799,'56734677888','shenzhen.jpg',6,1),(13,'中国国际航空','海口-上海',899,'63268809055','zgguojihk.jpg',4,3),(14,'中国国际航空','北京-上海',120,'99832179013','zgguojihk.jpg',4,4),(15,'中国国际航空','西安-上海',300,'89876393993','zgguojihk.jpg',4,3),(16,'中国国际航空','长沙-成都',400,'83938663323','zgguojihk.jpg',4,3),(17,'重庆航空','重庆-北京',655,'66784920002','cqhangkong.jpg',1,1),(18,'重庆航空','重庆-上海',700,'99477999944','cqhangkong.jpg',1,1),(19,'重庆航空','重庆-成都',199,'88888888888','cqhangkong.jpg',1,1);

/*Table structure for table `typehoteltb` */

DROP TABLE IF EXISTS `typehoteltb`;

CREATE TABLE `typehoteltb` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `typehoteltb` */

insert  into `typehoteltb`(`id`,`name`) values (1,'连锁酒店'),(2,'星级酒店'),(3,'平民酒店'),(4,'其他酒店');

/*Table structure for table `typeplanetb` */

DROP TABLE IF EXISTS `typeplanetb`;

CREATE TABLE `typeplanetb` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `typeplanetb` */

insert  into `typeplanetb`(`id`,`name`) values (1,'国内机票'),(2,'海外机票'),(3,'跨国机票'),(4,'特价机票');

/*Table structure for table `typetb` */

DROP TABLE IF EXISTS `typetb`;

CREATE TABLE `typetb` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `typetb` */

insert  into `typetb`(`id`,`name`) values (1,'自由行'),(2,'跟团游'),(3,'自驾游'),(4,'其它');

/*Table structure for table `userinfo` */

DROP TABLE IF EXISTS `userinfo`;

CREATE TABLE `userinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(40) NOT NULL,
  `password` varchar(40) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `userinfo` */

insert  into `userinfo`(`id`,`username`,`password`) values (1,'boyce','1'),(2,'5','2'),(4,'terry','terry'),(5,'qqq','qqq');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
