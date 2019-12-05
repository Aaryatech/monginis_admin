package com.ats.adminpanel.model.salesreport2;

import java.util.List;

import com.ats.adminpanel.model.AllFrIdName;

public class SubCatFrReportList {

	List<SubCatFrReport> subCatFrReportList;

	List<AllFrIdName> frList;

	public List<SubCatFrReport> getSubCatFrReportList() {
		return subCatFrReportList;
	}

	public void setSubCatFrReportList(List<SubCatFrReport> subCatFrReportList) {
		this.subCatFrReportList = subCatFrReportList;
	}

	public List<AllFrIdName> getFrList() {
		return frList;
	}

	public void setFrList(List<AllFrIdName> frList) {
		this.frList = frList;
	}

	@Override
	public String toString() {
		return "SubCatFrReportList [subCatFrReportList=" + subCatFrReportList + ", frList=" + frList + "]";
	}

}
