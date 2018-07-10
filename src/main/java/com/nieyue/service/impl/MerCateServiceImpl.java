package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.MerCate;
import com.nieyue.dao.MerCateDao;
import com.nieyue.exception.CommonRollbackException;
import com.nieyue.service.MerCateService;
@Service
public class MerCateServiceImpl implements MerCateService{
	@Resource
	MerCateDao merCateDao;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addMerCate(MerCate merCate) {
		merCate.setUpdateDate(new Date());
		List<MerCate> mcl = this.browsePagingMerCate(1, Integer.MAX_VALUE, "mer_cate_id", "asc");
		mcl.forEach(e->{
			if(e.getName().equals(merCate.getName())){
				throw new CommonRollbackException(e.getName()+"已经存在");
			}
		});
		boolean b = merCateDao.addMerCate(merCate);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delMerCate(Integer merCateId) {
		boolean b = merCateDao.delMerCate(merCateId);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateMerCate(MerCate merCate) {
		merCate.setUpdateDate(new Date());
		boolean b = merCateDao.updateMerCate(merCate);
		return b;
	}

	@Override
	public MerCate loadMerCate(Integer merCateId) {
		MerCate r = merCateDao.loadMerCate(merCateId);
		return r;
	}

	@Override
	public int countAll() {
		int c = merCateDao.countAll();
		return c;
	}

	@Override
	public List<MerCate> browsePagingMerCate(int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<MerCate> l = merCateDao.browsePagingMerCate(pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	
}
