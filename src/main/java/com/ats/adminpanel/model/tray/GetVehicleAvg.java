package com.ats.adminpanel.model.tray;

import java.io.Serializable;

public class GetVehicleAvg implements Serializable {

	private int tranId;
	
	private String tranDate;
	
	private int vehId;
	
	private int drvId;
	
	private String driverName;
	
	private int routeId;
	
	private String routeName;
	
	private String vehNo;
	
	private String vehOuttime;
	
	private String vehIntime;
	
	private float vehOutkm;
	
	private float vehInkm;
	
	private float vehRunningKm;
	
	private float diesel;
	
	private int vehStatus;
	
	private int delStatus;

	private int extraTrayOut;
	
	private int extraTrayIn;

	private int vehIsRegular;

	private int isSameDay;
	
	private float vehCompAvg;
	
	private float vehStandAvg;
	
	private float vehMiniAvg;

	public int getTranId() {
		return tranId;
	}

	public String getTranDate() {
		return tranDate;
	}

	public int getVehId() {
		return vehId;
	}

	public int getDrvId() {
		return drvId;
	}

	public String getDriverName() {
		return driverName;
	}

	public int getRouteId() {
		return routeId;
	}

	public String getRouteName() {
		return routeName;
	}

	public String getVehNo() {
		return vehNo;
	}

	public String getVehOuttime() {
		return vehOuttime;
	}

	public String getVehIntime() {
		return vehIntime;
	}

	public float getVehOutkm() {
		return vehOutkm;
	}

	public float getVehInkm() {
		return vehInkm;
	}

	public float getVehRunningKm() {
		return vehRunningKm;
	}

	public float getDiesel() {
		return diesel;
	}

	public int getVehStatus() {
		return vehStatus;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public int getExtraTrayOut() {
		return extraTrayOut;
	}

	public int getExtraTrayIn() {
		return extraTrayIn;
	}

	public int getVehIsRegular() {
		return vehIsRegular;
	}

	public int getIsSameDay() {
		return isSameDay;
	}

	public float getVehCompAvg() {
		return vehCompAvg;
	}

	public float getVehStandAvg() {
		return vehStandAvg;
	}

	public float getVehMiniAvg() {
		return vehMiniAvg;
	}

	public void setTranId(int tranId) {
		this.tranId = tranId;
	}

	public void setTranDate(String tranDate) {
		this.tranDate = tranDate;
	}

	public void setVehId(int vehId) {
		this.vehId = vehId;
	}

	public void setDrvId(int drvId) {
		this.drvId = drvId;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public void setVehNo(String vehNo) {
		this.vehNo = vehNo;
	}

	public void setVehOuttime(String vehOuttime) {
		this.vehOuttime = vehOuttime;
	}

	public void setVehIntime(String vehIntime) {
		this.vehIntime = vehIntime;
	}

	public void setVehOutkm(float vehOutkm) {
		this.vehOutkm = vehOutkm;
	}

	public void setVehInkm(float vehInkm) {
		this.vehInkm = vehInkm;
	}

	public void setVehRunningKm(float vehRunningKm) {
		this.vehRunningKm = vehRunningKm;
	}

	public void setDiesel(float diesel) {
		this.diesel = diesel;
	}

	public void setVehStatus(int vehStatus) {
		this.vehStatus = vehStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	public void setExtraTrayOut(int extraTrayOut) {
		this.extraTrayOut = extraTrayOut;
	}

	public void setExtraTrayIn(int extraTrayIn) {
		this.extraTrayIn = extraTrayIn;
	}

	public void setVehIsRegular(int vehIsRegular) {
		this.vehIsRegular = vehIsRegular;
	}

	public void setIsSameDay(int isSameDay) {
		this.isSameDay = isSameDay;
	}

	public void setVehCompAvg(float vehCompAvg) {
		this.vehCompAvg = vehCompAvg;
	}

	public void setVehStandAvg(float vehStandAvg) {
		this.vehStandAvg = vehStandAvg;
	}

	public void setVehMiniAvg(float vehMiniAvg) {
		this.vehMiniAvg = vehMiniAvg;
	}

	@Override
	public String toString() {
		return "GetVehicleAvg [tranId=" + tranId + ", tranDate=" + tranDate + ", vehId=" + vehId + ", drvId=" + drvId
				+ ", driverName=" + driverName + ", routeId=" + routeId + ", routeName=" + routeName + ", vehNo="
				+ vehNo + ", vehOuttime=" + vehOuttime + ", vehIntime=" + vehIntime + ", vehOutkm=" + vehOutkm
				+ ", vehInkm=" + vehInkm + ", vehRunningKm=" + vehRunningKm + ", diesel=" + diesel + ", vehStatus="
				+ vehStatus + ", delStatus=" + delStatus + ", extraTrayOut=" + extraTrayOut + ", extraTrayIn="
				+ extraTrayIn + ", vehIsRegular=" + vehIsRegular + ", isSameDay=" + isSameDay + ", vehCompAvg="
				+ vehCompAvg + ", vehStandAvg=" + vehStandAvg + ", vehMiniAvg=" + vehMiniAvg + "]";
	}
	
	
	
	
}
