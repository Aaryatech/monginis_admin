package com.ats.adminpanel.model.salesreport;

public class SalesReportItemwise {

	private int id;

	private String itemHsncd;

	private float itemTax1;

	private float itemTax2;

	private float itemTax3;

	private String itemName;

	float taxableAmtSum;
	float sgstRsSum;
	float cgstRsSum;
	float igstRsSum;
	int billQtySum;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getItemHsncd() {
		return itemHsncd;
	}

	public void setItemHsncd(String itemHsncd) {
		this.itemHsncd = itemHsncd;
	}

	public float getItemTax1() {
		return itemTax1;
	}

	public void setItemTax1(float itemTax1) {
		this.itemTax1 = itemTax1;
	}

	public float getItemTax2() {
		return itemTax2;
	}

	public void setItemTax2(float itemTax2) {
		this.itemTax2 = itemTax2;
	}

	public float getItemTax3() {
		return itemTax3;
	}

	public void setItemTax3(float itemTax3) {
		this.itemTax3 = itemTax3;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public float getTaxableAmtSum() {
		return taxableAmtSum;
	}

	public void setTaxableAmtSum(float taxableAmtSum) {
		this.taxableAmtSum = taxableAmtSum;
	}

	public float getSgstRsSum() {
		return sgstRsSum;
	}

	public void setSgstRsSum(float sgstRsSum) {
		this.sgstRsSum = sgstRsSum;
	}

	public float getCgstRsSum() {
		return cgstRsSum;
	}

	public void setCgstRsSum(float cgstRsSum) {
		this.cgstRsSum = cgstRsSum;
	}

	public float getIgstRsSum() {
		return igstRsSum;
	}

	public void setIgstRsSum(float igstRsSum) {
		this.igstRsSum = igstRsSum;
	}

	public int getBillQtySum() {
		return billQtySum;
	}

	public void setBillQtySum(int billQtySum) {
		this.billQtySum = billQtySum;
	}

	@Override
	public String toString() {
		return "SalesReportItemwise [id=" + id + ", itemHsncd=" + itemHsncd + ", itemTax1=" + itemTax1 + ", itemTax2="
				+ itemTax2 + ", itemTax3=" + itemTax3 + ", itemName=" + itemName + ", taxableAmtSum=" + taxableAmtSum
				+ ", sgstRsSum=" + sgstRsSum + ", cgstRsSum=" + cgstRsSum + ", igstRsSum=" + igstRsSum + ", billQtySum="
				+ billQtySum + "]";
	}

}
