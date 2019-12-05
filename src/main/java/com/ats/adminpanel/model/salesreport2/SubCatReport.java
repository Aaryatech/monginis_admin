package com.ats.adminpanel.model.salesreport2;

public class SubCatReport {

	private int subCatId;
	private int catId;

	private String subCatName;

	private float soldQty;
	private float soldAmt;
	private float varQty;
	private float varAmt;

	private float retQty;
	private float retAmt;

	private float netQty;
	private float netAmt;

	private float retAmtPer;

	public int getSubCatId() {
		return subCatId;
	}

	public void setSubCatId(int subCatId) {
		this.subCatId = subCatId;
	}

	public int getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	public String getSubCatName() {
		return subCatName;
	}

	public void setSubCatName(String subCatName) {
		this.subCatName = subCatName;
	}

	public float getSoldQty() {
		return soldQty;
	}

	public void setSoldQty(float soldQty) {
		this.soldQty = soldQty;
	}

	public float getSoldAmt() {
		return soldAmt;
	}

	public void setSoldAmt(float soldAmt) {
		this.soldAmt = soldAmt;
	}

	public float getVarQty() {
		return varQty;
	}

	public void setVarQty(float varQty) {
		this.varQty = varQty;
	}

	public float getVarAmt() {
		return varAmt;
	}

	public void setVarAmt(float varAmt) {
		this.varAmt = varAmt;
	}

	public float getRetQty() {
		return retQty;
	}

	public void setRetQty(float retQty) {
		this.retQty = retQty;
	}

	public float getRetAmt() {
		return retAmt;
	}

	public void setRetAmt(float retAmt) {
		this.retAmt = retAmt;
	}

	public float getNetQty() {
		return netQty;
	}

	public void setNetQty(float netQty) {
		this.netQty = netQty;
	}

	public float getNetAmt() {
		return netAmt;
	}

	public void setNetAmt(float netAmt) {
		this.netAmt = netAmt;
	}

	public float getRetAmtPer() {
		return retAmtPer;
	}

	public void setRetAmtPer(float retAmtPer) {
		this.retAmtPer = retAmtPer;
	}

	@Override
	public String toString() {
		return "SubCatReport [subCatId=" + subCatId + ", catId=" + catId + ", subCatName=" + subCatName + ", soldQty="
				+ soldQty + ", soldAmt=" + soldAmt + ", varQty=" + varQty + ", varAmt=" + varAmt + ", retQty=" + retQty
				+ ", retAmt=" + retAmt + ", netQty=" + netQty + ", netAmt=" + netAmt + ", retAmtPer=" + retAmtPer + "]";
	}

}
