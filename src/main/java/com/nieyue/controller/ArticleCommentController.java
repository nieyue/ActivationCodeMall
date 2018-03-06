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

import com.nieyue.bean.ArticleComment;
import com.nieyue.service.ArticleCommentService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;


/**
 * 文章评论控制类
 * @author yy
 *
 */
@Api(tags={"articleComment"},value="文章评论",description="文章评论管理")
@RestController
@RequestMapping("/articleComment")
public class ArticleCommentController {
	@Resource
	private ArticleCommentService articleCommentService;
	
	/**
	 * 文章评论分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "文章评论列表", notes = "文章评论分页浏览")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="pointNumber",value="点赞数",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="articleId",value="文章Id",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="accountId",value="账户Id",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="create_date"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList browsePagingArticleComment(
			@RequestParam(value="pointNumber",required=false)Integer pointNumber,
			@RequestParam(value="articleId",required=false)Integer articleId,
			@RequestParam(value="accountId",required=false)Integer accountId,	
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="create_date") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<ArticleComment> list = new ArrayList<ArticleComment>();
			list= articleCommentService.browsePagingArticleComment(pointNumber,articleId,accountId,pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				return ResultUtil.getSlefSRFailList(list);
			}
	}
	/**
	 * 文章评论修改
	 * @return
	 */
	@ApiOperation(value = "文章评论修改", notes = "文章评论修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateArticleComment(@ModelAttribute ArticleComment articleComment,HttpSession session)  {
		boolean um = articleCommentService.updateArticleComment(articleComment);
		return ResultUtil.getSR(um);
	}
	/**
	 * 文章评论增加
	 * @return 
	 */
	@ApiOperation(value = "文章评论增加", notes = "文章评论增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addArticleComment(@ModelAttribute ArticleComment articleComment, HttpSession session) {
		boolean am = articleCommentService.addArticleComment(articleComment);
		return ResultUtil.getSR(am);
	}
	/**
	 * 文章评论删除
	 * @return
	 */
	@ApiOperation(value = "文章评论删除", notes = "文章评论删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="articleCommentId",value="文章评论ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delArticleComment(@RequestParam("articleCommentId") Integer articleCommentId,HttpSession session)  {
		boolean dm = articleCommentService.delArticleComment(articleCommentId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * 文章评论浏览数量
	 * @return
	 */
	@ApiOperation(value = "文章评论数量", notes = "文章评论数量查询")
	@ApiImplicitParams({
		@ApiImplicitParam(name="pointNumber",value="点赞数",dataType="int", paramType = "query"),
		  @ApiImplicitParam(name="articleId",value="文章Id",dataType="int", paramType = "query"),
		  @ApiImplicitParam(name="accountId",value="账户Id",dataType="int", paramType = "query")
		  })
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(
			@RequestParam(value="pointNumber",required=false)Integer pointNumber,
			@RequestParam(value="articleId",required=false)Integer articleId,
			@RequestParam(value="accountId",required=false)Integer accountId,
			HttpSession session)  {
		int count = articleCommentService.countAll(pointNumber,articleId,accountId);
		return count;
	}
	/**
	 * 文章评论单个加载
	 * @return
	 */
	@ApiOperation(value = "文章评论单个加载", notes = "文章评论单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="articleCommentId",value="文章评论ID",dataType="int", paramType = "path",required=true)
		  })
	@RequestMapping(value = "/{articleCommentId}", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList loadArticleComment(@PathVariable("articleCommentId") Integer articleCommentId,HttpSession session)  {
		List<ArticleComment> list = new ArrayList<ArticleComment>();
		ArticleComment articleComment = articleCommentService.loadArticleComment(articleCommentId);
			if(articleComment!=null &&!articleComment.equals("")){
				list.add(articleComment);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				return ResultUtil.getSlefSRFailList(list);
			}
	}
	/**
	 * 点赞
	 * @return
	 */
	@RequestMapping(value = "/point", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList pointComment(
			@RequestParam("articleCommentId") Integer articleCommentId,
			@RequestParam("articleId") Integer articleId,
			@RequestParam("accountId") Integer accountId,
			HttpSession session)  {
		List<JSONObject> list = new ArrayList<JSONObject>();
		ArticleComment articleComment = articleCommentService.loadArticleComment(articleCommentId);
		//session.setAttribute("pointaid"+accountId+"acid"+articleCommentId+, value);
		JSONObject json = new JSONObject();
			articleComment.setPointNumber(articleComment.getPointNumber()+1);
			articleCommentService.updateArticleComment(articleComment);
			json.put("isPoint", 1);
			list.add(json);
			return ResultUtil.getSlefSRSuccessList(list);
	}
}
