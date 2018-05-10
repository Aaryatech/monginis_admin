package com.ats.adminpanel.model.salescompare;

import java.util.List;

import com.ats.adminpanel.model.Route;

public class SalesComparison {

	List<SalesComparisonReport> billTotalList;

	List<SalesCompareGrnTot> grnGvnTotalList;
   
	
	int frId;
	String frName;
	float perMonthSale;
	float prevMonthSale;
	float lastMonthDiff;
	String routeName;
	int routeId;
	
	float monthDiffInPer;
	

	public List<SalesComparisonReport> getBillTotalList() {
		return billTotalList;
	}

	public void setBillTotalList(List<SalesComparisonReport> billTotalList) {
		this.billTotalList = billTotalList;
	}


	public List<SalesCompareGrnTot> getGrnGvnTotalList() {
		return grnGvnTotalList;
	}

	public void setGrnGvnTotalList(List<SalesCompareGrnTot> grnGvnTotalList) {
		this.grnGvnTotalList = grnGvnTotalList;
	}

	public int getFrId() {
		return frId;
	}

	public void setFrId(int frId) {
		this.frId = frId;
	}

	public String getFrName() {
		return frName;
	}

	public void setFrName(String frName) {
		this.frName = frName;
	}

	public float getPerMonthSale() {
		return perMonthSale;
	}

	public void setPerMonthSale(float perMonthSale) {
		this.perMonthSale = perMonthSale;
	}

	public float getPrevMonthSale() {
		return prevMonthSale;
	}

	public void setPrevMonthSale(float prevMonthSale) {
		this.prevMonthSale = prevMonthSale;
	}

	public float getLastMonthDiff() {
		return lastMonthDiff;
	}

	public void setLastMonthDiff(float lastMonthDiff) {
		this.lastMonthDiff = lastMonthDiff;
	}

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public int getRouteId() {
		return routeId;
	}

	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}

	public float getMonthDiffInPer() {
		return monthDiffInPer;
	}

	public void setMonthDiffInPer(float monthDiffInPer) {
		this.monthDiffInPer = monthDiffInPer;
	}

	@Override
	public String toString() {
		return "SalesComparison [billTotalList=" + billTotalList + ", grnGvnTotalList=" + grnGvnTotalList
				+ ", frId=" + frId + ", frName=" + frName + ", perMonthSale="
				+ perMonthSale + ", prevMonthSale=" + prevMonthSale + ", lastMonthDiff=" + lastMonthDiff
				+ ", routeName=" + routeName + ", routeId=" + routeId + ", monthDiffInPer=" + monthDiffInPer + "]";
	}
    
}
