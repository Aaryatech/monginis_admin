package com.ats.adminpanel.model.spprod;



public class SpStation{

	private boolean isError;
	
	private String message;
	
	private int stId;
	
	private String stName;
	
	private int deptId;
	
	private int stIsUsed;
	
	private String stLocation;
	
	private int delStatus;

	
	
	public boolean isError() {
		return isError;
	}

	public void setError(boolean isError) {
		this.isError = isError;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

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
		return "SpStation [isError=" + isError + ", message=" + message + ", stId=" + stId + ", stName=" + stName
				+ ", deptId=" + deptId + ", stIsUsed=" + stIsUsed + ", stLocation=" + stLocation + ", delStatus="
				+ delStatus + "]";
	}
}
