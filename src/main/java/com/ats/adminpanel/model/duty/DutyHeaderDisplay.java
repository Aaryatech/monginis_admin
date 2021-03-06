package com.ats.adminpanel.model.duty;

import java.util.List;

public class DutyHeaderDisplay {

	private int dutyId;
	private String dutyCode;
	private String dutyName;
	private int deptId;
	private int desgId;
	private int type;
	private String typeDesc;
	private int shiftId;
	private int createdBy;
	private String createdDate;
	private int totalTaskWt;
	private int delStatus;
	private Integer exInt1;
	private Integer exInt2;
	private Integer exInt3;
	private String exVar1;
	private String exVar2;
	private String exVar3;

	private String deptName;
	private String desgName;
	private String shiftName;
	private String shiftFromTime;
	private String shiftToTime;
	private String noOfHr;
	private String createdByName;
	
	List<TaskDetailDisplay> taskDetailDisplay;
	
	

	public String getDutyCode() {
		return dutyCode;
	}

	public void setDutyCode(String dutyCode) {
		this.dutyCode = dutyCode;
	}

	public int getDutyId() {
		return dutyId;
	}

	public void setDutyId(int dutyId) {
		this.dutyId = dutyId;
	}

	public String getDutyName() {
		return dutyName;
	}

	public void setDutyName(String dutyName) {
		this.dutyName = dutyName;
	}

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public int getDesgId() {
		return desgId;
	}

	public void setDesgId(int desgId) {
		this.desgId = desgId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getTypeDesc() {
		return typeDesc;
	}

	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}

	public int getShiftId() {
		return shiftId;
	}

	public void setShiftId(int shiftId) {
		this.shiftId = shiftId;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public int getTotalTaskWt() {
		return totalTaskWt;
	}

	public void setTotalTaskWt(int totalTaskWt) {
		this.totalTaskWt = totalTaskWt;
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

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDesgName() {
		return desgName;
	}

	public void setDesgName(String desgName) {
		this.desgName = desgName;
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

	public String getCreatedByName() {
		return createdByName;
	}

	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}

	public List<TaskDetailDisplay> getTaskDetailDisplay() {
		return taskDetailDisplay;
	}

	public void setTaskDetailDisplay(List<TaskDetailDisplay> taskDetailDisplay) {
		this.taskDetailDisplay = taskDetailDisplay;
	}

	
	
	@Override
	public String toString() {
		return "DutyHeaderDisplay [dutyId=" + dutyId + ", dutyCode=" + dutyCode + ", dutyName=" + dutyName + ", deptId="
				+ deptId + ", desgId=" + desgId + ", type=" + type + ", typeDesc=" + typeDesc + ", shiftId=" + shiftId
				+ ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", totalTaskWt=" + totalTaskWt
				+ ", delStatus=" + delStatus + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exInt3=" + exInt3
				+ ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + ", exVar3=" + exVar3 + ", deptName=" + deptName
				+ ", desgName=" + desgName + ", shiftName=" + shiftName + ", shiftFromTime=" + shiftFromTime
				+ ", shiftToTime=" + shiftToTime + ", noOfHr=" + noOfHr + ", createdByName=" + createdByName
				+ ", taskDetailDisplay=" + taskDetailDisplay + "]";
	}

 

}
