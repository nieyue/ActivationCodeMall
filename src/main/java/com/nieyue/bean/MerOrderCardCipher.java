package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 商品订单卡密
 * @author yy
 *
 */
@ApiModel(value="商品订单卡密",description="商品订单卡密")
public class MerOrderCardCipher implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 商品订单卡密id
	 */
	@ApiModelProperty(value="商品订单卡密id",example="商品订单卡密id")
	private Integer merOrderCardCipherId;
	
	/**
	 * 卡密码
	 */
	@ApiModelProperty(value="卡密码",example="卡密码")
	private String code;
	/**
	 * 图片地址
	 */
	@ApiModelProperty(value="图片地址",example="图片地址")
	private String imgAddress;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value="创建时间",example="创建时间")
	private Date createDate;
	/**
	 * 订单id外键
	 */
	@ApiModelProperty(value="订单id外键",example="订单id外键")
	private Integer orderId;
	public Integer getMerOrderCardCipherId() {
		return merOrderCardCipherId;
	}
	public void setMerOrderCardCipherId(Integer merOrderCardCipherId) {
		this.merOrderCardCipherId = merOrderCardCipherId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getImgAddress() {
		return imgAddress;
	}
	public void setImgAddress(String imgAddress) {
		this.imgAddress = imgAddress;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
