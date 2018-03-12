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

import com.nieyue.bean.TeamPurchaseInfo;
import com.nieyue.exception.NotAnymoreException;
import com.nieyue.exception.NotIsNotExistException;
import com.nieyue.service.TeamPurchaseInfoService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * 团购信息控制类
 * @author yy
 *
 */
@Api(tags={"teamPurchaseInfo"},value="团购信息",description="团购信息管理")
@RestController
@RequestMapping("/teamPurchaseInfo")
public class TeamPurchaseInfoController {
	@Resource
	private TeamPurchaseInfoService teamPurchaseInfoService;
	
	/**
	 * 团购信息分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "团购信息列表", notes = "团购信息分页浏览")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="accountId",value="账户id,外键",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="createDate",value="创建时间",dataType="date-time", paramType = "query"),
	  @ApiImplicitParam(name="updateDate",value="更新时间",dataType="date-time", paramType = "query"),
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="update_date"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList browsePagingTeamPurchaseInfo(
			@RequestParam(value="accountId",required=false)Integer accountId,
			@RequestParam(value="createDate",required=false)Date createDate,
			@RequestParam(value="updateDate",required=false)Date updateDate,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="update_date") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<TeamPurchaseInfo> list = new ArrayList<TeamPurchaseInfo>();
			list= teamPurchaseInfoService.browsePagingTeamPurchaseInfo(accountId,createDate,updateDate,pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotAnymoreException();//没有更多
			}
	}
	/**
	 * 团购信息修改
	 * @return
	 */
	@ApiOperation(value = "团购信息修改", notes = "团购信息修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateTeamPurchaseInfo(@ModelAttribute TeamPurchaseInfo teamPurchaseInfo,HttpSession session)  {
		boolean um = teamPurchaseInfoService.updateTeamPurchaseInfo(teamPurchaseInfo);
		return ResultUtil.getSR(um);
	}
	/**
	 * 团购信息增加
	 * @return 
	 */
	@ApiOperation(value = "团购信息增加", notes = "团购信息增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addTeamPurchaseInfo(@ModelAttribute TeamPurchaseInfo teamPurchaseInfo, HttpSession session) {
		boolean am = teamPurchaseInfoService.addTeamPurchaseInfo(teamPurchaseInfo);
		return ResultUtil.getSR(am);
	}
	/**
	 * 团购信息删除
	 * @return
	 */
	@ApiOperation(value = "团购信息删除", notes = "团购信息删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="teamPurchaseInfoId",value="团购信息ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delTeamPurchaseInfo(@RequestParam("teamPurchaseInfoId") Integer teamPurchaseInfoId,HttpSession session)  {
		boolean dm = teamPurchaseInfoService.delTeamPurchaseInfo(teamPurchaseInfoId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * 团购信息浏览数量
	 * @return
	 */
	@ApiOperation(value = "团购信息数量", notes = "团购信息数量查询")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="accountId",value="团购信息类型id,外键",dataType="int", paramType = "query"),
		  @ApiImplicitParam(name="createDate",value="创建时间",dataType="date-time", paramType = "query"),
		  @ApiImplicitParam(name="updateDate",value="更新时间",dataType="date-time", paramType = "query")
		  })
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(
			@RequestParam(value="accountId",required=false)Integer accountId,
			@RequestParam(value="createDate",required=false)Date createDate,
			@RequestParam(value="updateDate",required=false)Date updateDate,
			HttpSession session)  {
		int count = teamPurchaseInfoService.countAll(accountId,createDate,updateDate);
		return count;
	}
	/**
	 * 团购信息单个加载
	 * @return
	 */
	@ApiOperation(value = "团购信息单个加载", notes = "团购信息单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="teamPurchaseInfoId",value="团购信息ID",dataType="int", paramType = "path",required=true)
		  })
	@RequestMapping(value = "/{teamPurchaseInfoId}", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList loadTeamPurchaseInfo(@PathVariable("teamPurchaseInfoId") Integer teamPurchaseInfoId,HttpSession session)  {
		List<TeamPurchaseInfo> list = new ArrayList<TeamPurchaseInfo>();
		TeamPurchaseInfo teamPurchaseInfo = teamPurchaseInfoService.loadTeamPurchaseInfo(teamPurchaseInfoId);
			if(teamPurchaseInfo!=null &&!teamPurchaseInfo.equals("")){
				list.add(teamPurchaseInfo);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotIsNotExistException("团购信息");//不存在
			}
	}
	
}
