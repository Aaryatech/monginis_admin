
package com.ats.adminpanel.model.item;

import java.util.List;

import com.ats.adminpanel.model.Info;

public class FrItemStockConfiResponse {

    private List<FrItemStockConfigure> frItemStockConfigure ;
    private Info info;

    public List<FrItemStockConfigure> getFrItemStockConfigure() {
        return frItemStockConfigure;
    }

    public void setFrItemStockConfigure(List<FrItemStockConfigure> frItemStockConfigure) {
        this.frItemStockConfigure = frItemStockConfigure;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

}
