package com.ats.adminpanel.model.spprod;

import java.util.List;

import com.ats.adminpanel.model.Info;

public class GetSpDetailForBomList {

	List<GetSpDetailForBom> getSpDetailForBomList;
	
	Info info;

	public List<GetSpDetailForBom> getGetSpDetailForBomList() {
		return getSpDetailForBomList;
	}

	public void setGetSpDetailForBomList(List<GetSpDetailForBom> getSpDetailForBomList) {
		this.getSpDetailForBomList = getSpDetailForBomList;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "GetSpDetailForBomList [getSpDetailForBomList=" + getSpDetailForBomList + ", info=" + info + "]";
	}
	
	
	
}
