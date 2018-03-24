package com.ats.adminpanel.model;

import java.io.Serializable;


public class SpCake{

	private int spId;
	
	private String spName;
	
	private String spCode;
	
	

	public String getSpCode() {
		return spCode;
	}

	public void setSpCode(String spCode) {
		this.spCode = spCode;
	}

	public int getSpId() {
		return spId;
	}

	public void setSpId(int spId) {
		this.spId = spId;
	}

	public String getSpName() {
		return spName;
	}

	public void setSpName(String spName) {
		this.spName = spName;
	}

	@Override
	public String toString() {
		return "SpCake [spId=" + spId + ", spName=" + spName + ", spCode=" + spCode + "]";
	}
     
}
