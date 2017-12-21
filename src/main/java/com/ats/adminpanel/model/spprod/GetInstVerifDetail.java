package com.ats.adminpanel.model.spprod;

import java.io.Serializable;


public class GetInstVerifDetail{

	private int instVerifDId;
	
	private int instVerifHId;
	
	private int instId;
	
	private String instrumentName;
	
	
	private int beforeStatus;

	private int afterStatus;

	
	public int getBeforeStatus() {
		return beforeStatus;
	}

	public void setBeforeStatus(int beforeStatus) {
		this.beforeStatus = beforeStatus;
	}

	public int getAfterStatus() {
		return afterStatus;
	}

	public void setAfterStatus(int afterStatus) {
		this.afterStatus = afterStatus;
	}

	public String getInstrumentName() {
		return instrumentName;
	}

	public void setInstrumentName(String instrumentName) {
		this.instrumentName = instrumentName;
	}

	public int getInstVerifDId() {
		return instVerifDId;
	}

	public void setInstVerifDId(int instVerifDId) {
		this.instVerifDId = instVerifDId;
	}

	public int getInstVerifHId() {
		return instVerifHId;
	}

	public void setInstVerifHId(int instVerifHId) {
		this.instVerifHId = instVerifHId;
	}

	public int getInstId() {
		return instId;
	}

	public void setInstId(int instId) {
		this.instId = instId;
	}

	@Override
	public String toString() {
		return "GetInstVerifDetail [instVerifDId=" + instVerifDId + ", instVerifHId=" + instVerifHId + ", instId="
				+ instId + ", instrumentName=" + instrumentName + ", beforeStatus=" + beforeStatus + ", afterStatus="
				+ afterStatus + "]";
	}
     
}
