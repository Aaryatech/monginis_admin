
package com.ats.adminpanel.model;

import java.util.List;

public class SpCakeOrdersBeanResponse {

    private List<SpCakeOrdersBean> spCakeOrdersBean;
    private Info info;

    public List<SpCakeOrdersBean> getSpCakeOrdersBean() {
        return spCakeOrdersBean;
    }

    public void setSpCakeOrdersBean(List<SpCakeOrdersBean> spCakeOrdersBean) {
        this.spCakeOrdersBean = spCakeOrdersBean;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

}
