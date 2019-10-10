package com.ats.adminpanel.model.checklist;

public class ChecklistDetail {
	
	private int checklistDetailId;
	private int checklistHeaderId;
	private String checklist_desc;
	private int isPhoto;
	private int isUsed;
	private int delStatus;
	private int createdBy;
	private String createdDate;
	private Integer exInt1;
	private Integer exInt2;
	private String exVar1;
	private String exVar2;
	public int getChecklistDetailId() {
		return checklistDetailId;
	}
	public void setChecklistDetailId(int checklistDetailId) {
		this.checklistDetailId = checklistDetailId;
	}
	public int getChecklistHeaderId() {
		return checklistHeaderId;
	}
	public void setChecklistHeaderId(int checklistHeaderId) {
		this.checklistHeaderId = checklistHeaderId;
	}
	public String getChecklist_desc() {
		return checklist_desc;
	}
	public void setChecklist_desc(String checklist_desc) {
		this.checklist_desc = checklist_desc;
	}
	public int getIsPhoto() {
		return isPhoto;
	}
	public void setIsPhoto(int isPhoto) {
		this.isPhoto = isPhoto;
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
	@Override
	public String toString() {
		return "ChecklistDetail [checklistDetailId=" + checklistDetailId + ", checklistHeaderId=" + checklistHeaderId
				+ ", checklist_desc=" + checklist_desc + ", isPhoto=" + isPhoto + ", isUsed=" + isUsed + ", delStatus="
				+ delStatus + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", exInt1=" + exInt1
				+ ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + "]";
	}
	
	

}
