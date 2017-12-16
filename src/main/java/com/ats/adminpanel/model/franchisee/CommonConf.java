package com.ats.adminpanel.model.franchisee;

public class CommonConf {
private int id;
private String name;
private int qty;
private int rmUomId;

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
@Override
public String toString() {
	return "CommonConf [id=" + id + ", name=" + name + ", qty=" + qty + "]";
}


}
