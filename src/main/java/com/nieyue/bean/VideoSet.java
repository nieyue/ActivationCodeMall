package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 视频集
 * @author 聂跃
 * @date 2017年4月12日
 */
@ApiModel(value="视频集",description="视频集")
public class VideoSet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 视频集id
	 */
	@ApiModelProperty(value="视频集id",example="视频集id")
	private Integer videoSetId;
	/**
	 * 视频集名称
	 */
	@ApiModelProperty(value="视频集名称",example="视频集名称")
	private String name;
	/**
	 * 封面
	 */
	@ApiModelProperty(value="封面",example="封面")
	private String imgAddress;
	/**
	 * 作者
	 */
	@ApiModelProperty(value="作者",example="作者")
	private String author;
	/**
	 * 简介
	 */
	@ApiModelProperty(value="简介",example="简介")
	private String summary;
	/**
	 * 推荐，默认0不推，1封推，2热门推荐，3专栏
	 */
	@ApiModelProperty(value="推荐，默认0不推，1封推，2热门推荐，3专栏",example="推荐，默认0不推，1封推，2热门推荐，3专栏")
	private Integer recommend;
	/**
	 * 是否收费，0免费，1vip免费，2付费课程
	 */
	@ApiModelProperty(value="是否收费，0免费，1vip免费，2付费课程",example="是否收费，0免费，1vip免费，2付费课程")
	private Integer cost;
	/**
	 * 视频集数
	 */
	@ApiModelProperty(value="总价，默认为0，若为0则免费",example="总价，默认为0，若为0则免费")
	private Double totalPrice;
	/**
	 * 视频集数
	 */
	@ApiModelProperty(value="视频集数",example="视频集数")
	private Integer number;
	/**
	 * 播放总次数
	 */
	@ApiModelProperty(value="播放总次数",example="播放总次数")
	private Integer playNumber;
	/**
	 * 状态0下架,默认1上架
	 */
	@ApiModelProperty(value="状态0下架,默认1上架",example="状态0下架,默认1上架")
	private Integer status;
	/**
	 * 视频集类型id,外键
	 */
	@ApiModelProperty(value="视频集类型id,外键",example="视频集类型id,外键")
	private Integer videoSetCateId;
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
	 * 视频列表
	 */
	@ApiModelProperty(value="视频列表",example="视频列表")
	private List<Video> videoList;
	/**
	 * 视频集标签列表
	 */
	@ApiModelProperty(value="视频集标签列表",example="视频集标签列表")
	private List<VideoSetTag> videoSetTagList;
	public Integer getVideoSetId() {
		return videoSetId;
	}
	public void setVideoSetId(Integer videoSetId) {
		this.videoSetId = videoSetId;
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
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public Integer getRecommend() {
		return recommend;
	}
	public void setRecommend(Integer recommend) {
		this.recommend = recommend;
	}
	public Integer getCost() {
		return cost;
	}
	public void setCost(Integer cost) {
		this.cost = cost;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Integer getPlayNumber() {
		return playNumber;
	}
	public void setPlayNumber(Integer playNumber) {
		this.playNumber = playNumber;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getVideoSetCateId() {
		return videoSetCateId;
	}
	public void setVideoSetCateId(Integer videoSetCateId) {
		this.videoSetCateId = videoSetCateId;
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
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public List<Video> getVideoList() {
		return videoList;
	}
	public void setVideoList(List<Video> videoList) {
		this.videoList = videoList;
	}
	public List<VideoSetTag> getVideoSetTagList() {
		return videoSetTagList;
	}
	public void setVideoSetTagList(List<VideoSetTag> videoSetTagList) {
		this.videoSetTagList = videoSetTagList;
	}
	
}
