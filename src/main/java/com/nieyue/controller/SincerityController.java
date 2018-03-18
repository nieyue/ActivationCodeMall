package com.nieyue.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nieyue.bean.Sincerity;
import com.nieyue.exception.NotAnymoreException;
import com.nieyue.exception.NotIsNotExistException;
import com.nieyue.service.SincerityService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * 诚信控制类
 * @author yy
 *
 */
@Api(tags={"sincerity"},value="诚信",description="诚信管理")
@RestController
@RequestMapping("/sincerity")
public class SincerityController {
	@Resource
	private SincerityService sincerityService;
	
	/**
	 * 诚信分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "诚信列表", notes = "诚信分页浏览")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="accountId",value="账户Id",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="createDate",value="创建时间",dataType="date-time", paramType = "query"),
	  @ApiImplicitParam(name="updateDate",value="更新时间",dataType="date-time", paramType = "query"),
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="update_date"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Sincerity>> browsePagingSincerity(
			@RequestParam(value="accountId",required=false)Integer accountId,
			@RequestParam(value="createDate",required=false)Date createDate,
			@RequestParam(value="updateDate",required=false)Date updateDate,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="update_date") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<Sincerity> list = new ArrayList<Sincerity>();
			list= sincerityService.browsePagingSincerity(accountId,createDate,updateDate,pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotAnymoreException();//没有更多
			}
	}
	/**
	 * 诚信修改
	 * @return
	 */
	@ApiOperation(value = "诚信修改", notes = "诚信修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateSincerity(@ModelAttribute Sincerity sincerity,HttpSession session)  {
		boolean um = sincerityService.updateSincerity(sincerity);
		return ResultUtil.getSR(um);
	}
	/**
	 * 诚信增加
	 * @return 
	 */
	@ApiOperation(value = "诚信增加", notes = "诚信增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addSincerity(@ModelAttribute Sincerity sincerity, HttpSession session) {
		boolean am = sincerityService.addSincerity(sincerity);
		return ResultUtil.getSR(am);
	}
	/**
	 * 诚信删除
	 * @return
	 */
	@ApiOperation(value = "诚信删除", notes = "诚信删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="sincerityId",value="诚信ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delSincerity(@RequestParam("sincerityId") Integer sincerityId,HttpSession session)  {
		boolean dm = sincerityService.delSincerity(sincerityId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * 诚信浏览数量
	 * @return
	 */
	@ApiOperation(value = "诚信数量", notes = "诚信数量查询")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="accountId",value="账户Id",dataType="int", paramType = "query"),
		  @ApiImplicitParam(name="createDate",value="创建时间",dataType="date-time", paramType = "query"),
		  @ApiImplicitParam(name="updateDate",value="更新时间",dataType="date-time", paramType = "query")
		  })
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(
			@RequestParam(value="accountId",required=false)Integer accountId,
			@RequestParam(value="createDate",required=false)Date createDate,
			@RequestParam(value="updateDate",required=false)Date updateDate,
			HttpSession session)  {
		int count = sincerityService.countAll(accountId,createDate,updateDate);
		return count;
	}
	/**
	 * 诚信单个加载
	 * @return
	 */
	@ApiOperation(value = "诚信单个加载", notes = "诚信单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="sincerityId",value="诚信ID",dataType="int", paramType = "path",required=true)
		  })
	@RequestMapping(value = "/load", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList<List<Sincerity>> loadSincerity(@RequestParam("sincerityId") Integer sincerityId,HttpSession session)  {
		List<Sincerity> list = new ArrayList<Sincerity>();
		Sincerity sincerity = sincerityService.loadSincerity(sincerityId);
			if(sincerity!=null &&!sincerity.equals("")){
				list.add(sincerity);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotIsNotExistException("诚信");//不存在
			}
	}
	
}
