import java.util.Calendar;
import java.util.GregorianCalendar;


public class DateAndTime {
	
	public static void main(String[] args) {
		
		Calendar cal = Calendar.getInstance();
//		cal = new GregorianCalendar();
		
		//cal.get(1);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		
		System.out.println(year + "년 " + (month + 1) + "월 "
				+ day + "일");
		System.out.println(dayOfWeek);
		
		int dayOfYear = cal.get(Calendar.DAY_OF_YEAR);
		System.out.println("오늘은 2015년의 " + dayOfYear + "일 째 날");
		
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int min = cal.get(Calendar.MINUTE);
		int sec = cal.get(Calendar.SECOND);
		
		System.out.println("현재시각: " + hour + ":" + min + ":" + sec);
	}
	
}













