package com.ats.adminpanel.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat; 

public class GetPoHeaderForPdf {
	
	private int poId; 
	private String poNo; 
	private Date poDate;
	private int poType; 
	private String suppName; 
	private String suppAddr; 
	private String suppState; 
	private String suppGstin; 
	private String tranName;
	private int quotationRefNo; 
	private Date quotationRefDate; 
	private String kindAttn; 
	private int payId;  
	private String delvAtRem; 
	private Date delvDateRem; 
	private String spRem; 
	private float poTotalValue;
	List<GetPoDetailedForPdf> purchaseOrderDetail;
	
	
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getPoDate() {
		return poDate;
	}
	public void setPoDate(Date poDate) {
		this.poDate = poDate;
	}
	public String getTranName() {
		return tranName;
	}
	public void setTranName(String tranName) {
		this.tranName = tranName;
	}
	public int getPoId() {
		return poId;
	}
	public void setPoId(int poId) {
		this.poId = poId;
	}
	public String getPoNo() {
		return poNo;
	}
	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}
	public int getPoType() {
		return poType;
	}
	public void setPoType(int poType) {
		this.poType = poType;
	}
	public String getSuppName() {
		return suppName;
	}
	public void setSuppName(String suppName) {
		this.suppName = suppName;
	}
	public String getSuppAddr() {
		return suppAddr;
	}
	public void setSuppAddr(String suppAddr) {
		this.suppAddr = suppAddr;
	}
	public String getSuppState() {
		return suppState;
	}
	public void setSuppState(String suppState) {
		this.suppState = suppState;
	}
	public String getSuppGstin() {
		return suppGstin;
	}
	public void setSuppGstin(String suppGstin) {
		this.suppGstin = suppGstin;
	}
	public int getQuotationRefNo() {
		return quotationRefNo;
	}
	public void setQuotationRefNo(int quotationRefNo) {
		this.quotationRefNo = quotationRefNo;
	}
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getQuotationRefDate() {
		return quotationRefDate;
	}
	public void setQuotationRefDate(Date quotationRefDate) {
		this.quotationRefDate = quotationRefDate;
	}
	public String getKindAttn() {
		return kindAttn;
	}
	public void setKindAttn(String kindAttn) {
		this.kindAttn = kindAttn;
	}
	public int getPayId() {
		return payId;
	}
	public void setPayId(int payId) {
		this.payId = payId;
	}
	public String getDelvAtRem() {
		return delvAtRem;
	}
	public void setDelvAtRem(String delvAtRem) {
		this.delvAtRem = delvAtRem;
	}
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getDelvDateRem() {
		return delvDateRem;
	}
	public void setDelvDateRem(Date delvDateRem) {
		this.delvDateRem = delvDateRem;
	}
	public String getSpRem() {
		return spRem;
	}
	public void setSpRem(String spRem) {
		this.spRem = spRem;
	}
	public float getPoTotalValue() {
		return poTotalValue;
	}
	public void setPoTotalValue(float poTotalValue) {
		this.poTotalValue = poTotalValue;
	}
	
	
	public List<GetPoDetailedForPdf> getPurchaseOrderDetail() {
		return purchaseOrderDetail;
	}
	public void setPurchaseOrderDetail(List<GetPoDetailedForPdf> purchaseOrderDetail) {
		this.purchaseOrderDetail = purchaseOrderDetail;
	}
	@Override
	public String toString() {
		return "GetPoHeaderForPdf [poId=" + poId + ", poNo=" + poNo + ", poDate=" + poDate + ", poType=" + poType
				+ ", suppName=" + suppName + ", suppAddr=" + suppAddr + ", suppState=" + suppState + ", suppGstin="
				+ suppGstin + ", tranName=" + tranName + ", quotationRefNo=" + quotationRefNo + ", quotationRefDate="
				+ quotationRefDate + ", kindAttn=" + kindAttn + ", payId=" + payId + ", delvAtRem=" + delvAtRem
				+ ", delvDateRem=" + delvDateRem + ", spRem=" + spRem + ", poTotalValue=" + poTotalValue
				+ ", purchaseOrderDetail=" + purchaseOrderDetail + "]";
	}
	
	

}
