package com.ats.adminpanel.model;

import java.io.Serializable;

public class StockDate implements Serializable{

	private int id;
	
	private String date1;
	
	private String date2;
	
	private String date3;
	
	private String date4;
	
	private String date5;

	public String getDate5() {
		return date5;
	}

	public void setDate5(String date5) {
		this.date5 = date5;
	}

	public int getId() {
		return id;
	}

	public String getDate1() {
		return date1;
	}

	public String getDate2() {
		return date2;
	}

	public String getDate3() {
		return date3;
	}

	public String getDate4() {
		return date4;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDate1(String date1) {
		this.date1 = date1;
	}

	public void setDate2(String date2) {
		this.date2 = date2;
	}

	public void setDate3(String date3) {
		this.date3 = date3;
	}

	public void setDate4(String date4) {
		this.date4 = date4;
	}

	@Override
	public String toString() {
		return "StockDate [id=" + id + ", date1=" + date1 + ", date2=" + date2 + ", date3=" + date3 + ", date4=" + date4
				+ "]";
	}
	
	
}
