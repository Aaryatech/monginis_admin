package com.ats.adminpanel.model.RawMaterial;

import java.io.Serializable;
import java.util.List;

import com.ats.adminpanel.model.modules.ErrorMessage;


public class RmItemSubCatList implements Serializable{
	List<GetRmItemSubCat> rmItemSubCategory;

	ErrorMessage errorMessage;

	public List<GetRmItemSubCat> getRmItemSubCategory() {
		return rmItemSubCategory;
	}

	public void setRmItemSubCategory(List<GetRmItemSubCat> rmItemSubCategory) {
		this.rmItemSubCategory = rmItemSubCategory;
	}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(ErrorMessage errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
}
