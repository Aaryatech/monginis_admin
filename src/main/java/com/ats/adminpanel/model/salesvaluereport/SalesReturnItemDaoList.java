package com.ats.adminpanel.model.salesvaluereport;

import java.util.List;

public class SalesReturnItemDaoList {

	private String month;
	private float monthTotal;

	private List<SalesReturnValueItemDao> salesReturnValueItemDao;

	private float totBillAmt;

	private float totGrnQty;

	private float totGvnQty;

	public float getMonthTotal() {
		return monthTotal;
	}

	public void setMonthTotal(float monthTotal) {
		this.monthTotal = monthTotal;
	}

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

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public List<SalesReturnValueItemDao> getSalesReturnValueItemDao() {
		return salesReturnValueItemDao;
	}

	public void setSalesReturnValueItemDao(List<SalesReturnValueItemDao> salesReturnValueItemDao) {
		this.salesReturnValueItemDao = salesReturnValueItemDao;
	}

	@Override
	public String toString() {
		return "SalesReturnItemDaoList [month=" + month + ", monthTotal=" + monthTotal + ", salesReturnValueItemDao="
				+ salesReturnValueItemDao + ", totBillAmt=" + totBillAmt + ", totGrnQty=" + totGrnQty + ", totGvnQty="
				+ totGvnQty + "]";
	}

}
