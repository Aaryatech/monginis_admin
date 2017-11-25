package com.ats.adminpanel.model.RawMaterial;

public class GetUomAndTax {

	
	private int rmId;
	private int taxId;
	private String uom;
	private String taxDesc;
	private int uomId;
	
	public int getRmId() {
		return rmId;
	}
	public void setRmId(int rmId) {
		this.rmId = rmId;
	}
	public int getTaxId() {
		return taxId;
	}
	public void setTaxId(int taxId) {
		this.taxId = taxId;
	}
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	public String getTaxDesc() {
		return taxDesc;
	}
	public void setTaxDesc(String taxDesc) {
		this.taxDesc = taxDesc;
	}
	public int getUomId() {
		return uomId;
	}
	public void setUomId(int uomId) {
		this.uomId = uomId;
	}
	@Override
	public String toString() {
		return "GetUomAndTax [rmId=" + rmId + ", taxId=" + taxId + ", uom=" + uom + ", taxDesc=" + taxDesc + ", uomId="
				+ uomId + "]";
	}
	 
	
	
}
