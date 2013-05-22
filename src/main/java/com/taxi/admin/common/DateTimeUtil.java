package com.taxi.admin.common;



import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间日期转换工具类 <br>
 * <p>
 * Copyright: Copyright (c) 2010-5-5 上午11:22:16
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: liuzc@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
public class DateTimeUtil {

	/**
	 * 获取两个日期对象相差天数
	 * 
	 * @param date1
	 *            日期对象
	 * @param date2
	 *            日期对象
	 * @return int 日差值
	 */
	public static int compareDay(java.util.Date date1, java.util.Date date2) {
		if (date1 == null || date2 == null)
			return 0;

		long time1 = date1.getTime();
		long time2 = date2.getTime();

		long margin = time1 - time2;

		/* 转化成天数 */
		int ret = (int) Math.floor((double) margin / (1000 * 60 * 60 * 24));

		return ret;
	}

	/**
	 * 获取两个日期对象相差天数
	 * 
	 * @param date1
	 *            String yyyy-MM-dd
	 * @param date2
	 *            String yyyy-MM-dd
	 * @return int 日差值
	 */
	public static int compareDay(String date1str, String date2str) {
		if (date1str == null || date2str == null)
			return 0;

		java.util.Date date1 = toDate(date1str, "00:00:01");
		java.util.Date date2 = toDate(date2str, "00:00:00");

		return compareDay(date1, date2);
	}

	/*
	 * * 获取两个日期对象相差的小时数 @param date1 日期对象 @param date2 日期对象 @return int 相差小时数
	 */
	public static int compareHour(java.util.Date date1, java.util.Date date2) {
		if (date1 == null || date2 == null)
			return 0;

		long time1 = date1.getTime();
		long time2 = date2.getTime();

		long margin = time1 - time2;

		int ret = (int) Math.floor((double) margin / (1000 * 60 * 60 * 24));

		return ret;
	}

	/**
	 * 获取两个日期对象相差的分钟数
	 * 
	 * @param date1
	 *            日期对象
	 * @param date2
	 *            日期对象
	 * @return int 相差分钟数
	 */
	public static int compareMinute(java.util.Date date1, java.util.Date date2) {
		if (date1 == null || date2 == null)
			return 0;

		long time1 = date1.getTime();
		long time2 = date2.getTime();

		long margin = time1 - time2;

		int ret = (int) Math.floor((double) margin / (1000 * 60 * 60 * 24));

		return ret;
	}

	/**
	 * 获取两个日期对象相差月数
	 * 
	 * @param date1
	 *            日期对象
	 * @param date2
	 *            日期对象
	 * @return int 月份差值
	 */
	public static int compareMonth(java.util.Date date1, java.util.Date date2) {
		if (date1 == null || date2 == null)
			return 0;

		int year = compareYear(date1, date2);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date1);
		int month1 = calendar.get(Calendar.MONTH);

		calendar.setTime(date2);
		int month2 = calendar.get(Calendar.MONTH);

		/* 进行比较 */
		return 12 * year + (month1 - month2);

	}

	/**
	 * 获取两个日期对象相差秒数
	 * 
	 * @param date1
	 *            日期对象
	 * @param date2
	 *            日期对象
	 * @return int 相差秒数
	 */
	public static int compareSecond(java.util.Date date1, java.util.Date date2) {
		if (date1 == null || date2 == null)
			return 0;

		long time1 = date1.getTime();
		long time2 = date2.getTime();

		long margin = time1 - time2;

		Long longValue = new Long(margin / (1000));

		return longValue.intValue();
	}

	/**
	 * 获取两个日期对象相差年数
	 * 
	 * @parma date1 日期对象
	 * @param date2
	 *            日期对象
	 * @return int 年份差值
	 */
	public static int compareYear(java.util.Date date1, java.util.Date date2) {
		if (date1 == null || date2 == null)
			return 0;

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date1);
		int year1 = calendar.get(Calendar.YEAR);

		calendar.setTime(date2);
		int year2 = calendar.get(Calendar.YEAR);

		return year1 - year2;
	}

	public static String converTime(String str, String format) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
		String dateString = str;
		try {
			Date date = df.parse(dateString);
			dateString = toDateTimeString(date, format);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return dateString;
	}

	/**
	 * 得到指定年月的最后一天.
	 * 
	 * @param monthStr
	 *            年月字符串，格式：yyyy-MM
	 */
	public static String endDayOfMoth(String monthStr) {
		int day[] = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

		int year = Integer.parseInt(monthStr.substring(0, 4));
		int month = Integer.parseInt(monthStr.substring(5));

		int endDay = day[month];

		if (month == 2) {
			if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
				endDay = 29;
			}
		}
		return endDay + "";
	}

	/**
	 * 获取当前日期
	 * 
	 * @return 当前日期
	 */
	public static int getCurrentDay() {
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		return day;
	}

	/**
	 * 获取当前时
	 * 
	 * @return 当前时间，如:23点,0点,1点等
	 */
	public static int getCurrentHour() {
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);

		return hour;
	}

	/**
	 * 获取当前分
	 * 
	 * @return 当前分
	 */
	public static int getCurrentMinute() {
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.MINUTE);

		return hour;
	}

	/**
	 * 获取当前月份
	 * 
	 * @return 月份
	 */
	public static int getCurrentMonth() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获取当前时间的星期数:星期日=7;星期一=1;星期二=2;星期三=3;星期四=4;星期五=5;星期六=6;
	 * 
	 * @return 周数值
	 */
	public static int getCurrentWeek() {
		Calendar calendar = Calendar.getInstance();
		int week = calendar.get(Calendar.DAY_OF_WEEK);
		week = week - 1;
		if (week == 0)
			week = 7;

		return week;
	}

	/**
	 * 获取当前年
	 * 
	 * @return 当前年
	 */
	public static int getCurrentYear() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 取得当前的日期时间字符串YYYY-MM-DD
	 * 
	 * @return String 取得当前的日期时间字符串
	 */
	public static String getDateString() {
		String format = "yyyy-MM-dd";
		return getDateTimeString(format);
	}

	/**
	 * 取得当前的日期时间字符串YYYY/MM/DD (移动)
	 * 
	 * @return String 取得当前的日期时间字符串YYYY/MM/DD
	 */
	public static String getDateString2() {
		String format = "yyyy/MM/dd";
		return getDateTimeString(format);
	}

	/**
	 * 取得当前的日期时间字符串YYMMDDHHMISS
	 * 
	 * @return String 取得当前的日期时间字符串YYMMDDHHMISS
	 */
	public static String getDateTime12String() {
		String format = "yyMMddHHmmss";
		return getDateTimeString(format);
	}

	/**
	 * 取得当前的日期时间字符串YYYYMMDDHHMISS
	 * 
	 * @return String 取得当前的日期时间字符串YYYYMMDDHHMISS
	 */
	public static String getDateTime14String() {
		String format = "yyyyMMddHHmmss";
		return getDateTimeString(format);
	}

	/**
	 * 取得当前的日期时间字符串YYYYMM
	 * 
	 * @return String 取得当前的日期时间字符串
	 */
	public static String getDateTime6String() {
		String format = "yyyyMM";
		return getDateTimeString(format);
	}

	/**
	 * 取得当前的日期时间字符串YYYYMMDD
	 * 
	 * @return String 取得当前的日期时间字符串
	 */
	public static String getDateTime8String() {
		String format = "yyyyMMdd";
		return getDateTimeString(format);
	}

	/**
	 * 取得当前的日期整型数组共7项,分别为年,月,日,时,分,秒,毫秒
	 * 
	 * @return int[] 共7项,分别为年,月,日,时,分,秒,毫秒
	 */
	public static int[] getDateTimes() {
		int[] dates = new int[7];
		Calendar calendar = Calendar.getInstance();
		dates[0] = calendar.get(Calendar.YEAR);
		dates[1] = calendar.get(Calendar.MONTH) + 1;
		dates[2] = calendar.get(Calendar.DAY_OF_MONTH);
		dates[3] = calendar.get(Calendar.HOUR_OF_DAY);
		dates[4] = calendar.get(Calendar.MINUTE);
		dates[5] = calendar.get(Calendar.SECOND);
		dates[6] = calendar.get(Calendar.MILLISECOND);
		return dates;
	}

	/**
	 * 取得当前的日期时间字符串YYYY-MM-DD HH:mm:ss
	 * 
	 * @return String 取得当前的日期时间字符串YYYY-MM-DD HH:mm:ss
	 */
	public static String getDateTimeString() {
		String format = "yyyy-MM-dd HH:mm:ss";
		return getDateTimeString(format);
	}

	/**
	 * 取得当前的日期时间字符串
	 * 
	 * @param format
	 *            格式,如String format = "yyyy-MM-dd HH:mm:ss";
	 * @return String 取得当前的日期时间字符串
	 */
	public static String getDateTimeString(String format) {
		return toDateTimeString(new java.util.Date(), format);
	}

	/**
	 * 取得当前的日期时间字符串YYYY/MM/DD HH:mm:ss (移动)
	 * 
	 * @return String 取得当前的日期时间字符串YYYY/MM/DD HH:mm:ss
	 */
	public static String getDateTimeString2() {
		String format = "yyyy/MM/dd HH:mm:ss";
		return getDateTimeString(format);
	}

	/** 返回从当日结束的Timestamp */
	public static Timestamp getDayEnd(Timestamp stamp) {
		return getDayEnd(stamp, 0);
	}

	/** 返回从多少日后结束的Timestamp */
	public static Timestamp getDayEnd(Timestamp stamp, int daysLater) {
		Calendar tempCal = Calendar.getInstance();

		tempCal.setTime(new java.util.Date(stamp.getTime()));
		tempCal.set(tempCal.get(Calendar.YEAR), tempCal.get(Calendar.MONTH), tempCal.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
		tempCal.add(Calendar.DAY_OF_MONTH, daysLater);
		return new Timestamp(tempCal.getTime().getTime());
	}

	/**
	 * 获取指定时间所在周的第一天的时间
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @param day
	 *            日
	 * @return 年、月、日数组
	 */
	public static int[] getDayOfWeek(int year, int month, int day) {
		int[] rtn = new int[6];
		int week = 0;
		long longDate = 0;

		java.util.Date date = null;
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();

		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, day);
		calendar.setFirstDayOfWeek(Calendar.SUNDAY);

		week = calendar.get(Calendar.DAY_OF_WEEK);
		longDate = (calendar.getTime()).getTime() - (60 * 1000 * 60 * 24 * (week - 1));
		date = new java.util.Date(longDate);
		calendar1.setTime(date);

		rtn[0] = calendar1.get(Calendar.YEAR);
		rtn[1] = calendar1.get(Calendar.MONTH) + 1;
		rtn[2] = calendar1.get(Calendar.DATE);

		longDate = (calendar.getTime()).getTime() + (60 * 1000 * 60 * 24 * (7 - week));
		date = new java.util.Date(longDate);
		calendar2.setTime(date);
		rtn[3] = calendar2.get(Calendar.YEAR);
		rtn[4] = calendar2.get(Calendar.MONTH) + 1;
		rtn[5] = calendar2.get(Calendar.DATE);

		return rtn;
	}

	/**
	 * 根据传入的日期得到是每周的周几
	 * 
	 * @param sendDate
	 *            日期格式：yyyy-MM-dd
	 * @return
	 */
	public static String getDayOfWeek(String sendDate) {
		String dayName[] = { "0", "7", "1", "2", "3", "4", "5", "6" };

		Calendar c = toCalendar(sendDate + " 00:00:00");
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

		return dayName[dayOfWeek];
	}

	/** 返回从当日开始的Timestamp */
	public static Timestamp getDayStart(Timestamp stamp) {
		return getDayStart(stamp, 0);
	}

	/** 返回多少天后开始的Timestamp */
	public static Timestamp getDayStart(Timestamp stamp, int daysLater) {
		Calendar tempCal = Calendar.getInstance();

		tempCal.setTime(new java.util.Date(stamp.getTime()));
		tempCal.set(tempCal.get(Calendar.YEAR), tempCal.get(Calendar.MONTH), tempCal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		tempCal.add(Calendar.DAY_OF_MONTH, daysLater);
		return new Timestamp(tempCal.getTime().getTime());
	}

	/**
	 * 获取后一天的Date String
	 * 
	 * @param spe
	 *            分隔符
	 * @return YYYY+spe+MM+spe+DD
	 */
	public static String getNextDateStr(String spe) {
		Calendar calendar = Calendar.getInstance();

		long longDate = (calendar.getTime()).getTime() + (1000 * 60 * 60 * 24 * 1);
		java.util.Date date = new java.util.Date(longDate);
		calendar.setTime(date);

		return toDateString(calendar.getTime(), spe);
	}

	/**
	 * 获取后几天对应的当前时间
	 * 
	 * @param format
	 *            格式化如 yyyy-MM-dd
	 * @return String
	 */
	public static String getNextDateString(int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, days);

		return toDateString(calendar);
	}

	/**
	 * 获取指定时间的后一天的Date String
	 * 
	 * @param spe
	 *            分隔符
	 * @return YYYY+spe+MM+spe+DD
	 */
	public static String getNextDateString(String currentDate) {
		Calendar calendar = toCalendar(currentDate + " 00:00:01");
		calendar.add(Calendar.DAY_OF_MONTH, 1);

		return toDateString(calendar);
	}

	/**
	 * 获取后几小时对应的当前时间
	 * 
	 * @param format
	 *            格式化如 yyyy-MM-dd
	 * @return String
	 */
	public static String getNextDateStringByHour(int hours) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR_OF_DAY, hours);

		return toDateString(calendar);
	}

	/**
	 * 获取后几秒对应的当前时间
	 * 
	 * @param format
	 *            格式化如 yyyy-MM-dd
	 * @return String
	 */
	public static String getNextDateStringBySecond(int seconds) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.SECOND, seconds);

		return toDateString(calendar);
	}

	/**
	 * 获取后一天的Date String
	 * 
	 * @param format
	 *            格式化
	 * @return YYYY+spe+MM+spe+DD
	 */
	public static String getNextDateTimeStr(String format) {
		Calendar calendar = Calendar.getInstance();

		long longDate = (calendar.getTime()).getTime() + (1000 * 60 * 60 * 24 * 1);
		java.util.Date date = new java.util.Date(longDate);
		calendar.setTime(date);

		return toDateTimeString(calendar.getTime(), format);
	}

	/**
	 * 获取后几天对应的当前时间
	 * 
	 * @param format
	 *            格式化如 yyyy-MM-dd
	 * @return String
	 */
	public static String getNextDateTimeString(int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, days);

		return toDateTimeString(calendar);
	}

	/**
	 * 获取后几小时对应的当前时间
	 * 
	 * @param format
	 *            格式化如 yyyy-MM-dd
	 * @return String
	 */
	public static String getNextDateTimeStringByHour(int hours) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR_OF_DAY, hours);

		return toDateTimeString(calendar);
	}

	/**
	 * 获取后几秒对应的当前时间
	 * 
	 * @param format
	 *            格式化如 yyyy-MM-dd
	 * @return String
	 */
	public static String getNextDateTimeStringBySecond(int seconds) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.SECOND, seconds);

		return toDateTimeString(calendar);
	}

	/**
	 * 获取后一天String
	 * 
	 * @param year
	 *            当前年
	 * @param month
	 *            当前月
	 * @param day
	 *            当前日期
	 * @param days
	 *            相差天数
	 * @return 年、月、日数组
	 */
	public static int[] getNextDay() {
		Calendar calendar = Calendar.getInstance();

		long longDate = (calendar.getTime()).getTime() + (1000 * 60 * 60 * 24 * 1);
		java.util.Date date = new java.util.Date(longDate);
		calendar.setTime(date);

		int[] rtn = new int[3];
		rtn[0] = calendar.get(Calendar.YEAR);
		rtn[1] = calendar.get(Calendar.MONTH) + 1;
		rtn[2] = calendar.get(Calendar.DATE);

		return rtn;
	}

	/**
	 * 获取当前时间的后一天或数天的年、月、日，并以数组形式还回。 数组0为年；1为月；2为日
	 * 
	 * @param year
	 *            当前年
	 * @param month
	 *            当前月
	 * @param day
	 *            当前日期
	 * @param days
	 *            相差天数
	 * @return 年、月、日数组
	 */
	public static int[] getNextDay(int year, int month, int day, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, day);

		long longDate = (calendar.getTime()).getTime() + (1000 * 60 * 60 * 24 * days);
		java.util.Date date = new java.util.Date(longDate);
		calendar.setTime(date);

		int[] rtn = new int[3];
		rtn[0] = calendar.get(Calendar.YEAR);
		rtn[1] = calendar.get(Calendar.MONTH) + 1;
		rtn[2] = calendar.get(Calendar.DATE);

		return rtn;
	}

	/** 返回下一天开始的Timestamp */
	public static Timestamp getNextDayStart(Timestamp stamp) {
		return getDayStart(stamp, 1);
	}

	/**
	 * 获取前几小时对应的当前时间
	 * 
	 * @param format
	 *            格式化如 yyyy-MM-dd
	 * @return String
	 */
	public static String getPreviousDateByHourString(int hours) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR_OF_DAY, -hours);

		return toDateString(calendar);
	}

	/**
	 * 获取前几秒对应的当前时间
	 * 
	 * @param format
	 *            格式化如 yyyy-MM-dd
	 * @return String
	 */
	public static String getPreviousDateBySecondString(int second) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.SECOND, -second);

		return toDateString(calendar);
	}

	/**
	 * 获取前一天对应的当前时间,采用标准格式yyyy-MM-dd
	 * 
	 * @return String
	 */
	public static String getPreviousDateString() {

		return getPreviousDateTimeString("yyyy-MM-dd");
	}

	/**
	 * 获取前几天对应的当前时间
	 * 
	 * @param format
	 *            格式化如 yyyy-MM-dd
	 * @return String
	 */
	public static String getPreviousDateString(int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -days);

		return toDateString(calendar);
	}

	/**
	 * 获取前一天对应的当前时间,采用标准格式yyyyMMdd
	 * 
	 * @return String
	 */
	public static String getPreviousDateString1() {

		return getPreviousDateTimeString("yyyyMMdd");
	}

	/**
	 * 获取前一天对应的当前时间,采用短信格式yyyy/MM/dd
	 * 
	 * @return String
	 */
	public static String getPreviousDateString2() {

		return getPreviousDateTimeString("yyyy/MM/dd");
	}

	/**
	 * 获取前几小时对应的当前时间
	 * 
	 * @param format
	 *            格式化如 yyyy-MM-dd
	 * @return String
	 */
	public static String getPreviousDateTimeByHourString(int hours) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR_OF_DAY, -hours);

		return toDateTimeString(calendar);
	}

	/**
	 * 获取前几秒对应的当前时间
	 * 
	 * @param format
	 *            格式化如 yyyy-MM-dd
	 * @return String
	 */
	public static String getPreviousDateTimeBySecondString(int second) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.SECOND, -second);

		return toDateTimeString(calendar);
	}

	/**
	 * 获取前一天对应的当前时间,采用标准格式yyyy-MM-dd HH:mm:ss
	 * 
	 * @return String
	 */
	public static String getPreviousDateTimeString() {

		return getPreviousDateTimeString("yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 获取前几天对应的当前时间
	 * 
	 * @param format
	 *            格式化如 yyyy-MM-dd
	 * @return String
	 */
	public static String getPreviousDateTimeString(int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -days);

		return toDateTimeString(calendar);
	}

	/**
	 * 获取前一天对应的当前时间
	 * 
	 * @param format
	 *            格式化如 yyyy-MM-dd HH:mm:ss
	 * @return String
	 */
	public static String getPreviousDateTimeString(String format) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -1);

		return toDateTimeString(calendar, format);
	}

	/**
	 * 获取前一天对应的当前时间,采用短信格式yyyy/MM/dd HH:mm:ss
	 * 
	 * @return String
	 */
	public static String getPreviousDateTimeString2() {

		return getPreviousDateTimeString("yyyy/MM/dd HH:mm:ss");
	}

	/**
	 * 获取当前时间的前一天或数天的年、月、日，并以数组形式还回。 数组0为年；1为月；2为日
	 * 
	 * @param year
	 *            当前年
	 * @param month
	 *            当前月
	 * @param day
	 *            当前日期
	 * @param days
	 *            相差天数
	 * @return 年、月、日数组
	 */
	public static int[] getPreviousDay(int year, int month, int day, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, day);

		long longDate = (calendar.getTime()).getTime() - (1000 * 60 * 60 * 24 * days);
		java.util.Date date = new java.util.Date(longDate);
		calendar.setTime(date);

		int[] rtn = new int[3];
		rtn[0] = calendar.get(Calendar.YEAR);
		rtn[1] = calendar.get(Calendar.MONTH) + 1;
		rtn[2] = calendar.get(Calendar.DATE);

		return rtn;
	}

	/**
	 * 得到当前时间的上一个月.格式：yyyy-MM
	 */
	public static String getProviesMoth() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);

		return DateTimeUtil.toDateTimeString(calendar, "yyyy-MM");
	}

	/**
	 * 获取和当前时间毫秒差值
	 * 
	 * @param dateTime
	 *            YYYY-MM-DD hh:mm:ss
	 * @return 毫秒差
	 */
	public static long getTimeMargin(String dateTime) {
		int index = dateTime.indexOf(" ");
		String date = dateTime.substring(0, index);
		String time = dateTime.substring(index + 1);

		int dateSlash1 = date.indexOf("-");
		int dateSlash2 = date.lastIndexOf("-");

		if (dateSlash1 <= 0 || dateSlash1 == dateSlash2)
			return -1;

		int timeColon1 = time.indexOf(":");
		int timeColon2 = time.lastIndexOf(":");

		if (timeColon1 <= 0 || timeColon1 == timeColon2)
			return -1;

		Calendar calendar = Calendar.getInstance();

		try {
			int year = Integer.parseInt(date.substring(0, dateSlash1));
			int month = Integer.parseInt(date.substring(dateSlash1 + 1, dateSlash2));
			int day = Integer.parseInt(date.substring(dateSlash2 + 1));

			int hour = Integer.parseInt(time.substring(0, timeColon1));
			int minute = Integer.parseInt(time.substring(timeColon1 + 1, timeColon2));
			int second = Integer.parseInt(time.substring(timeColon2 + 1));

			calendar.set(year, month - 1, day, hour, minute, second);
		}
		catch (Exception e) {
			return -1;
		}

		return System.currentTimeMillis() - calendar.getTimeInMillis();
	}

	public static String getTimeMillStr() {
		Calendar calendar = Calendar.getInstance();
		long time = calendar.getTimeInMillis();
		String result = String.valueOf(time);
		return result;
	}

	/**
	 * 取得当前的日期时间字符串HH:mm:ss
	 * 
	 * @return String 取得当前的日期时间字符串
	 */
	public static String getTimeString() {
		String format = "HH:mm:ss";
		return getDateTimeString(format);
	}

	/**
	 * 日期字符串校验，是否与指定的 format格式一致.
	 * 
	 * @param dateStr
	 * @param format
	 *            格式字符串，比如 yyyy-MM-dd
	 * @return
	 */
	public static boolean isDate(String dateStr, String format) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			sdf.setLenient(false);
			sdf.parse(dateStr);
		}
		catch (ParseException e) {
			return false;
		}
		return true;
	}

	/** 返回当前时间的Date */
	public static java.util.Date nowDate() {
		return new java.util.Date();
	}

	/** 返回当前时间的Timestamp */
	public static Timestamp nowTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

	/**
	 * 通过标准时间输入,年,月,日,时,分,秒,生成java.util.Date
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @param day
	 *            日
	 * @param hour
	 *            时
	 * @param minute
	 *            分
	 * @param second
	 *            秒
	 * @return Calendar
	 */
	public static Calendar toCalendar(int year, int month, int day, int hour, int minute, int second) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month - 1);
		c.set(Calendar.DATE, day);
		c.set(Calendar.HOUR_OF_DAY, hour);
		c.set(Calendar.MINUTE, minute);
		c.set(Calendar.SECOND, second);

		return c;
	}

	/**
	 * 通过整型数组,组织一个日历
	 * 
	 * @param dates
	 * @return 通过整型数组,返回一个日历
	 */
	public static Calendar toCalendar(int[] dates) {
		if (dates == null || dates.length < 6)
			return null;

		return toCalendar(dates[0], dates[1], dates[2], dates[3], dates[4], dates[5]);
	}

	/**
	 * 通过String,组织一个日历
	 * 
	 * @param dates
	 * @return 通过整型数组,返回一个日历
	 */
	public static Calendar toCalendar(String datetime) {
		java.util.Date date = toDateFromStr(datetime);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}

	/**
	 * 通过标准时间输入,年,月,日,时,分,秒,生成java.util.Date
	 * 
	 * @param yearStr
	 *            年
	 * @param monthStr
	 *            月
	 * @param dayStr
	 *            日
	 * @param hourStr
	 *            时
	 * @param minuteStr
	 *            分
	 * @param secondStr
	 *            秒
	 * @return Calendar
	 */
	public static Calendar toCalendar(String yearStr, String monthStr, String dayStr, String hourStr, String minuteStr, String secondStr) {
		int year, month, day, hour, minute, second;

		try {
			year = Integer.parseInt(yearStr);
			month = Integer.parseInt(monthStr);
			day = Integer.parseInt(dayStr);
			hour = Integer.parseInt(hourStr);
			minute = Integer.parseInt(minuteStr);
			second = Integer.parseInt(secondStr);
		}
		catch (Exception e) {
			return null;
		}

		return toCalendar(year, month, day, hour, minute, second);
	}

	/**
	 * 通过标准时间输入,年,月,日,时,分,秒,生成java.util.Date
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @param day
	 *            日
	 * @param hour
	 *            时
	 * @param minute
	 *            分
	 * @param second
	 *            秒
	 * @return java.util.Date
	 */
	public static java.util.Date toDate(int year, int month, int day, int hour, int minute, int second) {
		Calendar calendar = Calendar.getInstance();

		try {
			calendar.set(year, month - 1, day, hour, minute, second);
		}
		catch (Exception e) {
			return null;
		}
		return calendar.getTime();
	}

	/**
	 * 通过整型数组,组织一个日期
	 * 
	 * @param dates
	 * @return 通过整型数组,组织一个日期
	 */
	public static java.util.Date toDate(int[] dates) {
		if (dates == null || dates.length < 6)
			return null;

		return toCalendar(dates).getTime();
	}

	/**
	 * 字符串转为时间,字符串符合标准格式:"YYYY-MM-DD HH:MM:SS"
	 * 
	 * @param dateTime
	 *            标准时间格式 "YYYY-MM-DD HH:MM:SS"
	 * @return java.util.Date
	 */
	public static java.util.Date toDate(String dateTime) {
		int index = dateTime.indexOf(" ");
		String date = dateTime.substring(0, index);
		String time = dateTime.substring(index + 1);

		return toDate(date, time);
	}

	/**
	 * 字符串转为时间,字符串符合标准日期格式:"YYYY-MM-DD",和标准时间格式:"HH:MM:SS"
	 * 
	 * @param date
	 *            标准日期格式 "YYYY-MM-DD"
	 * @param time
	 *            标准时间格式 "HH:MM:SS"
	 * @return java.util.Date
	 */
	public static java.util.Date toDate(String date, String time) {
		if (date == null || time == null)
			return null;

		int dateSlash1 = date.indexOf("-");
		int dateSlash2 = date.lastIndexOf("-");

		if (dateSlash1 <= 0 || dateSlash1 == dateSlash2)
			return null;

		int timeColon1 = time.indexOf(":");
		int timeColon2 = time.lastIndexOf(":");

		if (timeColon1 <= 0 || timeColon1 == timeColon2)
			return null;

		String year = date.substring(0, dateSlash1);
		String month = date.substring(dateSlash1 + 1, dateSlash2);
		String day = date.substring(dateSlash2 + 1);

		String hour = time.substring(0, timeColon1);
		String minute = time.substring(timeColon1 + 1, timeColon2);
		String second = time.substring(timeColon2 + 1);
		;

		return toDate(year, month, day, hour, minute, second);
	}

	/**
	 * 通过标准时间输入,年,月,日,时,分,秒,生成java.util.Date
	 * 
	 * @param yearStr
	 *            年
	 * @param monthStr
	 *            月
	 * @param dayStr
	 *            日
	 * @param hourStr
	 *            时
	 * @param minuteStr
	 *            分
	 * @param secondStr
	 *            秒
	 * @return java.util.Date
	 */
	public static java.util.Date toDate(String yearStr, String monthStr, String dayStr, String hourStr, String minuteStr, String secondStr) {
		int year, month, day, hour, minute, second;

		try {
			year = Integer.parseInt(yearStr);
			month = Integer.parseInt(monthStr);
			day = Integer.parseInt(dayStr);
			hour = Integer.parseInt(hourStr);
			minute = Integer.parseInt(minuteStr);
			second = Integer.parseInt(secondStr);
		}
		catch (Exception e) {
			return null;
		}
		return toDate(year, month, day, hour, minute, second);
	}

	/**
	 * 取得当前的日期时间 按默认格式YYYY-MM-DD HH:mm:ss不对则返回null
	 * 
	 * @param str
	 *            字符串
	 * @return 取得当前的日期时间 按默认格式不对则返回null
	 */
	public static java.util.Date toDateFromStr(String str) {
		try {
			String format = "yyyy-MM-dd HH:mm:ss";
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.parse(str);
		}
		catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 取得当前的日期时间
	 * 
	 * @param str
	 *            字符串
	 * @param format
	 *            格式
	 * @return 取得当前的日期时间 如果格式不对则返回null
	 */
	public static java.util.Date toDateFromStr(String str, String format) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.parse(str);
		}
		catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 取得给定日历,给定格式的日期字符串
	 * 
	 * @param calendar
	 *            日历,给定一个日历
	 * @return String 取得默认的日期时间字符串"yyyy-MM-dd"
	 */
	public static String toDateString(Calendar calendar) {
		String format = "yyyy-MM-dd";
		return toDateTimeString(calendar.getTime(), format);
	}

	/** ****************************************************** */
	// 以下为数据库使用的日期方法,Timestamp ,java.sql.Date
	/** ****************************************************** */

	/**
	 * 生成标准日期,格式为 YYYY-MM-DD
	 * 
	 * @param date
	 *            The Date
	 * @return 生成日期,格式为 YYYY-MM-DD
	 */
	public static String toDateString(java.util.Date date) {
		return toDateString(date, "-");
	}

	/**
	 * 生成标准日期,格式为 YYYY+spe+MM+spe+DD
	 * 
	 * @param date
	 *            The Date
	 * @return 生成日期,格式为 YYYY+spe+MM+spe+DD
	 */
	public static String toDateString(java.util.Date date, String spe) {
		if (date == null)
			return "";

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int year = calendar.get(Calendar.YEAR);

		String monthStr = "" + month;
		String dayStr = "" + day;
		String yearStr = "" + year;

		if (month < 10)
			monthStr = "0" + month;

		if (day < 10)
			dayStr = "0" + day;

		return yearStr + spe + monthStr + spe + dayStr;
	}

	/**
	 * 取得给定日历,给定格式的日期时间字符串
	 * 
	 * @param calendar
	 *            日历,给定一个日历
	 * @return String 取得默认的日期时间字符串"yyyy-MM-dd HH:mm:ss"
	 */
	public static String toDateTimeString(Calendar calendar) {
		String format = "yyyy-MM-dd HH:mm:ss";
		return toDateTimeString(calendar.getTime(), format);
	}

	/**
	 * 取得给定日历,给定格式的日期时间字符串
	 * 
	 * @param calendar
	 *            日历,给定一个日历
	 * @param format
	 *            格式,如String format = "yyyy-MM-dd HH:mm:ss";
	 * @return String 取得给定日历,给定格式的日期时间字符串
	 */
	public static String toDateTimeString(Calendar calendar, String format) {
		return toDateTimeString(calendar.getTime(), format);
	}

	/**
	 * 生成标准格式的字符串 格式为: "MM-DD-YYYY HH:MM:SS"
	 * 
	 * @param date
	 *            The Date
	 * @return 生成默认格式的字符串 格式为: "MM-DD-YYYY HH:MM:SS"
	 */
	public static String toDateTimeString(java.util.Date date) {
		if (date == null)
			return "";

		String dateString = toDateString(date);
		String timeString = toTimeString(date);

		if (dateString == null || timeString == null)
			return "";

		return dateString + " " + timeString;
	}

	/**
	 * 取得给定时间,给定格式的日期时间字符串
	 * 
	 * @param date
	 *            日期,给定一个时间
	 * @param format
	 *            格式,如String format = "yyyy-MM-dd HH:mm:ss";
	 * @return String 取得给定时间,给定格式的日期时间字符串
	 */
	public static String toDateTimeString(java.util.Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * 取得给定时间,给定格式的日期时间字符串,标准格式:"yyyy-MM-dd HH:mm:ss";
	 * 
	 * @param datetime
	 *            日期,给定一个时间的毫秒数
	 * @return String 取得给定时间,给定格式的日期时间字符串
	 */
	public static String toDateTimeString(long datetime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new java.util.Date(datetime));
	}

	/**
	 * 取得给定时间,给定格式的日期时间字符串
	 * 
	 * @param datetime
	 *            日期,给定一个时间的毫秒数
	 * @param format
	 *            格式,如String format = "yyyy-MM-dd HH:mm:ss";
	 * @return String 取得给定时间,给定格式的日期时间字符串
	 */
	public static String toDateTimeString(long datetime, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new java.util.Date(datetime));
	}

	/**
	 * 生成java.sql.Date,通过传入year, month, day
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @param day
	 *            日
	 * @return A java.sql.Date
	 */
	public static java.sql.Date toSqlDate(int year, int month, int day) {
		java.util.Date newDate = toDate(year, month, day, 0, 0, 0);

		if (newDate == null)
			return null;

		return new java.sql.Date(newDate.getTime());
	}

	/**
	 * String到java.sql.Date的转换 标准格式:YYYY-MM-DD
	 * 
	 * @param date
	 *            The date String
	 * @return java.sql.Date
	 */
	public static java.sql.Date toSqlDate(String date) {
		java.util.Date newDate = toDate(date, "00:00:00");

		if (newDate == null)
			return null;

		return new java.sql.Date(newDate.getTime());
	}

	/**
	 * 生成java.sql.Date,通过传入year, month, day
	 * 
	 * @param yearStr
	 *            年
	 * @param monthStr
	 *            月
	 * @param dayStr
	 *            日
	 * @return A java.sql.Date
	 */
	public static java.sql.Date toSqlDate(String yearStr, String monthStr, String dayStr) {
		java.util.Date newDate = toDate(yearStr, monthStr, dayStr, "0", "0", "0");

		if (newDate == null)
			return null;

		return new java.sql.Date(newDate.getTime());
	}

	/**
	 * 生成 java.sql.Time 通过输入时,分,秒
	 * 
	 * @param hour
	 *            int 时
	 * @param minute
	 *            int 分
	 * @param second
	 *            秒
	 * @return A java.sql.Time
	 */
	public static java.sql.Time toSqlTime(int hour, int minute, int second) {
		java.util.Date newDate = toDate(0, 0, 0, hour, minute, second);

		if (newDate == null)
			return null;

		return new java.sql.Time(newDate.getTime());
	}

	/**
	 * 转换String 到 java.sql.Time,格式:"HH:MM:SS"
	 * 
	 * @param time
	 *            The time String
	 * @return A java.sql.Time
	 */
	public static java.sql.Time toSqlTime(String time) {
		java.util.Date newDate = toDate("1970-1-1", time);

		if (newDate == null)
			return null;

		return new java.sql.Time(newDate.getTime());
	}

	/**
	 * 生成 java.sql.Time 通过输入时,分,秒
	 * 
	 * @param hourStr
	 *            时
	 * @param minuteStr
	 *            分
	 * @param secondStr
	 *            秒
	 * @return A java.sql.Time
	 */
	public static java.sql.Time toSqlTime(String hourStr, String minuteStr, String secondStr) {
		java.util.Date newDate = toDate("0", "0", "0", hourStr, minuteStr, secondStr);

		if (newDate == null)
			return null;

		return new java.sql.Time(newDate.getTime());
	}

	/**
	 * 生成 Timestamp 通过输入年,月,日,时,分,秒
	 * 
	 * @param year
	 *            年 int
	 * @param month
	 *            月 int
	 * @param day
	 *            日 int
	 * @param hour
	 *            时 int
	 * @param minute
	 *            分 int
	 * @param second
	 *            秒 int
	 * @return Timestamp
	 */
	public static Timestamp toTimestamp(int year, int month, int day, int hour, int minute, int second) {
		java.util.Date newDate = toDate(year, month, day, hour, minute, second);

		if (newDate != null)
			return null;

		return new Timestamp(newDate.getTime());
	}

	/**
	 * 转换String 到 java.sql.Timestamp,格式:"YYYY-MM-DD HH:MM:SS"
	 * 
	 * @param dateTime
	 *            格式:"YYYY-MM-DD HH:MM:SS"
	 * @return Timestamp
	 */
	public static Timestamp toTimestamp(String dateTime) {
		java.util.Date newDate = toDate(dateTime);

		if (newDate != null)
			return null;

		return new Timestamp(newDate.getTime());
	}

	/**
	 * 转换String 到 java.sql.Timestamp,格式:"YYYY-MM-DD HH:MM:SS"
	 * 
	 * @param date
	 *            The date String: YYYY-MM-DD
	 * @param time
	 *            The time String: HH:MM:SS
	 * @return Timestamp
	 */
	public static Timestamp toTimestamp(String date, String time) {
		java.util.Date newDate = toDate(date, time);

		if (newDate == null)
			return null;

		return new Timestamp(newDate.getTime());
	}

	/**
	 * 生成 Timestamp 通过输入年,月,日,时,分,秒
	 * 
	 * @param yearStr
	 *            年
	 * @param monthStr
	 *            月
	 * @param dayStr
	 *            日
	 * @param hourStr
	 *            时
	 * @param minuteStr
	 *            分
	 * @param secondStr
	 *            T秒
	 * @return Timestamp
	 */
	public static Timestamp toTimestamp(String yearStr, String monthStr, String dayStr, String hourStr, String minuteStr, String secondStr) {
		java.util.Date newDate = toDate(yearStr, monthStr, dayStr, hourStr, minuteStr, secondStr);

		if (newDate != null)
			return null;

		return new Timestamp(newDate.getTime());
	}

	/**
	 * 根据输入的时,分,秒,生成时间格式 HH:MM:SS
	 * 
	 * @param hour
	 *            时
	 * @param minute
	 *            分
	 * @param second
	 *            秒
	 * @return 生成时间格式为 HH:MM:SS
	 */
	public static String toTimeString(int hour, int minute, int second) {
		String hourStr = "" + hour;
		String minuteStr = "" + minute;
		String secondStr = "" + second;

		if (hour < 10)
			hourStr = "0" + hour;

		if (minute < 10)
			minuteStr = "0" + minute;

		if (second < 10)
			secondStr = "0" + second;

		return hourStr + ":" + minuteStr + ":" + secondStr;
	}

	/**
	 * 根据输入的时间,生成时间格式 HH:MM:SS
	 * 
	 * @param date
	 *            java.util.Date
	 * @return 生成时间格式为 HH:MM:SS
	 */
	public static String toTimeString(java.util.Date date) {
		if (date == null)
			return "";

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);
		return toTimeString(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND));
	}

	/**
	 * 获取传入时间的前N个小时对应的时间
	 * 
	 * @param format
	 *            格式化如 yyyy-MM-dd HH:mm:ss
	 * @param hour
	 *            小时数
	 * @return String 格式化如 yyyy-MM-dd HH:mm:ss
	 */
	public static String getPreviousDateTimeString(String format, int hour) {
		Calendar calendar = toCalendar(format);
		calendar.add(Calendar.HOUR_OF_DAY, -hour);
		return toDateTimeString(calendar, format);
	}

	/**
	 * 获取传入时间的前N天时间
	 * 
	 * @param format
	 *            格式化如 yyyMMdd
	 * @return String 格式化如 yyyMMdd
	 */
	public static String getPreviousDateString(String format, int days) {
		format = format.substring(0, 4) + "-" + format.substring(4, 6) + "-" + format.substring(6, 8) + " 00:00:00";
		Calendar calendar = toCalendar(format);
		calendar.add(Calendar.DAY_OF_MONTH, -days);
		return toDateString(calendar).replaceAll("-", "").substring(0, 8);
	}
}