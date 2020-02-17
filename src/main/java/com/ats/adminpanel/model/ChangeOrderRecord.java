package com.ats.adminpanel.model;

import java.io.Serializable;


public class ChangeOrderRecord  implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int changeId;
	private int userId;
	private String userName;
	private String dateTime;
	private String changeDate;
	
	private int changeQty;
	private int origQty;
	private int changeType;
	private String changeName;
	
	private long orderId;
	private int itemId;
	private String itemName;
	private int frId;
	private String frName;
	private String exVar1;
	
	
	private String deliveryDate;
	
	
	
	public String getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public int getChangeId() {
		return changeId;
	}
	public void setChangeId(int changeId) {
		this.changeId = changeId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public String getChangeDate() {
		return changeDate;
	}
	public void setChangeDate(String changeDate) {
		this.changeDate = changeDate;
	}
	public int getChangeQty() {
		return changeQty;
	}
	public void setChangeQty(int changeQty) {
		this.changeQty = changeQty;
	}
	public int getOrigQty() {
		return origQty;
	}
	public void setOrigQty(int origQty) {
		this.origQty = origQty;
	}
	public int getChangeType() {
		return changeType;
	}
	public void setChangeType(int changeType) {
		this.changeType = changeType;
	}
	public String getChangeName() {
		return changeName;
	}
	public void setChangeName(String changeName) {
		this.changeName = changeName;
	}
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getFrId() {
		return frId;
	}
	public void setFrId(int frId) {
		this.frId = frId;
	}
	public String getFrName() {
		return frName;
	}
	public void setFrName(String frName) {
		this.frName = frName;
	}
	public String getExVar1() {
		return exVar1;
	}
	public void setExVar1(String exVar1) {
		this.exVar1 = exVar1;
	}
	@Override
	public String toString() {
		return "ChangeOrderRecord [changeId=" + changeId + ", userId=" + userId + ", userName=" + userName
				+ ", dateTime=" + dateTime + ", changeDate=" + changeDate + ", changeQty=" + changeQty + ", origQty="
				+ origQty + ", changeType=" + changeType + ", changeName=" + changeName + ", orderId=" + orderId
				+ ", itemId=" + itemId + ", itemName=" + itemName + ", frId=" + frId + ", frName=" + frName
				+ ", exVar1=" + exVar1 + ", deliveryDate=" + deliveryDate + "]";
	}
	
	
}
