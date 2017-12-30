package com.ats.adminpanel.model.accessright;

 
 
public class AccessRightSubModule {

	
	
	
	 
	
	 
	private int subModuleId;
	
	 
	private int moduleId;
	 
	String subModulName;
	 
	String subModuleMapping;
	 
	String subModuleDesc;
	
	 
	private int type;
	 
	private int view;
	 
	private int addApproveConfig;
	 
	private int editReject;
	 
	private int deleteRejectApprove;
	 
	private int isDelete;
	
 
	private int orderBy;

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

	public int getView() {
		return view;
	}

	public void setView(int view) {
		this.view = view;
	}

	public int getAddApproveConfig() {
		return addApproveConfig;
	}

	public void setAddApproveConfig(int addApproveConfig) {
		this.addApproveConfig = addApproveConfig;
	}

	public int getEditReject() {
		return editReject;
	}

	public void setEditReject(int editReject) {
		this.editReject = editReject;
	}

	public int getDeleteRejectApprove() {
		return deleteRejectApprove;
	}

	public void setDeleteRejectApprove(int deleteRejectApprove) {
		this.deleteRejectApprove = deleteRejectApprove;
	}

	public int getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}

	public int getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}

	@Override
	public String toString() {
		return "AccessRightSubModule [subModuleId=" + subModuleId + ", moduleId=" + moduleId + ", subModulName="
				+ subModulName + ", subModuleMapping=" + subModuleMapping + ", subModuleDesc=" + subModuleDesc
				+ ", type=" + type + ", view=" + view + ", addApproveConfig=" + addApproveConfig + ", editReject="
				+ editReject + ", deleteRejectApprove=" + deleteRejectApprove + ", isDelete=" + isDelete + ", orderBy="
				+ orderBy + "]";
	}

	 
	
}
