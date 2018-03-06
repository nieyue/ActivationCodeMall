package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 视频集类型
 * @author 聂跃
 * @date 2017年4月12日
 */
@ApiModel(value="视频集类型",description="视频集类型")
public class VideoSetCate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 视频集类型id
	 */
	@ApiModelProperty(value="视频集类型id",example="视频集类型id")
	private Integer videoSetCateId;
	/**
	 * 视频集类型名称
	 */
	@ApiModelProperty(value="视频集类型名称",example="视频集类型名称")
	private String name;
	/**
	 *简介
	 */
	@ApiModelProperty(value="简介",example="简介")
	private String summary;
	/**
	 * 视频集类型图标
	 */
	@ApiModelProperty(value="视频集类型图标",example="视频集类型图标")
	private String icon;
	/**
	 * 封面
	 */
	@ApiModelProperty(value="封面",example="封面")
	private String imgAddress;
	/**
	 * 播放总次数
	 */
	@ApiModelProperty(value="播放总次数",example="播放总次数")
	private Integer playNumber;
	/**
	 * 更新时间
	 */
	@ApiModelProperty(value="更新时间",example="更新时间")
	private Date updateDate;
	public Integer getVideoSetCateId() {
		return videoSetCateId;
	}
	public void setVideoSetCateId(Integer videoSetCateId) {
		this.videoSetCateId = videoSetCateId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getImgAddress() {
		return imgAddress;
	}
	public void setImgAddress(String imgAddress) {
		this.imgAddress = imgAddress;
	}
	public Integer getPlayNumber() {
		return playNumber;
	}
	public void setPlayNumber(Integer playNumber) {
		this.playNumber = playNumber;
	}
	
}
