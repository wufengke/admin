package com.cyou.base.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyou.base.bean.Dictionary;
import com.cyou.base.dao.DictionaryDao;
import com.cyou.base.service.DictionaryService;
@Service
public class DictionaryServiceImpl implements DictionaryService {
	@Autowired
	private DictionaryDao dictionaryDao;

	private final static Logger logger = Logger.getLogger(DictionaryServiceImpl.class);

	@Override
	public String findByGroupnameDictkey(String groupName, String dictKey) {
		if (dictKey == null) {
			return null;
		}

		List<Dictionary> list = this.findByGroupnameOrderbyRankAsc(groupName);
		if (list.size() > 0) {
			for (Iterator<Dictionary> iter = list.iterator(); iter.hasNext();) {
				Dictionary dictionary = iter.next();
				if (dictionary.getDictKey().equals(dictKey)) {
					return dictionary.getDictValue();
				}
			}
		}
		return null;
	}

	public List<Dictionary> findByGroupnameOrderbyRankAsc(String groupName) {

		List<Dictionary> list = null;
		// 从数据库中提取
		try {
			list = dictionaryDao.findByGroupnameOrderbyRankAsc(groupName);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			list = new ArrayList<Dictionary>(0);
		}
		return list;
	}

	@Override
	public Dictionary findDictByGroupnameDictkey(String groupName,
			String dictKey) {
		if (dictKey == null) {
			return null;
		}

		List<Dictionary> list = this.findByGroupnameOrderbyRankAsc(groupName);
		if (list.size() > 0) {
			for (Iterator<Dictionary> iter = list.iterator(); iter.hasNext();) {
				Dictionary dictionary = iter.next();
				if (dictionary.getDictKey().equals(dictKey)) {
					return dictionary;
				}
			}
		}
		return null;
	}

}
