package com.ats.adminpanel.model.spprod;

import java.io.Serializable;

public class Shift {
	

	private boolean isError;
	
	private String message;
	
	private int shiftId;
	
	
	private int shiftNo;
	
	
	private String shiftName;
	
	private int isUsed;
	
	private int delStatus;

	public boolean isError() {
		return isError;
	}

	public void setError(boolean isError) {
		this.isError = isError;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getShiftId() {
		return shiftId;
	}

	public void setShiftId(int shiftId) {
		this.shiftId = shiftId;
	}

	public int getShiftNo() {
		return shiftNo;
	}

	public void setShiftNo(int shiftNo) {
		this.shiftNo = shiftNo;
	}

	public String getShiftName() {
		return shiftName;
	}

	public void setShiftName(String shiftName) {
		this.shiftName = shiftName;
	}

	public int getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(int isUsed) {
		this.isUsed = isUsed;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	@Override
	public String toString() {
		return "Shift [isError=" + isError + ", message=" + message + ", shiftId=" + shiftId + ", shiftNo=" + shiftNo
				+ ", shiftName=" + shiftName + ", isUsed=" + isUsed + ", delStatus=" + delStatus + "]";
	}

}
