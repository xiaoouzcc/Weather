package project.release.sdk.com.weather.utils;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SuppressLint("SimpleDateFormat")
public class DateUtils {

	private final static ThreadLocal<SimpleDateFormat> dateFormater = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd HH");
		}
	};

	private final static ThreadLocal<SimpleDateFormat> dateFormater3 = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm");
		}
	};

	private final static ThreadLocal<SimpleDateFormat> dateFormater4 = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("HH:mm");
		}
	};

	private final static ThreadLocal<SimpleDateFormat> dateFormater2 = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd");
		}
	};

	/**
	 * date转换成string
	 * 
	 * @author 左成城
	 * @data 2015-6-9 上午10:43:50
	 * @param style
	 * @param date
	 * @return
	 */
	public static String dateToStr(String style, Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat(style);
		return formatter.format(date);
	}

	/**
	 * 判断是否是同一天,只比较年月日
	 * 
	 * @author 左成城
	 * @data 2015-6-8 下午5:09:42
	 * @param sdate
	 * @return
	 */
	public static boolean isSampleDay(String sdate) {
		Date time = toDate(sdate);
		if (time == null) {
			return false;
		}
		Calendar cal = Calendar.getInstance();
		// 判断是否是同一天
		String curDate = dateFormater2.get().format(cal.getTime());
		String paramDate = dateFormater2.get().format(time);
		if (curDate.equals(paramDate))
			return true;
		else
			return false;
	}

	/**
	 * 将字符串转为日期类型，带小时和分
	 * 
	 * @author 左成城
	 * @data 2015-6-8 下午5:11:23
	 * @param sdate
	 * @return
	 */
	public static Date toDateHour(String sdate) {
		try {
			return dateFormater.get().parse(sdate);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 将字符串转为日期类型,年月日
	 * 
	 * @author 左成城
	 * @data 2015-6-8 下午5:11:23
	 * @param sdate
	 * @return
	 */
	public static Date toDate(String sdate) {
		try {
			return dateFormater2.get().parse(sdate);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 转换成字符串。。年月日
	 * 
	 * @author 左成城
	 * @data 2015-6-8 下午5:28:18
	 * @param date
	 * @return
	 */
	public static String toString(Date date) {
		String str = dateFormater2.get().format(date);
		return str;
	}

	/**
	 * 转换成字符串。。年月日小时
	 * 
	 * @author 左成城
	 * @data 2015-6-8 下午5:28:18
	 * @param date
	 * @return
	 */
	public static String toStringHour(Date date) {
		String str = dateFormater.get().format(date);
		return str;
	}

	public static String toDateHourMin(Date date) {
		String str = dateFormater3.get().format(date);
		return str;
	}

	public static String toHourMin(Date date) {
		String str = dateFormater4.get().format(date);
		return str;
	}

	/**
	 * 判断选择的时间大于当前时间
	 * 
	 * @author 左成城
	 * @data 2015-6-8 下午5:13:31
	 * @param date
	 * @return
	 */
	public static boolean getTamp(Date preDate, Date Choosedate) {
		long currentTamp = preDate.getTime();
		long chooseTamp = Choosedate.getTime();
		if (chooseTamp > currentTamp)
			return true;
		else
			return false;
	}

	/**
	 * 判断选择的时间大于或者等于当前时间
	 * 
	 * @author 左成城
	 * @data 2015-6-8 下午5:13:31
	 * @param date
	 * @return
	 */
	public static boolean getTampEquals(Date preDate, Date Choosedate) {
		long currentTamp = preDate.getTime();
		long chooseTamp = Choosedate.getTime();
		if (chooseTamp >= currentTamp)
			return true;
		else
			return false;
	}

	/**
	 * 获取当前是周几
	 * 
	 * @author shuwanli
	 * @data 2015年10月20日 下午3:43:50
	 * @param date
	 * @return
	 */
	public static String getWeekOfDate(Date date) {
		String[] weekDays = { "周日", "周一", "周二", "周三", "周四", "周五", "周六" };
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0) {
			w = 0;
		}
		return weekDays[w];
	}
}
