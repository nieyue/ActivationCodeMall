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

import com.nieyue.bean.AccountLevel;
import com.nieyue.exception.NotAnymoreException;
import com.nieyue.exception.NotIsNotExistException;
import com.nieyue.service.AccountLevelService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * 等级控制类
 * @author yy
 *
 */
@Api(tags={"accountLevel"},value="等级",description="等级管理")
@RestController
@RequestMapping("/accountLevel")
public class AccountLevelController {
	@Resource
	private AccountLevelService accountLevelService;
	
	/**
	 * 等级分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "等级列表", notes = "等级分页浏览")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="level",value="等级名",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="update_date"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<AccountLevel>> browsePagingAccountLevel(
			@RequestParam(value="level",required=false)Integer level,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="update_date") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<AccountLevel> list = new ArrayList<AccountLevel>();
			list= accountLevelService.browsePagingAccountLevel(level,pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotAnymoreException();//没有更多
			}
	}
	/**
	 * 等级修改
	 * @return
	 */
	@ApiOperation(value = "等级修改", notes = "等级修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateAccountLevel(@ModelAttribute AccountLevel accountLevel,HttpSession session)  {
		boolean um = accountLevelService.updateAccountLevel(accountLevel);
		return ResultUtil.getSR(um);
	}
	/**
	 * 等级增加
	 * @return 
	 */
	@ApiOperation(value = "等级增加", notes = "等级增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addAccountLevel(@ModelAttribute AccountLevel accountLevel, HttpSession session) {
		boolean am = accountLevelService.addAccountLevel(accountLevel);
		return ResultUtil.getSR(am);
	}
	/**
	 * 等级删除
	 * @return
	 */
	@ApiOperation(value = "等级删除", notes = "等级删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="accountLevelId",value="等级ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delAccountLevel(@RequestParam("accountLevelId") Integer accountLevelId,HttpSession session)  {
		boolean dm = accountLevelService.delAccountLevel(accountLevelId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * 等级浏览数量
	 * @return
	 */
	@ApiOperation(value = "等级数量", notes = "等级数量查询")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="level",value="等级名",dataType="int", paramType = "query")
		  })
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(
			@RequestParam(value="level",required=false)Integer level,
			HttpSession session)  {
		int count = accountLevelService.countAll(level);
		return count;
	}
	/**
	 * 等级单个加载
	 * @return
	 */
	@ApiOperation(value = "等级单个加载", notes = "等级单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="accountLevelId",value="等级ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/load", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList<List<AccountLevel>> loadAccountLevel(@RequestParam("accountLevelId") Integer accountLevelId,HttpSession session)  {
		List<AccountLevel> list = new ArrayList<AccountLevel>();
		AccountLevel accountLevel = accountLevelService.loadAccountLevel(accountLevelId);
			if(accountLevel!=null &&!accountLevel.equals("")){
				list.add(accountLevel);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotIsNotExistException("等级");//不存在
	}
	
}
}
