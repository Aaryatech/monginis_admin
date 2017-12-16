package com.ats.adminpanel.model.production;

import java.util.List;


public class PostProdPlanHeader {

	
	private int productionHeaderId;
	 
	private int itemGrp1;
	
	private int timeSlot;
	
	private String productionBatch;
	
	private int productionStatus;
	
	private String productionDate;
	

	private int isMixing;

	private int isBom;
	
	private int isPlanned;

	
	private int delStatus;
	
	private List<PostProductionPlanDetail> postProductionPlanDetail;
	
	

	public int getIsPlanned() {
		return isPlanned;
	}

	public void setIsPlanned(int isPlanned) {
		this.isPlanned = isPlanned;
	}

	public String getProductionBatch() {
		return productionBatch;
	}

	public void setProductionBatch(String productionBatch) {
		this.productionBatch = productionBatch;
	}

	public int getProductionStatus() {
		return productionStatus;
	}

	public void setProductionStatus(int productionStatus) {
		this.productionStatus = productionStatus;
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

	
	public int getProductionHeaderId() {
		return productionHeaderId;
	}

	public void setProductionHeaderId(int productionHeaderId) {
		this.productionHeaderId = productionHeaderId;
	}


	public int getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(int timeSlot) {
		this.timeSlot = timeSlot;
	}

	public String getProductionDate() {
		return productionDate;
	}

	
     
	public void setProductionDate(String productionDate) {
		this.productionDate = productionDate;
	}

	public List<PostProductionPlanDetail> getPostProductionPlanDetail() {
		return postProductionPlanDetail;
	}

	public void setPostProductionPlanDetail(List<PostProductionPlanDetail> postProductionPlanDetail) {
		this.postProductionPlanDetail = postProductionPlanDetail;
	}

	public int getItemGrp1() {
		return itemGrp1;
	}

	public void setItemGrp1(int itemGrp1) {
		this.itemGrp1 = itemGrp1;
	}

	@Override
	public String toString() {
		return "PostProdPlanHeader [productionHeaderId=" + productionHeaderId + ", itemGrp1=" + itemGrp1 + ", timeSlot="
				+ timeSlot + ", productionBatch=" + productionBatch + ", productionStatus=" + productionStatus
				+ ", productionDate=" + productionDate + ", isMixing=" + isMixing + ", isBom=" + isBom + ", delStatus="
				+ delStatus + ", postProductionPlanDetail=" + postProductionPlanDetail + "]";
	}
    
}
