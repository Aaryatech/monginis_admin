package com.ats.adminpanel.model.events;

import java.util.List;

import com.ats.adminpanel.model.message.Info;

public class AllEventsListResponse {


private List<Event> event = null;

private Info info;

public List<Event> getEvent() {
return event;
}

public void setEvent(List<Event> event) {
this.event = event;
}

public Info getInfo() {
return info;
}

public void setInfo(Info info) {
this.info = info;
}

}