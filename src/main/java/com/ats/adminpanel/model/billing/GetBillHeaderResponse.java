
package com.ats.adminpanel.model.billing;

import java.util.List;

import com.ats.adminpanel.model.Info;

public class GetBillHeaderResponse {

    private List<GetBillHeader> getBillHeaders = null;
    private Info info;

    public List<GetBillHeader> getGetBillHeaders() {
        return getBillHeaders;
    }

    public void setGetBillHeaders(List<GetBillHeader> getBillHeaders) {
        this.getBillHeaders = getBillHeaders;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

}
