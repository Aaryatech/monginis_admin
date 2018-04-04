package com.ats.adminpanel.model;

import java.util.Date;


import com.fasterxml.jackson.annotation.JsonFormat;

public class GetSpCkOrder {
 

	private int spOrderNo;

	private String frName;
	
	private String frMob;
	
	private String spName;
	
	private String orderDate;
	
	private float spPrice;
	
	private float spSelectedWeight;
	
	private String spInstructions;
	
	private float spSubTotal;
	
	private float spAdvance;
	
	private float rmAmount;
	
	private String spDeliveryDate;
	
	private String itemId;
	
	private String spDeliveryPlace;
	
	private String spCustName;
	
	private String spEvents;
	
	private String spEventsName;
	
	private String spCustMobNo;

	private String spfName;

	private String cusChoicePhoto;
	
	private String orderPhoto;
	
	public String getItemId() {
		return itemId;
	}


	public void setItemId(String itemId) {
		this.itemId = itemId;
	}


	public String getSpEvents() {
		return spEvents;
	}


	public void setSpEvents(String spEvents) {
		this.spEvents = spEvents;
	}


	public String getSpEventsName() {
		return spEventsName;
	}


	public void setSpEventsName(String spEventsName) {
		this.spEventsName = spEventsName;
	}


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


	public String getCusChoicePhoto() {
		return cusChoicePhoto;
	}


	public void setCusChoicePhoto(String cusChoicePhoto) {
		this.cusChoicePhoto = cusChoicePhoto;
	}


	public String getOrderPhoto() {
		return orderPhoto;
	}


	public void setOrderPhoto(String orderPhoto) {
		this.orderPhoto = orderPhoto;
	}


	@Override
	public String toString() {
		return "GetSpCkOrder [spOrderNo=" + spOrderNo + ", frName=" + frName + ", frMob=" + frMob + ", spName=" + spName
				+ ", orderDate=" + orderDate + ", spPrice=" + spPrice + ", spSelectedWeight=" + spSelectedWeight
				+ ", spInstructions=" + spInstructions + ", spSubTotal=" + spSubTotal + ", spAdvance=" + spAdvance
				+ ", rmAmount=" + rmAmount + ", spDeliveryDate=" + spDeliveryDate + ", itemId=" + itemId
				+ ", spDeliveryPlace=" + spDeliveryPlace + ", spCustName=" + spCustName + ", spEvents=" + spEvents
				+ ", spEventsName=" + spEventsName + ", spCustMobNo=" + spCustMobNo + ", spfName=" + spfName
				+ ", cusChoicePhoto=" + cusChoicePhoto + ", orderPhoto=" + orderPhoto + "]";
	}
    
	}
	

	