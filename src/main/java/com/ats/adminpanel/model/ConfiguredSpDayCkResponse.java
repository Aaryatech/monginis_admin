package com.ats.adminpanel.model;

import java.util.List;

public class ConfiguredSpDayCkResponse {

	List<GetConfiguredSpDayCk> configuredSpDayCkList;
	ErrorMessage errorMessage;
	public List<GetConfiguredSpDayCk> getConfiguredSpDayCkList() {
		return configuredSpDayCkList;
	}
	public void setConfiguredSpDayCkList(List<GetConfiguredSpDayCk> configuredSpDayCkList) {
		this.configuredSpDayCkList = configuredSpDayCkList;
	}
	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(ErrorMessage errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
}
