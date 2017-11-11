package com.ats.adminpanel.model.billing;

import java.util.Date;




public class PostBillDetail {
	
	
	private int billDetailNo;
		
	
	private int billNo;
	
	
	private int menuId;
	
	
	private int catId;
	
	
	private int itemId;
	
	
	private int orderQty;
	
	
	private int billQty;
	
	private int orderId;
	
	private int rateType;
	
	
	private float rate;
	
	
	private float mrp;
	
	
	private float grandTotal;
	
	
	private Float sgstPer;
	
	
	private Float sgstRs;
	
	
	private Float cgstPer;
	
	
	private Float cgstRs;
	
	
	private Float igstPer;
	
	
	private Float igstRs;
	
	
	private Float taxableAmt;
	
	
	private String remark;
	
	
	private int delStatus;

	private Float baseRate;
	
	private Float totalTax;
	
	private int grnType;
	
	private Date expiryDate;
	
	
	
	 private int isGrngvnApplied;
	    
	    
	    
	    
		public int getIsGrngvnApplied() {
			return isGrngvnApplied;
		}
		public void setIsGrngvnApplied(int isGrngvnApplied) {
			this.isGrngvnApplied = isGrngvnApplied;
		}

	public int getGrnType() {
		return grnType;
	}


	public void setGrnType(int grnType) {
		this.grnType = grnType;
	}


	public Date getExpiryDate() {
		return expiryDate;
	}


	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}


	public int getBillDetailNo() {
		return billDetailNo;
	}


	public void setBillDetailNo(int billDetailNo) {
		this.billDetailNo = billDetailNo;
	}


	public int getBillNo() {
		return billNo;
	}


	public void setBillNo(int billNo) {
		this.billNo = billNo;
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


	public int getItemId() {
		return itemId;
	}


	public void setItemId(int itemId) {
		this.itemId = itemId;
	}


	public int getOrderQty() {
		return orderQty;
	}


	public void setOrderQty(int orderQty) {
		this.orderQty = orderQty;
	}


	public int getBillQty() {
		return billQty;
	}


	public void setBillQty(int billQty) {
		this.billQty = billQty;
	}


	public int getRateType() {
		return rateType;
	}


	public void setRateType(int rateType) {
		this.rateType = rateType;
	}


	public float getRate() {
		return rate;
	}


	public void setRate(float rate) {
		this.rate = rate;
	}


	public float getMrp() {
		return mrp;
	}


	public void setMrp(float mrp) {
		this.mrp = mrp;
	}


	public float getGrandTotal() {
		return grandTotal;
	}


	public void setGrandTotal(float grandTotal) {
		this.grandTotal = grandTotal;
	}


	public Float getSgstPer() {
		return sgstPer;
	}


	public void setSgstPer(Float sgstPer) {
		this.sgstPer = sgstPer;
	}


	public Float getSgstRs() {
		return sgstRs;
	}


	public void setSgstRs(Float sgstRs) {
		this.sgstRs = sgstRs;
	}


	public Float getCgstPer() {
		return cgstPer;
	}


	public void setCgstPer(Float cgstPer) {
		this.cgstPer = cgstPer;
	}


	public Float getCgstRs() {
		return cgstRs;
	}


	public void setCgstRs(Float cgstRs) {
		this.cgstRs = cgstRs;
	}


	public Float getIgstPer() {
		return igstPer;
	}


	public void setIgstPer(Float igstPer) {
		this.igstPer = igstPer;
	}


	public Float getIgstRs() {
		return igstRs;
	}


	public void setIgstRs(Float igstRs) {
		this.igstRs = igstRs;
	}


	

	public Float getTaxableAmt() {
		return taxableAmt;
	}


	public void setTaxableAmt(Float taxableAmt) {
		this.taxableAmt = taxableAmt;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}




	
	

	public int getDelStatus() {
		return delStatus;
	}


	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}


	public Float getBaseRate() {
		return baseRate;
	}


	public void setBaseRate(Float baseRate) {
		this.baseRate = baseRate;
	}

	
	

	public Float getTotalTax() {
		return totalTax;
	}


	public void setTotalTax(Float totalTax) {
		this.totalTax = totalTax;
	}

	
	

	public int getOrderId() {
		return orderId;
	}


	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}


	@Override
	public String toString() {
		return "PostBillDetail [billDetailNo=" + billDetailNo + ", billNo=" + billNo + ", menuId=" + menuId + ", catId="
				+ catId + ", itemId=" + itemId + ", orderQty=" + orderQty + ", billQty=" + billQty + ", orderId="
				+ orderId + ", rateType=" + rateType + ", rate=" + rate + ", mrp=" + mrp + ", grandTotal=" + grandTotal
				+ ", sgstPer=" + sgstPer + ", sgstRs=" + sgstRs + ", cgstPer=" + cgstPer + ", cgstRs=" + cgstRs
				+ ", igstPer=" + igstPer + ", igstRs=" + igstRs + ", taxableAmt=" + taxableAmt + ", remark=" + remark
				+ ", delStatus=" + delStatus + ", baseRate=" + baseRate + ", totalTax=" + totalTax + ", grnType="
				+ grnType + ", expiryDate=" + expiryDate + "]";
	}



	
}
