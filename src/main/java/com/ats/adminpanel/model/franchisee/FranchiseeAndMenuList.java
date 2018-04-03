package com.ats.adminpanel.model.franchisee;

import java.util.List;

public class FranchiseeAndMenuList {
	List<FranchiseeList> allFranchisee;
     List<Menu> allMenu;
	public List<FranchiseeList> getAllFranchisee() {
		return allFranchisee;
	}
	public void setAllFranchisee(List<FranchiseeList> allFranchisee) {
		this.allFranchisee = allFranchisee;
	}
	public List<Menu> getAllMenu() {
		return allMenu;
	}
	public void setAllMenu(List<Menu> allMenu) {
		this.allMenu = allMenu;
	}




}
