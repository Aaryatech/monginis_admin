package com.ats.adminpanel.model.logistics;
 
public class MechType {
 
	private int typeId;  
	private String typeName;  
	private int type; 
	private int delStatus;
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}
	@Override
	public String toString() {
		return "MechType [typeId=" + typeId + ", typeName=" + typeName + ", type=" + type + ", delStatus=" + delStatus
				+ "]";
	}
	
	
}
