package com.ats.adminpanel.model.spprod;

import java.util.List;

import com.ats.adminpanel.model.Info;

public class InstAllocToStationList {

	List<GetInstAllocToStation> instAllocList;
	
	Info info;

	public List<GetInstAllocToStation> getInstAllocList() {
		return instAllocList;
	}

	public void setInstAllocList(List<GetInstAllocToStation> instAllocList) {
		this.instAllocList = instAllocList;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "InstAllocToStationList [instAllocList=" + instAllocList + ", info=" + info + "]";
	}
	
}
