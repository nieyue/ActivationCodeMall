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

import com.nieyue.bean.MerNotice;
import com.nieyue.exception.NotAnymoreException;
import com.nieyue.exception.NotIsNotExistException;
import com.nieyue.service.MerNoticeService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * 商品公告控制类
 * @author yy
 *
 */
@Api(tags={"merNotice"},value="商品公告",description="商品公告管理")
@RestController
@RequestMapping("/merNotice")
public class MerNoticeController {
	@Resource
	private MerNoticeService merNoticeService;
	
	/**
	 * 商品公告分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "商品公告列表", notes = "商品公告分页浏览")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="merId",value="商品id",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="create_date"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<MerNotice>> browsePagingMerNotice(
			@RequestParam(value="merId",required=false)Integer merId,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="create_date") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<MerNotice> list = new ArrayList<MerNotice>();
			list= merNoticeService.browsePagingMerNotice(merId,pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotAnymoreException();//没有更多
			}
	}
	/**
	 * 商品公告修改
	 * @return
	 */
	@ApiOperation(value = "商品公告修改", notes = "商品公告修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateMerNotice(@ModelAttribute MerNotice merNotice,HttpSession session)  {
		boolean um = merNoticeService.updateMerNotice(merNotice);
		return ResultUtil.getSR(um);
	}
	/**
	 * 商品公告增加
	 * @return 
	 */
	@ApiOperation(value = "商品公告增加", notes = "商品公告增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addMerNotice(@ModelAttribute MerNotice merNotice, HttpSession session) {
		boolean am = merNoticeService.addMerNotice(merNotice);
		return ResultUtil.getSR(am);
	}
	/**
	 * 商品公告删除
	 * @return
	 */
	@ApiOperation(value = "商品公告删除", notes = "商品公告删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="merNoticeId",value="商品公告ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delMerNotice(@RequestParam("merNoticeId") Integer merNoticeId,HttpSession session)  {
		boolean dm = merNoticeService.delMerNotice(merNoticeId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * 商品公告浏览数量
	 * @return
	 */
	@ApiOperation(value = "商品公告数量", notes = "商品公告数量查询")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="merId",value="商品id",dataType="int", paramType = "query")
		  })
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(
			@RequestParam(value="merId",required=false)Integer merId,
			HttpSession session)  {
		int count = merNoticeService.countAll(merId);
		return count;
	}
	/**
	 * 商品公告单个加载
	 * @return
	 */
	@ApiOperation(value = "商品公告单个加载", notes = "商品公告单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="merNoticeId",value="商品公告ID",dataType="int", paramType = "path",required=true)
		  })
	@RequestMapping(value = "/load", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList<List<MerNotice>> loadMerNotice(@RequestParam("merNoticeId") Integer merNoticeId,HttpSession session)  {
		List<MerNotice> list = new ArrayList<MerNotice>();
		MerNotice merNotice = merNoticeService.loadMerNotice(merNoticeId);
			if(merNotice!=null &&!merNotice.equals("")){
				list.add(merNotice);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotIsNotExistException("商品公告");//不存在
			}
	}
	
}
