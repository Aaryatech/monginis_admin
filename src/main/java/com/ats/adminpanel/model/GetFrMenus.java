
package com.ats.adminpanel.model;

import java.util.List;

public class GetFrMenus {

    private ErrorMessage errorMessage;
    private List<FrMenu> frMenus = null;

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<FrMenu> getFrMenus() {
        return frMenus;
    }

    public void setFrMenus(List<FrMenu> frMenus) {
        this.frMenus = frMenus;
    }

	@Override
	public String toString() {
		return "GetFrMenus [errorMessage=" + errorMessage + ", frMenus=" + frMenus + "]";
	}

    
    
    
}
