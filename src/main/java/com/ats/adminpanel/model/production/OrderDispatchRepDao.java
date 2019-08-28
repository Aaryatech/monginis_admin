package com.ats.adminpanel.model.production;

public class OrderDispatchRepDao {

	private int id;
	
	private String itemName;
	
	private float opTotal;
	
	private float orderQty;

	public int getId() {
		return id;
	}

	public String getItemName() {
		return itemName;
	}

	public float getOpTotal() {
		return opTotal;
	}

	public float getOrderQty() {
		return orderQty;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setOpTotal(float opTotal) {
		this.opTotal = opTotal;
	}

	public void setOrderQty(float orderQty) {
		this.orderQty = orderQty;
	}

	@Override
	public String toString() {
		return "OrderDispatchRepDao [id=" + id + ", itemName=" + itemName + ", opTotal=" + opTotal + ", orderQty="
				+ orderQty + "]";
	}

}
