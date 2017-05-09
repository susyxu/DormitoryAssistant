package com.susy.dormitoryassistant.entity.weather;

/**
 * Created by susy on 17/5/8.
 */

public class WeatherRoot {
    private String desc;

    private int status;

    private WeatherData data;

    public String getDesc() {
        return desc;
    }

    public int getStatus() {
        return status;
    }

    public WeatherData getData() {
        return data;
    }
}
