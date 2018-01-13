package com.ats.adminpanel.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class GenerateBill {
	
	
	private int orderId;
	
	int  frId;
	int isPositive;
	
	int  menuId;
	
	int  itemId;
	
	int  orderQty;
	
	double  orderRate;
	
	double  orderMrp;
	
	String  frName;
	
	String  menuTitle;
	
	String  itemName;
	
	int  catId;
	
	String  frCode;
	
	int  rateType;
	
	int  subCatId;
	
	double itemTax1;
	
	double itemTax2;
	
	double itemTax3;
	
	int grnType;//newly Added
	
	int itemShelfLife;//newly Added
	int isSameState;
	
	
	
	String  deliveryDate;//newlyAdded
	
	//@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public int getItemShelfLife() {
		return itemShelfLife;
	}

	public void setItemShelfLife(int itemShelfLife) {
		this.itemShelfLife = itemShelfLife;
	}

	public int getGrnType() {
		return grnType;
	}

	public void setGrnType(int grnType) {
		this.grnType = grnType;
	}

	public int getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	public String getFrCode() {
		return frCode;
	}

	public void setFrCode(String frCode) {
		this.frCode = frCode;
	}

	public int getRateType() {
		return rateType;
	}

	public void setRateType(int rateType) {
		this.rateType = rateType;
	}

	public int getSubCatId() {
		return subCatId;
	}

	public void setSubCatId(int subCatId) {
		this.subCatId = subCatId;
	}


	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getFrId() {
		return frId;
	}

	public void setFrId(int frId) {
		this.frId = frId;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getOrderQty() {
		return orderQty;
	}

	public void setOrderQty(int orderQty) {
		this.orderQty = orderQty;
	}


	public double getOrderRate() {
		return orderRate;
	}

	public void setOrderRate(double orderRate) {
		this.orderRate = orderRate;
	}

	public double getOrderMrp() {
		return orderMrp;
	}

	public void setOrderMrp(double orderMrp) {
		this.orderMrp = orderMrp;
	}

	public String getFrName() {
		return frName;
	}

	public void setFrName(String frName) {
		this.frName = frName;
	}

	public String getMenuTitle() {
		return menuTitle;
	}

	public void setMenuTitle(String menuTitle) {
		this.menuTitle = menuTitle;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getItemTax1() {
		return itemTax1;
	}

	public void setItemTax1(double itemTax1) {
		this.itemTax1 = itemTax1;
	}

	public double getItemTax2() {
		return itemTax2;
	}

	public void setItemTax2(double itemTax2) {
		this.itemTax2 = itemTax2;
	}

	public double getItemTax3() {
		return itemTax3;
	}

	public void setItemTax3(double itemTax3) {
		this.itemTax3 = itemTax3;
	}

	public int getIsPositive() {
		return isPositive;
	}

	public void setIsPositive(int isPositive) {
		this.isPositive = isPositive;
	}

	public int getIsSameState() {
		return isSameState;
	}

	public void setIsSameState(int isSameState) {
		this.isSameState = isSameState;
	}

	@Override
	public String toString() {
		return "GenerateBill [orderId=" + orderId + ", frId=" + frId + ", isPositive=" + isPositive + ", menuId="
				+ menuId + ", itemId=" + itemId + ", orderQty=" + orderQty + ", orderRate=" + orderRate + ", orderMrp="
				+ orderMrp + ", frName=" + frName + ", menuTitle=" + menuTitle + ", itemName=" + itemName + ", catId="
				+ catId + ", frCode=" + frCode + ", rateType=" + rateType + ", subCatId=" + subCatId + ", itemTax1="
				+ itemTax1 + ", itemTax2=" + itemTax2 + ", itemTax3=" + itemTax3 + ", grnType=" + grnType
				+ ", itemShelfLife=" + itemShelfLife + ", isSameState=" + isSameState + ", deliveryDate=" + deliveryDate
				+ "]";
	}

}
