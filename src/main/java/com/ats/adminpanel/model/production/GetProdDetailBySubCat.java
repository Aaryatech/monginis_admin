package com.ats.adminpanel.model.production;


public class GetProdDetailBySubCat {
	

	private int subCatId;
	
	
	private String subCateName;

	private int itemGrp2;
	
	int totalQty;

	public int getSubCatId() {
		return subCatId;
	}

	public void setSubCatId(int subCatId) {
		this.subCatId = subCatId;
	}

	public String getSubCateName() {
		return subCateName;
	}

	public void setSubCateName(String subCateName) {
		this.subCateName = subCateName;
	}

	public int getItemGrp2() {
		return itemGrp2;
	}

	public void setItemGrp2(int itemGrp2) {
		this.itemGrp2 = itemGrp2;
	}

	public int getTotalQty() {
		return totalQty;
	}

	public void setTotalQty(int totalQty) {
		this.totalQty = totalQty;
	}

	@Override
	public String toString() {
		return "GetProdDetailBySubCat [subCatId=" + subCatId + ", subCateName=" + subCateName + ", itemGrp2=" + itemGrp2
				+ ", totalQty=" + totalQty + "]";
	}
	

}
