package com.ats.adminpanel.model.item;

import java.util.List;

public class FrItemStockList {
	private List<FrItemStock> frItemStock;

	public List<FrItemStock> getFrItemStock() {
		return frItemStock;
	}

	public void setFrItemStock(List<FrItemStock> frItemStock) {
		this.frItemStock = frItemStock;
	}

	@Override
	public String toString() {
		return "FrItemStockList [frItemStock=" + frItemStock + ", getFrItemStock()=" + getFrItemStock()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	
	
	
	
	
	
	

}
