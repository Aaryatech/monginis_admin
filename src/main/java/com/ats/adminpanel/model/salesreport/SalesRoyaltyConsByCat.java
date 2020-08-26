package com.ats.adminpanel.model.salesreport;

public class SalesRoyaltyConsByCat {

private String uid;
	
	private int id;
	private int catId;
	private int subCatId;
	private String item_name;
	private String cat_name;
	
	float billQty;
	float grandTotal;
	float taxableAmt;
	
	float grnQty;
	float grnGrandTotal;
	float grnTaxableAmt;
	
	float gvnQty;
	float gvnGrandTotal;
	float gvnTaxableAmt;
	
	float crnGrnQty;
	float crnGrnGrandTotal;
	float crnGrnTaxableAmt;
	
	float crnGvnQty;
	float crnGvnGrandTotal;
	float crnGvnTaxableAmt;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCatId() {
		return catId;
	}
	public void setCatId(int catId) {
		this.catId = catId;
	}
	public int getSubCatId() {
		return subCatId;
	}
	public void setSubCatId(int subCatId) {
		this.subCatId = subCatId;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public String getCat_name() {
		return cat_name;
	}
	public void setCat_name(String cat_name) {
		this.cat_name = cat_name;
	}
	public float getBillQty() {
		return billQty;
	}
	public void setBillQty(float billQty) {
		this.billQty = billQty;
	}
	public float getGrandTotal() {
		return grandTotal;
	}
	public void setGrandTotal(float grandTotal) {
		this.grandTotal = grandTotal;
	}
	public float getTaxableAmt() {
		return taxableAmt;
	}
	public void setTaxableAmt(float taxableAmt) {
		this.taxableAmt = taxableAmt;
	}
	public float getGrnQty() {
		return grnQty;
	}
	public void setGrnQty(float grnQty) {
		this.grnQty = grnQty;
	}
	public float getGrnGrandTotal() {
		return grnGrandTotal;
	}
	public void setGrnGrandTotal(float grnGrandTotal) {
		this.grnGrandTotal = grnGrandTotal;
	}
	public float getGrnTaxableAmt() {
		return grnTaxableAmt;
	}
	public void setGrnTaxableAmt(float grnTaxableAmt) {
		this.grnTaxableAmt = grnTaxableAmt;
	}
	public float getGvnQty() {
		return gvnQty;
	}
	public void setGvnQty(float gvnQty) {
		this.gvnQty = gvnQty;
	}
	public float getGvnGrandTotal() {
		return gvnGrandTotal;
	}
	public void setGvnGrandTotal(float gvnGrandTotal) {
		this.gvnGrandTotal = gvnGrandTotal;
	}
	public float getGvnTaxableAmt() {
		return gvnTaxableAmt;
	}
	public void setGvnTaxableAmt(float gvnTaxableAmt) {
		this.gvnTaxableAmt = gvnTaxableAmt;
	}
	public float getCrnGrnQty() {
		return crnGrnQty;
	}
	public void setCrnGrnQty(float crnGrnQty) {
		this.crnGrnQty = crnGrnQty;
	}
	public float getCrnGrnGrandTotal() {
		return crnGrnGrandTotal;
	}
	public void setCrnGrnGrandTotal(float crnGrnGrandTotal) {
		this.crnGrnGrandTotal = crnGrnGrandTotal;
	}
	public float getCrnGrnTaxableAmt() {
		return crnGrnTaxableAmt;
	}
	public void setCrnGrnTaxableAmt(float crnGrnTaxableAmt) {
		this.crnGrnTaxableAmt = crnGrnTaxableAmt;
	}
	public float getCrnGvnQty() {
		return crnGvnQty;
	}
	public void setCrnGvnQty(float crnGvnQty) {
		this.crnGvnQty = crnGvnQty;
	}
	public float getCrnGvnGrandTotal() {
		return crnGvnGrandTotal;
	}
	public void setCrnGvnGrandTotal(float crnGvnGrandTotal) {
		this.crnGvnGrandTotal = crnGvnGrandTotal;
	}
	public float getCrnGvnTaxableAmt() {
		return crnGvnTaxableAmt;
	}
	public void setCrnGvnTaxableAmt(float crnGvnTaxableAmt) {
		this.crnGvnTaxableAmt = crnGvnTaxableAmt;
	}
	@Override
	public String toString() {
		return "SalesRoyaltyConsByCat [uid=" + uid + ", id=" + id + ", catId=" + catId + ", subCatId=" + subCatId
				+ ", item_name=" + item_name + ", cat_name=" + cat_name + ", billQty=" + billQty + ", grandTotal="
				+ grandTotal + ", taxableAmt=" + taxableAmt + ", grnQty=" + grnQty + ", grnGrandTotal=" + grnGrandTotal
				+ ", grnTaxableAmt=" + grnTaxableAmt + ", gvnQty=" + gvnQty + ", gvnGrandTotal=" + gvnGrandTotal
				+ ", gvnTaxableAmt=" + gvnTaxableAmt + ", crnGrnQty=" + crnGrnQty + ", crnGrnGrandTotal="
				+ crnGrnGrandTotal + ", crnGrnTaxableAmt=" + crnGrnTaxableAmt + ", crnGvnQty=" + crnGvnQty
				+ ", crnGvnGrandTotal=" + crnGvnGrandTotal + ", crnGvnTaxableAmt=" + crnGvnTaxableAmt + "]";
	}
	
}
