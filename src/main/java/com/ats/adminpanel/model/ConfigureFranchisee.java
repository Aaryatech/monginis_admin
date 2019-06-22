package com.ats.adminpanel.model;


public class ConfigureFranchisee {

	private int settingId;
	
	private int frId;

	private int menuId;
	
	private int catId;
	
	private int subCatId;
	
	private int settingType;
	
	private String fromTime;

	private String toTime;

	private String day;
	
	private String date;
	
	private String itemShow;

	private int delStatus;
	
	public int getSettingId() {
		return settingId;
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
	
	public String getItemShow() {
		return itemShow;
	}
	public void setItemShow(String itemShow) {
		this.itemShow = itemShow;
	}
	public int getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	@Override
	public String toString() {
		return "ConfigureFranchisee [settingId=" + settingId + ", frId=" + frId + ", menuId=" + menuId + ", catId="
				+ catId + ", subCatId=" + subCatId + ", settingType=" + settingType + ", fromTime=" + fromTime
				+ ", toTime=" + toTime + ", day=" + day + ", date=" + date + ", itemShow=" + itemShow + ", delStatus="
				+ delStatus + "]";
	}
	
		
}
