package com.ats.adminpanel.model.spprod;

import java.util.List;

import com.ats.adminpanel.model.ErrorMessage;

public class MDeptList {
	
	private List<MDept> list;
	
	private ErrorMessage errorMessage;

	public List<MDept> getList() {
		return list;
	}

	public void setList(List<MDept> list) {
		this.list = list;
	}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(ErrorMessage errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return "MDeptList [list=" + list + ", errorMessage=" + errorMessage + "]";
	}

	
}
