package com.nieyue.controller;

import com.nieyue.bean.Permission;
import com.nieyue.exception.NotAnymoreException;
import com.nieyue.exception.NotIsNotExistException;
import com.nieyue.service.PermissionService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


/**
 * 权限控制类
 * @author yy
 *
 */
@Api(tags={"permission"},value="权限",description="权限管理")
@RestController
@RequestMapping("/permission")
public class PermissionController {
	@Resource
	private PermissionService permissionService;
	
	/**
	 * 权限分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "权限列表", notes = "权限分页浏览")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="type",value="权限类型，默认0开放，1鉴权",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="managerName",value="权限管理名,模糊查询",dataType="string", paramType = "query"),
	  @ApiImplicitParam(name="name",value="权限名,模糊查询",dataType="string", paramType = "query"),
	  @ApiImplicitParam(name="route",value="权限路由,模糊查询",dataType="string", paramType = "query"),
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="permission_id"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Permission>> browsePagingPermission(
			@RequestParam(value="type",required=false)Integer type,
			@RequestParam(value="managerName",required=false)String managerName,
			@RequestParam(value="name",required=false)String name,
			@RequestParam(value="route",required=false)String route,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="permission_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<Permission> list = new ArrayList<Permission>();
			list= permissionService.browsePagingPermission(
					type,managerName,name,route,
					pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotAnymoreException();//没有更多
			}
	}
	/**
	 * 初始化权限
	 * @return
	 */
	@ApiOperation(value = "初始化权限", notes = "初始化权限")
	@RequestMapping(value = "/init", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult initPermission()  {
		boolean um = permissionService.initPermission();
		return ResultUtil.getSR(um);
	}
	/**
	 * 权限修改
	 * @return
	 */
	@ApiOperation(value = "权限修改", notes = "权限修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updatePermission(@ModelAttribute Permission permission,HttpSession session)  {
		boolean um = permissionService.updatePermission(permission);
		return ResultUtil.getSR(um);
	}
	/**
	 * 权限增加
	 * @return 
	 */
	@ApiOperation(value = "权限增加", notes = "权限增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addPermission(@ModelAttribute Permission permission, HttpSession session) {
		boolean am = permissionService.addPermission(permission);
		return ResultUtil.getSR(am);
	}
	/**
	 * 权限删除
	 * @return
	 */
	@ApiOperation(value = "权限删除", notes = "权限删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="permissionId",value="权限ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delPermission(@RequestParam("permissionId") Integer permissionId,HttpSession session)  {
		boolean dm = permissionService.delPermission(permissionId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * 权限浏览数量
	 * @return
	 */
	@ApiOperation(value = "权限数量", notes = "权限数量查询")
	@ApiImplicitParams({
			@ApiImplicitParam(name="type",value="权限类型，默认0开放，1鉴权",dataType="int", paramType = "query"),
			@ApiImplicitParam(name="managerName",value="权限管理名",dataType="string", paramType = "query"),
			@ApiImplicitParam(name="name",value="权限名",dataType="string", paramType = "query"),
			@ApiImplicitParam(name="route",value="权限路由",dataType="string", paramType = "query")
	})
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(
			@RequestParam(value="type",required=false)Integer type,
			@RequestParam(value="managerName",required=false)String managerName,
			@RequestParam(value="name",required=false)String name,
			@RequestParam(value="route",required=false)String route,
			HttpSession session)  {
		int count = permissionService.countAll(type,managerName,name,route);
		return count;
	}
	/**
	 * 权限单个加载
	 * @return
	 */
	@ApiOperation(value = "权限单个加载", notes = "权限单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="permissionId",value="权限ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/load", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList<List<Permission>> loadPermission(@RequestParam("permissionId") Integer permissionId,HttpSession session)  {
		List<Permission> list = new ArrayList<Permission>();
		Permission Permission = permissionService.loadPermission(permissionId);
			if(Permission!=null &&!Permission.equals("")){
				list.add(Permission);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotIsNotExistException("权限");//不存在
			}
	}
	
}
