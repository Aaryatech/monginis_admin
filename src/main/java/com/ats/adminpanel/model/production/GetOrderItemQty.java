package com.ats.adminpanel.model.production;

import java.io.Serializable;




public class GetOrderItemQty implements Serializable{

	


	
	int orderId;
	
	private int qty;
	

	private String itemId;
	
	private int menuId;
	
	private int itemGrp1;

	private String itemName;

	
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public int getItemGrp1() {
		return itemGrp1;
	}

	public void setItemGrp1(int itemGrp1) {
		this.itemGrp1 = itemGrp1;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	@Override
	public String toString() {
		return "GetOrderItemQty [orderId=" + orderId + ", qty=" + qty + ", itemId=" + itemId + ", menuId=" + menuId
				+ ", itemGrp1=" + itemGrp1 + ", itemName=" + itemName + "]";
	}

	 

	 
	
	

	
	
	
	
}
