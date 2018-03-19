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

import com.nieyue.bean.MerCardCipher;
import com.nieyue.exception.NotAnymoreException;
import com.nieyue.exception.NotIsNotExistException;
import com.nieyue.service.MerCardCipherService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * 商品卡密控制类
 * @author yy
 *
 */
@Api(tags={"merCardCipher"},value="商品卡密",description="商品卡密管理")
@RestController
@RequestMapping("/merCardCipher")
public class MerCardCipherController {
	@Resource
	private MerCardCipherService merCardCipherService;
	
	/**
	 * 商品卡密分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "商品卡密列表", notes = "商品卡密分页浏览")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="merId",value="商品id",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="create_date"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<MerCardCipher>> browsePagingMerCardCipher(
			@RequestParam(value="merId",required=false)Integer merId,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="create_date") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<MerCardCipher> list = new ArrayList<MerCardCipher>();
			list= merCardCipherService.browsePagingMerCardCipher(merId,pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotAnymoreException();//没有更多
			}
	}
	/**
	 * 商品卡密修改
	 * @return
	 */
	@ApiOperation(value = "商品卡密修改", notes = "商品卡密修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateMerCardCipher(@ModelAttribute MerCardCipher merCardCipher,HttpSession session)  {
		boolean um = merCardCipherService.updateMerCardCipher(merCardCipher);
		return ResultUtil.getSR(um);
	}
	/**
	 * 商品卡密增加
	 * @return 
	 */
	@ApiOperation(value = "商品卡密增加", notes = "商品卡密增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addMerCardCipher(@ModelAttribute MerCardCipher merCardCipher, HttpSession session) {
		boolean am = merCardCipherService.addMerCardCipher(merCardCipher);
		return ResultUtil.getSR(am);
	}
	/**
	 * 商品卡密删除
	 * @return
	 */
	@ApiOperation(value = "商品卡密删除", notes = "商品卡密删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="merCardCipherId",value="商品卡密ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delMerCardCipher(@RequestParam("merCardCipherId") Integer merCardCipherId,HttpSession session)  {
		boolean dm = merCardCipherService.delMerCardCipher(merCardCipherId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * 商品卡密浏览数量
	 * @return
	 */
	@ApiOperation(value = "商品卡密数量", notes = "商品卡密数量查询")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="merId",value="商品id",dataType="int", paramType = "query")
		  })
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(
			@RequestParam(value="merId",required=false)Integer merId,
			HttpSession session)  {
		int count = merCardCipherService.countAll(merId);
		return count;
	}
	/**
	 * 商品卡密单个加载
	 * @return
	 */
	@ApiOperation(value = "商品卡密单个加载", notes = "商品卡密单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="merCardCipherId",value="商品卡密ID",dataType="int", paramType = "path",required=true)
		  })
	@RequestMapping(value = "/load", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList<List<MerCardCipher>> loadMerCardCipher(@RequestParam("merCardCipherId") Integer merCardCipherId,HttpSession session)  {
		List<MerCardCipher> list = new ArrayList<MerCardCipher>();
		MerCardCipher merCardCipher = merCardCipherService.loadMerCardCipher(merCardCipherId);
			if(merCardCipher!=null &&!merCardCipher.equals("")){
				list.add(merCardCipher);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotIsNotExistException("商品卡密");//不存在
			}
	}
	
}
