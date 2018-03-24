package com.ats.adminpanel.model.RawMaterial;

public class GetRmItemCategory {

	private int catId;
	
	private int grpId;
	

	private String grpName;
	 
	
	private String catName;
	
	
	private String catDesc;

	private int delStatus;

	public int getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	public int getGrpId() {
		return grpId;
	}

	public void setGrpId(int grpId) {
		this.grpId = grpId;
	}

	public String getGrpName() {
		return grpName;
	}

	public void setGrpName(String grpName) {
		this.grpName = grpName;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public String getCatDesc() {
		return catDesc;
	}

	public void setCatDesc(String catDesc) {
		this.catDesc = catDesc;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	@Override
	public String toString() {
		return "GetRmItemCategory [catId=" + catId + ", grpId=" + grpId + ", grpName=" + grpName + ", catName="
				+ catName + ", catDesc=" + catDesc + ", delStatus=" + delStatus + "]";
	}
	
	

}
