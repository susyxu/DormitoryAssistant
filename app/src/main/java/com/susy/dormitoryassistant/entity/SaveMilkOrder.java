package com.susy.dormitoryassistant.entity;

/**
 * Created by susy on 17/3/12.
 */

public class SaveMilkOrder {
    private String info;
    private MilkOrder data;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public MilkOrder getData() {
        return data;
    }

    public void setData(MilkOrder data) {
        this.data = data;
    }
}
