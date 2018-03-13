package com.ats.adminpanel.model;

public class MaterialRecieptAcc {
	
	private int mrnDetailedId;
	private String item;
	private int rmId;
	private int incldTax;
	private float poRate;
	private int reciedvedQty;
	private int stockQty;
	private int rejectedQty;
	private float rateCal;
	private float value ;
	private float discPer;
	private float discAmt;
	private float cdPer;
	private float cdAmt;
	private float divFactor;
	private float insuAmt;
	private float freightAmt;
	private float other1;
	private float other2;
	private float other3;
	private float other4;
	private int taxId;
	private float gst;
	private float cgst;
	private float sgst;
	private float igst;
	private float cess;
	private float taxableAmt;
	private float cgstAmt;
	private float sgstAmt;
	private float igstAmt;
	private float cessAmt;
	private float varifiedRate;
	private float grandTotal;
	
	
	public float getVarifiedRate() {
		return varifiedRate;
	}
	public void setVarifiedRate(float varifiedRate) {
		this.varifiedRate = varifiedRate;
	}
	public int getStockQty() {
		return stockQty;
	}
	public void setStockQty(int stockQty) {
		this.stockQty = stockQty;
	}
	public int getRejectedQty() {
		return rejectedQty;
	}
	public void setRejectedQty(int rejectedQty) {
		this.rejectedQty = rejectedQty;
	}
	public float getOther3() {
		return other3;
	}
	public void setOther3(float other3) {
		this.other3 = other3;
	}
	public float getOther4() {
		return other4;
	}
	public void setOther4(float other4) {
		this.other4 = other4;
	}
	public int getTaxId() {
		return taxId;
	}
	public void setTaxId(int taxId) {
		this.taxId = taxId;
	}
	public int getRmId() {
		return rmId;
	}
	public void setRmId(int rmId) {
		this.rmId = rmId;
	}
	public int getMrnDetailedId() {
		return mrnDetailedId;
	}
	public void setMrnDetailedId(int mrnDetailedId) {
		this.mrnDetailedId = mrnDetailedId;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public int getIncldTax() {
		return incldTax;
	}
	public void setIncldTax(int incldTax) {
		this.incldTax = incldTax;
	}
	public float getPoRate() {
		return poRate;
	}
	public void setPoRate(float poRate) {
		this.poRate = poRate;
	}
	public int getReciedvedQty() {
		return reciedvedQty;
	}
	public void setReciedvedQty(int reciedvedQty) {
		this.reciedvedQty = reciedvedQty;
	}
	
	public float getRateCal() {
		return rateCal;
	}
	public void setRateCal(float rateCal) {
		this.rateCal = rateCal;
	}
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	public float getDiscPer() {
		return discPer;
	}
	public void setDiscPer(float discPer) {
		this.discPer = discPer;
	}
	public float getDiscAmt() {
		return discAmt;
	}
	public void setDiscAmt(float discAmt) {
		this.discAmt = discAmt;
	}
	public float getCdPer() {
		return cdPer;
	}
	public void setCdPer(float cdPer) {
		this.cdPer = cdPer;
	}
	public float getCdAmt() {
		return cdAmt;
	}
	public void setCdAmt(float cdAmt) {
		this.cdAmt = cdAmt;
	}
	public float getDivFactor() {
		return divFactor;
	}
	public void setDivFactor(float divFactor) {
		this.divFactor = divFactor;
	}
	public float getInsuAmt() {
		return insuAmt;
	}
	public void setInsuAmt(float insuAmt) {
		this.insuAmt = insuAmt;
	}
	public float getFreightAmt() {
		return freightAmt;
	}
	public void setFreightAmt(float freightAmt) {
		this.freightAmt = freightAmt;
	}
	public float getOther1() {
		return other1;
	}
	public void setOther1(float other1) {
		this.other1 = other1;
	}
	public float getOther2() {
		return other2;
	}
	public void setOther2(float other2) {
		this.other2 = other2;
	}
	public float getGst() {
		return gst;
	}
	public void setGst(float gst) {
		this.gst = gst;
	}
	public float getCgst() {
		return cgst;
	}
	public void setCgst(float cgst) {
		this.cgst = cgst;
	}
	public float getSgst() {
		return sgst;
	}
	public void setSgst(float sgst) {
		this.sgst = sgst;
	}
	public float getIgst() {
		return igst;
	}
	public void setIgst(float igst) {
		this.igst = igst;
	}
	public float getCess() {
		return cess;
	}
	public void setCess(float cess) {
		this.cess = cess;
	}
	public float getTaxableAmt() {
		return taxableAmt;
	}
	public void setTaxableAmt(float taxableAmt) {
		this.taxableAmt = taxableAmt;
	}
	public float getCgstAmt() {
		return cgstAmt;
	}
	public void setCgstAmt(float cgstAmt) {
		this.cgstAmt = cgstAmt;
	}
	public float getSgstAmt() {
		return sgstAmt;
	}
	public void setSgstAmt(float sgstAmt) {
		this.sgstAmt = sgstAmt;
	}
	public float getIgstAmt() {
		return igstAmt;
	}
	public void setIgstAmt(float igstAmt) {
		this.igstAmt = igstAmt;
	}
	public float getCessAmt() {
		return cessAmt;
	}
	public void setCessAmt(float cessAmt) {
		this.cessAmt = cessAmt;
	}
	
	public float getGrandTotal() {
		return grandTotal;
	}
	public void setGrandTotal(float grandTotal) {
		this.grandTotal = grandTotal;
	}
	@Override
	public String toString() {
		return "MaterialRecieptAcc [mrnDetailedId=" + mrnDetailedId + ", item=" + item + ", rmId=" + rmId
				+ ", incldTax=" + incldTax + ", poRate=" + poRate + ", reciedvedQty=" + reciedvedQty + ", stockQty="
				+ stockQty + ", rejectedQty=" + rejectedQty + ", rateCal=" + rateCal + ", value=" + value + ", discPer="
				+ discPer + ", discAmt=" + discAmt + ", cdPer=" + cdPer + ", cdAmt=" + cdAmt + ", divFactor="
				+ divFactor + ", insuAmt=" + insuAmt + ", freightAmt=" + freightAmt + ", other1=" + other1 + ", other2="
				+ other2 + ", other3=" + other3 + ", other4=" + other4 + ", taxId=" + taxId + ", gst=" + gst + ", cgst="
				+ cgst + ", sgst=" + sgst + ", igst=" + igst + ", cess=" + cess + ", taxableAmt=" + taxableAmt
				+ ", cgstAmt=" + cgstAmt + ", sgstAmt=" + sgstAmt + ", igstAmt=" + igstAmt + ", cessAmt=" + cessAmt
				+ ", varifiedRate=" + varifiedRate + ", grandTotal=" + grandTotal + "]";
	}
	
	
	
	
	
}
