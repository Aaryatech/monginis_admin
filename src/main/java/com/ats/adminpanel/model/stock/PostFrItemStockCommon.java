package com.ats.adminpanel.model.stock;

import java.util.List;

public class PostFrItemStockCommon {
	
	
	private List<PostFrItemStockHeader>frItemStockHeaders;

	
	
	
	public List<PostFrItemStockHeader> getFrItemStockHeaders() {
		return frItemStockHeaders;
	}

	public void setFrItemStockHeaders(List<PostFrItemStockHeader> frItemStockHeaders) {
		this.frItemStockHeaders = frItemStockHeaders;
	}

	
	
	
	
	@Override
	public String toString() {
		return "PostFrItemStockCommon [frItemStockHeaders=" + frItemStockHeaders + "]";
	}
	
	

}
