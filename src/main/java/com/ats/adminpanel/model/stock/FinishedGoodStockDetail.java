package com.ats.adminpanel.model.stock;

import java.util.Date;

public class FinishedGoodStockDetail {

	int finStockDetailId;

	int itemId;

	Date stockDate;

	String itemName;

	float opT1;

	float opT2;

	float opT3;

	float opTotal;

	float prodQty;

	float rejQty;

	float frSaleQty;

	float gateSaleQty;

	float cloT1;

	float cloT2;

	float cloT3;

	float cloCurrent;

	float totalCloStk;

	public int getFinStockDetailId() {
		return finStockDetailId;
	}

	public void setFinStockDetailId(int finStockDetailId) {
		this.finStockDetailId = finStockDetailId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public Date getStockDate() {
		return stockDate;
	}

	public void setStockDate(Date stockDate) {
		this.stockDate = stockDate;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public float getOpT1() {
		return opT1;
	}

	public void setOpT1(float opT1) {
		this.opT1 = opT1;
	}

	public float getOpT2() {
		return opT2;
	}

	public void setOpT2(float opT2) {
		this.opT2 = opT2;
	}

	public float getOpT3() {
		return opT3;
	}

	public void setOpT3(float opT3) {
		this.opT3 = opT3;
	}

	public float getOpTotal() {
		return opTotal;
	}

	public void setOpTotal(float opTotal) {
		this.opTotal = opTotal;
	}

	public float getProdQty() {
		return prodQty;
	}

	public void setProdQty(float prodQty) {
		this.prodQty = prodQty;
	}

	public float getRejQty() {
		return rejQty;
	}

	public void setRejQty(float rejQty) {
		this.rejQty = rejQty;
	}

	public float getFrSaleQty() {
		return frSaleQty;
	}

	public void setFrSaleQty(float frSaleQty) {
		this.frSaleQty = frSaleQty;
	}

	public float getGateSaleQty() {
		return gateSaleQty;
	}

	public void setGateSaleQty(float gateSaleQty) {
		this.gateSaleQty = gateSaleQty;
	}

	public float getCloT1() {
		return cloT1;
	}

	public void setCloT1(float cloT1) {
		this.cloT1 = cloT1;
	}

	public float getCloT2() {
		return cloT2;
	}

	public void setCloT2(float cloT2) {
		this.cloT2 = cloT2;
	}

	public float getCloT3() {
		return cloT3;
	}

	public void setCloT3(float cloT3) {
		this.cloT3 = cloT3;
	}

	public float getCloCurrent() {
		return cloCurrent;
	}

	public void setCloCurrent(float cloCurrent) {
		this.cloCurrent = cloCurrent;
	}

	public float getTotalCloStk() {
		return totalCloStk;
	}

	public void setTotalCloStk(float totalCloStk) {
		this.totalCloStk = totalCloStk;
	}

}
