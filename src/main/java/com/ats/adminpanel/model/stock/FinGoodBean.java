package com.ats.adminpanel.model.stock;

import java.util.List;

public class FinGoodBean {
	
	 List<FinishedGoodStockDetail> stockDetail;
	 
	int isDayEndEnable;
	
	String stockDate;

	public List<FinishedGoodStockDetail> getStockDetail() {
		return stockDetail;
	}

	public void setStockDetail(List<FinishedGoodStockDetail> stockDetail) {
		this.stockDetail = stockDetail;
	}

	public int getIsDayEndEnable() {
		return isDayEndEnable;
	}

	public void setIsDayEndEnable(int isDayEndEnable) {
		this.isDayEndEnable = isDayEndEnable;
	}

	public String getStockDate() {
		return stockDate;
	}

	public void setStockDate(String stockDate) {
		this.stockDate = stockDate;
	}

	@Override
	public String toString() {
		return "FinGoodBean [stockDetail=" + stockDetail + ", isDayEndEnable=" + isDayEndEnable + ", stockDate="
				+ stockDate + "]";
	}


}
