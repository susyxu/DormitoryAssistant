package com.susy.dormitoryassistant.entity;

/**
 * 通知类
 */
public class Notice {
    /**
     * 通知编号
     */
    private Integer noticeId;
    /**
     * 通知类型
     */
    private String type;
    /**
     * 通知的时间
     */
    private String time;
    /**
     * 通知细节
     */
    private String detail;
    /**
     * 谁添加的通知
     */
    private String userId;

    public Integer getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Integer noticeId) {
        this.noticeId = noticeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}