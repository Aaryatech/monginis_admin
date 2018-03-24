package com.ats.adminpanel.model.purchaseorder;

import java.util.List;

import com.ats.adminpanel.model.ErrorMessage;


public class GetPurchaseOrder {
	
	@Override
	public String toString() {
		return "GetPurchaseOrder [getPurchaseOrderList=" + getPurchaseOrderList + ", errorMessage=" + errorMessage
				+ "]";
	}
	List<GetPurchaseOrderList> getPurchaseOrderList;
	ErrorMessage errorMessage;
	public List<GetPurchaseOrderList> getGetPurchaseOrderList() {
		return getPurchaseOrderList;
	}
	public void setGetPurchaseOrderList(List<GetPurchaseOrderList> getPurchaseOrderList) {
		this.getPurchaseOrderList = getPurchaseOrderList;
	}
	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(ErrorMessage errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	

}
