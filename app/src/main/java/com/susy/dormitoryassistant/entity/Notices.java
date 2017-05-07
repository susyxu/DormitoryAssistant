package com.susy.dormitoryassistant.entity;

/**
 * 通知类
 */
public class Notices {
    private String info;
    private NoticeVO data;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public NoticeVO getData() {
        return data;
    }

    public void setData(NoticeVO data) {
        this.data = data;
    }
}