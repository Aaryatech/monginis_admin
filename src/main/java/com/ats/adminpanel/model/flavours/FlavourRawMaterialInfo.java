package com.ats.adminpanel.model.flavours;

public class FlavourRawMaterialInfo {

	private int flavourDetailId;
	private int flavourId;
	private String rmName;
	private int ttlQty;
	private int rmQty;
	private int weight;
	private int rmType;
	
	public int getFlavourDetailId() {
		return flavourDetailId;
	}
	public void setFlavourDetailId(int flavourDetailId) {
		this.flavourDetailId = flavourDetailId;
	}
	public int getFlavourId() {
		return flavourId;
	}
	public void setFlavourId(int flavourId) {
		this.flavourId = flavourId;
	}
	public String getRmName() {
		return rmName;
	}
	public void setRmName(String rmName) {
		this.rmName = rmName;
	}
	public int getTtlQty() {
		return ttlQty;
	}
	public void setTtlQty(int ttlQty) {
		this.ttlQty = ttlQty;
	}
	public int getRmQty() {
		return rmQty;
	}
	public void setRmQty(int rmQty) {
		this.rmQty = rmQty;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getRmType() {
		return rmType;
	}
	public void setRmType(int rmType) {
		this.rmType = rmType;
	}
	@Override
	public String toString() {
		return "FlavourRawMaterialInfo [flavourDetailId=" + flavourDetailId + ", flavourId=" + flavourId + ", rmName="
				+ rmName + ", ttlQty=" + ttlQty + ", rmQty=" + rmQty + ", weight=" + weight + ", rmType=" + rmType
				+ "]";
	}
	
}
