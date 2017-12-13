package com.ats.adminpanel.model.production.mixing.temp;

public class GetTempMixItemDetail {
	
private int tempId;
	
	private int sfId;
	
	private int rmId;
	
	private String rmName;
	
	private int rmType;
	
	private String uom;
	
	int total;

	public int getTempId() {
		return tempId;
	}

	public void setTempId(int tempId) {
		this.tempId = tempId;
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

	public String getRmName() {
		return rmName;
	}

	public void setRmName(String rmName) {
		this.rmName = rmName;
	}

	public int getRmType() {
		return rmType;
	}

	public void setRmType(int rmType) {
		this.rmType = rmType;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "GetTempMixItemDetail [tempId=" + tempId + ", sfId=" + sfId + ", rmId=" + rmId + ", rmName=" + rmName
				+ ", rmType=" + rmType + ", uom=" + uom + ", total=" + total + "]";
	}
	

}
