package com.ats.adminpanel.model;


public class AllFrIdName {
	
	private int frId;
	String frName;

	

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
		return "AllFrIdName [frId=" + frId + ", frName=" + frName + "]";
	}

		


}
