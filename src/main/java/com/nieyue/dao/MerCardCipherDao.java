package com.nieyue.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.MerCardCipher;

/**
 * 商品卡密数据库接口
 * @author yy
 *
 */
@Mapper
public interface MerCardCipherDao {
	/** 新增商品卡密*/	
	public boolean addMerCardCipher(MerCardCipher merCardCipher) ;	
	/** 删除商品卡密 */	
	public boolean delMerCardCipher(Integer merCardCipherId) ;
	/** 更新商品卡密*/	
	public boolean updateMerCardCipher(MerCardCipher merCardCipher);
	/** 装载商品卡密 */	
	public MerCardCipher loadMerCardCipher(Integer merCardCipherId);	
	/** 商品卡密总共数目 */	
	public int countAll(
			@Param("merId")Integer merId
			);	
	/** 分页商品卡密信息 */
	public List<MerCardCipher> browsePagingMerCardCipher(
			@Param("merId")Integer merId,
			@Param("pageNum")int pageNum,
			@Param("pageSize")int pageSize,
			@Param("orderName")String orderName,
			@Param("orderWay")String orderWay) ;		
}
