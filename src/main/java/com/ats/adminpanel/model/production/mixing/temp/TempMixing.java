package com.ats.adminpanel.model.production.mixing.temp;

public class TempMixing {

	private int tempId;

	private int sfId;

	private int rmId;

	private int qty;
	private int prodHeaderId;
	

	public int getProdHeaderId() {
		return prodHeaderId;
	}

	public void setProdHeaderId(int prodHeaderId) {
		this.prodHeaderId = prodHeaderId;
	}

	public int getSfId() {
		return sfId;
	}

	public void setSfId(int sfId) {
		this.sfId = sfId;
	}

	public int getRmId() {
		return rmId;
	}

	public void setRmId(int rmId) {
		this.rmId = rmId;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public int getTempId() {
		return tempId;
	}

	public void setTempId(int tempId) {
		this.tempId = tempId;
	}

	@Override
	public String toString() {
		return "TempMixing [tempId=" + tempId + ", sfId=" + sfId + ", rmId=" + rmId + ", qty=" + qty + ", prodHeaderId="
				+ prodHeaderId + "]";
	}

	
	
}
