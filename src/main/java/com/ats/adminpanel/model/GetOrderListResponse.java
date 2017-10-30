
package com.ats.adminpanel.model;

import java.util.List;

public class GetOrderListResponse {

   
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

}
