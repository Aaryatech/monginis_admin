package com.ats.adminpanel.model.item;


public class Item {

int id;

private String itemId;

private String itemName;

private int itemGrp1;

private int itemGrp2;

private int itemGrp3;

private float itemRate1;

private float itemRate2;

private float itemMrp1;

private float itemMrp2;

private float itemMrp3;




private String itemImage;

private float itemTax1;

private float itemTax2;

private float itemTax3;

private int itemIsUsed;

private float itemSortId;

private int grnTwo;

private int delStatus;




private int minQty;

private float itemRate3;

private int shelfLife;


public int getId() {
	return id;
}


public void setId(int id) {
	this.id = id;
}


public String getItemId() {
	return itemId;
}


public void setItemId(String itemId) {
	this.itemId = itemId;
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


public int getItemGrp2() {
	return itemGrp2;
}


public void setItemGrp2(int itemGrp2) {
	this.itemGrp2 = itemGrp2;
}


public int getItemGrp3() {
	return itemGrp3;
}


public void setItemGrp3(int itemGrp3) {
	this.itemGrp3 = itemGrp3;
}


public float getItemRate1() {
	return itemRate1;
}


public void setItemRate1(float itemRate1) {
	this.itemRate1 = itemRate1;
}


public float getItemRate2() {
	return itemRate2;
}


public void setItemRate2(float itemRate2) {
	this.itemRate2 = itemRate2;
}


public float getItemMrp1() {
	return itemMrp1;
}


public void setItemMrp1(float itemMrp1) {
	this.itemMrp1 = itemMrp1;
}


public float getItemMrp2() {
	return itemMrp2;
}


public void setItemMrp2(float itemMrp2) {
	this.itemMrp2 = itemMrp2;
}


public float getItemMrp3() {
	return itemMrp3;
}


public void setItemMrp3(float itemMrp3) {
	this.itemMrp3 = itemMrp3;
}


public String getItemImage() {
	return itemImage;
}


public void setItemImage(String itemImage) {
	this.itemImage = itemImage;
}


public float getItemTax1() {
	return itemTax1;
}


public void setItemTax1(float itemTax1) {
	this.itemTax1 = itemTax1;
}


public float getItemTax2() {
	return itemTax2;
}


public void setItemTax2(float itemTax2) {
	this.itemTax2 = itemTax2;
}


public float getItemTax3() {
	return itemTax3;
}


public void setItemTax3(float itemTax3) {
	this.itemTax3 = itemTax3;
}


public int getItemIsUsed() {
	return itemIsUsed;
}


public void setItemIsUsed(int itemIsUsed) {
	this.itemIsUsed = itemIsUsed;
}


public float getItemSortId() {
	return itemSortId;
}


public void setItemSortId(float itemSortId) {
	this.itemSortId = itemSortId;
}


public int getGrnTwo() {
	return grnTwo;
}


public void setGrnTwo(int grnTwo) {
	this.grnTwo = grnTwo;
}


public int getDelStatus() {
	return delStatus;
}


public void setDelStatus(int delStatus) {
	this.delStatus = delStatus;
}


public int getMinQty() {
	return minQty;
}


public void setMinQty(int minQty) {
	this.minQty = minQty;
}


public float getItemRate3() {
	return itemRate3;
}


public void setItemRate3(float itemRate3) {
	this.itemRate3 = itemRate3;
}


public int getShelfLife() {
	return shelfLife;
}


public void setShelfLife(int shelfLife) {
	this.shelfLife = shelfLife;
}


/*public int getShelfLife() {
	return shelfLife;
}

public void setShelfLife(int shelfLife) {
	this.shelfLife = shelfLife;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getItemId() {
	return itemId;
}

public void setItemId(String itemId) {
	this.itemId = itemId;
}

public String getItemName() {
	return itemName;
}

public void setItemName(String itemName) {
	this.itemName = itemName;
}

public String getItemGrp1() {
	return itemGrp1;
}

public void setItemGrp1(String itemGrp1) {
	this.itemGrp1 = itemGrp1;
}

public String getItemGrp2() {
	return itemGrp2;
}

public void setItemGrp2(String itemGrp2) {
	this.itemGrp2 = itemGrp2;
}

public int getItemGrp3() {
	return itemGrp3;
}

public void setItemGrp3(int itemGrp3) {
	this.itemGrp3 = itemGrp3;
}

public Integer getItemRate1() {
	return itemRate1;
}

public void setItemRate1(Integer itemRate1) {
	this.itemRate1 = itemRate1;
}

public Integer getItemRate2() {
	return itemRate2;
}

public void setItemRate2(Integer itemRate2) {
	this.itemRate2 = itemRate2;
}

public Integer getItemMrp1() {
	return itemMrp1;
}

public void setItemMrp1(Integer itemMrp1) {
	this.itemMrp1 = itemMrp1;
}

public Integer getItemMrp2() {
	return itemMrp2;
}

public void setItemMrp2(Integer itemMrp2) {
	this.itemMrp2 = itemMrp2;
}

public Integer getItemMrp3() {
	return itemMrp3;
}

public void setItemMrp3(Integer itemMrp3) {
	this.itemMrp3 = itemMrp3;
}

public String getItemImage() {
	return itemImage;
}

public void setItemImage(String itemImage) {
	this.itemImage = itemImage;
}

public Integer getItemTax1() {
	return itemTax1;
}

public void setItemTax1(Integer itemTax1) {
	this.itemTax1 = itemTax1;
}

public Integer getItemTax2() {
	return itemTax2;
}

public void setItemTax2(Integer itemTax2) {
	this.itemTax2 = itemTax2;
}

public Integer getItemTax3() {
	return itemTax3;
}

public void setItemTax3(Integer itemTax3) {
	this.itemTax3 = itemTax3;
}

public Integer getItemIsUsed() {
	return itemIsUsed;
}

public void setItemIsUsed(Integer itemIsUsed) {
	this.itemIsUsed = itemIsUsed;
}

public Double getItemSortId() {
	return itemSortId;
}

public void setItemSortId(Double itemSortId) {
	this.itemSortId = itemSortId;
}

public Integer getGrnTwo() {
	return grnTwo;
}

public void setGrnTwo(Integer grnTwo) {
	this.grnTwo = grnTwo;
}

public Integer getDelStatus() {
	return delStatus;
}

public void setDelStatus(Integer delStatus) {
	this.delStatus = delStatus;
}

public int getMinQty() {
	return minQty;
}

public void setMinQty(int minQty) {
	this.minQty = minQty;
}

public Integer getItemRate3() {
	return itemRate3;
}

public void setItemRate3(Integer itemRate3) {
	this.itemRate3 = itemRate3;
}
*/
@Override
public String toString() {
	return "Item [id=" + id + ", itemId=" + itemId + ", itemName=" + itemName + ", itemGrp1=" + itemGrp1 + ", itemGrp2="
			+ itemGrp2 + ", itemGrp3=" + itemGrp3 + ", itemRate1=" + itemRate1 + ", itemRate2=" + itemRate2
			+ ", itemMrp1=" + itemMrp1 + ", itemMrp2=" + itemMrp2 + ", itemMrp3=" + itemMrp3 + ", itemImage="
			+ itemImage + ", itemTax1=" + itemTax1 + ", itemTax2=" + itemTax2 + ", itemTax3=" + itemTax3
			+ ", itemIsUsed=" + itemIsUsed + ", itemSortId=" + itemSortId + ", grnTwo=" + grnTwo + ", delStatus="
			+ delStatus + ", minQty=" + minQty + ", itemRate3=" + itemRate3 + ", shelfLife=" + shelfLife + "]";
}


}










