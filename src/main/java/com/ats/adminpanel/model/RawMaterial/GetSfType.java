package com.ats.adminpanel.model.RawMaterial;

public class GetSfType {
	
	private int id;
	
	private String sfTypeName;
	
	private int delStatus;

	public int getId() {
		return id;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	public String getSfTypeName() {
		return sfTypeName;
	}

	public void setSfTypeName(String sfTypeName) {
		this.sfTypeName = sfTypeName;
	}

	@Override
	public String toString() {
		return "GetSfType [id=" + id + ", sfTypeName=" + sfTypeName + ", delStatus=" + delStatus + "]";
	}
}
