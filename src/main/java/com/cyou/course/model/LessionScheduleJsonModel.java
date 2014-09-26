package com.cyou.course.model;

import java.io.Serializable;
import java.util.List;

public class LessionScheduleJsonModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<LessionScheduleModel> schedule;
	
	public List<LessionScheduleModel> getSchedule() {
		return schedule;
	}
	public void setSchedule(List<LessionScheduleModel> schedule) {
		this.schedule = schedule;
	}

}
