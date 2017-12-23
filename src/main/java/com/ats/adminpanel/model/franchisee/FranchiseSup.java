package com.ats.adminpanel.model.franchisee;

import java.io.Serializable;


public class FranchiseSup {

	private boolean error;
	
	private String message;

	private int id;
	
	private int frId;
	
	private String frPanNo;
	
	private String frState;
	
	private String frCountry;
	
	private int delStatus;

	
	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFrId() {
		return frId;
	}

	public void setFrId(int frId) {
		this.frId = frId;
	}

	public String getFrPanNo() {
		return frPanNo;
	}

	public void setFrPanNo(String frPanNo) {
		this.frPanNo = frPanNo;
	}

	public String getFrState() {
		return frState;
	}

	public void setFrState(String frState) {
		this.frState = frState;
	}

	public String getFrCountry() {
		return frCountry;
	}

	public void setFrCountry(String frCountry) {
		this.frCountry = frCountry;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	@Override
	public String toString() {
		return "FranchiseSup [id=" + id + ", frId=" + frId + ", frPanNo=" + frPanNo + ", frState=" + frState
				+ ", frCountry=" + frCountry + ", delStatus=" + delStatus + "]";
	}
}
