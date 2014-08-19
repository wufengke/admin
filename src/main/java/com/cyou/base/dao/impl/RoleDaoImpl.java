package com.cyou.base.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.cyou.base.bean.UserRole;
import com.cyou.base.dao.RoleDao;
import com.cyou.core.dao.hibernate.BaseCyouPayDaoImpl;
@Repository
public class RoleDaoImpl extends BaseCyouPayDaoImpl implements RoleDao{

	@Override
	public List<UserRole> getUserRolesByRole(final Integer id) {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(Session arg0) throws HibernateException,
					SQLException {
				return arg0.createQuery("from UserRole ur where ur.userRoleId.role_Id=" + id).list();
			}
		});
	}

	@Override
	public void removeUserRole(final Integer id) {
		getHibernateTemplate().execute(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(Session arg0) throws HibernateException,
					SQLException {
						return arg0.createQuery("delete from UserRole ur where ur.userRoleId.role_Id=" + id).executeUpdate();
			}
		});
	}

}
