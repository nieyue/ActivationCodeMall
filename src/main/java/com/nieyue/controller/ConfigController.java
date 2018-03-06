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

import com.nieyue.bean.Config;
import com.nieyue.service.ConfigService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * 配置控制类
 * @author yy
 *
 */
@Api(tags={"config"},value="配置",description="配置管理")
@RestController
@RequestMapping("/config")
public class ConfigController {
	@Resource
	private ConfigService configService;
	
	/**
	 * 配置分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "配置列表", notes = "配置分页浏览")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="create_date"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList browsePagingConfig(
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="create_date") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<Config> list = new ArrayList<Config>();
			list= configService.browsePagingConfig(pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				return ResultUtil.getSlefSRFailList(list);
			}
	}
	/**
	 * 配置修改
	 * @return
	 */
	@ApiOperation(value = "配置修改", notes = "配置修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateConfig(@ModelAttribute Config config,HttpSession session)  {
		boolean um = configService.updateConfig(config);
		return ResultUtil.getSR(um);
	}
	/**
	 * 配置增加
	 * @return 
	 */
	@ApiOperation(value = "配置增加", notes = "配置增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addConfig(@ModelAttribute Config config, HttpSession session) {
		boolean am = configService.addConfig(config);
		return ResultUtil.getSR(am);
	}
	/**
	 * 配置删除
	 * @return
	 */
	@ApiOperation(value = "配置删除", notes = "配置删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="configId",value="配置ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delConfig(@RequestParam("configId") Integer configId,HttpSession session)  {
		boolean dm = configService.delConfig(configId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * 配置浏览数量
	 * @return
	 */
	@ApiOperation(value = "配置数量", notes = "配置数量查询")
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(HttpSession session)  {
		int count = configService.countAll();
		return count;
	}
	/**
	 * 配置单个加载
	 * @return
	 */
	@ApiOperation(value = "配置单个加载", notes = "配置单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="configId",value="配置ID",dataType="int", paramType = "path",required=true)
		  })
	@RequestMapping(value = "/{configId}", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList loadConfig(@PathVariable("configId") Integer configId,HttpSession session)  {
		List<Config> list = new ArrayList<Config>();
		Config config = configService.loadConfig(configId);
			if(config!=null &&!config.equals("")){
				list.add(config);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				return ResultUtil.getSlefSRFailList(list);
			}
	}
	
}
