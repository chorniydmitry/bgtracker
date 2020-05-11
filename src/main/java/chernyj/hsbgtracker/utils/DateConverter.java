package chernyj.hsbgtracker.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {
	public static final String DDMMYYYY_FORMAT = "yyyy-MM-dd";
	public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";
	public static final int HS_LAST_TIME_SYMBOLS_TO_REMOVE = 4;

	public static String getCurrentDateString() {

		String pattern = DDMMYYYY_FORMAT;
		String dateInString = new SimpleDateFormat(pattern).format(new Date());

		return dateInString;
	}

	public static Date convert(String dateString) {
		String dateStr = 
				dateString.substring(0, 
				dateString.length() - 
				HS_LAST_TIME_SYMBOLS_TO_REMOVE);
		
		DateFormat formatter = new SimpleDateFormat(DEFAULT_PATTERN);

		Date date = null;

		try {
			date = formatter.parse(getCurrentDateString() + " " + dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date;
	}

}
