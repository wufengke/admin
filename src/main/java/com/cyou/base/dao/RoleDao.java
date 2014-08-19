package com.cyou.base.dao;

import java.util.List;

import com.cyou.base.bean.UserRole;
import com.cyou.core.dao.BaseDao;

public interface RoleDao extends BaseDao{

	List<UserRole> getUserRolesByRole(Integer id);

	void removeUserRole(Integer id);

}
