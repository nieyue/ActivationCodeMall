package com.nieyue.controller;

import java.util.ArrayList;
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

import com.nieyue.bean.AppVersion;
import com.nieyue.exception.NotAnymoreException;
import com.nieyue.exception.NotIsNotExistException;
import com.nieyue.service.AppVersionService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;




/**
 * App版本控制类
 * @author yy
 *
 */
@Api(tags={"appVersion"},value="App版本",description="App版本管理")
@RestController
@RequestMapping("/appVersion")
public class AppVersionController {
	@Resource
	private AppVersionService appVersionService;
	
	/**
	 * App版本分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "App版本列表", notes = "App版本分页浏览")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="platform",value="app平台，0安卓，1为IOS",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="status",value="app状态，0上线，1为未上线",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="createDate",value="创建时间",dataType="date-time", paramType = "query"),
	  @ApiImplicitParam(name="loginDate",value="最后登陆时间",dataType="date-time", paramType = "query"),
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="app_version_id"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList browsePagingAppVersion(
			@RequestParam(value="platform",required=false)Integer platform,
			@RequestParam(value="status",required=false)Integer status,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="app_version_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<AppVersion> list = new ArrayList<AppVersion>();
			list= appVersionService.browsePagingAppVersion(platform,status,pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotAnymoreException();//没有更多
			}
	}
	/**
	 * App版本修改
	 * @return
	 */
	@ApiOperation(value = "App版本修改", notes = "App版本修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateAppVersion(@ModelAttribute AppVersion appVersion,HttpSession session)  {
		boolean um = appVersionService.updateAppVersion(appVersion);
		return ResultUtil.getSR(um);
	}
	/**
	 * App版本增加
	 * @return 
	 */
	@ApiOperation(value = "App版本增加", notes = "App版本增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addAppVersion(@ModelAttribute AppVersion appVersion, HttpSession session) {
		boolean am = appVersionService.addAppVersion(appVersion);
		return ResultUtil.getSR(am);
	}
	/**
	 * App版本删除
	 * @return
	 */
	@ApiOperation(value = "App版本删除", notes = "App版本删除")
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delAppVersion(
			@RequestParam("appVersionId") Integer appVersionId,HttpSession session)  {
		boolean dm = appVersionService.delAppVersion(appVersionId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * App版本浏览数量
	 * @return
	 */
	@ApiOperation(value = "App版本数目", notes = "App版本数目")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="platform",value="app平台，0安卓，1为IOS",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="status",value="app状态，0上线，1为未上线",dataType="int", paramType = "query")
	  })
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(
			@RequestParam(value="platform",required=false)Integer platform,
			@RequestParam(value="status",required=false)Integer status,
			HttpSession session)  {
		int count = appVersionService.countAll(platform,status);
		return count;
	}
	/**
	 * App版本单个加载
	 * @return
	 */
	@ApiOperation(value = "App版本单个加载", notes = "App版本单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="appVersionId",value="app版本ID",dataType="int", paramType = "path",required=true)
		  })
	@RequestMapping(value = "/{appVersionId}", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList loadAppVersion(@PathVariable("appVersionId") Integer appVersionId,HttpSession session)  {
		List<AppVersion> list = new ArrayList<AppVersion>();
		AppVersion appVersion = appVersionService.loadAppVersion(appVersionId);
			if(appVersion!=null &&!appVersion.equals("")){
				list.add(appVersion);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotIsNotExistException("App版本");//不存在
			}
	}
	
}
