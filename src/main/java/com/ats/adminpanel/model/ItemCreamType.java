package com.ats.adminpanel.model;

public class ItemCreamType {
	
	private int itemCreamId;
	private String itemCreamName;
	private int sequenceNo;
	private int delStatus;
	private int categoryId;

	public int getItemCreamId() {
		return itemCreamId;
	}

	public void setItemCreamId(int itemCreamId) {
		this.itemCreamId = itemCreamId;
	}

	public String getItemCreamName() {
		return itemCreamName;
	}

	public void setItemCreamName(String itemCreamName) {
		this.itemCreamName = itemCreamName;
	}

	public int getSequenceNo() {
		return sequenceNo;
	}

	public void setSequenceNo(int sequenceNo) {
		this.sequenceNo = sequenceNo;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	@Override
	public String toString() {
		return "ItemCreamType [itemCreamId=" + itemCreamId + ", itemCreamName=" + itemCreamName + ", sequenceNo="
				+ sequenceNo + ", delStatus=" + delStatus + ", categoryId=" + categoryId + "]";
	}

	
}
