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

import com.nieyue.bean.VideoSetCollect;
import com.nieyue.exception.NotAnymoreException;
import com.nieyue.exception.NotIsNotExistException;
import com.nieyue.service.VideoSetCollectService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * 视频集收藏控制类
 * @author yy
 *
 */
@Api(tags={"videoSetCollect"},value="视频集收藏",description="视频集收藏管理")
@RestController
@RequestMapping("/videoSetCollect")
public class VideoSetCollectController {
	@Resource
	private VideoSetCollectService videoSetCollectService;
	
	/**
	 * 视频集收藏分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "视频集收藏列表", notes = "视频集收藏分页浏览")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="videoSetId",value="视频集id外键",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="accountId",value="收藏人id外键",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="create_date"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList browsePagingVideoSetCollect(
			@RequestParam(value="videoSetId",required=false)Integer videoSetId,
			@RequestParam(value="accountId",required=false)Integer accountId,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="create_date") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<VideoSetCollect> list = new ArrayList<VideoSetCollect>();
			list= videoSetCollectService.browsePagingVideoSetCollect(videoSetId,accountId,pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotAnymoreException();//没有更多
			}
	}
	/**
	 * 视频集收藏修改
	 * @return
	 */
	@ApiOperation(value = "视频集收藏修改", notes = "视频集收藏修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateVideoSetCollect(@ModelAttribute VideoSetCollect videoSetCollect,HttpSession session)  {
		boolean um = videoSetCollectService.updateVideoSetCollect(videoSetCollect);
		return ResultUtil.getSR(um);
	}
	/**
	 * 视频集收藏增加
	 * @return 
	 */
	@ApiOperation(value = "视频集收藏增加", notes = "视频集收藏增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addVideoSetCollect(@ModelAttribute VideoSetCollect videoSetCollect, HttpSession session) {
		boolean am = videoSetCollectService.addVideoSetCollect(videoSetCollect);
		return ResultUtil.getSR(am);
	}
	/**
	 * 视频集收藏删除
	 * @return
	 */
	@ApiOperation(value = "视频集收藏删除", notes = "视频集收藏删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="videoSetCollectId",value="视频集收藏ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delVideoSetCollect(
			@RequestParam("videoSetCollectId") Integer videoSetCollectId,
			HttpSession session)  {
		boolean dm = videoSetCollectService.delVideoSetCollect(videoSetCollectId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * 视频集收藏浏览数量
	 * @return
	 */
	@ApiOperation(value = "视频集收藏数量", notes = "视频集收藏数量查询")
	@ApiImplicitParams({
		 @ApiImplicitParam(name="videoSetId",value="视频集id外键",dataType="int", paramType = "query"),
		  @ApiImplicitParam(name="accountId",value="收藏人id外键",dataType="int", paramType = "query")
		  })
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(
			@RequestParam(value="videoSetId",required=false)Integer videoSetId,
			@RequestParam(value="accountId",required=false)Integer accountId,
			HttpSession session)  {
		int count = videoSetCollectService.countAll(videoSetId,accountId);
		return count;
	}
	/**
	 * 视频集收藏单个加载
	 * @return
	 */
	@ApiOperation(value = "视频集收藏单个加载", notes = "视频集收藏单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="videoSetCollectId",value="视频集收藏ID",dataType="int", paramType = "path",required=true)
		  })
	@RequestMapping(value = "/{videoSetCollectId}", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList loadVideoSetCollect(@PathVariable("videoSetCollectId") Integer videoSetCollectId,HttpSession session)  {
		List<VideoSetCollect> list = new ArrayList<VideoSetCollect>();
		VideoSetCollect videoSetCollect = videoSetCollectService.loadVideoSetCollect(videoSetCollectId);
			if(videoSetCollect!=null &&!videoSetCollect.equals("")){
				list.add(videoSetCollect);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotIsNotExistException("视频集收藏");//不存在
			}
	}
	
	/**
	 * 视频集收藏批量删除
	 * @return
	 */
	@ApiOperation(value = "视频集收藏批量删除videoSetCollectId=1&videoSetCollectId=2", notes = "视频集收藏批量删除")
	@ApiImplicitParams({
		@ApiImplicitParam(name="videoSetCollectId",value="视频集收藏ID列表",dataType="array", paramType = "query",required=true)
	})
	@RequestMapping(value = "/deleteBatch", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delVideoSetCollectList(
		 @RequestParam(value = "videoSetCollectId") Integer[] videoSetCollectId,
			HttpSession session)  {
		 boolean dm=false;
		for(int i=0;i<videoSetCollectId.length;i++){
		    dm = videoSetCollectService.delVideoSetCollect(videoSetCollectId[i]);
		  }
		return ResultUtil.getSR(dm);
	}
	
}
