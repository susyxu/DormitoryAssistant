package com.susy.dormitoryassistant.entity;

import java.util.List;

/**
 * Created by susy on 17/4/27.
 */

public class SaveDisobeyOrder {
    private String info;
    private DisobeyOrder data;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public DisobeyOrder getData() {
        return data;
    }

    public void setData(DisobeyOrder data) {
        this.data = data;
    }
}
