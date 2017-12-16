package com.ats.adminpanel.model.spprod;

import java.util.List;

import com.ats.adminpanel.model.ErrorMessage;

public class GetEmployeeList {

	List<Employee> getEmpList;
	
	ErrorMessage errorMessage;

	public List<Employee> getGetEmpList() {
		return getEmpList;
	}

	public void setGetEmpList(List<Employee> getEmpList) {
		this.getEmpList = getEmpList;
	}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(ErrorMessage errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return "GetEmployeeList [getEmpList=" + getEmpList + ", errorMessage=" + errorMessage + "]";
	}
	
	
}
