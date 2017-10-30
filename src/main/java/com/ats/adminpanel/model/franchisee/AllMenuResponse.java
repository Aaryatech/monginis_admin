package com.ats.adminpanel.model.franchisee;

import java.util.List;

import com.ats.adminpanel.model.modules.ErrorMessage;

public class AllMenuResponse {

private List<Menu> menuConfigurationPage ;

private ErrorMessage errorMessage;


public List<Menu> getMenuConfigurationPage() {
	return menuConfigurationPage;
}

public void setMenuConfigurationPage(List<Menu> menuConfigurationPage) {
	this.menuConfigurationPage = menuConfigurationPage;
}

public ErrorMessage getErrorMessage() {
return errorMessage;
}

public void setErrorMessage(ErrorMessage errorMessage) {
this.errorMessage = errorMessage;
}

}