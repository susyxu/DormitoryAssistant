package com.susy.dormitoryassistant.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.susy.dormitoryassistant.entity.LoginStudent;
import com.susy.dormitoryassistant.entity.LoginUser;

/**
 * 整个程序的开始运行的Application
 * Created by susy on 17/2/4.
 */
public class DormitoryApplication extends Application {
    private static DormitoryApplication mInstance;

    public static DormitoryApplication getmInstance() {
        return mInstance;
    }

    //全局参数
    private LoginStudent globalStudent = null;
    private LoginUser globalUser = null;

    public LoginStudent getGlobalStudent() {
        return globalStudent;
    }

    public void setGlobalStudent(LoginStudent globalStudent) {
        this.globalStudent = globalStudent;
    }

    public LoginUser getGlobalUser() {
        return globalUser;
    }

    public void setGlobalUser(LoginUser globalUser) {
        this.globalUser = globalUser;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        Fresco.initialize(this);
    }
}
