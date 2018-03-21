package com.caiw.utils;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间处理工具类
 * 字符串转时间
 * 时间转字符串
 *
 * @author 蔡维
 */
public class DateUtil {
	/**
	 * 传入字符串时间并将字符串时间转化为日期
	 *
	 * @param time:字符串时间
	 * @return 返回日期
	 */
	public static  Date timeToDate(String time){
		if (time.length() > 10) {
			try {
				Date date = null;
				if (time.charAt(4) == '-') {//当分隔符位'-'时
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					date = format.parse(time);
					return date;
				} else if (time.charAt(4) == '/') {//当分隔符位'/'时
					SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					date = format.parse(time);
					return date;
				} else if (Character.isDigit(time.charAt(4))) {//当没有分隔符时
					SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
					date = format.parse(time);
					return date;
				} else {
					return null;
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println("程序错误");
				return null;
			}
		} else{
			try {
				Date date = null;
				if (time.charAt(4) == '-') {//当分隔符位'-'时
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					date = format.parse(time);
					return date;
				} else if (time.charAt(4) == '/') {//当分隔符位'/'时
					SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
					date = format.parse(time);
					return date;
				} else if (Character.isDigit(time.charAt(4))) {//无分隔符时
					SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
					date = format.parse(time);
					return date;
				} else {
					return null;
				}
			} catch (Exception e) {

				return null;
			}
		}
	}

	/**
	 * date转化为字符串
	 * @param date
	 * @return
	 */
	public static String dateToTime(Date date){
		try {
			//调用format方法转换
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String time = formatter.format(date);
			return time;
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("代码出错！");
		}
		return null;
	}

}
