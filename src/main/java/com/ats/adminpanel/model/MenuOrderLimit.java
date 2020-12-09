package com.ats.adminpanel.model;

public class MenuOrderLimit {

	private String id;
	private int menuId;
	private int catId;
	private String menuTitle;
	private int subCatId;
	private String subCatName;
	private int qtyLimit;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public int getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	public String getMenuTitle() {
		return menuTitle;
	}

	public void setMenuTitle(String menuTitle) {
		this.menuTitle = menuTitle;
	}

	public int getSubCatId() {
		return subCatId;
	}

	public void setSubCatId(int subCatId) {
		this.subCatId = subCatId;
	}

	public String getSubCatName() {
		return subCatName;
	}

	public void setSubCatName(String subCatName) {
		this.subCatName = subCatName;
	}

	public int getQtyLimit() {
		return qtyLimit;
	}

	public void setQtyLimit(int qtyLimit) {
		this.qtyLimit = qtyLimit;
	}

	@Override
	public String toString() {
		return "MenuOrderLimit [id=" + id + ", menuId=" + menuId + ", catId=" + catId + ", menuTitle=" + menuTitle
				+ ", subCatId=" + subCatId + ", subCatName=" + subCatName + ", qtyLimit=" + qtyLimit + "]";
	}

	
}
