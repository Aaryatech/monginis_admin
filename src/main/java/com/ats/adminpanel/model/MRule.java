package com.ats.adminpanel.model;
 
import com.fasterxml.jackson.annotation.JsonFormat;
 

public class MRule {
	 
	private int ruleId; 
	private String fileName; 
	private String date; 
	private int status;
	public int getRuleId() {
		return ruleId;
	}
	public void setRuleId(int ruleId) {
		this.ruleId = ruleId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "MRule [ruleId=" + ruleId + ", fileName=" + fileName + ", date=" + date + ", status=" + status + "]";
	}
	
	

}
