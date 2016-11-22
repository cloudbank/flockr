package com.anubis.phlix.util;

import android.content.Context;
import android.text.format.DateUtils;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtility {

    public static String relativeTime(String timestamp, Context c) {
        Calendar cal = Calendar.getInstance();
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
            cal.setTime(df.parse(timestamp));
            Log.d("DEBUG", timestamp + ":" + df.parse(timestamp));
        } catch (ParseException e) {
            e.printStackTrace();
            Log.d("DEBUG", "relativeTime" + e);
        }
        String s = DateUtils.getRelativeDateTimeString(c,
                cal.getTimeInMillis(), DateUtils.SECOND_IN_MILLIS,
                DateUtils.DAY_IN_MILLIS, 0).toString();
        s = s.substring(0, s.indexOf(','));
        s = manicureTimeFromRelative(s);
        return s;
    }

    private static String manicureTimeFromRelative(String s) {

        if (s.contains("seconds") || s.contains("second")) {
            s = s.substring(0, s.indexOf("s") + 1);
        } else if (s.contains("minutes") || s.contains("minute")) {
            s = s.substring(0, s.indexOf("m") + 1);
        } else if (s.contains("hours") || s.contains("hour")) {
            s = s.substring(0, s.indexOf("h") + 1);
        }
        s = s.replaceAll(" ", "");
        if (s.contains("in")) {
            s = s.substring(s.indexOf("n") + 1);
        }
        return s;
    }

}
