package com.ats.adminpanel.model.login;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User implements Serializable {

	
	private static final long serialVersionUID = 1L;

    private int id;
	private String username;
    private String password;
    private int usertype;
    private int delStatus;
    private boolean error;
    private String message;
    private int roleId;
    private int deptId;
    
    
        
    
    public User() {
		super();
	}
    
    
	public User(int id, String username, String password, int usertype, int delStatus, boolean error,
			String message,int deptId, int roleId) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.usertype = usertype;
		this.delStatus = delStatus;
		this.error = error;
		this.message = message;
		this.deptId=deptId;
		this.roleId=roleId;
	}


	public int getDeptId() {
		return deptId;
	}


	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}


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
	

	public boolean isError() {
		return error;
	}


	public void setError(boolean error) {
		this.error = error;
	}


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	public int getRoleId() {
		return roleId;
	}


	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", usertype=" + usertype
				+ ", delStatus=" + delStatus + ", error=" + error + ", message=" + message + ", roleId=" + roleId
				+ ", deptId=" + deptId + "]";
	}


	 
	
}
