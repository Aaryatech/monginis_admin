package com.ats.adminpanel.model.materialreceipt;

import java.io.Serializable;
import java.util.List;

import com.ats.adminpanel.model.ErrorMessage;
 

public class GetMaterialRecNoteList implements Serializable{

	List<MaterialRecNote> materialRecNoteList;
	
	ErrorMessage errorMessage;

	public List<MaterialRecNote> getMaterialRecNoteList() {
		return materialRecNoteList;
	}

	public void setMaterialRecNoteList(List<MaterialRecNote> materialRecNoteList) {
		this.materialRecNoteList = materialRecNoteList;
	}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(ErrorMessage errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return "GetMaterialRecNoteList [materialRecNoteList=" + materialRecNoteList + ", errorMessage=" + errorMessage
				+ "]";
	}

	 	
	
}
