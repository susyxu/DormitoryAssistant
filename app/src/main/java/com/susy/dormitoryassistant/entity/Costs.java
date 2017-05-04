package com.susy.dormitoryassistant.entity;

import java.util.List;

/**
 * Created by susy on 17/5/4.
 */

public class Costs {
    private String info;
    private List<Cost> data;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<Cost> getData() {
        return data;
    }

    public void setData(List<Cost> data) {
        this.data = data;
    }
}
