package com.ats.adminpanel.model.hr;

public class EmpWiseVisitorReport {

	private int empId;
	private String empName;
	private Integer count;
	
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
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
	@Override
	public String toString() {
		return "EmpWiseVisitorReport [empId=" + empId + ", empName=" + empName + ", count=" + count + "]";
	}
	
	

	
}
