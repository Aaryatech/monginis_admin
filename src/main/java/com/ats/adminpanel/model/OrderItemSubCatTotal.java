package com.ats.adminpanel.model;

import java.io.Serializable;
public class OrderItemSubCatTotal implements Serializable{


	private int subCatId;
	
	private String subCatName;
	
	private int total;

	public int getSubCatId() {
		return subCatId;
	}

	public String getSubCatName() {
		return subCatName;
	}

	public int getTotal() {
		return total;
	}

	public void setSubCatId(int subCatId) {
		this.subCatId = subCatId;
	}

	public void setSubCatName(String subCatName) {
		this.subCatName = subCatName;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "OrderItemSubCatTotal [subCatId=" + subCatId + ", subCatName=" + subCatName + ", total=" + total + "]";
	}
	
	
}
