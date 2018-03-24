package com.ats.adminpanel.model.franchisee;

import java.io.Serializable;


public class FrTarget{
	
	private boolean error;
	
	private String message;

	private int frTargetId;
	
	private int frId;
	
	private int frTargetMonth;
	
	private int frTargetYear;
	
	private float frTargetAmt;
	
	private float frAchievedSale;
	
	private String frAward;
	
	private String remark;
	
	private int status;
	
	private int delStatus;

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getFrTargetId() {
		return frTargetId;
	}

	public void setFrTargetId(int frTargetId) {
		this.frTargetId = frTargetId;
	}

	public int getFrId() {
		return frId;
	}

	public void setFrId(int frId) {
		this.frId = frId;
	}

	public int getFrTargetMonth() {
		return frTargetMonth;
	}

	public void setFrTargetMonth(int frTargetMonth) {
		this.frTargetMonth = frTargetMonth;
	}

	public int getFrTargetYear() {
		return frTargetYear;
	}

	public void setFrTargetYear(int frTargetYear) {
		this.frTargetYear = frTargetYear;
	}

	public float getFrTargetAmt() {
		return frTargetAmt;
	}

	public void setFrTargetAmt(float frTargetAmt) {
		this.frTargetAmt = frTargetAmt;
	}

	public float getFrAchievedSale() {
		return frAchievedSale;
	}

	public void setFrAchievedSale(float frAchievedSale) {
		this.frAchievedSale = frAchievedSale;
	}

	public String getFrAward() {
		return frAward;
	}

	public void setFrAward(String frAward) {
		this.frAward = frAward;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	@Override
	public String toString() {
		return "FrTarget [error=" + error + ", message=" + message + ", frTargetId=" + frTargetId + ", frId=" + frId
				+ ", frTargetMonth=" + frTargetMonth + ", frTargetYear=" + frTargetYear + ", frTargetAmt=" + frTargetAmt
				+ ", frAchievedSale=" + frAchievedSale + ", frAward=" + frAward + ", remark=" + remark + ", status="
				+ status + ", delStatus=" + delStatus + "]";
	}
	
}
