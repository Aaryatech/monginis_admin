package com.ats.adminpanel.model.salesvaluereport;

import java.io.Serializable;

public class SalesReturnQtyDao implements Serializable{
	
	private String id;
	
	private int subCatId;
	
	private float billQty;
	
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

	public float getBillQty() {
		return billQty;
	}

	public void setBillQty(float billQty) {
		this.billQty = billQty;
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
		return "SalesReturnQtyDao [id=" + id + ", subCatId=" + subCatId + ", billQty=" + billQty + ", grnQty=" + grnQty
				+ ", gvnQty=" + gvnQty + "]";
	}
	
	
}
