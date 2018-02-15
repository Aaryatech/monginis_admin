package com.ats.adminpanel.model.logistics;
 
public class VehicalType {
	
	 
	private int vehiTypeId;  
	private String vehTypeName; 
	private int makeId; 
	private int delStatus;
	public int getVehiTypeId() {
		return vehiTypeId;
	}
	public void setVehiTypeId(int vehiTypeId) {
		this.vehiTypeId = vehiTypeId;
	}
	public String getVehTypeName() {
		return vehTypeName;
	}
	public void setVehTypeName(String vehTypeName) {
		this.vehTypeName = vehTypeName;
	}
	public int getMakeId() {
		return makeId;
	}
	public void setMakeId(int makeId) {
		this.makeId = makeId;
	}
	public int getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}
	@Override
	public String toString() {
		return "VehicalType [vehiTypeId=" + vehiTypeId + ", vehTypeName=" + vehTypeName + ", makeId=" + makeId
				+ ", delStatus=" + delStatus + "]";
	}
	
	

}
