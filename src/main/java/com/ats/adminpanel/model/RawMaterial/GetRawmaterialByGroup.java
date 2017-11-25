package com.ats.adminpanel.model.RawMaterial;
 

public class GetRawmaterialByGroup {

	
	private int rmId;
	 
	private String rmName;
	 
	private int grpId; 
	
	private String grpName;
	 
	private int catId;
	 
	private String catName;
	 
	private int subCatId;
	 
	private String sunCatName;
	 
	private int rmPackQty;
	
  
	private int rmRate;
	 
	private int taxId;
	 
	private int rmIsCritical;

	public int getRmId() {
		return rmId;
	}

	public void setRmId(int rmId) {
		this.rmId = rmId;
	}

	public String getRmName() {
		return rmName;
	}

	public void setRmName(String rmName) {
		this.rmName = rmName;
	}

	public int getGrpId() {
		return grpId;
	}

	public void setGrpId(int grpId) {
		this.grpId = grpId;
	}

	public String getGrpName() {
		return grpName;
	}

	public void setGrpName(String grpName) {
		this.grpName = grpName;
	}

	public int getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public int getSubCatId() {
		return subCatId;
	}

	public void setSubCatId(int subCatId) {
		this.subCatId = subCatId;
	}

	public String getSunCatName() {
		return sunCatName;
	}

	public void setSunCatName(String sunCatName) {
		this.sunCatName = sunCatName;
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

	public int getTaxId() {
		return taxId;
	}

	public void setTaxId(int taxId) {
		this.taxId = taxId;
	}

	public int getRmIsCritical() {
		return rmIsCritical;
	}

	public void setRmIsCritical(int rmIsCritical) {
		this.rmIsCritical = rmIsCritical;
	}

	@Override
	public String toString() {
		return "GetRawmaterialByGroup [rmId=" + rmId + ", rmName=" + rmName + ", grpId=" + grpId + ", grpName="
				+ grpName + ", catId=" + catId + ", catName=" + catName + ", subCatId=" + subCatId + ", sunCatName="
				+ sunCatName + ", rmPackQty=" + rmPackQty + ", rmRate=" + rmRate + ", taxId=" + taxId
				+ ", rmIsCritical=" + rmIsCritical + "]";
	}
	
	
}
