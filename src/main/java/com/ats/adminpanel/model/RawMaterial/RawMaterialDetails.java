package com.ats.adminpanel.model.RawMaterial;

 

public class RawMaterialDetails {
private int rmId;

private String rmName;
	 
	private String rmCode;
	 
	private int rmUomId;
	 
	private String rmSpecification;
	 
	private String rmIcon;
	
	private int subCatId;
	
	private int catId;
	
	private int grpId;
	 
	private int rmWeight;
	 
	private int rmPackQty;
	
  
	private int rmRate;
	 
	private int rmTaxId;
	 
	private int rmMinQty;
	
	 
	private int rmMaxQty;
	 
	private int rmRolQty;
	 
	private int rmOpRate;
	 
	private int rmOpQty;
	 
	private int rmReceivedQty;
	 
	private int rmIssQty;
	 
	private int rmRejQty;
	 
	private int rmCloQty;
	 
	private int rmIsCritical;
	 
	private int isTallySync;
	 
	private int delStatus;

	
	
	public int getIsTallySync() {
		return isTallySync;
	}

	public void setIsTallySync(int isTallySync) {
		this.isTallySync = isTallySync;
	}

	public int getRmId() {
		return rmId;
	}

	public void setRmId(int rmId) {
		this.rmId = rmId;
	}

	public String getRmCode() {
		return rmCode;
	}

	public void setRmCode(String rmCode) {
		this.rmCode = rmCode;
	}

	 

	public String getRmSpecification() {
		return rmSpecification;
	}

	public void setRmSpecification(String rmSpecification) {
		this.rmSpecification = rmSpecification;
	}

	public String getRmIcon() {
		return rmIcon;
	}

	public void setRmIcon(String rmIcon) {
		this.rmIcon = rmIcon;
	}

	public int getRmWeight() {
		return rmWeight;
	}

	public void setRmWeight(int rmWeight) {
		this.rmWeight = rmWeight;
	}

	public int getRmPackQty() {
		return rmPackQty;
	}

	public void setRmPackQty(int rmPackQty) {
		this.rmPackQty = rmPackQty;
	}

	public int getRmRate() {
		return rmRate;
	}

	public void setRmRate(int rmRate) {
		this.rmRate = rmRate;
	}

	 

	public int getRmMinQty() {
		return rmMinQty;
	}

	public void setRmMinQty(int rmMinQty) {
		this.rmMinQty = rmMinQty;
	}

	public int getRmMaxQty() {
		return rmMaxQty;
	}

	public void setRmMaxQty(int rmMaxQty) {
		this.rmMaxQty = rmMaxQty;
	}

	public int getRmRolQty() {
		return rmRolQty;
	}

	public void setRmRolQty(int rmRolQty) {
		this.rmRolQty = rmRolQty;
	}

	public int getRmOpRate() {
		return rmOpRate;
	}

	public void setRmOpRate(int rmOpRate) {
		this.rmOpRate = rmOpRate;
	}

	public int getRmOpQty() {
		return rmOpQty;
	}

	public void setRmOpQty(int rmOpQty) {
		this.rmOpQty = rmOpQty;
	}

	 

	public int getRmIssQty() {
		return rmIssQty;
	}

	public void setRmIssQty(int rmIssQty) {
		this.rmIssQty = rmIssQty;
	}

	public int getRmRejQty() {
		return rmRejQty;
	}

	public void setRmRejQty(int rmRejQty) {
		this.rmRejQty = rmRejQty;
	}

	public int getRmCloQty() {
		return rmCloQty;
	}

	public void setRmCloQty(int rmCloQty) {
		this.rmCloQty = rmCloQty;
	}

	public int getRmIsCritical() {
		return rmIsCritical;
	}

	public void setRmIsCritical(int rmIsCritical) {
		this.rmIsCritical = rmIsCritical;
	}

	 

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	public int getRmReceivedQty() {
		return rmReceivedQty;
	}

	public void setRmReceivedQty(int rmReceivedQty) {
		this.rmReceivedQty = rmReceivedQty;
	}

	public String getRmName() {
		return rmName;
	}

	public void setRmName(String rmName) {
		this.rmName = rmName;
	}

	public int getSubCatId() {
		return subCatId;
	}

	public void setSubCatId(int subCatId) {
		this.subCatId = subCatId;
	}

	public int getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	public int getGrpId() {
		return grpId;
	}

	public void setGrpId(int grpId) {
		this.grpId = grpId;
	}

	 

	public int getRmTaxId() {
		return rmTaxId;
	}

	public void setRmTaxId(int rmTaxId) {
		this.rmTaxId = rmTaxId;
	}

	public int getRmUomId() {
		return rmUomId;
	}

	public void setRmUomId(int rmUomId) {
		this.rmUomId = rmUomId;
	}

	@Override
	public String toString() {
		return "RawMaterialDetails [rmId=" + rmId + ", rmName=" + rmName + ", rmCode=" + rmCode + ", rmUomId=" + rmUomId
				+ ", rmSpecification=" + rmSpecification + ", rmIcon=" + rmIcon + ", subCatId=" + subCatId + ", catId="
				+ catId + ", grpId=" + grpId + ", rmWeight=" + rmWeight + ", rmPackQty=" + rmPackQty + ", rmRate="
				+ rmRate + ", rmTaxId=" + rmTaxId + ", rmMinQty=" + rmMinQty + ", rmMaxQty=" + rmMaxQty + ", rmRolQty="
				+ rmRolQty + ", rmOpRate=" + rmOpRate + ", rmOpQty=" + rmOpQty + ", rmReceivedQty=" + rmReceivedQty
				+ ", rmIssQty=" + rmIssQty + ", rmRejQty=" + rmRejQty + ", rmCloQty=" + rmCloQty + ", rmIsCritical="
				+ rmIsCritical + ", delStatus=" + delStatus + "]";
	}

	 

 
	 
 
	 
	 
	
	
}