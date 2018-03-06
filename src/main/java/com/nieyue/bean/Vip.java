package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * vip
 * @author yy
 *
 */
@ApiModel(value="vip",description="vip")
public class Vip implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * vipid
	 */
	@ApiModelProperty(value="vipid",example="vipid")
	private Integer vipId;
	/**
	 * 等级名
	 */
	@ApiModelProperty(value="等级名",example="等级名")
	private String name;
	/**
	 * 等级,默认是0，数字越大，等级越高
	 */
	@ApiModelProperty(value="等级,默认是0，数字越大，等级越高",example="等级,默认是0，数字越大，等级越高")
	private Integer level;
	/**
	 * 状态，0到期,1没到期
	 */
	@ApiModelProperty(value="状态，0到期,1没到期",example="状态，0到期,1没到期")
	private Integer status;
	/**
	 * 到期时间
	 */
	@ApiModelProperty(value="到期时间",example="到期时间")
	private Date expireDate;
	/**
	 * vip创建时间
	 */
	@ApiModelProperty(value="创建时间",example="创建时间")
	private Date createDate;
	/**
	 * vip更新时间
	 */
	@ApiModelProperty(value="更新时间",example="更新时间")
	private Date updateDate;
	/**
	 * 账户id外键
	 */
	@ApiModelProperty(value="账户id外键",example="账户id外键")
	private Integer accountId;
	public Integer getVipId() {
		return vipId;
	}
	public void setVipId(Integer vipId) {
		this.vipId = vipId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
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
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
