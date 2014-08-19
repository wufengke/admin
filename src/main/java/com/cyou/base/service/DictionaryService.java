package com.cyou.base.service;

import java.util.List;

import com.cyou.base.bean.Dictionary;



public interface DictionaryService {
	/**
	 * 根据groupname得到一组字典，按rank升序排列
	 * 
	 * @param groupName
	 *            字典组名
	 * @return 如果没有查到结果返回长度为0的list
	 */
	public List<Dictionary> findByGroupnameOrderbyRankAsc(String groupName);

	/**
	 * 根据groupName、dictKey得到字典值
	 * 
	 * @param groupName
	 *            字典组名
	 * @param dictKey
	 *            字典key
	 * @return 如果没有查到结果，返回null
	 */
	public String findByGroupnameDictkey(String groupName, String dictKey);

	/**
	 * 根据groupName、dictKey得到字典
	 * 
	 * @param groupName
	 *            字典组名
	 * @param dictKey
	 *            字典key
	 * @return 如果没有查到结果，返回null
	 */
	public Dictionary findDictByGroupnameDictkey(String groupName,
			String dictKey);

}
