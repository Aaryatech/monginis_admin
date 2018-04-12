package com.ats.adminpanel.model.production;

public class SubCatwiseVariancePdf {
	
	String subCatName;
	
	
	float opBalance;
	
	int planQty;
	
	int prodQty;
	
	float total;
	
	int orderQty;
	
	float variance;
	
	float clBal;
	
	
	
	

	public String getSubCatName() {
		return subCatName;
	}

	public void setSubCatName(String subCatName) {
		this.subCatName = subCatName;
	}

	
	public float getOpBalance() {
		return opBalance;
	}

	public void setOpBalance(float opBalance) {
		this.opBalance = opBalance;
	}

	public int getPlanQty() {
		return planQty;
	}

	public void setPlanQty(int planQty) {
		this.planQty = planQty;
	}

	public int getProdQty() {
		return prodQty;
	}

	public void setProdQty(int prodQty) {
		this.prodQty = prodQty;
	}

	
	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public int getOrderQty() {
		return orderQty;
	}

	public void setOrderQty(int orderQty) {
		this.orderQty = orderQty;
	}

	

	public float getVariance() {
		return variance;
	}

	public void setVariance(float variance) {
		this.variance = variance;
	}

	

	public float getClBal() {
		return clBal;
	}

	public void setClBal(float clBal) {
		this.clBal = clBal;
	}

	@Override
	public String toString() {
		return "SubCatwiseVariancePdf [subCatName=" + subCatName + ", opBalance=" + opBalance + ", planQty=" + planQty
				+ ", prodQty=" + prodQty + ", total=" + total + ", orderQty=" + orderQty + ", variance=" + variance
				+ ", clBal=" + clBal + "]";
	}


}
