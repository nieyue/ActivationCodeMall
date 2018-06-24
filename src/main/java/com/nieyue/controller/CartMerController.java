package com.nieyue.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nieyue.bean.CartMer;
import com.nieyue.exception.CommonRollbackException;
import com.nieyue.exception.NotAnymoreException;
import com.nieyue.exception.NotIsNotExistException;
import com.nieyue.service.CartMerService;
import com.nieyue.util.NumberUtil;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * 购物车商品控制类
 * @author yy
 *
 */
@Api(tags={"cartMer"},value="购物车商品",description="购物车商品管理")
@RestController
@RequestMapping("/cartMer")
public class CartMerController {
	@Resource
	private CartMerService cartMerService;
	
	/**
	 * 购物车商品分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "购物车商品列表", notes = "购物车商品分页浏览")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="number",value="数量",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="merId",value="商品id",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="accountId",value="账户id",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="update_date"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<CartMer>> browsePagingCartMer(
			@RequestParam(value="number",required=false)Integer number,
			@RequestParam(value="merId",required=false)Integer merId,
			@RequestParam(value="accountId",required=false)Integer accountId,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="update_date") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<CartMer> list = new ArrayList<CartMer>();
			list= cartMerService.browsePagingCartMer(number,merId,accountId,pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotAnymoreException();//没有更多
			}
	}
	/**
	 * 用户购物车信息
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "购物车商品列表", notes = "购物车商品分页浏览,"
			+ "cartMerCountAll总数,cartMerListAll总列表,"
			+ "cartMerCount2降价总数,cartMerList2降价列表,"
			+ "cartMerCount3预购总数,cartMerList3预购列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name="accountId",value="账户id",dataType="int", paramType = "query")
	})
	@RequestMapping(value = "/userlist", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Map<String,Object>>> browseUserCartMer(
			@RequestParam(value="accountId",required=false)Integer accountId
			)  {
		List<CartMer> cartMerListAll= cartMerService.browsePagingCartMer(null,null,accountId,1,Integer.MAX_VALUE, "cart_mer_id", "asc");
		if(cartMerListAll.size()>0){
			List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
			Map<String,Object> map=new HashMap<>();
			map.put("cartMerCountAll", cartMerListAll.size());//总数
			map.put("cartMerListAll", cartMerListAll);//总列表
			List<CartMer> cartMerList2 = new ArrayList<CartMer>();
			cartMerListAll.forEach((cm)->{
				if(cm.getMer().getType().equals(2)){
					cartMerList2.add(cm);
				}
			});
			map.put("cartMerCount2", cartMerList2.size());//降价总数
			map.put("cartMerList2", cartMerList2);//降价列表
			List<CartMer> cartMerList3 = new ArrayList<CartMer>();
			cartMerListAll.forEach((cm)->{
				if(cm.getMer().getType().equals(3)){
					cartMerList3.add(cm);
				}
			});
			map.put("cartMerCount3", cartMerList3.size());//预购总数
			map.put("cartMerList3", cartMerList3);//预购列表
			list.add(map);
			return ResultUtil.getSlefSRSuccessList(list);
		}else{
			throw new NotAnymoreException();//没有更多
		}
	}
	/**
	 * 购物车商品修改
	 * @return
	 */
	@ApiOperation(value = "购物车商品修改", notes = "购物车商品修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateCartMer(@ModelAttribute CartMer cartMer,HttpSession session)  {
		boolean um = cartMerService.updateCartMer(cartMer);
		return ResultUtil.getSR(um);
	}
	/**
	 * 购物车商品增加
	 * @return 
	 */
	@ApiOperation(value = "购物车商品增加", notes = "购物车商品增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addCartMer(@ModelAttribute CartMer cartMer, HttpSession session) {
		boolean am = cartMerService.addCartMer(cartMer);
		return ResultUtil.getSR(am);
	}
	/**
	 * 购物车商品删除
	 * @return
	 */
	@ApiOperation(value = "购物车商品删除", notes = "购物车商品删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="cartMerId",value="购物车商品ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delCartMer(@RequestParam("cartMerId") Integer cartMerId,HttpSession session)  {
		boolean dm = cartMerService.delCartMer(cartMerId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * 购物车商品批量删除
	 * @return
	 */
	@ApiOperation(value = "购物车商品批量删除", notes = "购物车商品批量删除")
	@ApiImplicitParams({
		@ApiImplicitParam(name="cartMerIds",value="购物车商品ID集合数组，\"22,33,44,53,3\"",dataType="string", paramType = "query",required=true)
	})
	@RequestMapping(value = "/deleteBatch", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delBatchCartMer(@RequestParam("cartMerIds") String cartMerIds,HttpSession session)  {
		String[] ids = cartMerIds.replace(" ","").split(",");
		boolean dm=false;
		for (int i = 0; i < ids.length; i++) {
			if(!NumberUtil.isNumeric(ids[i])){
				throw new CommonRollbackException("参数错误");
			}
		}
		for (int i = 0; i < ids.length; i++) {
			dm = cartMerService.delCartMer(new Integer(ids[i]));
		}
		return ResultUtil.getSR(dm);
	}
	/**
	 * 购物车商品批量转未支付订单商品
	 * @return
	 */
	@ApiOperation(value = "购物车商品批量转未支付订单商品", notes = "购物车商品批量转未支付订单商品")
	@ApiImplicitParams({
		@ApiImplicitParam(name="cartMerIds",value="购物车商品ID集合数组，\"22,33,44,53,3\"",dataType="string", paramType = "query",required=true)
	})
	@RequestMapping(value = "/turnOrderBatch", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult batchCartMerTurnOrder(@RequestParam("cartMerIds") String cartMerIds,HttpSession session)  {
		boolean dm=cartMerService.batchCartMerTurnOrder(cartMerIds);
		return ResultUtil.getSR(dm);
	}
	/**
	 * 购物车商品浏览数量
	 * @return
	 */
	@ApiOperation(value = "购物车商品数量", notes = "购物车商品数量查询")
	@ApiImplicitParams({
		@ApiImplicitParam(name="number",value="数量",dataType="int", paramType = "query"),
		  @ApiImplicitParam(name="merId",value="商品id",dataType="int", paramType = "query"),
		  @ApiImplicitParam(name="accountId",value="账户id",dataType="int", paramType = "query")
		  })
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(
			@RequestParam(value="number",required=false)Integer number,
			@RequestParam(value="merId",required=false)Integer merId,
			@RequestParam(value="accountId",required=false)Integer accountId,
			HttpSession session)  {
		int count = cartMerService.countAll(number,merId,accountId);
		return count;
	}
	/**
	 * 购物车商品单个加载
	 * @return
	 */
	@ApiOperation(value = "购物车商品单个加载", notes = "购物车商品单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="cartMerId",value="购物车商品ID",dataType="int", paramType = "path",required=true)
		  })
	@RequestMapping(value = "/load", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList<List<CartMer>> loadCartMer(@RequestParam("cartMerId") Integer cartMerId,HttpSession session)  {
		List<CartMer> list = new ArrayList<CartMer>();
		CartMer cartMer = cartMerService.loadCartMer(cartMerId);
			if(cartMer!=null &&!cartMer.equals("")){
				list.add(cartMer);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotIsNotExistException("购物车商品");//不存在
			}
	}
	
}
