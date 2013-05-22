package com.taxi.admin.authenticate.interceptor;


import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2010-10-29 下午05:38:28
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: majk@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
public class EncodeInterceptor implements Interceptor {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		// TODO Auto-generated method stub
		// ActionContext的实例
		ActionContext tmpContext = ActionContext.getContext();
		// HttpServlceRequest实例
		HttpServletRequest request = (HttpServletRequest) tmpContext.get(StrutsStatics.HTTP_REQUEST);
		if (request.getMethod().equalsIgnoreCase("post")) {

			request.setCharacterEncoding("utf-8");
		}
		return arg0.invoke();
	}

}
