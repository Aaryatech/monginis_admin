package com.ats.adminpanel.model;

import java.util.List;

public class GateEntryAddList {

	private List<GateEntryAdd> gateEntryAddList;
	
	private Info info;

	public List<GateEntryAdd> getGateEntryAddList() {
		return gateEntryAddList;
	}

	public void setGateEntryAddList(List<GateEntryAdd> gateEntryAddList) {
		this.gateEntryAddList = gateEntryAddList;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "GateEntryAddList [gateEntryAddList=" + gateEntryAddList + ", info=" + info + "]";
	}
	
	
}
