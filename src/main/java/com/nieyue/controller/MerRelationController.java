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

import com.nieyue.bean.MerRelation;
import com.nieyue.exception.NotAnymoreException;
import com.nieyue.exception.NotIsNotExistException;
import com.nieyue.service.MerRelationService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * 商品关系控制类
 * @author yy
 *
 */
@Api(tags={"merRelation"},value="商品关系",description="商品关系管理")
@RestController
@RequestMapping("/merRelation")
public class MerRelationController {
	@Resource
	private MerRelationService merRelationService;
	
	/**
	 * 商品关系分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "商品关系列表", notes = "商品关系分页浏览")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="platformMerId",value="平台商品id",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="sellerMerId",value="商家商品id",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="sellerAccountId",value="商家账户id",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="create_date"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<MerRelation>> browsePagingMerRelation(
			@RequestParam(value="platformMerId",required=false)Integer platformMerId,
			@RequestParam(value="sellerMerId",required=false)Integer sellerMerId,
			@RequestParam(value="sellerAccountId",required=false)Integer sellerAccountId,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="create_date") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<MerRelation> list = new ArrayList<MerRelation>();
			list= merRelationService.browsePagingMerRelation(platformMerId,sellerMerId,sellerAccountId,pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotAnymoreException();//没有更多
			}
	}
	/**
	 * 商品关系修改
	 * @return
	 */
	@ApiOperation(value = "商品关系修改", notes = "商品关系修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateMerRelation(@ModelAttribute MerRelation merRelation,HttpSession session)  {
		boolean um = merRelationService.updateMerRelation(merRelation);
		return ResultUtil.getSR(um);
	}
	/**
	 * 商品关系增加
	 * @return 
	 */
	@ApiOperation(value = "商品关系增加", notes = "商品关系增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addMerRelation(@ModelAttribute MerRelation merRelation, HttpSession session) {
		boolean am = merRelationService.addMerRelation(merRelation);
		return ResultUtil.getSR(am);
	}
	/**
	 * 商品关系删除
	 * @return
	 */
	@ApiOperation(value = "商品关系删除", notes = "商品关系删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="merRelationId",value="商品关系ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delMerRelation(@RequestParam("merRelationId") Integer merRelationId,HttpSession session)  {
		boolean dm = merRelationService.delMerRelation(merRelationId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * 商品关系浏览数量
	 * @return
	 */
	@ApiOperation(value = "商品关系数量", notes = "商品关系数量查询")
	@ApiImplicitParams({
		@ApiImplicitParam(name="platformMerId",value="平台商品id",dataType="int", paramType = "query"),
		  @ApiImplicitParam(name="sellerMerId",value="商家商品id",dataType="int", paramType = "query"),
		  @ApiImplicitParam(name="sellerAccountId",value="商家账户id",dataType="int", paramType = "query")
		  })
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(
			@RequestParam(value="platformMerId",required=false)Integer platformMerId,
			@RequestParam(value="sellerMerId",required=false)Integer sellerMerId,
			@RequestParam(value="sellerAccountId",required=false)Integer sellerAccountId,
			HttpSession session)  {
		int count = merRelationService.countAll(platformMerId,sellerMerId,sellerAccountId);
		return count;
	}
	/**
	 * 商品关系单个加载
	 * @return
	 */
	@ApiOperation(value = "商品关系单个加载", notes = "商品关系单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="merRelationId",value="商品关系ID",dataType="int", paramType = "path",required=true)
		  })
	@RequestMapping(value = "/load", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList<List<MerRelation>> loadMerRelation(@RequestParam("merRelationId") Integer merRelationId,HttpSession session)  {
		List<MerRelation> list = new ArrayList<MerRelation>();
		MerRelation merRelation = merRelationService.loadMerRelation(merRelationId);
			if(merRelation!=null &&!merRelation.equals("")){
				list.add(merRelation);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotIsNotExistException("商品关系");//不存在
			}
	}
	
}
