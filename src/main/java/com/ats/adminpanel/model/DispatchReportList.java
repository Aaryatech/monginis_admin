package com.ats.adminpanel.model;

import java.util.List;

import com.ats.adminpanel.model.franchisee.FrNameIdByRouteId;
import com.ats.adminpanel.model.franchisee.SubCategory;
import com.ats.adminpanel.model.item.Item;

public class DispatchReportList {

	private List<DispatchReport> dispatchReportList;
	private List<Item> itemList;
	private List<FrNameIdByRouteId>  frList;
	private List<SubCategory>  subCatList;

	public List<SubCategory> getSubCatList() {
		return subCatList;
	}

	public void setSubCatList(List<SubCategory> subCatList) {
		this.subCatList = subCatList;
	}

	public List<Item> getItemList() {
		return itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}

	public List<DispatchReport> getDispatchReportList() {
		return dispatchReportList;
	}

	public void setDispatchReportList(List<DispatchReport> dispatchReportList) {
		this.dispatchReportList = dispatchReportList;
	}

	public List<FrNameIdByRouteId> getFrList() {
		return frList;
	}

	public void setFrList(List<FrNameIdByRouteId> frList) {
		this.frList = frList;
	}

	@Override
	public String toString() {
		return "DispatchReportList [dispatchReportList=" + dispatchReportList + ", itemList=" + itemList + ", frList="
				+ frList + ", subCatList=" + subCatList + "]";
	}

	

}
