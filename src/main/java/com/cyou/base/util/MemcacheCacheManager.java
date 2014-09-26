package com.cyou.base.util;

import java.util.Date;

import com.alisoft.xplatform.asf.cache.ICacheManager;
import com.alisoft.xplatform.asf.cache.IMemcachedCache;
import com.alisoft.xplatform.asf.cache.memcached.CacheUtil;
import com.alisoft.xplatform.asf.cache.memcached.MemcachedCacheManager;

public class MemcacheCacheManager {
   
    private static ICacheManager<IMemcachedCache> manager;
    public static IMemcachedCache memcachedCache; 
    
    /**
     * 默认缓存时间
     */
    private Date defaultExpireTime = new Date(2*60*60*1000);
   
//    private static Map<String, IMemcachedCache>   cacheArray; 
	
	private static ICacheManager<IMemcachedCache> newInstanceCache(){ 
			if (manager == null) {
				manager = CacheUtil.getCacheManager(IMemcachedCache.class, MemcachedCacheManager.class.getName());
			    manager.setConfigFile("memcached.xml");
			    manager.start();
			}
			return manager; 
	}
	
	public void init(){
		memcachedCache = MemcacheCacheManager.newInstanceCache().getCache("cyouQPSCache");	
		try{
			if(memcachedCache.stats()==null){
				manager.stop();
				manager = null;
			}	
		}catch (Exception e) {
			manager.stop();
			manager = null;
		}
	}
	
	public void destory(){
		manager.stop();
	}
	
	/**
	 * @param key
	 * @param value
	 */
	public void put(String key,Object value){
		memcachedCache.put(key, value,this.defaultExpireTime);
	}
	
	/**
	 * @param key
	 * @param value
	 */
	public void put(String key,Object value,long expire){
		if(expire==0){
			memcachedCache.put(key, value);//永久缓存,过期时间为0,
		}else{
			memcachedCache.put(key, value,new Date(expire));
		}
		
		
	}
	
	/**
	 * @param key
	 * @return
	 */
	public Object get(String key){
		return memcachedCache.get(key);
	}
	
	/**
	 * @param key
	 */
	public void remove(String key){
		memcachedCache.remove(key);
	}
	
	public void replace(String key,Object object){
		memcachedCache.replace(key, object);
	}
	
    public static void main(String[] args) throws InterruptedException {
    	MemcacheCacheManager cacheManager = new MemcacheCacheManager();
    	cacheManager.init();
    	memcachedCache.put("test", "zhouqizhu",10000);
    	System.out.println(memcachedCache.get("test"));
		Thread.sleep(11000);
		System.out.println(memcachedCache.get("test"));
	}
}
