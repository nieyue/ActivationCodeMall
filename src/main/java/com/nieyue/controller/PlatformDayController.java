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

import com.nieyue.bean.PlatformDay;
import com.nieyue.service.PlatformDayService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * 平台日控制类
 * @author yy
 *
 */
@Api(tags={"platformDay"},value="平台日",description="平台日管理")
@RestController
@RequestMapping("/platformDay")
public class PlatformDayController {
	@Resource
	private PlatformDayService platformDayService;
	
	/**
	 * 平台日分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "平台日列表", notes = "平台日分页浏览")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="createDate",value="创建时间",dataType="date-time", paramType = "query"),
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="update_date"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList browsePagingPlatformDay(
			@RequestParam(value="createDate",required=false)Date createDate,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="update_date") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<PlatformDay> list = new ArrayList<PlatformDay>();
			list= platformDayService.browsePagingPlatformDay(createDate,pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				return ResultUtil.getSlefSRFailList(list);
			}
	}
	/**
	 * 平台日修改
	 * @return
	 */
	@ApiOperation(value = "平台日修改", notes = "平台日修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updatePlatformDay(@ModelAttribute PlatformDay platformDay,HttpSession session)  {
		boolean um = platformDayService.updatePlatformDay(platformDay);
		return ResultUtil.getSR(um);
	}
	/**
	 * 平台日增加
	 * @return 
	 */
	@ApiOperation(value = "平台日增加", notes = "平台日增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addPlatformDay(@ModelAttribute PlatformDay platformDay, HttpSession session) {
		boolean am = platformDayService.addPlatformDay(platformDay);
		return ResultUtil.getSR(am);
	}
	/**
	 * 平台日删除
	 * @return
	 */
	@ApiOperation(value = "平台日删除", notes = "平台日删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="platformDayId",value="平台日ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delPlatformDay(@RequestParam("platformDayId") Integer platformDayId,HttpSession session)  {
		boolean dm = platformDayService.delPlatformDay(platformDayId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * 平台日浏览数量
	 * @return
	 */
	@ApiOperation(value = "平台日数量", notes = "平台日数量查询")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="createDate",value="创建时间",dataType="date-time", paramType = "query")
		  })
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(
			@RequestParam(value="createDate",required=false)Date createDate,
			HttpSession session)  {
		int count = platformDayService.countAll(createDate);
		return count;
	}
	/**
	 * 平台日单个加载
	 * @return
	 */
	@ApiOperation(value = "平台日单个加载", notes = "平台日单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="platformDayId",value="平台日ID",dataType="int", paramType = "path",required=true)
		  })
	@RequestMapping(value = "/{platformDayId}", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList loadPlatformDay(@PathVariable("platformDayId") Integer platformDayId,HttpSession session)  {
		List<PlatformDay> list = new ArrayList<PlatformDay>();
		PlatformDay platformDay = platformDayService.loadPlatformDay(platformDayId);
			if(platformDay!=null &&!platformDay.equals("")){
				list.add(platformDay);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				return ResultUtil.getSlefSRFailList(list);
			}
	}
	
}
