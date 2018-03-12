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

import com.nieyue.bean.AccountParent;
import com.nieyue.exception.NotAnymoreException;
import com.nieyue.exception.NotIsNotExistException;
import com.nieyue.service.AccountParentService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * 账户上级控制类
 * @author yy
 *
 */
@Api(tags={"accountParent"},value="账户上级",description="账户上级管理")
@RestController
@RequestMapping("/accountParent")
public class AccountParentController {
	@Resource
	private AccountParentService accountParentService;
	
	/**
	 * 账户上级分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "账户上级列表", notes = "账户上级分页浏览")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="phone",value="模糊查询手机号",dataType="string", paramType = "query"),
	  @ApiImplicitParam(name="accountLevelId",value="账户等级ID",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="accountId",value="账户Id",dataType="int", paramType = "query"),
      @ApiImplicitParam(name="masterId",value="直接上级Id",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="realMasterId",value="真实上级Id",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="createDate",value="创建时间",dataType="date-time", paramType = "query"),
	  @ApiImplicitParam(name="updateDate",value="更新时间",dataType="date-time", paramType = "query"),
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="update_date"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList browsePagingAccountParent(
			@RequestParam(value="phone",required=false)String phone,
			@RequestParam(value="accountLevelId",required=false)Integer accountLevelId,
			@RequestParam(value="accountId",required=false)Integer accountId,
			@RequestParam(value="masterId",required=false)Integer masterId,
			@RequestParam(value="realMasterId",required=false)Integer realMasterId,
			@RequestParam(value="createDate",required=false)Date createDate,
			@RequestParam(value="updateDate",required=false)Date updateDate,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="update_date") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<AccountParent> list = new ArrayList<AccountParent>();
			list= accountParentService.browsePagingAccountParent(
					phone,
					accountLevelId,
					accountId,
					masterId,
					realMasterId,
					createDate,
					updateDate,
					pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotAnymoreException();//没有更多
			}
	}
	/**
	 * 账户上级修改
	 * @return
	 */
	@ApiOperation(value = "账户上级修改", notes = "账户上级修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateAccountParent(@ModelAttribute AccountParent accountParent,HttpSession session)  {
		boolean um = accountParentService.updateAccountParent(accountParent);
		return ResultUtil.getSR(um);
	}
	/**
	 * 账户上级增加
	 * @return 
	 */
	@ApiOperation(value = "账户上级增加", notes = "账户上级增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addAccountParent(@ModelAttribute AccountParent accountParent, HttpSession session) {
		boolean am = accountParentService.addAccountParent(accountParent);
		return ResultUtil.getSR(am);
	}
	/**
	 * 账户上级删除
	 * @return
	 */
	@ApiOperation(value = "账户上级删除", notes = "账户上级删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="accountParentId",value="账户上级ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delAccountParent(@RequestParam("accountParentId") Integer accountParentId,HttpSession session)  {
		boolean dm = accountParentService.delAccountParent(accountParentId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * 账户上级浏览数量
	 * @return
	 */
	@ApiOperation(value = "账户上级数量", notes = "账户上级数量查询")
	@ApiImplicitParams({
		@ApiImplicitParam(name="phone",value="模糊查询手机号",dataType="string", paramType = "query"),
		 @ApiImplicitParam(name="accountLevelId",value="账户等级ID",dataType="int", paramType = "query"),
		  @ApiImplicitParam(name="accountId",value="账户Id",dataType="int", paramType = "query"),
	      @ApiImplicitParam(name="masterId",value="直接上级Id",dataType="int", paramType = "query"),
		  @ApiImplicitParam(name="realMasterId",value="真实上级Id",dataType="int", paramType = "query"),
		  @ApiImplicitParam(name="createDate",value="创建时间",dataType="date-time", paramType = "query"),
		  @ApiImplicitParam(name="updateDate",value="更新时间",dataType="date-time", paramType = "query")
		  })
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(
			@RequestParam(value="phone",required=false)String phone,
			@RequestParam(value="accountLevelId",required=false)Integer accountLevelId,
			@RequestParam(value="accountId",required=false)Integer accountId,
			@RequestParam(value="masterId",required=false)Integer masterId,
			@RequestParam(value="realMasterId",required=false)Integer realMasterId,
			@RequestParam(value="createDate",required=false)Date createDate,
			@RequestParam(value="updateDate",required=false)Date updateDate,
			HttpSession session)  {
		int count = accountParentService.countAll(
				phone,
				accountLevelId,
				accountId,
				masterId,
				realMasterId,
				createDate,
				updateDate);
		return count;
	}
	/**
	 * 账户上级单个加载
	 * @return
	 */
	@ApiOperation(value = "账户上级单个加载", notes = "账户上级单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="accountParentId",value="账户上级ID",dataType="int", paramType = "path",required=true)
		  })
	@RequestMapping(value = "/{accountParentId}", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList loadAccountParent(@PathVariable("accountParentId") Integer accountParentId,HttpSession session)  {
		List<AccountParent> list = new ArrayList<AccountParent>();
		AccountParent accountParent = accountParentService.loadAccountParent(accountParentId);
			if(accountParent!=null &&!accountParent.equals("")){
				list.add(accountParent);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotIsNotExistException("账户上级");//不存在
			}
	}
	
}
