package com.ats.adminpanel.model;


public class GetSubCategory{

	private int subCatId;
	
	private String subCatName;
	
	private int catId;
	
	private int delStatus;

	private int qty;
	
	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public int getSubCatId() {
		return subCatId;
	}

	public String getSubCatName() {
		return subCatName;
	}

	public int getCatId() {
		return catId;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setSubCatId(int subCatId) {
		this.subCatId = subCatId;
	}

	public void setSubCatName(String subCatName) {
		this.subCatName = subCatName;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	@Override
	public String toString() {
		return "GetSubCategory [subCatId=" + subCatId + ", subCatName=" + subCatName + ", catId=" + catId
				+ ", delStatus=" + delStatus + "]";
	}	
	
	
}
