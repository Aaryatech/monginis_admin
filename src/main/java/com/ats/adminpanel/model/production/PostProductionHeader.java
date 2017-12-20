package com.ats.adminpanel.model.production;

import java.sql.Date;
import java.util.List;


public class PostProductionHeader {
	
	private int productionHeaderId;
	
	private int itemGrp1;
	
	private int timeSlot;
	
	private String productionDate;
	
private int isMixing;

	
	private int isBom;
	
	
	private int delStatus;
	
	
 

	private int isPlanned;
	
	
/*	private int int2;
	private int int3;
	
	private String varchar1;
	private String*/
	
private String productionBatch;
	
	
	private int productionStatus;
	
	
	private List<PostProductionDetail> postProductionDetail;

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

	public List<PostProductionDetail> getPostProductionDetail() {
		return postProductionDetail;
	}

	public void setPostProductionDetail(List<PostProductionDetail> postProductionDetail) {
		this.postProductionDetail = postProductionDetail;
	}

	public int getItemGrp1() {
		return itemGrp1;
	}

	public void setItemGrp1(int itemGrp1) {
		this.itemGrp1 = itemGrp1;
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

	@Override
	public String toString() {
		return "PostProductionHeader [productionHeaderId=" + productionHeaderId + ", itemGrp1=" + itemGrp1
				+ ", timeSlot=" + timeSlot + ", productionDate=" + productionDate + ", isMixing=" + isMixing
				+ ", isBom=" + isBom + ", delStatus=" + delStatus + ", isPlanned=" + isPlanned + ", productionBatch="
				+ productionBatch + ", productionStatus=" + productionStatus + ", postProductionDetail="
				+ postProductionDetail + "]";
	}

	 
	
	
	
	
	

}
