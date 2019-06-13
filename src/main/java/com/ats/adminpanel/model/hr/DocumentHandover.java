package com.ats.adminpanel.model.hr;

import java.io.Serializable;
import java.util.Date;


public class DocumentHandover implements Serializable{

	private int documentHandoverId;

	private int gatepassInwardId;
	private String handOverDate;
	private int fromUserId;
	private int toUserId;
	private String fromUserName;
	private String toUserName;
	private int status;
	private int delStatus;
	private int fromDepatrmentId;
	private String fromDepatrmentName;
	private int toDeptId;
	private String toDeptName;
	private int exInt1;
	private Integer exInt2;
	private Integer exInt3;
	private String exVar1;
	private String exVar2;
	private String exVar3;
	
	public int getDocumentHandoverId() {
		return documentHandoverId;
	}
	public void setDocumentHandoverId(int documentHandoverId) {
		this.documentHandoverId = documentHandoverId;
	}
	public int getGatepassInwardId() {
		return gatepassInwardId;
	}
	public void setGatepassInwardId(int gatepassInwardId) {
		this.gatepassInwardId = gatepassInwardId;
	}
	public String getHandOverDate() {
		return handOverDate;
	}
	public void setHandOverDate(String handOverDate) {
		this.handOverDate = handOverDate;
	}
	public int getFromUserId() {
		return fromUserId;
	}
	public void setFromUserId(int fromUserId) {
		this.fromUserId = fromUserId;
	}
	public int getToUserId() {
		return toUserId;
	}
	public void setToUserId(int toUserId) {
		this.toUserId = toUserId;
	}
	public String getFromUserName() {
		return fromUserName;
	}
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}
	public String getToUserName() {
		return toUserName;
	}
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
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
	public int getFromDepatrmentId() {
		return fromDepatrmentId;
	}
	public void setFromDepatrmentId(int fromDepatrmentId) {
		this.fromDepatrmentId = fromDepatrmentId;
	}
	public String getFromDepatrmentName() {
		return fromDepatrmentName;
	}
	public void setFromDepatrmentName(String fromDepatrmentName) {
		this.fromDepatrmentName = fromDepatrmentName;
	}
	public int getToDeptId() {
		return toDeptId;
	}
	public void setToDeptId(int toDeptId) {
		this.toDeptId = toDeptId;
	}
	public String getToDeptName() {
		return toDeptName;
	}
	public void setToDeptName(String toDeptName) {
		this.toDeptName = toDeptName;
	}
	public int getExInt1() {
		return exInt1;
	}
	public void setExInt1(int exInt1) {
		this.exInt1 = exInt1;
	}
	public Integer getExInt2() {
		return exInt2;
	}
	public void setExInt2(Integer exInt2) {
		this.exInt2 = exInt2;
	}
	public Integer getExInt3() {
		return exInt3;
	}
	public void setExInt3(Integer exInt3) {
		this.exInt3 = exInt3;
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
	public String getExVar3() {
		return exVar3;
	}
	public void setExVar3(String exVar3) {
		this.exVar3 = exVar3;
	}
	@Override
	public String toString() {
		return "DocumentHandover [documentHandoverId=" + documentHandoverId + ", gatepassInwardId=" + gatepassInwardId
				+ ", handOverDate=" + handOverDate + ", fromUserId=" + fromUserId + ", toUserId=" + toUserId
				+ ", fromUserName=" + fromUserName + ", toUserName=" + toUserName + ", status=" + status
				+ ", delStatus=" + delStatus + ", fromDepatrmentId=" + fromDepatrmentId + ", fromDepatrmentName="
				+ fromDepatrmentName + ", toDeptId=" + toDeptId + ", toDeptName=" + toDeptName + ", exInt1=" + exInt1
				+ ", exInt2=" + exInt2 + ", exInt3=" + exInt3 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2
				+ ", exVar3=" + exVar3 + "]";
	}
	
	

}
