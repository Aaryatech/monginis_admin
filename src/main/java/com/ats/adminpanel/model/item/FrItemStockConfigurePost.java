
package com.ats.adminpanel.model.item;


public class FrItemStockConfigurePost {

    private Integer frStockId;
    private Integer itemId;
    private Integer type;
    private Integer minQty;
    private Integer maxQty;

    public Integer getFrStockId() {
        return frStockId;
    }

    public void setFrStockId(Integer frStockId) {
        this.frStockId = frStockId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
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
		return "FrItemStockConfigurePost [frStockId=" + frStockId + ", itemId=" + itemId + ", type=" + type
				+ ", minQty=" + minQty + ", maxQty=" + maxQty + "]";
	}
    
    

}
