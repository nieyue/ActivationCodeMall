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

import com.nieyue.bean.Article;
import com.nieyue.exception.NotAnymoreException;
import com.nieyue.exception.NotIsNotExistException;
import com.nieyue.service.ArticleService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * 文章控制类
 * @author yy
 *
 */
@Api(tags={"article"},value="文章",description="文章管理")
@RestController
@RequestMapping("/article")
public class ArticleController {
	@Resource
	private ArticleService articleService;
	
	/**
	 * 文章分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "文章列表", notes = "文章分页浏览")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="articleCateId",value="文章类型id外键",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="commentNumber",value="评论数",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="createDate",value="创建时间",dataType="date-time", paramType = "query"),
	  @ApiImplicitParam(name="updateDate",value="更新时间",dataType="date-time", paramType = "query"),
	  @ApiImplicitParam(name="status",value="状态0下架,默认1上架",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="update_date"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList browsePagingArticle(
			@RequestParam(value="articleCateId",required=false)Integer articleCateId,
			@RequestParam(value="commentNumber",required=false)Integer commentNumber,
			@RequestParam(value="createDate",required=false)Date createDate,
			@RequestParam(value="updateDate",required=false)Date updateDate,
			@RequestParam(value="status",required=false)Integer status,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="update_date") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<Article> list = new ArrayList<Article>();
			list= articleService.browsePagingArticle(articleCateId,commentNumber,createDate,updateDate,status,pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotAnymoreException();//没有更多
			}
	}
	/**
	 * 文章修改
	 * @return
	 */
	@ApiOperation(value = "文章修改", notes = "文章修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateArticle(@ModelAttribute Article article,HttpSession session)  {
		boolean um = articleService.updateArticle(article);
		return ResultUtil.getSR(um);
	}
	/**
	 * 文章增加
	 * @return 
	 */
	@ApiOperation(value = "文章增加", notes = "文章增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addArticle(@ModelAttribute Article article, HttpSession session) {
		boolean am = articleService.addArticle(article);
		return ResultUtil.getSR(am);
	}
	/**
	 * 文章删除
	 * @return
	 */
	@ApiOperation(value = "文章删除", notes = "文章删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="articleId",value="文章ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delArticle(@RequestParam("articleId") Integer articleId,HttpSession session)  {
		boolean dm = articleService.delArticle(articleId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * 文章浏览数量
	 * @return
	 */
	@ApiOperation(value = "文章数量", notes = "文章数量查询")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="articleCateId",value="文章类型id,外键",dataType="int", paramType = "query"),
		  @ApiImplicitParam(name="commentNumber",value="评论数",dataType="int", paramType = "query"),
		  @ApiImplicitParam(name="createDate",value="创建时间",dataType="date-time", paramType = "query"),
		  @ApiImplicitParam(name="updateDate",value="更新时间",dataType="date-time", paramType = "query"),
		  @ApiImplicitParam(name="status",value="状态0下架,默认1上架",dataType="int", paramType = "query")
		  })
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(
			@RequestParam(value="articleCateId",required=false)Integer articleCateId,
			@RequestParam(value="commentNumber",required=false)Integer commentNumber,
			@RequestParam(value="createDate",required=false)Date createDate,
			@RequestParam(value="updateDate",required=false)Date updateDate,
			@RequestParam(value="status",required=false)Integer status,
			HttpSession session)  {
		int count = articleService.countAll(articleCateId,commentNumber,createDate,updateDate,status);
		return count;
	}
	/**
	 * 文章单个加载
	 * @return
	 */
	@ApiOperation(value = "文章单个加载", notes = "文章单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="articleId",value="文章ID",dataType="int", paramType = "path",required=true)
		  })
	@RequestMapping(value = "/{articleId}", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList loadArticle(@PathVariable("articleId") Integer articleId,HttpSession session)  {
		List<Article> list = new ArrayList<Article>();
		Article article = articleService.loadArticle(articleId);
			if(article!=null &&!article.equals("")){
				list.add(article);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotIsNotExistException("文章");//不存在
			}
	}
	
}
