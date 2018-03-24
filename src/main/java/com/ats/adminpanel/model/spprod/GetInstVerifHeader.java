package com.ats.adminpanel.model.spprod;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class GetInstVerifHeader implements Serializable{

	private int instVerifHId;
	
	private Date instVerifDate;
	
	private int userId;
	
	private String userName;
	
	private int stationId;
	
	private String stName;
	
	private int shiftId;
	
	private String shiftName;
	
	private int stStatus;
	
	private String startTime;
	
	private String endTime;
	


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getStName() {
		return stName;
	}

	public void setStName(String stName) {
		this.stName = stName;
	}

	public String getShiftName() {
		return shiftName;
	}

	public void setShiftName(String shiftName) {
		this.shiftName = shiftName;
	}

	private List<InstVerificationDetail> instVerificationDetailList;
	
	public List<InstVerificationDetail> getInstVerificationDetailList() {
		return instVerificationDetailList;
	}

	public void setInstVerificationDetailList(List<InstVerificationDetail> instVerificationDetailList) {
		this.instVerificationDetailList = instVerificationDetailList;
	}



	public int getInstVerifHId() {
		return instVerifHId;
	}

	public void setInstVerifHId(int instVerifHId) {
		this.instVerifHId = instVerifHId;
	}

	public Date getInstVerifDate() {
		return instVerifDate;
	}

	public void setInstVerifDate(Date instVerifDate) {
		this.instVerifDate = instVerifDate;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getStationId() {
		return stationId;
	}

	public void setStationId(int stationId) {
		this.stationId = stationId;
	}

	public int getShiftId() {
		return shiftId;
	}

	public void setShiftId(int shiftId) {
		this.shiftId = shiftId;
	}

	public int getStStatus() {
		return stStatus;
	}

	public void setStStatus(int stStatus) {
		this.stStatus = stStatus;
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

	@Override
	public String toString() {
		return "GetInstVerifHeader [instVerifHId=" + instVerifHId + ", instVerifDate=" + instVerifDate + ", userId="
				+ userId + ", userName=" + userName + ", stationId=" + stationId + ", stName=" + stName + ", shiftId="
				+ shiftId + ", shiftName=" + shiftName + ", stStatus=" + stStatus + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", instVerificationDetailList="
				+ instVerificationDetailList + "]";
	}
      
}
