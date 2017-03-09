package com.susy.dormitoryassistant.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by susy on 17/3/5.
 */

public class UtilTools {
    public static String getCurrentTime_ch() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 EEEE", Locale.CHINA);
        return sdf.format(new Date().getTime());
    }
}
