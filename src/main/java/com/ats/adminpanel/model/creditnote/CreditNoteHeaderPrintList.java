package com.ats.adminpanel.model.creditnote;

import java.util.List;

public class CreditNoteHeaderPrintList {

	
	List<CreditNoteHeaderPrint> headerPrintList;

	public List<CreditNoteHeaderPrint> getHeaderPrintList() {
		return headerPrintList;
	}

	public void setHeaderPrintList(List<CreditNoteHeaderPrint> headerPrintList) {
		this.headerPrintList = headerPrintList;
	}

	@Override
	public String toString() {
		return "CreditNoteHeaderPrintList [headerPrintList=" + headerPrintList + "]";
	}
	
	
}


