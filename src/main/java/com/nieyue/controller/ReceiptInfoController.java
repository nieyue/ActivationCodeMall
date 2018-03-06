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

import com.nieyue.bean.ReceiptInfo;
import com.nieyue.service.ReceiptInfoService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;


/**
 * 收货信息控制类
 * @author yy
 *
 */
@Api(tags={"receiptInfo"},value="收货信息",description="收货信息管理")
@RestController
@RequestMapping("/receiptInfo")
public class ReceiptInfoController {
	@Resource
	private ReceiptInfoService receiptInfoService;
	
	/**
	 * 收货信息分页浏览
	 * @param orderName 收货信息排序数据库字段
	 * @param orderWay 收货信息排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "收货信息", notes = "收货信息分页浏览")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="accountId",value="账户ID",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="isDefault",value="默认为0不是，1是",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="createDate",value="创建时间",dataType="date-time", paramType = "query"),
	  @ApiImplicitParam(name="updateDate",value="更新时间",dataType="date-time", paramType = "query"),
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="receipt_info_id"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList browsePagingReceiptInfo(
			@RequestParam(value="accountId",required=false)Integer accountId,
			@RequestParam(value="isDefault",required=false)Integer isDefault,
			@RequestParam(value="createDate",required=false)Date createDate,
			@RequestParam(value="updateDate",required=false)Date updateDate,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="receipt_info_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<ReceiptInfo> list = new ArrayList<ReceiptInfo>();
			list= receiptInfoService.browsePagingReceiptInfo(accountId,isDefault,createDate,updateDate,pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				return ResultUtil.getSlefSRFailList(list);
			}
	}
	/**
	 * 收货信息修改
	 * @return
	 */
	@ApiOperation(value = "收货信息修改", notes = "收货信息修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateReceiptInfo(@ModelAttribute ReceiptInfo receiptInfo,HttpSession session)  {
		System.out.println(JSONObject.fromObject(receiptInfo).toString());
		boolean um =receiptInfoService.updateReceiptInfo(receiptInfo);
		return ResultUtil.getSR(um); 
	}
	/**
	 * 收货信息增加
	 * @return 
	 */
	@ApiOperation(value = "收货信息增加", notes = "收货信息增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addReceiptInfo(@ModelAttribute ReceiptInfo receiptInfo, HttpSession session) {
		boolean am = receiptInfoService.addReceiptInfo(receiptInfo);
		return ResultUtil.getSR(am);
	}
	/**
	 * 收货信息删除
	 * @return
	 */
	@ApiOperation(value = "收货信息删除", notes = "收货信息删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="receiptInfoId",value="收货信息ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delReceiptInfo(@RequestParam("receiptInfoId") Integer receiptInfoId,HttpSession session)  {
		boolean dm = receiptInfoService.delReceiptInfo(receiptInfoId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * 收货信息浏览数量
	 * @return
	 */
	@ApiOperation(value = "收货信息数量", notes = "收货信息数量查询")
	@ApiImplicitParams({
		@ApiImplicitParam(name="accountId",value="收货信息id",dataType="int", paramType = "query"),
		  @ApiImplicitParam(name="isDefault",value="'默认为0不是，1是",dataType="int", paramType = "query"),
		  @ApiImplicitParam(name="createDate",value="创建时间",dataType="date-time", paramType = "query"),
		  @ApiImplicitParam(name="updateDate",value="更新时间",dataType="date-time", paramType = "query")
		  })
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(
			@RequestParam(value="accountId",required=false)Integer accountId,
			@RequestParam(value="isDefault",required=false)Integer isDefault,
			@RequestParam(value="createDate",required=false)Date createDate,
			@RequestParam(value="updateDate",required=false)Date updateDate,
			HttpSession session)  {
		int count = receiptInfoService.countAll(accountId,isDefault,createDate,updateDate);
		return count;
	}
	/**
	 * 收货信息单个加载
	 * @return
	 */
	@ApiOperation(value = "收货信息单个加载", notes = "收货信息单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="receiptInfoId",value="收货信息ID",dataType="int", paramType = "path",required=true)
		  })
	@RequestMapping(value = "/{receiptInfoId}", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList loadReceiptInfo(@PathVariable("receiptInfoId") Integer receiptInfoId,HttpSession session)  {
		List<ReceiptInfo> list = new ArrayList<ReceiptInfo>();
		ReceiptInfo receiptInfo = receiptInfoService.loadReceiptInfo(receiptInfoId);
			if(receiptInfo!=null &&!receiptInfo.equals("")){
				list.add(receiptInfo);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				return ResultUtil.getSlefSRFailList(list);
			}
	}
	/**
	 * 设置默认收货信息
	 * @return
	 */
	@ApiOperation(value = "设置默认收货信息", notes = "设置默认收货信息")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="receiptInfoId",value="收货信息ID",dataType="int", paramType = "query",required=true),
		  @ApiImplicitParam(name="accountId",value="账户ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/setIsDefault", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult setReceiptInfoIsDefault(
			@RequestParam(value="receiptInfoId") Integer receiptInfoId,
			@RequestParam(value="accountId") Integer accountId,
			HttpSession session)  {
		boolean b=false;
			List<ReceiptInfo> l = receiptInfoService.browsePagingReceiptInfo(accountId, 1, null, null, 1, Integer.MAX_VALUE, "receipt_info_id", "asc");
			if(l.size()>0){
			for (int i = 0; i < l.size(); i++) {
				ReceiptInfo re = l.get(i);
				re.setIsDefault(0);
				b = receiptInfoService.updateReceiptInfo(re);
			}
			}
		ReceiptInfo newreceiptInfo = receiptInfoService.loadReceiptInfo(receiptInfoId);
		newreceiptInfo.setIsDefault(1);
		b=receiptInfoService.updateReceiptInfo(newreceiptInfo);
		return ResultUtil.getSR(b);   
	}
	
}
