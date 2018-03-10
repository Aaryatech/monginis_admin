package com.ats.adminpanel.model.creditnote;

import java.util.List;

public class CreditPrintBean {
	
	GetCreditNoteHeaders headers;
	
	
	List<GetCrnDetails> details;


	public GetCreditNoteHeaders getHeaders() {
		return headers;
	}


	public void setHeaders(GetCreditNoteHeaders headers) {
		this.headers = headers;
	}


	public List<GetCrnDetails> getDetails() {
		return details;
	}


	public void setDetails(List<GetCrnDetails> details) {
		this.details = details;
	}


	@Override
	public String toString() {
		return "CreditPrintBean [headers=" + headers + ", details=" + details + "]";
	}

}
