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

import com.nieyue.bean.OrderReceiptInfo;
import com.nieyue.exception.NotAnymoreException;
import com.nieyue.exception.NotIsNotExistException;
import com.nieyue.service.OrderReceiptInfoService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * 订单收货信息控制类
 * @author yy
 *
 */
@Api(tags={"orderReceiptInfo"},value="订单收货信息",description="订单收货信息管理")
@RestController
@RequestMapping("/orderReceiptInfo")
public class OrderReceiptInfoController {
	@Resource
	private OrderReceiptInfoService orderReceiptInfoService;
	
	/**
	 * 订单收货信息分页浏览
	 * @param orderName 订单收货信息排序数据库字段
	 * @param orderWay 订单收货信息排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "订单收货信息", notes = "订单收货信息分页浏览")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="accountId",value="账户ID",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="isDefault",value="默认为0不是，1是",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="orderId",value="订单id",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="createDate",value="创建时间",dataType="date-time", paramType = "query"),
	  @ApiImplicitParam(name="updateDate",value="更新时间",dataType="date-time", paramType = "query"),
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="order_receipt_info_id"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<OrderReceiptInfo>> browsePagingOrderReceiptInfo(
			@RequestParam(value="accountId",required=false)Integer accountId,
			@RequestParam(value="isDefault",required=false)Integer isDefault,
			@RequestParam(value="orderId",required=false)Integer orderId,
			@RequestParam(value="createDate",required=false)Date createDate,
			@RequestParam(value="updateDate",required=false)Date updateDate,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="order_receipt_info_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<OrderReceiptInfo> list = new ArrayList<OrderReceiptInfo>();
			list= orderReceiptInfoService.browsePagingOrderReceiptInfo(accountId,isDefault,orderId,createDate,updateDate,pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotAnymoreException();//没有更多
			}
	}
	/**
	 * 订单收货信息修改
	 * @return
	 */
	@ApiOperation(value = "订单收货信息修改", notes = "订单收货信息修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateOrderReceiptInfo(@ModelAttribute OrderReceiptInfo orderReceiptInfo,HttpSession session)  {
		boolean um =orderReceiptInfoService.updateOrderReceiptInfo(orderReceiptInfo);
		return ResultUtil.getSR(um); 
	}
	/**
	 * 订单收货信息增加
	 * @return 
	 */
	@ApiOperation(value = "订单收货信息增加", notes = "订单收货信息增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addOrderReceiptInfo(@ModelAttribute OrderReceiptInfo orderReceiptInfo, HttpSession session) {
		boolean am = orderReceiptInfoService.addOrderReceiptInfo(orderReceiptInfo);
		return ResultUtil.getSR(am);
	}
	/**
	 * 订单收货信息删除
	 * @return
	 */
	@ApiOperation(value = "订单收货信息删除", notes = "订单收货信息删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="orderReceiptInfoId",value="订单收货信息ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delOrderReceiptInfo(@RequestParam("OrderReceiptInfoId") Integer orderReceiptInfoId,HttpSession session)  {
		boolean dm = orderReceiptInfoService.delOrderReceiptInfo(orderReceiptInfoId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * 订单收货信息浏览数量
	 * @return
	 */
	@ApiOperation(value = "订单收货信息数量", notes = "订单收货信息数量查询")
	@ApiImplicitParams({
		@ApiImplicitParam(name="accountId",value="订单收货信息id",dataType="int", paramType = "query"),
		  @ApiImplicitParam(name="isDefault",value="'默认为0不是，1是",dataType="int", paramType = "query"),
		  @ApiImplicitParam(name="orderId",value="'订单id",dataType="int", paramType = "query"),
		  @ApiImplicitParam(name="createDate",value="创建时间",dataType="date-time", paramType = "query"),
		  @ApiImplicitParam(name="updateDate",value="更新时间",dataType="date-time", paramType = "query")
		  })
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(
			@RequestParam(value="accountId",required=false)Integer accountId,
			@RequestParam(value="isDefault",required=false)Integer isDefault,
			@RequestParam(value="orderId",required=false)Integer orderId,
			@RequestParam(value="createDate",required=false)Date createDate,
			@RequestParam(value="updateDate",required=false)Date updateDate,
			HttpSession session)  {
		int count = orderReceiptInfoService.countAll(accountId,isDefault,orderId,createDate,updateDate);
		return count;
	}
	/**
	 * 订单收货信息单个加载
	 * @return
	 */
	@ApiOperation(value = "订单收货信息单个加载", notes = "订单收货信息单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="orderReceiptInfoId",value="订单收货信息ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/load", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList<List<OrderReceiptInfo>> loadOrderReceiptInfo(@RequestParam("orderReceiptInfoId") Integer orderReceiptInfoId,HttpSession session)  {
		List<OrderReceiptInfo> list = new ArrayList<OrderReceiptInfo>();
		OrderReceiptInfo orderReceiptInfo = orderReceiptInfoService.loadOrderReceiptInfo(orderReceiptInfoId);
			if(orderReceiptInfo!=null &&!orderReceiptInfo.equals("")){
				list.add(orderReceiptInfo);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotIsNotExistException("订单收货信息");//不存在
			}
	}
	
}
