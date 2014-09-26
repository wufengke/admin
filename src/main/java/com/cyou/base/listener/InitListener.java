package com.cyou.base.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class InitListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		//启动memcache进程上下文对象，用到的时候直接获取即可
		//MemcacheCacheManager.getCache();
		
	}

}
