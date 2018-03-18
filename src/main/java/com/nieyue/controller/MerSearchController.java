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

import com.nieyue.bean.MerSearch;
import com.nieyue.exception.NotAnymoreException;
import com.nieyue.exception.NotIsNotExistException;
import com.nieyue.service.MerSearchService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * 商品搜索控制类
 * @author yy
 *
 */
@Api(tags={"merSearch"},value="商品搜索",description="商品搜索管理")
@RestController
@RequestMapping("/merSearch")
public class MerSearchController {
	@Resource
	private MerSearchService merSearchService;
	
	/**
	 * 商品搜索分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "商品搜索列表", notes = "商品搜索分页浏览")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="update_date"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<MerSearch>> browsePagingMerSearch(
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="update_date") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<MerSearch> list = new ArrayList<MerSearch>();
			list= merSearchService.browsePagingMerSearch(pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotAnymoreException();//没有更多
			}
	}
	/**
	 * 商品搜索修改
	 * @return
	 */
	@ApiOperation(value = "商品搜索修改", notes = "商品搜索修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateMerSearch(@ModelAttribute MerSearch merSearch,HttpSession session)  {
		boolean um = merSearchService.updateMerSearch(merSearch);
		return ResultUtil.getSR(um);
	}
	/**
	 * 商品搜索增加
	 * @return 
	 */
	@ApiOperation(value = "商品搜索增加", notes = "商品搜索增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addMerSearch(@ModelAttribute MerSearch merSearch, HttpSession session) {
		boolean am = merSearchService.addMerSearch(merSearch);
		return ResultUtil.getSR(am);
	}
	/**
	 * 商品搜索删除
	 * @return
	 */
	@ApiOperation(value = "商品搜索删除", notes = "商品搜索删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="merSearchId",value="商品搜索ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delMerSearch(@RequestParam("merSearchId") Integer merSearchId,HttpSession session)  {
		boolean dm = merSearchService.delMerSearch(merSearchId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * 商品搜索浏览数量
	 * @return
	 */
	@ApiOperation(value = "商品搜索数量", notes = "商品搜索数量查询")
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(HttpSession session)  {
		int count = merSearchService.countAll();
		return count;
	}
	/**
	 * 商品搜索单个加载
	 * @return
	 */
	@ApiOperation(value = "商品搜索单个加载", notes = "商品搜索单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="merSearchId",value="商品搜索ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/load", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList<List<MerSearch>> loadMerSearch(@RequestParam("merSearchId") Integer merSearchId,HttpSession session)  {
		List<MerSearch> list = new ArrayList<MerSearch>();
		MerSearch merSearch = merSearchService.loadMerSearch(merSearchId);
			if(merSearch!=null &&!merSearch.equals("")){
				list.add(merSearch);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotIsNotExistException("商品搜索");//不存在
			}
	}
	
}
