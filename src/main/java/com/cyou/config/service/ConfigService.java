package com.cyou.config.service;

import java.util.List;

import com.cyou.config.bean.IndexPic;
import com.cyou.core.bean.PageList;
import com.cyou.course.bean.Course;

public interface ConfigService {
	
   IndexPic getIndexPicById(Integer id);
	
   void updateIndexPic(IndexPic ip);
	
   void saveIndexPic(IndexPic ip);
   
   void deleteIndexPic(IndexPic ip);
   
   void addIndexPic(IndexPic ip);
   
   PageList getPageList(PageList pagelist);
   
   List<IndexPic> GetUseableIndexPic();
}
