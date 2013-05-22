package com.taxi.admin.common;

import java.net.URISyntaxException;

import com.cplatform.config.XMLConfig;

/**
 * Title. xml配置文件解析工具类<br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2011-11-15 上午10:01:07
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: guodong@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
public class ConfigDateHandler extends XMLConfig {

	/**
	 * 配置文件路径及名称
	 */
	static final String CONFIG_FILENAME = ConfigDateHandler.class.getResource("/").getPath() + "config_data.xml";

	// 对象实例.
	private static ConfigDateHandler instance;

	/**
	 * 获取对象实例
	 * <p>
	 * 如果init方法在服务启动时没有加载则调用此方法会重新建立对象
	 * 
	 * @return
	 */
	public static ConfigDateHandler getInstance() {
		if (instance == null) {
			try {
				init();
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return instance;
	}

	// 启动时初始化对象实例.
	public synchronized static void init() throws Exception {
		if (instance != null) {
			return;
		}
		instance = new ConfigDateHandler();
	}

	// 对象创建.
	ConfigDateHandler() throws Exception {
		super("config", CONFIG_FILENAME, true);
	}

	// 获取程序运行间隔时间.
	public int getSleepTime() {
		return getInteger("systime.re_sleep_time", 10);
	}

	// 获取得到超时时间.
	public int getTimeOut() {
		return getInteger("re_time_out", 5);
	}
	
	/**
	 * 获取当前web应用发布后的根路径
	 * 
	 * @return
	 */
	public static String getWebRootPath() {
		String webRootPath = "";
		try {
			String classPath = ConfigDateHandler.class.getClassLoader().getResource("").toURI().getPath();
			webRootPath = classPath;
		}
		catch (URISyntaxException e) {
			e.printStackTrace();
		}

		return webRootPath;
	}
}