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

import com.nieyue.bean.Integral;
import com.nieyue.service.IntegralService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * 积分控制类
 * @author yy
 *
 */
@Api(tags={"integral"},value="积分",description="积分管理")
@RestController
@RequestMapping("/integral")
public class IntegralController {
	@Resource
	private IntegralService integralService;
	
	/**
	 * 积分分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "积分列表", notes = "积分分页浏览")
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
	public @ResponseBody StateResultList browsePagingIntegral(
			@RequestParam(value="accountId",required=false)Integer accountId,
			@RequestParam(value="createDate",required=false)Date createDate,
			@RequestParam(value="updateDate",required=false)Date updateDate,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="update_date") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<Integral> list = new ArrayList<Integral>();
			list= integralService.browsePagingIntegral(accountId,createDate,updateDate,pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				return ResultUtil.getSlefSRFailList(list);
			}
	}
	/**
	 * 积分修改
	 * @return
	 */
	@ApiOperation(value = "积分修改", notes = "积分修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateIntegral(@ModelAttribute Integral integral,HttpSession session)  {
		boolean um = integralService.updateIntegral(integral);
		return ResultUtil.getSR(um);
	}
	/**
	 * 积分增加
	 * @return 
	 */
	@ApiOperation(value = "积分增加", notes = "积分增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addIntegral(@ModelAttribute Integral integral, HttpSession session) {
		boolean am = integralService.addIntegral(integral);
		return ResultUtil.getSR(am);
	}
	/**
	 * 积分删除
	 * @return
	 */
	@ApiOperation(value = "积分删除", notes = "积分删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="integralId",value="积分ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delIntegral(@RequestParam("integralId") Integer integralId,HttpSession session)  {
		boolean dm = integralService.delIntegral(integralId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * 积分浏览数量
	 * @return
	 */
	@ApiOperation(value = "积分数量", notes = "积分数量查询")
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
		int count = integralService.countAll(accountId,createDate,updateDate);
		return count;
	}
	/**
	 * 积分单个加载
	 * @return
	 */
	@ApiOperation(value = "积分单个加载", notes = "积分单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="integralId",value="积分ID",dataType="int", paramType = "path",required=true)
		  })
	@RequestMapping(value = "/{integralId}", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList loadIntegral(@PathVariable("integralId") Integer integralId,HttpSession session)  {
		List<Integral> list = new ArrayList<Integral>();
		Integral integral = integralService.loadIntegral(integralId);
			if(integral!=null &&!integral.equals("")){
				list.add(integral);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				return ResultUtil.getSlefSRFailList(list);
			}
	}
	
}
