package utils;

import java.util.GregorianCalendar;

public class DateUtils {

	public static String formatDate(String date) {
		String[] splitDate = date.split(" ");
		String[] splittedDate = splitDate[0].split("-");
		return splittedDate[2] + " " + getMonthToChar(splittedDate[1]) + " " + splittedDate[0];
	}
	
	public static String getMonthToChar(String month) {
		String[] months = {"gennaio", "febbraio", "marzo", "aprile", "maggio", "giugno", "luglio",
						   "agosto", "settembre", "ottobre", "novembre", "dicembre"};
		int index = Integer.parseInt(month);
		return months[index-1];
	}
	
	public static int getCurrentYear() {
		return GregorianCalendar.getInstance().get(GregorianCalendar.YEAR);
	}
	
	public static int getCurrentMonth() {
		return GregorianCalendar.getInstance().get(GregorianCalendar.MONTH)+1;
	}
	
	public static int getCurrentDay() {
		return GregorianCalendar.getInstance().get(GregorianCalendar.DAY_OF_MONTH);
	}
	
	public static String getDate(String giorno, String mese, String anno) {
		String[] months = {"gen", "feb", "mar", "apr", "mag", "giu", "lug", "ago", "set", "ott", "nov", "dic"};
		return giorno + "-" + months[Integer.parseInt(mese)-1] + "-" + anno;
	}
	
}
