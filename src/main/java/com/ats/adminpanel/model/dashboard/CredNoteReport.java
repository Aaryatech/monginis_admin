package com.ats.adminpanel.model.dashboard;


public class CredNoteReport {
	
	
	private String  crnDate;
	private float crnGrandTotal;
	private float crnTaxableAmt;
	private float crnTotalTax;
	
	private int isGrn;

	public String getCrnDate() {
		return crnDate;
	}

	public void setCrnDate(String crnDate) {
		this.crnDate = crnDate;
	}

	public float getCrnGrandTotal() {
		return crnGrandTotal;
	}

	public void setCrnGrandTotal(float crnGrandTotal) {
		this.crnGrandTotal = crnGrandTotal;
	}

	public float getCrnTaxableAmt() {
		return crnTaxableAmt;
	}

	public void setCrnTaxableAmt(float crnTaxableAmt) {
		this.crnTaxableAmt = crnTaxableAmt;
	}

	public float getCrnTotalTax() {
		return crnTotalTax;
	}

	public void setCrnTotalTax(float crnTotalTax) {
		this.crnTotalTax = crnTotalTax;
	}

	public int getIsGrn() {
		return isGrn;
	}

	public void setIsGrn(int isGrn) {
		this.isGrn = isGrn;
	}

	@Override
	public String toString() {
		return "CredNoteReportFrEnd [crnDate=" + crnDate + ", crnGrandTotal=" + crnGrandTotal + ", crnTaxableAmt="
				+ crnTaxableAmt + ", crnTotalTax=" + crnTotalTax + ", isGrn=" + isGrn + "]";
	}
	

}
