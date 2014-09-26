package com.cyou.course.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.cyou.core.dao.hibernate.BaseCyouPayDaoImpl;
import com.cyou.course.bean.Course;
import com.cyou.course.bean.CourseDetail;
import com.cyou.course.dao.CourseDao;
@Repository
@SuppressWarnings("unchecked")
public class CourseDaoImpl extends BaseCyouPayDaoImpl implements CourseDao{

	@Override
	public List<Course> getOnlineCourseList() {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				// TODO Auto-generated method stub
				return session.createQuery("from Course c where c.status = 1").list();
			}
		});
	}

	@Override
	public CourseDetail getCourseDetailByDetailId(final String detailId) {
		return (CourseDetail) getHibernateTemplate().execute(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				return session.createQuery("from CourseDetail cd where cd.courseDetailId='" + detailId + "'").uniqueResult();
			}
		});
	}

	@Override
	public Course getCourseByDetailId(final String detailId) {
		return (Course) getHibernateTemplate().execute(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				return session.createQuery("from Course c where c.courseDetailId='" + detailId + "'").uniqueResult();
			}
		});
	}

	
}
