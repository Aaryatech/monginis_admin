package com.ats.adminpanel.model.stock;

import java.sql.Date;
import java.util.List;

public class PostFrItemStockHeader {

	private int openingStockHeaderId;
	
	private int month;
	
	private int year;
	
	private Date fromDate;
	
	private Date toDate;
	
	private int frId;
	
	private int catId;
	
	private int isMonthClosed;

	private List<PostFrItemStockDetail>postFrItemStockDetailList;
	
	
	

	public int getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	public int getOpeningStockHeaderId() {
		return openingStockHeaderId;
	}

	public void setOpeningStockHeaderId(int openingStockHeaderId) {
		this.openingStockHeaderId = openingStockHeaderId;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}


	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public int getFrId() {
		return frId;
	}

	public void setFrId(int frId) {
		this.frId = frId;
	}

	public int getIsMonthClosed() {
		return isMonthClosed;
	}

	public void setIsMonthClosed(int isMonthClosed) {
		this.isMonthClosed = isMonthClosed;
	}

	public List<PostFrItemStockDetail> getPostFrItemStockDetailList() {
		return postFrItemStockDetailList;
	}

	public void setPostFrItemStockDetailList(List<PostFrItemStockDetail> postFrItemStockDetailList) {
		this.postFrItemStockDetailList = postFrItemStockDetailList;
	}

	@Override
	public String toString() {
		return "PostFrItemStockHeader [openingStockHeaderId=" + openingStockHeaderId + ", month=" + month + ", year="
				+ year + ", fromDate=" + fromDate + ", toDate=" + toDate + ", frId=" + frId + ", isMonthClosed="
				+ isMonthClosed + ", postFrItemStockDetailList=" + postFrItemStockDetailList + "]";
	}
	
	
	
}
