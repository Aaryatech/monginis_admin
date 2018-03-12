package com.ats.adminpanel.model.logistics;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TrayManagementReport {
	 
	private int tranId;  
	private String tranDate; 
	private int vehId; 
	private String vehNo; 
	private int drvId; 
	private String driverName; 
	private int routeId; 
	private String routeName; 
	private float vehRunningKm; 
	private float diesel;
	public int getTranId() {
		return tranId;
	}
	public void setTranId(int tranId) {
		this.tranId = tranId;
	}
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public String getTranDate() {
		return tranDate;
	}
	public void setTranDate(String tranDate) {
		this.tranDate = tranDate;
	}
	public int getVehId() {
		return vehId;
	}
	public void setVehId(int vehId) {
		this.vehId = vehId;
	}
	public String getVehNo() {
		return vehNo;
	}
	public void setVehNo(String vehNo) {
		this.vehNo = vehNo;
	}
	public int getDrvId() {
		return drvId;
	}
	public void setDrvId(int drvId) {
		this.drvId = drvId;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
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
	public float getVehRunningKm() {
		return vehRunningKm;
	}
	public void setVehRunningKm(float vehRunningKm) {
		this.vehRunningKm = vehRunningKm;
	}
	public float getDiesel() {
		return diesel;
	}
	public void setDiesel(float diesel) {
		this.diesel = diesel;
	}
	@Override
	public String toString() {
		return "TrayManagementReport [tranId=" + tranId + ", tranDate=" + tranDate + ", vehId=" + vehId + ", vehNo="
				+ vehNo + ", drvId=" + drvId + ", driverName=" + driverName + ", routeId=" + routeId + ", routeName="
				+ routeName + ", vehRunningKm=" + vehRunningKm + ", diesel=" + diesel + "]";
	}
	
	

}
