
package com.ats.adminpanel.model;

import java.util.List;

public class AllEventListResponse {

   
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

	@Override
	public String toString() {
		return "AllEventListResponse [event=" + event + ", info=" + info + "]";
	}

    
    
    
}
