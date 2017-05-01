package com.susy.dormitoryassistant.entity;

/**
 * Created by susy on 17/4/27.
 */

public class MilkOrder {
    private int milkOrderId;
    private String milkOrderStatus;
    private String milkOrderTime;
    private String userId;
    private String studentId;
    private String milkOrderFinishTime;
    private String milkMonth;
    private String dormitoryId;
    private String milkOrderUuid;
    private String milkType;
    private String milkCount;

    public int getMilkOrderId() {
        return milkOrderId;
    }

    public void setMilkOrderId(int milkOrderId) {
        this.milkOrderId = milkOrderId;
    }

    public String getMilkOrderStatus() {
        return milkOrderStatus;
    }

    public void setMilkOrderStatus(String milkOrderStatus) {
        this.milkOrderStatus = milkOrderStatus;
    }

    public String getMilkOrderTime() {
        return milkOrderTime;
    }

    public void setMilkOrderTime(String milkOrderTime) {
        this.milkOrderTime = milkOrderTime;
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

    public String getMilkOrderFinishTime() {
        return milkOrderFinishTime;
    }

    public void setMilkOrderFinishTime(String milkOrderFinishTime) {
        this.milkOrderFinishTime = milkOrderFinishTime;
    }

    public String getMilkMonth() {
        return milkMonth;
    }

    public void setMilkMonth(String milkMonth) {
        this.milkMonth = milkMonth;
    }

    public String getDormitoryId() {
        return dormitoryId;
    }

    public void setDormitoryId(String dormitoryId) {
        this.dormitoryId = dormitoryId;
    }

    public String getMilkOrderUuid() {
        return milkOrderUuid;
    }

    public void setMilkOrderUuid(String milkOrderUuid) {
        this.milkOrderUuid = milkOrderUuid;
    }

    public String getMilkType() {
        return milkType;
    }

    public void setMilkType(String milkType) {
        this.milkType = milkType;
    }

    public String getMilkCount() {
        return milkCount;
    }

    public void setMilkCount(String milkCount) {
        this.milkCount = milkCount;
    }
}
