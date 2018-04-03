package com.ats.adminpanel.model.logistics;

 

import com.fasterxml.jackson.annotation.JsonFormat; 
public class DriverMaster {
	
	 
	private int driverId;  
	private String driverName; 
	private String address1; 
	private String address2; 
	private String mobile1; 
	private String mobile2; 
	private String mobile3; 
	private String driverDob; 
	private String joiningDate; 
	private String licNo; 
	private String licExpireDate; 
	private int delStatus;
	public int getDriverId() {
		return driverId;
	}
	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getMobile1() {
		return mobile1;
	}
	public void setMobile1(String mobile1) {
		this.mobile1 = mobile1;
	}
	public String getMobile2() {
		return mobile2;
	}
	public void setMobile2(String mobile2) {
		this.mobile2 = mobile2;
	}
	public String getMobile3() {
		return mobile3;
	}
	public void setMobile3(String mobile3) {
		this.mobile3 = mobile3;
	}
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public String getDriverDob() {
		return driverDob;
	}
	public void setDriverDob(String driverDob) {
		this.driverDob = driverDob;
	}
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public String getJoiningDate() {
		return joiningDate;
	}
	
	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}
	public String getLicNo() {
		return licNo;
	}
	public void setLicNo(String licNo) {
		this.licNo = licNo;
	}
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public String getLicExpireDate() {
		return licExpireDate;
	}
	public void setLicExpireDate(String licExpireDate) {
		this.licExpireDate = licExpireDate;
	}
	public int getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}
	@Override
	public String toString() {
		return "DriverMaster [driverId=" + driverId + ", driverName=" + driverName + ", address1=" + address1
				+ ", address2=" + address2 + ", mobile1=" + mobile1 + ", mobile2=" + mobile2 + ", mobile3=" + mobile3
				+ ", driverDob=" + driverDob + ", joiningDate=" + joiningDate + ", licNo=" + licNo + ", licExpireDate="
				+ licExpireDate + ", delStatus=" + delStatus + "]";
	}
	
	
	

}
