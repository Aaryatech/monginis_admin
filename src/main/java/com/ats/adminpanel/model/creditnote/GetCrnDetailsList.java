package com.ats.adminpanel.model.creditnote;

import java.util.List;

public class GetCrnDetailsList {

	List<GetCrnDetails> crnDetails;

	public List<GetCrnDetails> getCrnDetails() {
		return crnDetails;
	}

	public void setCrnDetails(List<GetCrnDetails> crnDetails) {
		this.crnDetails = crnDetails;
	}

	@Override
	public String toString() {
		return "GetCrnDetailsList [crnDetails=" + crnDetails + "]";
	}
	
	
	
}
