package com.ats.adminpanel.model.tray;


public class FranchiseInRoute {

	private int frId;
	private String frName;
	private String frCode;
	public int getFrId() {
		return frId;
	}
	public void setFrId(int frId) {
		this.frId = frId;
	}
	public String getFrName() {
		return frName;
	}
	public void setFrName(String frName) {
		this.frName = frName;
	}
	public String getFrCode() {
		return frCode;
	}
	public void setFrCode(String frCode) {
		this.frCode = frCode;
	}
	@Override
	public String toString() {
		return "FranchiseInRoute [frId=" + frId + ", frName=" + frName + ", frCode=" + frCode + "]";
	}
	
	
	
}
