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

import com.nieyue.bean.VipNumber;
import com.nieyue.service.VipNumberService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * vip购买次数控制类
 * @author yy
 *
 */
@Api(tags={"vipNumber"},value="vip购买次数",description="vip购买次数管理")
@RestController
@RequestMapping("/vipNumber")
public class VipNumberController {
	@Resource
	private VipNumberService vipNumberService;
	
	/**
	 * vip购买次数分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "vip购买次数列表", notes = "vip购买次数分页浏览")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="number",value="次数",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="accountId",value="购买人id",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="realMasterId",value="真实上级id",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="createDate",value="创建时间",dataType="date-time", paramType = "query"),
	  @ApiImplicitParam(name="updateDate",value="更新时间",dataType="date-time", paramType = "query"),
	  @ApiImplicitParam(name="status",value="状态，1待处理，2已处理，3已超次",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="update_date"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList browsePagingVipNumber(
			@RequestParam(value="number",required=false)Integer number,
			@RequestParam(value="accountId",required=false)Integer accountId,
			@RequestParam(value="realMasterId",required=false)Integer realMasterId,
			@RequestParam(value="createDate",required=false)Date createDate,
			@RequestParam(value="updateDate",required=false)Date updateDate,
			@RequestParam(value="status",required=false)Integer status,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="update_date") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<VipNumber> list = new ArrayList<VipNumber>();
			list= vipNumberService.browsePagingVipNumber(number,accountId,realMasterId,createDate,updateDate,status,pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				return ResultUtil.getSlefSRFailList(list);
			}
	}
	/**
	 * vip购买次数修改
	 * @return
	 */
	@ApiOperation(value = "vip购买次数修改", notes = "vip购买次数修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateVipNumber(@ModelAttribute VipNumber vipNumber,HttpSession session)  {
		boolean um = vipNumberService.updateVipNumber(vipNumber);
		return ResultUtil.getSR(um);
	}
	/**
	 * vip购买次数增加
	 * @return 
	 */
	@ApiOperation(value = "vip购买次数增加", notes = "vip购买次数增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addVipNumber(@ModelAttribute VipNumber vipNumber, HttpSession session) {
		boolean am = vipNumberService.addVipNumber(vipNumber);
		return ResultUtil.getSR(am);
	}
	/**
	 * vip购买次数删除
	 * @return
	 */
	@ApiOperation(value = "vip购买次数删除", notes = "vip购买次数删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="vipNumberId",value="vip购买次数ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delVipNumber(@RequestParam("vipNumberId") Integer vipNumberId,HttpSession session)  {
		boolean dm = vipNumberService.delVipNumber(vipNumberId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * vip购买次数浏览数量
	 * @return
	 */
	@ApiOperation(value = "vip购买次数数量", notes = "vip购买次数数量查询")
	@ApiImplicitParams({
		@ApiImplicitParam(name="number",value="次数",dataType="int", paramType = "query"),
		  @ApiImplicitParam(name="accountId",value="购买人id",dataType="int", paramType = "query"),
		  @ApiImplicitParam(name="realMasterId",value="真实上级id",dataType="int", paramType = "query"),
		  @ApiImplicitParam(name="createDate",value="创建时间",dataType="date-time", paramType = "query"),
		  @ApiImplicitParam(name="updateDate",value="更新时间",dataType="date-time", paramType = "query"),
		  @ApiImplicitParam(name="status",value="状态，1待处理，2已处理，3已超次",dataType="int", paramType = "query")
		  })
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(
			@RequestParam(value="number",required=false)Integer number,
			@RequestParam(value="accountId",required=false)Integer accountId,
			@RequestParam(value="realMasterId",required=false)Integer realMasterId,
			@RequestParam(value="createDate",required=false)Date createDate,
			@RequestParam(value="updateDate",required=false)Date updateDate,
			@RequestParam(value="status",required=false)Integer status,
			HttpSession session)  {
		int count = vipNumberService.countAll(number,accountId,realMasterId,createDate,updateDate,status);
		return count;
	}
	/**
	 * vip购买次数单个加载
	 * @return
	 */
	@ApiOperation(value = "vip购买次数单个加载", notes = "vip购买次数单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="vipNumberId",value="vip购买次数ID",dataType="int", paramType = "path",required=true)
		  })
	@RequestMapping(value = "/{vipNumberId}", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList loadVipNumber(@PathVariable("vipNumberId") Integer vipNumberId,HttpSession session)  {
		List<VipNumber> list = new ArrayList<VipNumber>();
		VipNumber vipNumber = vipNumberService.loadVipNumber(vipNumberId);
			if(vipNumber!=null &&!vipNumber.equals("")){
				list.add(vipNumber);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				return ResultUtil.getSlefSRFailList(list);
			}
	}
	
}
