package com.nieyue.dao;

import com.nieyue.bean.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 权限数据库接口
 * @author yy
 *
 */
@Mapper
public interface PermissionDao {
	/** 新增权限*/	
	public boolean addPermission(Permission permission) ;
	/** 删除权限 */	
	public boolean delPermission(Integer permissionId) ;
	/** 更新权限*/	
	public boolean updatePermission(Permission permission);
	/** 装载权限 */	
	public Permission loadPermission(Integer permissionId);
	/** 权限总共数目 */	
	public int countAll(
			@Param("type") Integer type,
			@Param("managerName") String managerName,
			@Param("name") String name,
			@Param("route") String route
	);
	/** 分页权限信息 */
	public List<Permission> browsePagingPermission(
			@Param("type") Integer type,
			@Param("managerName") String managerName,
			@Param("name") String name,
			@Param("route") String route,
			@Param("pageNum") int pageNum,
			@Param("pageSize") int pageSize,
			@Param("orderName") String orderName,
			@Param("orderWay") String orderWay) ;
}
