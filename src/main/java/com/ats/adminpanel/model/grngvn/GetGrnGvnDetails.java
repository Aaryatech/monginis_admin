package com.ats.adminpanel.model.grngvn;

import java.util.Date;

public class GetGrnGvnDetails {
	
	
int grnGvnId;
int grnGvnHeaderId;
String itemHsncd;
	
	String grnGvnDate;
	
	
	private int billNo;

	
	private int frId;

	
	private int itemId;
	
	
	private float itemRate;
	
	
	private float itemMrp;

	
	private int grnGvnQty;

	
	private float grnGvnAmt;

	
	private int grnType;
	
	
	private int isGrn;
	
	
	private int isGrnEdit;
	
	
	private String grnGvnEntryDateTime;

	
	private String frGrnGvnRemark;

	
	private String gvnPhotoUpload1;

	
	private String gvnPhotoUpload2;

	
	private int grnGvnStatus;

	
	private int approvedLoginGate;

	
	private String approveimedDateTimeGate;

	
	private String approvedRemarkGate;

	
	private int approvedLoginStore;

	
	private String approvedDateTimeStore;

	
	private String approvedRemarkStore;

	
	private int approvedLoginAcc;

	
	private String grnApprovedDateTimeAcc;

	
	private String approvedRemarkAcc;
	

	
	private int delStatus;
	
	
	private int grnGvnQtyAuto;
	
	private String itemName;
	
	private String frName;
	
	
	//newly Added
	
	private int isTallySync;
		
		
		
		private float baseRate;
		
		
		private float sgstPer;
		
		
		private float cgstPer;
		
		
		private float igstPer;
		
		
		private float taxableAmt;
		
		
		private float totalTax;
		
		
		
		
		private float roundUpAmt;
		
		
		private float finalAmt;

		private int isCreditNote;
		
		
		
		
		
		private int menuId;
		

		private int catId;
		

		private String invoiceNo;
		

		private String refInvoiceDate;
		
		
		//23 FEB new Fields to handle qty variation between entry(insert) and dispatch
		
				int aprQtyGate;
				int aprQtyStore;
				int aprQtyAcc;
				
				
				float aprTaxableAmt;
				float aprTotalTax;
				float aprSgstRs;
				float aprCgstRs;
				float aprIgstRs;
				float aprGrandTotal;
				float aprROff;
				
				
				int isSameState;
				//23 FEB new Fields to handle qty variation between entry(insert) and dispatch
				
				
		
						

	

	public int getIsCreditNote() {
			return isCreditNote;
		}

		public String getRefInvoiceDate() {
		return refInvoiceDate;
	}

	public void setRefInvoiceDate(String refInvoiceDate) {
		this.refInvoiceDate = refInvoiceDate;
	}

		public void setIsCreditNote(int isCreditNote) {
			this.isCreditNote = isCreditNote;
		}

	public int getMenuId() {
			return menuId;
		}

		public int getCatId() {
			return catId;
		}

		public String getInvoiceNo() {
			return invoiceNo;
		}

		
		public void setMenuId(int menuId) {
			this.menuId = menuId;
		}

		public void setCatId(int catId) {
			this.catId = catId;
		}

		public void setInvoiceNo(String invoiceNo) {
			this.invoiceNo = invoiceNo;
		}

		

	public int getGrnGvnId() {
		return grnGvnId;
	}

	public void setGrnGvnId(int grnGvnId) {
		this.grnGvnId = grnGvnId;
	}

	 

	public String getGrnGvnDate() {
		return grnGvnDate;
	}

	public void setGrnGvnDate(String grnGvnDate) {
		this.grnGvnDate = grnGvnDate;
	}

	public int getBillNo() {
		return billNo;
	}

	public void setBillNo(int billNo) {
		this.billNo = billNo;
	}

	public int getFrId() {
		return frId;
	}

	public void setFrId(int frId) {
		this.frId = frId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public float getItemRate() {
		return itemRate;
	}

	public void setItemRate(float itemRate) {
		this.itemRate = itemRate;
	}

	public float getItemMrp() {
		return itemMrp;
	}

	public void setItemMrp(float itemMrp) {
		this.itemMrp = itemMrp;
	}

	public int getGrnGvnQty() {
		return grnGvnQty;
	}

	public void setGrnGvnQty(int grnGvnQty) {
		this.grnGvnQty = grnGvnQty;
	}

	public float getGrnGvnAmt() {
		return grnGvnAmt;
	}

	public void setGrnGvnAmt(float grnGvnAmt) {
		this.grnGvnAmt = grnGvnAmt;
	}

	public int getGrnType() {
		return grnType;
	}

	public void setGrnType(int grnType) {
		this.grnType = grnType;
	}

	public int getIsGrn() {
		return isGrn;
	}

	public void setIsGrn(int isGrn) {
		this.isGrn = isGrn;
	}

	public int getIsGrnEdit() {
		return isGrnEdit;
	}

	public void setIsGrnEdit(int isGrnEdit) {
		this.isGrnEdit = isGrnEdit;
	}

	public String getGrnGvnEntryDateTime() {
		return grnGvnEntryDateTime;
	}

	public void setGrnGvnEntryDateTime(String grnGvnEntryDateTime) {
		this.grnGvnEntryDateTime = grnGvnEntryDateTime;
	}

	public String getFrGrnGvnRemark() {
		return frGrnGvnRemark;
	}

	public void setFrGrnGvnRemark(String frGrnGvnRemark) {
		this.frGrnGvnRemark = frGrnGvnRemark;
	}

	public String getGvnPhotoUpload1() {
		return gvnPhotoUpload1;
	}

	public void setGvnPhotoUpload1(String gvnPhotoUpload1) {
		this.gvnPhotoUpload1 = gvnPhotoUpload1;
	}

	public String getGvnPhotoUpload2() {
		return gvnPhotoUpload2;
	}

	public void setGvnPhotoUpload2(String gvnPhotoUpload2) {
		this.gvnPhotoUpload2 = gvnPhotoUpload2;
	}

	public int getGrnGvnStatus() {
		return grnGvnStatus;
	}

	public void setGrnGvnStatus(int grnGvnStatus) {
		this.grnGvnStatus = grnGvnStatus;
	}

	public int getApprovedLoginGate() {
		return approvedLoginGate;
	}

	public void setApprovedLoginGate(int approvedLoginGate) {
		this.approvedLoginGate = approvedLoginGate;
	}

	public String getApproveimedDateTimeGate() {
		return approveimedDateTimeGate;
	}

	public void setApproveimedDateTimeGate(String approveimedDateTimeGate) {
		this.approveimedDateTimeGate = approveimedDateTimeGate;
	}

	public String getApprovedRemarkGate() {
		return approvedRemarkGate;
	}

	public void setApprovedRemarkGate(String approvedRemarkGate) {
		this.approvedRemarkGate = approvedRemarkGate;
	}

	public int getApprovedLoginStore() {
		return approvedLoginStore;
	}

	public void setApprovedLoginStore(int approvedLoginStore) {
		this.approvedLoginStore = approvedLoginStore;
	}

	public String getApprovedDateTimeStore() {
		return approvedDateTimeStore;
	}

	public void setApprovedDateTimeStore(String approvedDateTimeStore) {
		this.approvedDateTimeStore = approvedDateTimeStore;
	}

	public String getApprovedRemarkStore() {
		return approvedRemarkStore;
	}

	public void setApprovedRemarkStore(String approvedRemarkStore) {
		this.approvedRemarkStore = approvedRemarkStore;
	}

	public int getApprovedLoginAcc() {
		return approvedLoginAcc;
	}

	public void setApprovedLoginAcc(int approvedLoginAcc) {
		this.approvedLoginAcc = approvedLoginAcc;
	}

	public String getGrnApprovedDateTimeAcc() {
		return grnApprovedDateTimeAcc;
	}

	public void setGrnApprovedDateTimeAcc(String grnApprovedDateTimeAcc) {
		this.grnApprovedDateTimeAcc = grnApprovedDateTimeAcc;
	}

	public String getApprovedRemarkAcc() {
		return approvedRemarkAcc;
	}

	public void setApprovedRemarkAcc(String approvedRemarkAcc) {
		this.approvedRemarkAcc = approvedRemarkAcc;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	public int getGrnGvnQtyAuto() {
		return grnGvnQtyAuto;
	}

	public void setGrnGvnQtyAuto(int grnGvnQtyAuto) {
		this.grnGvnQtyAuto = grnGvnQtyAuto;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getFrName() {
		return frName;
	}

	public void setFrName(String frName) {
		this.frName = frName;
	}

	public int getIsTallySync() {
		return isTallySync;
	}

	public void setIsTallySync(int isTallySync) {
		this.isTallySync = isTallySync;
	}

	public float getBaseRate() {
		return baseRate;
	}

	public void setBaseRate(float baseRate) {
		this.baseRate = baseRate;
	}

	public float getSgstPer() {
		return sgstPer;
	}

	public void setSgstPer(float sgstPer) {
		this.sgstPer = sgstPer;
	}

	public float getCgstPer() {
		return cgstPer;
	}

	public void setCgstPer(float cgstPer) {
		this.cgstPer = cgstPer;
	}

	public float getIgstPer() {
		return igstPer;
	}

	public void setIgstPer(float igstPer) {
		this.igstPer = igstPer;
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

	public float getRoundUpAmt() {
		return roundUpAmt;
	}

	public void setRoundUpAmt(float roundUpAmt) {
		this.roundUpAmt = roundUpAmt;
	}

	public float getFinalAmt() {
		return finalAmt;
	}

	public void setFinalAmt(float finalAmt) {
		this.finalAmt = finalAmt;
	}

	public int getGrnGvnHeaderId() {
		return grnGvnHeaderId;
	}

	public void setGrnGvnHeaderId(int grnGvnHeaderId) {
		this.grnGvnHeaderId = grnGvnHeaderId;
	}

	public int getAprQtyGate() {
		return aprQtyGate;
	}

	public void setAprQtyGate(int aprQtyGate) {
		this.aprQtyGate = aprQtyGate;
	}

	public int getAprQtyStore() {
		return aprQtyStore;
	}

	public void setAprQtyStore(int aprQtyStore) {
		this.aprQtyStore = aprQtyStore;
	}

	public int getAprQtyAcc() {
		return aprQtyAcc;
	}

	public void setAprQtyAcc(int aprQtyAcc) {
		this.aprQtyAcc = aprQtyAcc;
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

	public int getIsSameState() {
		return isSameState;
	}

	public void setIsSameState(int isSameState) {
		this.isSameState = isSameState;
	}

	public String getItemHsncd() {
		return itemHsncd;
	}

	public void setItemHsncd(String itemHsncd) {
		this.itemHsncd = itemHsncd;
	}

	@Override
	public String toString() {
		return "GetGrnGvnDetails [grnGvnId=" + grnGvnId + ", grnGvnHeaderId=" + grnGvnHeaderId + ", itemHsncd="
				+ itemHsncd + ", grnGvnDate=" + grnGvnDate + ", billNo=" + billNo + ", frId=" + frId + ", itemId="
				+ itemId + ", itemRate=" + itemRate + ", itemMrp=" + itemMrp + ", grnGvnQty=" + grnGvnQty
				+ ", grnGvnAmt=" + grnGvnAmt + ", grnType=" + grnType + ", isGrn=" + isGrn + ", isGrnEdit=" + isGrnEdit
				+ ", grnGvnEntryDateTime=" + grnGvnEntryDateTime + ", frGrnGvnRemark=" + frGrnGvnRemark
				+ ", gvnPhotoUpload1=" + gvnPhotoUpload1 + ", gvnPhotoUpload2=" + gvnPhotoUpload2 + ", grnGvnStatus="
				+ grnGvnStatus + ", approvedLoginGate=" + approvedLoginGate + ", approveimedDateTimeGate="
				+ approveimedDateTimeGate + ", approvedRemarkGate=" + approvedRemarkGate + ", approvedLoginStore="
				+ approvedLoginStore + ", approvedDateTimeStore=" + approvedDateTimeStore + ", approvedRemarkStore="
				+ approvedRemarkStore + ", approvedLoginAcc=" + approvedLoginAcc + ", grnApprovedDateTimeAcc="
				+ grnApprovedDateTimeAcc + ", approvedRemarkAcc=" + approvedRemarkAcc + ", delStatus=" + delStatus
				+ ", grnGvnQtyAuto=" + grnGvnQtyAuto + ", itemName=" + itemName + ", frName=" + frName
				+ ", isTallySync=" + isTallySync + ", baseRate=" + baseRate + ", sgstPer=" + sgstPer + ", cgstPer="
				+ cgstPer + ", igstPer=" + igstPer + ", taxableAmt=" + taxableAmt + ", totalTax=" + totalTax
				+ ", roundUpAmt=" + roundUpAmt + ", finalAmt=" + finalAmt + ", isCreditNote=" + isCreditNote
				+ ", menuId=" + menuId + ", catId=" + catId + ", invoiceNo=" + invoiceNo + ", refInvoiceDate="
				+ refInvoiceDate + ", aprQtyGate=" + aprQtyGate + ", aprQtyStore=" + aprQtyStore + ", aprQtyAcc="
				+ aprQtyAcc + ", aprTaxableAmt=" + aprTaxableAmt + ", aprTotalTax=" + aprTotalTax + ", aprSgstRs="
				+ aprSgstRs + ", aprCgstRs=" + aprCgstRs + ", aprIgstRs=" + aprIgstRs + ", aprGrandTotal="
				+ aprGrandTotal + ", aprROff=" + aprROff + ", isSameState=" + isSameState + "]";
	}

	
}
