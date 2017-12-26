package com.ats.adminpanel.model.spprod;

import java.io.Serializable;

public class GetSpDetailForBom{


	private int spCakeDetailedId;
	
	private int spId;
	
	private int rmId;
	
	private String rmName;
	
	private int rmType;
	
	private String uom;
	
	private int total;

	public int getSpCakeDetailedId() {
		return spCakeDetailedId;
	}

	public void setSpCakeDetailedId(int spCakeDetailedId) {
		this.spCakeDetailedId = spCakeDetailedId;
	}

	public int getSpId() {
		return spId;
	}

	public void setSpId(int spId) {
		this.spId = spId;
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
		return "GetSpMixingForBom [spCakeDetailedId=" + spCakeDetailedId + ", spId=" + spId + ", rmId=" + rmId
				+ ", rmName=" + rmName + ", rmType=" + rmType + ", uom=" + uom + ", total=" + total + "]";
	}
	
	
}
