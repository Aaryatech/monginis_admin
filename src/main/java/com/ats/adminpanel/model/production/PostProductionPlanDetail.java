package com.ats.adminpanel.model.production;

import java.util.Date;


public class PostProductionPlanDetail {


	private int productionDetailId;
	
	private int productionHeaderId;

	private int planQty;
	
	private int orderQty;
	
	private int openingQty;
	
	private int rejectedQty;
	
	private int productionQty;
	 
	private int itemId;

	private String productionBatch;
	
	private Date productionDate;

		public String getProductionBatch() {
		return productionBatch;
	}

	public void setProductionBatch(String productionBatch) {
		this.productionBatch = productionBatch;
	}

	public Date getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
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

		public int getItemId() {
			return itemId;
		}

		public void setItemId(int itemId) {
			this.itemId = itemId;
		}

		@Override
		public String toString() {
			return "PostProductionPlanDetail [productionDetailId=" + productionDetailId + ", productionHeaderId="
					+ productionHeaderId + ", planQty=" + planQty + ", orderQty=" + orderQty + ", openingQty="
					+ openingQty + ", rejectedQty=" + rejectedQty + ", productionQty=" + productionQty + ", itemId="
					+ itemId + ", productionBatch=" + productionBatch + ", productionDate=" + productionDate + "]";
		}


}
