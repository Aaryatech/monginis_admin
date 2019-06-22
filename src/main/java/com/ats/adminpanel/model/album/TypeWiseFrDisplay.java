package com.ats.adminpanel.model.album;

public class TypeWiseFrDisplay {

	private int typeId;
	private String typeName;
	private String frIds;
	private String frName;

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

	public String getFrIds() {
		return frIds;
	}

	public void setFrIds(String frIds) {
		this.frIds = frIds;
	}
	
	

	public String getFrName() {
		return frName;
	}

	public void setFrName(String frName) {
		this.frName = frName;
	}

	
	
	@Override
	public String toString() {
		return "TypeWiseFrDisplay [typeId=" + typeId + ", typeName=" + typeName + ", frIds=" + frIds + ", frName=" + frName
				+ "]";
	}
	
}
