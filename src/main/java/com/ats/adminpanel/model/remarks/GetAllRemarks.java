package com.ats.adminpanel.model.remarks;

public class GetAllRemarks {
	
	private int remarkId;
	
	private String remark;
	
	private int moduleId;
	
	private int subModuleId;
	
	private int isFrUsed;
	
	private int isActive;
	
	private int isDelStatus;

	public int getRemarkId() {
		return remarkId;
	}

	public String getRemark() {
		return remark;
	}

	public int getModuleId() {
		return moduleId;
	}

	public int getSubModuleId() {
		return subModuleId;
	}

	public int getIsFrUsed() {
		return isFrUsed;
	}

	public void setRemarkId(int remarkId) {
		this.remarkId = remarkId;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}

	public void setSubModuleId(int subModuleId) {
		this.subModuleId = subModuleId;
	}

	public void setIsFrUsed(int isFrUsed) {
		this.isFrUsed = isFrUsed;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public int getIsDelStatus() {
		return isDelStatus;
	}

	public void setIsDelStatus(int isDelStatus) {
		this.isDelStatus = isDelStatus;
	}

	@Override
	public String toString() {
		return "GetAllRemarks [remarkId=" + remarkId + ", remark=" + remark + ", moduleId=" + moduleId
				+ ", subModuleId=" + subModuleId + ", isFrUsed=" + isFrUsed + ", isActive=" + isActive
				+ ", isDelStatus=" + isDelStatus + "]";
	}

	 

}
