package com.ats.adminpanel.model.logistics;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SparePart {
	 
	private int sprId;  
	private String sprName; 
	private int typeId; 
	private int groupId;  
	private String sprUom; 
	private String sprDate1; 
	private int sprRate1; 
	private String sprDate2; 
	private int sprRate2; 
	private String sprDate3; 
	private int sprRate3; 
	private int sprIscritical; 
	private int delStatus; 
	private int sprWarrantyPeriod; 
	private int makeId; 
	private int sprType; 
	private float cgst; 
	private float sgst; 
	private float igst; 
	private int disc; 
	private int extraCharges;
	public int getSprId() {
		return sprId;
	}
	public void setSprId(int sprId) {
		this.sprId = sprId;
	}
	public String getSprName() {
		return sprName;
	}
	public void setSprName(String sprName) {
		this.sprName = sprName;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getSprUom() {
		return sprUom;
	}
	public void setSprUom(String sprUom) {
		this.sprUom = sprUom;
	}
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public String getSprDate1() {
		return sprDate1;
	}
	public void setSprDate1(String sprDate1) {
		this.sprDate1 = sprDate1;
	}
	public int getSprRate1() {
		return sprRate1;
	}
	public void setSprRate1(int sprRate1) {
		this.sprRate1 = sprRate1;
	}
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public String getSprDate2() {
		return sprDate2;
	}
	public void setSprDate2(String sprDate2) {
		this.sprDate2 = sprDate2;
	}
	public int getSprRate2() {
		return sprRate2;
	}
	public void setSprRate2(int sprRate2) {
		this.sprRate2 = sprRate2;
	}
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public String getSprDate3() {
		return sprDate3;
	}
	public void setSprDate3(String sprDate3) {
		this.sprDate3 = sprDate3;
	}
	public int getSprRate3() {
		return sprRate3;
	}
	public void setSprRate3(int sprRate3) {
		this.sprRate3 = sprRate3;
	}
	public int getSprIscritical() {
		return sprIscritical;
	}
	public void setSprIscritical(int sprIscritical) {
		this.sprIscritical = sprIscritical;
	}
	public int getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}
	public int getSprWarrantyPeriod() {
		return sprWarrantyPeriod;
	}
	public void setSprWarrantyPeriod(int sprWarrantyPeriod) {
		this.sprWarrantyPeriod = sprWarrantyPeriod;
	}
	public int getMakeId() {
		return makeId;
	}
	public void setMakeId(int makeId) {
		this.makeId = makeId;
	}
	public int getSprType() {
		return sprType;
	}
	public void setSprType(int sprType) {
		this.sprType = sprType;
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
	public int getDisc() {
		return disc;
	}
	public void setDisc(int disc) {
		this.disc = disc;
	}
	public int getExtraCharges() {
		return extraCharges;
	}
	public void setExtraCharges(int extraCharges) {
		this.extraCharges = extraCharges;
	}
	@Override
	public String toString() {
		return "SparePart [sprId=" + sprId + ", sprName=" + sprName + ", typeId=" + typeId + ", groupId=" + groupId
				+ ", sprUom=" + sprUom + ", sprDate1=" + sprDate1 + ", sprRate1=" + sprRate1 + ", sprDate2=" + sprDate2
				+ ", sprRate2=" + sprRate2 + ", sprDate3=" + sprDate3 + ", sprRate3=" + sprRate3 + ", sprIscritical="
				+ sprIscritical + ", delStatus=" + delStatus + ", sprWarrantyPeriod=" + sprWarrantyPeriod + ", makeId="
				+ makeId + ", sprType=" + sprType + ", cgst=" + cgst + ", sgst=" + sgst + ", igst=" + igst + ", disc="
				+ disc + ", extraCharges=" + extraCharges + "]";
	}
	
	
	

}
