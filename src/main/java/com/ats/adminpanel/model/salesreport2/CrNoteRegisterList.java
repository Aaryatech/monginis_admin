package com.ats.adminpanel.model.salesreport2;

import java.util.List;

public class CrNoteRegisterList {
	
	List<CrNoteRegItem> crNoteRegItemList;
	List<CrNoteRegSp> crNoteRegSpList;
	
	
	public List<CrNoteRegItem> getCrNoteRegItemList() {
		return crNoteRegItemList;
	}
	public void setCrNoteRegItemList(List<CrNoteRegItem> crNoteRegItemList) {
		this.crNoteRegItemList = crNoteRegItemList;
	}
	
	public List<CrNoteRegSp> getCrNoteRegSpList() {
		return crNoteRegSpList;
	}
	public void setCrNoteRegSpList(List<CrNoteRegSp> crNoteRegSpList) {
		this.crNoteRegSpList = crNoteRegSpList;
	}
	
	@Override
	public String toString() {
		return "CrNoteRegisterList [crNoteRegItemList=" + crNoteRegItemList + ", crNoteRegSpList=" + crNoteRegSpList
				+ "]";
	}

}
