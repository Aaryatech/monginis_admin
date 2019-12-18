package com.ats.adminpanel.model;

import java.util.List;

import com.ats.adminpanel.model.tray.FranchiseInRoute;


public class RouteWithFrList {
	
	private int routeId;
	private String routeName;
	List<FranchiseInRoute> frList;
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
	public List<FranchiseInRoute> getFrList() {
		return frList;
	}
	public void setFrList(List<FranchiseInRoute> frList) {
		this.frList = frList;
	}
	@Override
	public String toString() {
		return "RouteWithFrList [routeId=" + routeId + ", routeName=" + routeName + ", frList=" + frList + "]";
	}

}
