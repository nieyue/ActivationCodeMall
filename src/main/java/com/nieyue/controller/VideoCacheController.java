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

import com.nieyue.bean.VideoCache;
import com.nieyue.service.VideoCacheService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * 视频缓存控制类
 * @author yy
 *
 */
@Api(tags={"videoCache"},value="视频缓存",description="视频缓存管理")
@RestController
@RequestMapping("/videoCache")
public class VideoCacheController {
	@Resource
	private VideoCacheService videoCacheService;
	
	/**
	 * 视频缓存分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "视频缓存列表", notes = "视频缓存分页浏览")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="videoId",value="视频id外键",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="accountId",value="收藏人id外键",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="create_date"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList browsePagingVideoCache(
			@RequestParam(value="videoId",required=false)Integer videoId,
			@RequestParam(value="accountId",required=false)Integer accountId,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="create_date") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<VideoCache> list = new ArrayList<VideoCache>();
			list= videoCacheService.browsePagingVideoCache(videoId,accountId,pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				return ResultUtil.getSlefSRFailList(list);
			}
	}
	/**
	 * 视频缓存修改
	 * @return
	 */
	@ApiOperation(value = "视频缓存修改", notes = "视频缓存修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateVideoCache(@ModelAttribute VideoCache videoCache,HttpSession session)  {
		boolean um = videoCacheService.updateVideoCache(videoCache);
		return ResultUtil.getSR(um);
	}
	/**
	 * 视频缓存增加
	 * @return 
	 */
	@ApiOperation(value = "视频缓存增加", notes = "视频缓存增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addVideoCache(@ModelAttribute VideoCache videoCache, HttpSession session) {
		boolean am = videoCacheService.addVideoCache(videoCache);
		return ResultUtil.getSR(am);
	}
	/**
	 * 视频缓存删除
	 * @return
	 */
	@ApiOperation(value = "视频缓存删除", notes = "视频缓存删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="VideoCacheId",value="视频缓存ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delVideoCache(@RequestParam("videoCacheId") Integer videoCacheId,HttpSession session)  {
		boolean dm = videoCacheService.delVideoCache(videoCacheId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * 视频缓存浏览数量
	 * @return
	 */
	@ApiOperation(value = "视频缓存数量", notes = "视频缓存数量查询")
	@ApiImplicitParams({
		 @ApiImplicitParam(name="videoId",value="视频id外键",dataType="int", paramType = "query"),
		  @ApiImplicitParam(name="accountId",value="收藏人id外键",dataType="int", paramType = "query")
		  })
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(
			@RequestParam(value="videoId",required=false)Integer videoId,
			@RequestParam(value="accountId",required=false)Integer accountId,
			HttpSession session)  {
		int count = videoCacheService.countAll(videoId,accountId);
		return count;
	}
	/**
	 * 视频缓存单个加载
	 * @return
	 */
	@ApiOperation(value = "视频缓存单个加载", notes = "视频缓存单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="VideoCacheId",value="视频缓存ID",dataType="int", paramType = "path",required=true)
		  })
	@RequestMapping(value = "/{videoCacheId}", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList loadVideoCache(@PathVariable("videoCacheId") Integer videoCacheId,HttpSession session)  {
		List<VideoCache> list = new ArrayList<VideoCache>();
		VideoCache VideoCache = videoCacheService.loadVideoCache(videoCacheId);
			if(VideoCache!=null &&!VideoCache.equals("")){
				list.add(VideoCache);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				return ResultUtil.getSlefSRFailList(list);
			}
	}
	
}
