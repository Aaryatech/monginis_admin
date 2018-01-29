package com.ats.adminpanel.model.production;

public class GetProdPlanDetail {

	private int productionDetailId;
	
	private int productionHeaderId;

	private int planQty;
	
	private int orderQty;
	
	private int openingQty;
	
	private int rejectedQty;
	
	private int productionQty;
	 
	private int itemId;
	
	private String itemName;
	

	private String productionBatch;
	
	private String productionDate;
	
	
	

private float  curClosingQty;//new Field Added Sachin


private float curOpeQty;//new fiedl

private float opTotal;


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

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public float getCurClosingQty() {
		return curClosingQty;
	}

	public void setCurClosingQty(float curClosingQty) {
		this.curClosingQty = curClosingQty;
	}

	public float getCurOpeQty() {
		return curOpeQty;
	}

	public void setCurOpeQty(float curOpeQty) {
		this.curOpeQty = curOpeQty;
	}

	public float getOpTotal() {
		return opTotal;
	}

	public void setOpTotal(float opTotal) {
		this.opTotal = opTotal;
	}

	@Override
	public String toString() {
		return "GetProdPlanDetail [productionDetailId=" + productionDetailId + ", productionHeaderId="
				+ productionHeaderId + ", planQty=" + planQty + ", orderQty=" + orderQty + ", openingQty=" + openingQty
				+ ", rejectedQty=" + rejectedQty + ", productionQty=" + productionQty + ", itemId=" + itemId
				+ ", itemName=" + itemName + ", productionBatch=" + productionBatch + ", productionDate="
				+ productionDate + ", curClosingQty=" + curClosingQty + ", curOpeQty=" + curOpeQty + ", opTotal="
				+ opTotal + "]";
	}

	
	
	
}
