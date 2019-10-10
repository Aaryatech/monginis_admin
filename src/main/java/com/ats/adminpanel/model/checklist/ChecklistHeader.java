package com.ats.adminpanel.model.checklist;

import java.util.List;

public class ChecklistHeader {
	
	private int checklistHeaderId;
	private int deptId;
	private String checklistName;
	private int isUsed;
	private int delStatus;
	private int createdBy;
	private String createdDate;
	private Integer exInt1;
	private Integer exInt2;
	private Integer exInt3;
	private String exVar1;
	private String exVar2;
	private String exVar3;
	
	List<ChecklistDetail> checklistDetail;

	public int getChecklistHeaderId() {
		return checklistHeaderId;
	}

	public void setChecklistHeaderId(int checklistHeaderId) {
		this.checklistHeaderId = checklistHeaderId;
	}

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public String getChecklistName() {
		return checklistName;
	}

	public void setChecklistName(String checklistName) {
		this.checklistName = checklistName;
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

	public List<ChecklistDetail> getChecklistDetail() {
		return checklistDetail;
	}

	public void setChecklistDetail(List<ChecklistDetail> checklistDetail) {
		this.checklistDetail = checklistDetail;
	}

	@Override
	public String toString() {
		return "ChecklistHeader [checklistHeaderId=" + checklistHeaderId + ", deptId=" + deptId + ", checklistName="
				+ checklistName + ", isUsed=" + isUsed + ", delStatus=" + delStatus + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exInt3=" + exInt3
				+ ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + ", exVar3=" + exVar3 + ", checklistDetail="
				+ checklistDetail + "]";
	}
	
	


}
