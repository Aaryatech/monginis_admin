package com.ats.adminpanel.model.productionplan;

import java.util.List;

import com.ats.adminpanel.model.ErrorMessage;



public class GetMixingList {
	
	List<MixingHeader> mixingHeaderList;
	
	ErrorMessage errorMessage;

	public List<MixingHeader> getMixingHeaderList() {
		return mixingHeaderList;
	}

	public void setMixingHeaderList(List<MixingHeader> mixingHeaderList) {
		this.mixingHeaderList = mixingHeaderList;
	}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(ErrorMessage errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return "GetMixingList [mixingHeaderList=" + mixingHeaderList + ", errorMessage=" + errorMessage + "]";
	}
	
	

}
