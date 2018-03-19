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

import com.nieyue.bean.MerImg;
import com.nieyue.exception.NotAnymoreException;
import com.nieyue.exception.NotIsNotExistException;
import com.nieyue.service.MerImgService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * 商品图片控制类
 * @author yy
 *
 */
@Api(tags={"merImg"},value="商品图片",description="商品图片管理")
@RestController
@RequestMapping("/merImg")
public class MerImgController {
	@Resource
	private MerImgService merImgService;
	
	/**
	 * 商品图片分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "商品图片列表", notes = "商品图片分页浏览")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="merId",value="商品id",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="update_date"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<MerImg>> browsePagingMerImg(
			@RequestParam(value="merId",required=false)Integer merId,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="update_date") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<MerImg> list = new ArrayList<MerImg>();
			list= merImgService.browsePagingMerImg(merId,pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotAnymoreException();//没有更多
			}
	}
	/**
	 * 商品图片修改
	 * @return
	 */
	@ApiOperation(value = "商品图片修改", notes = "商品图片修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateMerImg(@ModelAttribute MerImg merImg,HttpSession session)  {
		boolean um = merImgService.updateMerImg(merImg);
		return ResultUtil.getSR(um);
	}
	/**
	 * 商品图片增加
	 * @return 
	 */
	@ApiOperation(value = "商品图片增加", notes = "商品图片增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addMerImg(@ModelAttribute MerImg merImg, HttpSession session) {
		boolean am = merImgService.addMerImg(merImg);
		return ResultUtil.getSR(am);
	}
	/**
	 * 商品图片删除
	 * @return
	 */
	@ApiOperation(value = "商品图片删除", notes = "商品图片删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="MerImgId",value="商品图片ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delMerImg(@RequestParam("merImgId") Integer merImgId,HttpSession session)  {
		boolean dm = merImgService.delMerImg(merImgId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * 商品图片浏览数量
	 * @return
	 */
	@ApiOperation(value = "商品图片数量", notes = "商品图片数量查询")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="merId",value="商品id",dataType="int", paramType = "query")
		  })
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(
			@RequestParam(value="merId",required=false)Integer merId,
			HttpSession session)  {
		int count = merImgService.countAll(merId);
		return count;
	}
	/**
	 * 商品图片单个加载
	 * @return
	 */
	@ApiOperation(value = "商品图片单个加载", notes = "商品图片单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="merImgId",value="商品图片ID",dataType="int", paramType = "path",required=true)
		  })
	@RequestMapping(value = "/load", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList<List<MerImg>> loadMerImg(@RequestParam("merImgId") Integer merImgId,HttpSession session)  {
		List<MerImg> list = new ArrayList<MerImg>();
		MerImg merImg = merImgService.loadMerImg(merImgId);
			if(merImg!=null &&!merImg.equals("")){
				list.add(merImg);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotIsNotExistException("商品图片");//不存在
			}
	}
	
}
