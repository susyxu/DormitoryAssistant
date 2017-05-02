package com.susy.dormitoryassistant.entity;

import java.util.Map;

/**
 * Created by susy on 17/5/2.
 */

public class DormStudentData {
    private String dormitoryId;
    private String dormitoryName;
    private String dormitoryNum;
    private Map<String,Student> studentMap;
    private String dormitoryLeader;

    public String getDormitoryId() {
        return dormitoryId;
    }

    public void setDormitoryId(String dormitoryId) {
        this.dormitoryId = dormitoryId;
    }

    public String getDormitoryName() {
        return dormitoryName;
    }

    public void setDormitoryName(String dormitoryName) {
        this.dormitoryName = dormitoryName;
    }

    public String getDormitoryNum() {
        return dormitoryNum;
    }

    public void setDormitoryNum(String dormitoryNum) {
        this.dormitoryNum = dormitoryNum;
    }

    public Map<String, Student> getStudentMap() {
        return studentMap;
    }

    public void setStudentMap(Map<String, Student> studentMap) {
        this.studentMap = studentMap;
    }

    public String getDormitoryLeader() {
        return dormitoryLeader;
    }

    public void setDormitoryLeader(String dormitoryLeader) {
        this.dormitoryLeader = dormitoryLeader;
    }
}
