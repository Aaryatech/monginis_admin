package com.ats.adminpanel.model.flavours;

import java.util.List;

import com.ats.adminpanel.model.ErrorMessage;
public class FlavourDetailList {

	List<FlavourDetail> flavourDetailList;
	ErrorMessage errorMessage;
	public List<FlavourDetail> getFlavourDetailList() {
		return flavourDetailList;
	}
	public void setFlavourDetailList(List<FlavourDetail> flavourDetailList) {
		this.flavourDetailList = flavourDetailList;
	}
	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(ErrorMessage errorMessage) {
		this.errorMessage = errorMessage;
	}
	@Override
	public String toString() {
		return "FlavourDetailList [flavourDetailList=" + flavourDetailList + ", errorMessage=" + errorMessage + "]";
	}
	
	
}
