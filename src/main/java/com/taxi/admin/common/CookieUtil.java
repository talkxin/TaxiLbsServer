package com.taxi.admin.common;

import java.util.HashMap;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taxi.admin.authenticate.model.RoleVsMenuModel;

/**
 * Title. Cookie帮助类<br>
 * Description.提供根据name获取cookie对象和value方法</p> Cookie的set，get方法
 * <p>
 * Copyright: Copyright (c) 2011-5-23 上午10:01:22
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: guodong@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
public class CookieUtil {

	/** Cookie保留时间 30天 */
	public static final int COOKIE_ALIVE_TIME = 30 * 24 * 60 * 60 * 1000;

	/** 用户登陆名，用来做cookie认证 */
	public static String LOGIN_NAME_COOKIE = "cp_loginName";

	/** 用户ID，用来做cookie认证 */
	public final static String USER_ID_COOKIE = "cp_userId";
	
	/**用户角色，用来坐cookie认证*/
	public final static String USER_ROLEID_COOKIE="cp_roleId";
	
	/**根据菜单id存处用户权限*/
	public final static Map<Long ,RoleVsMenuModel> USER_ROLEVSMENU_COOKIE=new HashMap<Long ,RoleVsMenuModel>();

	/**
	 * 返回指定的Cookie对象，没有则返回null
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param name
	 *            cookie name
	 * @return
	 */
	public static Cookie getCookie(HttpServletRequest request, String name) {
		Cookie cookies[] = request.getCookies();
		if (cookies == null || name == null || name.length() == 0) {
			return null;
		}
		for (int i = 0; i < cookies.length; i++) {
			if (cookies[i].getName().equals(name)) {
				return cookies[i];
			}
		}
		return null;
	}

	/**
	 * 设置Cookie
	 * 
	 * @param request
	 * @param response
	 * @param name
	 * @param value
	 * @param maxAge
	 * @return
	 */
	public static Cookie setCookie(HttpServletRequest request, HttpServletResponse response, String name, String value, int maxAge) {
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(maxAge);
		response.addCookie(cookie);
		return cookie;
	}

	/**
	 * 获取指定cookie的值.没有则返回null
	 * 
	 * @param request
	 * @param name
	 * @return String
	 */
	public static String getCookieValue(HttpServletRequest request, String name) {
		Cookie cookie = getCookie(request, name);
		if (cookie != null) {
			return cookie.getValue();
		}
		return null;
	}
}
