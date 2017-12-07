package com.ats.adminpanel.model.RawMaterial;

public class GetSfType {
	
	private int id;
	
	private String sfName;
	
	private int delStatus;

	public int getId() {
		return id;
	}

	public String getSfName() {
		return sfName;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setSfName(String sfName) {
		this.sfName = sfName;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	@Override
	public String toString() {
		return "GetSfType [id=" + id + ", sfName=" + sfName + ", delStatus=" + delStatus + "]";
	}
	
}
