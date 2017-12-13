package com.ats.adminpanel.model.production.mixing.temp;

import java.util.List;

import com.ats.adminpanel.model.Info;

public class GetSFMixingForBomList {

	
List<GetSFMixingForBom> sFMixingForBom;
	
	Info info;

	public List<GetSFMixingForBom> getsFMixingForBom() {
		return sFMixingForBom;
	}

	public void setsFMixingForBom(List<GetSFMixingForBom> sFMixingForBom) {
		this.sFMixingForBom = sFMixingForBom;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "GetSFMixingForBomList [sFMixingForBom=" + sFMixingForBom + ", info=" + info + "]";
	}
	
	
	
}
