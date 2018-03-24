package com.ats.adminpanel.model.creditnote;

import java.sql.Date;
import java.util.List;


public class PostCreditNoteHeader {
	
	
	private int crnId;
	
	//newly added
	private String crnNo;
	

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
	
	//new field 23 FEB
	String grnGvnSrNoList;//comma seperated unique;
	
	//new field 23 FEB
	int isDeposited; //default 0 when actually deposited in bank set value
	
	int isGrn;
	
	
	
	
	

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

	public String getCrnNo() {
		return crnNo;
	}

	public void setCrnNo(String crnNo) {
		this.crnNo = crnNo;
	}

	public String getGrnGvnSrNoList() {
		return grnGvnSrNoList;
	}

	public void setGrnGvnSrNoList(String grnGvnSrNoList) {
		this.grnGvnSrNoList = grnGvnSrNoList;
	}

	public int getIsDeposited() {
		return isDeposited;
	}

	public void setIsDeposited(int isDeposited) {
		this.isDeposited = isDeposited;
	}

	public int getIsGrn() {
		return isGrn;
	}

	public void setIsGrn(int isGrn) {
		this.isGrn = isGrn;
	}

	@Override
	public String toString() {
		return "PostCreditNoteHeader [crnId=" + crnId + ", crnNo=" + crnNo + ", crnDate=" + crnDate + ", frId=" + frId
				+ ", crnTaxableAmt=" + crnTaxableAmt + ", crnTotalTax=" + crnTotalTax + ", crnGrandTotal="
				+ crnGrandTotal + ", crnFinalAmt=" + crnFinalAmt + ", roundOff=" + roundOff + ", userId=" + userId
				+ ", createdDateTime=" + createdDateTime + ", isTallySync=" + isTallySync + ", grnGvnSrNoList="
				+ grnGvnSrNoList + ", isDeposited=" + isDeposited + ", isGrn=" + isGrn + ", postCreditNoteDetails="
				+ postCreditNoteDetails + "]";
	}

}
