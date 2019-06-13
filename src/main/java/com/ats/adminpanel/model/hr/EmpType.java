package com.ats.adminpanel.model.hr;

public class EmpType {

	private int empTypeId;
	
	private int companyId;
	private String empTypeName;
	private String empTypeShortName;
	private int compOffRequestAllowed;
	private int setting1;
	private int setting2;
	private String empTypeRemarks;
	private String empTypeAccess;
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
	public int getEmpTypeId() {
		return empTypeId;
	}
	public void setEmpTypeId(int empTypeId) {
		this.empTypeId = empTypeId;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public String getEmpTypeName() {
		return empTypeName;
	}
	public void setEmpTypeName(String empTypeName) {
		this.empTypeName = empTypeName;
	}
	public String getEmpTypeShortName() {
		return empTypeShortName;
	}
	public void setEmpTypeShortName(String empTypeShortName) {
		this.empTypeShortName = empTypeShortName;
	}
	public int getCompOffRequestAllowed() {
		return compOffRequestAllowed;
	}
	public void setCompOffRequestAllowed(int compOffRequestAllowed) {
		this.compOffRequestAllowed = compOffRequestAllowed;
	}
	public int getSetting1() {
		return setting1;
	}
	public void setSetting1(int setting1) {
		this.setting1 = setting1;
	}
	public int getSetting2() {
		return setting2;
	}
	public void setSetting2(int setting2) {
		this.setting2 = setting2;
	}
	public String getEmpTypeRemarks() {
		return empTypeRemarks;
	}
	public void setEmpTypeRemarks(String empTypeRemarks) {
		this.empTypeRemarks = empTypeRemarks;
	}
	public String getEmpTypeAccess() {
		return empTypeAccess;
	}
	public void setEmpTypeAccess(String empTypeAccess) {
		this.empTypeAccess = empTypeAccess;
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
		return "EmpType [empTypeId=" + empTypeId + ", companyId=" + companyId + ", empTypeName=" + empTypeName
				+ ", empTypeShortName=" + empTypeShortName + ", compOffRequestAllowed=" + compOffRequestAllowed
				+ ", setting1=" + setting1 + ", setting2=" + setting2 + ", empTypeRemarks=" + empTypeRemarks
				+ ", empTypeAccess=" + empTypeAccess + ", delStatus=" + delStatus + ", isActive=" + isActive
				+ ", makerUserId=" + makerUserId + ", makerEnterDatetime=" + makerEnterDatetime + ", exInt1=" + exInt1
				+ ", exInt2=" + exInt2 + ", exInt3=" + exInt3 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2
				+ ", exVar3=" + exVar3 + "]";
	}
	
	
	
}
