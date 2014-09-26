package com.cyou.applyTeach.service;

import com.cyou.applyTeach.bean.ApplyTeach;
import com.cyou.core.bean.PageList;




public interface ApplyTeachService{
	
	PageList getPageList(PageList pageList);

	ApplyTeach getApplyTeach(Integer id);

	boolean saveOrUpdateApplyTeach(ApplyTeach at);
	
}
