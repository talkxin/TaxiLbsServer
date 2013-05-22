package com.taxi.admin.email;



/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2010-7-13 下午03:28:33
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: liqi@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
public interface SendEmailBOable {

	/**
	 * 发送邮件
	 * 
	 * @param emailModel
	 *            email模型
	 * @throws Exception
	 *             抛出信息异常
	 */
	public void sendEmail(EmailModel emailModel) throws Exception;

	/**
	 * 发送邮件可以无附件
	 * 
	 * @param emailModel
	 *            email模型
	 * @throws Exception
	 *             抛出信息异常
	 */
	public void sendEmailNoAttachFile(EmailModel emailModel) throws Exception;
}
