package com.susy.dormitoryassistant.entity;

import java.util.List;

/**
 * Created by susy on 17/5/4.
 */

public class Scores {
    private String info;
    private List<Score> data;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<Score> getData() {
        return data;
    }

    public void setData(List<Score> data) {
        this.data = data;
    }
}
