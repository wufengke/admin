package com.cyou.base.service;

import java.util.List;

import com.cyou.base.bean.City;
import com.cyou.base.bean.Province;

public interface ProvinceCityService{

	List<Province> getProvinceList();

	List<City> getCityList();

	List<City> getCityListByProvinceId(Integer province);

	
}
