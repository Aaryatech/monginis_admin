package com.ats.adminpanel.model;

public class GetRmData {
	
  private int rmId;
  
  private int rmType;
  
  private float rmQty;

public int getRmId() {
	return rmId;
}

public int getRmType() {
	return rmType;
}

public float getRmQty() {
	return rmQty;
}

public void setRmId(int rmId) {
	this.rmId = rmId;
}

public void setRmType(int rmType) {
	this.rmType = rmType;
}

public void setRmQty(float rmQty) {
	this.rmQty = rmQty;
}

@Override
public String toString() {
	return "GetRmData [rmId=" + rmId + ", rmType=" + rmType + ", rmQty=" + rmQty + "]";
}
  
  

}
