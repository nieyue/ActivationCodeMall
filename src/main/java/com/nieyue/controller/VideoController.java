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

import com.nieyue.bean.Video;
import com.nieyue.bean.VideoSet;
import com.nieyue.exception.NotAnymoreException;
import com.nieyue.exception.NotIsNotExistException;
import com.nieyue.service.VideoService;
import com.nieyue.service.VideoSetService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * 视频控制类
 * @author yy
 *
 */
@Api(tags={"video"},value="视频",description="视频管理")
@RestController
@RequestMapping("/video")
public class VideoController {
	@Resource
	private VideoService videoService;
	@Resource
	private VideoSetService videoSetService;
	
	/**
	 * 视频分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "视频列表", notes = "视频分页浏览")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="videoSetId",value="视频集id,外键",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="createDate",value="创建时间",dataType="date-time", paramType = "query"),
	  @ApiImplicitParam(name="updateDate",value="更新时间",dataType="date-time", paramType = "query"),
	  @ApiImplicitParam(name="status",value="状态0下架,默认1上架",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="update_date"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList browsePagingVideo(
			@RequestParam(value="videoSetId",required=false)Integer videoSetId,
			@RequestParam(value="createDate",required=false)Date createDate,
			@RequestParam(value="updateDate",required=false)Date updateDate,
			@RequestParam(value="status",required=false)Integer status,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="update_date") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<Video> list = new ArrayList<Video>();
			list= videoService.browsePagingVideo(videoSetId,createDate,updateDate,status,pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotAnymoreException();//没有更多
			}
	}
	/**
	 * 视频修改
	 * @return
	 */
	@ApiOperation(value = "视频修改", notes = "视频修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateVideo(@ModelAttribute Video video,HttpSession session)  {
		boolean um = videoService.updateVideo(video);
		return ResultUtil.getSR(um);
	}
	/**
	 * 视频增加
	 * @return 
	 */
	@ApiOperation(value = "视频增加", notes = "视频增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addVideo(@ModelAttribute Video video, HttpSession session) {
		boolean am = videoService.addVideo(video);
		return ResultUtil.getSR(am);
	}
	/**
	 * 视频删除
	 * @return
	 */
	@ApiOperation(value = "视频删除", notes = "视频删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="videoId",value="视频ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delVideo(@RequestParam("videoId") Integer videoId,HttpSession session)  {
		boolean dm = videoService.delVideo(videoId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * 视频浏览数量
	 * @return
	 */
	@ApiOperation(value = "视频数量", notes = "视频数量查询")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="videoSetId",value="视频类型id,外键",dataType="int", paramType = "query"),
		  @ApiImplicitParam(name="createDate",value="创建时间",dataType="date-time", paramType = "query"),
		  @ApiImplicitParam(name="updateDate",value="更新时间",dataType="date-time", paramType = "query"),
		  @ApiImplicitParam(name="status",value="状态0下架,默认1上架",dataType="int", paramType = "query")
		  })
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(
			@RequestParam(value="videoSetId",required=false)Integer videoSetId,
			@RequestParam(value="createDate",required=false)Date createDate,
			@RequestParam(value="updateDate",required=false)Date updateDate,
			@RequestParam(value="status",required=false)Integer status,
			HttpSession session)  {
		int count = videoService.countAll(videoSetId,createDate,updateDate,status);
		return count;
	}
	/**
	 * 视频单个加载
	 * @return
	 */
	@ApiOperation(value = "视频单个加载", notes = "视频单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="videoId",value="视频ID",dataType="int", paramType = "path",required=true)
		  })
	@RequestMapping(value = "/{videoId}", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList loadVideo(@PathVariable("videoId") Integer videoId,HttpSession session)  {
		List<Video> list = new ArrayList<Video>();
		Video video = videoService.loadVideo(videoId);
			if(video!=null &&!video.equals("")){
				list.add(video);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotIsNotExistException("视频");//不存在
			}
	}
	/**
	 * 根据视频id获取视频集
	 * @return
	 */
			@ApiOperation(value = "根据视频id获取视频集", notes = "根据视频id获取视频集")
	@ApiImplicitParams({
		@ApiImplicitParam(name="videoId",value="视频ID",dataType="int", paramType = "query",required=true),
		@ApiImplicitParam(name="accountId",value="账户ID",dataType="int", paramType = "query",required=true)
	})
	@RequestMapping(value = "/loadVideoSet", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList loadVideoSetByVideoId(
			@RequestParam(value="videoId")Integer videoId,
			@RequestParam(value="accountId")Integer accountId,
			HttpSession session)  {
		List<VideoSet> list = new ArrayList<VideoSet>();
		Video video = videoService.loadVideo(videoId);
		if(video!=null &&!video.equals("")){
			VideoSet videoSet = videoSetService.loadVideoSet(video.getVideoSetId());
			list.add(videoSet);
			return ResultUtil.getSlefSRSuccessList(list);
		}else{
			throw new NotIsNotExistException("视频");//不存在
		}
	}
	/**
	 * 观看视频
	 * @return
	 */
	@ApiOperation(value = "观看视频", notes = "观看视频")
	@ApiImplicitParams({
		@ApiImplicitParam(name="videoId",value="视频ID",dataType="int", paramType = "query",required=true),
		@ApiImplicitParam(name="accountId",value="账户ID",dataType="int", paramType = "query",required=true),
		@ApiImplicitParam(name="type",value="类型，默认1开始看视频，2观看中（只加积分，不算播放次数）",dataType="int", paramType = "query",required=true)
	})
	@RequestMapping(value = "/watch", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList watchVideo(
			@RequestParam("videoId") Integer videoId,
			@RequestParam("accountId") Integer accountId,
			@RequestParam(value="type") Integer type,
			HttpSession session)  {
		List<Video> list = new ArrayList<Video>();
		boolean b = videoService.watchVideo(videoId, accountId,type);
		if(b){
			return ResultUtil.getSlefSRSuccessList(list);
		}else{
			return ResultUtil.getSlefSRFailList(list);
		}
	}
	
}
