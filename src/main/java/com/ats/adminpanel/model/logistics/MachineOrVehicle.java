package com.ats.adminpanel.model.logistics;

public class MachineOrVehicle {
	
	private int mechId;
	private String mechName;
	private int dealerId;
	
	public int getMechId() {
		return mechId;
	}
	public void setMechId(int mechId) {
		this.mechId = mechId;
	}
	public String getMechName() {
		return mechName;
	}
	public void setMechName(String mechName) {
		this.mechName = mechName;
	}
	 
	public int getDealerId() {
		return dealerId;
	}
	public void setDealerId(int dealerId) {
		this.dealerId = dealerId;
	}
	@Override
	public String toString() {
		return "MachineOrVehicle [mechId=" + mechId + ", mechName=" + mechName + ", dealerId=" + dealerId + "]";
	}
	
	

}
