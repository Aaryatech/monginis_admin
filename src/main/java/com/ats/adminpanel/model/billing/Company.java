package com.ats.adminpanel.model.billing;

import java.io.Serializable;


public class Company implements Serializable{

	
	private int compId;
	
	private String compName;
	
	private String factAddress;
	
	private String phoneNo1;
	
	private String phoneNo2;
	
	private String email;
	
	private String gstin;
	
	private int stateCode;
	
	private String state;
	
	private String cinNo;
	
	private String fdaDeclaration;
	
	private String fdaLicenceNo;
	
	private String fromDate;

	private String toDate;

	private int delStatus;
	
	private String panNo;

	private String exVar1;
	
	private String exVar2;
	
	private String exVar3;
	
	private String exVar4;
	
	private String exVar5;
	
	private String exVar6;
	
	

	public String getPanNo() {
		return panNo;
	}

	public String getExVar1() {
		return exVar1;
	}

	public String getExVar2() {
		return exVar2;
	}

	public String getExVar3() {
		return exVar3;
	}

	public String getExVar4() {
		return exVar4;
	}

	public String getExVar5() {
		return exVar5;
	}

	public String getExVar6() {
		return exVar6;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public void setExVar1(String exVar1) {
		this.exVar1 = exVar1;
	}

	public void setExVar2(String exVar2) {
		this.exVar2 = exVar2;
	}

	public void setExVar3(String exVar3) {
		this.exVar3 = exVar3;
	}

	public void setExVar4(String exVar4) {
		this.exVar4 = exVar4;
	}

	public void setExVar5(String exVar5) {
		this.exVar5 = exVar5;
	}

	public void setExVar6(String exVar6) {
		this.exVar6 = exVar6;
	}

	public int getCompId() {
		return compId;
	}

	public String getCompName() {
		return compName;
	}

	public String getFactAddress() {
		return factAddress;
	}

	public String getPhoneNo1() {
		return phoneNo1;
	}

	public String getPhoneNo2() {
		return phoneNo2;
	}

	public String getEmail() {
		return email;
	}

	public String getGstin() {
		return gstin;
	}

	public int getStateCode() {
		return stateCode;
	}

	public String getState() {
		return state;
	}

	public String getCinNo() {
		return cinNo;
	}

	public String getFdaDeclaration() {
		return fdaDeclaration;
	}

	public String getFdaLicenceNo() {
		return fdaLicenceNo;
	}

	public String getFromDate() {
		return fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setCompId(int compId) {
		this.compId = compId;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public void setFactAddress(String factAddress) {
		this.factAddress = factAddress;
	}

	public void setPhoneNo1(String phoneNo1) {
		this.phoneNo1 = phoneNo1;
	}

	public void setPhoneNo2(String phoneNo2) {
		this.phoneNo2 = phoneNo2;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setGstin(String gstin) {
		this.gstin = gstin;
	}

	public void setStateCode(int stateCode) {
		this.stateCode = stateCode;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setCinNo(String cinNo) {
		this.cinNo = cinNo;
	}

	public void setFdaDeclaration(String fdaDeclaration) {
		this.fdaDeclaration = fdaDeclaration;
	}

	public void setFdaLicenceNo(String fdaLicenceNo) {
		this.fdaLicenceNo = fdaLicenceNo;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	@Override
	public String toString() {
		return "Company [compId=" + compId + ", compName=" + compName + ", factAddress=" + factAddress + ", phoneNo1="
				+ phoneNo1 + ", phoneNo2=" + phoneNo2 + ", email=" + email + ", gstin=" + gstin + ", stateCode="
				+ stateCode + ", state=" + state + ", cinNo=" + cinNo + ", fdaDeclaration=" + fdaDeclaration
				+ ", fdaLicenceNo=" + fdaLicenceNo + ", fromDate=" + fromDate + ", toDate=" + toDate + ", delStatus="
				+ delStatus + ", panNo=" + panNo + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + ", exVar3=" + exVar3
				+ ", exVar4=" + exVar4 + ", exVar5=" + exVar5 + ", exVar6=" + exVar6 + "]";
	}
    
}
