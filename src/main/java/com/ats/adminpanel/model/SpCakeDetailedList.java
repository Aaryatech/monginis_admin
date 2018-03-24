package com.ats.adminpanel.model;

import java.util.List;


public class SpCakeDetailedList {
	
	private List<SpCakeDetailed> spCakeDetailed;

	public List<SpCakeDetailed> getSpCakeDetailed() {
		return spCakeDetailed;
	}

	public void setSpCakeDetailed(List<SpCakeDetailed> spCakeDetailed) {
		this.spCakeDetailed = spCakeDetailed;
	}

	@Override
	public String toString() {
		return "SpCakeDetailedList [spCakeDetailed=" + spCakeDetailed + "]";
	}
	
	

}
