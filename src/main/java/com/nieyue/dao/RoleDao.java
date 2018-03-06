package com.nieyue.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.Role;

/**
 * 角色数据库接口
 * @author yy
 *
 */
@Mapper
public interface RoleDao {
	/** 新增角色*/	
	public boolean addRole(Role role) ;	
	/** 删除角色 */	
	public boolean delRole(Integer roleId) ;
	/** 更新角色*/	
	public boolean updateRole(Role role);
	/** 装载角色 */	
	public Role loadRole(Integer roleId);	
	/** 角色总共数目 */	
	public int countAll();	
	/** 分页角色信息 */
	public List<Role> browsePagingRole(@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("orderName")String orderName,@Param("orderWay")String orderWay) ;		
}
