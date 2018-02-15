package com.ats.adminpanel.model.logistics; 
public class Dealer {
	
	 
	private int dealerId;  
	private String dealerName;  
	private String dealerMobileNo; 
	private int makeId; 
	private String city; 
	private String dealerEmail;   
	private String contactPerson;  
	private String personMobileNo; 
	private String contactPersonEmail; 
	private int delStatus;
	public int getDealerId() {
		return dealerId;
	}
	public void setDealerId(int dealerId) {
		this.dealerId = dealerId;
	}
	public String getDealerName() {
		return dealerName;
	}
	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}
	public String getDealerMobileNo() {
		return dealerMobileNo;
	}
	public void setDealerMobileNo(String dealerMobileNo) {
		this.dealerMobileNo = dealerMobileNo;
	}
	public int getMakeId() {
		return makeId;
	}
	public void setMakeId(int makeId) {
		this.makeId = makeId;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDealerEmail() {
		return dealerEmail;
	}
	public void setDealerEmail(String dealerEmail) {
		this.dealerEmail = dealerEmail;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	public String getPersonMobileNo() {
		return personMobileNo;
	}
	public void setPersonMobileNo(String personMobileNo) {
		this.personMobileNo = personMobileNo;
	}
	public String getContactPersonEmail() {
		return contactPersonEmail;
	}
	public void setContactPersonEmail(String contactPersonEmail) {
		this.contactPersonEmail = contactPersonEmail;
	}
	public int getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}
	@Override
	public String toString() {
		return "Dealer [dealerId=" + dealerId + ", dealerName=" + dealerName + ", dealerMobileNo=" + dealerMobileNo
				+ ", makeId=" + makeId + ", city=" + city + ", dealerEmail=" + dealerEmail + ", contactPerson="
				+ contactPerson + ", personMobileNo=" + personMobileNo + ", contactPersonEmail=" + contactPersonEmail
				+ ", delStatus=" + delStatus + "]";
	}
	
	
	

}
