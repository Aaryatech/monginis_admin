package com.ats.adminpanel.model.creditnote;

import java.sql.Date;
import java.util.List;


public class PostCreditNoteHeader {
	
	
	private int crnId;

	private Date crnDate;

	private int frId;

	private float crnTaxableAmt;

	private float crnTotalTax;

	private float crnGrandTotal;

	private float crnFinalAmt;

	private float roundOff;

	private int userId;

	private String createdDateTime;

	private int isTallySync;

	List<PostCreditNoteDetails> postCreditNoteDetails;

	public int getCrnId() {
		return crnId;
	}

	public void setCrnId(int crnId) {
		this.crnId = crnId;
	}

	public Date getCrnDate() {
		return crnDate;
	}

	public void setCrnDate(Date crnDate) {
		this.crnDate = crnDate;
	}

	public int getFrId() {
		return frId;
	}

	public void setFrId(int frId) {
		this.frId = frId;
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

	public float getCrnGrandTotal() {
		return crnGrandTotal;
	}

	public void setCrnGrandTotal(float crnGrandTotal) {
		this.crnGrandTotal = crnGrandTotal;
	}

	public float getCrnFinalAmt() {
		return crnFinalAmt;
	}

	public void setCrnFinalAmt(float crnFinalAmt) {
		this.crnFinalAmt = crnFinalAmt;
	}

	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(String createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public int getIsTallySync() {
		return isTallySync;
	}

	public void setIsTallySync(int isTallySync) {
		this.isTallySync = isTallySync;
	}

	public List<PostCreditNoteDetails> getPostCreditNoteDetails() {
		return postCreditNoteDetails;
	}

	public void setPostCreditNoteDetails(List<PostCreditNoteDetails> postCreditNoteDetails) {
		this.postCreditNoteDetails = postCreditNoteDetails;
	}

	public float getRoundOff() {
		return roundOff;
	}

	public void setRoundOff(float roundOff) {
		this.roundOff = roundOff;
	}

	@Override
	public String toString() {
		return "PostCreditNoteHeader [crnId=" + crnId + ", crnDate=" + crnDate + ", frId=" + frId + ", crnTaxableAmt="
				+ crnTaxableAmt + ", crnTotalTax=" + crnTotalTax + ", crnGrandTotal=" + crnGrandTotal + ", crnFinalAmt="
				+ crnFinalAmt + ", roundOff=" + roundOff + ", userId=" + userId + ", createdDateTime=" + createdDateTime
				+ ", isTallySync=" + isTallySync + ", postCreditNoteDetails=" + postCreditNoteDetails + "]";
	}

	
	
	
	

}
