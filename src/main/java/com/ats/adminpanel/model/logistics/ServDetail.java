package com.ats.adminpanel.model.logistics;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat; 

public class ServDetail {
	
	 
	private int servDetailId;  
	private int servId; 
	private String servDate; 
	private int servType;   
	private int groupId; 
	private int sprId; 
	private int sprQty; 
	private int sprRate; 
	private float sprTaxableAmt; 
	private float sprTaxAmt;   
	private float total; 
	private float disc; 
	private float extraCharges; 
	private int delStatus;
	public int getServDetailId() {
		return servDetailId;
	}
	public void setServDetailId(int servDetailId) {
		this.servDetailId = servDetailId;
	}
	public int getServId() {
		return servId;
	}
	public void setServId(int servId) {
		this.servId = servId;
	}
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public String getServDate() {
		return servDate;
	}
	public void setServDate(String servDate) {
		this.servDate = servDate;
	}
	public int getServType() {
		return servType;
	}
	public void setServType(int servType) {
		this.servType = servType;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public int getSprId() {
		return sprId;
	}
	public void setSprId(int sprId) {
		this.sprId = sprId;
	}
	public int getSprQty() {
		return sprQty;
	}
	public void setSprQty(int sprQty) {
		this.sprQty = sprQty;
	}
	public int getSprRate() {
		return sprRate;
	}
	public void setSprRate(int sprRate) {
		this.sprRate = sprRate;
	}
	public float getSprTaxableAmt() {
		return sprTaxableAmt;
	}
	public void setSprTaxableAmt(float sprTaxableAmt) {
		this.sprTaxableAmt = sprTaxableAmt;
	}
	public float getSprTaxAmt() {
		return sprTaxAmt;
	}
	public void setSprTaxAmt(float sprTaxAmt) {
		this.sprTaxAmt = sprTaxAmt;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public float getDisc() {
		return disc;
	}
	public void setDisc(float disc) {
		this.disc = disc;
	}
	public float getExtraCharges() {
		return extraCharges;
	}
	public void setExtraCharges(float extraCharges) {
		this.extraCharges = extraCharges;
	}
	public int getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}
	@Override
	public String toString() {
		return "ServDetail [servDetailId=" + servDetailId + ", servId=" + servId + ", servDate=" + servDate
				+ ", servType=" + servType + ", groupId=" + groupId + ", sprId=" + sprId + ", sprQty=" + sprQty
				+ ", sprRate=" + sprRate + ", sprTaxableAmt=" + sprTaxableAmt + ", sprTaxAmt=" + sprTaxAmt + ", total="
				+ total + ", disc=" + disc + ", extraCharges=" + extraCharges + ", delStatus=" + delStatus + "]";
	}
	
	

}
