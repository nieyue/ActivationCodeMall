package com.nieyue.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nieyue.bean.BankCard;
import com.nieyue.exception.CommonRollbackException;
import com.nieyue.exception.NotAnymoreException;
import com.nieyue.exception.NotIsNotExistException;
import com.nieyue.service.BankCardService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * 银行卡控制类
 * @author yy
 *
 */
@Api(tags={"bankCard"},value="银行卡",description="银行卡管理")
@RestController
@RequestMapping("/bankCard")
public class BankCardController {
	@Resource
	private BankCardService bankCardService;
	
	/**
	 * 银行卡分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "银行卡列表", notes = "银行卡分页浏览")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="accountId",value="账户id",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="create_date"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<BankCard>> browsePagingBankCard(
			@RequestParam(value="accountId",required=false)Integer accountId,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="create_date") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<BankCard> list = new ArrayList<BankCard>();
			list= bankCardService.browsePagingBankCard(accountId,pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotAnymoreException();//没有更多
			}
	}
	/**
	 * 银行卡修改
	 * @return
	 */
	@ApiOperation(value = "银行卡修改", notes = "银行卡修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateBankCard(@ModelAttribute BankCard bankCard,HttpSession session)  {
		boolean um = bankCardService.updateBankCard(bankCard);
		return ResultUtil.getSR(um);
	}
	/**
	 * 银行卡增加
	 * @return 
	 */
	@ApiOperation(value = "银行卡增加", notes = "银行卡增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addBankCard(@ModelAttribute BankCard bankCard, HttpSession session) {
		boolean am = bankCardService.addBankCard(bankCard);
		return ResultUtil.getSR(am);
	}
	/**
	 * 银行卡增加或者更新
	 * @return 
	 */
	@ApiOperation(value = "银行卡增加或者更新", notes = "银行卡增加或者更新")
	@RequestMapping(value = "/addOrUpdate", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addOrUpdateBankCard(
			@RequestParam("validCode") String validCode,
			@ModelAttribute BankCard bankCard, HttpSession session) {
		if(StringUtils.isEmpty(validCode)||!validCode.equals((String)session.getAttribute("validCode"))){
			throw new CommonRollbackException("验证码错误");
		}
		boolean am=false;
		if(bankCard.getBankCardId()==null){
			am = bankCardService.addBankCard(bankCard);
		}else{
			am = bankCardService.updateBankCard(bankCard);
		}
		return ResultUtil.getSR(am);
	}
	/**
	 * 银行卡删除
	 * @return
	 */
	@ApiOperation(value = "银行卡删除", notes = "银行卡删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="bankCardId",value="银行卡ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delBankCard(@RequestParam("bankCardId") Integer bankCardId,HttpSession session)  {
		boolean dm = bankCardService.delBankCard(bankCardId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * 银行卡浏览数量
	 * @return
	 */
	@ApiOperation(value = "银行卡数量", notes = "银行卡数量查询")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="accountId",value="账户id",dataType="int", paramType = "query")
		  })
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(
			@RequestParam(value="accountId",required=false)Integer accountId,
			HttpSession session)  {
		int count = bankCardService.countAll(accountId);
		return count;
	}
	/**
	 * 银行卡单个加载
	 * @return
	 */
	@ApiOperation(value = "银行卡单个加载", notes = "银行卡单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="bankCardId",value="银行卡ID",dataType="int", paramType = "path",required=true)
		  })
	@RequestMapping(value = "/load", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList<List<BankCard>> loadBankCard(@RequestParam("bankCardId") Integer bankCardId,HttpSession session)  {
		List<BankCard> list = new ArrayList<BankCard>();
		BankCard bankCard = bankCardService.loadBankCard(bankCardId);
			if(bankCard!=null &&!bankCard.equals("")){
				list.add(bankCard);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotIsNotExistException("银行卡");//不存在
			}
	}
	
}
