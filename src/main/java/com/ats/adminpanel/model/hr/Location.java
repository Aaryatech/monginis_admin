package com.ats.adminpanel.model.hr;

public class Location {
	
	private int locId;

	private String locName;
	private String locNameShort;
	private String locShortAddress;
	private String locHrContactPerson;
	private String locHrContactNumber;
	private String locHrContactEmail;
	private String locRemarks;
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
	private int compId;
	
	
	
	
	public Location() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Location(int locId, String locName, String locNameShort, String locShortAddress, String locHrContactPerson,
			String locHrContactNumber, String locHrContactEmail, String locRemarks, int delStatus, int isActive,
			int makerUserId, String makerEnterDatetime, int compId) {
		super();
		this.locId = locId;
		this.locName = locName;
		this.locNameShort = locNameShort;
		this.locShortAddress = locShortAddress;
		this.locHrContactPerson = locHrContactPerson;
		this.locHrContactNumber = locHrContactNumber;
		this.locHrContactEmail = locHrContactEmail;
		this.locRemarks = locRemarks;
		this.delStatus = delStatus;
		this.isActive = isActive;
		this.makerUserId = makerUserId;
		this.makerEnterDatetime = makerEnterDatetime;
		this.compId = compId;
	}
	public int getLocId() {
		return locId;
	}
	public void setLocId(int locId) {
		this.locId = locId;
	}
	public String getLocName() {
		return locName;
	}
	public void setLocName(String locName) {
		this.locName = locName;
	}
	public String getLocNameShort() {
		return locNameShort;
	}
	public void setLocNameShort(String locNameShort) {
		this.locNameShort = locNameShort;
	}
	public String getLocShortAddress() {
		return locShortAddress;
	}
	public void setLocShortAddress(String locShortAddress) {
		this.locShortAddress = locShortAddress;
	}
	public String getLocHrContactPerson() {
		return locHrContactPerson;
	}
	public void setLocHrContactPerson(String locHrContactPerson) {
		this.locHrContactPerson = locHrContactPerson;
	}
	public String getLocHrContactNumber() {
		return locHrContactNumber;
	}
	public void setLocHrContactNumber(String locHrContactNumber) {
		this.locHrContactNumber = locHrContactNumber;
	}
	public String getLocHrContactEmail() {
		return locHrContactEmail;
	}
	public void setLocHrContactEmail(String locHrContactEmail) {
		this.locHrContactEmail = locHrContactEmail;
	}
	public String getLocRemarks() {
		return locRemarks;
	}
	public void setLocRemarks(String locRemarks) {
		this.locRemarks = locRemarks;
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
	public int getCompId() {
		return compId;
	}
	public void setCompId(int compId) {
		this.compId = compId;
	}
	@Override
	public String toString() {
		return "Location [locId=" + locId + ", locName=" + locName + ", locNameShort=" + locNameShort
				+ ", locShortAddress=" + locShortAddress + ", locHrContactPerson=" + locHrContactPerson
				+ ", locHrContactNumber=" + locHrContactNumber + ", locHrContactEmail=" + locHrContactEmail
				+ ", locRemarks=" + locRemarks + ", delStatus=" + delStatus + ", isActive=" + isActive
				+ ", makerUserId=" + makerUserId + ", makerEnterDatetime=" + makerEnterDatetime + ", exInt1=" + exInt1
				+ ", exInt2=" + exInt2 + ", exInt3=" + exInt3 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2
				+ ", exVar3=" + exVar3 + ", compId=" + compId + "]";
	}
	
	

}
