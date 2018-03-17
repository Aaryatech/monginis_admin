package com.ats.adminpanel.model.afe;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;


public class AfeQuestion {
	
	
	private int queId;

	private String question;
	
	private int queType;
	
	private int quePoint;
	
	private String queDate;
	
	private String catName;

	private int delStatus;

	private int exInt1;

	private int exInt2;

	private String exVar1;

	private String exVar2;

	public int getQueId() {
		return queId;
	}

	public void setQueId(int queId) {
		this.queId = queId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public int getQueType() {
		return queType;
	}

	public void setQueType(int queType) {
		this.queType = queType;
	}

	public int getQuePoint() {
		return quePoint;
	}

	public void setQuePoint(int quePoint) {
		this.quePoint = quePoint;
	}

	

	public String getQueDate() {
		return queDate;
	}

	public void setQueDate(String queDate) {
		this.queDate = queDate;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	public int getExInt1() {
		return exInt1;
	}

	public void setExInt1(int exInt1) {
		this.exInt1 = exInt1;
	}

	public int getExInt2() {
		return exInt2;
	}

	public void setExInt2(int exInt2) {
		this.exInt2 = exInt2;
	}

	public String getExVar1() {
		return exVar1;
	}

	public void setExVar1(String exVar1) {
		this.exVar1 = exVar1;
	}

	public String getExVar2() {
		return exVar2;
	}

	public void setExVar2(String exVar2) {
		this.exVar2 = exVar2;
	}

	@Override
	public String toString() {
		return "AfeQuestion [queId=" + queId + ", question=" + question + ", queType=" + queType + ", quePoint="
				+ quePoint + ", queDate=" + queDate + ", catName=" + catName + ", delStatus=" + delStatus + ", exInt1="
				+ exInt1 + ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + "]";
	}
	

}
