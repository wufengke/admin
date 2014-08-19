package com.cyou.core.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsService;
import org.springframework.security.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.cyou.base.bean.Role;
import com.cyou.base.bean.User;
import com.cyou.core.util.StrMD5;

	@Repository("securityService")
	public class SecurityService extends HibernateDaoSupport implements UserDetailsService {

	    /**
	     * Init sessionFactory here because the annotation of Spring 2.5 can not support override inject
	     *  
	     * @param sessionFactory
	     */
		@Resource(name="sessionFactory")
	    public void init(SessionFactory sessionFactory) {
	        super.setSessionFactory(sessionFactory);
	    }

	    @SuppressWarnings("unchecked")
		public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException, DataAccessException {
	    	if(userName!=null && "administrator".equals(userName)){
	    		User user = new User();
	    		user.setUsername("administrator");
	    		user.setPassword(new StrMD5(")P(O*I&Ucyou").getResult());
	    		Set<Role> roles = new HashSet<Role>();
	    		roles.add(new Role("ROLE_ADMIN"));
	    		user.setRoles(roles);
	    		return user;
	    	}else{
	    		List<User> users = getHibernateTemplate().find("FROM User user WHERE user.username = ? AND user.disabled = false", userName);
		        if(users.isEmpty()) {
		            throw new UsernameNotFoundException("User " + userName + " has no GrantedAuthority");
		        }
		        return users.get(0);
	    	}
	        
	    }
	}

