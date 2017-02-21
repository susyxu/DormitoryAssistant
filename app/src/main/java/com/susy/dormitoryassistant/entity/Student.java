package com.susy.dormitoryassistant.entity;

/**
 * Created by susy on 17/1/19.
 */

public class Student {
    private String studentId;

    /**
     * 姓名
     */
    private String studentName;

    /**
     * 密码
     */
    private String studentPassword;

    /**
     * 年龄
     */
    private String studentAge;

    /**
     * 性别
     */
    private String studentSex;

    /**
     * 学院？我也记不清当时为什么叫college了
     */
    private String studentCollege;

    /**
     * 专业
     */
    private String studentMajor;

    /**
     * 班级
     */
    private String studentClass;

    /**
     * 联系方式
     */
    private String studentPhone;

    /**
     * 学生状态
     */
    private String studentStatus;

    /**
     * 寝室，如明德1-530
     */
    private String dormitoryId;

    /**
     * 床位号
     */
    private String bedNum;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentPassword() {
        return studentPassword;
    }

    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword;
    }

    public String getStudentAge() {
        return studentAge;
    }

    public void setStudentAge(String studentAge) {
        this.studentAge = studentAge;
    }

    public String getStudentSex() {
        return studentSex;
    }

    public void setStudentSex(String studentSex) {
        this.studentSex = studentSex;
    }

    public String getStudentCollege() {
        return studentCollege;
    }

    public void setStudentCollege(String studentCollege) {
        this.studentCollege = studentCollege;
    }

    public String getStudentMajor() {
        return studentMajor;
    }

    public void setStudentMajor(String studentMajor) {
        this.studentMajor = studentMajor;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public String getStudentPhone() {
        return studentPhone;
    }

    public void setStudentPhone(String studentPhone) {
        this.studentPhone = studentPhone;
    }

    public String getStudentStatus() {
        return studentStatus;
    }

    public void setStudentStatus(String studentStatus) {
        this.studentStatus = studentStatus;
    }

    public String getDormitoryId() {
        return dormitoryId;
    }

    public void setDormitoryId(String dormitoryId) {
        this.dormitoryId = dormitoryId;
    }

    public String getBedNum() {
        return bedNum;
    }

    public void setBedNum(String bedNum) {
        this.bedNum = bedNum;
    }
}
