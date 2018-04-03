package com.ats.adminpanel.model.production.mixing.temp;

import java.util.List;

import com.ats.adminpanel.model.Info;
//same bean used for Bom and mixing
public class GetSFPlanDetailForMixingList {
	
List<GetSFPlanDetailForMixing> sfPlanDetailForMixing;
	
	Info info;

	public List<GetSFPlanDetailForMixing> getSfPlanDetailForMixing() {
		return sfPlanDetailForMixing;
	}

	public void setSfPlanDetailForMixing(List<GetSFPlanDetailForMixing> sfPlanDetailForMixing) {
		this.sfPlanDetailForMixing = sfPlanDetailForMixing;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "GetSFPlanDetailForMixingList [sfPlanDetailForMixing=" + sfPlanDetailForMixing + ", info=" + info + "]";
	}
	

}
