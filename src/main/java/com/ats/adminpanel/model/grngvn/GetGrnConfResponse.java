
package com.ats.adminpanel.model.grngvn;

import java.util.List;

import com.ats.adminpanel.model.Info;

public class GetGrnConfResponse {

    private List<GetGrnItemConfig> getGrnItemConfigs = null;
    private Info info;

    public List<GetGrnItemConfig> getGetGrnItemConfigs() {
        return getGrnItemConfigs;
    }

    public void setGetGrnItemConfigs(List<GetGrnItemConfig> getGrnItemConfigs) {
        this.getGrnItemConfigs = getGrnItemConfigs;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

	@Override
	public String toString() {
		return "GetGrnConfResponse [getGrnItemConfigs=" + getGrnItemConfigs + ", info=" + info + "]";
	}

    
    
    
}
