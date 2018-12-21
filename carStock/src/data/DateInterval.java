package data;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateInterval {
	private Calendar initialDate;
	private Calendar finalDate;
	private boolean empty;
	
	public DateInterval(String p) {
//		p = p.replaceAll("\\s+","");
		if(p.equals(""))
		{
			initialDate = new GregorianCalendar();
			finalDate = new GregorianCalendar();
			empty = true;
			return;
		}
		try {
//			System.out.println("-"+p+"-");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String[] intervalString = p.split(":");
			
			initialDate = new GregorianCalendar();
//			System.out.println(intervalString[0]);
			initialDate.setTime(sdf.parse(intervalString[0]));
			
			finalDate = new GregorianCalendar();
			finalDate.setTime(sdf.parse(intervalString[1]));
			
			empty = false;
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public DateInterval(Calendar date1, Calendar date2) {
		double comparation = date1.compareTo(date2);
		if(comparation < 0) {
			initialDate = date1;
			finalDate = date2;
			empty = false;
		} else if (comparation > 0){
			initialDate = date2;
			finalDate = date1;
			empty = false;
		} else {
			initialDate = date1;
			finalDate = date2;
			empty = true;
		}
		
	}
	
	public boolean dateInInterval(Calendar date) {
		if(empty || (initialDate.compareTo(date) >= 0 && finalDate.compareTo(date) <= 0))
			return true;
		return false;
	}
	
	@Override
	public String toString() {
		if(empty)
			return "true";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(initialDate.getTime()) + ":" + sdf.format(finalDate.getTime());
	}
}
