package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 账户
 * @author 聂跃
 * @date 2017年4月12日
 */
@ApiModel(value="账户",description="账户")
public class Account implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 账户id
	 */
	@ApiModelProperty(value="账户id",example="账户id")
	private Integer accountId;
	/**
	 * 签名
	 */
	@ApiModelProperty(value="签名",example="签名")
	private String sign;
	/**
	 * 注册手机号
	 */
	@ApiModelProperty(value="注册手机号",example="注册手机号")
	private String phone;
	/**
	 * 密码
	 */
	@ApiModelProperty(value="密码",example="密码")
	private String password;
	/**
	 * 联系手机号，可以任意修改
	 */
	@ApiModelProperty(value="联系手机号，可以任意修改",example="联系手机号，可以任意修改")
	private String contactPhone;
	/**
	 * 昵称
	 */
	@ApiModelProperty(value="昵称",example="昵称")
	private String nickname;
	/**
	 * 图像
	 */
	@ApiModelProperty(value="图像",example="图像")
	private String icon;
	/**
	 * 性别,默认为0未知，为1男性，为2女性
	 */
	@ApiModelProperty(value="性别,默认为0未知，为1男性，为2女性",example="性别,默认为0未知，为1男性，为2女性")
	private Integer sex;
	/**
	 * 年龄
	 */
	@ApiModelProperty(value="年龄",example="年龄")
	private Integer age;
	/**
	 * 国家
	 */
	@ApiModelProperty(value="国家",example="国家")
	private String country;
	/**
	 * 省
	 */
	@ApiModelProperty(value="省",example="省")
	private String province;
	/**
	 * 城市
	 */
	@ApiModelProperty(value="城市",example="城市")
	private String city;
	/**
	 * 真实姓名
	 */
	@ApiModelProperty(value="真实姓名",example="真实姓名")
	private String realname;
	/**
	 * email
	 */
	@ApiModelProperty(value="email",example="email")
	private String email;
	/**
	 * 认证，0没认证，1审核中，2已认证
	 */
	@ApiModelProperty(value="认证，0没认证，1审核中，2已认证",example="认证，0没认证，1审核中，2已认证")
	private Integer auth;
	/**
	 * 身份证
	 */
	@ApiModelProperty(value="身份证",example="身份证")
	private String identityCards;
	/**
	 * 身份证正面
	 */
	@ApiModelProperty(value="身份证正面",example="身份证正面")
	private String identityCardsFrontImg;
	/**
	 * 身份证反面
	 */
	@ApiModelProperty(value="身份证反面",example="身份证反面")
	private String identityCardsBackImg;
	/**
	 * 微信号
	 */
	@ApiModelProperty(value="微信号",example="微信号")
	private String wechat;
	/**
	 * 支付宝账号
	 */
	@ApiModelProperty(value="支付宝账号",example="支付宝账号")
	private String alipay;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value="创建时间",example="创建时间")
	private Date createDate;
	/**
	 * 登陆时间
	 */
	@ApiModelProperty(value="登陆时间",example="登陆时间")
	private Date loginDate;
	/**
	 * 状态 0是正常，1是锁定，2是异常
	 */
	@ApiModelProperty(value="状态 0是正常，1是锁定，2是异常",example="状态 0是正常，1是锁定，2是异常")
	private Integer status;
	/**
	 * 直接上级id外键
	 */
	@ApiModelProperty(value="直接上级id外键",example="直接上级id外键")
	private Integer masterId;
	/**
	 * 角色id外键
	 */
	@ApiModelProperty(value="角色id外键",example="角色id外键")
	private Integer roleId;
	/**
	 * 角色名
	 */
	@ApiModelProperty(value="角色名",example="角色名")
	private String roleName;
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getAuth() {
		return auth;
	}
	public void setAuth(Integer auth) {
		this.auth = auth;
	}
	public String getIdentityCards() {
		return identityCards;
	}
	public void setIdentityCards(String identityCards) {
		this.identityCards = identityCards;
	}
	public String getIdentityCardsFrontImg() {
		return identityCardsFrontImg;
	}
	public void setIdentityCardsFrontImg(String identityCardsFrontImg) {
		this.identityCardsFrontImg = identityCardsFrontImg;
	}
	public String getIdentityCardsBackImg() {
		return identityCardsBackImg;
	}
	public void setIdentityCardsBackImg(String identityCardsBackImg) {
		this.identityCardsBackImg = identityCardsBackImg;
	}
	public String getWechat() {
		return wechat;
	}
	public void setWechat(String wechat) {
		this.wechat = wechat;
	}
	public String getAlipay() {
		return alipay;
	}
	public void setAlipay(String alipay) {
		this.alipay = alipay;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public Integer getMasterId() {
		return masterId;
	}
	public void setMasterId(Integer masterId) {
		this.masterId = masterId;
	}
	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", sign=" + sign + ", phone=" + phone + ", password=" + password
				+ ", contactPhone=" + contactPhone + ", nickname=" + nickname + ", icon=" + icon + ", sex=" + sex
				+ ", age=" + age + ", country=" + country + ", province=" + province + ", city=" + city + ", realname="
				+ realname + ", email=" + email + ", auth=" + auth + ", identityCards=" + identityCards
				+ ", identityCardsFrontImg=" + identityCardsFrontImg + ", identityCardsBackImg=" + identityCardsBackImg
				+ ", wechat=" + wechat + ", alipay=" + alipay + ", createDate=" + createDate + ", loginDate="
				+ loginDate + ", status=" + status + ", masterId=" + masterId + ", roleId=" + roleId + ", roleName="
				+ roleName + "]";
	}
	
	
}
