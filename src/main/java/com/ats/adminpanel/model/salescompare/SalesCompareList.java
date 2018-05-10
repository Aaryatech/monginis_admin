package com.ats.adminpanel.model.salescompare;

import java.util.List;

import com.ats.adminpanel.model.Route;

public class SalesCompareList {

	List<Route> routeList;

	List<SalesComparison> saleCompFinal;

	String prevMonth;
	
	String currMonth;
	
	
	public String getPrevMonth() {
		return prevMonth;
	}

	public String getCurrMonth() {
		return currMonth;
	}

	public void setPrevMonth(String prevMonth) {
		this.prevMonth = prevMonth;
	}

	public void setCurrMonth(String currMonth) {
		this.currMonth = currMonth;
	}

	public List<Route> getRouteList() {
		return routeList;
	}

	public List<SalesComparison> getSaleCompFinal() {
		return saleCompFinal;
	}

	public void setRouteList(List<Route> routeList) {
		this.routeList = routeList;
	}

	public void setSaleCompFinal(List<SalesComparison> saleCompFinal) {
		this.saleCompFinal = saleCompFinal;
	}

	@Override
	public String toString() {
		return "SalesCompareList [routeList=" + routeList + ", saleCompFinal=" + saleCompFinal + "]";
	}
	
	
	
}
