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

import com.nieyue.bean.SpreadLinkTerm;
import com.nieyue.exception.NotAnymoreException;
import com.nieyue.exception.NotIsNotExistException;
import com.nieyue.service.SpreadLinkTermService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * 推广链接项控制类
 * @author yy
 *
 */
@Api(tags={"spreadLinkTerm"},value="推广链接项",description="推广链接项管理")
@RestController
@RequestMapping("/spreadLinkTerm")
public class SpreadLinkTermController {
	@Resource
	private SpreadLinkTermService spreadLinkTermService;
	
	/**
	 * 推广链接项分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "推广链接项列表", notes = "推广链接项分页浏览")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="create_date"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<SpreadLinkTerm>> browsePagingSpreadLinkTerm(
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="create_date") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<SpreadLinkTerm> list = new ArrayList<SpreadLinkTerm>();
			list= spreadLinkTermService.browsePagingSpreadLinkTerm(pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotAnymoreException();//没有更多
			}
	}
	/**
	 * 推广链接项修改
	 * @return
	 */
	@ApiOperation(value = "推广链接项修改", notes = "推广链接项修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateSpreadLinkTerm(@ModelAttribute SpreadLinkTerm spreadLinkTerm,HttpSession session)  {
		boolean um = spreadLinkTermService.updateSpreadLinkTerm(spreadLinkTerm);
		return ResultUtil.getSR(um);
	}
	/**
	 * 推广链接项增加
	 * @return 
	 */
	@ApiOperation(value = "推广链接项增加", notes = "推广链接项增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addSpreadLinkTerm(@ModelAttribute SpreadLinkTerm spreadLinkTerm, HttpSession session) {
		boolean am = spreadLinkTermService.addSpreadLinkTerm(spreadLinkTerm);
		return ResultUtil.getSR(am);
	}
	/**
	 * 推广链接项删除
	 * @return
	 */
	@ApiOperation(value = "推广链接项删除", notes = "推广链接项删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="spreadLinkTermId",value="推广链接项ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delSpreadLinkTerm(@RequestParam("spreadLinkTermId") Integer spreadLinkTermId,HttpSession session)  {
		boolean dm = spreadLinkTermService.delSpreadLinkTerm(spreadLinkTermId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * 推广链接项浏览数量
	 * @return
	 */
	@ApiOperation(value = "推广链接项数量", notes = "推广链接项数量查询")
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(HttpSession session)  {
		int count = spreadLinkTermService.countAll();
		return count;
	}
	/**
	 * 推广链接项单个加载
	 * @return
	 */
	@ApiOperation(value = "推广链接项单个加载", notes = "推广链接项单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="spreadLinkTermId",value="推广链接项ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/load", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList<List<SpreadLinkTerm>> loadSpreadLinkTerm(@RequestParam("spreadLinkTermId") Integer spreadLinkTermId,HttpSession session)  {
		List<SpreadLinkTerm> list = new ArrayList<SpreadLinkTerm>();
		SpreadLinkTerm spreadLinkTerm = spreadLinkTermService.loadSpreadLinkTerm(spreadLinkTermId);
			if(spreadLinkTerm!=null &&!spreadLinkTerm.equals("")){
				list.add(spreadLinkTerm);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotIsNotExistException("推广链接项");//不存在
			}
	}
	
}
