package com.ats.adminpanel.model.masters;

import java.io.Serializable;


public class GateSaleDiscount implements Serializable{

    private String message;

	private boolean isError;
	
	private int discountId;
	
	private String discountHead;

	private float discountPer;
	
	private int catId;
	
	private int userType;

	private int delStatus;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isError() {
		return isError;
	}

	public void setError(boolean isError) {
		this.isError = isError;
	}
    
	public int getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	public int getDiscountId() {
		return discountId;
	}

	public void setDiscountId(int discountId) {
		this.discountId = discountId;
	}

	public String getDiscountHead() {
		return discountHead;
	}

	public void setDiscountHead(String discountHead) {
		this.discountHead = discountHead;
	}

	public float getDiscountPer() {
		return discountPer;
	}

	public void setDiscountPer(float discountPer) {
		this.discountPer = discountPer;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	@Override
	public String toString() {
		return "GateSaleDiscount [message=" + message + ", isError=" + isError + ", discountId=" + discountId
				+ ", discountHead=" + discountHead + ", discountPer=" + discountPer + ", catId=" + catId + ", userType="
				+ userType + ", delStatus=" + delStatus + "]";
	}

	
}
