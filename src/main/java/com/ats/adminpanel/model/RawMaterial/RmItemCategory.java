package com.ats.adminpanel.model.RawMaterial;

import java.io.Serializable;


public class RmItemCategory {
 
	
	private int catId;
	

	private String catName;
	
	private String catDesc;
	
	private int grpId;
	
	private int delStatus;

	
	
	public int getGrpId() {
		return grpId;
	}

	public void setGrpId(int grpId) {
		this.grpId = grpId;
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

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	public String getCatDesc() {
		return catDesc;
	}

	public void setCatDesc(String catDesc) {
		this.catDesc = catDesc;
	}

	@Override
	public String toString() {
		return "RmItemCategory [catId=" + catId + ", catName=" + catName + ", catDesc=" + catDesc + ", delStatus="
				+ delStatus + "]";
	}

	
}
