package com.ats.adminpanel.model.franchisee;

public class Menu {
	private Integer menuId;
	private String menuTitle;
	private String menuDesc;
	private String menuImage;
	private String selectedMenuImage;
	private String isSameDayApplicable;
	private Integer mainCatId;
	private Integer delStatus;

	public Integer getMenuId() {
	return menuId;
	}

	public void setMenuId(Integer menuId) {
	this.menuId = menuId;
	}

	public String getMenuTitle() {
	return menuTitle;
	}

	public void setMenuTitle(String menuTitle) {
	this.menuTitle = menuTitle;
	}

	public String getMenuDesc() {
	return menuDesc;
	}

	public void setMenuDesc(String menuDesc) {
	this.menuDesc = menuDesc;
	}

	public String getMenuImage() {
	return menuImage;
	}

	public void setMenuImage(String menuImage) {
	this.menuImage = menuImage;
	}

	public String getSelectedMenuImage() {
	return selectedMenuImage;
	}

	public void setSelectedMenuImage(String selectedMenuImage) {
	this.selectedMenuImage = selectedMenuImage;
	}

	public String getIsSameDayApplicable() {
	return isSameDayApplicable;
	}

	public void setIsSameDayApplicable(String isSameDayApplicable) {
	this.isSameDayApplicable = isSameDayApplicable;
	}

	public Integer getMainCatId() {
	return mainCatId;
	}

	public void setMainCatId(Integer mainCatId) {
	this.mainCatId = mainCatId;
	}

	public Integer getDelStatus() {
	return delStatus;
	}

	public void setDelStatus(Integer delStatus) {
	this.delStatus = delStatus;
	}

	@Override
	public String toString() {
		return "Menu [menuId=" + menuId + ", menuTitle=" + menuTitle + ", menuDesc=" + menuDesc + ", menuImage="
				+ menuImage + ", selectedMenuImage=" + selectedMenuImage + ", isSameDayApplicable="
				+ isSameDayApplicable + ", mainCatId=" + mainCatId + ", delStatus=" + delStatus + "]";
	}


	
}
