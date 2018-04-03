package com.ats.adminpanel.model; 

public class GetPoDetailedForPdf {
	 
	private int poDetailId; 
	private int rmId; 
	private String rmName; 
	private String poQty; 
	private float poRate; 
	private float discPer; 
	private float poTaxable; 
	private String uom; 
	private String hsnCode;
	public String getHsnCode() {
		return hsnCode;
	}
	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}
	public int getPoDetailId() {
		return poDetailId;
	}
	public void setPoDetailId(int poDetailId) {
		this.poDetailId = poDetailId;
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
	public String getPoQty() {
		return poQty;
	}
	public void setPoQty(String poQty) {
		this.poQty = poQty;
	}
	public float getPoRate() {
		return poRate;
	}
	public void setPoRate(float poRate) {
		this.poRate = poRate;
	}
	public float getDiscPer() {
		return discPer;
	}
	public void setDiscPer(float discPer) {
		this.discPer = discPer;
	}
	public float getPoTaxable() {
		return poTaxable;
	}
	public void setPoTaxable(float poTaxable) {
		this.poTaxable = poTaxable;
	}
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	@Override
	public String toString() {
		return "GetPoDetailedForPdf [poDetailId=" + poDetailId + ", rmId=" + rmId + ", rmName=" + rmName + ", poQty="
				+ poQty + ", poRate=" + poRate + ", discPer=" + discPer + ", poTaxable=" + poTaxable + ", uom=" + uom
				+ ", hsnCode=" + hsnCode + "]";
	}
	 

}
