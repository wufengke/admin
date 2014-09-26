package com.cyou.applyTeach.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cyou.applyTeach.bean.ApplyTeach;
import com.cyou.applyTeach.dao.ApplyTeachDao;
import com.cyou.applyTeach.service.ApplyTeachService;
import com.cyou.base.bean.Account;
import com.cyou.base.dao.UserDao;
import com.cyou.core.bean.PageList;
@Service
@Transactional
public class ApplyTeachServiceImpl  implements ApplyTeachService{

	private static Logger logger = Logger.getLogger(ApplyTeachServiceImpl.class);

	@Resource
	private ApplyTeachDao applyTeachDao;
	
	@Resource
	private UserDao userDao;
	
	@Override
	public PageList getPageList(PageList pageList) {
		try {
			return applyTeachDao.search("from ApplyTeach a order by a.updateTime desc", null, pageList);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return pageList;
		}
		
	}

	@Override
	public ApplyTeach getApplyTeach(Integer id) {
		try {
			return applyTeachDao.get(ApplyTeach.class, id);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public boolean saveOrUpdateApplyTeach(ApplyTeach at) {
		try {
			applyTeachDao.saveOrUpdate(at);
			Account account = userDao.getAccountByUserId(at.getUserId());
			if(account != null){
				if(at.getStatus().equals(1)){
					account.setAccountType("1");
				}else {
					account.setAccountType("0");
				}
				userDao.update(account);
			}
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
		
	}
}
