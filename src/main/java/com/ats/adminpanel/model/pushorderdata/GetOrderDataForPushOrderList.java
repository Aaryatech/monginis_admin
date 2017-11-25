package com.ats.adminpanel.model.pushorderdata;

import java.util.List;

import com.ats.adminpanel.model.Info;

public class GetOrderDataForPushOrderList {
	
	List<GetOrderDataForPushOrder> orderDataForPushOrder;
	
	
	Info info;


	public List<GetOrderDataForPushOrder> getOrderDataForPushOrder() {
		return orderDataForPushOrder;
	}


	public Info getInfo() {
		return info;
	}


	public void setOrderDataForPushOrder(List<GetOrderDataForPushOrder> orderDataForPushOrder) {
		this.orderDataForPushOrder = orderDataForPushOrder;
	}


	public void setInfo(Info info) {
		this.info = info;
	}


	@Override
	public String toString() {
		return "GetOrderDataForPushOrderList [orderDataForPushOrder=" + orderDataForPushOrder + ", info=" + info + "]";
	}
	
	

}
