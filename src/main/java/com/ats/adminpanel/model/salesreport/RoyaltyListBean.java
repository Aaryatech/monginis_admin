package com.ats.adminpanel.model.salesreport;

import java.util.List;

import com.ats.adminpanel.model.item.MCategoryList;

public class RoyaltyListBean {
	
	 List<MCategoryList> categoryList;
	
	 List<SalesReportRoyalty> salesReportRoyalty;

	public List<MCategoryList> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<MCategoryList> categoryList) {
		this.categoryList = categoryList;
	}

	public List<SalesReportRoyalty> getSalesReportRoyalty() {
		return salesReportRoyalty;
	}

	public void setSalesReportRoyalty(List<SalesReportRoyalty> salesReportRoyalty) {
		this.salesReportRoyalty = salesReportRoyalty;
	}

	@Override
	public String toString() {
		return "RoyaltyListBean [categoryList=" + categoryList + ", salesReportRoyalty=" + salesReportRoyalty + "]";
	}
	
}
