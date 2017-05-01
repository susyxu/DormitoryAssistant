package com.susy.dormitoryassistant.entity;

/**
 * Created by susy on 17/3/12.
 */

public class SaveRepairOrder {
    private String info;
    private RepairOrder data;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public RepairOrder getData() {
        return data;
    }

    public void setData(RepairOrder data) {
        this.data = data;
    }
}
