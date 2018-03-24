package com.ats.adminpanel.model.spprod;

import java.io.Serializable;


public class MDept implements Serializable
{
private boolean isError;
	
	private String message;
	
	private int deptId;
	
	private String deptCode;

	private String deptName;
	
	private int isActive;
	
	private int delStatus;

	
	public boolean isError() {
		return isError;
	}

	public void setError(boolean isError) {
		this.isError = isError;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	@Override
	public String toString() {
		return "MDept [deptId=" + deptId + ", deptCode=" + deptCode + ", deptName=" + deptName + ", isActive="
				+ isActive + ", delStatus=" + delStatus + "]";
	}

}
