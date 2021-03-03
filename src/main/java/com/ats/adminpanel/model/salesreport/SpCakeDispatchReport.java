package com.ats.adminpanel.model.salesreport;

public class SpCakeDispatchReport {
	
private String frCode;
private String frName;
private int spId;
private String srNo;
private String spDeliveryPlace;

public String getFrCode() {
	return frCode;
}
public void setFrCode(String frCode) {
	this.frCode = frCode;
}
public String getFrName() {
	return frName;
}
public void setFrName(String frName) {
	this.frName = frName;
}
public int getSpId() {
	return spId;
}
public void setSpId(int spId) {
	this.spId = spId;
}
public String getSrNo() {
	return srNo;
}
public void setSrNo(String srNo) {
	this.srNo = srNo;
}
public String getSpDeliveryPlace() {
	return spDeliveryPlace;
}
public void setSpDeliveryPlace(String spDeliveryPlace) {
	this.spDeliveryPlace = spDeliveryPlace;
}
@Override
public String toString() {
	return "SpCakeDispatchReport [frCode=" + frCode + ", frName=" + frName + ", spId=" + spId + ", srNo=" + srNo
			+ ", spDeliveryPlace=" + spDeliveryPlace + "]";
}
 

}
