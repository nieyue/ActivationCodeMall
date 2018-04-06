package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.Mer;
import com.nieyue.bean.MerCardCipher;
import com.nieyue.dao.MerCardCipherDao;
import com.nieyue.service.MerCardCipherService;
import com.nieyue.service.MerService;
@Service
public class MerCardCipherServiceImpl implements MerCardCipherService{
	@Resource
	MerCardCipherDao merCardCipherDao;
	@Resource
	MerService merservice;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addMerCardCipher(MerCardCipher merCardCipher) {
		merCardCipher.setCreateDate(new Date());
		boolean b = merCardCipherDao.addMerCardCipher(merCardCipher);
		Mer mer=merservice.loadMer(merCardCipher.getMerId());
		mer.setStockNumber(mer.getStockNumber()+1);
		b=merservice.updateMer(mer);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delMerCardCipher(Integer merCardCipherId) {
		MerCardCipher merCardCipher=loadMerCardCipher(merCardCipherId);
		boolean b = merCardCipherDao.delMerCardCipher(merCardCipherId);
		Mer mer=merservice.loadMer(merCardCipher.getMerId());
		mer.setStockNumber(mer.getStockNumber()-1);
		b=merservice.updateMer(mer);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateMerCardCipher(MerCardCipher merCardCipher) {
		boolean b = merCardCipherDao.updateMerCardCipher(merCardCipher);
		return b;
	}

	@Override
	public MerCardCipher loadMerCardCipher(Integer merCardCipherId) {
		MerCardCipher r = merCardCipherDao.loadMerCardCipher(merCardCipherId);
		return r;
	}

	@Override
	public int countAll(
			Integer merId) {
		int c = merCardCipherDao.countAll(merId);
		return c;
	}

	@Override
	public List<MerCardCipher> browsePagingMerCardCipher(
			Integer merId,
			int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<MerCardCipher> l = merCardCipherDao.browsePagingMerCardCipher(merId,pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	
}
