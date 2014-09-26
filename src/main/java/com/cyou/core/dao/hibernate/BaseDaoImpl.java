package com.cyou.core.dao.hibernate;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

import org.displaytag.properties.SortOrderEnum;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.cyou.core.bean.PageList;
import com.cyou.core.dao.BaseDao;

public class BaseDaoImpl extends HibernateDaoSupport implements BaseDao {

    
	@SuppressWarnings("unchecked")
	public <T> T get(Class<T> entityClass, Serializable id)
    	throws DataAccessException
	{
	    return (T)getHibernateTemplate().get(entityClass, id);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T get(Class<T> entityClass, Serializable id, LockMode lockMode)
	    throws DataAccessException
	{
	    return (T)getHibernateTemplate().get(entityClass, id, lockMode);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T load(Class<T> entityClass, Serializable id)
	    throws DataAccessException
	{
	    return (T)getHibernateTemplate().load(entityClass, id);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T load(Class<T> entityClass, Serializable id, LockMode lockMode)
	    throws DataAccessException
	{
	    return (T)getHibernateTemplate().load(entityClass, id, lockMode);
	}
	
	 @SuppressWarnings("unchecked")
	public <T> List<T> loadAll(Class<T> entityClass)
     	throws DataAccessException
	 {
	     return getHibernateTemplate().loadAll(entityClass);
	 }
	 
	 public void evict(Object entity)
	     throws DataAccessException
	 {
	     getHibernateTemplate().evict(entity);
	 }
	
	 public Serializable save(Object entity)
	     throws DataAccessException
	 {
	     return getHibernateTemplate().save(entity);
	 }
	
	 public void saveOrUpdate(Object entity)
	     throws DataAccessException
	 {
	     getHibernateTemplate().saveOrUpdate(entity);
	 }
	
	 public void update(Object entity)
	     throws DataAccessException
	 {
	     getHibernateTemplate().update(entity);
	 }
	
	 public void update(Object entity, LockMode lockMode)
	     throws DataAccessException
	 {
	     getHibernateTemplate().update(entity, lockMode);
	 }
	 
	 public void updateHQL(final String hql)
		     throws DataAccessException
		 {
		     getHibernateTemplate().execute(new HibernateCallback() {
				
				@Override
				public Object doInHibernate(Session session) throws HibernateException,
						SQLException {
					// TODO Auto-generated method stub
					return session.createQuery(hql).executeUpdate();
				}
			});
		 }
	 
	
	 public void delete(Object entity)
	     throws DataAccessException
	 {
	     getHibernateTemplate().delete(entity);
	 }
	
	 public void delete(Object entity, LockMode lockMode)	
	     throws DataAccessException
	 {
	     getHibernateTemplate().delete(entity, lockMode);
	 }
	
	 public void flush()
	     throws DataAccessException
	 {
	     getHibernateTemplate().flush();
	 }
	
	 public void clear()
	     throws DataAccessException
	 {
	     getHibernateTemplate().clear();
	 }
	
	 public List find(String queryString) throws DataAccessException
	 {
	     try{
	    	 return getHibernateTemplate().find(queryString);
	     }catch(Exception e){
	    	 e.printStackTrace();
	     }
	     return null;
	 }
	 
	 public List<?> find(String queryString,Object[] obj){
	 	return getHibernateTemplate().find(queryString, obj);
	 }
	 public List<?> find(String hql,Object[] obj,int statrNum,int lastNum){
	 	Query q = super.getSession().createQuery(hql);
	 	if(obj != null && obj.length>0){
	 		for(int i = 0;i<obj.length;i++){
	 			q.setParameter(i, obj[i]);
	 		}
	 	}
	 	q.setFirstResult(statrNum);
			q.setMaxResults(lastNum);
			return q.list();
	 }
	 
	 /**
		 * 根据HQL语句进行分页查询
		 * @param hql
		 * @param objs
		 * @param offset
		 * @param pagesize
		 * @return
		 */
		public PageList search(String hql,
								Object[] objs,
								PageList pageList ){
			hql = getHqlbyPageList(hql, pageList);
			//获取记录总数
			String countHql = getCountQuery(hql);
			Query query = getSession().createQuery(countHql);
			
			if(objs != null && objs.length > 0){
				for(int i=0; i<objs.length; i++){
					query.setParameter(i, objs[i]);
				}
			}
			int total = ((Long)query.uniqueResult()).intValue();
			//获取当前页的结果集
			query = getSession().createQuery(hql);
			if(objs != null && objs.length > 0){
				for(int i=0; i<objs.length; i++){
					query.setParameter(i, objs[i]);
				}
			}
			if(pageList.isExport() == false){
				int offset = (pageList.getPageNumber()-1)*pageList.getObjectsPerPage();
				int pagesize = pageList.getObjectsPerPage();
				query.setFirstResult(offset);
				query.setMaxResults(pagesize);
			}else{
				pageList.setObjectsPerPage(total);
			}
			
			List<?> datas = query.list();
			pageList.setFullListSize(total);
			pageList.setList(datas);
			return pageList;
		}
		
		
		/**
		 * 根据HQL语句进行分页查询
		 * @param hql
		 * @param objs
		 * @param offset
		 * @param pagesize
		 * @return
		 */
		public PageList searchSQL(String hql,
								Object[] objs,
								PageList pageList ){
			hql = getHqlbyPageList(hql, pageList);
			try{
				//获取记录总数
				String countHql = getCountQuery(hql);
				Query query = getSession().createSQLQuery(countHql);
				
				if(objs != null && objs.length > 0){
					for(int i=0; i<objs.length; i++){
						query.setParameter(i, objs[i]);
					}
				}
				int total = ((BigInteger)query.uniqueResult()).intValue();
				
				//获取当前页的结果集
				query = getSession().createSQLQuery(hql);
				if(objs != null && objs.length > 0){
					for(int i=0; i<objs.length; i++){
						query.setParameter(i, objs[i]);
					}
				}
				if(pageList.isExport() == false){
					int offset = (pageList.getPageNumber()-1)*pageList.getObjectsPerPage();
					int pagesize = pageList.getObjectsPerPage();
					query.setFirstResult(offset);
					query.setMaxResults(pagesize);
				}else{
					pageList.setObjectsPerPage(total);
				}
				
				List<?> datas = query.list();
				
				pageList.setFullListSize(total);
				pageList.setList(datas);
				return pageList;
			}catch(Exception e){
				e.printStackTrace();
			}
			return pageList;
		}
		/**
		 * select ... from Object o where o.id is null
		 * 经过转换，可以得到：
		 * select count(*) from Object o where o.id is null
		 * @param hql
		 * @return
		 */
		private String getCountQuery(String hql){
			int index = hql.indexOf("from");
	    	int end = hql.lastIndexOf("order by");
	    	
	    	if (index!=-1 && end!=-1)
	    		hql = "select count(*) " + hql.substring(index,end);
			else if(index != -1)
				hql = "select count(*) " + hql.substring(index);
			
			return hql;
		}
		
		/**
		 * 根据pageList属性 获取hql语句
		 * @param hql
		 * @param pageList
		 * @return
		 */
		private String getHqlbyPageList(String hql,PageList pageList){
			if(pageList.getSortCriterion() == null){
				return hql;
			}else{
				int end = hql.lastIndexOf("order");
				String sortDirectionString = " asc";
				if(SortOrderEnum.DESCENDING.equals(pageList.getSortDirection())){
					sortDirectionString = " desc";
				}
		    	if(end ==-1){
		    		hql+=" order by "+pageList.getSortCriterion() +sortDirectionString;
		    	}else{
		    		hql+=" "+pageList.getSortCriterion() +sortDirectionString;
		    	}
				return hql;
			}
	    	
		}
		
		/**
		 * 得到总记录数
		 * @param hql         hql语句
		 * @return 总记录数
		 */
		public Long getCountRows(final String hql) {
			return (Long) getHibernateTemplate().execute(new HibernateCallback() {
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					return session.createQuery(hql).uniqueResult();
				}
			});
		}
		/**
		 * 得到列表数据
		 * @param hql         hql语句
		 * @param offset      偏移量
		 * @param pagesize    每页显示记录数
		 * @return            日志列表,如果没有查到结果，返回长度为0的list
		 */
		@SuppressWarnings("unchecked")
		public List getPageData(final String hql, final int offset,
				final int pagesize) {
			return (List) getHibernateTemplate().execute(new HibernateCallback() {
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					return session.createQuery(hql).setFirstResult(offset)
							.setMaxResults(pagesize).list();
				}
			});
		}
		/**
		 * 得到列表数据
		 * @param hql         hql语句
		 * @return            日志列表,如果没有查到结果，返回长度为0的list
		 */
		public List getPageData(final String hql) {
			return (List) getHibernateTemplate().execute(new HibernateCallback() {
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					return session.createQuery(hql).list();
				}
			});
		}
	
}
