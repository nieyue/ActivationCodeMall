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

import com.nieyue.bean.Medal;
import com.nieyue.service.MedalService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * 勋章控制类
 * @author yy
 *
 */
@Api(tags={"medal"},value="勋章",description="勋章管理")
@RestController
@RequestMapping("/medal")
public class MedalController {
	@Resource
	private MedalService medalService;
	
	/**
	 * 勋章分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "勋章列表", notes = "勋章分页浏览")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="medalTermId",value="勋章项ID",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="accountId",value="勋章人ID",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="createDate",value="创建时间",dataType="date-time", paramType = "query"),
	  @ApiImplicitParam(name="updateDate",value="更新时间",dataType="date-time", paramType = "query"),
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="create_date"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList browsePagingMedal(
			@RequestParam(value="medalTermId",required=false)Integer medalTermId,
			@RequestParam(value="accountId",required=false)Integer accountId,
			@RequestParam(value="createDate",required=false)Date createDate,
			@RequestParam(value="updateDate",required=false)Date updateDate,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="create_date") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<Medal> list = new ArrayList<Medal>();
			list= medalService.browsePagingMedal(medalTermId,accountId,createDate,updateDate,pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				return ResultUtil.getSlefSRFailList(list);
			}
	}
	/**
	 * 勋章修改
	 * @return
	 */
	@ApiOperation(value = "勋章修改", notes = "勋章修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateMedal(@ModelAttribute Medal medal,HttpSession session)  {
		boolean um = medalService.updateMedal(medal);
		return ResultUtil.getSR(um);
	}
	/**
	 * 勋章增加
	 * @return 
	 */
	@ApiOperation(value = "勋章增加", notes = "勋章增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addMedal(@ModelAttribute Medal medal, HttpSession session) {
		boolean am = medalService.addMedal(medal);
		return ResultUtil.getSR(am);
	}
	/**
	 * 勋章删除
	 * @return
	 */
	@ApiOperation(value = "勋章删除", notes = "勋章删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="MedalId",value="勋章ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delMedal(@RequestParam("medalId") Integer medalId,HttpSession session)  {
		boolean dm = medalService.delMedal(medalId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * 勋章浏览数量
	 * @return
	 */
	@ApiOperation(value = "勋章数量", notes = "勋章数量查询")
	@ApiImplicitParams({
		@ApiImplicitParam(name="medalTermId",value="勋章项ID",dataType="int", paramType = "query"),
		  @ApiImplicitParam(name="accountId",value="勋章人ID",dataType="int", paramType = "query"),
		  @ApiImplicitParam(name="createDate",value="创建时间",dataType="date-time", paramType = "query"),
		  @ApiImplicitParam(name="updateDate",value="更新时间",dataType="date-time", paramType = "query")
		  })
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(
			@RequestParam(value="medalTermId",required=false)Integer medalTermId,
			@RequestParam(value="accountId",required=false)Integer accountId,
			@RequestParam(value="createDate",required=false)Date createDate,
			@RequestParam(value="updateDate",required=false)Date updateDate,
			HttpSession session)  {
		int count = medalService.countAll(medalTermId,accountId,createDate,updateDate);
		return count;
	}
	/**
	 * 勋章单个加载
	 * @return
	 */
	@ApiOperation(value = "勋章单个加载", notes = "勋章单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="medalId",value="勋章ID",dataType="int", paramType = "path",required=true)
		  })
	@RequestMapping(value = "/{medalId}", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList loadMedal(@PathVariable("medalId") Integer medalId,HttpSession session)  {
		List<Medal> list = new ArrayList<Medal>();
		Medal medal = medalService.loadMedal(medalId);
			if(medal!=null &&!medal.equals("")){
				list.add(medal);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				return ResultUtil.getSlefSRFailList(list);
			}
	}
	
}
