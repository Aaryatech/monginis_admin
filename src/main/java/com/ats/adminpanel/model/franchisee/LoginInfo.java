package com.ats.adminpanel.model.franchisee;

public class LoginInfo {

	private boolean error;
    private String message;
	private int accessRight;
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
	public int getAccessRight() {
		return accessRight;
	}
	public void setAccessRight(int accessRight) {
		this.accessRight = accessRight;
	}
	@Override
	public String toString() {
		return "LoginInfo [error=" + error + ", message=" + message + ", accessRight=" + accessRight + "]";
	}
	
}
