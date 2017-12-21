package com.ats.adminpanel.model.spprod;

import java.io.Serializable;


public class InstAllocToStation{


	private boolean isError;
	
	private String message;
	
	private int instAllocId;
	
	private int stId;
	
	private String instId;
	
	private int delStatus;

	public boolean isError() {
		return isError;
	}

	public void setError(boolean isError) {
		this.isError = isError;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getInstAllocId() {
		return instAllocId;
	}

	public void setInstAllocId(int instAllocId) {
		this.instAllocId = instAllocId;
	}

	public int getStId() {
		return stId;
	}

	public void setStId(int stId) {
		this.stId = stId;
	}

	public String getInstId() {
		return instId;
	}

	public void setInstId(String instId) {
		this.instId = instId;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	@Override
	public String toString() {
		return "InstAllocToStation [isError=" + isError + ", message=" + message + ", instAllocId=" + instAllocId
				+ ", stId=" + stId + ", instId=" + instId + ", delStatus=" + delStatus + "]";
	}
	
	
	
}
