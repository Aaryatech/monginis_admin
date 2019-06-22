package com.ats.adminpanel.model.album;

public class TypeWiseFr {

	private int typeId;
	private String typeName;
	private String frIds;

	public TypeWiseFr() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TypeWiseFr(int typeId, String typeName, String frIds) {
		super();
		this.typeId = typeId;
		this.typeName = typeName;
		this.frIds = frIds;
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

	public String getFrIds() {
		return frIds;
	}

	public void setFrIds(String frIds) {
		this.frIds = frIds;
	}

	@Override
	public String toString() {
		return "TypeWiseFr [typeId=" + typeId + ", typeName=" + typeName + ", frIds=" + frIds + "]";
	}

}
