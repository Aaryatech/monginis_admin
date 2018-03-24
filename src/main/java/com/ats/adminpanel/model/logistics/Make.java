package com.ats.adminpanel.model.logistics;
 

public class Make {
	
	private int makeId; 
	private String makeName;  
	private int delStatus;
	
	public int getMakeId() {
		return makeId;
	}
	public void setMakeId(int makeId) {
		this.makeId = makeId;
	}
	public String getMakeName() {
		return makeName;
	}
	public void setMakeName(String makeName) {
		this.makeName = makeName;
	}
	public int getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}
	@Override
	public String toString() {
		return "Make [makeId=" + makeId + ", makeName=" + makeName + ", delStatus=" + delStatus + "]";
	}
	
	
	

}
