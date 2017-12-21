package com.ats.adminpanel.model.spprod;

import java.io.Serializable;
import java.util.Date;

public class GetAllocStationCk{

	private int spCkAllocDId;
	
	private int stationId;
	
	private String stName;
	
	private int tSpCakeId;
	
	private String spName;
	
	private String spCode;
	
	private String reqDate;

	public int getSpCkAllocDId() {
		return spCkAllocDId;
	}

	public void setSpCkAllocDId(int spCkAllocDId) {
		this.spCkAllocDId = spCkAllocDId;
	}

	public int getStationId() {
		return stationId;
	}

	public void setStationId(int stationId) {
		this.stationId = stationId;
	}

	public String getStName() {
		return stName;
	}

	public void setStName(String stName) {
		this.stName = stName;
	}

	public int gettSpCakeId() {
		return tSpCakeId;
	}

	public void settSpCakeId(int tSpCakeId) {
		this.tSpCakeId = tSpCakeId;
	}

	public String getSpName() {
		return spName;
	}

	public void setSpName(String spName) {
		this.spName = spName;
	}

	public String getSpCode() {
		return spCode;
	}

	public void setSpCode(String spCode) {
		this.spCode = spCode;
	}

	public String getReqDate() {
		return reqDate;
	}

	public void setReqDate(String reqDate) {
		this.reqDate = reqDate;
	}

	@Override
	public String toString() {
		return "GetAllocStationCk [spCkAllocDId=" + spCkAllocDId + ", stationId=" + stationId + ", stName=" + stName
				+ ", tSpCakeId=" + tSpCakeId + ", spName=" + spName + ", spCode=" + spCode + ", reqDate=" + reqDate
				+ "]";
	}
	
}
