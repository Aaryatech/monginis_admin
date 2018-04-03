package com.ats.adminpanel.model.RawMaterial;
 

public class RmRateVerification {

	private int rmRateVerId;
	 
	private int suppId;
	 
	private int rmId;
	 
	private String rateDate;
	 
	private float rateTaxExtra;
	 
	private float rateTaxIncl;
	 
	private String date1;
	 
	private float rateTaxExtra1;
	 
	private float rateTaxIncl1;
 
	private String date2;
	 
	private float rateTaxExtra2;
	 
	private float rateTaxIncl2;
	 
	private int taxId;
	 
/*	private float igstPer;*/

	public int getRmRateVerId() {
		return rmRateVerId;
	}

	public void setRmRateVerId(int rmRateVerId) {
		this.rmRateVerId = rmRateVerId;
	}

	public int getSuppId() {
		return suppId;
	}

	public void setSuppId(int suppId) {
		this.suppId = suppId;
	}

	public int getRmId() {
		return rmId;
	}

	public void setRmId(int rmId) {
		this.rmId = rmId;
	}

	public String getRateDate() {
		return rateDate;
	}

	public void setRateDate(String rateDate) {
		this.rateDate = rateDate;
	}

	public float getRateTaxExtra() {
		return rateTaxExtra;
	}

	public void setRateTaxExtra(float rateTaxExtra) {
		this.rateTaxExtra = rateTaxExtra;
	}

	public float getRateTaxIncl() {
		return rateTaxIncl;
	}

	public void setRateTaxIncl(float rateTaxIncl) {
		this.rateTaxIncl = rateTaxIncl;
	}

	public String getDate1() {
		return date1;
	}

	public void setDate1(String date1) {
		this.date1 = date1;
	}

	public float getRateTaxExtra1() {
		return rateTaxExtra1;
	}

	public void setRateTaxExtra1(float rateTaxExtra1) {
		this.rateTaxExtra1 = rateTaxExtra1;
	}

	public float getRateTaxIncl1() {
		return rateTaxIncl1;
	}

	public void setRateTaxIncl1(float rateTaxIncl1) {
		this.rateTaxIncl1 = rateTaxIncl1;
	}

	public String getDate2() {
		return date2;
	}

	public void setDate2(String date2) {
		this.date2 = date2;
	}

	public float getRateTaxExtra2() {
		return rateTaxExtra2;
	}

	public void setRateTaxExtra2(float rateTaxExtra2) {
		this.rateTaxExtra2 = rateTaxExtra2;
	}

	public float getRateTaxIncl2() {
		return rateTaxIncl2;
	}

	public void setRateTaxIncl2(float rateTaxIncl2) {
		this.rateTaxIncl2 = rateTaxIncl2;
	}

	public int getTaxId() {
		return taxId;
	}

	public void setTaxId(int taxId) {
		this.taxId = taxId;
	}

	@Override
	public String toString() {
		return "RmRateVerification [rmRateVerId=" + rmRateVerId + ", suppId=" + suppId + ", rmId=" + rmId
				+ ", rateDate=" + rateDate + ", rateTaxExtra=" + rateTaxExtra + ", rateTaxIncl=" + rateTaxIncl
				+ ", date1=" + date1 + ", rateTaxExtra1=" + rateTaxExtra1 + ", rateTaxIncl1=" + rateTaxIncl1
				+ ", date2=" + date2 + ", rateTaxExtra2=" + rateTaxExtra2 + ", rateTaxIncl2=" + rateTaxIncl2
				+ ", taxId=" + taxId + "]";
	}
 
	
}
