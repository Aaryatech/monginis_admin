package com.ats.adminpanel.model.pushorderdata;

public class GetOrderDataForPushOrder {
	
	
	int orderId;
	int itemId;

	int orderQty;

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

	@Override
	public String toString() {
		return "GetOrderDataForPushOrder [orderId=" + orderId + ", itemId=" + itemId + ", orderQty=" + orderQty + "]";
	}
	
	
	
}
