package com.ats.adminpanel.model.RawMaterial;

import java.util.List;

public class ItemSfHeader{
	

	private int sfId;

	private String sfName;
	
	private int sfUomId;
	
	private String sfType;

	private float sfWeight;
	
	private int stockQty;
	
	private float minLevelQty;
	
	private float maxLevelQty;
	
	private float reorderLevelQty;
	
	private int delStatus;

	private float mulFactor;
	
	
	
	@Override
	public String toString() {
		return "ItemSfHeader [sfId=" + sfId + ", sfName=" + sfName + ", sfUomId=" + sfUomId + ", sfType=" + sfType
				+ ", sfWeight=" + sfWeight + ", stockQty=" + stockQty + ", minLevelQty=" + minLevelQty
				+ ", maxLevelQty=" + maxLevelQty + ", reorderLevelQty=" + reorderLevelQty + ", delStatus=" + delStatus
				+ ", mulFactor=" + mulFactor + "]";
	}

	public int getSfId() {
		return sfId;
	}

	public void setSfId(int sfId) {
		this.sfId = sfId;
	}

	public String getSfName() {
		return sfName;
	}

	public void setSfName(String sfName) {
		this.sfName = sfName;
	}


	public int getSfUomId() {
		return sfUomId;
	}

	public void setSfUomId(int sfUomId) {
		this.sfUomId = sfUomId;
	}


	public int getStockQty() {
		return stockQty;
	}

	public void setStockQty(int stockQty) {
		this.stockQty = stockQty;
	}

	public float getMinLevelQty() {
		return minLevelQty;
	}

	public void setMinLevelQty(float minLevelQty) {
		this.minLevelQty = minLevelQty;
	}

	public float getMaxLevelQty() {
		return maxLevelQty;
	}

	public void setMaxLevelQty(float maxLevelQty) {
		this.maxLevelQty = maxLevelQty;
	}

	public float getReorderLevelQty() {
		return reorderLevelQty;
	}

	public void setReorderLevelQty(float reorderLevelQty) {
		this.reorderLevelQty = reorderLevelQty;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	public String getSfType() {
		return sfType;
	}

	public void setSfType(String sfType) {
		this.sfType = sfType;
	}

	public float getSfWeight() {
		return sfWeight;
	}

	public void setSfWeight(float sfWeight) {
		this.sfWeight = sfWeight;
	}

	public float getMulFactor() {
		return mulFactor;
	}

	public void setMulFactor(float mulFactor) {
		this.mulFactor = mulFactor;
	}

	
}
