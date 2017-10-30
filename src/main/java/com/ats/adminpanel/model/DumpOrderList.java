package com.ats.adminpanel.model;

import java.util.List;

public class DumpOrderList {
	
	List<OrderData> orderData;
	
	private String itemId;
	

	private String itemName;


	public List<OrderData> getOrderData() {
		return orderData;
	}


	public void setOrderData(List<OrderData> orderData) {
		this.orderData = orderData;
	}


	public String getItemId() {
		return itemId;
	}


	public void setItemId(String itemId) {
		this.itemId = itemId;
	}


	public String getItemName() {
		return itemName;
	}


	public void setItemName(String itemName) {
		this.itemName = itemName;
	}


	@Override
	public String toString() {
		return "DumpOrder [orderData=" + orderData + ", itemId=" + itemId + ", itemName=" + itemName + "]";
	}
	

	
	
}
