
package com.ats.adminpanel.model.item;


public class StockDetail {

    private Integer frStockId;
    private Integer type;
    private Integer minQty;
    private Integer maxQty;
    private Integer reorderQty;
    
    
    
    public Integer getReorderQty() {
		return reorderQty;
	}

	public void setReorderQty(Integer reorderQty) {
		this.reorderQty = reorderQty;
	}

	public Integer getFrStockId() {
        return frStockId;
    }

    public void setFrStockId(Integer frStockId) {
        this.frStockId = frStockId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getMinQty() {
        return minQty;
    }

    public void setMinQty(Integer minQty) {
        this.minQty = minQty;
    }

    public Integer getMaxQty() {
        return maxQty;
    }

    public void setMaxQty(Integer maxQty) {
        this.maxQty = maxQty;
    }

	@Override
	public String toString() {
		return "StockDetail [frStockId=" + frStockId + ", type=" + type + ", minQty=" + minQty + ", maxQty=" + maxQty
				+ ", reorderQty=" + reorderQty + "]";
	}

    
    
    
}
