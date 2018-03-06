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

import com.nieyue.bean.Feedback;
import com.nieyue.service.FeedbackService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * 意见反馈控制类
 * @author yy
 *
 */
@Api(tags={"feedback"},value="意见反馈",description="意见反馈管理")
@RestController
@RequestMapping("/feedback")
public class FeedbackController {
	@Resource
	private FeedbackService feedbackService;
	
	/**
	 * 意见反馈分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "意见反馈列表", notes = "意见反馈分页浏览")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="create_date"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList browsePagingFeedback(
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="create_date") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<Feedback> list = new ArrayList<Feedback>();
			list= feedbackService.browsePagingFeedback(pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				return ResultUtil.getSlefSRFailList(list);
			}
	}
	/**
	 * 意见反馈修改
	 * @return
	 */
	@ApiOperation(value = "意见反馈修改", notes = "意见反馈修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateFeedback(@ModelAttribute Feedback feedback,HttpSession session)  {
		boolean um = feedbackService.updateFeedback(feedback);
		return ResultUtil.getSR(um);
	}
	/**
	 * 意见反馈增加
	 * @return 
	 */
	@ApiOperation(value = "意见反馈增加", notes = "意见反馈增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addFeedback(@ModelAttribute Feedback feedback, HttpSession session) {
		boolean am = feedbackService.addFeedback(feedback);
		return ResultUtil.getSR(am);
	}
	/**
	 * 意见反馈删除
	 * @return
	 */
	@ApiOperation(value = "意见反馈删除", notes = "意见反馈删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="feedbackId",value="意见反馈ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delFeedback(@RequestParam("feedbackId") Integer feedbackId,HttpSession session)  {
		boolean dm = feedbackService.delFeedback(feedbackId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * 意见反馈浏览数量
	 * @return
	 */
	@ApiOperation(value = "意见反馈数量", notes = "意见反馈数量查询")
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(HttpSession session)  {
		int count = feedbackService.countAll();
		return count;
	}
	/**
	 * 意见反馈单个加载
	 * @return
	 */
	@ApiOperation(value = "意见反馈单个加载", notes = "意见反馈单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="feedbackId",value="意见反馈ID",dataType="int", paramType = "path",required=true)
		  })
	@RequestMapping(value = "/{feedbackId}", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList loadFeedback(@PathVariable("feedbackId") Integer feedbackId,HttpSession session)  {
		List<Feedback> list = new ArrayList<Feedback>();
		Feedback feedback = feedbackService.loadFeedback(feedbackId);
			if(feedback!=null &&!feedback.equals("")){
				list.add(feedback);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				return ResultUtil.getSlefSRFailList(list);
			}
	}
	
}
