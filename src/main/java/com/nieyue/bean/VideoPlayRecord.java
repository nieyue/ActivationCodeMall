package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 视频播放记录
 * @author 聂跃
 */
@ApiModel(value="视频播放记录",description="视频播放记录")
public class VideoPlayRecord implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 视频播放记录id
	 */
	@ApiModelProperty(value="视频播放记录id",example="视频播放记录id")
	private Integer videoPlayRecordId;
	/**
	 * 名称
	 */
	@ApiModelProperty(value="名称",example="名称")
	private String name;
	/**
	 * 封面
	 */
	@ApiModelProperty(value="封面",example="封面")
	private String imgAddress;
	/**
	 * 时长
	 */
	@ApiModelProperty(value="时长",example="时长")
	private String duration;
	/**
	 * 容量，单位byte
	 */
	@ApiModelProperty(value="容量，单位byte",example="容量，单位byte")
	private String size;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value="创建时间",example="创建时间")
	private Date createDate;
	/**
	 * 观看者id外键
	 */
	@ApiModelProperty(value="观看者id外键",example="观看者id外键")
	private Integer accountId;
	/**
	 * 视频id,外键
	 */
	@ApiModelProperty(value="视频id,外键",example="视频id,外键")
	private Integer videoId;
	public Integer getVideoPlayRecordId() {
		return videoPlayRecordId;
	}
	public void setVideoPlayRecordId(Integer videoPlayRecordId) {
		this.videoPlayRecordId = videoPlayRecordId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImgAddress() {
		return imgAddress;
	}
	public void setImgAddress(String imgAddress) {
		this.imgAddress = imgAddress;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	public Integer getVideoId() {
		return videoId;
	}
	public void setVideoId(Integer videoId) {
		this.videoId = videoId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
