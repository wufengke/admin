package com.cyou.course.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cyou.core.bean.PageList;
import com.cyou.course.bean.Course;
import com.cyou.course.bean.CourseDetail;
import com.cyou.course.dao.CourseDao;
import com.cyou.course.service.CourseService;
@Service
@Transactional
public class CourseServiceImpl  implements CourseService{

	private static Logger logger = Logger.getLogger(CourseServiceImpl.class);

	@Resource
	private CourseDao courseDao;
	
	@Override
	public List<Course> getOnlineCourseList() {
		try {
			return courseDao.getOnlineCourseList();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ArrayList<Course>();
		}
	}

	@Override
	public CourseDetail getCourseDetailByDetailId(String detailId) {
		try {
			return courseDao.getCourseDetailByDetailId(detailId);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return null;
		}
	}

	@Override
	public Course getCourseByDetailId(String detailId) {
		try {
			return courseDao.getCourseByDetailId(detailId);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return null;
		}
	}

	@Override
	public Course getCourseById(Integer id) {
		try {
			return courseDao.get(Course.class, id);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return null;
		}
	}

	@Override
	public void updateCourse(Course course) {
		try {
			courseDao.update(course);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}

	@Override
	public PageList getPageList(PageList pageList) {
		PageList list = courseDao.search("from Course c order by c.updateTime desc", null, pageList);
		return list;
	}

	@Override
	public void saveCourse(Course course) {
		try {
			courseDao.save(course);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}

	@Override
	public void saveCourseDetail(CourseDetail cd) {
		try {
			courseDao.save(cd);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}

	@Override
	public void deleteCourse(Course c, CourseDetail cd) {
		try {
			if(c != null){
				courseDao.delete(c);
			}
			if(cd != null){
				courseDao.delete(cd);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}

	@Override
	public void saveOrUpdateCourseDetail(CourseDetail cd) {
		try {
			courseDao.saveOrUpdate(cd);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}

	@Override
	public void updateCourseDetail(CourseDetail cd) {
		try {
			courseDao.update(cd);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
}
