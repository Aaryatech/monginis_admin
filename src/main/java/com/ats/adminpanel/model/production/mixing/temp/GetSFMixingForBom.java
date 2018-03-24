package com.ats.adminpanel.model.production.mixing.temp;

public class GetSFMixingForBom {
	
private int sfDid;
	
	private int sfId;
	
	private int rmId;
	
	private String rmName;
	
	private int rmType;
	
	private String uom;
	
	float total;

	public int getSfDid() {
		return sfDid;
	}

	public void setSfDid(int sfDid) {
		this.sfDid = sfDid;
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

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "GetSFMixingForBom [sfDid=" + sfDid + ", sfId=" + sfId + ", rmId=" + rmId + ", rmName=" + rmName
				+ ", rmType=" + rmType + ", uom=" + uom + ", total=" + total + "]";
	}
	
}
