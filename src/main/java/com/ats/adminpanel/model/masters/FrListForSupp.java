package com.ats.adminpanel.model.masters;

public class FrListForSupp{

	private int frId;
	
	private String frName;

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

	@Override
	public String toString() {
		return "FrListForSupp [frId=" + frId + ", frName=" + frName + "]";
	}
	
}
