package com.ats.adminpanel.model;

import java.io.Serializable;
import java.util.Date;



public class GetConfiguredSpDayCk implements Serializable{

	
		private int spdayId;

		
		private String frId;


		private String itemId;

	
		private String orderFromDate;
		
		private String orderToDate;
		
		private String deliveryToDate;
		
		private String deliveryFromDate;

		private String spdayName;
		
		private String fromTime;
		
		private String toTime;

		private int menuId;
		
		private int catId;
		
		private int subCatId;
		
		
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

		private int delStatus;
		public int getDelStatus() {
			return delStatus;
		}

		public void setDelStatus(int delStatus) {
			this.delStatus = delStatus;
		}

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

		public void setItemId(String itemId) {
			this.itemId = itemId;
		}

		public String getSpdayName() {
			return spdayName;
		}

		public void setSpdayName(String spdayName) {
			this.spdayName = spdayName;
		}

		public String getFromTime() {
			return fromTime;
		}

		public void setFromTime(String fromTime) {
			this.fromTime = fromTime;
		}

		public String getToTime() {
			return toTime;
		}

		public void setToTime(String toTime) {
			this.toTime = toTime;
		}

		@Override
		public String toString() {
			return "GetConfiguredSpDayCk [spdayId=" + spdayId + ", frId=" + frId + ", itemId=" + itemId
					+ ", orderFromDate=" + orderFromDate + ", orderToDate=" + orderToDate + ", deliveryToDate="
					+ deliveryToDate + ", deliveryFromDate=" + deliveryFromDate + ", spdayName=" + spdayName
					+ ", fromTime=" + fromTime + ", toTime=" + toTime + ", menuId=" + menuId + ", catId=" + catId
					+ ", subCatId=" + subCatId + ", delStatus=" + delStatus + "]";
		}
		
	

}
