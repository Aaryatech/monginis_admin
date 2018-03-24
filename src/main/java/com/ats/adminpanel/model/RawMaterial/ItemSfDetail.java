package com.ats.adminpanel.model.RawMaterial;


public class ItemSfDetail {
	
	
private int sfDid;
	
	private int sfId;
	
	private int rmId;
	
	private String rmName;
	
	private float rmQty;
	
	private int rmUnit;
	
	private float rmWeight;
	
	private int delStatus;
	 
	private int rmType;
	
	public int getRmType() {
		return rmType;
	}

	public void setRmType(int rmType) {
		this.rmType = rmType;
	}

	public int getSfDid() {
		return sfDid;
	}

	public int getSfId() {
		return sfId;
	}

	public int getRmId() {
		return rmId;
	}

	public String getRmName() {
		return rmName;
	}

	public float getRmQty() {
		return rmQty;
	}

	public int getRmUnit() {
		return rmUnit;
	}

	public float getRmWeight() {
		return rmWeight;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setSfDid(int sfDid) {
		this.sfDid = sfDid;
	}

	public void setSfId(int sfId) {
		this.sfId = sfId;
	}

	public void setRmId(int rmId) {
		this.rmId = rmId;
	}

	public void setRmName(String rmName) {
		this.rmName = rmName;
	}

	public void setRmQty(float rmQty) {
		this.rmQty = rmQty;
	}

	public void setRmUnit(int rmUnit) {
		this.rmUnit = rmUnit;
	}

	public void setRmWeight(float rmWeight) {
		this.rmWeight = rmWeight;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	@Override
	public String toString() {
		return "ItemSfDetail [sfDid=" + sfDid + ", sfId=" + sfId + ", rmId=" + rmId + ", rmName=" + rmName + ", rmQty="
				+ rmQty + ", rmUnit=" + rmUnit + ", rmWeight=" + rmWeight + ", delStatus=" + delStatus + "]";
	}
	
}
