package com.ats.adminpanel.model.salesvaluereport;

import java.util.List;

public class SalesReturnValueDaoList {

	private String month;
	private float monthTotal;

	private List<SalesReturnValueDao> salesReturnQtyValueList;
	
	private float totBillAmt;
	
	private float totGrnQty;
	
	private float totGvnQty;
	
	

	public float getTotBillAmt() {
		return totBillAmt;
	}

	public void setTotBillAmt(float totBillAmt) {
		this.totBillAmt = totBillAmt;
	}

	public float getTotGrnQty() {
		return totGrnQty;
	}

	public void setTotGrnQty(float totGrnQty) {
		this.totGrnQty = totGrnQty;
	}

	public float getTotGvnQty() {
		return totGvnQty;
	}

	public void setTotGvnQty(float totGvnQty) {
		this.totGvnQty = totGvnQty;
	}

	public float getMonthTotal() {
		return monthTotal;
	}

	public void setMonthTotal(float monthTotal) {
		this.monthTotal = monthTotal;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public List<SalesReturnValueDao> getSalesReturnQtyValueList() {
		return salesReturnQtyValueList;
	}

	public void setSalesReturnQtyValueList(List<SalesReturnValueDao> salesReturnQtyValueList) {
		this.salesReturnQtyValueList = salesReturnQtyValueList;
	}

	@Override
	public String toString() {
		return "SalesReturnValueDaoList [month=" + month + ", monthTotal=" + monthTotal + ", salesReturnQtyValueList="
				+ salesReturnQtyValueList + ", totBillAmt=" + totBillAmt + ", totGrnQty=" + totGrnQty + ", totGvnQty="
				+ totGvnQty + "]";
	}

	

}
