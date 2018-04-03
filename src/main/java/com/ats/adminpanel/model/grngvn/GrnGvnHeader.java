package com.ats.adminpanel.model.grngvn;

import java.util.Date;
import java.util.List;


public class GrnGvnHeader {
	
	private int grnGvnHeaderId;

	private int frId;
	
	private String grngvnSrno;
	
	private String grngvnDate;
	
	private int isGrn;
	
	private float taxableAmt;
	
	private float taxAmt;
	
	private float totalAmt;
	
	private int grngvnStatus;
	
	private float apporvedAmt;
	
	private int isCreditNote;

	private int creditNoteId;
	
	private String approvedDatetime;

	List<GrnGvn> grnGvn;
	
	
	//new Field added on 26 FEB
	
	float aprTaxableAmt;
	float aprTotalTax;
	float aprSgstRs;
	float aprCgstRs;
	float aprIgstRs;
	float aprGrandTotal;
	float aprROff;
	//nw field

	public int getGrnGvnHeaderId() {
		return grnGvnHeaderId;
	}

	public void setGrnGvnHeaderId(int grnGvnHeaderId) {
		this.grnGvnHeaderId = grnGvnHeaderId;
	}

	public int getFrId() {
		return frId;
	}

	public void setFrId(int frId) {
		this.frId = frId;
	}

	public String getGrngvnSrno() {
		return grngvnSrno;
	}

	public void setGrngvnSrno(String grngvnSrno) {
		this.grngvnSrno = grngvnSrno;
	}

	
	
	public String getGrngvnDate() {
		return grngvnDate;
	}

	public void setGrngvnDate(String grngvnDate) {
		this.grngvnDate = grngvnDate;
	}

	public int getIsGrn() {
		return isGrn;
	}

	public void setIsGrn(int isGrn) {
		this.isGrn = isGrn;
	}

	public float getTaxableAmt() {
		return taxableAmt;
	}

	public void setTaxableAmt(float taxableAmt) {
		this.taxableAmt = taxableAmt;
	}

	public float getTaxAmt() {
		return taxAmt;
	}

	public void setTaxAmt(float taxAmt) {
		this.taxAmt = taxAmt;
	}

	public float getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(float totalAmt) {
		this.totalAmt = totalAmt;
	}

	public int getGrngvnStatus() {
		return grngvnStatus;
	}

	public void setGrngvnStatus(int grngvnStatus) {
		this.grngvnStatus = grngvnStatus;
	}

	public float getApporvedAmt() {
		return apporvedAmt;
	}

	public void setApporvedAmt(float apporvedAmt) {
		this.apporvedAmt = apporvedAmt;
	}

	public int getIsCreditNote() {
		return isCreditNote;
	}

	public void setIsCreditNote(int isCreditNote) {
		this.isCreditNote = isCreditNote;
	}

	public int getCreditNoteId() {
		return creditNoteId;
	}

	public void setCreditNoteId(int creditNoteId) {
		this.creditNoteId = creditNoteId;
	}

	public String getApprovedDatetime() {
		return approvedDatetime;
	}

	public void setApprovedDatetime(String approvedDatetime) {
		this.approvedDatetime = approvedDatetime;
	}

	public List<GrnGvn> getGrnGvn() {
		return grnGvn;
	}

	public void setGrnGvn(List<GrnGvn> grnGvn) {
		this.grnGvn = grnGvn;
	}

	public float getAprTaxableAmt() {
		return aprTaxableAmt;
	}

	public void setAprTaxableAmt(float aprTaxableAmt) {
		this.aprTaxableAmt = aprTaxableAmt;
	}

	public float getAprTotalTax() {
		return aprTotalTax;
	}

	public void setAprTotalTax(float aprTotalTax) {
		this.aprTotalTax = aprTotalTax;
	}

	public float getAprSgstRs() {
		return aprSgstRs;
	}

	public void setAprSgstRs(float aprSgstRs) {
		this.aprSgstRs = aprSgstRs;
	}

	public float getAprCgstRs() {
		return aprCgstRs;
	}

	public void setAprCgstRs(float aprCgstRs) {
		this.aprCgstRs = aprCgstRs;
	}

	public float getAprIgstRs() {
		return aprIgstRs;
	}

	public void setAprIgstRs(float aprIgstRs) {
		this.aprIgstRs = aprIgstRs;
	}

	public float getAprGrandTotal() {
		return aprGrandTotal;
	}

	public void setAprGrandTotal(float aprGrandTotal) {
		this.aprGrandTotal = aprGrandTotal;
	}

	public float getAprROff() {
		return aprROff;
	}

	public void setAprROff(float aprROff) {
		this.aprROff = aprROff;
	}

	@Override
	public String toString() {
		return "GrnGvnHeader [grnGvnHeaderId=" + grnGvnHeaderId + ", frId=" + frId + ", grngvnSrno=" + grngvnSrno
				+ ", grngvnDate=" + grngvnDate + ", isGrn=" + isGrn + ", taxableAmt=" + taxableAmt + ", taxAmt="
				+ taxAmt + ", totalAmt=" + totalAmt + ", grngvnStatus=" + grngvnStatus + ", apporvedAmt=" + apporvedAmt
				+ ", isCreditNote=" + isCreditNote + ", creditNoteId=" + creditNoteId + ", approvedDatetime="
				+ approvedDatetime + ", grnGvn=" + grnGvn + ", aprTaxableAmt=" + aprTaxableAmt + ", aprTotalTax="
				+ aprTotalTax + ", aprSgstRs=" + aprSgstRs + ", aprCgstRs=" + aprCgstRs + ", aprIgstRs=" + aprIgstRs
				+ ", aprGrandTotal=" + aprGrandTotal + ", aprROff=" + aprROff + "]";
	}

	
	
}