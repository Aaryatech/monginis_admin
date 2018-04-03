package com.ats.adminpanel.model.accessright;

import java.util.List;

public class ModuleJson {

	
	private int moduleId;
	 
	String moduleName;
	 
	String moduleDesc;
	
	List<SubModuleJson> subModuleJsonList;

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

	public List<SubModuleJson> getSubModuleJsonList() {
		return subModuleJsonList;
	}

	public void setSubModuleJsonList(List<SubModuleJson> subModuleJsonList) {
		this.subModuleJsonList = subModuleJsonList;
	}

	@Override
	public String toString() {
		return "ModuleJson [moduleId=" + moduleId + ", moduleName=" + moduleName + ", moduleDesc=" + moduleDesc
				+ ", subModuleJsonList=" + subModuleJsonList + "]";
	}
	
	
}
