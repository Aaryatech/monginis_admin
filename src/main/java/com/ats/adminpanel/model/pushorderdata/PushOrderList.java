package com.ats.adminpanel.model.pushorderdata;

import java.util.List;

public class PushOrderList {
	
	List<GetOrderDataForPushOrder> getOrderDataForPushOrder;
	
	

	private int itemId;
	
	private String itemName;

	public List<GetOrderDataForPushOrder> getGetOrderDataForPushOrder() {
		return getOrderDataForPushOrder;
	}

	public int getItemId() {
		return itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setGetOrderDataForPushOrder(List<GetOrderDataForPushOrder> getOrderDataForPushOrder) {
		this.getOrderDataForPushOrder = getOrderDataForPushOrder;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	

	
	
	@Override
	public String toString() {
		return "PushOrderList [getOrderDataForPushOrder=" + getOrderDataForPushOrder + ", itemId=" + itemId
				+ ", itemName=" + itemName + "]";
	}
}
