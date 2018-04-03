package com.ats.adminpanel.model.spprod;

import java.util.List;

import com.ats.adminpanel.model.Info;

public class SpStationList {

	List<GetSpStation> spStationList;
	
	Info info;

	public List<GetSpStation> getSpStationList() {
		return spStationList;
	}

	public void setSpStationList(List<GetSpStation> spStationList) {
		this.spStationList = spStationList;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "SpStationList [spStationList=" + spStationList + ", info=" + info + "]";
	}
}
