package com.ats.adminpanel.model.mastexcel;
 
//used by tally then for sp cale mas export to excel mspcake insert bean is different
public class SpecialCake {
	
	private int id;
	
	private String ItemName;

	private String itemGroup;
	
	private String subGroup;
	
	private String subSubGroup;
	
	private String hsnCode;
	
	private String uom;
	
	private float sgstPer;
	
	private float cgstPer;
	
	private float igstPer;

	private float cessPer;
	
	private String spCode; 
	private float spRate1; 
	private float spRate2; 
	private float spRate3; 
	private float mrpRate1; 
	private float mrpRate2; 
	private float mrpRate3;
	private String erpLinkCode;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getItemName() {
		return ItemName;
	}

	public void setItemName(String itemName) {
		ItemName = itemName;
	}

	public String getItemGroup() {
		return itemGroup;
	}

	public void setItemGroup(String itemGroup) {
		this.itemGroup = itemGroup;
	}

	public String getSubGroup() {
		return subGroup;
	}

	public void setSubGroup(String subGroup) {
		this.subGroup = subGroup;
	}

	public String getSubSubGroup() {
		return subSubGroup;
	}

	public void setSubSubGroup(String subSubGroup) {
		this.subSubGroup = subSubGroup;
	}

	public String getHsnCode() {
		return hsnCode;
	}

	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public float getSgstPer() {
		return sgstPer;
	}

	public void setSgstPer(float sgstPer) {
		this.sgstPer = sgstPer;
	}

	public float getCgstPer() {
		return cgstPer;
	}

	public void setCgstPer(float cgstPer) {
		this.cgstPer = cgstPer;
	}

	public float getIgstPer() {
		return igstPer;
	}

	public void setIgstPer(float igstPer) {
		this.igstPer = igstPer;
	}

	public float getCessPer() {
		return cessPer;
	}

	public void setCessPer(float cessPer) {
		this.cessPer = cessPer;
	}

	public String getSpCode() {
		return spCode;
	}

	public void setSpCode(String spCode) {
		this.spCode = spCode;
	}

	public float getSpRate1() {
		return spRate1;
	}

	public void setSpRate1(float spRate1) {
		this.spRate1 = spRate1;
	}

	public float getSpRate2() {
		return spRate2;
	}

	public void setSpRate2(float spRate2) {
		this.spRate2 = spRate2;
	}

	public float getSpRate3() {
		return spRate3;
	}

	public void setSpRate3(float spRate3) {
		this.spRate3 = spRate3;
	}

	public float getMrpRate1() {
		return mrpRate1;
	}

	public void setMrpRate1(float mrpRate1) {
		this.mrpRate1 = mrpRate1;
	}

	public float getMrpRate2() {
		return mrpRate2;
	}

	public void setMrpRate2(float mrpRate2) {
		this.mrpRate2 = mrpRate2;
	}

	public float getMrpRate3() {
		return mrpRate3;
	}

	public void setMrpRate3(float mrpRate3) {
		this.mrpRate3 = mrpRate3;
	}

	public String getErpLinkCode() {
		return erpLinkCode;
	}

	public void setErpLinkCode(String erpLinkCode) {
		this.erpLinkCode = erpLinkCode;
	}

	@Override
	public String toString() {
		return "SpecialCake [id=" + id + ", ItemName=" + ItemName + ", itemGroup=" + itemGroup + ", subGroup="
				+ subGroup + ", subSubGroup=" + subSubGroup + ", hsnCode=" + hsnCode + ", uom=" + uom + ", sgstPer="
				+ sgstPer + ", cgstPer=" + cgstPer + ", igstPer=" + igstPer + ", cessPer=" + cessPer + ", spCode="
				+ spCode + ", spRate1=" + spRate1 + ", spRate2=" + spRate2 + ", spRate3=" + spRate3 + ", mrpRate1="
				+ mrpRate1 + ", mrpRate2=" + mrpRate2 + ", mrpRate3=" + mrpRate3 + ", erpLinkCode=" + erpLinkCode + "]";
	}
	
}
