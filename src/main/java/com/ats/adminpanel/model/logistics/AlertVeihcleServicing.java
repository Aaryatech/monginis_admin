package com.ats.adminpanel.model.logistics;
 
public class AlertVeihcleServicing {
	 
	private int alertId;   
	private int vehId;  
	private String vehNo; 
	private int typeId; 
	private String typeName; 
	private int lastServKm;   
	private int nextServKm;    
	private int alertServKm;
	public int getAlertId() {
		return alertId;
	}
	public void setAlertId(int alertId) {
		this.alertId = alertId;
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
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public int getLastServKm() {
		return lastServKm;
	}
	public void setLastServKm(int lastServKm) {
		this.lastServKm = lastServKm;
	}
	public int getNextServKm() {
		return nextServKm;
	}
	public void setNextServKm(int nextServKm) {
		this.nextServKm = nextServKm;
	}
	public int getAlertServKm() {
		return alertServKm;
	}
	public void setAlertServKm(int alertServKm) {
		this.alertServKm = alertServKm;
	}
	@Override
	public String toString() {
		return "AlertVeihcleServicing [alertId=" + alertId + ", vehId=" + vehId + ", vehNo=" + vehNo + ", typeId="
				+ typeId + ", typeName=" + typeName + ", lastServKm=" + lastServKm + ", nextServKm=" + nextServKm
				+ ", alertServKm=" + alertServKm + "]";
	}
	
	

}
