package com.ats.adminpanel.model.production;

import java.sql.Date;
import java.util.List;


public class PostProductionHeader {
	
	private int productionHeaderId;
	
	private int itemGrp1;
	
	private int timeSlot;
	
	private String productionDate;
	
/*	private int int2;
	private int int3;
	
	private String varchar1;
	private String*/
	
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

	@Override
	public String toString() {
		return "PostProductionHeader [productionHeaderId=" + productionHeaderId + ", itemGrp1=" + itemGrp1
				+ ", timeSlot=" + timeSlot + ", productionDate=" + productionDate + ", postProductionDetail="
				+ postProductionDetail + "]";
	}

	
	
	
	
	

}
