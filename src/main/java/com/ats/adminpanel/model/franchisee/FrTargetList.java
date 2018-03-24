package com.ats.adminpanel.model.franchisee;

import java.util.List;

import com.ats.adminpanel.model.Info;

public class FrTargetList {

	private List<FrTarget> frTargetList;
	
	Info info;

	public List<FrTarget> getFrTargetList() {
		return frTargetList;
	}

	public void setFrTargetList(List<FrTarget> frTargetList) {
		this.frTargetList = frTargetList;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "FrTargetList [frTargetList=" + frTargetList + ", info=" + info + "]";
	}
	
}
