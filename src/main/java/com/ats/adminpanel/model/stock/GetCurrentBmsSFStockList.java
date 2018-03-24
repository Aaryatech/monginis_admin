package com.ats.adminpanel.model.stock;

import java.util.List;

import com.ats.adminpanel.model.Info;

public class GetCurrentBmsSFStockList {
	
	List<GetCurrentBmsSFStock> currentBmsSFStock;
	
	Info info;

	public List<GetCurrentBmsSFStock> getCurrentBmsSFStock() {
		return currentBmsSFStock;
	}

	public void setCurrentBmsSFStock(List<GetCurrentBmsSFStock> currentBmsSFStock) {
		this.currentBmsSFStock = currentBmsSFStock;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "GetCurrentBmsSFStockList [currentBmsSFStock=" + currentBmsSFStock + ", info=" + info + "]";
	}
	
	

}
