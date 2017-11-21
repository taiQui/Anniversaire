/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anniversaire;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author greg1
 * 
 * Convertisseur entre Calendar et String et inversement
 * 
 */
public class Convertisseur {
    public static Calendar stringToCalendar(String stringDate, String datePattern) {
    if (stringDate == null) {
      return null;
    }
    Calendar calendar = new GregorianCalendar();
    try {
      Timestamp newDate = Timestamp.valueOf(stringDate);
      calendar.setTime(newDate);
    }
    catch (Exception e) {
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern);
      try {
        calendar.setTime(simpleDateFormat.parse(stringDate));
      }
      catch (ParseException pe) {
        calendar = null;
      }
    }
    return calendar;
  }
    
    
     public static String calendarToString(Calendar calendar, String datePattern) {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern);
    String calendarString = simpleDateFormat.format(calendar.getTime());
    return calendarString;
    
  }
    
}
