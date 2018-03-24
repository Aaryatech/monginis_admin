package com.ats.adminpanel.model.spprod;

public class StationWiseCkCount {

	private int stationId;
	
	private String stName;
	
	private int compQty;
	
	private int totQty;

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

	public int getCompQty() {
		return compQty;
	}

	public void setCompQty(int compQty) {
		this.compQty = compQty;
	}

	public int getTotQty() {
		return totQty;
	}

	public void setTotQty(int totQty) {
		this.totQty = totQty;
	}

	@Override
	public String toString() {
		return "StationWiseCkCount [stationId=" + stationId + ", stName=" + stName + ", compQty=" + compQty
				+ ", totQty=" + totQty + "]";
	}
	
}
