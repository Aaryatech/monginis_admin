package com.ats.adminpanel.model.RawMaterial;

public class RawMaterialUom {
	

	private int uomId;
	

	private String uom;


	public int getUomId() {
		return uomId;
	}


	public void setUomId(int uomId) {
		this.uomId = uomId;
	}


	public String getUom() {
		return uom;
	}


	public void setUom(String uom) {
		this.uom = uom;
	}


	@Override
	public String toString() {
		return "RawMaterialUom [uomId=" + uomId + ", uom=" + uom + "]";
	}
	
	
}
