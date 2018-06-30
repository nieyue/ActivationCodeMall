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

import com.nieyue.bean.Account;
import com.nieyue.bean.MerOrderCardCipher;
import com.nieyue.bean.Order;
import com.nieyue.bean.Role;
import com.nieyue.exception.MySessionException;
import com.nieyue.exception.NotAnymoreException;
import com.nieyue.exception.NotIsNotExistException;
import com.nieyue.service.MerOrderCardCipherService;
import com.nieyue.service.OrderService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;


/**
 * 商品订单卡密控制类
 * @author yy
 *
 */
@Api(tags={"merOrderCardCipher"},value="商品订单卡密",description="商品订单卡密管理")
@RestController
@RequestMapping("/merOrderCardCipher")
public class MerOrderCardCipherController {
	@Resource
	private MerOrderCardCipherService merOrderCardCipherService;
	@Resource
	private OrderService orderService;
	
	/**
	 * 商品订单卡密分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "商品订单卡密列表", notes = "商品订单卡密分页浏览")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="orderId",value="订单id",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="create_date"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<MerOrderCardCipher>> browsePagingMerOrderCardCipher(
			@RequestParam(value="orderId",required=false)Integer orderId,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="create_date") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay
			,HttpSession session)  {
		Account sessionAccount = (Account)session.getAttribute("account");
		Role sessionRole = (Role)session.getAttribute("role");
		//用户只能获取自己的
			if(sessionRole!=null&&sessionRole.getName().equals("用户")){
				if(orderId==null){
					throw new MySessionException();
				}
				Order order = orderService.loadOrder(orderId);
				System.out.println(JSONObject.fromObject(order));
				if(order==null||!order.getAccountId().equals(sessionAccount.getAccountId())){
					throw new MySessionException();					
				}
				//orderService.browsePagingOrder(null, null, null, null, sessionAccount.getAccountId(), null, null, null, null, 1, Integer.MAX_VALUE, "order_id", "asc");
			}
			//商户只能获取自己的
			if(sessionRole!=null&&sessionRole.getName().equals("商户")){
				if(orderId==null){
					throw new MySessionException();
				}
				Order order = orderService.loadOrder(orderId);
				if(order==null||!order.getMerchantAccountId().equals(sessionAccount.getAccountId())){
					throw new MySessionException();					
				}
			}
			List<MerOrderCardCipher> list = new ArrayList<MerOrderCardCipher>();
			list= merOrderCardCipherService.browsePagingMerOrderCardCipher(orderId,pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotAnymoreException();//没有更多
			}
	}
	/**
	 * 商品订单卡密修改
	 * @return
	 */
	@ApiOperation(value = "商品订单卡密修改", notes = "商品订单卡密修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateMerOrderCardCipher(@ModelAttribute MerOrderCardCipher merOrderCardCipher,HttpSession session)  {
		boolean um = merOrderCardCipherService.updateMerOrderCardCipher(merOrderCardCipher);
		return ResultUtil.getSR(um);
	}
	/**
	 * 商品订单卡密增加
	 * @return 
	 */
	@ApiOperation(value = "商品订单卡密增加", notes = "商品订单卡密增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addMerOrderCardCipher(@ModelAttribute MerOrderCardCipher merOrderCardCipher, HttpSession session) {
		boolean am = merOrderCardCipherService.addMerOrderCardCipher(merOrderCardCipher);
		return ResultUtil.getSR(am);
	}
	/**
	 * 商品订单卡密删除
	 * @return
	 */
	@ApiOperation(value = "商品订单卡密删除", notes = "商品订单卡密删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="merOrderCardCipherId",value="商品订单卡密ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delMerOrderCardCipher(@RequestParam("merOrderCardCipherId") Integer merOrderCardCipherId,HttpSession session)  {
		boolean dm = merOrderCardCipherService.delMerOrderCardCipher(merOrderCardCipherId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * 商品订单卡密浏览数量
	 * @return
	 */
	@ApiOperation(value = "商品订单卡密数量", notes = "商品订单卡密数量查询")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="orderId",value="订单id",dataType="int", paramType = "query")
		  })
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(
			@RequestParam(value="orderId",required=false)Integer orderId,
			HttpSession session)  {
		int count = merOrderCardCipherService.countAll(orderId);
		return count;
	}
	/**
	 * 商品订单卡密单个加载
	 * @return
	 */
	@ApiOperation(value = "商品订单卡密单个加载", notes = "商品订单卡密单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="merOrderCardCipherId",value="商品订单卡密ID",dataType="int", paramType = "path",required=true)
		  })
	@RequestMapping(value = "/load", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList<List<MerOrderCardCipher>> loadMerOrderCardCipher(@RequestParam("merOrderCardCipherId") Integer merOrderCardCipherId,HttpSession session)  {
		List<MerOrderCardCipher> list = new ArrayList<MerOrderCardCipher>();
		MerOrderCardCipher merOrderCardCipher = merOrderCardCipherService.loadMerOrderCardCipher(merOrderCardCipherId);
			if(merOrderCardCipher!=null &&!merOrderCardCipher.equals("")){
				Account sessionAccount = (Account)session.getAttribute("account");
				Role sessionRole = (Role)session.getAttribute("role");
				Integer orderId = merOrderCardCipher.getOrderId();
				//用户只能获取自己的
					if(sessionRole!=null&&sessionRole.getName().equals("用户")){
						if(orderId==null){
							throw new MySessionException();
						}
						Order order = orderService.loadOrder(orderId);
						if(order==null||!order.getAccountId().equals(sessionAccount.getAccountId())){
							throw new MySessionException();					
						}
						//orderService.browsePagingOrder(null, null, null, null, sessionAccount.getAccountId(), null, null, null, null, 1, Integer.MAX_VALUE, "order_id", "asc");
					}
					//商户只能获取自己的
					if(sessionRole!=null&&sessionRole.getName().equals("商户")){
						if(orderId==null){
							throw new MySessionException();
						}
						Order order = orderService.loadOrder(orderId);
						if(order==null||!order.getMerchantAccountId().equals(sessionAccount.getAccountId())){
							throw new MySessionException();					
						}
					}
				
				
				list.add(merOrderCardCipher);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotIsNotExistException("商品订单卡密");//不存在
			}
	}
	
}
