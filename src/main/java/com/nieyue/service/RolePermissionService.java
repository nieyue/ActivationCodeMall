package com.nieyue.service;

import com.nieyue.bean.RolePermission;

import java.util.List;

/**
 * 角色权限逻辑层接口
 * @author yy
 *
 */
public interface RolePermissionService {
	/** 新增角色权限 */	
	public boolean addRolePermission(RolePermission rolePermission) ;
	/** 删除角色权限 */	
	public boolean delRolePermission(Integer rolePermissionId) ;
	/** 更新角色权限*/	
	public boolean updateRolePermission(RolePermission rolePermission);
	/** 装载角色权限 */	
	public RolePermission loadRolePermission(Integer rolePermissionId);
	/** 角色权限总共数目 */	
	public int countAll(
            Integer region,
			Integer roleId,
			Integer permissionId
    );
	/** 分页角色权限信息 */
	public List<RolePermission> browsePagingRolePermission(
			Integer region,
			Integer roleId,
			Integer permissionId,
            int pageNum, int pageSize, String orderName, String orderWay) ;
}
