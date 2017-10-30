
package com.ats.adminpanel.model.modules;

import java.util.List;

public class ModuleResponse {

    
    private List<MainModuleWithSubModule> mainModuleWithSubModule = null;
   
    private ErrorMessage errorMessage;

    public List<MainModuleWithSubModule> getMainModuleWithSubModule() {
        return mainModuleWithSubModule;
    }

    public void setMainModuleWithSubModule(List<MainModuleWithSubModule> mainModuleWithSubModule) {
        this.mainModuleWithSubModule = mainModuleWithSubModule;
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }

	@Override
	public String toString() {
		return "ModuleResponse [mainModuleWithSubModule=" + mainModuleWithSubModule + ", errorMessage=" + errorMessage
				+ "]";
	}

}
