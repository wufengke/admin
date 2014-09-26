package com.cyou.base.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cyou.base.bean.City;
import com.cyou.base.bean.Province;
import com.cyou.base.dao.ProvinceCityDao;
import com.cyou.base.service.ProvinceCityService;
@Service
@Transactional
public class ProvinceCityServiceImpl  implements ProvinceCityService{
	
	@Resource
	private ProvinceCityDao provinceCityDao;

	@Override
	public List<Province> getProvinceList() {
		try {
			return provinceCityDao.find("from Province p where p.isvisible =1");
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Province>();
		}
	}

	@Override
	public List<City> getCityList() {
		try {
			return provinceCityDao.find("from City p where p.isvisible =1");
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<City>();
		}
	}

	@Override
	public List<City> getCityListByProvinceId(Integer province) {
		try {
			return provinceCityDao.find("from City p where p.isvisible =1 and p.provinceId=" + province);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<City>();
		}
	}
	
	
}
