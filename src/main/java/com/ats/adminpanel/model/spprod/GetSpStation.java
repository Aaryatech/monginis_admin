package com.ats.adminpanel.model.spprod;

import java.io.Serializable;

public class GetSpStation implements Serializable{


	private int stId;
	
	private String stName;
	
	private int deptId;
	
	private String deptName;
	
	private int stIsUsed;
	
	private String stLocation;
	
	private int delStatus;

	public int getStId() {
		return stId;
	}

	public void setStId(int stId) {
		this.stId = stId;
	}

	public String getStName() {
		return stName;
	}

	public void setStName(String stName) {
		this.stName = stName;
	}

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public int getStIsUsed() {
		return stIsUsed;
	}

	public void setStIsUsed(int stIsUsed) {
		this.stIsUsed = stIsUsed;
	}

	public String getStLocation() {
		return stLocation;
	}

	public void setStLocation(String stLocation) {
		this.stLocation = stLocation;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	@Override
	public String toString() {
		return "GetSpStation [stId=" + stId + ", stName=" + stName + ", deptId=" + deptId + ", deptName=" + deptName
				+ ", stIsUsed=" + stIsUsed + ", stLocation=" + stLocation + ", delStatus=" + delStatus + "]";
	}
	
	
}
