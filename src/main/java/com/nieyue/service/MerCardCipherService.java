package com.nieyue.service;

import java.util.List;

import com.nieyue.bean.MerCardCipher;

/**
 * 商品卡密逻辑层接口
 * @author yy
 *
 */
public interface MerCardCipherService {
	/** 新增商品卡密 */	
	public boolean addMerCardCipher(MerCardCipher merCardCipher) ;	
	/** 删除商品卡密 */	
	public boolean delMerCardCipher(Integer merCardCipherId) ;
	/** 更新商品卡密*/	
	public boolean updateMerCardCipher(MerCardCipher merCardCipher);
	/** 装载商品卡密 */	
	public MerCardCipher loadMerCardCipher(Integer merCardCipherId);	
	/** 商品卡密总共数目 */	
	public int countAll(
			Integer merId
			);
	/** 分页商品卡密信息 */
	public List<MerCardCipher> browsePagingMerCardCipher(
			Integer merId,
			int pageNum,
			int pageSize,
			String orderName,
			String orderWay) ;
}
