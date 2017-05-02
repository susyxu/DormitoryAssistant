package com.susy.dormitoryassistant.entity;

import java.util.List;

/**
 * Created by susy on 17/4/27.
 */

public class DisobeyOrders {
    private String info;
    private List<DisobeyOrder> data;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<DisobeyOrder> getData() {
        return data;
    }

    public void setData(List<DisobeyOrder> data) {
        this.data = data;
    }
}
