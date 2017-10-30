
package com.ats.adminpanel.model;

import java.util.List;

public class OrderCountsResponse {

    @Override
	public String toString() {
		return "OrderCountsResponse [orderCount=" + orderCount + ", info=" + info + "]";
	}

	private List<OrderCount> orderCount = null;
    private Info info;

    public List<OrderCount> getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(List<OrderCount> orderCount) {
        this.orderCount = orderCount;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

}
