package com.ats.adminpanel.model.message;


public class Info {

    private String message;
  
    private Boolean error;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

	@Override
	public String toString() {
		return "Info [message=" + message + ", error=" + error + "]";
	}

}