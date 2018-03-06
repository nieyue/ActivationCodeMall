package com.nieyue.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.Feedback;

/**
 * 反馈意见数据库接口
 * @author yy
 *
 */
@Mapper
public interface FeedbackDao {
	/** 新增反馈意见*/	
	public boolean addFeedback(Feedback feedback) ;	
	/** 删除反馈意见 */	
	public boolean delFeedback(Integer feedbackId) ;
	/** 更新反馈意见*/	
	public boolean updateFeedback(Feedback feedback);
	/** 装载反馈意见 */	
	public Feedback loadFeedback(Integer feedbackId);	
	/** 反馈意见总共数目 */	
	public int countAll(
			);	
	/** 分页反馈意见信息 */
	public List<Feedback> browsePagingFeedback(
			@Param("pageNum")int pageNum,
			@Param("pageSize")int pageSize,
			@Param("orderName")String orderName,
			@Param("orderWay")String orderWay) ;		
}
