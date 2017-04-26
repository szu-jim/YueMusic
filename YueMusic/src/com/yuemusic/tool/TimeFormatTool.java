package com.yuemusic.tool;

public class TimeFormatTool {
	/**
	 * 将秒转换为时分秒，输入秒，返回时分秒的字符串
	 * @param minuteParam
	 * @return
	 */
	public static String convertTimeFormat(long minuteParam) {
		String timeString = null;
		long hour = 0;
		long minute = 0;
		long second = 0;
		if (minuteParam <= 0){
			return "00:00";
		}else {
			minute = minuteParam / 60;
			if (minute < 60) {
				second = minuteParam % 60;
				timeString = unitFormat(minute) + ":" + unitFormat(second);
			} else {
				hour = minute / 60;
				if (hour > 99){
					return "99:59:59";
				}
				minute = minute % 60;
				second = minuteParam - hour * 3600 - minute * 60;
				timeString = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
			}
		}
		return timeString;
	}

	private static String unitFormat(long param) {
		String returnString = null;
		if(param >= 0 && param < 10){
			returnString = "0" + Long.toString(param);
		} else {
			returnString = "" + param;
		}
		return returnString;
	}
}
