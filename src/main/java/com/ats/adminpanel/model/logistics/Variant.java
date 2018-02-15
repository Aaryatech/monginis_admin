package com.ats.adminpanel.model.logistics; 

public class Variant {
	 
	private int variantId;   
	private String variantName;  
	private int makeId; 
	private int vehTypeId; 
	private int delStatus;
	public int getVariantId() {
		return variantId;
	}
	public void setVariantId(int variantId) {
		this.variantId = variantId;
	}
	public String getVariantName() {
		return variantName;
	}
	public void setVariantName(String variantName) {
		this.variantName = variantName;
	}
	public int getMakeId() {
		return makeId;
	}
	public void setMakeId(int makeId) {
		this.makeId = makeId;
	}
	public int getVehTypeId() {
		return vehTypeId;
	}
	public void setVehTypeId(int vehTypeId) {
		this.vehTypeId = vehTypeId;
	}
	public int getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}
	@Override
	public String toString() {
		return "Variant [variantId=" + variantId + ", variantName=" + variantName + ", makeId=" + makeId
				+ ", vehTypeId=" + vehTypeId + ", delStatus=" + delStatus + "]";
	}
	
	

}
