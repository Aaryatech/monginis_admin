package com.ats.adminpanel.model.RawMaterial;

import java.util.List;

 

public class GetRawMaterialDetailList {

	
	List<RawMaterialDetails> rawMaterialDetailsList;
	Info info;
	public List<RawMaterialDetails> getRawMaterialDetailsList() {
		return rawMaterialDetailsList;
	}
	public void setRawMaterialDetailsList(List<RawMaterialDetails> rawMaterialDetailsList) {
		this.rawMaterialDetailsList = rawMaterialDetailsList;
	}
	public Info getInfo() {
		return info;
	}
	public void setInfo(Info info) {
		this.info = info;
	}
	@Override
	public String toString() {
		return "GetRawMaterialDetailList [rawMaterialDetailsList=" + rawMaterialDetailsList + ", info=" + info + "]";
	}
	
}
