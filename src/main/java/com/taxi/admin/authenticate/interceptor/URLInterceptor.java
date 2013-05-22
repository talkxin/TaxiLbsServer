package com.taxi.admin.authenticate.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsStatics;

import com.taxi.admin.common.ConfigDateHandler;
import com.taxi.admin.common.CookieUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * Title. url拦截 <br>
 * Description. url拦截
 * <p>
 * Copyright: Copyright (c) 2010-9-21 上午10:31:43
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: yekang-cr@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
public class URLInterceptor implements Interceptor {

	@Override
	public void init() {
	}

	/**
	 * @author guodong
	 *         <P>
	 *         更改拦截器session认证，添加cookie校验
	 *         <p>
	 *         当session失效时从cookie获取用户信息放入session<br>
	 *         防止session失效造成重新登陆的问题
	 */
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {

		// ActionContext实例
		ActionContext ctx = invocation.getInvocationContext();
		HttpServletRequest request = (HttpServletRequest) ctx.get(StrutsStatics.HTTP_REQUEST);

		// 检查是否为其他站点访问
		HttpServletResponse response = ServletActionContext.getResponse();
		String referer = request.getHeader("Referer");

		// if (referer != null &&
		// !referer.startsWith("http://211.141.87.93:8083")) {
		if (referer != null && !referer.startsWith(ConfigDateHandler.getInstance().getValue("domainName.domainUrl"))) {
			// response.sendRedirect("http://211.141.87.93:8083/main.do");
			response.sendRedirect(ConfigDateHandler.getInstance().getValue("domainName.domainMainPage"));
		}

		// 用户ID
		Object userId = ctx.getSession().get("userId");
		if (userId == null) {
			// HttpServletRequest request = (HttpServletRequest)
			// ctx.get(StrutsStatics.HTTP_REQUEST);
			userId = CookieUtil.getCookieValue(request, CookieUtil.USER_ID_COOKIE);
			if (userId != null) {
				ctx.getSession().put("userId", userId);
			}
		}
		// 获得登录信息
		Object loginName = ctx.getSession().get("loginName");
		if (loginName == null) {
			// HttpServletRequest request = (HttpServletRequest)
			// ctx.get(StrutsStatics.HTTP_REQUEST);
			loginName = CookieUtil.getCookieValue(request, CookieUtil.LOGIN_NAME_COOKIE);
			if (loginName == null) {
				return Action.LOGIN;
			} else {
				ctx.getSession().put("loginName", loginName);
				return invocation.invoke();
			}
		} else {
			return invocation.invoke();
		}
	}

	@Override
	public void destroy() {
	}
}
