package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.Permission;
import com.nieyue.bean.RolePermission;
import com.nieyue.dao.PermissionDao;
import com.nieyue.service.PermissionService;
import com.nieyue.service.RolePermissionService;
import com.nieyue.shiro.ShiroService;
import com.nieyue.shiro.ShiroUtil;

@Service
public class PermissionServiceImpl implements PermissionService{
	@Resource
	PermissionDao permissionDao;
	@Resource
	RolePermissionService rolePermissionService;
	@Resource
	ShiroService shiroService;
	@Resource
	ShiroUtil shiroUtil;
	/**
	 * 初始化注册权限
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean initPermission() {
		boolean b = shiroUtil.initPermission();
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addPermission(Permission permission) {
		Lock lock=new ReentrantLock();
		lock.lock();
		boolean b=false;
		try {
			List<Permission> pl = browsePagingPermission(null, null, null, permission.getRoute(), 1, Integer.MAX_VALUE, "permission_id", "asc");
			if(pl.size()>0){
				return b;
			}
			permission.setUpdateDate(new Date());
			b = permissionDao.addPermission(permission);
			if(b){
				//同步钱权限数据
				shiroService.updatePermission(browsePagingPermission(null,null,null,null,1,Integer.MAX_VALUE,"permission_id","asc"));
			}
		}finally {
			lock.unlock();
		}
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delPermission(Integer permissionId) {
		boolean b = permissionDao.delPermission(permissionId);
		if(b){
			List<RolePermission> rpl = rolePermissionService.browsePagingRolePermission(null, null, permissionId, 1, Integer.MAX_VALUE, "role_permission_id", "asc");
			for (RolePermission rolePermission : rpl) {
				b=rolePermissionService.delRolePermission(rolePermission.getRolePermissionId());
			}
			if(b){
				//同步钱权限数据
				shiroService.updatePermission(browsePagingPermission(null,null,null,null,1,Integer.MAX_VALUE,"permission_id","asc"));
			}
		}
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updatePermission(Permission permission) {
		permission.setUpdateDate(new Date());
		boolean b = permissionDao.updatePermission(permission);
		if(b){
			//同步钱权限数据
			shiroService.updatePermission(browsePagingPermission(null,null,null,null,1,Integer.MAX_VALUE,"permission_id","asc"));
		}
		return b;
	}

	@Override
	public Permission loadPermission(Integer permissionId) {
		Permission r = permissionDao.loadPermission(permissionId);
		return r;
	}

	@Override
	public int countAll(
						Integer type,
						String managerName,
						String name,
						String route) {
		int c = permissionDao.countAll(
				 type,
				 managerName,
				 name,
				 route
		);
		return c;
	}

	@Override
	public List<Permission> browsePagingPermission(
			Integer type,
			String managerName,
			String name,
			String route,
			int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<Permission> l = permissionDao.browsePagingPermission(
				 type,
				 managerName,
				 name,
				 route,
				pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	
}
