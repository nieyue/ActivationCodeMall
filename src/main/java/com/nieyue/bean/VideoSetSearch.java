package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 视频集搜索
 * @author 聂跃
 * @date 2017年4月12日
 */
@ApiModel(value="视频集搜索",description="视频集搜索")
public class VideoSetSearch implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 视频集搜索id
	 */
	@ApiModelProperty(value="视频集搜索id",example="视频集搜索id")
	private Integer videoSetSearchId;
	/**
	 * 视频集搜索名称
	 */
	@ApiModelProperty(value="视频集搜索名称",example="视频集搜索名称")
	private String name;
	/**
	 * 视频集搜索次数
	 */
	@ApiModelProperty(value="视频集搜索次数",example="视频集搜索次数")
	private Integer number;
	/**
	 * 更新时间
	 */
	@ApiModelProperty(value="更新时间",example="更新时间")
	private Date updateDate;
	
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
	public Integer getVideoSetSearchId() {
		return videoSetSearchId;
	}
	public void setVideoSetSearchId(Integer videoSetSearchId) {
		this.videoSetSearchId = videoSetSearchId;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	
	
}
