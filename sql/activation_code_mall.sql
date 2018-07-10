# 数据库 
#创建数据库
DROP DATABASE IF EXISTS activation_code_mall_db;
CREATE DATABASE activation_code_mall_db;

#使用数据库 
use activation_code_mall_db;

#创建角色表
CREATE TABLE role_tb(
role_id int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
name varchar(255) COMMENT '角色名',
duty varchar(255) COMMENT '角色职责',
update_date datetime COMMENT '更新时间',
PRIMARY KEY (role_id)
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='角色表';

#创建权限表
CREATE TABLE permission_tb(
permission_id int(11) NOT NULL AUTO_INCREMENT COMMENT '权限id',
type tinyint(4) COMMENT '权限类型，默认0开放，1鉴权',
manager_name varchar(255) COMMENT '权限管理名',
name varchar(255) COMMENT '权限名',
route varchar(255) unique COMMENT '权限路由',
update_date datetime COMMENT '更新时间',
PRIMARY KEY (permission_id),
INDEX INDEX_TYPE (type) USING BTREE,
INDEX INDEX_MANAGERNAME (manager_name) USING BTREE,
INDEX INDEX_NAME (name) USING BTREE,
INDEX INDEX_ROUTE (route) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='权限表';

#创建角色权限表
CREATE TABLE role_permission_tb(
role_permission_id int(11) NOT NULL AUTO_INCREMENT COMMENT '角色权限id',
region tinyint(4) COMMENT "范围，1公共，2自身",
role_id int(11) COMMENT '角色id,外键',
permission_id int(11) COMMENT '权限id,外键',
update_date datetime COMMENT '更新时间',
PRIMARY KEY (role_permission_id),
UNIQUE KEY UNIQUE_ROLEID_PERMISSIONID (role_id,permission_id),
INDEX INDEX_REGION (region) USING BTREE,
INDEX INDEX_ROLEID (role_id) USING BTREE,
INDEX INDEX_PERMISSIONID (permission_id) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='角色权限表';

#创建等级表
CREATE TABLE account_level_tb(
account_level_id int(11) NOT NULL AUTO_INCREMENT COMMENT '账户等级id',
name varchar(255) COMMENT '等级名',
level tinyint(4) DEFAULT 0 COMMENT '等级,默认是0，数字越大，等级越高',
img_address varchar(255) COMMENT '等级图片',
seller_upgrade_integral decimal(11,2) DEFAULT 0  COMMENT '商户升级积分',
user_upgrade_integral decimal(11,2) DEFAULT 0  COMMENT '用户升级积分',
mark varchar(255) COMMENT '备注（权益）',
update_date datetime COMMENT '更新时间',
PRIMARY KEY (account_level_id),
INDEX INDEX_LEVEL (level) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='等级表';

#创建账户表 
CREATE TABLE account_tb(
account_id int(11) NOT NULL AUTO_INCREMENT COMMENT '账户id',
phone varchar(255) COMMENT '注册手机号',
password varchar(255) COMMENT '密码',
nickname varchar(255) COMMENT '昵称',
icon varchar(255) COMMENT '图像',
sex tinyint(4) DEFAULT 0 COMMENT '性别,默认为0未知，为1男性，为2女性',
country varchar(255) COMMENT '国家,默认中国',
realname varchar(255) COMMENT '真实姓名',
email varchar(255) COMMENT 'email',
safety_grade tinyint(4) COMMENT '安全等级，1低，2中，3高',
auth tinyint(4) COMMENT '认证，0没认证，1审核中，2已认证',
card_secret_receive tinyint(4) COMMENT '卡密接受方式，0全部接收，1本账号内，2邮箱接收，3手机接收',
identity_cards varchar(255) COMMENT '身份证',
identity_cards_hold_img varchar(255) COMMENT '手持身份证上半身照',
identity_cards_front_img varchar(255) COMMENT '身份证正面',
identity_cards_back_img varchar(255) COMMENT '身份证反面',
birthday datetime COMMENT '出生年月',
create_date datetime COMMENT '创建时间',
login_date datetime COMMENT '登陆时间',
status tinyint DEFAULT 0 COMMENT '状态，默认0正常，1锁定，2，异常',
role_id int(11) COMMENT '角色id外键',
role_name varchar(255) COMMENT '角色名',
PRIMARY KEY (account_id),
INDEX INDEX_AUTH (auth) USING BTREE,
INDEX INDEX_PHONE (phone) USING BTREE,
INDEX INDEX_REALNAME (realname) USING BTREE,
INDEX INDEX_CREATEDATE (create_date) USING BTREE,
INDEX INDEX_LOGINDATE (login_date) USING BTREE,
INDEX INDEX_ROLEID (role_id) USING BTREE,
INDEX INDEX_STATUS (status) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='账户表';

#创建银行卡表
CREATE TABLE bank_card_tb(
bank_card_id int(11) NOT NULL AUTO_INCREMENT COMMENT '银行卡id',
realname varchar(255) COMMENT '姓名',
identity varchar(255) COMMENT '身份证',
bank_name varchar(255) COMMENT '银行名',
number varchar(255) COMMENT '银行卡卡号',
phone varchar(255) COMMENT '预留手机号',
create_date datetime COMMENT '创建时间',
update_date datetime COMMENT '更新时间',
account_id int(11) COMMENT '账户id',
PRIMARY KEY (bank_card_id),
INDEX INDEX_ACCOUNTID (account_id) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='银行卡表';

#创建财务表 
CREATE TABLE finance_tb(
finance_id int(11) NOT NULL AUTO_INCREMENT COMMENT '财务id',
password varchar(255) COMMENT '提现密码',
money decimal(11,2) DEFAULT 0 COMMENT '余额',
recharge decimal(11,2) DEFAULT 0 COMMENT '充值金额',
consume decimal(11,2) DEFAULT 0 COMMENT '消费金额',
withdrawals decimal(11,2) DEFAULT 0 COMMENT '提现金额',
refund decimal(11,2) DEFAULT 0 COMMENT '退款金额',
frozen decimal(11,2) DEFAULT 0 COMMENT '冻结金额',
recommend_commission decimal(11,2) DEFAULT 0 COMMENT '推荐佣金',
base_profit decimal(11,2) DEFAULT 0 COMMENT '赠送金钱',
account_id int(11) COMMENT '账户id外键',
create_date datetime COMMENT '创建时间',
update_date datetime COMMENT '更新时间',
PRIMARY KEY (finance_id),
INDEX INDEX_MONEY (money) USING BTREE,
INDEX INDEX_ACCOUNTID (account_id) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='财务表';

#创建财务记录表 
CREATE TABLE finance_record_tb(
finance_record_id int(11) NOT NULL AUTO_INCREMENT COMMENT '财务记录id',
method tinyint(4) COMMENT '方式，1支付宝，2微信,3百度钱包,4Paypal,5网银',
type tinyint(4) COMMENT '类型，1购买商品，2提现记录，3退款记录（用户），4诚信押金，5进账记录，6被退款记录（商户），7申请退保证金',
transaction_number varchar(255) COMMENT '交易单号',
realname varchar(255) COMMENT '真实姓名',
brokerage decimal(11,2) DEFAULT 0 COMMENT '手续费',
money decimal(11,2) DEFAULT 0 COMMENT '金额',
real_money decimal(11,2) DEFAULT 0 COMMENT '实际金额',
status tinyint(4) COMMENT '状态，默认1待处理，2成功，3已拒绝',
create_date datetime   COMMENT '创建时间',
update_date datetime   COMMENT '更新时间',
account_id int(11) COMMENT '账户id,外键',
PRIMARY KEY (finance_record_id),
INDEX INDEX_ACCOUNTID (account_id) USING BTREE,
INDEX INDEX_STATUS (status) USING BTREE,
INDEX INDEX_METHOD (method) USING BTREE,
INDEX INDEX_TYPE (type) USING BTREE,
INDEX INDEX_TRANSACTIONNUMBER (transaction_number) USING BTREE,
INDEX INDEX_CREATEDATE (create_date) USING BTREE,
INDEX INDEX_UPDATEDATE (update_date) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='财务记录表 ';

#创建积分表 
CREATE TABLE integral_tb(
integral_id int(11) NOT NULL AUTO_INCREMENT COMMENT '积分id',
name varchar(255) COMMENT '名称',
level int(11) COMMENT '等级',
integral decimal(11,2) DEFAULT 0 COMMENT '剩余积分',
upgrade_integral decimal(11,2) DEFAULT 0 COMMENT '升级积分',
consume decimal(11,2) DEFAULT 0 COMMENT '消费积分',
base_profit decimal(11,2) DEFAULT 0 COMMENT '赠送积分',
create_date datetime COMMENT '创建时间',
update_date datetime COMMENT '更新时间',
account_id int(11) COMMENT '账户id外键',
PRIMARY KEY (integral_id),
INDEX INDEX_ACCOUNTID (account_id) USING BTREE,
INDEX INDEX_CREATEDATE (create_date) USING BTREE,
INDEX INDEX_UPDATEDATE (update_date) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='积分表';

#创建积分详细表 
CREATE TABLE integral_detail_tb(
integral_detail_id int(11) NOT NULL AUTO_INCREMENT COMMENT '积分详细id',
type tinyint(4) DEFAULT 0 COMMENT '类型,1增长积分',
integral decimal(11,2) DEFAULT 0 COMMENT '积分',
create_date datetime COMMENT '创建时间',
update_date datetime COMMENT '更新时间',
account_id int(11) COMMENT '账户id外键',
PRIMARY KEY (integral_detail_id),
INDEX INDEX_TYPE (type) USING BTREE,
INDEX INDEX_ACCOUNTID (account_id) USING BTREE,
INDEX INDEX_CREATEDATE (create_date) USING BTREE,
INDEX INDEX_UPDATEDATE (update_date) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='积分详细表';

#创建诚信表 
CREATE TABLE sincerity_tb(
sincerity_id int(11) NOT NULL AUTO_INCREMENT COMMENT '诚信id',
level int(11) COMMENT '诚信等级',
money decimal(11,2) DEFAULT 0 COMMENT '已充值金额',
upgrade_money decimal(11,2) DEFAULT 0 COMMENT '升级金额',
create_date datetime COMMENT '创建时间',
update_date datetime COMMENT '更新时间',
account_id int(11) COMMENT '账户id外键',
PRIMARY KEY (sincerity_id),
INDEX INDEX_ACCOUNTID (account_id) USING BTREE,
INDEX INDEX_CREATEDATE (create_date) USING BTREE,
INDEX INDEX_UPDATEDATE (update_date) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='诚信表';

#创建商品搜索表 
CREATE TABLE mer_search_tb(
mer_search_id int(11) NOT NULL AUTO_INCREMENT COMMENT '商品搜索id',
name varchar(255) COMMENT '商品搜索名称',
number int(11) COMMENT '次数',
update_date datetime COMMENT '更新时间',
PRIMARY KEY (mer_search_id),
INDEX INDEX_NAME (name) USING BTREE,
INDEX INDEX_NUMBER (number) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='商品搜索表';

#创建商品公用表 
CREATE TABLE mer_common_tb(
mer_common_id int(11) NOT NULL AUTO_INCREMENT COMMENT '商品公用id',
guide longtext COMMENT '购物指南',
guarantee longtext COMMENT '售后保障',
update_date datetime COMMENT '更新时间',
PRIMARY KEY (mer_common_id)
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='商品公用表';

#创建商品类型表 
CREATE TABLE mer_cate_tb(
mer_cate_id int(11) NOT NULL AUTO_INCREMENT COMMENT '商品类型id',
name varchar(255) COMMENT '商品类型名称',
summary varchar(255) COMMENT '简介',
update_date datetime COMMENT '更新时间',
PRIMARY KEY (mer_cate_id)
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='商品类型表';

#创建商品表 
CREATE TABLE mer_tb(
mer_id int(11) NOT NULL AUTO_INCREMENT COMMENT '商品id',
region tinyint(4) COMMENT '范围，1官网自营，2商户非自营，3商户自营',
platform_proportion decimal(11,2) DEFAULT 5  COMMENT '平台分成比例，单位%',
type tinyint(4) COMMENT '类型，1普通商品，2降价商品，3预购商品',
deliver_end_date datetime  COMMENT '最迟发货时间，预购商品可选',
name varchar(255) COMMENT '名称',
summary longtext COMMENT '简介',
img_address varchar(255) COMMENT '封面',
platform varchar(255) COMMENT '平台',
recommend tinyint(4) DEFAULT 0 COMMENT '推荐，默认0不推，1封推，2推荐',
old_unit_price decimal(11,2) DEFAULT 0 COMMENT '原始单价',
unit_price decimal(11,2) DEFAULT 0 COMMENT '单价',
discount decimal(11,2) DEFAULT 0 COMMENT '折扣',
sale_number int(11) DEFAULT 0  COMMENT '销量',
stock_number int(11)   COMMENT '库存数',
mer_score decimal(11,2)  COMMENT '商品评分',
mer_comment_number int(11)  COMMENT '商品评论数',
details longtext  COMMENT '商品详情',
configuration longtext  COMMENT '配置要求',
install_activation longtext  COMMENT '安装激活',
status tinyint(4) DEFAULT 1 COMMENT '状态0下架,默认1上架',
mer_cate_id int(11) COMMENT '商品类型id,外键',
seller_account_id int(11) COMMENT '商户账户id,外键',
create_date datetime  COMMENT '创建时间',
update_date datetime  COMMENT '更新时间',
PRIMARY KEY (mer_id),
INDEX INDEX_REGION (region) USING BTREE,
INDEX INDEX_TYPE (type) USING BTREE,
INDEX INDEX_NAME (name) USING BTREE,
INDEX INDEX_RECOMMEND (recommend) USING BTREE,
INDEX INDEX_UNITPRICE (unit_price) USING BTREE,
INDEX INDEX_SALENUMBER (sale_number) USING BTREE,
INDEX INDEX_MERSCORE (mer_score) USING BTREE,
INDEX INDEX_VIDEOSETCATEID (mer_cate_id) USING BTREE,
INDEX INDEX_SELLERACCOUNTID (seller_account_id) USING BTREE,
INDEX INDEX_CREATEDATE (create_date) USING BTREE,
INDEX INDEX_UPDATEDATE (update_date) USING BTREE,
INDEX INDEX_STATUS (status) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='商品表';

#创建商品关系表 
CREATE TABLE mer_relation_tb(
mer_relation_id int(11) NOT NULL AUTO_INCREMENT COMMENT '商品关系id',
create_date datetime COMMENT '创建时间',
platform_mer_id int(11) COMMENT '平台商品id',
seller_mer_id int(11) COMMENT '商家商品id',
seller_account_id int(11) COMMENT '商家账户id外键',
PRIMARY KEY (mer_relation_id),
INDEX INDEX_PLATFORMMERID (platform_mer_id) USING BTREE,
INDEX INDEX_SELLERMERID (seller_mer_id) USING BTREE,
INDEX INDEX_SELLERACCOUNTID (seller_account_id) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='商品关系表';

#创建商品卡密表 
CREATE TABLE mer_card_cipher_tb(
mer_card_cipher_id int(11) NOT NULL AUTO_INCREMENT COMMENT '商品卡密id',
code varchar(255) COMMENT '卡密码',
img_address varchar(255) COMMENT '图片地址',
create_date datetime COMMENT '创建时间',
mer_id int(11) COMMENT '商品id外键',
PRIMARY KEY (mer_card_cipher_id),
INDEX INDEX_MERID (mer_id) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='商品卡密表';

#创建商品公告表 
CREATE TABLE mer_notice_tb(
mer_notice_id int(11) NOT NULL AUTO_INCREMENT COMMENT '商品公告id',
title varchar(255) COMMENT '标题',
content longtext COMMENT '内容',
create_date datetime COMMENT '创建时间',
mer_id int(11) COMMENT '商品id外键',
PRIMARY KEY (mer_notice_id),
INDEX INDEX_MERID (mer_id) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='商品公告表';

#创建商品图片表 
CREATE TABLE mer_img_tb(
mer_img_id int(11) NOT NULL AUTO_INCREMENT COMMENT '商品图片id',
img_address varchar(255) COMMENT '图片地址',
number int(11) COMMENT '图片顺序',
create_date datetime COMMENT '创建时间',
update_date datetime COMMENT '更新时间',
mer_id int(11) COMMENT '商品id外键',
PRIMARY KEY (mer_img_id),
INDEX INDEX_MERID (mer_id) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='商品图片表';

#创建购物车商品表 
CREATE TABLE cart_mer_tb(
cart_mer_id int(11) NOT NULL AUTO_INCREMENT COMMENT '购物车商品id',
number int(11) COMMENT '数量',
total_price decimal(11,2) COMMENT '总价',
create_date datetime COMMENT '创建时间',
update_date datetime COMMENT '更新时间',
spread_account_id int(11) COMMENT '推广账户id',
mer_id int(11) COMMENT '商品id外键',
account_id int(11) COMMENT '账户id外键',
PRIMARY KEY (cart_mer_id),
INDEX INDEX_NUMBER (number) USING BTREE,
INDEX INDEX_MERID (mer_id) USING BTREE,
INDEX INDEX_ACCOUNTID (account_id) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='购物车商品表';

#创建订单表 
CREATE TABLE order_tb(
order_id int(11) NOT NULL AUTO_INCREMENT COMMENT '订单id',
order_number varchar(255) COMMENT '订单号',
type tinyint(4) COMMENT '类型，1购买商品，2账户提现，3退款，4诚信押金',
pay_type tinyint(4) COMMENT '方式，1支付宝，2微信,3百度钱包,4Paypal,5网银',
region tinyint(4) COMMENT '范围，1官网自营，2商户非自营，3商户自营',
platform_proportion decimal(11,2) DEFAULT 5  COMMENT '平台分成比例，单位%',
mer_type tinyint(4) COMMENT '商品类型，1普通商品，2降价商品，3预购商品',
create_date datetime  COMMENT '创建时间',
update_date datetime  COMMENT '更新时间',
payment_date datetime  COMMENT '支付日期',
merchant_account_id int(11) COMMENT '商户id',
spread_account_id int(11) COMMENT '推广账户id',
account_id int(11) COMMENT '下单人',
status tinyint(4) COMMENT '订单状态，2待支付，3已支付,4预购商品，5问题单，6已取消，7已删除',
substatus tinyint(4) COMMENT '子状态，2（1待支付），3（1冻结单，2已完成），4（1等待发货），5（1待解决（买家提问后），2解决中（卖家回复后），3申请退款，4已退款，5已解决），6（1正常取消,2订单商品库存不够），7（1已删除）',
PRIMARY KEY (order_id),
INDEX INDEX_TYPE (type) USING BTREE,
INDEX INDEX_PAYTYPE (pay_type) USING BTREE,
INDEX INDEX_REGION (region) USING BTREE,
INDEX INDEX_PLATFORMPROPORTION (platform_proportion) USING BTREE,
INDEX INDEX_MERTYPE (mer_type) USING BTREE,
INDEX INDEX_MERCHATACCOUNTID (merchant_account_id) USING BTREE,
INDEX INDEX_SPREADACCOUNTID (spread_account_id) USING BTREE,
INDEX INDEX_ACCOUNTID (account_id) USING BTREE,
INDEX INDEX_STATUS (status) USING BTREE,
INDEX INDEX_SUBSTATUS (substatus) USING BTREE,
INDEX INDEX_CREATEDATE (create_date) USING BTREE,
INDEX INDEX_UPDATEDATE (update_date) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='订单表';

#创建订单详情表 
CREATE TABLE order_detail_tb(
order_detail_id int(11) NOT NULL AUTO_INCREMENT COMMENT '订单详情id',
name varchar(255) COMMENT '名称',
img_address varchar(255) COMMENT '商品图片',
mer_cate_name varchar(255) COMMENT '类型名名称',
unit_price decimal(11,2) COMMENT '单价',
number int(11) COMMENT '数量',
total_price decimal(11,2) COMMENT '总价',
create_date datetime  COMMENT '创建时间',
update_date datetime  COMMENT '更新时间',
coupon_id int(11) COMMENT '优惠劵id',
mer_id int(11) COMMENT '商品id',
order_id int(11) COMMENT '订单id',
PRIMARY KEY (order_detail_id),
INDEX INDEX_COUPONID (coupon_id) USING BTREE,
INDEX INDEX_MERID (mer_id) USING BTREE,
INDEX INDEX_ORDERID (order_id) USING BTREE,
INDEX INDEX_CREATEDATE (create_date) USING BTREE,
INDEX INDEX_UPDATEDATE (update_date) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='订单详情表';

#创建商品订单卡密表 
CREATE TABLE mer_order_card_cipher_tb(
mer_order_card_cipher_id int(11) NOT NULL AUTO_INCREMENT COMMENT '商品订单卡密id',
code varchar(255) COMMENT '卡密码',
img_address varchar(255) COMMENT '图片地址',
create_date datetime COMMENT '创建时间',
order_id int(11) COMMENT '订单id外键',
PRIMARY KEY (mer_order_card_cipher_id),
INDEX INDEX_ORDERID (order_id) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='商品订单卡密表';

#创建商品订单评论表 
CREATE TABLE mer_order_comment_tb(
mer_order_comment_id int(11) NOT NULL AUTO_INCREMENT COMMENT '商品订单评论id',
mer_score decimal(11,2) COMMENT '评分',
content varchar(255) COMMENT '内容',
create_date datetime COMMENT '创建时间',
mer_id int(11) COMMENT '商品id外键',
order_id int(11) COMMENT '订单id外键',
account_id int(11) COMMENT '评论人id外键',
PRIMARY KEY (mer_order_comment_id),
INDEX INDEX_MERSCORE (mer_score) USING BTREE,
INDEX INDEX_MERID (mer_id) USING BTREE,
INDEX INDEX_ORDERID (order_id) USING BTREE,
INDEX INDEX_ACCOUNTID (account_id) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='商品订单评论表';

#创建商品订单问题表 
CREATE TABLE order_problem_tb(
order_problem_id int(11) NOT NULL AUTO_INCREMENT COMMENT '商品订单问题id',
reason tinyint(4) COMMENT '原因，0其他，1不能充值，2卡密无效，3提示卡密错误',
number tinyint(4) COMMENT '顺序，默认1初次，2二次，以此类推',
content varchar(255) COMMENT '内容',
create_date datetime COMMENT '创建时间',
mer_id int(11) COMMENT '商品id外键',
order_id int(11) COMMENT '订单id外键',
account_id int(11) COMMENT '提问人id外键',
PRIMARY KEY (order_problem_id),
INDEX INDEX_NUMBER (number) USING BTREE,
INDEX INDEX_MERID (mer_id) USING BTREE,
INDEX INDEX_ORDERID (order_id) USING BTREE,
INDEX INDEX_ACCOUNTID (account_id) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='商品订单问题表';

#创建商品订单问题反馈表 
CREATE TABLE order_problem_answer_tb(
order_problem_answer_id int(11) NOT NULL AUTO_INCREMENT COMMENT '商品订单问题反馈id',
content varchar(255) COMMENT '内容',
create_date datetime COMMENT '创建时间',
order_problem_id int(11) COMMENT '商品订单问题id外键',
account_id int(11) COMMENT '回复人id外键',
PRIMARY KEY (order_problem_answer_id),
INDEX INDEX_ORDERPROBLEMID (order_problem_id) USING BTREE,
INDEX INDEX_ACCOUNTID (account_id) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='商品订单问题反馈表';

#创建文章类型表 
CREATE TABLE article_cate_tb(
article_cate_id int(11) NOT NULL AUTO_INCREMENT COMMENT '文章类型id',
name varchar(255) COMMENT '文章类型名称',
update_date datetime COMMENT '更新时间',
PRIMARY KEY (article_cate_id)
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='文章类型表';

#创建文章表 
CREATE TABLE article_tb(
article_id int(11) NOT NULL AUTO_INCREMENT COMMENT '文章id',
title varchar(255) COMMENT '标题',
subtitle varchar(255) COMMENT '子标题',
resource varchar(255) COMMENT '来源',
img_address varchar(255) COMMENT '封面',
redirect_url varchar(255)  COMMENT '跳转url',
content longtext  COMMENT '内容',
reading_number bigint(20) DEFAULT 0 COMMENT '阅读数',
status tinyint(4) COMMENT '状态,下架0，上架1',
create_date datetime COMMENT '创建时间',
update_date datetime COMMENT '更新时间',
article_cate_id int(11) COMMENT '文章类型id外键',
PRIMARY KEY (article_id),
INDEX INDEX_ARTICLECATEID (article_cate_id) USING BTREE,
INDEX INDEX_READINGNUMBER (reading_number) USING BTREE,
INDEX INDEX_CREATEDATE (create_date) USING BTREE,
INDEX INDEX_UPDATEDATE (update_date) USING BTREE,
INDEX INDEX_STATUS (status) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='文章表';

#创建通知表 
CREATE TABLE notice_tb(
notice_id int(11) NOT NULL AUTO_INCREMENT COMMENT '通知id',
region tinyint(4) COMMENT '范围，1全局，2个人',
type tinyint(4) COMMENT '类型，1系统消息，2申请新产品销售，3新增商品类型，4商品申请自营，5提现申请，6问题单反馈，7订单商品动态',
is_mer_dynamic tinyint(4) COMMENT '是否商品动态，默认0不是，1是',
title varchar(255) COMMENT '标题',
img_address varchar(255) COMMENT '图片地址',
content longtext COMMENT '内容',
status tinyint(4) COMMENT '状态，默认为1审核中，2申请成功，3申请失败',
create_date datetime COMMENT '创建时间',
update_date datetime COMMENT '更新时间',
account_id int(11) COMMENT '账户id',
business_id int(11) COMMENT '业务id,外键',
PRIMARY KEY (notice_id),
INDEX INDEX_REGION (region) USING BTREE,
INDEX INDEX_TYPE (type) USING BTREE,
INDEX INDEX_ISMERDYNAMIC (is_mer_dynamic) USING BTREE,
INDEX INDEX_STATUS (status) USING BTREE,
INDEX INDEX_ACCOUNTID (account_id) USING BTREE,
INDEX INDEX_BUSINESSID (business_id) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='通知表';

#创建收货信息表 
CREATE TABLE receipt_info_tb(
receipt_info_id int(11) NOT NULL AUTO_INCREMENT COMMENT '收货信息id',
name varchar(255) COMMENT '收货地址姓名',
phone varchar(255) COMMENT '手机号',
telephone_area varchar(255) COMMENT '电话区号',
telephone varchar(255) COMMENT '电话号',
telephone_extension varchar(255) COMMENT '电话分机',
postcode varchar(255) COMMENT '邮政编码',
country varchar(255) COMMENT '国家',
province varchar(255) COMMENT '省',
city varchar(255) COMMENT '市',
area varchar(255) COMMENT '区',
address varchar(255) COMMENT '收货地址',
is_default tinyint(4) DEFAULT 0 COMMENT '默认为0不是，1是',
create_date datetime   COMMENT '创建时间',
update_date datetime   COMMENT '更新时间',
account_id int(11) COMMENT '账户id,外键',
PRIMARY KEY (receipt_info_id),
INDEX INDEX_ACCOUNTID (account_id) USING BTREE,
INDEX INDEX_ISDEFAULT (is_default) USING BTREE,
INDEX INDEX_CREATEDATE (create_date) USING BTREE,
INDEX INDEX_UPDATEDATE (update_date) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='收货地址表 ';

#创建订单收货信息表 
CREATE TABLE order_receipt_info_tb(
order_receipt_info_id int(11) NOT NULL AUTO_INCREMENT COMMENT '订单收货信息id',
name varchar(255) COMMENT '收货地址姓名',
phone varchar(255) COMMENT '手机号',
telephone_area varchar(255) COMMENT '电话区号',
telephone varchar(255) COMMENT '电话号',
telephone_extension varchar(255) COMMENT '电话分机',
postcode varchar(255) COMMENT '邮政编码',
country varchar(255) COMMENT '国家',
province varchar(255) COMMENT '省',
city varchar(255) COMMENT '市',
area varchar(255) COMMENT '区',
address varchar(255) COMMENT '收货地址',
is_default tinyint(4) DEFAULT 0 COMMENT '默认为0不是，1是',
create_date datetime   COMMENT '创建时间',
update_date datetime   COMMENT '更新时间',
account_id int(11) COMMENT '账户id,外键',
order_id int(11) COMMENT '订单id,外键',
PRIMARY KEY (order_receipt_info_id),
INDEX INDEX_ACCOUNTID (account_id) USING BTREE,
INDEX INDEX_ISDEFAULT (is_default) USING BTREE,
INDEX INDEX_ORDERID (order_id) USING BTREE,
INDEX INDEX_CREATEDATE (create_date) USING BTREE,
INDEX INDEX_UPDATEDATE (update_date) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='订单收货地址表 ';

#创建优惠劵项表 
CREATE TABLE coupon_term_tb(
coupon_term_id int(11) NOT NULL AUTO_INCREMENT COMMENT '优惠劵项id',
name varchar(255) COMMENT '名称',
img_address varchar(255) COMMENT '图片',
discount decimal(11,2) COMMENT '折扣',
content varchar(255) COMMENT '内容',
update_date datetime COMMENT '更新时间',
mer_cate_id int(11) COMMENT '商品类型id,外键',
PRIMARY KEY (coupon_term_id)
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='优惠劵项表';

#创建优惠劵表 
CREATE TABLE coupon_tb(
coupon_id int(11) NOT NULL AUTO_INCREMENT COMMENT '优惠劵id',
code varchar(255) COMMENT '优惠劵码',
name varchar(255) COMMENT '名称',
img_address varchar(255) COMMENT '图片',
discount decimal(11,2) COMMENT '折扣',
content varchar(255) COMMENT '内容',
create_date datetime COMMENT '创建时间',
update_date datetime COMMENT '更新时间',
start_date datetime COMMENT '开始时间',
end_date datetime COMMENT '结束时间',
status tinyint(4)  COMMENT '状态，默认1可用，2已用，3已失效',
mer_cate_id int(11) COMMENT '商品类型id,此商品类型才能使用',
account_id int(11) COMMENT '优惠劵人ID，此id账户才能使用',
coupon_term_id int(11) COMMENT '优惠劵项ID',
order_id int(11) COMMENT '订单id,外键',
PRIMARY KEY (coupon_id),
INDEX INDEX_CODE (code) USING BTREE,
INDEX INDEX_MERCATEID (mer_cate_id) USING BTREE,
INDEX INDEX_COUPONTERMID (coupon_term_id) USING BTREE,
INDEX INDEX_ACCOUNTID (account_id) USING BTREE,
INDEX INDEX_ORDERID (order_id) USING BTREE,
INDEX INDEX_STARTDATE (start_date) USING BTREE,
INDEX INDEX_ENDDATE (end_date) USING BTREE,
INDEX INDEX_CREATEDATE (create_date) USING BTREE,
INDEX INDEX_UPDATEDATE (update_date) USING BTREE,
INDEX INDEX_STATUS(status) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='优惠劵表';

#创建配置表 
CREATE TABLE config_tb(
config_id int(11) NOT NULL AUTO_INCREMENT COMMENT '配置id',
customer_service_phone varchar(255)  COMMENT '客服电话',
order_mer_max_number int(11) DEFAULT 5 COMMENT '最大订单商品数量',
seller_integral_per decimal(11,2) DEFAULT 1  COMMENT '商户每盈利一元钱获得积分',
user_integral_per decimal(11,2) DEFAULT 1  COMMENT '用户每消费一元钱获得积分',
seller_sincerity_upgrade_money decimal(11,2) DEFAULT 0  COMMENT '商户诚信升级金额',
freeze_day_number int(11) DEFAULT 0  COMMENT '冻结天数',
platform_proportion decimal(11,2) DEFAULT 5  COMMENT '平台分成比例，单位%',
spread_proportion decimal(11,2) DEFAULT 1  COMMENT '推广分成比例，单位%',
min_withdrawals decimal(11,2) DEFAULT 500  COMMENT '提现最低额度',
withdrawals_proportion decimal(11,2) DEFAULT 3  COMMENT '提现手续费比例，单位%',
withdrawals_min_brokerage decimal(11,2) DEFAULT 2000  COMMENT '无提现手续费最低额度',
create_date datetime COMMENT '创建时间',
update_date datetime COMMENT '更新时间',
PRIMARY KEY (config_id)
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='配置表';

#创建支付表
CREATE TABLE payment_tb(
payment_id int(11) NOT NULL AUTO_INCREMENT COMMENT '支付id',
subject varchar(255) COMMENT '主题',
body varchar(255) COMMENT '内容',
notify_url varchar(255) COMMENT '异步通知',
type tinyint(4) COMMENT '支付类型，1支付宝，2微信,3百度钱包,4Paypal,5网银',
order_number varchar(255) COMMENT '平台订单号',
money decimal(11,2) COMMENT '金额',
status tinyint(4) DEFAULT 1 COMMENT '状态，1已下单-未支付，2支付成功，3支付失败,4异常',
business_type tinyint(4) COMMENT '业务类型，1购买商品，2账户提现，3退款，4诚信押金',
business_id int(11) COMMENT '业务id,外键',
business_notify_url longtext COMMENT '业务回调,外键',
account_id int(11) COMMENT '账户id,外键',
create_date datetime  COMMENT '创建时间',
update_date datetime  COMMENT '更新时间',
PRIMARY KEY (payment_id),
INDEX INDEX_ORDERNUMBER (order_number) USING BTREE,
INDEX INDEX_TYPE (type) USING BTREE,
INDEX INDEX_BUSINESSTYPE (business_type) USING BTREE,
INDEX INDEX_BUSINESSID (business_id) USING BTREE,
INDEX INDEX_ACCOUNTID (account_id) USING BTREE,
INDEX INDEX_STATUS (status) USING BTREE,
INDEX INDEX_CREATEDATE (create_date) USING BTREE,
INDEX INDEX_UPDATEDATE (update_date) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='支付表';

#创建banner表 
CREATE TABLE banner_tb(
banner_id int(11) NOT NULL AUTO_INCREMENT COMMENT 'bannerid',
name varchar(255)  COMMENT '名称',
type tinyint(4) DEFAULT 0 COMMENT '类型，默认1首页轮播',
img_address varchar(255)  COMMENT '图片',
content varchar(255)  COMMENT '内容',
link varchar(255)  COMMENT '链接',
update_date datetime COMMENT '更新时间',
status tinyint(4)  COMMENT '状态，默认0下架，1上架',
PRIMARY KEY (banner_id),
INDEX INDEX_TYPE (type) USING BTREE,
INDEX INDEX_STATUS (status) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='banner表';

#创建推广链接项表 
CREATE TABLE spread_link_term_tb(
spread_link_term_id int(11) NOT NULL AUTO_INCREMENT COMMENT '推广链接项id',
link varchar(255)  COMMENT '链接',
create_date datetime COMMENT '创建时间',
mer_id int(11) COMMENT '商品id,外键',
PRIMARY KEY (spread_link_term_id),
INDEX INDEX_MERID (mer_id) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='推广链接项表';

#创建推广链接表 
CREATE TABLE spread_link_tb(
spread_link_id int(11) NOT NULL AUTO_INCREMENT COMMENT '推广链接id',
link varchar(255)  COMMENT '链接',
spread_number int(11)  COMMENT '推广次数',
create_date datetime COMMENT '创建时间',
mer_id int(11) COMMENT '商品id,外键',
account_id int(11) COMMENT '推广账户id',
PRIMARY KEY (spread_link_id),
INDEX INDEX_MERID (mer_id) USING BTREE,
INDEX INDEX_ACCOUNTID (account_id) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='推广链接表';

#创建推广订单账户表 
CREATE TABLE spread_order_account_tb(
spread_order_account_id int(11) NOT NULL AUTO_INCREMENT COMMENT '推广订单账户id',
name varchar(255)  COMMENT '用户名',
email varchar(255)  COMMENT '用户邮箱',
trade_number int(11)  COMMENT '交易笔数',
create_date datetime COMMENT '创建时间',
account_id int(11) COMMENT '推广账户id',
PRIMARY KEY (spread_order_account_id),
INDEX INDEX_ACCOUNTID (account_id) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='推广订单账户表';

#创建推广记录表 
CREATE TABLE spread_record_tb(
spread_record_id int(11) NOT NULL AUTO_INCREMENT COMMENT '推广记录id',
name varchar(255)  COMMENT '用户名',
money decimal(11,2) COMMENT '交易金额',
spread_proportion decimal(11,2) DEFAULT 1  COMMENT '返利分成比例，单位%',
spread_money decimal(11,2)   COMMENT '返利金额',
link varchar(255)  COMMENT '链接',
create_date datetime COMMENT '创建时间',
account_id int(11) COMMENT '推广账户id',
PRIMARY KEY (spread_record_id),
INDEX INDEX_ACCOUNTID (account_id) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='推广记录表';

#设置初始角色
INSERT IGNORE INTO role_tb (name,duty,update_date) 
VALUES ("超级管理员","超级管理员",now());
INSERT IGNORE INTO role_tb (name,duty,update_date) 
VALUES ("普通管理员","普通管理员",now());
INSERT IGNORE INTO role_tb (name,duty,update_date) 
VALUES ("商户","商户",now());
INSERT IGNORE INTO role_tb (name,duty,update_date) 
VALUES ("推广户","推广户",now());
INSERT IGNORE INTO role_tb (name,duty,update_date) 
VALUES ("用户","用户",now());
 
#设置初始管理员密码MD5加密123456
INSERT IGNORE INTO account_tb (nickname,phone,email,password,create_date,login_date,role_id,role_name) 
VALUES ("聂跃","15111336587","278076304@qq.com","11874bb6149dd45428da628c9766b252",now(),now(),"1000","超级管理员"); 
#财务
INSERT IGNORE INTO finance_tb (money,recharge,consume,withdrawals,refund,frozen,recommend_commission,base_profit,create_date,update_date,account_id) 
VALUES (10000,0,0,0,0,0,0,0,now(),now(),1000);   
#积分
INSERT IGNORE INTO integral_tb (name,level,integral,upgrade_integral,consume,base_profit,create_date,update_date,account_id) 
VALUES ('初级',0,0,1000,0,0,now(),now(),1000);   
#诚信
INSERT IGNORE INTO sincerity_tb (level,money,upgrade_money,create_date,update_date,account_id) 
VALUES (0,0,10000,now(),now(),1000);   
