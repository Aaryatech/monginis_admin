package com.ats.adminpanel.model;

import java.util.List;
 
public class CreditNoteList {
	
	List<CreditNote> creditNoteList;
	
	ErrorMessage errorMessage;

	public List<CreditNote> getCreditNoteList() {
		return creditNoteList;
	}

	public void setCreditNoteList(List<CreditNote> creditNoteList) {
		this.creditNoteList = creditNoteList;
	}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(ErrorMessage errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return "CreditNoteList [creditNoteList=" + creditNoteList + ", errorMessage=" + errorMessage + "]";
	}
	
	

}
