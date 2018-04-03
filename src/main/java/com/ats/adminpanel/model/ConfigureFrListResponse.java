
package com.ats.adminpanel.model;

import java.util.List;

public class ConfigureFrListResponse {

    private List<ConfigureFrBean> configureFrBean = null;
    private Object info;

    public List<ConfigureFrBean> getConfigureFrBean() {
        return configureFrBean;
    }

    public void setConfigureFrBean(List<ConfigureFrBean> configureFrBean) {
        this.configureFrBean = configureFrBean;
    }

    public Object getInfo() {
        return info;
    }

    public void setInfo(Object info) {
        this.info = info;
    }

	@Override
	public String toString() {
		return "ConfigureFrListResponse [configureFrBean=" + configureFrBean + ", info=" + info + "]";
	}

}
