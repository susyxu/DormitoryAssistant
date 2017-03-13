package com.susy.dormitoryassistant.entity;

/**
 * Created by susy on 17/3/12.
 */

public class SaveWaterOrder {
    private String info;
    private WaterOrder data;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public WaterOrder getData() {
        return data;
    }

    public void setData(WaterOrder data) {
        this.data = data;
    }
}
