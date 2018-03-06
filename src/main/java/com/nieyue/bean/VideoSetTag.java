package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 视频集标签
 * @author 聂跃
 * @date 2017年4月12日
 */
@ApiModel(value="视频集标签",description="视频集标签")
public class VideoSetTag implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 视频集标签id
	 */
	@ApiModelProperty(value="视频集标签id",example="视频集标签id")
	private Integer videoSetTagId;
	/**
	 * 视频集标签名称
	 */
	@ApiModelProperty(value="视频集标签名称",example="视频集标签名称")
	private String name;
	/**
	 * 更新时间
	 */
	@ApiModelProperty(value="更新时间",example="更新时间")
	private Date updateDate;
	/**
	 * 视频集ID
	 */
	@ApiModelProperty(value="视频集ID",example="视频集ID")
	private Integer videoSetId;
	public Integer getVideoSetTagId() {
		return videoSetTagId;
	}
	public void setVideoSetTagId(Integer videoSetTagId) {
		this.videoSetTagId = videoSetTagId;
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
	public Integer getVideoSetId() {
		return videoSetId;
	}
	public void setVideoSetId(Integer videoSetId) {
		this.videoSetId = videoSetId;
	}

	
}
