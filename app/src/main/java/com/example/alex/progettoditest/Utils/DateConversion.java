package com.example.alex.progettoditest.Utils;

import java.text.Format;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Alex on 02/01/2018.
 */

public class DateConversion {

    public static String formatDateToString(Date date){
        Format format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ITALY);
        return format2.format(date);
    }

    public static Date formatStringToDate(String dateString){
        if(dateString.charAt(0) == '"' && dateString.charAt(dateString.length()-1) == '"'){
            dateString = dateString.substring(1,dateString.length()-1);
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ITALY);
        return  format.parse(dateString, new ParsePosition(0));
    }
}