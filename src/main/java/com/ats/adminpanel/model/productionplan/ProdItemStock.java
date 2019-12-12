package com.ats.adminpanel.model.productionplan;

public class ProdItemStock {

	private String uniqueId;
	private int id;
	private String itemName;
	private int itemGrp1;
	private int itemLimit;
	private int type;
	private int totFr;

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getItemGrp1() {
		return itemGrp1;
	}

	public void setItemGrp1(int itemGrp1) {
		this.itemGrp1 = itemGrp1;
	}

	public int getItemLimit() {
		return itemLimit;
	}

	public void setItemLimit(int itemLimit) {
		this.itemLimit = itemLimit;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getTotFr() {
		return totFr;
	}

	public void setTotFr(int totFr) {
		this.totFr = totFr;
	}

	@Override
	public String toString() {
		return "ProdItemStock [uniqueId=" + uniqueId + ", id=" + id + ", itemName=" + itemName + ", itemGrp1="
				+ itemGrp1 + ", itemLimit=" + itemLimit + ", type=" + type + ", totFr=" + totFr + "]";
	}

}
