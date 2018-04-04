package com.nieyue.controller;

import com.nieyue.bean.RolePermission;
import com.nieyue.exception.NotAnymoreException;
import com.nieyue.exception.NotIsNotExistException;
import com.nieyue.service.RolePermissionService;
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
 * 角色权限控制类
 * @author yy
 *
 */
@Api(tags={"rolePermission"},value="角色权限",description="角色权限管理")
@RestController
@RequestMapping("/rolePermission")
public class RolePermissionController {
	@Resource
	private RolePermissionService rolePermissionService;
	
	/**
	 * 角色权限分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "角色权限列表", notes = "角色权限分页浏览")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="region",value="范围，1公共，2自身",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="roleId",value="角色id",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="permissionId",value="权限id",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="role_permission_id"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<RolePermission>> browsePagingRolePermission(
			@RequestParam(value="region",required=false)Integer region,
			@RequestParam(value="roleId",required=false)Integer roleId,
			@RequestParam(value="permissionId",required=false)Integer permissionId,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="role_permission_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<RolePermission> list = new ArrayList<RolePermission>();
			list= rolePermissionService.browsePagingRolePermission(
					region,roleId,permissionId,
					pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotAnymoreException();//没有更多
			}
	}
	/**
	 * 角色权限修改
	 * @return
	 */
	@ApiOperation(value = "角色权限修改", notes = "角色权限修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateRolePermission(@ModelAttribute RolePermission rolePermission,HttpSession session)  {
		boolean um = rolePermissionService.updateRolePermission(rolePermission);
		return ResultUtil.getSR(um);
	}
	/**
	 * 角色权限增加
	 * @return 
	 */
	@ApiOperation(value = "角色权限增加", notes = "角色权限增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addRolePermission(@ModelAttribute RolePermission rolePermission, HttpSession session) {
		boolean am = rolePermissionService.addRolePermission(rolePermission);
		return ResultUtil.getSR(am);
	}
	/**
	 * 角色权限删除
	 * @return
	 */
	@ApiOperation(value = "角色权限删除", notes = "角色权限删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="rolePermissionId",value="角色权限ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delRolePermission(@RequestParam("rolePermissionId") Integer rolePermissionId,HttpSession session)  {
		boolean dm = rolePermissionService.delRolePermission(rolePermissionId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * 角色权限浏览数量
	 * @return
	 */
	@ApiOperation(value = "角色权限数量", notes = "角色权限数量查询")
	@ApiImplicitParams({
			@ApiImplicitParam(name="region",value="范围，1公共，2自身",dataType="int", paramType = "query"),
			@ApiImplicitParam(name="roleId",value="角色id",dataType="int", paramType = "query"),
			@ApiImplicitParam(name="permissionId",value="权限id",dataType="int", paramType = "query")
	})
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(
			@RequestParam(value="region",required=false)Integer region,
			@RequestParam(value="roleId",required=false)Integer roleId,
			@RequestParam(value="permissionId",required=false)Integer permissionId,
			HttpSession session)  {
		int count = rolePermissionService.countAll(region,roleId,permissionId);
		return count;
	}
	/**
	 * 角色权限单个加载
	 * @return
	 */
	@ApiOperation(value = "角色权限单个加载", notes = "角色权限单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="rolePermissionId",value="角色权限ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/load", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList<List<RolePermission>> loadRolePermission(@RequestParam("rolePermissionId") Integer rolePermissionId,HttpSession session)  {
		List<RolePermission> list = new ArrayList<RolePermission>();
		RolePermission rolePermission = rolePermissionService.loadRolePermission(rolePermissionId);
			if(rolePermission!=null &&!rolePermission.equals("")){
				list.add(rolePermission);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotIsNotExistException("角色权限");//不存在
			}
	}
	
}
