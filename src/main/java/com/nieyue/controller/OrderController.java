package com.nieyue.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nieyue.bean.Order;
import com.nieyue.exception.CommonNotRollbackException;
import com.nieyue.exception.CommonRollbackException;
import com.nieyue.exception.NotAnymoreException;
import com.nieyue.exception.NotIsNotExistException;
import com.nieyue.service.OrderDetailService;
import com.nieyue.service.OrderService;
import com.nieyue.util.NumberUtil;
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
	  @ApiImplicitParam(name="type",value="类型，1购买商品，2账户提现，3退款，4诚信押金",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="payType",value="方式，1支付宝，2微信,3百度钱包,4Paypal,5网银",dataType="int", paramType = "query"), 
	  @ApiImplicitParam(name="accountId",value="下单人id外键",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="status",value="订单状态，2待支付，3已支付,4预购商品，5问题单，6已取消，7已删除",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="substatus",value="子状态，2（1待支付），3（1冻结单,2已完成），4（1等待发货），5（1待解决（买家提问后），2解决中（卖家回复后），3申请退款，4已退款，5已解决），6（1已取消），7（1已删除）",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="createDate",value="创建时间",dataType="date-time", paramType = "query"),
	  @ApiImplicitParam(name="updateDate",value="更新时间",dataType="date-time", paramType = "query"),
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="create_date"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Order>> browsePagingOrder(
			@RequestParam(value="type",required=false)Integer type,
			@RequestParam(value="payType",required=false)Integer payType,
			@RequestParam(value="accountId",required=false)Integer accountId,
			@RequestParam(value="status",required=false)Integer status,
			@RequestParam(value="substatus",required=false)Integer substatus,
			@RequestParam(value="createDate",required=false)Date createDate,
			@RequestParam(value="updateDate",required=false)Date updateDate,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="create_date") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<Order> list = new ArrayList<Order>();
			list= orderService.browsePagingOrder(type,payType,accountId,status,substatus,createDate,updateDate,pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotAnymoreException();//没有更多
			}
	}
	/**
	 * 用户订单
	 * @return
	 */
	@ApiOperation(value = "用户订单", notes = "订单分页浏览"
			+ "orderCount2待支付总数,orderList2待支付列表"
			+ "orderCount3已支付总数,orderList3已支付列表"
			+ "orderCount4预购商品总数,orderList4预购商品列表"
			+ "orderCount5我的问题单总数,orderList5我的问题单列表"
			+ "orderCount6已取消总数,orderList6已取消列表"
			+ "orderCount5_4已退款总数,orderList5_4已退款列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name="accountId",value="下单人id外键",dataType="int", paramType = "query"),
		@ApiImplicitParam(name="status",value="订单状态，2待支付，3已支付,4预购商品，5问题单，6已取消，7已删除",dataType="int", paramType = "query"),
		@ApiImplicitParam(name="substatus",value="子状态，2（1待支付），3（1冻结单,2已完成），4（1等待发货），5（1待解决（买家提问后），2解决中（卖家回复后），3申请退款，4已退款，5已解决），6（1已取消），7（1已删除）",dataType="int", paramType = "query"),
	})
	@RequestMapping(value = "/userlist", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Map<String,Object>>> browseUserOrder(
			@RequestParam(value="accountId",required=false)Integer accountId,
			@RequestParam(value="status",required=false)Integer status,
			@RequestParam(value="substatus",required=false)Integer substatus
			)  {
		//类型，1购买商品，2账户提现，3退款，4诚信押金"
		List<Order> orderListAll= orderService.browsePagingOrder(1,null,accountId,status,substatus,null,null,1, Integer.MAX_VALUE, "order_id", "asc");
		if(orderListAll.size()>0){
			List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
			Map<String,Object> map=new HashMap<>();
			List<Order> orderList2 = new ArrayList<Order>();
			List<Order> orderList3 = new ArrayList<Order>();
			List<Order> orderList4 = new ArrayList<Order>();
			List<Order> orderList5 = new ArrayList<Order>();
			List<Order> orderList5_4 = new ArrayList<Order>();
			List<Order> orderList6 = new ArrayList<Order>();
			orderListAll.forEach((o)->{
				if(o.getStatus().equals(2)&&o.getSubstatus().equals(1)){
					orderList2.add(o);
				}
				if(o.getStatus().equals(3)){
					orderList3.add(o);
				}
				if(o.getStatus().equals(4)&&o.getSubstatus().equals(1)){
					orderList4.add(o);
				}
				if(o.getStatus().equals(5)){
					if(o.getSubstatus().equals(1)
							||o.getSubstatus().equals(2)
							||o.getSubstatus().equals(5)
							){//除去申请退款和已退款
						orderList5.add(o);						
					}else{
						if(o.getSubstatus().equals(4)){
							orderList5_4.add(o);
						}
					}
				}
				if(o.getStatus().equals(6)&&o.getSubstatus().equals(1)){
					orderList6.add(o);
				}
			});
			map.put("orderCount2", orderList2.size());//待支付总数
			map.put("orderList2", orderList2);//待支付列表
			map.put("orderCount3", orderList3.size());//已支付总数
			map.put("orderList3", orderList3);//已支付列表
			map.put("orderCount4", orderList4.size());//预购商品总数
			map.put("orderList4", orderList4);//预购商品列表
			map.put("orderCount5", orderList5.size());//我的问题单总数
			map.put("orderList5", orderList5);//我的问题单列表
			map.put("orderCount6", orderList6.size());//已取消总数
			map.put("orderList6", orderList6);//已取消列表
			map.put("orderCount5_4", orderList5_4.size());//已退款总数
			map.put("orderList5_4", orderList5_4);//已退款列表
			list.add(map);
			return ResultUtil.getSlefSRSuccessList(list);
		}else{
			throw new NotAnymoreException();//没有更多
		}
	}
	/**
	 * 购买商品订单支付
	 * @return 
	 * @throws CommonNotRollbackException 
	 */
	@ApiOperation(value = "购买商品订单支付", notes = "购买商品订单支付,")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="payType",value="支付方式，1支付宝，2微信,3百度钱包,4Paypal,5网银",dataType="int", paramType = "query",required=true),
		  @ApiImplicitParam(name="accountId",value="下单人id外键",dataType="int", paramType = "query",required=true),
		  @ApiImplicitParam(name="orderIds",value="订单id列表如:\"101,3,44\"",dataType="string", paramType = "query")
		  })
	@RequestMapping(value = "/payment", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<String>> paymentOrder(
			@RequestParam(value="payType")Integer payType,
			@RequestParam(value="accountId")Integer accountId,
			@RequestParam(value="orderIds")String orderIds,
			 HttpSession session) throws CommonNotRollbackException {
		List<String> list=new ArrayList<>();
		String s = orderService.thirdPartyPaymentOrder( payType, accountId, orderIds);
		if(StringUtils.isEmpty(s)){
			return ResultUtil.getSlefSRFailList(list);			
		}
		list.add(s);
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
	 * 订单批量删除
	 * @return
	 */
	@ApiOperation(value = "订单批量删除", notes = "订单批量删除")
	@ApiImplicitParams({
		@ApiImplicitParam(name="orderIds",value="订单批量删除ID集合数组，\"22,33,44,53,3\"",dataType="string", paramType = "query",required=true)
	})
	@RequestMapping(value = "/deleteBatch", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delBatchOrder(@RequestParam("orderIds") String orderIds,HttpSession session)  {
		String[] ids = orderIds.replace(" ","").split(",");
		boolean dm=false;
		for (int i = 0; i < ids.length; i++) {
			if(!NumberUtil.isNumeric(ids[i])){
				throw new CommonRollbackException("参数错误");
			}
		}
		for (int i = 0; i < ids.length; i++) {
			Order order = orderService.loadOrder(new Integer(ids[i]));
			order.setStatus(7);//已删除
			order.setSubstatus(1);
			dm =orderService.updateOrder(order);
		}
		return ResultUtil.getSR(dm);
	}
	/**
	 * 订单浏览数量
	 * @return
	 */
	@ApiOperation(value = "订单数量", notes = "订单数量查询")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="type",value="类型，1购买商品，2账户提现，3退款，4诚信押金",dataType="int", paramType = "query"),
		  @ApiImplicitParam(name="payType",value="方式，1支付宝，2微信,3百度钱包,4Paypal,5网银",dataType="int", paramType = "query"),
		  @ApiImplicitParam(name="accountId",value="下单人id外键",dataType="int", paramType = "query"),
		  @ApiImplicitParam(name="status",value="订单状态，2待支付，3已支付,4预购商品，5问题单，6已取消，7已删除",dataType="int", paramType = "query"),
		  @ApiImplicitParam(name="substatus",value="子状态，2（1待支付），3（1冻结单,2已完成），4（1等待发货），5（1待解决（买家提问后），2解决中（卖家回复后），3申请退款，4已退款，5已解决），6（1已取消），7（1已删除）",dataType="int", paramType = "query"),
		  @ApiImplicitParam(name="createDate",value="创建时间",dataType="date-time", paramType = "query"),
		  @ApiImplicitParam(name="updateDate",value="更新时间",dataType="date-time", paramType = "query"),
		  })
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(
			@RequestParam(value="type",required=false)Integer type,
			@RequestParam(value="payType",required=false)Integer payType,
			@RequestParam(value="accountId",required=false)Integer accountId,
			@RequestParam(value="status",required=false)Integer status,
			@RequestParam(value="substatus",required=false)Integer substatus,
			@RequestParam(value="createDate",required=false)Date createDate,
			@RequestParam(value="updateDate",required=false)Date updateDate,
			HttpSession session)  {
		int count = orderService.countAll(type,payType,accountId,status,substatus,createDate,updateDate);
		return count;
	}
	/**
	 * 订单单个加载
	 * @return
	 */
	@ApiOperation(value = "订单单个加载", notes = "订单单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="orderId",value="订单ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/load", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList<List<Order>> loadOrder(@RequestParam("orderId") Integer orderId,HttpSession session)  {
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
