package com.taxi.admin.cache;



import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.ehcache.Cache;

import org.apache.log4j.Logger;

import com.cplatform.util2.FileTools;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2011-6-30 下午04:54:09
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: guodong@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
public class URLJob {

	private static Logger log = Logger.getLogger("URLCacheFactory.class");

	private static final long ONE_SECOND = 1000L;

	// 最大批量入库条目数1000
	private static final int MAX_SIZE_INDB = 1000;

	/**
	 * 更新开关标志位：默认不更新，当有用户访问时打开此开关<br>
	 * 防止后台程序执行此定时任务
	 */
	public static boolean canBeUpdate = false;

	public static String TEMP_DIR = System.getProperty("java.io.tmpdir");

	public static String URL_JOB_ROOT = "URLJob.txt";


//	/**
//	 * 将缓存中的待更新数据入库
//	 * <p>
//	 * 遍历全部缓存VISITCOUNT_CACHE的key<br>
//	 * 获取待更新的缓存Element放入list并将该条目待更新状态置为false压入缓存<br>
//	 * 直到该条目有用户再次访问时再更新为待更新状态
//	 */
//	@SuppressWarnings("unchecked")
//	public void waitForUpdateToDB() {
//		if (!canBeUpdate) {
//			return;
//		}
//		try {
//			Thread.sleep((long) (Math.random() * 5000));
//			if (!this.canUpdate()) {
//				return;
//			}
//			Cache cache = ContextCacheManager.instance().fetchCache(AdaptationURLUtil.VISITCOUNT_CACHE);
//			Iterator<String> keys = ((List<String>) cache.getKeys()).iterator();
//			while (keys.hasNext()) {
//				String name = keys.next();
//				VisitCountModel visitCount = ContextCacheManager.instance().getVisitCount(name);
//				if (visitCount != null && visitCount.isToUpdate()) {
//					list.add(visitCount);
//					visitCount.setToUpdate(false);
//					ContextCacheManager.instance().putVisitCount(visitCount);
//				}
//			}
//			List<VisitCountModel> sublist = new ArrayList<VisitCountModel>();
//
//			if (!list.isEmpty()) {
//				int count = (int) Math.ceil(list.size() * 1.0 / MAX_SIZE_INDB);
//				int lastCount = count;
//				while (count > 0) {
//					if (count == lastCount) {
//						sublist = list.subList((count - 1) * MAX_SIZE_INDB, list.size());
//					} else {
//						sublist = list.subList((count - 1) * MAX_SIZE_INDB, (count - 1) * MAX_SIZE_INDB + MAX_SIZE_INDB);
//					}
//					URLCacheFactory.updateCache(sublist);
//					count--;
//					sublist.clear();
//				}
//			}
//
//		}
//		catch (InterruptedException e1) {
//			ContextCacheManager.instance().removeAllVisitCountCache();
//			log.error("InterruptedException更新短地址统计数据出错：");
//			e1.printStackTrace();
//		}
//		catch (Exception e) {
//			ContextCacheManager.instance().removeAllVisitCountCache();
//			log.error("Exception更新短地址统计数据出错：");
//			e.printStackTrace();
//		}
//		finally {
//			list.clear();
//		}
//	}

	/**
	 * 先来先得
	 * 
	 * @return
	 */
	private synchronized boolean canUpdate() {
		boolean mark = false;
		String content = "";
		String filePath = this.getClass().getResource("/").getPath();
		try {
			File file = new File(filePath + URL_JOB_ROOT);
			if (!file.exists()) {
				file.createNewFile();
			}
			content = FileTools.readLine(filePath + URL_JOB_ROOT);
			if (content == null) {
				FileTools.writeTxt(TEMP_DIR, filePath + URL_JOB_ROOT);
				mark = true;
			} else {
				if (content.equals(TEMP_DIR)) {
					mark = true;
				}
			}
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mark;
	}

}
