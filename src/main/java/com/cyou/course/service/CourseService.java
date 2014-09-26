package com.cyou.course.service;

import java.util.List;

import com.cyou.core.bean.PageList;
import com.cyou.course.bean.Course;
import com.cyou.course.bean.CourseDetail;



public interface CourseService{

	List<Course> getOnlineCourseList();

	CourseDetail getCourseDetailByDetailId(String detailId);

	Course getCourseByDetailId(String detailId);

	Course getCourseById(Integer id);

	void updateCourse(Course course);

	PageList getPageList(PageList pageList);

	void saveCourse(Course course);

	void saveCourseDetail(CourseDetail cd);

	void deleteCourse(Course c, CourseDetail cd);

	void saveOrUpdateCourseDetail(CourseDetail cd);

	void updateCourseDetail(CourseDetail cd);
	
}
