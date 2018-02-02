package com.ats.adminpanel.model;



public class Variance {

	
		private int id;
		private String itemName;
		 private String itemId;
		private int spCakeQty;
		private int orderQty;
		 
		
		public int getSpCakeQty() {
			return spCakeQty;
		}
		public void setSpCakeQty(int spCakeQty) {
			this.spCakeQty = spCakeQty;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getItemId() {
			return itemId;
		}
		public void setItemId(String itemId) {
			this.itemId = itemId;
		}
		public String getItemName() {
			return itemName;
		}
		public void setItemName(String itemName) {
			this.itemName = itemName;
		}
		 
		public int getOrderQty() {
			return orderQty;
		}
		public void setOrderQty(int orderQty) {
			this.orderQty = orderQty;
		}
		@Override
		public String toString() {
			return "Variance [id=" + id + ", itemName=" + itemName + ", itemId=" + itemId + ", spCakeQty=" + spCakeQty
					+ ", orderQty=" + orderQty + "]";
		}
		
		
}
