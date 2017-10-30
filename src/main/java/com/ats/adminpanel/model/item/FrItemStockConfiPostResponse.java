
package com.ats.adminpanel.model.item;

import java.util.List;

import com.ats.adminpanel.model.Info;

public class FrItemStockConfiPostResponse {

    private List<FrItemStockConfigurePost> frItemStockConfigurePosts ;
    private Info info;

    public List<FrItemStockConfigurePost> getFrItemStockConfigurePosts() {
        return frItemStockConfigurePosts;
    }

    public void setFrItemStockConfigurePosts(List<FrItemStockConfigurePost> frItemStockConfigurePosts) {
        this.frItemStockConfigurePosts = frItemStockConfigurePosts;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

}
