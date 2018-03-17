package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 商品卡密
 * @author yy
 *
 */
@ApiModel(value="商品卡密",description="商品卡密")
public class MerCardCipher implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 商品卡密id
	 */
	@ApiModelProperty(value="商品卡密id",example="商品卡密id")
	private Integer merCardCipherId;
	
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
	 * 商品id外键
	 */
	@ApiModelProperty(value="商品id外键",example="商品id外键")
	private Integer merId;
	public Integer getMerCardCipherId() {
		return merCardCipherId;
	}
	public void setMerCardCipherId(Integer merCardCipherId) {
		this.merCardCipherId = merCardCipherId;
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
	public Integer getMerId() {
		return merId;
	}
	public void setMerId(Integer merId) {
		this.merId = merId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
