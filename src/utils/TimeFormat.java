package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeFormat {
	public static String formatDate(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	public static String formatTime(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		return sdf.format(date);
	}

	public static String formatHour(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("HH");
		return sdf.format(date);
	}


	public static Date toDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}


	public static Date toDay(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}


	public static Date toTime(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH");
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static int getWeek(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("EEE");
		String week = sdf.format(date);
		if (!(week.equals("星期六")||week.equals("星期日"))){
			return 1;
		}
		return 0;
	}
}
