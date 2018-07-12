/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : activation_code_mall_db

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2018-07-12 18:30:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `account_level_tb`
-- ----------------------------
DROP TABLE IF EXISTS `account_level_tb`;
CREATE TABLE `account_level_tb` (
  `account_level_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '账户等级id',
  `name` varchar(255) DEFAULT NULL COMMENT '等级名',
  `level` tinyint(4) DEFAULT '0' COMMENT '等级,默认是0，数字越大，等级越高',
  `img_address` varchar(255) DEFAULT NULL COMMENT '等级图片',
  `seller_upgrade_integral` decimal(11,2) DEFAULT '0.00' COMMENT '商户升级积分',
  `user_upgrade_integral` decimal(11,2) DEFAULT '0.00' COMMENT '用户升级积分',
  `mark` varchar(255) DEFAULT NULL COMMENT '备注（权益）',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`account_level_id`),
  KEY `INDEX_LEVEL` (`level`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1004 DEFAULT CHARSET=utf8 COMMENT='等级表';

-- ----------------------------
-- Records of account_level_tb
-- ----------------------------
INSERT INTO `account_level_tb` VALUES ('1000', '初级', '0', 'http://p55v5f6od.bkt.clouddn.com/o_1c945i5mg1gin1n1k13k7o3p1f7l1d.png', '0.00', '0.00', '1000分以下初级商家或买家', '2018-03-21 19:38:19');
INSERT INTO `account_level_tb` VALUES ('1001', '铜牌', '1', 'http://p55v5f6od.bkt.clouddn.com/o_1c945imdee6k6n51lri1vku1ct1i.png', '1000.00', '1000.00', '1000-5000分铜牌商家或买家', '2018-03-21 19:37:20');
INSERT INTO `account_level_tb` VALUES ('1002', '银牌', '2', 'http://p55v5f6od.bkt.clouddn.com/o_1c945j7us1juqid01qvlkvhc4b1n.png', '5000.00', '5000.00', '5000-50000分银牌商家或买家', '2018-03-21 19:37:37');
INSERT INTO `account_level_tb` VALUES ('1003', '金牌', '3', 'http://p55v5f6od.bkt.clouddn.com/o_1c945jjtd1gaject11s017caq7s1s.png', '50000.00', '50000.00', '50000分以上金牌商家或买家', '2018-03-21 19:37:49');

-- ----------------------------
-- Table structure for `account_tb`
-- ----------------------------
DROP TABLE IF EXISTS `account_tb`;
CREATE TABLE `account_tb` (
  `account_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '账户id',
  `phone` varchar(255) DEFAULT NULL COMMENT '注册手机号',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `nickname` varchar(255) DEFAULT NULL COMMENT '昵称',
  `icon` varchar(255) DEFAULT NULL COMMENT '图像',
  `sex` tinyint(4) DEFAULT '0' COMMENT '性别,默认为0未知，为1男性，为2女性',
  `country` varchar(255) DEFAULT NULL COMMENT '国家,默认中国',
  `realname` varchar(255) DEFAULT NULL COMMENT '真实姓名',
  `email` varchar(255) DEFAULT NULL COMMENT 'email',
  `safety_grade` tinyint(4) DEFAULT NULL COMMENT '安全等级，1低，2中，3高',
  `auth` tinyint(4) DEFAULT NULL COMMENT '认证，0没认证，1审核中，2已认证',
  `card_secret_receive` tinyint(4) DEFAULT NULL COMMENT '卡密接受方式，0全部接收，1本账号内，2邮箱接收，3手机接收',
  `identity_cards` varchar(255) DEFAULT NULL COMMENT '身份证',
  `identity_cards_hold_img` varchar(255) DEFAULT NULL COMMENT '手持身份证上半身照',
  `identity_cards_front_img` varchar(255) DEFAULT NULL COMMENT '身份证正面',
  `identity_cards_back_img` varchar(255) DEFAULT NULL COMMENT '身份证反面',
  `birthday` datetime DEFAULT NULL COMMENT '出生年月',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `login_date` datetime DEFAULT NULL COMMENT '登陆时间',
  `status` tinyint(4) DEFAULT '0' COMMENT '状态，默认0正常，1锁定，2，异常',
  `role_id` int(11) DEFAULT NULL COMMENT '角色id外键',
  `role_name` varchar(255) DEFAULT NULL COMMENT '角色名',
  PRIMARY KEY (`account_id`),
  KEY `INDEX_AUTH` (`auth`) USING BTREE,
  KEY `INDEX_PHONE` (`phone`) USING BTREE,
  KEY `INDEX_REALNAME` (`realname`) USING BTREE,
  KEY `INDEX_CREATEDATE` (`create_date`) USING BTREE,
  KEY `INDEX_LOGINDATE` (`login_date`) USING BTREE,
  KEY `INDEX_ROLEID` (`role_id`) USING BTREE,
  KEY `INDEX_STATUS` (`status`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1008 DEFAULT CHARSET=utf8 COMMENT='账户表';

-- ----------------------------
-- Records of account_tb
-- ----------------------------
INSERT INTO `account_tb` VALUES ('1000', '15111336586', '11874bb6149dd45428da628c9766b252', '聂跃', 'http://p55v5f6od.bkt.clouddn.com/o_1c9jajk0vrdt1v171fvr12ne1u5u29.png', '0', '', '', '278076303@qq.com', '1', '0', '0', '', '', '', '', null, '2018-03-19 10:29:28', '2018-07-12 17:42:21', '0', '1000', '超级管理员');
INSERT INTO `account_tb` VALUES ('1001', '15674830902', '11874bb6149dd45428da628c9766b252', 'lala', null, '1', null, null, '892331662@qq.com', '1', '0', '2', null, null, null, null, null, '2018-03-28 10:41:31', '2018-05-29 20:03:53', '0', '1003', '用户');
INSERT INTO `account_tb` VALUES ('1003', '15111336587x', '11874bb6149dd45428da628c9766b252', 'nieyue012', 'http://p55v5f6od.bkt.clouddn.com/o_1chb9nd8c1ct55rlsng161qg4a.png', '2', null, null, '278076304x@qq.com', '1', '0', '0', null, null, null, null, '1986-02-01 00:00:00', '2018-04-08 09:48:13', '2018-07-11 15:41:23', '0', '1003', '用户');
INSERT INTO `account_tb` VALUES ('1004', '15111336587', '11874bb6149dd45428da628c9766b252', 'nieyuesdfsd', 'http://p55v5f6od.bkt.clouddn.com/o_1chk92vf1163f1b1p4of1n0bhdpa.png', null, null, '梵蒂冈', '278076304@qq.com', '1', '1', '0', '430703198801150759', 'http://p55v5f6od.bkt.clouddn.com/o_1chhk5ad7ihe10ib1ifmd841tt5o.png', 'http://p55v5f6od.bkt.clouddn.com/o_1chhk5uccej39l214kr18nt1gpkt.png', 'http://p55v5f6od.bkt.clouddn.com/o_1chhk61n71aq81un7cngt9p1jqe12.jpg', null, '2018-04-08 13:37:33', '2018-07-12 11:11:26', '0', '1001', '商户');
INSERT INTO `account_tb` VALUES ('1005', '15111336590', '11874bb6149dd45428da628c9766b252', null, null, '1', '中国', null, null, '1', '0', '0', null, null, null, null, null, '2018-04-08 17:18:21', '2018-04-08 17:19:06', '0', '1004', '普通管理员');
INSERT INTO `account_tb` VALUES ('1006', '10000000000', '11874bb6149dd45428da628c9766b252', 'ddd', '', null, '', '', '', '1', '0', '0', '', '', '', '', null, '2018-06-27 09:44:33', '2018-06-27 09:44:43', '1', '1004', '普通管理员');
INSERT INTO `account_tb` VALUES ('1007', '15111336587xxx', '11874bb6149dd45428da628c9766b252', '推广户2', 'http://p55v5f6od.bkt.clouddn.com/o_1chfsit3bn891cq6a171hiq51ca.png', null, null, 'dfsdf', '278076304xxx@qq.com', '1', '1', '0', '430703198801150759', 'http://p55v5f6od.bkt.clouddn.com/o_1chi8homjiar2393t6io838sv.png', 'http://p55v5f6od.bkt.clouddn.com/o_1chi8hs2t90216fd1ugcd911nsk14.png', 'http://p55v5f6od.bkt.clouddn.com/o_1chi8hv41ro217hi166r1a9f1lna19.png', null, '2018-07-01 19:22:10', '2018-07-05 11:03:44', '0', '1002', '推广户');

-- ----------------------------
-- Table structure for `article_cate_tb`
-- ----------------------------
DROP TABLE IF EXISTS `article_cate_tb`;
CREATE TABLE `article_cate_tb` (
  `article_cate_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '文章类型id',
  `name` varchar(255) DEFAULT NULL COMMENT '文章类型名称',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`article_cate_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1002 DEFAULT CHARSET=utf8 COMMENT='文章类型表';

-- ----------------------------
-- Records of article_cate_tb
-- ----------------------------
INSERT INTO `article_cate_tb` VALUES ('1000', '科技', '2018-03-19 11:42:33');
INSERT INTO `article_cate_tb` VALUES ('1001', '财经', '2018-03-19 11:43:07');

-- ----------------------------
-- Table structure for `article_tb`
-- ----------------------------
DROP TABLE IF EXISTS `article_tb`;
CREATE TABLE `article_tb` (
  `article_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '文章id',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `subtitle` varchar(255) DEFAULT NULL COMMENT '子标题',
  `resource` varchar(255) DEFAULT NULL COMMENT '来源',
  `img_address` varchar(255) DEFAULT NULL COMMENT '封面',
  `redirect_url` varchar(255) DEFAULT NULL COMMENT '跳转url',
  `content` longtext COMMENT '内容',
  `reading_number` bigint(20) DEFAULT '0' COMMENT '阅读数',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态,下架0，上架1',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `article_cate_id` int(11) DEFAULT NULL COMMENT '文章类型id外键',
  PRIMARY KEY (`article_id`),
  KEY `INDEX_ARTICLECATEID` (`article_cate_id`) USING BTREE,
  KEY `INDEX_READINGNUMBER` (`reading_number`) USING BTREE,
  KEY `INDEX_CREATEDATE` (`create_date`) USING BTREE,
  KEY `INDEX_UPDATEDATE` (`update_date`) USING BTREE,
  KEY `INDEX_STATUS` (`status`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1003 DEFAULT CHARSET=utf8 COMMENT='文章表';

-- ----------------------------
-- Records of article_tb
-- ----------------------------
INSERT INTO `article_tb` VALUES ('1000', '苹果正秘密研制屏幕生产，企图摆脱三星垄断', '【Technews科技新报】根据《彭博社》引用知情人士的消息指出，美国科技大厂苹果正在自行设计，并生产设备所需的屏幕，目前已在美国加州总部的秘密工厂内开始进行测试。', '苹果/三星', 'http://5b0988e595225.cdn.sohucs.com/c_fill,w_150,h_100,g_faces,q_70/images/20180318/4483c4a801f349a3a884199fe62c4d64.jpeg', null, '【Technews科技新报】根据《彭博社》引用知情人士的消息指出，美国科技大厂苹果正在自行设计，并生产设备所需的屏幕，目前已在美国加州总部的秘密工厂内开始进行测试。\r\n\r\n报导指出，该知情人士表示，苹果正在着重投资开发下一代 MicroLED 屏幕的技术。由于 MicroLED 使用的发光架构不同于当前的 OLED 屏幕，这将使得这种屏幕的应用将会使设备更薄、更清晰，而且更节能。\r\n\r\n报导进一步指出，因为 Micro LED 屏幕的生产难度远高于目前使用的 OLED 屏幕，过去苹果曾经一度要放弃该项项目，但是目前进度上已经有所进展，不过要距离商用阶段还需几年时间。\r\n\r\n而对于苹果自行研发屏幕，长远来看将有助于苹果摆脱主要供应商──韩国大厂三星方面的技术垄断。不过，此举也会伤及部分屏幕生产厂商，如日本的 Japan Display、夏普以及韩国的 LG 等，同时，人机界面解决方案提供商 Synaptics 等公司也会受到波及。\r\n\r\n而受到此消息的冲击，三星 19 日股价，开盘后一度下跌最高 0.78%，来到每股 252.7 万韩元。而 LG 则是最多下跌超过 1.1%，每股来到 40.85 万韩元。而 Japan Display 则是开盘后最高下跌 2.42%，来到每股 199 日元。夏普同样最高下跌超过 2.4%，股价来到 3,405 日元。', '4', '1', '2018-03-19 11:45:09', '2018-03-19 11:45:11', '1000');
INSERT INTO `article_tb` VALUES ('1001', '新型锂硅电池来了 手机电量将增加30%以上', '北京时间3月19日上午消息，Sila Technologies和Angstron Materials开发出一种新的锂硅电池技术，在未来短短几年内，它可以让手机、汽车、智能手表电池的电量增加30%甚至更多，这种电池很快就可以为生产做好准备了。', '手机/技术', 'http://5b0988e595225.cdn.sohucs.com/c_fill,w_150,h_100,g_faces,q_70/images/20180318/fd1867d3aa354018bc3f7ec8cf257ade.jpeg', null, '北京时间3月19日上午消息，Sila Technologies和Angstron Materials开发出一种新的锂硅电池技术，在未来短短几年内，它可以让手机、汽车、智能手表电池的电量增加30%甚至更多，这种电池很快就可以为生产做好准备了。\r\n\r\n看看最近出现所谓“突破性电池技术”，往往并没有那么大的突破，不过《华尔街日报》科技记者克里斯托弗·米姆斯(Christopher Mims)相信，这种锂硅电池是真正的突破。宝马、英特尔、高通也是这样认为的，正因如此，它们全都为新电池的开发工作提供支持。\r\n\r\n新技术的核心在于“阳极制造”，主要用硅来制造，阳极是电池的主要组成部分。目前的电池阳极是用石墨制造的，新阳极用硅制造，可以存储更多电量，但在真实世界中，硅阳极往往比较脆弱，或者寿命很短，难以推广使用。\r\n\r\nSila Technologies开发出一种原型产品，成功解决这些问题，它将硅与石墨纳米粒子融合，让产品变得更耐用。Sila Technologies还说，如果使用它们开发的新技术，电池存储的电量会比今天的锂离子电池高20-40%。\r\n\r\n一些创业公司都想开发出最好的锂硅电池，比如Enovix，它得到了英特尔与高通的支持，Enovix宣称如果使用它的技术，可以让智能手机电池的电量增加最多50%。', '8', '1', '2018-03-19 11:46:40', '2018-03-19 11:46:42', '1000');
INSERT INTO `article_tb` VALUES ('1002', '易纲获任央行行长 9天前曾表态“监管水平和开放程度相适应”', '谁将接替周小川？谜底很快揭晓。', '央行/人民币/货币政策', 'http://5b0988e595225.cdn.sohucs.com/c_fill,w_150,h_100,g_faces,q_70/images/20180319/1c6fd0eda74a4a2abcd8376936d00fa0.jpeg', '', ' <p data-role=\"original-title\" style=\"display:none\">原标题：易纲获任央行行长 9天前曾表态“监管水平和开放程度相适应”</p>\r\n            <p>　　谁将接替周小川？谜底很快揭晓。 </p> \r\n<p>3月19日上午，在十三届全国人大一次会议第七次全体会议上，国务院总理李克强提名易纲为中国人民银行行长。 </p> \r\n<table> \r\n <tbody> \r\n  <tr> \r\n   <td><p align=\"center\"><img src=\"//5b0988e595225.cdn.sohucs.com/images/20180319/262a6ac1028443a4803ab7006e2c0b1e.jpeg\"></p></td> \r\n  </tr> \r\n </tbody> \r\n</table> \r\n<p>60岁的易纲，1997年加入央行，2007年升任央行副行长，2016年兼任人民银行党委副书记。与周小川一样，他也是一位技术派学者型官员，同时兼具国际视野，并且是坚定的改革派。 </p> \r\n<p>青年时代留美学习、任教，易纲身上至今保留着学者风范。观点碰撞遇到焦点时总是免不了唇枪舌剑，而他始终温和表达、从容应对，引用数据、联系实操，往往一语中的，让对方心服口服。 </p> \r\n<p>熟悉易纲的记者都知道他每次只回答“三个问题”的习惯，无论记者如何追问，他总是从容应答，精准扼要，他有他的原则。 </p> \r\n<p>稳步推动汇率市场化改革、审慎推进外汇储备投资多元化等等，易纲的改革思想不仅局限在书本上，他更是一位实干家。 </p> \r\n<p><strong>从学者到官员</strong></p> \r\n<p>生于北京，当过知青，易纲的命运转折开始于高考。 </p> \r\n<p>他是1977年恢复高考后的第一批大学生。“这年我19岁。邓小平先生果断恢复的高考，改变了我的一生。”易纲在纪念恢复高考40周年的一篇文章中写道。 </p> \r\n<p>1978年至1980年，他在北京大学经济系学习，1980年大学三年级时前往美国，分别在美国哈姆林大学工商管理专业、伊利诺大学经济学专业学习，1986年获经济学博士学位。易纲一直研究中国经济问题，博士毕业后，他希望学成回国，当时中国改革开放风头正劲，是施展拳脚的好时候。 </p> \r\n<p>但当时的北大校长张龙翔希望易纲能够先在美国任教，只有那样才能了解美国的大学和教育。于是易纲申请了印第安纳大学的助理教授职位，并很快获得了美国的终身教职。 </p> \r\n<p>上世纪80年代中期，易纲结识了一群同样研究中国经济问题的留学生，其中包括现任证监会副主席方星海，以及林毅夫、钱颖一、杨小凯、许小年等中国最优秀的经济学家。这段经历对他日后影响深远。 </p> \r\n<p>1985年，中国留美经济学会在纽约成立，7年后，易纲担任会长，并开始定期在中国举行研讨会，聚焦中国经济的市场化改革。 </p> \r\n<p>1994年，易纲果断放弃了在美国安逸而稳定的生活，回到祖国，和林毅夫、张维迎等共同创办了北京大学中国经济研究中心，也就是后来的北大国家发展研究院，聚集众多专注中国经济问题的顶尖学者。 </p> \r\n<p>易纲日后在人民银行主管的货币政策和国际业务，正是他当时所研究和教授的领域。 </p> \r\n<p>1990年，易纲发布的第一篇独立署名文章就是研究中国1953年至1988年通货膨胀和价格波动的关系。此后十多年，他持续研究这一问题，到2003年，出版了《中国货币化进程》。 </p> \r\n<p>即使进入人民银行工作后，他依然笔根不辍，截至目前，易纲已在《计量经济学杂志》、《中国经济评论》、《比较经济学研究》等经济杂志上发表中文论文40余篇，英文学术论文20余篇，著有10本书，其英文专著《中国的货币，银行和金融市场》，被世界银行和国际货币基金组织（IMF）等国际组织多次引用。 </p> \r\n<p>1997年，易纲加入中国人民银行，开始将自己的理论投入实际的政策研究和制定工作。他历任货币政策委员会副秘书长、秘书长兼货币政策司副司长，货币政策司司长，行长助理，外汇管理局局长等职，从2007年就开始担任央行副行长一职。 </p> \r\n<p>2014年4月，易纲赴制定中国经济政策最高级别的议事机构中央财经领导小组办公室，担任副主任。2016年初，他卸任外管局局长，升任人民银行党委副书记。当时有观察者预测，易纲这次新头衔“加持”，可能预示着未来将承担更重大的使命。 </p> \r\n<p><strong>温文尔雅的原则先生</strong></p> \r\n<p>熟悉他的记者都知道易纲每次只回答“三个问题”的习惯。无论记者如何追问，他总是从容应答，精准扼要，但他有他的原则。 </p> \r\n<p>去年“两会”上，第一财经记者追采到当时还是政协经济组委员的易纲。2017年3月4日一早7点多，易纲独自一人前往酒店，正通过安检时被记者迎面碰到。尽管采访来的有些突然，一连接到三个关于宏观货币政策的问题，易纲并没有回避，而是准确、精炼地回答了记者的提问。 </p> \r\n<p>对于中国是否会跟随美联储的脚步加息，易纲说，这还得考虑国内需求为主，具体要看经济、物价等方面。“我觉得还得再看一看。”至于降准，他说，应当综合研究，尽管当下我国外汇占款持续减少，但流动性还是正常、稳定的。 </p> \r\n<p>解释“稳健中性”货币政策时，易纲的回答简短有力：“观察，央行肯定要做到不松不紧。” </p> \r\n<p>记者还要继续追问，他释放了一贯的原则，“今天就到这里吧。”因为宏观经济政策会影响市场，即使央行官员表态也会引起市场反响，所以，央行官员面对媒体随时要扮演新闻发言人的角色。 </p> \r\n<p>而在学术讨论时，易纲又总是展现他学者的风范。讨论遇到焦点问题时总是免不了唇枪舌剑，而易纲始终温和表达、从容应对，引用数据、联系实操，往往一语中的，让对方心服口服。 </p> \r\n<p>2015年3月，中国发展高层论坛上演了一场关于人民币资本项目可兑换的激辩。中国社科院学部委员余永定观点鲜明，担忧我国放缓了推进人民币资本项目可兑换的进度。因为政府工作报告把“加快推进人民币资本项目可兑换”，改成了“稳步实现人民币资本项目可兑换”。从“加快”到“稳步”，一词之差，政策基调大变。 </p> \r\n<p>本来不是发言嘉宾、全程在观众席上聆听的易纲在所有嘉宾发言结束后，在台下发言，指出“加快”是“推进”，而“稳步”是“实现”，回应了对改革放缓的质疑，寥寥数言扭转了整场讨论的结论，起到了“四两拨千斤”的作用。 </p> \r\n<p>留美多年、并成为同批留学生中最早获得美国大学终身教职的易纲，在国际舞台上也游刃有余，其智者风范为境外媒体所熟悉。 </p> \r\n<p>2016年4月，易纲与前任美联储主席伯南克在美国顶级智库布鲁金斯学会的一场论坛上也有过一次激烈的交锋。当时，市场刚刚经历了2015年8月11日新汇改带来的波动，中国央行面临着“保汇率”还是“保储备”的监管抉择，加强资本管制的呼声不绝如缕。 </p> \r\n<table> \r\n <tbody> \r\n  <tr> \r\n   <td><p align=\"center\"><img src=\"//5b0988e595225.cdn.sohucs.com/images/20180319/1c6fd0eda74a4a2abcd8376936d00fa0.jpeg\"></p></td> \r\n  </tr> \r\n </tbody> \r\n</table> \r\n<p>伯南克等国际顶尖的经济学家认为中国难以突破“三元悖论”（货币政策独立性、稳定的汇率和资金自由流动不能同时实现）的限制，陷入了资本管制和人民币大幅贬值的两难选择。 </p> \r\n<p>易纲非但没有回避这一质疑，反而把话题拉回到“三元悖论”上，针锋相对地提出，“三元悖论”在实际上可以灵活运用，中国不必必须放弃其中“一元”，央行可以在“三元”的三个目标上各取一部分，实现最优结合。 </p> \r\n<p>他解释说，中国作为一个大国需要有一定独立的货币政策来决定利率水平。中国资本账户也基本上是自由流动的，只是在非正常情况下采取一定管理。而人民币汇率在近年来也不再是固定汇率。 </p> \r\n<p>获得成绩时，易纲依然保持着宠辱不惊。2015年11月30日，IMF总裁拉加德宣布将人民币纳入特别提款权（SDR）篮子，人民币终于拥有国际储备货币地位，权重仅次于美元和欧元。 </p> \r\n<p>次日，时任央行副行长、外汇管理局局长的易纲说，加入SDR标志着国际社会对中国经济发展和改革开放的肯定，心情是喜悦、平静。 </p> \r\n<p><strong>坚定推进汇率市场化</strong></p> \r\n<p>和前任周小川一样，易纲也是一个坚定的改革派。 </p> \r\n<p>易纲任职央行副行长以来，人民币汇率市场化经历了两次重要的变革。中国汇改从1994年结束“双轨制”开始，到2005年参考一篮子货币，进展比较缓慢。与西方当时主流的改革路径不同，易纲始终强调，汇率改革是一个渐进的过程。在他看来，汇率改革的最终目标，就是使人民币成为可兑换货币。而目前，中国社会主义市场经济体制最好的选择是实行“以市场供求为基础、参考一篮子货币进行调节、有管理的浮动汇率制度。” </p> \r\n<p>2009年7月，易纲接任国家外汇管理局局长时正是国际金融危机刚刚爆发之际，美国将世界经济复苏不平衡的原因归咎中国，并再次将矛头指向人民币被低估问题。但如果人民币升值将进一步打击已经受危机重创的出口，还会放任外汇投机活动。而与此同时，各国纷纷出手干预外汇市场，全球“货币战争”一触即发。 </p> \r\n<p>在这样的内外部环境下，2010年6月人民币汇率改革重新启动。中国结束了自2008年下半年以来人民币汇率盯住美元的做法，一定程度上增强了人民币的弹性，即实现“双向波动”，人民币的价格逐步由市场需求来决定。 </p> \r\n<p>2010年10月8日，易纲在华盛顿参加IMF和世界银行年会前夕的一场研讨会上表示，在本次国际金融危机最严重的时期，许多国家货币兑美元都大幅贬值，而人民币汇率保持了稳定，为抵御国际金融危机发挥了重要作用，中国将保持人民币汇率在合理均衡水平上的基本稳定。 </p> \r\n<p>2015年8月11日，汇率迎来第二次重大改革，确立了以“收盘汇率+一篮子货币汇率变化”人民币汇率中间价的市场化形成机制，并且人民币一次性贬值3%左右。当时中国正在为人民币加入SDR篮子做最后的冲刺，IMF评估的一项重点是人民币是否为“自由使用货币”，因此人民币汇率形成机制的市场化程度是人民币能否最终入篮的关键。 </p> \r\n<p>但是市场主体“习惯了过去非常稳定的汇率”，这次调整引起了波动，一直到2017年初，人民币贬值预期一直存在。 </p> \r\n<p>易纲在2015年8月13日的吹风会上表示，一个僵化的、固定的汇率是不适合中国国情的，也是不可持续的。汇改对人民币国际化的影响主要是正面的。易纲在做政策解读时阐述了他心中改革的方向，“要相信市场，要尊重市场，甚至要敬畏市场，要顺应市场。” </p> \r\n<p>在他的眼中，改革是一门艺术，是高度专业化精细化的艺术。他指出，加快外汇市场的发展包含丰富外汇的产品，推动外汇市场的对外开放。这里面也包括延长交易时间，引入合格的境外主体，从而促进形成境内外一致的人民币汇率。这些表述背后的核心是稳步有序的市场化。 </p> \r\n<p>在3月10日的今年“两会”记者会上，有境外媒体质疑，从2016年下半年开始，中国加大对资本外流的管制，导致人民币国际化进程有所放缓。对此，易纲做了通俗、精彩的回应。 </p> \r\n<p>资本可兑换是在稳步的推进，在资本项目下有两个最重要的项目，一方面是直接投资，包括两个方向，一个方向是FDI（外商直接投资），一个方向是ODI（对外直接投资）。“直接投资，我觉得真实贸易投资背景下都是很方便的。另外一个大的项，比如说组合投资，就是金融市场的开放，我们国内股市、债市的开放和中国的居民将来可以在更大范围内配置资产，配置它的组合投资。不管是直接投资，还是组合投资，在这两个方面都会进一步的稳步推进资本项目的可兑换。这里有一些‘放管服’的改革，有一些便利化的改革，还有一些数据透明度，还有一些比如说反洗钱、反恐融资的要求，这些都会稳步的推进。” </p> \r\n<p>易纲同时强调，“我们国内市场现在也在变大，不管是股市还是债市还是其他的市场，将来也都要做双向的开放。” </p> \r\n<p>“在做开放的同时，非常重要的一条就是要把控好风险，使我们的监管水平和开放的程度相适应，这样就能够在开放中防范好风险，使得中国的居民和全世界的投资者在中国市场上更加的便利，配置资源的效率更高。”他在记者会上表示。 </p> \r\n<p><strong>创造性推动外储投资多元化</strong></p> \r\n<p>易纲在央行的一项重要改革是在外管局局长任上，推进外汇储备投资的多元化。2009年他接任外管局局长时，中国外汇储备达到两万亿美元，随后数年继续大幅增长，直至2014年上半年，外储达到历史峰值，逼近4万亿美元，成为世界上最大规模的官方外汇储备。 </p> \r\n<p>这既是一份厚实的家底，也是一份甜蜜的负担。如何替国家理好财，是外管局局长的重担。而过去，中国外储因大量投向美元资产，受到市场的质疑，尤其是2010年全球金融危机过后，美元贬值，外界传言称，这导致了中国外汇储备“缩水”。 </p> \r\n<p>2010年，易纲接任外管局局长不久，中国外汇储备多元化应当侧重资产投资。他不止一次公开强调，中国外汇储备配置没有把“鸡蛋都放在一个篮子里”。 </p> \r\n<p>据媒体报道，当时为了解决外汇局人才问题，易纲力邀朱长虹辞去全球最大债券基金、“债券之王”格罗斯领导的太平洋投资管理公司的职务，加盟外汇局。朱长虹从系统、模型、技术、人员等方面，引入了诸多市场化的手法，让中国的外储投资更上一个台阶。 </p> \r\n<p>2011年3月举行的一次央行新闻发布会上，易纲回应此问题时称，中国的外汇储备方针，多年以来一直是推进外汇储备投资的多元化。 </p> \r\n<p>一方面在币种上是多元化的，“是一篮子货币，主要的可兑换货币、储备货币、新兴市场的货币，中国都有”，另一方面，在资产上，也是推进多元化的，“有各种各样的资产”。 </p> \r\n<p>易纲同样强调，多元化投资是审慎推进的。“只要资产符合安全性、流动性、收益性的要求，都会予以考虑，然后进入一个严格的在防范风险前提下的投资程序。” </p> \r\n<p>2014年全国两会期间，易纲表示，中国外汇储备投资在全球同行中居前列，并且远高于所投资国CPI。对于中国的外汇储备有过度挂钩美元资产之嫌，易纲解释称，“目前3万多亿美元的外汇储备，只投资了1万多亿美元的美国国债，投资标的实际上是多元化的。” </p> \r\n<p>在2015年8月的外管局分局长高级研修班上，易纲专门强调，下一阶段要继续加强外汇储备经营管理，确保安全、流动和保值增值。 </p> \r\n<p>截至2018年2月末，中国外汇储备余额3.13万亿美元。美国财政部3月15日公布的数据显示，今年1月中国减持167亿美元美国国债，持有规模降至6个月新低，但仍为美国第一大债权国。 </p> \r\n<p><strong>新起点</strong></p> \r\n<p>接下来，60岁的易纲将开始他在央行第21年的工作，肩上的担子可谓厚重。 </p> \r\n<p>今年1月29日，他在《中国金融》撰文指出2018年货币政策面临的挑战时称，预计2018年中国经济仍有望保持平稳增长。但也要看到，经济中仍存在一些问题和隐患，内生增长动力仍待强化，结构调整任重道远，债务和杠杆水平还处在高位，资产泡沫“堰塞湖”的警报尚不能完全解除，金融乱象仍然存在，金融监管构架还有待进一步完善。 </p> \r\n<p>他将如何带领中国央行保持货币政策稳健中性，管住货币供给闸门，同时守住不发生系统性金融风险，继续稳妥推进各项金融改革，外界将拭目以待。 </p> \r\n<p>一如去年底他在中国金融四十人论坛上评价人民币加入SDR时所言，“这是一个里程碑，同时也是一个新起点”。<a href=\"//www.sohu.com/?strategyid=00001 \" target=\"_blank\" title=\"点击进入搜狐首页\" id=\"backsohucom\" style=\"white-space: nowrap;\"><span class=\"backword\"><i class=\"backsohu\"></i>返回搜狐，查看更多</span></a></p>      <p data-role=\"editor-name\">责任编辑：<span></span></p>', '14', '1', '2018-03-19 11:48:03', '2018-03-19 11:48:05', '1001');

-- ----------------------------
-- Table structure for `bank_card_tb`
-- ----------------------------
DROP TABLE IF EXISTS `bank_card_tb`;
CREATE TABLE `bank_card_tb` (
  `bank_card_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '银行卡id',
  `realname` varchar(255) DEFAULT NULL COMMENT '姓名',
  `identity` varchar(255) DEFAULT NULL COMMENT '身份证',
  `bank_name` varchar(255) DEFAULT NULL COMMENT '银行名',
  `number` varchar(255) DEFAULT NULL COMMENT '银行卡卡号',
  `phone` varchar(255) DEFAULT NULL COMMENT '预留手机号',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `account_id` int(11) DEFAULT NULL COMMENT '账户id',
  PRIMARY KEY (`bank_card_id`),
  KEY `INDEX_ACCOUNTID` (`account_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='银行卡表';

-- ----------------------------
-- Records of bank_card_tb
-- ----------------------------
INSERT INTO `bank_card_tb` VALUES ('2', '聂跃', '430703198801150759', '中国建设银行', '6217857500017514991', '15111336587', '2018-03-22 14:47:12', '2018-03-22 14:47:12', '1000');
INSERT INTO `bank_card_tb` VALUES ('3', '聂跃', '430703198801150759', '中国建设银行', '6217857500017514990', '15111336587', '2018-03-22 14:56:57', '2018-05-29 09:32:27', '1000');
INSERT INTO `bank_card_tb` VALUES ('7', '聂跃', '430703198801150759', '123df', '1155485695848444445556', '15111336587', '2018-07-03 17:59:19', '2018-07-03 17:59:19', '1004');

-- ----------------------------
-- Table structure for `banner_tb`
-- ----------------------------
DROP TABLE IF EXISTS `banner_tb`;
CREATE TABLE `banner_tb` (
  `banner_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'bannerid',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `type` tinyint(4) DEFAULT '0' COMMENT '类型，默认1首页轮播',
  `img_address` varchar(255) DEFAULT NULL COMMENT '图片',
  `content` varchar(255) DEFAULT NULL COMMENT '内容',
  `link` varchar(255) DEFAULT NULL COMMENT '链接',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态，默认0下架，1上架',
  PRIMARY KEY (`banner_id`),
  KEY `INDEX_TYPE` (`type`) USING BTREE,
  KEY `INDEX_STATUS` (`status`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1002 DEFAULT CHARSET=utf8 COMMENT='banner表';

-- ----------------------------
-- Records of banner_tb
-- ----------------------------
INSERT INTO `banner_tb` VALUES ('1000', '堕落之王', '1', 'http://img.fhyx.com/uploads/2018/05/21/2018052131146975.jpg', '仅售9rmb', 'http://ali213.fhyx.hk/item/1426.html#attrone=682', '2018-05-29 14:55:58', '1');
INSERT INTO `banner_tb` VALUES ('1001', '绝地求生', '1', 'http://img.fhyx.com/uploads/2018/05/28/20180528112359383.jpg', '八连冠', 'http://ali213.fhyx.hk/item/2254.html#attrone=294', '2018-05-29 14:54:46', '1');

-- ----------------------------
-- Table structure for `cart_mer_tb`
-- ----------------------------
DROP TABLE IF EXISTS `cart_mer_tb`;
CREATE TABLE `cart_mer_tb` (
  `cart_mer_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '购物车商品id',
  `number` int(11) DEFAULT NULL COMMENT '数量',
  `total_price` decimal(11,2) DEFAULT NULL COMMENT '总价',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `spread_account_id` int(11) DEFAULT NULL COMMENT '推广账户id',
  `mer_id` int(11) DEFAULT NULL COMMENT '商品id外键',
  `account_id` int(11) DEFAULT NULL COMMENT '账户id外键',
  PRIMARY KEY (`cart_mer_id`),
  KEY `INDEX_NUMBER` (`number`) USING BTREE,
  KEY `INDEX_MERID` (`mer_id`) USING BTREE,
  KEY `INDEX_ACCOUNTID` (`account_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='购物车商品表';

-- ----------------------------
-- Records of cart_mer_tb
-- ----------------------------
INSERT INTO `cart_mer_tb` VALUES ('3', '1', '17.90', '2018-04-07 11:08:27', '2018-04-07 11:08:27', null, '1', '1001');
INSERT INTO `cart_mer_tb` VALUES ('12', '3', '237.69', '2018-06-26 19:02:13', '2018-06-27 16:48:12', null, '6', '1003');
INSERT INTO `cart_mer_tb` VALUES ('13', '1', '259.00', '2018-06-26 19:04:23', '2018-06-26 19:04:23', null, '2', '1003');
INSERT INTO `cart_mer_tb` VALUES ('14', '2', '196.00', '2018-06-28 11:32:34', '2018-06-28 11:32:36', null, '3', '1003');

-- ----------------------------
-- Table structure for `config_tb`
-- ----------------------------
DROP TABLE IF EXISTS `config_tb`;
CREATE TABLE `config_tb` (
  `config_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '配置id',
  `customer_service_phone` varchar(255) DEFAULT NULL COMMENT '客服电话',
  `order_mer_max_number` int(11) DEFAULT '5' COMMENT '最大订单商品数量',
  `seller_integral_per` decimal(11,2) DEFAULT '1.00' COMMENT '商户每盈利一元钱获得积分',
  `user_integral_per` decimal(11,2) DEFAULT '1.00' COMMENT '用户每消费一元钱获得积分',
  `seller_sincerity_upgrade_money` decimal(11,2) DEFAULT '0.00' COMMENT '商户诚信升级金额',
  `freeze_day_number` int(11) DEFAULT '0' COMMENT '冻结天数',
  `platform_proportion` decimal(11,2) DEFAULT '5.00' COMMENT '平台分成比例，单位%',
  `spread_proportion` decimal(11,2) DEFAULT '1.00' COMMENT '推广分成比例，单位%',
  `min_withdrawals` decimal(11,2) DEFAULT '500.00' COMMENT '提现最低额度',
  `withdrawals_proportion` decimal(11,2) DEFAULT '3.00' COMMENT '提现手续费比例，单位%',
  `withdrawals_min_brokerage` decimal(11,2) DEFAULT '2000.00' COMMENT '无提现手续费最低额度',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`config_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='配置表';

-- ----------------------------
-- Records of config_tb
-- ----------------------------
INSERT INTO `config_tb` VALUES ('1', '400002152', '5', '1.00', '1.00', '10000.00', '7', '5.00', '1.00', '2000.00', '3.00', '10000.00', '2018-03-21 19:04:55', '2018-03-21 19:06:30');

-- ----------------------------
-- Table structure for `coupon_tb`
-- ----------------------------
DROP TABLE IF EXISTS `coupon_tb`;
CREATE TABLE `coupon_tb` (
  `coupon_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '优惠劵id',
  `code` varchar(255) DEFAULT NULL COMMENT '优惠劵码',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `img_address` varchar(255) DEFAULT NULL COMMENT '图片',
  `discount` decimal(11,2) DEFAULT NULL COMMENT '折扣',
  `content` varchar(255) DEFAULT NULL COMMENT '内容',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `start_date` datetime DEFAULT NULL COMMENT '开始时间',
  `end_date` datetime DEFAULT NULL COMMENT '结束时间',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态，默认1可用，2已用，3已失效',
  `mer_cate_id` int(11) DEFAULT NULL COMMENT '商品类型id,此商品类型才能使用',
  `account_id` int(11) DEFAULT NULL COMMENT '优惠劵人ID，此id账户才能使用',
  `coupon_term_id` int(11) DEFAULT NULL COMMENT '优惠劵项ID',
  `order_id` int(11) DEFAULT NULL COMMENT '订单id',
  PRIMARY KEY (`coupon_id`),
  KEY `INDEX_CODE` (`code`) USING BTREE,
  KEY `INDEX_MERCATEID` (`mer_cate_id`) USING BTREE,
  KEY `INDEX_COUPONTERMID` (`coupon_term_id`) USING BTREE,
  KEY `INDEX_ACCOUNTID` (`account_id`) USING BTREE,
  KEY `INDEX_STARTDATE` (`start_date`) USING BTREE,
  KEY `INDEX_ENDDATE` (`end_date`) USING BTREE,
  KEY `INDEX_CREATEDATE` (`create_date`) USING BTREE,
  KEY `INDEX_UPDATEDATE` (`update_date`) USING BTREE,
  KEY `INDEX_ORDERID` (`order_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='优惠劵表';

-- ----------------------------
-- Records of coupon_tb
-- ----------------------------

-- ----------------------------
-- Table structure for `coupon_term_tb`
-- ----------------------------
DROP TABLE IF EXISTS `coupon_term_tb`;
CREATE TABLE `coupon_term_tb` (
  `coupon_term_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '优惠劵项id',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `img_address` varchar(255) DEFAULT NULL COMMENT '图片',
  `discount` decimal(11,2) DEFAULT NULL COMMENT '折扣',
  `content` varchar(255) DEFAULT NULL COMMENT '内容',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `mer_cate_id` int(11) DEFAULT NULL COMMENT '商品类型id,外键',
  PRIMARY KEY (`coupon_term_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='优惠劵项表';

-- ----------------------------
-- Records of coupon_term_tb
-- ----------------------------
INSERT INTO `coupon_term_tb` VALUES ('1', '撒的发是', 'http://p2bhwwngu.bkt.clouddn.com/o_1c5gaqqst68db2j16nno61q3cp.jpg', '1.00', '三大范德萨33', '2018-04-11 19:06:01', null);
INSERT INTO `coupon_term_tb` VALUES ('2', '福德宫', 'http://p2bhwwngu.bkt.clouddn.com/o_1c5gaqqst68db2j16nno61q3cp.jpg', '0.70', '的发货的发货22', '2018-04-11 19:05:55', '1003');

-- ----------------------------
-- Table structure for `finance_record_tb`
-- ----------------------------
DROP TABLE IF EXISTS `finance_record_tb`;
CREATE TABLE `finance_record_tb` (
  `finance_record_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '财务记录id',
  `method` tinyint(4) DEFAULT NULL COMMENT '方式，1支付宝，2微信,3百度钱包,4Paypal,5网银',
  `type` tinyint(4) DEFAULT NULL COMMENT '类型，1购买商品，2账户提现，3退款，4诚信押金',
  `transaction_number` varchar(255) DEFAULT NULL COMMENT '交易单号',
  `brokerage` decimal(11,2) DEFAULT '0.00' COMMENT '手续费',
  `money` decimal(11,2) DEFAULT '0.00' COMMENT '金额',
  `real_money` decimal(11,2) DEFAULT '0.00' COMMENT '实际金额',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态，默认1待处理，2成功，3已拒绝',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `account_id` int(11) DEFAULT NULL COMMENT '账户id,外键',
  `realname` varchar(255) DEFAULT NULL COMMENT '真实姓名',
  PRIMARY KEY (`finance_record_id`),
  KEY `INDEX_ACCOUNTID` (`account_id`) USING BTREE,
  KEY `INDEX_STATUS` (`status`) USING BTREE,
  KEY `INDEX_METHOD` (`method`) USING BTREE,
  KEY `INDEX_TYPE` (`type`) USING BTREE,
  KEY `INDEX_TRANSACTIONNUMBER` (`transaction_number`) USING BTREE,
  KEY `INDEX_CREATEDATE` (`create_date`) USING BTREE,
  KEY `INDEX_UPDATEDATE` (`update_date`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='财务记录表 ';

-- ----------------------------
-- Records of finance_record_tb
-- ----------------------------
INSERT INTO `finance_record_tb` VALUES ('1', '1', '1', '462635495898218496', '0.00', '17.90', '17.90', '2', '2018-06-30 15:08:18', '2018-06-30 15:08:18', '1003', 'nieyue0123');
INSERT INTO `finance_record_tb` VALUES ('2', '1', '1', '462682100982611968', '0.00', '17.90', '17.90', '2', '2018-06-30 18:13:29', '2018-06-30 18:13:29', '1003', 'nieyue0123');
INSERT INTO `finance_record_tb` VALUES ('3', '1', '1', '462682952799617024', '0.00', '5.80', '5.80', '2', '2018-06-30 18:16:52', '2018-06-30 18:16:52', '1003', 'nieyue0123');
INSERT INTO `finance_record_tb` VALUES ('4', '1', '1', '462713875922419712', '0.00', '238.40', '238.40', '2', '2018-06-30 20:19:45', '2018-06-30 20:19:45', '1003', 'nieyue0123');
INSERT INTO `finance_record_tb` VALUES ('5', '1', '1', '462714767019081728', '0.00', '238.40', '238.40', '2', '2018-06-30 20:23:18', '2018-06-30 20:23:18', '1003', 'nieyue0123');
INSERT INTO `finance_record_tb` VALUES ('6', '1', '1', '462715309216759808', '0.00', '238.40', '238.40', '2', '2018-06-30 20:25:27', '2018-06-30 20:25:27', '1003', 'nieyue0123');
INSERT INTO `finance_record_tb` VALUES ('7', '1', '1', '465933223831011328', '0.00', '238.40', '226.48', '2', '2018-07-09 17:32:17', '2018-07-09 17:32:17', '1003', 'nieyue012');
INSERT INTO `finance_record_tb` VALUES ('8', '1', '5', '465933223831011328', '0.00', '238.40', '226.48', '2', '2018-07-09 17:32:17', '2018-07-09 17:32:17', '1004', 'nieyue012');
INSERT INTO `finance_record_tb` VALUES ('9', '1', '1', '465933904159703040', '0.00', '35.80', '34.01', '2', '2018-07-09 17:34:59', '2018-07-09 17:34:59', '1003', 'nieyue012');
INSERT INTO `finance_record_tb` VALUES ('10', '1', '5', '465933904159703040', '0.00', '35.80', '34.01', '2', '2018-07-09 17:34:59', '2018-07-09 17:34:59', '1004', 'nieyue012');

-- ----------------------------
-- Table structure for `finance_tb`
-- ----------------------------
DROP TABLE IF EXISTS `finance_tb`;
CREATE TABLE `finance_tb` (
  `finance_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '财务id',
  `password` varchar(255) DEFAULT NULL COMMENT '提现密码',
  `money` decimal(11,2) DEFAULT '0.00' COMMENT '余额',
  `recharge` decimal(11,2) DEFAULT '0.00' COMMENT '充值金额',
  `consume` decimal(11,2) DEFAULT '0.00' COMMENT '消费金额',
  `withdrawals` decimal(11,2) DEFAULT '0.00' COMMENT '提现金额',
  `refund` decimal(11,2) DEFAULT '0.00' COMMENT '退款金额',
  `frozen` decimal(11,2) DEFAULT '0.00' COMMENT '冻结金额',
  `recommend_commission` decimal(11,2) DEFAULT '0.00' COMMENT '推荐佣金',
  `base_profit` decimal(11,2) DEFAULT '0.00' COMMENT '赠送金钱',
  `account_id` int(11) DEFAULT NULL COMMENT '账户id外键',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`finance_id`),
  KEY `INDEX_MONEY` (`money`) USING BTREE,
  KEY `INDEX_ACCOUNTID` (`account_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1008 DEFAULT CHARSET=utf8 COMMENT='财务表';

-- ----------------------------
-- Records of finance_tb
-- ----------------------------
INSERT INTO `finance_tb` VALUES ('1000', null, '10000.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '1000', '2018-03-19 10:29:28', '2018-03-19 10:29:28');
INSERT INTO `finance_tb` VALUES ('1001', null, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '1001', '2018-03-28 10:41:31', '2018-03-28 10:41:31');
INSERT INTO `finance_tb` VALUES ('1003', null, '0.00', '0.00', '1031.00', '0.00', '0.00', '0.00', '0.00', '0.00', '1003', '2018-04-08 09:48:13', '2018-07-09 17:34:59');
INSERT INTO `finance_tb` VALUES ('1004', '11874bb6149dd45428da628c9766b252', '0.00', '0.00', '0.00', '0.00', '0.00', '34.01', '0.00', '0.00', '1004', '2018-04-08 13:37:33', '2018-07-09 17:34:59');
INSERT INTO `finance_tb` VALUES ('1005', null, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '1005', '2018-04-08 17:18:21', '2018-04-08 17:18:21');
INSERT INTO `finance_tb` VALUES ('1006', null, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '1006', '2018-06-27 09:44:33', '2018-06-27 09:44:33');
INSERT INTO `finance_tb` VALUES ('1007', '11874bb6149dd45428da628c9766b252', '10000.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '1007', '2018-07-01 19:22:10', '2018-07-04 11:42:01');

-- ----------------------------
-- Table structure for `integral_detail_tb`
-- ----------------------------
DROP TABLE IF EXISTS `integral_detail_tb`;
CREATE TABLE `integral_detail_tb` (
  `integral_detail_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '积分详细id',
  `type` tinyint(4) DEFAULT '0' COMMENT '类型,1增长积分',
  `integral` decimal(11,2) DEFAULT '0.00' COMMENT '积分',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `account_id` int(11) DEFAULT NULL COMMENT '账户id外键',
  PRIMARY KEY (`integral_detail_id`),
  KEY `INDEX_TYPE` (`type`) USING BTREE,
  KEY `INDEX_ACCOUNTID` (`account_id`) USING BTREE,
  KEY `INDEX_CREATEDATE` (`create_date`) USING BTREE,
  KEY `INDEX_UPDATEDATE` (`update_date`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='积分详细表';

-- ----------------------------
-- Records of integral_detail_tb
-- ----------------------------
INSERT INTO `integral_detail_tb` VALUES ('1', '0', '17.90', '2018-06-30 15:08:18', '2018-06-30 15:08:18', '1003');
INSERT INTO `integral_detail_tb` VALUES ('2', '0', '17.90', '2018-06-30 18:13:29', '2018-06-30 18:13:29', '1003');
INSERT INTO `integral_detail_tb` VALUES ('3', '0', '5.80', '2018-06-30 18:16:52', '2018-06-30 18:16:52', '1003');
INSERT INTO `integral_detail_tb` VALUES ('4', '0', '238.40', '2018-06-30 20:19:45', '2018-06-30 20:19:45', '1003');
INSERT INTO `integral_detail_tb` VALUES ('5', '0', '238.40', '2018-06-30 20:23:18', '2018-06-30 20:23:18', '1003');
INSERT INTO `integral_detail_tb` VALUES ('6', '0', '238.40', '2018-06-30 20:25:27', '2018-06-30 20:25:27', '1003');
INSERT INTO `integral_detail_tb` VALUES ('7', '0', '238.40', '2018-07-09 17:32:17', '2018-07-09 17:32:17', '1003');
INSERT INTO `integral_detail_tb` VALUES ('8', '0', '238.40', '2018-07-09 17:32:17', '2018-07-09 17:32:17', '1004');
INSERT INTO `integral_detail_tb` VALUES ('9', '0', '35.80', '2018-07-09 17:34:59', '2018-07-09 17:34:59', '1003');
INSERT INTO `integral_detail_tb` VALUES ('10', '0', '35.80', '2018-07-09 17:34:59', '2018-07-09 17:34:59', '1004');

-- ----------------------------
-- Table structure for `integral_tb`
-- ----------------------------
DROP TABLE IF EXISTS `integral_tb`;
CREATE TABLE `integral_tb` (
  `integral_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '积分id',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `level` int(11) DEFAULT NULL COMMENT '等级',
  `integral` decimal(11,2) DEFAULT '0.00' COMMENT '剩余积分',
  `upgrade_integral` decimal(11,2) DEFAULT '0.00' COMMENT '升级积分',
  `consume` decimal(11,2) DEFAULT '0.00' COMMENT '消费积分',
  `base_profit` decimal(11,2) DEFAULT '0.00' COMMENT '赠送积分',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `account_id` int(11) DEFAULT NULL COMMENT '账户id外键',
  PRIMARY KEY (`integral_id`),
  KEY `INDEX_ACCOUNTID` (`account_id`) USING BTREE,
  KEY `INDEX_CREATEDATE` (`create_date`) USING BTREE,
  KEY `INDEX_UPDATEDATE` (`update_date`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='积分表';

-- ----------------------------
-- Records of integral_tb
-- ----------------------------
INSERT INTO `integral_tb` VALUES ('1', '初级', '0', '0.00', '1000.00', '0.00', '0.00', '2018-03-23 11:35:07', '2018-03-23 11:35:07', '1000');
INSERT INTO `integral_tb` VALUES ('2', '初级', '0', '0.00', '1000.00', '0.00', '0.00', '2018-03-28 10:41:31', '2018-03-28 10:41:31', '1001');
INSERT INTO `integral_tb` VALUES ('3', '铜牌', '1', '1031.00', '5000.00', '0.00', '0.00', '2018-04-08 09:48:13', '2018-07-09 17:34:59', '1003');
INSERT INTO `integral_tb` VALUES ('4', '初级', '0', '274.20', '1000.00', '0.00', '0.00', '2018-04-08 13:37:33', '2018-07-09 17:34:59', '1004');
INSERT INTO `integral_tb` VALUES ('5', '初级', '0', '0.00', '1000.00', '0.00', '0.00', '2018-04-08 17:18:21', '2018-04-08 17:18:21', '1005');
INSERT INTO `integral_tb` VALUES ('6', '初级', '0', '0.00', '1000.00', '0.00', '0.00', '2018-06-27 09:44:33', '2018-06-27 09:44:33', '1006');
INSERT INTO `integral_tb` VALUES ('7', '初级', '0', '0.00', '1000.00', '0.00', '0.00', '2018-07-01 19:22:10', '2018-07-01 19:22:10', '1007');

-- ----------------------------
-- Table structure for `mer_card_cipher_tb`
-- ----------------------------
DROP TABLE IF EXISTS `mer_card_cipher_tb`;
CREATE TABLE `mer_card_cipher_tb` (
  `mer_card_cipher_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品卡密id',
  `code` varchar(255) DEFAULT NULL COMMENT '卡密码',
  `img_address` varchar(255) DEFAULT NULL COMMENT '图片地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `mer_id` int(11) DEFAULT NULL COMMENT '商品id外键',
  PRIMARY KEY (`mer_card_cipher_id`),
  KEY `INDEX_MERID` (`mer_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COMMENT='商品卡密表';

-- ----------------------------
-- Records of mer_card_cipher_tb
-- ----------------------------
INSERT INTO `mer_card_cipher_tb` VALUES ('3', 'gdgdg', 'http://p55v5f6od.bkt.clouddn.com/o_1cadf4obe1gro1k9o1hji1oln7av17.png', '2018-04-06 20:40:02', '1');
INSERT INTO `mer_card_cipher_tb` VALUES ('6', null, 'http://p55v5f6od.bkt.clouddn.com/o_1ch831t3p1btgjau1e6o1k6a1phqv.jpg', '2018-06-30 18:15:45', '5');
INSERT INTO `mer_card_cipher_tb` VALUES ('7', null, 'http://p55v5f6od.bkt.clouddn.com/o_1ch832d8rilm147c635a11ot114.jpg', '2018-06-30 18:16:01', '5');
INSERT INTO `mer_card_cipher_tb` VALUES ('8', '34gfg', null, '2018-07-06 11:57:18', '5');
INSERT INTO `mer_card_cipher_tb` VALUES ('9', 'dfgdfg', null, '2018-07-06 11:57:18', '5');
INSERT INTO `mer_card_cipher_tb` VALUES ('11', '2fdgdfg', null, '2018-07-06 14:49:06', '8');
INSERT INTO `mer_card_cipher_tb` VALUES ('12', '3gfdgfdgdfgdfg', null, '2018-07-06 14:49:06', '8');
INSERT INTO `mer_card_cipher_tb` VALUES ('13', '4gfhfghfghgf', null, '2018-07-06 14:49:06', '8');
INSERT INTO `mer_card_cipher_tb` VALUES ('14', '6hgfhfgh', null, '2018-07-06 14:49:06', '8');
INSERT INTO `mer_card_cipher_tb` VALUES ('16', 'ertert', null, '2018-07-06 19:18:57', '9');
INSERT INTO `mer_card_cipher_tb` VALUES ('17', 'ertertre', null, '2018-07-09 16:16:08', '9');
INSERT INTO `mer_card_cipher_tb` VALUES ('18', 'yhtrth', null, '2018-07-09 16:16:08', '9');
INSERT INTO `mer_card_cipher_tb` VALUES ('19', '34g3g4g', null, '2018-07-09 16:18:43', '9');
INSERT INTO `mer_card_cipher_tb` VALUES ('20', 'dgfdg324', null, '2018-07-09 16:18:44', '9');
INSERT INTO `mer_card_cipher_tb` VALUES ('21', 'ewrgerg', null, '2018-07-10 16:35:56', '10');
INSERT INTO `mer_card_cipher_tb` VALUES ('22', 'ergerwgre', null, '2018-07-10 16:35:56', '10');
INSERT INTO `mer_card_cipher_tb` VALUES ('23', 'gerg343434', null, '2018-07-10 16:35:56', '10');
INSERT INTO `mer_card_cipher_tb` VALUES ('24', 'e34343r', null, '2018-07-10 16:35:56', '10');
INSERT INTO `mer_card_cipher_tb` VALUES ('25', 'g3434gfe', null, '2018-07-10 16:35:56', '10');
INSERT INTO `mer_card_cipher_tb` VALUES ('26', 'rgfdg3434', null, '2018-07-10 16:35:56', '10');
INSERT INTO `mer_card_cipher_tb` VALUES ('27', 'e333343434r', null, '2018-07-10 16:35:56', '10');
INSERT INTO `mer_card_cipher_tb` VALUES ('28', 'gfdbfdbdf33434', null, '2018-07-10 16:35:56', '10');
INSERT INTO `mer_card_cipher_tb` VALUES ('29', '3434443434e', null, '2018-07-10 16:35:56', '10');

-- ----------------------------
-- Table structure for `mer_cate_tb`
-- ----------------------------
DROP TABLE IF EXISTS `mer_cate_tb`;
CREATE TABLE `mer_cate_tb` (
  `mer_cate_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品类型id',
  `name` varchar(255) DEFAULT NULL COMMENT '商品类型名称',
  `summary` varchar(255) DEFAULT NULL COMMENT '简介',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`mer_cate_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1006 DEFAULT CHARSET=utf8 COMMENT='商品类型表';

-- ----------------------------
-- Records of mer_cate_tb
-- ----------------------------
INSERT INTO `mer_cate_tb` VALUES ('1000', '暗黑破坏神', null, '2018-03-19 11:33:41');
INSERT INTO `mer_cate_tb` VALUES ('1001', '剑灵专区', '', '2018-03-26 15:54:37');
INSERT INTO `mer_cate_tb` VALUES ('1002', '魔兽世界', '魔兽世界需要，请批准', '2018-03-26 15:44:00');
INSERT INTO `mer_cate_tb` VALUES ('1003', '赛车竞速', null, '2018-03-27 10:38:59');
INSERT INTO `mer_cate_tb` VALUES ('1004', '角色扮演', null, '2018-06-24 14:20:23');
INSERT INTO `mer_cate_tb` VALUES ('1005', '剑灵专区2', '剑灵专区2', '2018-07-10 16:26:24');

-- ----------------------------
-- Table structure for `mer_common_tb`
-- ----------------------------
DROP TABLE IF EXISTS `mer_common_tb`;
CREATE TABLE `mer_common_tb` (
  `mer_common_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品公用id',
  `guide` longtext COMMENT '购物指南',
  `guarantee` longtext COMMENT '售后保障',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`mer_common_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1001 DEFAULT CHARSET=utf8 COMMENT='商品公用表';

-- ----------------------------
-- Records of mer_common_tb
-- ----------------------------
INSERT INTO `mer_common_tb` VALUES ('1000', '<p>第一步：打开商品详细页，浏览商品详情等相关信息：</p><p>第二步：点击立即购买按钮，然后填写收货地址和购买信息：</p><p>第三步：填写完信息确认无误后，点击购买按钮(下图以网上银行为例)：</p><p>第四步： 选择银行并进入银行页面付款（务必填写支付邮箱获取支付凭证）</p><p>第五步：成功支付：</p><p>如果是支付宝用户可以在确认购买信息中的支付方式点选支付宝：</p><p>进入支付页面后输入支付宝帐号及支付密码即可完成支付：</p>', '<table border=\"0\" cellpadding=\"1\" cellspacing=\"1\"><tbody><tr><td colspan=\"2\">数字版产品收货说明：</td></tr><tr><td>注册会员</td><td>1：手机注册会员</td></tr><tr><td>&nbsp;</td><td>付款成功后，系统会将激活码以短信的形式自动发送到您绑定的手机上。</td></tr><tr><td>&nbsp;</td><td>2：邮箱注册会员</td></tr><tr><td>&nbsp;</td><td>付款成功后，系统会将激活码以邮件的形式自动发送到您绑定的电子邮箱上。&nbsp;</td></tr><tr><td>&nbsp;</td><td>3：第三方平台登录会员</td></tr><tr><td>&nbsp;</td><td>付款成功后，进入页面顶部“我的商城”，在订单处手工领取激活码。</td></tr><tr><td>匿名购买</td><td>付款成功后，系统会将激活码以短信的形式自动发送到您购买时填写的手机上。</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td colspan=\"2\">实体版产品收货说明：</td></tr><tr><td colspan=\"2\">1：签收快递前应该先仔细检查物品的质量和数量再签收（收包裹前准备一把小刀，轻松割开）。如发现有商品缺少、配送有误、货物破损等问题请第一时间联系客服（快递单上有凤凰游戏商城的联系电话）。一旦签收，我们将理解为您已经确定了物品的质量和数量。不再进行售后服务。</td></tr><tr><td colspan=\"2\">2：如果在配送过程中因用户个人原因拒收(如不想要了，个人信息不正确，本人不在这个地址等)导致物品被退回，用户需自行承担来回运费。若需再次发货，请用户及时联系人工客服补交邮费。</td></tr></tbody></table><table border=\"0\" cellpadding=\"1\" cellspacing=\"1\"><tbody><tr><td>&nbsp;</td></tr><tr><td>数字版产品售后说明：</td></tr><tr><td>1：数字版产品为虚拟产品，付款成功后系统会自动发货，一经出售的数字版产品均不再进行任何原因的二次销售。故数字版产品如无质量问题，不接受任何理由的退换货服务，请谅解。</td></tr><tr><td>2：数字版产品如在激活码过程中遇到激活失败的问题，您可以选择以下三种方式寻求帮助。</td></tr><tr><td>a：直接联系在线客服咨询，凤凰游戏商城聘请了专业的技术顾问，提供免费解答。</td></tr><tr><td>b：登录“客服中心”-“FAQ资料库”自助查找原因（如官方服务器维护，个人操作错误，激活码输入错误等）</td></tr><tr><td>c：进入“在线提交问题”页面，详细描述在激活过程中遇到的问题，并提供激活码失败的截图。</td></tr><tr><td>3：数字版产品一旦激活成功，表示激活码本身不存在质量问题，非产品质量问题外的任何问题均不在退换货服务范围内。（如配置不符合，未反激活便卸载游戏或重装系统，官方服务器维护，游戏内容不喜欢等）</td></tr><tr><td>4：凤凰游戏商城所有出售的数字版产品均由官方直接供货，如激活码本身产生质量问题，将无条件为用户退货。</td></tr><tr><td>&nbsp;</td></tr><tr><td>实体版产品售后说明：</td></tr><tr><td>1：产品包装盒内部出现问题（如物品数量缺少，质量损坏等），请您拍下完整清晰的照片提交给在线客服，或者“在线提交问题”，我们会联系官方及时为您处理。</td></tr><tr><td>2：产品包装盒外部出现问题（如运输途中外包装损坏等），请拒绝签收并拍下完整清晰的照片提交给在线客服，或者“在线提交问题”，我们会联系物流公司进行协商处理，如您已经签收，很抱歉，快递公司将不予处理。商城客服会参考实际情况，与您各自承担一半责任一起处理。</td></tr><tr><td>&nbsp;</td></tr><tr><td>以下情况接受退换货服务：</td></tr><tr><td>1：商品未经拆封，外包装完整，相关附（配）件，赠品齐全无损坏。</td></tr><tr><td>2：商品表面无划痕、无磨损、无磕碰、无使用、无拆卸等痕迹，不影响二次销售。</td></tr><tr><td>3：（若有）防伪标识码未刮开或撕损，（若有）唯一性三包卡无缺失或撕损。</td></tr><tr><td>4：数字版产品本身存在质量问题，并经官方检测确认。</td></tr><tr><td>5：实体版产品未经签收便发现存在质量问题。</td></tr><tr><td>6：所有主机产品七天包换，三个月保修，一年免费维修。</td></tr><tr><td>7：所有主机产品原装配件，国产配件七天包换，三个月保修。</td></tr><tr><td>8：所有主机产品的原装闪存卡、记忆卡、硬盘，一年包换。</td></tr><tr><td>&nbsp;</td></tr><tr><td>以下情况不接受任何理由的退换货服务：</td></tr><tr><td>1：非质量问题的数字版产品。</td></tr><tr><td>2：任何非凤凰游戏商城出售的商品。</td></tr><tr><td>3：因用户个人原因造成的商品损坏、保管不当、非正常使用，或对产品部件进行安装、喷涂、改造等。</td></tr><tr><td>4：未经授权的维修，误用、滥用，私人改装，撕毁、涂改标贴货防伪标记，不正确的安装所造成的商品质量问题。</td></tr><tr><td>5：销售商品附送赠品，套装中的任何商品存在破损或丢失。</td></tr><tr><td>6：未经客服同意擅自将商品拒收或退回，商城不承担保管、重新发货、退款责任，必要时凤凰游戏商城有权处置滞留商品。</td></tr><tr><td>7：所有带液晶屏等电子产品根据厂商提供的说明，如果出现损坏破裂出现黑点（俗称坏点）以及带颜色或白色持续发光的点（俗称亮点）不在退换货服务范围内。</td></tr><tr><td>8：电子产品主机及设备因个人升级、降级、自行拆换硬盘或内置电池等引起的硬件损坏。</td></tr><tr><td>9：电子产品因维护不当（如堵住散热口，过长时间工作等）造成的（如XBOX三红，PS4蓝灯等）CPU过热造成的硬件损伤。</td></tr><tr><td>10：擅自将电子产品破解、越狱和解锁。</td></tr><tr><td>&nbsp;</td></tr><tr><td>退换货要求：</td></tr><tr><td>1：非质量问题产生的退换货申请，运费由用户自行承担。</td></tr><tr><td>2：由质量问题产生的退换货申请，运费由凤凰游戏商城承担。</td></tr><tr><td>3：退换货前需与在线客服取得联系并达成共识。如未经允许擅自退回物品，责任由用户自行承担。</td></tr><tr><td>4：退回的包裹内请留下信息纸条，内容包括：订单号，姓名，联系方式，退换货原因。</td></tr><tr><td>5：寄回物品时请连同收到的赠品，配件一同寄回，并在包裹内放上填充物，避免运输过程中的损坏。</td></tr><tr><td>6：我们会在收货前进行验货，确认产品质量与数量无误后将在5个工作日内为您办理退换货服务，并及时与您保持联系。</td></tr></tbody></table>', '2018-03-26 15:18:04');

-- ----------------------------
-- Table structure for `mer_img_tb`
-- ----------------------------
DROP TABLE IF EXISTS `mer_img_tb`;
CREATE TABLE `mer_img_tb` (
  `mer_img_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品图片id',
  `img_address` varchar(255) DEFAULT NULL COMMENT '图片地址',
  `number` int(11) DEFAULT NULL COMMENT '图片顺序',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `mer_id` int(11) DEFAULT NULL COMMENT '商品id外键',
  PRIMARY KEY (`mer_img_id`),
  KEY `INDEX_MERID` (`mer_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='商品图片表';

-- ----------------------------
-- Records of mer_img_tb
-- ----------------------------
INSERT INTO `mer_img_tb` VALUES ('1', 'http://p55v5f6od.bkt.clouddn.com/o_1cadk9ejb1045jkr8ma50s1duph.png', '2', '2018-04-06 21:55:52', '2018-04-06 22:03:21', '1');
INSERT INTO `mer_img_tb` VALUES ('3', 'http://p55v5f6od.bkt.clouddn.com/o_1cadn718dp4dr8l1hvq1a9hi8st5.jpg', '3', '2018-04-06 22:54:25', '2018-04-06 22:54:47', '1');
INSERT INTO `mer_img_tb` VALUES ('4', 'http://img.fhyx.com/uploads/contents/2015/01/07/2015010745728598.jpg', '1', '2018-05-31 10:29:17', '2018-05-31 10:29:17', '4');
INSERT INTO `mer_img_tb` VALUES ('5', 'http://img.fhyx.com/uploads/contents/2015/01/07/2015010745729733.jpg', '2', '2018-05-31 10:29:31', '2018-05-31 10:29:31', '4');
INSERT INTO `mer_img_tb` VALUES ('6', 'http://img.fhyx.com/uploads/contents/2015/01/07/2015010745730275.jpg', '3', '2018-05-31 10:29:41', '2018-05-31 10:29:41', '4');
INSERT INTO `mer_img_tb` VALUES ('7', 'http://p55v5f6od.bkt.clouddn.com/o_1cadk9ejb1045jkr8ma50s1duph.png', '2', '2018-07-06 14:49:06', '2018-07-06 14:49:06', '8');
INSERT INTO `mer_img_tb` VALUES ('8', 'http://p55v5f6od.bkt.clouddn.com/o_1cadn718dp4dr8l1hvq1a9hi8st5.jpg', '3', '2018-07-06 14:49:06', '2018-07-06 14:49:06', '8');
INSERT INTO `mer_img_tb` VALUES ('9', 'http://img.fhyx.com/uploads/contents/2015/01/07/2015010745728598.jpg', '1', '2018-07-06 19:18:57', '2018-07-06 19:18:57', '9');
INSERT INTO `mer_img_tb` VALUES ('10', 'http://img.fhyx.com/uploads/contents/2015/01/07/2015010745729733.jpg', '2', '2018-07-06 19:18:57', '2018-07-06 19:18:57', '9');
INSERT INTO `mer_img_tb` VALUES ('11', 'http://img.fhyx.com/uploads/contents/2015/01/07/2015010745730275.jpg', '3', '2018-07-06 19:18:57', '2018-07-06 19:18:57', '9');

-- ----------------------------
-- Table structure for `mer_notice_tb`
-- ----------------------------
DROP TABLE IF EXISTS `mer_notice_tb`;
CREATE TABLE `mer_notice_tb` (
  `mer_notice_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品公告id',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `content` longtext COMMENT '内容',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `mer_id` int(11) DEFAULT NULL COMMENT '商品id外键',
  PRIMARY KEY (`mer_notice_id`),
  KEY `INDEX_MERID` (`mer_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='商品公告表';

-- ----------------------------
-- Records of mer_notice_tb
-- ----------------------------
INSERT INTO `mer_notice_tb` VALUES ('1', '窘分的空间裂缝', '<p>输入内容...古典风格的现代诠释<img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/50/pcmoren_huaixiao_org.png\" alt=\"[坏笑]\" data-w-e=\"1\"></p>', '2018-04-06 21:22:01', '1');
INSERT INTO `mer_notice_tb` VALUES ('2', '反对反对反对', '<p>输入内容...</p><table border=\"0\" cellpadding=\"1\" cellspacing=\"1\"><tbody><tr><td><p><br><img src=\"http://img.fhyx.com/uploads/contents/2017/01/13/201701133574084.jpg\" title=\"点击查看大图\"></p></td></tr></tbody></table>', '2018-04-06 21:28:40', '1');
INSERT INTO `mer_notice_tb` VALUES ('3', '复古风格', '<p>输入内容...</p><table border=\"0\" cellpadding=\"1\" cellspacing=\"1\"><tbody><tr><td><p><br><img src=\"http://img.fhyx.com/uploads/contents/2017/01/13/201701133574084.jpg\" title=\"点击查看大图\"></p></td></tr></tbody></table>', '2018-04-06 21:32:31', '1');
INSERT INTO `mer_notice_tb` VALUES ('4', '安裝和激活說明（點擊查看大圖）', '<table border=\"0\" cellpadding=\"1\" cellspacing=\"1\"><tbody><tr><td>步驟一：</td><td>進入戰網進行注冊賬号</td></tr><tr><td>&nbsp;</td><td><p>戰網注冊地址：</p><p>https://tw.battle.net/account/creation/tos.html?style=LOBBY&amp;theme&amp;country=TW</p></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>&nbsp;</td><td><a href=\"http://www.ali213.net/showbigpic.html?http://images.ali213.net/picfile/pic/2015/03/16/20150316100245932.jpg\" target=\"_blank\"><img src=\"http://images.ali213.net/picfile/pic/2015/03/16/584_20150316100245932.jpg\"></a></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>步驟二：</td><td>注冊時選擇地區爲香港（身份證随便填）</td></tr><tr><td>&nbsp;</td><td><a href=\"http://www.ali213.net/showbigpic.html?http://images.ali213.net/picfile/pic/2015/03/16/20150316100249781.jpg\" target=\"_blank\"><img src=\"http://images.ali213.net/picfile/pic/2015/03/16/584_20150316100249781.jpg\"></a></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>步驟三：</td><td>注冊完帳号通過郵箱驗證之後登入帳号管理選擇上方遊戲管理菜單，點啓動序号激活遊戲。</td></tr><tr><td>&nbsp;</td><td><a href=\"http://www.ali213.net/showbigpic.html?http://images.ali213.net/picfile/pic/2015/03/16/20150316100252138.jpg\" target=\"_blank\"><img src=\"http://images.ali213.net/picfile/pic/2015/03/16/584_20150316100252138.jpg\"></a></td></tr><tr><td>&nbsp;</td><td><a href=\"http://www.ali213.net/showbigpic.html?http://images.ali213.net/picfile/pic/2015/03/16/2015031610025566.jpg\" target=\"_blank\"><img src=\"http://images.ali213.net/picfile/pic/2015/03/16/584_2015031610025566.jpg\"></a></td></tr></tbody></table>', '2018-05-31 13:21:02', '4');
INSERT INTO `mer_notice_tb` VALUES ('5', '什麽是共享儲存箱?', '<table border=\"0\" cellpadding=\"1\" cellspacing=\"1\"><tbody><tr><td>答：</td><td>共享儲存箱是一種物品儲存管理機制，玩家可以爲其賬号下的所有角色儲存一些有用的物品。</td></tr><tr><td>&nbsp;</td><td>不管玩家當前在使用哪個角色進行遊戲，任何角色都可以使用儲存在共享儲存箱裏的道具。</td></tr></tbody></table><p><br></p>', '2018-05-31 13:27:04', '4');

-- ----------------------------
-- Table structure for `mer_order_card_cipher_tb`
-- ----------------------------
DROP TABLE IF EXISTS `mer_order_card_cipher_tb`;
CREATE TABLE `mer_order_card_cipher_tb` (
  `mer_order_card_cipher_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品订单卡密id',
  `code` varchar(255) DEFAULT NULL COMMENT '卡密码',
  `img_address` varchar(255) DEFAULT NULL COMMENT '图片地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `order_id` int(11) DEFAULT NULL COMMENT '订单id外键',
  PRIMARY KEY (`mer_order_card_cipher_id`),
  KEY `INDEX_ORDERID` (`order_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='商品订单卡密表';

-- ----------------------------
-- Records of mer_order_card_cipher_tb
-- ----------------------------
INSERT INTO `mer_order_card_cipher_tb` VALUES ('1', 'sdfsdfs34', 'https://afp.alicdn.com/afp-creative/creative/u124884735/14945f2171400c10764ab8f3468470e4.jpg', '2018-06-30 15:08:18', '13');
INSERT INTO `mer_order_card_cipher_tb` VALUES ('2', 'efsgwrgwg', 'https://file.iviewui.com/dist/485aef835353edc50b8b253c2b4ad33b.jpg', '2018-06-30 18:13:29', '32');
INSERT INTO `mer_order_card_cipher_tb` VALUES ('3', 'sdgfgdsfgsfdgfd', '', '2018-06-30 18:16:52', '34');
INSERT INTO `mer_order_card_cipher_tb` VALUES ('4', 'dfgfsdgfdsg', '', '2018-06-30 18:16:52', '34');
INSERT INTO `mer_order_card_cipher_tb` VALUES ('6', 'sdafsdfsd', '', '2018-06-30 20:19:45', '35');
INSERT INTO `mer_order_card_cipher_tb` VALUES ('7', 'yukjyuiky', '', '2018-06-30 20:23:17', '11');
INSERT INTO `mer_order_card_cipher_tb` VALUES ('8', 'yyy', '', '2018-06-30 20:25:27', '36');
INSERT INTO `mer_order_card_cipher_tb` VALUES ('9', 'ertret', null, '2018-07-09 17:32:17', '37');
INSERT INTO `mer_order_card_cipher_tb` VALUES ('10', '1fdgdf', null, '2018-07-09 17:34:59', '38');

-- ----------------------------
-- Table structure for `mer_order_comment_tb`
-- ----------------------------
DROP TABLE IF EXISTS `mer_order_comment_tb`;
CREATE TABLE `mer_order_comment_tb` (
  `mer_order_comment_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品订单评论id',
  `mer_score` decimal(11,2) DEFAULT NULL COMMENT '评分',
  `content` varchar(255) DEFAULT NULL COMMENT '内容',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `mer_id` int(11) DEFAULT NULL COMMENT '商品id外键',
  `order_id` int(11) DEFAULT NULL COMMENT '订单id外键',
  `account_id` int(11) DEFAULT NULL COMMENT '评论人id外键',
  PRIMARY KEY (`mer_order_comment_id`),
  KEY `INDEX_MERSCORE` (`mer_score`) USING BTREE,
  KEY `INDEX_MERID` (`mer_id`) USING BTREE,
  KEY `INDEX_ORDERID` (`order_id`) USING BTREE,
  KEY `INDEX_ACCOUNTID` (`account_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='商品订单评论表';

-- ----------------------------
-- Records of mer_order_comment_tb
-- ----------------------------
INSERT INTO `mer_order_comment_tb` VALUES ('1', '4.00', '可以额，不错！', '2018-07-09 17:33:47', '9', '37', '1003');
INSERT INTO `mer_order_comment_tb` VALUES ('2', '4.00', '的所发生的', '2018-07-11 14:54:22', '8', '38', '1003');

-- ----------------------------
-- Table structure for `mer_relation_tb`
-- ----------------------------
DROP TABLE IF EXISTS `mer_relation_tb`;
CREATE TABLE `mer_relation_tb` (
  `mer_relation_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品关系id',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `platform_mer_id` int(11) DEFAULT NULL COMMENT '平台商品id',
  `seller_mer_id` int(11) DEFAULT NULL COMMENT '商家商品id',
  `seller_account_id` int(11) DEFAULT NULL COMMENT '商家账户id外键',
  PRIMARY KEY (`mer_relation_id`),
  KEY `INDEX_PLATFORMMERID` (`platform_mer_id`) USING BTREE,
  KEY `INDEX_SELLERMERID` (`seller_mer_id`) USING BTREE,
  KEY `INDEX_SELLERACCOUNTID` (`seller_account_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='商品关系表';

-- ----------------------------
-- Records of mer_relation_tb
-- ----------------------------
INSERT INTO `mer_relation_tb` VALUES ('1', '2018-07-06 11:57:18', '5', '7', '1004');
INSERT INTO `mer_relation_tb` VALUES ('2', '2018-07-06 14:49:06', '1', '8', '1004');
INSERT INTO `mer_relation_tb` VALUES ('3', '2018-07-06 19:18:57', '4', '9', '1004');
INSERT INTO `mer_relation_tb` VALUES ('4', '2018-07-10 16:35:56', '2', '10', '1004');

-- ----------------------------
-- Table structure for `mer_search_tb`
-- ----------------------------
DROP TABLE IF EXISTS `mer_search_tb`;
CREATE TABLE `mer_search_tb` (
  `mer_search_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品搜索id',
  `name` varchar(255) DEFAULT NULL COMMENT '商品搜索名称',
  `number` int(11) DEFAULT NULL COMMENT '次数',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`mer_search_id`),
  KEY `INDEX_NAME` (`name`) USING BTREE,
  KEY `INDEX_NUMBER` (`number`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='商品搜索表';

-- ----------------------------
-- Records of mer_search_tb
-- ----------------------------
INSERT INTO `mer_search_tb` VALUES ('1', '暗黑破坏神', '19999', '2018-03-26 13:57:24');
INSERT INTO `mer_search_tb` VALUES ('2', '剑灵', '6756752', '2018-03-26 13:58:31');
INSERT INTO `mer_search_tb` VALUES ('3', '魔兽', '12323', '2018-03-26 13:58:44');
INSERT INTO `mer_search_tb` VALUES ('4', '死灵法师', '1232323', '2018-03-26 13:59:17');
INSERT INTO `mer_search_tb` VALUES ('5', '守望先锋', '12323', '2018-03-26 13:59:31');

-- ----------------------------
-- Table structure for `mer_tb`
-- ----------------------------
DROP TABLE IF EXISTS `mer_tb`;
CREATE TABLE `mer_tb` (
  `mer_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `region` tinyint(4) DEFAULT NULL COMMENT '范围，1官网自营，2商户非自营，3商户自营',
  `platform_proportion` decimal(11,2) DEFAULT '5.00' COMMENT '平台分成比例，单位%',
  `type` tinyint(4) DEFAULT NULL COMMENT '类型，1普通商品，2降价商品，3预购商品',
  `deliver_end_date` datetime DEFAULT NULL COMMENT '最迟发货时间，预购商品可选',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `summary` longtext COMMENT '简介',
  `img_address` varchar(255) DEFAULT NULL COMMENT '封面',
  `platform` varchar(255) DEFAULT NULL COMMENT '平台',
  `recommend` tinyint(4) DEFAULT '0' COMMENT '推荐，默认0不推，1封推，2推荐',
  `old_unit_price` decimal(11,2) DEFAULT '0.00' COMMENT '原始单价',
  `unit_price` decimal(11,2) DEFAULT '0.00' COMMENT '单价',
  `discount` decimal(11,2) DEFAULT '0.00' COMMENT '折扣',
  `sale_number` int(11) DEFAULT '0' COMMENT '销量',
  `stock_number` int(11) DEFAULT NULL COMMENT '库存数',
  `mer_score` decimal(11,2) DEFAULT NULL COMMENT '商品评分',
  `mer_comment_number` int(11) DEFAULT NULL COMMENT '商品评论数',
  `details` longtext COMMENT '商品详情',
  `configuration` longtext COMMENT '配置要求',
  `install_activation` longtext COMMENT '安装激活',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态0下架,默认1上架',
  `mer_cate_id` int(11) DEFAULT NULL COMMENT '商品类型id,外键',
  `seller_account_id` int(11) DEFAULT NULL COMMENT '商户账户id,外键',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`mer_id`),
  KEY `INDEX_REGION` (`region`) USING BTREE,
  KEY `INDEX_TYPE` (`type`) USING BTREE,
  KEY `INDEX_NAME` (`name`) USING BTREE,
  KEY `INDEX_RECOMMEND` (`recommend`) USING BTREE,
  KEY `INDEX_UNITPRICE` (`unit_price`) USING BTREE,
  KEY `INDEX_SALENUMBER` (`sale_number`) USING BTREE,
  KEY `INDEX_MERSCORE` (`mer_score`) USING BTREE,
  KEY `INDEX_VIDEOSETCATEID` (`mer_cate_id`) USING BTREE,
  KEY `INDEX_SELLERACCOUNTID` (`seller_account_id`) USING BTREE,
  KEY `INDEX_CREATEDATE` (`create_date`) USING BTREE,
  KEY `INDEX_UPDATEDATE` (`update_date`) USING BTREE,
  KEY `INDEX_STATUS` (`status`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='商品表';

-- ----------------------------
-- Records of mer_tb
-- ----------------------------
INSERT INTO `mer_tb` VALUES ('1', '1', '5.00', '1', null, '科林麦克雷拉力赛之尘埃3 PC版', '语言：英文，使用平台：Steam，激活码用于绑定账户。注意：仅限18岁以上玩家购买和使用！', 'http://img.fhyx.com/uploads/2017/01/19/2017011921538261.gif', 'pc端1', '0', '179.00', '17.90', '0.10', '0', '1', '0.00', '0', '<table border=\"0\" cellpadding=\"1\" cellspacing=\"1\"><tbody><tr><td>产品参数：</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>游戏名称：科林麦克雷拉力赛之尘埃3</td><td>游戏类型：赛车竞速RAC</td><td>制作公司：Codemasters</td><td>发售日期：2011年05月24日</td></tr><tr><td>使用平台：Steam</td><td>语言版本：英文</td><td>发行公司：Codemasters</td><td>客服邮箱：kf@fhyx.hk</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr></tbody></table><table border=\"0\" cellpadding=\"1\" cellspacing=\"1\"><tbody><tr><td><table border=\"0\" cellpadding=\"1\" cellspacing=\"1\"><tbody><tr><td colspan=\"2\"><strong>注意：仅限18岁以上玩家购买和使用！语言暂不支持中文！</strong></td></tr></tbody></table></td></tr></tbody></table><table border=\"0\" cellpadding=\"1\" cellspacing=\"1\"><tbody><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td><strong>全球版key：</strong></td><td>DiRT 3 - Complete Edition</td></tr><tr><td>&nbsp;</td><td>适用于全球地区使用。</td></tr><tr><td>&nbsp;</td><td>格式为“AAAAA-BBBBB-CCCCC”共15位，由数字和字母组成。</td></tr><tr><td>&nbsp;</td><td>使用说明：登录需绑定游戏的Steam账号，进入游戏库，在“产品代码”对话框中输入激活码。</td></tr></tbody></table><table border=\"0\" cellpadding=\"1\" cellspacing=\"1\"><tbody><tr><td>&nbsp;</td></tr><tr><td><strong>售后说明：</strong></td></tr><tr><td>1：CDkey与礼物类商品，一经使用将永久与您的Steam账号绑定，无法剥离，无法二次销售，因此售出的商品不再提供任何退换货服务（包括但不限于买错游戏，配置不符，网络问题，游戏限区等）。如您对此持有异议，请勿购买。强烈建议您在购买游戏之前，先了解游戏的配置要求，语言版本，使用地区等基本信息。</td></tr><tr><td>2：国区礼物为Steam商店内出售的正版游戏礼物（Gift），国区版仅限中国大陆玩家激活使用，非中国大陆玩家请勿购买！国区礼物非激活码key，非实物盒装光盘。付款后，系统将以邮件的形式，将礼物链接发送至您的订单和接收邮箱内。请自行领取并使用。</td></tr><tr><td>3：如玩家采用第三方软件，作弊，违规等非正常游戏手段（包括但不限于使用外挂，篡改游戏数据，打非官方补丁等）导致账户异常或者封禁，后果需玩家自行承担。</td></tr><tr><td>4：商城提供免费的正版游戏下载和技术顾问支持，如需咨询，请提交至<strong>客服邮箱：kf@fhyx.hk</strong></td></tr><tr><td>&nbsp;</td></tr></tbody></table><table border=\"0\" cellpadding=\"1\" cellspacing=\"1\"><tbody><tr><td>游戏背景：</td><td>&nbsp;</td></tr><tr><td><p>&nbsp; &nbsp; &nbsp; 游戏中将带有全新的生涯模式，强调了团队合作的元素。你将从上世纪90年代开始，以“私人赛车手”来参加一系列赛事。融合众多现代和经典赛车，还有Morris Cooper这样长相古怪的传奇赛车和诸如super buggies和TRAID trucks之类的车。所有的赛车模型会比以往的作品更加细致。《尘埃3》带有从挪威的阿斯彭到蒙特卡罗到非洲平原的超过100条赛道。</p></td><td>&nbsp;</td></tr></tbody></table><table border=\"0\" cellpadding=\"1\" cellspacing=\"1\"><tbody><tr><td>&nbsp;</td></tr></tbody></table><table border=\"0\" cellpadding=\"1\" cellspacing=\"1\"><tbody><tr><td>游戏截图：</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr><tr><td>&nbsp;</td><td><p><img src=\"http://img.fhyx.com/uploads/contents/2017/01/13/201701133574084.jpg\" title=\"点击查看大图\"></p></td></tr><tr><td>&nbsp;</td><td><p><img src=\"http://img.fhyx.com/uploads/contents/2017/01/13/2017011335740953.jpg\"></p></td></tr><tr><td>&nbsp;</td><td><p><img src=\"http://img.fhyx.com/uploads/contents/2017/01/13/2017011335740656.jpg\" title=\"点击查看大图\"></p></td></tr></tbody></table>', '<div><div>最低配置</div><p>系统：&nbsp;WinXP/2003/Vista/7</p><p>Cpu：&nbsp;AMD Athlon 64 X2 2.8GHz / Intel PentiumD 2.8Ghz</p><p>内存：&nbsp;2GB</p><p>显卡：&nbsp;AMD Radeon HD 2000 / NVIDIA GeForce 8000</p><p>硬盘：&nbsp;5.88 GB</p></div><div><div>推荐配置</div><p>系统：&nbsp;WinXP/2003/Vista/7</p><p>Cpu：&nbsp;AMD Phenom II / Intel Core i7</p><p>内存：&nbsp;3GB</p><p>显卡：&nbsp;AMD Radeon HD 6000系列</p><p>硬盘：&nbsp;5.88 GB</p></div>', '<p><iframe frameborder=\"0\" scrolling=\"yes\" name=\"main\" src=\"http://ali213.fhyx.hk/help/steam.html\"></iframe>&nbsp;&nbsp;<br></p>', '1', '1003', null, '2018-03-27 10:43:47', '2018-06-30 18:13:29');
INSERT INTO `mer_tb` VALUES ('2', '1', '5.00', '1', null, '三国志13 PC版中文', '三国志13 PC版中文', 'http://img.fhyx.com/uploads/2017/02/17/2017021745626168.jpg', 'Steam', '0', '259.00', '259.00', '1.00', '0', '0', '0.00', '0', '<table border=\"0\" cellpadding=\"1\" cellspacing=\"1\"><tbody><tr><td>产品参数：</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>游戏名称：三国志13</td><td>游戏类型：策略战棋SLG</td><td>制作公司：KOEI TECMO</td><td>发售时间：2016年01月28日</td></tr><tr><td>使用平台：Steam</td><td>语言版本：繁体中文</td><td>发行公司：KOEI TECMO</td><td>客服邮箱：kf@fhyx.hk</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr></tbody></table><table border=\"0\" cellpadding=\"1\" cellspacing=\"1\"><tbody><tr><td colspan=\"2\"><strong>注意：仅限16岁以上玩家购买和使用！</strong></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td><strong>三国志13台版key：</strong></td><td>含且仅含ROMANCE OF THE THREE KINGDOMS 13 /三国志13本体。</td></tr><tr><td>&nbsp;</td><td>适用于全球地区使用</td></tr><tr><td>&nbsp;</td><td>格式为“AAAAA-BBBBB-CCCCC”共15位，由数字和字母组成。</td></tr><tr><td>&nbsp;</td><td>使用说明：登录需绑定游戏的Steam账号，进入游戏库，在“产品代码”对话框中输入激活码。</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td><strong>三国志13PK威力加强版：</strong></td><td>含且仅含三国志13PK威力加强版，Romance Of Three Kingdom 13 PK DLC。</td></tr><tr><td>&nbsp;</td><td>该内容需要在Steam拥有基础游戏ROMANCE OF THE THREE KINGDOMS 13 / 三国志13 才能运行，无法单独使用。</td></tr><tr><td>&nbsp;</td><td>适用于全球地区使用</td></tr><tr><td>&nbsp;</td><td>如需购买，请点击&nbsp;&nbsp;<a href=\"http://www.fhyx.hk/item/1158.html\" target=\"_blank\"><img src=\"http://img.fhyx.com/uploads/contents/2017/02/16/201702169484332.jpg\" title=\"点击查看大图\"></a>&nbsp;前往购买</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td><strong>三国志13with PUK合辑：</strong></td><td>包含2件物品：三国志13 本体，三国志13PK威力加强版</td></tr><tr><td>&nbsp;</td><td>适用于全球地区使用</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td><strong>额外DLC：</strong></td><td>「英雄十三杰」「吕布讨伐战」「赤壁之战」</td></tr><tr><td>&nbsp;</td><td>该内容需要在Steam拥有基础游戏ROMANCE OF THE THREE KINGDOMS 13 / 三国志13 才能运行，无法单独使用。</td></tr></tbody></table><table border=\"0\" cellpadding=\"1\" cellspacing=\"1\"><tbody><tr><td>&nbsp;</td></tr><tr><td>游戏背景：</td></tr><tr><td>&nbsp;　&nbsp;&nbsp; 30 周年记念作品的最新作《三国志13》是以「这就是三国志。百花撩乱的英杰剧。」为概念，是一款在游戏中各种场面都可以感受到英杰们纵横无尽的精彩演出。各路英杰们不同的生存之道以及在战场中牵连起来的羁绊等，透过全武将游玩而描绘的更加鲜明的“群像剧”。水上战以及攻城战等的战略・战闘将会展开大规模的“壮阔” 战斗。以辽阔的中国大陆为舞台，数百名登场人物们为了扩大自己势力的“魄力” 等，都可更加强烈感受到《三国志》的世界。<p>　　玩家要扮演在《三国志》中活跃的一名英杰，以历史的主角来创造只属于自己的历史。与神算的军师跟无双的豪杰们时而合作时而竞争，玩家可自由的开创自己的故事。</p></td></tr><tr><td>&nbsp;</td></tr></tbody></table><table border=\"0\" cellpadding=\"1\" cellspacing=\"1\"><tbody><tr><td>游戏截图：</td><td>&nbsp;</td></tr><tr><td>&nbsp;</td><td><p><img src=\"http://img.fhyx.com/uploads/contents/2016/09/12/2016091231942808.jpg\" title=\"点击查看大图\"></p></td></tr><tr><td>&nbsp;</td><td><p><img src=\"http://img.fhyx.com/uploads/contents/2016/09/12/2016091231942900.jpg\" title=\"点击查看大图\"></p></td></tr><tr><td>&nbsp;</td><td><p><img src=\"http://img.fhyx.com/uploads/contents/2016/09/12/2016091231943169.jpg\" title=\"点击查看大图\"></p></td></tr></tbody></table>', '<div><div>最低配置</div><p>系统：&nbsp;Windows Vista / 7 / 8 / 8.1 / 10日语版</p><p>Cpu：&nbsp;Pentium4 1.6GHz以上</p><p>内存：&nbsp;1 GB以上</p><p>显卡：&nbsp;Intel HD Graphics 2000/Nvidia GeForce 7800/ATI Radeon X1300/</p><p>硬盘：&nbsp;7GB以上</p></div><div><div>推荐配置</div><p>系统：&nbsp;Windows Vista / 7 / 8 / 8.1 / 10日语版</p><p>Cpu：&nbsp;Core2 Duo 2.0GHz以上</p><p>内存：&nbsp;2 GB以上</p><p>显卡：&nbsp;GTX430</p><p>硬盘：&nbsp;7GB以上</p></div>', '<p><iframe frameborder=\"0\" scrolling=\"yes\" name=\"main\" src=\"http://ali213.fhyx.hk/help/steam.html\"></iframe>&nbsp;&nbsp;<br></p>', '1', '1003', null, '2018-04-08 18:55:29', '2018-04-08 18:55:29');
INSERT INTO `mer_tb` VALUES ('3', '1', '5.00', '1', null, '绝地求生大逃杀PC版', '绝地求生大逃杀PC版', 'http://img.fhyx.com/uploads/2017/10/04/2017100453152231.jpg', 'Steam', '0', '98.00', '98.00', '1.00', '0', '0', '0.00', '0', '<table border=\"0\" cellpadding=\"1\" cellspacing=\"1\"><tbody><tr><td>产品参数：</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>游戏名称：绝地求生大逃杀</td><td>游戏类型：动作游戏ACT</td><td>制作公司：Bluehole, Inc.</td><td>发售日期：2017年3月23日</td></tr><tr><td>使用平台：Steam</td><td>语言版本：英文/中文</td><td>发行公司：Bluehole, Inc.</td><td>客服邮箱：kf@fhyx.hk</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr></tbody></table><table border=\"0\" cellpadding=\"1\" cellspacing=\"1\"><tbody><tr><td colspan=\"2\"><strong>需要同意一份来自第三方的最终用户许可协议:PLAYERUNKNOWN\'S BATTLEGROUNDS EULA</strong></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td><strong>大陆专用key：</strong></td><td>适用且仅适用于中国大陆地区使用。</td></tr><tr><td>&nbsp;</td><td>格式为“AAAAA-BBBBB-CCCCC”共15位，由数字和字母组成。</td></tr><tr><td>&nbsp;</td><td>使用说明：登录需绑定游戏的Steam账号，进入游戏库，在“产品代码”对话框中输入激活码。</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td><strong>EARLY BIRD KEY：</strong></td><td>兑换5把/ 兑换10把 均为大陆版Key</td></tr><tr><td>&nbsp;</td><td>适用且仅适用于中国大陆地区使用。</td></tr><tr><td>&nbsp;</td><td>需要在Steam 拥有基础游戏绝地求生大逃杀才能运行。</td></tr><tr><td>&nbsp;</td><td><p>我们的钥匙为蓝洞（吃鸡开发商）直供，区别于其他渠道的钥匙，没有7天交易限制，可直接交易，</p><p>且用我们的钥匙打开无限制（没有经过steam市场）的宝箱获取的饰品，可立即交易，</p><p>目前Early Bird Key支持开启GAMESCOM INVITATIONAL CRATE，FEVER CRATE，DESPERADO CRATE！</p></td></tr><tr><td>&nbsp;</td><td><p><img src=\"http://img.fhyx.com/uploads/contents/2018/04/08/20180408346414.png\" title=\"点击查看大图\"></p></td></tr><tr><td>&nbsp;</td><td>购买后，这件物品：<br>一周内将不可交易<br>一周内无法在Steam社区市场挂出<br>该物品相当用于提供Steam退款的“游戏中物品”</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td><strong>WEAPON SKIN KEY：</strong></td><td>兑换1把为STEAM国区代购</td></tr><tr><td>&nbsp;</td><td>适用且仅适用于中国大陆地区使用。</td></tr><tr><td>&nbsp;</td><td>需要在Steam 拥有基础游戏绝地求生大逃杀才能运行。</td></tr><tr><td>&nbsp;</td><td><p><img src=\"http://img.fhyx.com/uploads/contents/2018/04/08/2018040834713987.jpg\" title=\"点击查看大图\"></p></td></tr><tr><td>&nbsp;</td><td>购买后，这件物品：<br>一周内将不可交易<br>一周内无法在Steam社区市场挂出<br>该物品相当用于提供Steam退款的“游戏中物品”</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td><strong>STEAM代购：</strong></td><td>仅限STEAM国区代购，适用于中国大陆地使用。</td></tr><tr><td>&nbsp;</td><td>付款成功后，<strong>请主动联系客服添加STEAM好友，便于接收礼物。</strong>请勿留邮箱。</td></tr><tr><td>&nbsp;</td><td>代购时间：<strong>9:00-21:00</strong>（代购需人工在线，无法做到即买即发，请谨慎购买。）</td></tr><tr><td>&nbsp;</td><td>使用说明：登录需要绑定游戏的Steam账号，确定版本无误，点击“添加至我的游戏库”。</td></tr></tbody></table><table border=\"0\" cellpadding=\"1\" cellspacing=\"1\"><tbody><tr><td>&nbsp;</td></tr><tr><td>&nbsp;</td></tr><tr><td><strong>大陆版Key激活地址：</strong></td></tr><tr><td><a href=\"https://store.steampowered.com/login/?redir=account%2Fregisterkey&amp;redir_ssl=1\" target=\"_blank\"><strong>https://store.steampowered.com/login/?redir=account%2Fregisterkey&amp;redir_ssl=1</strong></a></td></tr><tr><td>&nbsp;</td></tr><tr><td><strong>激活流程：</strong></td></tr><tr><td>第一步：首先请前往Steam官网注册账号，并下载安装Steam客户端</td></tr><tr><td>官网地址：http://store.steampowered.com/join/？</td></tr><tr><td><p><img src=\"http://img.fhyx.com/uploads/contents/2018/02/27/20180227100511495.jpg\" title=\"点击查看大图\"></p></td></tr><tr><td>&nbsp;</td></tr><tr><td>第二步：安装好Steam客户端后，请购买我们的激活码，收到激活码后，请登录您的Steam客户端，</td></tr><tr><td>并点击游戏，在弹出的选项中选择在Steam上激活产品</td></tr><tr><td><p><img src=\"http://img.fhyx.com/uploads/contents/2018/02/27/2018022710051128.jpg\" title=\"点击查看大图\"></p></td></tr><tr><td>&nbsp;</td></tr><tr><td>第三步：下一步在弹出的产品激活产品代码框中输入我们刚才发给你的激活码，激活成功后即可安装游戏</td></tr><tr><td><p><img src=\"http://img.fhyx.com/uploads/contents/2018/02/27/20180227100511688.jpg\" title=\"点击查看大图\"><img src=\"http://img.fhyx.com/uploads/contents/2018/02/27/20180227100512925.jpg\" title=\"点击查看大图\"><img src=\"http://img.fhyx.com/uploads/contents/2018/02/27/20180227100512221.jpg\" title=\"点击查看大图\"><img src=\"http://img.fhyx.com/uploads/contents/2018/02/27/20180227100513606.jpg\"></p></td></tr><tr><td>&nbsp;</td></tr></tbody></table><table border=\"0\" cellpadding=\"1\" cellspacing=\"1\"><tbody><tr><td><p><strong>PUBG宝箱钥匙是绝地求生游戏内道具，激活前需购买绝地求生游戏，</strong></p><p><strong>该key唯一用途为开启PUBG宝箱，宝箱需另外途径获取</strong></p></td></tr><tr><td><strong>用此钥匙打开无限制宝箱，饰品可直接交易</strong></td></tr><tr><td><a href=\"http://ali213.fhyx.hk/item/2224.html\" target=\"_blank\"><img src=\"http://img.fhyx.com/uploads/contents/2018/02/27/201802274581870.jpg\"></a></td></tr><tr><td><strong>激活流程</strong></td></tr><tr><td><strong>第一步：打开绝地求生，选择REWARDS</strong></td></tr><tr><td><p><strong><img src=\"http://img.fhyx.com/uploads/contents/2018/02/27/201802274391812.jpg\"></strong></p></td></tr><tr><td><strong>第二步：点击ADD BONUS/GIFT CODE</strong></td></tr><tr><td><p><strong><img src=\"http://img.fhyx.com/uploads/contents/2018/02/27/2018022743918116.jpg\" title=\"点击查看大图\"></strong></p></td></tr><tr><td><strong>第三步：输入激活码，点击REDEEM，钥匙数量变化即代表激活成功</strong></td></tr><tr><td><p><strong><img src=\"http://img.fhyx.com/uploads/contents/2018/02/27/2018022743919157.jpg\" title=\"点击查看大图\"><img src=\"http://img.fhyx.com/uploads/contents/2018/02/27/2018022743919708.jpg\"></strong></p></td></tr><tr><td><strong>第四步：选择MY CRATES，点击下方OPEN CRATE</strong></td></tr><tr><td><p><strong><img src=\"http://img.fhyx.com/uploads/contents/2018/02/27/20180227439201.jpg\" title=\"点击查看大图\"></strong></p></td></tr><tr><td><strong>第五步：点击USE KEY，即可使用钥匙</strong></td></tr><tr><td><p><strong><img src=\"http://img.fhyx.com/uploads/contents/2018/02/27/2018022743921219.jpg\"><img src=\"http://img.fhyx.com/uploads/contents/2018/02/27/201802274392156.jpg\" title=\"点击查看大图\"></strong></p></td></tr></tbody></table>', '<div><div>最低配置</div><p>系统：&nbsp;64-bit Windows 7, Windows 8.1, Windows 10</p><p>Cpu：&nbsp;Intel Core i3-4340 / AMD FX-6300</p><p>内存：&nbsp;6 GB RAM</p><p>显卡：&nbsp;nVidia GeForce GTX 660 2GB / AMD Radeon HD 7850 2GB</p><p>硬盘：需要30 GB可用空间</p></div><div><div>推荐配置</div><p>系统：无</p><p>Cpu：无</p><p>内存：无</p><p>显卡：无</p><p>硬盘：无</p></div>', '<p><iframe frameborder=\"0\" scrolling=\"yes\" name=\"main\" src=\"http://ali213.fhyx.hk/help/steam.html\"></iframe>&nbsp;&nbsp;<br></p>', '1', '1001', null, '2018-04-08 19:02:37', '2018-04-08 19:02:37');
INSERT INTO `mer_tb` VALUES ('4', '1', '5.00', '1', null, '暗黑破坏神3 PC版 全球版', '暗黑破壞神3 PC版 全球版', 'http://img.fhyx.com/uploads/2016/12/16/2016121643141739.jpg', '', '2', '298.00', '238.40', '0.80', '0', '0', '0.00', '0', '<table border=\"0\" cellpadding=\"1\" cellspacing=\"1\"><tbody><tr><td>産品參數：</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>遊戲名稱：暗黑破壞神3</td><td>遊戲類型：角色扮演RPG</td><td>制作公司：Blizzard</td><td>發售時間：2012年05月15日</td></tr><tr><td>使用平台：暴雪戰網</td><td>語言版本：繁體中文</td><td>發行公司：Blizzard</td><td>客服郵箱：kf@fhyx.hk</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr></tbody></table><table border=\"0\" cellpadding=\"1\" cellspacing=\"1\"><tbody><tr><td colspan=\"2\"><strong>注意：僅限18歲以上玩家購買和使用！全球版Key國區無法使用，激活平台爲暴雪戰網。（無法用于STEAM平台）</strong></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td><strong>暗黑破壞神3主程序：</strong></td><td>支持全球地區使用（除國服）。</td></tr><tr><td>&nbsp;</td><td>激活平台爲暴雪戰網。（無法用于STEAM平台）</td></tr><tr><td>&nbsp;</td><td>格式爲“AAAAAA-BBBB-CCCCCC-DDDD-EEEEEE”共26位，由數字和字母組成。 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td><strong>死神之鐮資料片：</strong></td><td>支持全球地區使用（除國服）。</td></tr><tr><td>&nbsp;</td><td>激活平台爲暴雪戰網。（無法用于STEAM平台）</td></tr><tr><td>&nbsp;</td><td>該内容需要在戰網平台擁有基礎遊戲 Diablo III 才能運行，無法單獨使用。</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td><strong>暗黑破壞神3合輯：</strong></td><td>diablo 3 battlechest</td></tr><tr><td>&nbsp;</td><td>包含2件物品：暗黑破壞神3主程序，死神之鐮資料片</td></tr><tr><td>&nbsp;</td><td>支持全球地區使用（除國服）。</td></tr><tr><td>&nbsp;</td><td>激活平台爲暴雪戰網。（無法用于STEAM平台）</td></tr><tr><td colspan=\"2\">&nbsp;</td></tr></tbody></table><table border=\"0\" cellpadding=\"1\" cellspacing=\"1\"><tbody><tr><td><strong>售後說明：</strong></td></tr><tr><td>1：CDkey與禮物類商品，一經使用将永久與您的Steam賬号綁定，無法剝離，無法二次銷售，因此售出的商品不再提供任何退換貨服務（包括但不限于買錯遊戲，配置不符，網絡問題，遊戲限區等）。如您對此持有異議，請勿購買。強烈建議您在購買遊戲之前，先了解遊戲的配置要求，語言版本，使用地區等基本信息。</td></tr><tr><td>2：國區禮物爲Steam商店内出售的正版遊戲禮物（Gift），國區版僅限中國大陸玩家激活使用，非中國大陸玩家請勿購買！國區禮物非激活碼key，非實物盒裝光盤。付款後，系統将以郵件的形式，将禮物鏈接發送至您的訂單和接收郵箱内。請自行領取并使用。</td></tr><tr><td>3：如玩家采用第三方軟件，作弊，違規等非正常遊戲手段（包括但不限于使用外挂，篡改遊戲數據，打非官方補丁等）導緻賬戶異常或者封禁，後果需玩家自行承擔。</td></tr><tr><td>4：商城提供免費的正版遊戲下載和技術顧問支持，如需咨詢，請提交至<strong>客服郵箱：kf@fhyx.hk</strong></td></tr><tr><td>&nbsp;</td></tr><tr></tr></tbody></table><table border=\"0\" cellpadding=\"1\" cellspacing=\"1\"><tbody><tr><td>遊戲背景：</td></tr><tr><td>&nbsp; &nbsp; &nbsp; &nbsp;《暗黑破壞神3》是著名動作角色扮演遊戲《暗黑破壞神2》的續作，于2008年6月28日在法國巴黎舉行的2008Blizzard全球邀請賽中公布研發暗黑破壞神 III及播放遊戲試玩畫面，已經知道有野蠻人、巫醫、魔法師、武僧四個職業角色。目前以Windows和Mac OS X爲支援平台。發行日期尚未公布。<br><br>&nbsp; &nbsp; &nbsp; &nbsp;《暗黑破壞神3》将像前作一樣是一款動作角色扮演遊戲。雖然《暗黑破壞神3》會保留很多原版的遊戲特點，但它将着重在合作和小組配合上。《暗黑破壞神3》的專有引擎将使用Havok物理引擎來顯示，玩家可損壞遊戲環境。開發小組計劃支持多種不同的系統組合，而且不要求Windows Vista的DirectX 10。暴雪娛樂準備同時間出版Microsoft Windows和Mac OS X版本，目前不準備支持遊戲機平台。<br><br>&nbsp; &nbsp; &nbsp; &nbsp;多人遊戲将使用暴雪自有的Battle.net服務，許多爲《星際争霸2》而開發的新功能将同時間應用于《暗黑破壞神3》。玩家将可以随時參加和退出合夥遊戲。在前作裏，每個角色的性别是預定的。在《暗黑破壞神3》裏，玩家在建立人物時可以随意選擇一個角色的男性和女性。<br><br>&nbsp; &nbsp; &nbsp; &nbsp;新的任務系統将與随機布局發生器配合給遊戲帶來充分的重玩價值。随機遭遇系統将更加的鞏固其重玩價值。總的來說，遊戲世界将包含随機布局與靜态布局綜合特點。敵人将利用三維環境來決定其戰術，比如敵人可以從深處攀牆來攻擊玩家。</td></tr><tr><td>&nbsp;</td></tr></tbody></table><table border=\"0\" cellpadding=\"1\" cellspacing=\"1\"><tbody><tr><td>遊戲截圖：</td><td>&nbsp;</td></tr><tr><td>&nbsp;</td><td><p><img src=\"http://img.fhyx.com/uploads/contents/2015/01/07/2015010745728598.jpg\" title=\"点击查看大图\"></p></td></tr><tr><td>&nbsp;</td><td><p><img src=\"http://img.fhyx.com/uploads/contents/2015/01/07/2015010745729733.jpg\" title=\"点击查看大图\"></p></td></tr><tr><td>&nbsp;</td><td><p><img src=\"http://img.fhyx.com/uploads/contents/2015/01/07/2015010745730275.jpg\" title=\"点击查看大图\"></p></td></tr></tbody></table>', '<div><div>最低配置</div><p>系統：WinXP/2003/Vista/7</p><p>Cpu：Intel Pentium D / AMD AthlonTM 64 X2 4400+</p><p>内存：1.5GB</p><p>顯卡：NVIDIA GeForce 7800GT / ATI Radeon X1950 Pro</p><p>硬盤：7.6G</p></div><div><div>推薦配置</div><p>系統：WinXP/2003/Vista/7</p><p>Cpu：Intel Core 2 Duo / AMD AthlonTM 64 X2 5600+</p><p>内存：2GB</p><p>顯卡：NVIDIA GeForce 260 / ATI Radeon 4870</p><p>硬盤：7.6G</p></div><p><br></p>', '<table border=\"0\" cellpadding=\"1\" cellspacing=\"1\"><tbody><tr><td>步驟一：</td><td>進入戰網進行注冊賬号</td></tr><tr><td>&nbsp;</td><td><p>戰網注冊地址：</p><p>https://tw.battle.net/account/creation/tos.html?style=LOBBY&amp;theme&amp;country=TW</p></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>&nbsp;</td><td><a href=\"http://www.ali213.net/showbigpic.html?http://images.ali213.net/picfile/pic/2015/03/16/20150316100245932.jpg\" target=\"_blank\"><img src=\"http://images.ali213.net/picfile/pic/2015/03/16/584_20150316100245932.jpg\"></a></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>步驟二：</td><td>注冊時選擇地區爲香港（身份證随便填）</td></tr><tr><td>&nbsp;</td><td><a href=\"http://www.ali213.net/showbigpic.html?http://images.ali213.net/picfile/pic/2015/03/16/20150316100249781.jpg\" target=\"_blank\"><img src=\"http://images.ali213.net/picfile/pic/2015/03/16/584_20150316100249781.jpg\"></a></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>步驟三：</td><td>注冊完帳号通過郵箱驗證之後登入帳号管理選擇上方遊戲管理菜單，點啓動序号激活遊戲。</td></tr><tr><td>&nbsp;</td><td><a href=\"http://www.ali213.net/showbigpic.html?http://images.ali213.net/picfile/pic/2015/03/16/20150316100252138.jpg\" target=\"_blank\"><img src=\"http://images.ali213.net/picfile/pic/2015/03/16/584_20150316100252138.jpg\"></a></td></tr><tr><td>&nbsp;</td><td><a href=\"http://www.ali213.net/showbigpic.html?http://images.ali213.net/picfile/pic/2015/03/16/2015031610025566.jpg\" target=\"_blank\"><img src=\"http://images.ali213.net/picfile/pic/2015/03/16/584_2015031610025566.jpg\"></a></td></tr></tbody></table>', '1', '1002', null, '2018-04-08 19:16:06', '2018-06-30 20:25:27');
INSERT INTO `mer_tb` VALUES ('5', '1', '5.00', '2', null, '幻想三国志2 PC版中文', ' 上古时期，黄帝蚩尤大战涿鹿。当时，蚩尤得风伯雨师、魍魉魑魅、山精鬼怪之助，黄帝则有风后力牧、应龙女魃、天女之辅。此役风云变色、石破天惊。', 'http://img.fhyx.com/uploads/2017/12/21/2017122124058472.jpg', 'PC', '2', '29.00', '2.90', '0.10', '0', '4', '0.00', '0', '<table border=\"0\" cellpadding=\"1\" cellspacing=\"1\"><tbody><tr><td>产品参数：</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>游戏名称：幻想三国志2</td><td>游戏类型：角色扮演类</td><td>制作公司：宇峻奥汀</td><td>发售时间：2005年07月31日</td></tr><tr><td>游戏版本：数字版</td><td>语言版本：简体中文</td><td>发行公司：寰宇之星</td><td>游戏大小：&nbsp;2.43G</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr></tbody></table><p><a href=\"http://www.fhyx.com/item/2214.html#attrone=5\" target=\"_blank\"><img src=\"http://img.fhyx.com/uploads/contents/2018/04/25/20180425943183.jpg\" title=\"点击查看大图\"></a>&nbsp;&nbsp;</p><table border=\"0\" cellpadding=\"1\" cellspacing=\"1\"><tbody><tr><td>&nbsp;</td><td colspan=\"2\">&nbsp;</td></tr><tr><td><strong>特别说明：</strong></td><td colspan=\"2\">凤凰游戏商城销售的《幻想三国志1-4外》数字版，不适用于腾讯WEGAME和方块游戏平台。仅限于激活隐藏角色。</td></tr><tr><td>&nbsp;</td><td colspan=\"2\">&nbsp;</td></tr><tr><td><strong>数字版：</strong></td><td colspan=\"2\">格式为“&nbsp;AAAAA&nbsp;-BBBBB-CCCCC-DDDDD-EEEEE”，共25位，由字母和数字组成。</td></tr><tr><td>&nbsp;</td><td colspan=\"2\">激活方式为登录激活：一个激活码支持且仅支持绑定一个账号。</td></tr><tr><td>&nbsp;</td><td colspan=\"2\">&nbsp;</td></tr><tr><td><strong>win10兼容方法：</strong></td><td colspan=\"2\">1：以[视窗模式]启动游戏</td></tr><tr><td>&nbsp;</td><td colspan=\"2\">:2：设定相容性</td></tr><tr><td>&nbsp;</td><td colspan=\"2\">&nbsp; &nbsp;- 设定步骤: 点选档案.\\Game\\FS.exe 右键→ 内容→ 相容性/设定/减少的色彩模式→ 16位元</td></tr><tr><td>&nbsp;</td><td colspan=\"2\" rowspan=\"1\"><p><img src=\"http://img.fhyx.com/uploads/contents/2018/02/09/2018020913007554.png\" title=\"点击查看大图\"></p></td></tr></tbody></table><table border=\"0\" cellpadding=\"1\" cellspacing=\"1\"><tbody><tr><td colspan=\"2\">&nbsp;</td></tr><tr><td colspan=\"2\"><strong>购买《幻想三国志2》数字版，免费赠送独立角色“楚歌”&nbsp;</strong></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>赠送人物使用方法：</td><td>您可以使用《幻想三国志2》的激活码去《幻想三国志5》游戏中解锁且仅解锁独立角色“楚歌” 。</td></tr><tr><td>&nbsp;</td><td>《幻想三国志5》游戏暂未上市，具体时间请前往留意官网咨询： http://fs5.fhyx.com/</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr></tbody></table><table border=\"0\" cellpadding=\"1\" cellspacing=\"1\"><tbody><tr></tr></tbody></table><table border=\"0\" cellpadding=\"1\" cellspacing=\"1\"><tbody><tr><td>隐藏人物：</td><td>&nbsp;</td></tr><tr><td colspan=\"1\" rowspan=\"22\"><p><img src=\"http://img.fhyx.com/uploads/contents/2018/02/05/20180205435004.jpg\" title=\"点击查看大图\"></p></td><td>.&nbsp; &nbsp;&nbsp;姓名：楚歌</td></tr><tr><td>.&nbsp; &nbsp;&nbsp;字号：羽凤&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;</td></tr><tr><td>.&nbsp; &nbsp;&nbsp;年龄：18岁</td></tr><tr><td>.&nbsp; &nbsp;&nbsp;前世：应龙</td></tr><tr><td>.&nbsp; &nbsp;&nbsp;生日：3月31日</td></tr><tr><td>.&nbsp; &nbsp;&nbsp;身高：175CM</td></tr><tr><td>.&nbsp; &nbsp;&nbsp;喜好：赚钱</td></tr><tr><td>.&nbsp; &nbsp;&nbsp;代表台词：黄泉海畔，那是我们分离的地方。我想在那里再次等她。这次，就算躯体腐朽，灵魂飘散，我也不会离开半步&nbsp;</td></tr><tr><td>.&nbsp; &nbsp;&nbsp;人物介绍：字君河，因祸避居西凉召德村。昏迷三载，方自醒转，记忆全失。个性放荡不羁、圆滑乐观，立志成为天下第一有钱人。<br>.&nbsp; &nbsp;&nbsp;为应龙转世，与海棠（女魃转世）有着极深情谊。本为汉少帝刘辩，受董卓迫害，由真正的楚谦之子楚君河替死，逃到西凉。</td></tr><tr></tr></tbody></table><table border=\"0\" cellpadding=\"1\" cellspacing=\"1\"><tbody><tr></tr><tr><td><strong>配置要求：</strong></td><td>&nbsp;</td></tr><tr><td>&nbsp;</td><td>系统：Windows XP/Vista/7/10</td></tr><tr><td>&nbsp;</td><td>Cpu：intel pentium(R)Dual-Core CPU E5200 2.50GHZ或AMD同性能处理器（双核）</td></tr><tr><td>&nbsp;</td><td>内存：2 GB</td></tr><tr><td>&nbsp;</td><td>显卡：NVIDIA GeForce 8600GT或AMD同性能显卡（显存512M）</td></tr><tr><td>&nbsp;</td><td>硬盘：20G</td></tr><tr><td>&nbsp;</td></tr></tbody></table><table border=\"0\" cellpadding=\"1\" cellspacing=\"1\"><tbody><tr><td>游戏介绍：</td></tr><tr><td>&nbsp;上古时期，黄帝蚩尤大战涿鹿。当时，蚩尤得风伯雨师、魍魉魑魅、山精鬼怪之助，黄帝则有风后力牧、应龙女魃、天女之辅。此役风云变色、石破天惊。<br>在忠臣风后、力牧、大将应龙、与义女女魃的相助下，涿鹿之战，黄帝终于获得了最后的胜利。然而，在消灭蚩尤势力的同时，黄帝也失去了忠臣风后与挚爱凤曦，而大将应龙与义女女魃，也因双双感染了人间浊气而从此消失天界。<br>时光流转，为了一偿未了的心愿，黄帝决心投身地界。在转生之前，他来到天界的花园悬圃，为应龙与女魃做了一件事。<br>东汉末年，乱象纷起，汉室存亡生死一线。莲无神树下，云空和道天两仙，以汉朝兴衰、天道之向为赌注，继续两人未完的棋局。<br>荧惑守心、天象异变，一个关于三龙宝器的传说迅速在朝野传开：当年承天命而来，协助黄帝打败蚩尤的神兽应龙，战后因感染邪气而堕入人间，临死前其指爪化为青龙剑、眼化为赤龙珠、心则化为历代传承的玉龙玺。在此乱世中，传说只要能拥有其中一样，便能雄霸一方成为王者，若三者龙气合一，则天下尽得、心愿尽遂、并可拥有扭转天道之能力。<br>公元一九六年，西凉召德村。<br>楚歌与好友韩靖、心仪之人沉嫣，他们如同一般年轻男女，对未来有着美好的憧憬，并一心期待着前往京城发展、为此乱世尽一份心力。然而，一场意外却让他们遭遇了生离死别的命运。<br>在陌生的长安，楚歌无意中解救了一名身份不凡的少女‧汉朝兴平公主，从此卷入了拥汉室之护凰血族、及反汉室自立为王之暗行七众两势力的漩涡中。随后，青楼名伶海棠、曹操麾下夏侯翎、书生道士杜晏、鬼族之女蜜儿，一一因命运的相连而出现在楚歌的面前。</td></tr></tbody></table><table border=\"0\" cellpadding=\"1\" cellspacing=\"1\"><tbody><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>游戏截图：</td><td>&nbsp;</td></tr><tr><td>&nbsp;</td><td><p><img src=\"http://img.fhyx.com/uploads/contents/2017/12/18/2017121841654282.jpg\"></p></td></tr><tr><td>&nbsp;</td><td><img src=\"http://img.fhyx.com/uploads/contents/2017/12/18/2017121841651725.jpg\" title=\"点击查看大图\"></td></tr><tr><td>&nbsp;</td><td><img src=\"http://img.fhyx.com/uploads/contents/2017/12/18/2017121841653607.jpg\" title=\"点击查看大图\"></td></tr></tbody></table>', '<div><div>最低配置</div><p>系统：&nbsp;WinXP/2003/Vista/7</p><p>Cpu：无</p><p>内存：无</p><p>显卡：无</p><p>硬盘：&nbsp;2.4G</p></div><div><div>推荐配置</div><p>系统：&nbsp;WinXP/2003/Vista/7</p><p>Cpu：无</p><p>内存：无</p><p>显卡：无</p><p>硬盘：&nbsp;2.4G</p></div>', null, '1', '1004', null, '2018-06-24 14:24:08', '2018-07-06 11:57:18');
INSERT INTO `mer_tb` VALUES ('6', '1', '5.00', '3', '2018-07-28 00:00:00', '极品飞车17：最高通缉PC版', '极品飞车17', 'http://img.fhyx.com/uploads/2016/12/16/2016121631059549.gif', 'PC', '2', '139.00', '79.23', '0.57', '0', '0', '0.00', '0', '<table border=\"0\" cellpadding=\"1\" cellspacing=\"1\"><tbody><tr><td>产品参数：</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>游戏名称：极品飞车：最高通缉</td><td>游戏类型：赛车竞速（RAC）</td><td>制作公司：美国艺电（EA）</td><td>发售日期：2012年10月30日</td></tr><tr><td>使用平台：Origin &nbsp;</td><td>语言版本：英文</td><td>发行公司：EA Canada</td><td>游戏平台：PC</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr></tbody></table><table border=\"0\" cellpadding=\"1\" cellspacing=\"1\"><tbody><tr><td colspan=\"2\"><strong>注意：仅限18岁以上玩家购买和使用！本产品语言暂不支持中文！</strong></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td><strong>全球版key：</strong></td><td>适用于全球地区使用。</td></tr><tr><td>&nbsp;</td><td>格式为“AAAA-BBBB-CCCC-DDDD-EEEE”共20位，由数字和字母组成。</td></tr><tr><td>&nbsp;</td><td>使用说明：登录需绑定游戏的Origin账号，进入游戏库，在“兑换产品代码”对话框中输入激活码。</td></tr></tbody></table><table border=\"0\" cellpadding=\"1\" cellspacing=\"1\"><tbody><tr><td>&nbsp;</td></tr><tr><td><strong>售后说明：</strong></td></tr><tr><td>1：CDkey与礼物类商品，一经使用将永久与您的Steam账号绑定，无法剥离，无法二次销售，因此售出的商品不再提供任何退换货服务（包括但不限于买错游戏，配置不符，网络问题，游戏限区等）。如您对此持有异议，请勿购买。强烈建议您在购买游戏之前，先了解游戏的配置要求，语言版本，使用地区等基本信息。</td></tr><tr><td>2：国区礼物为Steam商店内出售的正版游戏礼物（Gift），国区版仅限中国大陆玩家激活使用，非中国大陆玩家请勿购买！国区礼物非激活码key，非实物盒装光盘。付款后，系统将以邮件的形式，将礼物链接发送至您的订单和接收邮箱内。请自行领取并使用。</td></tr><tr><td>3：如玩家采用第三方软件，作弊，违规等非正常游戏手段（包括但不限于使用外挂，篡改游戏数据，打非官方补丁等）导致账户异常或者封禁，后果需玩家自行承担。</td></tr><tr><td>4：商城提供免费的正版游戏下载和技术顾问支持，如需咨询，请提交至<strong>客服邮箱：kf@fhyx.hk</strong></td></tr><tr><td>&nbsp;</td></tr><tr></tr></tbody></table><table border=\"0\" cellpadding=\"1\" cellspacing=\"1\" height=\"106\" width=\"750\"><tbody><tr><td>游戏截图</td><td>&nbsp;</td></tr><tr><td>&nbsp;</td><td><p><img src=\"http://img.fhyx.com/uploads/contents/2016/04/29/201604296163640.jpg\" title=\"点击查看大图\"></p></td></tr><tr><td>&nbsp;</td><td><p><img src=\"http://img.fhyx.com/uploads/contents/2016/04/29/2016042961637260.jpg\" title=\"点击查看大图\"></p></td></tr><tr><td>&nbsp;</td><td><p><img src=\"http://img.fhyx.com/uploads/contents/2016/04/29/2016042961636536.jpg\" title=\"点击查看大图\"></p></td></tr></tbody></table>', '<p>无&nbsp;&nbsp;<br></p>', '<p><br></p>', '0', '1003', null, '2018-06-24 14:34:10', '2018-06-27 17:30:08');
INSERT INTO `mer_tb` VALUES ('7', '2', '5.00', '2', null, '幻想三国志2 PC版中文', ' 上古时期，黄帝蚩尤大战涿鹿。当时，蚩尤得风伯雨师、魍魉魑魅、山精鬼怪之助，黄帝则有风后力牧、应龙女魃、天女之辅。此役风云变色、石破天惊。', 'http://img.fhyx.com/uploads/2017/12/21/2017122124058472.jpg', 'PC', '2', '29.00', '2.90', '0.10', '0', '0', '0.00', '0', '<table border=\"0\" cellpadding=\"1\" cellspacing=\"1\"><tbody><tr><td>产品参数：</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>游戏名称：幻想三国志2</td><td>游戏类型：角色扮演类</td><td>制作公司：宇峻奥汀</td><td>发售时间：2005年07月31日</td></tr><tr><td>游戏版本：数字版</td><td>语言版本：简体中文</td><td>发行公司：寰宇之星</td><td>游戏大小：&nbsp;2.43G</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr></tbody></table><p><a href=\"http://www.fhyx.com/item/2214.html#attrone=5\" target=\"_blank\"><img src=\"http://img.fhyx.com/uploads/contents/2018/04/25/20180425943183.jpg\" title=\"点击查看大图\"></a>&nbsp;&nbsp;</p><table border=\"0\" cellpadding=\"1\" cellspacing=\"1\"><tbody><tr><td>&nbsp;</td><td colspan=\"2\">&nbsp;</td></tr><tr><td><strong>特别说明：</strong></td><td colspan=\"2\">凤凰游戏商城销售的《幻想三国志1-4外》数字版，不适用于腾讯WEGAME和方块游戏平台。仅限于激活隐藏角色。</td></tr><tr><td>&nbsp;</td><td colspan=\"2\">&nbsp;</td></tr><tr><td><strong>数字版：</strong></td><td colspan=\"2\">格式为“&nbsp;AAAAA&nbsp;-BBBBB-CCCCC-DDDDD-EEEEE”，共25位，由字母和数字组成。</td></tr><tr><td>&nbsp;</td><td colspan=\"2\">激活方式为登录激活：一个激活码支持且仅支持绑定一个账号。</td></tr><tr><td>&nbsp;</td><td colspan=\"2\">&nbsp;</td></tr><tr><td><strong>win10兼容方法：</strong></td><td colspan=\"2\">1：以[视窗模式]启动游戏</td></tr><tr><td>&nbsp;</td><td colspan=\"2\">:2：设定相容性</td></tr><tr><td>&nbsp;</td><td colspan=\"2\">&nbsp; &nbsp;- 设定步骤: 点选档案.\\Game\\FS.exe 右键→ 内容→ 相容性/设定/减少的色彩模式→ 16位元</td></tr><tr><td>&nbsp;</td><td colspan=\"2\" rowspan=\"1\"><p><img src=\"http://img.fhyx.com/uploads/contents/2018/02/09/2018020913007554.png\" title=\"点击查看大图\"></p></td></tr></tbody></table><table border=\"0\" cellpadding=\"1\" cellspacing=\"1\"><tbody><tr><td colspan=\"2\">&nbsp;</td></tr><tr><td colspan=\"2\"><strong>购买《幻想三国志2》数字版，免费赠送独立角色“楚歌”&nbsp;</strong></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>赠送人物使用方法：</td><td>您可以使用《幻想三国志2》的激活码去《幻想三国志5》游戏中解锁且仅解锁独立角色“楚歌” 。</td></tr><tr><td>&nbsp;</td><td>《幻想三国志5》游戏暂未上市，具体时间请前往留意官网咨询： http://fs5.fhyx.com/</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr></tbody></table><table border=\"0\" cellpadding=\"1\" cellspacing=\"1\"><tbody><tr></tr></tbody></table><table border=\"0\" cellpadding=\"1\" cellspacing=\"1\"><tbody><tr><td>隐藏人物：</td><td>&nbsp;</td></tr><tr><td colspan=\"1\" rowspan=\"22\"><p><img src=\"http://img.fhyx.com/uploads/contents/2018/02/05/20180205435004.jpg\" title=\"点击查看大图\"></p></td><td>.&nbsp; &nbsp;&nbsp;姓名：楚歌</td></tr><tr><td>.&nbsp; &nbsp;&nbsp;字号：羽凤&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;</td></tr><tr><td>.&nbsp; &nbsp;&nbsp;年龄：18岁</td></tr><tr><td>.&nbsp; &nbsp;&nbsp;前世：应龙</td></tr><tr><td>.&nbsp; &nbsp;&nbsp;生日：3月31日</td></tr><tr><td>.&nbsp; &nbsp;&nbsp;身高：175CM</td></tr><tr><td>.&nbsp; &nbsp;&nbsp;喜好：赚钱</td></tr><tr><td>.&nbsp; &nbsp;&nbsp;代表台词：黄泉海畔，那是我们分离的地方。我想在那里再次等她。这次，就算躯体腐朽，灵魂飘散，我也不会离开半步&nbsp;</td></tr><tr><td>.&nbsp; &nbsp;&nbsp;人物介绍：字君河，因祸避居西凉召德村。昏迷三载，方自醒转，记忆全失。个性放荡不羁、圆滑乐观，立志成为天下第一有钱人。<br>.&nbsp; &nbsp;&nbsp;为应龙转世，与海棠（女魃转世）有着极深情谊。本为汉少帝刘辩，受董卓迫害，由真正的楚谦之子楚君河替死，逃到西凉。</td></tr><tr></tr></tbody></table><table border=\"0\" cellpadding=\"1\" cellspacing=\"1\"><tbody><tr></tr><tr><td><strong>配置要求：</strong></td><td>&nbsp;</td></tr><tr><td>&nbsp;</td><td>系统：Windows XP/Vista/7/10</td></tr><tr><td>&nbsp;</td><td>Cpu：intel pentium(R)Dual-Core CPU E5200 2.50GHZ或AMD同性能处理器（双核）</td></tr><tr><td>&nbsp;</td><td>内存：2 GB</td></tr><tr><td>&nbsp;</td><td>显卡：NVIDIA GeForce 8600GT或AMD同性能显卡（显存512M）</td></tr><tr><td>&nbsp;</td><td>硬盘：20G</td></tr><tr><td>&nbsp;</td></tr></tbody></table><table border=\"0\" cellpadding=\"1\" cellspacing=\"1\"><tbody><tr><td>游戏介绍：</td></tr><tr><td>&nbsp;上古时期，黄帝蚩尤大战涿鹿。当时，蚩尤得风伯雨师、魍魉魑魅、山精鬼怪之助，黄帝则有风后力牧、应龙女魃、天女之辅。此役风云变色、石破天惊。<br>在忠臣风后、力牧、大将应龙、与义女女魃的相助下，涿鹿之战，黄帝终于获得了最后的胜利。然而，在消灭蚩尤势力的同时，黄帝也失去了忠臣风后与挚爱凤曦，而大将应龙与义女女魃，也因双双感染了人间浊气而从此消失天界。<br>时光流转，为了一偿未了的心愿，黄帝决心投身地界。在转生之前，他来到天界的花园悬圃，为应龙与女魃做了一件事。<br>东汉末年，乱象纷起，汉室存亡生死一线。莲无神树下，云空和道天两仙，以汉朝兴衰、天道之向为赌注，继续两人未完的棋局。<br>荧惑守心、天象异变，一个关于三龙宝器的传说迅速在朝野传开：当年承天命而来，协助黄帝打败蚩尤的神兽应龙，战后因感染邪气而堕入人间，临死前其指爪化为青龙剑、眼化为赤龙珠、心则化为历代传承的玉龙玺。在此乱世中，传说只要能拥有其中一样，便能雄霸一方成为王者，若三者龙气合一，则天下尽得、心愿尽遂、并可拥有扭转天道之能力。<br>公元一九六年，西凉召德村。<br>楚歌与好友韩靖、心仪之人沉嫣，他们如同一般年轻男女，对未来有着美好的憧憬，并一心期待着前往京城发展、为此乱世尽一份心力。然而，一场意外却让他们遭遇了生离死别的命运。<br>在陌生的长安，楚歌无意中解救了一名身份不凡的少女‧汉朝兴平公主，从此卷入了拥汉室之护凰血族、及反汉室自立为王之暗行七众两势力的漩涡中。随后，青楼名伶海棠、曹操麾下夏侯翎、书生道士杜晏、鬼族之女蜜儿，一一因命运的相连而出现在楚歌的面前。</td></tr></tbody></table><table border=\"0\" cellpadding=\"1\" cellspacing=\"1\"><tbody><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>游戏截图：</td><td>&nbsp;</td></tr><tr><td>&nbsp;</td><td><p><img src=\"http://img.fhyx.com/uploads/contents/2017/12/18/2017121841654282.jpg\"></p></td></tr><tr><td>&nbsp;</td><td><img src=\"http://img.fhyx.com/uploads/contents/2017/12/18/2017121841651725.jpg\" title=\"点击查看大图\"></td></tr><tr><td>&nbsp;</td><td><img src=\"http://img.fhyx.com/uploads/contents/2017/12/18/2017121841653607.jpg\" title=\"点击查看大图\"></td></tr></tbody></table>', '<div><div>最低配置</div><p>系统：&nbsp;WinXP/2003/Vista/7</p><p>Cpu：无</p><p>内存：无</p><p>显卡：无</p><p>硬盘：&nbsp;2.4G</p></div><div><div>推荐配置</div><p>系统：&nbsp;WinXP/2003/Vista/7</p><p>Cpu：无</p><p>内存：无</p><p>显卡：无</p><p>硬盘：&nbsp;2.4G</p></div>', null, '0', '1004', '1004', '2018-07-06 11:57:18', '2018-07-10 16:35:16');
INSERT INTO `mer_tb` VALUES ('8', '2', '5.00', '1', null, '科林麦克雷拉力赛之尘埃3 PC版', '语言：英文，使用平台：Steam，激活码用于绑定账户。注意：仅限18岁以上玩家购买和使用！', 'http://img.fhyx.com/uploads/2017/01/19/2017011921538261.gif', 'pc端1', '0', '179.00', '35.80', '0.20', '0', '4', '4.00', '1', '<table border=\"0\" cellpadding=\"1\" cellspacing=\"1\"><tbody><tr><td>产品参数：</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>游戏名称：科林麦克雷拉力赛之尘埃3</td><td>游戏类型：赛车竞速RAC</td><td>制作公司：Codemasters</td><td>发售日期：2011年05月24日</td></tr><tr><td>使用平台：Steam</td><td>语言版本：英文</td><td>发行公司：Codemasters</td><td>客服邮箱：kf@fhyx.hk</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr></tbody></table><table border=\"0\" cellpadding=\"1\" cellspacing=\"1\"><tbody><tr><td><table border=\"0\" cellpadding=\"1\" cellspacing=\"1\"><tbody><tr><td colspan=\"2\"><strong>注意：仅限18岁以上玩家购买和使用！语言暂不支持中文！</strong></td></tr></tbody></table></td></tr></tbody></table><table border=\"0\" cellpadding=\"1\" cellspacing=\"1\"><tbody><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td><strong>全球版key：</strong></td><td>DiRT 3 - Complete Edition</td></tr><tr><td>&nbsp;</td><td>适用于全球地区使用。</td></tr><tr><td>&nbsp;</td><td>格式为“AAAAA-BBBBB-CCCCC”共15位，由数字和字母组成。</td></tr><tr><td>&nbsp;</td><td>使用说明：登录需绑定游戏的Steam账号，进入游戏库，在“产品代码”对话框中输入激活码。</td></tr></tbody></table><table border=\"0\" cellpadding=\"1\" cellspacing=\"1\"><tbody><tr><td>&nbsp;</td></tr><tr><td><strong>售后说明：</strong></td></tr><tr><td>1：CDkey与礼物类商品，一经使用将永久与您的Steam账号绑定，无法剥离，无法二次销售，因此售出的商品不再提供任何退换货服务（包括但不限于买错游戏，配置不符，网络问题，游戏限区等）。如您对此持有异议，请勿购买。强烈建议您在购买游戏之前，先了解游戏的配置要求，语言版本，使用地区等基本信息。</td></tr><tr><td>2：国区礼物为Steam商店内出售的正版游戏礼物（Gift），国区版仅限中国大陆玩家激活使用，非中国大陆玩家请勿购买！国区礼物非激活码key，非实物盒装光盘。付款后，系统将以邮件的形式，将礼物链接发送至您的订单和接收邮箱内。请自行领取并使用。</td></tr><tr><td>3：如玩家采用第三方软件，作弊，违规等非正常游戏手段（包括但不限于使用外挂，篡改游戏数据，打非官方补丁等）导致账户异常或者封禁，后果需玩家自行承担。</td></tr><tr><td>4：商城提供免费的正版游戏下载和技术顾问支持，如需咨询，请提交至<strong>客服邮箱：kf@fhyx.hk</strong></td></tr><tr><td>&nbsp;</td></tr></tbody></table><table border=\"0\" cellpadding=\"1\" cellspacing=\"1\"><tbody><tr><td>游戏背景：</td><td>&nbsp;</td></tr><tr><td><p>&nbsp; &nbsp; &nbsp; 游戏中将带有全新的生涯模式，强调了团队合作的元素。你将从上世纪90年代开始，以“私人赛车手”来参加一系列赛事。融合众多现代和经典赛车，还有Morris Cooper这样长相古怪的传奇赛车和诸如super buggies和TRAID trucks之类的车。所有的赛车模型会比以往的作品更加细致。《尘埃3》带有从挪威的阿斯彭到蒙特卡罗到非洲平原的超过100条赛道。</p></td><td>&nbsp;</td></tr></tbody></table><table border=\"0\" cellpadding=\"1\" cellspacing=\"1\"><tbody><tr><td>&nbsp;</td></tr></tbody></table><table border=\"0\" cellpadding=\"1\" cellspacing=\"1\"><tbody><tr><td>游戏截图：</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr><tr><td>&nbsp;</td><td><p><img src=\"http://img.fhyx.com/uploads/contents/2017/01/13/201701133574084.jpg\" title=\"点击查看大图\"></p></td></tr><tr><td>&nbsp;</td><td><p><img src=\"http://img.fhyx.com/uploads/contents/2017/01/13/2017011335740953.jpg\"></p></td></tr><tr><td>&nbsp;</td><td><p><img src=\"http://img.fhyx.com/uploads/contents/2017/01/13/2017011335740656.jpg\" title=\"点击查看大图\"></p></td></tr></tbody></table>', '<div><div>最低配置</div><p>系统：&nbsp;WinXP/2003/Vista/7</p><p>Cpu：&nbsp;AMD Athlon 64 X2 2.8GHz / Intel PentiumD 2.8Ghz</p><p>内存：&nbsp;2GB</p><p>显卡：&nbsp;AMD Radeon HD 2000 / NVIDIA GeForce 8000</p><p>硬盘：&nbsp;5.88 GB</p></div><div><div>推荐配置</div><p>系统：&nbsp;WinXP/2003/Vista/7</p><p>Cpu：&nbsp;AMD Phenom II / Intel Core i7</p><p>内存：&nbsp;3GB</p><p>显卡：&nbsp;AMD Radeon HD 6000系列</p><p>硬盘：&nbsp;5.88 GB</p></div>', '<p><iframe frameborder=\"0\" scrolling=\"yes\" name=\"main\" src=\"http://ali213.fhyx.hk/help/steam.html\"></iframe>&nbsp;&nbsp;<br></p>', '1', '1003', '1004', '2018-07-06 14:49:06', '2018-07-11 14:54:22');
INSERT INTO `mer_tb` VALUES ('9', '2', '5.00', '1', null, '暗黑破坏神3 PC版 全球版', '暗黑破壞神3 PC版 全球版', 'http://img.fhyx.com/uploads/2016/12/16/2016121643141739.jpg', '', '2', '298.00', '238.40', '0.80', '0', '5', '4.00', '1', '<table border=\"0\" cellpadding=\"1\" cellspacing=\"1\"><tbody><tr><td>産品參數：</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>遊戲名稱：暗黑破壞神3</td><td>遊戲類型：角色扮演RPG</td><td>制作公司：Blizzard</td><td>發售時間：2012年05月15日</td></tr><tr><td>使用平台：暴雪戰網</td><td>語言版本：繁體中文</td><td>發行公司：Blizzard</td><td>客服郵箱：kf@fhyx.hk</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr></tbody></table><table border=\"0\" cellpadding=\"1\" cellspacing=\"1\"><tbody><tr><td colspan=\"2\"><strong>注意：僅限18歲以上玩家購買和使用！全球版Key國區無法使用，激活平台爲暴雪戰網。（無法用于STEAM平台）</strong></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td><strong>暗黑破壞神3主程序：</strong></td><td>支持全球地區使用（除國服）。</td></tr><tr><td>&nbsp;</td><td>激活平台爲暴雪戰網。（無法用于STEAM平台）</td></tr><tr><td>&nbsp;</td><td>格式爲“AAAAAA-BBBB-CCCCCC-DDDD-EEEEEE”共26位，由數字和字母組成。 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td><strong>死神之鐮資料片：</strong></td><td>支持全球地區使用（除國服）。</td></tr><tr><td>&nbsp;</td><td>激活平台爲暴雪戰網。（無法用于STEAM平台）</td></tr><tr><td>&nbsp;</td><td>該内容需要在戰網平台擁有基礎遊戲 Diablo III 才能運行，無法單獨使用。</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td><strong>暗黑破壞神3合輯：</strong></td><td>diablo 3 battlechest</td></tr><tr><td>&nbsp;</td><td>包含2件物品：暗黑破壞神3主程序，死神之鐮資料片</td></tr><tr><td>&nbsp;</td><td>支持全球地區使用（除國服）。</td></tr><tr><td>&nbsp;</td><td>激活平台爲暴雪戰網。（無法用于STEAM平台）</td></tr><tr><td colspan=\"2\">&nbsp;</td></tr></tbody></table><table border=\"0\" cellpadding=\"1\" cellspacing=\"1\"><tbody><tr><td><strong>售後說明：</strong></td></tr><tr><td>1：CDkey與禮物類商品，一經使用将永久與您的Steam賬号綁定，無法剝離，無法二次銷售，因此售出的商品不再提供任何退換貨服務（包括但不限于買錯遊戲，配置不符，網絡問題，遊戲限區等）。如您對此持有異議，請勿購買。強烈建議您在購買遊戲之前，先了解遊戲的配置要求，語言版本，使用地區等基本信息。</td></tr><tr><td>2：國區禮物爲Steam商店内出售的正版遊戲禮物（Gift），國區版僅限中國大陸玩家激活使用，非中國大陸玩家請勿購買！國區禮物非激活碼key，非實物盒裝光盤。付款後，系統将以郵件的形式，将禮物鏈接發送至您的訂單和接收郵箱内。請自行領取并使用。</td></tr><tr><td>3：如玩家采用第三方軟件，作弊，違規等非正常遊戲手段（包括但不限于使用外挂，篡改遊戲數據，打非官方補丁等）導緻賬戶異常或者封禁，後果需玩家自行承擔。</td></tr><tr><td>4：商城提供免費的正版遊戲下載和技術顧問支持，如需咨詢，請提交至<strong>客服郵箱：kf@fhyx.hk</strong></td></tr><tr><td>&nbsp;</td></tr><tr></tr></tbody></table><table border=\"0\" cellpadding=\"1\" cellspacing=\"1\"><tbody><tr><td>遊戲背景：</td></tr><tr><td>&nbsp; &nbsp; &nbsp; &nbsp;《暗黑破壞神3》是著名動作角色扮演遊戲《暗黑破壞神2》的續作，于2008年6月28日在法國巴黎舉行的2008Blizzard全球邀請賽中公布研發暗黑破壞神 III及播放遊戲試玩畫面，已經知道有野蠻人、巫醫、魔法師、武僧四個職業角色。目前以Windows和Mac OS X爲支援平台。發行日期尚未公布。<br><br>&nbsp; &nbsp; &nbsp; &nbsp;《暗黑破壞神3》将像前作一樣是一款動作角色扮演遊戲。雖然《暗黑破壞神3》會保留很多原版的遊戲特點，但它将着重在合作和小組配合上。《暗黑破壞神3》的專有引擎将使用Havok物理引擎來顯示，玩家可損壞遊戲環境。開發小組計劃支持多種不同的系統組合，而且不要求Windows Vista的DirectX 10。暴雪娛樂準備同時間出版Microsoft Windows和Mac OS X版本，目前不準備支持遊戲機平台。<br><br>&nbsp; &nbsp; &nbsp; &nbsp;多人遊戲将使用暴雪自有的Battle.net服務，許多爲《星際争霸2》而開發的新功能将同時間應用于《暗黑破壞神3》。玩家将可以随時參加和退出合夥遊戲。在前作裏，每個角色的性别是預定的。在《暗黑破壞神3》裏，玩家在建立人物時可以随意選擇一個角色的男性和女性。<br><br>&nbsp; &nbsp; &nbsp; &nbsp;新的任務系統将與随機布局發生器配合給遊戲帶來充分的重玩價值。随機遭遇系統将更加的鞏固其重玩價值。總的來說，遊戲世界将包含随機布局與靜态布局綜合特點。敵人将利用三維環境來決定其戰術，比如敵人可以從深處攀牆來攻擊玩家。</td></tr><tr><td>&nbsp;</td></tr></tbody></table><table border=\"0\" cellpadding=\"1\" cellspacing=\"1\"><tbody><tr><td>遊戲截圖：</td><td>&nbsp;</td></tr><tr><td>&nbsp;</td><td><p><img src=\"http://img.fhyx.com/uploads/contents/2015/01/07/2015010745728598.jpg\" title=\"点击查看大图\"></p></td></tr><tr><td>&nbsp;</td><td><p><img src=\"http://img.fhyx.com/uploads/contents/2015/01/07/2015010745729733.jpg\" title=\"点击查看大图\"></p></td></tr><tr><td>&nbsp;</td><td><p><img src=\"http://img.fhyx.com/uploads/contents/2015/01/07/2015010745730275.jpg\" title=\"点击查看大图\"></p></td></tr></tbody></table>', '<div><div>最低配置</div><p>系統：WinXP/2003/Vista/7</p><p>Cpu：Intel Pentium D / AMD AthlonTM 64 X2 4400+</p><p>内存：1.5GB</p><p>顯卡：NVIDIA GeForce 7800GT / ATI Radeon X1950 Pro</p><p>硬盤：7.6G</p></div><div><div>推薦配置</div><p>系統：WinXP/2003/Vista/7</p><p>Cpu：Intel Core 2 Duo / AMD AthlonTM 64 X2 5600+</p><p>内存：2GB</p><p>顯卡：NVIDIA GeForce 260 / ATI Radeon 4870</p><p>硬盤：7.6G</p></div><p><br></p>', '<table border=\"0\" cellpadding=\"1\" cellspacing=\"1\"><tbody><tr><td>步驟一：</td><td>進入戰網進行注冊賬号</td></tr><tr><td>&nbsp;</td><td><p>戰網注冊地址：</p><p>https://tw.battle.net/account/creation/tos.html?style=LOBBY&amp;theme&amp;country=TW</p></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>&nbsp;</td><td><a href=\"http://www.ali213.net/showbigpic.html?http://images.ali213.net/picfile/pic/2015/03/16/20150316100245932.jpg\" target=\"_blank\"><img src=\"http://images.ali213.net/picfile/pic/2015/03/16/584_20150316100245932.jpg\"></a></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>步驟二：</td><td>注冊時選擇地區爲香港（身份證随便填）</td></tr><tr><td>&nbsp;</td><td><a href=\"http://www.ali213.net/showbigpic.html?http://images.ali213.net/picfile/pic/2015/03/16/20150316100249781.jpg\" target=\"_blank\"><img src=\"http://images.ali213.net/picfile/pic/2015/03/16/584_20150316100249781.jpg\"></a></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>步驟三：</td><td>注冊完帳号通過郵箱驗證之後登入帳号管理選擇上方遊戲管理菜單，點啓動序号激活遊戲。</td></tr><tr><td>&nbsp;</td><td><a href=\"http://www.ali213.net/showbigpic.html?http://images.ali213.net/picfile/pic/2015/03/16/20150316100252138.jpg\" target=\"_blank\"><img src=\"http://images.ali213.net/picfile/pic/2015/03/16/584_20150316100252138.jpg\"></a></td></tr><tr><td>&nbsp;</td><td><a href=\"http://www.ali213.net/showbigpic.html?http://images.ali213.net/picfile/pic/2015/03/16/2015031610025566.jpg\" target=\"_blank\"><img src=\"http://images.ali213.net/picfile/pic/2015/03/16/584_2015031610025566.jpg\"></a></td></tr></tbody></table>', '1', '1002', '1004', '2018-07-06 19:18:57', '2018-07-09 17:33:47');
INSERT INTO `mer_tb` VALUES ('10', '3', '6.22', '1', null, '三国志13 PC版中文', '三国志13 PC版中文', 'http://img.fhyx.com/uploads/2017/02/17/2017021745626168.jpg', 'Steam', '0', '259.00', '259.00', '1.00', '0', '9', '0.00', '0', '<table border=\"0\" cellpadding=\"1\" cellspacing=\"1\"><tbody><tr><td>产品参数：</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>游戏名称：三国志13</td><td>游戏类型：策略战棋SLG</td><td>制作公司：KOEI TECMO</td><td>发售时间：2016年01月28日</td></tr><tr><td>使用平台：Steam</td><td>语言版本：繁体中文</td><td>发行公司：KOEI TECMO</td><td>客服邮箱：kf@fhyx.hk</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr></tbody></table><table border=\"0\" cellpadding=\"1\" cellspacing=\"1\"><tbody><tr><td colspan=\"2\"><strong>注意：仅限16岁以上玩家购买和使用！</strong></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td><strong>三国志13台版key：</strong></td><td>含且仅含ROMANCE OF THE THREE KINGDOMS 13 /三国志13本体。</td></tr><tr><td>&nbsp;</td><td>适用于全球地区使用</td></tr><tr><td>&nbsp;</td><td>格式为“AAAAA-BBBBB-CCCCC”共15位，由数字和字母组成。</td></tr><tr><td>&nbsp;</td><td>使用说明：登录需绑定游戏的Steam账号，进入游戏库，在“产品代码”对话框中输入激活码。</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td><strong>三国志13PK威力加强版：</strong></td><td>含且仅含三国志13PK威力加强版，Romance Of Three Kingdom 13 PK DLC。</td></tr><tr><td>&nbsp;</td><td>该内容需要在Steam拥有基础游戏ROMANCE OF THE THREE KINGDOMS 13 / 三国志13 才能运行，无法单独使用。</td></tr><tr><td>&nbsp;</td><td>适用于全球地区使用</td></tr><tr><td>&nbsp;</td><td>如需购买，请点击&nbsp;&nbsp;<a href=\"http://www.fhyx.hk/item/1158.html\" target=\"_blank\"><img src=\"http://img.fhyx.com/uploads/contents/2017/02/16/201702169484332.jpg\" title=\"点击查看大图\"></a>&nbsp;前往购买</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td><strong>三国志13with PUK合辑：</strong></td><td>包含2件物品：三国志13 本体，三国志13PK威力加强版</td></tr><tr><td>&nbsp;</td><td>适用于全球地区使用</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td><strong>额外DLC：</strong></td><td>「英雄十三杰」「吕布讨伐战」「赤壁之战」</td></tr><tr><td>&nbsp;</td><td>该内容需要在Steam拥有基础游戏ROMANCE OF THE THREE KINGDOMS 13 / 三国志13 才能运行，无法单独使用。</td></tr></tbody></table><table border=\"0\" cellpadding=\"1\" cellspacing=\"1\"><tbody><tr><td>&nbsp;</td></tr><tr><td>游戏背景：</td></tr><tr><td>&nbsp;　&nbsp;&nbsp; 30 周年记念作品的最新作《三国志13》是以「这就是三国志。百花撩乱的英杰剧。」为概念，是一款在游戏中各种场面都可以感受到英杰们纵横无尽的精彩演出。各路英杰们不同的生存之道以及在战场中牵连起来的羁绊等，透过全武将游玩而描绘的更加鲜明的“群像剧”。水上战以及攻城战等的战略・战闘将会展开大规模的“壮阔” 战斗。以辽阔的中国大陆为舞台，数百名登场人物们为了扩大自己势力的“魄力” 等，都可更加强烈感受到《三国志》的世界。<p>　　玩家要扮演在《三国志》中活跃的一名英杰，以历史的主角来创造只属于自己的历史。与神算的军师跟无双的豪杰们时而合作时而竞争，玩家可自由的开创自己的故事。</p></td></tr><tr><td>&nbsp;</td></tr></tbody></table><table border=\"0\" cellpadding=\"1\" cellspacing=\"1\"><tbody><tr><td>游戏截图：</td><td>&nbsp;</td></tr><tr><td>&nbsp;</td><td><p><img src=\"http://img.fhyx.com/uploads/contents/2016/09/12/2016091231942808.jpg\" title=\"点击查看大图\"></p></td></tr><tr><td>&nbsp;</td><td><p><img src=\"http://img.fhyx.com/uploads/contents/2016/09/12/2016091231942900.jpg\" title=\"点击查看大图\"></p></td></tr><tr><td>&nbsp;</td><td><p><img src=\"http://img.fhyx.com/uploads/contents/2016/09/12/2016091231943169.jpg\" title=\"点击查看大图\"></p></td></tr></tbody></table>', '<div><div>最低配置</div><p>系统：&nbsp;Windows Vista / 7 / 8 / 8.1 / 10日语版</p><p>Cpu：&nbsp;Pentium4 1.6GHz以上</p><p>内存：&nbsp;1 GB以上</p><p>显卡：&nbsp;Intel HD Graphics 2000/Nvidia GeForce 7800/ATI Radeon X1300/</p><p>硬盘：&nbsp;7GB以上</p></div><div><div>推荐配置</div><p>系统：&nbsp;Windows Vista / 7 / 8 / 8.1 / 10日语版</p><p>Cpu：&nbsp;Core2 Duo 2.0GHz以上</p><p>内存：&nbsp;2 GB以上</p><p>显卡：&nbsp;GTX430</p><p>硬盘：&nbsp;7GB以上</p></div>', '<p><iframe frameborder=\"0\" scrolling=\"yes\" name=\"main\" src=\"http://ali213.fhyx.hk/help/steam.html\"></iframe>&nbsp;&nbsp;<br></p>', '1', '1003', '1004', '2018-07-10 16:35:56', '2018-07-10 17:24:46');

-- ----------------------------
-- Table structure for `notice_tb`
-- ----------------------------
DROP TABLE IF EXISTS `notice_tb`;
CREATE TABLE `notice_tb` (
  `notice_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '通知id',
  `region` tinyint(4) DEFAULT NULL COMMENT '范围，1全局，2个人',
  `type` tinyint(4) DEFAULT NULL COMMENT '类型，1系统消息，2申请新产品销售，3新增商品类型，4商品申请自营，5提现申请，6问题单反馈，7订单商品动态',
  `is_mer_dynamic` tinyint(4) DEFAULT NULL COMMENT '是否商品动态，默认0不是，1是',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `img_address` varchar(255) DEFAULT NULL COMMENT '图片地址',
  `content` longtext COMMENT '内容',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态，状态，0正常，1审核中，2申请成功，3申请失败',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `account_id` int(11) DEFAULT NULL COMMENT '账户id',
  `business_id` int(11) DEFAULT NULL COMMENT '业务id,外键',
  PRIMARY KEY (`notice_id`),
  KEY `INDEX_REGION` (`region`) USING BTREE,
  KEY `INDEX_TYPE` (`type`) USING BTREE,
  KEY `INDEX_ISMERDYNAMIC` (`is_mer_dynamic`) USING BTREE,
  KEY `INDEX_STATUS` (`status`) USING BTREE,
  KEY `INDEX_ACCOUNTID` (`account_id`) USING BTREE,
  KEY `INDEX_BUSINESSID` (`business_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1028 DEFAULT CHARSET=utf8 COMMENT='通知表';

-- ----------------------------
-- Records of notice_tb
-- ----------------------------
INSERT INTO `notice_tb` VALUES ('1000', '1', '1', '0', '下雨', 'http://p2bhwwngu.bkt.clouddn.com/o_1c5gaqqst68db2j16nno61q3cp.jpg', '明天会下雨，请做好准备！', null, '2018-04-11 17:33:15', '2018-06-27 10:55:23', null, null);
INSERT INTO `notice_tb` VALUES ('1001', '2', '1', '0', '下雨', 'http://p2bhwwngu.bkt.clouddn.com/o_1c5gaqqst68db2j16nno61q3cp.jpg', '明天会下雨，请做好准备！', '0', '2018-04-11 17:33:15', '2018-06-27 10:55:23', '1000', '1000');
INSERT INTO `notice_tb` VALUES ('1002', '2', '1', '0', '下雨', 'http://p2bhwwngu.bkt.clouddn.com/o_1c5gaqqst68db2j16nno61q3cp.jpg', '明天会下雨，请做好准备！', '0', '2018-04-11 17:33:15', '2018-06-27 10:55:23', '1001', '1000');
INSERT INTO `notice_tb` VALUES ('1003', '2', '1', '0', '下雨', 'http://p2bhwwngu.bkt.clouddn.com/o_1c5gaqqst68db2j16nno61q3cp.jpg', '明天会下雨，请做好准备！', '0', '2018-04-11 17:33:15', '2018-06-27 10:55:23', '1003', '1000');
INSERT INTO `notice_tb` VALUES ('1004', '2', '1', '0', '下雨', 'http://p2bhwwngu.bkt.clouddn.com/o_1c5gaqqst68db2j16nno61q3cp.jpg', '明天会下雨，请做好准备！', '0', '2018-04-11 17:33:15', '2018-06-27 10:55:23', '1004', '1000');
INSERT INTO `notice_tb` VALUES ('1005', '2', '1', '0', '下雨', 'http://p2bhwwngu.bkt.clouddn.com/o_1c5gaqqst68db2j16nno61q3cp.jpg', '明天会下雨，请做好准备！', '0', '2018-04-11 17:33:15', '2018-06-27 10:55:23', '1005', '1000');
INSERT INTO `notice_tb` VALUES ('1006', '1', '1', '0', '这个是干什么', 'http://p2bhwwngu.bkt.clouddn.com/o_1c5gaqqst68db2j16nno61q3cp.jpg', '测试555', null, '2018-06-27 13:53:27', '2018-06-27 13:54:21', null, null);
INSERT INTO `notice_tb` VALUES ('1007', '2', '1', '0', '这个是干什么', 'http://p2bhwwngu.bkt.clouddn.com/o_1c5gaqqst68db2j16nno61q3cp.jpg', '测试555', '0', '2018-06-27 13:53:27', '2018-06-27 13:54:21', '1000', '1006');
INSERT INTO `notice_tb` VALUES ('1008', '2', '1', '0', '这个是干什么', 'http://p2bhwwngu.bkt.clouddn.com/o_1c5gaqqst68db2j16nno61q3cp.jpg', '测试555', '0', '2018-06-27 13:53:27', '2018-06-27 13:54:21', '1001', '1006');
INSERT INTO `notice_tb` VALUES ('1009', '2', '1', '0', '这个是干什么', 'http://p2bhwwngu.bkt.clouddn.com/o_1c5gaqqst68db2j16nno61q3cp.jpg', '测试555', '0', '2018-06-27 13:53:27', '2018-06-27 13:54:21', '1003', '1006');
INSERT INTO `notice_tb` VALUES ('1010', '2', '1', '0', '这个是干什么', 'http://p2bhwwngu.bkt.clouddn.com/o_1c5gaqqst68db2j16nno61q3cp.jpg', '测试555', '0', '2018-06-27 13:53:27', '2018-06-27 13:54:21', '1004', '1006');
INSERT INTO `notice_tb` VALUES ('1011', '2', '1', '0', '这个是干什么', 'http://p2bhwwngu.bkt.clouddn.com/o_1c5gaqqst68db2j16nno61q3cp.jpg', '测试555', '0', '2018-06-27 13:53:27', '2018-06-27 13:54:21', '1005', '1006');
INSERT INTO `notice_tb` VALUES ('1012', '2', '1', '0', '这个是干什么', 'http://p2bhwwngu.bkt.clouddn.com/o_1c5gaqqst68db2j16nno61q3cp.jpg', '测试555', '0', '2018-06-27 13:53:27', '2018-06-27 13:54:21', '1006', '1006');
INSERT INTO `notice_tb` VALUES ('1013', '2', '7', '1', '订单商品动态', 'http://img.fhyx.com/uploads/2017/01/19/2017011921538261.gif', '{\"orderId\":13,\"orderNumber\":\"462635481683722240\",\"merName\":\"科林麦克雷拉力赛之尘埃3 PC版\",\"content\":\"卡号及卡密已经发送给您，请查收！\"}', '0', '2018-06-30 15:08:18', '2018-06-30 15:08:18', '1003', '13');
INSERT INTO `notice_tb` VALUES ('1014', '2', '7', '1', '订单商品动态', 'http://img.fhyx.com/uploads/2017/01/19/2017011921538261.gif', '{\"orderId\":32,\"orderNumber\":\"462681509468307456\",\"merName\":\"科林麦克雷拉力赛之尘埃3 PC版\",\"content\":\"卡号及卡密已经发送给您，请查收！\"}', '0', '2018-06-30 18:13:29', '2018-06-30 18:13:29', '1003', '32');
INSERT INTO `notice_tb` VALUES ('1015', '2', '7', '1', '订单商品动态', 'http://img.fhyx.com/uploads/2017/12/21/2017122124058472.jpg', '{\"orderId\":34,\"orderNumber\":\"462682889591455744\",\"merName\":\"幻想三国志2 PC版中文\",\"content\":\"卡号及卡密已经发送给您，请查收！\"}', '0', '2018-06-30 18:16:52', '2018-06-30 18:16:52', '1003', '34');
INSERT INTO `notice_tb` VALUES ('1017', '2', '7', '1', '订单商品动态', 'http://img.fhyx.com/uploads/2016/12/16/2016121643141739.jpg', '{\"orderId\":35,\"orderNumber\":\"462712287791153152\",\"merName\":\"暗黑破坏神3 PC版 全球版\",\"content\":\"卡号及卡密已经发送给您，请查收！\"}', '0', '2018-06-30 20:19:45', '2018-06-30 20:19:45', '1003', '35');
INSERT INTO `notice_tb` VALUES ('1018', '2', '7', '1', '订单商品动态', 'http://img.fhyx.com/uploads/2016/12/16/2016121643141739.jpg', '{\"orderId\":11,\"orderNumber\":\"461531951187099648\",\"merName\":\"暗黑破坏神3 PC版 全球版\",\"content\":\"卡号及卡密已经发送给您，请查收！\"}', '0', '2018-06-30 20:23:17', '2018-06-30 20:23:17', '1003', '11');
INSERT INTO `notice_tb` VALUES ('1019', '2', '7', '1', '订单商品动态', 'http://img.fhyx.com/uploads/2016/12/16/2016121643141739.jpg', '{\"orderId\":36,\"orderNumber\":\"462715293987241984\",\"merName\":\"暗黑破坏神3 PC版 全球版\",\"content\":\"卡号及卡密已经发送给您，请查收！\"}', '0', '2018-06-30 20:25:27', '2018-06-30 20:25:27', '1003', '36');
INSERT INTO `notice_tb` VALUES ('1020', '2', '3', '1', '新增商品类型', null, '{\"merCateName\":\"剑灵专区2\",\"merCateSummary\":\"剑灵专区2\"}', '2', '2018-07-06 19:18:25', '2018-07-10 16:26:24', '1004', '1005');
INSERT INTO `notice_tb` VALUES ('1021', '2', '2', '1', '申请新产品销售', 'http://img.fhyx.com/uploads/2016/12/16/2016121643141739.jpg', '{\"merName\":\"暗黑破坏神3 PC版 全球版\",\"merCateName\":\"魔兽世界\",\"merPrice\":238.4,\"merDiscount\":0.8}', '2', '2018-07-06 19:18:57', '2018-07-09 00:29:23', '1004', '9');
INSERT INTO `notice_tb` VALUES ('1022', '2', '7', '0', '订单商品动态', 'http://img.fhyx.com/uploads/2016/12/16/2016121643141739.jpg', '{\"orderId\":37,\"orderNumber\":\"465932717259751424\",\"merName\":\"暗黑破坏神3 PC版 全球版\",\"content\":\"卡号及卡密已经发送给您，请查收！\"}', '0', '2018-07-09 17:32:17', '2018-07-09 17:32:17', '1003', '37');
INSERT INTO `notice_tb` VALUES ('1023', '2', '7', '0', '订单商品动态', 'http://img.fhyx.com/uploads/2017/01/19/2017011921538261.gif', '{\"orderId\":38,\"orderNumber\":\"465933786765328384\",\"merName\":\"科林麦克雷拉力赛之尘埃3 PC版\",\"content\":\"卡号及卡密已经发送给您，请查收！\"}', '0', '2018-07-09 17:34:59', '2018-07-09 17:34:59', '1003', '38');
INSERT INTO `notice_tb` VALUES ('1024', '2', '2', '1', '申请新产品销售', 'http://img.fhyx.com/uploads/2017/02/17/2017021745626168.jpg', '{\"merName\":\"三国志13 PC版中文\",\"merCateName\":\"赛车竞速\",\"merPrice\":259,\"merDiscount\":1}', '2', '2018-07-10 16:35:56', '2018-07-10 17:10:28', '1004', '10');
INSERT INTO `notice_tb` VALUES ('1026', '2', '4', '1', '商品申请自营', 'http://img.fhyx.com/uploads/2017/02/17/2017021745626168.jpg', '{\"merName\":\"三国志13 PC版中文\",\"merCateName\":\"赛车竞速\",\"merPrice\":\"259\",\"merPlatformProportion\":\"6.22\"}', '2', '2018-07-10 17:11:38', '2018-07-10 17:24:46', '1004', '10');
INSERT INTO `notice_tb` VALUES ('1027', '2', '4', '1', '商品申请自营', 'http://img.fhyx.com/uploads/2017/02/17/2017021745626168.jpg', '{\"merName\":\"三国志13 PC版中文\",\"merCateName\":\"赛车竞速\",\"merPrice\":\"259\",\"merPlatformProportion\":\"4\"}', '3', '2018-07-10 18:04:11', '2018-07-10 18:54:42', '1004', '10');

-- ----------------------------
-- Table structure for `order_detail_tb`
-- ----------------------------
DROP TABLE IF EXISTS `order_detail_tb`;
CREATE TABLE `order_detail_tb` (
  `order_detail_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单详情id',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `img_address` varchar(255) DEFAULT NULL COMMENT '商品图片',
  `mer_cate_name` varchar(255) DEFAULT NULL COMMENT '类型名名称',
  `unit_price` decimal(11,2) DEFAULT NULL COMMENT '单价',
  `number` int(11) DEFAULT NULL COMMENT '数量',
  `total_price` decimal(11,2) DEFAULT NULL COMMENT '总价',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `coupon_id` int(11) DEFAULT NULL COMMENT '优惠劵id',
  `mer_id` int(11) DEFAULT NULL COMMENT '商品id',
  `order_id` int(11) DEFAULT NULL COMMENT '订单id',
  PRIMARY KEY (`order_detail_id`),
  KEY `INDEX_COUPONID` (`coupon_id`) USING BTREE,
  KEY `INDEX_MERID` (`mer_id`) USING BTREE,
  KEY `INDEX_ORDERID` (`order_id`) USING BTREE,
  KEY `INDEX_CREATEDATE` (`create_date`) USING BTREE,
  KEY `INDEX_UPDATEDATE` (`update_date`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COMMENT='订单详情表';

-- ----------------------------
-- Records of order_detail_tb
-- ----------------------------
INSERT INTO `order_detail_tb` VALUES ('4', '幻想三国志2 PC版中文', 'http://img.fhyx.com/uploads/2017/12/21/2017122124058472.jpg', '角色扮演', '2.90', '3', '8.70', '2018-06-26 10:50:58', '2018-06-26 10:50:58', null, '5', '5');
INSERT INTO `order_detail_tb` VALUES ('5', '极品飞车17：最高通缉PC版', 'http://img.fhyx.com/uploads/2016/12/16/2016121631059549.gif', '赛车竞速', '79.23', '2', '158.46', '2018-06-26 10:50:58', '2018-06-26 10:50:58', null, '6', '6');
INSERT INTO `order_detail_tb` VALUES ('6', '幻想三国志2 PC版中文', 'http://img.fhyx.com/uploads/2017/12/21/2017122124058472.jpg', '角色扮演', '2.90', '3', '8.70', '2018-06-26 16:15:58', '2018-06-26 16:15:58', null, '5', '7');
INSERT INTO `order_detail_tb` VALUES ('7', '科林麦克雷拉力赛之尘埃3 PC版', 'http://img.fhyx.com/uploads/2017/01/19/2017011921538261.gif', '赛车竞速', '17.90', '4', '71.60', '2018-06-26 16:16:15', '2018-06-26 16:16:15', null, '1', '8');
INSERT INTO `order_detail_tb` VALUES ('8', '极品飞车17：最高通缉PC版', 'http://img.fhyx.com/uploads/2016/12/16/2016121631059549.gif', '赛车竞速', '79.23', '2', '158.46', '2018-06-26 16:17:23', '2018-06-26 16:17:23', null, '6', '9');
INSERT INTO `order_detail_tb` VALUES ('9', '暗黑破坏神3 PC版 全球版', 'http://img.fhyx.com/uploads/2016/12/16/2016121643141739.jpg', '魔兽世界', '238.40', '1', '238.40', '2018-06-27 09:49:04', '2018-06-27 09:49:04', null, '4', '10');
INSERT INTO `order_detail_tb` VALUES ('10', '暗黑破坏神3 PC版 全球版', 'http://img.fhyx.com/uploads/2016/12/16/2016121643141739.jpg', '魔兽世界', '238.40', '1', '238.40', '2018-06-27 14:03:12', '2018-06-27 14:03:12', null, '4', '11');
INSERT INTO `order_detail_tb` VALUES ('11', '科林麦克雷拉力赛之尘埃3 PC版', 'http://img.fhyx.com/uploads/2017/01/19/2017011921538261.gif', '赛车竞速', '17.90', '1', '17.90', '2018-06-27 16:44:23', '2018-06-27 16:44:23', null, '1', '12');
INSERT INTO `order_detail_tb` VALUES ('12', '科林麦克雷拉力赛之尘埃3 PC版', 'http://img.fhyx.com/uploads/2017/01/19/2017011921538261.gif', '赛车竞速', '17.90', '1', '17.90', '2018-06-30 15:08:14', '2018-06-30 15:08:14', null, '1', '13');
INSERT INTO `order_detail_tb` VALUES ('13', '科林麦克雷拉力赛之尘埃3 PC版', 'http://img.fhyx.com/uploads/2017/01/19/2017011921538261.gif', '赛车竞速', '17.90', '2', '35.80', '2018-06-30 18:09:46', '2018-06-30 18:09:46', null, '1', '14');
INSERT INTO `order_detail_tb` VALUES ('31', '科林麦克雷拉力赛之尘埃3 PC版', 'http://img.fhyx.com/uploads/2017/01/19/2017011921538261.gif', '赛车竞速', '17.90', '1', '17.90', '2018-06-30 18:11:08', '2018-06-30 18:11:08', null, '1', '32');
INSERT INTO `order_detail_tb` VALUES ('33', '幻想三国志2 PC版中文', 'http://img.fhyx.com/uploads/2017/12/21/2017122124058472.jpg', '角色扮演', '2.90', '2', '5.80', '2018-06-30 18:16:37', '2018-06-30 18:16:37', null, '5', '34');
INSERT INTO `order_detail_tb` VALUES ('34', '暗黑破坏神3 PC版 全球版', 'http://img.fhyx.com/uploads/2016/12/16/2016121643141739.jpg', '魔兽世界', '238.40', '1', '238.40', '2018-06-30 20:13:26', '2018-06-30 20:13:26', null, '4', '35');
INSERT INTO `order_detail_tb` VALUES ('35', '暗黑破坏神3 PC版 全球版', 'http://img.fhyx.com/uploads/2016/12/16/2016121643141739.jpg', '魔兽世界', '238.40', '1', '238.40', '2018-06-30 20:25:23', '2018-06-30 20:25:23', null, '4', '36');
INSERT INTO `order_detail_tb` VALUES ('36', '暗黑破坏神3 PC版 全球版', 'http://img.fhyx.com/uploads/2016/12/16/2016121643141739.jpg', '魔兽世界', '238.40', '1', '238.40', '2018-07-09 17:30:16', '2018-07-09 17:30:16', null, '9', '37');
INSERT INTO `order_detail_tb` VALUES ('37', '科林麦克雷拉力赛之尘埃3 PC版', 'http://img.fhyx.com/uploads/2017/01/19/2017011921538261.gif', '赛车竞速', '35.80', '1', '35.80', '2018-07-09 17:34:31', '2018-07-09 17:34:31', null, '8', '38');

-- ----------------------------
-- Table structure for `order_problem_answer_tb`
-- ----------------------------
DROP TABLE IF EXISTS `order_problem_answer_tb`;
CREATE TABLE `order_problem_answer_tb` (
  `order_problem_answer_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品订单问题反馈id',
  `content` varchar(255) DEFAULT NULL COMMENT '内容',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `order_problem_id` int(11) DEFAULT NULL COMMENT '商品订单问题id外键',
  `account_id` int(11) DEFAULT NULL COMMENT '回复人id外键',
  PRIMARY KEY (`order_problem_answer_id`),
  KEY `INDEX_ORDERPROBLEMID` (`order_problem_id`) USING BTREE,
  KEY `INDEX_ACCOUNTID` (`account_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='商品订单问题反馈表';

-- ----------------------------
-- Records of order_problem_answer_tb
-- ----------------------------
INSERT INTO `order_problem_answer_tb` VALUES ('1', '哪里不能用', '2018-05-31 15:59:50', '1', '1000');
INSERT INTO `order_problem_answer_tb` VALUES ('2', '别急，我先看看', '2018-07-11 15:40:06', '13', '1004');
INSERT INTO `order_problem_answer_tb` VALUES ('3', '没什么问题的，亲，您再测试测试，仔细的。没什么问题的，亲，您再测试测试，仔细的。没什么问题的，亲，您再测试测试，仔细的。没什么问题的，亲，您再测试测试，仔细的。没什么问题的，亲，您再测试测试，仔细的。没什么问题的，亲，您再测试测试，仔细的。没什么问题的，亲，您再测试测试，仔细的。没什么问题的，亲，您再测试测试，仔细的。没什么问题的，亲，您再测试测试，仔细的。没什么问题的，亲，您再测试测试，仔细的。没什么问题的，亲，您再测试测试，仔细的。没什么问题的，亲，您再测试测试，仔细的。没什么问题的，亲，您再测试测试', '2018-07-11 15:40:51', '12', '1004');
INSERT INTO `order_problem_answer_tb` VALUES ('4', '343gfdg', '2018-07-12 16:36:07', '10', null);

-- ----------------------------
-- Table structure for `order_problem_tb`
-- ----------------------------
DROP TABLE IF EXISTS `order_problem_tb`;
CREATE TABLE `order_problem_tb` (
  `order_problem_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品订单问题id',
  `reason` tinyint(4) DEFAULT NULL COMMENT '原因，0其他，1不能充值，2卡密无效，3提示卡密错误',
  `number` tinyint(4) DEFAULT NULL COMMENT '顺序，默认1初次，2二次，以此类推',
  `content` varchar(255) DEFAULT NULL COMMENT '内容',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `mer_id` int(11) DEFAULT NULL COMMENT '商品id外键',
  `order_id` int(11) DEFAULT NULL COMMENT '订单id外键',
  `account_id` int(11) DEFAULT NULL COMMENT '提问人id外键',
  PRIMARY KEY (`order_problem_id`),
  KEY `INDEX_NUMBER` (`number`) USING BTREE,
  KEY `INDEX_MERID` (`mer_id`) USING BTREE,
  KEY `INDEX_ORDERID` (`order_id`) USING BTREE,
  KEY `INDEX_ACCOUNTID` (`account_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='商品订单问题表';

-- ----------------------------
-- Records of order_problem_tb
-- ----------------------------
INSERT INTO `order_problem_tb` VALUES ('1', '1', '1', '这个不能用', '2018-05-31 15:59:22', '4', '1', '1000');
INSERT INTO `order_problem_tb` VALUES ('2', '3', '1', '卡密没有图片，密码是乱码', '2018-06-30 18:39:34', '5', '34', '1003');
INSERT INTO `order_problem_tb` VALUES ('3', '0', '2', '能不能快点', '2018-06-30 19:23:10', '5', '34', '1003');
INSERT INTO `order_problem_tb` VALUES ('10', '0', '3', '是的发生的', '2018-06-30 19:35:06', '5', '34', '1003');
INSERT INTO `order_problem_tb` VALUES ('11', '0', '1', '你笑', '2018-07-01 19:12:16', '4', '36', '1003');
INSERT INTO `order_problem_tb` VALUES ('12', '2', '1', '卡密有错误。不能充值', '2018-07-09 17:52:28', '8', '38', '1003');
INSERT INTO `order_problem_tb` VALUES ('13', '0', '2', '能快点解决吗？', '2018-07-09 17:52:54', '8', '38', '1003');
INSERT INTO `order_problem_tb` VALUES ('14', '0', '3', '还是不行哦。', '2018-07-11 15:42:06', '8', '38', '1003');

-- ----------------------------
-- Table structure for `order_receipt_info_tb`
-- ----------------------------
DROP TABLE IF EXISTS `order_receipt_info_tb`;
CREATE TABLE `order_receipt_info_tb` (
  `order_receipt_info_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单收货信息id',
  `name` varchar(255) DEFAULT NULL COMMENT '收货地址姓名',
  `phone` varchar(255) DEFAULT NULL COMMENT '手机号',
  `telephone_area` varchar(255) DEFAULT NULL COMMENT '电话区号',
  `telephone` varchar(255) DEFAULT NULL COMMENT '电话号',
  `telephone_extension` varchar(255) DEFAULT NULL COMMENT '电话分机',
  `postcode` varchar(255) DEFAULT NULL COMMENT '邮政编码',
  `country` varchar(255) DEFAULT NULL COMMENT '国家',
  `province` varchar(255) DEFAULT NULL COMMENT '省',
  `city` varchar(255) DEFAULT NULL COMMENT '市',
  `area` varchar(255) DEFAULT NULL COMMENT '区',
  `address` varchar(255) DEFAULT NULL COMMENT '收货地址',
  `is_default` tinyint(4) DEFAULT '0' COMMENT '默认为0不是，1是',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `account_id` int(11) DEFAULT NULL COMMENT '账户id,外键',
  `order_id` int(11) DEFAULT NULL COMMENT '订单id,外键',
  PRIMARY KEY (`order_receipt_info_id`),
  KEY `INDEX_ACCOUNTID` (`account_id`) USING BTREE,
  KEY `INDEX_ISDEFAULT` (`is_default`) USING BTREE,
  KEY `INDEX_ORDERID` (`order_id`) USING BTREE,
  KEY `INDEX_CREATEDATE` (`create_date`) USING BTREE,
  KEY `INDEX_UPDATEDATE` (`update_date`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='订单收货地址表 ';

-- ----------------------------
-- Records of order_receipt_info_tb
-- ----------------------------
INSERT INTO `order_receipt_info_tb` VALUES ('1', '股份', '15111336577', null, '', null, '415666', '中国大陆', '河北省', '石家庄市', '长安区', '是打发', '1', '2018-06-26 10:50:58', '2018-06-26 10:50:58', '1003', '5');
INSERT INTO `order_receipt_info_tb` VALUES ('2', '股份', '15111336577', null, '', null, '415666', '中国大陆', '河北省', '石家庄市', '长安区', '是打发', '1', '2018-06-26 10:50:58', '2018-06-26 10:50:58', '1003', '6');
INSERT INTO `order_receipt_info_tb` VALUES ('3', '第三方1', '15111336587', null, '', null, '4115000', '中国大陆', '湖南省', '长沙市', '芙蓉区', 'sadfsdaf', '0', '2018-06-26 16:15:58', '2018-06-26 16:15:58', '1003', '7');
INSERT INTO `order_receipt_info_tb` VALUES ('4', '股份', '15111336577', null, '', null, '415666', '中国大陆', '河北省', '石家庄市', '长安区', '是打发', '1', '2018-06-26 16:16:15', '2018-06-26 16:16:15', '1003', '8');
INSERT INTO `order_receipt_info_tb` VALUES ('5', '股份', '15111336577', null, '', null, '415666', '中国大陆', '河北省', '石家庄市', '长安区', '是打发', '1', '2018-06-26 16:17:23', '2018-06-26 16:17:23', '1003', '9');
INSERT INTO `order_receipt_info_tb` VALUES ('6', '股份', '15111336577', null, '', null, '415666', '中国大陆', '河北省', '石家庄市', '长安区', '是打发', '1', '2018-06-27 09:49:04', '2018-06-27 09:49:04', '1003', '10');
INSERT INTO `order_receipt_info_tb` VALUES ('7', '第三方1', '15111336587', null, '', null, '4115000', '中国大陆', '湖南省', '长沙市', '芙蓉区', 'sadfsdaf', '0', '2018-06-27 14:03:12', '2018-06-27 14:03:12', '1003', '11');
INSERT INTO `order_receipt_info_tb` VALUES ('8', '股份', '15111336577', null, '', null, '415666', '中国大陆', '河北省', '石家庄市', '长安区', '是打发', '1', '2018-06-27 16:44:23', '2018-06-27 16:44:23', '1003', '12');
INSERT INTO `order_receipt_info_tb` VALUES ('9', '股份', '15111336577', null, '', null, '415666', '中国大陆', '河北省', '石家庄市', '长安区', '是打发', '1', '2018-06-30 15:08:14', '2018-06-30 15:08:14', '1003', '13');
INSERT INTO `order_receipt_info_tb` VALUES ('10', '股份', '15111336577', null, '', null, '415666', '中国大陆', '河北省', '石家庄市', '长安区', '是打发', '1', '2018-06-30 18:09:46', '2018-06-30 18:09:46', '1003', '14');
INSERT INTO `order_receipt_info_tb` VALUES ('11', '股份', '15111336577', null, '', null, '415666', '中国大陆', '河北省', '石家庄市', '长安区', '是打发', '1', '2018-06-30 18:11:08', '2018-06-30 18:11:08', '1003', '32');
INSERT INTO `order_receipt_info_tb` VALUES ('12', '第三方1', '15111336587', null, '', null, '4115000', '中国大陆', '湖南省', '长沙市', '芙蓉区', 'sadfsdaf', '0', '2018-06-30 18:16:37', '2018-06-30 18:16:37', '1003', '34');
INSERT INTO `order_receipt_info_tb` VALUES ('13', '股份', '15111336577', null, '', null, '415666', '中国大陆', '河北省', '石家庄市', '长安区', '是打发', '1', '2018-06-30 20:13:26', '2018-06-30 20:13:26', '1003', '35');
INSERT INTO `order_receipt_info_tb` VALUES ('14', '股份', '15111336577', null, '', null, '415666', '中国大陆', '河北省', '石家庄市', '长安区', '是打发', '1', '2018-06-30 20:25:23', '2018-06-30 20:25:23', '1003', '36');
INSERT INTO `order_receipt_info_tb` VALUES ('15', '股份', '15111336577', null, '', null, '415666', '中国大陆', '河北省', '石家庄市', '长安区', '是打发', '1', '2018-07-09 17:30:16', '2018-07-09 17:30:16', '1003', '37');
INSERT INTO `order_receipt_info_tb` VALUES ('16', '股份', '15111336577', null, '', null, '415666', '中国大陆', '河北省', '石家庄市', '长安区', '是打发', '1', '2018-07-09 17:34:31', '2018-07-09 17:34:31', '1003', '38');

-- ----------------------------
-- Table structure for `order_tb`
-- ----------------------------
DROP TABLE IF EXISTS `order_tb`;
CREATE TABLE `order_tb` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `order_number` varchar(255) DEFAULT NULL COMMENT '订单号',
  `type` tinyint(4) DEFAULT NULL COMMENT '类型，1购买商品，2账户提现，3退款，4诚信押金',
  `pay_type` tinyint(4) DEFAULT NULL COMMENT '方式，1支付宝，2微信,3百度钱包,4Paypal,5网银',
  `region` tinyint(4) DEFAULT '1' COMMENT '范围，1官网自营，2商户非自营，3商户自营',
  `platform_proportion` decimal(11,2) DEFAULT '5.00' COMMENT '平台分成比例，单位%',
  `mer_type` tinyint(4) DEFAULT '1' COMMENT '商品类型，1普通商品，2降价商品，3预购商品',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `payment_date` datetime DEFAULT NULL COMMENT '支付日期',
  `merchant_account_id` int(11) DEFAULT NULL COMMENT '商户id',
  `spread_account_id` int(11) DEFAULT NULL COMMENT '推广账户id',
  `account_id` int(11) DEFAULT NULL COMMENT '下单人',
  `status` tinyint(4) DEFAULT NULL COMMENT '订单状态，2待支付，3已支付,4预购商品，5问题单，6已取消，7已删除',
  `substatus` tinyint(4) DEFAULT NULL COMMENT '子状态，2（1待支付），3（1冻结单,2已完成），4（1等待发货），5（1待解决（买家提问后），2解决中（卖家回复后），3申请退款，4已退款，5已解决），6（1正常取消,2订单商品库存不够），7（1已删除）',
  PRIMARY KEY (`order_id`),
  KEY `INDEX_TYPE` (`type`) USING BTREE,
  KEY `INDEX_PAYTYPE` (`pay_type`) USING BTREE,
  KEY `INDEX_ACCOUNTID` (`account_id`) USING BTREE,
  KEY `INDEX_STATUS` (`status`) USING BTREE,
  KEY `INDEX_SUBSTATUS` (`substatus`) USING BTREE,
  KEY `INDEX_CREATEDATE` (`create_date`) USING BTREE,
  KEY `INDEX_UPDATEDATE` (`update_date`) USING BTREE,
  KEY `INDEX_MERCHATACCOUNTID` (`merchant_account_id`) USING BTREE,
  KEY `INDEX_SPREADACCOUNTID` (`spread_account_id`) USING BTREE,
  KEY `INDEX_REGION` (`region`) USING BTREE,
  KEY `INDEX_PLATFORMPROPORTION` (`platform_proportion`) USING BTREE,
  KEY `INDEX_MERTYPE` (`mer_type`) USING BTREE,
  KEY `INDEX_PAYMENTDATE` (`payment_date`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8 COMMENT='订单表';

-- ----------------------------
-- Records of order_tb
-- ----------------------------
INSERT INTO `order_tb` VALUES ('5', '461121184830849024', '1', '1', '1', '5.00', '2', '2018-06-26 10:50:58', '2018-06-26 10:50:58', null, null, null, '1003', '7', '1');
INSERT INTO `order_tb` VALUES ('6', '461121184902152192', '1', '1', '1', '5.00', '3', '2018-06-26 10:50:58', '2018-06-26 10:50:58', null, null, null, '1003', '7', '1');
INSERT INTO `order_tb` VALUES ('7', '461202973658185728', '1', '2', '1', '5.00', '2', '2018-06-26 16:15:58', '2018-06-26 16:15:58', null, null, null, '1003', '2', '1');
INSERT INTO `order_tb` VALUES ('8', '461203044529340416', '1', '1', '1', '5.00', '1', '2018-06-26 16:16:15', '2018-06-26 16:16:15', null, null, null, '1003', '7', '1');
INSERT INTO `order_tb` VALUES ('9', '461203330945777664', '1', '2', '1', '5.00', '3', '2018-06-26 16:17:23', '2018-06-26 16:17:23', null, null, null, '1003', '2', '1');
INSERT INTO `order_tb` VALUES ('10', '461467994971701248', '1', '1', '1', '5.00', '1', '2018-06-27 09:49:04', '2018-06-27 09:49:04', null, null, null, '1003', '7', '1');
INSERT INTO `order_tb` VALUES ('11', '461531951187099648', '1', '1', '1', '5.00', '1', '2018-06-27 14:03:12', '2018-06-30 20:23:17', '2018-06-30 20:23:17', null, null, '1003', '3', '1');
INSERT INTO `order_tb` VALUES ('12', '461572514267529216', '1', '1', '1', '5.00', '1', '2018-06-27 16:44:23', '2018-06-27 16:44:23', null, null, null, '1003', '6', '1');
INSERT INTO `order_tb` VALUES ('13', '462635481683722240', '1', '1', '1', '5.00', '1', '2018-06-30 15:08:14', '2018-06-30 15:08:18', '2018-06-30 15:08:18', null, null, '1003', '3', '1');
INSERT INTO `order_tb` VALUES ('14', '462681164474220544', '1', '1', '1', '5.00', '1', '2018-06-30 18:09:46', '2018-06-30 18:09:46', null, null, null, '1003', '2', '1');
INSERT INTO `order_tb` VALUES ('32', '462681509468307456', '1', '1', '1', '5.00', '1', '2018-06-30 18:11:08', '2018-06-30 18:13:29', '2018-06-30 18:13:29', null, null, '1003', '3', '1');
INSERT INTO `order_tb` VALUES ('34', '462682889591455744', '1', '2', '1', '5.00', '2', '2018-06-30 18:16:37', '2018-06-30 18:16:52', '2018-06-30 18:16:52', null, null, '1003', '5', '5');
INSERT INTO `order_tb` VALUES ('35', '462712287791153152', '1', '1', '1', '5.00', '1', '2018-06-30 20:13:26', '2018-06-30 20:19:45', '2018-06-30 20:19:45', null, null, '1003', '3', '1');
INSERT INTO `order_tb` VALUES ('36', '462715293987241984', '1', '1', '1', '5.00', '1', '2018-06-30 20:25:23', '2018-06-30 20:25:27', '2018-06-30 20:25:27', null, null, '1003', '5', '3');
INSERT INTO `order_tb` VALUES ('37', '465932717259751424', '1', '1', '2', '5.00', '1', '2018-07-09 17:30:16', '2018-07-09 17:32:17', '2018-07-09 17:32:17', '1004', null, '1003', '3', '1');
INSERT INTO `order_tb` VALUES ('38', '465933786765328384', '1', '2', '2', '5.00', '1', '2018-07-09 17:34:31', '2018-07-09 17:34:59', '2018-07-09 17:34:59', '1004', null, '1003', '5', '1');

-- ----------------------------
-- Table structure for `payment_tb`
-- ----------------------------
DROP TABLE IF EXISTS `payment_tb`;
CREATE TABLE `payment_tb` (
  `payment_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '支付id',
  `subject` varchar(255) DEFAULT NULL COMMENT '主题',
  `body` varchar(255) DEFAULT NULL COMMENT '内容',
  `notify_url` varchar(255) DEFAULT NULL COMMENT '异步通知',
  `type` tinyint(4) DEFAULT NULL COMMENT '支付类型，1支付宝，2微信,3百度钱包,4Paypal,5网银',
  `order_number` varchar(255) DEFAULT NULL COMMENT '平台订单号',
  `money` decimal(11,2) DEFAULT NULL COMMENT '金额',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态，1已下单-未支付，2支付成功，3支付失败,4异常',
  `business_type` tinyint(4) DEFAULT NULL COMMENT '业务类型，1购买商品，2账户提现，3退款，4诚信押金',
  `business_id` int(11) DEFAULT NULL COMMENT '业务id,外键',
  `business_notify_url` longtext COMMENT '业务回调,外键',
  `account_id` int(11) DEFAULT NULL COMMENT '账户id,外键',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`payment_id`),
  KEY `INDEX_ORDERNUMBER` (`order_number`) USING BTREE,
  KEY `INDEX_TYPE` (`type`) USING BTREE,
  KEY `INDEX_BUSINESSTYPE` (`business_type`) USING BTREE,
  KEY `INDEX_BUSINESSID` (`business_id`) USING BTREE,
  KEY `INDEX_ACCOUNTID` (`account_id`) USING BTREE,
  KEY `INDEX_STATUS` (`status`) USING BTREE,
  KEY `INDEX_CREATEDATE` (`create_date`) USING BTREE,
  KEY `INDEX_UPDATEDATE` (`update_date`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='支付表';

-- ----------------------------
-- Records of payment_tb
-- ----------------------------

-- ----------------------------
-- Table structure for `permission_tb`
-- ----------------------------
DROP TABLE IF EXISTS `permission_tb`;
CREATE TABLE `permission_tb` (
  `permission_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `type` tinyint(4) DEFAULT NULL COMMENT '权限类型，默认0开放，1鉴权',
  `manager_name` varchar(255) DEFAULT NULL COMMENT '权限管理名',
  `name` varchar(255) DEFAULT NULL COMMENT '权限名',
  `route` varchar(255) DEFAULT NULL COMMENT '权限路由',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`permission_id`),
  UNIQUE KEY `route` (`route`) USING BTREE,
  KEY `INDEX_TYPE` (`type`) USING BTREE,
  KEY `INDEX_MANAGERNAME` (`manager_name`) USING BTREE,
  KEY `INDEX_NAME` (`name`) USING BTREE,
  KEY `INDEX_ROUTE` (`route`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1979 DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Records of permission_tb
-- ----------------------------
INSERT INTO `permission_tb` VALUES ('1699', '1', '账户管理', '账户增加', '/account/add', '2018-04-04 13:39:29');
INSERT INTO `permission_tb` VALUES ('1700', '1', '账户管理', '账户实名认证', '/account/auth', '2018-04-04 13:39:29');
INSERT INTO `permission_tb` VALUES ('1701', '0', '账户管理', '账户数量', '/account/count', '2018-04-04 13:39:29');
INSERT INTO `permission_tb` VALUES ('1702', '1', '账户管理', '账户删除', '/account/delete', '2018-04-04 13:39:29');
INSERT INTO `permission_tb` VALUES ('1703', '0', '账户管理', '是否登录', '/account/islogin', '2018-04-04 13:39:29');
INSERT INTO `permission_tb` VALUES ('1704', '1', '账户管理', '账户列表', '/account/list', '2018-06-24 21:55:03');
INSERT INTO `permission_tb` VALUES ('1705', '1', '账户管理', '账户单个加载', '/account/load', '2018-06-24 21:55:15');
INSERT INTO `permission_tb` VALUES ('1706', '0', '账户管理', '管理员登录', '/account/login', '2018-04-04 13:39:29');
INSERT INTO `permission_tb` VALUES ('1707', '0', '账户管理', '登出', '/account/loginout', '2018-04-04 13:39:29');
INSERT INTO `permission_tb` VALUES ('1708', '1', '账户管理', '手机号账户是否存在', '/account/phoneIsExist', '2018-04-04 13:39:29');
INSERT INTO `permission_tb` VALUES ('1709', '1', '账户管理', '账户修改', '/account/update', '2018-04-04 13:39:29');
INSERT INTO `permission_tb` VALUES ('1710', '1', '账户管理', '账户修改用户信息', '/account/updateInfo', '2018-04-04 13:39:29');
INSERT INTO `permission_tb` VALUES ('1711', '1', '账户管理', '账户修改密码', '/account/updatePassword', '2018-04-04 13:39:29');
INSERT INTO `permission_tb` VALUES ('1712', '0', '账户管理', '手机验证码发送/邮箱验证链接', '/account/validCode', '2018-04-04 13:39:29');
INSERT INTO `permission_tb` VALUES ('1713', '0', '账户管理', '邮箱验证请求地址', '/account/validCodeEmail', '2018-04-04 13:39:29');
INSERT INTO `permission_tb` VALUES ('1714', '0', '账户管理', 'web用户登录', '/account/weblogin', '2018-04-04 13:39:29');
INSERT INTO `permission_tb` VALUES ('1715', '0', '账户管理', 'web用户注册', '/account/webregister', '2018-04-04 13:39:29');
INSERT INTO `permission_tb` VALUES ('1716', '1', '等级管理', '等级增加', '/accountLevel/add', '2018-04-04 13:39:29');
INSERT INTO `permission_tb` VALUES ('1717', '0', '等级管理', '等级数量', '/accountLevel/count', '2018-04-04 13:39:29');
INSERT INTO `permission_tb` VALUES ('1718', '1', '等级管理', '等级删除', '/accountLevel/delete', '2018-04-04 13:39:29');
INSERT INTO `permission_tb` VALUES ('1719', '0', '等级管理', '等级列表', '/accountLevel/list', '2018-04-04 13:39:29');
INSERT INTO `permission_tb` VALUES ('1720', '0', '等级管理', '等级单个加载', '/accountLevel/load', '2018-04-04 13:39:29');
INSERT INTO `permission_tb` VALUES ('1721', '1', '等级管理', '等级修改', '/accountLevel/update', '2018-04-04 13:39:29');
INSERT INTO `permission_tb` VALUES ('1722', '1', '文章管理', '文章增加', '/article/add', '2018-04-04 13:39:29');
INSERT INTO `permission_tb` VALUES ('1723', '0', '文章管理', '文章数量', '/article/count', '2018-04-04 13:39:29');
INSERT INTO `permission_tb` VALUES ('1724', '1', '文章管理', '文章删除', '/article/delete', '2018-04-04 13:39:29');
INSERT INTO `permission_tb` VALUES ('1725', '0', '文章管理', '文章列表', '/article/list', '2018-04-04 13:39:29');
INSERT INTO `permission_tb` VALUES ('1726', '0', '文章管理', '文章单个加载', '/article/load', '2018-04-04 13:39:29');
INSERT INTO `permission_tb` VALUES ('1727', '1', '文章管理', '文章修改', '/article/update', '2018-04-04 13:39:29');
INSERT INTO `permission_tb` VALUES ('1728', '1', '文章类型管理', '文章类型增加', '/articleCate/add', '2018-04-04 13:39:29');
INSERT INTO `permission_tb` VALUES ('1729', '0', '文章类型管理', '文章类型数量', '/articleCate/count', '2018-04-04 13:39:29');
INSERT INTO `permission_tb` VALUES ('1730', '1', '文章类型管理', '文章类型删除', '/articleCate/delete', '2018-04-04 13:39:29');
INSERT INTO `permission_tb` VALUES ('1731', '0', '文章类型管理', '文章类型列表', '/articleCate/list', '2018-04-04 13:39:29');
INSERT INTO `permission_tb` VALUES ('1732', '0', '文章类型管理', '文章类型单个加载', '/articleCate/load', '2018-04-04 13:39:29');
INSERT INTO `permission_tb` VALUES ('1733', '1', '文章类型管理', '文章类型修改', '/articleCate/update', '2018-04-04 13:39:29');
INSERT INTO `permission_tb` VALUES ('1734', '1', '银行卡管理', '银行卡增加', '/bankCard/add', '2018-04-04 13:39:29');
INSERT INTO `permission_tb` VALUES ('1735', '0', '银行卡管理', '银行卡数量', '/bankCard/count', '2018-04-04 13:39:29');
INSERT INTO `permission_tb` VALUES ('1736', '1', '银行卡管理', '银行卡删除', '/bankCard/delete', '2018-04-04 13:39:29');
INSERT INTO `permission_tb` VALUES ('1737', '0', '银行卡管理', '银行卡列表', '/bankCard/list', '2018-04-04 13:39:29');
INSERT INTO `permission_tb` VALUES ('1738', '0', '银行卡管理', '银行卡单个加载', '/bankCard/load', '2018-04-04 13:39:29');
INSERT INTO `permission_tb` VALUES ('1739', '1', '银行卡管理', '银行卡修改', '/bankCard/update', '2018-04-04 13:39:29');
INSERT INTO `permission_tb` VALUES ('1740', '1', '购物车商品管理', '购物车商品增加', '/cartMer/add', '2018-04-04 13:39:29');
INSERT INTO `permission_tb` VALUES ('1741', '0', '购物车商品管理', '购物车商品数量', '/cartMer/count', '2018-04-04 13:39:29');
INSERT INTO `permission_tb` VALUES ('1742', '1', '购物车商品管理', '购物车商品删除', '/cartMer/delete', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1743', '0', '购物车商品管理', '购物车商品列表', '/cartMer/list', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1744', '0', '购物车商品管理', '购物车商品单个加载', '/cartMer/load', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1745', '1', '购物车商品管理', '购物车商品修改', '/cartMer/update', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1746', '1', '配置管理', '配置增加', '/config/add', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1747', '0', '配置管理', '配置数量', '/config/count', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1748', '1', '配置管理', '配置删除', '/config/delete', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1749', '0', '配置管理', '配置列表', '/config/list', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1750', '0', '配置管理', '配置单个加载', '/config/load', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1751', '1', '配置管理', '配置修改', '/config/update', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1752', '1', '优惠劵管理', '优惠劵增加', '/coupon/add', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1753', '0', '优惠劵管理', '优惠劵数量', '/coupon/count', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1754', '1', '优惠劵管理', '优惠劵删除', '/coupon/delete', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1755', '0', '优惠劵管理', '优惠劵列表', '/coupon/list', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1756', '0', '优惠劵管理', '优惠劵单个加载', '/coupon/load', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1757', '1', '优惠劵管理', '优惠劵修改', '/coupon/update', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1758', '1', '优惠劵项管理', '优惠劵项增加', '/couponTerm/add', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1759', '0', '优惠劵项管理', '优惠劵项数量', '/couponTerm/count', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1760', '1', '优惠劵项管理', '优惠劵项删除', '/couponTerm/delete', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1761', '0', '优惠劵项管理', '优惠劵项列表', '/couponTerm/list', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1762', '0', '优惠劵项管理', '优惠劵项单个加载', '/couponTerm/load', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1763', '1', '优惠劵项管理', '优惠劵项修改', '/couponTerm/update', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1764', '1', '财务管理', '财务增加', '/finance/add', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1765', '0', '财务管理', '充值回调', '/finance/alipayRechargeNotifyUrl', '2018-04-09 22:01:55');
INSERT INTO `permission_tb` VALUES ('1766', '0', '财务管理', '财务数量', '/finance/count', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1767', '1', '财务管理', '财务删除', '/finance/delete', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1768', '0', '财务管理', '财务列表', '/finance/list', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1769', '0', '财务管理', '财务单个加载', '/finance/load', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1770', '1', '财务管理', '提现密码验证', '/finance/passwordValid', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1771', '1', '财务管理', '充值', '/finance/recharge', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1772', '1', '财务管理', '财务修改', '/finance/update', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1773', '1', '财务管理', '修改或增加提现密码', '/finance/updatePassword', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1774', '1', '财务管理', '管理员修改或增加交易密码', '/finance/updatePasswordByFinanceId', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1775', '1', '财务管理', '提现', '/finance/withdrawals', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1776', '1', '财务记录管理', '财务记录增加', '/financeRecord/add', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1777', '0', '财务记录管理', '财务记录数量', '/financeRecord/count', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1778', '1', '财务记录管理', '财务记录删除', '/financeRecord/delete', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1779', '0', '财务记录管理', '财务记录列表', '/financeRecord/list', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1780', '0', '财务记录管理', '财务记录单个加载', '/financeRecord/load', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1781', '1', '财务记录管理', '财务记录修改', '/financeRecord/update', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1782', '1', '财务记录管理', '提现到账', '/financeRecord/withdrawals', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1783', '0', '工具接口管理', '二维码', '/tool/getBarcode', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1784', '0', '工具接口管理', '二维码Url', '/tool/getBarcodeUrl', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1785', '0', '工具接口管理', '验证码', '/tool/getVerificationCode', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1787', '1', '积分管理', '积分增加', '/integral/add', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1788', '0', '积分管理', '积分数量', '/integral/count', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1789', '1', '积分管理', '积分删除', '/integral/delete', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1790', '0', '积分管理', '积分列表', '/integral/list', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1791', '0', '积分管理', '积分单个加载', '/integral/load', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1792', '1', '积分管理', '积分修改', '/integral/update', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1793', '1', '积分详情管理', '积分详情增加', '/integralDetail/add', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1794', '0', '积分详情管理', '积分详情数量', '/integralDetail/count', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1795', '1', '积分详情管理', '积分详情删除', '/integralDetail/delete', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1796', '0', '积分详情管理', '积分详情列表', '/integralDetail/list', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1797', '0', '积分详情管理', '积分详情单个加载', '/integralDetail/load', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1798', '1', '积分详情管理', '积分详情修改', '/integralDetail/update', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1799', '1', '商品管理', '商品增加', '/mer/add', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1800', '0', '商品管理', '商品数量', '/mer/count', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1801', '1', '商品管理', '商品删除', '/mer/delete', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1802', '0', '商品管理', '商品列表', '/mer/list', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1803', '0', '商品管理', '商品单个加载', '/mer/load', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1804', '1', '商品管理', '商品修改', '/mer/update', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1805', '1', '商品卡密管理', '商品卡密增加', '/merCardCipher/add', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1806', '0', '商品卡密管理', '商品卡密数量', '/merCardCipher/count', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1807', '1', '商品卡密管理', '商品卡密删除', '/merCardCipher/delete', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1808', '1', '商品卡密管理', '商品卡密列表', '/merCardCipher/list', '2018-06-30 17:35:29');
INSERT INTO `permission_tb` VALUES ('1809', '1', '商品卡密管理', '商品卡密单个加载', '/merCardCipher/load', '2018-06-30 17:35:24');
INSERT INTO `permission_tb` VALUES ('1810', '1', '商品卡密管理', '商品卡密修改', '/merCardCipher/update', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1811', '1', '商品类型管理', '商品类型增加', '/merCate/add', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1812', '0', '商品类型管理', '商品类型数量', '/merCate/count', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1813', '1', '商品类型管理', '商品类型删除', '/merCate/delete', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1814', '0', '商品类型管理', '商品类型列表', '/merCate/list', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1815', '0', '商品类型管理', '商品类型单个加载', '/merCate/load', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1816', '1', '商品类型管理', '商品类型修改', '/merCate/update', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1817', '1', '商品公用管理', '商品公用增加', '/merCommon/add', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1818', '0', '商品公用管理', '商品公用数量', '/merCommon/count', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1819', '1', '商品公用管理', '商品公用删除', '/merCommon/delete', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1820', '0', '商品公用管理', '商品公用列表', '/merCommon/list', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1821', '0', '商品公用管理', '商品公用单个加载', '/merCommon/load', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1822', '1', '商品公用管理', '商品公用修改', '/merCommon/update', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1823', '1', '商品图片管理', '商品图片增加', '/merImg/add', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1824', '0', '商品图片管理', '商品图片数量', '/merImg/count', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1825', '1', '商品图片管理', '商品图片删除', '/merImg/delete', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1826', '0', '商品图片管理', '商品图片列表', '/merImg/list', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1827', '0', '商品图片管理', '商品图片单个加载', '/merImg/load', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1828', '1', '商品图片管理', '商品图片修改', '/merImg/update', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1829', '1', '商品公告管理', '商品公告增加', '/merNotice/add', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1830', '0', '商品公告管理', '商品公告数量', '/merNotice/count', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1831', '1', '商品公告管理', '商品公告删除', '/merNotice/delete', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1832', '0', '商品公告管理', '商品公告列表', '/merNotice/list', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1833', '0', '商品公告管理', '商品公告单个加载', '/merNotice/load', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1834', '1', '商品公告管理', '商品公告修改', '/merNotice/update', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1835', '1', '商品订单卡密管理', '商品订单卡密增加', '/merOrderCardCipher/add', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1836', '0', '商品订单卡密管理', '商品订单卡密数量', '/merOrderCardCipher/count', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1837', '1', '商品订单卡密管理', '商品订单卡密删除', '/merOrderCardCipher/delete', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1838', '1', '商品订单卡密管理', '商品订单卡密列表', '/merOrderCardCipher/list', '2018-06-30 17:04:32');
INSERT INTO `permission_tb` VALUES ('1839', '1', '商品订单卡密管理', '商品订单卡密单个加载', '/merOrderCardCipher/load', '2018-06-30 17:04:26');
INSERT INTO `permission_tb` VALUES ('1840', '1', '商品订单卡密管理', '商品订单卡密修改', '/merOrderCardCipher/update', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1841', '1', '商品订单评论管理', '商品订单评论增加', '/merOrderComment/add', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1842', '0', '商品订单评论管理', '商品订单评论数量', '/merOrderComment/count', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1843', '1', '商品订单评论管理', '商品订单评论删除', '/merOrderComment/delete', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1844', '0', '商品订单评论管理', '商品订单评论列表', '/merOrderComment/list', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1845', '0', '商品订单评论管理', '商品订单评论单个加载', '/merOrderComment/load', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1846', '1', '商品订单评论管理', '商品订单评论修改', '/merOrderComment/update', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1847', '1', '商品关系管理', '商品关系增加', '/merRelation/add', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1848', '0', '商品关系管理', '商品关系数量', '/merRelation/count', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1849', '1', '商品关系管理', '商品关系删除', '/merRelation/delete', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1850', '0', '商品关系管理', '商品关系列表', '/merRelation/list', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1851', '0', '商品关系管理', '商品关系单个加载', '/merRelation/load', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1852', '1', '商品关系管理', '商品关系修改', '/merRelation/update', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1853', '1', '商品搜索管理', '商品搜索增加', '/merSearch/add', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1854', '0', '商品搜索管理', '商品搜索数量', '/merSearch/count', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1855', '1', '商品搜索管理', '商品搜索删除', '/merSearch/delete', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1856', '0', '商品搜索管理', '商品搜索列表', '/merSearch/list', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1857', '0', '商品搜索管理', '商品搜索单个加载', '/merSearch/load', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1858', '1', '商品搜索管理', '商品搜索修改', '/merSearch/update', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1859', '1', '通知管理', '通知增加', '/notice/add', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1860', '0', '通知管理', '通知数量', '/notice/count', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1861', '1', '通知管理', '通知删除', '/notice/delete', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1862', '0', '通知管理', '通知列表', '/notice/list', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1863', '0', '通知管理', '通知单个加载', '/notice/load', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1864', '1', '通知管理', '通知修改', '/notice/update', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1865', '1', '订单管理', '订单增加', '/order/add', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1866', '0', '订单管理', '订单数量', '/order/count', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1867', '1', '订单管理', '订单删除', '/order/delete', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1868', '0', '订单管理', '订单列表', '/order/list', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1869', '0', '订单管理', '订单单个加载', '/order/load', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1870', '1', '订单管理', '申请订单', '/order/payment', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1871', '1', '订单管理', '订单修改', '/order/update', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1872', '1', '订单详情管理', '订单详情增加', '/orderDetail/add', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1873', '0', '订单详情管理', '订单详情数量', '/orderDetail/count', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1874', '1', '订单详情管理', '订单详情删除', '/orderDetail/delete', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1875', '0', '订单详情管理', '订单详情列表', '/orderDetail/list', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1876', '0', '订单详情管理', '订单详情单个加载', '/orderDetail/load', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1877', '1', '订单详情管理', '订单详情修改', '/orderDetail/update', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1878', '1', '商品订单问题管理', '商品订单问题增加', '/orderProblem/add', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1879', '0', '商品订单问题管理', '商品订单问题数量', '/orderProblem/count', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1880', '1', '商品订单问题管理', '商品订单问题删除', '/orderProblem/delete', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1881', '0', '商品订单问题管理', '商品订单问题列表', '/orderProblem/list', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1882', '0', '商品订单问题管理', '商品订单问题单个加载', '/orderProblem/load', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1883', '1', '商品订单问题管理', '商品订单问题修改', '/orderProblem/update', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1884', '1', '商品订单问题反馈管理', '商品订单问题反馈增加', '/orderProblemAnswer/add', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1885', '0', '商品订单问题反馈管理', '商品订单问题反馈数量', '/orderProblemAnswer/count', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1886', '1', '商品订单问题反馈管理', '商品订单问题反馈删除', '/orderProblemAnswer/delete', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1887', '0', '商品订单问题反馈管理', '商品订单问题反馈列表', '/orderProblemAnswer/list', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1888', '0', '商品订单问题反馈管理', '商品订单问题反馈单个加载', '/orderProblemAnswer/load', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1889', '1', '商品订单问题反馈管理', '商品订单问题反馈修改', '/orderProblemAnswer/update', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1890', '1', '支付管理', '支付增加', '/payment/add', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1891', '0', '支付管理', '阿里云支付回调', '/payment/alipayNotifyUrl', '2018-04-09 21:57:44');
INSERT INTO `permission_tb` VALUES ('1892', '1', '支付管理', '阿里云支付查询', '/payment/alipayTradeQuery', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1893', '0', '支付管理', '支付浏览数量', '/payment/count', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1894', '1', '支付管理', '支付删除', '/payment/delete', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1895', '0', '支付管理', '支付列表', '/payment/list', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1896', '0', '支付管理', '支付单个加载', '/payment/load', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1897', '1', '支付管理', '支付修改', '/payment/update', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1898', '1', '权限管理', '权限增加', '/permission/add', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1899', '0', '权限管理', '权限数量', '/permission/count', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1900', '1', '权限管理', '权限删除', '/permission/delete', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1901', '1', '权限管理', '初始化权限', '/permission/init', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1902', '0', '权限管理', '权限列表', '/permission/list', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1903', '0', '权限管理', '权限单个加载', '/permission/load', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1904', '1', '权限管理', '权限修改', '/permission/update', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1905', '1', '收货信息管理', '收货信息增加', '/receiptInfo/add', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1906', '0', '收货信息管理', '收货信息数量', '/receiptInfo/count', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1907', '1', '收货信息管理', '收货信息删除', '/receiptInfo/delete', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1908', '0', '收货信息管理', '收货信息', '/receiptInfo/list', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1909', '0', '收货信息管理', '收货信息单个加载', '/receiptInfo/load', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1910', '1', '收货信息管理', '设置默认收货信息', '/receiptInfo/setIsDefault', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1911', '1', '收货信息管理', '收货信息修改', '/receiptInfo/update', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1912', '1', '角色管理', '角色增加', '/role/add', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1913', '0', '角色管理', '角色数量', '/role/count', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1914', '1', '角色管理', '角色删除', '/role/delete', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1915', '0', '角色管理', '角色列表', '/role/list', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1916', '0', '角色管理', '角色单个加载', '/role/load', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1917', '1', '角色管理', '角色修改', '/role/update', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1918', '1', '角色权限管理', '角色权限增加', '/rolePermission/add', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1919', '0', '角色权限管理', '角色权限数量', '/rolePermission/count', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1920', '1', '角色权限管理', '角色权限删除', '/rolePermission/delete', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1921', '0', '角色权限管理', '角色权限列表', '/rolePermission/list', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1922', '0', '角色权限管理', '角色权限单个加载', '/rolePermission/load', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1923', '1', '角色权限管理', '角色权限修改', '/rolePermission/update', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1924', '1', '诚信管理', '诚信增加', '/sincerity/add', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1925', '0', '诚信管理', '诚信数量', '/sincerity/count', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1926', '1', '诚信管理', '诚信删除', '/sincerity/delete', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1927', '0', '诚信管理', '诚信列表', '/sincerity/list', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1928', '0', '诚信管理', '诚信单个加载', '/sincerity/load', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1929', '1', '诚信管理', '诚信修改', '/sincerity/update', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1930', '0', '工具接口管理', 'getSession', '/tool/getSession', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1931', '0', '工具接口管理', 'getQiniuToken', '/tool/qiniuToken', '2018-04-04 13:39:30');
INSERT INTO `permission_tb` VALUES ('1932', '1', 'banner管理', 'banner增加', '/banner/add', '2018-04-05 19:28:37');
INSERT INTO `permission_tb` VALUES ('1933', '0', 'banner管理', 'banner数目', '/banner/count', '2018-04-05 19:28:37');
INSERT INTO `permission_tb` VALUES ('1934', '1', 'banner管理', 'banner删除', '/banner/delete', '2018-04-05 19:28:37');
INSERT INTO `permission_tb` VALUES ('1935', '0', 'banner管理', 'banner列表', '/banner/list', '2018-04-05 19:28:37');
INSERT INTO `permission_tb` VALUES ('1936', '0', 'banner管理', 'banner单个加载', '/banner/load', '2018-04-05 19:28:37');
INSERT INTO `permission_tb` VALUES ('1937', '1', 'banner管理', 'banner修改', '/banner/update', '2018-04-05 19:28:37');
INSERT INTO `permission_tb` VALUES ('1938', '0', '购物车商品管理', '购物车商品列表', '/cartMer/userlist', '2018-04-09 21:56:52');
INSERT INTO `permission_tb` VALUES ('1939', '0', '订单管理', '订单列表', '/order/userlist', '2018-04-09 21:56:34');
INSERT INTO `permission_tb` VALUES ('1940', '1', '订单收货信息管理', '订单收货信息增加', '/orderReceiptInfo/add', '2018-04-07 22:43:29');
INSERT INTO `permission_tb` VALUES ('1941', '0', '订单收货信息管理', '订单收货信息数量', '/orderReceiptInfo/count', '2018-04-07 22:43:29');
INSERT INTO `permission_tb` VALUES ('1942', '1', '订单收货信息管理', '订单收货信息删除', '/orderReceiptInfo/delete', '2018-04-07 22:43:29');
INSERT INTO `permission_tb` VALUES ('1943', '0', '订单收货信息管理', '订单收货信息', '/orderReceiptInfo/list', '2018-04-07 22:43:29');
INSERT INTO `permission_tb` VALUES ('1944', '0', '订单收货信息管理', '订单收货信息单个加载', '/orderReceiptInfo/load', '2018-04-07 22:43:29');
INSERT INTO `permission_tb` VALUES ('1945', '1', '订单收货信息管理', '订单收货信息修改', '/orderReceiptInfo/update', '2018-04-07 22:43:29');
INSERT INTO `permission_tb` VALUES ('1946', '1', '推广链接管理', '推广链接增加', '/spreadLink/add', '2018-04-07 22:43:29');
INSERT INTO `permission_tb` VALUES ('1947', '0', '推广链接管理', '推广链接数量', '/spreadLink/count', '2018-04-07 22:43:29');
INSERT INTO `permission_tb` VALUES ('1948', '1', '推广链接管理', '推广链接删除', '/spreadLink/delete', '2018-04-07 22:43:29');
INSERT INTO `permission_tb` VALUES ('1949', '0', '推广链接管理', '推广链接列表', '/spreadLink/list', '2018-04-07 22:43:29');
INSERT INTO `permission_tb` VALUES ('1950', '0', '推广链接管理', '推广链接单个加载', '/spreadLink/load', '2018-04-07 22:43:29');
INSERT INTO `permission_tb` VALUES ('1951', '1', '推广链接管理', '推广链接修改', '/spreadLink/update', '2018-04-07 22:43:29');
INSERT INTO `permission_tb` VALUES ('1952', '1', '推广链接项管理', '推广链接项增加', '/spreadLinkTerm/add', '2018-04-07 22:43:29');
INSERT INTO `permission_tb` VALUES ('1953', '0', '推广链接项管理', '推广链接项数量', '/spreadLinkTerm/count', '2018-04-07 22:43:29');
INSERT INTO `permission_tb` VALUES ('1954', '1', '推广链接项管理', '推广链接项删除', '/spreadLinkTerm/delete', '2018-04-07 22:43:29');
INSERT INTO `permission_tb` VALUES ('1955', '0', '推广链接项管理', '推广链接项列表', '/spreadLinkTerm/list', '2018-04-07 22:43:29');
INSERT INTO `permission_tb` VALUES ('1956', '0', '推广链接项管理', '推广链接项单个加载', '/spreadLinkTerm/load', '2018-04-07 22:43:29');
INSERT INTO `permission_tb` VALUES ('1957', '1', '推广链接项管理', '推广链接项修改', '/spreadLinkTerm/update', '2018-04-07 22:43:29');
INSERT INTO `permission_tb` VALUES ('1958', '1', '推广订单账户管理', '推广订单账户增加', '/spreadOrderAccount/add', '2018-04-07 22:43:29');
INSERT INTO `permission_tb` VALUES ('1959', '0', '推广订单账户管理', '推广订单账户数量', '/spreadOrderAccount/count', '2018-04-07 22:43:29');
INSERT INTO `permission_tb` VALUES ('1960', '1', '推广订单账户管理', '推广订单账户删除', '/spreadOrderAccount/delete', '2018-04-07 22:43:29');
INSERT INTO `permission_tb` VALUES ('1961', '0', '推广订单账户管理', '推广订单账户列表', '/spreadOrderAccount/list', '2018-04-07 22:43:29');
INSERT INTO `permission_tb` VALUES ('1962', '0', '推广订单账户管理', '推广订单账户单个加载', '/spreadOrderAccount/load', '2018-04-07 22:43:29');
INSERT INTO `permission_tb` VALUES ('1963', '1', '推广订单账户管理', '推广订单账户修改', '/spreadOrderAccount/update', '2018-04-07 22:43:29');
INSERT INTO `permission_tb` VALUES ('1964', '1', '推广记录管理', '推广记录增加', '/spreadRecord/add', '2018-04-07 22:43:29');
INSERT INTO `permission_tb` VALUES ('1965', '0', '推广记录管理', '推广记录数量', '/spreadRecord/count', '2018-04-07 22:43:29');
INSERT INTO `permission_tb` VALUES ('1966', '1', '推广记录管理', '推广记录删除', '/spreadRecord/delete', '2018-04-07 22:43:29');
INSERT INTO `permission_tb` VALUES ('1967', '0', '推广记录管理', '推广记录列表', '/spreadRecord/list', '2018-04-07 22:43:29');
INSERT INTO `permission_tb` VALUES ('1968', '0', '推广记录管理', '推广记录单个加载', '/spreadRecord/load', '2018-04-07 22:43:29');
INSERT INTO `permission_tb` VALUES ('1969', '1', '推广记录管理', '推广记录修改', '/spreadRecord/update', '2018-04-07 22:43:29');
INSERT INTO `permission_tb` VALUES ('1970', '1', '购物车商品管理', '购物车商品批量删除', '/cartMer/deleteBatch', '2018-06-24 21:26:55');
INSERT INTO `permission_tb` VALUES ('1971', '1', '购物车商品管理', '购物车商品批量转未支付订单商品', '/cartMer/turnOrderBatch', '2018-06-25 18:24:58');
INSERT INTO `permission_tb` VALUES ('1972', '1', '订单管理', '订单批量删除', '/order/deleteBatch', '2018-06-26 15:53:38');
INSERT INTO `permission_tb` VALUES ('1973', '1', '财务管理', '退款', '/finance/refund', '2018-06-26 18:44:36');
INSERT INTO `permission_tb` VALUES ('1974', '1', '银行卡管理', '银行卡增加或者更新', '/bankCard/addOrUpdate', '2018-07-03 16:39:02');
INSERT INTO `permission_tb` VALUES ('1975', '0', '工具接口管理', '导入Excel', '/tool/importExcel', '2018-07-05 18:27:03');
INSERT INTO `permission_tb` VALUES ('1976', '0', '工具接口管理', '文件增加、修改', '/tool/file/add', '2018-07-05 19:31:46');
INSERT INTO `permission_tb` VALUES ('1977', '1', '商品管理', '商户商品增加提交审核', '/mer/addSellerMer', '2018-07-06 11:36:00');
INSERT INTO `permission_tb` VALUES ('1978', '1', '商品管理', '商户商品增加卡密', '/mer/addMerCardCipher', '2018-07-09 15:06:09');

-- ----------------------------
-- Table structure for `receipt_info_tb`
-- ----------------------------
DROP TABLE IF EXISTS `receipt_info_tb`;
CREATE TABLE `receipt_info_tb` (
  `receipt_info_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '收货信息id',
  `name` varchar(255) DEFAULT NULL COMMENT '收货地址姓名',
  `phone` varchar(255) DEFAULT NULL COMMENT '手机号',
  `telephone_area` varchar(255) DEFAULT NULL COMMENT '电话区号',
  `telephone` varchar(255) DEFAULT NULL COMMENT '电话号',
  `telephone_extension` varchar(255) DEFAULT NULL COMMENT '电话分机',
  `postcode` varchar(255) DEFAULT NULL COMMENT '邮政编码',
  `country` varchar(255) DEFAULT NULL COMMENT '国家',
  `province` varchar(255) DEFAULT NULL COMMENT '省',
  `city` varchar(255) DEFAULT NULL COMMENT '市',
  `area` varchar(255) DEFAULT NULL COMMENT '区',
  `address` varchar(255) DEFAULT NULL COMMENT '收货地址',
  `is_default` tinyint(4) DEFAULT '0' COMMENT '默认为0不是，1是',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `account_id` int(11) DEFAULT NULL COMMENT '账户id,外键',
  PRIMARY KEY (`receipt_info_id`),
  KEY `INDEX_ACCOUNTID` (`account_id`) USING BTREE,
  KEY `INDEX_ISDEFAULT` (`is_default`) USING BTREE,
  KEY `INDEX_CREATEDATE` (`create_date`) USING BTREE,
  KEY `INDEX_UPDATEDATE` (`update_date`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='收货地址表 ';

-- ----------------------------
-- Records of receipt_info_tb
-- ----------------------------
INSERT INTO `receipt_info_tb` VALUES ('4', '打算', '15674830902', null, null, null, '456465', '中国大陆', null, '阿萨德', null, '奥术大师大所多撒', '0', '2018-04-03 20:36:37', '2018-04-03 20:36:37', '1001');
INSERT INTO `receipt_info_tb` VALUES ('5', '第三方1', '15111336587', null, '', null, '4115000', '中国大陆', '湖南省', '长沙市', '芙蓉区', 'sadfsdaf', '0', '2018-06-24 14:52:15', '2018-06-24 15:06:09', '1003');
INSERT INTO `receipt_info_tb` VALUES ('6', '股份', '15111336577', null, '', null, '415666', '中国大陆', '河北省', '石家庄市', '长安区', '是打发', '1', '2018-06-24 14:52:38', '2018-06-24 14:52:38', '1003');

-- ----------------------------
-- Table structure for `role_permission_tb`
-- ----------------------------
DROP TABLE IF EXISTS `role_permission_tb`;
CREATE TABLE `role_permission_tb` (
  `role_permission_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色权限id',
  `region` tinyint(4) DEFAULT NULL COMMENT '范围，1公共，2自身',
  `role_id` int(11) DEFAULT NULL COMMENT '角色id,外键',
  `permission_id` int(11) DEFAULT NULL COMMENT '权限id,外键',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`role_permission_id`),
  UNIQUE KEY `UNIQUE_ROLEID_PERMISSIONID` (`role_id`,`permission_id`) USING BTREE,
  KEY `INDEX_REGION` (`region`) USING BTREE,
  KEY `INDEX_ROLEID` (`role_id`) USING BTREE,
  KEY `INDEX_PERMISSIONID` (`permission_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2199 DEFAULT CHARSET=utf8 COMMENT='角色权限表';

-- ----------------------------
-- Records of role_permission_tb
-- ----------------------------
INSERT INTO `role_permission_tb` VALUES ('1001', '2', '1003', '1905', '2018-04-05 18:06:20');
INSERT INTO `role_permission_tb` VALUES ('1003', '2', '1003', '1907', '2018-04-05 18:11:33');
INSERT INTO `role_permission_tb` VALUES ('1004', '1', '1003', '1708', '2018-04-05 18:23:59');
INSERT INTO `role_permission_tb` VALUES ('1023', '2', '1003', '1910', '2018-04-06 01:17:10');
INSERT INTO `role_permission_tb` VALUES ('1025', '2', '1003', '1889', '2018-04-06 01:18:56');
INSERT INTO `role_permission_tb` VALUES ('1026', '2', '1003', '1884', '2018-04-06 01:19:08');
INSERT INTO `role_permission_tb` VALUES ('1027', '2', '1003', '1886', '2018-04-06 01:19:17');
INSERT INTO `role_permission_tb` VALUES ('1028', '2', '1003', '1883', '2018-04-06 01:19:32');
INSERT INTO `role_permission_tb` VALUES ('1029', '2', '1003', '1880', '2018-04-06 01:19:47');
INSERT INTO `role_permission_tb` VALUES ('1030', '2', '1003', '1878', '2018-04-06 01:20:10');
INSERT INTO `role_permission_tb` VALUES ('1031', '2', '1003', '1877', '2018-04-06 01:20:26');
INSERT INTO `role_permission_tb` VALUES ('1032', '2', '1003', '1874', '2018-04-06 01:20:36');
INSERT INTO `role_permission_tb` VALUES ('1033', '2', '1003', '1872', '2018-04-06 01:20:47');
INSERT INTO `role_permission_tb` VALUES ('1034', '2', '1003', '1870', '2018-04-06 01:21:00');
INSERT INTO `role_permission_tb` VALUES ('1035', '2', '1003', '1841', '2018-04-06 01:22:44');
INSERT INTO `role_permission_tb` VALUES ('1036', '1', '1003', '1773', '2018-04-06 01:25:01');
INSERT INTO `role_permission_tb` VALUES ('1037', '1', '1003', '1771', '2018-04-06 01:25:53');
INSERT INTO `role_permission_tb` VALUES ('1038', '2', '1003', '1745', '2018-04-06 01:26:37');
INSERT INTO `role_permission_tb` VALUES ('1039', '2', '1003', '1740', '2018-04-06 01:46:26');
INSERT INTO `role_permission_tb` VALUES ('1040', '2', '1003', '1700', '2018-04-06 01:46:40');
INSERT INTO `role_permission_tb` VALUES ('1041', '2', '1003', '1710', '2018-04-06 01:46:49');
INSERT INTO `role_permission_tb` VALUES ('1042', '2', '1003', '1711', '2018-04-06 01:46:56');
INSERT INTO `role_permission_tb` VALUES ('1043', '2', '1003', '1734', '2018-04-06 01:47:19');
INSERT INTO `role_permission_tb` VALUES ('1044', '2', '1003', '1736', '2018-04-06 01:47:27');
INSERT INTO `role_permission_tb` VALUES ('1045', '2', '1003', '1739', '2018-04-06 01:47:37');
INSERT INTO `role_permission_tb` VALUES ('1046', '2', '1003', '1742', '2018-04-06 01:47:49');
INSERT INTO `role_permission_tb` VALUES ('1608', '1', '1004', '1969', '2018-04-08 15:44:12');
INSERT INTO `role_permission_tb` VALUES ('1609', '1', '1004', '1966', '2018-04-08 15:44:12');
INSERT INTO `role_permission_tb` VALUES ('1610', '1', '1004', '1964', '2018-04-08 15:44:12');
INSERT INTO `role_permission_tb` VALUES ('1611', '1', '1004', '1963', '2018-04-08 15:44:13');
INSERT INTO `role_permission_tb` VALUES ('1612', '1', '1004', '1960', '2018-04-08 15:44:13');
INSERT INTO `role_permission_tb` VALUES ('1613', '1', '1004', '1958', '2018-04-08 15:44:13');
INSERT INTO `role_permission_tb` VALUES ('1614', '1', '1004', '1957', '2018-04-08 15:44:13');
INSERT INTO `role_permission_tb` VALUES ('1615', '1', '1004', '1954', '2018-04-08 15:44:13');
INSERT INTO `role_permission_tb` VALUES ('1616', '1', '1004', '1952', '2018-04-08 15:44:13');
INSERT INTO `role_permission_tb` VALUES ('1617', '1', '1004', '1951', '2018-04-08 15:44:13');
INSERT INTO `role_permission_tb` VALUES ('1618', '1', '1004', '1948', '2018-04-08 15:44:13');
INSERT INTO `role_permission_tb` VALUES ('1619', '1', '1004', '1946', '2018-04-08 15:44:13');
INSERT INTO `role_permission_tb` VALUES ('1620', '1', '1004', '1945', '2018-04-08 15:44:14');
INSERT INTO `role_permission_tb` VALUES ('1621', '1', '1004', '1942', '2018-04-08 15:44:14');
INSERT INTO `role_permission_tb` VALUES ('1622', '1', '1004', '1940', '2018-04-08 15:44:14');
INSERT INTO `role_permission_tb` VALUES ('1625', '1', '1004', '1937', '2018-04-08 15:44:14');
INSERT INTO `role_permission_tb` VALUES ('1626', '1', '1004', '1934', '2018-04-08 15:44:14');
INSERT INTO `role_permission_tb` VALUES ('1627', '1', '1004', '1932', '2018-04-08 15:44:14');
INSERT INTO `role_permission_tb` VALUES ('1628', '1', '1004', '1929', '2018-04-08 15:44:14');
INSERT INTO `role_permission_tb` VALUES ('1629', '1', '1004', '1926', '2018-04-08 15:44:14');
INSERT INTO `role_permission_tb` VALUES ('1630', '1', '1004', '1924', '2018-04-08 15:44:14');
INSERT INTO `role_permission_tb` VALUES ('1637', '1', '1004', '1911', '2018-04-08 15:44:15');
INSERT INTO `role_permission_tb` VALUES ('1638', '1', '1004', '1910', '2018-04-08 15:44:15');
INSERT INTO `role_permission_tb` VALUES ('1639', '1', '1004', '1907', '2018-04-08 15:44:15');
INSERT INTO `role_permission_tb` VALUES ('1640', '1', '1004', '1905', '2018-04-08 15:44:15');
INSERT INTO `role_permission_tb` VALUES ('1645', '1', '1004', '1897', '2018-04-08 15:44:16');
INSERT INTO `role_permission_tb` VALUES ('1646', '1', '1004', '1894', '2018-04-08 15:44:16');
INSERT INTO `role_permission_tb` VALUES ('1647', '1', '1004', '1892', '2018-04-08 15:44:16');
INSERT INTO `role_permission_tb` VALUES ('1649', '1', '1004', '1890', '2018-04-08 15:44:16');
INSERT INTO `role_permission_tb` VALUES ('1650', '1', '1004', '1889', '2018-04-08 15:44:16');
INSERT INTO `role_permission_tb` VALUES ('1651', '1', '1004', '1886', '2018-04-08 15:44:17');
INSERT INTO `role_permission_tb` VALUES ('1652', '1', '1004', '1884', '2018-04-08 15:44:17');
INSERT INTO `role_permission_tb` VALUES ('1653', '1', '1004', '1883', '2018-04-08 15:44:17');
INSERT INTO `role_permission_tb` VALUES ('1654', '1', '1004', '1880', '2018-04-08 15:44:17');
INSERT INTO `role_permission_tb` VALUES ('1655', '1', '1004', '1878', '2018-04-08 15:44:17');
INSERT INTO `role_permission_tb` VALUES ('1656', '1', '1004', '1877', '2018-04-08 15:44:17');
INSERT INTO `role_permission_tb` VALUES ('1657', '1', '1004', '1874', '2018-04-08 15:44:17');
INSERT INTO `role_permission_tb` VALUES ('1658', '1', '1004', '1872', '2018-04-08 15:44:17');
INSERT INTO `role_permission_tb` VALUES ('1659', '1', '1004', '1871', '2018-04-08 15:44:17');
INSERT INTO `role_permission_tb` VALUES ('1660', '1', '1004', '1870', '2018-04-08 15:44:17');
INSERT INTO `role_permission_tb` VALUES ('1661', '1', '1004', '1867', '2018-04-08 15:44:18');
INSERT INTO `role_permission_tb` VALUES ('1662', '1', '1004', '1865', '2018-04-08 15:44:18');
INSERT INTO `role_permission_tb` VALUES ('1663', '1', '1004', '1864', '2018-04-08 15:44:18');
INSERT INTO `role_permission_tb` VALUES ('1664', '1', '1004', '1861', '2018-04-08 15:44:18');
INSERT INTO `role_permission_tb` VALUES ('1665', '1', '1004', '1859', '2018-04-08 15:44:18');
INSERT INTO `role_permission_tb` VALUES ('1666', '1', '1004', '1858', '2018-04-08 15:44:18');
INSERT INTO `role_permission_tb` VALUES ('1667', '1', '1004', '1855', '2018-04-08 15:44:18');
INSERT INTO `role_permission_tb` VALUES ('1668', '1', '1004', '1853', '2018-04-08 15:44:18');
INSERT INTO `role_permission_tb` VALUES ('1669', '1', '1004', '1852', '2018-04-08 15:44:18');
INSERT INTO `role_permission_tb` VALUES ('1670', '1', '1004', '1849', '2018-04-08 15:44:19');
INSERT INTO `role_permission_tb` VALUES ('1671', '1', '1004', '1847', '2018-04-08 15:44:19');
INSERT INTO `role_permission_tb` VALUES ('1672', '1', '1004', '1846', '2018-04-08 15:44:19');
INSERT INTO `role_permission_tb` VALUES ('1673', '1', '1004', '1843', '2018-04-08 15:44:19');
INSERT INTO `role_permission_tb` VALUES ('1674', '1', '1004', '1841', '2018-04-08 15:44:19');
INSERT INTO `role_permission_tb` VALUES ('1675', '1', '1004', '1840', '2018-04-08 15:44:19');
INSERT INTO `role_permission_tb` VALUES ('1676', '1', '1004', '1837', '2018-04-08 15:44:19');
INSERT INTO `role_permission_tb` VALUES ('1677', '1', '1004', '1835', '2018-04-08 15:44:19');
INSERT INTO `role_permission_tb` VALUES ('1678', '1', '1004', '1834', '2018-04-08 15:44:19');
INSERT INTO `role_permission_tb` VALUES ('1679', '1', '1004', '1831', '2018-04-08 15:44:19');
INSERT INTO `role_permission_tb` VALUES ('1680', '1', '1004', '1829', '2018-04-08 15:44:19');
INSERT INTO `role_permission_tb` VALUES ('1681', '1', '1004', '1828', '2018-04-08 15:44:20');
INSERT INTO `role_permission_tb` VALUES ('1682', '1', '1004', '1825', '2018-04-08 15:44:20');
INSERT INTO `role_permission_tb` VALUES ('1683', '1', '1004', '1823', '2018-04-08 15:44:20');
INSERT INTO `role_permission_tb` VALUES ('1684', '1', '1004', '1822', '2018-04-08 15:44:20');
INSERT INTO `role_permission_tb` VALUES ('1685', '1', '1004', '1819', '2018-04-08 15:44:20');
INSERT INTO `role_permission_tb` VALUES ('1686', '1', '1004', '1817', '2018-04-08 15:44:20');
INSERT INTO `role_permission_tb` VALUES ('1687', '1', '1004', '1816', '2018-04-08 15:44:20');
INSERT INTO `role_permission_tb` VALUES ('1688', '1', '1004', '1813', '2018-04-08 15:44:20');
INSERT INTO `role_permission_tb` VALUES ('1689', '1', '1004', '1811', '2018-04-08 15:44:20');
INSERT INTO `role_permission_tb` VALUES ('1690', '1', '1004', '1810', '2018-04-08 15:44:20');
INSERT INTO `role_permission_tb` VALUES ('1691', '1', '1004', '1807', '2018-04-08 15:44:21');
INSERT INTO `role_permission_tb` VALUES ('1692', '1', '1004', '1805', '2018-04-08 15:44:21');
INSERT INTO `role_permission_tb` VALUES ('1693', '1', '1004', '1804', '2018-04-08 15:44:21');
INSERT INTO `role_permission_tb` VALUES ('1694', '1', '1004', '1801', '2018-04-08 15:44:21');
INSERT INTO `role_permission_tb` VALUES ('1695', '1', '1004', '1799', '2018-04-08 15:44:21');
INSERT INTO `role_permission_tb` VALUES ('1696', '1', '1004', '1798', '2018-04-08 15:44:21');
INSERT INTO `role_permission_tb` VALUES ('1697', '1', '1004', '1795', '2018-04-08 15:44:21');
INSERT INTO `role_permission_tb` VALUES ('1698', '1', '1004', '1793', '2018-04-08 15:44:21');
INSERT INTO `role_permission_tb` VALUES ('1699', '1', '1004', '1792', '2018-04-08 15:44:21');
INSERT INTO `role_permission_tb` VALUES ('1700', '1', '1004', '1789', '2018-04-08 15:44:21');
INSERT INTO `role_permission_tb` VALUES ('1701', '1', '1004', '1787', '2018-04-08 15:44:22');
INSERT INTO `role_permission_tb` VALUES ('1702', '1', '1004', '1782', '2018-04-08 15:44:22');
INSERT INTO `role_permission_tb` VALUES ('1703', '1', '1004', '1781', '2018-04-08 15:44:22');
INSERT INTO `role_permission_tb` VALUES ('1704', '1', '1004', '1778', '2018-04-08 15:44:22');
INSERT INTO `role_permission_tb` VALUES ('1705', '1', '1004', '1776', '2018-04-08 15:44:22');
INSERT INTO `role_permission_tb` VALUES ('1706', '1', '1004', '1775', '2018-04-08 15:44:22');
INSERT INTO `role_permission_tb` VALUES ('1707', '1', '1004', '1774', '2018-04-08 15:44:22');
INSERT INTO `role_permission_tb` VALUES ('1708', '1', '1004', '1773', '2018-04-08 15:44:22');
INSERT INTO `role_permission_tb` VALUES ('1709', '1', '1004', '1772', '2018-04-08 15:44:22');
INSERT INTO `role_permission_tb` VALUES ('1710', '1', '1004', '1771', '2018-04-08 15:44:22');
INSERT INTO `role_permission_tb` VALUES ('1711', '1', '1004', '1770', '2018-04-08 15:44:23');
INSERT INTO `role_permission_tb` VALUES ('1712', '1', '1004', '1767', '2018-04-08 15:44:23');
INSERT INTO `role_permission_tb` VALUES ('1714', '1', '1004', '1764', '2018-04-08 15:44:23');
INSERT INTO `role_permission_tb` VALUES ('1715', '1', '1004', '1763', '2018-04-08 15:44:23');
INSERT INTO `role_permission_tb` VALUES ('1716', '1', '1004', '1760', '2018-04-08 15:44:23');
INSERT INTO `role_permission_tb` VALUES ('1717', '1', '1004', '1758', '2018-04-08 15:44:23');
INSERT INTO `role_permission_tb` VALUES ('1718', '1', '1004', '1757', '2018-04-08 15:44:23');
INSERT INTO `role_permission_tb` VALUES ('1719', '1', '1004', '1754', '2018-04-08 15:44:23');
INSERT INTO `role_permission_tb` VALUES ('1720', '1', '1004', '1752', '2018-04-08 15:44:23');
INSERT INTO `role_permission_tb` VALUES ('1721', '1', '1004', '1751', '2018-04-08 15:44:24');
INSERT INTO `role_permission_tb` VALUES ('1722', '1', '1004', '1748', '2018-04-08 15:44:24');
INSERT INTO `role_permission_tb` VALUES ('1723', '1', '1004', '1746', '2018-04-08 15:44:24');
INSERT INTO `role_permission_tb` VALUES ('1724', '1', '1004', '1745', '2018-04-08 15:44:24');
INSERT INTO `role_permission_tb` VALUES ('1725', '1', '1004', '1742', '2018-04-08 15:44:24');
INSERT INTO `role_permission_tb` VALUES ('1726', '1', '1004', '1740', '2018-04-08 15:44:24');
INSERT INTO `role_permission_tb` VALUES ('1727', '1', '1004', '1739', '2018-04-08 15:44:24');
INSERT INTO `role_permission_tb` VALUES ('1728', '1', '1004', '1736', '2018-04-08 15:44:24');
INSERT INTO `role_permission_tb` VALUES ('1729', '1', '1004', '1734', '2018-04-08 15:44:24');
INSERT INTO `role_permission_tb` VALUES ('1730', '1', '1004', '1733', '2018-04-08 15:44:24');
INSERT INTO `role_permission_tb` VALUES ('1731', '1', '1004', '1730', '2018-04-08 15:44:25');
INSERT INTO `role_permission_tb` VALUES ('1732', '1', '1004', '1728', '2018-04-08 15:44:25');
INSERT INTO `role_permission_tb` VALUES ('1733', '1', '1004', '1727', '2018-04-08 15:44:25');
INSERT INTO `role_permission_tb` VALUES ('1734', '1', '1004', '1724', '2018-04-08 15:44:25');
INSERT INTO `role_permission_tb` VALUES ('1735', '1', '1004', '1722', '2018-04-08 15:44:25');
INSERT INTO `role_permission_tb` VALUES ('1736', '1', '1004', '1721', '2018-04-08 15:44:25');
INSERT INTO `role_permission_tb` VALUES ('1737', '1', '1004', '1718', '2018-04-08 15:44:25');
INSERT INTO `role_permission_tb` VALUES ('1738', '1', '1004', '1716', '2018-04-08 15:44:25');
INSERT INTO `role_permission_tb` VALUES ('1739', '1', '1004', '1711', '2018-04-08 15:44:25');
INSERT INTO `role_permission_tb` VALUES ('1740', '1', '1004', '1710', '2018-04-08 15:44:25');
INSERT INTO `role_permission_tb` VALUES ('1741', '1', '1004', '1709', '2018-04-08 15:44:26');
INSERT INTO `role_permission_tb` VALUES ('1742', '1', '1004', '1708', '2018-04-08 15:44:26');
INSERT INTO `role_permission_tb` VALUES ('1743', '1', '1004', '1702', '2018-04-08 15:44:26');
INSERT INTO `role_permission_tb` VALUES ('1744', '1', '1004', '1700', '2018-04-08 15:44:26');
INSERT INTO `role_permission_tb` VALUES ('1745', '1', '1004', '1699', '2018-04-08 15:44:26');
INSERT INTO `role_permission_tb` VALUES ('1923', '2', '1001', '1892', '2018-04-09 21:16:32');
INSERT INTO `role_permission_tb` VALUES ('1926', '2', '1001', '1889', '2018-04-09 21:16:32');
INSERT INTO `role_permission_tb` VALUES ('1927', '2', '1001', '1886', '2018-04-09 21:16:32');
INSERT INTO `role_permission_tb` VALUES ('1928', '2', '1001', '1884', '2018-04-09 21:16:32');
INSERT INTO `role_permission_tb` VALUES ('1939', '2', '1001', '1864', '2018-04-09 21:16:33');
INSERT INTO `role_permission_tb` VALUES ('1940', '2', '1001', '1861', '2018-04-09 21:16:34');
INSERT INTO `role_permission_tb` VALUES ('1941', '2', '1001', '1859', '2018-04-09 21:16:34');
INSERT INTO `role_permission_tb` VALUES ('1966', '2', '1001', '1810', '2018-04-09 21:16:36');
INSERT INTO `role_permission_tb` VALUES ('1967', '2', '1001', '1807', '2018-04-09 21:16:36');
INSERT INTO `role_permission_tb` VALUES ('1968', '2', '1001', '1805', '2018-04-09 21:16:36');
INSERT INTO `role_permission_tb` VALUES ('1969', '2', '1001', '1804', '2018-04-09 21:16:36');
INSERT INTO `role_permission_tb` VALUES ('1970', '2', '1001', '1801', '2018-04-09 21:16:37');
INSERT INTO `role_permission_tb` VALUES ('1971', '2', '1001', '1799', '2018-04-09 21:16:37');
INSERT INTO `role_permission_tb` VALUES ('1978', '2', '1001', '1782', '2018-04-09 21:16:37');
INSERT INTO `role_permission_tb` VALUES ('1982', '2', '1001', '1775', '2018-04-09 21:16:38');
INSERT INTO `role_permission_tb` VALUES ('1984', '2', '1001', '1773', '2018-04-09 21:16:38');
INSERT INTO `role_permission_tb` VALUES ('1986', '2', '1001', '1771', '2018-04-09 21:16:38');
INSERT INTO `role_permission_tb` VALUES ('1987', '2', '1001', '1770', '2018-04-09 21:16:38');
INSERT INTO `role_permission_tb` VALUES ('1994', '2', '1001', '1757', '2018-04-09 21:16:39');
INSERT INTO `role_permission_tb` VALUES ('1995', '2', '1001', '1754', '2018-04-09 21:16:39');
INSERT INTO `role_permission_tb` VALUES ('1996', '2', '1001', '1752', '2018-04-09 21:16:39');
INSERT INTO `role_permission_tb` VALUES ('2004', '2', '1001', '1736', '2018-04-09 21:16:40');
INSERT INTO `role_permission_tb` VALUES ('2015', '2', '1001', '1711', '2018-04-09 21:16:41');
INSERT INTO `role_permission_tb` VALUES ('2016', '2', '1001', '1710', '2018-04-09 21:16:41');
INSERT INTO `role_permission_tb` VALUES ('2018', '2', '1001', '1708', '2018-04-09 21:16:41');
INSERT INTO `role_permission_tb` VALUES ('2020', '2', '1001', '1700', '2018-04-09 21:16:42');
INSERT INTO `role_permission_tb` VALUES ('2079', '2', '1002', '1861', '2018-04-09 21:33:36');
INSERT INTO `role_permission_tb` VALUES ('2113', '2', '1002', '1793', '2018-04-09 21:33:39');
INSERT INTO `role_permission_tb` VALUES ('2117', '2', '1002', '1782', '2018-04-09 21:33:39');
INSERT INTO `role_permission_tb` VALUES ('2121', '2', '1002', '1775', '2018-04-09 21:33:40');
INSERT INTO `role_permission_tb` VALUES ('2123', '2', '1002', '1773', '2018-04-09 21:33:40');
INSERT INTO `role_permission_tb` VALUES ('2125', '2', '1002', '1771', '2018-04-09 21:33:40');
INSERT INTO `role_permission_tb` VALUES ('2126', '2', '1002', '1770', '2018-04-09 21:33:40');
INSERT INTO `role_permission_tb` VALUES ('2135', '2', '1002', '1752', '2018-04-09 21:33:41');
INSERT INTO `role_permission_tb` VALUES ('2154', '2', '1002', '1711', '2018-04-09 21:33:43');
INSERT INTO `role_permission_tb` VALUES ('2155', '2', '1002', '1710', '2018-04-09 21:33:43');
INSERT INTO `role_permission_tb` VALUES ('2156', '2', '1002', '1709', '2018-04-09 21:33:43');
INSERT INTO `role_permission_tb` VALUES ('2157', '2', '1002', '1708', '2018-04-09 21:33:43');
INSERT INTO `role_permission_tb` VALUES ('2159', '2', '1002', '1700', '2018-04-09 21:33:44');
INSERT INTO `role_permission_tb` VALUES ('2162', '2', '1003', '1752', '2018-04-12 14:51:37');
INSERT INTO `role_permission_tb` VALUES ('2163', '2', '1003', '1911', '2018-04-12 14:55:41');
INSERT INTO `role_permission_tb` VALUES ('2164', '1', '1004', '1970', '2018-06-24 17:45:24');
INSERT INTO `role_permission_tb` VALUES ('2165', '2', '1003', '1970', '2018-06-24 21:27:40');
INSERT INTO `role_permission_tb` VALUES ('2166', '1', '1004', '1704', '2018-06-24 21:55:34');
INSERT INTO `role_permission_tb` VALUES ('2167', '1', '1004', '1705', '2018-06-24 21:55:45');
INSERT INTO `role_permission_tb` VALUES ('2168', '2', '1001', '1704', '2018-06-24 21:56:02');
INSERT INTO `role_permission_tb` VALUES ('2169', '2', '1001', '1705', '2018-06-24 21:56:10');
INSERT INTO `role_permission_tb` VALUES ('2170', '2', '1002', '1704', '2018-06-24 21:56:35');
INSERT INTO `role_permission_tb` VALUES ('2171', '2', '1002', '1705', '2018-06-24 21:56:40');
INSERT INTO `role_permission_tb` VALUES ('2172', '2', '1003', '1705', '2018-06-24 21:56:54');
INSERT INTO `role_permission_tb` VALUES ('2173', '2', '1003', '1704', '2018-06-24 21:56:59');
INSERT INTO `role_permission_tb` VALUES ('2174', '1', '1004', '1971', '2018-06-25 18:25:06');
INSERT INTO `role_permission_tb` VALUES ('2175', '2', '1003', '1971', '2018-06-25 18:25:19');
INSERT INTO `role_permission_tb` VALUES ('2176', '2', '1003', '1871', '2018-06-26 14:31:59');
INSERT INTO `role_permission_tb` VALUES ('2177', '1', '1004', '1972', '2018-06-26 15:53:52');
INSERT INTO `role_permission_tb` VALUES ('2178', '2', '1003', '1972', '2018-06-26 15:54:03');
INSERT INTO `role_permission_tb` VALUES ('2179', '1', '1004', '1973', '2018-06-26 18:44:50');
INSERT INTO `role_permission_tb` VALUES ('2180', '2', '1003', '1973', '2018-06-26 18:45:06');
INSERT INTO `role_permission_tb` VALUES ('2181', '1', '1004', '1838', '2018-06-30 17:04:47');
INSERT INTO `role_permission_tb` VALUES ('2182', '1', '1004', '1839', '2018-06-30 17:04:53');
INSERT INTO `role_permission_tb` VALUES ('2185', '2', '1003', '1839', '2018-06-30 17:13:52');
INSERT INTO `role_permission_tb` VALUES ('2186', '2', '1003', '1838', '2018-06-30 17:14:05');
INSERT INTO `role_permission_tb` VALUES ('2187', '1', '1004', '1809', '2018-06-30 17:35:41');
INSERT INTO `role_permission_tb` VALUES ('2188', '1', '1004', '1808', '2018-06-30 17:35:46');
INSERT INTO `role_permission_tb` VALUES ('2189', '1', '1001', '1838', '2018-06-30 17:37:05');
INSERT INTO `role_permission_tb` VALUES ('2190', '1', '1001', '1808', '2018-06-30 17:37:20');
INSERT INTO `role_permission_tb` VALUES ('2191', '1', '1001', '1809', '2018-06-30 17:37:30');
INSERT INTO `role_permission_tb` VALUES ('2192', '2', '1001', '1839', '2018-06-30 17:37:41');
INSERT INTO `role_permission_tb` VALUES ('2193', '2', '1002', '1946', '2018-07-01 20:59:04');
INSERT INTO `role_permission_tb` VALUES ('2194', '2', '1001', '1974', '2018-07-03 16:40:02');
INSERT INTO `role_permission_tb` VALUES ('2195', '2', '1002', '1974', '2018-07-03 16:40:13');
INSERT INTO `role_permission_tb` VALUES ('2196', '2', '1001', '1977', '2018-07-06 11:36:32');
INSERT INTO `role_permission_tb` VALUES ('2197', '1', '1004', '1978', '2018-07-09 15:06:23');
INSERT INTO `role_permission_tb` VALUES ('2198', '2', '1001', '1978', '2018-07-09 15:06:41');

-- ----------------------------
-- Table structure for `role_tb`
-- ----------------------------
DROP TABLE IF EXISTS `role_tb`;
CREATE TABLE `role_tb` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `name` varchar(255) DEFAULT NULL COMMENT '角色名',
  `duty` varchar(255) DEFAULT NULL COMMENT '角色职责',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1005 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of role_tb
-- ----------------------------
INSERT INTO `role_tb` VALUES ('1000', '超级管理员', '超级管理员', '2018-03-19 10:29:27');
INSERT INTO `role_tb` VALUES ('1001', '商户', '商户', '2018-03-19 10:29:27');
INSERT INTO `role_tb` VALUES ('1002', '推广户', '推广户', '2018-03-19 10:29:27');
INSERT INTO `role_tb` VALUES ('1003', '用户', '用户', '2018-03-19 10:29:27');
INSERT INTO `role_tb` VALUES ('1004', '普通管理员', '普通管理员', '2018-04-08 11:01:26');

-- ----------------------------
-- Table structure for `sincerity_tb`
-- ----------------------------
DROP TABLE IF EXISTS `sincerity_tb`;
CREATE TABLE `sincerity_tb` (
  `sincerity_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '诚信id',
  `level` int(11) DEFAULT NULL COMMENT '诚信等级',
  `money` decimal(11,2) DEFAULT '0.00' COMMENT '已充值金额',
  `upgrade_money` decimal(11,2) DEFAULT '0.00' COMMENT '升级金额',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `account_id` int(11) DEFAULT NULL COMMENT '账户id外键',
  PRIMARY KEY (`sincerity_id`),
  KEY `INDEX_ACCOUNTID` (`account_id`) USING BTREE,
  KEY `INDEX_CREATEDATE` (`create_date`) USING BTREE,
  KEY `INDEX_UPDATEDATE` (`update_date`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='诚信表';

-- ----------------------------
-- Records of sincerity_tb
-- ----------------------------
INSERT INTO `sincerity_tb` VALUES ('1', '0', '0.00', '10000.00', '2018-03-23 15:16:50', '2018-03-23 15:16:50', '1000');
INSERT INTO `sincerity_tb` VALUES ('2', '0', '0.00', '10000.00', '2018-03-28 10:46:24', '2018-03-28 10:46:26', '1001');
INSERT INTO `sincerity_tb` VALUES ('3', '0', '0.00', '10000.00', '2018-04-08 09:48:13', '2018-04-08 09:48:13', '1003');
INSERT INTO `sincerity_tb` VALUES ('4', '0', '0.00', '10000.00', '2018-04-08 13:37:33', '2018-04-08 13:37:33', '1004');
INSERT INTO `sincerity_tb` VALUES ('5', '0', '0.00', '10000.00', '2018-04-08 17:18:21', '2018-04-08 17:18:21', '1005');
INSERT INTO `sincerity_tb` VALUES ('6', '0', '0.00', '10000.00', '2018-06-27 09:44:33', '2018-06-27 09:44:33', '1006');
INSERT INTO `sincerity_tb` VALUES ('7', '0', '0.00', '10000.00', '2018-07-01 19:22:10', '2018-07-01 19:22:10', '1007');

-- ----------------------------
-- Table structure for `spread_link_tb`
-- ----------------------------
DROP TABLE IF EXISTS `spread_link_tb`;
CREATE TABLE `spread_link_tb` (
  `spread_link_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '推广链接id',
  `link` varchar(255) DEFAULT NULL COMMENT '链接',
  `spread_number` int(11) DEFAULT NULL COMMENT '推广次数',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `mer_id` int(11) DEFAULT NULL COMMENT '商品id,外键',
  `account_id` int(11) DEFAULT NULL COMMENT '推广账户id',
  PRIMARY KEY (`spread_link_id`),
  KEY `INDEX_MERID` (`mer_id`) USING BTREE,
  KEY `INDEX_ACCOUNTID` (`account_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='推广链接表';

-- ----------------------------
-- Records of spread_link_tb
-- ----------------------------
INSERT INTO `spread_link_tb` VALUES ('1', 'http://localhost:9000/home/gooddetail.html?goodid=4&said=1007', '0', '2018-07-01 21:00:26', '4', '1007');

-- ----------------------------
-- Table structure for `spread_link_term_tb`
-- ----------------------------
DROP TABLE IF EXISTS `spread_link_term_tb`;
CREATE TABLE `spread_link_term_tb` (
  `spread_link_term_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '推广链接项id',
  `link` varchar(255) DEFAULT NULL COMMENT '链接',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `mer_id` int(11) DEFAULT NULL COMMENT '商品id,外键',
  PRIMARY KEY (`spread_link_term_id`),
  KEY `INDEX_MERID` (`mer_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='推广链接项表';

-- ----------------------------
-- Records of spread_link_term_tb
-- ----------------------------

-- ----------------------------
-- Table structure for `spread_order_account_tb`
-- ----------------------------
DROP TABLE IF EXISTS `spread_order_account_tb`;
CREATE TABLE `spread_order_account_tb` (
  `spread_order_account_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '推广订单账户id',
  `name` varchar(255) DEFAULT NULL COMMENT '用户名',
  `email` varchar(255) DEFAULT NULL COMMENT '用户邮箱',
  `trade_number` int(11) DEFAULT NULL COMMENT '交易笔数',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `account_id` int(11) DEFAULT NULL COMMENT '推广账户id',
  PRIMARY KEY (`spread_order_account_id`),
  KEY `INDEX_ACCOUNTID` (`account_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='推广订单账户表';

-- ----------------------------
-- Records of spread_order_account_tb
-- ----------------------------

-- ----------------------------
-- Table structure for `spread_record_tb`
-- ----------------------------
DROP TABLE IF EXISTS `spread_record_tb`;
CREATE TABLE `spread_record_tb` (
  `spread_record_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '推广记录id',
  `name` varchar(255) DEFAULT NULL COMMENT '用户名',
  `money` decimal(11,2) DEFAULT NULL COMMENT '交易金额',
  `spread_proportion` decimal(11,2) DEFAULT '1.00' COMMENT '返利分成比例，单位%',
  `spread_money` decimal(11,2) DEFAULT NULL COMMENT '返利金额',
  `link` varchar(255) DEFAULT NULL COMMENT '链接',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `account_id` int(11) DEFAULT NULL COMMENT '推广账户id',
  PRIMARY KEY (`spread_record_id`),
  KEY `INDEX_ACCOUNTID` (`account_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='推广记录表';

-- ----------------------------
-- Records of spread_record_tb
-- ----------------------------
