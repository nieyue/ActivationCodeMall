package com.nieyue.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nieyue.bean.Mer;
import com.nieyue.exception.NotAnymoreException;
import com.nieyue.exception.NotIsNotExistException;
import com.nieyue.service.MerService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * 商品控制类
 * @author yy
 *
 */
@Api(tags={"mer"},value="商品",description="商品管理")
@RestController
@RequestMapping("/mer")
public class MerController {
	@Resource
	private MerService merService;
	
	/**
	 * 商品分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "商品列表", notes = "商品分页浏览")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="region",value="范围，1官网自营，2商户非自营，3商户自营",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="type",value="类型，1普通商品，2降价商品，3预购商品",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="name",value="名称,模糊查询",dataType="string", paramType = "query"),
	  @ApiImplicitParam(name="recommend",value="推荐，默认0不推，1封推，2推荐",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="unitPrice",value="单价",dataType="double", paramType = "query"),
	  @ApiImplicitParam(name="saleNumber",value="销量",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="merScore",value="商品评分",dataType="double", paramType = "query"),
	  @ApiImplicitParam(name="merCateId",value="商品类型id",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="sellerAccountId",value="商户账户id",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="createDate",value="创建时间",dataType="date-time", paramType = "query"),
	  @ApiImplicitParam(name="updateDate",value="更新时间",dataType="date-time", paramType = "query"),
	  @ApiImplicitParam(name="status",value="状态0下架,默认1上架",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="update_date"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Mer>> browsePagingMer(
			@RequestParam(value="region",required=false)Integer region,
			@RequestParam(value="type",required=false)Integer type,
			@RequestParam(value="name",required=false)String name,
			@RequestParam(value="recommend",required=false)Integer recommend,
			@RequestParam(value="unitPrice",required=false)Double unitPrice,
			@RequestParam(value="saleNumber",required=false)Integer saleNumber,
			@RequestParam(value="merScore",required=false)Double merScore,
			@RequestParam(value="merCateId",required=false)Integer merCateId,
			@RequestParam(value="sellerAccountId",required=false)Integer sellerAccountId,
			@RequestParam(value="createDate",required=false)Date createDate,
			@RequestParam(value="updateDate",required=false)Date updateDate,
			@RequestParam(value="status",required=false)Integer status,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="update_date") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
		List<Mer> list = new ArrayList<Mer>();
			list= merService.browsePagingMer(
					region,type,name,recommend,unitPrice,saleNumber,merScore,merCateId,sellerAccountId,createDate,updateDate,status,pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotAnymoreException();//没有更多
			}
	}
	/**
	 * 商品修改
	 * @return
	 */
	@ApiOperation(value = "商品修改", notes = "商品修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateMer(@ModelAttribute Mer mer,HttpSession session)  {
		boolean um = merService.updateMer(mer);
		return ResultUtil.getSR(um);
	}
	/**
	 * 商品增加
	 * @return 
	 */
	@ApiOperation(value = "商品增加", notes = "商品增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addMer(@ModelAttribute Mer mer, HttpSession session) {
		boolean am = merService.addMer(mer);
		return ResultUtil.getSR(am);
	}
	/**
	 * 商户商品增加提交审核
	 * @return 
	 */
	@ApiOperation(value = "商户商品增加提交审核", notes = "商户商品增加提交审核")
	@ApiImplicitParams({
			@ApiImplicitParam(name="accountId",value="商户账户id",dataType="int", paramType = "query",required=true),
		    @ApiImplicitParam(name="merId",value="官方自营商品id",dataType="int", paramType = "query",required=true),
		    @ApiImplicitParam(name="unitPrice",value="单价",dataType="double", paramType = "query",required=true),
		    @ApiImplicitParam(name="discount",value="折扣",dataType="double", paramType = "query",required=true),
		    @ApiImplicitParam(name="merCardCipherType",value="卡密类型，1上传文件的字符串文字，2字符串文字，3字符串图片",dataType="int", paramType = "query",required=true),
		    @ApiImplicitParam(name="merCardCiphers",value="商品卡密集合，如：2323dfsf,sdf23",dataType="string", paramType = "query",required=true),
		  })
	@RequestMapping(value = "/addSellerMer", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Mer>> addSellerMer(
			@RequestParam(value="accountId")Integer sellerAccountId,
			@RequestParam(value="merId")Integer merId,
			@RequestParam(value="unitPrice")Double unitPrice,
			@RequestParam(value="discount")Double discount,
			@RequestParam(value="merCardCipherType")Integer merCardCipherType,
			@RequestParam(value="merCardCiphers")String merCardCiphers,
			HttpSession session) {
		Mer mer = merService.addSellerMer(sellerAccountId, merId, unitPrice, discount,merCardCipherType, merCardCiphers);
		if(mer!=null){
			List<Mer> list=new ArrayList<>();
			list.add(mer);
			return ResultUtil.getSlefSRSuccessList(list);
		}
		return ResultUtil.getSlefSRFailList(null);
	}
	/**
	 * 商品删除
	 * @return
	 */
	@ApiOperation(value = "商品删除", notes = "商品删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="merId",value="商品ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delMer(@RequestParam("merId") Integer merId,HttpSession session)  {
		boolean dm = merService.delMer(merId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * 商品浏览数量
	 * @return
	 */
	@ApiOperation(value = "商品数量", notes = "商品数量查询")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="region",value="范围，1官网自营，2商户非自营，3商户自营",dataType="int", paramType = "query"),
		  @ApiImplicitParam(name="type",value="类型，1普通商品，2降价商品，3预购商品",dataType="int", paramType = "query"),
		  @ApiImplicitParam(name="name",value="名称,模糊查询",dataType="string", paramType = "query"),
		  @ApiImplicitParam(name="recommend",value="推荐，默认0不推，1封推，2推荐",dataType="int", paramType = "query"),
		  @ApiImplicitParam(name="unitPrice",value="单价",dataType="double", paramType = "query"),
		  @ApiImplicitParam(name="saleNumber",value="销量",dataType="int", paramType = "query"),
		  @ApiImplicitParam(name="merScore",value="商品评分",dataType="double", paramType = "query"),
		  @ApiImplicitParam(name="merCateId",value="商品类型id",dataType="int", paramType = "query"),
		  @ApiImplicitParam(name="sellerAccountId",value="商户账户id",dataType="int", paramType = "query"),
		  @ApiImplicitParam(name="createDate",value="创建时间",dataType="date-time", paramType = "query"),
		  @ApiImplicitParam(name="updateDate",value="更新时间",dataType="date-time", paramType = "query"),
		  @ApiImplicitParam(name="status",value="状态0下架,默认1上架",dataType="int", paramType = "query")
		  })
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(
			@RequestParam(value="region",required=false)Integer region,
			@RequestParam(value="type",required=false)Integer type,
			@RequestParam(value="name",required=false)String name,
			@RequestParam(value="recommend",required=false)Integer recommend,
			@RequestParam(value="unitPrice",required=false)Double unitPrice,
			@RequestParam(value="saleNumber",required=false)Integer saleNumber,
			@RequestParam(value="merScore",required=false)Double merScore,
			@RequestParam(value="merCateId",required=false)Integer merCateId,
			@RequestParam(value="sellerAccountId",required=false)Integer sellerAccountId,
			@RequestParam(value="createDate",required=false)Date createDate,
			@RequestParam(value="updateDate",required=false)Date updateDate,
			@RequestParam(value="status",required=false)Integer status,
			HttpSession session)  {
		int count = merService.countAll(region,type,name,recommend,unitPrice,saleNumber,merScore,merCateId,sellerAccountId,createDate,updateDate,status);
		return count;
	}
	/**
	 * 商品单个加载
	 * @return
	 */
	@ApiOperation(value = "商品单个加载", notes = "商品单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="merId",value="商品ID",dataType="int", paramType = "path",required=true)
		  })
	@RequestMapping(value = "/load", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList<List<Mer>> loadMer(@RequestParam("merId") Integer merId,HttpSession session)  {
		Mer mer = merService.loadMer(merId);
			if(mer!=null &&!mer.equals("")){
				List<Mer> list = new ArrayList<Mer>();
				list.add(mer);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotIsNotExistException("商品");//不存在
			}
	}
	
}
