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
	
	private String empMobile;
	
	private int discountId;
	
	private String discountHead;
	
	private String empDob;
	
	private String empDoj;
	
	private int empFamMemb;
	
	private float monthlyLimit;
	
	private float yearlyLimit;
	
	
	public String getEmpMobile() {
		return empMobile;
	}

	public void setEmpMobile(String empMobile) {
		this.empMobile = empMobile;
	}

	public int getDiscountId() {
		return discountId;
	}

	public void setDiscountId(int discountId) {
		this.discountId = discountId;
	}

	public String getDiscountHead() {
		return discountHead;
	}

	public void setDiscountHead(String discountHead) {
		this.discountHead = discountHead;
	}

	public String getEmpDob() {
		return empDob;
	}

	public void setEmpDob(String empDob) {
		this.empDob = empDob;
	}

	public String getEmpDoj() {
		return empDoj;
	}

	public void setEmpDoj(String empDoj) {
		this.empDoj = empDoj;
	}

	public int getEmpFamMemb() {
		return empFamMemb;
	}

	public void setEmpFamMemb(int empFamMemb) {
		this.empFamMemb = empFamMemb;
	}

	public float getMonthlyLimit() {
		return monthlyLimit;
	}

	public void setMonthlyLimit(float monthlyLimit) {
		this.monthlyLimit = monthlyLimit;
	}

	public float getYearlyLimit() {
		return yearlyLimit;
	}

	public void setYearlyLimit(float yearlyLimit) {
		this.yearlyLimit = yearlyLimit;
	}

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
				+ ", isUsed=" + isUsed + ", delStatus=" + delStatus + ", empType=" + empType + ", typeName=" + typeName
				+ ", empMob=" + empMobile + ", discountId=" + discountId + ", discountHead=" + discountHead + ", empDob="
				+ empDob + ", empDoj=" + empDoj + ", empFamMemb=" + empFamMemb + ", monthlyLimit=" + monthlyLimit
				+ ", yearlyLimit=" + yearlyLimit + "]";
	}
    
}
