package com.ats.adminpanel.model.creditnote;

import java.sql.Date;


public class GetGrnGvnForCreditNote {
	
	
	int grnGvnId;

	Date grnGvnDate;
	

	private int billNo;

	private int frId;

	private int itemId;
	
	private float itemRate;
	
	private float itemMrp;

	private int aprQtyAcc;

	private float aprGrandTotal;

	private int grnType;
	
	private int isGrn;
	
	private int isGrnEdit;
	
	

	private String frGrnGvnRemark;

	

	private int grnGvnStatus;

	

	private String grnApprovedDateTimeAcc;


	private int delStatus;
	
	private int grnGvnQtyAuto;
	
	
	
//newly added 04/11/17
	
	private int isTallySync;
	
	
	private float baseRate;
	
	private float sgstPer;
	
	private float cgstPer;
	
	private float igstPer;
	
	private float aprTaxableAmt;
	
	private float aprTotalTax;
	
	
	
	private float aprROff;
	
	
	private String itemName;

	private String frName;

	
	
	private int isCreditNote;
	
	
	
	private int menuId;
	

	private int catId;
	

	private String invoiceNo;
	

	private Date refInvoiceDate;
	
	
	//new column 23 FEB
	private String grngvnSrno;
	
	//new column 23 FEB
	private int grnGvnHeaderId;

	public int getGrnGvnId() {
		return grnGvnId;
	}

	public void setGrnGvnId(int grnGvnId) {
		this.grnGvnId = grnGvnId;
	}

	public Date getGrnGvnDate() {
		return grnGvnDate;
	}

	public void setGrnGvnDate(Date grnGvnDate) {
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

	public int getAprQtyAcc() {
		return aprQtyAcc;
	}

	public void setAprQtyAcc(int aprQtyAcc) {
		this.aprQtyAcc = aprQtyAcc;
	}

	public float getAprGrandTotal() {
		return aprGrandTotal;
	}

	public void setAprGrandTotal(float aprGrandTotal) {
		this.aprGrandTotal = aprGrandTotal;
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

	public String getFrGrnGvnRemark() {
		return frGrnGvnRemark;
	}

	public void setFrGrnGvnRemark(String frGrnGvnRemark) {
		this.frGrnGvnRemark = frGrnGvnRemark;
	}

	public int getGrnGvnStatus() {
		return grnGvnStatus;
	}

	public void setGrnGvnStatus(int grnGvnStatus) {
		this.grnGvnStatus = grnGvnStatus;
	}

	public String getGrnApprovedDateTimeAcc() {
		return grnApprovedDateTimeAcc;
	}

	public void setGrnApprovedDateTimeAcc(String grnApprovedDateTimeAcc) {
		this.grnApprovedDateTimeAcc = grnApprovedDateTimeAcc;
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

	public float getAprROff() {
		return aprROff;
	}

	public void setAprROff(float aprROff) {
		this.aprROff = aprROff;
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

	public int getIsCreditNote() {
		return isCreditNote;
	}

	public void setIsCreditNote(int isCreditNote) {
		this.isCreditNote = isCreditNote;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public int getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public Date getRefInvoiceDate() {
		return refInvoiceDate;
	}

	public void setRefInvoiceDate(Date refInvoiceDate) {
		this.refInvoiceDate = refInvoiceDate;
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

	@Override
	public String toString() {
		return "GetGrnGvnForCreditNote [grnGvnId=" + grnGvnId + ", grnGvnDate=" + grnGvnDate + ", billNo=" + billNo
				+ ", frId=" + frId + ", itemId=" + itemId + ", itemRate=" + itemRate + ", itemMrp=" + itemMrp
				+ ", aprQtyAcc=" + aprQtyAcc + ", aprGrandTotal=" + aprGrandTotal + ", grnType=" + grnType + ", isGrn="
				+ isGrn + ", isGrnEdit=" + isGrnEdit + ", frGrnGvnRemark=" + frGrnGvnRemark + ", grnGvnStatus="
				+ grnGvnStatus + ", grnApprovedDateTimeAcc=" + grnApprovedDateTimeAcc + ", delStatus=" + delStatus
				+ ", grnGvnQtyAuto=" + grnGvnQtyAuto + ", isTallySync=" + isTallySync + ", baseRate=" + baseRate
				+ ", sgstPer=" + sgstPer + ", cgstPer=" + cgstPer + ", igstPer=" + igstPer + ", aprTaxableAmt="
				+ aprTaxableAmt + ", aprTotalTax=" + aprTotalTax + ", aprROff=" + aprROff + ", itemName=" + itemName
				+ ", frName=" + frName + ", isCreditNote=" + isCreditNote + ", menuId=" + menuId + ", catId=" + catId
				+ ", invoiceNo=" + invoiceNo + ", refInvoiceDate=" + refInvoiceDate + ", grngvnSrno=" + grngvnSrno
				+ ", grnGvnHeaderId=" + grnGvnHeaderId + "]";
	}


}
