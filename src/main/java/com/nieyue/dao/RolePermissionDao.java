package com.nieyue.dao;

import com.nieyue.bean.RolePermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色权限数据库接口
 * @author yy
 *
 */
@Mapper
public interface RolePermissionDao {
	/** 新增角色权限*/	
	public boolean addRolePermission(RolePermission rolePermission) ;
	/** 删除角色权限 */	
	public boolean delRolePermission(Integer rolePermissionId) ;
	/** 更新角色权限*/	
	public boolean updateRolePermission(RolePermission rolePermission);
	/** 装载角色权限 */	
	public RolePermission loadRolePermission(Integer rolePermissionId);
	/** 角色权限总共数目 */	
	public int countAll(
            @Param("region") Integer region,
            @Param("roleId") Integer roleId,
            @Param("permissionId") Integer permissionId
    );
	/** 分页角色权限信息 */
	public List<RolePermission> browsePagingRolePermission(
            @Param("region") Integer region,
			@Param("roleId") Integer roleId,
			@Param("permissionId") Integer permissionId,
            @Param("pageNum") int pageNum,
            @Param("pageSize") int pageSize,
            @Param("orderName") String orderName,
            @Param("orderWay") String orderWay) ;
}
