package com.ats.adminpanel.model;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SalesVoucher {
	 
	private int billDetailNo; 
	private int billNo; 
	private String vNo; 
	private String date; 
	private String vType; 
	private String partyName; 
	private String gstin; 
	private String state; 
	private String itemName; 
	private String hsnCode; 
	private int qty; 
	private String uom; 
	private float rate; 
	private float amount; 
	private float sgstPer; 
	private float sgstRs; 
	private float cgstPer; 
	private float cgstRs; 
	private float igstPer; 
	private float igstRs; 
	private float cessPer; 
	private float cessRs; 
	private float itemDiscPer; 
	private float totalDisc; 
	private float roundOff; 
	private float totalAmt; 
	private float billTotal; 
	private String remark;
  

	public int getBillDetailNo() {
		return billDetailNo;
	}

	public void setBillDetailNo(int billDetailNo) {
		this.billDetailNo = billDetailNo;
	}

	public int getBillNo() {
		return billNo;
	}

	public void setBillNo(int billNo) {
		this.billNo = billNo;
	}
 
	 public String getvNo() {
		return vNo;
	}

	public void setvNo(String vNo) {
		this.vNo = vNo;
	}

	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getvType() {
		return vType;
	}

	public void setvType(String vType) {
		this.vType = vType;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public String getGstin() {
		return gstin;
	}

	public void setGstin(String gstin) {
		this.gstin = gstin;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getHsnCode() {
		return hsnCode;
	}

	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public float getSgstPer() {
		return sgstPer;
	}

	public void setSgstPer(float sgstPer) {
		this.sgstPer = sgstPer;
	}

	public float getSgstRs() {
		return sgstRs;
	}

	public void setSgstRs(float sgstRs) {
		this.sgstRs = sgstRs;
	}

	public float getCgstPer() {
		return cgstPer;
	}

	public void setCgstPer(float cgstPer) {
		this.cgstPer = cgstPer;
	}

	public float getCgstRs() {
		return cgstRs;
	}

	public void setCgstRs(float cgstRs) {
		this.cgstRs = cgstRs;
	}

	public float getIgstPer() {
		return igstPer;
	}

	public void setIgstPer(float igstPer) {
		this.igstPer = igstPer;
	}

	public float getIgstRs() {
		return igstRs;
	}

	public void setIgstRs(float igstRs) {
		this.igstRs = igstRs;
	}

	public float getCessPer() {
		return cessPer;
	}

	public void setCessPer(float cessPer) {
		this.cessPer = cessPer;
	}

	public float getCessRs() {
		return cessRs;
	}

	public void setCessRs(float cessRs) {
		this.cessRs = cessRs;
	}

	public float getItemDiscPer() {
		return itemDiscPer;
	}

	public void setItemDiscPer(float itemDiscPer) {
		this.itemDiscPer = itemDiscPer;
	}

	public float getTotalDisc() {
		return totalDisc;
	}

	public void setTotalDisc(float totalDisc) {
		this.totalDisc = totalDisc;
	}

	public float getRoundOff() {
		return roundOff;
	}

	public void setRoundOff(float roundOff) {
		this.roundOff = roundOff;
	}

	public float getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(float totalAmt) {
		this.totalAmt = totalAmt;
	}

	public float getBillTotal() {
		return billTotal;
	}

	public void setBillTotal(float billTotal) {
		this.billTotal = billTotal;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "SalesVoucher [billDetailNo=" + billDetailNo + ", billNo=" + billNo + ", vNo=" + vNo + ", date=" + date
				+ ", vType=" + vType + ", partyName=" + partyName + ", gstin=" + gstin + ", state=" + state
				+ ", itemName=" + itemName + ", hsnCode=" + hsnCode + ", qty=" + qty + ", uom=" + uom + ", rate=" + rate
				+ ", amount=" + amount + ", sgstPer=" + sgstPer + ", sgstRs=" + sgstRs + ", cgstPer=" + cgstPer
				+ ", cgstRs=" + cgstRs + ", igstPer=" + igstPer + ", igstRs=" + igstRs + ", cessPer=" + cessPer
				+ ", cessRs=" + cessRs + ", itemDiscPer=" + itemDiscPer + ", totalDisc=" + totalDisc + ", roundOff="
				+ roundOff + ", totalAmt=" + totalAmt + ", billTotal=" + billTotal + ", remark=" + remark + "]";
	}
	
	

}