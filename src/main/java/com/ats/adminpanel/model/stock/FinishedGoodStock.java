package com.ats.adminpanel.model.stock;

import java.util.Date;
import java.util.List;

public class FinishedGoodStock {
	
int finStockId;
int catId;
Date finGoodStockDate;

int finGoodStockStatus;

	
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


	@Override
	public String toString() {
		return "FinishedGoodStock [finStockId=" + finStockId + ", catId=" + catId + ", finGoodStockDate="
				+ finGoodStockDate + ", finGoodStockStatus=" + finGoodStockStatus + ", finishedGoodStockDetail="
				+ finishedGoodStockDetail + "]";
	}


}
