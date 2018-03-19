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

import com.nieyue.bean.MerOrderComment;
import com.nieyue.exception.NotAnymoreException;
import com.nieyue.exception.NotIsNotExistException;
import com.nieyue.service.MerOrderCommentService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * 商品订单评论控制类
 * @author yy
 *
 */
@Api(tags={"merOrderComment"},value="商品订单评论",description="商品订单评论管理")
@RestController
@RequestMapping("/merOrderComment")
public class MerOrderCommentController {
	@Resource
	private MerOrderCommentService merOrderCommentService;
	
	/**
	 * 商品订单评论分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "商品订单评论列表", notes = "商品订单评论分页浏览")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="merScore",value="评分",dataType="double", paramType = "query"),
	  @ApiImplicitParam(name="merId",value="商品id",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="orderId",value="订单id",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="accountId",value="账户id",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="create_date"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<MerOrderComment>> browsePagingMerOrderComment(
			@RequestParam(value="merScore",required=false)Double merScore,
			@RequestParam(value="merId",required=false)Integer merId,
			@RequestParam(value="orderId",required=false)Integer orderId,
			@RequestParam(value="accountId",required=false)Integer accountId,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="create_date") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<MerOrderComment> list = new ArrayList<MerOrderComment>();
			list= merOrderCommentService.browsePagingMerOrderComment(merScore,merId,orderId,accountId,pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotAnymoreException();//没有更多
			}
	}
	/**
	 * 商品订单评论修改
	 * @return
	 */
	@ApiOperation(value = "商品订单评论修改", notes = "商品订单评论修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateMerOrderComment(@ModelAttribute MerOrderComment merOrderComment,HttpSession session)  {
		boolean um = merOrderCommentService.updateMerOrderComment(merOrderComment);
		return ResultUtil.getSR(um);
	}
	/**
	 * 商品订单评论增加
	 * @return 
	 */
	@ApiOperation(value = "商品订单评论增加", notes = "商品订单评论增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addMerOrderComment(@ModelAttribute MerOrderComment merOrderComment, HttpSession session) {
		boolean am = merOrderCommentService.addMerOrderComment(merOrderComment);
		return ResultUtil.getSR(am);
	}
	/**
	 * 商品订单评论删除
	 * @return
	 */
	@ApiOperation(value = "商品订单评论删除", notes = "商品订单评论删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="merOrderCommentId",value="商品订单评论ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delMerOrderComment(@RequestParam("merOrderCommentId") Integer merOrderCommentId,HttpSession session)  {
		boolean dm = merOrderCommentService.delMerOrderComment(merOrderCommentId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * 商品订单评论浏览数量
	 * @return
	 */
	@ApiOperation(value = "商品订单评论数量", notes = "商品订单评论数量查询")
	@ApiImplicitParams({
		 @ApiImplicitParam(name="merScore",value="评分",dataType="double", paramType = "query"),
		  @ApiImplicitParam(name="merId",value="商品id",dataType="int", paramType = "query"),
		  @ApiImplicitParam(name="orderId",value="订单id",dataType="int", paramType = "query"),
		  @ApiImplicitParam(name="accountId",value="账户id",dataType="int", paramType = "query")
		  })
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(
			@RequestParam(value="merScore",required=false)Double merScore,
			@RequestParam(value="merId",required=false)Integer merId,
			@RequestParam(value="orderId",required=false)Integer orderId,
			@RequestParam(value="accountId",required=false)Integer accountId,
			HttpSession session)  {
		int count = merOrderCommentService.countAll(merScore,merId,orderId,accountId);
		return count;
	}
	/**
	 * 商品订单评论单个加载
	 * @return
	 */
	@ApiOperation(value = "商品订单评论单个加载", notes = "商品订单评论单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="merOrderCommentId",value="商品订单评论ID",dataType="int", paramType = "path",required=true)
		  })
	@RequestMapping(value = "/load", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList<List<MerOrderComment>> loadMerOrderComment(@RequestParam("merOrderCommentId") Integer merOrderCommentId,HttpSession session)  {
		List<MerOrderComment> list = new ArrayList<MerOrderComment>();
		MerOrderComment merOrderComment = merOrderCommentService.loadMerOrderComment(merOrderCommentId);
			if(merOrderComment!=null &&!merOrderComment.equals("")){
				list.add(merOrderComment);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotIsNotExistException("商品订单评论");//不存在
			}
	}
	
}
