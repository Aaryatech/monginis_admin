package com.ats.adminpanel.model;



public class GateEntryAdd {
	
	private String docRef;
	private String docDate;
	private int supplier;
	private String vehicalNo;
	private int lrNo;
	private int transporter;
	private String description;
	private int noOfItems;
	private String remark;
	private int totQty;
	private int dcQty;
	private int revQty;
	private String image1;
	private String image2;
	
	
	public String getDocRef() {
		return docRef;
	}
	public void setDocRef(String docRef) {
		this.docRef = docRef;
	}
	public String getDocDate() {
		return docDate;
	}
	public void setDocDate(String docDate) {
		this.docDate = docDate;
	}
	public int getSupplier() {
		return supplier;
	}
	public void setSupplier(int Supplier) {
		this.supplier = Supplier;
	}
	public String getVehicalNo() {
		return vehicalNo;
	}
	public void setVehicalNo(String VehicalNo) {
		this.vehicalNo = VehicalNo;
	}
	public int getLrNo() {
		return lrNo;
	}
	public void setLrNo(int lrNo) {
		this.lrNo = lrNo;
	}
	public int getTransporter() {
		return transporter;
	}
	public void setTransporter(int transporter) {
		this.transporter = transporter;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getNoOfItems() {
		return noOfItems;
	}
	public void setNoOfItems(int noOfItems) {
		this.noOfItems = noOfItems;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getTotQty() {
		return totQty;
	}
	public void setTotQty(int totQty) {
		this.totQty = totQty;
	}
	public int getDcQty() {
		return dcQty;
	}
	public void setDcQty(int dcQty) {
		this.dcQty = dcQty;
	}
	public int getRevQty() {
		return revQty;
	}
	public void setRevQty(int revQty) {
		this.revQty = revQty;
	}
	public String getImage1() {
		return image1;
	}
	public void setImage1(String image1) {
		this.image1 = image1;
	}
	public String getImage2() {
		return image2;
	}
	public void setImage2(String image2) {
		this.image2 = image2;
	}
	@Override
	public String toString() {
		return "GateEntryAddList [docRef=" + docRef + ", docDate=" + docDate + ", supplier=" + supplier + ", vehicalNo="
				+ vehicalNo + ", lrNo=" + lrNo + ", transporter=" + transporter + ", description=" + description
				+ ", noOfItems=" + noOfItems + ", remark=" + remark + ", totQty=" + totQty + ", dcQty=" + dcQty
				+ ", revQty=" + revQty + ", image1=" + image1 + ", image2=" + image2 + "]";
	}
	
	
	
}
