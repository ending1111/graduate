CREATE DATABASE  IF NOT EXISTS `garbage_classification_db` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `garbage_classification_db`;
-- MySQL dump 10.13  Distrib 8.0.16, for macos10.14 (x86_64)
--
-- Host: localhost    Database: garbage_classification_db
-- ------------------------------------------------------
-- Server version	5.7.29-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `t_admin`
--

DROP TABLE IF EXISTS `t_admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `t_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_admin`
--

LOCK TABLES `t_admin` WRITE;
/*!40000 ALTER TABLE `t_admin` DISABLE KEYS */;
INSERT INTO `t_admin` VALUES (1,'admin','http://n.sinaimg.cn/sinacn10209/198/w99h99/20191010/0ea8-ifrwayx3799221.jpg','123');
/*!40000 ALTER TABLE `t_admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_garbage`
--

DROP TABLE IF EXISTS `t_garbage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `t_garbage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `g_name` varchar(45) NOT NULL,
  `g_type` int(11) NOT NULL COMMENT '1干垃圾、2湿垃圾、3可回收物、4有害垃圾',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_garbage`
--

LOCK TABLES `t_garbage` WRITE;
/*!40000 ALTER TABLE `t_garbage` DISABLE KEYS */;
INSERT INTO `t_garbage` VALUES (1,'纸张',1),(2,'塑料',1),(3,'玻璃',1),(4,'金属',1),(5,'残渣',2),(6,'瓜皮',2),(7,'毛发',2),(8,'树叶',2),(9,'易拉罐',3),(10,'矿泉水瓶',3),(11,'水银温度计',4),(12,'废油漆桶',4),(13,'废旧电池',4),(14,'尘土',1),(15,'碎餐具',1),(16,'布料',1),(17,'拖鞋',1),(18,'剩菜剩饭',2),(19,'沥青',2),(20,'动物尸体',2),(21,'废铁',3),(22,'旧电器',3),(23,'旧衣物',3),(24,'书本',3),(25,'医疗废水',4),(26,'化工原料',4),(27,'烟花火药',4),(28,'硫酸硫磺',4);
/*!40000 ALTER TABLE `t_garbage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_news`
--

DROP TABLE IF EXISTS `t_news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `t_news` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `author_name` varchar(45) NOT NULL,
  `author_avatar` varchar(255) NOT NULL,
  `content` mediumtext NOT NULL,
  `n_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_news`
--

LOCK TABLES `t_news` WRITE;
/*!40000 ALTER TABLE `t_news` DISABLE KEYS */;
INSERT INTO `t_news` VALUES (1,'2019上海垃圾分类四类标志公布!','上海本地宝','http://cd.bendibao.com/images/logo-new.jpg','几种复合垃圾处理法\n\n　　1、中药渣： 湿垃圾\n\n　　2、用过的餐巾纸、卫生纸或者厨房专用清洁纸： 干垃圾\n\n　　3、尿不湿： 虽然含水但仍然属于干垃圾\n\n　　4、喝剩半瓶的可乐怎么处理： 先把剩下的可乐倒入下水道，然后把瓶子用清水冲洗，接着把瓶子压扁，最后投放到可回收物垃圾桶中。\n\n　　5、吃剩的外卖： 剩饭剩菜倒进湿垃圾桶，餐盒扔进干垃圾桶。\n\n　　6、单独打包的湿垃圾： 先破袋，然后把湿垃圾倒进湿垃圾桶，最后把垃圾袋扔进干垃圾桶。\n\n　　提醒： 干垃圾和湿垃圾的区别，不是简单的含不含水;虽然纸张可以回收，但含水即溶的纸张却不能回收。','2019-02-25 17:45:00','http://n.sinaimg.cn/spider2020219/672/w428h244/20200219/d22c-iprtayz8141373.jpg'),(2,'北京市生活垃圾分类新规明年5月实施 明确生活垃圾四分法 个人违规将实行“教罚并举”','北京日报','http://www.newsimg.cn/xl2017/images/net_logo.png','昨天下午，《北京市生活垃圾管理条例》修改决定经市十五届人大常委会第十六次会议表决通过，修改后的条例将于明年5月1日正式施行。这是自2012年条例施行以来，本市首次对该条例进行修改。\n\n　　修改后的条例首次明确，单位和个人是生活垃圾分类投放的责任主体，并对个人违法投放垃圾的行为，实行教育和处罚相结合。违规投放的个人“屡教不改”，最高可处200元罚款。此外，要求餐馆、旅馆不得主动提供一次性用品，并对“混装混运”现象加大了处罚力度。\n\n　　在随后举行的新闻发布会上，市人大常委会法制办主任王荣梅、市人大常委会城建环保办主任郝志兰、市城市管理委员会主任孙新军进一步介绍了法规修改的背景、重点内容及下一步实施举措。','2019-11-28 08:15:46','http://n.sinaimg.cn/spider2020219/672/w428h244/20200219/d22c-iprtayz8141373.jpg'),(3,'长沙侨企仁和环保保障全市垃圾处理安全、稳定运行','新安晚报','http://n.sinaimg.cn/sinacn10208/360/w180h180/20191010/ff3d-ifrwayx3647638.jpg','红网时刻2月19日（通讯员 夏巍）自疫情发生以来，长沙侨企湖南仁和环保科技有限公司第一时间成立疫情防控领导小组，迅速决策部署，制定安全生产运行与疫情防控方案，全体员工“硬核坚守”，连续奋战在环保工作一线，以最实际、最有力的行动保障春节期间全市垃圾处理安全稳定运行，助力疫情防控阻击战。','2020-02-20 00:15:42','http://n.sinaimg.cn/spider2020219/677/w422h255/20200219/aa30-iprtayz8141439.jpg'),(4,'减少近七千吨建筑垃圾 揭秘龙光地产装配式建筑匠造之道','和讯网','http://n.sinaimg.cn/sinacn10208/360/w180h180/20191010/ff3d-ifrwayx3647638.jpg','近年来，随着社会发展，绿色低碳、节能环保成为许多城市建设的共同追求，众多房企也开始积极介入装配式建筑、绿色建筑等新兴领域。作为大湾区龙头房企，龙光地产顺应时代发展，在项目建设中积极推行装配式建筑施工，以满足社会环境的可持续发展需求，为绿色城市的建设添砖加瓦。','2020-02-20 00:15:42','http://n.sinaimg.cn/spider2020219/552/w347h205/20200219/e1b8-iprtayz8141375.jpg'),(5,'学生毕业离校，直接把垃圾扔走廊，舍管大爷怒了','凉城空宛儿','http://n.sinaimg.cn/sinacn10209/198/w99h99/20191010/0ea8-ifrwayx3799221.jpg','又到一年毕业季，对于高校最高年级的孩子们来说，六月份是一个特殊的时间节点，这意味着自己完成了高校的学业，即将踏上社会拥抱新的生活。此时的学生们心里肯定五味杂陈，有几分不舍、有几分激动，还有几分惆怅。宿舍就像一个温馨的小家，陪伴了学生好几年，而宿舍的舍友们也像是自己的亲兄弟姐妹一样陪自己走过人生中最美好的那段时光。毕业的时候，我们就要将宿舍清空，将宿舍留给新一届的学弟学妹们。我们呼吁每个学生都带好自己的物品，同时清理垃圾。','2020-02-20 00:15:42','https://n.sinaimg.cn/sinacn20191216ac/171/w561h410/20191216/ed65-ikvenft3147737.png'),(6,'日处理3万吨！合肥首台医废垃圾焚烧车驰援武汉方舱医院','新安晚报','http://n.sinaimg.cn/sinacn10208/360/w180h180/20191010/ff3d-ifrwayx3647638.jpg','2月15日晚，合肥首台医废垃圾焚烧车到达武汉方舱医院，5人团队包括运营工程师、危废处置的核心技术骨干等，他们讲持续一个月协助处理医疗垃圾。2月17日该台医废垃圾焚烧车已处理约为两吨医废垃圾。','2020-02-20 00:15:42','http://n.sinaimg.cn/sinacn20123/288/w699h389/20200219/2987-ipvnszc7782074.jpg'),(7,'湖南人文科技学院陈庆平团队“逆行”湖北 决战医疗垃圾','中国新闻网','http://n.sinaimg.cn/sinacn10217/360/w180h180/20200108/a0c5-imvsvyz9170147.jpg','武汉雷神山医院医疗垃圾无害化气化裂解处理车间里，一袋袋装满口罩、防护服、唾弃物等高危医疗垃圾的垃圾袋被直接扔进焚烧炉进行气化处理。被口罩、护目镜、防护服等“全副武装”的湖南人文科技学院副教授陈庆平像无数奔走在抗疫一线的“逆行者”一样，和团队成员开始了新一天的战“疫”。','2020-02-20 00:32:26','https://n.sinaimg.cn/spider2020219/425/w700h525/20200219/1aaa-iprtayz9046774.jpg'),(8,'清洁工在捞河道垃圾，捞上了一个矿泉水瓶子','呆萌爱旅游的大胖子','http://n.sinaimg.cn/sinacn10216/224/w112h112/20191010/eff2-ifrwayx6150074.jpg','我们都知道，世界大了什么样的人都有，有些人很有素质，有些人则是没有素质，会乱丢垃圾。国外的一个清洁工在清理河道垃圾的时候，捞上来了一个矿泉水瓶子，瓶子已经是脏兮兮的了，也不知道是扔了多久。可是等清洁工抓起瓶子要放到袋子里的时候，他发现不对劲了，不说瓶子的重量，就说这形状，有点怪怪的，细看之后，清洁工忍不住泪目，因为他发现这个瓶子是跟一只乌龟绑在一起的，而乌龟已经死了，身体都发臭了，清洁工完全不明白，到底是谁这么狠心，要这样的对乌龟，尤其是绳子还绑住了乌龟的腿，让乌龟不能游动，只能等死，太残忍了。','2020-02-20 00:32:26','https://n.sinaimg.cn/sinacn20200219ac/264/w663h401/20200219/7c40-iprtayz8798167.png'),(9,'男子义务清理河道，看到捞起来的“垃圾”，男子意外的发财了','中国新闻网','http://n.sinaimg.cn/sinacn10217/360/w180h180/20200108/a0c5-imvsvyz9170147.jpg','我们都知道在生活中有些人有素质，有些人素质比较低，会乱丢垃圾。一个男子外出散步的时候，看到河道里的水很脏，而且还飘着很多垃圾，男子觉得这样的画面实在是太难看了，于是就自发的义务清理河道。一开始男子只是打捞起了飘在水面的垃圾，等男子把上面看到的垃圾都清理掉之后，就开始打捞水底下的垃圾，可是河道淤泥下的“垃圾”，有些让男子很意外，比如说男子会打捞到完好的玩具车。更让人惊喜的是，男子还会打捞到一枚银币，这枚银币看起来不是当前所流通的硬币，男子把它捡起来放到一边，还有一些瓷器，这些看着不同寻常的东西，男子都收起来了。','2020-02-20 00:38:40','https://n.sinaimg.cn/sinacn20200219ac/284/w645h439/20200219/307d-iprtayz8741324.png'),(10,'哈市居民小区生活垃圾日产日清，疫情期间你丢掉的垃圾去哪了','中国新闻网','http://n.sinaimg.cn/sinacn10217/360/w180h180/20200108/a0c5-imvsvyz9170147.jpg','2月19日10时许，在哈市道里区中东铁路公园附近的斯大林生活垃圾转运站，移动式垃圾压缩箱的马达正在轰鸣作业，从附近小区运送来的生活垃圾由小型垃圾运输车转运，被卸到移动式垃圾压缩箱中如今，哈尔滨全市各城区447辆垃圾运输车、570个垃圾转运间每天在高效运转，确保全市各个小区生活垃圾日产日清。为此，市城管局还成立了6个检查组对全市小区内生活垃圾转运进行专项督导。目前，居民小区生活垃圾做到日产日清无积存。','2020-02-20 00:38:40','https://n.sinaimg.cn/spider2020219/120/w1080h1440/20200219/5b95-iprtayz8491095.jpg'),(11,'日处理3万吨！合肥首台医废垃圾焚烧车驰援武汉方舱医院','新安晚报','http://n.sinaimg.cn/sinacn10208/360/w180h180/20191010/ff3d-ifrwayx3647638.jpg','2月15日晚，合肥首台医废垃圾焚烧车到达武汉方舱医院，5人团队包括运营工程师、危废处置的核心技术骨干等，他们讲持续一个月协助处理医疗垃圾。2月17日该台医废垃圾焚烧车已处理约为两吨医废垃圾。','2020-02-20 00:15:42','http://n.sinaimg.cn/sinacn20123/288/w699h389/20200219/2987-ipvnszc7782074.jpg'),(12,'湖南人文科技学院陈庆平团队“逆行”湖北 决战医疗垃圾','中国新闻网','http://n.sinaimg.cn/sinacn10217/360/w180h180/20200108/a0c5-imvsvyz9170147.jpg','武汉雷神山医院医疗垃圾无害化气化裂解处理车间里，一袋袋装满口罩、防护服、唾弃物等高危医疗垃圾的垃圾袋被直接扔进焚烧炉进行气化处理。被口罩、护目镜、防护服等“全副武装”的湖南人文科技学院副教授陈庆平像无数奔走在抗疫一线的“逆行者”一样，和团队成员开始了新一天的战“疫”。','2020-02-20 00:32:26','https://n.sinaimg.cn/spider2020219/425/w700h525/20200219/1aaa-iprtayz9046774.jpg'),(13,'清洁工在捞河道垃圾，捞上了一个矿泉水瓶子','呆萌爱旅游的大胖子','http://n.sinaimg.cn/sinacn10216/224/w112h112/20191010/eff2-ifrwayx6150074.jpg','我们都知道，世界大了什么样的人都有，有些人很有素质，有些人则是没有素质，会乱丢垃圾。国外的一个清洁工在清理河道垃圾的时候，捞上来了一个矿泉水瓶子，瓶子已经是脏兮兮的了，也不知道是扔了多久。可是等清洁工抓起瓶子要放到袋子里的时候，他发现不对劲了，不说瓶子的重量，就说这形状，有点怪怪的，细看之后，清洁工忍不住泪目，因为他发现这个瓶子是跟一只乌龟绑在一起的，而乌龟已经死了，身体都发臭了，清洁工完全不明白，到底是谁这么狠心，要这样的对乌龟，尤其是绳子还绑住了乌龟的腿，让乌龟不能游动，只能等死，太残忍了。','2020-02-20 00:32:26','https://n.sinaimg.cn/sinacn20200219ac/264/w663h401/20200219/7c40-iprtayz8798167.png'),(14,'男子义务清理河道，看到捞起来的“垃圾”，男子意外的发财了','中国新闻网','http://n.sinaimg.cn/sinacn10217/360/w180h180/20200108/a0c5-imvsvyz9170147.jpg','我们都知道在生活中有些人有素质，有些人素质比较低，会乱丢垃圾。一个男子外出散步的时候，看到河道里的水很脏，而且还飘着很多垃圾，男子觉得这样的画面实在是太难看了，于是就自发的义务清理河道。一开始男子只是打捞起了飘在水面的垃圾，等男子把上面看到的垃圾都清理掉之后，就开始打捞水底下的垃圾，可是河道淤泥下的“垃圾”，有些让男子很意外，比如说男子会打捞到完好的玩具车。更让人惊喜的是，男子还会打捞到一枚银币，这枚银币看起来不是当前所流通的硬币，男子把它捡起来放到一边，还有一些瓷器，这些看着不同寻常的东西，男子都收起来了。','2020-02-20 00:38:40','https://n.sinaimg.cn/sinacn20200219ac/284/w645h439/20200219/307d-iprtayz8741324.png'),(15,'哈市居民小区生活垃圾日产日清，疫情期间你丢掉的垃圾去哪了','中国新闻网','http://n.sinaimg.cn/sinacn10217/360/w180h180/20200108/a0c5-imvsvyz9170147.jpg','2月19日10时许，在哈市道里区中东铁路公园附近的斯大林生活垃圾转运站，移动式垃圾压缩箱的马达正在轰鸣作业，从附近小区运送来的生活垃圾由小型垃圾运输车转运，被卸到移动式垃圾压缩箱中如今，哈尔滨全市各城区447辆垃圾运输车、570个垃圾转运间每天在高效运转，确保全市各个小区生活垃圾日产日清。为此，市城管局还成立了6个检查组对全市小区内生活垃圾转运进行专项督导。目前，居民小区生活垃圾做到日产日清无积存。','2020-02-20 00:38:40','https://n.sinaimg.cn/spider2020219/120/w1080h1440/20200219/5b95-iprtayz8491095.jpg');
/*!40000 ALTER TABLE `t_news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_recycle`
--

DROP TABLE IF EXISTS `t_recycle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `t_recycle` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `garbage_type` int(11) NOT NULL,
  `function` mediumtext NOT NULL COMMENT '该种垃圾的回收做法',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_recycle`
--

LOCK TABLES `t_recycle` WRITE;
/*!40000 ALTER TABLE `t_recycle` DISABLE KEYS */;
INSERT INTO `t_recycle` VALUES (1,3,'可回收物投放指导\r\n\r\n1、可回收物应轻投轻放，清洁干燥、避免污染；\r\n2、废纸尽量平整；\r\n3、立体包装物请清空内容物，清洁后扁压投放；\r\n4、有尖锐边角的，应包裹后投放。\r\n'),(2,4,'有害垃圾投放指导\r\n\r\n1、有害垃圾指废电池、废灯管、废药品、废油漆及其容器等对人体健康或者自然环境造成直接或者潜在危害的生活废弃物。\r\n2、常见包括废电池、废荧光灯管、废灯泡、废水银温度计、废油漆桶、过期药品等。有害有毒垃圾需特殊正确的方法安全处理。\r\n'),(3,1,'干垃圾投放指导\r\n\r\n危害较小，但无再次利用价值，如建筑垃圾类，生活垃圾类等，一般采取填埋、焚烧、卫生分解等方法，部分还可以使用生物解决，如放蚯蚓等。是可回收垃圾、厨余垃圾、有害垃圾剩余下来的一种垃圾\r\n'),(4,2,'湿垃圾投放指导\r\n1、厨余垃圾（餐厨垃圾）应从产生时就与其他类别垃圾分开归类，去除食材食品的包装物，不得混入纸巾餐具、厨房用具等。\r\n2、餐厨垃圾中的废弃食用油脂，应单独存放、交付餐厨垃圾收集运输单位。\r\n3、难以生物降解的贝壳、大骨头、毛发等，宜作为其他垃圾投放。\r\n4、厨余垃圾（餐厨垃圾）滤去水分后再投放。\r\n');
/*!40000 ALTER TABLE `t_recycle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `nickname` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES (1,'zhouli','zl123123','LilyChow','15695522635','https://school-1256137468.cos.ap-guangzhou.myqcloud.com/news/news_picture_86701582258853710.jpeg');
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-21 12:58:30
