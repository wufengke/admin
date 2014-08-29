package com.cyou.userorder.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.cyou.core.dao.hibernate.BaseCyouPayDaoImpl;
import com.cyou.userorder.bean.UserOrder;
import com.cyou.userorder.bean.UserOrderModel;
import com.cyou.userorder.dao.PayDao;
@Repository
@SuppressWarnings("unchecked")
public class PayDaoImpl extends BaseCyouPayDaoImpl implements PayDao{

	@Override
	public List<UserOrderModel> getOrderByUserId(final String userId) {
		return (List<UserOrderModel>) getHibernateTemplate().execute(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				return session.createSQLQuery("select c.SMALL_IMAGE_URL as smallImageUrl,c.COURSE_TITLE as courseTitle," +
						"o.ORDER_ID as orderId,o.AMOUNT as amount,o.STATUS as status, o.CREATE_TIME as createTime " +
						"from COURSE_BRIEF c, USER_ORDER o where c.COURSE_ID = o.COURSE_ID and o.USER_ID='"+userId+"' order by o.CREATE_TIME desc")
						.setResultTransformer(Transformers.aliasToBean(UserOrderModel.class)).list();
			}
		});
	}

	@Override
	public List<UserOrderModel> getOrderByUserIdAndStatus(final String userId,final int status) {
		
		return (List<UserOrderModel>) getHibernateTemplate().execute(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				return session.createSQLQuery("select c.SMALL_IMAGE_URL as smallImageUrl,c.COURSE_TITLE as courseTitle," +
						"o.ORDER_ID as orderId,o.AMOUNT as amount,o.STATUS as status, o.CREATE_TIME as createTime " +
						"from COURSE_BRIEF c, USER_ORDER o where c.COURSE_ID = o.COURSE_ID and o.USER_ID='"+userId+"' and o.status="+status+" order by o.CREATE_TIME desc")
						.setResultTransformer(Transformers.aliasToBean(UserOrderModel.class)).list();
			}
		});
	}
	
	@Override
	public Long getCanceledOrderCountByUserId(final String userId) {
		return (Long) getHibernateTemplate().execute(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				return session.createQuery("select count(o.orderId) from UserOrder o where o.userId='" + userId + "' and o.status=2").uniqueResult();
			}
		});
	}

	@Override
	public Long getNotPaidOrderCountByUserId(final String userId) {
		return (Long) getHibernateTemplate().execute(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				return session.createQuery("select count(o.orderId) from UserOrder o where o.userId='" + userId + "' and o.status=0").uniqueResult();
			}
		});
	}

	@Override
	public Long getPaidOrderCountByUserId(final String userId) {
		return (Long) getHibernateTemplate().execute(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				return session.createQuery("select count(o.orderId) from UserOrder o where o.userId='" + userId + "' and o.status=1").uniqueResult();
			}
		});
	}

	@Override
	public Long getRefundOrderCountByUserId(final String userId) {
		return (Long) getHibernateTemplate().execute(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				return session.createQuery("select count(o.orderId) from UserOrder o where o.userId='" + userId + "' and o.status=3").uniqueResult();
			}
		});
	}

	@Override
	public long getAllOrderCountByUserId(final String userId) {
		return (Long) getHibernateTemplate().execute(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				return session.createQuery("select count(o.orderId) from UserOrder o where o.userId='" + userId + "'").uniqueResult();
			}
		});
	}

	@Override
	public UserOrderModel getUserOrderModelByOrderId(final String orderId) {
		return (UserOrderModel) getHibernateTemplate().execute(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				return session.createSQLQuery("select c.original_Price as originalPrice,c.SMALL_IMAGE_URL as smallImageUrl,c.COURSE_TITLE as courseTitle," +
						"o.ORDER_ID as orderId,o.AMOUNT as amount,o.STATUS as status, o.CREATE_TIME as createTime " +
						"from COURSE_BRIEF c, USER_ORDER o where c.COURSE_ID = o.COURSE_ID and o.ORDER_ID='"+orderId+"'")
						.setResultTransformer(Transformers.aliasToBean(UserOrderModel.class)).uniqueResult();
			}
		});
	}

	@Override
	public UserOrder getOrderbyOrderId(final String orderId) {
		return (UserOrder) getHibernateTemplate().execute(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				return session.createQuery("from UserOrder o where o.orderId='" + orderId + "'").uniqueResult();
			}
		});
	}

	
}
