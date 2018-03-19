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

import com.nieyue.bean.OrderProblem;
import com.nieyue.exception.NotAnymoreException;
import com.nieyue.exception.NotIsNotExistException;
import com.nieyue.service.OrderProblemService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * 商品订单问题控制类
 * @author yy
 *
 */
@Api(tags={"orderProblem"},value="商品订单问题",description="商品订单问题管理")
@RestController
@RequestMapping("/orderProblem")
public class OrderProblemController {
	@Resource
	private OrderProblemService orderProblemService;
	
	/**
	 * 商品订单问题分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "商品订单问题列表", notes = "商品订单问题分页浏览")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="number",value="顺序，默认1初次，2二次，以此类推",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="merId",value="商品id",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="orderId",value="订单id",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="accountId",value="账户id",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="create_date"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<OrderProblem>> browsePagingOrderProblem(
			@RequestParam(value="number",required=false)Integer number,
			@RequestParam(value="merId",required=false)Integer merId,
			@RequestParam(value="orderId",required=false)Integer orderId,
			@RequestParam(value="accountId",required=false)Integer accountId,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="create_date") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<OrderProblem> list = new ArrayList<OrderProblem>();
			list= orderProblemService.browsePagingOrderProblem(number,merId,orderId,accountId,pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotAnymoreException();//没有更多
			}
	}
	/**
	 * 商品订单问题修改
	 * @return
	 */
	@ApiOperation(value = "商品订单问题修改", notes = "商品订单问题修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateOrderProblem(@ModelAttribute OrderProblem orderProblem,HttpSession session)  {
		boolean um = orderProblemService.updateOrderProblem(orderProblem);
		return ResultUtil.getSR(um);
	}
	/**
	 * 商品订单问题增加
	 * @return 
	 */
	@ApiOperation(value = "商品订单问题增加", notes = "商品订单问题增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addOrderProblem(@ModelAttribute OrderProblem orderProblem, HttpSession session) {
		boolean am = orderProblemService.addOrderProblem(orderProblem);
		return ResultUtil.getSR(am);
	}
	/**
	 * 商品订单问题删除
	 * @return
	 */
	@ApiOperation(value = "商品订单问题删除", notes = "商品订单问题删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="orderProblemId",value="商品订单问题ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delOrderProblem(@RequestParam("orderProblemId") Integer orderProblemId,HttpSession session)  {
		boolean dm = orderProblemService.delOrderProblem(orderProblemId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * 商品订单问题浏览数量
	 * @return
	 */
	@ApiOperation(value = "商品订单问题数量", notes = "商品订单问题数量查询")
	@ApiImplicitParams({
		 @ApiImplicitParam(name="number",value="顺序，默认1初次，2二次，以此类推",dataType="int", paramType = "query"),
		  @ApiImplicitParam(name="merId",value="商品id",dataType="int", paramType = "query"),
		  @ApiImplicitParam(name="orderId",value="订单id",dataType="int", paramType = "query"),
		  @ApiImplicitParam(name="accountId",value="账户id",dataType="int", paramType = "query")
		  })
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(
			@RequestParam(value="number",required=false)Integer number,
			@RequestParam(value="merId",required=false)Integer merId,
			@RequestParam(value="orderId",required=false)Integer orderId,
			@RequestParam(value="accountId",required=false)Integer accountId,
			HttpSession session)  {
		int count = orderProblemService.countAll(number,merId,orderId,accountId);
		return count;
	}
	/**
	 * 商品订单问题单个加载
	 * @return
	 */
	@ApiOperation(value = "商品订单问题单个加载", notes = "商品订单问题单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="orderProblemId",value="商品订单问题ID",dataType="int", paramType = "path",required=true)
		  })
	@RequestMapping(value = "/load", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList<List<OrderProblem>> loadOrderProblem(@RequestParam("orderProblemId") Integer orderProblemId,HttpSession session)  {
		List<OrderProblem> list = new ArrayList<OrderProblem>();
		OrderProblem orderProblem = orderProblemService.loadOrderProblem(orderProblemId);
			if(orderProblem!=null &&!orderProblem.equals("")){
				list.add(orderProblem);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotIsNotExistException("商品订单问题");//不存在
			}
	}
	
}
