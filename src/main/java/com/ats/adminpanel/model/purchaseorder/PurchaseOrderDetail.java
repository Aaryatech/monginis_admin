package com.ats.adminpanel.model.purchaseorder;

 

public class PurchaseOrderDetail {

	
private int poDetailId;
	
	 
	private int poId;
	 
	private int poNo;
	 
	private String poDate;
	 
	private int poType;
	 
	private int suppId;
	 
	private int rmId;
	 
	private String rmName;
	 
	private int rmUomId;
	 
	private String specification;
	 
	private String rmRemark;
	 
	private int poQty;
	 
	private float poRate;
	 
	private float discPer;
	 
	private float gstPer;
	 
	private float cgstPer;
	
	 
	private float sgstPer;
	 
	private float igstPer;
	 
	private float cessPer;
	 
	private float poTaxable;
	 
	private float poTotal;
	 
	private int schDays;
	 
	private int delStatus;

	public int getPoDetailId() {
		return poDetailId;
	}

	public void setPoDetailId(int poDetailId) {
		this.poDetailId = poDetailId;
	}

	public int getPoId() {
		return poId;
	}

	public void setPoId(int poId) {
		this.poId = poId;
	}

	public int getPoNo() {
		return poNo;
	}

	public void setPoNo(int poNo) {
		this.poNo = poNo;
	}

	public String getPoDate() {
		return poDate;
	}

	public void setPoDate(String poDate) {
		this.poDate = poDate;
	}

	public int getPoType() {
		return poType;
	}

	public void setPoType(int poType) {
		this.poType = poType;
	}

	public int getSuppId() {
		return suppId;
	}

	public void setSuppId(int suppId) {
		this.suppId = suppId;
	}

	public int getRmId() {
		return rmId;
	}

	public void setRmId(int rmId) {
		this.rmId = rmId;
	}

	public String getRmName() {
		return rmName;
	}

	public void setRmName(String rmName) {
		this.rmName = rmName;
	}

	public int getRmUomId() {
		return rmUomId;
	}

	public void setRmUomId(int rmUomId) {
		this.rmUomId = rmUomId;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getRmRemark() {
		return rmRemark;
	}

	public void setRmRemark(String rmRemark) {
		this.rmRemark = rmRemark;
	}

	public int getPoQty() {
		return poQty;
	}

	public void setPoQty(int poQty) {
		this.poQty = poQty;
	}

	public float getPoRate() {
		return poRate;
	}

	public void setPoRate(float poRate) {
		this.poRate = poRate;
	}

	public float getDiscPer() {
		return discPer;
	}

	public void setDiscPer(float discPer) {
		this.discPer = discPer;
	}

	public float getGstPer() {
		return gstPer;
	}

	public void setGstPer(float gstPer) {
		this.gstPer = gstPer;
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

	public float getCessPer() {
		return cessPer;
	}

	public void setCessPer(float cessPer) {
		this.cessPer = cessPer;
	}

	public float getPoTaxable() {
		return poTaxable;
	}

	public void setPoTaxable(float poTaxable) {
		this.poTaxable = poTaxable;
	}

	public float getPoTotal() {
		return poTotal;
	}

	public void setPoTotal(float poTotal) {
		this.poTotal = poTotal;
	}

	public int getSchDays() {
		return schDays;
	}

	public void setSchDays(int schDays) {
		this.schDays = schDays;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	@Override
	public String toString() {
		return "PurchaseOrderDetail [poDetailId=" + poDetailId + ", poId=" + poId + ", poNo=" + poNo + ", poDate="
				+ poDate + ", poType=" + poType + ", suppId=" + suppId + ", rmId=" + rmId + ", rmName=" + rmName
				+ ", rmUomId=" + rmUomId + ", specification=" + specification + ", rmRemark=" + rmRemark + ", poQty="
				+ poQty + ", poRate=" + poRate + ", discPer=" + discPer + ", gstPer=" + gstPer + ", cgstPer=" + cgstPer
				+ ", sgstPer=" + sgstPer + ", igstPer=" + igstPer + ", cessPer=" + cessPer + ", poTaxable=" + poTaxable
				+ ", poTotal=" + poTotal + ", schDays=" + schDays + ", delStatus=" + delStatus + "]";
	}
	
	
}
