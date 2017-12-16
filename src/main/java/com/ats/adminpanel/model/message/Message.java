package com.ats.adminpanel.model.message;




public class Message {

  
    private int msgId;
 
    private String msgFrdt;
  
    private String msgTodt;
  
    private String msgImage;
  
    private String msgHeader;
   
    private String msgDetails;
 
    private int isActive;
 
    private int delStatus;

	public int getMsgId() {
		return msgId;
	}

	public void setMsgId(int msgId) {
		this.msgId = msgId;
	}

	public String getMsgFrdt() {
		return msgFrdt;
	}

	public void setMsgFrdt(String msgFrdt) {
		this.msgFrdt = msgFrdt;
	}

	public String getMsgTodt() {
		return msgTodt;
	}

	public void setMsgTodt(String msgTodt) {
		this.msgTodt = msgTodt;
	}

	public String getMsgImage() {
		return msgImage;
	}

	public void setMsgImage(String msgImage) {
		this.msgImage = msgImage;
	}

	public String getMsgHeader() {
		return msgHeader;
	}

	public void setMsgHeader(String msgHeader) {
		this.msgHeader = msgHeader;
	}

	public String getMsgDetails() {
		return msgDetails;
	}

	public void setMsgDetails(String msgDetails) {
		this.msgDetails = msgDetails;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	@Override
	public String toString() {
		return "Message [msgId=" + msgId + ", msgFrdt=" + msgFrdt + ", msgTodt=" + msgTodt + ", msgImage=" + msgImage
				+ ", msgHeader=" + msgHeader + ", msgDetails=" + msgDetails + ", isActive=" + isActive + ", delStatus="
				+ delStatus + "]";
	}

  
}