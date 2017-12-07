package com.ats.adminpanel.model.purchaseorder;

import java.util.List;

import com.ats.adminpanel.model.ErrorMessage;



public class GetPurchaseOrderList {
	
	 private List<PurchaseOrderHeader> purchaseOrderHeaderList;
	 ErrorMessage errorMessage;
	public List<PurchaseOrderHeader> getPurchaseOrderHeaderList() {
		return purchaseOrderHeaderList;
	}
	public void setPurchaseOrderHeaderList(List<PurchaseOrderHeader> purchaseOrderHeaderList) {
		this.purchaseOrderHeaderList = purchaseOrderHeaderList;
	}
	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(ErrorMessage errorMessage) {
		this.errorMessage = errorMessage;
	}
	@Override
	public String toString() {
		return "GetPurchaseOrderList [purchaseOrderHeaderList=" + purchaseOrderHeaderList + ", errorMessage="
				+ errorMessage + "]";
	}
	
	 
	 

}
