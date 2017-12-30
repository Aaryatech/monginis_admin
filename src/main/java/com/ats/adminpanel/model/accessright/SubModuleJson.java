package com.ats.adminpanel.model.accessright;

public class SubModuleJson {

	
	private int subModuleId;
	
	 
	private int moduleId;
	 
	String subModulName;
	 
	String subModuleMapping;
	 
	String subModuleDesc;
	
	 
	private int type;
	 
	private String view;
	 
	private String addApproveConfig;
	 
	private String editReject;
	 
	private String deleteRejectApprove;

	public int getSubModuleId() {
		return subModuleId;
	}

	public void setSubModuleId(int subModuleId) {
		this.subModuleId = subModuleId;
	}

	public int getModuleId() {
		return moduleId;
	}

	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}

	public String getSubModulName() {
		return subModulName;
	}

	public void setSubModulName(String subModulName) {
		this.subModulName = subModulName;
	}

	public String getSubModuleMapping() {
		return subModuleMapping;
	}

	public void setSubModuleMapping(String subModuleMapping) {
		this.subModuleMapping = subModuleMapping;
	}

	public String getSubModuleDesc() {
		return subModuleDesc;
	}

	public void setSubModuleDesc(String subModuleDesc) {
		this.subModuleDesc = subModuleDesc;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

	public String getAddApproveConfig() {
		return addApproveConfig;
	}

	public void setAddApproveConfig(String addApproveConfig) {
		this.addApproveConfig = addApproveConfig;
	}

	public String getEditReject() {
		return editReject;
	}

	public void setEditReject(String editReject) {
		this.editReject = editReject;
	}

	public String getDeleteRejectApprove() {
		return deleteRejectApprove;
	}

	public void setDeleteRejectApprove(String deleteRejectApprove) {
		this.deleteRejectApprove = deleteRejectApprove;
	}

	@Override
	public String toString() {
		return "SubModuleJson [subModuleId=" + subModuleId + ", moduleId=" + moduleId + ", subModulName=" + subModulName
				+ ", subModuleMapping=" + subModuleMapping + ", subModuleDesc=" + subModuleDesc + ", type=" + type
				+ ", view=" + view + ", addApproveConfig=" + addApproveConfig + ", editReject=" + editReject
				+ ", deleteRejectApprove=" + deleteRejectApprove + "]";
	}
	
	
	 
}
