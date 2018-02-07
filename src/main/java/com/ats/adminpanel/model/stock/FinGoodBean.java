package com.ats.adminpanel.model.stock;

import java.util.List;

public class FinGoodBean {
	
	 List<FinishedGoodStockDetail> stockDetail;
	 
	int isDayEndEnable;

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

	@Override
	public String toString() {
		return "FinGoodBean [stockDetail=" + stockDetail + ", isDayEndEnable=" + isDayEndEnable + "]";
	}

	

}
