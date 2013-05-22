package com.taxi.admin.cache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.apache.log4j.Logger;


/**
 * Title.缓存上下文通用管理器 <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2011-6-29 上午09:21:04
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: guodong@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
public class ContextCacheManager {

	private static Logger log = Logger.getLogger("ContextCacheManager.class");

	/**
	 * 创建本地缓存管理器上下文实例
	 */
	private static ContextCacheManager ins = new ContextCacheManager();

	public synchronized static ContextCacheManager instance() {
		if (ins == null) {
			ins = new ContextCacheManager();
		}
		return ins;
	}

	/**
	 * 初始化缓存管理器
	 */
	private CacheManager cacheManager = null;

	/**
	 * 缓存配置文件加载
	 */
	private ContextCacheManager() {
		
			// 线上服务使用默认配置文件
			cacheManager = CacheManager.create();

		// 注册一个JVM关闭钩子。 用来当vm关闭时将缓存放入磁盘
		// System.setProperty("net.sf.ehcache.enableShutdownHook", "true");
	}

	/**
	 * 通过Key清理缓存
	 * 
	 * @param cacheName
	 * @void clearCache(ContextCacheManager)
	 */
	public synchronized void clearCache(String cacheName) {
		Cache cache = cacheManager.getCache(cacheName);
		if (cache != null) {
			cache.removeAll();
		}
	}

	/**
	 * 命中缓存，如果没有则新建
	 * 
	 * @param cacheName
	 * @return Cache
	 */
	public synchronized Cache fetchCache(String cacheName) {
		Cache cache = cacheManager.getCache(cacheName);
		if (cache == null) {
			cacheManager.addCache(cacheName);
			cache = cacheManager.getCache(cacheName);
		}

		return cache;
	}

	/**
	 *根据缓存键获取缓存对象
	 * @param cacheName 缓存的名称（对应ehcache.xml中配置的name）
	 * @param key 缓存设计的键
	 * @return Object 需对应放入缓存的类型做强制转换
	 */
	public synchronized Object getObject(String cacheName, String key) {
		Element element = fetchCache(cacheName).get(key);
		if (element == null) {
			return null;
		} else {
			return element.getObjectValue();
		}
	}


	/**
	 * 首先将旧的数据置空
	 * <P>
	 * 然后加入新的数据
	 * 
	 * @param cacheName 缓存的名称（对应ehcache.xml中配置的name）
	 * @param key 缓存设计的键
	 * @param value 缓存键对应的值
	 * @return 返回本实例
	 */
	public synchronized ContextCacheManager putObject(String cacheName, String key, Object value) {
		Object oldValue = getObject(cacheName, key);
		if (oldValue != null) {
			this.fetchCache(cacheName).remove(key);
		}
		Element element = new Element(key, value);
		this.fetchCache(cacheName).put(element);
		return this;
	}
	/**
	 * 
	 * @param cacheName  缓存的名称（对应ehcache.xml中配置的name）
	 * @param key 缓存键
	 * @param value 缓存值
	 * @return 返回本实例
	 */
	public ContextCacheManager putObject(String cacheName, Long key, Object value) {
		return putObject(cacheName, String.valueOf(key), value);
	}
	/**
	 * 获取所有在该cacheManager管理器中的缓存名称
	 * @return 缓存名称组成的数组
	 */
	public String[] getCacheNames() {
		return cacheManager.getCacheNames();
	}

	public static void printStatic(){
		String[] cacheNames = instance().getCacheNames();
		for (int i = 0; i < cacheNames.length; i++) {
			Cache cache  = instance().fetchCache(cacheNames[i]);
			
			System.out.println(cache.getStatistics().toString());
		}
	}


}
