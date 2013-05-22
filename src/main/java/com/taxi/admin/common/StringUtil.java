package com.taxi.admin.common;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串处理公用类. <br>
 * 类详细说明.
 * <p>
 * 修改历史: Jan 15, 2010 9:17:42 AM baowr@c-platform.com <br>
 * 修改说明: 代码规范修改<br>
 * <p>
 * Copyright: Copyright (c) Jan 15, 2006 9:17:28 AM
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author XXX@c-platform.com
 * @version 1.0.0
 */
public class StringUtil {

    /**
     * 修改字符串的编码集
     * 
     * @param str
     *            字符串
     * @param fromCharset
     *            原编码集
     * @param toCharset
     *            目的编码集
     * @return 转换后的字符串
     */
    public static String changeCharset(String str, String fromCharset, String toCharset) {
        if (isEmpty(str)) {
            return str;
        }

        String src, dest;
        src = str;

        try {
            dest = new String(src.getBytes(fromCharset), toCharset);

            if (dest.length() == src.length()) {
                dest = src;
            }
        }
        catch (UnsupportedEncodingException e) {
            return src;
        }

        return dest;
    }

    /**
     * 获取日期中的天数，格式为yyyy-mm-dd
     * 
     * @param obj
     *            要获取的日期
     * @return 返回int
     */
    public static int convertToDay(Object obj) {

        String str = getString(obj);
        int loc = str.lastIndexOf("-");
        String tmpStr = str.substring(loc + 1);
        return getInt(tmpStr, 0);
    }

    /**
     * 根据sep将字符串转换成字符串集合
     * 
     * @param hs
     *            字符串集合
     * @param str
     *            字符串
     * @param sep
     *            分割字符串
     */
    public static void covertToSet(Set<String> hs, String str, String sep) {
        if (str == null) {
            return;
        }
        if (hs == null) {
            return;
        }

        String[] arr = str.split(sep);
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            hs.add(arr[i]);
        }
    }

    /**
     * 将数组转化为sep分割的字符串
     * 
     * @param arr
     *            字符串数组
     * @param sep
     *            分割字符串
     * @return 字符串
     */
    public static String covertToString(String[] arr, String sep) {
        if (arr == null) {
            return "";
        }
        if (arr.length == 0) {
            return "";
        }
        if (arr.length == 1) {
            return arr[0];
        }
        if (arr.length > 1) {
            StringBuffer strBuff = new StringBuffer();
            for (int i = 0; i < arr.length; i++) {
                strBuff.append(arr[i]);
                if (i != (arr.length - 1)) {
                    strBuff.append(sep);
                }
            }
            return strBuff.toString();
        }
        return "";
    }

    /**
     * 返回0~limit长度的截取字符串.
     * 
     * @History: Apr 20, 2010 9:41:42 AM majk@c-platform.com <br>
     *           把参数s修改为str <br>
     *           把变量x修改为tmpLoc <br>
     * @param str
     *            输入的字符串
     * @param limit
     *            字符串显示的最大长度
     * @return 截取后的字符串
     */
    public static String cutString(String str, int limit) {
        if (str == null || str.length() == 0) {
            return "";
        }

        if (str.length() < limit / 2 || limit < 1) {
            return str;
        }

        char c;
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            c = str.charAt(i);
            if (c > 128 || c < 0) {
                count++;
            }

            count++;

            if (count >= limit) {
                // System.out.println(count);
                int tmpLoc = count % 2 == 0 ? ++i : i;
                return str.substring(0, tmpLoc) + "…";
            }
        }

        return str;
    }

    /**
     * 判断两个字符串是否相等
     * 
     * @History: Apr 20, 2010 9:44:07 AM majk@c-platform.com <br>
     *           把参数str1修改为strSource <br>
     *           把参数str2修改为strTarget <br>
     * @param strSource
     *            字符串1
     * @param strTarget
     *            字符串2
     * @return true：相等；false：不等
     */
    public static boolean equal(String strSource, String strTarget) {
        if (strSource == null) {
            return strTarget == null;
        }

        return strSource.equals(strTarget);
    }

    /**
     * 将fileName中最后一个'/'之后的内容编码.
     * 
     * @History: Apr 20, 2010 1:08:51 PM majk@c-platform.com <br>
     *           对废弃URLEncoder.encode()进行修改 <br>
     * @param fileName
     *            文件名编码
     * @throws UnsupportedEncodingException
     */
    public static String fileUrlEncode(String fileName) {
        if (isEmpty(fileName)) {
            return fileName;
        }
        /**
         * fileName中最后一个'/'之后的内容编码后的字符串
         */
        String tempEncoderStr = null;
        try {
            tempEncoderStr = URLEncoder.encode(fileName.substring(fileName.lastIndexOf("/") + 1), "GBK");
        }
        catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block

        }
        if (fileName.indexOf("/") > -1) {
            // return fileName.substring(0, fileName.lastIndexOf("/") + 1) +
            // URLEncoder.encode(fileName.substring(fileName.lastIndexOf("/") +
            // 1));
            return fileName.substring(0, fileName.lastIndexOf("/") + 1) + tempEncoderStr;
        } else {
            return fileName;
        }
    }

    /**
     * GBK转码为iso-8859-1
     * 
     * @param change
     *            字符串
     * @throws UnsupportedEncodingException
     *             UnsupportedEncodingException
     * @return String
     */
    public static String gb2iso(String change) throws UnsupportedEncodingException {
        if (change == null) {
            change = "";
        }
        try {
            String result = new String(change.getBytes("GBK"), "iso-8859-1");
            if (result.length() == change.length()) {
                return change;
            } else {
                return result;
            }
        }
        catch (UnsupportedEncodingException ex) {
            return "";
        }
    }

    /**
     * 补0函数
     * 
     * @History: Apr 20, 2010 9:46:18 AM majk@c-platform.com <br>
     *           把参数it修改为intAppend <br>
     * @param intAppend
     *            数字
     * @return String 补0后的字符串
     */
    public static String get00String(int intAppend) {
        if (intAppend >= 10) {
            return String.valueOf(intAppend);
        } else {
            return "0" + String.valueOf(intAppend);
        }
    }

    /**
     * 将字符串转换为Double，如果字符串为空或者不是数字格式返回默认Double
     * 
     * @param num
     *            字符串
     * @param def
     *            默认Double
     * @return 转换后的字符串
     */
    public static BigDecimal getBigDecimal(String num, BigDecimal def) {
        if (isEmpty(num)) {
            return def;
        }

        BigDecimal ret = def;
        try {
            ret = new BigDecimal(num);

        }
        catch (NumberFormatException ex) {
            // ignore
        }

        return ret;
    }

    /**
     * 将字符串转换成布尔型
     * 
     * @History: Apr 20, 2010 9:47:24 AM majk@c-platform.com <br>
     *           把参数def修改为isDef <br>
     * @param str
     *            字符串
     * @param isDef
     *            默认的布尔型
     * @return 布尔型
     */
    public static Boolean getBoolean(String str, Boolean isDef) {
        if (isEmpty(str)) {
            return isDef;
        }
        str = str.toLowerCase();
        if (str.equals("true") || str.equals("yes") || str.equals("1")) {
            return Boolean.TRUE;
        } else if (str.equals("false") || str.equals("no") || str.equals("0")) {
            return Boolean.FALSE;
        } else {
            return isDef;
        }
    }

    /**
     * 将字符串转换为Double，如果字符串为空或者不是数字格式返回默认Double
     * 
     * @param num
     *            字符串
     * @param def
     *            默认Double
     * @return 转换后的字符串
     */
    public static Double getDouble(String num, Double def) {
        if (isEmpty(num)) {
            return def;
        }

        Double ret = def;
        try {
            ret = Double.valueOf(num);

        }
        catch (NumberFormatException ex) {
            // ignore
        }

        return ret;
    }

    /**
     * 将object类弄转换为int
     * 
     * @param obj
     *            要转换的object对象
     * @param def
     *            出错时默认值
     * @return 返回int
     */
    public static int getInt(Object obj, Integer def) {
        String tmpStr = getString(obj);
        return getInt(tmpStr, def);
    }

    /**
     * 将字符串转换为Integer，如果字符串为空或者不是数字格式返回默认Integer
     * 
     * @param num
     *            字符串
     * @param def
     *            默认Integer
     * @return 转换后的字符串
     */
    public static Integer getInt(String num, Integer def) {
        if (isEmpty(num)) {
            return def;
        }

        Integer ret = def;
        try {
            ret = Integer.valueOf(num);
        }
        catch (NumberFormatException ex) {
            // ignore
        }

        return ret;
    }

    /**
     * 将字符串转换为Long，如果字符串为空或者不是数字格式返回默认Long
     * 
     * @param num
     *            字符串
     * @param def
     *            默认Long
     * @return 转换后的字符串
     */
    public static Long getLong(String num, Long def) {
        if (isEmpty(num)) {
            return def;
        }

        Long ret = def;
        try {
            ret = Long.valueOf(num);
        }
        catch (NumberFormatException ex) {
            // ignore
        }

        return ret;
    }

    /**
     * 打印异常堆栈信息
     * 
     * @param ex
     *            异常
     * @return 堆栈信息
     */
    public static String getStackTrace(Throwable ex) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        ex.printStackTrace(pw);

        return sw.toString();
    }

    /**
     * 把NULL值转化为空("")
     * 
     * @param obj
     *            传入一个object
     * @return String strValue=null,return"";str=str
     */
    public static String getString(Object obj) {
        try {
            if (obj == null) {
                return "";
            }
            return obj.toString();
        }
        catch (Exception ex) {
            return "";
        }
    }

    /**
     * 把NULL值转化为空("")
     * 
     * @param strValue
     *            String 传入一个字符串
     * @return String strValue=null,return"";str=str
     */

    public static String getString(String strValue) {
        try {
            if (strValue == null) {
                return "";
            }
            return strValue;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

    /**
     * 如果字符串为空，则返回默认string
     * 
     * @param str
     *            字符串
     * @param def
     *            默认string
     * @return string
     */
    public static String getString(String str, String def) {
        if (isEmpty(str)) {
            return def;
        } else {
            return str;
        }
    }

    /**
     * 把NULL值转化为空("")
     * 
     * @param strValue
     *            String 传入一个字符串
     * @return String strValue=null,return"0";str=str
     */
    public static String getStringNum(String strValue) {
        try {
            if (strValue == null || strValue.equals("")) {
                return "0";
            }
            return strValue;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return "0";
        }
    }

    public static String getSystemParameter(String str) {
        Properties props = System.getProperties();
        String result = props.getProperty(str);
        return result;
    }

    /**
     * 将html标签代码转换
     * 
     * @param html
     *            Input String
     * @return escaped string
     */
    public static String htmlEncode(String html) {
        if (StringUtil.isEmpty(html)) {
            return "";
        }

        return html.replaceAll("&", "&amp;").replaceAll("\"", "&quot;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
    }

    /**
     * 将html标签代码转换
     * 
     * @param html
     *            字符串
     * @param def
     *            默认值
     * @return 转换后的string
     */
    public static String htmlEncode(String html, String def) {
        if (StringUtil.isEmpty(html)) {
            return def;
        } else {
            return htmlEncode(html);
        }
    }

    /**
     * 将html内容标签代码转换
     * 
     * @param str
     *            字符串
     * @return 转换后的string
     */
    public static String htmlText(String str) {
        if (StringUtil.isEmpty(str)) {
            return "";
        }

        return str.replaceAll("&", "&amp;").replaceAll("\"", "&quot;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\r\n", "<br>")
                .replaceAll("\n", "<br>");

    }

    /**
     * 将html内容标签代码转换
     * 
     * @param str
     *            字符串
     * @param def
     *            默认字符串
     * @return 转换后的string
     */
    public static String htmlText(String str, String def) {
        if (StringUtil.isEmpty(str)) {
            return def;
        } else {
            return htmlText(str);
        }
    }

    /**
     * 判断对象是否为空
     * 
     * @param obj
     *            对象
     * @return true：为空；false： 不为空
     */
    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        String str = String.valueOf(obj);
        return str == null || str.trim().length() == 0;

    }

    /**
     * 判断字符串是否为空
     * 
     * @param str
     *            字符串
     * @return if empty, then true, else false
     */
    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;

    }

    /**
     * 判断字符串是否为Integer
     * 
     * @History: Apr 20, 2010 9:23:41 AM majk@c-platform.com <br>
     *           把参数s修改为str <br>
     * @param str
     *            字符串
     * @return If integer, then true, else false.
     */
    public static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }

        char c;
        for (int i = 0; i < str.length(); i++) {
            c = str.charAt(i);

            if (c < '0' || c > '9') {
                return false;
            }
        }

        return true;
    }

    /**
     * 判断字符串是否为合法名（以字母开头，仅包含字母，数字和下划线）
     * 
     * @param str
     *            字符串
     * @return If legal, then true, else, false.
     */
    public static boolean isLegalName(String str) {
        if (StringUtil.isEmpty(str)) {
            return false;
        }

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (i == 0 && c >= '0' && c <= '9') {
                return false;
            }

            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9') || c == '_')) {
                return false;
            }
        }

        return true;
    }

    /**
     * 判断字符串是否是纯数字
     * 
     * @param str
     *            字符串
     * @return If isNumric, then true, else, false
     */
    public static boolean isNumric(String str) {
        if ((str == null) || str.equals("")) {
            return false;
        }
        boolean flag = true;
        String match = "0123456789";
        int len = str.length();
        for (int i = 0; i < len; i++) {
            char s = str.charAt(i);
            if (match.indexOf(s) >= 0) {
                continue;
            } else {
                flag = false;
                break;
            }
        }
        return flag;
    }

    /**
     * iso-8859-1转码为GBK
     * 
     * @History: Apr 20, 2010 9:24:52 AM majk@c-platform.com <br>
     *           把参数s修改为str <br>
     * @param str
     *            字符串
     * @throws UnsupportedEncodingException
     *             UnsupportedEncodingException
     * @return String
     */
    public static String iso2gb(String str) throws UnsupportedEncodingException {
        if (str == null) {
            str = "";
        }
        try {
            String result = new String(str.getBytes("iso-8859-1"), "GBK");
            if (result.length() == str.length()) {
                return str;
            } else {
                return result;
            }
        }
        catch (UnsupportedEncodingException ex) {
            return "";
        }
    }

    /**
     * 对数组中的字符串以逗号进行组合
     * 
     * @History: Apr 20, 2010 2:50:33 PM majk@c-platform.com <br>
     *           添加方法注释
     * @param source
     *            字符串数组
     * @return 字符串
     */
    public static String join(String[] source) {
        return join(source, ",");
    }

    /**
     * 组合字符串数组为字符串
     * 
     * @History: Apr 20, 2010 9:26:43 AM majk@c-platform.com <br>
     *           把参数s修改为strSource <br>
     *           把参数str修改为strSplit <br>
     * @param strSource
     *            字符串数组
     * @param strSplit
     *            间隔字符串
     * @return 字符串
     */
    public static String join(String[] strSource, String strSplit) {
        if (strSource == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < strSource.length; i++) {
            sb.append(strSource[i]);
            sb.append(strSplit);
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    /**
     * 将NULL转换为空串
     * 
     * @param str
     *            字符串
     * @return String 转换后的字符串
     */
    public static String nullToEmpty(String str) {
        return str == null ? "" : str;
    }

    /**
     * 日期型参数去"-"格式化为字符串(如 "2005-10-12" 格式化为"20051012")
     * 
     * @History: Apr 20, 2010 9:27:55 AM majk@c-platform.com <br>
     *           把参数s修改为strTime <br>
     * @param strTime
     *            需要格式化的字符串参数(如 "2005-01-12")
     * @return 格式化后的日期
     */
    public static String pdateFormateToStr(String strTime) {
        if (strTime != null && strTime.length() == 10) {
            return strTime.replaceAll("-", "");
        }
        return strTime;
    }

    /**
     * 不论大小写的替换字符串
     * 
     * @param src
     *            字符串
     * @param pattern
     *            要替换的字符串
     * @param rep
     *            替换成的目标字符串
     * @return 替换后的字符串
     */
    public static String replaceIgnoreCase(String src, String pattern, String rep) {
        if (src == null) {
            return null;
        }

        Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(src);
        StringBuffer sb = new StringBuffer();

        while (m.find()) {
            m.appendReplacement(sb, rep);
        }

        m.appendTail(sb);

        return sb.toString();
    }

    /**
     * 替换字符串中的一些半角符号，如：* < >
     * 
     * @param str
     *            字符串
     * @return String
     */
    public static String replaceStrs(String str) {
        if (str == null) {
            return "";
        }
        String[] s = new String[14];
        s[0] = "#";
        s[1] = "$";
        s[2] = "%";
        s[3] = "^";
        s[4] = "&";
        s[5] = "*";
        s[6] = "\"";
        s[7] = "'";
        s[8] = "<";
        s[9] = ">";
        s[10] = ",";
        s[11] = "!";
        s[12] = ";";
        s[13] = "?";
        String[] s2 = new String[14];
        s2[0] = "＃";
        s2[1] = "￥";
        s2[2] = "％";
        s2[3] = "＾";
        s2[4] = "＆";
        s2[5] = "＊";
        s2[6] = "＂";
        s2[7] = "＂";
        s2[8] = "＜";
        s2[9] = "＞";
        s2[10] = "，";
        s2[11] = "！";
        s2[12] = "；";
        s2[13] = "？";
        int temp = 0;
        for (int i = 0; i < 14; i++) {
            temp = str.indexOf(s[i]);
            if (temp >= 0) {
                str = strReplace(str, s[i], s2[i]);
            }
        }
        return str;
    }

    /**
     * 分割字符串为数组
     * 
     * @History: Apr 20, 2010 9:31:54 AM majk@c-platform.com <br>
     *           把参数1修改strSource <br>
     *           把参数2修改为strSplit <br>
     *           把变量fff修改为tmpStrSplit <br>
     * @param strSource
     *            欲分割的字符串
     * @param strSplit
     *            分割标准
     * @return 字符串数组
     */
    public static String[] split(String strSource, String strSplit) {

        String str = "1|1|1";

        int k = 0;
        String tmpStrSource = "";
        tmpStrSource = strSource;
        if (tmpStrSource == null || tmpStrSource == "") {
            tmpStrSource = str;
        }
        String tmpStrSplit = strSplit;
        if (tmpStrSplit == null || tmpStrSplit == "") {
            tmpStrSplit = "|";
        }
        int start = 0;
        int m = 0;
        for (int n = m + 1; m < n; n++) {
            if (tmpStrSource.indexOf(tmpStrSplit, start) == -1) {
                break;
            }
            start = tmpStrSource.indexOf(tmpStrSplit, start) + 1;
            k++;
            m++;
        }

        String result[] = new String[k + 1];
        start = 0;
        int end = 0;
        for (int yyy = 0; yyy <= k; yyy++) {
            if (tmpStrSource.indexOf(tmpStrSplit, start) == -1) {
                result[yyy] = tmpStrSource.substring(end, tmpStrSource.length());
            } else {
                start = tmpStrSource.indexOf(tmpStrSplit, start);
                result[yyy] = tmpStrSource.substring(end, start);
                end = start + tmpStrSplit.length();
                start = end;
            }
        }
        return result;
    }

    /**
     * 字符串的长度, non-ASCII 是双倍长度.
     * 
     * @History: Apr 20, 2010 9:32:32 AM majk@c-platform.com <br>
     *           把参数s修改为str <br>
     * @param str
     *            字符串
     * @return 长度
     */
    public static int stringLength(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }

        char c;
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            c = str.charAt(i);
            if (c > 128 || c < 0) {
                count++;
            }

            count++;
        }
        return count;
    }

    /**
     * 转化字符{strFrom} 说明:把String型转化为int型
     * 
     * @param strFrom
     *            String 转换前字符
     * @return int
     */

    public static int StringtoInt(String strFrom) {
        if (strFrom == null || strFrom.equals("")) {
            return 0;
        }
        try {
            return Integer.parseInt(strFrom);
        }
        catch (Exception ex) {
            return 0;
        }
    }

    /**
     * 转化字符{strFrom} 说明:把String型转化为long型
     * 
     * @param strFrom
     *            String 转换前字符
     * @return long
     */

    public static long StringtoLong(String strFrom) {

        if (strFrom == null || strFrom.equals("")) {
            return 0;
        }
        try {
            return Long.parseLong(strFrom);
        }
        catch (Exception ex) {
            return 0;
        }
    }

    /**
     * 字符串替换函数
     * 
     * @History: Apr 20, 2010 9:36:05 AM majk@c-platform.com <br>
     *           把参数1修改为strSource <br>
     *           把参数2修改为strReplace <br>
     *           把参数3修改为strNew <br>
     *           把变量sF修改为tmpStartStr <br>
     *           把变量sH修改为tmpEndStr <br>
     * @param strSource
     *            String 原来的字符串
     * @param strReplace
     *            String 要替换掉的字符串
     * @param strNew
     *            String 新的字符串
     * @return String 替换后的结果
     */
    public static String strReplace(String strSource, String strReplace, String strNew) {
        /**
         * 查找要替换掉的字符串位置
         */
        int tmpReplaceLocation;
        /**
         * 要替换掉字符串前的部分字符串
         */
        String tmpStartStr = "";
        /**
         * 要替换字符串后的部分字符串
         */
        String tmpEndStr = "";
        // 如果新串中包括旧串
        if (strReplace.indexOf(strNew) != -1) {
            return strSource;
        }

        if (strSource == null || strReplace == null || strNew == null) {
            return strSource;
        }
        tmpReplaceLocation = strSource.indexOf(strReplace);
        while (tmpReplaceLocation != -1) {
            tmpStartStr = strSource.substring(0, tmpReplaceLocation);
            tmpEndStr = strSource.substring(tmpReplaceLocation + strReplace.length());
            strSource = tmpStartStr + strNew + tmpEndStr;
            tmpReplaceLocation = strSource.indexOf(strReplace);
        }
        return strSource;
    }

    /**
     * 截取str中len长度的字符串
     * 
     * @param str
     *            字符串
     * @param len
     *            长度
     * @return 截取后的字符串
     */
    public static String substr(String str, int len) {
        if (str == null) {
            return "";
        }
        if (str.length() > len) {
            return str.substring(0, len);
        }
        return str;
    }

    /**
     * 转半角的函数(DBC case) 任意字符串 半角字符串 全角空格为12288，半角空格为32
     * 其他字符半角(33-126)与全角(65281-65374)的对应关系是：均相差65248
     * 
     * @param input
     *            字符串
     * @return 转换后的字符串
     */
    public static String toDBC(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375) {
                c[i] = (char) (c[i] - 65248);
            }
        }
        return new String(c);
    }

    /**
     * 转全角的函数(SBC case) 任意字符串 全角字符串 全角空格为12288，半角空格为32
     * 其他字符半角(33-126)与全角(65281-65374)的对应关系是：均相差65248
     * 
     * @param input
     *            字符串
     * @return 转换后的字符串
     */
    public static String toSBC(String input) {
        // 半角转全角：
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 32) {
                c[i] = (char) 12288;
                continue;
            }
            if (c[i] < 127) {
                c[i] = (char) (c[i] + 65248);
            }
        }
        return new String(c);
    }

    /**
     * 过滤SQL特殊字符
     * 
     * @param str
     *            字符串
     * @return 过滤后的字符串
     */
    public static String tranSpecialChar(String str) {
        str = getString(str);
        str = str.replaceAll("\\'", toSBC("'"));
        str = str.replaceAll("\"", toSBC("\""));
        str = str.replaceAll("%", toSBC("%"));
        str = str.replaceAll("@", toSBC("@"));
        str = str.replaceAll("\\?", toSBC("?"));

        return str;
    }
    
    /**
     * 对escape转码后的字符串解码
     * 
     * @param src 
     * 			需要解码的字符串
     * @return 解码后的字符串
     */
    public static String unescape(String src) {
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length());
		int lastPos = 0, pos = 0;
		char ch;
		while (lastPos < src.length()) {
			pos = src.indexOf("%", lastPos);
			if (pos == lastPos) {
				if (src.charAt(pos + 1) == 'u') {
					ch = (char) Integer.parseInt(src
							.substring(pos + 2, pos + 6), 16);
					tmp.append(ch);
					lastPos = pos + 6;
				} else {
					ch = (char) Integer.parseInt(src
							.substring(pos + 1, pos + 3), 16);
					tmp.append(ch);
					lastPos = pos + 3;
				}
			} else {
				if (pos == -1) {
					tmp.append(src.substring(lastPos));
					lastPos = src.length();
				} else {
					tmp.append(src.substring(lastPos, pos));
					lastPos = pos;
				}
			}
		}
		return tmp.toString();
	}

}
