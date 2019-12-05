package com.ats.adminpanel.model.salesreport2;

public class SalesReport {
	
	private int frId;
	private	String frName;
	private	String frCode;
	private	String frCity;
	
	private float saleValue;
	private float grnValue;
	private float gvnValue;
	
	
	
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
	public String getFrCity() {
		return frCity;
	}
	public void setFrCity(String frCity) {
		this.frCity = frCity;
	}
	public float getSaleValue() {
		return saleValue;
	}
	public void setSaleValue(float saleValue) {
		this.saleValue = saleValue;
	}
	public float getGrnValue() {
		return grnValue;
	}
	public void setGrnValue(float grnValue) {
		this.grnValue = grnValue;
	}
	public float getGvnValue() {
		return gvnValue;
	}
	public void setGvnValue(float gvnValue) {
		this.gvnValue = gvnValue;
	}

	@Override
	public String toString() {
		return "SalesReport [frId=" + frId + ", frName=" + frName + ", frCode=" + frCode + ", frCity=" + frCity
				+ ", saleValue=" + saleValue + ", grnValue=" + grnValue + ", gvnValue=" + gvnValue + "]";
	}
	

}
