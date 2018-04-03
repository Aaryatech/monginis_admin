package com.ats.adminpanel.model.production.mixing.temp;

import java.util.List;

public class GetTempMixItemDetailList {
	
	
	List<GetTempMixItemDetail> tempMixItemDetail;

	public List<GetTempMixItemDetail> getTempMixItemDetail() {
		return tempMixItemDetail;
	}

	public void setTempMixItemDetail(List<GetTempMixItemDetail> tempMixItemDetail) {
		this.tempMixItemDetail = tempMixItemDetail;
	}

	@Override
	public String toString() {
		return "GetTempMixItemDetailList [tempMixItemDetail=" + tempMixItemDetail + "]";
	}
	
	
}
