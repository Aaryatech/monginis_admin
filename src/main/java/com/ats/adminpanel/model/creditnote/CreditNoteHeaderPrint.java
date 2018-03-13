package com.ats.adminpanel.model.creditnote;

import java.util.List;

public class CreditNoteHeaderPrint {

	
	private int crnId;

	private String crnNo;

	private String crnDate;

	private int frId;

	private float crnTaxableAmt;

	private float crnTotalTax;

	private float crnGrandTotal;


	private float roundOff;

	
	private String createdDateTime;

	private int isTallySync;
	
	
	int isDeposited; 

	private String frName;
	private String frAddress;
	
	private String fromDate;
	
	private String toDate;
	
	
private String frGstNo;

private int isSameState;

private int isGrn;
	
	List<GetCrnDetails> crnDetails;
	
	List<String> srNoList;
	
	List<CrnSrNoDateBean> srNoDateList;
	


	public int getCrnId() {
		return crnId;
	}


	public void setCrnId(int crnId) {
		this.crnId = crnId;
	}


	public String getCrnNo() {
		return crnNo;
	}


	public void setCrnNo(String crnNo) {
		this.crnNo = crnNo;
	}


	public String getCrnDate() {
		return crnDate;
	}


	public void setCrnDate(String crnDate) {
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


	public float getRoundOff() {
		return roundOff;
	}


	public void setRoundOff(float roundOff) {
		this.roundOff = roundOff;
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


	public int getIsDeposited() {
		return isDeposited;
	}


	public void setIsDeposited(int isDeposited) {
		this.isDeposited = isDeposited;
	}


	public String getFrName() {
		return frName;
	}


	public void setFrName(String frName) {
		this.frName = frName;
	}


	public String getFrAddress() {
		return frAddress;
	}


	public void setFrAddress(String frAddress) {
		this.frAddress = frAddress;
	}


	public List<GetCrnDetails> getCrnDetails() {
		return crnDetails;
	}


	public void setCrnDetails(List<GetCrnDetails> crnDetails) {
		this.crnDetails = crnDetails;
	}


	public String getFromDate() {
		return fromDate;
	}


	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}


	public String getToDate() {
		return toDate;
	}


	public void setToDate(String toDate) {
		this.toDate = toDate;
	}


	public String getFrGstNo() {
		return frGstNo;
	}


	public void setFrGstNo(String frGstNo) {
		this.frGstNo = frGstNo;
	}


	public int getIsSameState() {
		return isSameState;
	}


	public void setIsSameState(int isSameState) {
		this.isSameState = isSameState;
	}


	public List<String> getSrNoList() {
		return srNoList;
	}


	public void setSrNoList(List<String> srNoList) {
		this.srNoList = srNoList;
	}


	public int getIsGrn() {
		return isGrn;
	}


	public void setIsGrn(int isGrn) {
		this.isGrn = isGrn;
	}


	public List<CrnSrNoDateBean> getSrNoDateList() {
		return srNoDateList;
	}


	public void setSrNoDateList(List<CrnSrNoDateBean> srNoDateList) {
		this.srNoDateList = srNoDateList;
	}


	@Override
	public String toString() {
		return "CreditNoteHeaderPrint [crnId=" + crnId + ", crnNo=" + crnNo + ", crnDate=" + crnDate + ", frId=" + frId
				+ ", crnTaxableAmt=" + crnTaxableAmt + ", crnTotalTax=" + crnTotalTax + ", crnGrandTotal="
				+ crnGrandTotal + ", roundOff=" + roundOff + ", createdDateTime=" + createdDateTime + ", isTallySync="
				+ isTallySync + ", isDeposited=" + isDeposited + ", frName=" + frName + ", frAddress=" + frAddress
				+ ", fromDate=" + fromDate + ", toDate=" + toDate + ", frGstNo=" + frGstNo + ", isSameState="
				+ isSameState + ", isGrn=" + isGrn + ", crnDetails=" + crnDetails + ", srNoList=" + srNoList
				+ ", srNoDateList=" + srNoDateList + "]";
	}

	
}
