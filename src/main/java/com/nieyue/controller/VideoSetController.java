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

import com.nieyue.bean.VideoSet;
import com.nieyue.service.VideoSetService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * 视频集控制类
 * @author yy
 *
 */
@Api(tags={"videoSet"},value="视频集",description="视频集管理")
@RestController
@RequestMapping("/videoSet")
public class VideoSetController {
	@Resource
	private VideoSetService videoSetService;
	
	/**
	 * 视频集分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "视频集列表", notes = "视频集分页浏览")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="name",value="查询名称，模糊查询",dataType="string", paramType = "query"),
	  @ApiImplicitParam(name="recommend",value="推荐，默认0不推，1封推，2热门推荐，3专栏",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="cost",value="是否收费，0免费，1vip免费，2付费课程",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="videoSetCateId",value="视频集类型id,外键",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="createDate",value="创建时间",dataType="date-time", paramType = "query"),
	  @ApiImplicitParam(name="updateDate",value="更新时间",dataType="date-time", paramType = "query"),
	  @ApiImplicitParam(name="status",value="状态0下架,默认1上架",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="update_date"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList browsePagingVideoSet(
			@RequestParam(value="name",required=false)String name,
			@RequestParam(value="recommend",required=false)Integer recommend,
			@RequestParam(value="cost",required=false)Integer cost,
			@RequestParam(value="videoSetCateId",required=false)Integer videoSetCateId,
			@RequestParam(value="createDate",required=false)Date createDate,
			@RequestParam(value="updateDate",required=false)Date updateDate,
			@RequestParam(value="status",required=false)Integer status,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="update_date") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<VideoSet> list = new ArrayList<VideoSet>();
			list= videoSetService.browsePagingVideoSet(name,recommend,cost,videoSetCateId,createDate,updateDate,status,pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				return ResultUtil.getSlefSRFailList(list);
			}
	}

	/**
	 * 视频集修改
	 * @return
	 */
	@ApiOperation(value = "视频集修改", notes = "视频集修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateVideoSet(@ModelAttribute VideoSet videoSet,HttpSession session)  {
		boolean um = videoSetService.updateVideoSet(videoSet);
		return ResultUtil.getSR(um);
	}
	/**
	 * 视频集增加
	 * @return 
	 */
	@ApiOperation(value = "视频集增加", notes = "视频集增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addVideoSet(@ModelAttribute VideoSet videoSet, HttpSession session) {
		boolean am = videoSetService.addVideoSet(videoSet);
		return ResultUtil.getSR(am);
	}
	/**
	 * 视频集删除
	 * @return
	 */
	@ApiOperation(value = "视频集删除", notes = "视频集删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="videoSetId",value="视频集ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delVideoSet(@RequestParam("videoSetId") Integer videoSetId,HttpSession session)  {
		boolean dm = videoSetService.delVideoSet(videoSetId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * 视频集浏览数量
	 * @return
	 */
	@ApiOperation(value = "视频集数量", notes = "视频集数量查询")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="name",value="查询名称，模糊查询",dataType="string", paramType = "query"),
		  @ApiImplicitParam(name="recommend",value="推荐，默认0不推，1封推，2热门推荐，3专栏",dataType="int", paramType = "query"),
		  @ApiImplicitParam(name="cost",value="是否收费，0免费，1vip免费，2付费课程",dataType="int", paramType = "query"),
		  @ApiImplicitParam(name="videoSetCateId",value="视频集类型id,外键",dataType="int", paramType = "query"),
		  @ApiImplicitParam(name="createDate",value="创建时间",dataType="date-time", paramType = "query"),
		  @ApiImplicitParam(name="updateDate",value="更新时间",dataType="date-time", paramType = "query"),
		  @ApiImplicitParam(name="status",value="状态0下架,默认1上架",dataType="int", paramType = "query")
		  })
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(
			@RequestParam(value="name",required=false)String name,
			@RequestParam(value="recommend",required=false)Integer recommend,
			@RequestParam(value="cost",required=false)Integer cost,
			@RequestParam(value="videoSetCateId",required=false)Integer videoSetCateId,
			@RequestParam(value="createDate",required=false)Date createDate,
			@RequestParam(value="updateDate",required=false)Date updateDate,
			@RequestParam(value="status",required=false)Integer status,
			HttpSession session)  {
		int count = videoSetService.countAll(name,recommend,cost,videoSetCateId,createDate,updateDate,status);
		return count;
	}
	/**
	 * 视频集单个加载
	 * @return
	 */
	@ApiOperation(value = "视频集单个加载", notes = "视频集单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="videoSetId",value="视频集ID",paramType="path",required=true)
		  })
	@RequestMapping(value = "/{videoSetId}", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList loadVideoSet(@PathVariable("videoSetId") Integer videoSetId,HttpSession session)  {
		List<VideoSet> list = new ArrayList<VideoSet>();
		VideoSet videoSet = videoSetService.loadVideoSet(videoSetId);
			if(videoSet!=null &&!videoSet.equals("")){
				list.add(videoSet);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				return ResultUtil.getSlefSRFailList(list);
			}
	}
	
}
