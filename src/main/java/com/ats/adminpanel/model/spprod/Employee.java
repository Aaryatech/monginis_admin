package com.ats.adminpanel.model.spprod;

import java.io.Serializable;

public class Employee implements Serializable{


	private boolean isError;
	
	private String message;
	
	private int empId;
	
	private String empName;

	private int deptId;

	private int isUsed;
	
	private int delStatus;

	private int empType;
	
	public int getEmpType() {
		return empType;
	}

	public void setEmpType(int empType) {
		this.empType = empType;
	}

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

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public int getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(int isUsed) {
		this.isUsed = isUsed;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	@Override
	public String toString() {
		return "Employee [isError=" + isError + ", message=" + message + ", empId=" + empId + ", empName=" + empName
				+ ", deptId=" + deptId + ", isUsed=" + isUsed + ", delStatus=" + delStatus + "]";
	}
    
    
}
