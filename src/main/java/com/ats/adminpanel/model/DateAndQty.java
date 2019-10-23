package com.ats.adminpanel.model;
 

public class DateAndQty {
	
	private float qty;
	private String dlvDate;
	public float getQty() {
		return qty;
	}
	public void setQty(float qty) {
		this.qty = qty;
	}
	public String getDlvDate() {
		return dlvDate;
	}
	public void setDlvDate(String dlvDate) {
		this.dlvDate = dlvDate;
	}
	@Override
	public String toString() {
		return "DateAndQty [qty=" + qty + ", dlvDate=" + dlvDate + "]";
	}
	
	

}
