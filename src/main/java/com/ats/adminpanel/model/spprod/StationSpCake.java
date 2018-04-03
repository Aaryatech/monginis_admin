package com.ats.adminpanel.model.spprod;


public class StationSpCake{

    private int spOrderNo;
	
	private int frId;
	
	private int spId;
	
	private String spName;
	
	private String spCode;

	public int getSpOrderNo() {
		return spOrderNo;
	}

	public void setSpOrderNo(int spOrderNo) {
		this.spOrderNo = spOrderNo;
	}

	public int getFrId() {
		return frId;
	}

	public void setFrId(int frId) {
		this.frId = frId;
	}

	public int getSpId() {
		return spId;
	}

	public void setSpId(int spId) {
		this.spId = spId;
	}

	public String getSpName() {
		return spName;
	}

	public void setSpName(String spName) {
		this.spName = spName;
	}

	public String getSpCode() {
		return spCode;
	}

	public void setSpCode(String spCode) {
		this.spCode = spCode;
	}

	@Override
	public String toString() {
		return "StationSpCake [spOrderNo=" + spOrderNo + ", frId=" + frId + ", spId=" + spId + ", spName=" + spName
				+ ", spCode=" + spCode + "]";
	}

	
}
