package com.susy.dormitoryassistant.entity;

import java.util.List;

/**
 * Created by susy on 17/4/27.
 */

public class WaterOrders {
    private String info;
    private List<WaterOrder> data;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<WaterOrder> getData() {
        return data;
    }

    public void setData(List<WaterOrder> data) {
        this.data = data;
    }
}
