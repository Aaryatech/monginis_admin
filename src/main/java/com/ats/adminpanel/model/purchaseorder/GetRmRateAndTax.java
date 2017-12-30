package com.ats.adminpanel.model.purchaseorder;

 

public class GetRmRateAndTax {

	
private int rmId;
	
	 
	private String rmName;
	
 
	private int rmUomId;

	
	 
	private float gstPer;
	
	 
	private float cgstPer;
	
	
	 
	private float sgstPer;
	
	 
	private float igstPer;
	
  
	 
	private String specification;
	
	
	 
	private float rateTaxExtra;
	
	 
	private float rateTaxIncl;


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


	public String getSpecification() {
		return specification;
	}


	public void setSpecification(String specification) {
		this.specification = specification;
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
		return "GetRmRateAndTax [rmId=" + rmId + ", rmName=" + rmName + ", rmUomId=" + rmUomId + ", gstPer=" + gstPer
				+ ", cgstPer=" + cgstPer + ", sgstPer=" + sgstPer + ", igstPer=" + igstPer + ", specification="
				+ specification + ", rateTaxExtra=" + rateTaxExtra + ", rateTaxIncl=" + rateTaxIncl + "]";
	}
	
	
}
