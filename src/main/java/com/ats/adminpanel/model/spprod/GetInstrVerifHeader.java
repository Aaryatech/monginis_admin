package com.ats.adminpanel.model.spprod;

import java.util.Date;
import java.util.List;

public class GetInstrVerifHeader{

	private boolean isError;
	
	private String message;
	
	private int instVerifHId;
	
	private String instVerifDate;
	
	private int userId;
	
	private int stationId;
	
	private int shiftId;
	
	private int stStatus;
	
	private String startTime;
	
	private String endTime;
	
	private int delStatus;

	private List<GetInstVerifDetail> instVerificationDetailList;
	
	public List<GetInstVerifDetail> getInstVerificationDetailList() {
		return instVerificationDetailList;
	}

	public void setInstVerificationDetailList(List<GetInstVerifDetail> instVerificationDetailList) {
		this.instVerificationDetailList = instVerificationDetailList;
	}

	public boolean isError() {
		return isError;
	}

	public void setError(boolean isError) {
		this.isError = isError;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getInstVerifHId() {
		return instVerifHId;
	}

	public void setInstVerifHId(int instVerifHId) {
		this.instVerifHId = instVerifHId;
	}

	public String getInstVerifDate() {
		return instVerifDate;
	}

	public void setInstVerifDate(String instVerifDate) {
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

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	@Override
	public String toString() {
		return "GetInstrVerifHeader [isError=" + isError + ", message=" + message + ", instVerifHId=" + instVerifHId
				+ ", instVerifDate=" + instVerifDate + ", userId=" + userId + ", stationId=" + stationId + ", shiftId="
				+ shiftId + ", stStatus=" + stStatus + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", delStatus=" + delStatus + ", instVerificationDetailList=" + instVerificationDetailList + "]";
	}

	
}
