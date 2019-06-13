package com.ats.adminpanel.model.hr;

public class Employee {
	
private int empId;
	
	private String empDsc;
	private String empCode;
	private int companyId;
	private int empCatId;
	private int empTypeId;
	private int empDeptId;
	private int locId ;
	private String empFname;
	private String empMname;
	private String empSname;
	private String empPhoto;
	private String empMobile1;
	private String empMobile2;
	private String empEmail;
	private String empAddressTemp;
	private String empAddressPerm;
	private String empBloodgrp;
	private String empEmergencyPerson1;
	private String empEmergencyNo1;
	private String empEmergencyPerson2;
	private String empEmergencyNo2;
	private float empRatePerhr;
	private String empJoiningDate;
	private float empPrevExpYrs;
	private float empPrevExpMonths;
	private String empLeavingDate;
	private String empLeavingReason;
	private String lockPeriod;
	private String termConditions;
	private int salaryId;
	private int delStatus;
	private int isActive;
	private int makerUserId ;
	private String makerEnterDatetime;
	
	private Integer exInt1;
	private Integer exInt2;
	private Integer exInt3;
	private String exVar1; 
	private String exVar2; 
	private String exVar3;
	
	
	
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Employee(int empId, String empDsc, String empCode, int companyId, int empCatId, int empTypeId, int empDeptId,
			int locId, String empFname, String empMname, String empSname, String empPhoto, String empMobile1,
			String empMobile2, String empEmail, String empAddressTemp, String empAddressPerm, String empBloodgrp,
			String empEmergencyPerson1, String empEmergencyNo1, String empEmergencyPerson2, String empEmergencyNo2,
			float empRatePerhr, String empJoiningDate, float empPrevExpYrs, float empPrevExpMonths,
			String empLeavingDate, String empLeavingReason, String lockPeriod, String termConditions, int salaryId,
			int delStatus, int isActive, int makerUserId, String makerEnterDatetime) {
		super();
		this.empId = empId;
		this.empDsc = empDsc;
		this.empCode = empCode;
		this.companyId = companyId;
		this.empCatId = empCatId;
		this.empTypeId = empTypeId;
		this.empDeptId = empDeptId;
		this.locId = locId;
		this.empFname = empFname;
		this.empMname = empMname;
		this.empSname = empSname;
		this.empPhoto = empPhoto;
		this.empMobile1 = empMobile1;
		this.empMobile2 = empMobile2;
		this.empEmail = empEmail;
		this.empAddressTemp = empAddressTemp;
		this.empAddressPerm = empAddressPerm;
		this.empBloodgrp = empBloodgrp;
		this.empEmergencyPerson1 = empEmergencyPerson1;
		this.empEmergencyNo1 = empEmergencyNo1;
		this.empEmergencyPerson2 = empEmergencyPerson2;
		this.empEmergencyNo2 = empEmergencyNo2;
		this.empRatePerhr = empRatePerhr;
		this.empJoiningDate = empJoiningDate;
		this.empPrevExpYrs = empPrevExpYrs;
		this.empPrevExpMonths = empPrevExpMonths;
		this.empLeavingDate = empLeavingDate;
		this.empLeavingReason = empLeavingReason;
		this.lockPeriod = lockPeriod;
		this.termConditions = termConditions;
		this.salaryId = salaryId;
		this.delStatus = delStatus;
		this.isActive = isActive;
		this.makerUserId = makerUserId;
		this.makerEnterDatetime = makerEnterDatetime;
	}




	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getEmpDsc() {
		return empDsc;
	}
	public void setEmpDsc(String empDsc) {
		this.empDsc = empDsc;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public int getEmpCatId() {
		return empCatId;
	}
	public void setEmpCatId(int empCatId) {
		this.empCatId = empCatId;
	}
	public int getEmpTypeId() {
		return empTypeId;
	}
	public void setEmpTypeId(int empTypeId) {
		this.empTypeId = empTypeId;
	}
	public int getEmpDeptId() {
		return empDeptId;
	}
	public void setEmpDeptId(int empDeptId) {
		this.empDeptId = empDeptId;
	}
	public int getLocId() {
		return locId;
	}
	public void setLocId(int locId) {
		this.locId = locId;
	}
	public String getEmpFname() {
		return empFname;
	}
	public void setEmpFname(String empFname) {
		this.empFname = empFname;
	}
	public String getEmpMname() {
		return empMname;
	}
	public void setEmpMname(String empMname) {
		this.empMname = empMname;
	}
	public String getEmpSname() {
		return empSname;
	}
	public void setEmpSname(String empSname) {
		this.empSname = empSname;
	}
	public String getEmpPhoto() {
		return empPhoto;
	}
	public void setEmpPhoto(String empPhoto) {
		this.empPhoto = empPhoto;
	}
	public String getEmpMobile1() {
		return empMobile1;
	}
	public void setEmpMobile1(String empMobile1) {
		this.empMobile1 = empMobile1;
	}
	public String getEmpMobile2() {
		return empMobile2;
	}
	public void setEmpMobile2(String empMobile2) {
		this.empMobile2 = empMobile2;
	}
	public String getEmpEmail() {
		return empEmail;
	}
	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}
	public String getEmpAddressTemp() {
		return empAddressTemp;
	}
	public void setEmpAddressTemp(String empAddressTemp) {
		this.empAddressTemp = empAddressTemp;
	}
	public String getEmpAddressPerm() {
		return empAddressPerm;
	}
	public void setEmpAddressPerm(String empAddressPerm) {
		this.empAddressPerm = empAddressPerm;
	}
	public String getEmpBloodgrp() {
		return empBloodgrp;
	}
	public void setEmpBloodgrp(String empBloodgrp) {
		this.empBloodgrp = empBloodgrp;
	}
	public String getEmpEmergencyPerson1() {
		return empEmergencyPerson1;
	}
	public void setEmpEmergencyPerson1(String empEmergencyPerson1) {
		this.empEmergencyPerson1 = empEmergencyPerson1;
	}
	public String getEmpEmergencyNo1() {
		return empEmergencyNo1;
	}
	public void setEmpEmergencyNo1(String empEmergencyNo1) {
		this.empEmergencyNo1 = empEmergencyNo1;
	}
	public String getEmpEmergencyPerson2() {
		return empEmergencyPerson2;
	}
	public void setEmpEmergencyPerson2(String empEmergencyPerson2) {
		this.empEmergencyPerson2 = empEmergencyPerson2;
	}
	public String getEmpEmergencyNo2() {
		return empEmergencyNo2;
	}
	public void setEmpEmergencyNo2(String empEmergencyNo2) {
		this.empEmergencyNo2 = empEmergencyNo2;
	}
	public float getEmpRatePerhr() {
		return empRatePerhr;
	}
	public void setEmpRatePerhr(float empRatePerhr) {
		this.empRatePerhr = empRatePerhr;
	}
	public String getEmpJoiningDate() {
		return empJoiningDate;
	}
	public void setEmpJoiningDate(String empJoiningDate) {
		this.empJoiningDate = empJoiningDate;
	}
	public float getEmpPrevExpYrs() {
		return empPrevExpYrs;
	}
	public void setEmpPrevExpYrs(float empPrevExpYrs) {
		this.empPrevExpYrs = empPrevExpYrs;
	}
	public float getEmpPrevExpMonths() {
		return empPrevExpMonths;
	}
	public void setEmpPrevExpMonths(float empPrevExpMonths) {
		this.empPrevExpMonths = empPrevExpMonths;
	}
	public String getEmpLeavingDate() {
		return empLeavingDate;
	}
	public void setEmpLeavingDate(String empLeavingDate) {
		this.empLeavingDate = empLeavingDate;
	}
	public String getEmpLeavingReason() {
		return empLeavingReason;
	}
	public void setEmpLeavingReason(String empLeavingReason) {
		this.empLeavingReason = empLeavingReason;
	}
	public String getLockPeriod() {
		return lockPeriod;
	}
	public void setLockPeriod(String lockPeriod) {
		this.lockPeriod = lockPeriod;
	}
	public String getTermConditions() {
		return termConditions;
	}
	public void setTermConditions(String termConditions) {
		this.termConditions = termConditions;
	}
	public int getSalaryId() {
		return salaryId;
	}
	public void setSalaryId(int salaryId) {
		this.salaryId = salaryId;
	}
	public int getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	public int getMakerUserId() {
		return makerUserId;
	}
	public void setMakerUserId(int makerUserId) {
		this.makerUserId = makerUserId;
	}
	public String getMakerEnterDatetime() {
		return makerEnterDatetime;
	}
	public void setMakerEnterDatetime(String makerEnterDatetime) {
		this.makerEnterDatetime = makerEnterDatetime;
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
		return "Employee [empId=" + empId + ", empDsc=" + empDsc + ", empCode=" + empCode + ", companyId=" + companyId
				+ ", empCatId=" + empCatId + ", empTypeId=" + empTypeId + ", empDeptId=" + empDeptId + ", locId="
				+ locId + ", empFname=" + empFname + ", empMname=" + empMname + ", empSname=" + empSname + ", empPhoto="
				+ empPhoto + ", empMobile1=" + empMobile1 + ", empMobile2=" + empMobile2 + ", empEmail=" + empEmail
				+ ", empAddressTemp=" + empAddressTemp + ", empAddressPerm=" + empAddressPerm + ", empBloodgrp="
				+ empBloodgrp + ", empEmergencyPerson1=" + empEmergencyPerson1 + ", empEmergencyNo1=" + empEmergencyNo1
				+ ", empEmergencyPerson2=" + empEmergencyPerson2 + ", empEmergencyNo2=" + empEmergencyNo2
				+ ", empRatePerhr=" + empRatePerhr + ", empJoiningDate=" + empJoiningDate + ", empPrevExpYrs="
				+ empPrevExpYrs + ", empPrevExpMonths=" + empPrevExpMonths + ", empLeavingDate=" + empLeavingDate
				+ ", empLeavingReason=" + empLeavingReason + ", lockPeriod=" + lockPeriod + ", termConditions="
				+ termConditions + ", salaryId=" + salaryId + ", delStatus=" + delStatus + ", isActive=" + isActive
				+ ", makerUserId=" + makerUserId + ", makerEnterDatetime=" + makerEnterDatetime + ", exInt1=" + exInt1
				+ ", exInt2=" + exInt2 + ", exInt3=" + exInt3 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2
				+ ", exVar3=" + exVar3 + "]";
	}
	
	
	

}
