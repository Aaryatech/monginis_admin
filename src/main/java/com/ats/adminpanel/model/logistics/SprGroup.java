package com.ats.adminpanel.model.logistics;
 

public class SprGroup {
	
	 
	private int groupId;  
	private String groupName; 
	private int delStatus; 
	private int typeId;
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public int getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	@Override
	public String toString() {
		return "SprGroup [groupId=" + groupId + ", groupName=" + groupName + ", delStatus=" + delStatus + ", typeId="
				+ typeId + "]";
	}
	
	
	

}
