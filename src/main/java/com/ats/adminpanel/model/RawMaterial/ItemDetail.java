package com.ats.adminpanel.model.RawMaterial;

public class ItemDetail {


	private int itemDetailId;
	
	
	private int itemId;
	
	private String itemName;
	
	private int noOfPiecesPerItem;

	private int rmId;
	
	private String rmName;
	
	private int rmType;
	
	private int rmUomId;
	
	private float rmQty;
	
	private float rmWeight;
	
	private int delStatus;
	
	private int int1;

	private int int2;
	
	private String varchar1;
	
	
	
	public int getInt1() {
		return int1;
	}

	public int getInt2() {
		return int2;
	}

	public String getVarchar1() {
		return varchar1;
	}

	public void setInt1(int int1) {
		this.int1 = int1;
	}

	public void setInt2(int int2) {
		this.int2 = int2;
	}

	public void setVarchar1(String varchar1) {
		this.varchar1 = varchar1;
	}

	public int getNoOfPiecesPerItem() {
		return noOfPiecesPerItem;
	}

	public void setNoOfPiecesPerItem(int noOfPiecesPerItem) {
		this.noOfPiecesPerItem = noOfPiecesPerItem;
	}

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

	public float getRmQty() {
		return rmQty;
	}

	public void setRmQty(float rmQty) {
		this.rmQty = rmQty;
	}

	public float getRmWeight() {
		return rmWeight;
	}

	public void setRmWeight(float rmWeight) {
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
		return "ItemDetail [itemDetailId=" + itemDetailId + ", itemId=" + itemId + ", itemName=" + itemName
				+ ", noOfPiecesPerItem=" + noOfPiecesPerItem + ", rmId=" + rmId + ", rmName=" + rmName + ", rmType="
				+ rmType + ", rmUomId=" + rmUomId + ", rmQty=" + rmQty + ", rmWeight=" + rmWeight + ", delStatus="
				+ delStatus + ", int1=" + int1 + ", int2=" + int2 + ", varchar1=" + varchar1 + "]";
	}
    
}
