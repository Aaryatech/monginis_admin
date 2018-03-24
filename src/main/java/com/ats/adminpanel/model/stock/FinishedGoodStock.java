package com.ats.adminpanel.model.stock;

import java.util.Date;
import java.util.List;

public class FinishedGoodStock {
	
int finStockId;
int catId;
Date finGoodStockDate;
String timestamp;//new field 27-01-18
int finGoodStockStatus;
int delStatus;

	
	List<FinishedGoodStockDetail> finishedGoodStockDetail;


	public int getFinStockId() {
		return finStockId;
	}


	public void setFinStockId(int finStockId) {
		this.finStockId = finStockId;
	}


	public int getCatId() {
		return catId;
	}


	public void setCatId(int catId) {
		this.catId = catId;
	}


	public Date getFinGoodStockDate() {
		return finGoodStockDate;
	}


	public void setFinGoodStockDate(Date finGoodStockDate) {
		this.finGoodStockDate = finGoodStockDate;
	}


	public int getFinGoodStockStatus() {
		return finGoodStockStatus;
	}


	public void setFinGoodStockStatus(int finGoodStockStatus) {
		this.finGoodStockStatus = finGoodStockStatus;
	}


	public List<FinishedGoodStockDetail> getFinishedGoodStockDetail() {
		return finishedGoodStockDetail;
	}


	public void setFinishedGoodStockDetail(List<FinishedGoodStockDetail> finishedGoodStockDetail) {
		this.finishedGoodStockDetail = finishedGoodStockDetail;
	}


	public int getDelStatus() {
		return delStatus;
	}


	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}


	public String getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}


	@Override
	public String toString() {
		return "FinishedGoodStock [finStockId=" + finStockId + ", catId=" + catId + ", finGoodStockDate="
				+ finGoodStockDate + ", timestamp=" + timestamp + ", finGoodStockStatus=" + finGoodStockStatus
				+ ", delStatus=" + delStatus + ", finishedGoodStockDetail=" + finishedGoodStockDetail + "]";
	}


	

}
