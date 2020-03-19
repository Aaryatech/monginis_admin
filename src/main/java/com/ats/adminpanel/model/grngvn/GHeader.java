package com.ats.adminpanel.model.grngvn;

import java.util.Date;


public class GHeader {
	private int grnGvnHeaderId;

	private int frId;
	
	private String grngvnSrno;
	
	private String grngvnDate;
	
	
	private int isGrn;
	
	private String frName;

	
	
	public int getGrnGvnHeaderId() {
		return grnGvnHeaderId;
	}

	public void setGrnGvnHeaderId(int grnGvnHeaderId) {
		this.grnGvnHeaderId = grnGvnHeaderId;
	}

	public int getFrId() {
		return frId;
	}

	public void setFrId(int frId) {
		this.frId = frId;
	}

	public String getGrngvnSrno() {
		return grngvnSrno;
	}

	public void setGrngvnSrno(String grngvnSrno) {
		this.grngvnSrno = grngvnSrno;
	}

	public String getGrngvnDate() {
		return grngvnDate;
	}

	public void setGrngvnDate(String grngvnDate) {
		this.grngvnDate = grngvnDate;
	}

	public int getIsGrn() {
		return isGrn;
	}

	public void setIsGrn(int isGrn) {
		this.isGrn = isGrn;
	}

	public String getFrName() {
		return frName;
	}

	public void setFrName(String frName) {
		this.frName = frName;
	}

	@Override
	public String toString() {
		return "GHeader [grnGvnHeaderId=" + grnGvnHeaderId + ", frId=" + frId + ", grngvnSrno=" + grngvnSrno
				+ ", grngvnDate=" + grngvnDate + ", isGrn=" + isGrn + ", frName=" + frName + "]";
	}

	
	
}
