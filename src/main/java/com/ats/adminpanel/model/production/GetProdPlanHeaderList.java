package com.ats.adminpanel.model.production;

import java.util.List;

import com.ats.adminpanel.model.Info;

public class GetProdPlanHeaderList {
	
List<GetProdPlanHeader> prodPlanHeader;

Info info;

public List<GetProdPlanHeader> getProdPlanHeader() {
	return prodPlanHeader;
}

public void setProdPlanHeader(List<GetProdPlanHeader> prodPlanHeader) {
	this.prodPlanHeader = prodPlanHeader;
}

public Info getInfo() {
	return info;
}

public void setInfo(Info info) {
	this.info = info;
}



}
