package com.ats.adminpanel.model.checklist;

public class EmpWiseReport {
	
	private int empId;
	private String empName;
	private int totalAssign;
	private int totalTask;
	private int totalDetailTask;
	private int pendingCount;
	private int approvedCount;
	private int rejectedCount;
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public int getTotalAssign() {
		return totalAssign;
	}
	public void setTotalAssign(int totalAssign) {
		this.totalAssign = totalAssign;
	}
	public int getTotalTask() {
		return totalTask;
	}
	public void setTotalTask(int totalTask) {
		this.totalTask = totalTask;
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
		return "EmpWiseReport [empId=" + empId + ", empName=" + empName + ", totalAssign=" + totalAssign
				+ ", totalTask=" + totalTask + ", totalDetailTask=" + totalDetailTask + ", pendingCount=" + pendingCount
				+ ", approvedCount=" + approvedCount + ", rejectedCount=" + rejectedCount + "]";
	}
	
	

}
