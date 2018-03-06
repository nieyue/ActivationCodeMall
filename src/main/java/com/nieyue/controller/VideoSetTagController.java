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

import com.nieyue.bean.VideoSetTag;
import com.nieyue.service.VideoSetTagService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * 视频集标签控制类
 * @author yy
 *
 */
@Api(tags={"videoSetTag"},value="视频集标签",description="视频集标签管理")
@RestController
@RequestMapping("/videoSetTag")
public class VideoSetTagController {
	@Resource
	private VideoSetTagService videoSetTagService;
	
	/**
	 * 视频集标签分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "视频集标签列表", notes = "视频集标签分页浏览")
	@ApiImplicitParams({
		@ApiImplicitParam(name="videoSetId",value="视频集ID",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="update_date"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList browsePagingVideoSetTag(
			@RequestParam(value="videoSetId",required=false)Integer videoSetId,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="update_date") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<VideoSetTag> list = new ArrayList<VideoSetTag>();
			list= videoSetTagService.browsePagingVideoSetTag(videoSetId,pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				return ResultUtil.getSlefSRFailList(list);
			}
	}
	/**
	 * 视频集标签修改
	 * @return
	 */
	@ApiOperation(value = "视频集标签修改", notes = "视频集标签修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateVideoSetTag(@ModelAttribute VideoSetTag videoSetTag,HttpSession session)  {
		boolean um = videoSetTagService.updateVideoSetTag(videoSetTag);
		return ResultUtil.getSR(um);
	}
	/**
	 * 视频集标签增加
	 * @return 
	 */
	@ApiOperation(value = "视频集标签增加", notes = "视频集标签增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addVideoSetTag(@ModelAttribute VideoSetTag videoSetTag, HttpSession session) {
		boolean am = videoSetTagService.addVideoSetTag(videoSetTag);
		return ResultUtil.getSR(am);
	}
	/**
	 * 视频集标签删除
	 * @return
	 */
	@ApiOperation(value = "视频集标签删除", notes = "视频集标签删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="videoSetTagId",value="视频集标签ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delVideoSetTag(@RequestParam("videoSetTagId") Integer videoSetTagId,HttpSession session)  {
		boolean dm = videoSetTagService.delVideoSetTag(videoSetTagId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * 视频集标签浏览数量
	 * @return
	 */
	@ApiOperation(value = "视频集标签数量", notes = "视频集标签数量查询")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="videoSetId",value="视频集ID",dataType="int", paramType = "query")
		  })
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(
			@RequestParam(value="videoSetId",required=false)Integer videoSetId,
			HttpSession session)  {
		int count = videoSetTagService.countAll(videoSetId);
		return count;
	}
	/**
	 * 视频集标签单个加载
	 * @return
	 */
	@ApiOperation(value = "视频集标签单个加载", notes = "视频集标签单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="videoSetTagId",value="视频集标签ID",dataType="int", paramType = "path",required=true)
		  })
	@RequestMapping(value = "/{videoSetTagId}", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList loadVideoSetTag(@PathVariable("VideoSetTagId") Integer videoSetTagId,HttpSession session)  {
		List<VideoSetTag> list = new ArrayList<VideoSetTag>();
		VideoSetTag videoSetTag = videoSetTagService.loadVideoSetTag(videoSetTagId);
			if(videoSetTag!=null &&!videoSetTag.equals("")){
				list.add(videoSetTag);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				return ResultUtil.getSlefSRFailList(list);
			}
	}
	
}
