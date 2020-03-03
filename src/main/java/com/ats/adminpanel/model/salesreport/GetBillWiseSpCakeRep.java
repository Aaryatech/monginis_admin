package com.ats.adminpanel.model.salesreport;

public class GetBillWiseSpCakeRep {
	private String id;
	private String invoiceNo;
	private String billDate;
	private String billNo;
	private int qty;
	private float grandTotal;
	private int itemId;
	private int frId;
	private String itemName;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public String getBillDate() {
		return billDate;
	}
	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public float getGrandTotal() {
		return grandTotal;
	}
	public void setGrandTotal(float grandTotal) {
		this.grandTotal = grandTotal;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getFrId() {
		return frId;
	}
	public void setFrId(int frId) {
		this.frId = frId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	@Override
	public String toString() {
		return "GetBillWiseSpCakeRep [id=" + id + ", invoiceNo=" + invoiceNo + ", billDate=" + billDate + ", billNo="
				+ billNo + ", qty=" + qty + ", grandTotal=" + grandTotal + ", itemId=" + itemId + ", frId=" + frId
				+ ", itemName=" + itemName + "]";
	}
	
	
}
