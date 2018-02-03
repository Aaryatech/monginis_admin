package com.ats.adminpanel.model.production.mixing.temp;


//same bean used for Bom and mixing

public class GetSFPlanDetailForMixing {

	
	private int itemDetailId;
	
	private int itemId;
	
	private int rmType;
	
	private int rmId;
	
	private String uom;
	
	
	private float rmQty;
	
	private float noPiecesPerItem;
	
	private String rmName;
	
	private float planQty;
	
	float total;
	
	//private float mulFactor; // Not in new Query by sumit Sir 30 Jan 2018
	//private int sfId;
	
	

	public int getItemDetailId() {
		return itemDetailId;
	}

	public void setItemDetailId(int itemDetailId) {
		this.itemDetailId = itemDetailId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getRmType() {
		return rmType;
	}

	public void setRmType(int rmType) {
		this.rmType = rmType;
	}

	public int getRmId() {
		return rmId;
	}

	public void setRmId(int rmId) {
		this.rmId = rmId;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public float getRmQty() {
		return rmQty;
	}

	public void setRmQty(float rmQty) {
		this.rmQty = rmQty;
	}

	public float getNoPiecesPerItem() {
		return noPiecesPerItem;
	}

	public void setNoPiecesPerItem(float noPiecesPerItem) {
		this.noPiecesPerItem = noPiecesPerItem;
	}

	public String getRmName() {
		return rmName;
	}

	public void setRmName(String rmName) {
		this.rmName = rmName;
	}

	public float getPlanQty() {
		return planQty;
	}

	public void setPlanQty(float planQty) {
		this.planQty = planQty;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "GetSFPlanDetailForMixing [itemDetailId=" + itemDetailId + ", itemId=" + itemId + ", rmType=" + rmType
				+ ", rmId=" + rmId + ", uom=" + uom + ", rmQty=" + rmQty + ", noPiecesPerItem=" + noPiecesPerItem
				+ ", rmName=" + rmName + ", planQty=" + planQty + ", total=" + total + "]";
	}

}
