package com.ats.adminpanel.model.accessright;

import java.util.List;

 
 
public class AccessRightModule {

 
	private int moduleId;
	 
	String moduleName;
	 
	String moduleDesc;
	 
	private int delStatus;
	
 
	private int orderBy;
	 
	List<AccessRightSubModule> accessRightSubModuleList;

	public int getModuleId() {
		return moduleId;
	}

	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getModuleDesc() {
		return moduleDesc;
	}

	public void setModuleDesc(String moduleDesc) {
		this.moduleDesc = moduleDesc;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	public List<AccessRightSubModule> getAccessRightSubModuleList() {
		return accessRightSubModuleList;
	}

	public void setAccessRightSubModuleList(List<AccessRightSubModule> accessRightSubModuleList) {
		this.accessRightSubModuleList = accessRightSubModuleList;
	}

	public int getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}

	@Override
	public String toString() {
		return "AccessRightModule [moduleId=" + moduleId + ", moduleName=" + moduleName + ", moduleDesc=" + moduleDesc
				+ ", delStatus=" + delStatus + ", orderBy=" + orderBy + ", accessRightSubModuleList="
				+ accessRightSubModuleList + "]";
	}

	 
	 
	
	
	
	
}
