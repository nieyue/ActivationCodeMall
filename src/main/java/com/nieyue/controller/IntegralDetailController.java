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

import com.nieyue.bean.IntegralDetail;
import com.nieyue.service.IntegralDetailService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * 积分详情控制类
 * @author yy
 *
 */
@Api(tags={"integralDetail"},value="积分详情",description="积分详情管理")
@RestController
@RequestMapping("/integralDetail")
public class IntegralDetailController {
	@Resource
	private IntegralDetailService integralDetailService;
	
	/**
	 * 积分详情分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "积分详情列表", notes = "积分详情分页浏览")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="type",value="类型,0失去，1获得",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="accountId",value="账户Id",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="createDate",value="创建时间",dataType="date-time", paramType = "query"),
	  @ApiImplicitParam(name="updateDate",value="更新时间",dataType="date-time", paramType = "query"),
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="update_date"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList browsePagingIntegralDetail(
			@RequestParam(value="type",required=false)Integer type,
			@RequestParam(value="accountId",required=false)Integer accountId,
			@RequestParam(value="createDate",required=false)Date createDate,
			@RequestParam(value="updateDate",required=false)Date updateDate,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="update_date") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<IntegralDetail> list = new ArrayList<IntegralDetail>();
			list= integralDetailService.browsePagingIntegralDetail(type,accountId,createDate,updateDate,pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				return ResultUtil.getSlefSRFailList(list);
			}
	}
	/**
	 * 积分详情修改
	 * @return
	 */
	@ApiOperation(value = "积分详情修改", notes = "积分详情修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateIntegralDetail(@ModelAttribute IntegralDetail integralDetail,HttpSession session)  {
		boolean um = integralDetailService.updateIntegralDetail(integralDetail);
		return ResultUtil.getSR(um);
	}
	/**
	 * 积分详情增加
	 * @return 
	 */
	@ApiOperation(value = "积分详情增加", notes = "积分详情增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addIntegralDetail(@ModelAttribute IntegralDetail integralDetail, HttpSession session) {
		boolean am = integralDetailService.addIntegralDetail(integralDetail);
		return ResultUtil.getSR(am);
	}
	/**
	 * 积分详情删除
	 * @return
	 */
	@ApiOperation(value = "积分详情删除", notes = "积分详情删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="integralDetailId",value="积分详情ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delIntegralDetail(@RequestParam("integralDetailId") Integer integralDetailId,HttpSession session)  {
		boolean dm = integralDetailService.delIntegralDetail(integralDetailId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * 积分详情浏览数量
	 * @return
	 */
	@ApiOperation(value = "积分详情数量", notes = "积分详情数量查询")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="type",value="类型，0失去，1获得",dataType="int", paramType = "query"),
		  @ApiImplicitParam(name="accountId",value="账户Id",dataType="int", paramType = "query"),
		  @ApiImplicitParam(name="createDate",value="创建时间",dataType="date-time", paramType = "query"),
		  @ApiImplicitParam(name="updateDate",value="更新时间",dataType="date-time", paramType = "query")
		  })
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(
			@RequestParam(value="type",required=false)Integer type,
			@RequestParam(value="accountId",required=false)Integer accountId,
			@RequestParam(value="createDate",required=false)Date createDate,
			@RequestParam(value="updateDate",required=false)Date updateDate,
			HttpSession session)  {
		int count = integralDetailService.countAll(type,accountId,createDate,updateDate);
		return count;
	}
	/**
	 * 积分详情单个加载
	 * @return
	 */
	@ApiOperation(value = "积分详情单个加载", notes = "积分详情单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="integralDetailId",value="积分详情ID",dataType="int", paramType = "path",required=true)
		  })
	@RequestMapping(value = "/{integralDetailId}", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList loadIntegralDetail(@PathVariable("integralDetailId") Integer integralDetailId,HttpSession session)  {
		List<IntegralDetail> list = new ArrayList<IntegralDetail>();
		IntegralDetail integralDetail = integralDetailService.loadIntegralDetail(integralDetailId);
			if(integralDetail!=null &&!integralDetail.equals("")){
				list.add(integralDetail);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				return ResultUtil.getSlefSRFailList(list);
			}
	}
	
}
