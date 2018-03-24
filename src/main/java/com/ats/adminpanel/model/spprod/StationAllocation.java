package com.ats.adminpanel.model.spprod;

import java.io.Serializable;



public class StationAllocation  {

	
	private boolean isError;

	private String message;
	

	private int allocationId;
	
	private int stationId;
	
	
	private int shiftId;
	

	private int empMistryId;
	
	
	private int empHelperId;
	
	
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

	public int getAllocationId() {
		return allocationId;
	}

	public void setAllocationId(int allocationId) {
		this.allocationId = allocationId;
	}

	public int getStationId() {
		return stationId;
	}

	public void setStationId(int stationId) {
		this.stationId = stationId;
	}

	public int getShiftId() {
		return shiftId;
	}

	public void setShiftId(int shiftId) {
		this.shiftId = shiftId;
	}

	public int getEmpMistryId() {
		return empMistryId;
	}

	public void setEmpMistryId(int empMistryId) {
		this.empMistryId = empMistryId;
	}

	public int getEmpHelperId() {
		return empHelperId;
	}

	public void setEmpHelperId(int empHelperId) {
		this.empHelperId = empHelperId;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	@Override
	public String toString() {
		return "StationAllocation [isError=" + isError + ", message=" + message + ", allocationId=" + allocationId
				+ ", stationId=" + stationId + ", shiftId=" + shiftId + ", empMistryId=" + empMistryId
				+ ", empHelperId=" + empHelperId + ", delStatus=" + delStatus + "]";
	}
	
}
