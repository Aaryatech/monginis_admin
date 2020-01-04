package com.ats.adminpanel.model.logistics;


public class RouteTargetReport {
	
	private String id;
	private int routeId;
	private String routeName;
	private String frName;
	private int frTarget;
	private int ttlTarget;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	public String getFrName() {
		return frName;
	}
	public void setFrName(String frName) {
		this.frName = frName;
	}
	public int getFrTarget() {
		return frTarget;
	}
	public void setFrTarget(int frTarget) {
		this.frTarget = frTarget;
	}
	public int getTtlTarget() {
		return ttlTarget;
	}
	public void setTtlTarget(int ttlTarget) {
		this.ttlTarget = ttlTarget;
	}
	@Override
	public String toString() {
		return "RouteTargetReport [id=" + id + ", routeId=" + routeId + ", routeName=" + routeName + ", frName="
				+ frName + ", frTarget=" + frTarget + ", ttlTarget=" + ttlTarget + "]";
	}
	
	
}
