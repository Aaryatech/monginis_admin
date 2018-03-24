package com.ats.adminpanel.model.production.mixing.temp;

import java.util.List;

public class TempMixingList {
	
	
	List<TempMixing> tempMixing;

	public List<TempMixing> getTempMixing() {
		return tempMixing;
	}

	public void setTempMixing(List<TempMixing> tempMixing) {
		this.tempMixing = tempMixing;
	}

	@Override
	public String toString() {
		return "TempMixingList [tempMixing=" + tempMixing + "]";
	}

	
	
	

}
