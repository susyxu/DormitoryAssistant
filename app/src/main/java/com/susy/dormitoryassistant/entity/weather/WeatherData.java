package com.susy.dormitoryassistant.entity.weather;

import java.util.List;

/**
 * Created by susy on 17/5/8.
 */

public class WeatherData {
    private String wendu;

    private String ganmao;

    private List<Forecast> forecast ;

    private Yesterday yesterday;

    private String aqi;

    private String city;

    public String getWendu() {
        return wendu;
    }

    public String getGanmao() {
        return ganmao;
    }

    public List<Forecast> getForecast() {
        return forecast;
    }

    public Yesterday getYesterday() {
        return yesterday;
    }

    public String getAqi() {
        return aqi;
    }

    public String getCity() {
        return city;
    }
}
