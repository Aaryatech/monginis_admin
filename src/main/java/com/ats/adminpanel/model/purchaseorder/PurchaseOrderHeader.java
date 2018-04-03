package com.ats.adminpanel.model.purchaseorder;

import java.util.List;
 

public class PurchaseOrderHeader {

private int poId;
	 
	private int poNo;
	 
	private String poDate;
	 
	private int poType;
	 
	private int suppId;
	 
	private int quotationRefNo;
	 
	private String quotationRefDate;
 
	private String kindAttn;
 
	private int payId;
 
	private int taxationRem;
 
	private int freidhtRem;
 
	private int insuRem;
	
 
	private String delvAtRem;
 
	private String delvDateRem;
 
	private int tranId;
 
	private String spRem;
 
	private int validity;
 
	private float poTotalValue;
 
	private int poStatus;
 
	private int userId;
 
	private int approvedId;
 
	private int delStatusId;
	
 
	List<PurchaseOrderDetail> purchaseOrderDetail;


	public int getPoId() {
		return poId;
	}


	public void setPoId(int poId) {
		this.poId = poId;
	}


	public int getPoNo() {
		return poNo;
	}


	public void setPoNo(int poNo) {
		this.poNo = poNo;
	}


	public String getPoDate() {
		return poDate;
	}


	public void setPoDate(String poDate) {
		this.poDate = poDate;
	}


	public int getPoType() {
		return poType;
	}


	public void setPoType(int poType) {
		this.poType = poType;
	}


	public int getSuppId() {
		return suppId;
	}


	public void setSuppId(int suppId) {
		this.suppId = suppId;
	}


	public int getQuotationRefNo() {
		return quotationRefNo;
	}


	public void setQuotationRefNo(int quotationRefNo) {
		this.quotationRefNo = quotationRefNo;
	}


	public String getQuotationRefDate() {
		return quotationRefDate;
	}


	public void setQuotationRefDate(String quotationRefDate) {
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


	public int getTaxationRem() {
		return taxationRem;
	}


	public void setTaxationRem(int taxationRem) {
		this.taxationRem = taxationRem;
	}


	public int getFreidhtRem() {
		return freidhtRem;
	}


	public void setFreidhtRem(int freidhtRem) {
		this.freidhtRem = freidhtRem;
	}


	public int getInsuRem() {
		return insuRem;
	}


	public void setInsuRem(int insuRem) {
		this.insuRem = insuRem;
	}


	public String getDelvAtRem() {
		return delvAtRem;
	}


	public void setDelvAtRem(String delvAtRem) {
		this.delvAtRem = delvAtRem;
	}


	public String getDelvDateRem() {
		return delvDateRem;
	}


	public void setDelvDateRem(String delvDateRem) {
		this.delvDateRem = delvDateRem;
	}


	public int getTranId() {
		return tranId;
	}


	public void setTranId(int tranId) {
		this.tranId = tranId;
	}


	public String getSpRem() {
		return spRem;
	}


	public void setSpRem(String spRem) {
		this.spRem = spRem;
	}


	public int getValidity() {
		return validity;
	}


	public void setValidity(int validity) {
		this.validity = validity;
	}


	public float getPoTotalValue() {
		return poTotalValue;
	}


	public void setPoTotalValue(float poTotalValue) {
		this.poTotalValue = poTotalValue;
	}


	public int getPoStatus() {
		return poStatus;
	}


	public void setPoStatus(int poStatus) {
		this.poStatus = poStatus;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public int getApprovedId() {
		return approvedId;
	}


	public void setApprovedId(int approvedId) {
		this.approvedId = approvedId;
	}


	public int getDelStatusId() {
		return delStatusId;
	}


	public void setDelStatusId(int delStatusId) {
		this.delStatusId = delStatusId;
	}


	public List<PurchaseOrderDetail> getPurchaseOrderDetail() {
		return purchaseOrderDetail;
	}


	public void setPurchaseOrderDetail(List<PurchaseOrderDetail> purchaseOrderDetail) {
		this.purchaseOrderDetail = purchaseOrderDetail;
	}


	@Override
	public String toString() {
		return "PurchaseOrderHeader [poId=" + poId + ", poNo=" + poNo + ", poDate=" + poDate + ", poType=" + poType
				+ ", suppId=" + suppId + ", quotationRefNo=" + quotationRefNo + ", quotationRefDate=" + quotationRefDate
				+ ", kindAttn=" + kindAttn + ", payId=" + payId + ", taxationRem=" + taxationRem + ", freidhtRem="
				+ freidhtRem + ", insuRem=" + insuRem + ", delvAtRem=" + delvAtRem + ", delvDateRem=" + delvDateRem
				+ ", tranId=" + tranId + ", spRem=" + spRem + ", validity=" + validity + ", poTotalValue="
				+ poTotalValue + ", poStatus=" + poStatus + ", userId=" + userId + ", approvedId=" + approvedId
				+ ", delStatusId=" + delStatusId + ", purchaseOrderDetail=" + purchaseOrderDetail + "]";
	}
	
	
}
