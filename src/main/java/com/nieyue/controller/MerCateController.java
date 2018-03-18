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

import com.nieyue.bean.MerCate;
import com.nieyue.exception.NotAnymoreException;
import com.nieyue.exception.NotIsNotExistException;
import com.nieyue.service.MerCateService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * 商品类型控制类
 * @author yy
 *
 */
@Api(tags={"merCate"},value="商品类型",description="商品类型管理")
@RestController
@RequestMapping("/merCate")
public class MerCateController {
	@Resource
	private MerCateService merCateService;
	
	/**
	 * 商品类型分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "商品类型列表", notes = "商品类型分页浏览")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="update_date"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<MerCate>> browsePagingMerCate(
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="update_date") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<MerCate> list = new ArrayList<MerCate>();
			list= merCateService.browsePagingMerCate(pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotAnymoreException();//没有更多
			}
	}
	/**
	 * 商品类型修改
	 * @return
	 */
	@ApiOperation(value = "商品类型修改", notes = "商品类型修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateMerCate(@ModelAttribute MerCate merCate,HttpSession session)  {
		boolean um = merCateService.updateMerCate(merCate);
		return ResultUtil.getSR(um);
	}
	/**
	 * 商品类型增加
	 * @return 
	 */
	@ApiOperation(value = "商品类型增加", notes = "商品类型增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addMerCate(@ModelAttribute MerCate merCate, HttpSession session) {
		boolean am = merCateService.addMerCate(merCate);
		return ResultUtil.getSR(am);
	}
	/**
	 * 商品类型删除
	 * @return
	 */
	@ApiOperation(value = "商品类型删除", notes = "商品类型删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="merCateId",value="商品类型ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delMerCate(@RequestParam("merCateId") Integer merCateId,HttpSession session)  {
		boolean dm = merCateService.delMerCate(merCateId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * 商品类型浏览数量
	 * @return
	 */
	@ApiOperation(value = "商品类型数量", notes = "商品类型数量查询")
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(HttpSession session)  {
		int count = merCateService.countAll();
		return count;
	}
	/**
	 * 商品类型单个加载
	 * @return
	 */
	@ApiOperation(value = "商品类型单个加载", notes = "商品类型单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="merCateId",value="商品类型ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/load", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList<List<MerCate>> loadMerCate(@RequestParam("merCateId") Integer merCateId,HttpSession session)  {
		List<MerCate> list = new ArrayList<MerCate>();
		MerCate merCate = merCateService.loadMerCate(merCateId);
			if(merCate!=null &&!merCate.equals("")){
				list.add(merCate);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotIsNotExistException("商品类型");//不存在
			}
	}
	
}
