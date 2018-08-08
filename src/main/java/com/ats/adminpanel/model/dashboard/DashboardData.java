package com.ats.adminpanel.model.dashboard;


public class DashboardData{

	 PurBillBackEndReport daySale;
	
	  PurBillBackEndReport monthSale;
	
	 PurBillBackEndReport yearSale;
	 
	 CredNoteReport dayGrn;
	 
	 CredNoteReport monthGrn;
	 
	 CredNoteReport yearGrn;
	 
     CredNoteReport dayGvn;
	 
	 CredNoteReport monthGvn;
	 
	 CredNoteReport yearGvn;
	 
	 
	public CredNoteReport getDayGrn() {
		return dayGrn;
	}

	public CredNoteReport getMonthGrn() {
		return monthGrn;
	}

	public CredNoteReport getYearGrn() {
		return yearGrn;
	}

	public CredNoteReport getDayGvn() {
		return dayGvn;
	}

	public CredNoteReport getMonthGvn() {
		return monthGvn;
	}

	public CredNoteReport getYearGvn() {
		return yearGvn;
	}

	public void setDayGrn(CredNoteReport dayGrn) {
		this.dayGrn = dayGrn;
	}

	public void setMonthGrn(CredNoteReport monthGrn) {
		this.monthGrn = monthGrn;
	}

	public void setYearGrn(CredNoteReport yearGrn) {
		this.yearGrn = yearGrn;
	}

	public void setDayGvn(CredNoteReport dayGvn) {
		this.dayGvn = dayGvn;
	}

	public void setMonthGvn(CredNoteReport monthGvn) {
		this.monthGvn = monthGvn;
	}

	public void setYearGvn(CredNoteReport yearGvn) {
		this.yearGvn = yearGvn;
	}

	public PurBillBackEndReport getDaySale() {
		return daySale;
	}

	public PurBillBackEndReport getMonthSale() {
		return monthSale;
	}

	public PurBillBackEndReport getYearSale() {
		return yearSale;
	}

	public void setDaySale(PurBillBackEndReport daySale) {
		this.daySale = daySale;
	}

	public void setMonthSale(PurBillBackEndReport monthSale) {
		this.monthSale = monthSale;
	}

	public void setYearSale(PurBillBackEndReport yearSale) {
		this.yearSale = yearSale;
	}

	@Override
	public String toString() {
		return "DashboardData [daySale=" + daySale + ", monthSale=" + monthSale + ", yearSale=" + yearSale + ", dayGrn="
				+ dayGrn + ", monthGrn=" + monthGrn + ", yearGrn=" + yearGrn + ", dayGvn=" + dayGvn + ", monthGvn="
				+ monthGvn + ", yearGvn=" + yearGvn + "]";
	}

	
}
