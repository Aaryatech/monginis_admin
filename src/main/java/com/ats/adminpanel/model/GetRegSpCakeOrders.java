package com.ats.adminpanel.model;

public class GetRegSpCakeOrders {

	
private int rspId;

	 
	private String frName;
	
	 
	private String frMob;
	 
	private String itemName;
	 
	private String orderDate;
	 
	private float rate;
	 
	private int qty;
	
 
	 
	private float rspSubTotal;
	 
	private float rspAdvanceAmt;
	 
	private float rspRemainingAmt; 
	private String rspDeliveryDt;
	
	private String rspPlace;
	  
	private String rspCustName;
	
	 
	private String rspCustMobileNo;


	public String getRspPlace() {
		return rspPlace;
	}


	public void setRspPlace(String rspPlace) {
		this.rspPlace = rspPlace;
	}


	public int getRspId() {
		return rspId;
	}


	public void setRspId(int rspId) {
		this.rspId = rspId;
	}


	public String getFrName() {
		return frName;
	}


	public void setFrName(String frName) {
		this.frName = frName;
	}


	public String getFrMob() {
		return frMob;
	}


	public void setFrMob(String frMob) {
		this.frMob = frMob;
	}


	public String getItemName() {
		return itemName;
	}


	public void setItemName(String itemName) {
		this.itemName = itemName;
	}


	public String getOrderDate() {
		return orderDate;
	}


	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}


	public float getRate() {
		return rate;
	}


	public void setRate(float rate) {
		this.rate = rate;
	}


	public int getQty() {
		return qty;
	}


	public void setQty(int qty) {
		this.qty = qty;
	}


	public float getRspSubTotal() {
		return rspSubTotal;
	}


	public void setRspSubTotal(float rspSubTotal) {
		this.rspSubTotal = rspSubTotal;
	}


	public float getRspAdvanceAmt() {
		return rspAdvanceAmt;
	}


	public void setRspAdvanceAmt(float rspAdvanceAmt) {
		this.rspAdvanceAmt = rspAdvanceAmt;
	}


	public float getRspRemainingAmt() {
		return rspRemainingAmt;
	}


	public void setRspRemainingAmt(float rspRemainingAmt) {
		this.rspRemainingAmt = rspRemainingAmt;
	}


	public String getRspDeliveryDt() {
		return rspDeliveryDt;
	}


	public void setRspDeliveryDt(String rspDeliveryDt) {
		this.rspDeliveryDt = rspDeliveryDt;
	}


	public String getRspCustName() {
		return rspCustName;
	}


	public void setRspCustName(String rspCustName) {
		this.rspCustName = rspCustName;
	}


	public String getRspCustMobileNo() {
		return rspCustMobileNo;
	}


	public void setRspCustMobileNo(String rspCustMobileNo) {
		this.rspCustMobileNo = rspCustMobileNo;
	}


	@Override
	public String toString() {
		return "GetRegSpCakeOrders [rspId=" + rspId + ", frName=" + frName + ", frMob=" + frMob + ", itemName="
				+ itemName + ", orderDate=" + orderDate + ", rate=" + rate + ", qty=" + qty + ", rspSubTotal="
				+ rspSubTotal + ", rspAdvanceAmt=" + rspAdvanceAmt + ", rspRemainingAmt=" + rspRemainingAmt
				+ ", rspDeliveryDt=" + rspDeliveryDt + ", rspCustName=" + rspCustName + ", rspCustMobileNo="
				+ rspCustMobileNo + "]";
	}

	
	
}
