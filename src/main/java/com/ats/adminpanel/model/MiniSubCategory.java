package com.ats.adminpanel.model;
 
public class MiniSubCategory {
	
	 
	private int miniCatId; 
	private String miniCatName; 
	private int subCatId;	 
	private int delStatus; 
	private String subCatName; 
	private String catName;
	public int getMiniCatId() {
		return miniCatId;
	}
	public void setMiniCatId(int miniCatId) {
		this.miniCatId = miniCatId;
	}
	public String getMiniCatName() {
		return miniCatName;
	}
	public void setMiniCatName(String miniCatName) {
		this.miniCatName = miniCatName;
	}
	public int getSubCatId() {
		return subCatId;
	}
	public void setSubCatId(int subCatId) {
		this.subCatId = subCatId;
	}
	public int getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}
	public String getSubCatName() {
		return subCatName;
	}
	public void setSubCatName(String subCatName) {
		this.subCatName = subCatName;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	@Override
	public String toString() {
		return "MiniSubCategory [miniCatId=" + miniCatId + ", miniCatName=" + miniCatName + ", subCatId=" + subCatId
				+ ", delStatus=" + delStatus + ", subCatName=" + subCatName + ", catName=" + catName + "]";
	}
	
	

}
