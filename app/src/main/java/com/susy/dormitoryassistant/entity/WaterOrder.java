package com.susy.dormitoryassistant.entity;

/**
 * Created by susy on 17/3/12.
 */

public class WaterOrder {
    private int waterOrderId;
    private String waterOrderStatus;
    private String waterOrderTime;
    private String userId;
    private String studentId;
    private String waterOrderFinishTime;
    private String waterCount;
    private String dormitoryId;

    public int getWaterOrderId() {
        return waterOrderId;
    }

    public void setWaterOrderId(int waterOrderId) {
        this.waterOrderId = waterOrderId;
    }

    public String getWaterOrderStatus() {
        return waterOrderStatus;
    }

    public void setWaterOrderStatus(String waterOrderStatus) {
        this.waterOrderStatus = waterOrderStatus;
    }

    public String getWaterOrderTime() {
        return waterOrderTime;
    }

    public void setWaterOrderTime(String waterOrderTime) {
        this.waterOrderTime = waterOrderTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getWaterOrderFinishTime() {
        return waterOrderFinishTime;
    }

    public void setWaterOrderFinishTime(String waterOrderFinishTime) {
        this.waterOrderFinishTime = waterOrderFinishTime;
    }

    public String getWaterCount() {
        return waterCount;
    }

    public void setWaterCount(String waterCount) {
        this.waterCount = waterCount;
    }

    public String getDormitoryId() {
        return dormitoryId;
    }

    public void setDormitoryId(String dormitoryId) {
        this.dormitoryId = dormitoryId;
    }
}
