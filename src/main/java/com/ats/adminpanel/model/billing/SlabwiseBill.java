package com.ats.adminpanel.model.billing;

import java.io.Serializable;


public class SlabwiseBill{


	private float taxPer;
	
	private float taxableAmt;
	
	private float cgstAmt;
	
	private float sgstAmt;
	
	private float totalTax;
	
	private float grandTotal;

	public float getTaxPer() {
		return taxPer;
	}

	public void setTaxPer(float taxPer) {
		this.taxPer = taxPer;
	}

	public float getTaxableAmt() {
		return taxableAmt;
	}

	public void setTaxableAmt(float taxableAmt) {
		this.taxableAmt = taxableAmt;
	}

	public float getCgstAmt() {
		return cgstAmt;
	}

	public void setCgstAmt(float cgstAmt) {
		this.cgstAmt = cgstAmt;
	}

	public float getSgstAmt() {
		return sgstAmt;
	}

	public void setSgstAmt(float sgstAmt) {
		this.sgstAmt = sgstAmt;
	}

	public float getTotalTax() {
		return totalTax;
	}

	public void setTotalTax(float totalTax) {
		this.totalTax = totalTax;
	}

	public float getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(float grandTotal) {
		this.grandTotal = grandTotal;
	}

	@Override
	public String toString() {
		return "SlabwiseBill [taxPer=" + taxPer + ", taxableAmt=" + taxableAmt + ", cgstAmt=" + cgstAmt + ", sgstAmt="
				+ sgstAmt + ", totalTax=" + totalTax + ", grandTotal=" + grandTotal + "]";
	}
	
	
}
