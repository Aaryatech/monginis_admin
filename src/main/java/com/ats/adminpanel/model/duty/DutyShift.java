package com.ats.adminpanel.model.duty;

public class DutyShift {
	
	private int shiftId;
	private String shiftName;
	private String shiftFromTime;
	private String shiftToTime;
	private String noOfHr;
	private int isActive;
	private int delStatus;
	private Integer exInt1;
	private Integer exInt2;
	private Integer exInt3;
	private String exVar1;
	private String exVar2;
	private String exVar3;
	
	
	
	public DutyShift() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	


	public DutyShift(int shiftId, String shiftName, String shiftFromTime, String shiftToTime, String noOfHr,
			int isActive, int delStatus, Integer exInt1, Integer exInt2, Integer exInt3) {
		super();
		this.shiftId = shiftId;
		this.shiftName = shiftName;
		this.shiftFromTime = shiftFromTime;
		this.shiftToTime = shiftToTime;
		this.noOfHr = noOfHr;
		this.isActive = isActive;
		this.delStatus = delStatus;
		this.exInt1 = exInt1;
		this.exInt2 = exInt2;
		this.exInt3 = exInt3;
	}





	public int getShiftId() {
		return shiftId;
	}
	public void setShiftId(int shiftId) {
		this.shiftId = shiftId;
	}
	public String getShiftName() {
		return shiftName;
	}
	public void setShiftName(String shiftName) {
		this.shiftName = shiftName;
	}
	public String getShiftFromTime() {
		return shiftFromTime;
	}
	public void setShiftFromTime(String shiftFromTime) {
		this.shiftFromTime = shiftFromTime;
	}
	public String getShiftToTime() {
		return shiftToTime;
	}
	public void setShiftToTime(String shiftToTime) {
		this.shiftToTime = shiftToTime;
	}
	public String getNoOfHr() {
		return noOfHr;
	}
	public void setNoOfHr(String noOfHr) {
		this.noOfHr = noOfHr;
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
		return "DutyShift [shiftId=" + shiftId + ", shiftName=" + shiftName + ", shiftFromTime=" + shiftFromTime
				+ ", shiftToTime=" + shiftToTime + ", noOfHr=" + noOfHr + ", isActive=" + isActive + ", delStatus="
				+ delStatus + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exInt3=" + exInt3 + ", exVar1=" + exVar1
				+ ", exVar2=" + exVar2 + ", exVar3=" + exVar3 + "]";
	}

	

}
