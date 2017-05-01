package com.susy.dormitoryassistant.entity;

/**
 * Created by susy on 17/4/28.
 */

public class RepairOrder {
    private String repairOrderId;
    private String repairOrderStatus;
    private String repairOrderTime;
    private String userId;
    private String studentId;
    private String repairOrderFinishTime;
    private String repairOrderItem;
    private String repairOrderDetail;
    private String dormitoryId;
    private String repairOrderUuid;
    private String repairOrderFreeTime;

    public String getRepairOrderId() {
        return repairOrderId;
    }

    public void setRepairOrderId(String repairOrderId) {
        this.repairOrderId = repairOrderId;
    }

    public String getRepairOrderStatus() {
        return repairOrderStatus;
    }

    public void setRepairOrderStatus(String repairOrderStatus) {
        this.repairOrderStatus = repairOrderStatus;
    }

    public String getRepairOrderTime() {
        return repairOrderTime;
    }

    public void setRepairOrderTime(String repairOrderTime) {
        this.repairOrderTime = repairOrderTime;
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

    public String getRepairOrderFinishTime() {
        return repairOrderFinishTime;
    }

    public void setRepairOrderFinishTime(String repairOrderFinishTime) {
        this.repairOrderFinishTime = repairOrderFinishTime;
    }

    public String getRepairOrderItem() {
        return repairOrderItem;
    }

    public void setRepairOrderItem(String repairOrderItem) {
        this.repairOrderItem = repairOrderItem;
    }

    public String getRepairOrderDetail() {
        return repairOrderDetail;
    }

    public void setRepairOrderDetail(String repairOrderDetail) {
        this.repairOrderDetail = repairOrderDetail;
    }

    public String getDormitoryId() {
        return dormitoryId;
    }

    public void setDormitoryId(String dormitoryId) {
        this.dormitoryId = dormitoryId;
    }

    public String getRepairOrderUuid() {
        return repairOrderUuid;
    }

    public void setRepairOrderUuid(String repairOrderUuid) {
        this.repairOrderUuid = repairOrderUuid;
    }

    public String getRepairOrderFreeTime() {
        return repairOrderFreeTime;
    }

    public void setRepairOrderFreeTime(String repairOrderFreeTime) {
        this.repairOrderFreeTime = repairOrderFreeTime;
    }
}
