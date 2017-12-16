package com.ats.adminpanel.model.spprod;

import java.util.List;

import com.ats.adminpanel.model.ErrorMessage;


public class StationAllocList {
	
	List<GetStationAllocation> stationAllocationList;
	
	ErrorMessage errorMessage;

	public List<GetStationAllocation> getStationAllocationList() {
		return stationAllocationList;
	}

	public void setStationAllocationList(List<GetStationAllocation> stationAllocationList) {
		this.stationAllocationList = stationAllocationList;
	}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(ErrorMessage errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return "StationAllocList [stationAllocationList=" + stationAllocationList + ", errorMessage=" + errorMessage
				+ "]";
	}
	
	

}
