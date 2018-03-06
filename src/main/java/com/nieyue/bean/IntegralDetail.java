package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 积分详情
 * @author 聂跃
 */
@ApiModel(value="积分详情",description="积分详情")
public class IntegralDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 积分详情id
	 */
	@ApiModelProperty(value="积分详情id",example="积分详情id")
	private Integer integralDetailId;
	/**
	 * 类型,0失去，1获得
	 */
	@ApiModelProperty(value="类型,0失去，1获得",example="类型,0失去，1获得")
	private Integer type;
	/**
	 * 积分
	 */
	@ApiModelProperty(value="积分",example="积分")
	private Double integral;
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
	/**
	 * 账户id外键
	 */
	@ApiModelProperty(value="账户id外键",example="账户id外键")
	private Integer accountId;
	public Integer getIntegralDetailId() {
		return integralDetailId;
	}
	public void setIntegralDetailId(Integer integralDetailId) {
		this.integralDetailId = integralDetailId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Double getIntegral() {
		return integral;
	}
	public void setIntegral(Double integral) {
		this.integral = integral;
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
