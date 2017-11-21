package com.ats.adminpanel.model.RawMaterial; 

public class Info {
	private String message;
       
	private boolean isError;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isError() {
		return isError;
	}

	public void setError(boolean isError) {
		this.isError = isError;
	}

	@Override
	public String toString() {
		return "Info [message=" + message + ", isError=" + isError + "]";
	}
	
	
}
