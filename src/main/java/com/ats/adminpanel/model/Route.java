
package com.ats.adminpanel.model;


public class Route {

   
    private int routeId;
   
    private String routeName;
    
    private int delStatus;

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public int getDelStatus() {
        return delStatus;
    }

    public void setDelStatus(Integer delStatus) {
        this.delStatus = delStatus;
    }

	@Override
	public String toString() {
		return "Route [routeId=" + routeId + ", routeName=" + routeName + ", delStatus=" + delStatus + "]";
	}

}
