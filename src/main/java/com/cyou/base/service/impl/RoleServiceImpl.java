package com.cyou.base.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cyou.base.bean.Role;
import com.cyou.base.bean.UserRole;
import com.cyou.base.dao.RoleDao;
import com.cyou.base.service.RoleService;
import com.cyou.core.bean.PageList;
@Service
@Transactional
public class RoleServiceImpl  implements RoleService{
	private final static Logger logger = Logger.getLogger(RoleServiceImpl.class);
	@Resource
	private RoleDao roleDao;

	@Override
	public PageList getPageList(PageList pageList) {
		PageList list = roleDao.search("from Role ", null, pageList);
		return list;
	}
	
	@Override
	public void addRole(Role role) {
		try {
			roleDao.save(role);
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
		}	
	}
	
	@Override
	public void updateRole(Role role) {
		Role sessionRole = roleDao.get(Role.class, role.getId());
		sessionRole.setDescription(role.getDescription());
		roleDao.update(sessionRole);
	}

	@Override
	public boolean isRoleExist(String name) {
		List list =  roleDao.find("from Role where name = ?", new Object[]{name});
		if(list != null && list.size() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Role getRoleById(Integer id) {
		try {
			return roleDao.get(Role.class, id);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public List<UserRole> getUserRolesByRole(Role r) {
		try {
			return roleDao.getUserRolesByRole(r.getId());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public void removeRole(Role role) {
		try {
			roleDao.delete(role);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
	}

	@Override
	public void removeUserRole(Integer id) {
		try {
			roleDao.removeUserRole(id);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}


}
