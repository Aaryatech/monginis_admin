package com.ats.adminpanel.model.mastexcel;

import java.util.List;

import com.ats.adminpanel.model.ErrorMessage;

public class FranchiseeList {
 
	List<Franchisee> franchiseeList;
	
	ErrorMessage errorMessage;

	public List<Franchisee> getFranchiseeList() {
		return franchiseeList;
	}

	public void setFranchiseeList(List<Franchisee> franchiseeList) {
		this.franchiseeList = franchiseeList;
	}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(ErrorMessage errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return "FranchiseeList [franchiseeList=" + franchiseeList + ", errorMessage=" + errorMessage + "]";
	}
	
	
}


