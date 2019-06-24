
package com.ats.adminpanel.model;

import java.util.List;

public class GetOrderListResponse {

	List<OrderItemSubCatTotal> orderItemSubCatTotalList;

	private Info info;

	private List<GetOrder> getOder = null;

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public List<GetOrder> getGetOder() {
		return getOder;
	}

	public void setGetOder(List<GetOrder> getOder) {
		this.getOder = getOder;
	}

	public List<OrderItemSubCatTotal> getOrderItemSubCatTotalList() {
		return orderItemSubCatTotalList;
	}

	public void setOrderItemSubCatTotalList(List<OrderItemSubCatTotal> orderItemSubCatTotalList) {
		this.orderItemSubCatTotalList = orderItemSubCatTotalList;
	}

	@Override
	public String toString() {
		return "GetOrderListResponse [orderItemSubCatTotalList=" + orderItemSubCatTotalList + ", info=" + info
				+ ", getOder=" + getOder + "]";
	}

}
