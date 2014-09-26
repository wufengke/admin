package com.cyou.core.dao.hibernate;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;

public class BaseCyouPayDaoImpl extends BaseDaoImpl{

    @Resource(name="sessionFactory")
    public void setSupperSessionFactory(SessionFactory sessionFactory) {
    	super.setSessionFactory(sessionFactory);
    }
    
	

}
