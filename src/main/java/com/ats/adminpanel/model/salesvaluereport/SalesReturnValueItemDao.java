package com.ats.adminpanel.model.salesvaluereport;

public class SalesReturnValueItemDao {

	private String id;

	private int itemId;

	private float grandTotal;

	private float grnQty;

	private float gvnQty;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public float getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(float grandTotal) {
		this.grandTotal = grandTotal;
	}

	public float getGrnQty() {
		return grnQty;
	}

	public void setGrnQty(float grnQty) {
		this.grnQty = grnQty;
	}

	public float getGvnQty() {
		return gvnQty;
	}

	public void setGvnQty(float gvnQty) {
		this.gvnQty = gvnQty;
	}

	@Override
	public String toString() {
		return "SalesReturnValueItemDao [id=" + id + ", itemId=" + itemId + ", grandTotal=" + grandTotal + ", grnQty="
				+ grnQty + ", gvnQty=" + gvnQty + "]";
	}

}
