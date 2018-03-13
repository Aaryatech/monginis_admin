package com.ats.adminpanel.model.creditnote;

import java.sql.Date;


public class GetCrnDetails {
	
	
private int crndId;
	
	private int crnId;
	
	private int itemId;
	
	private int grnGvnId;
	private int isGrn;
	
	private int grnType;
	
	private String grnGvnDate;
	
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
	
	
	
		private int catId;
		
		
		private float baseRate;
		
		private float cessPer;
		
		private String  refInvoiceNo;
		
		
		private String grngvnSrno;
		
		private int grnGvnHeaderId;
		
		
		private String itemName;
		
		private String itemHsncd;
		
		private String itemUom;


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


		

		public String getGrnGvnDate() {
			return grnGvnDate;
		}


		public void setGrnGvnDate(String grnGvnDate) {
			this.grnGvnDate = grnGvnDate;
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


		public int getCatId() {
			return catId;
		}


		public void setCatId(int catId) {
			this.catId = catId;
		}


		public float getBaseRate() {
			return baseRate;
		}


		public void setBaseRate(float baseRate) {
			this.baseRate = baseRate;
		}


		public float getCessPer() {
			return cessPer;
		}


		public void setCessPer(float cessPer) {
			this.cessPer = cessPer;
		}


		public String getRefInvoiceNo() {
			return refInvoiceNo;
		}


		public void setRefInvoiceNo(String refInvoiceNo) {
			this.refInvoiceNo = refInvoiceNo;
		}


		public String getGrngvnSrno() {
			return grngvnSrno;
		}


		public void setGrngvnSrno(String grngvnSrno) {
			this.grngvnSrno = grngvnSrno;
		}


		public int getGrnGvnHeaderId() {
			return grnGvnHeaderId;
		}


		public void setGrnGvnHeaderId(int grnGvnHeaderId) {
			this.grnGvnHeaderId = grnGvnHeaderId;
		}


		public String getItemName() {
			return itemName;
		}


		public void setItemName(String itemName) {
			this.itemName = itemName;
		}


		public String getItemHsncd() {
			return itemHsncd;
		}


		public void setItemHsncd(String itemHsncd) {
			this.itemHsncd = itemHsncd;
		}


		public String getItemUom() {
			return itemUom;
		}


		public void setItemUom(String itemUom) {
			this.itemUom = itemUom;
		}

		@Override
		public String toString() {
			return "GetCrnDetails [crndId=" + crndId + ", crnId=" + crnId + ", itemId=" + itemId + ", grnGvnId="
					+ grnGvnId + ", isGrn=" + isGrn + ", grnType=" + grnType + ", grnGvnDate=" + grnGvnDate
					+ ", grnGvnQty=" + grnGvnQty + ", taxableAmt=" + taxableAmt + ", totalTax=" + totalTax
					+ ", grnGvnAmt=" + grnGvnAmt + ", cgstPer=" + cgstPer + ", sgstPer=" + sgstPer + ", igstPer="
					+ igstPer + ", cgstRs=" + cgstRs + ", sgstRs=" + sgstRs + ", igstRs=" + igstRs + ", cessRs="
					+ cessRs + ", delStatus=" + delStatus + ", billNo=" + billNo + ", billDate=" + billDate + ", catId="
					+ catId + ", baseRate=" + baseRate + ", cessPer=" + cessPer + ", refInvoiceNo=" + refInvoiceNo
					+ ", grngvnSrno=" + grngvnSrno + ", grnGvnHeaderId=" + grnGvnHeaderId + ", itemName=" + itemName
					+ ", itemHsncd=" + itemHsncd + ", itemUom=" + itemUom + "]";
		}

}
