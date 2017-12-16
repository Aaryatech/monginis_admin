package com.ats.adminpanel.model.spprod;

import java.io.Serializable;



public class GetEmployee implements Serializable{

	private int empId;

	private String empName;
	
	private int deptId;

	private String deptName;
	
	private int isUsed;
	
	private int delStatus;

	private int empType;
	
	private String typeName;
	
	
	public int getEmpType() {
		return empType;
	}

	public void setEmpType(int empType) {
		this.empType = empType;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
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

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
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
		return "GetEmployee [empId=" + empId + ", empName=" + empName + ", deptId=" + deptId + ", deptName=" + deptName
				+ ", isUsed=" + isUsed + ", delStatus=" + delStatus + "]";
	}
}
