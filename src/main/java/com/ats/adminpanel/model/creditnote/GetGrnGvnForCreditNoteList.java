package com.ats.adminpanel.model.creditnote;

import java.util.List;

import com.ats.adminpanel.model.Info;

public class GetGrnGvnForCreditNoteList {

	List<GetGrnGvnForCreditNote> getGrnGvnForCreditNotes;
	
	Info info;

	public List<GetGrnGvnForCreditNote> getGetGrnGvnForCreditNotes() {
		return getGrnGvnForCreditNotes;
	}

	public void setGetGrnGvnForCreditNotes(List<GetGrnGvnForCreditNote> getGrnGvnForCreditNotes) {
		this.getGrnGvnForCreditNotes = getGrnGvnForCreditNotes;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "GetGrnGvnForCreditNoteList [getGrnGvnForCreditNotes=" + getGrnGvnForCreditNotes + ", info=" + info
				+ "]";
	}
	
	
	
	
	
}
