package com.ats.adminpanel.model;

public class OrderData {
	
private int frId;
	
	private int orderQty;

	public int getFrId() {
		return frId;
	}

	public void setFrId(int frId) {
		this.frId = frId;
	}

	public int getOrderQty() {
		return orderQty;
	}

	public void setOrderQty(int orderQty) {
		this.orderQty = orderQty;
	}

	@Override
	public String toString() {
		return "OrderData [frId=" + frId + ", orderQty=" + orderQty + "]";
	}
	
	

}
