package com.taxi.admin.email;


/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2010-6-2 上午11:22:47
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: majk@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
public class EmailModel {

    private String[] attachFileName;// 附件文件名

    private String attachPath;// 附件路径

    private String content;// 发送内容

    private String from;// 发送人,多个以分号隔开

    private String title;// 标题

    private String to;// 收件人

    /**
	 * 
	 */
    public EmailModel() {
        // TODO Auto-generated constructor stub
    }

    public String[] getAttachFileName() {
        return attachFileName;
    }

    public String getAttachPath() {
        return attachPath;
    }

    public String getContent() {
        return content;
    }

    public String getFrom() {
        return from;
    }

    public String getTitle() {
        return title;
    }

    public String getTo() {
        return to;
    }

    public void setAttachFileName(String[] attachFileName) {
        this.attachFileName = attachFileName;
    }

    public void setAttachPath(String attachPath) {
        this.attachPath = attachPath;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTo(String to) {
        this.to = to;
    }

}
