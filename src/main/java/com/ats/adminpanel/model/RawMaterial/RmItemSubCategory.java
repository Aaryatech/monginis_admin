package com.ats.adminpanel.model.RawMaterial;

import java.io.Serializable;

public class RmItemSubCategory  implements Serializable{


	private int subCatId;
	

	private int catId;
	
	
	private String subCatName;
	
	private String subCatDesc;
	
	private int delStatus;

	
	
	public String getSubCatDesc() {
		return subCatDesc;
	}

	public void setSubCatDesc(String subCatDesc) {
		this.subCatDesc = subCatDesc;
	}

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

	public String getSubCatName() {
		return subCatName;
	}

	public void setSubCatName(String subCatName) {
		this.subCatName = subCatName;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	@Override
	public String toString() {
		return "RmItemSubCategory [subCatId=" + subCatId + ", catId=" + catId + ", subCatName=" + subCatName
				+ ", subCatDesc=" + subCatDesc + ", delStatus=" + delStatus + "]";
	}
}
