package com.ats.adminpanel.model.productionplan;

public class AddSfInManualMixing {
	
	private int sfId;
	private String sfName;
	private int qty;
	private float totalQty;
	private float mulFactor;
	private int uomId;
	private String uomName;
	
	
	public int getUomId() {
		return uomId;
	}
	public void setUomId(int uomId) {
		this.uomId = uomId;
	}
	public String getUomName() {
		return uomName;
	}
	public void setUomName(String uomName) {
		this.uomName = uomName;
	}
	public float getMulFactor() {
		return mulFactor;
	}
	public void setMulFactor(float mulFactor) {
		this.mulFactor = mulFactor;
	}
	public int getSfId() {
		return sfId;
	}
	public void setSfId(int sfId) {
		this.sfId = sfId;
	}
	public String getSfName() {
		return sfName;
	}
	public void setSfName(String sfName) {
		this.sfName = sfName;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public float getTotalQty() {
		return totalQty;
	}
	public void setTotalQty(float totalQty) {
		this.totalQty = totalQty;
	}
	@Override
	public String toString() {
		return "AddSfInManualMixing [sfId=" + sfId + ", sfName=" + sfName + ", qty=" + qty + ", totalQty=" + totalQty
				+ ", mulFactor=" + mulFactor + ", uomId=" + uomId + ", uomName=" + uomName + "]";
	}
	
	

}
