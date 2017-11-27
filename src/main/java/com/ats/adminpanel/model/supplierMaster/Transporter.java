package com.ats.adminpanel.model.supplierMaster;

import java.io.Serializable;



public class Transporter implements Serializable{

	
	private int tranId;
	
	
	private String tranName;
	
	
	private String tranMobile;
	
	
	private String tranEmail;
	
	private int delStatus;

	public int getTranId() {
		return tranId;
	}

	public void setTranId(int tranId) {
		this.tranId = tranId;
	}

	public String getTranName() {
		return tranName;
	}

	public void setTranName(String tranName) {
		this.tranName = tranName;
	}

	public String getTranMobile() {
		return tranMobile;
	}

	public void setTranMobile(String tranMobile) {
		this.tranMobile = tranMobile;
	}

	public String getTranEmail() {
		return tranEmail;
	}

	public void setTranEmail(String tranEmail) {
		this.tranEmail = tranEmail;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	@Override
	public String toString() {
		return "Transporter [tranId=" + tranId + ", tranName=" + tranName + ", tranMobile=" + tranMobile
				+ ", tranEmail=" + tranEmail + ", delStatus=" + delStatus + "]";
	}
	
	
}
