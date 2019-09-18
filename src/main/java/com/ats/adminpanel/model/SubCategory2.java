package com.ats.adminpanel.model;

import java.io.Serializable;

public class SubCategory2 implements Serializable{

	
	private int miniCatId;
	
	private String miniCatName;
	 
	private int subCatId;	
	
	private int delStatus;

	public int getMiniCatId() {
		return miniCatId;
	}

	public String getMiniCatName() {
		return miniCatName;
	}

	public int getSubCatId() {
		return subCatId;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setMiniCatId(int miniCatId) {
		this.miniCatId = miniCatId;
	}

	public void setMiniCatName(String miniCatName) {
		this.miniCatName = miniCatName;
	}

	public void setSubCatId(int subCatId) {
		this.subCatId = subCatId;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	@Override
	public String toString() {
		return "SubCategory2 [miniCatId=" + miniCatId + ", miniCatName=" + miniCatName + ", subCatId=" + subCatId
				+ ", delStatus=" + delStatus + "]";
	}

	
	
}
