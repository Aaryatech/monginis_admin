package com.ats.adminpanel.model.production;



public class PostProductionDetail {
	

	private int productionDetailId;
	
	private int productionHeaderId;

	private int productionQty;
	
	private int itemId;
	
	private int openingQty;
	
	private int rejectedQty;

	private int planQty;
	
	private int orderQty;
	
	private String productionBatch;
	
	private String productionDate;

	public int getProductionDetailId() {
		return productionDetailId;
	}

	public void setProductionDetailId(int productionDetailId) {
		this.productionDetailId = productionDetailId;
	}

	public int getProductionHeaderId() {
		return productionHeaderId;
	}

	public void setProductionHeaderId(int productionHeaderId) {
		this.productionHeaderId = productionHeaderId;
	}

	public int getProductionQty() {
		return productionQty;
	}

	public void setProductionQty(int productionQty) {
		this.productionQty = productionQty;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getOpeningQty() {
		return openingQty;
	}

	public void setOpeningQty(int openingQty) {
		this.openingQty = openingQty;
	}

	public int getRejectedQty() {
		return rejectedQty;
	}

	public void setRejectedQty(int rejectedQty) {
		this.rejectedQty = rejectedQty;
	}

	public int getPlanQty() {
		return planQty;
	}

	public void setPlanQty(int planQty) {
		this.planQty = planQty;
	}

	public int getOrderQty() {
		return orderQty;
	}

	public void setOrderQty(int orderQty) {
		this.orderQty = orderQty;
	}

	public String getProductionBatch() {
		return productionBatch;
	}

	public void setProductionBatch(String productionBatch) {
		this.productionBatch = productionBatch;
	}

	public String getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(String productionDate) {
		this.productionDate = productionDate;
	}

	@Override
	public String toString() {
		return "PostProductionDetail [productionDetailId=" + productionDetailId + ", productionHeaderId="
				+ productionHeaderId + ", productionQty=" + productionQty + ", itemId=" + itemId + ", openingQty="
				+ openingQty + ", rejectedQty=" + rejectedQty + ", planQty=" + planQty + ", orderQty=" + orderQty
				+ ", productionBatch=" + productionBatch + ", productionDate=" + productionDate + "]";
	}

	 
	
	
}
