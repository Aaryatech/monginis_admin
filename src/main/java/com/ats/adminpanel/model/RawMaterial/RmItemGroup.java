package com.ats.adminpanel.model.RawMaterial;

public class RmItemGroup {

	
	private int grpId;
	
	private int delStatus;
	
	private String grpName;

	public int getGrpId() {
		return grpId;
	}

	public void setGrpId(int grpId) {
		this.grpId = grpId;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	public String getGrpName() {
		return grpName;
	}

	public void setGrpName(String grpName) {
		this.grpName = grpName;
	}

	@Override
	public String toString() {
		return "RmItemGroup [grpId=" + grpId + ", delStatus=" + delStatus + ", grpName=" + grpName + "]";
	}
	
	
}
