package com.ats.adminpanel.model;
 
import com.fasterxml.jackson.annotation.JsonFormat;

public class CreditNote {
	
	 
	private int crndId; 
	private String crnNo; 
	private int vNo; 
	private String date; 
	private String vType; 
	private String partyName; 
	private String gstin; 
	private String state; 
	private String itemName; 
	private String hsnCode; 
	private String uom; 
	private int qty; 
	private float rate; 
	private float amount; 
	private float cgstPer; 
	private float sgstPer; 
	private float igstPer; 
	private float cessPer; 
	private float cgstRs; 
	private float sgstRs; 
	private float igstRs; 
	private float cessRs; 
	private float itemDiscPer; 
	private float totalDisc; 
	private float roundOff; 
	private float totalAmt; 
	private float billTotal; 
	private String refBillNo; 
	private String refBillDate; 
	private int grnGvnHeaderId; 
	private int isDeposited; 
	private String grngvnSrno; 
	private String crDbNo; 
	private String crDbDate; 
	private int frId; 
	private String frCode; 
	private int itemId; 
	private int catId; 
	private float crnTaxableAmt; 
	private float crnTotalTax; 
	private float crnGrandTotal; 
	private String itemcode;
	private int isGrn;
	private String erpLink;
	public int getCrndId() {
		return crndId;
	}
	public void setCrndId(int crndId) {
		this.crndId = crndId;
	}
	public String getCrnNo() {
		return crnNo;
	}
	public void setCrnNo(String crnNo) {
		this.crnNo = crnNo;
	}
	public int getvNo() {
		return vNo;
	}
	public void setvNo(int vNo) {
		this.vNo = vNo;
	}
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getvType() {
		return vType;
	}
	public void setvType(String vType) {
		this.vType = vType;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public String getGstin() {
		return gstin;
	}
	public void setGstin(String gstin) {
		this.gstin = gstin;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getHsnCode() {
		return hsnCode;
	}
	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public float getRate() {
		return rate;
	}
	public void setRate(float rate) {
		this.rate = rate;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
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
	public float getCessPer() {
		return cessPer;
	}
	public void setCessPer(float cessPer) {
		this.cessPer = cessPer;
	}
	public float getCgstRs() {
		return cgstRs;
	}
	public void setCgstRs(float cgstRs) {
		this.cgstRs = cgstRs;
	}
	public float getSgstRs() {
		return sgstRs;
	}
	public void setSgstRs(float sgstRs) {
		this.sgstRs = sgstRs;
	}
	public float getIgstRs() {
		return igstRs;
	}
	public void setIgstRs(float igstRs) {
		this.igstRs = igstRs;
	}
	public float getCessRs() {
		return cessRs;
	}
	public void setCessRs(float cessRs) {
		this.cessRs = cessRs;
	}
	public float getItemDiscPer() {
		return itemDiscPer;
	}
	public void setItemDiscPer(float itemDiscPer) {
		this.itemDiscPer = itemDiscPer;
	}
	public float getTotalDisc() {
		return totalDisc;
	}
	public void setTotalDisc(float totalDisc) {
		this.totalDisc = totalDisc;
	}
	public float getRoundOff() {
		return roundOff;
	}
	public void setRoundOff(float roundOff) {
		this.roundOff = roundOff;
	}
	public float getTotalAmt() {
		return totalAmt;
	}
	public void setTotalAmt(float totalAmt) {
		this.totalAmt = totalAmt;
	}
	public float getBillTotal() {
		return billTotal;
	}
	public void setBillTotal(float billTotal) {
		this.billTotal = billTotal;
	}
	public String getRefBillNo() {
		return refBillNo;
	}
	public void setRefBillNo(String refBillNo) {
		this.refBillNo = refBillNo;
	}
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public String getRefBillDate() {
		return refBillDate;
	}
	public void setRefBillDate(String refBillDate) {
		this.refBillDate = refBillDate;
	}
	public int getGrnGvnHeaderId() {
		return grnGvnHeaderId;
	}
	public void setGrnGvnHeaderId(int grnGvnHeaderId) {
		this.grnGvnHeaderId = grnGvnHeaderId;
	}
	public int getIsDeposited() {
		return isDeposited;
	}
	public void setIsDeposited(int isDeposited) {
		this.isDeposited = isDeposited;
	}
	public String getGrngvnSrno() {
		return grngvnSrno;
	}
	public void setGrngvnSrno(String grngvnSrno) {
		this.grngvnSrno = grngvnSrno;
	}
	public String getCrDbNo() {
		return crDbNo;
	}
	public void setCrDbNo(String crDbNo) {
		this.crDbNo = crDbNo;
	}
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public String getCrDbDate() {
		return crDbDate;
	}
	public void setCrDbDate(String crDbDate) {
		this.crDbDate = crDbDate;
	}
	
	public int getFrId() {
		return frId;
	}
	public void setFrId(int frId) {
		this.frId = frId;
	}
	public String getFrCode() {
		return frCode;
	}
	public void setFrCode(String frCode) {
		this.frCode = frCode;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getCatId() {
		return catId;
	}
	public void setCatId(int catId) {
		this.catId = catId;
	}
	public float getCrnTaxableAmt() {
		return crnTaxableAmt;
	}
	public void setCrnTaxableAmt(float crnTaxableAmt) {
		this.crnTaxableAmt = crnTaxableAmt;
	}
	public float getCrnTotalTax() {
		return crnTotalTax;
	}
	public void setCrnTotalTax(float crnTotalTax) {
		this.crnTotalTax = crnTotalTax;
	}
	public float getCrnGrandTotal() {
		return crnGrandTotal;
	}
	public void setCrnGrandTotal(float crnGrandTotal) {
		this.crnGrandTotal = crnGrandTotal;
	}
	
	public String getItemcode() {
		return itemcode;
	}
	public void setItemcode(String itemcode) {
		this.itemcode = itemcode;
	}
	
	public int getIsGrn() {
		return isGrn;
	}
	public void setIsGrn(int isGrn) {
		this.isGrn = isGrn;
	}
	
	
	public String getErpLink() {
		return erpLink;
	}
	public void setErpLink(String erpLink) {
		this.erpLink = erpLink;
	}
	@Override
	public String toString() {
		return "CreditNote [crndId=" + crndId + ", crnNo=" + crnNo + ", vNo=" + vNo + ", date=" + date + ", vType="
				+ vType + ", partyName=" + partyName + ", gstin=" + gstin + ", state=" + state + ", itemName="
				+ itemName + ", hsnCode=" + hsnCode + ", uom=" + uom + ", qty=" + qty + ", rate=" + rate + ", amount="
				+ amount + ", cgstPer=" + cgstPer + ", sgstPer=" + sgstPer + ", igstPer=" + igstPer + ", cessPer="
				+ cessPer + ", cgstRs=" + cgstRs + ", sgstRs=" + sgstRs + ", igstRs=" + igstRs + ", cessRs=" + cessRs
				+ ", itemDiscPer=" + itemDiscPer + ", totalDisc=" + totalDisc + ", roundOff=" + roundOff + ", totalAmt="
				+ totalAmt + ", billTotal=" + billTotal + ", refBillNo=" + refBillNo + ", refBillDate=" + refBillDate
				+ ", grnGvnHeaderId=" + grnGvnHeaderId + ", isDeposited=" + isDeposited + ", grngvnSrno=" + grngvnSrno
				+ ", crDbNo=" + crDbNo + ", crDbDate=" + crDbDate + ", frId=" + frId + ", frCode=" + frCode
				+ ", itemId=" + itemId + ", catId=" + catId + ", crnTaxableAmt=" + crnTaxableAmt + ", crnTotalTax="
				+ crnTotalTax + ", crnGrandTotal=" + crnGrandTotal + ", itemcode=" + itemcode + ", isGrn=" + isGrn
				+ ", erpLink=" + erpLink + "]";
	}
 
 
	

}
