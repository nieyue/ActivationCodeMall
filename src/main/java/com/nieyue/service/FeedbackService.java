package com.nieyue.service;

import java.util.List;

import com.nieyue.bean.Feedback;

/**
 * 意见反馈逻辑层接口
 * @author yy
 *
 */
public interface FeedbackService {
	/** 新增意见反馈 */	
	public boolean addFeedback(Feedback feedback) ;	
	/** 删除意见反馈 */	
	public boolean delFeedback(Integer feedbackId) ;
	/** 更新意见反馈*/	
	public boolean updateFeedback(Feedback feedback);
	/** 装载意见反馈 */	
	public Feedback loadFeedback(Integer feedbackId);	
	/** 意见反馈总共数目 */	
	public int countAll(
			);
	/** 分页意见反馈信息 */
	public List<Feedback> browsePagingFeedback(
			int pageNum,
			int pageSize,
			String orderName,
			String orderWay) ;
}
