package com.ats.adminpanel.controller.model;

public class GetSfData {

	private int sfId;
	
	private float rmQty;

	public int getSfId() {
		return sfId;
	}

	public float getRmQty() {
		return rmQty;
	}

	public void setSfId(int sfId) {
		this.sfId = sfId;
	}

	public void setRmQty(float rmQty) {
		this.rmQty = rmQty;
	}

	@Override
	public String toString() {
		return "GetSfData [sfId=" + sfId + ", rmQty=" + rmQty + "]";
	}
	
}
