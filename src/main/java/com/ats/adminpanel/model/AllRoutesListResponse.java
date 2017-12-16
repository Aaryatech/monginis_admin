
package com.ats.adminpanel.model;

import java.util.List;

public class AllRoutesListResponse {

    
    private List<Route> route = null;
    
    private Info info;

    public List<Route> getRoute() {
        return route;
    }

    public void setRoute(List<Route> route) {
        this.route = route;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

}
