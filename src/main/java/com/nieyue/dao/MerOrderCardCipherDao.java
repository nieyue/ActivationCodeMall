package com.nieyue.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.MerOrderCardCipher;

/**
 * 商品订单卡密数据库接口
 * @author yy
 *
 */
@Mapper
public interface MerOrderCardCipherDao {
	/** 新增商品订单卡密*/	
	public boolean addMerOrderCardCipher(MerOrderCardCipher merOrderCardCipher) ;	
	/** 删除商品订单卡密 */	
	public boolean delMerOrderCardCipher(Integer merOrderCardCipherId) ;
	/** 更新商品订单卡密*/	
	public boolean updateMerOrderCardCipher(MerOrderCardCipher merOrderCardCipher);
	/** 装载商品订单卡密 */	
	public MerOrderCardCipher loadMerOrderCardCipher(Integer merOrderCardCipherId);	
	/** 商品订单卡密总共数目 */	
	public int countAll(
			@Param("orderId")Integer orderId
			);	
	/** 分页商品订单卡密信息 */
	public List<MerOrderCardCipher> browsePagingMerOrderCardCipher(
			@Param("orderId")Integer orderId,
			@Param("pageNum")int pageNum,
			@Param("pageSize")int pageSize,
			@Param("orderName")String orderName,
			@Param("orderWay")String orderWay) ;		
}
