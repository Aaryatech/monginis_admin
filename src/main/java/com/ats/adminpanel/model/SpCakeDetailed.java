package com.ats.adminpanel.model;



public class SpCakeDetailed {
	
	
	private int spCakeDetailedId;
	private int spId;
	private int rmType;
	private int rmId;
	private String rmName;
	private int rmUomId;
	private float rmQty;
	private float rmWeight;
	private float noOfPiecesPerItem;
	private int delStatus;

	
	
	
	
	

	public int getSpCakeDetailedId() {
		return spCakeDetailedId;
	}
	public void setSpCakeDetailedId(int spCakeDetailedId) {
		this.spCakeDetailedId = spCakeDetailedId;
	}
	public int getSpId() {
		return spId;
	}
	public void setSpId(int spId) {
		this.spId = spId;
	}
	public int getRmType() {
		return rmType;
	}
	public void setRmType(int rmType) {
		this.rmType = rmType;
	}
	public int getRmId() {
		return rmId;
	}
	public void setRmId(int rmId) {
		this.rmId = rmId;
	}
	public String getRmName() {
		return rmName;
	}
	public void setRmName(String rmName) {
		this.rmName = rmName;
	}
	public int getRmUomId() {
		return rmUomId;
	}
	public void setRmUomId(int rmUomId) {
		this.rmUomId = rmUomId;
	}
	public float getRmQty() {
		return rmQty;
	}
	public void setRmQty(float rmQty) {
		this.rmQty = rmQty;
	}
	public float getRmWeight() {
		return rmWeight;
	}
	public void setRmWeight(float rmWeight) {
		this.rmWeight = rmWeight;
	}
	public float getNoOfPiecesPerItem() {
		return noOfPiecesPerItem;
	}
	public void setNoOfPiecesPerItem(float noOfPiecesPerItem) {
		this.noOfPiecesPerItem = noOfPiecesPerItem;
	}
	public int getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}
	@Override
	public String toString() {
		return "SpCakeDetailed [spCakeDetailedId=" + spCakeDetailedId + ", spId=" + spId + ", rmType=" + rmType
				+ ", rmId=" + rmId + ", rmName=" + rmName + ", rmUomId=" + rmUomId + ", rmQty=" + rmQty + ", rmWeight="
				+ rmWeight + ", noOfPiecesPerItem=" + noOfPiecesPerItem + ", delStatus=" + delStatus + "]";
	}
	
	
	
	

}
