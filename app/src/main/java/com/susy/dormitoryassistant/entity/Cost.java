package com.susy.dormitoryassistant.entity;

/**
 * Created by susy on 17/5/4.
 */

public class Cost {
    private int recordId;

    private String userId;

    private String dormitoryId;

    private String waterCount;

    private String elecCount;

    private String time;

    private String quarter;

    private String currentElecPrice;

    private String currentWaterPrice;

    private String uuid;

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDormitoryId() {
        return dormitoryId;
    }

    public void setDormitoryId(String dormitoryId) {
        this.dormitoryId = dormitoryId;
    }

    public String getWaterCount() {
        return waterCount;
    }

    public void setWaterCount(String waterCount) {
        this.waterCount = waterCount;
    }

    public String getElecCount() {
        return elecCount;
    }

    public void setElecCount(String elecCount) {
        this.elecCount = elecCount;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    public String getCurrentElecPrice() {
        return currentElecPrice;
    }

    public void setCurrentElecPrice(String currentElecPrice) {
        this.currentElecPrice = currentElecPrice;
    }

    public String getCurrentWaterPrice() {
        return currentWaterPrice;
    }

    public void setCurrentWaterPrice(String currentWaterPrice) {
        this.currentWaterPrice = currentWaterPrice;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
