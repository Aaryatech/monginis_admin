
package com.ats.adminpanel.model;


public class Route {

   
    private int routeId;
   
    private String routeName;
    
    private int delStatus;
    private int routeSeqNo;
	public int getRouteId() {
		return routeId;
	}
	public void setRouteId(int routeId) {
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
	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}
	public int getRouteSeqNo() {
		return routeSeqNo;
	}
	public void setRouteSeqNo(int routeSeqNo) {
		this.routeSeqNo = routeSeqNo;
	}
	@Override
	public String toString() {
		return "Route [routeId=" + routeId + ", routeName=" + routeName + ", delStatus=" + delStatus + ", routeSeqNo="
				+ routeSeqNo + "]";
	}

   
}
