package com.ats.adminpanel.model.stock;

import java.util.List;

public class UpdateBmsStockList {
	
	List<UpdateBmsStock> bmsStock;

	public List<UpdateBmsStock> getBmsStock() {
		return bmsStock;
	}

	public void setBmsStock(List<UpdateBmsStock> bmsStock) {
		this.bmsStock = bmsStock;
	}

	@Override
	public String toString() {
		return "UpdateBmsStockList [bmsStock=" + bmsStock + "]";
	}


}
