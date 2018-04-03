package com.ats.adminpanel.model.spprod;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class SpCkAllocHeader implements Serializable {

	private int spCkAllocId;
	
	private int stationId;
	
	private int shiftId;
	
	private int reqUserId;
	
	private Date reqDate;
	
	private String reqTime;


	List<SpCkAllocDetail> spCkAllocDetailList;
	
	
	public List<SpCkAllocDetail> getSpCkAllocDetailList() {
		return spCkAllocDetailList;
	}

	public void setSpCkAllocDetailList(List<SpCkAllocDetail> spCkAllocDetailList) {
		this.spCkAllocDetailList = spCkAllocDetailList;
	}

	public int getSpCkAllocId() {
		return spCkAllocId;
	}

	public void setSpCkAllocId(int spCkAllocId) {
		this.spCkAllocId = spCkAllocId;
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

	public int getReqUserId() {
		return reqUserId;
	}

	public void setReqUserId(int reqUserId) {
		this.reqUserId = reqUserId;
	}

	public Date getReqDate() {
		return reqDate;
	}

	public void setReqDate(Date reqDate) {
		this.reqDate = reqDate;
	}

	public String getReqTime() {
		return reqTime;
	}

	public void setReqTime(String reqTime) {
		this.reqTime = reqTime;
	}

	@Override
	public String toString() {
		return "SpCkAllocHeader [spCkAllocId=" + spCkAllocId + ", stationId=" + stationId + ", shiftId=" + shiftId
				+ ", reqUserId=" + reqUserId + ", reqDate=" + reqDate + ", reqTime=" + reqTime + "]";
	}
	

}
