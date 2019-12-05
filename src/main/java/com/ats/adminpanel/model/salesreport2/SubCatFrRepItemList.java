package com.ats.adminpanel.model.salesreport2;

import java.util.List;

import com.ats.adminpanel.model.AllFrIdName;
import com.ats.adminpanel.model.franchisee.SubCategory;
import com.ats.adminpanel.model.item.Item;

public class SubCatFrRepItemList {

	List<SubCatItemReport> subCatItemReport;
	List<AllFrIdName> frList;
	List<Item> itemList;

	List<SubCategory> subCatList;

	public List<SubCategory> getSubCatList() {
		return subCatList;
	}

	public void setSubCatList(List<SubCategory> subCatList) {
		this.subCatList = subCatList;
	}

	public List<SubCatItemReport> getSubCatItemReport() {
		return subCatItemReport;
	}

	public void setSubCatItemReport(List<SubCatItemReport> subCatItemReport) {
		this.subCatItemReport = subCatItemReport;
	}

	public List<AllFrIdName> getFrList() {
		return frList;
	}

	public void setFrList(List<AllFrIdName> frList) {
		this.frList = frList;
	}

	public List<Item> getItemList() {
		return itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}

	@Override
	public String toString() {
		return "SubCatFrRepItemList [subCatItemReport=" + subCatItemReport + ", frList=" + frList + ", itemList="
				+ itemList + ", subCatList=" + subCatList + "]";
	}

}
