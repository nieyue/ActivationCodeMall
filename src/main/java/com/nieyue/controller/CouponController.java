package com.nieyue.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nieyue.bean.Coupon;
import com.nieyue.exception.NotAnymoreException;
import com.nieyue.exception.NotIsNotExistException;
import com.nieyue.service.CouponService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * 优惠劵控制类
 * @author yy
 *
 */
@Api(tags={"coupon"},value="优惠劵",description="优惠劵管理")
@RestController
@RequestMapping("/coupon")
public class CouponController {
	@Resource
	private CouponService couponService;
	
	/**
	 * 优惠劵分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "优惠劵列表", notes = "优惠劵分页浏览")
	@ApiImplicitParams({
		@ApiImplicitParam(name="code",value="优惠劵码",dataType="string", paramType = "query"),
		@ApiImplicitParam(name="merCateId",value="商品类型id,此商品类型才能使用",dataType="int", paramType = "query"),
		@ApiImplicitParam(name="couponTermId",value="优惠劵项ID",dataType="int", paramType = "query"),
		@ApiImplicitParam(name="accountId",value="优惠劵人ID，此id账户才能使用",dataType="int", paramType = "query"),
		@ApiImplicitParam(name="orderId",value="订单ID",dataType="int", paramType = "query"),
		@ApiImplicitParam(name="startDate",value="开始时间",dataType="date-time", paramType = "query"),
		@ApiImplicitParam(name="endDate",value="结束时间",dataType="date-time", paramType = "query"),
		@ApiImplicitParam(name="createDate",value="创建时间",dataType="date-time", paramType = "query"),
		@ApiImplicitParam(name="updateDate",value="更新时间",dataType="date-time", paramType = "query"),
		@ApiImplicitParam(name="status",value="状态，默认1可用，2已用，3已失效",dataType="int", paramType = "query"),
	    @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	    @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	    @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="update_date"),
	    @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Coupon>> browsePagingCoupon(
			@RequestParam(value="code",required=false)String code,
			@RequestParam(value="merCateId",required=false)Integer merCateId,
			@RequestParam(value="couponTermId",required=false)Integer couponTermId,
			@RequestParam(value="accountId",required=false)Integer accountId,
			@RequestParam(value="orderId",required=false)Integer orderId,
			@RequestParam(value="startDate",required=false)Date startDate,
			@RequestParam(value="endDate",required=false)Date endDate,
			@RequestParam(value="createDate",required=false)Date createDate,
			@RequestParam(value="updateDate",required=false)Date updateDate,
			@RequestParam(value="status",required=false)Integer status,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="update_date") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<Coupon> list = new ArrayList<Coupon>();
			list= couponService.browsePagingCoupon(
					code,merCateId,couponTermId,accountId,orderId,startDate,endDate,createDate,updateDate,status,
					pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotAnymoreException();//没有更多
			}
	}
	/**
	 * 优惠劵修改
	 * @return
	 */
	@ApiOperation(value = "优惠劵修改", notes = "优惠劵修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateCoupon(@ModelAttribute Coupon coupon,HttpSession session)  {
		boolean um = couponService.updateCoupon(coupon);
		return ResultUtil.getSR(um);
	}
	/**
	 * 优惠劵增加
	 * @return 
	 */
	@ApiOperation(value = "优惠劵增加", notes = "优惠劵增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addCoupon(@ModelAttribute Coupon coupon, HttpSession session) {
		boolean am = couponService.addCoupon(coupon);
		return ResultUtil.getSR(am);
	}
	/**
	 * 优惠劵删除
	 * @return
	 */
	@ApiOperation(value = "优惠劵删除", notes = "优惠劵删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="couponId",value="优惠劵ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delCoupon(@RequestParam("couponId") Integer couponId,HttpSession session)  {
		boolean dm = couponService.delCoupon(couponId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * 优惠劵浏览数量
	 * @return
	 */
	@ApiOperation(value = "优惠劵数量", notes = "优惠劵数量查询")
	@ApiImplicitParams({
		@ApiImplicitParam(name="code",value="优惠劵码",dataType="string", paramType = "query"),
		@ApiImplicitParam(name="merCateId",value="商品类型id,此商品类型才能使用",dataType="int", paramType = "query"),
		@ApiImplicitParam(name="couponTermId",value="优惠劵项ID",dataType="int", paramType = "query"),
		@ApiImplicitParam(name="accountId",value="优惠劵人ID，此id账户才能使用",dataType="int", paramType = "query"),
		@ApiImplicitParam(name="orderId",value="订单ID",dataType="int", paramType = "query"),
		@ApiImplicitParam(name="startDate",value="开始时间",dataType="date-time", paramType = "query"),
		@ApiImplicitParam(name="endDate",value="结束时间",dataType="date-time", paramType = "query"),
		@ApiImplicitParam(name="createDate",value="创建时间",dataType="date-time", paramType = "query"),
		@ApiImplicitParam(name="updateDate",value="更新时间",dataType="date-time", paramType = "query"),
		@ApiImplicitParam(name="status",value="状态，默认1可用，2已用，3已失效",dataType="int", paramType = "query")
	  })
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(
			@RequestParam(value="code",required=false)String code,
			@RequestParam(value="merCateId",required=false)Integer merCateId,
			@RequestParam(value="couponTermId",required=false)Integer couponTermId,
			@RequestParam(value="accountId",required=false)Integer accountId,
			@RequestParam(value="orderId",required=false)Integer orderId,
			@RequestParam(value="startDate",required=false)Date startDate,
			@RequestParam(value="endDate",required=false)Date endDate,
			@RequestParam(value="createDate",required=false)Date createDate,
			@RequestParam(value="updateDate",required=false)Date updateDate,
			@RequestParam(value="status",required=false)Integer status,
			HttpSession session)  {
		int count = couponService.countAll(code,merCateId,couponTermId,accountId,orderId,startDate,endDate,createDate,updateDate,status);
		return count;
	}
	/**
	 * 优惠劵单个加载
	 * @return
	 */
	@ApiOperation(value = "优惠劵单个加载", notes = "优惠劵单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="couponId",value="优惠劵ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/load", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList<List<Coupon>> loadCoupon(@RequestParam("couponId") Integer couponId,HttpSession session)  {
		List<Coupon> list = new ArrayList<Coupon>();
		Coupon coupon = couponService.loadCoupon(couponId);
			if(coupon!=null &&!coupon.equals("")){
				list.add(coupon);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotIsNotExistException("优惠劵");//不存在
			}
	}
	
}
