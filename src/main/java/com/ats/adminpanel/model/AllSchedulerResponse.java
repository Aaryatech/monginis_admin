package com.ats.adminpanel.model;

import java.util.List;

import com.ats.adminpanel.model.Info;

public class AllSchedulerResponse {


private List<Scheduler> schedulerList = null;

private Info info;

public List<Scheduler> getSchedulerList() {
return schedulerList;
}

public void setSchedulerList(List<Scheduler> schedulerList) {
this.schedulerList = schedulerList;
}

public Info getInfo() {
return info;
}

public void setInfo(Info info) {
this.info = info;
}

}
