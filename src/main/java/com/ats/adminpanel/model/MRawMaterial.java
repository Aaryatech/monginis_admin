package com.ats.adminpanel.model;

public class MRawMaterial {

	
	private int rmId;

	private String rmName;
	
	private int rmRate;
	
	private int qty;
	
	private int poQty;
	private int stockQty;
	private float  poRate;

	public int getPoQty() {
		return poQty;
	}

	public void setPoQty(int poQty) {
		this.poQty = poQty;
	}

	public int getStockQty() {
		return stockQty;
	}

	public void setStockQty(int stockQty) {
		this.stockQty = stockQty;
	}

	public float getPoRate() {
		return poRate;
	}

	public void setPoRate(float poRate) {
		this.poRate = poRate;
	}

	public int getRmId() {
		return rmId;
	}

	public void setRmId(int rmId) {
		this.rmId = rmId;
	}

	public String getRmName() {
		return rmName;
	}

	public void setRmName(String rmName) {
		this.rmName = rmName;
	}

	public int getRmRate() {
		return rmRate;
	}

	public void setRmRate(int rmRate) {
		this.rmRate = rmRate;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	@Override
	public String toString() {
		return "MRawMaterial [rmId=" + rmId + ", rmName=" + rmName + ", rmRate=" + rmRate + ", qty=" + qty + "]";
	}
	
	

	
	
	
	
}
