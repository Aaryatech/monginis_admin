package com.ats.adminpanel.model;



public class Variance {

	
		private int id;
		private String itemName;
		 private String itemId;
		private int prodRejectedQty;
		private int orderQty;
		
		
		
		
		 
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
		public int getProdRejectedQty() {
			return prodRejectedQty;
		}
		public void setProdRejectedQty(int prodRejectedQty) {
			this.prodRejectedQty = prodRejectedQty;
		}
		public int getOrderQty() {
			return orderQty;
		}
		public void setOrderQty(int orderQty) {
			this.orderQty = orderQty;
		}
		@Override
		public String toString() {
			return "Variance [id=" + id + ", itemName=" + itemName + ", itemId=" + itemId + ", prodRejectedQty="
					+ prodRejectedQty + ", orderQty=" + orderQty + "]";
		}
		
		
		
}
