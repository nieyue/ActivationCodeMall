package com.nieyue.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nieyue.bean.CouponTerm;
import com.nieyue.exception.NotAnymoreException;
import com.nieyue.exception.NotIsNotExistException;
import com.nieyue.service.CouponTermService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * 优惠劵项控制类
 * @author yy
 *
 */
@Api(tags={"couponTerm"},value="优惠劵项",description="优惠劵项管理")
@RestController
@RequestMapping("/couponTerm")
public class CouponTermController {
	@Resource
	private CouponTermService couponTermService;
	
	/**
	 * 优惠劵项分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "优惠劵项列表", notes = "优惠劵项分页浏览")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="update_date"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<CouponTerm>> browsePagingCouponTerm(
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="update_date") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<CouponTerm> list = new ArrayList<CouponTerm>();
			list= couponTermService.browsePagingCouponTerm(pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotAnymoreException();//没有更多
			}
	}
	/**
	 * 优惠劵项修改
	 * @return
	 */
	@ApiOperation(value = "优惠劵项修改", notes = "优惠劵项修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateCouponTerm(@ModelAttribute CouponTerm couponTerm,HttpSession session)  {
		boolean um = couponTermService.updateCouponTerm(couponTerm);
		return ResultUtil.getSR(um);
	}
	/**
	 * 优惠劵项增加
	 * @return 
	 */
	@ApiOperation(value = "优惠劵项增加", notes = "优惠劵项增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addCouponTerm(@ModelAttribute CouponTerm couponTerm, HttpSession session) {
		boolean am = couponTermService.addCouponTerm(couponTerm);
		return ResultUtil.getSR(am);
	}
	/**
	 * 优惠劵项删除
	 * @return
	 */
	@ApiOperation(value = "优惠劵项删除", notes = "优惠劵项删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="CouponTermId",value="优惠劵项ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delCouponTerm(@RequestParam("CouponTermId") Integer couponTermId,HttpSession session)  {
		boolean dm = couponTermService.delCouponTerm(couponTermId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * 优惠劵项浏览数量
	 * @return
	 */
	@ApiOperation(value = "优惠劵项数量", notes = "优惠劵项数量查询")
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(HttpSession session)  {
		int count = couponTermService.countAll();
		return count;
	}
	/**
	 * 优惠劵项单个加载
	 * @return
	 */
	@ApiOperation(value = "优惠劵项单个加载", notes = "优惠劵项单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="couponTermId",value="优惠劵项ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/load", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList<List<CouponTerm>> loadCouponTerm(@RequestParam("couponTermId") Integer couponTermId,HttpSession session)  {
		List<CouponTerm> list = new ArrayList<CouponTerm>();
		CouponTerm couponTerm = couponTermService.loadCouponTerm(couponTermId);
			if(couponTerm!=null &&!couponTerm.equals("")){
				list.add(couponTerm);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotIsNotExistException("优惠劵项");//不存在
			}
	}
	
}
