package com.cyou.feedback.service;

import com.cyou.core.bean.PageList;
import com.cyou.feedback.bean.Feedback;




public interface AboutService{

	void saveFeedback(Feedback fb);

	PageList getPageList(PageList pagelist);

}
