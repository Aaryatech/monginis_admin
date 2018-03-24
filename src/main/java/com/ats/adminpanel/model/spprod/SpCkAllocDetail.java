package com.ats.adminpanel.model.spprod;

import java.io.Serializable;
import java.util.Date;

public class SpCkAllocDetail implements Serializable{


	private int spCkAllocDId;
	
	private int spCkAllocId;
	
	private int tSpCakeId;
	
	private String startTime;
	
	private String endTime;
	
	private int status;
	
	private Date pickupDate;
	
	private int frId;
	
	private int spId;
	
	private String spCode;
	
	private String spName;
	
	private int delStatus;

	private int isBom;

	public int getIsBom() {
		return isBom;
	}

	public void setIsBom(int isBom) {
		this.isBom = isBom;
	}

	public int getFrId() {
		return frId;
	}

	public void setFrId(int frId) {
		this.frId = frId;
	}

	public int getSpId() {
		return spId;
	}

	public void setSpId(int spId) {
		this.spId = spId;
	}

	public String getSpCode() {
		return spCode;
	}

	public void setSpCode(String spCode) {
		this.spCode = spCode;
	}

	public String getSpName() {
		return spName;
	}

	public void setSpName(String spName) {
		this.spName = spName;
	}

	public int getSpCkAllocDId() {
		return spCkAllocDId;
	}

	public void setSpCkAllocDId(int spCkAllocDId) {
		this.spCkAllocDId = spCkAllocDId;
	}

	public int getSpCkAllocId() {
		return spCkAllocId;
	}

	public void setSpCkAllocId(int spCkAllocId) {
		this.spCkAllocId = spCkAllocId;
	}

	public int gettSpCakeId() {
		return tSpCakeId;
	}

	public void settSpCakeId(int tSpCakeId) {
		this.tSpCakeId = tSpCakeId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getPickupDate() {
		return pickupDate;
	}

	public void setPickupDate(Date pickupDate) {
		this.pickupDate = pickupDate;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	@Override
	public String toString() {
		return "SpCkAllocDetail [spCkAllocDId=" + spCkAllocDId + ", spCkAllocId=" + spCkAllocId + ", tSpCakeId="
				+ tSpCakeId + ", startTime=" + startTime + ", endTime=" + endTime + ", status=" + status
				+ ", pickupDate=" + pickupDate + ", delStatus=" + delStatus + "]";
	}
}
