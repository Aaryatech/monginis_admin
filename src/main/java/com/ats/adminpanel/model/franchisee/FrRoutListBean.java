package com.ats.adminpanel.model.franchisee;

import java.util.List;

import com.ats.adminpanel.model.Route;

public class FrRoutListBean {

	List<FranchiseeList> fr;
	
	List<Route> route;

	public List<FranchiseeList> getFr() {
		return fr;
	}

	public void setFr(List<FranchiseeList> fr) {
		this.fr = fr;
	}

	public List<Route> getRoute() {
		return route;
	}

	public void setRoute(List<Route> route) {
		this.route = route;
	}

	@Override
	public String toString() {
		return "FrRoutListBean [fr=" + fr + ", route=" + route + "]";
	}

	
	
}
