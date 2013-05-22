package com.taxi.admin.email;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.taxi.admin.common.StringUtil;
import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2010-7-13 下午03:28:27
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: liqi@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
public class SendEmailBOImpl implements SendEmailBOable {

	/**
	 * 测试代码，发布时删除
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EmailModel sendInfo = new EmailModel();

		sendInfo.setTo("602910645@qq.com;");
		sendInfo.setTitle("spring email 测试");
		sendInfo.setContent("测测");
		String attachPath = "C:";
		String[] attachFileName = { "数据20100625.xls" };
		sendInfo.setAttachFileName(attachFileName);
		sendInfo.setAttachPath(attachPath);
		ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[] { "applicationContext-db.xml" });
		SendEmailBOImpl emailBO = (SendEmailBOImpl) ctx.getBean("sendEmail");
		try {
			emailBO.sendEmail(sendInfo);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		emailBO.getLogger().info("邮件传送OK..");
	}

	/**
	 * email发送类
	 */
	private JavaMailSenderImpl javaMail;

	/**
	 * 日志信息类
	 */
	private final Log logger = LogFactory.getLog(SendEmailBOImpl.class);

	/**
	 * 邮件信息设置类
	 */
	private MimeMessageHelper messageHelper;

	/**
	 * 
	 */
	public SendEmailBOImpl() {
		// TODO Auto-generated constructor stub
	}

	public JavaMailSenderImpl getJavaMail() {
		return javaMail;
	}

	public Log getLogger() {
		return logger;
	}

	public MimeMessageHelper getMessageHelper() {
		return messageHelper;
	}

	@Override
	public void sendEmail(EmailModel emailModel) throws Exception {
		if (null == emailModel.getAttachFileName()) {
			return;
		}
		/**
		 * 获得发送人
		 */
		String tmpUserName = emailModel.getFrom();
		/**
		 * 判断邮件用户名是否为空，为空取spring配置文件中的默认用户名
		 */
		if (tmpUserName == null || "".equalsIgnoreCase(tmpUserName)) {
			emailModel.setFrom(javaMail.getUsername());
		}

		/**
		 * 创建一个MimeMessage对象
		 */
		MimeMessage mimeMessage = javaMail.createMimeMessage();
		/**
		 * 创建一个MimeMessageHelper对象
		 */
		MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "GBK");
		// MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,
		// true);

		/**
		 * 邮件群发人分析
		 */
		String tmpToUser = emailModel.getTo();
		/**
		 * 群发接收人
		 */
		String[] arrToUser = tmpToUser.split(";");
		/**
		 * 设置发送人
		 */
		messageHelper.setFrom(emailModel.getFrom());
		/**
		 * 设置接收人
		 */

		/**
		 * 设置标题
		 */
		messageHelper.setSubject(emailModel.getTitle());
		/**
		 * 设置内容
		 */
		messageHelper.setText(emailModel.getContent(), true);
		// messageHelper.setText(emailModel.getContent());

		/**
		 * 附件名，类型为数组
		 */
		String[] attachFileName = emailModel.getAttachFileName();
		/**
		 * 附件路径
		 */
		String attachPath = emailModel.getAttachPath();
		/**
		 * 操作系统分隔符
		 */
		String systemSeparator = StringUtil.getSystemParameter("file.separator");
		try {
			/**
			 * 判断是否有附件，没有附件将不会发送附件
			 */
			if (attachFileName != null && ArrayUtils.isNotEmpty(attachFileName)) {
				for (int j = 0; j < attachFileName.length; j++) {
					if (attachFileName[j] == null) {
						throw new Exception("没有生成附件，邮件不发送！");
					}
					messageHelper
					        .addAttachment(MimeUtility.encodeText(attachFileName[j]), new File(attachPath + systemSeparator + attachFileName[j]));
				}
			}
		}
		catch (Exception ex) {
			throw ex;
		}

		try {

			/**
			 * 设置发送人
			 */
			messageHelper.setTo(arrToUser);
			/**
			 * 发送邮件
			 */
			javaMail.send(mimeMessage);
		}
		catch (Exception ex) {
			throw ex;
		}

	}

	public void setJavaMail(JavaMailSenderImpl javaMail) {
		this.javaMail = javaMail;
	}

	public void setMessageHelper(MimeMessageHelper messageHelper) {
		this.messageHelper = messageHelper;
	}

	@Override
	public void sendEmailNoAttachFile(EmailModel emailModel) throws Exception {
		// TODO Auto-generated method stub

		/**
		 * 获得发送人
		 */
		String tmpUserName = emailModel.getFrom();
		/**
		 * 判断邮件用户名是否为空，为空取spring配置文件中的默认用户名
		 */
		if (tmpUserName == null || "".equalsIgnoreCase(tmpUserName)) {
			emailModel.setFrom(javaMail.getUsername());
		}
		/**
		 * 创建一个MimeMessage对象
		 */
		MimeMessage mimeMessage = javaMail.createMimeMessage();
		/**
		 * 创建一个MimeMessageHelper对象
		 */
		MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "GBK");
		// MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,
		// true);

		/**
		 * 邮件群发人分析
		 */
		String tmpToUser = emailModel.getTo();
		/**
		 * 群发接收人
		 */
		String[] arrToUser = tmpToUser.split(";");

		/**
		 * 设置发送人
		 */
		messageHelper.setFrom(emailModel.getFrom());

		/**
		 * 设置标题
		 */
		messageHelper.setSubject(emailModel.getTitle());
		/**
		 * 设置内容
		 */
		messageHelper.setText(emailModel.getContent(), true);
		// messageHelper.setText(emailModel.getContent());

		try {

			/**
			 * 设置发送人
			 */
			messageHelper.setTo(arrToUser);
			/**
			 * 发送邮件
			 */
			javaMail.send(mimeMessage);
		}
		catch (Exception ex) {
			throw ex;
		}
	}
}
