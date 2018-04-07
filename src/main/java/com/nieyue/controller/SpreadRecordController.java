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

import com.nieyue.bean.SpreadRecord;
import com.nieyue.exception.NotAnymoreException;
import com.nieyue.exception.NotIsNotExistException;
import com.nieyue.service.SpreadRecordService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * 推广记录控制类
 * @author yy
 *
 */
@Api(tags={"spreadRecord"},value="推广记录",description="推广记录管理")
@RestController
@RequestMapping("/spreadRecord")
public class SpreadRecordController {
	@Resource
	private SpreadRecordService spreadRecordService;
	
	/**
	 * 推广记录分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "推广记录列表", notes = "推广记录分页浏览")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="accountId",value="推广账户id",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="create_date"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<SpreadRecord>> browsePagingSpreadRecord(
			@RequestParam(value="accountId",required=false)Integer accountId,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="create_date") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<SpreadRecord> list = new ArrayList<SpreadRecord>();
			list= spreadRecordService.browsePagingSpreadRecord(accountId,pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotAnymoreException();//没有更多
			}
	}
	/**
	 * 推广记录修改
	 * @return
	 */
	@ApiOperation(value = "推广记录修改", notes = "推广记录修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateSpreadRecord(@ModelAttribute SpreadRecord spreadRecord,HttpSession session)  {
		boolean um = spreadRecordService.updateSpreadRecord(spreadRecord);
		return ResultUtil.getSR(um);
	}
	/**
	 * 推广记录增加
	 * @return 
	 */
	@ApiOperation(value = "推广记录增加", notes = "推广记录增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addSpreadRecord(@ModelAttribute SpreadRecord spreadRecord, HttpSession session) {
		boolean am = spreadRecordService.addSpreadRecord(spreadRecord);
		return ResultUtil.getSR(am);
	}
	/**
	 * 推广记录删除
	 * @return
	 */
	@ApiOperation(value = "推广记录删除", notes = "推广记录删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="spreadRecordId",value="推广记录ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delSpreadRecord(@RequestParam("spreadRecordId") Integer spreadRecordId,HttpSession session)  {
		boolean dm = spreadRecordService.delSpreadRecord(spreadRecordId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * 推广记录浏览数量
	 * @return
	 */
	@ApiOperation(value = "推广记录数量", notes = "推广记录数量查询")
	@ApiImplicitParams({
		@ApiImplicitParam(name="accountId",value="推广账户id",dataType="int", paramType = "query")
	  })
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(
			@RequestParam(value="accountId",required=false)Integer accountId,
			HttpSession session)  {
		int count = spreadRecordService.countAll(accountId);
		return count;
	}
	/**
	 * 推广记录单个加载
	 * @return
	 */
	@ApiOperation(value = "推广记录单个加载", notes = "推广记录单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="spreadRecordId",value="推广记录ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/load", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList<List<SpreadRecord>> loadSpreadRecord(@RequestParam("spreadRecordId") Integer spreadRecordId,HttpSession session)  {
		List<SpreadRecord> list = new ArrayList<SpreadRecord>();
		SpreadRecord spreadRecord = spreadRecordService.loadSpreadRecord(spreadRecordId);
			if(spreadRecord!=null &&!spreadRecord.equals("")){
				list.add(spreadRecord);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotIsNotExistException("推广记录");//不存在
			}
	}
	
}
