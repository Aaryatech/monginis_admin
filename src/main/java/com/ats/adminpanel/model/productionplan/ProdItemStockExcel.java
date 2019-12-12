package com.ats.adminpanel.model.productionplan;

import java.util.List;

public class ProdItemStockExcel {

	private int id;
	private String itemName;

	private List<TypewiseStockExcel> stockList;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public List<TypewiseStockExcel> getStockList() {
		return stockList;
	}

	public void setStockList(List<TypewiseStockExcel> stockList) {
		this.stockList = stockList;
	}

	@Override
	public String toString() {
		return "ProdItemStockExcel [id=" + id + ", itemName=" + itemName + ", stockList=" + stockList + "]";
	}

}
