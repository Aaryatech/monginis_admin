package com.ats.adminpanel.model.production;

import java.util.List;

import com.ats.adminpanel.model.Info;

public class GetProdPlanDetailList {
	
	List<GetProdPlanDetail> prodPlanDetail;
	
	Info info;

	public List<GetProdPlanDetail> getProdPlanDetail() {
		return prodPlanDetail;
	}

	public void setProdPlanDetail(List<GetProdPlanDetail> prodPlanDetail) {
		this.prodPlanDetail = prodPlanDetail;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "GetProdPlanDetailList [prodPlanDetail=" + prodPlanDetail + ", info=" + info + "]";
	}
	
}
