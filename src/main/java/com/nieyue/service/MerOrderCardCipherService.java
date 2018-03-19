package com.nieyue.service;

import java.util.List;

import com.nieyue.bean.MerOrderCardCipher;

/**
 * 商品订单卡密逻辑层接口
 * @author yy
 *
 */
public interface MerOrderCardCipherService {
	/** 新增商品订单卡密 */	
	public boolean addMerOrderCardCipher(MerOrderCardCipher merOrderCardCipher) ;	
	/** 删除商品订单卡密 */	
	public boolean delMerOrderCardCipher(Integer merOrderCardCipherId) ;
	/** 更新商品订单卡密*/	
	public boolean updateMerOrderCardCipher(MerOrderCardCipher merOrderCardCipher);
	/** 装载商品订单卡密 */	
	public MerOrderCardCipher loadMerOrderCardCipher(Integer merOrderCardCipherId);	
	/** 商品订单卡密总共数目 */	
	public int countAll(
			Integer orderId
			);
	/** 分页商品订单卡密信息 */
	public List<MerOrderCardCipher> browsePagingMerOrderCardCipher(
			Integer orderId,
			int pageNum,
			int pageSize,
			String orderName,
			String orderWay);
}
