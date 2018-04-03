package com.ats.adminpanel.model.spprod;

import java.io.Serializable;

public class InstVerificationDetail implements Serializable{

	private int instVerifDId;
	
	private int instVerifHId;
	
	private int instId;
	
	private int bef;
	
	private int aft;

	
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

	public int getBef() {
		return bef;
	}

	public void setBef(int bef) {
		this.bef = bef;
	}

	public int getAft() {
		return aft;
	}

	public void setAft(int aft) {
		this.aft = aft;
	}

	@Override
	public String toString() {
		return "InstVerificationDetail [instVerifDId=" + instVerifDId + ", instVerifHId=" + instVerifHId + ", instId="
				+ instId + ", bef=" + bef + ", aft=" + aft + "]";
	}

}
