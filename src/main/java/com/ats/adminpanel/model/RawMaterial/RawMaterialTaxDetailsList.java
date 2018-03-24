package com.ats.adminpanel.model.RawMaterial;

import java.util.List;
 

public class RawMaterialTaxDetailsList {
	
	List<RawMaterialTaxDetails> rawMaterialTaxDetailsList;
	
	Info info;

	public List<RawMaterialTaxDetails> getRawMaterialTaxDetailsList() {
		return rawMaterialTaxDetailsList;
	}

	public void setRawMaterialTaxDetailsList(List<RawMaterialTaxDetails> rawMaterialTaxDetailsList) {
		this.rawMaterialTaxDetailsList = rawMaterialTaxDetailsList;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "RawMaterialTaxDetailsList [rawMaterialTaxDetailsList=" + rawMaterialTaxDetailsList + ", info=" + info
				+ "]";
	}
	
	

}
