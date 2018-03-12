package com.nieyue.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alipay.api.AlipayApiException;
import com.nieyue.bean.Payment;
import com.nieyue.exception.NotAnymoreException;
import com.nieyue.exception.NotIsNotExistException;
import com.nieyue.pay.AlipayUtil;
import com.nieyue.service.PaymentService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;



/**
 * 支付控制类
 * @author yy
 *
 */
@Api(tags={"payment"},value="支付",description="支付管理")
@RestController
@RequestMapping("/payment")
public class PaymentController {
	@Resource
	private PaymentService paymentService;
	@Resource
	private AlipayUtil alipayUtil;
	
	
	/**
	 * 支付分页浏览
	 * @param orderName 支付排序数据库字段
	 * @param orderWay 支付排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "支付列表", notes = "支付分页浏览")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="orderNumber",value="订单号",dataType="string", paramType = "query"),
	  @ApiImplicitParam(name="type",value="支付类型，默认1支付宝支付，2微信支付，3银联支付,4ios内购",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="businessType",value="业务类型，1VIP购买，2团购卡团购，3付费课程",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="businessId",value="业务ID",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="accountId",value="账户ID",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="createDate",value="创建时间",dataType="date-time", paramType = "query"),
	  @ApiImplicitParam(name="updateDate",value="更新时间",dataType="date-time", paramType = "query"),
	  @ApiImplicitParam(name="status",value="状态，1已下单-未支付，2支付成功，3支付失败,4异常",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="payment_id"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList browsePagingPayment(
			@RequestParam(value="orderNumber",required=false)String orderNumber,
			@RequestParam(value="type",required=false)Integer type,
			@RequestParam(value="businessType",required=false)Integer businessType,
			@RequestParam(value="businessId",required=false)Integer businessId,
			@RequestParam(value="accountId",required=false)Integer accountId,
			@RequestParam(value="createDate",required=false)Date createDate,
			@RequestParam(value="updateDate",required=false)Date updateDate,
			@RequestParam(value="status",required=false)Integer status,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="payment_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<Payment> list = new ArrayList<Payment>();
			list= paymentService.browsePagingPayment(orderNumber,type,businessType,businessId,accountId,createDate,updateDate,status,pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotAnymoreException();//没有更多
			}
	}
	/**
	 * 支付修改
	 * @return
	 */
	@ApiOperation(value = "支付修改", notes = "支付修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updatePayment(@ModelAttribute Payment payment,HttpSession session)  {
		boolean um = paymentService.updatePayment(payment);
		return ResultUtil.getSR(um);
	}
	/**
	 * 阿里云支付查询
	 * @return
	 * @throws AlipayApiException 
	 */
	@ApiOperation(value = "阿里云支付查询", notes = "阿里云支付查询")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="orderNumber",value="订单号",dataType="string", paramType = "query",required=true)
	 	  })
	@RequestMapping(value = "/alipayTradeQuery", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList alipayAlipayTradeQuery(
			@RequestParam(value="orderNumber") String orderNumber,
			HttpSession session) throws AlipayApiException  {
		String body = alipayUtil.getAlipayTradeQuery(orderNumber);
		List<String> ls=new ArrayList<String>();
		if(!body.equals("")&&body!=null){
			ls.add(body);
			return ResultUtil.getSlefSRSuccessList(ls);
		}
		return ResultUtil.getSlefSRFailList(ls);
	}
	/**
	 * 阿里云支付回调
	 * @return
	 * @throws Exception 
	 */
	@ApiOperation(value = "阿里云支付回调", notes = "阿里云支付回调")
	@RequestMapping(value = "/alipayNotifyUrl", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody String alipayNotifyUrl(HttpServletRequest request,HttpSession session) {
		String pm = alipayUtil.getNotifyUrl(request);
		return pm;
	}

	/**
	 * 支付增加
	 * @return 
	 */
	@ApiOperation(value = "支付增加", notes = "支付增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addPayment(@RequestBody Payment payment, HttpSession session) {
		boolean am = paymentService.addPayment(payment);
		return ResultUtil.getSR(am);
	}
	/**
	 * 支付删除
	 * @return
	 */
	@ApiOperation(value = "支付删除", notes = "支付删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="paymentId",value="支付ID",dataType="int", paramType = "query",required=true)
		 	  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delPayment(@RequestParam("paymentId") Integer paymentId,HttpSession session)  {
		boolean dm = paymentService.delPayment(paymentId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * 支付浏览数量
	 * @return
	 */
	@ApiOperation(value = "支付浏览数量", notes = "支付浏览数量")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="orderNumber",value="订单号",dataType="string", paramType = "query"),
	  @ApiImplicitParam(name="type",value="支付类型，默认1支付宝支付，2微信支付，3银联支付,4ios内购",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="businessType",value="业务类型，1VIP购买，2团购卡团购，3付费课程",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="businessId",value="业务ID",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="accountId",value="账户ID",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="createDate",value="创建时间",dataType="date-time", paramType = "query"),
	  @ApiImplicitParam(name="updateDate",value="更新时间",dataType="date-time", paramType = "query"),
	  @ApiImplicitParam(name="status",value="状态，1已下单-未支付，2支付成功，3支付失败,4异常",dataType="int", paramType = "query")
	  })
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(
			@RequestParam(value="orderNumber",required=false)String orderNumber,
			@RequestParam(value="type",required=false)Integer type,
			@RequestParam(value="businessType",required=false)Integer businessType,
			@RequestParam(value="businessId",required=false)Integer businessId,
			@RequestParam(value="accountId",required=false)Integer accountId,
			@RequestParam(value="createDate",required=false)Date createDate,
			@RequestParam(value="updateDate",required=false)Date updateDate,
			@RequestParam(value="status",required=false)Integer status,
			HttpSession session)  {
		int count = paymentService.countAll(orderNumber,type,businessType,businessId,accountId,createDate,updateDate,status);
		return count;
	}
	/**
	 * 支付单个加载
	 * @return
	 */
	@ApiOperation(value = "支付单个加载", notes = "支付单个加载")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="paymentId",value="支付ID",dataType="int", paramType="path",required=true)
	 	  })
	@RequestMapping(value = "/{paymentId}", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList loadPayment(@PathVariable("paymentId") Integer paymentId,HttpSession session)  {
		List<Payment> list = new ArrayList<Payment>();
		Payment payment = paymentService.loadPayment(paymentId);
			if(payment!=null &&!payment.equals("")){
				list.add(payment);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotIsNotExistException("支付");//不存在
			}
	}
	
}
