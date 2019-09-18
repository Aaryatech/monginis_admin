package com.ats.adminpanel.model.RawMaterial;

public class GetItemSfHeader {
	
	private int sfId;

	private String sfName;
	
	private int sfUomId;
	
	private String sfType;
	
	private String sfTypeName;

	private float sfWeight;
	
	private int stockQty;
	
	private float minLevelQty;
	
	private float maxLevelQty;
	
	private float reorderLevelQty;
	
	private float mulFactor;
	
	private int delStatus;
	
    private int int1;//deptId
	
	private int int2;//issue Sequence No
	
	private String varchar1;//extra
	
	private String varchar2;//extra
	
	private int bool1;//extra
	
     
	public int getInt1() {
		return int1;
	}

	public int getInt2() {
		return int2;
	}

	public String getVarchar1() {
		return varchar1;
	}

	public String getVarchar2() {
		return varchar2;
	}

	public int getBool1() {
		return bool1;
	}

	public void setInt1(int int1) {
		this.int1 = int1;
	}

	public void setInt2(int int2) {
		this.int2 = int2;
	}

	public void setVarchar1(String varchar1) {
		this.varchar1 = varchar1;
	}

	public void setVarchar2(String varchar2) {
		this.varchar2 = varchar2;
	}

	public void setBool1(int bool1) {
		this.bool1 = bool1;
	}

	public float getMulFactor() {
		return mulFactor;
	}

	public void setMulFactor(float mulFactor) {
		this.mulFactor = mulFactor;
	}

	public String getSfTypeName() {
		return sfTypeName;
	}

	public void setSfTypeName(String sfTypeName) {
		this.sfTypeName = sfTypeName;
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

	@Override
	public String toString() {
		return "GetItemSfHeader [sfId=" + sfId + ", sfName=" + sfName + ", sfUomId=" + sfUomId + ", sfType=" + sfType
				+ ", sfTypeName=" + sfTypeName + ", sfWeight=" + sfWeight + ", stockQty=" + stockQty + ", minLevelQty="
				+ minLevelQty + ", maxLevelQty=" + maxLevelQty + ", reorderLevelQty=" + reorderLevelQty + ", mulFactor="
				+ mulFactor + ", delStatus=" + delStatus + ", int1=" + int1 + ", int2=" + int2 + ", varchar1="
				+ varchar1 + ", varchar2=" + varchar2 + ", bool1=" + bool1 + "]";
	}

}
