package com.ats.adminpanel.model.productionplan;

public class ProdItemStockTotal {

	private int id;
	private int total;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "ProdItemStockTotal [id=" + id + ", total=" + total + "]";
	}

}
