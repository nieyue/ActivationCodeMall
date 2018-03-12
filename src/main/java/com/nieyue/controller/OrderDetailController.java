package com.nieyue.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nieyue.bean.OrderDetail;
import com.nieyue.exception.NotAnymoreException;
import com.nieyue.exception.NotIsNotExistException;
import com.nieyue.service.OrderDetailService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * 订单详情控制类
 * @author yy
 *
 */
@Api(tags={"orderDetail"},value="订单详情",description="订单详情管理")
@RestController
@RequestMapping("/orderDetail")
public class OrderDetailController {
	@Resource
	private OrderDetailService orderDetailService;
	
	/**
	 * 订单详情分页浏览
	 * @param OrderDetailName 商品排序数据库字段
	 * @param OrderDetailWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "订单详情列表", notes = "订单详情分页浏览")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="orderId",value="订单Id",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="createDate",value="创建时间",dataType="date-time", paramType = "query"),
	  @ApiImplicitParam(name="updateDate",value="更新时间",dataType="date-time", paramType = "query"),
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="OrderDetailName",value="排序字段",dataType="string", paramType = "query",defaultValue="create_date"),
	  @ApiImplicitParam(name="OrderDetailWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList browsePagingOrderDetail(
			@RequestParam(value="orderId",required=false)Integer orderId,
			@RequestParam(value="createDate",required=false)Date createDate,
			@RequestParam(value="updateDate",required=false)Date updateDate,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="OrderDetailName",required=false,defaultValue="create_date") String OrderDetailName,
			@RequestParam(value="OrderDetailWay",required=false,defaultValue="desc") String OrderDetailWay)  {
			List<OrderDetail> list = new ArrayList<OrderDetail>();
			list= orderDetailService.browsePagingOrderDetail(orderId,createDate,updateDate,pageNum, pageSize, OrderDetailName, OrderDetailWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotAnymoreException();//没有更多
			}
	}
	/**
	 * 订单详情修改
	 * @return
	 */
	@ApiOperation(value = "订单详情修改", notes = "订单详情修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateOrderDetail(@ModelAttribute OrderDetail orderDetail,HttpSession session)  {
		boolean um = orderDetailService.updateOrderDetail(orderDetail);
		return ResultUtil.getSR(um);
	}
	/**
	 * 订单详情增加
	 * @return 
	 */
	@ApiOperation(value = "订单详情增加", notes = "订单详情增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addOrderDetail(@ModelAttribute OrderDetail orderDetail, HttpSession session) {
		boolean am = orderDetailService.addOrderDetail(orderDetail);
		return ResultUtil.getSR(am);
	}
	/**
	 * 订单详情删除
	 * @return
	 */
	@ApiOperation(value = "订单详情删除", notes = "订单详情删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="OrderDetailId",value="订单详情ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delOrderDetail(@RequestParam("orderDetailId") Integer orderDetailId,HttpSession session)  {
		boolean dm = orderDetailService.delOrderDetail(orderDetailId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * 订单详情浏览数量
	 * @return
	 */
	@ApiOperation(value = "订单详情数量", notes = "订单详情数量查询")
	@ApiImplicitParams({
		@ApiImplicitParam(name="orderId",value="订单Id",dataType="int", paramType = "query"),
		  @ApiImplicitParam(name="createDate",value="创建时间",dataType="date-time", paramType = "query"),
		  @ApiImplicitParam(name="updateDate",value="更新时间",dataType="date-time", paramType = "query")
		  })
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(
			@RequestParam(value="orderId",required=false)Integer orderId,
			@RequestParam(value="createDate",required=false)Date createDate,
			@RequestParam(value="updateDate",required=false)Date updateDate,
			HttpSession session)  {
		int count = orderDetailService.countAll(orderId,createDate,updateDate);
		return count;
	}
	/**
	 * 订单详情单个加载
	 * @return
	 */
	@ApiOperation(value = "订单详情单个加载", notes = "订单详情单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="orderDetailId",value="订单详情ID",dataType="int", paramType = "path",required=true)
		  })
	@RequestMapping(value = "/{orderDetailId}", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList loadOrderDetail(@PathVariable("orderDetailId") Integer orderDetailId,HttpSession session)  {
		List<OrderDetail> list = new ArrayList<OrderDetail>();
		OrderDetail orderDetail = orderDetailService.loadOrderDetail(orderDetailId);
			if(orderDetail!=null &&!orderDetail.equals("")){
				list.add(orderDetail);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotIsNotExistException("订单详情");//不存在
			}
	}
	
}
