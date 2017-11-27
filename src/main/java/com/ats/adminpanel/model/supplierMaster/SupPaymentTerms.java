package com.ats.adminpanel.model.supplierMaster;

import java.io.Serializable;


public class SupPaymentTerms implements Serializable{
	

	private int payId;


	private String payDesc;

	private int creditDays;
	
	private int delStatus;

	public int getPayId() {
		return payId;
	}

	public void setPayId(int payId) {
		this.payId = payId;
	}

	public String getPayDesc() {
		return payDesc;
	}

	public void setPayDesc(String payDesc) {
		this.payDesc = payDesc;
	}

	public int getCreditDays() {
		return creditDays;
	}

	public void setCreditDays(int creditDays) {
		this.creditDays = creditDays;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	@Override
	public String toString() {
		return "SupPaymentTerms [payId=" + payId + ", payDesc=" + payDesc + ", creditDays=" + creditDays
				+ ", delStatus=" + delStatus + "]";
	}
	
	
	

}
