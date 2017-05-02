package com.susy.dormitoryassistant.entity;

import java.util.List;

/**
 * Created by susy on 17/5/1.
 */

public class Dorms {
    private String info;
    private List<String> data;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
