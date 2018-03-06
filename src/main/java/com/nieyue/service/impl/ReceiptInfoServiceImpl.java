package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.ReceiptInfo;
import com.nieyue.dao.ReceiptInfoDao;
import com.nieyue.service.ReceiptInfoService;
@Service
public class ReceiptInfoServiceImpl implements ReceiptInfoService{
	@Resource
	ReceiptInfoDao receiptInfoDao;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addReceiptInfo(ReceiptInfo receiptInfo) {
		receiptInfo.setCreateDate(new Date());
		receiptInfo.setUpdateDate(new Date());
		//if(receiptInfo.getIsDefault()==null||receiptInfo.getIsDefault().equals("")){
			receiptInfo.setIsDefault(0);
		//}
		boolean b = receiptInfoDao.addReceiptInfo(receiptInfo);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delReceiptInfo(Integer receiptInfoId) {
		boolean b = receiptInfoDao.delReceiptInfo(receiptInfoId);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateReceiptInfo(ReceiptInfo receiptInfo) {
		receiptInfo.setUpdateDate(new Date());
		boolean b = receiptInfoDao.updateReceiptInfo(receiptInfo);
		return b;
	}

	@Override
	public ReceiptInfo loadReceiptInfo(Integer receiptInfoId) {
		ReceiptInfo r = receiptInfoDao.loadReceiptInfo(receiptInfoId);
		return r;
	}

	@Override
	public int countAll(
			Integer accountId,
			Integer isDefault,
			Date createDate,
			Date updateDate
			) {
		int c = receiptInfoDao.countAll(
				accountId,
				isDefault,
				createDate,
				updateDate);
		return c;
	}

	@Override
	public List<ReceiptInfo> browsePagingReceiptInfo(
			Integer accountId,
			Integer isDefault,
			Date createDate,
			Date updateDate,
			int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<ReceiptInfo> l = receiptInfoDao.browsePagingReceiptInfo(
				accountId,
				isDefault,
				createDate,
				updateDate,
				pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	
}
