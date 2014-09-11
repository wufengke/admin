package com.cyou.feedback.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cyou.core.bean.PageList;
import com.cyou.feedback.bean.Feedback;
import com.cyou.feedback.dao.AboutDao;
import com.cyou.feedback.service.AboutService;
@Service
@Transactional
public class AboutServiceImpl  implements AboutService{

	private static Logger logger = Logger.getLogger(AboutServiceImpl.class);

	@Resource
	private AboutDao aboutDao;
	@Override
	public void saveFeedback(Feedback fb) {
		try {
			aboutDao.save(fb);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	@Override
	public PageList getPageList(PageList pageList) {
		return aboutDao.search("from Feedback f order by f.createTime desc", null, pageList);
	}
	
}
