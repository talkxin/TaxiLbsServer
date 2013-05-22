package com.taxi.admin.cache;



import java.util.Map;

import javax.persistence.Cacheable;


import org.apache.log4j.Logger;

import com.taxi.admin.authenticate.model.RoleVsMenuModel;

import com.taxi.admin.cache.ContextCacheManager;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2011-6-29 上午09:31:45
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: guodong@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
public class CacheFactory {

    private static Logger log = Logger.getLogger("CacheFactory.class");

    private static boolean isLoaded = false;

    /**
     * 默认分包大小1000条数据为一包
     */
    public static int pageSize = 1000;

    /**
     * 缓存加载入口方法
     */
    public synchronized static void load() {
        if (!isLoaded) {
            // loadSegmentDate();
           // loadSegmentDateNoSyn();
        }
        isLoaded = true;
    }

    private static boolean isLoading;

    /**
     * 登录后权限缓存set
     * @param codeCache 缓存名称
     * @param key 缓存键（角色id）
     */
    public static void  setRoleVsMenuByKey(String catcheName, String key,Map<Long, RoleVsMenuModel> map) {
    	try {
    		ContextCacheManager.instance().putObject(catcheName, key, map);
        }
        catch (Exception ex) {
	       ex.printStackTrace();
	      log.info("装入缓存失败"+ex);
        }
    	
    }
    /**
     * 获取权限缓存
     * @param codeCache 缓存名称
     * @param key 角色id
     * @return 角色对应的权限map
     */
    @SuppressWarnings( { "unchecked" })
    public static Map<Long, RoleVsMenuModel> getRoleVsMenuByKey(String catcheName,String key){
    	try {
    		Map<Long, RoleVsMenuModel> map = (Map<Long, RoleVsMenuModel>)ContextCacheManager.instance().getObject(catcheName, key);
    		if (null != map) {
    			return map;
    		}
        }
        catch (Exception ex) {
	       ex.printStackTrace();
	       log.info("获取缓存失败"+ex);
        }
    	return null;
    }

}
