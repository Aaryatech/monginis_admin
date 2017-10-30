
package com.ats.adminpanel.model;

import java.util.Date;

public class Order {

   
    private int orderId;
   
    private Date orderDate;
    
    private int frId;
   
    private int orderType;
    
    private int orderSubType;
    
    private int refId;
    
    private String itemId;
    
    private int orderQty;
    
    private int orderRate;
    
    private int orderMrp;
   
    private int orderStatus;
    
    private String dateTime;
    
    private Date productionDate;
    
    private Date deliveryDate;
    
    private int isEdit;
    
    private int editQty;
   
    private int userId;
    
    private int isPositive;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public int getFrId() {
		return frId;
	}

	public void setFrId(int frId) {
		this.frId = frId;
	}

	public int getOrderType() {
		return orderType;
	}

	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}

	public int getOrderSubType() {
		return orderSubType;
	}

	public void setOrderSubType(int orderSubType) {
		this.orderSubType = orderSubType;
	}

	public int getRefId() {
		return refId;
	}

	public void setRefId(int refId) {
		this.refId = refId;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public int getOrderQty() {
		return orderQty;
	}

	public void setOrderQty(int orderQty) {
		this.orderQty = orderQty;
	}

	public int getOrderRate() {
		return orderRate;
	}

	public void setOrderRate(int orderRate) {
		this.orderRate = orderRate;
	}

	public int getOrderMrp() {
		return orderMrp;
	}

	public void setOrderMrp(int orderMrp) {
		this.orderMrp = orderMrp;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public Date getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public int getIsEdit() {
		return isEdit;
	}

	public void setIsEdit(int isEdit) {
		this.isEdit = isEdit;
	}

	public int getEditQty() {
		return editQty;
	}

	public void setEditQty(int editQty) {
		this.editQty = editQty;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getIsPositive() {
		return isPositive;
	}

	public void setIsPositive(int isPositive) {
		this.isPositive = isPositive;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderDate=" + orderDate + ", frId=" + frId + ", orderType=" + orderType
				+ ", orderSubType=" + orderSubType + ", refId=" + refId + ", itemId=" + itemId + ", orderQty="
				+ orderQty + ", orderRate=" + orderRate + ", orderMrp=" + orderMrp + ", orderStatus=" + orderStatus
				+ ", dateTime=" + dateTime + ", productionDate=" + productionDate + ", deliveryDate=" + deliveryDate
				+ ", isEdit=" + isEdit + ", editQty=" + editQty + ", userId=" + userId + ", isPositive=" + isPositive
				+ "]";
	}
    
}
