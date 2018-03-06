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

import com.nieyue.bean.VideoSetCate;
import com.nieyue.service.VideoSetCateService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * 视频集类型控制类
 * @author yy
 *
 */
@Api(tags={"videoSetCate"},value="视频集类型",description="视频集类型管理")
@RestController
@RequestMapping("/videoSetCate")
public class VideoSetCateController {
	@Resource
	private VideoSetCateService videoSetCateService;
	
	/**
	 * 视频集类型分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "视频集类型列表", notes = "视频集类型分页浏览")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="update_date"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList browsePagingVideoSetCate(
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="update_date") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<VideoSetCate> list = new ArrayList<VideoSetCate>();
			list= videoSetCateService.browsePagingVideoSetCate(pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				return ResultUtil.getSlefSRFailList(list);
			}
	}
	/**
	 * 视频集类型修改
	 * @return
	 */
	@ApiOperation(value = "视频集类型修改", notes = "视频集类型修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateVideoSetCate(@ModelAttribute VideoSetCate videoSetCate,HttpSession session)  {
		boolean um = videoSetCateService.updateVideoSetCate(videoSetCate);
		return ResultUtil.getSR(um);
	}
	/**
	 * 视频集类型增加
	 * @return 
	 */
	@ApiOperation(value = "视频集类型增加", notes = "视频集类型增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addVideoSetCate(@ModelAttribute VideoSetCate videoSetCate, HttpSession session) {
		boolean am = videoSetCateService.addVideoSetCate(videoSetCate);
		return ResultUtil.getSR(am);
	}
	/**
	 * 视频集类型删除
	 * @return
	 */
	@ApiOperation(value = "视频集类型删除", notes = "视频集类型删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="videoSetCateId",value="视频集类型ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delVideoSetCate(@RequestParam("videoSetCateId") Integer videoSetCateId,HttpSession session)  {
		boolean dm = videoSetCateService.delVideoSetCate(videoSetCateId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * 视频集类型浏览数量
	 * @return
	 */
	@ApiOperation(value = "视频集类型数量", notes = "视频集类型数量查询")
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(HttpSession session)  {
		int count = videoSetCateService.countAll();
		return count;
	}
	/**
	 * 视频集类型单个加载
	 * @return
	 */
	@ApiOperation(value = "视频集类型单个加载", notes = "视频集类型单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="videoSetCateId",value="视频集类型ID",dataType="int", paramType = "path",required=true)
		  })
	@RequestMapping(value = "/{videoSetCateId}", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList loadVideoSetCate(@PathVariable("videoSetCateId") Integer videoSetCateId,HttpSession session)  {
		List<VideoSetCate> list = new ArrayList<VideoSetCate>();
		VideoSetCate videoSetCate = videoSetCateService.loadVideoSetCate(videoSetCateId);
			if(videoSetCate!=null &&!videoSetCate.equals("")){
				list.add(videoSetCate);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				return ResultUtil.getSlefSRFailList(list);
			}
	}
	
}
