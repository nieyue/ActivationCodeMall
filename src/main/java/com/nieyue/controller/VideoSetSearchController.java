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

import com.nieyue.bean.VideoSet;
import com.nieyue.bean.VideoSetSearch;
import com.nieyue.exception.NotAnymoreException;
import com.nieyue.exception.NotIsNotExistException;
import com.nieyue.service.VideoSetSearchService;
import com.nieyue.service.VideoSetService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * 视频集搜索控制类
 * @author yy
 *
 */
@Api(tags={"videoSetSearch"},value="视频集搜索",description="视频集搜索管理")
@RestController
@RequestMapping("/videoSetSearch")
public class VideoSetSearchController {
	@Resource
	private VideoSetSearchService videoSetSearchService;
	@Resource
	private VideoSetService videoSetService;
	
	/**
	 * 视频集搜索分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "视频集搜索列表", notes = "视频集搜索分页浏览")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="name",value="名称",dataType="string", paramType = "query"),
	  @ApiImplicitParam(name="number",value="次数",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="update_date"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList browsePagingVideoSetSearch(
			@RequestParam(value="name",required=false)String name,
			@RequestParam(value="number",required=false)Integer number,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="number") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<VideoSetSearch> list = new ArrayList<VideoSetSearch>();
			list= videoSetSearchService.browsePagingVideoSetSearch(name,number,pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotAnymoreException();//没有更多
			}
	}
	/**
	 * 视频集搜索  查询
	 * @return 
	 */
	@ApiOperation(value = "视频集搜索 查询", notes = "视频集搜索查询")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="name",value="查询名称，模糊查询",dataType="string", paramType = "query"),
		  })
	@RequestMapping(value = "/search", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList searchVideoSetSearch(
			@RequestParam(value="name")String name,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="update_date") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay,
			HttpSession session) {
		List<VideoSetSearch> vssl = videoSetSearchService.browsePagingVideoSetSearch(name, null, 1, Integer.MAX_VALUE, "video_set_search_id", "asc");
		boolean am=false;
		//增加查询次数
		if(vssl.size()==1){
			VideoSetSearch vss = vssl.get(0);
			vss.setNumber(vss.getNumber()+1);
			am = videoSetSearchService.updateVideoSetSearch(vss);
		}else{
			VideoSetSearch vss =new VideoSetSearch();
			vss.setName(name);
			vss.setNumber(1);
			am = videoSetSearchService.addVideoSetSearch(vss);
		}
		//查询结果
		List<VideoSet> list=new ArrayList<VideoSet>();
		if(am){
			list = videoSetService.browsePagingVideoSet(name,null, null, null, null, null, 1, pageNum, pageSize, orderName, orderWay);
			
			return ResultUtil.getSlefSRSuccessList(list);
		} 
		return ResultUtil.getSlefSRFailList(list);
		
	}

	/**
	 * 视频集搜索修改
	 * @return
	 */
	@ApiOperation(value = "视频集搜索修改", notes = "视频集搜索修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateVideoSetSearch(@ModelAttribute VideoSetSearch videoSetSearch,HttpSession session)  {
		boolean um = videoSetSearchService.updateVideoSetSearch(videoSetSearch);
		return ResultUtil.getSR(um);
	}
	/**
	 * 视频集搜索增加
	 * @return 
	 */
	@ApiOperation(value = "视频集搜索增加", notes = "视频集搜索增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addVideoSetSearch(@ModelAttribute VideoSetSearch videoSetSearch, HttpSession session) {
		boolean am = videoSetSearchService.addVideoSetSearch(videoSetSearch);
		return ResultUtil.getSR(am);
	}
	/**
	 * 视频集搜索删除
	 * @return
	 */
	@ApiOperation(value = "视频集搜索删除", notes = "视频集搜索删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="videoSetSearchId",value="视频集搜索ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delVideoSetSearch(@RequestParam("videoSetSearchId") Integer videoSetSearchId,HttpSession session)  {
		boolean dm = videoSetSearchService.delVideoSetSearch(videoSetSearchId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * 视频集搜索浏览数量
	 * @return
	 */
	@ApiOperation(value = "视频集搜索数量", notes = "视频集搜索数量查询")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="name",value="名称",dataType="string", paramType = "query"),
		  @ApiImplicitParam(name="number",value="次数",dataType="int", paramType = "query")
		  })
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(
			@RequestParam(value="name",required=false) String name,
			@RequestParam(value="number",required=false) Integer number,
			HttpSession session)  {
		int count = videoSetSearchService.countAll(name,number);
		return count;
	}
	/**
	 * 视频集搜索单个加载
	 * @return
	 */
	@ApiOperation(value = "视频集搜索单个加载", notes = "视频集搜索单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="videoSetSearchId",value="视频集搜索ID",dataType="int", paramType = "path",required=true)
		  })
	@RequestMapping(value = "/{videoSetSearchId}", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList loadVideoSetSearch(@PathVariable("videoSetSearchId") Integer videoSetSearchId,HttpSession session)  {
		List<VideoSetSearch> list = new ArrayList<VideoSetSearch>();
		VideoSetSearch videoSetSearch = videoSetSearchService.loadVideoSetSearch(videoSetSearchId);
			if(videoSetSearch!=null &&!videoSetSearch.equals("")){
				list.add(videoSetSearch);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotIsNotExistException("视频集搜索");//不存在
			}
	}
	
}
