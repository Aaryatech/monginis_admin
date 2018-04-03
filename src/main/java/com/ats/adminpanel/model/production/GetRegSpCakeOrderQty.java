package com.ats.adminpanel.model.production;

import java.sql.Date;

public class GetRegSpCakeOrderQty {

	

	int rspId;
	
	private int qty;
	
	private int itemId;
	
	private int menuId;
	
	private Date productionDate;
	
	private int itemGrp1;
	private String itemName;

	public int getRspId() {
		return rspId;
	}

	public void setRspId(int rspId) {
		this.rspId = rspId;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
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

	
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Date getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
	}

	@Override
	public String toString() {
		return "GetRegSpCakeOrderQty [rspId=" + rspId + ", qty=" + qty + ", itemId=" + itemId + ", menuId=" + menuId
				+ ", productionDate=" + productionDate + ", itemGrp1=" + itemGrp1 + ", itemName=" + itemName + "]";
	}

	 
	 
	
}
