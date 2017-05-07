package com.susy.dormitoryassistant.utils;

import android.content.Context;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by susy on 17/3/5.
 */

public class UtilTools {
    /**
     * 格式化一种今天的年月日星期
     */
    public static String getCurrentTime_ch() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 EEEE", Locale.CHINA);
        return sdf.format(new Date().getTime());
    }

    /**
     * 当前季度
     *
     * @return 返回季度
     */
    public static String getTimeAndQ() {
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1;
        int q = 0;
        if (month == 1 || month == 2 || month == 3) {
            q = 1;
        } else if (month == 4 || month == 5 || month == 6) {
            q = 2;
        } else if (month == 7 || month == 8 || month == 9) {
            q = 3;
        } else if (month == 10 || month == 11 || month == 12) {
            q = 4;
        }
        return year + "-" + String.valueOf(q);
    }

    /**
     * 当前周数
     */
    public static String getCurrentWeekOfYear() {
        Calendar curr = Calendar.getInstance();
        System.out.println(curr.getFirstDayOfWeek());
        int week = curr.get(Calendar.WEEK_OF_YEAR);
        int year = curr.get(Calendar.YEAR);
        return year + "-" + week;
    }

    /**
     * 气泡提示
     */
    public static void showToast(Context context, String strvalue) {
        Toast toast = Toast.makeText(context, strvalue, Toast.LENGTH_SHORT);
        toast.show();
    }
}
