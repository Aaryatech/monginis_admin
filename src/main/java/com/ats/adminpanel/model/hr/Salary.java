package com.ats.adminpanel.model.hr;

public class Salary {
	
	private int salaryId;

	private String salaryBracket;
	private String salaryDescription;
	private int salaryType;
	private int delStatus;
	private int isUsed;
	
	private Integer exInt1;
	private Integer exInt2;
	private Integer exInt3;
	private String exVar1;
	private String exVar2;
	private String exVar3;
	public int getSalaryId() {
		return salaryId;
	}
	public void setSalaryId(int salaryId) {
		this.salaryId = salaryId;
	}
	public String getSalaryBracket() {
		return salaryBracket;
	}
	public void setSalaryBracket(String salaryBracket) {
		this.salaryBracket = salaryBracket;
	}
	public String getSalaryDescription() {
		return salaryDescription;
	}
	public void setSalaryDescription(String salaryDescription) {
		this.salaryDescription = salaryDescription;
	}
	public int getSalaryType() {
		return salaryType;
	}
	public void setSalaryType(int salaryType) {
		this.salaryType = salaryType;
	}
	public int getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}
	public int getIsUsed() {
		return isUsed;
	}
	public void setIsUsed(int isUsed) {
		this.isUsed = isUsed;
	}
	public Integer getExInt1() {
		return exInt1;
	}
	public void setExInt1(Integer exInt1) {
		this.exInt1 = exInt1;
	}
	public Integer getExInt2() {
		return exInt2;
	}
	public void setExInt2(Integer exInt2) {
		this.exInt2 = exInt2;
	}
	public Integer getExInt3() {
		return exInt3;
	}
	public void setExInt3(Integer exInt3) {
		this.exInt3 = exInt3;
	}
	public String getExVar1() {
		return exVar1;
	}
	public void setExVar1(String exVar1) {
		this.exVar1 = exVar1;
	}
	public String getExVar2() {
		return exVar2;
	}
	public void setExVar2(String exVar2) {
		this.exVar2 = exVar2;
	}
	public String getExVar3() {
		return exVar3;
	}
	public void setExVar3(String exVar3) {
		this.exVar3 = exVar3;
	}
	@Override
	public String toString() {
		return "Salary [salaryId=" + salaryId + ", salaryBracket=" + salaryBracket + ", salaryDescription="
				+ salaryDescription + ", salaryType=" + salaryType + ", delStatus=" + delStatus + ", isUsed=" + isUsed
				+ ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exInt3=" + exInt3 + ", exVar1=" + exVar1
				+ ", exVar2=" + exVar2 + ", exVar3=" + exVar3 + "]";
	}
	
	

}
