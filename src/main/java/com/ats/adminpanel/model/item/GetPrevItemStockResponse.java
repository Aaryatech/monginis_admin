
package com.ats.adminpanel.model.item;

import java.util.List;

public class GetPrevItemStockResponse {

    private Integer itemId;
    private String itemName;
    private List<StockDetail> stockDetails ;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public List<StockDetail> getStockDetails() {
        return stockDetails;
    }

    public void setStockDetails(List<StockDetail> stockDetails) {
        this.stockDetails = stockDetails;
    }

	@Override
	public String toString() {
		return "GetPrevItemStockResponse [itemId=" + itemId + ", itemName=" + itemName + ", stockDetails="
				+ stockDetails + "]";
	}

    
    
    
}
