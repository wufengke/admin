package com.cyou.common.util;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.apache.commons.lang.StringUtils;



/**
 * <p>
 * Copyright © 2007 SinoFloat  All Rights Reserved.
 * </p>
 * <p>
 * SinoFloat Corp CONFIDENTIAL. All information, copyrights, trade secrets<br>
 * and other intellectual property rights, contained herein are the property<br>
 * of SinoFloat. This file is strictly confidential and must not be<br>
 * copied, accessed, disclosed or used in any manner, in whole or in part,<br>
 * without SinoFloat's express written authorization.
 * </p>
 */
public class DateUtils {
	
//	static Calendar ca = Calendar.getInstance();
    
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private static DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static DateFormat dateFormat3 = new SimpleDateFormat("yyyy/MM/dd");

    public static long HALF_HOUR = 1800000;
    
    public static long HOUR = 3600000;
    
	public static Date getDate(){
		return Calendar.getInstance().getTime();
	}
	
	public static Date getDate(long l){
		return new Date(l);
	}
	
	public static long getTime(){
		return Calendar.getInstance().getTimeInMillis();
	}
	
	public static Date getDateFromNow(long l){
		Date date = getDate();
		return new Date(date.getTime()+l);
	}
	
	public static long getTimeFromNow(long l){
		return getTime()+l;
	}
	
	public static long getIntervalTillTime(long l){
		return Math.max(0, l-getTime());
	}
	
    public static long getIntervalFromTime(long l) {
        return Math.max(0, getTime()-l);
    }

	public static Date getDate(Date date, long lauchtime) {
		return new Date(date.getTime()+lauchtime);
	}
	
	public static String formatTaskTimeString(long remain, long endtime) {
		//return formatTimeString(remain) + " / " + formatDateString(DateUtils.getDate(endtime));
	    return formatTaskTimeString(endtime);
	}
	
	   public static String formatTaskTimeString(long endtime) {
	       long remain = Math.max(0, endtime-DateUtils.getTime());
	        return formatTimeString(remain) + "/" + formatDateString(DateUtils.getDate(endtime));
	    }
	/**
	 * Format a date to: hh:mm:ss (OLD:yyyy年mm月dd日 hh时mm分ss秒)
	 */
	public static String formatDateString(Date d) {
		StringBuffer result = new StringBuffer("");
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		String[] datearr = bartDateFormat.format(d).split("-");
		
		result
//		.append(datearr[0]).append("年")
//		.append(datearr[1]).append("月")
//		.append(datearr[2]).append("日")
//		.append(" ")
//		.append(datearr[3]).append("时")
//		.append(datearr[4]).append("分")
//		.append(datearr[5]).append("秒")
		.append(datearr[3]).append(":")
		.append(datearr[4]).append(":")
		.append(datearr[5])
		;
		return result.toString();
	}
	public static String formatDate(Date d, String format) {
		SimpleDateFormat bartDateFormat = new SimpleDateFormat(format);
		return bartDateFormat.format(d);
	}

	public static String format(long l,String format) {
		Date d = getDate(l);
		SimpleDateFormat bartDateFormat = new SimpleDateFormat(format);
		String datearr = bartDateFormat.format(d);

		return datearr;
	}
	
	public static java.util.Date parseDate(String sDate){
	    java.text.DateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    java.util.Date retDate=null;
	    try {
	      retDate = formatter.parse(sDate);
	    }
	    catch (Exception ex) {
	    }
	    return retDate;
	  }
	
	public static java.util.Date parseDate(String sDate,String format){
	    java.text.DateFormat formatter = new java.text.SimpleDateFormat(format);
	    java.util.Date retDate=null;
	    try {
	      retDate = formatter.parse(sDate);
	    }
	    catch (Exception ex) {
	    }
	    return retDate;
	  }
	
	/**
	 * Milliseconds -> hh:mm:ss
	 */
	public static String formatTimeString(long millisecond) {
		millisecond = millisecond < 0 ? -millisecond : millisecond; 
		
//		dateFormat.setTimeZone(TimeZone.getTimeZone("GMT + 0")); //BUG: less than 24 hours
//		return dateFormat.format(millisecond);
		
		
		int seconds = (int)(millisecond/1000);
		
		StringBuffer result = new StringBuffer("");
		int ss = seconds % 60;
		int mm = seconds/60%60;
		int hh = seconds/3600;
		
		if(hh == 0)
			result.append("00");
		else if(hh < 10)
			result.append("0").append(hh);
		else
			result.append(hh);
		
		result.append(":");
		
		if(mm == 0)
			result.append("00");
		else if(mm < 10)
			result.append("0").append(mm);
		else
			result.append(mm);
		
		result.append(":");
		
		if(ss == 0)
			result.append("00");
		else if(ss < 10)
			result.append("0").append(ss);
		else
			result.append(ss);
		
		return result.toString();
	}
	
	public static int getHour() {
	    Calendar cpcalendar = new GregorianCalendar();
	    cpcalendar.setTime(getDate());
	    return cpcalendar.get(Calendar.HOUR_OF_DAY);
	}
	
	public static int getHour(Date date) {
	    Calendar cpcalendar = new GregorianCalendar();
        cpcalendar.setTime(date);
        return cpcalendar.get(Calendar.HOUR_OF_DAY);
	}
	
	   public static int getHour(long date) {
	       return getHour(getDate(date));
	    }
	
	public static void main(String[] args) {
	    System.out.println(getHour());
	    
	    System.out.println(new Date(1304177003921l));
	    
	    System.out.println(System.currentTimeMillis());
	    
	    Calendar c = Calendar.getInstance();
	    c.set(Calendar.YEAR,2011);
	    c.set(Calendar.MONTH,3);
	    c.set(Calendar.DATE,30);
	    c.set(Calendar.HOUR_OF_DAY,23);
	    c.set(Calendar.MINUTE,23);
	    c.set(Calendar.SECOND,23);
	    long l = c.getTimeInMillis();
	    System.out.println(l);
		System.out.println(2>>4);
	    
	}

	//当前时间点距当天0时的毫秒数
	public static long getMillisecondInADay() {
		Calendar cl = Calendar.getInstance();
		return (cl.get(Calendar.HOUR_OF_DAY)*3600 + cl.get(Calendar.MINUTE)*60 + cl.get(Calendar.SECOND))*1000 + cl.get(Calendar.MILLISECOND);
	}

    public static long getTodayTime() {
        return DateUtils.getTime()-getMillisecondInADay();
    }
    
    public static String getPlainString(Date dt) {
        SimpleDateFormat bartDateFormat = new SimpleDateFormat("yy-MM-dd-HH-mm-ss");
        StringBuffer result = new StringBuffer("");
        String[] datearr = bartDateFormat.format(dt).split("-");
        result
        .append(datearr[0]).append("-")
        .append(datearr[1]).append("-")
        .append(datearr[2]).append("-")
        .append(" <a name=\"ta\">")
        .append(datearr[3]).append(":")
        .append(datearr[4]).append(":")
        .append(datearr[5]).append("</a>");
        return result.toString();
    }
    
    public static String getPlainString2(Date dt) {
        SimpleDateFormat bartDateFormat = new SimpleDateFormat("yy-MM-dd-HH-mm-ss");
        StringBuffer result = new StringBuffer("");
        String[] datearr = bartDateFormat.format(dt).split("-");
        result
        .append(datearr[0]).append("-")
        .append(datearr[1]).append("-")
        .append(datearr[2]).append(" ")
        .append(datearr[3]).append(":")
        .append(datearr[4]).append(":")
        .append(datearr[5]);
        return result.toString();
    }
    
    public static String getPlainString3(Date dt) {
        SimpleDateFormat bartDateFormat = new SimpleDateFormat("yy-MM-dd");
        StringBuffer result = new StringBuffer("");
        String[] datearr = bartDateFormat.format(dt).split("-");
        result
        .append(datearr[0]).append("-")
        .append(datearr[1]).append("-")
        .append(datearr[2]).append(" ");
        return result.toString();
    }
    
    public  static String  getPlainString4(Date dt) {
    	 SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	 String result = bartDateFormat.format(dt);
    	 return result;
    }

    /**
     * 将毫秒转成yyyy-MM-dd HH:mm:ss的时间格式
     * @param date     毫秒
     * @return
     */ 
    public static String parseLongtoTime(long date) {
    	return dateFormat2.format(new Date(date));
 	}
     
     /**
      * 将毫秒转成yyyyMMdd的时间格式
      * @param date     毫秒
      * @return
      */ 
    public static String parseLongtoTime2(long date) {
  		return dateFormat3.format(new Date(date));
  	}
    
    /**
     * 年龄转换为生日
     * @param age
     * @return
     */
    public static Date parseAgetoBirthday(int age) {
    	Calendar cpcalendar = new GregorianCalendar();
        cpcalendar.setTime(new Date());
        int year = cpcalendar.get(Calendar.YEAR) - age;
        cpcalendar.set(Calendar.YEAR, year);
        cpcalendar.set(Calendar.MONTH, 0);
        cpcalendar.set(Calendar.DAY_OF_MONTH, 1);
        return cpcalendar.getTime();
    }
    
    /**
     * 生日转换为年龄
     * @param birthday
     * @return
     */
    public static int parseBirthdaytoAge(Date birthday) {
    	Calendar nowDate = new GregorianCalendar();
    	nowDate.setTime(new Date());
    	Calendar birthdayDate = new GregorianCalendar();
    	birthdayDate.setTime(birthday);
    	return nowDate.get(Calendar.YEAR) - birthdayDate.get(Calendar.YEAR);
    }
    
    /**
     * date:Sep 19, 2012
     * author:maojunfeng
     * return:String 
     * description:根据当前本地时间和时区参数获取对应的时间字符串
     */
    public static String parseDateByTimeZone(String timezone){
    	Date d = new Date();
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(StringUtils.isNotEmpty(timezone)){
			s.setTimeZone(TimeZone.getTimeZone(timezone));			
		}
		
		return s.format(d);
    }
    
    /**
     * date:Sep 19, 2012
     * author:maojunfeng
     * return:String 
     * description:根据当前本地时间和时区参数获取对应的时间
     */
    public static Date getDateByTimeZone(String timezone){
    	Date d = new Date();
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(StringUtils.isNotEmpty(timezone)){
			s.setTimeZone(TimeZone.getTimeZone(timezone));			
		}
		String time = s.format(d);
		SimpleDateFormat s2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			d = s2.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return d;
    }
}

