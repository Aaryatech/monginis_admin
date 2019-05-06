package com.ats.adminpanel.model.catstock;

import java.util.List;

import com.ats.adminpanel.model.AllFrIdName;
import com.ats.adminpanel.model.franchisee.SubCategory;
import com.ats.adminpanel.model.item.Item;

public class CategoryStockDAO {
	
	private List<CategoryWiseOrderData> categoryWiseOrderDataList;
	private List<Item> itemList;
	private List<AllFrIdName> frList;
	private List<SubCategory>  subCatList;
	public List<CategoryWiseOrderData> getCategoryWiseOrderDataList() {
		return categoryWiseOrderDataList;
	}
	public List<Item> getItemList() {
		return itemList;
	}
	public List<AllFrIdName> getFrList() {
		return frList;
	}
	public List<SubCategory> getSubCatList() {
		return subCatList;
	}
	public void setCategoryWiseOrderDataList(List<CategoryWiseOrderData> categoryWiseOrderDataList) {
		this.categoryWiseOrderDataList = categoryWiseOrderDataList;
	}
	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}
	public void setFrList(List<AllFrIdName> frList) {
		this.frList = frList;
	}
	public void setSubCatList(List<SubCategory> subCatList) {
		this.subCatList = subCatList;
	}
	@Override
	public String toString() {
		return "CategoryStockDAO [categoryWiseOrderDataList=" + categoryWiseOrderDataList + ", itemList=" + itemList
				+ ", frList=" + frList + ", subCatList=" + subCatList + "]";
	}
   
	

}
