package com.ats.adminpanel.model.RawMaterial;

public class GetItemDetail {


	private int itemDetailId;
	
	
	private int itemId;
	
	private String itemName;
	
	private int rmId;
	
	private String rmName;
	
	private int rmType;
	
	private int rmUomId;
	
	private int rmQty;
	
	private int rmWeight;
	
	private int delStatus;

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

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
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

	public int getRmUomId() {
		return rmUomId;
	}

	public void setRmUomId(int rmUomId) {
		this.rmUomId = rmUomId;
	}

	public int getRmQty() {
		return rmQty;
	}

	public void setRmQty(int rmQty) {
		this.rmQty = rmQty;
	}

	public int getRmWeight() {
		return rmWeight;
	}

	public void setRmWeight(int rmWeight) {
		this.rmWeight = rmWeight;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	@Override
	public String toString() {
		return "GetItemDetail [itemDetailId=" + itemDetailId + ", itemId=" + itemId + ", itemName=" + itemName
				+ ", rmId=" + rmId + ", rmName=" + rmName + ", rmType=" + rmType 
				+ ", rmUomId=" + rmUomId + ", rmQty=" + rmQty + ", rmWeight=" + rmWeight + ", delStatus=" + delStatus
				+ "]";
	}
	
}
