package com.ats.adminpanel.model.materialreceipt;

import java.util.Date; 
import com.fasterxml.jackson.annotation.JsonFormat;

public class GetMaterialReceiptByDate {
	
	private int mrnId; 
	private String mrnNo; 
	private Date gateEntryDate;  
	private String vehicleNo; 
	private String lrNo; 
	private Date mrnStoreDate; 
	private int mrnType; 
	private int againstPo; 
	private String poNo; 
	private Date poDate; 
	private Date invBookDate; 
	private String invoiceNumber; 
	private float basicValue; 
	private float billAmount; 
	private String suppName; 
	private String tranName;
	private int status;
	
	
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getMrnId() {
		return mrnId;
	}
	public void setMrnId(int mrnId) {
		this.mrnId = mrnId;
	}
	public String getMrnNo() {
		return mrnNo;
	}
	public void setMrnNo(String mrnNo) {
		this.mrnNo = mrnNo;
	}
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getGateEntryDate() {
		return gateEntryDate;
	}
	public void setGateEntryDate(Date gateEntryDate) {
		this.gateEntryDate = gateEntryDate;
	}
	public String getVehicleNo() {
		return vehicleNo;
	}
	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}
	public String getLrNo() {
		return lrNo;
	}
	public void setLrNo(String lrNo) {
		this.lrNo = lrNo;
	}
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getMrnStoreDate() {
		return mrnStoreDate;
	}
	public void setMrnStoreDate(Date mrnStoreDate) {
		this.mrnStoreDate = mrnStoreDate;
	}
	public int getMrnType() {
		return mrnType;
	}
	public void setMrnType(int mrnType) {
		this.mrnType = mrnType;
	}
	public int getAgainstPo() {
		return againstPo;
	}
	public void setAgainstPo(int againstPo) {
		this.againstPo = againstPo;
	}
	public String getPoNo() {
		return poNo;
	}
	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getPoDate() {
		return poDate;
	}
	public void setPoDate(Date poDate) {
		this.poDate = poDate;
	}
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getInvBookDate() {
		return invBookDate;
	}
	public void setInvBookDate(Date invBookDate) {
		this.invBookDate = invBookDate;
	}
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public float getBasicValue() {
		return basicValue;
	}
	public void setBasicValue(float basicValue) {
		this.basicValue = basicValue;
	}
	public float getBillAmount() {
		return billAmount;
	}
	public void setBillAmount(float billAmount) {
		this.billAmount = billAmount;
	}
	public String getSuppName() {
		return suppName;
	}
	public void setSuppName(String suppName) {
		this.suppName = suppName;
	}
	public String getTranName() {
		return tranName;
	}
	public void setTranName(String tranName) {
		this.tranName = tranName;
	}
	@Override
	public String toString() {
		return "GetMaterialReceiptByDate [mrnId=" + mrnId + ", mrnNo=" + mrnNo + ", gateEntryDate=" + gateEntryDate
				+ ", vehicleNo=" + vehicleNo + ", lrNo=" + lrNo + ", mrnStoreDate=" + mrnStoreDate + ", mrnType="
				+ mrnType + ", againstPo=" + againstPo + ", poNo=" + poNo + ", poDate=" + poDate + ", invBookDate="
				+ invBookDate + ", invoiceNumber=" + invoiceNumber + ", basicValue=" + basicValue + ", billAmount="
				+ billAmount + ", suppName=" + suppName + ", tranName=" + tranName + ", status=" + status + "]";
	}
	
	

}
