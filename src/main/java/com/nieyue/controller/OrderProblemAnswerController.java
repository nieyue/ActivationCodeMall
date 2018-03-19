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

import com.nieyue.bean.OrderProblemAnswer;
import com.nieyue.exception.NotAnymoreException;
import com.nieyue.exception.NotIsNotExistException;
import com.nieyue.service.OrderProblemAnswerService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * 商品订单问题反馈控制类
 * @author yy
 *
 */
@Api(tags={"orderProblemAnswer"},value="商品订单问题反馈",description="商品订单问题反馈管理")
@RestController
@RequestMapping("/orderProblemAnswer")
public class OrderProblemAnswerController {
	@Resource
	private OrderProblemAnswerService orderProblemAnswerService;
	
	/**
	 * 商品订单问题反馈分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "商品订单问题反馈列表", notes = "商品订单问题反馈分页浏览")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="create_date"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<OrderProblemAnswer>> browsePagingOrderProblemAnswer(
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="create_date") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<OrderProblemAnswer> list = new ArrayList<OrderProblemAnswer>();
			list= orderProblemAnswerService.browsePagingOrderProblemAnswer(pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotAnymoreException();//没有更多
			}
	}
	/**
	 * 商品订单问题反馈修改
	 * @return
	 */
	@ApiOperation(value = "商品订单问题反馈修改", notes = "商品订单问题反馈修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateOrderProblemAnswer(@ModelAttribute OrderProblemAnswer orderProblemAnswer,HttpSession session)  {
		boolean um = orderProblemAnswerService.updateOrderProblemAnswer(orderProblemAnswer);
		return ResultUtil.getSR(um);
	}
	/**
	 * 商品订单问题反馈增加
	 * @return 
	 */
	@ApiOperation(value = "商品订单问题反馈增加", notes = "商品订单问题反馈增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addOrderProblemAnswer(@ModelAttribute OrderProblemAnswer orderProblemAnswer, HttpSession session) {
		boolean am = orderProblemAnswerService.addOrderProblemAnswer(orderProblemAnswer);
		return ResultUtil.getSR(am);
	}
	/**
	 * 商品订单问题反馈删除
	 * @return
	 */
	@ApiOperation(value = "商品订单问题反馈删除", notes = "商品订单问题反馈删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="orderProblemAnswerId",value="商品订单问题反馈ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delOrderProblemAnswer(@RequestParam("orderProblemAnswerId") Integer orderProblemAnswerId,HttpSession session)  {
		boolean dm = orderProblemAnswerService.delOrderProblemAnswer(orderProblemAnswerId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * 商品订单问题反馈浏览数量
	 * @return
	 */
	@ApiOperation(value = "商品订单问题反馈数量", notes = "商品订单问题反馈数量查询")
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(
			HttpSession session)  {
		int count = orderProblemAnswerService.countAll();
		return count;
	}
	/**
	 * 商品订单问题反馈单个加载
	 * @return
	 */
	@ApiOperation(value = "商品订单问题反馈单个加载", notes = "商品订单问题反馈单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="orderProblemAnswerId",value="商品订单问题反馈ID",dataType="int", paramType = "path",required=true)
		  })
	@RequestMapping(value = "/load", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList<List<OrderProblemAnswer>> loadOrderProblemAnswer(@RequestParam("orderProblemAnswerId") Integer orderProblemAnswerId,HttpSession session)  {
		List<OrderProblemAnswer> list = new ArrayList<OrderProblemAnswer>();
		OrderProblemAnswer orderProblemAnswer = orderProblemAnswerService.loadOrderProblemAnswer(orderProblemAnswerId);
			if(orderProblemAnswer!=null &&!orderProblemAnswer.equals("")){
				list.add(orderProblemAnswer);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotIsNotExistException("商品订单问题反馈");//不存在
			}
	}
	
}
