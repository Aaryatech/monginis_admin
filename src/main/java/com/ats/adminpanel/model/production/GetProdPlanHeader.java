package com.ats.adminpanel.model.production;

import java.util.Date;

public class GetProdPlanHeader {
	 
	
	
	private int productionHeaderId;
	 

	private int catId;
	
	
	private int timeSlot;
	
	
	private String productionBatch;
	
	
	private String productionStatus;
	
	
	private String productionDate;

	
	private int isMixing;

	
	private int isBom;
	
	
	private int delStatus;
	
	
	private String  catName;

	private int isPlanned;
	
	
	public int getProductionHeaderId() {
		return productionHeaderId;
	}


	public void setProductionHeaderId(int productionHeaderId) {
		this.productionHeaderId = productionHeaderId;
	}


	public int getCatId() {
		return catId;
	}


	public void setCatId(int catId) {
		this.catId = catId;
	}


	public int getTimeSlot() {
		return timeSlot;
	}


	public void setTimeSlot(int timeSlot) {
		this.timeSlot = timeSlot;
	}


	public String getProductionBatch() {
		return productionBatch;
	}


	public void setProductionBatch(String productionBatch) {
		this.productionBatch = productionBatch;
	}


	public String getProductionStatus() {
		return productionStatus;
	}


	public void setProductionStatus(String productionStatus) {
		this.productionStatus = productionStatus;
	}


	public String getProductionDate() {
		return productionDate;
	}


	public void setProductionDate(String productionDate) {
		this.productionDate = productionDate;
	}


	public int getIsMixing() {
		return isMixing;
	}


	public void setIsMixing(int isMixing) {
		this.isMixing = isMixing;
	}


	public int getIsBom() {
		return isBom;
	}


	public void setIsBom(int isBom) {
		this.isBom = isBom;
	}


	public int getDelStatus() {
		return delStatus;
	}


	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}


	public String getCatName() {
		return catName;
	}


	public void setCatName(String catName) {
		this.catName = catName;
	}


	public int getIsPlanned() {
		return isPlanned;
	}


	public void setIsPlanned(int isPlanned) {
		this.isPlanned = isPlanned;
	}


	@Override
	public String toString() {
		return "GetProdPlanHeader [productionHeaderId=" + productionHeaderId + ", catId=" + catId + ", timeSlot="
				+ timeSlot + ", productionBatch=" + productionBatch + ", productionStatus=" + productionStatus
				+ ", productionDate=" + productionDate + ", isMixing=" + isMixing + ", isBom=" + isBom + ", delStatus="
				+ delStatus + ", catName=" + catName + ", isPlanned=" + isPlanned + "]";
	}


}
