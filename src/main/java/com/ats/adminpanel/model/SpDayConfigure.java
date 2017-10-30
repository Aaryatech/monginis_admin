package com.ats.adminpanel.model;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;


public class SpDayConfigure implements Serializable{

	
	private int spdayId;

	
	private String frId;

	
	private String itemId;

	
	private String orderFromDate;
	
	
	private String orderToDate;
	
	
	private String deliveryToDate;
	
	
	private String deliveryFromDate;

	
	
	private String spdayName;
	
	
	
	private Time fromTime;
	
	
	private Time toTime;
	
	
	
	private int delStatus;
	
	
	private int menuId;
	
	
	private int catId;
	
	
	private int subCatId;

	public int getSpdayId() {
		return spdayId;
	}

	public void setSpdayId(int spdayId) {
		this.spdayId = spdayId;
	}

	public String getFrId() {
		return frId;
	}

	public void setFrId(String frId) {
		this.frId = frId;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	
	public String getOrderFromDate() {
		return orderFromDate;
	}

	public void setOrderFromDate(String orderFromDate) {
		this.orderFromDate = orderFromDate;
	}

	public String getOrderToDate() {
		return orderToDate;
	}

	public void setOrderToDate(String orderToDate) {
		this.orderToDate = orderToDate;
	}

	public String getDeliveryToDate() {
		return deliveryToDate;
	}

	public void setDeliveryToDate(String deliveryToDate) {
		this.deliveryToDate = deliveryToDate;
	}

	public String getDeliveryFromDate() {
		return deliveryFromDate;
	}

	public void setDeliveryFromDate(String deliveryFromDate) {
		this.deliveryFromDate = deliveryFromDate;
	}

	public String getSpdayName() {
		return spdayName;
	}

	public void setSpdayName(String spdayName) {
		this.spdayName = spdayName;
	}

	

	public Time getFromTime() {
		return fromTime;
	}

	public void setFromTime(Time fromTime) {
		this.fromTime = fromTime;
	}

	public Time getToTime() {
		return toTime;
	}

	public void setToTime(Time toTime) {
		this.toTime = toTime;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public int getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	public int getSubCatId() {
		return subCatId;
	}

	public void setSubCatId(int subCatId) {
		this.subCatId = subCatId;
	}

	@Override
	public String toString() {
		return "SpDayConfigure [spdayId=" + spdayId + ", frId=" + frId + ", itemId=" + itemId + ", orderFromDate="
				+ orderFromDate + ", orderToDate=" + orderToDate + ", deliveryToDate=" + deliveryToDate
				+ ", deliveryFromDate=" + deliveryFromDate + ", spdayName=" + spdayName + ", fromTime=" + fromTime
				+ ", toTime=" + toTime + ", delStatus=" + delStatus + ", menuId=" + menuId + ", catId=" + catId
				+ ", subCatId=" + subCatId + "]";
	}

	
	
	
	
}
