package com.ats.adminpanel.model.franchisee;

public class CommonConf {
private int id;
private String name;
private int qty;
private int rmUomId;



private float  curClosingQty;//new Field Added Sachin


private float curOpeQty;//new fiedl



public int getRmUomId() {
	return rmUomId;
}
public void setRmUomId(int rmUomId) {
	this.rmUomId = rmUomId;
}
public int getQty() {
	return qty;
}
public void setQty(int qty) {
	this.qty = qty;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public float getCurClosingQty() {
	return curClosingQty;
}
public void setCurClosingQty(float curClosingQty) {
	this.curClosingQty = curClosingQty;
}
public float getCurOpeQty() {
	return curOpeQty;
}
public void setCurOpeQty(float curOpeQty) {
	this.curOpeQty = curOpeQty;
}
@Override
public String toString() {
	return "CommonConf [id=" + id + ", name=" + name + ", qty=" + qty + ", rmUomId=" + rmUomId + ", curClosingQty="
			+ curClosingQty + ", curOpeQty=" + curOpeQty + "]";
}



}
