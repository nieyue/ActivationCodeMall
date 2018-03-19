package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.MerOrderCardCipher;
import com.nieyue.dao.MerOrderCardCipherDao;
import com.nieyue.service.MerOrderCardCipherService;
@Service
public class MerOrderCardCipherServiceImpl implements MerOrderCardCipherService{
	@Resource
	MerOrderCardCipherDao merOrderCardCipherDao;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addMerOrderCardCipher(MerOrderCardCipher merOrderCardCipher) {
		merOrderCardCipher.setCreateDate(new Date());
		boolean b = merOrderCardCipherDao.addMerOrderCardCipher(merOrderCardCipher);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delMerOrderCardCipher(Integer merOrderCardCipherId) {
		boolean b = merOrderCardCipherDao.delMerOrderCardCipher(merOrderCardCipherId);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateMerOrderCardCipher(MerOrderCardCipher merOrderCardCipher) {
		boolean b = merOrderCardCipherDao.updateMerOrderCardCipher(merOrderCardCipher);
		return b;
	}

	@Override
	public MerOrderCardCipher loadMerOrderCardCipher(Integer merOrderCardCipherId) {
		MerOrderCardCipher r = merOrderCardCipherDao.loadMerOrderCardCipher(merOrderCardCipherId);
		return r;
	}

	@Override
	public int countAll(
			Integer orderId) {
		int c = merOrderCardCipherDao.countAll(orderId);
		return c;
	}

	@Override
	public List<MerOrderCardCipher> browsePagingMerOrderCardCipher(
			Integer orderId,
			int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<MerOrderCardCipher> l = merOrderCardCipherDao.browsePagingMerOrderCardCipher(orderId,pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	
}
