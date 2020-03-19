package com.ats.adminpanel.model.ggreports;

public class GGDetailApr {

	int grnGvnId;

	private int billNo;

	private int frId;

	private int itemId;

	private int grnGvnQty;
	private int aprQtyAcc;
	
	private String aprDateGate;
	private String aprDateStore;
	private String aprDateAcc;
	
	private String itemName;
	
	private String approvedDatetimeGate;
	private String approvedDatetimeStore;
	private String approvedDatetimeAcc;
	
	private String gate;
	private String store ;
	private String acc ;
	
	private int status;
	private int isGrn;
	
	
	public int getIsGrn() {
		return isGrn;
	}
	public void setIsGrn(int isGrn) {
		this.isGrn = isGrn;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getGrnGvnId() {
		return grnGvnId;
	}
	public void setGrnGvnId(int grnGvnId) {
		this.grnGvnId = grnGvnId;
	}
	public int getBillNo() {
		return billNo;
	}
	public void setBillNo(int billNo) {
		this.billNo = billNo;
	}
	public int getFrId() {
		return frId;
	}
	public void setFrId(int frId) {
		this.frId = frId;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getGrnGvnQty() {
		return grnGvnQty;
	}
	public void setGrnGvnQty(int grnGvnQty) {
		this.grnGvnQty = grnGvnQty;
	}
	public int getAprQtyAcc() {
		return aprQtyAcc;
	}
	public void setAprQtyAcc(int aprQtyAcc) {
		this.aprQtyAcc = aprQtyAcc;
	}
	public String getAprDateGate() {
		return aprDateGate;
	}
	public void setAprDateGate(String aprDateGate) {
		this.aprDateGate = aprDateGate;
	}
	public String getAprDateStore() {
		return aprDateStore;
	}
	public void setAprDateStore(String aprDateStore) {
		this.aprDateStore = aprDateStore;
	}
	public String getAprDateAcc() {
		return aprDateAcc;
	}
	public void setAprDateAcc(String aprDateAcc) {
		this.aprDateAcc = aprDateAcc;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public String getGate() {
		return gate;
	}
	public void setGate(String gate) {
		this.gate = gate;
	}
	public String getStore() {
		return store;
	}
	public void setStore(String store) {
		this.store = store;
	}
	public String getAcc() {
		return acc;
	}
	public void setAcc(String acc) {
		this.acc = acc;
	}
	public String getApprovedDatetimeGate() {
		return approvedDatetimeGate;
	}
	public void setApprovedDatetimeGate(String approvedDatetimeGate) {
		this.approvedDatetimeGate = approvedDatetimeGate;
	}
	public String getApprovedDatetimeStore() {
		return approvedDatetimeStore;
	}
	public void setApprovedDatetimeStore(String approvedDatetimeStore) {
		this.approvedDatetimeStore = approvedDatetimeStore;
	}
	public String getApprovedDatetimeAcc() {
		return approvedDatetimeAcc;
	}
	public void setApprovedDatetimeAcc(String approvedDatetimeAcc) {
		this.approvedDatetimeAcc = approvedDatetimeAcc;
	}
	@Override
	public String toString() {
		return "GGDetailApr [grnGvnId=" + grnGvnId + ", billNo=" + billNo + ", frId=" + frId + ", itemId=" + itemId
				+ ", grnGvnQty=" + grnGvnQty + ", aprQtyAcc=" + aprQtyAcc + ", aprDateGate=" + aprDateGate
				+ ", aprDateStore=" + aprDateStore + ", aprDateAcc=" + aprDateAcc + ", itemName=" + itemName
				+ ", approvedDatetimeGate=" + approvedDatetimeGate + ", approvedDatetimeStore=" + approvedDatetimeStore
				+ ", approvedDatetimeAcc=" + approvedDatetimeAcc + ", gate=" + gate + ", store=" + store + ", acc="
				+ acc + ", status=" + status + ", isGrn=" + isGrn + "]";
	}
	


}
