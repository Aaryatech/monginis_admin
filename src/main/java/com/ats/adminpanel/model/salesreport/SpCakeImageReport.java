package com.ats.adminpanel.model.salesreport;


public class SpCakeImageReport {

	private int spOrderNo;
	
	private float spSelectedWeight;
	private String orderPhoto;
	private String orderPhoto2;
	
	private int spId;
	
	private String orderDate;
	private String spDeliveryDate;
	private String spCode;
	private String spName;
	private String frName;
	private String frCode;
	
	private String spfName;

	public int getSpOrderNo() {
		return spOrderNo;
	}

	public void setSpOrderNo(int spOrderNo) {
		this.spOrderNo = spOrderNo;
	}

	public float getSpSelectedWeight() {
		return spSelectedWeight;
	}

	public void setSpSelectedWeight(float spSelectedWeight) {
		this.spSelectedWeight = spSelectedWeight;
	}

	public String getOrderPhoto() {
		return orderPhoto;
	}

	public void setOrderPhoto(String orderPhoto) {
		this.orderPhoto = orderPhoto;
	}

	public String getOrderPhoto2() {
		return orderPhoto2;
	}

	public void setOrderPhoto2(String orderPhoto2) {
		this.orderPhoto2 = orderPhoto2;
	}

	public int getSpId() {
		return spId;
	}

	public void setSpId(int spId) {
		this.spId = spId;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getSpDeliveryDate() {
		return spDeliveryDate;
	}

	public void setSpDeliveryDate(String spDeliveryDate) {
		this.spDeliveryDate = spDeliveryDate;
	}

	public String getSpCode() {
		return spCode;
	}

	public void setSpCode(String spCode) {
		this.spCode = spCode;
	}

	public String getSpName() {
		return spName;
	}

	public void setSpName(String spName) {
		this.spName = spName;
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

	public String getSpfName() {
		return spfName;
	}

	public void setSpfName(String spfName) {
		this.spfName = spfName;
	}

	@Override
	public String toString() {
		return "SpCakeImageReport [spOrderNo=" + spOrderNo + ", spSelectedWeight=" + spSelectedWeight + ", orderPhoto="
				+ orderPhoto + ", orderPhoto2=" + orderPhoto2 + ", spId=" + spId + ", orderDate=" + orderDate
				+ ", spDeliveryDate=" + spDeliveryDate + ", spCode=" + spCode + ", spName=" + spName + ", frName="
				+ frName + ", frCode=" + frCode + ", spfName=" + spfName + "]";
	}
	
}
