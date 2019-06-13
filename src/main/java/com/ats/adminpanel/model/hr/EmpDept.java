package com.ats.adminpanel.model.hr;


public class EmpDept {

	private int empDeptId;
	
	private int companyId;
	private String empDeptName;
	private String empDeptShortName;
	private String empDeptRemarks;
	private int delStatus;
	private int isActive;
	private int makerUserId;
	private String makerEnterDatetime;
	
	private Integer exInt1;
	private Integer exInt2;
	private Integer exInt3;
	private String exVar1; 
	private String exVar2; 
	private String exVar3;
	
	
	
	
	public EmpDept() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EmpDept(int empDeptId, int companyId, String empDeptName, String empDeptShortName, String empDeptRemarks,
			int delStatus, int isActive, int makerUserId, String makerEnterDatetime) {
		super();
		this.empDeptId = empDeptId;
		this.companyId = companyId;
		this.empDeptName = empDeptName;
		this.empDeptShortName = empDeptShortName;
		this.empDeptRemarks = empDeptRemarks;
		this.delStatus = delStatus;
		this.isActive = isActive;
		this.makerUserId = makerUserId;
		this.makerEnterDatetime = makerEnterDatetime;
	}
	public int getEmpDeptId() {
		return empDeptId;
	}
	public void setEmpDeptId(int empDeptId) {
		this.empDeptId = empDeptId;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public String getEmpDeptName() {
		return empDeptName;
	}
	public void setEmpDeptName(String empDeptName) {
		this.empDeptName = empDeptName;
	}
	public String getEmpDeptShortName() {
		return empDeptShortName;
	}
	public void setEmpDeptShortName(String empDeptShortName) {
		this.empDeptShortName = empDeptShortName;
	}
	public String getEmpDeptRemarks() {
		return empDeptRemarks;
	}
	public void setEmpDeptRemarks(String empDeptRemarks) {
		this.empDeptRemarks = empDeptRemarks;
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
		return "EmpDept [empDeptId=" + empDeptId + ", companyId=" + companyId + ", empDeptName=" + empDeptName
				+ ", empDeptShortName=" + empDeptShortName + ", empDeptRemarks=" + empDeptRemarks + ", delStatus="
				+ delStatus + ", isActive=" + isActive + ", makerUserId=" + makerUserId + ", makerEnterDatetime="
				+ makerEnterDatetime + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exInt3=" + exInt3 + ", exVar1="
				+ exVar1 + ", exVar2=" + exVar2 + ", exVar3=" + exVar3 + "]";
	}
	
	
	
}
