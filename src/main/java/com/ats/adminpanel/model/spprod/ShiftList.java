package com.ats.adminpanel.model.spprod;

import java.io.Serializable;
import java.util.List;

import com.ats.adminpanel.model.ErrorMessage;


public class ShiftList implements Serializable{
	
	List<Shift> shiftList;
	
	ErrorMessage errorMessage;

	public List<Shift> getShiftList() {
		return shiftList;
	}

	public void setShiftList(List<Shift> shiftList) {
		this.shiftList = shiftList;
	}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(ErrorMessage errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return "ShiftList [shiftList=" + shiftList + ", errorMessage=" + errorMessage + "]";
	}
    
}
