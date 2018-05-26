package com.ats.adminpanel.model.ggreports;

public class GrnGvnReportByGrnType  {
	
	private int frId;
	
	private String frName;
	
	private float aprAmtGrn1;
	
	private float aprAmtGrn2;
	
	private float aprAmtGrn3;
	
	private float aprAmtGvn;
	
	private float totalAprAmt;


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


	public float getAprAmtGrn1() {
		return aprAmtGrn1;
	}


	public void setAprAmtGrn1(float aprAmtGrn1) {
		this.aprAmtGrn1 = aprAmtGrn1;
	}


	public float getAprAmtGrn2() {
		return aprAmtGrn2;
	}


	public void setAprAmtGrn2(float aprAmtGrn2) {
		this.aprAmtGrn2 = aprAmtGrn2;
	}


	public float getAprAmtGrn3() {
		return aprAmtGrn3;
	}


	public void setAprAmtGrn3(float aprAmtGrn3) {
		this.aprAmtGrn3 = aprAmtGrn3;
	}


	public float getAprAmtGvn() {
		return aprAmtGvn;
	}


	public void setAprAmtGvn(float aprAmtGvn) {
		this.aprAmtGvn = aprAmtGvn;
	}


	public float getTotalAprAmt() {
		return totalAprAmt;
	}


	public void setTotalAprAmt(float totalAprAmt) {
		this.totalAprAmt = totalAprAmt;
	}

	@Override
	public String toString() {
		return "GrnGvnReportByGrnType [frId=" + frId + ", frName=" + frName + ", aprAmtGrn1=" + aprAmtGrn1
				+ ", aprAmtGrn2=" + aprAmtGrn2 + ", aprAmtGrn3=" + aprAmtGrn3 + ", aprAmtGvn=" + aprAmtGvn
				+ ", totalAprAmt=" + totalAprAmt + "]";
	}

}
