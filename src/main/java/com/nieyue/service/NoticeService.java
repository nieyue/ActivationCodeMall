package com.nieyue.service;

import java.util.List;

import com.nieyue.bean.Notice;

/**
 * 通知逻辑层接口
 * @author yy
 *
 */
public interface NoticeService {
	/** 新增通知 */	
	public boolean addNotice(Notice notice) ;	
	/** 删除通知 */	
	public boolean delNotice(Integer noticeId) ;
	/** 更新通知*/	
	public boolean updateNotice(Notice notice);
	/** 装载通知 */	
	public Notice loadNotice(Integer noticeId);	
	/** 通知总共数目 */	
	public int countAll(String title,Integer status,Integer accountId);
	/** 分页通知信息 */
	public List<Notice> browsePagingNotice(String title,Integer status,Integer accountId,int pageNum,int pageSize,String orderName,String orderWay) ;
}
