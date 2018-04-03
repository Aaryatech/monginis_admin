package com.ats.adminpanel.model.accessright;

import java.util.List;

public class AssignRoleDetailList {

	
	private String roleName;
	
	private int roleId;
	
	
	String roleJson;
	//List<AccessRightModule> accessRightModuleList;

	private int delStatus;
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	 

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	public String getRoleJson() {
		return roleJson;
	}

	public void setRoleJson(String roleJson) {
		this.roleJson = roleJson;
	}

	@Override
	public String toString() {
		return "AssignRoleDetailList [roleName=" + roleName + ", roleId=" + roleId + ", roleJson=" + roleJson
				+ ", delStatus=" + delStatus + "]";
	}
 
	 
	
	
}
