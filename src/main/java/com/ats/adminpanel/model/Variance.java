package com.ats.adminpanel.model;



public class Variance {

	
		private int id;
		private String itemName;
		 private String itemId;
		private int spCakeQty;
		private int orderQty;
		
		private float  curClosingQty; 
		 private float curOpeQty;//new fiedl
		 private int remainingQty;//new fiedl
		 
		 
		
		public int getRemainingQty() {
			return remainingQty;
		}
		public void setRemainingQty(int remainingQty) {
			this.remainingQty = remainingQty;
		}
		public float getCurClosingQty() {
			return curClosingQty;
		}
		public void setCurClosingQty(float curClosingQty) {
			this.curClosingQty = curClosingQty;
		}
		public float getCurOpeQty() {
			return curOpeQty;
		}
		public void setCurOpeQty(float curOpeQty) {
			this.curOpeQty = curOpeQty;
		}
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
					+ ", orderQty=" + orderQty + ", curClosingQty=" + curClosingQty + ", curOpeQty=" + curOpeQty
					+ ", remainingQty=" + remainingQty + "]";
		}
		
		
}
