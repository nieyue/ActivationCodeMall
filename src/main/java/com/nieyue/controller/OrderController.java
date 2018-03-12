package com.nieyue.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nieyue.bean.Order;
import com.nieyue.bean.OrderDetail;
import com.nieyue.exception.NotAnymoreException;
import com.nieyue.exception.NotIsNotExistException;
import com.nieyue.service.OrderDetailService;
import com.nieyue.service.OrderService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * 订单控制类
 * @author yy
 *
 */
@Api(tags={"order"},value="订单",description="订单管理")
@RestController
@RequestMapping("/order")
public class OrderController {
	@Resource
	private OrderService orderService;
	
	@Resource
	private OrderDetailService orderDetailService;
	
	/**
	 * 订单分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "订单列表", notes = "订单分页浏览")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="type",value="类型，1VIP购买，2团购卡团购，3付费课程",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="payType",value="支付类型，1支付宝，2微信,3余额支付,4ios内购",dataType="int", paramType = "query"), 
	  @ApiImplicitParam(name="accountId",value="下单人id外键",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="status",value="订单状态，-1待处理删除，0已完成删除,1待处理，2已完成",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="createDate",value="创建时间",dataType="date-time", paramType = "query"),
	  @ApiImplicitParam(name="updateDate",value="更新时间",dataType="date-time", paramType = "query"),
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="create_date"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList browsePagingOrder(
			@RequestParam(value="type",required=false)Integer type,
			@RequestParam(value="payType",required=false)Integer payType,
			@RequestParam(value="accountId",required=false)Integer accountId,
			@RequestParam(value="status",required=false)Integer status,
			@RequestParam(value="createDate",required=false)Date createDate,
			@RequestParam(value="updateDate",required=false)Date updateDate,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="create_date") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<Order> list = new ArrayList<Order>();
			list= orderService.browsePagingOrder(type,payType,accountId,status,createDate,updateDate,pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotAnymoreException();//没有更多
			}
	}
	/**
	 * 申请订单 类型，1VIP购买，2团购卡团购，3付费课程
	 * @return 
	 */
	@ApiOperation(value = "申请订单", notes = "申请订单,返回值都是list里面的map格式,(生产环境，payType=3,mapkey是“order”,其他的返回 “result”，拿着这个result去请求支付。测试环境都是“order”)")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="type",value="类型，1VIP购买，2团购卡团购，3付费课程",dataType="int", paramType = "query",required=true),
		  @ApiImplicitParam(name="payType",value="支付类型，1支付宝，2微信,3余额支付,4ios内购",dataType="int", paramType = "query",required=true),
		  @ApiImplicitParam(name="accountId",value="下单人id外键",dataType="int", paramType = "query",required=true),
		  @ApiImplicitParam(name="businessId",value="业务id",dataType="int", paramType = "query",required=true),
		  @ApiImplicitParam(name="nickname",value="昵称(团购必填)",dataType="int", paramType = "query"),
		  @ApiImplicitParam(name="phone",value="会员账号，手机号(团购必填)",dataType="string", paramType = "query"),
		  @ApiImplicitParam(name="contactPhone",value="联系电话(团购必填)",dataType="string", paramType = "query"),
		  })
	@RequestMapping(value = "/payment", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList paymentOrder(
			@RequestParam(value="type")Integer type,
			@RequestParam(value="payType")Integer payType,
			@RequestParam(value="accountId")Integer accountId,
			@RequestParam(value="businessId")Integer businessId,
			@RequestParam(value="nickname",required=false)String nickname,
			@RequestParam(value="phone",required=false)String phone,
			@RequestParam(value="contactPhone",required=false)String contactPhone,
			 HttpSession session) {
		List<Map<Object,Object>> list=new ArrayList<>();
		if(payType==3){//余额支付
			boolean b= orderService.balancePaymentOrder(type, payType, accountId, businessId,nickname,phone,contactPhone);
			if(b){
				return ResultUtil.getSlefSRSuccessList(list);
			}
		}else{//微信、支付宝、ios内购支付
			String str = orderService.thirdPartyPaymentOrder(type,payType,accountId,businessId,nickname,phone,contactPhone);
			if(str!=null && !str.equals("")){
			Map<Object,Object> map=new HashMap<Object,Object>();
			map.put("result", str);
			list.add(map);
			return ResultUtil.getSlefSRSuccessList(list);
			}
		}
//		boolean b = orderService.balancePaymentOrder(type, payType, accountId, businessId,nickname,phone,contactPhone);
//		if(b){
//			return ResultUtil.getSlefSRSuccessList(list);
//		}
		return ResultUtil.getSlefSRFailList(list);
	}
	/**
	 * 视频集是否订单
	 * @return
	 */
	@ApiOperation(value = "视频集是否订单。videoSetIsOrder默认0，非订单，1是已经是订单", notes = "视频集是否订单。videoSetIsOrder默认0，非订单，1是已经是订单")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="accountId",value="账户Id",dataType="int", paramType = "query",required=true),
		  @ApiImplicitParam(name="videoSetId",value="视频集id",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/videoSetIsOrder", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList VideoSetOrder(
			@RequestParam(value="accountId")Integer accountId,
			@RequestParam(value="videoSetId")Integer videoSetId,
			HttpSession session)  {
		int videoSetIsOrder=0;//默认0，非订单
		//0是已成功删除
		List<Order> o0l = orderService.browsePagingOrder(3, null, accountId, 0, null, null, 1, Integer.MAX_VALUE, "order_id", "asc");
		for (int i = 0; i < o0l.size(); i++) {
			Order o0 = o0l.get(i);
			Integer o0Id = o0.getOrderId();
			List<OrderDetail> o0dl = orderDetailService.browsePagingOrderDetail(o0Id, null, null, 1, 1, "order_detail_id", "asc");
				if(o0dl.size()==1 &&o0dl.get(0).getBusinessId().equals(videoSetId)){
					videoSetIsOrder=1;
				}
		}
		//是已成功
		List<Order> o2l = orderService.browsePagingOrder(3, null, accountId, 2, null, null, 1, Integer.MAX_VALUE,  "order_id", "asc");
		for (int j = 0; j < o2l.size(); j++) {
			Order o2 = o2l.get(j);
			Integer o2Id = o2.getOrderId();
			List<OrderDetail> o2dl = orderDetailService.browsePagingOrderDetail(o2Id, null, null, 1, 1, "order_detail_id", "asc");
				if(o2dl.size()==1 &&o2dl.get(0).getBusinessId().equals(videoSetId)){
					videoSetIsOrder=1;
				}
		}
		List<Map<Object,Object>> list=new ArrayList<>();
		Map<Object,Object> map=new HashMap<>();
				map.put("videoSetIsOrder", videoSetIsOrder);
				list.add(map);
		return ResultUtil.getSlefSRSuccessList(list);
	}
	/**
	 * 订单修改
	 * @return
	 */
	@ApiOperation(value = "订单修改", notes = "订单修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateOrder(@ModelAttribute Order order,HttpSession session)  {
		boolean um = orderService.updateOrder(order);
		return ResultUtil.getSR(um);
	}
	/**
	 * 订单增加
	 * @return 
	 */
	@ApiOperation(value = "订单增加", notes = "订单增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addOrder(@ModelAttribute Order order, HttpSession session) {
		boolean am = orderService.addOrder(order);
		return ResultUtil.getSR(am);
	}
	/**
	 * 订单删除
	 * @return
	 */
	@ApiOperation(value = "订单删除", notes = "订单删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="orderId",value="订单ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delOrder(@RequestParam("orderId") Integer orderId,HttpSession session)  {
		boolean dm = orderService.delOrder(orderId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * 订单浏览数量
	 * @return
	 */
	@ApiOperation(value = "订单数量", notes = "订单数量查询")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="type",value="类型，1VIP购买，2团购卡团购，3付费课程",dataType="int", paramType = "query"),
		  @ApiImplicitParam(name="payType",value="支付类型，1支付宝，2微信,3余额支付,4ios内购",dataType="int", paramType = "query"),
		  @ApiImplicitParam(name="accountId",value="下单人id外键",dataType="int", paramType = "query"),
		  @ApiImplicitParam(name="status",value="订单状态，-1待处理删除，0已完成删除,1待处理，2已完成",dataType="int", paramType = "query"),
		  @ApiImplicitParam(name="createDate",value="创建时间",dataType="date-time", paramType = "query"),
		  @ApiImplicitParam(name="updateDate",value="更新时间",dataType="date-time", paramType = "query"),
		  })
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(
			@RequestParam(value="type",required=false)Integer type,
			@RequestParam(value="payType",required=false)Integer payType,
			@RequestParam(value="accountId",required=false)Integer accountId,
			@RequestParam(value="status",required=false)Integer status,
			@RequestParam(value="createDate",required=false)Date createDate,
			@RequestParam(value="updateDate",required=false)Date updateDate,
			HttpSession session)  {
		int count = orderService.countAll(type,payType,accountId,status,createDate,updateDate);
		return count;
	}
	/**
	 * 订单单个加载
	 * @return
	 */
	@ApiOperation(value = "订单单个加载", notes = "订单单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="orderId",value="订单ID",dataType="int", paramType = "path",required=true)
		  })
	@RequestMapping(value = "/{orderId}", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList loadOrder(@PathVariable("orderId") Integer orderId,HttpSession session)  {
		List<Order> list = new ArrayList<Order>();
		Order order = orderService.loadOrder(orderId);
			if(order!=null &&!order.equals("")){
				list.add(order);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotIsNotExistException("订单");//不存在
			}
	}
	
}
