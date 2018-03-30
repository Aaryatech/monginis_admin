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

	@Override
	public String toString() {
		return "SpecialCake [id=" + id + ", ItemName=" + ItemName + ", itemGroup=" + itemGroup + ", subGroup="
				+ subGroup + ", subSubGroup=" + subSubGroup + ", hsnCode=" + hsnCode + ", uom=" + uom + ", sgstPer="
				+ sgstPer + ", cgstPer=" + cgstPer + ", igstPer=" + igstPer + ", cessPer=" + cessPer + "]";
	}
	
}
