package com.ats.adminpanel.model.productionplan;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;



public class MixingHeader {
	
	
	
	private int mixId;
	private Date mixDate;
	private int ProductionId;
	private String productionBatch;
	private int status;
	private int delStatus;
	private int timeSlot;
	private int isBom;
	private int exInt1;
	private int exInt2;
	private int exInt3;
	private String exVarchar1;
	private String exVarchar2;
	private String exVarchar3;
	private int exBool1;
	List<MixingDetailed> mixingDetailed;
	public int getMixId() {
		return mixId;
	}
	public void setMixId(int mixId) {
		this.mixId = mixId;
	}
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getMixDate() {
		return mixDate;
	}
	public void setMixDate(Date mixDate) {
		this.mixDate = mixDate;
	}
	public int getProductionId() {
		return ProductionId;
	}
	public void setProductionId(int productionId) {
		ProductionId = productionId;
	}
	public String getProductionBatch() {
		return productionBatch;
	}
	public void setProductionBatch(String productionBatch) {
		this.productionBatch = productionBatch;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}
	public int getTimeSlot() {
		return timeSlot;
	}
	public void setTimeSlot(int timeSlot) {
		this.timeSlot = timeSlot;
	}
	public int getIsBom() {
		return isBom;
	}
	public void setIsBom(int isBom) {
		this.isBom = isBom;
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
	public int getExInt3() {
		return exInt3;
	}
	public void setExInt3(int exInt3) {
		this.exInt3 = exInt3;
	}
	public String getExVarchar1() {
		return exVarchar1;
	}
	public void setExVarchar1(String exVarchar1) {
		this.exVarchar1 = exVarchar1;
	}
	public String getExVarchar2() {
		return exVarchar2;
	}
	public void setExVarchar2(String exVarchar2) {
		this.exVarchar2 = exVarchar2;
	}
	public String getExVarchar3() {
		return exVarchar3;
	}
	public void setExVarchar3(String exVarchar3) {
		this.exVarchar3 = exVarchar3;
	}
	public int getExBool1() {
		return exBool1;
	}
	public void setExBool1(int exBool1) {
		this.exBool1 = exBool1;
	}
	public List<MixingDetailed> getMixingDetailed() {
		return mixingDetailed;
	}
	public void setMixingDetailed(List<MixingDetailed> mixingDetailed) {
		this.mixingDetailed = mixingDetailed;
	}
	@Override
	public String toString() {
		return "MixingHeader [mixId=" + mixId + ", mixDate=" + mixDate + ", ProductionId=" + ProductionId
				+ ", productionBatch=" + productionBatch + ", status=" + status + ", delStatus=" + delStatus
				+ ", timeSlot=" + timeSlot + ", isBom=" + isBom + ", exInt1=" + exInt1 + ", exInt2=" + exInt2
				+ ", exInt3=" + exInt3 + ", exVarchar1=" + exVarchar1 + ", exVarchar2=" + exVarchar2 + ", exVarchar3="
				+ exVarchar3 + ", exBool1=" + exBool1 + ", mixingDetailed=" + mixingDetailed + "]";
	}
	
	

}
