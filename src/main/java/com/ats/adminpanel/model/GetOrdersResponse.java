package com.ats.adminpanel.model;

import java.util.List;

public class GetOrdersResponse {
	
	List<OrderItemSubCatTotal> orderItemSubCatTotalList;
	
	List<GetOrder> orderList;

	public List<OrderItemSubCatTotal> getOrderItemSubCatTotalList() {
		return orderItemSubCatTotalList;
	}

	public List<GetOrder> getOrderList() {
		return orderList;
	}

	public void setOrderItemSubCatTotalList(List<OrderItemSubCatTotal> orderItemSubCatTotalList) {
		this.orderItemSubCatTotalList = orderItemSubCatTotalList;
	}

	public void setOrderList(List<GetOrder> orderList) {
		this.orderList = orderList;
	}

	@Override
	public String toString() {
		return "GetOrdersResponse [orderItemSubCatTotalList=" + orderItemSubCatTotalList + ", orderList=" + orderList
				+ "]";
	}

	
}
