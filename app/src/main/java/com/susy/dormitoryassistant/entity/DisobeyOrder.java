package com.susy.dormitoryassistant.entity;

/**
 * Created by susy on 17/5/2.
 */

public class DisobeyOrder {
    private String disobedientRuleId;
    private String studentId;
    private String ruleId;
    private String userId;
    private String time;
    private String detail;
    private String status;
    private String uuid;

    public String getDisobedientRuleId() {
        return disobedientRuleId;
    }

    public void setDisobedientRuleId(String disobedientRuleId) {
        this.disobedientRuleId = disobedientRuleId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
