package com.susy.dormitoryassistant.utils;

import android.content.Context;
import android.widget.Toast;

import java.text.SimpleDateFormat;
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
     * 气泡提示
     */
    public static void showToast(Context context, String strvalue) {
        Toast toast = Toast.makeText(context, strvalue, Toast.LENGTH_SHORT);
        toast.show();
    }
}
