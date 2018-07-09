package com.nieyue.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.Account;
import com.nieyue.bean.Mer;
import com.nieyue.bean.MerCardCipher;
import com.nieyue.bean.MerCate;
import com.nieyue.bean.MerImg;
import com.nieyue.bean.MerRelation;
import com.nieyue.bean.Notice;
import com.nieyue.business.NoticeBusiness;
import com.nieyue.dao.MerDao;
import com.nieyue.exception.AccountIsNotExistException;
import com.nieyue.exception.CommonRollbackException;
import com.nieyue.exception.MySessionException;
import com.nieyue.service.AccountService;
import com.nieyue.service.MerCardCipherService;
import com.nieyue.service.MerCateService;
import com.nieyue.service.MerImgService;
import com.nieyue.service.MerRelationService;
import com.nieyue.service.MerService;
import com.nieyue.service.NoticeService;
@Service
public class MerServiceImpl implements MerService{
	@Resource
	MerDao merDao;
	@Resource
	MerImgService merImgService;
	@Resource
	MerCateService merCateService;
	@Resource
	AccountService accountService;
	@Resource
	MerCardCipherService merCardCipherService;
	@Resource
	MerRelationService merRelationService;
	@Resource
	NoticeService noticeService;
	@Resource
	NoticeBusiness noticeBusiness;
	
	
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addMer(Mer mer) {
		mer.setCreateDate(new Date());
		mer.setUpdateDate(new Date());
		mer.setSaleNumber(0);
		mer.setStockNumber(0);
		mer.setMerScore(0.0);
		mer.setMerCommentNumber(0);
		boolean b = merDao.addMer(mer);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delMer(Integer merId) {
		boolean b = merDao.delMer(merId);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateMer(Mer mer) {
		mer.setUpdateDate(new Date());
		boolean b = merDao.updateMer(mer);
		return b;
	}

	@Override
	public Mer loadMer(Integer merId) {
		Mer r = merDao.loadMer(merId);
		MerCate merCate=merCateService.loadMerCate(r.getMerCateId());
		r.setMerCate(merCate);
		List<MerImg> mil=merImgService.browsePagingMerImg(merId, 1, Integer.MAX_VALUE, "mer_img_id", "asc");
		r.setMerImgList(mil);
		Account sa = accountService.loadAccount(r.getSellerAccountId());
		r.setSellerAccount(sa);
		return r;
	}

	@Override
	public int countAll(
			Integer region,
			Integer type,
			String name,
			Integer recommend,
			Double unitPrice,
			Integer saleNumber,
			Double merScore,
			Integer merCateId,
			Integer sellerAccountId,
			Date createDate,
			Date updateDate,
			Integer status) {
		int c = merDao.countAll(
				region,
				type,
				name,
				recommend,
				unitPrice,
				saleNumber,
				merScore,
				merCateId,
				sellerAccountId,
				createDate,
				updateDate,
				status);
		return c;
	}

	@Override
	public List<Mer> browsePagingMer(
			Integer region,
			Integer type,
			String name,
			Integer recommend,
			Double unitPrice,
			Integer saleNumber,
			Double merScore,
			Integer merCateId,
			Integer sellerAccountId,
			Date createDate,
			Date updateDate,
			Integer status,
			int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<Mer> l = merDao.browsePagingMer(
				region,
				type,
				name,
				recommend,
				unitPrice,
				saleNumber,
				merScore,
				merCateId,
				sellerAccountId,
				createDate,
				updateDate,
				status,
				pageNum-1, pageSize, orderName, orderWay);
		l.forEach((m)->{
			MerCate merCate=merCateService.loadMerCate(m.getMerCateId());
			m.setMerCate(merCate);
			List<MerImg> mil=merImgService.browsePagingMerImg(m.getMerId(), 1, Integer.MAX_VALUE, "mer_img_id", "asc");
			m.setMerImgList(mil);
			Account sa = accountService.loadAccount(m.getSellerAccountId());
			m.setSellerAccount(sa);
		});
		return l;
	}
	@Override
	public Mer addSellerMer(Integer sellerAccountId, Integer merId, Double unitPrice, Double discount,
			Integer merCardCipherType,String merCardCiphers) {
		if(merCardCiphers.length()<=0){
			throw new CommonRollbackException("缺失卡密");			
		}
		String[] mccs = merCardCiphers.replace(" ","").split(",");
		if(merCardCipherType!=1&&merCardCipherType!=2&&merCardCipherType!=3){
			throw new CommonRollbackException("卡密类型错误");			
		}
		
		if(unitPrice<=0){
			throw new CommonRollbackException("商品单价必须大于0");
		}
		if(discount<=0||discount>1){
			throw new CommonRollbackException("商品折扣大于0，小于等于1");
		}
		Account sa = accountService.loadAccount(sellerAccountId);
		if(sa==null){
			throw new AccountIsNotExistException();
		}
		if(!sa.getRoleName().equals("商户")){
			throw new MySessionException();
		}
		Mer mer = this.loadMer(merId);
		if(mer==null){
			throw new CommonRollbackException("商品不存在");
		}
		if(mer.getStatus()==0){
			throw new CommonRollbackException("商品"+mer.getName()+"已下架");
		}
		if(!mer.getRegion().equals(1)){//1官网自营，2商家非自营，3商家自营
			throw new CommonRollbackException("非官网自营商品");
		}
		//查询商品关系，是否已经存在该商品，存在就返回报错，不存在就继续添加
		List<MerRelation> mrl = merRelationService.browsePagingMerRelation(merId, null, sellerAccountId, 1, 1, "create_date", "asc");
		if(mrl.size()>0){
			throw new CommonRollbackException("此商品已经申报");
		}
		//添加商户商品
		Mer sellerMer=new Mer();
		sellerMer.setPlatformProportion(mer.getPlatformProportion());
		sellerMer.setType(mer.getType());
		sellerMer.setDeliverEndDate(mer.getDeliverEndDate());
		sellerMer.setName(mer.getName());
		sellerMer.setSummary(mer.getSummary());
		sellerMer.setImgAddress(mer.getImgAddress());
		sellerMer.setPlatform(mer.getPlatform());
		sellerMer.setRecommend(mer.getRecommend());
		sellerMer.setMerCateId(mer.getMerCateId());
		sellerMer.setDetails(mer.getDetails());
		sellerMer.setConfiguration(mer.getConfiguration());
		sellerMer.setInstallActivation(mer.getInstallActivation());
		sellerMer.setCreateDate(new Date());
		sellerMer.setUpdateDate(new Date());
		sellerMer.setSaleNumber(0);
		sellerMer.setStockNumber(0);
		sellerMer.setMerScore(0.0);
		sellerMer.setMerCommentNumber(0);
		sellerMer.setOldUnitPrice(mer.getOldUnitPrice());
		sellerMer.setDiscount(discount);
		sellerMer.setUnitPrice(unitPrice);//单价
		sellerMer.setRegion(2);
		sellerMer.setStatus(0);//下架
		sellerMer.setSellerAccountId(sellerAccountId);
		boolean b = this.addMer(sellerMer);
		if(!b){
			throw new CommonRollbackException("增加商品失败");
		}
		//添加商户商品图片
		List<MerImg> sellerMerImgList=new ArrayList<>();
		for (int i = 0; i < mer.getMerImgList().size(); i++) {
			MerImg merImg = mer.getMerImgList().get(i);
			MerImg sellerMerImg=new MerImg();
			sellerMerImg.setCreateDate(new Date());
			sellerMerImg.setUpdateDate(new Date());
			sellerMerImg.setImgAddress(merImg.getImgAddress());
			sellerMerImg.setMerId(sellerMer.getMerId());
			sellerMerImg.setNumber(merImg.getNumber());
			b=merImgService.addMerImg(sellerMerImg);
			if(!b){
				throw new CommonRollbackException("增加商品图片失败");
			}
			sellerMerImgList.add(sellerMerImg);
		}
		sellerMer.setMerImgList(sellerMerImgList);
		//添加商户商品图片卡密
		for (int i = 0; i < mccs.length; i++) {
			String mcc = mccs[i];
			MerCardCipher merCardCipher=new MerCardCipher();
			if(merCardCipherType==1||merCardCipherType==2){
				merCardCipher.setCode(mcc);				
			}
			if(merCardCipherType==3){
				merCardCipher.setImgAddress(mcc);
			}
			merCardCipher.setCreateDate(new Date());
			merCardCipher.setMerId(sellerMer.getMerId());
			b=merCardCipherService.addMerCardCipher(merCardCipher);
			if(!b){
				throw new CommonRollbackException("增加商品卡密失败");
			}
		}
		sellerMer.setMerCate(mer.getMerCate());
		//添加商品关系
		MerRelation merRelation=new MerRelation();
		merRelation.setCreateDate(new Date());
		merRelation.setPlatformMerId(mer.getMerId());
		merRelation.setSellerMerId(sellerMer.getMerId());
		merRelation.setSellerAccountId(sellerAccountId);
		b=merRelationService.addMerRelation(merRelation);
		if(!b){
			throw new CommonRollbackException("增加商品关系失败");
		}
		/**
		 * 发送通知给后台
		 * 申请新产品销售
		 */
		Notice notice=new Notice();
		notice.setBusinessId(sellerMer.getMerId());
		notice.setCreateDate(new Date());
		notice.setUpdateDate(new Date());
		notice.setImgAddress(sellerMer.getImgAddress());
		notice.setAccountId(sellerAccountId);
		notice.setType(2);//类型，1系统消息，2申请新产品销售，3新增商品类型，4商品申请自营，5提现申请，6问题单反馈,7订单商品动态
		noticeService.addNotice(notice);
		return sellerMer;
	}
	@Override
	public Mer addMerCardCipher(Integer sellerAccountId, Integer merId, Integer merCardCipherType,
			String merCardCiphers) {
		if(merCardCiphers.length()<=0){
			throw new CommonRollbackException("缺失卡密");			
		}
		String[] mccs = merCardCiphers.replace(" ","").split(",");
		if(merCardCipherType!=1&&merCardCipherType!=2&&merCardCipherType!=3){
			throw new CommonRollbackException("卡密类型错误");			
		}
		Account sa = accountService.loadAccount(sellerAccountId);
		if(sa==null){
			throw new AccountIsNotExistException();
		}
		if(!sa.getRoleName().equals("商户")){
			throw new MySessionException();
		}
		Mer mer = this.loadMer(merId);
		if(mer==null){
			throw new CommonRollbackException("商品不存在");
		}
		boolean b=false;
		//添加商户商品图片卡密
		for (int i = 0; i < mccs.length; i++) {
			String mcc = mccs[i];
			MerCardCipher merCardCipher=new MerCardCipher();
			if(merCardCipherType==1||merCardCipherType==2){
				merCardCipher.setCode(mcc);				
			}
			if(merCardCipherType==3){
				merCardCipher.setImgAddress(mcc);
			}
			merCardCipher.setCreateDate(new Date());
			merCardCipher.setMerId(mer.getMerId());
			b=merCardCipherService.addMerCardCipher(merCardCipher);
			if(!b){
				throw new CommonRollbackException("增加商品卡密失败");
			}
		}
		mer.setMerCate(mer.getMerCate());
		return mer;
	}

	
}
