package com.ats.adminpanel.model.masters;

public class Rate {


private int sprId;

private String sprName;

private int sprRate;

private int sprAddOnRate;

private int delStatus;

public int getSprId() {
return sprId;
}

public void setSprId(Integer sprId) {
this.sprId = sprId;
}

public String getSprName() {
return sprName;
}

public void setSprName(String sprName) {
this.sprName = sprName;
}

public int getSprRate() {
return sprRate;
}

public void setSprRate(Integer sprRate) {
this.sprRate = sprRate;
}

public int getSprAddOnRate() {
return sprAddOnRate;
}

public void setSprAddOnRate(Integer sprAddOnRate) {
this.sprAddOnRate = sprAddOnRate;
}

public int getDelStatus() {
return delStatus;
}

public void setDelStatus(Integer delStatus) {
this.delStatus = delStatus;
}

@Override
public String toString() {
	return "Rate [sprId=" + sprId + ", sprName=" + sprName + ", sprRate=" + sprRate + ", sprAddOnRate=" + sprAddOnRate
			+ ", delStatus=" + delStatus + "]";
}

}
