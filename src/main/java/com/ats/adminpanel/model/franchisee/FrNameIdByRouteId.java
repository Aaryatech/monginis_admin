
package com.ats.adminpanel.model.franchisee;


public class FrNameIdByRouteId {

    private Integer frId;
    private Integer frRouteId;
    private String frName;

    public Integer getFrId() {
        return frId;
    }

    public void setFrId(Integer frId) {
        this.frId = frId;
    }

    public Integer getFrRouteId() {
        return frRouteId;
    }

    public void setFrRouteId(Integer frRouteId) {
        this.frRouteId = frRouteId;
    }

    public String getFrName() {
        return frName;
    }

    public void setFrName(String frName) {
        this.frName = frName;
    }

	@Override
	public String toString() {
		return "FrNameIdByRouteId [frId=" + frId + ", frRouteId=" + frRouteId + ", frName=" + frName + "]";
	}

}
