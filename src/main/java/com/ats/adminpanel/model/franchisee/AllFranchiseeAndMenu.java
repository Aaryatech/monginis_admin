package com.ats.adminpanel.model.franchisee;

import java.util.List;

import com.ats.adminpanel.model.item.Item;


public class AllFranchiseeAndMenu {
	
	List<FranchiseeList> allFranchisee;
	List<Menu> allMenu=null;
	List<Item> items=null;
	List<SubCategory> subCategories=null;
	
	
	public void setAllFranchisee(List<FranchiseeList> allFranchisee) {
		this.allFranchisee = allFranchisee;
	}
	
	public List<Menu> getAllMenu() {
		return allMenu;
	}
	public void setAllMenu(List<Menu> allMenu) {
		this.allMenu = allMenu;
	}
	public List<FranchiseeList> getAllFranchisee() {
		return allFranchisee;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}

	public List<SubCategory> getSubCategories() {
		return subCategories;
	}

	public void setSubCategories(List<SubCategory> subCategories) {
		this.subCategories = subCategories;
	}

	@Override
	public String toString() {
		return "AllFranchiseeAndMenu [allFranchisee=" + allFranchisee + ", allMenu=" + allMenu + ", items=" + items
				+ ", subCategories=" + subCategories + "]";
	}
	
	
}
