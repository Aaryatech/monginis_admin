
package com.ats.adminpanel.model;

import java.io.Serializable;
import java.sql.Date;


public class FrMenu implements Serializable {

    private Integer settingId;
    private String fromTime;
    private String toTime;
    private String itemShow;
    private String menuDesc;
    private String menuImage;
    private String selectedMenuImage;
    private String menuTitle;
    private Integer settingType;
    private Integer frId;
    private Integer menuId;
    private Integer catId;
    private String time;
    private Integer isSameDayApplicable;
	private int day;
	private String date;

    
    public Integer getIsSameDayApplicable() {
		return isSameDayApplicable;
	}

	public void setIsSameDayApplicable(Integer isSameDayApplicable) {
		this.isSameDayApplicable = isSameDayApplicable;
	}

	public Integer getSettingId() {
        return settingId;
    }

    public void setSettingId(Integer settingId) {
        this.settingId = settingId;
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

    public String getMenuTitle() {
        return menuTitle;
    }

    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    public Integer getSettingType() {
        return settingType;
    }

    public void setSettingType(Integer settingType) {
        this.settingType = settingType;
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

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

	public String getSelectedMenuImage() {
		return selectedMenuImage;
	}

	public void setSelectedMenuImage(String selectedMenuImage) {
		this.selectedMenuImage = selectedMenuImage;
	}
	
	
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}


	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}


	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "FrMenu [settingId=" + settingId + ", fromTime=" + fromTime + ", toTime=" + toTime + ", itemShow="
				+ itemShow + ", menuDesc=" + menuDesc + ", menuImage=" + menuImage + ", selectedMenuImage="
				+ selectedMenuImage + ", menuTitle=" + menuTitle + ", settingType=" + settingType + ", frId=" + frId
				+ ", menuId=" + menuId + ", catId=" + catId + ", time=" + time + ", isSameDayApplicable="
				+ isSameDayApplicable + ", day=" + day + ", date=" + date + "]";
	}


}
