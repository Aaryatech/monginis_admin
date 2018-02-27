package com.ats.adminpanel.model.logistics;

import com.fasterxml.jackson.annotation.JsonFormat;

public class VehicleDcoument {
	
	 
	private int vehDocId;  
	private int vehId; 
	private int docId; 
	private String entryDate; 
	private String docDate; 
	private String docExpireDate;  
	private String docExpNotificationDate; 
	private String docPath;  
	private int currentKm; 
	private int delStatus;
	private int remainingDay;
	public int getVehDocId() {
		return vehDocId;
	}
	public void setVehDocId(int vehDocId) {
		this.vehDocId = vehDocId;
	}
	public int getVehId() {
		return vehId;
	}
	public void setVehId(int vehId) {
		this.vehId = vehId;
	}
	public int getDocId() {
		return docId;
	}
	public void setDocId(int docId) {
		this.docId = docId;
	}
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public String getDocDate() {
		return docDate;
	}
	public void setDocDate(String docDate) {
		this.docDate = docDate;
	}
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public String getDocExpireDate() {
		return docExpireDate;
	}
	public void setDocExpireDate(String docExpireDate) {
		this.docExpireDate = docExpireDate;
	}
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public String getDocExpNotificationDate() {
		return docExpNotificationDate;
	}
	public void setDocExpNotificationDate(String docExpNotificationDate) {
		this.docExpNotificationDate = docExpNotificationDate;
	}
	public String getDocPath() {
		return docPath;
	}
	public void setDocPath(String docPath) {
		this.docPath = docPath;
	}
	public int getCurrentKm() {
		return currentKm;
	}
	public void setCurrentKm(int currentKm) {
		this.currentKm = currentKm;
	}
	public int getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	public int getRemainingDay() {
		return remainingDay;
	}
	public void setRemainingDay(int remainingDay) {
		this.remainingDay = remainingDay;
	}
	@Override
	public String toString() {
		return "VehicleDcoument [vehDocId=" + vehDocId + ", vehId=" + vehId + ", docId=" + docId + ", entryDate="
				+ entryDate + ", docDate=" + docDate + ", docExpireDate=" + docExpireDate + ", docExpNotificationDate="
				+ docExpNotificationDate + ", docPath=" + docPath + ", currentKm=" + currentKm + ", delStatus="
				+ delStatus + ", remainingDay=" + remainingDay + "]";
	}
	
	

}
