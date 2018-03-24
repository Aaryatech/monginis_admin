package com.ats.adminpanel.model;



public class GetUserDetail {


	private int id;
	private String username;
    private String password;
    private int usertype;
    private int delStatus;
    private int deptId;
    private int roleId;
    private String deptName;
	
    private String roleName;
    
    private String typeName;

	


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public int getUsertype() {
		return usertype;
	}


	public void setUsertype(int usertype) {
		this.usertype = usertype;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}


	public String getRoleName() {
		return roleName;
	}


	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}


	public String getTypeName() {
		return typeName;
	}


	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}


	@Override
	public String toString() {
		return "GetUserDetail [id=" + id + ", username=" + username + ", password=" + password + ", usertype="
				+ usertype + ", delStatus=" + delStatus + ", deptId=" + deptId + ", roleId=" + roleId + ", deptName="
				+ deptName + ", roleName=" + roleName + ", typeName=" + typeName + "]";
	}


	
}
