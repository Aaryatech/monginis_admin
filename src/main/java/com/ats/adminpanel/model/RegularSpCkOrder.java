package com.ats.adminpanel.model;
public class RegularSpCkOrder {

private Integer rspId;
private String frName;
private String itemName;
private Integer id;
private String rspDeliveryDt;
private String rspEvents;
private String rspEventsName;
private Double rspSubTotal;
private Double tax1;
private Double tax1Amt;
private Double tax2;
private Double tax2Amt;
private Double rate;
private Double mrp;
private Integer qty;

public Integer getRspId() {
return rspId;
}

public void setRspId(Integer rspId) {
this.rspId = rspId;
}

public String getFrName() {
return frName;
}

public void setFrName(String frName) {
this.frName = frName;
}

public String getItemName() {
return itemName;
}

public void setItemName(String itemName) {
this.itemName = itemName;
}

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public String getRspDeliveryDt() {
return rspDeliveryDt;
}

public void setRspDeliveryDt(String rspDeliveryDt) {
this.rspDeliveryDt = rspDeliveryDt;
}

public String getRspEvents() {
return rspEvents;
}

public void setRspEvents(String rspEvents) {
this.rspEvents = rspEvents;
}

public String getRspEventsName() {
return rspEventsName;
}

public void setRspEventsName(String rspEventsName) {
this.rspEventsName = rspEventsName;
}


public Double getTax1() {
return tax1;
}

public void setTax1(Double tax1) {
this.tax1 = tax1;
}

public Double getTax1Amt() {
return tax1Amt;
}

public void setTax1Amt(Double tax1Amt) {
this.tax1Amt = tax1Amt;
}

public Double getTax2() {
return tax2;
}

public void setTax2(Double tax2) {
this.tax2 = tax2;
}

public Double getTax2Amt() {
return tax2Amt;
}

public void setTax2Amt(Double tax2Amt) {
this.tax2Amt = tax2Amt;
}

public Integer getQty() {
return qty;
}

public void setQty(Integer qty) {
this.qty = qty;
}

public Double getRspSubTotal() {
	return rspSubTotal;
}

public void setRspSubTotal(Double rspSubTotal) {
	this.rspSubTotal = rspSubTotal;
}

public Double getRate() {
	return rate;
}

public void setRate(Double rate) {
	this.rate = rate;
}

public Double getMrp() {
	return mrp;
}

public void setMrp(Double mrp) {
	this.mrp = mrp;
}

@Override
public String toString() {
	return "RegularSpCkOrder [rspId=" + rspId + ", frName=" + frName + ", itemName=" + itemName + ", id=" + id
			+ ", rspDeliveryDt=" + rspDeliveryDt + ", rspEvents=" + rspEvents + ", rspEventsName=" + rspEventsName
			+ ", rspSubTotal=" + rspSubTotal + ", tax1=" + tax1 + ", tax1Amt=" + tax1Amt + ", tax2=" + tax2
			+ ", tax2Amt=" + tax2Amt + ", rate=" + rate + ", mrp=" + mrp + ", qty=" + qty + "]";
}

}