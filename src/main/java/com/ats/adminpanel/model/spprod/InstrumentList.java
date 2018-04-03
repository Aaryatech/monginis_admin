package com.ats.adminpanel.model.spprod;

import java.util.List;

import com.ats.adminpanel.model.Info;

public class InstrumentList {

	List<Instrument> instrumentList;
	Info info;
	public List<Instrument> getInstrumentList() {
		return instrumentList;
	}
	public void setInstrumentList(List<Instrument> instrumentList) {
		this.instrumentList = instrumentList;
	}
	public Info getInfo() {
		return info;
	}
	public void setInfo(Info info) {
		this.info = info;
	}
	@Override
	public String toString() {
		return "InstrumentList [instrumentList=" + instrumentList + ", info=" + info + "]";
	}
	
}
