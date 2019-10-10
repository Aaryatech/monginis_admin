package com.ats.adminpanel.model.checklist;

public class ChecklistActionDetail {
	
	private int actionDetailId;
	private int actionHeaderId;
	private int checklistMasterHeaderId;
	private int checklistMasterDetailId;
	private String checklist_desc;
	private int isPhoto;
	private int checkStatus;
	private String actionPhoto;
	private String closedPhoto;
	private String checkDate;
	private int delStatus;
	private Integer exInt1;
	private Integer exInt2;
	private String exVar1;
	private String exVar2;
	public int getActionDetailId() {
		return actionDetailId;
	}
	public void setActionDetailId(int actionDetailId) {
		this.actionDetailId = actionDetailId;
	}
	public int getActionHeaderId() {
		return actionHeaderId;
	}
	public void setActionHeaderId(int actionHeaderId) {
		this.actionHeaderId = actionHeaderId;
	}
	public int getChecklistMasterHeaderId() {
		return checklistMasterHeaderId;
	}
	public void setChecklistMasterHeaderId(int checklistMasterHeaderId) {
		this.checklistMasterHeaderId = checklistMasterHeaderId;
	}
	public int getChecklistMasterDetailId() {
		return checklistMasterDetailId;
	}
	public void setChecklistMasterDetailId(int checklistMasterDetailId) {
		this.checklistMasterDetailId = checklistMasterDetailId;
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
	public int getCheckStatus() {
		return checkStatus;
	}
	public void setCheckStatus(int checkStatus) {
		this.checkStatus = checkStatus;
	}
	public String getActionPhoto() {
		return actionPhoto;
	}
	public void setActionPhoto(String actionPhoto) {
		this.actionPhoto = actionPhoto;
	}
	public String getClosedPhoto() {
		return closedPhoto;
	}
	public void setClosedPhoto(String closedPhoto) {
		this.closedPhoto = closedPhoto;
	}
	public String getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
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
		return "ChecklistActionDetail [actionDetailId=" + actionDetailId + ", actionHeaderId=" + actionHeaderId
				+ ", checklistMasterHeaderId=" + checklistMasterHeaderId + ", checklistMasterDetailId="
				+ checklistMasterDetailId + ", checklist_desc=" + checklist_desc + ", isPhoto=" + isPhoto
				+ ", checkStatus=" + checkStatus + ", actionPhoto=" + actionPhoto + ", closedPhoto=" + closedPhoto
				+ ", checkDate=" + checkDate + ", delStatus=" + delStatus + ", exInt1=" + exInt1 + ", exInt2=" + exInt2
				+ ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + "]";
	}
	
	

}
