package com.ats.adminpanel.model.franchisee;

public class GetFranchiseSup {

	private int id;
	
	private int frId;
	
	private String frName;
	
	private String frPanNo;
	
	private String frState;
	
	private String frCountry;
	
	private int delStatus;

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

	public String getFrName() {
		return frName;
	}

	public void setFrName(String frName) {
		this.frName = frName;
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
		return "GetFranchiseSup [id=" + id + ", frId=" + frId + ", frName=" + frName + ", frPanNo=" + frPanNo
				+ ", frState=" + frState + ", frCountry=" + frCountry + ", delStatus=" + delStatus + "]";
	}
	
	
}
