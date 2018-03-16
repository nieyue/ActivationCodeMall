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

import com.nieyue.bean.Split;
import com.nieyue.exception.CommonNotRollbackException;
import com.nieyue.exception.NotAnymoreException;
import com.nieyue.exception.NotIsNotExistException;
import com.nieyue.service.SplitService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * 拆分控制类
 * @author yy
 *
 */
@Api(tags={"split"},value="拆分",description="拆分管理")
@RestController
@RequestMapping("/split")
public class SplitController {
	@Resource
	private SplitService splitService;
	
	
	/**
	 * 拆分分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "拆分列表", notes = "拆分分页浏览")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="recommendAccountId",value="推荐人id",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="accountId",value="账户自身id,邀请码",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="buyAccountId",value="购买者id,外键",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="applyDate",value="申请时间",dataType="date-time", paramType = "query"),
	  @ApiImplicitParam(name="splitDate",value="拆分时间",dataType="date-time", paramType = "query"),
	  @ApiImplicitParam(name="createDate",value="创建时间",dataType="date-time", paramType = "query"),
	  @ApiImplicitParam(name="updateDate",value="更新时间",dataType="date-time", paramType = "query"),
	  @ApiImplicitParam(name="status",value="拆分状态，默认0已申请，1已拆分，2已拒绝，3已退款，4已推荐给上级",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="create_date"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList browsePagingSplit(
			@RequestParam(value="recommendAccountId",required=false)Integer recommendAccountId,
			@RequestParam(value="accountId",required=false)Integer accountId,
			@RequestParam(value="buyAccountId",required=false)Integer buyAccountId,
			@RequestParam(value="applyDate",required=false)Date applyDate,
			@RequestParam(value="splitDate",required=false)Date splitDate,
			@RequestParam(value="createDate",required=false)Date createDate,
			@RequestParam(value="updateDate",required=false)Date updateDate,
			@RequestParam(value="status",required=false)Integer status,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="create_date") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<Split> list = new ArrayList<Split>();
			list= splitService.browsePagingSplit(recommendAccountId,accountId,buyAccountId,applyDate,splitDate,createDate,updateDate,status,pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotAnymoreException();//没有更多
			}
	}
	/**
	 * 立即拆分
	 * @return
	 * @throws CommonNotRollbackException 
	 */
	@ApiOperation(value = "立即拆分", notes = "立即拆分")
	@ApiImplicitParams({
		@ApiImplicitParam(name="splitId",value="拆分id",dataType="int", paramType = "query",required=true),
		@ApiImplicitParam(name="accountId",value="拆分人的id",dataType="int", paramType = "query",required=true)
	})
	@RequestMapping(value = "/immediatelySplit", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult immediatelySplit(
			@RequestParam(value="splitId")Integer splitId,
			@RequestParam(value="accountId")Integer accountId,
			HttpSession session) throws CommonNotRollbackException  {
		boolean b = splitService.immediatelySplit(splitId, accountId);
		return ResultUtil.getSR(b);
		
	}
	/**
	 * 推荐给上级
	 * @return
	 */
	@ApiOperation(value = "推荐给上级", notes = "推荐给上级")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="splitId",value="拆分id",dataType="int", paramType = "query",required=true),
		  @ApiImplicitParam(name="accountId",value="点击推荐上级的人的账户id",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/recommendParent", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult recommendParent(
			@RequestParam(value="splitId")Integer splitId,
			@RequestParam(value="accountId")Integer accountId,
			HttpSession session)  {
		boolean b = splitService.recommendParent(splitId, accountId);
		return ResultUtil.getSR(b);
	}
	/**
	 * 拆分修改
	 * @return
	 */
	@ApiOperation(value = "拆分修改", notes = "拆分修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateSplit(@ModelAttribute Split split,HttpSession session)  {
		boolean um = splitService.updateSplit(split);
		return ResultUtil.getSR(um);
	}
	/**
	 * 拆分增加
	 * @return 
	 */
	@ApiOperation(value = "拆分增加", notes = "拆分增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addSplit(@ModelAttribute Split split, HttpSession session) {
		boolean am = splitService.addSplit(split);
		return ResultUtil.getSR(am);
	}
	/**
	 * 拆分删除
	 * @return
	 */
	@ApiOperation(value = "拆分删除", notes = "拆分删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="splitId",value="拆分ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delSplit(@RequestParam("splitId") Integer splitId,HttpSession session)  {
		boolean dm = splitService.delSplit(splitId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * 拆分浏览数量
	 * @return
	 */
	@ApiOperation(value = "拆分数量", notes = "拆分数量查询")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="recommendAccountId",value="推荐人id",dataType="int", paramType = "query"),
		  @ApiImplicitParam(name="accountId",value="账户自身id,邀请码",dataType="int", paramType = "query"),
		  @ApiImplicitParam(name="buyAccountId",value="购买者id,外键",dataType="int", paramType = "query"),
		  @ApiImplicitParam(name="applyDate",value="申请时间",dataType="date-time", paramType = "query"),
		  @ApiImplicitParam(name="splitDate",value="拆分时间",dataType="date-time", paramType = "query"),
		  @ApiImplicitParam(name="createDate",value="创建时间",dataType="date-time", paramType = "query"),
		  @ApiImplicitParam(name="updateDate",value="更新时间",dataType="date-time", paramType = "query"),
		  @ApiImplicitParam(name="status",value="拆分状态，默认0已申请，1已拆分，2已拒绝，3已退款，4已推荐给上级",dataType="int", paramType = "query")
		  })
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(
			@RequestParam(value="recommendAccountId",required=false)Integer recommendAccountId,
			@RequestParam(value="accountId",required=false)Integer accountId,
			@RequestParam(value="buyAccountId",required=false)Integer buyAccountId,
			@RequestParam(value="applyDate",required=false)Date applyDate,
			@RequestParam(value="splitDate",required=false)Date splitDate,
			@RequestParam(value="createDate",required=false)Date createDate,
			@RequestParam(value="updateDate",required=false)Date updateDate,
			@RequestParam(value="status",required=false)Integer status,
			HttpSession session)  {
		int count = splitService.countAll(recommendAccountId,accountId,buyAccountId,applyDate,splitDate,createDate,updateDate,status);
		return count;
	}
	/**
	 * 拆分单个加载
	 * @return
	 */
	@ApiOperation(value = "拆分单个加载", notes = "拆分单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="splitId",value="拆分ID",dataType="int", paramType = "path",required=true)
		  })
	@RequestMapping(value = "/{splitId}", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList loadSplit(@PathVariable("splitId") Integer splitId,HttpSession session)  {
		List<Split> list = new ArrayList<Split>();
		Split split = splitService.loadSplit(splitId);
			if(split!=null &&!split.equals("")){
				list.add(split);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotIsNotExistException("拆分");//不存在
			}
	}
	
}
