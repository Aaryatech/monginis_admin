package com.ats.adminpanel.model.creditnote;

import java.util.List;

public class CreditPrintBean {
	
	
	CreditNoteHeaderPrint creditHeader;

	public CreditNoteHeaderPrint getCreditHeader() {
		return creditHeader;
	}

	public void setCreditHeader(CreditNoteHeaderPrint creditHeader) {
		this.creditHeader = creditHeader;
	}

	@Override
	public String toString() {
		return "CreditPrintBean [creditHeader=" + creditHeader + "]";
	}
	
	
	/*GetCreditNoteHeaders headers;
	
	
	List<GetCrnDetails> crnDetails;
	


	public GetCreditNoteHeaders getHeaders() {
		return headers;
	}


	public void setHeaders(GetCreditNoteHeaders headers) {
		this.headers = headers;
	}


	public List<GetCrnDetails> getCrnDetails() {
		return crnDetails;
	}


	public void setCrnDetails(List<GetCrnDetails> crnDetails) {
		this.crnDetails = crnDetails;
	}


	@Override
	public String toString() {
		return "CreditPrintBean [headers=" + headers + ", crnDetails=" + crnDetails + "]";
	}
*/

	
}
