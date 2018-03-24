package com.ats.adminpanel.model.flavours;

public class Flavour {


private int spfId;

private int spType;

private String spfName;

private Double spfAdonRate;

private int delStatus;

public int getSpfId() {
return spfId;
}

public void setSpfId(int spfId) {
this.spfId = spfId;
}

public Integer getSpType() {
return spType;
}

public void setSpType(Integer spType) {
this.spType = spType;
}

public String getSpfName() {
return spfName;
}

public void setSpfName(String spfName) {
this.spfName = spfName;
}

public Double getSpfAdonRate() {
return spfAdonRate;
}

public void setSpfAdonRate(Double spfAdonRate) {
this.spfAdonRate = spfAdonRate;
}

public Integer getDelStatus() {
return delStatus;
}

public void setDelStatus(Integer delStatus) {
this.delStatus = delStatus;
}

@Override
public String toString() {
	return "Flavour [spfId=" + spfId + ", spType=" + spType + ", spfName=" + spfName + ", spfAdonRate=" + spfAdonRate
			+ ", delStatus=" + delStatus + "]";
}

}