package com.ats.adminpanel.model.purchaseorder;

import java.io.Serializable;


public class PoDetailsByChkSupp implements Serializable{

	
	private int suppId;
	
	private int rmId;
	 
	private String rmName;
	 
	private int rmUomId;
	 
	private String specification;
	 	 
	private int poQty;
	 
	private float discPer;
	 
	private float gstPer;
	 
	private float cgstPer;
	 
	private float sgstPer;
	 
	private float igstPer;

	private int schDays;
	 	
	private float rateTaxExtra;
	 
	private float rateTaxIncl;

	public int getSuppId() {
		return suppId;
	}

	public void setSuppId(int suppId) {
		this.suppId = suppId;
	}

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

	public int getRmUomId() {
		return rmUomId;
	}

	public void setRmUomId(int rmUomId) {
		this.rmUomId = rmUomId;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}


	public int getPoQty() {
		return poQty;
	}

	public void setPoQty(int poQty) {
		this.poQty = poQty;
	}

	public float getDiscPer() {
		return discPer;
	}

	public void setDiscPer(float discPer) {
		this.discPer = discPer;
	}

	public float getGstPer() {
		return gstPer;
	}

	public void setGstPer(float gstPer) {
		this.gstPer = gstPer;
	}

	public float getCgstPer() {
		return cgstPer;
	}

	public void setCgstPer(float cgstPer) {
		this.cgstPer = cgstPer;
	}

	public float getSgstPer() {
		return sgstPer;
	}

	public void setSgstPer(float sgstPer) {
		this.sgstPer = sgstPer;
	}

	public float getIgstPer() {
		return igstPer;
	}

	public void setIgstPer(float igstPer) {
		this.igstPer = igstPer;
	}


	public int getSchDays() {
		return schDays;
	}

	public void setSchDays(int schDays) {
		this.schDays = schDays;
	}

	public float getRateTaxExtra() {
		return rateTaxExtra;
	}

	public void setRateTaxExtra(float rateTaxExtra) {
		this.rateTaxExtra = rateTaxExtra;
	}

	public float getRateTaxIncl() {
		return rateTaxIncl;
	}

	public void setRateTaxIncl(float rateTaxIncl) {
		this.rateTaxIncl = rateTaxIncl;
	}

	@Override
	public String toString() {
		return "PoDetailsByChkSupp [suppId=" + suppId + ", rmId=" + rmId + ", rmName=" + rmName + ", rmUomId=" + rmUomId
				+ ", specification=" + specification + ", poQty=" + poQty + ", discPer="
				+ discPer + ", gstPer=" + gstPer + ", cgstPer=" + cgstPer + ", sgstPer=" + sgstPer + ", igstPer="
				+ igstPer + ", schDays=" + schDays + ", rateTaxExtra=" + rateTaxExtra
				+ ", rateTaxIncl=" + rateTaxIncl + "]";
	}
	
	
}
