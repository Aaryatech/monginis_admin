package com.ats.adminpanel.model.creditnote;

import java.util.Date;


public class PostCreditNoteDetails {
	
	
	private int crndId;
	
	
	private int crnId;
	
	
	private int itemId;
	
	
	private int grnGvnId;
	
	
	private int isGrn;
	
	
	private int grnType;
	
	private Date grnGvnDate;
	
	private int grnGvnQty;
	
	private float taxableAmt;
	
	private float totalTax;
	
	private float grnGvnAmt;
	
	private float cgstPer;
	
	private float sgstPer;
	
	
	private float igstPer;
	
	private float cgstRs;
	
	private float sgstRs;
	
	private float igstRs;
	
	private float cessRs;
		
	private int delStatus;
	
	private int billNo;
	
	private Date billDate;
	
	

	public int getBillNo() {
		return billNo;
	}

	public void setBillNo(int billNo) {
		this.billNo = billNo;
	}

	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public int getCrndId() {
		return crndId;
	}

	public void setCrndId(int crndId) {
		this.crndId = crndId;
	}

	public int getCrnId() {
		return crnId;
	}

	public void setCrnId(int crnId) {
		this.crnId = crnId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getGrnGvnId() {
		return grnGvnId;
	}

	public void setGrnGvnId(int grnGvnId) {
		this.grnGvnId = grnGvnId;
	}

	public int getIsGrn() {
		return isGrn;
	}

	public void setIsGrn(int isGrn) {
		this.isGrn = isGrn;
	}

	public int getGrnType() {
		return grnType;
	}

	public void setGrnType(int grnType) {
		this.grnType = grnType;
	}

	

	public int getGrnGvnQty() {
		return grnGvnQty;
	}

	public void setGrnGvnQty(int grnGvnQty) {
		this.grnGvnQty = grnGvnQty;
	}

	public float getTaxableAmt() {
		return taxableAmt;
	}

	public void setTaxableAmt(float taxableAmt) {
		this.taxableAmt = taxableAmt;
	}

	public float getTotalTax() {
		return totalTax;
	}

	public void setTotalTax(float totalTax) {
		this.totalTax = totalTax;
	}

	public float getGrnGvnAmt() {
		return grnGvnAmt;
	}

	public void setGrnGvnAmt(float grnGvnAmt) {
		this.grnGvnAmt = grnGvnAmt;
	}

	public float getCgstPer() {
		return cgstPer;
	}

	public void setCgstPer(float cgstPer) {
		this.cgstPer = cgstPer;
	}

	public float getSgstPer() {
		return sgstPer;
	}

	public void setSgstPer(float sgstPer) {
		this.sgstPer = sgstPer;
	}

	public float getIgstPer() {
		return igstPer;
	}

	public void setIgstPer(float igstPer) {
		this.igstPer = igstPer;
	}

	public float getCgstRs() {
		return cgstRs;
	}

	public void setCgstRs(float cgstRs) {
		this.cgstRs = cgstRs;
	}

	public float getSgstRs() {
		return sgstRs;
	}

	public void setSgstRs(float sgstRs) {
		this.sgstRs = sgstRs;
	}

	public float getIgstRs() {
		return igstRs;
	}

	public void setIgstRs(float igstRs) {
		this.igstRs = igstRs;
	}

	public float getCessRs() {
		return cessRs;
	}

	public void setCessRs(float cessRs) {
		this.cessRs = cessRs;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	public Date getGrnGvnDate() {
		return grnGvnDate;
	}

	public void setGrnGvnDate(Date grnGvnDate) {
		this.grnGvnDate = grnGvnDate;
	}

	@Override
	public String toString() {
		return "PostCreditNoteDetails [crndId=" + crndId + ", crnId=" + crnId + ", itemId=" + itemId + ", grnGvnId="
				+ grnGvnId + ", isGrn=" + isGrn + ", grnType=" + grnType + ", grnGvnDate=" + grnGvnDate + ", grnGvnQty="
				+ grnGvnQty + ", taxableAmt=" + taxableAmt + ", totalTax=" + totalTax + ", grnGvnAmt=" + grnGvnAmt
				+ ", cgstPer=" + cgstPer + ", sgstPer=" + sgstPer + ", igstPer=" + igstPer + ", cgstRs=" + cgstRs
				+ ", sgstRs=" + sgstRs + ", igstRs=" + igstRs + ", cessRs=" + cessRs + ", delStatus=" + delStatus
				+ ", billNo=" + billNo + ", billDate=" + billDate + "]";
	}

	
	
	

}
