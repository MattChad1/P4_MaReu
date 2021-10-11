package com.mchadeville.mareu.util;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Calendar;

public class UtilsTest {

    @Test
    public void dateStringToCalendar() {
        String dateStr = "10/10/2021";
        Calendar dateRes = Utils.dateStringToCalendar(dateStr);

        assertEquals(2021, dateRes.get(Calendar.YEAR));
        assertEquals(9, dateRes.get(Calendar.MONTH)); // Calendar.MONTH de 0 à 11
        assertEquals(10, dateRes.get(Calendar.DAY_OF_MONTH));
    }

    @Test
    public void calendarToString() {
        Calendar date1 = Calendar.getInstance();
        date1.set(2021, 10, 10);
        String res = Utils.calendarToString(date1);

        assertEquals("10/11/2021", res);
    }

    @Test
    public void daysBetween() {
        Calendar date1 = Calendar.getInstance();
        date1.set(2021, 10, 10);
        Calendar date2 = Calendar.getInstance();
        date2.set(2021, 10, 15);
        assertEquals(5, Utils.daysBetween(date1, date2));
    }
}