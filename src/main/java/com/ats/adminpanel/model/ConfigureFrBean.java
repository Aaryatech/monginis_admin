
package com.ats.adminpanel.model;


public class ConfigureFrBean {

    private int settingId;
    private int frId;
    private int menuId;
    private int settingType;
    private String fromTime;
    private String toTime;
    private String day;
    private String date;
    private String itemShow;
    private Integer delStatus;
    private String frName;
    private String menuTitle;
    private String catName;
    private int catId;
    private int subCatId;
	public int getSettingId() {
		return settingId;
	}
	public void setSettingId(int settingId) {
		this.settingId = settingId;
	}
	public int getFrId() {
		return frId;
	}
	public void setFrId(int frId) {
		this.frId = frId;
	}
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public int getSettingType() {
		return settingType;
	}
	public void setSettingType(int settingType) {
		this.settingType = settingType;
	}
	public String getFromTime() {
		return fromTime;
	}
	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}
	public String getToTime() {
		return toTime;
	}
	public void setToTime(String toTime) {
		this.toTime = toTime;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getItemShow() {
		return itemShow;
	}
	public void setItemShow(String itemShow) {
		this.itemShow = itemShow;
	}
	public Integer getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(Integer delStatus) {
		this.delStatus = delStatus;
	}
	public String getFrName() {
		return frName;
	}
	public void setFrName(String frName) {
		this.frName = frName;
	}
	public String getMenuTitle() {
		return menuTitle;
	}
	public void setMenuTitle(String menuTitle) {
		this.menuTitle = menuTitle;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public int getCatId() {
		return catId;
	}
	public void setCatId(int catId) {
		this.catId = catId;
	}
	public int getSubCatId() {
		return subCatId;
	}
	public void setSubCatId(int subCatId) {
		this.subCatId = subCatId;
	}
	@Override
	public String toString() {
		return "ConfigureFrBean [settingId=" + settingId + ", frId=" + frId + ", menuId=" + menuId + ", settingType="
				+ settingType + ", fromTime=" + fromTime + ", toTime=" + toTime + ", day=" + day + ", date=" + date
				+ ", itemShow=" + itemShow + ", delStatus=" + delStatus + ", frName=" + frName + ", menuTitle="
				+ menuTitle + ", catName=" + catName + ", catId=" + catId + ", subCatId=" + subCatId + "]";
	}
}
