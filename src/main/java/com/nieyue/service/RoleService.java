package com.nieyue.service;

import java.util.List;

import com.nieyue.bean.Role;

/**
 * 角色逻辑层接口
 * @author yy
 *
 */
public interface RoleService {
	/** 新增角色 */	
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
	public List<Role> browsePagingRole(int pageNum,int pageSize,String orderName,String orderWay) ;
}
