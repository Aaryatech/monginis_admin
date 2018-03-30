package com.ats.adminpanel.model.mastexcel;

import java.util.List;

import com.ats.adminpanel.model.ErrorMessage;



public class SpCakeList {
	
	List<SpecialCake> specialCakeList;
	
	ErrorMessage errorMessage;

	public List<SpecialCake> getSpecialCakeList() {
		return specialCakeList;
	}

	public void setSpecialCakeList(List<SpecialCake> specialCakeList) {
		this.specialCakeList = specialCakeList;
	}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(ErrorMessage errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return "SpCakeList [specialCakeList=" + specialCakeList + ", errorMessage=" + errorMessage + "]";
	}
	
	


}
