package com.cyou.base.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cyou.base.bean.Dictionary;
import com.cyou.base.dao.DictionaryDao;
import com.cyou.core.dao.hibernate.BaseCyouPayDaoImpl;




@Repository
public class DictionaryDaoImpl extends BaseCyouPayDaoImpl implements DictionaryDao{

	/**
	 * 添加字典
	 * 
	 * @param clubDictionary
	 * @return 返回的对象如果id=0，表示添加失败
	 */
	public Dictionary saveDictionary(Dictionary dictionary) {
		getHibernateTemplate().save(dictionary);
		return dictionary;
	}

	/**
	 * 修改字典
	 * 
	 * @param dictionary
	 */
	public void updateDictionary(Dictionary dictionary) {
		getHibernateTemplate().update(dictionary);
	}

	/**
	 * 删除字典
	 * 
	 * @param dictionary
	 */
	public void deleteDictionary(Dictionary dictionary) {
		getHibernateTemplate().delete(dictionary);
	}

	/**
	 * 根据ID得到某一个字典信息
	 * 
	 * @param id
	 *            字典id
	 * @return 如果没有查询到相关对像，返回NULL
	 */
	public Dictionary findById(short id) {
		return (Dictionary) getHibernateTemplate().get(Dictionary.class, id);
	}

	/**
	 * 根据groupname得到组字典，按rank升序排列
	 * 
	 * @param groupName
	 * @return 如果没有查到结果，返回长度为0的list
	 */
	@SuppressWarnings("unchecked")
	public List<Dictionary> findByGroupnameOrderbyRankAsc(final String groupName) {
		String hql = "from Dictionary " 
				+ " where groupName='"+groupName+"' and status=1 order by rank asc";
		return getPageData(hql);
	}

	/**
	 * 根据status得到字典，按index，rank升序排列
	 * 
	 * @param status
	 *            是否启用
	 * @return 如果没有查到结果，返回长度为0的list；
	 */
	@SuppressWarnings("unchecked")
	public List<Dictionary> findByStatusOrderbyIndexRankAsc(final byte status) {
		String hql = "from Dictionary" 
				+ " where status='"+status+"' group by groupName order by rank asc";
		return getPageData(hql);
	}

	/**
	 * 根据pid得到字典，按index，rank升序排列
	 * 
	 * @param pid
	 *            上一级id
	 * @return 如果没有查到结果，返回长度为0的list
	 */
	@SuppressWarnings("unchecked")
	public List<Dictionary> findByPidOrderbyIndexRankAsc(final short pid) {
		String hql = "from Dictionary"
				+ " where pid='"+pid+"' and status=1 group by groupName order by rank asc";
		return getPageData(hql);
	}

	
	public String findByGroupnameKey(String groupName, String key) {
		String hql = "from Dictionary" + " where groupName='"
				+ groupName + "' and dictKey='" + key
				+ "' and status=1 order by rank asc";
		List<Dictionary> list = getPageData(hql);
		if (list == null || list.size() == 0) {
			return null;
		} else {
			return list.get(0).getDictValue();
		}
	}
}
