package com.ats.adminpanel.model.purchaseorder;

import java.util.List;

import com.ats.adminpanel.model.ErrorMessage;


public class PurchaseOrderDetailedList {
	
	private List<PurchaseOrderDetail> purchaseOrderDetaillist;
	ErrorMessage errorMessage;
	
	public List<PurchaseOrderDetail> getPurchaseOrderDetaillist() {
		return purchaseOrderDetaillist;
	}
	public void setPurchaseOrderDetaillist(List<PurchaseOrderDetail> purchaseOrderDetaillist) {
		this.purchaseOrderDetaillist = purchaseOrderDetaillist;
	}
	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(ErrorMessage errorMessage) {
		this.errorMessage = errorMessage;
	}
	@Override
	public String toString() {
		return "PurchaseOrderDetailedList [purchaseOrderDetaillist=" + purchaseOrderDetaillist + ", errorMessage="
				+ errorMessage + "]";
	}

}
