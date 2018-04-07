package com.ats.adminpanel.model.mastexcel;



public class Franchisee {
	
private int customerId;
    
	private String customerName;
	
 	private String address1;
	
	private String address2;
	
	private int frRateCat;
	
	private String city;

 	private String state;
	
 	private String GSTIN;
	
	private String type;
	
	private String frCode;
	
 	private String country;

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getGSTIN() {
		return GSTIN;
	}

	public void setGSTIN(String gSTIN) {
		GSTIN = gSTIN;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFrCode() {
		return frCode;
	}

	public void setFrCode(String frCode) {
		this.frCode = frCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getFrRateCat() {
		return frRateCat;
	}

	public void setFrRateCat(int frRateCat) {
		this.frRateCat = frRateCat;
	}

	@Override
	public String toString() {
		return "Franchisee [customerId=" + customerId + ", customerName=" + customerName + ", address1=" + address1
				+ ", address2=" + address2 + ", frRateCat=" + frRateCat + ", city=" + city + ", state=" + state
				+ ", GSTIN=" + GSTIN + ", type=" + type + ", frCode=" + frCode + ", country=" + country + "]";
	}

}
