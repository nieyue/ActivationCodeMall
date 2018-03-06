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

import com.nieyue.bean.VipGrowthRecord;
import com.nieyue.service.VipGrowthRecordService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * Vip成长记录控制类
 * @author yy
 *
 */
@Api(tags={"vipGrowthRecord"},value="Vip成长记录",description="Vip成长记录管理")
@RestController
@RequestMapping("/vipGrowthRecord")
public class VipGrowthRecordController {
	@Resource
	private VipGrowthRecordService vipGrowthRecordService;
	
	/**
	 * Vip成长记录分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "Vip成长记录列表", notes = "Vip成长记录分页浏览")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="accountId",value="账户Id",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="level",value="等级名",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="createDate",value="创建时间",dataType="date-time", paramType = "query"),
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="create_date"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList browsePagingVipGrowthRecord(
			@RequestParam(value="accountId",required=false)Integer accountId,
			@RequestParam(value="level",required=false)Integer level,
			@RequestParam(value="createDate",required=false)Date createDate,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="create_date") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<VipGrowthRecord> list = new ArrayList<VipGrowthRecord>();
			list= vipGrowthRecordService.browsePagingVipGrowthRecord(accountId,level,createDate,pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				return ResultUtil.getSlefSRFailList(list);
			}
	}
	/**
	 * Vip成长记录修改
	 * @return
	 */
	@ApiOperation(value = "Vip成长记录修改", notes = "Vip成长记录修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateVipGrowthRecord(@ModelAttribute VipGrowthRecord vipGrowthRecord,HttpSession session)  {
		boolean um = vipGrowthRecordService.updateVipGrowthRecord(vipGrowthRecord);
		return ResultUtil.getSR(um);
	}
	/**
	 * Vip成长记录增加
	 * @return 
	 */
	@ApiOperation(value = "Vip成长记录增加", notes = "Vip成长记录增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addVipGrowthRecord(@ModelAttribute VipGrowthRecord vipGrowthRecord, HttpSession session) {
		boolean am = vipGrowthRecordService.addVipGrowthRecord(vipGrowthRecord);
		return ResultUtil.getSR(am);
	}
	/**
	 * Vip成长记录删除
	 * @return
	 */
	@ApiOperation(value = "Vip成长记录删除", notes = "Vip成长记录删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="vipGrowthRecordId",value="Vip成长记录Id",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delVipGrowthRecord(@RequestParam("vipGrowthRecordId") Integer vipGrowthRecordId,HttpSession session)  {
		boolean dm = vipGrowthRecordService.delVipGrowthRecord(vipGrowthRecordId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * Vip成长记录浏览数量
	 * @return
	 */
	@ApiOperation(value = "Vip成长记录数量", notes = "Vip成长记录数量查询")
	@ApiImplicitParams({
		@ApiImplicitParam(name="accountId",value="账户Id",dataType="int", paramType = "query"),
		  @ApiImplicitParam(name="level",value="等级名",dataType="int", paramType = "query"),
		  @ApiImplicitParam(name="createDate",value="创建时间",dataType="date-time", paramType = "query")
		  })
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(
			@RequestParam(value="accountId",required=false)Integer accountId,
			@RequestParam(value="level",required=false)Integer level,
			@RequestParam(value="createDate",required=false)Date createDate,
			HttpSession session)  {
		int count = vipGrowthRecordService.countAll(accountId,level,createDate);
		return count;
	}
	/**
	 * Vip成长记录单个加载
	 * @return
	 */
	@ApiOperation(value = "Vip成长记录单个加载", notes = "Vip成长记录单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="vipGrowthRecordId",value="Vip成长记录ID",dataType="int", paramType = "path",required=true)
		  })
	@RequestMapping(value = "/{vipGrowthRecordId}", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList loadVipGrowthRecord(@PathVariable("vipGrowthRecordId") Integer vipGrowthRecordId,HttpSession session)  {
		List<VipGrowthRecord> list = new ArrayList<VipGrowthRecord>();
		VipGrowthRecord vipGrowthRecord = vipGrowthRecordService.loadVipGrowthRecord(vipGrowthRecordId);
			if(vipGrowthRecord!=null &&!vipGrowthRecord.equals("")){
				list.add(vipGrowthRecord);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				return ResultUtil.getSlefSRFailList(list);
			}
	}
	
}
