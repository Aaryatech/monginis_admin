package com.ats.adminpanel.model.salesvaluereport;

import java.util.List;

public class SalesReturnQtyReportList {
	
	private String month;
	
	private List<SalesReturnQtyDao> salesReturnQtyDaoList;
	
	private float totBillQty;
	
	private float totGrnQty;
	
	private float totGvnQty;
	

	public float getTotBillQty() {
		return totBillQty;
	}

	public void setTotBillQty(float totBillQty) {
		this.totBillQty = totBillQty;
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

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public List<SalesReturnQtyDao> getSalesReturnQtyDaoList() {
		return salesReturnQtyDaoList;
	}

	public void setSalesReturnQtyDaoList(List<SalesReturnQtyDao> salesReturnQtyDaoList) {
		this.salesReturnQtyDaoList = salesReturnQtyDaoList;
	}

	@Override
	public String toString() {
		return "SalesReturnQtyReportList [month=" + month + ", salesReturnQtyDaoList=" + salesReturnQtyDaoList
				+ ", totBillQty=" + totBillQty + ", totGrnQty=" + totGrnQty + ", totGvnQty=" + totGvnQty + "]";
	}

    

}
