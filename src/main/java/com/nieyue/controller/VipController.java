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

import com.nieyue.bean.Vip;
import com.nieyue.exception.NotAnymoreException;
import com.nieyue.exception.NotIsNotExistException;
import com.nieyue.service.VipService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * Vip控制类
 * @author yy
 *
 */
@Api(tags={"vip"},value="vip",description="vip管理")
@RestController
@RequestMapping("/vip")
public class VipController {
	@Resource
	private VipService vipService;
	
	/**
	 * Vip分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "Vip列表", notes = "Vip分页浏览")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="accountId",value="账户Id",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="level",value="等级名",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="createDate",value="创建时间",dataType="date-time", paramType = "query"),
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="update_date"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList browsePagingVip(
			@RequestParam(value="accountId",required=false)Integer accountId,
			@RequestParam(value="level",required=false)Integer level,
			@RequestParam(value="createDate",required=false)Date createDate,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="update_date") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<Vip> list = new ArrayList<Vip>();
			list= vipService.browsePagingVip(accountId,level,createDate,pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotAnymoreException();//没有更多
			}
	}
	/**
	 * Vip修改
	 * @return
	 */
	@ApiOperation(value = "Vip修改", notes = "Vip修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateVip(@ModelAttribute Vip vip,HttpSession session)  {
		boolean um = vipService.updateVip(vip);
		return ResultUtil.getSR(um);
	}
	/**
	 * Vip增加
	 * @return 
	 */
	@ApiOperation(value = "Vip增加", notes = "Vip增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addVip(@ModelAttribute Vip vip, HttpSession session) {
		boolean am = vipService.addVip(vip);
		return ResultUtil.getSR(am);
	}
	/**
	 * Vip删除
	 * @return
	 */
	@ApiOperation(value = "Vip删除", notes = "Vip删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="VipId",value="VipID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delVip(@RequestParam("vipId") Integer vipId,HttpSession session)  {
		boolean dm = vipService.delVip(vipId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * Vip浏览数量
	 * @return
	 */
	@ApiOperation(value = "Vip数量", notes = "Vip数量查询")
	@ApiImplicitParams({
		@ApiImplicitParam(name="accountId",value="账户Id",dataType="int", paramType = "query"),
		  @ApiImplicitParam(name="level",value="等级名",dataType="int", paramType = "query"),
		  @ApiImplicitParam(name="createDate",value="创建时间",dataType="date-time", paramType = "query")
		  })
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(
			@RequestParam(value="accountId",required=false)Integer accountId,
			@RequestParam(value="level",required=false)Integer level,
			@RequestParam(value="createDate",required=false)Date createDate,
			HttpSession session)  {
		int count = vipService.countAll(accountId,level,createDate);
		return count;
	}
	/**
	 * Vip单个加载
	 * @return
	 */
	@ApiOperation(value = "Vip单个加载", notes = "Vip单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="vipId",value="vipID",dataType="int", paramType = "path",required=true)
		  })
	@RequestMapping(value = "/{vipId}", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList loadVip(@PathVariable("vipId") Integer vipId,HttpSession session)  {
		List<Vip> list = new ArrayList<Vip>();
		Vip vip = vipService.loadVip(vipId);
			if(vip!=null &&!vip.equals("")){
				list.add(vip);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotIsNotExistException("vip");//不存在
			}
	}
	
}
