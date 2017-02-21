package com.susy.dormitoryassistant.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * 整个程序的开始运行的Application
 * Created by susy on 17/2/4.
 */
public class DormitoryApplication extends Application {
    private static DormitoryApplication mInstance;

    public static DormitoryApplication getmInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        Fresco.initialize(this);
    }
}
