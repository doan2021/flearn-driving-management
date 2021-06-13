package com.doanfpt.management.application.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Common {

    public static float percentQuestion(int correctNumber, int incorrectNumber) {
        return (correctNumber * 100.0f) / (correctNumber + incorrectNumber);
    }
    
    public static String getFirstName(String fullNameGoogle) {
        if (fullNameGoogle == null || "".equals(fullNameGoogle)) {
            return fullNameGoogle;
        }
        String[] name = fullNameGoogle.split(" ");
        return name[name.length - 1];
    }
    
    public static String getLastName(String fullNameGoogle) {
        if (fullNameGoogle == null || "".equals(fullNameGoogle)) {
            return fullNameGoogle;
        }
        String[] name = fullNameGoogle.split(" ");
        String lastName = "";
        for(int i =0; i< name.length -1; i++) {
            if (i == name.length -2) {
                lastName = lastName.concat(name[i]);
            } else {
                lastName = lastName.concat(name[i]).concat(" ");
            }
        }
        return lastName;
    }
    
    public static Date stringToDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date dateReturn = new Date();
        try {
            dateReturn = dateFormat.parse(dateString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateReturn;
    }
    
    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }
}
