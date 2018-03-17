package com.ats.adminpanel.model.item;

public class ItemSup {

	private boolean error;
	
	private String message;

	private int id;

	private int itemId;
	
	private String itemHsncd;
	
	private String itemUom;
	
	private int uomId;
	
	private int trayType;
	
	private int noOfItemPerTray;
	
	private float actualWeight;
	
	private float baseWeight;
	
	private float inputPerQty;
	
	private int isGateSale;
	
	private int isGateSaleDisc;
	
	private int isAllowBday;
	
	private int delStatus;

	private int isTallySync;
	
	private int cutSection;
	
	private String shortName;
	
	
	public int getCutSection() {
		return cutSection;
	}

	public void setCutSection(int cutSection) {
		this.cutSection = cutSection;
	}

	public int getIsTallySync() {
		return isTallySync;
	}

	public void setIsTallySync(int isTallySync) {
		this.isTallySync = isTallySync;
	}

	public int getUomId() {
		return uomId;
	}

	public void setUomId(int uomId) {
		this.uomId = uomId;
	}

	public int getTrayType() {
		return trayType;
	}

	public void setTrayType(int trayType) {
		this.trayType = trayType;
	}

	public int getNoOfItemPerTray() {
		return noOfItemPerTray;
	}

	public void setNoOfItemPerTray(int noOfItemPerTray) {
		this.noOfItemPerTray = noOfItemPerTray;
	}

	public boolean getError() {
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

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	@Override
	public String toString() {
		return "ItemSup [error=" + error + ", message=" + message + ", id=" + id + ", itemId=" + itemId + ", itemHsncd="
				+ itemHsncd + ", itemUom=" + itemUom + ", uomId=" + uomId + ", trayType=" + trayType
				+ ", noOfItemPerTray=" + noOfItemPerTray + ", actualWeight=" + actualWeight + ", baseWeight="
				+ baseWeight + ", inputPerQty=" + inputPerQty + ", isGateSale=" + isGateSale + ", isGateSaleDisc="
				+ isGateSaleDisc + ", isAllowBday=" + isAllowBday + ", delStatus=" + delStatus + ", isTallySync="
				+ isTallySync + ", cutSection=" + cutSection + ", shortName=" + shortName + "]";
	}

	
}
