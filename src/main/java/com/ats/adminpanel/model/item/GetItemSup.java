package com.ats.adminpanel.model.item;


public class GetItemSup{

	private boolean error;
	
	private String message;
	
	private int id;

	private int itemId;
	
	private String itemName;
	
	private String itemHsncd;
	
	private String itemUom;
	
	private float actualWeight;
	
	private float baseWeight;
	
	private float inputPerQty;
	
	private int isGateSale;
	
	private int isGateSaleDisc;
	
	private int isAllowBday;
	
	private int delStatus;
	
	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemHsncd() {
		return itemHsncd;
	}

	public void setItemHsncd(String itemHsncd) {
		this.itemHsncd = itemHsncd;
	}

	public String getItemUom() {
		return itemUom;
	}

	public void setItemUom(String itemUom) {
		this.itemUom = itemUom;
	}

	public float getActualWeight() {
		return actualWeight;
	}

	public void setActualWeight(float actualWeight) {
		this.actualWeight = actualWeight;
	}

	public float getBaseWeight() {
		return baseWeight;
	}

	public void setBaseWeight(float baseWeight) {
		this.baseWeight = baseWeight;
	}

	public float getInputPerQty() {
		return inputPerQty;
	}

	public void setInputPerQty(float inputPerQty) {
		this.inputPerQty = inputPerQty;
	}

	public int getIsGateSale() {
		return isGateSale;
	}

	public void setIsGateSale(int isGateSale) {
		this.isGateSale = isGateSale;
	}

	public int getIsGateSaleDisc() {
		return isGateSaleDisc;
	}

	public void setIsGateSaleDisc(int isGateSaleDisc) {
		this.isGateSaleDisc = isGateSaleDisc;
	}

	public int getIsAllowBday() {
		return isAllowBday;
	}

	public void setIsAllowBday(int isAllowBday) {
		this.isAllowBday = isAllowBday;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	@Override
	public String toString() {
		return "GetItemSup [id=" + id + ", itemId=" + itemId + ", itemName=" + itemName + ", itemHsncd=" + itemHsncd
				+ ", itemUom=" + itemUom + ", actualWeight=" + actualWeight + ", baseWeight=" + baseWeight
				+ ", inputPerQty=" + inputPerQty + ", isGateSale=" + isGateSale + ", isGateSaleDisc=" + isGateSaleDisc
				+ ", isAllowBday=" + isAllowBday + ", delStatus=" + delStatus + "]";
	}
	
}
