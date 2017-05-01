package com.susy.dormitoryassistant.entity;

import java.util.List;

/**
 * Created by susy on 17/4/27.
 */

public class RepairOrders {
    private String info;
    private List<RepairOrder> data;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<RepairOrder> getData() {
        return data;
    }

    public void setData(List<RepairOrder> data) {
        this.data = data;
    }
}
