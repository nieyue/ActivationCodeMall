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

import com.nieyue.bean.ArticleCate;
import com.nieyue.service.ArticleCateService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * 文章类型控制类
 * @author yy
 *
 */
@Api(tags={"articleCate"},value="文章类型",description="文章类型管理")
@RestController
@RequestMapping("/articleCate")
public class ArticleCateController {
	@Resource
	private ArticleCateService articleCateService;
	
	/**
	 * 文章类型分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "文章类型列表", notes = "文章类型分页浏览")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="update_date"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList browsePagingArticleCate(
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="update_date") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<ArticleCate> list = new ArrayList<ArticleCate>();
			list= articleCateService.browsePagingArticleCate(pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				return ResultUtil.getSlefSRFailList(list);
			}
	}
	/**
	 * 文章类型修改
	 * @return
	 */
	@ApiOperation(value = "文章类型修改", notes = "文章类型修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateArticleCate(@ModelAttribute ArticleCate articleCate,HttpSession session)  {
		boolean um = articleCateService.updateArticleCate(articleCate);
		return ResultUtil.getSR(um);
	}
	/**
	 * 文章类型增加
	 * @return 
	 */
	@ApiOperation(value = "文章类型增加", notes = "文章类型增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addArticleCate(@ModelAttribute ArticleCate articleCate, HttpSession session) {
		boolean am = articleCateService.addArticleCate(articleCate);
		return ResultUtil.getSR(am);
	}
	/**
	 * 文章类型删除
	 * @return
	 */
	@ApiOperation(value = "文章类型删除", notes = "文章类型删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="articleCateId",value="文章类型ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delArticleCate(@RequestParam("articleCateId") Integer articleCateId,HttpSession session)  {
		boolean dm = articleCateService.delArticleCate(articleCateId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * 文章类型浏览数量
	 * @return
	 */
	@ApiOperation(value = "文章类型数量", notes = "文章类型数量查询")
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(HttpSession session)  {
		int count = articleCateService.countAll();
		return count;
	}
	/**
	 * 文章类型单个加载
	 * @return
	 */
	@ApiOperation(value = "文章类型单个加载", notes = "文章类型单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="articleCateId",value="文章类型ID",dataType="int", paramType = "path",required=true)
		  })
	@RequestMapping(value = "/{articleCateId}", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList loadArticleCate(@PathVariable("articleCateId") Integer articleCateId,HttpSession session)  {
		List<ArticleCate> list = new ArrayList<ArticleCate>();
		ArticleCate articleCate = articleCateService.loadArticleCate(articleCateId);
			if(articleCate!=null &&!articleCate.equals("")){
				list.add(articleCate);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				return ResultUtil.getSlefSRFailList(list);
			}
	}
	
}
