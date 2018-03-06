package com.nieyue.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.Notice;

/**
 * 通知数据库接口
 * @author yy
 *
 */
@Mapper
public interface NoticeDao {
	/** 新增通知*/	
	public boolean addNotice(Notice notice) ;	
	/** 删除通知 */	
	public boolean delNotice(Integer noticeId) ;
	/** 更新通知*/	
	public boolean updateNotice(Notice notice);
	/** 装载通知 */	
	public Notice loadNotice(Integer noticeId);	
	/** 通知总共数目 */	
	public int countAll(@Param("title")String title,@Param("status")Integer status,@Param("accountId")Integer accountId);	
	/** 分页通知信息 */
	public List<Notice> browsePagingNotice(@Param("title")String title,@Param("status")Integer status,@Param("accountId")Integer accountId,@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("orderName")String orderName,@Param("orderWay")String orderWay) ;		
}
