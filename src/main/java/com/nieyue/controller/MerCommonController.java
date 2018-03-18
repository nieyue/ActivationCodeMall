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

import com.nieyue.bean.MerCommon;
import com.nieyue.exception.NotAnymoreException;
import com.nieyue.exception.NotIsNotExistException;
import com.nieyue.service.MerCommonService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * 商品公用控制类
 * @author yy
 *
 */
@Api(tags={"merCommon"},value="商品公用",description="商品公用管理")
@RestController
@RequestMapping("/merCommon")
public class MerCommonController {
	@Resource
	private MerCommonService merCommonService;
	
	/**
	 * 商品公用分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "商品公用列表", notes = "商品公用分页浏览")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="update_date"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<MerCommon>> browsePagingMerCommon(
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="update_date") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<MerCommon> list = new ArrayList<MerCommon>();
			list= merCommonService.browsePagingMerCommon(pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotAnymoreException();//没有更多
			}
	}
	/**
	 * 商品公用修改
	 * @return
	 */
	@ApiOperation(value = "商品公用修改", notes = "商品公用修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateMerCommon(@ModelAttribute MerCommon merCommon,HttpSession session)  {
		boolean um = merCommonService.updateMerCommon(merCommon);
		return ResultUtil.getSR(um);
	}
	/**
	 * 商品公用增加
	 * @return 
	 */
	@ApiOperation(value = "商品公用增加", notes = "商品公用增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addMerCommon(@ModelAttribute MerCommon merCommon, HttpSession session) {
		boolean am = merCommonService.addMerCommon(merCommon);
		return ResultUtil.getSR(am);
	}
	/**
	 * 商品公用删除
	 * @return
	 */
	@ApiOperation(value = "商品公用删除", notes = "商品公用删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="merCommonId",value="商品公用ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delMerCommon(@RequestParam("merCommonId") Integer merCommonId,HttpSession session)  {
		boolean dm = merCommonService.delMerCommon(merCommonId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * 商品公用浏览数量
	 * @return
	 */
	@ApiOperation(value = "商品公用数量", notes = "商品公用数量查询")
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(HttpSession session)  {
		int count = merCommonService.countAll();
		return count;
	}
	/**
	 * 商品公用单个加载
	 * @return
	 */
	@ApiOperation(value = "商品公用单个加载", notes = "商品公用单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="merCommonId",value="商品公用ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/load", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList<List<MerCommon>> loadMerCommon(@RequestParam("merCommonId") Integer merCommonId,HttpSession session)  {
		List<MerCommon> list = new ArrayList<MerCommon>();
		MerCommon merCommon = merCommonService.loadMerCommon(merCommonId);
			if(merCommon!=null &&!merCommon.equals("")){
				list.add(merCommon);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotIsNotExistException("商品公用");//不存在
			}
	}
	
}
