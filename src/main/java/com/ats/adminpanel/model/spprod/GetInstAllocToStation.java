package com.ats.adminpanel.model.spprod;

import java.io.Serializable;


public class GetInstAllocToStation implements Serializable{


	private int instAllocId;

	private int stId;
	
	private String stName;
	

	private String instId;

	private int delStatus;

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

	public String getStName() {
		return stName;
	}

	public void setStName(String stName) {
		this.stName = stName;
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
		return "GetInstAllocToStation [instAllocId=" + instAllocId + ", stId=" + stId + ", stName=" + stName
				+ ", instId=" + instId + ", delStatus=" + delStatus + "]";
	}
	
	
}
