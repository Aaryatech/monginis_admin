package com.ats.adminpanel.model.RawMaterial;


public class GetRmItemSubCat {
		
	private int subCatId;
	
	
	private int catId;
	
	
	private String catName;
	
	private String subCatName;
	
	private String subCatDesc;
	
	private int delStatus;

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

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public String getSubCatName() {
		return subCatName;
	}

	public void setSubCatName(String subCatName) {
		this.subCatName = subCatName;
	}

	public String getSubCatDesc() {
		return subCatDesc;
	}

	public void setSubCatDesc(String subCatDesc) {
		this.subCatDesc = subCatDesc;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	@Override
	public String toString() {
		return "GetRmItemSubCat [subCatId=" + subCatId + ", catId=" + catId + ", catName=" + catName + ", subCatName="
				+ subCatName + ", subCatDesc=" + subCatDesc + ", delStatus=" + delStatus + "]";
	}
	
	
	
}
