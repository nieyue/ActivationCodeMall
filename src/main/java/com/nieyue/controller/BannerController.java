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

import com.nieyue.bean.Banner;
import com.nieyue.exception.NotAnymoreException;
import com.nieyue.exception.NotIsNotExistException;
import com.nieyue.service.BannerService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;




/**
 * banner控制类
 * @author yy
 *
 */
@Api(tags={"banner"},value="banner",description="banner管理")
@RestController
@RequestMapping("/banner")
public class BannerController {
	@Resource
	private BannerService bannerService;
	
	/**
	 * banner分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "banner列表", notes = "banner分页浏览")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="type",value="类型，默认1首页轮播",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="status",value="状态，默认0下架，1上架",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="banner_id"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Banner>> browsePagingBanner(
			@RequestParam(value="type",required=false)Integer type,
			@RequestParam(value="status",required=false)Integer status,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="banner_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<Banner> list = new ArrayList<Banner>();
			list= bannerService.browsePagingBanner(type,status,pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotAnymoreException();//没有更多
			}
	}
	/**
	 * banner修改
	 * @return
	 */
	@ApiOperation(value = "banner修改", notes = "banner修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateBanner(@ModelAttribute Banner banner,HttpSession session)  {
		boolean um = bannerService.updateBanner(banner);
		return ResultUtil.getSR(um);
	}
	/**
	 * banner增加
	 * @return 
	 */
	@ApiOperation(value = "banner增加", notes = "banner增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addBanner(@ModelAttribute Banner banner, HttpSession session) {
		boolean am = bannerService.addBanner(banner);
		return ResultUtil.getSR(am);
	}
	/**
	 * banner删除
	 * @return
	 */
	@ApiOperation(value = "banner删除", notes = "banner删除")
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delBanner(
			@RequestParam("bannerId") Integer bannerId,HttpSession session)  {
		boolean dm = bannerService.delBanner(bannerId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * banner浏览数量
	 * @return
	 */
	@ApiOperation(value = "banner数目", notes = "banner数目")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="type",value="类型，默认1首页轮播",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="status",value="状态，默认0下架，1上架",dataType="int", paramType = "query")
	  })
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(
			@RequestParam(value="type",required=false)Integer type,
			@RequestParam(value="status",required=false)Integer status,
			HttpSession session)  {
		int count = bannerService.countAll(type,status);
		return count;
	}
	/**
	 * banner单个加载
	 * @return
	 */
	@ApiOperation(value = "banner单个加载", notes = "banner单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="bannerId",value="bannerID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/load", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList<List<Banner>> loadBanner(
			@RequestParam("bannerId") Integer bannerId,HttpSession session)  {
		List<Banner> list = new ArrayList<Banner>();
		Banner banner = bannerService.loadBanner(bannerId);
			if(banner!=null &&!banner.equals("")){
				list.add(banner);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotIsNotExistException("banner");//不存在
			}
	}
	
}
