package com.ixigo.travelbooking.util;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class getDateFields {
	
 public String getDay(String dateValue)
 {
     

     DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MMM-yy", Locale.ENGLISH);
     LocalDate date = LocalDate.parse(dateValue, inputFormatter);

     String day = String.valueOf(date.getDayOfMonth());
     
     return day;
 }
 public String getMonthYear(String dateValue) {
	 
	 DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MMM-yy", Locale.ENGLISH);
     LocalDate date = LocalDate.parse(dateValue, inputFormatter);
     String monthYear = date.format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH));

     return monthYear;
 }
 
 public String getDateXpath(String dateFromExcel)
 {
	 String dayNumber=getDay(dateFromExcel);
		String departureDateValue = String.format(
				"(//div[@class='css-g5y9jx' and @style='width: 100%%; height: 336px;'])[1]//div[text()='%s']/ancestor::div[@style='cursor: pointer;']",
				 dayNumber);
		System.out.println(departureDateValue);
		return departureDateValue;
 }
}



