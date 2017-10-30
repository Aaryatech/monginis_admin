
package com.ats.adminpanel.model.modules;

import java.util.List;

public class MainModuleWithSubModule {

    
    private MainModule mainModule;
  
    private List<SubModuleList> subModuleList = null;

    public MainModule getMainModule() {
        return mainModule;
    }

    public void setMainModule(MainModule mainModule) {
        this.mainModule = mainModule;
    }

    public List<SubModuleList> getSubModuleList() {
        return subModuleList;
    }

    public void setSubModuleList(List<SubModuleList> subModuleList) {
        this.subModuleList = subModuleList;
    }

	@Override
	public String toString() {
		return "MainModuleWithSubModule [mainModule=" + mainModule + ", subModuleList=" + subModuleList + "]";
	}

}
