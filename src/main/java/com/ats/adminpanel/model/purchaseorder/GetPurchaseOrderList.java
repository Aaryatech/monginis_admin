package com.ats.adminpanel.model.purchaseorder;

import java.util.List;



public class GetPurchaseOrderList {
	
	 private PurchaseOrderHeader purchaseOrderHeader;
	 List<PurchaseOrderDetail> purchaseOrderDetaillist;
	public PurchaseOrderHeader getPurchaseOrderHeader() {
		return purchaseOrderHeader;
	}
	public void setPurchaseOrderHeader(PurchaseOrderHeader purchaseOrderHeader) {
		this.purchaseOrderHeader = purchaseOrderHeader;
	}
	public List<PurchaseOrderDetail> getPurchaseOrderDetaillist() {
		return purchaseOrderDetaillist;
	}
	public void setPurchaseOrderDetaillist(List<PurchaseOrderDetail> purchaseOrderDetaillist) {
		this.purchaseOrderDetaillist = purchaseOrderDetaillist;
	}
	 
	 

}
