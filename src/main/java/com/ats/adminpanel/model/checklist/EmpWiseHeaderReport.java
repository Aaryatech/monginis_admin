package com.ats.adminpanel.model.checklist;

public class EmpWiseHeaderReport {
	
	private int actionHeaderId;
	private String actionDate;
	private String actionDatetime;
	private String checklistName;
	private String closedDate;
	private String closedDatetime;
	private String closedByName;
	private int totalDetailTask;
	private int pendingCount;
	private int approvedCount;
	private int rejectedCount;
	public int getActionHeaderId() {
		return actionHeaderId;
	}
	public void setActionHeaderId(int actionHeaderId) {
		this.actionHeaderId = actionHeaderId;
	}
	public String getActionDate() {
		return actionDate;
	}
	public void setActionDate(String actionDate) {
		this.actionDate = actionDate;
	}
	public String getActionDatetime() {
		return actionDatetime;
	}
	public void setActionDatetime(String actionDatetime) {
		this.actionDatetime = actionDatetime;
	}
	public String getChecklistName() {
		return checklistName;
	}
	public void setChecklistName(String checklistName) {
		this.checklistName = checklistName;
	}
	public String getClosedDate() {
		return closedDate;
	}
	public void setClosedDate(String closedDate) {
		this.closedDate = closedDate;
	}
	public String getClosedDatetime() {
		return closedDatetime;
	}
	public void setClosedDatetime(String closedDatetime) {
		this.closedDatetime = closedDatetime;
	}
	public String getClosedByName() {
		return closedByName;
	}
	public void setClosedByName(String closedByName) {
		this.closedByName = closedByName;
	}
	public int getTotalDetailTask() {
		return totalDetailTask;
	}
	public void setTotalDetailTask(int totalDetailTask) {
		this.totalDetailTask = totalDetailTask;
	}
	public int getPendingCount() {
		return pendingCount;
	}
	public void setPendingCount(int pendingCount) {
		this.pendingCount = pendingCount;
	}
	public int getApprovedCount() {
		return approvedCount;
	}
	public void setApprovedCount(int approvedCount) {
		this.approvedCount = approvedCount;
	}
	public int getRejectedCount() {
		return rejectedCount;
	}
	public void setRejectedCount(int rejectedCount) {
		this.rejectedCount = rejectedCount;
	}
	@Override
	public String toString() {
		return "EmpWiseHeaderReport [actionHeaderId=" + actionHeaderId + ", actionDate=" + actionDate
				+ ", actionDatetime=" + actionDatetime + ", checklistName=" + checklistName + ", closedDate="
				+ closedDate + ", closedDatetime=" + closedDatetime + ", closedByName=" + closedByName
				+ ", totalDetailTask=" + totalDetailTask + ", pendingCount=" + pendingCount + ", approvedCount="
				+ approvedCount + ", rejectedCount=" + rejectedCount + "]";
	}
	
	

}
