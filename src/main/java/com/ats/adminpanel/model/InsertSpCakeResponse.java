package com.ats.adminpanel.model;

public class InsertSpCakeResponse {
	private String message;
	public boolean error;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isError() {
		return error;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	@Override
	public String toString() {
		return "InsetSpCakeResponse [message=" + message + ", error=" + error + "]";
	}
	
	
	

}
