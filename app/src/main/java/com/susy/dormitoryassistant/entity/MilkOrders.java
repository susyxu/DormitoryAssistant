package com.susy.dormitoryassistant.entity;

import java.util.List;

/**
 * Created by susy on 17/4/27.
 */

public class MilkOrders {
    private String info;
    private List<MilkOrder> data;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<MilkOrder> getData() {
        return data;
    }

    public void setData(List<MilkOrder> data) {
        this.data = data;
    }
}
