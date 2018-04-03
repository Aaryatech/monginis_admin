package com.ats.adminpanel.model.RawMaterial;

public class RawMaterialTaxDetails {

	private int taxId;
	

	private String taxDesc;
	
	private float sgstPer;
	
	private float cgstPer;
	
	private float igstPer;

	private int delStatus;
	
	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	public int getTaxId() {
		return taxId;
	}

	public void setTaxId(int taxId) {
		this.taxId = taxId;
	}

	public String getTaxDesc() {
		return taxDesc;
	}

	public void setTaxDesc(String taxDesc) {
		this.taxDesc = taxDesc;
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

	@Override
	public String toString() {
		return "RawMaterialTaxDetails [taxId=" + taxId + ", taxDesc=" + taxDesc + ", sgstPer=" + sgstPer + ", cgstPer="
				+ cgstPer + ", igstPer=" + igstPer + "]";
	}
	
	
}
