package com.ats.adminpanel.model.hr;

import java.util.List;



public class MaterialGatepassDisplay {

	private int gatepassInwardId;

	private String inwardDate;
	private int gatePassType;
	private int gatePassSubType;
	private String invoiceNumber;
	private String partyName;
	private int partyId;
	private int securityId;
	private String securityName;
	private String personPhoto;
	private String inwardPhoto;
	private String inTime;
	private float noOfNugs;
	private int itemType;
	private int delStatus;
	private int status;
	private int toEmpId;
	private int toDeptId;
	private int toStatus;
	private String toEmpName;
	private String toDeptName;
	private Integer exInt1;
	private Integer exInt2;
	private Integer exInt3;
	private String exVar1;
	private String exVar2;
	private String exVar3;
	
	private List<DocumentHandover> docHandoverDetail;
	
	public int getGatepassInwardId() {
		return gatepassInwardId;
	}
	public void setGatepassInwardId(int gatepassInwardId) {
		this.gatepassInwardId = gatepassInwardId;
	}
	public String getInwardDate() {
		return inwardDate;
	}
	public void setInwardDate(String inwardDate) {
		this.inwardDate = inwardDate;
	}
	public int getGatePassType() {
		return gatePassType;
	}
	public void setGatePassType(int gatePassType) {
		this.gatePassType = gatePassType;
	}
	public int getGatePassSubType() {
		return gatePassSubType;
	}
	public void setGatePassSubType(int gatePassSubType) {
		this.gatePassSubType = gatePassSubType;
	}
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public int getPartyId() {
		return partyId;
	}
	public void setPartyId(int partyId) {
		this.partyId = partyId;
	}
	public int getSecurityId() {
		return securityId;
	}
	public void setSecurityId(int securityId) {
		this.securityId = securityId;
	}
	public String getSecurityName() {
		return securityName;
	}
	public void setSecurityName(String securityName) {
		this.securityName = securityName;
	}
	public String getPersonPhoto() {
		return personPhoto;
	}
	public void setPersonPhoto(String personPhoto) {
		this.personPhoto = personPhoto;
	}
	public String getInwardPhoto() {
		return inwardPhoto;
	}
	public void setInwardPhoto(String inwardPhoto) {
		this.inwardPhoto = inwardPhoto;
	}
	public String getInTime() {
		return inTime;
	}
	public void setInTime(String inTime) {
		this.inTime = inTime;
	}
	public float getNoOfNugs() {
		return noOfNugs;
	}
	public void setNoOfNugs(float noOfNugs) {
		this.noOfNugs = noOfNugs;
	}
	public int getItemType() {
		return itemType;
	}
	public void setItemType(int itemType) {
		this.itemType = itemType;
	}
	public int getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getToEmpId() {
		return toEmpId;
	}
	public void setToEmpId(int toEmpId) {
		this.toEmpId = toEmpId;
	}
	public int getToDeptId() {
		return toDeptId;
	}
	public void setToDeptId(int toDeptId) {
		this.toDeptId = toDeptId;
	}
	public int getToStatus() {
		return toStatus;
	}
	public void setToStatus(int toStatus) {
		this.toStatus = toStatus;
	}
	public String getToEmpName() {
		return toEmpName;
	}
	public void setToEmpName(String toEmpName) {
		this.toEmpName = toEmpName;
	}
	public String getToDeptName() {
		return toDeptName;
	}
	public void setToDeptName(String toDeptName) {
		this.toDeptName = toDeptName;
	}
	public Integer getExInt1() {
		return exInt1;
	}
	public void setExInt1(Integer exInt1) {
		this.exInt1 = exInt1;
	}
	public Integer getExInt2() {
		return exInt2;
	}
	public void setExInt2(Integer exInt2) {
		this.exInt2 = exInt2;
	}
	public Integer getExInt3() {
		return exInt3;
	}
	public void setExInt3(Integer exInt3) {
		this.exInt3 = exInt3;
	}
	public String getExVar1() {
		return exVar1;
	}
	public void setExVar1(String exVar1) {
		this.exVar1 = exVar1;
	}
	public String getExVar2() {
		return exVar2;
	}
	public void setExVar2(String exVar2) {
		this.exVar2 = exVar2;
	}
	public String getExVar3() {
		return exVar3;
	}
	public void setExVar3(String exVar3) {
		this.exVar3 = exVar3;
	}
	
	
	
	public List<DocumentHandover> getDocHandoverDetail() {
		return docHandoverDetail;
	}
	public void setDocHandoverDetail(List<DocumentHandover> docHandoverDetail) {
		this.docHandoverDetail = docHandoverDetail;
	}
	
	
	
	@Override
	public String toString() {
		return "MaterialGatepassDisplay [gatepassInwardId=" + gatepassInwardId + ", inwardDate=" + inwardDate
				+ ", gatePassType=" + gatePassType + ", gatePassSubType=" + gatePassSubType + ", invoiceNumber="
				+ invoiceNumber + ", partyName=" + partyName + ", partyId=" + partyId + ", securityId=" + securityId
				+ ", securityName=" + securityName + ", personPhoto=" + personPhoto + ", inwardPhoto=" + inwardPhoto
				+ ", inTime=" + inTime + ", noOfNugs=" + noOfNugs + ", itemType=" + itemType + ", delStatus="
				+ delStatus + ", status=" + status + ", toEmpId=" + toEmpId + ", toDeptId=" + toDeptId + ", toStatus="
				+ toStatus + ", toEmpName=" + toEmpName + ", toDeptName=" + toDeptName + ", exInt1=" + exInt1
				+ ", exInt2=" + exInt2 + ", exInt3=" + exInt3 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2
				+ ", exVar3=" + exVar3 + ", docHandoverDetail=" + docHandoverDetail + "]";
	}
	
	
	
}
