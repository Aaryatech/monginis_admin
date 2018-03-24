package com.ats.adminpanel.model;

public class Scheduler {


private Integer schId;

private String schDate;

private String schTodate;

private String schOccasionname;

private String schMessage;

private Double schFrdttime;

private Double schTodttime;

private Integer isActive;

private Integer delStatus;

public Integer getSchId() {
return schId;
}

public void setSchId(Integer schId) {
this.schId = schId;
}

public String getSchDate() {
return schDate;
}

public void setSchDate(String schDate) {
this.schDate = schDate;
}

public String getSchTodate() {
return schTodate;
}

public void setSchTodate(String schTodate) {
this.schTodate = schTodate;
}

public String getSchOccasionname() {
return schOccasionname;
}

public void setSchOccasionname(String schOccasionname) {
this.schOccasionname = schOccasionname;
}

public String getSchMessage() {
return schMessage;
}

public void setSchMessage(String schMessage) {
this.schMessage = schMessage;
}

public Double getSchFrdttime() {
return schFrdttime;
}

public void setSchFrdttime(Double schFrdttime) {
this.schFrdttime = schFrdttime;
}

public Double getSchTodttime() {
return schTodttime;
}

public void setSchTodttime(Double schTodttime) {
this.schTodttime = schTodttime;
}

public Integer getIsActive() {
return isActive;
}

public void setIsActive(Integer isActive) {
this.isActive = isActive;
}

public Integer getDelStatus() {
return delStatus;
}

public void setDelStatus(Integer delStatus) {
this.delStatus = delStatus;
}

}
