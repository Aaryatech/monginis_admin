package com.ats.adminpanel.model.events;

public class Event {


private Integer speId;

private String speName;

private Integer delStatus;

public Integer getSpeId() {
return speId;
}

public void setSpeId(Integer speId) {
this.speId = speId;
}

public String getSpeName() {
return speName;
}

public void setSpeName(String speName) {
this.speName = speName;
}

public Integer getDelStatus() {
return delStatus;
}

public void setDelStatus(Integer delStatus) {
this.delStatus = delStatus;
}

@Override
public String toString() {
	return "Event [speId=" + speId + ", speName=" + speName + ", delStatus=" + delStatus + "]";
}

}
