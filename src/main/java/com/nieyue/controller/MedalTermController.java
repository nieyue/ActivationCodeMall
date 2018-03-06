package com.nieyue.controller;

import java.util.ArrayList;
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

import com.nieyue.bean.MedalTerm;
import com.nieyue.service.MedalTermService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * 勋章项控制类
 * @author yy
 *
 */
@Api(tags={"medalTerm"},value="勋章项",description="勋章项管理")
@RestController
@RequestMapping("/medalTerm")
public class MedalTermController {
	@Resource
	private MedalTermService medalTermService;
	
	/**
	 * 勋章项分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "勋章项列表", notes = "勋章项分页浏览")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="update_date"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList browsePagingMedalTerm(
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="update_date") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<MedalTerm> list = new ArrayList<MedalTerm>();
			list= medalTermService.browsePagingMedalTerm(pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				return ResultUtil.getSlefSRFailList(list);
			}
	}
	/**
	 * 勋章项修改
	 * @return
	 */
	@ApiOperation(value = "勋章项修改", notes = "勋章项修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateMedalTerm(@ModelAttribute MedalTerm medalTerm,HttpSession session)  {
		boolean um = medalTermService.updateMedalTerm(medalTerm);
		return ResultUtil.getSR(um);
	}
	/**
	 * 勋章项增加
	 * @return 
	 */
	@ApiOperation(value = "勋章项增加", notes = "勋章项增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addMedalTerm(@ModelAttribute MedalTerm medalTerm, HttpSession session) {
		boolean am = medalTermService.addMedalTerm(medalTerm);
		return ResultUtil.getSR(am);
	}
	/**
	 * 勋章项删除
	 * @return
	 */
	@ApiOperation(value = "勋章项删除", notes = "勋章项删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="medalTermId",value="勋章项ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delMedalTerm(@RequestParam("medalTermId") Integer medalTermId,HttpSession session)  {
		boolean dm = medalTermService.delMedalTerm(medalTermId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * 勋章项浏览数量
	 * @return
	 */
	@ApiOperation(value = "勋章项数量", notes = "勋章项数量查询")
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(HttpSession session)  {
		int count = medalTermService.countAll();
		return count;
	}
	/**
	 * 勋章项单个加载
	 * @return
	 */
	@ApiOperation(value = "勋章项单个加载", notes = "勋章项单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="medalTermId",value="勋章项ID",dataType="int", paramType = "path",required=true)
		  })
	@RequestMapping(value = "/{medalTermId}", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList loadMedalTerm(@PathVariable("medalTermId") Integer medalTermId,HttpSession session)  {
		List<MedalTerm> list = new ArrayList<MedalTerm>();
		MedalTerm medalTerm = medalTermService.loadMedalTerm(medalTermId);
			if(medalTerm!=null &&!medalTerm.equals("")){
				list.add(medalTerm);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				return ResultUtil.getSlefSRFailList(list);
			}
	}
	
}
