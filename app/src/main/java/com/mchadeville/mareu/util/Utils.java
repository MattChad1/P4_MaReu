package com.mchadeville.mareu.util;

import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Utils {

    public static String textFromTextInputLayout(TextInputLayout til) {
        return Objects.requireNonNull(til.getEditText()).getText().toString();
    }

    public static String listToStringRevert(List<String> list) {
        String re = "";
        for (int i= list.size() -1 ; i>=0; i--) {
            if (!re.isEmpty()) re += "\n";
            re += list.get(i);
        }
        return re;
    }

    public static String listToString(List<String> list) {
        String re = "";
        for (String st: list) {
            if (!re.isEmpty()) re += ", ";
            re += st;
        }
        return re;
    }

    public static Calendar dateStringToCalendar(String str) {
        String[] c = str.split("/");
        Calendar cdr = Calendar.getInstance();
        cdr.set(Integer.parseInt(c[2]), Integer.parseInt(c[1]) - 1, Integer.parseInt(c[0]));
        return cdr;
    }

    public static String calendarToString(Calendar cal) {
        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
        return format1.format(cal.getTime());
    }

    public static long daysBetween(Calendar startDate, Calendar endDate) {

        //Make sure we don't change the parameter passed
        Calendar newStart = Calendar.getInstance();
        newStart.setTimeInMillis(startDate.getTimeInMillis());
        newStart.set(Calendar.HOUR_OF_DAY, 0);
        newStart.set(Calendar.MINUTE, 0);
        newStart.set(Calendar.SECOND, 0);
        newStart.set(Calendar.MILLISECOND, 0);

        Calendar newEnd = Calendar.getInstance();
        newEnd.setTimeInMillis(endDate.getTimeInMillis());
        newEnd.set(Calendar.HOUR_OF_DAY, 0);
        newEnd.set(Calendar.MINUTE, 0);
        newEnd.set(Calendar.SECOND, 0);
        newEnd.set(Calendar.MILLISECOND, 0);

        long end = newEnd.getTimeInMillis();
        long start = newStart.getTimeInMillis();
        return TimeUnit.MILLISECONDS.toDays(Math.abs(end - start));
    }

}
