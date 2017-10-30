
package com.ats.adminpanel.model.franchisee;

import java.util.List;

import com.ats.adminpanel.model.Info;

public class FrNameIdByRouteIdResponse {

    private List<FrNameIdByRouteId> frNameIdByRouteIds = null;
    private Info info;

    public List<FrNameIdByRouteId> getFrNameIdByRouteIds() {
        return frNameIdByRouteIds;
    }

    public void setFrNameIdByRouteIds(List<FrNameIdByRouteId> frNameIdByRouteIds) {
        this.frNameIdByRouteIds = frNameIdByRouteIds;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

}
