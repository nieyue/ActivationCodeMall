package com.nieyue.service;

import com.nieyue.bean.Permission;

import java.util.List;

/**
 * 权限逻辑层接口
 * @author yy
 *
 */
public interface PermissionService {
	/** 新增权限 */	
	public boolean addPermission(Permission permission) ;	
	/** 删除权限 */	
	public boolean delPermission(Integer permissionId) ;
	/** 更新权限*/	
	public boolean updatePermission(Permission permission);
	/** 初始化权限 */	
	public boolean initPermission();	
	/** 装载权限 */	
	public Permission loadPermission(Integer permissionId);	
	/** 权限总共数目 */	
	public int countAll(
			Integer type,
			String managerName,
			String name,
			String route
	);
	/** 分页权限信息 */
	public List<Permission> browsePagingPermission(
			Integer type,
			String managerName,
			String name,
			String route,
			int pageNum, int pageSize, String orderName, String orderWay) ;
}
