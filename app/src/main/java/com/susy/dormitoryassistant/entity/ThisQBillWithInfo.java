package com.susy.dormitoryassistant.entity;

import java.util.List;

/**
 * Created by susy on 17/5/4.
 */

public class ThisQBillWithInfo {
    private String info;
    private List<ThisQBill> data;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<ThisQBill> getData() {
        return data;
    }

    public void setData(List<ThisQBill> data) {
        this.data = data;
    }
}
