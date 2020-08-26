package com.ats.adminpanel.model.salesreport;

import java.util.List;

import com.ats.adminpanel.model.item.MCategoryList;

public class RoyaltyListBeanConsByCat {
	 List<MCategoryList> categoryList;
		
	 List<SalesRoyaltyConsByCat> salesReportRoyalty;

	public List<MCategoryList> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<MCategoryList> categoryList) {
		this.categoryList = categoryList;
	}

	public List<SalesRoyaltyConsByCat> getSalesReportRoyalty() {
		return salesReportRoyalty;
	}

	public void setSalesReportRoyalty(List<SalesRoyaltyConsByCat> salesReportRoyalty) {
		this.salesReportRoyalty = salesReportRoyalty;
	}

	@Override
	public String toString() {
		return "RoyaltyListBean [categoryList=" + categoryList + ", salesReportRoyalty=" + salesReportRoyalty + "]";
	}
}
