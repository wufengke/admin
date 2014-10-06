package com.cyou.config.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cyou.config.bean.IndexPic;
import com.cyou.config.dao.ConfigDao;
import com.cyou.config.service.ConfigService;
import com.cyou.core.bean.PageList;
@Service
@Transactional
public class ConfigServiceImpl implements ConfigService {
	private static Logger logger = Logger.getLogger(ConfigServiceImpl.class);
	
	@Resource
	private ConfigDao configDao;
	
	@Override
	public void saveIndexPic(IndexPic ip) {
		try{
			configDao.save(ip);
		}catch(Exception e)
		{
			logger.error(e.getMessage(),e);
		}
	}
	@Override
	public PageList getPageList(PageList pagelist) {
		try {
			System.out.println("right");
			return configDao.search("from IndexPic i order by i.rank ", null, pagelist);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			System.out.println("Wrong");
			return pagelist;
		}
		
	}
	
	@Override
	public void updateIndexPic(IndexPic ip)
	{
		try{
		   configDao.update(ip);
		}catch(Exception e)
		{
			logger.error(e.getMessage(),e);
		}
	}
	
	@Override
	public IndexPic getIndexPicById(Integer id) {
		// TODO Auto-generated method stub
		try{
			 return configDao.get(IndexPic.class, id);
			}catch(Exception e)
			{
				logger.error(e.getMessage(),e);
				return null;
			}
	}
	@Override
	public void deleteIndexPic(IndexPic ip) {
		// TODO Auto-generated method stub
		try{
			 configDao.delete(ip);
			}catch(Exception e)
			{
				logger.error(e.getMessage(),e);
			}
	}
	@Override
	public void addIndexPic(IndexPic ip) {
		// TODO Auto-generated method stub
		try{
		 configDao.save(ip);
		}catch(Exception e)
		{
			logger.error(e.getMessage(),e);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<IndexPic> GetUseableIndexPic() {
		try{
		return configDao.find("from IndexPic i where i.status=1 order by i.rank");
		}catch(Exception e)
		{
			logger.error(e.getMessage(),e);
		}
		return null;
	}	
}
