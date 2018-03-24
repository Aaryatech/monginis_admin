
package com.ats.adminpanel.model.modules;


public class ErrorMessage {

   
    private Boolean error;
   
    private String message;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

	@Override
	public String toString() {
		return "info [error=" + error + ", message=" + message + "]";
	}
    
    

}
