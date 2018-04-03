package com.ats.adminpanel.model.RawMaterial;

import java.util.List;

import com.ats.adminpanel.model.ErrorMessage;


public class RawMaterialDetailsList {

	List<RawMaterialDetails> rawMaterialDetailsList;
	
	ErrorMessage errorMessage;

	public List<RawMaterialDetails> getRawMaterialDetailsList() {
		return rawMaterialDetailsList;
	}

	public void setRawMaterialDetailsList(List<RawMaterialDetails> rawMaterialDetailsList) {
		this.rawMaterialDetailsList = rawMaterialDetailsList;
	}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(ErrorMessage errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
}
