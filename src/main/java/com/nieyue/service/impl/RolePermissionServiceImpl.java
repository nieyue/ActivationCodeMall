package com.nieyue.service.impl;

import com.nieyue.bean.Permission;
import com.nieyue.bean.RolePermission;
import com.nieyue.dao.RolePermissionDao;
import com.nieyue.service.PermissionService;
import com.nieyue.service.RolePermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class RolePermissionServiceImpl implements RolePermissionService{
	@Resource
	RolePermissionDao rolePermissionDao;
	@Resource
	PermissionService permissionService;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addRolePermission(RolePermission rolePermission) {
		rolePermission.setUpdateDate(new Date());
		boolean b = rolePermissionDao.addRolePermission(rolePermission);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delRolePermission(Integer rolePermissionId) {
		boolean b = rolePermissionDao.delRolePermission(rolePermissionId);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateRolePermission(RolePermission rolePermission) {
		rolePermission.setUpdateDate(new Date());
		boolean b = rolePermissionDao.updateRolePermission(rolePermission);
		return b;
	}

	@Override
	public RolePermission loadRolePermission(Integer rolePermissionId) {
		RolePermission r = rolePermissionDao.loadRolePermission(rolePermissionId);
			Permission permission = permissionService.loadPermission(r.getPermissionId());
			r.setPermission(permission);
		return r;
	}

	@Override
	public int countAll(
						Integer region,
						Integer roleId,
						Integer permissionId) {
		int c = rolePermissionDao.countAll(
				region,
				roleId,
				permissionId
		);
		return c;
	}

	@Override
	public List<RolePermission> browsePagingRolePermission(
			Integer region,
			Integer roleId,
			Integer permissionId,
			int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<RolePermission> l = rolePermissionDao.browsePagingRolePermission(
				region,
				roleId,
				permissionId,
				pageNum-1, pageSize, orderName, orderWay);
		l.forEach((e)->{
			Permission permission = permissionService.loadPermission(e.getPermissionId());
			e.setPermission(permission);
		});
		return l;
	}

	
}
