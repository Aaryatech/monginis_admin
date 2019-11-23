package com.ats.adminpanel.model.salesvaluereport;

import java.io.Serializable;


public class SalesReturnValueDao implements Serializable{

	private String id;
	
	private int subCatId;
	
	private float grandTotal;

	private float grnQty;
	
	private float gvnQty;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getSubCatId() {
		return subCatId;
	}

	public void setSubCatId(int subCatId) {
		this.subCatId = subCatId;
	}

	public float getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(float grandTotal) {
		this.grandTotal = grandTotal;
	}

	public float getGrnQty() {
		return grnQty;
	}

	public void setGrnQty(float grnQty) {
		this.grnQty = grnQty;
	}

	public float getGvnQty() {
		return gvnQty;
	}

	public void setGvnQty(float gvnQty) {
		this.gvnQty = gvnQty;
	}

	@Override
	public String toString() {
		return "SalesReturnValueDao [id=" + id + ", subCatId=" + subCatId + ", grandTotal=" + grandTotal + ", grnQty="
				+ grnQty + ", gvnQty=" + gvnQty + "]";
	}
	
	
	
}
