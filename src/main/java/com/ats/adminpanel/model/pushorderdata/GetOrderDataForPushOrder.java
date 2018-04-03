package com.ats.adminpanel.model.pushorderdata;

public class GetOrderDataForPushOrder {
	
	
	int orderId;
	int itemId;

	int orderQty;

	int frId;
	
	public int getOrderId() {
		return orderId;
	}

	public int getItemId() {
		return itemId;
	}

	public int getOrderQty() {
		return orderQty;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public void setOrderQty(int orderQty) {
		this.orderQty = orderQty;
	}

	public int getFrId() {
		return frId;
	}

	public void setFrId(int frId) {
		this.frId = frId;
	}

	@Override
	public String toString() {
		return "GetOrderDataForPushOrder [orderId=" + orderId + ", itemId=" + itemId + ", orderQty=" + orderQty
				+ ", frId=" + frId + "]";
	}

}
