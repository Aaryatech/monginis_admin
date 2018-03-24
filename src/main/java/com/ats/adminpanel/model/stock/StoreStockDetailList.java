package com.ats.adminpanel.model.stock;

import java.util.List;

import com.ats.adminpanel.model.Info;

public class StoreStockDetailList {

	
	List<StoreStockDetail> storeStockDetailList;
	
	Info info;

	public List<StoreStockDetail> getStoreStockDetailList() {
		return storeStockDetailList;
	}

	public void setStoreStockDetailList(List<StoreStockDetail> storeStockDetailList) {
		this.storeStockDetailList = storeStockDetailList;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "StoreStockDetailList [storeStockDetailList=" + storeStockDetailList + ", info=" + info + "]";
	}
	
	
}
