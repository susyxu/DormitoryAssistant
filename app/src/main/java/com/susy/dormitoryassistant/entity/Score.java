package com.susy.dormitoryassistant.entity;

/**
 * Created by susy on 17/5/7.
 */

public class Score {
    private int gradeId;

    private String dormitoryId;

    private String userId;

    private String bedClean;

    private String floorClean;

    private String deskClean;

    private String wcClean;

    private String balconyClean;

    private String time;

    private String week;

    private String uuid;

    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public String getDormitoryId() {
        return dormitoryId;
    }

    public void setDormitoryId(String dormitoryId) {
        this.dormitoryId = dormitoryId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBedClean() {
        return bedClean;
    }

    public void setBedClean(String bedClean) {
        this.bedClean = bedClean;
    }

    public String getFloorClean() {
        return floorClean;
    }

    public void setFloorClean(String floorClean) {
        this.floorClean = floorClean;
    }

    public String getDeskClean() {
        return deskClean;
    }

    public void setDeskClean(String deskClean) {
        this.deskClean = deskClean;
    }

    public String getWcClean() {
        return wcClean;
    }

    public void setWcClean(String wcClean) {
        this.wcClean = wcClean;
    }

    public String getBalconyClean() {
        return balconyClean;
    }

    public void setBalconyClean(String balconyClean) {
        this.balconyClean = balconyClean;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}