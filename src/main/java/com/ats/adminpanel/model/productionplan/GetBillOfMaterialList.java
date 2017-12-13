package com.ats.adminpanel.model.productionplan;

import java.util.List;

import com.ats.adminpanel.model.ErrorMessage;



public class GetBillOfMaterialList {

	
	private List<BillOfMaterialHeader> billOfMaterialHeader;
	private ErrorMessage errorMessage;
	public List<BillOfMaterialHeader> getBillOfMaterialHeader() {
		return billOfMaterialHeader;
	}
	public void setBillOfMaterialHeader(List<BillOfMaterialHeader> billOfMaterialHeader) {
		this.billOfMaterialHeader = billOfMaterialHeader;
	}
	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(ErrorMessage errorMessage) {
		this.errorMessage = errorMessage;
	}
	@Override
	public String toString() {
		return "GetBillOfMaterialList [billOfMaterialHeader=" + billOfMaterialHeader + ", errorMessage=" + errorMessage
				+ "]";
	}
	
}
