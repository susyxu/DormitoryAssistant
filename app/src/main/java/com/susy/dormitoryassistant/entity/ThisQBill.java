package com.susy.dormitoryassistant.entity;

/**
 * 水电费的展示类
 */
public class ThisQBill {
	private String dormitoryId;
	private String time;

	private double waterUse;
	private double elecUse;

	private double waterPrice;
	private double elecPrice;

	private String waterMoney;
	private String elecMoney;

	private Cost preRecord;
	private Cost nextRecord;

	public String getDormitoryId() {
		return dormitoryId;
	}

	public void setDormitoryId(String dormitoryId) {
		this.dormitoryId = dormitoryId;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public double getWaterUse() {
		return waterUse;
	}

	public void setWaterUse(double waterUse) {
		this.waterUse = waterUse;
	}

	public double getElecUse() {
		return elecUse;
	}

	public void setElecUse(double elecUse) {
		this.elecUse = elecUse;
	}

	public double getWaterPrice() {
		return waterPrice;
	}

	public void setWaterPrice(double waterPrice) {
		this.waterPrice = waterPrice;
	}

	public double getElecPrice() {
		return elecPrice;
	}

	public void setElecPrice(double elecPrice) {
		this.elecPrice = elecPrice;
	}

	public String getWaterMoney() {
		return waterMoney;
	}

	public void setWaterMoney(String waterMoney) {
		this.waterMoney = waterMoney;
	}

	public String getElecMoney() {
		return elecMoney;
	}

	public void setElecMoney(String elecMoney) {
		this.elecMoney = elecMoney;
	}

	public Cost getPreRecord() {
		return preRecord;
	}

	public void setPreRecord(Cost preRecord) {
		this.preRecord = preRecord;
	}

	public Cost getNextRecord() {
		return nextRecord;
	}

	public void setNextRecord(Cost nextRecord) {
		this.nextRecord = nextRecord;
	}
}
