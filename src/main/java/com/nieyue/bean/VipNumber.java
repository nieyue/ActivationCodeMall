package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * vip购买次数
 * @author 聂跃
 */
@ApiModel(value="vip购买次数",description="vip购买次数")
public class VipNumber implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * vip购买次数id
	 */
	@ApiModelProperty(value="vip购买次数id",example="vip购买次数id")
	private Integer vipNumberId;
	/**
	 * 次数
	 */
	@ApiModelProperty(value="次数",example="次数")
	private Integer number;
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
	 * 购买人id外键
	 */
	@ApiModelProperty(value="购买人id外键",example="购买人id外键")
	private Integer accountId;
	/**
	 * 真实上级id外键
	 */
	@ApiModelProperty(value="真实上级id外键",example="真实上级id外键")
	private Integer realMasterId;
	/**
	 * 状态，1待处理，2已处理，3已超次
	 */
	@ApiModelProperty(value="状态，1待处理，2已处理，3已超次",example="状态，1待处理，2已处理，3已超次")
	private Integer status;
	public Integer getVipNumberId() {
		return vipNumberId;
	}
	public void setVipNumberId(Integer vipNumberId) {
		this.vipNumberId = vipNumberId;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getRealMasterId() {
		return realMasterId;
	}
	public void setRealMasterId(Integer realMasterId) {
		this.realMasterId = realMasterId;
	}
	
}
