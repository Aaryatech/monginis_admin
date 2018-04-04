package com.ats.adminpanel.model.billing;

import java.util.Date;

public class GetBillDetailPrint {
	
	 private Integer billDetailNo;
	    private Integer billNo;
	    private Integer menuId;
	    private Integer catId;
	    private Integer itemId;
	    private Integer orderQty;
	    private Integer billQty;
	    private Integer orderId;
	    private Integer rateType;
	    private float rate;
	    private float mrp;
	    private float grandTotal;
	    private float sgstPer;
	    private float sgstRs;
	    private float cgstPer;
	    private float cgstRs;
	    private float igstPer;
	    private float igstRs;
	    private float taxableAmt;
	    private String remark;
	    private Integer delStatus;
	    private String itemName;
	    private String catName;
	    private String billDate;
	    private float baseRate;
	    private float totalTax;
	    private int grnType;
	    private Date expiryDate;
	    private int isGrngvnApplied;
		
	    private String itemHsncd;
		private String itemUom;
		
		
		private int  subCatId;
		
		
		public Integer getBillDetailNo() {
			return billDetailNo;
		}
		public void setBillDetailNo(Integer billDetailNo) {
			this.billDetailNo = billDetailNo;
		}
		public Integer getBillNo() {
			return billNo;
		}
		public void setBillNo(Integer billNo) {
			this.billNo = billNo;
		}
		public Integer getMenuId() {
			return menuId;
		}
		public void setMenuId(Integer menuId) {
			this.menuId = menuId;
		}
		public Integer getCatId() {
			return catId;
		}
		public void setCatId(Integer catId) {
			this.catId = catId;
		}
		public Integer getItemId() {
			return itemId;
		}
		public void setItemId(Integer itemId) {
			this.itemId = itemId;
		}
		public Integer getOrderQty() {
			return orderQty;
		}
		public void setOrderQty(Integer orderQty) {
			this.orderQty = orderQty;
		}
		public Integer getBillQty() {
			return billQty;
		}
		public void setBillQty(Integer billQty) {
			this.billQty = billQty;
		}
		public Integer getOrderId() {
			return orderId;
		}
		public void setOrderId(Integer orderId) {
			this.orderId = orderId;
		}
		public Integer getRateType() {
			return rateType;
		}
		public void setRateType(Integer rateType) {
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
		public float getSgstPer() {
			return sgstPer;
		}
		public void setSgstPer(float sgstPer) {
			this.sgstPer = sgstPer;
		}
		public float getSgstRs() {
			return sgstRs;
		}
		public void setSgstRs(float sgstRs) {
			this.sgstRs = sgstRs;
		}
		public float getCgstPer() {
			return cgstPer;
		}
		public void setCgstPer(float cgstPer) {
			this.cgstPer = cgstPer;
		}
		public float getCgstRs() {
			return cgstRs;
		}
		public void setCgstRs(float cgstRs) {
			this.cgstRs = cgstRs;
		}
		public float getIgstPer() {
			return igstPer;
		}
		public void setIgstPer(float igstPer) {
			this.igstPer = igstPer;
		}
		public float getIgstRs() {
			return igstRs;
		}
		public void setIgstRs(float igstRs) {
			this.igstRs = igstRs;
		}
		public float getTaxableAmt() {
			return taxableAmt;
		}
		public void setTaxableAmt(float taxableAmt) {
			this.taxableAmt = taxableAmt;
		}
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
		public Integer getDelStatus() {
			return delStatus;
		}
		public void setDelStatus(Integer delStatus) {
			this.delStatus = delStatus;
		}
		public String getItemName() {
			return itemName;
		}
		public void setItemName(String itemName) {
			this.itemName = itemName;
		}
		public String getCatName() {
			return catName;
		}
		public void setCatName(String catName) {
			this.catName = catName;
		}
		public String getBillDate() {
			return billDate;
		}
		public void setBillDate(String billDate) {
			this.billDate = billDate;
		}
		public float getBaseRate() {
			return baseRate;
		}
		public void setBaseRate(float baseRate) {
			this.baseRate = baseRate;
		}
		public float getTotalTax() {
			return totalTax;
		}
		public void setTotalTax(float totalTax) {
			this.totalTax = totalTax;
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
		public int getIsGrngvnApplied() {
			return isGrngvnApplied;
		}
		public void setIsGrngvnApplied(int isGrngvnApplied) {
			this.isGrngvnApplied = isGrngvnApplied;
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
		public int getSubCatId() {
			return subCatId;
		}
		public void setSubCatId(int subCatId) {
			this.subCatId = subCatId;
		}
		@Override
		public String toString() {
			return "GetBillDetailPrint [billDetailNo=" + billDetailNo + ", billNo=" + billNo + ", menuId=" + menuId
					+ ", catId=" + catId + ", itemId=" + itemId + ", orderQty=" + orderQty + ", billQty=" + billQty
					+ ", orderId=" + orderId + ", rateType=" + rateType + ", rate=" + rate + ", mrp=" + mrp
					+ ", grandTotal=" + grandTotal + ", sgstPer=" + sgstPer + ", sgstRs=" + sgstRs + ", cgstPer="
					+ cgstPer + ", cgstRs=" + cgstRs + ", igstPer=" + igstPer + ", igstRs=" + igstRs + ", taxableAmt="
					+ taxableAmt + ", remark=" + remark + ", delStatus=" + delStatus + ", itemName=" + itemName
					+ ", catName=" + catName + ", billDate=" + billDate + ", baseRate=" + baseRate + ", totalTax="
					+ totalTax + ", grnType=" + grnType + ", expiryDate=" + expiryDate + ", isGrngvnApplied="
					+ isGrngvnApplied + ", itemHsncd=" + itemHsncd + ", itemUom=" + itemUom + ", subCatId=" + subCatId
					+ "]";
		}
		
		
		
		
}
