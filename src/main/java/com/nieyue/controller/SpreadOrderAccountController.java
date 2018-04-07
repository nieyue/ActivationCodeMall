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

import com.nieyue.bean.SpreadOrderAccount;
import com.nieyue.exception.NotAnymoreException;
import com.nieyue.exception.NotIsNotExistException;
import com.nieyue.service.SpreadOrderAccountService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * 推广订单账户控制类
 * @author yy
 *
 */
@Api(tags={"spreadOrderAccount"},value="推广订单账户",description="推广订单账户管理")
@RestController
@RequestMapping("/spreadOrderAccount")
public class SpreadOrderAccountController {
	@Resource
	private SpreadOrderAccountService spreadOrderAccountService;
	
	/**
	 * 推广订单账户分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "推广订单账户列表", notes = "推广订单账户分页浏览")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="accountId",value="推广账户id",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="create_date"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<SpreadOrderAccount>> browsePagingSpreadOrderAccount(
			@RequestParam(value="accountId",required=false)Integer accountId,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="create_date") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<SpreadOrderAccount> list = new ArrayList<SpreadOrderAccount>();
			list= spreadOrderAccountService.browsePagingSpreadOrderAccount(accountId,pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotAnymoreException();//没有更多
			}
	}
	/**
	 * 推广订单账户修改
	 * @return
	 */
	@ApiOperation(value = "推广订单账户修改", notes = "推广订单账户修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateSpreadOrderAccount(@ModelAttribute SpreadOrderAccount spreadOrderAccount,HttpSession session)  {
		boolean um = spreadOrderAccountService.updateSpreadOrderAccount(spreadOrderAccount);
		return ResultUtil.getSR(um);
	}
	/**
	 * 推广订单账户增加
	 * @return 
	 */
	@ApiOperation(value = "推广订单账户增加", notes = "推广订单账户增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addSpreadOrderAccount(@ModelAttribute SpreadOrderAccount spreadOrderAccount, HttpSession session) {
		boolean am = spreadOrderAccountService.addSpreadOrderAccount(spreadOrderAccount);
		return ResultUtil.getSR(am);
	}
	/**
	 * 推广订单账户删除
	 * @return
	 */
	@ApiOperation(value = "推广订单账户删除", notes = "推广订单账户删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="spreadOrderAccountId",value="推广订单账户ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delSpreadOrderAccount(@RequestParam("spreadOrderAccountId") Integer spreadOrderAccountId,HttpSession session)  {
		boolean dm = spreadOrderAccountService.delSpreadOrderAccount(spreadOrderAccountId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * 推广订单账户浏览数量
	 * @return
	 */
	@ApiOperation(value = "推广订单账户数量", notes = "推广订单账户数量查询")
	@ApiImplicitParams({
		@ApiImplicitParam(name="accountId",value="推广账户id",dataType="int", paramType = "query")
	  })
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(
			@RequestParam(value="accountId",required=false)Integer accountId,
			HttpSession session)  {
		int count = spreadOrderAccountService.countAll(accountId);
		return count;
	}
	/**
	 * 推广订单账户单个加载
	 * @return
	 */
	@ApiOperation(value = "推广订单账户单个加载", notes = "推广订单账户单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="spreadOrderAccountId",value="推广订单账户ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/load", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList<List<SpreadOrderAccount>> loadSpreadOrderAccount(@RequestParam("spreadOrderAccountId") Integer spreadOrderAccountId,HttpSession session)  {
		List<SpreadOrderAccount> list = new ArrayList<SpreadOrderAccount>();
		SpreadOrderAccount spreadOrderAccount = spreadOrderAccountService.loadSpreadOrderAccount(spreadOrderAccountId);
			if(spreadOrderAccount!=null &&!spreadOrderAccount.equals("")){
				list.add(spreadOrderAccount);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotIsNotExistException("推广订单账户");//不存在
			}
	}
	
}
