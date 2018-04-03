package com.ats.adminpanel.model.spprod;

import java.util.List;

import com.ats.adminpanel.model.Info;

public class StationSpCakeList {

	List<StationSpCake> stationSpCakeList;
	
	Info info;

	public List<StationSpCake> getStationSpCakeList() {
		return stationSpCakeList;
	}

	public void setStationSpCakeList(List<StationSpCake> stationSpCakeList) {
		this.stationSpCakeList = stationSpCakeList;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "StationSpCakeList [stationSpCakeList=" + stationSpCakeList + ", info=" + info + "]";
	}
	
	
}
