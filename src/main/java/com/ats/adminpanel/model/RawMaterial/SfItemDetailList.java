package com.ats.adminpanel.model.RawMaterial;

import java.util.List;

public class SfItemDetailList {
	
	List<ItemSfDetail> sfItemDetail;

	public List<ItemSfDetail> getSfItemDetail() {
		return sfItemDetail;
	}

	public void setSfItemDetail(List<ItemSfDetail> sfItemDetail) {
		this.sfItemDetail = sfItemDetail;
	}

	@Override
	public String toString() {
		return "SfItemDetailList [sfItemDetail=" + sfItemDetail + "]";
	}

}
