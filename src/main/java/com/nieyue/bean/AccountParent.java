package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 账户上级
 * @author yy
 *
 */
@ApiModel(value="账户上级",description="账户上级")
public class AccountParent implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 账户上级id
	 */
	@ApiModelProperty(value="账户上级ID",example="账户上级ID")
	private Integer accountParentId;
	/**
	 * 账户id
	 */
	@ApiModelProperty(value="账户id",example="账户id")
	private Integer accountId;
	/**
	 * 真实姓名
	 */
	@ApiModelProperty(value="真实姓名",example="真实姓名")
	private String realname;
	/**
	 * 手机号
	 */
	@ApiModelProperty(value="手机号",example="手机号")
	private String phone;
	/**
	 * 学员数
	 */
	@ApiModelProperty(value="学员数",example="学员数")
	private Integer subordinateNumber;
	/**
	 * 直接上级id
	 */
	@ApiModelProperty(value="直接上级id",example="直接上级id")
	private Integer masterId;
	/**
	 * 真实上级id
	 */
	@ApiModelProperty(value="真实上级id",example="真实上级id")
	private Integer realMasterId;
	/**
	 * 账户等级id
	 */
	@ApiModelProperty(value="账户等级id",example="账户等级id")
	private Integer accountLevelId;
	/**
	 * 等级名
	 */
	@ApiModelProperty(value="等级名",example="等级名")
	private String name;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value="创建时间",example="创建时间")
	private Date createDate;
	/**
	 * 更新时间
	 */
	@ApiModelProperty(value="更新时间",example="更新时间")
	private Date updateDate;
	public Integer getAccountParentId() {
		return accountParentId;
	}
	public void setAccountParentId(Integer accountParentId) {
		this.accountParentId = accountParentId;
	}
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getSubordinateNumber() {
		return subordinateNumber;
	}
	public void setSubordinateNumber(Integer subordinateNumber) {
		this.subordinateNumber = subordinateNumber;
	}
	public Integer getMasterId() {
		return masterId;
	}
	public void setMasterId(Integer masterId) {
		this.masterId = masterId;
	}
	public Integer getRealMasterId() {
		return realMasterId;
	}
	public void setRealMasterId(Integer realMasterId) {
		this.realMasterId = realMasterId;
	}
	public Integer getAccountLevelId() {
		return accountLevelId;
	}
	public void setAccountLevelId(Integer accountLevelId) {
		this.accountLevelId = accountLevelId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
