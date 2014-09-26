package com.cyou.base.dao;

import java.util.List;

import com.cyou.base.bean.Dictionary;
import com.cyou.core.dao.BaseDao;


public interface DictionaryDao extends BaseDao {

	List<Dictionary> findByGroupnameOrderbyRankAsc(String groupName);
	
	
}
