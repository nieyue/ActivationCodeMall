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

import com.nieyue.bean.SpreadLink;
import com.nieyue.exception.NotAnymoreException;
import com.nieyue.exception.NotIsNotExistException;
import com.nieyue.service.SpreadLinkService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * 推广链接控制类
 * @author yy
 *
 */
@Api(tags={"spreadLink"},value="推广链接",description="推广链接管理")
@RestController
@RequestMapping("/spreadLink")
public class SpreadLinkController {
	@Resource
	private SpreadLinkService spreadLinkService;
	
	/**
	 * 推广链接分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "推广链接列表", notes = "推广链接分页浏览")
	@ApiImplicitParams({
		@ApiImplicitParam(name="merId",value="商品id",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="accountId",value="推广账户id",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="create_date"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<SpreadLink>> browsePagingSpreadLink(
			@RequestParam(value="merId",required=false)Integer merId,
			@RequestParam(value="accountId",required=false)Integer accountId,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="create_date") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<SpreadLink> list = new ArrayList<SpreadLink>();
			list= spreadLinkService.browsePagingSpreadLink(merId,accountId,pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotAnymoreException();//没有更多
			}
	}
	/**
	 * 推广链接修改
	 * @return
	 */
	@ApiOperation(value = "推广链接修改", notes = "推广链接修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateSpreadLink(@ModelAttribute SpreadLink spreadLink,HttpSession session)  {
		boolean um = spreadLinkService.updateSpreadLink(spreadLink);
		return ResultUtil.getSR(um);
	}
	/**
	 * 推广链接增加
	 * @return 
	 */
	@ApiOperation(value = "推广链接增加", notes = "推广链接增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addSpreadLink(@ModelAttribute SpreadLink spreadLink, HttpSession session) {
		boolean am = spreadLinkService.addSpreadLink(spreadLink);
		return ResultUtil.getSR(am);
	}
	/**
	 * 推广链接删除
	 * @return
	 */
	@ApiOperation(value = "推广链接删除", notes = "推广链接删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="spreadLinkId",value="推广链接ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delSpreadLink(@RequestParam("spreadLinkId") Integer spreadLinkId,HttpSession session)  {
		boolean dm = spreadLinkService.delSpreadLink(spreadLinkId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * 推广链接浏览数量
	 * @return
	 */
	@ApiOperation(value = "推广链接数量", notes = "推广链接数量查询")
	@ApiImplicitParams({
		@ApiImplicitParam(name="merId",value="商品id",dataType="int", paramType = "query"),
		@ApiImplicitParam(name="accountId",value="推广账户id",dataType="int", paramType = "query")
	  })
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(
			@RequestParam(value="merId",required=false)Integer merId,
			@RequestParam(value="accountId",required=false)Integer accountId,
			HttpSession session)  {
		int count = spreadLinkService.countAll(merId,accountId);
		return count;
	}
	/**
	 * 推广链接单个加载
	 * @return
	 */
	@ApiOperation(value = "推广链接单个加载", notes = "推广链接单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="spreadLinkId",value="推广链接ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/load", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList<List<SpreadLink>> loadSpreadLink(@RequestParam("spreadLinkId") Integer spreadLinkId,HttpSession session)  {
		List<SpreadLink> list = new ArrayList<SpreadLink>();
		SpreadLink spreadLink = spreadLinkService.loadSpreadLink(spreadLinkId);
			if(spreadLink!=null &&!spreadLink.equals("")){
				list.add(spreadLink);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotIsNotExistException("推广链接");//不存在
			}
	}
	
}
