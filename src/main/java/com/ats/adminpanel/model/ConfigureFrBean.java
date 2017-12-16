
package com.ats.adminpanel.model;


public class ConfigureFrBean {

    private Integer settingId;
    private Integer frId;
    private Integer menuId;
    private Integer settingType;
    private String fromTime;
    private String toTime;
    private Integer day;
    private String date;
    private String itemShow;
    private Integer delStatus;
    private String frName;
    private String menuTitle;
    private String catName;
    private int catId;
    private int subCatId;

    
    
    public int getSubCatId() {
		return subCatId;
	}

	public void setSubCatId(int subCatId) {
		this.subCatId = subCatId;
	}

	public int getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	public Integer getSettingId() {
        return settingId;
    }

    public void setSettingId(Integer settingId) {
        this.settingId = settingId;
    }

    public Integer getFrId() {
        return frId;
    }

    public void setFrId(Integer frId) {
        this.frId = frId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getSettingType() {
        return settingType;
    }

    public void setSettingType(Integer settingType) {
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

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
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

	@Override
	public String toString() {
		return "ConfigureFrBean [settingId=" + settingId + ", frId=" + frId + ", menuId=" + menuId + ", settingType="
				+ settingType + ", fromTime=" + fromTime + ", toTime=" + toTime + ", day=" + day + ", date=" + date
				+ ", itemShow=" + itemShow + ", delStatus=" + delStatus + ", frName=" + frName + ", menuTitle="
				+ menuTitle + ", catName=" + catName + "]";
	}

    
    
    
}
