package com.ats.adminpanel.model.RawMaterial;

import java.io.Serializable;
import java.util.List;

import com.ats.adminpanel.model.ErrorMessage;


public class RmItemCatList implements Serializable{
	
	List<RmItemCategory> rmItemCategoryList;

	ErrorMessage errorMessage;

	public List<RmItemCategory> getRmItemCategoryList() {
		return rmItemCategoryList;
	}

	public void setRmItemCategoryList(List<RmItemCategory> rmItemCategoryList) {
		this.rmItemCategoryList = rmItemCategoryList;
	}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(ErrorMessage errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
}
