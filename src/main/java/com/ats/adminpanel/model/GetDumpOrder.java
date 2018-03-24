package com.ats.adminpanel.model;


import java.sql.Date;


import com.fasterxml.jackson.annotation.JsonFormat;

public class GetDumpOrder {
	
	
	
	private int orderId;
	
	//private Date orderDate;
	
	private int frId;
	
	private int menuId;
	
	private int orderQty;
	
	private String itemId;
	
	
	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	/*@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}*/

	public int getFrId() {
		return frId;
	}

	public void setFrId(int frId) {
		this.frId = frId;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public int getOrderQty() {
		return orderQty;
	}

	public void setOrderQty(int orderQty) {
		this.orderQty = orderQty;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	@Override
	public String toString() {
		return "GetDumpOrder [orderId=" + orderId + ", frId=" + frId + ", menuId=" + menuId + ", orderQty=" + orderQty
				+ ", itemId=" + itemId + "]";
	}

	
	
	

	
}
