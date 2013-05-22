package com.taxi.admin.common;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2010-8-19 ����08:46:19
 * <p>
 * Company: ��������ʮ�����ּ������޹�˾
 * <p>
 * Author: majk@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
public class StringOperator {

    /** ����������ã���¼��־ */
    private static Log log = LogFactory.getLog(StringOperator.class);

    /**
     * �ָ��ַ�
     * 
     * @param str
     *            ��Ҫ�ָ���ַ�
     * @param reg
     *            ��Ҫ��ָ�����ַ��������ʽ
     * @param flag
     *            ��ִ�Сд��0������֣�1���
     * @return ����string
     */
    public static final String[] findContentStr(String str, String reg, int flag) {

        String[] result = null;
        try {
            Pattern p = null;
            switch (flag) {
                case 0:

                    p = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
                    break;
                case 1:
                    p = Pattern.compile(reg);
                    break;

            }

            result = p.split(str);

        }
        catch (Exception e) {
            log.error("�ָ�ҳ��ָ����?" + reg, e);
        }
        return result;
    }

    /**
     * �����ַ�
     * 
     * @param str
     *            ��Ҫ���ҵ��ַ�
     * @param reg
     *            Ҫ���ҵ����ַ��������ʽ
     * @param flag
     *            ��ִ�Сд��0����֣�1���
     * @return ����string
     */
    public static final String findHrefString(String str, String reg, int flag) {

        String result = "";

        try {
            Pattern p = null;
            switch (flag) {
                case 0:

                    p = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
                    break;
                case 1:
                    p = Pattern.compile(reg);
                    break;

            }

            Matcher m = p.matcher(str);
            boolean isFind = m.find();
            while (isFind) {
                result = m.group();
                isFind = m.find();
            }

        }
        catch (Exception e) {
            log.error("���ҳ��������ַ���?" + reg, e);
        }
        return result;
    }

    /**
     * Ѱ���ַ��а���ٸ�������ʱ���ʽƥ����ַ�
     * 
     * @param strBuff
     *            �ַ�
     * @param reg
     *            ������ʽ,"<input(.+?)>"
     * @param flag
     *            ���Դ�Сд��־��0�����Դ�Сд��1����ִ�Сд
     * @return ����ƥ�������
     */
    public final static int findSubStr(StringBuffer strBuff, String reg, int flag) {
        int count = 0;
        try {
            Pattern p = null;
            switch (flag) {
                case 0:

                    p = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
                    break;
                case 1:
                    p = Pattern.compile(reg);
                    break;

            }

            Matcher m = p.matcher(strBuff);
            boolean isFind = m.find();
            while (isFind) {
                count++;
                isFind = m.find();
            }

        }
        catch (Exception e) {
            log.error("Ѱ�����ַ���?" + reg, e);
        }

        return count;

    }

    /**
     * �������ַ�
     * 
     * @param strBuff
     *            ��Ҫ���ҵ��ַ�
     * @param reg
     *            Ҫ���ҵ����ַ�������ʽ
     * @param flag
     *            ��ִ�Сд��0����֣�1���
     * @return ����list����
     */
    public static final List<String> findSubString(StringBuffer strBuff, String reg, int flag) {

        List<String> result = new ArrayList<String>();

        try {
            Pattern p = null;
            switch (flag) {
                case 0:

                    p = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
                    break;
                case 1:
                    p = Pattern.compile(reg);
                    break;

            }

            Matcher m = p.matcher(strBuff);
            boolean isFind = m.find();
            while (isFind) {
                result.add(m.group());
                isFind = m.find();
            }

        }
        catch (Exception e) {
            log.error("�������ַ���?" + reg, e);
        }
        return result;
    }

    /**
     * ���ҳ������е�����
     * 
     * @param str
     *            �ַ�
     * @param reg
     *            Ҫ���ҵ�������ʽ
     * @param flag
     *            �Ƿ���ִ�Сд��0����֣�1���
     * @return ����string
     */
    public static final String getHrefProperty(String str, String reg, int flag) {
        String result = "";

        try {
            Pattern p = null;
            switch (flag) {
                case 0:

                    p = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
                    break;
                case 1:
                    p = Pattern.compile(reg);
                    break;

            }

            Matcher m = p.matcher(str);
            boolean isFind = m.find();
            if (isFind) {
                result = m.group(1);

            }

        }
        catch (Exception e) {
            log.error("���ҳ��������ַ�����Գ��?" + reg, e);
        }
        return result;
    }

    /**
     * ���html�ĺ�׺
     * 
     * @return ����string
     */
    public static final String getHtmlEndStr() {
        StringBuffer sb = new StringBuffer();
        sb.append("</body>");
        sb.append("</html>");
        return sb.toString();
    }

    /**
     * ����html��ͷ�ַ�
     * 
     * @return
     */
    public static final String getHtmlHeadStr() {
        StringBuffer sb = new StringBuffer();
        sb.append("<%@ page pageEncoding=\"UTF-8\" import=\"java.util.*\"%><?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
        // .append("<%@ page pageEncoding=\"UTF-8\"
        // contentType=\"application/vnd.wap.xhtml+xml;charset=UTF-8\"%><?xml
        // version=\"1.0\" encoding=\"utf-8\"?>");
        sb.append("<%@ taglib prefix=\"s\" uri=\"/struts-tags\"%>\n");
        sb.append("<%@ taglib prefix=\"c\" uri=\"http://java.sun.com/jsp/jstl/core\" %>\n");
        sb.append("<%@ taglib uri=\"/WEB-INF/page.tld\" prefix=\"wapPage\"%>\n");
        sb.append("<c:set var=\"ctx\" value=\"${pageContext.request.contextPath}\"/>\n");
        sb.append("<!DOCTYPE html PUBLIC \"-//WAPFORUM//DTD XHTML Mobile 1.0//EN\" \"http://www.wapforum.org/DTD/xhtml-mobile10.dtd\">\n");
        sb.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">\n");
        // sb.append("<head>");
        // sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html;
        // charset=utf-8\" />");
        // sb.append("<meta http-equiv=\"Cache-Control\"
        // content=\"no-cache\"/>");
        // sb.append("<title>wap��ҳ</title>");
        // sb.append("</head>");
        // sb.append("<body>");
        return sb.toString();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println(StringOperator.getHrefProperty("13520470403", "\\d{3}(\\d*)\\d{4}", 1));

    }

    /**
     * ����������ʽ�滻�ַ�
     * 
     * @param strBuff
     *            ԭ�ַ�
     * @param reg
     *            ������ʽ
     * @param replaceStr
     *            ��Ҫ�滻�����ַ�
     * @param flag
     *            �Ƿ���Դ�Сд
     * @return �����ַ�
     */
    public static final String replaceAllStr(StringBuffer strBuff, String reg, String replaceStr, int flag) {

        StringBuffer result = new StringBuffer(100);
        try {
            Pattern p = null;
            switch (flag) {
                case 0:

                    p = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
                    break;
                case 1:
                    p = Pattern.compile(reg);
                    break;

            }

            Matcher m = p.matcher(strBuff);
            boolean isFind = m.find();
            while (isFind) {

                replaceStr = Matcher.quoteReplacement(replaceStr);
                m.appendReplacement(result, replaceStr);

                isFind = m.find();
            }
            m.appendTail(result);

        }
        catch (Exception e) {
            log.error("�滻�����ַ���?" + reg, e);
        }
        return result.toString();
    }

    /**
     * ����������ʽ�滻�ַ�
     * 
     * @param strBuff
     *            ԭ�ַ�
     * @param reg
     *            ������ʽ
     * @param replaceStr
     *            ��Ҫ�滻�����ַ�
     * @param flag
     *            �Ƿ���Դ�Сд
     * @return �����ַ�
     */
    public static final String replaceFirstStr(StringBuffer strBuff, String reg, String replaceStr, int flag) {

        StringBuffer result = new StringBuffer(100);
        try {
            Pattern p = null;
            switch (flag) {
                case 0:

                    p = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
                    break;
                case 1:
                    p = Pattern.compile(reg);
                    break;

            }

            Matcher m = p.matcher(strBuff);
            boolean isFind = m.find();
            if (isFind) {

                replaceStr = Matcher.quoteReplacement(replaceStr);
                m.appendReplacement(result, replaceStr);

            }
            m.appendTail(result);

        }
        catch (Exception e) {
            log.error("�滻���ַ���?" + reg, e);
        }
        return result.toString();
    }

    /**
     * �Է�ҳ������滻
     * 
     * @param strBuff
     *            ԭ�ַ�
     * @param reg
     *            ������ʽ
     * @param replaceStr
     *            ��Ҫ�滻�����ַ�
     * @param flag
     *            �Ƿ���Դ�Сд
     * @return �����ַ�
     */
    public static final String replacePageProperStr(StringBuffer strBuff, String reg, String replaceStr, int flag) {

        StringBuffer result = new StringBuffer(100);
        try {
            Pattern p = null;
            switch (flag) {
                case 0:

                    p = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
                    break;
                case 1:
                    p = Pattern.compile(reg);
                    break;

            }

            Matcher m = p.matcher(strBuff);
            boolean isFind = m.find();
            while (isFind) {

                replaceStr = Matcher.quoteReplacement(replaceStr);
                /**
                 * ��ҳ��ֻ��replaceStr�滻����һ��,�������""�������滻
                 */
                m.appendReplacement(result, replaceStr);
                replaceStr = "";
                isFind = m.find();
            }
            m.appendTail(result);

        }
        catch (Exception e) {
            log.error("�滻ҳ��ָ����?" + reg, e);
        }
        return result.toString();
    }

    /**
     * ����������ʽ�滻ָ��˳����ַ�
     * 
     * @param strBuff
     *            ԭ�ַ�
     * @param reg
     *            ������ʽ
     * @param replaceStr
     *            ��Ҫ�滻�����ַ�
     * @param flag
     *            �Ƿ���Դ�Сд
     * @param count
     *            �滻�ڼ����ַ�
     * @return �����ַ�
     */
    public static final String replaceCountStr(StringBuffer strBuff, String reg, String replaceStr, int flag, int count) {

        StringBuffer result = new StringBuffer(100);
        try {
            Pattern p = null;
            switch (flag) {
                case 0:

                    p = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
                    break;
                case 1:
                    p = Pattern.compile(reg);
                    break;

            }

            Matcher m = p.matcher(strBuff);
            boolean isFind = m.find();
            int tmpCount = 1;
            while (isFind) {

                if (tmpCount == count) {
                    replaceStr = Matcher.quoteReplacement(replaceStr);
                    m.appendReplacement(result, replaceStr);
                }

                isFind = m.find();

                tmpCount++;

            }
            m.appendTail(result);

        }
        catch (Exception e) {
            log.error("�滻ָ��˳����ַ���?" + reg, e);
        }
        return result.toString();
    }
}
