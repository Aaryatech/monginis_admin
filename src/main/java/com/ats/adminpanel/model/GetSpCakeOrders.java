package com.ats.adminpanel.model;

public class GetSpCakeOrders {

	 
	private int spOrderNo;

 
	private String frName;
	
 
	private String frMob;
	 
	private String spName;
	 
	private String orderDate;
	 
	private float spPrice;
	
	  
	private String spInstructions;
	 
	private float spSubTotal;
	 
	private float spAdvance;
	 
	private float rmAmount;
	 
	private String spDeliveryDate;
	
	private float spSelectedWeight;
	 
	private String spDeliveryPlace;
	
 
	private String spCustName;
	
	 
	private String spCustMobNo;

	 
	private String spfName;


	public int getSpOrderNo() {
		return spOrderNo;
	}


	public void setSpOrderNo(int spOrderNo) {
		this.spOrderNo = spOrderNo;
	}


	public String getFrName() {
		return frName;
	}


	public void setFrName(String frName) {
		this.frName = frName;
	}


	public String getFrMob() {
		return frMob;
	}


	public void setFrMob(String frMob) {
		this.frMob = frMob;
	}


	public String getSpName() {
		return spName;
	}


	public void setSpName(String spName) {
		this.spName = spName;
	}


	public String getOrderDate() {
		return orderDate;
	}


	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}


	public float getSpPrice() {
		return spPrice;
	}


	public void setSpPrice(float spPrice) {
		this.spPrice = spPrice;
	}


	public String getSpInstructions() {
		return spInstructions;
	}


	public void setSpInstructions(String spInstructions) {
		this.spInstructions = spInstructions;
	}


	public float getSpSubTotal() {
		return spSubTotal;
	}


	public void setSpSubTotal(float spSubTotal) {
		this.spSubTotal = spSubTotal;
	}


	public float getSpAdvance() {
		return spAdvance;
	}


	public void setSpAdvance(float spAdvance) {
		this.spAdvance = spAdvance;
	}


	public float getRmAmount() {
		return rmAmount;
	}


	public void setRmAmount(float rmAmount) {
		this.rmAmount = rmAmount;
	}


	public String getSpDeliveryDate() {
		return spDeliveryDate;
	}


	public void setSpDeliveryDate(String spDeliveryDate) {
		this.spDeliveryDate = spDeliveryDate;
	}


	public String getSpDeliveryPlace() {
		return spDeliveryPlace;
	}


	public void setSpDeliveryPlace(String spDeliveryPlace) {
		this.spDeliveryPlace = spDeliveryPlace;
	}


	public String getSpCustName() {
		return spCustName;
	}


	public void setSpCustName(String spCustName) {
		this.spCustName = spCustName;
	}


	public String getSpCustMobNo() {
		return spCustMobNo;
	}


	public void setSpCustMobNo(String spCustMobNo) {
		this.spCustMobNo = spCustMobNo;
	}


	public String getSpfName() {
		return spfName;
	}


	public void setSpfName(String spfName) {
		this.spfName = spfName;
	}


	public float getSpSelectedWeight() {
		return spSelectedWeight;
	}


	public void setSpSelectedWeight(float spSelectedWeight) {
		this.spSelectedWeight = spSelectedWeight;
	}


	@Override
	public String toString() {
		return "GetSpCakeOrders [spOrderNo=" + spOrderNo + ", frName=" + frName + ", frMob=" + frMob + ", spName="
				+ spName + ", orderDate=" + orderDate + ", spPrice=" + spPrice + ", spInstructions=" + spInstructions
				+ ", spSubTotal=" + spSubTotal + ", spAdvance=" + spAdvance + ", rmAmount=" + rmAmount
				+ ", spDeliveryDate=" + spDeliveryDate + ", spSelectedWeight=" + spSelectedWeight + ", spDeliveryPlace="
				+ spDeliveryPlace + ", spCustName=" + spCustName + ", spCustMobNo=" + spCustMobNo + ", spfName="
				+ spfName + "]";
	}

 
	
	
}