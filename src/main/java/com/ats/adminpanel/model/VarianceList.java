package com.ats.adminpanel.model;

import java.util.List;



public class VarianceList {
	
	private List<Variance> Varianceorderlist;

	public List<Variance> getVarianceorderlist() {
		return Varianceorderlist;
	}

	public void setVarianceorderlist(List<Variance> varianceorderlist) {
		Varianceorderlist = varianceorderlist;
	}

	@Override
	public String toString() {
		return "VarianceList [Varianceorderlist=" + Varianceorderlist + "]";
	}
	
	

}
