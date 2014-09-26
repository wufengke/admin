package com.cyou.core.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.LockMode;

import com.cyou.core.bean.PageList;

public interface BaseDao {
	/**
	 * 根据 ID 得到一个对象
	 * @param entityClass 实体类型
	 * @param id 主键
	 * @return 实体
	 */
	public <T> T get(Class<T> entityClass, Serializable id);
	
	public <T> T get(Class<T> entityClass, Serializable id, LockMode lockMode);
	
	public <T> T load(Class<T> entityClass, Serializable id);
	
	public <T> T load(Class<T> entityClass, Serializable id, LockMode lockMode);
	
	/**
	 * 根据实体类名 得到该类所有对象
	 * @param entityClass
	 * @return
	 */
	public <T> List<T> loadAll(Class<T> entityClass);
	/**
	 * 删除 hibernateSession 中的对象
	 * @param entity
	 */
	public void evict(Object entity);
	/**
	 * 存储一个对象
	 * @param entity
	 * @return 存储后的ID
	 */
	public Serializable save(Object entity);
	/**
	 * 存储一个对象（存在该对象就更新，否则SAVE）
	 * @param entity
	 */
	public void saveOrUpdate(Object entity);
	/**
	 * 更新一个对象
	 * @param entity
	 */
	public void update(Object entity);
	
	public void update(Object entity, LockMode lockMode);
	/**
	 * 删除一个对象
	 * @param entity
	 */
	public void delete(Object entity);
	
	public void delete(Object entity, LockMode lockMode);
	/**
	 * 同步 hibernateSession 中的对象（刷新）
	 */
	public void flush();
	/**
	 * 清空 hibernateSession 中的对象
	 */
	public void clear();
	/**
	 * 根据 HQL 语句 查询对象集合
	 * @param queryString
	 * @return
	 */
	public List find(String queryString);
	/**
	 * 根据 HQL 语句 查询对象集合
	 * @param queryString HQL语句（参数为 ？）
	 * @param obj 参数数组
	 * @return
	 */
	public List find(String queryString,Object[] obj);
	/**
	 * 根据HQL语句查询前几条对象的集合
	 * @param hql HQL语句（参数为 ？）
	 * @param obj 参数数组
	 * @param statrNum  其实数
	 * @param lastNum   结束数
	 * @return
	 */
	public List find(String hql,Object[] obj,int statrNum,int lastNum);
	
	/**
	 * 根据hql、参数、pageList 获取Pagelist对象
	 * @param hql 
	 * @param objs 
	 * @param pageList 
	 * @return Pagelist对象
	 */
	public PageList search(String hql,
			Object[] objs,
			PageList pageList );
	
	/**
	 * 根据hql、参数、pageList 获取Pagelist对象
	 * @param hql 
	 * @param objs 
	 * @param pageList 
	 * @return Pagelist对象
	 */
	public PageList searchSQL(String hql,
			Object[] objs,
			PageList pageList );
	
	public void updateHQL(final String sql);
}
