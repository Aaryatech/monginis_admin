package com.ats.adminpanel.model;



public class GetSellBillDetail {


	private int sellBillDetailNo;
	
	private int sellBillNo;
	
	private float taxableAmt;
	
	private float totalTax;

	private float grandTotal;
	

	private float mrp;
	
	private float mrpBaseRate;
	

	private int qty;
	
	private float sgstPer;
	
	private float cgstPer;
	
	private String itemName;

	public int getSellBillDetailNo() {
		return sellBillDetailNo;
	}

	public void setSellBillDetailNo(int sellBillDetailNo) {
		this.sellBillDetailNo = sellBillDetailNo;
	}

	public int getSellBillNo() {
		return sellBillNo;
	}

	public void setSellBillNo(int sellBillNo) {
		this.sellBillNo = sellBillNo;
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

	public float getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(float grandTotal) {
		this.grandTotal = grandTotal;
	}

	public float getMrp() {
		return mrp;
	}

	public void setMrp(float mrp) {
		this.mrp = mrp;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
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

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public float getMrpBaseRate() {
		return mrpBaseRate;
	}

	public void setMrpBaseRate(float mrpBaseRate) {
		this.mrpBaseRate = mrpBaseRate;
	}

	@Override
	public String toString() {
		return "GetSellBillDetail [sellBillDetailNo=" + sellBillDetailNo + ", sellBillNo=" + sellBillNo
				+ ", taxableAmt=" + taxableAmt + ", totalTax=" + totalTax + ", grandTotal=" + grandTotal + ", mrp="
				+ mrp + ", mrpBaseRate=" + mrpBaseRate + ", qty=" + qty + ", sgstPer=" + sgstPer + ", cgstPer="
				+ cgstPer + ", itemName=" + itemName + "]";
	}

	
	
	
	
}
