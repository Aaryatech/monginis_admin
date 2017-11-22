package com.ats.adminpanel.model.RawMaterial;

import java.io.Serializable;
import java.util.List;

import com.ats.adminpanel.model.ErrorMessage;


public class RmItemSubCatList implements Serializable{
	List<RmItemSubCategory> rmItemSubCategory;

	ErrorMessage errorMessage;

   
	public List<RmItemSubCategory> getRmItemSubCategory() {
		return rmItemSubCategory;
	}

	public void setRmItemSubCategory(List<RmItemSubCategory> rmItemSubCategory) {
		this.rmItemSubCategory = rmItemSubCategory;
	}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(ErrorMessage errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
}
