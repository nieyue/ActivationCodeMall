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

import com.nieyue.bean.VideoPlayRecord;
import com.nieyue.exception.NotAnymoreException;
import com.nieyue.exception.NotIsNotExistException;
import com.nieyue.service.VideoPlayRecordService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * 视频播放记录控制类
 * @author yy
 *
 */
@Api(tags={"videoPlayRecord"},value="视频播放记录",description="视频播放记录管理")
@RestController
@RequestMapping("/videoPlayRecord")
public class VideoPlayRecordController {
	@Resource
	private VideoPlayRecordService videoPlayRecordService;
	
	/**
	 * 视频播放记录分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "视频播放记录列表", notes = "视频播放记录分页浏览")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="videoId",value="视频id外键",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="accountId",value="收藏人id外键",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="create_date"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList browsePagingVideoPlayRecord(
			@RequestParam(value="videoId",required=false)Integer videoId,
			@RequestParam(value="accountId",required=false)Integer accountId,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="create_date") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<VideoPlayRecord> list = new ArrayList<VideoPlayRecord>();
			list= videoPlayRecordService.browsePagingVideoPlayRecord(videoId,accountId,pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotAnymoreException();//没有更多
			}
	}
	/**
	 * 视频播放记录修改
	 * @return
	 */
	@ApiOperation(value = "视频播放记录修改", notes = "视频播放记录修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateVideoPlayRecord(@ModelAttribute VideoPlayRecord videoPlayRecord,HttpSession session)  {
		boolean um = videoPlayRecordService.updateVideoPlayRecord(videoPlayRecord);
		return ResultUtil.getSR(um);
	}
	/**
	 * 视频播放记录增加
	 * @return 
	 */
	@ApiOperation(value = "视频播放记录增加", notes = "视频播放记录增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addVideoPlayRecord(@ModelAttribute VideoPlayRecord videoPlayRecord, HttpSession session) {
		boolean am = videoPlayRecordService.addVideoPlayRecord(videoPlayRecord);
		return ResultUtil.getSR(am);
	}
	/**
	 * 视频播放记录删除
	 * @return
	 */
	@ApiOperation(value = "视频播放记录删除", notes = "视频播放记录删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="videoPlayRecordId",value="视频播放记录ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delVideoPlayRecord(@RequestParam("videoPlayRecordId") Integer videoPlayRecordId,HttpSession session)  {
		boolean dm = videoPlayRecordService.delVideoPlayRecord(videoPlayRecordId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * 视频播放记录批量删除
	 * @return
	 */
	@ApiOperation(value = "视频播放记录批量删除videoPlayRecordId=1&videoPlayRecordId=2", notes = "视频播放记录批量删除")
	@ApiImplicitParams({
		@ApiImplicitParam(name="videoPlayRecordId",value="视频播放记录ID列表",dataType="array", paramType = "query",required=true)
	})
	@RequestMapping(value = "/deleteBatch", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delVideoPlayRecordList(
		 @RequestParam(value = "videoPlayRecordId") Integer[] videoPlayRecordId,
			HttpSession session)  {
		 boolean dm=false;
		for(int i=0;i<videoPlayRecordId.length;i++){
		    dm = videoPlayRecordService.delVideoPlayRecord(videoPlayRecordId[i]);
		  }
		return ResultUtil.getSR(dm);
	}
	/**
	 * 视频播放记录浏览数量
	 * @return
	 */
	@ApiOperation(value = "视频播放记录数量", notes = "视频播放记录数量查询")
	@ApiImplicitParams({
		 @ApiImplicitParam(name="videoId",value="视频id外键",dataType="int", paramType = "query"),
		  @ApiImplicitParam(name="accountId",value="收藏人id外键",dataType="int", paramType = "query")
		  })
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(
			@RequestParam(value="videoId",required=false)Integer videoId,
			@RequestParam(value="accountId",required=false)Integer accountId,
			HttpSession session)  {
		int count = videoPlayRecordService.countAll(videoId,accountId);
		return count;
	}
	/**
	 * 视频播放记录单个加载
	 * @return
	 */
	@ApiOperation(value = "视频播放记录单个加载", notes = "视频播放记录单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="videoPlayRecordId",value="视频播放记录ID",dataType="int", paramType = "path",required=true)
		  })
	@RequestMapping(value = "/{videoPlayRecordId}", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList loadVideoPlayRecord(@PathVariable("videoPlayRecordId") Integer videoPlayRecordId,HttpSession session)  {
		List<VideoPlayRecord> list = new ArrayList<VideoPlayRecord>();
		VideoPlayRecord videoPlayRecord = videoPlayRecordService.loadVideoPlayRecord(videoPlayRecordId);
			if(videoPlayRecord!=null &&!videoPlayRecord.equals("")){
				list.add(videoPlayRecord);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotIsNotExistException("视频播放记录");//不存在
			}
	}
	
}
