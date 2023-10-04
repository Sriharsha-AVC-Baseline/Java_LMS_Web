package com.itt.project;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class getDate {
	
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	public Date readDate(String dateInString) throws ParseException
	{
		Date date = format.parse(dateInString);
		return date;
	}
	
	public String returnDate(Date date)
	{
		String strDate = format.format(date);
		return strDate;
	}
	

}
