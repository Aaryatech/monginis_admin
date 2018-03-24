package com.ats.adminpanel.model.spprod;


public class MType {

	private boolean isError;
	
	private String message;
	
	private int typeId;
	
	private int subType;
	
	private String typeName;
	
	private int delStatus;

	
	public int getSubType() {
		return subType;
	}

	public void setSubType(int subType) {
		this.subType = subType;
	}

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

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	@Override
	public String toString() {
		return "Type [isError=" + isError + ", message=" + message + ", typeId=" + typeId + ", typeName=" + typeName
				+ ", delStatus=" + delStatus + "]";
	}
	
	
}
