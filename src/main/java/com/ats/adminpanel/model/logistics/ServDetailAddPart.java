package com.ats.adminpanel.model.logistics;

import java.util.Date;

public class ServDetailAddPart {
	
	private int servDetailId;  
	private int servId; 
	private String servDate;
	private int servType;   
	private int groupId; 
	private String groupName;
	private int vehId; 
	private String vehName;
	private int sprId; 
	private String partName;
	private int sprQty; 
	private int sprRate; 
	private float sprTaxableAmt; 
	private float sprTaxAmt;   
	private float sprTaxAmtPer;
	private float total;
	private float discPer; 
	private float disc; 
	private float extraCharges;
	private float extraChargesPer;
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
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public int getSprId() {
		return sprId;
	}
	public void setSprId(int sprId) {
		this.sprId = sprId;
	}
	public String getPartName() {
		return partName;
	}
	public void setPartName(String partName) {
		this.partName = partName;
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
	 
	public int getVehId() {
		return vehId;
	}
	public void setVehId(int vehId) {
		this.vehId = vehId;
	}
	public String getVehName() {
		return vehName;
	}
	public void setVehName(String vehName) {
		this.vehName = vehName;
	}
	
	
	public float getSprTaxAmtPer() {
		return sprTaxAmtPer;
	}
	public void setSprTaxAmtPer(float sprTaxAmtPer) {
		this.sprTaxAmtPer = sprTaxAmtPer;
	}
	public float getDiscPer() {
		return discPer;
	}
	public void setDiscPer(float discPer) {
		this.discPer = discPer;
	}
	public float getExtraChargesPer() {
		return extraChargesPer;
	}
	public void setExtraChargesPer(float extraChargesPer) {
		this.extraChargesPer = extraChargesPer;
	}
	@Override
	public String toString() {
		return "ServDetailAddPart [servDetailId=" + servDetailId + ", servId=" + servId + ", servDate=" + servDate
				+ ", servType=" + servType + ", groupId=" + groupId + ", groupName=" + groupName + ", vehId=" + vehId
				+ ", vehName=" + vehName + ", sprId=" + sprId + ", partName=" + partName + ", sprQty=" + sprQty
				+ ", sprRate=" + sprRate + ", sprTaxableAmt=" + sprTaxableAmt + ", sprTaxAmt=" + sprTaxAmt
				+ ", sprTaxAmtPer=" + sprTaxAmtPer + ", total=" + total + ", discPer=" + discPer + ", disc=" + disc
				+ ", extraCharges=" + extraCharges + ", extraChargesPer=" + extraChargesPer + ", delStatus=" + delStatus
				+ "]";
	}
	
	
	

}
