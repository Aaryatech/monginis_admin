package com.ats.adminpanel.model.materialreceipt;

import java.util.List;



public class GetTaxListByRmId {
	
	private List<GetTaxByRmId> getTaxByRmIdList;

	public List<GetTaxByRmId> getGetTaxByRmIdList() {
		return getTaxByRmIdList;
	}

	public void setGetTaxByRmIdList(List<GetTaxByRmId> getTaxByRmIdList) {
		this.getTaxByRmIdList = getTaxByRmIdList;
	}

	@Override
	public String toString() {
		return "GetTaxListByRmId [getTaxByRmIdList=" + getTaxByRmIdList + "]";
	}
	
	

}
