package com.ats.adminpanel.model;

import java.util.List;

import com.ats.adminpanel.controller.model.GetSfData;

public class GetPrepData {

	List<GetSfData> sfDataList;
	
	List<GetRmData> rmDataList;

	public List<GetSfData> getSfDataList() {
		return sfDataList;
	}

	public List<GetRmData> getRmDataList() {
		return rmDataList;
	}

	public void setSfDataList(List<GetSfData> sfDataList) {
		this.sfDataList = sfDataList;
	}

	public void setRmDataList(List<GetRmData> rmDataList) {
		this.rmDataList = rmDataList;
	}

	@Override
	public String toString() {
		return "GetPrepData [sfDataList=" + sfDataList + ", rmDataList=" + rmDataList + "]";
	}
	
	
}
