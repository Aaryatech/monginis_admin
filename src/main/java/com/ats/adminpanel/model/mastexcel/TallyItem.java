package com.ats.adminpanel.model.mastexcel;
 

public class TallyItem {

	private int id;

	private String itemName;

	private String itemGroup;

	private String subGroup;

	private String subSubGroup;

	private String hsnCode;

	private String uom;

	private float sgstPer;

	private float cgstPer;

	private float igstPer;

	private float cessPer;

	private int delStatus; 
	private float itemRate1; 
	private float itemRate2; 
	private float itemRate3; 
	private float itemMrp1; 
	private float itemMrp2; 
	private float itemMrp3; 
	private String itemCode;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
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

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	public float getItemRate1() {
		return itemRate1;
	}

	public void setItemRate1(float itemRate1) {
		this.itemRate1 = itemRate1;
	}

	public float getItemRate2() {
		return itemRate2;
	}

	public void setItemRate2(float itemRate2) {
		this.itemRate2 = itemRate2;
	}

	public float getItemRate3() {
		return itemRate3;
	}

	public void setItemRate3(float itemRate3) {
		this.itemRate3 = itemRate3;
	}

	public float getItemMrp1() {
		return itemMrp1;
	}

	public void setItemMrp1(float itemMrp1) {
		this.itemMrp1 = itemMrp1;
	}

	public float getItemMrp2() {
		return itemMrp2;
	}

	public void setItemMrp2(float itemMrp2) {
		this.itemMrp2 = itemMrp2;
	}

	public float getItemMrp3() {
		return itemMrp3;
	}

	public void setItemMrp3(float itemMrp3) {
		this.itemMrp3 = itemMrp3;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	@Override
	public String toString() {
		return "TallyItem [id=" + id + ", itemName=" + itemName + ", itemGroup=" + itemGroup + ", subGroup=" + subGroup
				+ ", subSubGroup=" + subSubGroup + ", hsnCode=" + hsnCode + ", uom=" + uom + ", sgstPer=" + sgstPer
				+ ", cgstPer=" + cgstPer + ", igstPer=" + igstPer + ", cessPer=" + cessPer + ", delStatus=" + delStatus
				+ ", itemRate1=" + itemRate1 + ", itemRate2=" + itemRate2 + ", itemRate3=" + itemRate3 + ", itemMrp1="
				+ itemMrp1 + ", itemMrp2=" + itemMrp2 + ", itemMrp3=" + itemMrp3 + ", itemCode=" + itemCode + "]";
	}
	
	

}
