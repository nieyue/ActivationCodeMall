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

import com.nieyue.bean.Distribute;
import com.nieyue.service.DistributeService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * 分发控制类
 * @author yy
 *
 */
@Api(tags={"distribute"},value="分发",description="分发管理")
@RestController
@RequestMapping("/distribute")
public class DistributeController {
	@Resource
	private DistributeService distributeService;
	
	/**
	 * 分发分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "分发列表", notes = "分发分页浏览")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="accountId",value="账户自身id,邀请码",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="buyAccountId",value="购买者id,外键",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="distributeDate",value="分发时间",dataType="date-time", paramType = "query"),
	  @ApiImplicitParam(name="createDate",value="创建时间",dataType="date-time", paramType = "query"),
	  @ApiImplicitParam(name="updateDate",value="更新时间",dataType="date-time", paramType = "query"),
	  @ApiImplicitParam(name="status",value="分发状态，默认1已分发",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="create_date"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList browsePagingDistribute(
			@RequestParam(value="accountId",required=false)Integer accountId,
			@RequestParam(value="buyAccountId",required=false)Integer buyAccountId,
			@RequestParam(value="distributeDate",required=false)Date distributeDate,
			@RequestParam(value="createDate",required=false)Date createDate,
			@RequestParam(value="updateDate",required=false)Date updateDate,
			@RequestParam(value="status",required=false)Integer status,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="create_date") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<Distribute> list = new ArrayList<Distribute>();
			list= distributeService.browsePagingDistribute(accountId,buyAccountId,distributeDate,createDate,updateDate,status,pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				return ResultUtil.getSlefSRFailList(list);
			}
	}
	/**
	 * 分发修改
	 * @return
	 */
	@ApiOperation(value = "分发修改", notes = "分发修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateDistribute(@ModelAttribute Distribute distribute,HttpSession session)  {
		boolean um = distributeService.updateDistribute(distribute);
		return ResultUtil.getSR(um);
	}
	/**
	 * 分发增加
	 * @return 
	 */
	@ApiOperation(value = "分发增加", notes = "分发增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addDistribute(@ModelAttribute Distribute distribute, HttpSession session) {
		boolean am = distributeService.addDistribute(distribute);
		return ResultUtil.getSR(am);
	}
	/**
	 * 分发删除
	 * @return
	 */
	@ApiOperation(value = "分发删除", notes = "分发删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="distributeId",value="分发ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delDistribute(@RequestParam("distributeId") Integer distributeId,HttpSession session)  {
		boolean dm = distributeService.delDistribute(distributeId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * 分发浏览数量
	 * @return
	 */
	@ApiOperation(value = "分发数量", notes = "分发数量查询")
	@ApiImplicitParams({
		 @ApiImplicitParam(name="accountId",value="账户自身id,邀请码",dataType="int", paramType = "query"),
		  @ApiImplicitParam(name="buyAccountId",value="购买者id,外键",dataType="int", paramType = "query"),
		  @ApiImplicitParam(name="distributeDate",value="分发时间",dataType="date-time", paramType = "query"),
		  @ApiImplicitParam(name="createDate",value="创建时间",dataType="date-time", paramType = "query"),
		  @ApiImplicitParam(name="updateDate",value="更新时间",dataType="date-time", paramType = "query"),
		  @ApiImplicitParam(name="status",value="分发状态，默认1已分发",dataType="int", paramType = "query")
		  })
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(
			@RequestParam(value="accountId",required=false)Integer accountId,
			@RequestParam(value="buyAccountId",required=false)Integer buyAccountId,
			@RequestParam(value="distributeDate",required=false)Date distributeDate,
			@RequestParam(value="createDate",required=false)Date createDate,
			@RequestParam(value="updateDate",required=false)Date updateDate,
			@RequestParam(value="status",required=false)Integer status,
			HttpSession session)  {
		int count = distributeService.countAll(accountId,buyAccountId,distributeDate,createDate,updateDate,status);
		return count;
	}
	/**
	 * 分发单个加载
	 * @return
	 */
	@ApiOperation(value = "分发单个加载", notes = "分发单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="distributeId",value="分发ID",dataType="int", paramType = "path",required=true)
		  })
	@RequestMapping(value = "/{distributeId}", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList loadDistribute(@PathVariable("distributeId") Integer distributeId,HttpSession session)  {
		List<Distribute> list = new ArrayList<Distribute>();
		Distribute distribute = distributeService.loadDistribute(distributeId);
			if(distribute!=null &&!distribute.equals("")){
				list.add(distribute);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				return ResultUtil.getSlefSRFailList(list);
			}
	}
	
}
