package co.yixiang.modules.quartz.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 功能:通用的静态工具函数(用于日期和时间处理)
 * <p>Description: 通用工具类</p>
 */
public class DateTimeUtils {

	private DateTimeUtils(){
	}
	
	public final static String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";
	public final static String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
	public final static String DATE_FORMAT_MMDD = "MMdd";
	public final static String DATE_FORMAT_MM_DD = "MM.dd";
	public final static String DATE_FORMAT_DD = "dd";
	public final static String DATETIME_FORMAT_HHMMSS = "HHmmss";
	public final static String DATETIME_FORMAT_HHMM = "HHmm";
	public final static String DATETIME_FORMAT_HHMM_COLON = "HH:mm";
	public final static String DATETIME_FORMAT_MMDDHHMMSS = "MMddHHmmss";
	public final static String DATETIME_FORMAT_HHMMSSSSS = "HHmmssSSS";
	public final static String DATETIME_FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	public final static String DATETIME_FORMAT_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
	public final static String DATETIME_FORMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	public final static String DATETIME_FORMAT_YYYYMMDDHH = "yyyyMMddHH";
	public final static String DATETIME_FORMAT_YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
	public final static String DATETIME_FORMAT_YYYY = "yyyy";
	public final static String DATETIME_FORMAT_YYYY_MM_DD_CN = "yyyy年MM月dd日";
	public final static String DATETIME_FORMAT_YYYY_MM_DD_HH_MM_CN = "yyyy年MM月dd日HH时mm分";
	public final static String YYYY_MM_DD_HH_MM_SS_CN = "yyyy年MM月dd日HH时mm分ss秒";
	public final static String DATETIME_FORMAT_YYYY_MM_CN = "yyyy年MM月";
	public final static String DATETIME_FORMAT_MM_DD_CN = "MM月dd日";
	public final static String DATETIME_FORMAT_YYYY_MM_DD_DIAN = "yyyy.MM.dd";
	public final static String DATETIME_FORMAT_HH_MM_SS = "HH:mm:ss";
	public final static String DATE_FORMAT_YYYYMM = "yyyyMM";
	public final static String DATE_FORMAT_YYYY_MM = "yyyy-MM";
	public final static String DATETIME_FORMAT_YYYY_MM_DD_HH_MM_SS_TZ = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	public final static String DATETIME_FORMAT_YYMMDDHHMM = "yyMMddHHmm";
	public final static String DATETIME_FORMAT_YYMMDDHHMMSS = "yyMMddHHmmss";
	/** AOP Date默认时区 **/
	public static final String DATE_TIMEZONE = "GMT+8";

	private static final Log log = LogFactory.getLog(DateTimeUtils.class);

	private static DateTimeFormatter UTC_DTF = DateTimeFormatter.ofPattern(DATETIME_FORMAT_YYYYMMDDHHMMSS);
	private static DateTimeFormatter STD_DTF = DateTimeFormatter.ofPattern(DATETIME_FORMAT_YYYY_MM_DD_HH_MM_SS);



	/**
	 * 获取任意时间下个月的最后一天
	 * 描述:<描述函数实现的功能>.
	 * @param repeatDate
	 * @return
	 */
	public static Date getNextMonthDateFirstDay(Date repeatDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(repeatDate);
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}

	/**
	 * 获取任意时间下个月的最后一天
	 * 描述:<描述函数实现的功能>.
	 * @param repeatDate
	 * @return
	 */
	public static Date getNextMonthDate(Date repeatDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(repeatDate);
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}


	/**
	 * 获得指定日期的后N天
	 * @param
	 * @return
	 */
	public static Date getSpecifiedDayAfter(Date date,int num){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int day=c.get(Calendar.DATE);
		c.set(Calendar.DATE,day+num);
		return c.getTime();
	}

	/**
	 * 获得指定日期的前N天
	 * @param
	 * @return
	 */
	public static Date getSpecifiedDayBefore(Date date, int num) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day - num);
		return c.getTime();
	}

	/**
	 * 日期间隔是否大于指定数
	 *
	 * @param begin
	 * @param end
	 * @param timeinmill
	 * @return
	 */
	public static boolean isAfterInMill(Date begin,Date end,long timeinmill){
		long begininmill = begin.getTime();
		long endinmill = end.getTime();
		if(endinmill-begininmill>timeinmill){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 比较两个时间大小
	 * @param first
	 * @param second
	 * @return
	 * 		<0: first<second
	 * 		=0: first=second
	 * 		>0: first>second
	 */
	public static int compareTwoDate(Date first, Date second){
		Calendar c1= Calendar.getInstance();
		Calendar c2= Calendar.getInstance();

		c1.setTime(first);
		c2.setTime(second);
		
		return c1.compareTo(c2);
	}

	/**
	 * 比较一天内时间的大小
	 * 
	 * @param time1
	 * @param time2
	 * @param timeFormat
	 * @return <0: first<second =0: first=second >0: first>second
	 * 
	 * 		
	 */
	public static int compareTimeInDay(String time1, String time2, String timeFormat) throws Exception {
		return DateTimeUtils.compareTwoDate(DateTimeUtils.getStringToDateTime(time1, timeFormat),
				DateTimeUtils.getStringToDateTime(time2, timeFormat));
	}

	/**
	 * 判断nowTime是不是在两个时间点之间
	 * @param nowTime
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public static boolean belongCalendar(Date nowTime, Date beginTime, Date endTime) {
		Calendar date = Calendar.getInstance();
		date.setTime(nowTime);
		Calendar begin = Calendar.getInstance();
		begin.setTime(beginTime);
		Calendar end = Calendar.getInstance();
		end.setTime(endTime);
		if (date.after(begin) && date.before(end)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 取得当前日期所在周的第一天
	 *
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfWeek(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
		return c.getTime ();
	}

	/**
	 * 取得当前日期所在周的最后一天
	 *
	 * @param date
	 * @return
	 */
	public static Date getLastDayOfWeek(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
		return c.getTime();
	}

	/**
	 * 获取当前月的第一天
	 * @return
	 */
	public static Date getFirstDayOfMonth(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
		return c.getTime();
	}
	
	/**
	 * 获取当前月的最后一天
	 * @return
	 */
	public static Date getLastDayOfMonth(Date date) {
		Calendar cale = Calendar.getInstance();
		cale.setTime(date);
		cale.set(Calendar.DAY_OF_MONTH,cale.getActualMaximum(Calendar.DAY_OF_MONTH));// 设置为1号,当前日期既为本月第一天
		return cale.getTime();
	}
	
	/**
	 * 计算日期增加或减少小时数后的日期
	 * @param date
	 * @param i 为负表示减多少小时
	 * @return
	 */
	public static Date addHH(Date date,int i){
		if(date==null)return null;
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		c.add(Calendar.HOUR, i);
		return c.getTime();
	}

	/**
	 * 计算日期增加或减少分钟数后的日期
	 * @param date
	 * @param i 为负表示减多少分钟
	 * @return
	 */
	public static Date addMM(Date date,int i){
		if(date==null)return null;
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		c.add(Calendar.MINUTE, i);
		return c.getTime();
	}

	/**
	 * 计算日期增加或减少秒数后的日期
	 * @param date
	 * @param i 为负表示减多少秒
	 * @return
	 */
	public static Date addSS(Date date,int i){
		if(date==null)return null;
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		c.add(Calendar.SECOND, i);
		return c.getTime();
	}

	/**
	 * 计算日期增加减少天数后的日期
	 * @param date
	 * @param i 为负表示减多少天
	 * @return
	 */
	public static Date addDate(Date date,int i){
		if(date==null)return null;
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		c.add(Calendar.DATE, i);
		return c.getTime();
	}
	/**
	 * 计算日期增加减少月数后的日期
	 * @param date
	 * @param i 为负表示减多少月
	 * @return
	 */
	public static Date addMonth(Date date,int i){
		if(date==null)return null;
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		c.add(Calendar.MONTH, i);
		return c.getTime();
	}

	public static String getMonth(){
		Calendar c = Calendar.getInstance();
		int mon = c.get(Calendar.MONTH) + 1;

		return String.format("%02d", mon) ;
	}

	/**
	 * 计算日期增加减少年数后的日期
	 * @param date
	 * @param i 为负表示减多少年
	 * @return
	 */
	public static Date addYear(Date date,int i){
		if(date==null)return null;
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		c.add(Calendar.YEAR, i);
		return c.getTime();
	}


	/**
	 * 获得当前时间字符串
	 * @param formatStr 日期格式
	 * @return string yyyy-MM-dd
	 */
	public static String getNowDateStr(String formatStr) {
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		return format.format(getNowDate());
	}

	/**
	 * 获得系统当前时间
	 * @return Date
	 */
	public static Date getNowDate(){
		return new Date();
	}

	public static Date getNowDateFormat() {
		SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_FORMAT_YYYY_MM_DD_HH_MM_SS);
		String format = sdf.format(new Date());
		try {
			return getStringToDateTime(format, DATETIME_FORMAT_YYYY_MM_DD_HH_MM_SS);
		} catch (Exception e) {
			log.error("时间格式转换异常：", e);
			return new Date();
		}
	}

	/**
	 * 把日期按照指定格式的转化成字符串
	 * @param date 日期对象
	 * @param formatStr 日期格式
	 * @return 字符串式的日期,格式为：yyyy-MM-dd HH:mm:ss
	 */
	public static String getDateTimeToString(Date date,String formatStr) {
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		return format.format(date);
	}

	/**
	 * 把日期字符串转化成指定格式的日期对象
	 * @param dateStr 日期字符串
	 * @param formatStr 日期格式
	 * @return Date类型的日期
	 * @throws Exception
	 */
	public static Date getStringToDateTime(String dateStr,String formatStr) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		return format.parse(dateStr);
	}

	/**
	 * 将当前时间格式字符串转为指定时间格式字符串
	 * @param dateStr
	 * @param nowFormatStr
	 * @param currFormatStr
	 * @return
	 * @throws Exception
	 */
	public static String getFormatDateStrFromDateStr(String dateStr,String nowFormatStr,String currFormatStr) throws Exception{
			Date dateTime = getStringToDateTime(dateStr, nowFormatStr);
			return getDateTimeToString(dateTime,currFormatStr);
	}

	/**
	 * 把日期字符串转化成指定格式的日期对象，如果异常则返回null
	 * @param dateStr 日期字符串
	 * @param formatStr 日期格式
	 * @return Date类型的日期
	 * @throws Exception
	 */
	public static Date getStringToDateTimeExceptionNull(String dateStr,String formatStr){
		Date date = null;

		if(StringUtils.isBlank(dateStr)){
			log.warn("解析的日期字符串为空");
			return date;
		}

		SimpleDateFormat format = new SimpleDateFormat(formatStr);

		try {
			date = format.parse(dateStr);
		} catch (ParseException e) {
			log.error("日期格式错误："+dateStr,e);
			return null ;
		}
		return date;
	}

	/**
	 * 校验日期与格式是否一致
	 * @param dttm
	 * @param format
	 * @return
	 */
	public static boolean isDate(String dttm, String format) {
		if (dttm == null || dttm.isEmpty() || format == null || format.isEmpty()) {
			return false;
		}

		if (format.replaceAll("'.+?'", "").indexOf('y') < 0) {
			format += "/yyyy";
			DateFormat formatter = new SimpleDateFormat("/yyyy");
			dttm += formatter.format(new Date());
		}

		DateFormat formatter = new SimpleDateFormat(format);
		formatter.setLenient(false);
		ParsePosition pos = new ParsePosition(0);
		Date date = formatter.parse(dttm, pos);

		if (date == null || pos.getErrorIndex() > 0) {
			return false;
		}
		if (pos.getIndex() != dttm.length()) {
			return false;
		}

		if (formatter.getCalendar().get(Calendar.YEAR) > 9999) {
			return false;
		}

		return true;
	}



    /**
     * 获得当前时间的i分钟前的时间
     * @param i
     * @return
     */
    public static Date minusMM(int i){
        return addMM(getNowDate(),-i);
    }


    /**
	 * 获得当前时间的i分钟后（或前，用负数表示）的时间
	 * @param i
	 * @return
	 */
	public static Date addMM(int i){
		return addMM(getNowDate(),i);
	}

	/**
	 * 获得某个时间的i分钟后（或前，用负数表示）的时间
	 * @param i
	 * @return
	 */
	public static Date dateAddMM(Date date, int i){
		return addMM(date,i);
	}



	/**
	 * @param date 获取给定日期的起初时间 XX-XX-XX 00:00:00
	 * @return date
	 */
	public static Date getBegin(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return  calendar.getTime();
	}

	/**
	 * @param date 获取给定日期的结束时间 XX-XX-XX 23:59:59
	 * @return date
	 */
	public static Date getEnd(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return  calendar.getTime();
	}

	public static boolean isToday( Date theDay ){
		Calendar cNow = Calendar.getInstance();
		int iYear = cNow.get(Calendar.YEAR);
		int iDay = cNow.get( Calendar.DAY_OF_YEAR ) ;

		Calendar cDay = Calendar.getInstance();
		cDay.setTime(theDay);
		int iTheYear = cDay.get(Calendar.YEAR);
		int iTheDay = cDay.get( Calendar.DAY_OF_YEAR ) ;

		return (iTheYear == iYear) && (iTheDay == iDay) ;
	}

	public static boolean isToday240000( Date theDay ){
		Date tomorrow = getSpecifiedDayAfter( getNowDate(),1) ;

		String strDate = getDateTimeToString(tomorrow,DATE_FORMAT_YYYYMMDD);
		strDate+="000000" ;

		Date target = getStringToDateTimeExceptionNull( strDate,DATETIME_FORMAT_YYYYMMDDHHMMSS );

		return compareTwoDate(theDay,target) == 0;
	}

	/**
	 * 计算两个日期之间的相差的天数. 计算方式：second - first
	 * <p> Create Date: 2015年1月22日 </p>
	 * @param smdate	较小的时间 
	 * @param bdate		较大的时间 
	 * @return 相差的天数
	 */
	public static int daysBetween( Date smdate,Date bdate ){
		int result  = 0;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		try{
			smdate=sdf.parse(sdf.format(smdate));
			bdate=sdf.parse(sdf.format(bdate));
			Calendar cal = Calendar.getInstance();
			cal.setTime(smdate);
			long time1 = cal.getTimeInMillis();
			cal.setTime(bdate);
			long time2 = cal.getTimeInMillis();
			long between_days=(time2-time1)/(1000*3600*24);
			result = Integer.parseInt(String.valueOf(between_days));
		}
		catch(ParseException e){
			log.error("日期转化异常",e);
		}
		catch(Exception e){
			log.error("格式转化异常",e);
		}

		return   result  ;
	}

	/**
	 * 计算两个日期之间的相差的秒数. 计算方式：second - first
	 * @param smdate	较小的时间 
	 * @param bdate		较大的时间 
	 * @return 相差的秒数
	 */
	public static int secondsBetween( Date smdate, Date bdate ){
		int result  = 0;
		try{
			Calendar cal = Calendar.getInstance();
			cal.setTime(smdate);
			long time1 = cal.getTimeInMillis();
			cal.setTime(bdate);
			long time2 = cal.getTimeInMillis();
			long between_min=(time2-time1)/1000;
			result = Integer.parseInt(String.valueOf(between_min));
		}
		catch(Exception e){
			log.error("格式转化异常",e);
		}

		return   result  ;
	}

	/**
	 * 将时间戳转换为date
	 * <p> Create Date: 2015年3月30日 </p>
	 * @param seconds
	 * @return
	 */
	public static Date getTimestampToDate(long seconds ){
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis( seconds );

		return calendar.getTime()  ;
	}
	
	
	/**
	 * 将date转时间戳
	 * 
	 * @param date
	 * @return
	 */
	public static long getDateToTimestamp(Date date) {
		long unixTimestamp = date.getTime() / 1000;
		return unixTimestamp;
	}
	
	/**
	 * 判断时间是否落于某段日期内
	 * @param date
	 * @param startTime yyyy-MM-dd
	 * @param endTime yyyy-MM-dd
	 * @return
	 */
	public static boolean isInDayInterval(Date date, String startTime, String endTime) {
		startTime = startTime.replace("-", "");
		endTime = endTime.replace("-", "");

		int iStartTime = Integer.parseInt(startTime);
		int iEndTime = Integer.parseInt(endTime);

		int iTime = Integer.parseInt(getDateTimeToString(date, DATE_FORMAT_YYYYMMDD));

		return iTime >= iStartTime && iTime <= iEndTime;
	}

	/**
	 * 判断时间是否落于某个区间内
	 * <p> Create Date: 2015年11月4日 </p>
	 * @param date
	 * @param startTime   hh:mm
	 * @param endTime	  hh:mm
	 * @return
	 */
	public static boolean isInTimeInterval(Date date, String startTime, String endTime) {
		int iStartTime = 0, iEndTime = 0, iTime = 0;
		try {
			startTime = startTime.replace(":", "");
			endTime = endTime.replace(":", "");

			iStartTime = Integer.parseInt(startTime);
			iEndTime = Integer.parseInt(endTime);

			iTime = Integer.parseInt(getDateTimeToString(date, DATETIME_FORMAT_HHMM));
		} catch (Exception e) {
			log.error("非工作时间转换异常.", e);
			return false;
		}

		boolean result = iStartTime <= iTime;
		result &= iTime <= iEndTime;

		return result;
	}

	/**
	 * 验证时间字符串格式输入是否正确
	 * @param timeStr
	 * @param template
	 * @return
	 */
	public static boolean valiDateFormat(String timeStr, String template) {
		boolean convertSuccess = true;
		// 指定日期格式
		SimpleDateFormat format = new SimpleDateFormat(template);
		try {
			format.setLenient(false);
			format.parse(timeStr);
		} catch (Exception e) {
			convertSuccess = false;
		}
		return convertSuccess;
	}

	/**
	 * 判断两个日期是否在同一天
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean inSameDay(Date date1,Date date2) {   
		Calendar c1 = Calendar.getInstance();   
		Calendar c2 = Calendar.getInstance();   
		c1.setTime(date1);   
		c2.setTime(date2);   
		return (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR))
				&& (c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH))
				&& (c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH));   
	}
	
	/**
     * 判断两个日期是否在同一个月份
     *
     * @param date1
     * @param date2
     * @return
     */
    @SuppressWarnings("deprecation")
	public static boolean inSameMonth(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return false;
        }
        if (date1.getYear() == date2.getYear() && date1.getMonth() == date2.getMonth()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断两个日期是否在同一年
     * @param date1
     * @param date2
     * @return
     */
    @SuppressWarnings("deprecation")
	public static boolean inSameYear(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return false;
        }
        if (date1.getYear() == date2.getYear()) {
            return true;
        } else {
            return false;
        }
    }
    
	/**
	 * 根据日期取得星期几
	 *
	 * @param date
	 * @return
	 */
	public static String getDayofWeekStr(Date date){
		String[] weekArr = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if(week_index < 0 || week_index > 6){
			return "";
		}
		return weekArr[week_index];
	}
	
	/**
	 * 根据日期取得星期几
	 *
	 * @param date
	 * @return
	 */
	public static int getDayOfWeekInt(Date date){
		int[] weekArr = {7, 1, 2, 3, 4, 5, 6};
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if(week_index < 0 || week_index > 6){
			return 9;
		}
		return weekArr[week_index];   
	}

	/**
	 * [Date转LocalDateTime]
	 */
	public static LocalDateTime dateToLocalDateTime(Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

	/**
	 * [LocalDateTime转Date]
	 */
	public static Date localDatetimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
	
    /**
	 * 获取到下一个自然日的时间差，单位是秒
	 *
	 * @return
	 */
	public static long getTimeDiffToNextDate() {
		// 默认24小时
		long time = 60 * 60 * 24;
		try {
			Date now = new Date();
			Date tomorrow = DateUtils.addDays(DateUtils.parseDate(DateFormatUtils.format(now, "yyyy-MM-dd 00:00:00"), "yyyy-MM-dd HH:mm:ss"), 1);
			time = (tomorrow.getTime() - now.getTime()) / 1000;
		} catch (Exception ex) {
			log.error("Error when get the time to next day.", ex);
		}
		return time + 1;
	}
	
	/**
     * 获取到下一个自然日的时间差，单位是毫秒
     *
     * @return
     */
    public static long getTimeDiffToNextDateMillis() {
		// 默认24小时
		long time = 60 * 60 * 24 * 1000L;
		try {
			Date now = new Date();
			Date tomorrow = DateUtils.addDays(
					DateUtils.parseDate(DateFormatUtils.format(now, "yyyy-MM-dd 00:00:00"), "yyyy-MM-dd HH:mm:ss"), 1);
			time = (tomorrow.getTime() - now.getTime());
		} catch (Exception ex) {
			log.error("Error when get the time to next day.{}", ex);
		}
        return time + 1;
    }
    

	/**
	 *
	 * @param date
	 * @param startTime
	 * @param endTime
	 * @param format
	 * @return
	 */
	public static boolean isInTimeIntervalByFormat(Date date, String startTime, String endTime,String format) {
		int iStartTime = 0, iEndTime = 0, iTime = 0;
		try {
			startTime = startTime.replace(":", "");
			endTime = endTime.replace(":", "");

			iStartTime = Integer.parseInt(startTime);
			iEndTime = Integer.parseInt(endTime);

			iTime = Integer.parseInt(getDateTimeToString(date, format));
		} catch (Exception e) {
			log.error("非工作时间转换异常.", e);
			return false;
		}

		boolean result = iStartTime <= iTime;
		result &= iTime <= iEndTime;

		return result;
	}

	/**
	 * utc时间格式转换
	 *
	 * @param utcStr utc时间
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String convertTimeFormat2(String utcStr) {
		if (StringUtils.isBlank(utcStr)) {
			return null;
		}
		return LocalDateTime.parse(utcStr, UTC_DTF).format(STD_DTF);
	}

	/**
	 * utc时间格式转换
	 *
	 * @param utcStr 集团时间
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String convertTimeFormat(String utcStr) {
		if (StringUtils.isBlank(utcStr)) {
			return null;
		}
		return LocalDateTime.parse(utcStr, UTC_DTF).format(STD_DTF);
	}

	/**
	 * 获取距当前日期day天前后的日期
	 *
	 * @param day
	 * @return
	 */
	public static String getPrevDate(int day) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, day);
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_YYYYMMDD);
		String prevDate = format.format(cal.getTime());
		return prevDate;
	}

	/**
	 * 秒转分秒
	 * @param time
	 * @return
	 */
	public static String secToTime(int time){
	StringBuilder stringBuilder = new StringBuilder();
		Integer minutes = time / 60;
		Integer remainingSeconds = time % 60;
		stringBuilder.append(minutes);
		stringBuilder.append(":");
		if(remainingSeconds < 10){
			stringBuilder.append("0");
		}
		stringBuilder.append(remainingSeconds);
		return stringBuilder.toString();
	}

	/**
	 * 返回指定日期相应位移后的日期
	 *
	 * @param date 参考日期
	 * @param field 位移单位，见 {@link Calendar}
	 * @param offset 位移数量，正数表示之后的时间，负数表示之前的时间
	 * @return 位移后的日期
	 */
	public static Date offsetDate(Date date, int field, int offset) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(field, offset);
		return calendar.getTime();
	}

	/**
	 * 时间比较
	 *
	 * @param dateBegin
	 * @param dateEnd
	 * @return
	 */
	public static boolean dateCompare(Date dateBegin, Date dateEnd) {
		if (null == dateBegin || null == dateEnd) {
			return false;
		}
		if(dateEnd.compareTo(dateBegin)>=0) {
			return true;
		} else {
			return false;
		}
	}
}