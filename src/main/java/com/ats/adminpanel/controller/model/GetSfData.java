package com.ats.adminpanel.controller.model;

public class GetSfData {

	private int sfId;
	
	private float rmQty;
	
	private int type;

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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "GetSfData [sfId=" + sfId + ", rmQty=" + rmQty + ", type=" + type + "]";
	}

	
}
